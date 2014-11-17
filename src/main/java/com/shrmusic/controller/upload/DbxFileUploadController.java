package com.shrmusic.controller.upload;

import com.shrmusic.service.upload.DbxUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/account/{id}/upload")
public class DbxFileUploadController {
    @Autowired
    private DbxUploadService dbxUploadService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void uploadFiles(@RequestParam("files") MultipartFile[] files, @PathVariable("id") final Long id,
                            HttpServletResponse response){
        if(dbxUploadService.uploadFiles(id, files)){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
