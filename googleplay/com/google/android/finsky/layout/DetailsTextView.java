package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.play.layout.PlayTextView;

public class DetailsTextView extends PlayTextView {
    public DetailsTextView(Context context) {
        this(context, null);
    }

    public DetailsTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void scrollTo(int x, int y) {
        super.scrollTo(x, 0);
    }
}
