package com.google.android.finsky.adapters;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.protos.Common.Image.Dimension;
import com.google.android.finsky.utils.FinskyLog;

public class ImageStripAdapter {
    private final DataSetObservable mDataSetObservable;
    private OnImageChildViewTapListener mImageChildTappedListener;
    private final int mImageCount;
    private final Dimension[] mImageDimensions;
    private final Drawable[] mImages;

    public interface OnImageChildViewTapListener {
        void onImageChildViewTap(int i);
    }

    public ImageStripAdapter(int imageCount, OnImageChildViewTapListener imageTapListener) {
        this.mImageCount = imageCount;
        this.mImages = new Drawable[this.mImageCount];
        this.mImageDimensions = new Dimension[this.mImageCount];
        this.mDataSetObservable = new DataSetObservable();
        this.mImageChildTappedListener = imageTapListener;
    }

    public void setImages(Drawable[] images, Dimension[] dimensions) {
        if (images.length != this.mImageCount) {
            FinskyLog.wtf("Number of images don't match", new Object[0]);
            return;
        }
        for (int i = 0; i < this.mImageCount; i++) {
            this.mImages[i] = images[i];
            this.mImageDimensions[i] = dimensions[i];
        }
        notifyDataSetChanged();
    }

    public void setImageDimensionAt(int index, Dimension dim) {
        this.mImageDimensions[index] = dim;
    }

    public void setImageAt(int index, Drawable drawable) {
        this.mImages[index] = drawable;
        notifyDataSetChanged();
    }

    public View getViewAt(Context context, ViewGroup parent, final int index) {
        View childView = LayoutInflater.from(context).inflate(R.layout.app_screenshot, parent, false);
        childView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ImageStripAdapter.this.mImageChildTappedListener.onImageChildViewTap(index);
            }
        });
        return childView;
    }

    public int getChildCount() {
        return this.mImageCount;
    }

    public Drawable getImageAt(int index) {
        return this.mImages[index];
    }

    public void getImageDimensionAt(int index, Dimension dimension, float scaleFactor) {
        Drawable drawable = getImageAt(index);
        if (drawable != null) {
            dimension.width = (int) (((float) drawable.getIntrinsicWidth()) * scaleFactor);
            dimension.height = (int) (((float) drawable.getIntrinsicHeight()) * scaleFactor);
        } else if (this.mImageDimensions[index] != null) {
            dimension.width = this.mImageDimensions[index].width;
            dimension.height = this.mImageDimensions[index].height;
        } else {
            dimension.width = 0;
            dimension.height = 0;
        }
        dimension.hasWidth = true;
        dimension.hasHeight = true;
    }

    public void registerDataSetObserver(DataSetObserver observer) {
        this.mDataSetObservable.registerObserver(observer);
    }

    public void unregisterAll() {
        this.mDataSetObservable.unregisterAll();
    }

    public void notifyDataSetChanged() {
        this.mDataSetObservable.notifyChanged();
    }

    public void notifyDataSetInvalidated() {
        this.mDataSetObservable.notifyInvalidated();
    }
}
