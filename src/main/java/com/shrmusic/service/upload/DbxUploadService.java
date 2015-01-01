package com.shrmusic.service.upload;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWriteMode;
import com.shrmusic.service.CurrentAuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class DbxUploadService {
    @Autowired
    private CurrentAuthenticatedUserService currentAuthenticatedUserService;

    public boolean uploadFiles(MultipartFile[] files){
        DbxClient client = currentAuthenticatedUserService.getClient();
        for (MultipartFile file : files) {
            try {
                byte[] bytes = file.getBytes();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                client.uploadFile("/" + file.getOriginalFilename(), DbxWriteMode.add(), bytes.length, byteArrayInputStream);
                byteArrayInputStream.close();
            } catch (IOException | DbxException e) {
                return false;
            }
        }
        return true;
    }
}
