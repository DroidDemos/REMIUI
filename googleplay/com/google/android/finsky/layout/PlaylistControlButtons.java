package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.previews.StatusListener;
import com.google.android.finsky.utils.Lists;
import com.google.android.play.layout.PlayActionButton;
import java.util.Collection;

public class PlaylistControlButtons extends PlayActionButton implements OnClickListener {
    private final PreviewController mConnection;
    private Collection<Document> mDocs;
    private boolean mIsPlaying;
    private final StatusListener mStatusListener;

    public PlaylistControlButtons(Context context) {
        this(context, null);
    }

    public PlaylistControlButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDocs = Lists.newArrayList();
        this.mIsPlaying = false;
        this.mStatusListener = new StatusListener() {
            public void queueChanged(int newSize) {
                PlaylistControlButtons.this.setIsPlaying(newSize > 0);
            }
        };
        this.mConnection = new PreviewController(this.mStatusListener);
    }

    public void configure(Collection<Document> docs) {
        this.mDocs = docs;
        configure(2, (int) R.string.play_all, null);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setIsPlaying(this.mConnection.getCurrentQueueSize() > 0);
    }

    protected void onDetachedFromWindow() {
        this.mConnection.unbind();
        super.onDetachedFromWindow();
    }

    public void onClick(View v) {
        if (this.mIsPlaying) {
            this.mConnection.skip();
            return;
        }
        this.mConnection.play(this.mDocs);
        setIsPlaying(true);
    }

    private void setIsPlaying(boolean isPlaying) {
        this.mIsPlaying = isPlaying;
        configure(2, this.mIsPlaying ? R.string.skip_track : R.string.play_all, null);
    }
}
