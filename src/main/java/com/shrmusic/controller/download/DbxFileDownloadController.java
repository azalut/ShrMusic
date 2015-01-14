package com.shrmusic.controller.download;

import com.shrmusic.service.download.DbxDownloadService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        byte[] file = dbxDownloadService.getFile(filename, extension);
        if(file == null){
            throw new FileNotFoundException("File was not found on your Dropbox account");
        }
        response.setContentType("audio/mpeg3");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        return file;
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
}
