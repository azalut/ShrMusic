package com.shrmusic.controller.download;

import com.shrmusic.entity.DownloadedFile;
import com.shrmusic.service.download.DbxDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/download")
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
        DownloadedFile downloadedFile = dbxDownloadService.getFile(filename + extension);
        if(downloadedFile == null){
            throw new FileNotFoundException("File was not found on your Dropbox account");
        }
        response.setContentType("audio/mpeg3");
        response.setHeader("Content-Disposition", "attachment; filename=" + filename + extension);
        return downloadedFile.getFileBytes();
    }

    @RequestMapping(value = "/zip")
    @ResponseStatus(HttpStatus.OK)
    public byte[] getFilesAsZipArchive(@RequestParam("filenames[]") final String[] filenames, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Disposition", "attachment; filename=files.zip");
        return dbxDownloadService.createZipArchiveWithFilesFromDbx(filenames);
    }
}
