package com.google.android.finsky.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MainActivity;
import com.google.android.finsky.download.Download.DownloadState;
import com.google.android.finsky.utils.FinskyLog;
import java.util.List;

public class DownloadBroadcastReceiver extends BroadcastReceiver {
    private static DownloadQueue sDownloadQueue;

    public static void initialize(DownloadQueue downloadQueue) {
        sDownloadQueue = downloadQueue;
    }

    public void onReceive(Context context, Intent intent) {
        FinskyLog.d("Intent received at DownloadBroadcastReceiver", new Object[0]);
        final Uri contentUri = sDownloadQueue.getDownloadManager().getUriFromBroadcast(intent);
        String action = intent.getAction();
        boolean isClicked = false;
        boolean isCompleted = false;
        if ("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED".equals(action)) {
            isClicked = true;
        } else if ("android.intent.action.DOWNLOAD_COMPLETE".equals(action)) {
            isCompleted = true;
        } else if ("android.intent.action.DOWNLOAD_COMPLETED".equals(action)) {
            isCompleted = true;
        }
        if (sDownloadQueue.getDownloadByContentUri(contentUri) == null) {
            FinskyLog.d("DownloadBroadcastReceiver could not find %s in queue.", contentUri);
            if (isClicked) {
                context = FinskyApp.get();
                if (FinskyApp.get().getCurrentAccount() != null) {
                    Intent myAppsIntent = MainActivity.getMyDownloadsIntent(context);
                    myAppsIntent.setFlags(268435456);
                    context.startActivity(myAppsIntent);
                    return;
                }
                return;
            }
            return;
        }
        final boolean finalIsClicked = isClicked;
        final boolean finalIsCompleted = isCompleted;
        new AsyncTask<Void, Void, Integer>() {
            protected Integer doInBackground(Void... params) {
                List<DownloadProgress> status = DownloadBroadcastReceiver.sDownloadQueue.getDownloadManager().query(contentUri, null);
                if (status.size() >= 1) {
                    return Integer.valueOf(((DownloadProgress) status.get(0)).statusCode);
                }
                FinskyLog.w("Unable to find %s in download manager", contentUri.toString());
                return Integer.valueOf(-1);
            }

            protected void onPostExecute(Integer httpStatus) {
                Download download = DownloadBroadcastReceiver.sDownloadQueue.getDownloadByContentUri(contentUri);
                if (download == null) {
                    FinskyLog.d("Did not find download in queue for %s", contentUri.toString());
                    return;
                }
                if (httpStatus.intValue() != -1) {
                    download.setHttpStatus(httpStatus.intValue());
                } else {
                    FinskyLog.e("DownloadBroadcastReceiver received invalid HTTP status of -1", new Object[0]);
                }
                if (finalIsClicked) {
                    DownloadBroadcastReceiver.sDownloadQueue.notifyClicked(download);
                } else if (!finalIsCompleted) {
                    FinskyLog.wtf("Invalid DownloadBroadcastReceiver intent", new Object[0]);
                } else if (download.isCompleted()) {
                    FinskyLog.d("Received ACTION_DOWNLOAD_COMPLETE %d for %s - dropping because already in state %s.", httpStatus, download, download.getState());
                } else if (DownloadManagerConstants.isStatusSuccess(httpStatus.intValue())) {
                    DownloadBroadcastReceiver.sDownloadQueue.setDownloadState(download, DownloadState.SUCCESS);
                } else {
                    DownloadBroadcastReceiver.sDownloadQueue.setDownloadState(download, DownloadState.ERROR);
                }
            }
        }.execute(new Void[0]);
    }
}
