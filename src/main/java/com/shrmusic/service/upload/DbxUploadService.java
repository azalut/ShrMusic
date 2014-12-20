package com.shrmusic.service.upload;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxWriteMode;
import com.shrmusic.service.DbxAbstractService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class DbxUploadService extends DbxAbstractService {

    public boolean uploadFiles(final Long id, MultipartFile[] files){
        DbxClient client = createDbxClient(id);
        if(client == null){
            return false;
        }
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
