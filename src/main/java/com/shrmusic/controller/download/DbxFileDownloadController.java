package com.shrmusic.controller.download;

import com.shrmusic.entity.DownloadedFile;
import com.shrmusic.service.download.DbxDownloadService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping(value = "account/{id}/download")
public class DbxFileDownloadController {
    @Autowired
    private DbxDownloadService dbxDownloadService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<String> getFilenameList() {
        return dbxDownloadService.getFilenameList();
    }

    @RequestMapping(value = "/{filename:.+}{extension:\\.[a-z0-9]+}", method = RequestMethod.GET)
    public byte[] getFileByName(@PathVariable("filename") final String filename, @PathVariable("extension") final String extension,
                                HttpServletResponse response) throws IOException {
        DownloadedFile downloadedFile = dbxDownloadService.getFile(filename, extension);
        if(downloadedFile == null){
            throw new FileNotFoundException("File was not found on your Dropbox account");
        }
        response.setContentType("audio/mpeg3");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        return downloadedFile.getFileBytes();
    }

    @RequestMapping(value = "/costam", method = RequestMethod.POST)
    public byte[] getZippedFiless(@RequestParam("filenames[]") String[] filenames){
        for (String filename : filenames) {
            System.err.println(filename);
        }
        return null;
    }



    @RequestMapping(value = "/zip", produces = "application/zip")
    public byte[] zipFiles(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

        ArrayList<File> files = new ArrayList<>(2);
        files.add(new File("README.md"));

        for (File file : files) {
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            FileInputStream fileInputStream = new FileInputStream(file);

            IOUtils.copy(fileInputStream, zipOutputStream);

            fileInputStream.close();
            zipOutputStream.closeEntry();
        }

        if (zipOutputStream != null) {
            zipOutputStream.finish();
            zipOutputStream.flush();
            IOUtils.closeQuietly(zipOutputStream);
        }
        IOUtils.closeQuietly(bufferedOutputStream);
        IOUtils.closeQuietly(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    @RequestMapping(value = "/zipmp3", produces = "application/zip")
    public byte[] zipMusic(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"test.zip\"");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

        DownloadedFile downloadedFile = dbxDownloadService.getFile("Sal De Sol - I Want Your Soul _Club Mix_", ".mp3");

        ZipEntry zipEntry = new ZipEntry(downloadedFile.getFilename());
        zipEntry.setSize(downloadedFile.getFileBytes().length);

        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(downloadedFile.getFileBytes());
        zipOutputStream.closeEntry();
        IOUtils.closeQuietly(zipOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}
