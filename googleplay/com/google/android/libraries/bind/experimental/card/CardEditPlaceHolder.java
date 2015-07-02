package com.google.android.libraries.bind.experimental.card;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.libraries.bind.R;
import com.google.android.libraries.bind.widget.BindingFrameLayout;

public class CardEditPlaceHolder extends BindingFrameLayout {
    public static final int[] EQUALITY_FIELDS;
    public static final int LAYOUT;

    static {
        LAYOUT = R.layout.bind__card_edit_placeholder;
        EQUALITY_FIELDS = new int[0];
    }

    public CardEditPlaceHolder(Context context) {
        this(context, null, 0);
    }

    public CardEditPlaceHolder(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardEditPlaceHolder(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
