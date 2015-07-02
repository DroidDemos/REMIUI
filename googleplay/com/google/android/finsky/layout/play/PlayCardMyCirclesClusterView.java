package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;

public class PlayCardMyCirclesClusterView extends PlayCardClusterView {
    public PlayCardMyCirclesClusterView(Context context) {
        this(context, null);
    }

    public PlayCardMyCirclesClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected int getPlayStoreUiElementType() {
        return 415;
    }
}
