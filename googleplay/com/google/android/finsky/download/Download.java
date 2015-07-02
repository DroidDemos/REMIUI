package com.google.android.finsky.download;

import android.net.Uri;
import com.google.android.finsky.download.obb.Obb;

public interface Download {

    public enum DownloadState {
        UNQUEUED,
        QUEUED,
        DOWNLOADING,
        SUCCESS,
        CANCELLED,
        ERROR
    }

    Uri getContentUri();

    String getCookieName();

    String getCookieValue();

    int getHttpStatus();

    boolean getInvisible();

    long getMaximumSize();

    Obb getObb();

    String getPackageName();

    DownloadProgress getProgress();

    Uri getRequestedDestination();

    DownloadState getState();

    String getTitle();

    String getUrl();

    boolean getWifiOnly();

    boolean isCompleted();

    boolean isObb();

    void setContentUri(Uri uri);

    void setHttpStatus(int i);

    void setProgress(DownloadProgress downloadProgress);

    void setState(DownloadState downloadState);
}
