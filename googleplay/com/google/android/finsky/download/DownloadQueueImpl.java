package com.google.android.finsky.download;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.download.Download.DownloadState;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.PackageManagerUtils;
import com.google.android.finsky.utils.PackageManagerUtils.FreeSpaceListener;
import com.google.android.finsky.utils.ParameterizedRunnable;
import com.google.android.finsky.utils.Utils;
import com.google.android.wallet.instrumentmanager.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class DownloadQueueImpl implements DownloadQueue, DownloadQueueListener {
    private Context mContext;
    private final DownloadManagerFacade mDownloadManager;
    private DownloadProgressManager mDownloadProgressManager;
    private LinkedList<DownloadQueueListener> mListeners;
    private final int mMaxConcurrent;
    private LinkedHashMap<String, Download> mPendingQueue;
    private Uri mPreviousContentUri;
    private int mPreviousProgressStatus;
    private HashMap<String, Download> mRunningMap;

    private abstract class ListenerNotifier implements Runnable {
        UpdateListenerType mType;

        abstract void updateListener(DownloadQueueListener downloadQueueListener);

        public ListenerNotifier(UpdateListenerType type) {
            this.mType = type;
        }

        public void run() {
            Iterator i$ = DownloadQueueImpl.this.mListeners.iterator();
            while (i$.hasNext()) {
                try {
                    updateListener((DownloadQueueListener) i$.next());
                } catch (Exception e) {
                    FinskyLog.wtf(e, "Download listener threw an exception during " + this.mType, new Object[0]);
                }
            }
        }
    }

    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$download$Download$DownloadState;
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType;

        static {
            $SwitchMap$com$google$android$finsky$download$Download$DownloadState = new int[DownloadState.values().length];
            try {
                $SwitchMap$com$google$android$finsky$download$Download$DownloadState[DownloadState.DOWNLOADING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$Download$DownloadState[DownloadState.CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$Download$DownloadState[DownloadState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$Download$DownloadState[DownloadState.SUCCESS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$Download$DownloadState[DownloadState.QUEUED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$Download$DownloadState[DownloadState.UNQUEUED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType = new int[UpdateListenerType.values().length];
            try {
                $SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType[UpdateListenerType.NOTIFICATION_CLICKED.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType[UpdateListenerType.COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType[UpdateListenerType.PROGRESS.ordinal()] = 3;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType[UpdateListenerType.CANCEL.ordinal()] = 4;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType[UpdateListenerType.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType[UpdateListenerType.START.ordinal()] = 6;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    private class PurgeCacheCallback implements FreeSpaceListener {
        private PurgeCacheCallback() {
        }

        public void onComplete(boolean success) {
            if (!success) {
                FinskyLog.w("Could not free required amount of space for download", new Object[0]);
            }
            new Handler(DownloadQueueImpl.this.mContext.getMainLooper()).post(new StartNextDownloadRunnable());
        }
    }

    private class StartNextDownloadRunnable implements Runnable {
        private StartNextDownloadRunnable() {
        }

        public void run() {
            Utils.ensureOnMainThread();
            if (DownloadQueueImpl.this.mRunningMap.size() < DownloadQueueImpl.this.mMaxConcurrent) {
                Download toStart = null;
                LinkedList<String> toRemove = new LinkedList();
                for (String key : DownloadQueueImpl.this.mPendingQueue.keySet()) {
                    Download current = (Download) DownloadQueueImpl.this.mPendingQueue.get(key);
                    toRemove.add(key);
                    if (current.getState().equals(DownloadState.QUEUED)) {
                        long downloadSize = current.getMaximumSize();
                        long dataSpace = Storage.dataPartitionAvailableSpace();
                        long externalStorageSpace = Storage.externalStorageAvailableSpace();
                        if (current.getRequestedDestination() != null) {
                            if (externalStorageSpace < downloadSize) {
                                current.setHttpStatus(198);
                                DownloadQueueImpl.this.setDownloadState(current, DownloadState.ERROR);
                            }
                        } else if (dataSpace < downloadSize) {
                            current.setHttpStatus(198);
                            DownloadQueueImpl.this.setDownloadState(current, DownloadState.ERROR);
                        }
                        toStart = current;
                        break;
                    }
                }
                Iterator i$ = toRemove.iterator();
                while (i$.hasNext()) {
                    DownloadQueueImpl.this.mPendingQueue.remove((String) i$.next());
                }
                DownloadQueueImpl.this.startDownload(toStart);
                if (DownloadQueueImpl.this.mRunningMap.size() == 0 && DownloadQueueImpl.this.mDownloadProgressManager != null) {
                    DownloadQueueImpl.this.mDownloadProgressManager.cleanup();
                    DownloadQueueImpl.this.mDownloadProgressManager = null;
                }
            }
        }
    }

    private enum UpdateListenerType {
        NOTIFICATION_CLICKED,
        COMPLETE,
        PROGRESS,
        CANCEL,
        START,
        ERROR
    }

    public DownloadQueueImpl(Context context, int maxConcurrent, DownloadManagerFacade downloadManager) {
        this.mPreviousContentUri = null;
        this.mPreviousProgressStatus = -1;
        setupQueue();
        this.mMaxConcurrent = maxConcurrent;
        this.mDownloadManager = downloadManager;
        this.mContext = context;
        this.mListeners = new LinkedList();
        this.mListeners.add(this);
    }

    public DownloadQueueImpl(Context context, DownloadManagerFacade downloadManager) {
        this(context, 1, downloadManager);
    }

    private void setupQueue() {
        this.mPendingQueue = Maps.newLinkedHashMap();
        this.mRunningMap = Maps.newHashMap();
    }

    public DownloadManagerFacade getDownloadManager() {
        return this.mDownloadManager;
    }

    public void add(Download download) {
        Utils.ensureOnMainThread();
        if (!download.getState().equals(DownloadState.UNQUEUED)) {
            FinskyLog.wtf("Added download %s (url=%s) while in state %s", download, download.getUrl(), download.getState());
        }
        if (getExisting(download.getUrl()) != null) {
            FinskyLog.wtf("Added download %s (url=%s) while existing found %s (url=%s)", download, download.getUrl(), getExisting(download.getUrl()), getExisting(download.getUrl()).getUrl());
        }
        FinskyLog.d("Download %s added to DownloadQueue", download.toString());
        this.mPendingQueue.put(download.getUrl(), download);
        if (this.mDownloadProgressManager == null) {
            this.mDownloadProgressManager = new DownloadProgressManager(this);
        }
        setDownloadState(download, DownloadState.QUEUED);
        startNextDownload();
    }

    public void addRecoveredDownload(Download download) {
        Utils.ensureOnMainThread();
        String url = download.getUrl();
        FinskyLog.d("Download queue recovering download %s.", download);
        setDownloadState(download, DownloadState.DOWNLOADING);
        this.mRunningMap.put(url, download);
        if (this.mDownloadProgressManager == null) {
            this.mDownloadProgressManager = new DownloadProgressManager(this);
        }
    }

    public Download getByPackageName(String packageName) {
        Utils.ensureOnMainThread();
        if (TextUtils.isEmpty(packageName)) {
            throw new IllegalArgumentException("empty packageName");
        }
        for (Download pending : this.mPendingQueue.values()) {
            if (packageName.equals(pending.getPackageName())) {
                return pending;
            }
        }
        for (Download running : this.mRunningMap.values()) {
            if (packageName.equals(running.getPackageName())) {
                return running;
            }
        }
        return null;
    }

    void notifyListeners(UpdateListenerType type, final Download download) {
        Runnable r;
        final DownloadProgress currentProgress = download == null ? null : download.getProgress();
        final int currentHttpStatus = download == null ? -1 : download.getHttpStatus();
        switch (AnonymousClass8.$SwitchMap$com$google$android$finsky$download$DownloadQueueImpl$UpdateListenerType[type.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                r = new ListenerNotifier(type) {
                    public void updateListener(DownloadQueueListener listener) {
                        listener.onNotificationClicked(download);
                    }
                };
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                r = new ListenerNotifier(type) {
                    public void updateListener(DownloadQueueListener listener) {
                        listener.onComplete(download);
                    }
                };
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                r = new ListenerNotifier(type) {
                    public void updateListener(DownloadQueueListener listener) {
                        listener.onProgress(download, currentProgress);
                    }
                };
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                r = new ListenerNotifier(type) {
                    public void updateListener(DownloadQueueListener listener) {
                        listener.onCancel(download);
                    }
                };
                break;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                r = new ListenerNotifier(type) {
                    public void updateListener(DownloadQueueListener listener) {
                        listener.onError(download, currentHttpStatus);
                    }
                };
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                r = new ListenerNotifier(type) {
                    public void updateListener(DownloadQueueListener listener) {
                        listener.onStart(download);
                    }
                };
                break;
            default:
                throw new IllegalStateException("Bad listener type.");
        }
        new Handler(Looper.getMainLooper()).post(r);
    }

    public void addListener(DownloadQueueListener listener) {
        Utils.ensureOnMainThread();
        this.mListeners.add(listener);
    }

    public void removeListener(DownloadQueueListener listener) {
        Utils.ensureOnMainThread();
        this.mListeners.remove(listener);
    }

    public Download getDownloadByContentUri(Uri uri) {
        String uriString;
        Utils.ensureOnMainThread();
        if (uri != null) {
            uriString = uri.toString();
        } else {
            uriString = null;
        }
        if (TextUtils.isEmpty(uriString)) {
            return null;
        }
        for (Download checkRunning : this.mRunningMap.values()) {
            if (uri.equals(checkRunning.getContentUri())) {
                return checkRunning;
            }
        }
        return null;
    }

    Download getExisting(String url) {
        if (this.mRunningMap.containsKey(url)) {
            return (Download) this.mRunningMap.get(url);
        }
        if (this.mPendingQueue.containsKey(url)) {
            return (Download) this.mPendingQueue.get(url);
        }
        return null;
    }

    private void remove(Download download) {
        FinskyLog.d("Download %s removed from DownloadQueue", download.toString());
        String url = download.getUrl();
        if (this.mPendingQueue.containsKey(url)) {
            this.mPendingQueue.remove(url);
            return;
        }
        this.mRunningMap.remove(download.getUrl());
        startNextDownload();
    }

    public void cancel(Download download) {
        Utils.ensureOnMainThread();
        if (download != null && !download.isCompleted()) {
            if (download.getState().equals(DownloadState.DOWNLOADING)) {
                this.mDownloadManager.remove(download.getContentUri());
            }
            setDownloadState(download, DownloadState.CANCELLED);
        }
    }

    public void notifyClicked(Download download) {
        FinskyLog.d("%s: onNotificationClicked", download.toString());
        notifyListeners(UpdateListenerType.NOTIFICATION_CLICKED, download);
    }

    public void notifyProgress(Download download, DownloadProgress progress) {
        if (!progress.equals(download.getProgress())) {
            download.setProgress(progress);
            boolean logProgress = false;
            if (progress.statusCode != this.mPreviousProgressStatus) {
                logProgress = true;
            } else if (this.mPreviousContentUri == null || !this.mPreviousContentUri.equals(download.getContentUri())) {
                logProgress = true;
            }
            if (logProgress) {
                FinskyLog.d("%s: onProgress %s.", download.toString(), progress.toString());
                this.mPreviousContentUri = download.getContentUri();
                this.mPreviousProgressStatus = progress.statusCode;
            }
            notifyListeners(UpdateListenerType.PROGRESS, download);
        }
    }

    public void setDownloadState(Download download, DownloadState state) {
        download.setState(state);
        switch (AnonymousClass8.$SwitchMap$com$google$android$finsky$download$Download$DownloadState[state.ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                notifyListeners(UpdateListenerType.START, download);
                return;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                notifyListeners(UpdateListenerType.CANCEL, download);
                return;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                notifyListeners(UpdateListenerType.ERROR, download);
                return;
            case R.styleable.WalletImFormEditText_required /*4*/:
                notifyListeners(UpdateListenerType.COMPLETE, download);
                return;
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return;
            default:
                FinskyLog.wtf("enum %s", state);
                return;
        }
    }

    private void startNextDownload() {
        if (this.mRunningMap.size() < this.mMaxConcurrent) {
            long largestDownloadSize = 0;
            for (String key : this.mPendingQueue.keySet()) {
                largestDownloadSize = Math.max(((Download) this.mPendingQueue.get(key)).getMaximumSize(), largestDownloadSize);
            }
            PackageManagerUtils.freeStorageAndNotify(this.mContext, largestDownloadSize, new PurgeCacheCallback());
        }
    }

    void startDownload(Download download) {
        if (download != null) {
            FinskyLog.d("Download %s starting", download.toString());
            this.mRunningMap.put(download.getUrl(), download);
            enqueueDownload(download);
        }
    }

    private void enqueueDownload(final Download download) {
        this.mDownloadManager.enqueue(download, new ParameterizedRunnable<Uri>() {
            public void run(final Uri uri) {
                FinskyLog.d("Enqueued %s as %s", download, uri.toString());
                new Handler(FinskyApp.get().getMainLooper()).post(new Runnable() {
                    public void run() {
                        if (download.isCompleted()) {
                            DownloadQueueImpl.this.mDownloadManager.remove(uri);
                            return;
                        }
                        download.setContentUri(uri);
                        DownloadQueueImpl.this.setDownloadState(download, DownloadState.DOWNLOADING);
                    }
                });
            }
        });
    }

    public void onComplete(Download download) {
        FinskyLog.d("%s: onComplete", download.toString());
        remove(download);
    }

    public void onCancel(Download download) {
        FinskyLog.d("%s: onCancel", download.toString());
        remove(download);
        removeFromDownloadManager(download);
    }

    public void onError(Download download, int httpStatus) {
        FinskyLog.d("%s: onError %d.", download.toString(), Integer.valueOf(httpStatus));
        if (httpStatus == 403 || httpStatus == 401) {
            FinskyApp.get().getVendingApi().getApiContext().scheduleReauthentication(true);
        }
        remove(download);
        removeFromDownloadManager(download);
    }

    public void onStart(Download download) {
        FinskyLog.d("%s: onStart", download.toString());
    }

    private void removeFromDownloadManager(Download download) {
        Uri contentUri = download.getContentUri();
        if (contentUri != null) {
            this.mDownloadManager.remove(contentUri);
        }
    }

    public void onNotificationClicked(Download download) {
    }

    public void onProgress(Download download, DownloadProgress progress) {
    }

    public List<DownloadProgress> getRunningDownloads() {
        return this.mDownloadManager.query(null, null);
    }

    public void release(Uri contentUri) {
        this.mDownloadManager.remove(contentUri);
    }
}
