package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.play.cardview.CardViewGroupDelegate;
import com.google.android.play.cardview.CardViewGroupDelegates;

public class CardFrameLayout extends FrameLayout {
    public CardFrameLayout(Context context) {
        this(context, null);
    }

    public CardFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getCardViewGroupDelegate().initialize(this, context, attrs, defStyle);
    }

    public CardViewGroupDelegate getCardViewGroupDelegate() {
        return CardViewGroupDelegates.IMPL;
    }
}
