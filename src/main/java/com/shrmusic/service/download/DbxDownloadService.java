package com.shrmusic.service.download;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.shrmusic.service.DbxAbstractService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DbxDownloadService extends DbxAbstractService {
    //TODO: finish this class
    public List<String> getFilenameList(final Long id) {
        DbxClient client = createDbxClient(id);
        if (client == null) {
            return Collections.emptyList();
        }
        try {
            DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
            listing.children.stream().forEach(System.out::println);
        } catch (DbxException e) {
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }
}
