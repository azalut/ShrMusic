package com.shrmusic.controller.upload;

import com.shrmusic.service.upload.DbxUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/upload")
public class DbxFileUploadController {
    @Autowired
    private DbxUploadService dbxUploadService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void uploadFiles(@RequestParam("files") MultipartFile[] files, HttpServletResponse response){
        if(dbxUploadService.uploadFiles(files)){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
