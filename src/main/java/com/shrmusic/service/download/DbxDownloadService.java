package com.shrmusic.service.download;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.shrmusic.service.DbxAbstractService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbxDownloadService extends DbxAbstractService {

    public List<String> getFilenameList(final Long id) {
        DbxClient client = createDbxClient(id);
        if (client == null) {
            return Collections.emptyList();
        }
        try {
            DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
            return listing.children.stream().map(n -> n.name).collect(Collectors.toList());
        } catch (DbxException e) {
            return Collections.emptyList();
        }
    }
}
