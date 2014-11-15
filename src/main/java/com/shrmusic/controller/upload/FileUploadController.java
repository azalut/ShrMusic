package com.shrmusic.controller.upload;

import com.shrmusic.service.upload.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping(value = "/upload")
public class FileUploadController {
    @Autowired
    private FileUploadService uploadService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void uploadFile(@RequestParam(value = "name") final String name, @RequestParam("file") MultipartFile file, HttpServletResponse response){
        if (uploadService.handleUpload(name, file)) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
