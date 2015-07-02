package com.google.android.finsky.download;

public interface DownloadQueueListener {
    void onCancel(Download download);

    void onComplete(Download download);

    void onError(Download download, int i);

    void onNotificationClicked(Download download);

    void onProgress(Download download, DownloadProgress downloadProgress);

    void onStart(Download download);
}
