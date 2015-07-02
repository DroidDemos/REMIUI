package com.google.android.libraries.bind.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.libraries.bind.data.Bound;
import com.google.android.libraries.bind.data.BoundHelper;
import com.google.android.libraries.bind.data.Data;

public class BoundFrameLayout extends FrameLayout implements Bound {
    private final BoundHelper boundHelper;

    public BoundFrameLayout(Context context) {
        this(context, null);
    }

    public BoundFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BoundFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.boundHelper = makeBoundHelper(context, attrs);
    }

    protected BoundHelper makeBoundHelper(Context context, AttributeSet attrs) {
        return new BoundHelper(context, attrs, this);
    }

    public void updateBoundData(Data data) {
        this.boundHelper.updateBoundData(data);
    }
}
