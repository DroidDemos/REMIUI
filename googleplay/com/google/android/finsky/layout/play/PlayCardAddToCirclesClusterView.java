package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;

public class PlayCardAddToCirclesClusterView extends PlayCardClusterView {
    public PlayCardAddToCirclesClusterView(Context context) {
        this(context, null);
    }

    public PlayCardAddToCirclesClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected int getPlayStoreUiElementType() {
        return 410;
    }
}
