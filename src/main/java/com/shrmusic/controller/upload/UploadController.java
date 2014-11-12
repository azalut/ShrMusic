package com.shrmusic.controller.upload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping(value = "/upload")
public class UploadController {
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void uploadFile(@RequestParam(value = "name") final String name, @RequestParam("file") MultipartFile file, HttpServletResponse response){
        try {
            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(name + ".mp3")));
                outputStream.write(bytes);
                outputStream.close();
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
