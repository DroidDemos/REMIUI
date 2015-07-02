package com.google.android.finsky.download;

import android.net.Uri;
import com.google.android.finsky.download.Download.DownloadState;
import com.google.android.finsky.download.obb.Obb;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import java.util.EnumSet;

public class DownloadImpl implements Download {
    private static final EnumSet<DownloadState> COMPLETED_STATES;
    private long mActualSize;
    private Uri mContentUri;
    private final String mCookieName;
    private final String mCookieValue;
    private final Uri mFileUri;
    private int mHttpStatus;
    private final boolean mInvisible;
    private DownloadProgress mLastProgress;
    private final long mMaximumSize;
    private final Obb mObb;
    private final String mPackageName;
    DownloadState mState;
    private final String mTitle;
    private final String mUrl;
    private final boolean mWifiOnly;

    static {
        COMPLETED_STATES = EnumSet.of(DownloadState.CANCELLED, DownloadState.ERROR, DownloadState.SUCCESS);
    }

    public DownloadImpl(String url, String title, String packageName, String cookieName, String cookieValue, Uri fileUri, long actualSize, long maximumSize, Obb obb, boolean wifiOnly, boolean invisible) {
        this.mUrl = url;
        this.mTitle = title;
        this.mPackageName = packageName;
        this.mCookieName = cookieName;
        this.mCookieValue = cookieValue;
        this.mFileUri = fileUri;
        this.mActualSize = actualSize;
        this.mMaximumSize = maximumSize;
        this.mObb = obb;
        this.mWifiOnly = wifiOnly;
        this.mInvisible = invisible;
        setState(DownloadState.UNQUEUED);
    }

    public DownloadProgress getProgress() {
        Utils.ensureOnMainThread();
        return this.mLastProgress;
    }

    public void setProgress(DownloadProgress progress) {
        Utils.ensureOnMainThread();
        this.mLastProgress = progress;
        if (this.mActualSize == -1 && progress != null && progress.bytesTotal > 0) {
            this.mActualSize = progress.bytesTotal;
        }
    }

    public String getPackageName() {
        Utils.ensureOnMainThread();
        return this.mPackageName;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public boolean isCompleted() {
        return COMPLETED_STATES.contains(this.mState);
    }

    public String getUrl() {
        Utils.ensureOnMainThread();
        return this.mUrl;
    }

    public Uri getContentUri() {
        Utils.ensureOnMainThread();
        return this.mContentUri;
    }

    public void setContentUri(Uri uri) {
        this.mContentUri = uri;
    }

    public long getMaximumSize() {
        return this.mMaximumSize;
    }

    public DownloadState getState() {
        Utils.ensureOnMainThread();
        return this.mState;
    }

    public Uri getRequestedDestination() {
        return this.mFileUri;
    }

    public boolean isObb() {
        return this.mObb != null;
    }

    public Obb getObb() {
        return this.mObb;
    }

    public String getCookieName() {
        return this.mCookieName;
    }

    public String getCookieValue() {
        return this.mCookieValue;
    }

    public boolean getInvisible() {
        return this.mInvisible;
    }

    public boolean getWifiOnly() {
        return this.mWifiOnly;
    }

    public void setState(DownloadState state) {
        if (isCompleted()) {
            throw new IllegalStateException("Received state update when already completed.");
        }
        if (this.mState == state) {
            FinskyLog.d("Duplicate state set for '%s' (%s). Already in that state", this, this.mState);
        } else {
            FinskyLog.d("%s from %s to %s.", this, this.mState, state);
        }
        this.mState = state;
    }

    public int hashCode() {
        return this.mUrl.hashCode();
    }

    public void setHttpStatus(int httpStatus) {
        this.mHttpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return this.mHttpStatus;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DownloadImpl)) {
            return false;
        }
        return this.mUrl.equals(((DownloadImpl) o).mUrl);
    }

    public String toString() {
        String name;
        if (this.mObb != null) {
            name = "obb-for-" + this.mObb.getPackage();
        } else if (this.mPackageName == null) {
            name = "self-update-download";
        } else {
            name = this.mPackageName;
        }
        if (name == null) {
            return "untitled-download";
        }
        return name;
    }
}
