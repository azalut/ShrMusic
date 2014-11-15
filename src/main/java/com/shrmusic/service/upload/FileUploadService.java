package com.shrmusic.service.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Service
public class FileUploadService {
    //TODO: DropboxUploadService has to be created and injected here to send file to dropbox account

    public boolean handleUpload(final String name, final MultipartFile file){
        try {
            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(name + ".mp3")));
                outputStream.write(bytes);
                outputStream.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
