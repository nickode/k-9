package com.fsck.k9.backend.imap;


import java.util.List;

import com.fsck.k9.backend.api.Backend;
import com.fsck.k9.backend.api.BackendStorage;
import com.fsck.k9.backend.api.SyncConfig;
import com.fsck.k9.backend.api.SyncListener;
import com.fsck.k9.mail.Flag;
import com.fsck.k9.mail.Folder;
import com.fsck.k9.mail.MessagingException;
import com.fsck.k9.mail.store.imap.ImapStore;
import org.jetbrains.annotations.NotNull;


public class ImapBackend implements Backend {
    private final ImapSync imapSync;
    private final CommandSetFlag commandSetFlag;


    public ImapBackend(String accountName, BackendStorage backendStorage, ImapStore imapStore) {
        imapSync = new ImapSync(accountName, backendStorage, imapStore);
        commandSetFlag = new CommandSetFlag(imapStore);
    }

    @Override
    public void sync(@NotNull String folder, @NotNull SyncConfig syncConfig, @NotNull SyncListener listener,
            Folder providedRemoteFolder) {
        imapSync.sync(folder, syncConfig, listener, providedRemoteFolder);
    }

    @Override
    public void setFlag(@NotNull String folderServerId, @NotNull List<String> messageServerIds, @NotNull Flag flag,
            boolean newState) throws MessagingException {
        commandSetFlag.setFlag(folderServerId, messageServerIds, flag, newState);
    }
}
