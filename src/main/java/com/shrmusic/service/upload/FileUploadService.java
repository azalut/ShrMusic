package com.shrmusic.service.upload;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    //TODO: DropboxUploadService has to be created and injected here to send file to dropbox account

    public boolean handleUpload(final MultipartFile[] files){
        try {
            for (MultipartFile file : files) {

            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
