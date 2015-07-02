package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.google.android.play.cardview.CardViewGroupDelegate;
import com.google.android.play.cardview.CardViewGroupDelegates;

public class CardLinearLayout extends LinearLayout {
    public CardLinearLayout(Context context) {
        this(context, null);
    }

    public CardLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getCardViewGroupDelegate().initialize(this, context, attrs, 0);
    }

    public CardLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        getCardViewGroupDelegate().initialize(this, context, attrs, defStyle);
    }

    public CardViewGroupDelegate getCardViewGroupDelegate() {
        return CardViewGroupDelegates.IMPL;
    }
}
