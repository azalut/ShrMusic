package com.shrmusic.service.download;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.shrmusic.service.CurrentAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbxDownloadService {
    @Autowired
    private CurrentAuthUserService currentAuthUserService;

    public List<String> getFilenameList() {
        DbxClient client = currentAuthUserService.getClient();
        try {
            DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
            return listing.children.stream().map(n -> n.name).collect(Collectors.toList());
        } catch (DbxException e) {
            return Collections.emptyList();
        }
    }
}
