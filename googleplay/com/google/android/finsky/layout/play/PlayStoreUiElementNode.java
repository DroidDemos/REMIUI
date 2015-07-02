package com.google.android.finsky.layout.play;

import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;

public interface PlayStoreUiElementNode {
    void childImpression(PlayStoreUiElementNode playStoreUiElementNode);

    PlayStoreUiElementNode getParentNode();

    PlayStoreUiElement getPlayStoreUiElement();
}
