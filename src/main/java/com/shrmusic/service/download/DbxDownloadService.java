package com.shrmusic.service.download;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.shrmusic.entity.DownloadedFile;
import com.shrmusic.service.CurrentAuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param extension
     * @return null if file was not found on dropbox, byte array if the file was found
     * @throws IOException
     */
    public DownloadedFile getFile(final String filename, final String extension) throws IOException {
        final String completeFilename = filename + extension;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DbxClient client = currentAuthenticatedUserService.getClient();
        try {
            DbxEntry.File file = client.getFile("/" + completeFilename, null, outputStream);
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
}
