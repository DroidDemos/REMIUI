package com.google.android.libraries.bind.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.google.android.libraries.bind.R;
import com.google.android.libraries.bind.data.BindingViewGroup;
import com.google.android.libraries.bind.data.BindingViewGroup.BlendMode;
import com.google.android.libraries.bind.data.Data;
import com.google.android.libraries.bind.data.DataList;

public class BindingFrameLayout extends BoundFrameLayout implements BindingViewGroup {
    protected final BindingViewGroupHelper bindingViewGroupHelper;

    public BindingFrameLayout(Context context) {
        this(context, null, 0);
    }

    public BindingFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BindingFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.bindingViewGroupHelper = new BindingViewGroupHelper(this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BindingFrameLayout);
        this.bindingViewGroupHelper.setOwnedByParent(a.getBoolean(R.styleable.BindingFrameLayout_bind__isOwnedByParent, false));
        this.bindingViewGroupHelper.setSupportsAnimationCapture(a.getBoolean(R.styleable.BindingFrameLayout_bind__supportsAnimationCapture, true));
        a.recycle();
    }

    public BindingViewGroupHelper getBindingViewGroupHelper() {
        return this.bindingViewGroupHelper;
    }

    public void setDataRow(DataList dataRow) {
        this.bindingViewGroupHelper.setDataRow(dataRow);
    }

    public DataList getDataRow() {
        return this.bindingViewGroupHelper.getDataRow();
    }

    public Data getData() {
        return this.bindingViewGroupHelper.getData();
    }

    public void prepareForRecycling() {
        this.bindingViewGroupHelper.prepareForRecycling();
    }

    public boolean startEditingIfPossible() {
        return this.bindingViewGroupHelper.startEditingIfPossible();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.bindingViewGroupHelper.onFinishInflate();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.bindingViewGroupHelper.onDetachedFromWindow();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.bindingViewGroupHelper.onAttachedToWindow();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        this.bindingViewGroupHelper.onStartTemporaryDetach();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        this.bindingViewGroupHelper.onFinishTemporaryDetach();
    }

    public void onDataUpdated(Data data) {
        this.bindingViewGroupHelper.onDataUpdated(data);
    }

    public void draw(Canvas canvas) {
        this.bindingViewGroupHelper.draw(canvas);
    }

    public void setOwnedByParent(boolean value) {
        this.bindingViewGroupHelper.setOwnedByParent(value);
    }

    public boolean isOwnedByParent() {
        return this.bindingViewGroupHelper.isOwnedByParent();
    }

    public void blendCapturedBitmap(Bitmap bitmap, Rect bitmapRect, long animationDuration, BlendMode blendMode) {
        this.bindingViewGroupHelper.blendCapturedBitmap(bitmap, bitmapRect, animationDuration, blendMode);
    }

    public boolean captureToBitmap(Bitmap bitmap, float left, float top) {
        return this.bindingViewGroupHelper.captureToBitmap(bitmap, left, top);
    }

    public final void updateBoundDataProxy(Data data) {
        updateBoundData(data);
    }

    public final void superDrawProxy(Canvas canvas) {
        super.draw(canvas);
    }

    public final void setMeasuredDimensionProxy(int measuredWidth, int measuredHeight) {
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.bindingViewGroupHelper.onMeasure(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
