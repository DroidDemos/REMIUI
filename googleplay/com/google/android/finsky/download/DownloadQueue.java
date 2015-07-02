package com.google.android.finsky.download;

import android.net.Uri;
import com.google.android.finsky.download.Download.DownloadState;
import java.util.List;

public interface DownloadQueue {
    void add(Download download);

    void addListener(DownloadQueueListener downloadQueueListener);

    void addRecoveredDownload(Download download);

    void cancel(Download download);

    Download getByPackageName(String str);

    Download getDownloadByContentUri(Uri uri);

    DownloadManagerFacade getDownloadManager();

    List<DownloadProgress> getRunningDownloads();

    void notifyClicked(Download download);

    void notifyProgress(Download download, DownloadProgress downloadProgress);

    void release(Uri uri);

    void setDownloadState(Download download, DownloadState downloadState);
}
