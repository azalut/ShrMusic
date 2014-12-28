package com.shrmusic.controller.download;

import com.shrmusic.service.download.DbxDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping(value = "account/{id}/download")
public class DbxFileDownloadController {
    @Autowired
    private DbxDownloadService dbxDownloadService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<String> getFilenameList() {
        return dbxDownloadService.getFilenameList();
    }
}
