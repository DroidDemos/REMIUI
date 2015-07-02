package com.google.android.play.analytics;

import android.util.Log;
import com.google.android.play.utils.Assertions;
import com.google.android.play.utils.FileModifiedDateComparator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class RollingFileStream<T> {
    private final WriteCallbacks<T> mCallbacks;
    private File mCurrentOutputFile;
    private final File mDirectory;
    private final String mFileNamePrefix;
    private final String mFileNameSuffix;
    private FileOutputStream mFileOutputStream;
    private final long mMaxStorageSize;
    private final ArrayList<File> mReadFiles;
    private final long mRecommendedFileSize;
    private final ArrayList<File> mWrittenFiles;

    public interface WriteCallbacks<T> {
        void onNewOutputFile();

        void onWrite(T t, OutputStream outputStream) throws IOException;
    }

    public RollingFileStream(File directory, String fileNamePrefix, String fileNameSuffix, long recommendedFileSize, long maxStorageSize, WriteCallbacks<T> callbacks) {
        boolean z;
        boolean z2 = true;
        Assertions.checkArgument(recommendedFileSize > 0, "recommendedFileSize must be positive");
        if (maxStorageSize > 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkArgument(z, "maxStorageSize must be positive");
        if (callbacks == null) {
            z2 = false;
        }
        Assertions.checkArgument(z2, "callbacks cannot be null");
        this.mDirectory = directory;
        this.mFileNamePrefix = fileNamePrefix;
        this.mFileNameSuffix = fileNameSuffix;
        this.mRecommendedFileSize = recommendedFileSize;
        this.mMaxStorageSize = maxStorageSize;
        this.mCallbacks = callbacks;
        createNewOutputFile();
        if (this.mCurrentOutputFile == null) {
            Log.e("RollingFileStream", "Could not create a temp file with prefix: \"" + this.mFileNamePrefix + "\" and suffix: \"" + this.mFileNameSuffix + "\" in dir: \"" + this.mDirectory.getAbsolutePath() + "\".");
        }
        this.mWrittenFiles = new ArrayList();
        this.mReadFiles = new ArrayList();
        loadWrittenFiles();
        ensureMaxStorageSizeLimit();
    }

    private void createNewOutputFile() {
        if (!this.mDirectory.exists()) {
            this.mDirectory.mkdirs();
        }
        this.mCurrentOutputFile = null;
        try {
            this.mCurrentOutputFile = File.createTempFile(this.mFileNamePrefix, this.mFileNameSuffix, this.mDirectory);
            this.mFileOutputStream = new FileOutputStream(this.mCurrentOutputFile);
            this.mCallbacks.onNewOutputFile();
        } catch (FileNotFoundException e) {
            if (this.mCurrentOutputFile != null) {
                this.mCurrentOutputFile.delete();
            }
            this.mCurrentOutputFile = null;
        } catch (IOException e2) {
        }
    }

    private void ensureMaxStorageSizeLimit() {
        long totalSize = 0;
        Iterator i$ = this.mReadFiles.iterator();
        while (i$.hasNext()) {
            totalSize += ((File) i$.next()).length();
        }
        i$ = this.mWrittenFiles.iterator();
        while (i$.hasNext()) {
            totalSize += ((File) i$.next()).length();
        }
        if (this.mCurrentOutputFile != null) {
            totalSize += this.mCurrentOutputFile.length();
        }
        int numPurged = 0;
        while (totalSize > this.mMaxStorageSize) {
            numPurged++;
            File f;
            if (this.mReadFiles.size() > 0) {
                f = (File) this.mReadFiles.remove(0);
                totalSize -= f.length();
                f.delete();
            } else if (this.mWrittenFiles.size() > 0) {
                f = (File) this.mWrittenFiles.remove(0);
                totalSize -= f.length();
                f.delete();
            } else if (this.mCurrentOutputFile != null) {
                totalSize -= this.mCurrentOutputFile.length();
                this.mCurrentOutputFile.delete();
                this.mCurrentOutputFile = null;
            }
        }
        if (numPurged > 0) {
            Log.d("RollingFileStream", numPurged + " files were purged due to exceeding total storage size of: " + this.mMaxStorageSize);
        }
    }

    private void loadWrittenFiles() {
        if (!this.mDirectory.exists()) {
            this.mDirectory.mkdirs();
        }
        Assertions.checkState(this.mDirectory.isDirectory(), "Expected a directory for path: " + this.mDirectory.getAbsolutePath());
        this.mWrittenFiles.clear();
        for (File f : this.mDirectory.listFiles()) {
            if (f.isFile() && !f.equals(this.mCurrentOutputFile)) {
                if (f.length() == 0) {
                    f.delete();
                } else {
                    this.mWrittenFiles.add(f);
                }
            }
        }
        Collections.sort(this.mWrittenFiles, FileModifiedDateComparator.INSTANCE);
    }

    public boolean hasUnreadFiles() {
        return !this.mWrittenFiles.isEmpty();
    }

    public long totalUnreadFileLength() {
        long totalFileLength = 0;
        for (int i = 0; i < this.mWrittenFiles.size(); i++) {
            totalFileLength += ((File) this.mWrittenFiles.get(i)).length();
        }
        return totalFileLength;
    }

    public boolean write(T data) throws IOException {
        if (this.mCurrentOutputFile == null) {
            createNewOutputFile();
            if (this.mCurrentOutputFile == null) {
                return false;
            }
        }
        this.mCallbacks.onWrite(data, this.mFileOutputStream);
        this.mFileOutputStream.flush();
        if (!shouldStartNewOutputFile()) {
            return false;
        }
        this.mFileOutputStream.close();
        this.mWrittenFiles.add(this.mCurrentOutputFile);
        createNewOutputFile();
        ensureMaxStorageSizeLimit();
        return true;
    }

    private boolean shouldStartNewOutputFile() {
        return this.mCurrentOutputFile != null && this.mCurrentOutputFile.length() >= this.mRecommendedFileSize;
    }

    public long peekNextReadLength() {
        if (this.mWrittenFiles.isEmpty()) {
            return -1;
        }
        return ((File) this.mWrittenFiles.get(0)).length();
    }

    public byte[] read() throws IOException {
        if (this.mWrittenFiles.isEmpty()) {
            Log.e("RollingFileStream", "This method should never be called when there are no written files.");
            return null;
        }
        File f = (File) this.mWrittenFiles.remove(0);
        byte[] data = toByteArray(f);
        this.mReadFiles.add(f);
        return data;
    }

    public void deleteAllReadFiles() {
        Iterator i$ = this.mReadFiles.iterator();
        while (i$.hasNext()) {
            ((File) i$.next()).delete();
        }
        this.mReadFiles.clear();
    }

    public void markAllFilesAsUnread() {
        this.mWrittenFiles.addAll(this.mReadFiles);
        Collections.sort(this.mWrittenFiles, FileModifiedDateComparator.INSTANCE);
        this.mReadFiles.clear();
    }

    private byte[] toByteArray(File f) throws IOException {
        long size = f.length();
        if (size > 2147483647L) {
            throw new OutOfMemoryError("Too large to fit in a byte array: " + size);
        } else if (size == 0) {
            return new byte[0];
        } else {
            FileInputStream fis = new FileInputStream(f);
            try {
                byte[] data = new byte[((int) size)];
                int totalBytesRead = 0;
                while (totalBytesRead < data.length) {
                    int bytesRead = fis.read(data, totalBytesRead, data.length - totalBytesRead);
                    if (bytesRead == -1) {
                        throw new IOException("Unexpected EOS: " + data.length + ", " + totalBytesRead);
                    }
                    totalBytesRead += bytesRead;
                }
                return data;
            } finally {
                fis.close();
            }
        }
    }
}
