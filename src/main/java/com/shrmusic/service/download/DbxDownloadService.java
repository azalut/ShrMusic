package com.shrmusic.service.download;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.shrmusic.entity.DownloadedFile;
import com.shrmusic.service.CurrentAuthenticatedUserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class DbxDownloadService {
    @Autowired
    private CurrentAuthenticatedUserService currentAuthenticatedUserService;

    public List<String> getFilenameList() {
        DbxClient client = currentAuthenticatedUserService.getClient();
        try {
            DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
            return listing.children.stream().map(n -> n.name).collect(Collectors.toList());
        } catch (DbxException e) {
            return Collections.emptyList();
        }
    }

    /**
     *
     * @param filename
     * @return null if file was not found on dropbox, byte array if the file was found
     * @throws IOException
     */
    public DownloadedFile getFile(final String filename) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DbxClient client = currentAuthenticatedUserService.getClient();
        try {
            DbxEntry.File file = client.getFile("/" + filename, null, outputStream);
            if(file == null){
                return null;
            }
            return new DownloadedFile(file.name, outputStream.toByteArray());
        } catch (DbxException e){
            e.printStackTrace();
            return null;
        } finally {
            outputStream.close();
        }
    }

    /**
     *
     * @param filenames
     * @return returns .zip file that contains files previously downloaded from user's dropbox account
     * @throws IOException
     */
    public byte[] createZipArchiveWithFilesFromDbx(final String[] filenames) throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

        for (String filename : filenames) {
            DownloadedFile downloadedFile = getFile(filename);
            zipOutputStream.putNextEntry(createZipEntry(downloadedFile.getFilename(), downloadedFile.getFileBytes().length));
            zipOutputStream.write(downloadedFile.getFileBytes());
            zipOutputStream.closeEntry();
        }
        IOUtils.closeQuietly(zipOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private ZipEntry createZipEntry(String filename, long size){
        ZipEntry zipEntry = new ZipEntry(filename);
        zipEntry.setSize(size);
        return zipEntry;
    }
}
