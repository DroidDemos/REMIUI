package com.google.android.libraries.bind.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.libraries.bind.R;

public class LoadingView extends FrameLayout {
    public LoadingView(Context context) {
        this(context, null, 0);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.bind__loading, this);
        setLayoutParams(new LayoutParams(-1, -1));
    }
}
