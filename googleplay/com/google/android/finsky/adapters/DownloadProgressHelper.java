package com.google.android.finsky.adapters;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.receivers.Installer.InstallerProgressReport;
import com.google.android.finsky.receivers.Installer.InstallerState;

public class DownloadProgressHelper {
    private static CharSequence sDownloadStatusFormatBytes;
    private static CharSequence sDownloadStatusFormatPercents;

    private static void initializeStrings(Context ctx) {
        if (sDownloadStatusFormatPercents == null) {
            sDownloadStatusFormatPercents = Html.fromHtml(ctx.getString(R.string.download_progress_percentage));
        }
        if (sDownloadStatusFormatBytes == null) {
            sDownloadStatusFormatBytes = Html.fromHtml(ctx.getString(R.string.download_progress_bytes));
        }
    }

    public static void configureDownloadProgressUi(Context ctx, InstallerProgressReport progressReport, TextView downloadingBytes, TextView downloadingPercentage, ProgressBar progressBar) {
        initializeStrings(ctx);
        downloadingPercentage.setText(" ");
        downloadingBytes.setText(" ");
        if (progressReport.installerState != InstallerState.DOWNLOADING) {
            progressBar.setIndeterminate(true);
            if (progressReport.installerState == InstallerState.INSTALLING) {
                downloadingBytes.setText(R.string.installing);
                return;
            }
            return;
        }
        boolean bytesValid = progressReport.bytesCompleted > 0 && progressReport.bytesTotal > 0 && progressReport.bytesCompleted <= progressReport.bytesTotal;
        int percent = 0;
        if (bytesValid) {
            percent = (int) ((progressReport.bytesCompleted * 100) / progressReport.bytesTotal);
            progressBar.setIndeterminate(false);
            progressBar.setProgress(percent);
        } else {
            progressBar.setIndeterminate(true);
        }
        int status = progressReport.downloadStatus;
        if (status == 195) {
            downloadingBytes.setText(R.string.download_paused_network);
        } else if (status == 196) {
            downloadingBytes.setText(R.string.download_paused_wifi);
        } else if (bytesValid) {
            downloadingPercentage.setText(TextUtils.expandTemplate(sDownloadStatusFormatPercents, new CharSequence[]{Integer.toString(percent)}));
            downloadingBytes.setText(TextUtils.expandTemplate(sDownloadStatusFormatBytes, new CharSequence[]{Formatter.formatFileSize(ctx, progressReport.bytesCompleted), Formatter.formatFileSize(ctx, progressReport.bytesTotal)}));
        } else {
            downloadingBytes.setText(R.string.download_in_progress);
        }
    }
}
