package com.google.android.finsky.download.obb;

import com.google.android.finsky.download.Storage;
import com.google.android.finsky.utils.FinskyLog;
import java.io.File;

class ObbImpl implements Obb {
    private final String TEMP_OBB_FILE_PREFIX;
    private String mFileName;
    private final boolean mIsPatch;
    private final String mPackageName;
    private long mSize;
    private int mState;
    private String mUrl;
    private int mVersionCode;

    ObbImpl(boolean isPatch, String packageName, int versionCode, String url, long size, int state) {
        this.mState = -1;
        this.TEMP_OBB_FILE_PREFIX = "temp.";
        this.mIsPatch = isPatch;
        this.mVersionCode = versionCode;
        this.mUrl = url;
        this.mSize = size;
        this.mPackageName = packageName;
        this.mFileName = generateFileName();
        this.mState = state;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObbImpl that = (ObbImpl) o;
        if (this.mFileName == null ? that.mFileName != null : !this.mFileName.equals(that.mFileName)) {
            return false;
        }
        if (this.mIsPatch != that.mIsPatch) {
            return false;
        }
        if (this.mVersionCode != that.mVersionCode) {
            return false;
        }
        if (this.mUrl == null ? that.mUrl != null : !this.mUrl.equals(that.mUrl)) {
            return false;
        }
        if (this.mSize != that.mSize) {
            return false;
        }
        if (this.mState < 0 ? that.mState >= 0 : this.mState != that.mState) {
            return false;
        }
        if (this.mPackageName != null) {
            if (this.mPackageName.equals(that.mPackageName)) {
                return true;
            }
        } else if (that.mPackageName == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str = "%s: %s v:%d %s";
        Object[] objArr = new Object[4];
        objArr[0] = this.mIsPatch ? "Patch" : "Main";
        objArr[1] = this.mPackageName;
        objArr[2] = Integer.valueOf(this.mVersionCode);
        objArr[3] = ObbState.toString(this.mState);
        return String.format(str, objArr);
    }

    private boolean isDownloaded() {
        File file = getFile();
        if (file != null && file.exists() && file.length() == this.mSize) {
            return true;
        }
        return false;
    }

    public void syncStateWithStorage() {
        if (this.mState != 5) {
            if (!Storage.externalStorageAvailable()) {
                setState(4);
            } else if (isDownloaded()) {
                setState(3);
            } else {
                setState(4);
            }
        }
    }

    public File getFile() {
        if (!Storage.externalStorageAvailable() || this.mSize <= 0) {
            return null;
        }
        File destinationDir = ObbFactory.getParentDirectory(this.mPackageName);
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }
        return new File(destinationDir, this.mFileName);
    }

    public File getTempFile() {
        File file = getFile();
        if (file == null) {
            return null;
        }
        return new File(file.getParentFile(), "temp." + file.getName());
    }

    public boolean finalizeTempFile() {
        File obbFile = getFile();
        if (obbFile == null) {
            logFinalizeProblem("main file null");
            return false;
        }
        File tempFile = getTempFile();
        if (tempFile == null) {
            logFinalizeProblem("temp file null");
            return false;
        } else if (tempFile.length() != this.mSize) {
            logFinalizeProblem("size mismatch: tempfile size=" + String.valueOf(tempFile.length()));
            return false;
        } else if (tempFile.renameTo(obbFile)) {
            return true;
        } else {
            logFinalizeProblem("renameTo() returned false");
            return false;
        }
    }

    private void logFinalizeProblem(String tag) {
        try {
            FinskyLog.w("Failure %s while finalizing %s", tag, toString());
            FinskyLog.w(" file=%s, size=%d", this.mFileName, Long.valueOf(this.mSize));
            File destinationDir = ObbFactory.getParentDirectory(this.mPackageName);
            FinskyLog.w(" Contents of %s:", destinationDir.getAbsolutePath());
            if (!destinationDir.exists()) {
                FinskyLog.w(" (Does not exist)", new Object[0]);
            } else if (destinationDir.isDirectory()) {
                File[] files = destinationDir.listFiles();
                if (files == null) {
                    FinskyLog.w(" (listFiles() returned null)", new Object[0]);
                    return;
                }
                for (File existingFile : files) {
                    FinskyLog.w("  name=%s size=%d", existingFile.getName(), Long.valueOf(existingFile.length()));
                }
            } else {
                FinskyLog.w(" (Is not a directory)", new Object[0]);
            }
        } catch (Exception e) {
            FinskyLog.wtf(e, "Unexpected exception", new Object[0]);
        }
    }

    private String getTypeString() {
        return this.mIsPatch ? "patch" : "main";
    }

    private String generateFileName() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTypeString()).append(".").append(this.mVersionCode).append(".").append(this.mPackageName).append(".obb");
        return sb.toString();
    }

    public long getSize() {
        return this.mSize;
    }

    public boolean isPatch() {
        return this.mIsPatch;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getPackage() {
        return this.mPackageName;
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int state) {
        if (state == 5) {
            reset();
        }
        this.mState = state;
    }

    private void reset() {
        this.mVersionCode = -1;
        this.mUrl = "";
        this.mSize = -1;
        this.mFileName = "";
    }
}
