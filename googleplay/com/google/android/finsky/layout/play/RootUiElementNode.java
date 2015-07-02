package com.google.android.finsky.layout.play;

public interface RootUiElementNode extends PlayStoreUiElementNode {
    void flushImpression();

    void startNewImpression();
}
