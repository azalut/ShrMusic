package com.shrmusic.controller.download;

import com.shrmusic.service.download.DbxDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
}
