package com.shrmusic.service.upload;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWriteMode;
import com.shrmusic.entity.user.User;
import com.shrmusic.repository.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Locale;

@Service
public class DbxUploadService {
    @Autowired
    private UserJpaRepository userJpaRepository;

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

    private DbxClient createDbxClient(final Long id){
        User user = userJpaRepository.findOne(id);
        if(user == null){
            return null;
        }

        DbxRequestConfig config = new DbxRequestConfig("JavaTutorial/1.0", Locale.getDefault().toString());
        return new DbxClient(config, user.getAccessToken());
    }
}
