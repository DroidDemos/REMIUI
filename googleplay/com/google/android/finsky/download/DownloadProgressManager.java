package com.google.android.finsky.download;

import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.finsky.download.Download.DownloadState;
import com.google.android.finsky.download.DownloadManagerFacade.Listener;
import com.google.android.finsky.utils.BackgroundThreadFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.utils.collections.Maps;
import com.google.android.play.utils.collections.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

class DownloadProgressManager implements Listener {
    private final DownloadManagerFacade mDownloadManager;
    private Map<Uri, DownloadProgress> mDownloadProgressMap;
    private final DownloadQueue mDownloadQueue;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;

    private static class ProgressRunnable implements Runnable {
        private final DownloadQueue downloadQueue;
        private Map<Uri, DownloadProgress> mProgressMap;
        private final Set<Uri> newUris;
        private final Set<Uri> oldUris;

        public ProgressRunnable(DownloadQueue downloadQueue, Set<Uri> oldUris, Set<Uri> newUris, Map<Uri, DownloadProgress> progressMap) {
            this.downloadQueue = downloadQueue;
            this.oldUris = oldUris;
            this.newUris = newUris;
            this.mProgressMap = progressMap;
        }

        public void run() {
            for (Uri uri : this.oldUris) {
                Download download = this.downloadQueue.getDownloadByContentUri(uri);
                if (download != null && download.getState().equals(DownloadState.DOWNLOADING)) {
                    this.downloadQueue.cancel(download);
                }
            }
            for (Uri uri2 : this.newUris) {
                download = this.downloadQueue.getDownloadByContentUri(uri2);
                if (download != null) {
                    DownloadProgress downloadProgress = (DownloadProgress) this.mProgressMap.get(uri2);
                    if (downloadProgress.statusCode == 198) {
                        FinskyLog.d("%s: Caught error 198 while state=%s", download.toString(), download.getState());
                        if (download.getState() == DownloadState.DOWNLOADING) {
                            download.setHttpStatus(198);
                            this.downloadQueue.setDownloadState(download, DownloadState.ERROR);
                        }
                    } else {
                        this.downloadQueue.notifyProgress(download, downloadProgress);
                    }
                }
            }
        }
    }

    public DownloadProgressManager(DownloadQueueImpl downloadQueue) {
        this.mDownloadProgressMap = null;
        this.mDownloadQueue = downloadQueue;
        this.mDownloadManager = downloadQueue.getDownloadManager();
        this.mHandlerThread = BackgroundThreadFactory.createHandlerThread("Download progress manager runner");
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mHandler.post(new Runnable() {
            public void run() {
                DownloadProgressManager.this.onDownloadProgress();
            }
        });
    }

    public void cleanup() {
        this.mHandler.post(new Runnable() {
            public void run() {
                DownloadProgressManager.this.mDownloadManager.unregisterListener(DownloadProgressManager.this);
                DownloadProgressManager.this.mHandlerThread.quit();
            }
        });
    }

    public void onChange() {
        onDownloadProgress();
    }

    private void onDownloadProgress() {
        Utils.ensureNotOnMainThread();
        Set<Uri> oldUris = Sets.newHashSet();
        if (this.mDownloadProgressMap != null) {
            oldUris.addAll(this.mDownloadProgressMap.keySet());
        }
        List<DownloadProgress> progress = this.mDownloadManager.query(null, this);
        Map<Uri, DownloadProgress> newMap = Maps.newHashMap();
        for (DownloadProgress entry : progress) {
            newMap.put(entry.contentUri, entry);
        }
        Map<Uri, DownloadProgress> downloadProgressMap = Collections.unmodifiableMap(newMap);
        if (this.mDownloadProgressMap == null || !this.mDownloadProgressMap.equals(downloadProgressMap)) {
            this.mDownloadProgressMap = downloadProgressMap;
            Set<Uri> newUris = Sets.newHashSet();
            if (this.mDownloadProgressMap != null) {
                newUris.addAll(this.mDownloadProgressMap.keySet());
            }
            oldUris.removeAll(newUris);
            new Handler(Looper.getMainLooper()).post(new ProgressRunnable(this.mDownloadQueue, oldUris, newUris, downloadProgressMap));
        }
    }
}
