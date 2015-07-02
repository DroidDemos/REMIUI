package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.adapters.DownloadProgressHelper;
import com.google.android.finsky.receivers.Installer.InstallerProgressReport;

public class PlayCardViewMyAppsDownloading extends PlayCardViewMyApps {
    private TextView mDownloadingBytes;
    private TextView mDownloadingPercentage;
    private ProgressBar mProgressBar;

    public PlayCardViewMyAppsDownloading(Context context) {
        this(context, null);
    }

    public PlayCardViewMyAppsDownloading(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mThumbnailAspectRatio = 1.0f;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mDownloadingBytes = (TextView) findViewById(R.id.downloading_bytes);
        this.mDownloadingPercentage = (TextView) findViewById(R.id.downloading_percentage);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureThumbnailSpanningHeight(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void bindProgress(InstallerProgressReport progressReport) {
        DownloadProgressHelper.configureDownloadProgressUi(getContext(), progressReport, this.mDownloadingBytes, this.mDownloadingPercentage, this.mProgressBar);
    }
}
