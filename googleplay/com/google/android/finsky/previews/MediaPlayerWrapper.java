package com.google.android.finsky.previews;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.google.android.finsky.FinskyApp;
import java.io.IOException;

public class MediaPlayerWrapper extends MediaPlayer implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private int mCurrentState;
    private OnCompletionListener mOnCompletionListener;
    private OnErrorListener mOnErrorListener;
    private OnPreparedListener mOnPreparedListener;
    private final StatusListener mStatusListener;
    private final WakeLock mWakeLock;

    public MediaPlayerWrapper(StatusListener listener) {
        this.mCurrentState = 0;
        this.mOnPreparedListener = null;
        this.mOnCompletionListener = null;
        this.mOnErrorListener = null;
        super.setOnPreparedListener(this);
        super.setOnCompletionListener(this);
        super.setOnErrorListener(this);
        this.mStatusListener = listener;
        this.mWakeLock = ((PowerManager) FinskyApp.get().getSystemService("power")).newWakeLock(6, getClass().getSimpleName());
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public void setOnPreparedListener(OnPreparedListener listener) {
        this.mOnPreparedListener = listener;
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        this.mOnCompletionListener = listener;
    }

    public void reset() {
        super.reset();
        this.mCurrentState = 0;
        this.mStatusListener.reset();
        releaseWakeLock();
    }

    public void resetWhileStayingAwake() {
        super.reset();
        this.mCurrentState = 0;
        this.mStatusListener.reset();
    }

    public void setDataSource(String path) throws IOException {
        super.setDataSource(path);
        this.mCurrentState = 1;
    }

    public void prepareAsync() {
        super.prepareAsync();
        this.mCurrentState = 2;
        this.mStatusListener.preparing();
    }

    public void prepare() throws IOException {
        super.prepare();
        this.mCurrentState = 3;
        this.mStatusListener.prepared();
    }

    public void start() {
        super.start();
        this.mCurrentState = 4;
        this.mStatusListener.playing();
        if (!this.mWakeLock.isHeld()) {
            this.mWakeLock.acquire();
        }
    }

    public void stop() {
        super.stop();
        this.mCurrentState = 6;
        releaseWakeLock();
    }

    public void pause() {
        super.pause();
        this.mCurrentState = 5;
        this.mStatusListener.paused();
        releaseWakeLock();
    }

    public void release() {
        super.release();
        this.mCurrentState = 9;
        releaseWakeLock();
    }

    public void onPrepared(MediaPlayer mp) {
        this.mCurrentState = 3;
        this.mStatusListener.prepared();
        if (this.mOnPreparedListener != null) {
            this.mOnPreparedListener.onPrepared(mp);
        }
    }

    public void onCompletion(MediaPlayer mp) {
        this.mCurrentState = 7;
        this.mStatusListener.completed();
        if (this.mOnCompletionListener != null) {
            this.mOnCompletionListener.onCompletion(mp);
        }
        if (this.mCurrentState != 10) {
            releaseWakeLock();
        }
    }

    public void setBetweenTrackState() {
        this.mCurrentState = 10;
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        this.mCurrentState = 8;
        this.mStatusListener.error();
        if (this.mOnErrorListener != null) {
            return this.mOnErrorListener.onError(mp, what, extra);
        }
        return false;
    }

    private void releaseWakeLock() {
        if (this.mWakeLock.isHeld()) {
            this.mWakeLock.release();
        }
    }
}
