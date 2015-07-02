package com.google.android.finsky.previews;

public abstract class StatusListener {
    public void playing() {
    }

    public void paused() {
    }

    public void completed() {
    }

    public void preparing() {
    }

    public void prepared() {
    }

    public void unrolling() {
    }

    public void error() {
    }

    public void queueChanged(int newSize) {
    }

    public void reset() {
    }
}
