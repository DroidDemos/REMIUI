package com.google.android.finsky.previews;

import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocDetails.SongDetails;
import java.util.Collection;

public class PreviewController {
    private static final PreviewPlayer mPlayer;
    private final StatusListener mStatusListener;

    static {
        mPlayer = new PreviewPlayer();
    }

    public PreviewController(StatusListener statusListener) {
        mPlayer.initialize();
        this.mStatusListener = statusListener;
        mPlayer.addStatusListener(statusListener);
    }

    public void unbind() {
        mPlayer.removeStatusListener(this.mStatusListener);
    }

    public void play(Collection<Document> tracks) {
        mPlayer.play((Collection) tracks);
    }

    public void togglePlayback(SongDetails track) {
        mPlayer.togglePlayback(track);
    }

    public void skip() {
        mPlayer.skip();
    }

    public SongDetails getCurrentTrack() {
        return mPlayer.getCurrentTrack();
    }

    public void getStatusUpdate(StatusListener statusListener) {
        mPlayer.notifyListener(statusListener);
    }

    public int getCurrentQueueSize() {
        return mPlayer.getCurrentQueueSize();
    }

    public static void reset() {
        mPlayer.reset();
    }

    public static void setupOnBackStackChangedListener(final NavigationManager navigationManager) {
        OnBackStackChangedListener listener = new OnBackStackChangedListener() {
            public void onBackStackChanged() {
                PreviewController.mPlayer.clear();
                PreviewController.mPlayer.stop();
                PreviewController.mPlayer.reset();
                navigationManager.removeOnBackStackChangedListener(this);
            }
        };
        navigationManager.removeOnBackStackChangedListener(listener);
        navigationManager.addOnBackStackChangedListener(listener);
    }
}
