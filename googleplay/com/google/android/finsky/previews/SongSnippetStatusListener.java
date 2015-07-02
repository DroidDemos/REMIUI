package com.google.android.finsky.previews;

public abstract class SongSnippetStatusListener extends StatusListener {
    protected abstract void update(int i, boolean z);

    public void playing() {
        update(2, true);
    }

    public void paused() {
        update(3, true);
    }

    public void completed() {
        update(5, false);
    }

    public void preparing() {
        update(1, true);
    }

    public void prepared() {
        update(2, true);
    }

    public void unrolling() {
        update(1, true);
    }

    public void error() {
        update(4, false);
    }
}
