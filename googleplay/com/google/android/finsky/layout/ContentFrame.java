package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.vending.R;

public class ContentFrame extends FrameLayout {
    private ViewGroup mDataLayout;
    private final LayoutInflater mInflater;

    public ContentFrame(Context context) {
        this(context, null, 0);
    }

    public ContentFrame(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentFrame(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        View inflated = this.mInflater.inflate(R.layout.page_loading_indicator, this, false);
        inflated.setVisibility(8);
        addView(inflated);
        inflated = this.mInflater.inflate(R.layout.page_error_indicator, this, false);
        inflated.setVisibility(8);
        addView(inflated);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ContentFrame);
        int resource = attributes.getResourceId(0, 0);
        int resId = attributes.getResourceId(1, 0);
        attributes.recycle();
        if (resource != 0) {
            addView(inflateDataLayout(resource, resId));
        }
    }

    public ViewGroup inflateDataLayout(int resource, int resId) {
        return inflateDataLayout(this.mInflater, resource, resId);
    }

    public ViewGroup inflateDataLayout(LayoutInflater inflater, int resource, int resId) {
        if (resource == 0) {
            return null;
        }
        this.mDataLayout = (ViewGroup) inflater.inflate(resource, this, false);
        if (resId != 0) {
            this.mDataLayout.setId(resId);
        }
        return this.mDataLayout;
    }
}
