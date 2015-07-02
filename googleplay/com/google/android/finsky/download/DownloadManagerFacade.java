package com.google.android.finsky.download;

import android.content.Intent;
import android.net.Uri;
import com.google.android.finsky.utils.ParameterizedRunnable;
import java.util.List;

public interface DownloadManagerFacade {

    public interface Listener {
        void onChange();
    }

    void enqueue(Download download, ParameterizedRunnable<Uri> parameterizedRunnable);

    Uri getFileUriForContentUri(Uri uri);

    Uri getUriFromBroadcast(Intent intent);

    List<DownloadProgress> query(Uri uri, Listener listener);

    void remove(Uri uri);

    void unregisterListener(Listener listener);
}
