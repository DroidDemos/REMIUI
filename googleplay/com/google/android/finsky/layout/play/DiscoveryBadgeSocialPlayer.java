package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;

public class DiscoveryBadgeSocialPlayer extends DiscoveryBadgeBase {
    public DiscoveryBadgeSocialPlayer(Context context) {
        this(context, null);
    }

    public DiscoveryBadgeSocialPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected int getPlayStoreUiElementType() {
        return 1806;
    }
}
