package com.google.android.finsky.download;

import android.net.Uri;
import com.google.android.finsky.utils.Objects;

public class DownloadProgress {
    public final long bytesCompleted;
    public final long bytesTotal;
    public final Uri contentUri;
    public final int statusCode;

    public DownloadProgress(Uri contentUri, long bytesCompleted, long bytesTotal, int statusId) {
        this.contentUri = contentUri;
        this.bytesCompleted = bytesCompleted;
        this.bytesTotal = bytesTotal;
        this.statusCode = statusId;
    }

    public String toString() {
        return this.bytesCompleted + "/" + this.bytesTotal + " Status: " + this.statusCode;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof DownloadProgress)) {
            return false;
        }
        DownloadProgress other = (DownloadProgress) o;
        if (this.bytesCompleted == other.bytesCompleted && this.bytesTotal == other.bytesTotal && this.statusCode == other.statusCode && Objects.equal(this.contentUri, other.contentUri)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.contentUri.hashCode();
    }
}
