package com.google.android.finsky.download;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.download.DownloadManagerFacade.Listener;
import com.google.android.finsky.utils.BackgroundThreadFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.ParameterizedRunnable;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.utils.collections.Lists;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DownloadManagerLegacyImpl implements DownloadManagerFacade {
    private static final Uri CONTENT_URI;
    private static final Uri FROYO_CONTENT_URI;
    private static final String[] QUERY_FILENAME_PROJECTION;
    private static Boolean sDownloadManagerUsesFroyoStrings;
    private ContentObserver mContentObserver;
    private final ContentResolver mContentResolver;
    private final Handler mHandler;
    private Listener mListener;
    private Cursor mListenerCursor;

    static {
        CONTENT_URI = Uri.parse("content://downloads/my_downloads");
        FROYO_CONTENT_URI = Uri.parse("content://downloads/download");
        sDownloadManagerUsesFroyoStrings = null;
        QUERY_FILENAME_PROJECTION = new String[]{"_data"};
    }

    protected DownloadManagerLegacyImpl(Context context) {
        this.mContentResolver = context.getContentResolver();
        HandlerThread thread = BackgroundThreadFactory.createHandlerThread("download-manager-thread");
        thread.start();
        this.mHandler = new Handler(thread.getLooper());
        this.mContentObserver = new ContentObserver(this.mHandler) {
            public boolean deliverSelfNotifications() {
                return false;
            }

            public void onChange(boolean selfChange) {
                DownloadManagerLegacyImpl.this.onChange();
            }
        };
    }

    public void enqueue(Download download, final ParameterizedRunnable<Uri> listener) {
        if (FinskyLog.DEBUG && Build.MODEL.equals("google_sdk")) {
            FinskyLog.d("Skip download of %s because emulator", download.toString());
            return;
        }
        final ContentValues request = makeRequest(download);
        this.mHandler.post(new Runnable() {
            public void run() {
                try {
                    Uri uri = DownloadManagerLegacyImpl.this.mContentResolver.insert(DownloadManagerLegacyImpl.CONTENT_URI, request);
                    DownloadManagerLegacyImpl.sniffDownloadManagerVersion(uri);
                    if (listener != null) {
                        listener.run(uri);
                    }
                } catch (IllegalArgumentException e) {
                    FinskyLog.e(e, "Unable to insert download request for %s", request.toString());
                }
            }
        });
    }

    private ContentValues makeRequest(Download download) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("otheruid", Integer.valueOf(1000));
        contentValues.put("uri", download.getUrl());
        Uri fileUri = download.getRequestedDestination();
        if (fileUri != null) {
            contentValues.put("destination", Integer.valueOf(4));
            contentValues.put("hint", fileUri.toString());
        } else {
            contentValues.put("destination", Integer.valueOf(2));
        }
        contentValues.put("notificationpackage", FinskyApp.get().getPackageName());
        contentValues.put("notificationclass", DownloadBroadcastReceiver.class.getName());
        String cookieName = download.getCookieName();
        String cookieValue = download.getCookieValue();
        if (!(cookieName == null || cookieValue == null)) {
            contentValues.put("cookiedata", cookieName + "=" + cookieValue);
        }
        boolean invisible = download.getInvisible();
        String title = download.getTitle();
        if (invisible || TextUtils.isEmpty(title)) {
            contentValues.put("visibility", Integer.valueOf(2));
        } else {
            contentValues.put("visibility", Integer.valueOf(0));
            contentValues.put("title", title);
        }
        if (download.getWifiOnly()) {
            contentValues.put("allowed_network_types", Integer.valueOf(2));
            contentValues.put("is_public_api", Boolean.valueOf(true));
            contentValues.put("allow_roaming", Boolean.valueOf(true));
        }
        return contentValues;
    }

    public void remove(final Uri uri) {
        this.mHandler.post(new Runnable() {
            public void run() {
                DownloadManagerLegacyImpl.this.synchronousRemove(uri);
            }
        });
    }

    private void synchronousRemove(Uri uri) {
        Utils.ensureNotOnMainThread();
        if (uri != null && uri.toString() != null && !uri.equals(Uri.EMPTY)) {
            this.mContentResolver.delete(uri, null, null);
        }
    }

    public List<DownloadProgress> query(Uri contentUri, Listener listener) {
        Utils.ensureNotOnMainThread();
        if (contentUri == null) {
            if (listener == null) {
                contentUri = CONTENT_URI;
            } else {
                contentUri = getContentUriForContentObserver();
            }
        }
        Cursor cursor = this.mContentResolver.query(contentUri, null, null, null, null);
        if (cursor == null) {
            FinskyLog.w("Download progress cursor null", new Object[0]);
            return Collections.emptyList();
        }
        List<DownloadProgress> result;
        if (cursor.getCount() < 1) {
            result = Collections.emptyList();
        } else {
            result = Lists.newArrayList(cursor.getCount());
            int indexId = cursor.getColumnIndexOrThrow("_id");
            int indexStatus = cursor.getColumnIndexOrThrow("status");
            int indexCurrentBytes = cursor.getColumnIndexOrThrow("current_bytes");
            int indexTotalBytes = cursor.getColumnIndexOrThrow("total_bytes");
            int indexNetworkTypes = cursor.getColumnIndex("allowed_network_types");
            while (cursor.moveToNext()) {
                Uri uri = Uri.parse(getContentUriString(String.valueOf(cursor.getLong(indexId))));
                int status = cursor.getInt(indexStatus);
                long currentBytes = cursor.getLong(indexCurrentBytes);
                long totalBytes = cursor.getLong(indexTotalBytes);
                if (status == 195 && indexNetworkTypes != -1 && cursor.getInt(indexNetworkTypes) == 2) {
                    status = 196;
                }
                result.add(new DownloadProgress(uri, currentBytes, totalBytes, status));
            }
        }
        if (listener == null) {
            cursor.close();
            return result;
        }
        if (this.mListenerCursor != null) {
            this.mListenerCursor.unregisterContentObserver(this.mContentObserver);
            this.mListenerCursor.close();
        }
        this.mListenerCursor = cursor;
        this.mListenerCursor.registerContentObserver(this.mContentObserver);
        this.mListener = listener;
        return result;
    }

    private void onChange() {
        Listener listener = this.mListener;
        unregisterListener(listener);
        if (listener != null) {
            listener.onChange();
        }
    }

    public void unregisterListener(Listener listener) {
        if (this.mListenerCursor != null) {
            this.mListenerCursor.unregisterContentObserver(this.mContentObserver);
            this.mListenerCursor.close();
        }
        this.mListener = null;
    }

    public Uri getFileUriForContentUri(Uri contentUri) {
        if ("file".equalsIgnoreCase(contentUri.getScheme())) {
            return contentUri;
        }
        String fileName = null;
        Cursor cursor = null;
        try {
            cursor = this.mContentResolver.query(contentUri, QUERY_FILENAME_PROJECTION, null, null, null);
            if (cursor.moveToFirst()) {
                fileName = cursor.getString(0);
            }
            if (cursor != null) {
                cursor.close();
            }
            if (fileName == null) {
                return null;
            }
            return Uri.fromFile(new File(Uri.parse(fileName).getPath()));
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public Uri getUriFromBroadcast(Intent intent) {
        Uri intentUri = intent.getData();
        if (intentUri != null) {
            return intentUri;
        }
        long id = intent.getLongExtra("extra_download_id", -1);
        if (id == -1) {
            long[] idArray = intent.getLongArrayExtra("extra_click_download_ids");
            if (idArray != null && idArray.length == 1) {
                id = idArray[0];
            }
        }
        if (id == -1) {
            return null;
        }
        return Uri.parse(getContentUriString(String.valueOf(id)));
    }

    private static void sniffDownloadManagerVersion(Uri fromUri) {
        if (VERSION.SDK_INT <= 10 && sDownloadManagerUsesFroyoStrings == null) {
            String uriString = fromUri.toString().toLowerCase(Locale.US);
            if (uriString.startsWith("content://downloads/download")) {
                sDownloadManagerUsesFroyoStrings = Boolean.TRUE;
                FinskyPreferences.downloadManagerUsesFroyoStrings.put(Boolean.valueOf(true));
            } else if (uriString.startsWith("content://downloads/my_downloads")) {
                sDownloadManagerUsesFroyoStrings = Boolean.FALSE;
                FinskyPreferences.downloadManagerUsesFroyoStrings.put(Boolean.valueOf(false));
            } else {
                FinskyLog.w("Unknown download manager URI string: %s", uriString);
            }
        }
    }

    private static boolean isFroyoDownloadManager() {
        if (VERSION.SDK_INT > 10) {
            return false;
        }
        if (sDownloadManagerUsesFroyoStrings != null) {
            return sDownloadManagerUsesFroyoStrings.booleanValue();
        }
        return ((Boolean) FinskyPreferences.downloadManagerUsesFroyoStrings.get()).booleanValue();
    }

    private Uri getContentUriForContentObserver() {
        if (isFroyoDownloadManager()) {
            return FROYO_CONTENT_URI;
        }
        return CONTENT_URI;
    }

    private String getContentUriString(String id) {
        if (isFroyoDownloadManager()) {
            return "content://downloads/download/" + id;
        }
        return "content://downloads/my_downloads/" + id;
    }
}
