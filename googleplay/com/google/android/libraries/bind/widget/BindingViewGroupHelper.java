package com.google.android.libraries.bind.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.bind.card.CardGroup;
import com.google.android.libraries.bind.data.BindingViewGroup;
import com.google.android.libraries.bind.data.BindingViewGroup.BlendMode;
import com.google.android.libraries.bind.data.Bound;
import com.google.android.libraries.bind.data.Data;
import com.google.android.libraries.bind.data.DataList;
import com.google.android.libraries.bind.data.DataView;
import com.google.android.libraries.bind.data.DataViewHelper;
import com.google.android.libraries.bind.util.Util;

public class BindingViewGroupHelper extends DataViewHelper {
    private static final Paint blendPaint;
    private long blendBitmapDurationMs;
    private long blendBitmapStartTimeMs;
    private BlendMode blendMode;
    private Bitmap blendedBitmap;
    private boolean blendedBitmapDstComputed;
    private final Rect blendedBitmapDstRect;
    private final Rect blendedBitmapSrcRect;
    private Data boundData;
    public boolean capturing;
    private CardGroup cardGroup;
    private int cardGroupPosition;
    private boolean isOwnedByParent;
    private boolean supportsAnimationCapture;
    private final ViewGroup viewGroup;

    static {
        blendPaint = new Paint();
    }

    public BindingViewGroupHelper(DataView view) {
        super(view);
        this.cardGroupPosition = -1;
        this.blendedBitmapSrcRect = new Rect();
        this.blendedBitmapDstRect = new Rect();
        Util.checkPrecondition(view instanceof BindingViewGroup);
        Util.checkPrecondition(view instanceof ViewGroup);
        this.viewGroup = (ViewGroup) view;
    }

    public void prepareForRecycling() {
        setDataRow(null);
        this.cardGroup = null;
        this.cardGroupPosition = -1;
        this.viewGroup.setOnLongClickListener(null);
        this.viewGroup.setLongClickable(false);
        if (VERSION.SDK_INT >= 14) {
            this.viewGroup.animate().cancel();
        }
        if (VERSION.SDK_INT >= 12) {
            this.viewGroup.setTranslationX(0.0f);
            this.viewGroup.setTranslationY(0.0f);
            this.viewGroup.setScaleX(1.0f);
            this.viewGroup.setScaleY(1.0f);
            this.viewGroup.setAlpha(1.0f);
            this.viewGroup.setRotation(0.0f);
        }
        if (this.viewGroup instanceof BindingViewGroup) {
            ((BindingViewGroup) this.viewGroup).setMeasuredDimensionProxy(0, 0);
        }
    }

    public void setOwnedByParent(boolean isOwnedByParent) {
        this.isOwnedByParent = isOwnedByParent;
    }

    public boolean isOwnedByParent() {
        return this.isOwnedByParent;
    }

    public void setSupportsAnimationCapture(boolean supportsAnimationCapture) {
        this.supportsAnimationCapture = supportsAnimationCapture;
    }

    public boolean startEditingIfPossible() {
        if (this.cardGroup != null) {
            return this.cardGroup.startEditing(this.viewGroup, this.cardGroupPosition);
        }
        return false;
    }

    public void setDataRow(DataList dataRow) {
        this.boundData = null;
        super.setDataRow(dataRow);
    }

    public Data getData() {
        return getDataRow() != null ? getDataRow().getData(0) : this.boundData;
    }

    public void onFinishInflate() {
        markDescendantsAsOwned(this.viewGroup);
    }

    public void onDataUpdated(Data data) {
        ((BindingViewGroup) this.viewGroup).updateBoundDataProxy(data);
        this.boundData = data;
        clearBlendedBitmap();
        sendDataToChildrenWhoWantIt(this.viewGroup, data);
    }

    protected void sendDataToChildrenWhoWantIt(ViewGroup viewGroup, Data data) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof BindingViewGroup) {
                if (((BindingViewGroup) child).isOwnedByParent()) {
                    ((BindingViewGroup) child).onDataUpdated(data);
                }
            } else if (child instanceof Bound) {
                ((Bound) child).updateBoundData(data);
            }
            if ((child instanceof ViewGroup) && !(child instanceof BindingViewGroup)) {
                sendDataToChildrenWhoWantIt((ViewGroup) child, data);
            }
        }
    }

    public boolean captureToBitmap(Bitmap bitmap, float left, float top) {
        if (!this.supportsAnimationCapture || this.viewGroup.getWidth() <= 0 || this.viewGroup.getHeight() <= 0) {
            return false;
        }
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(left, top);
        canvas.clipRect(new Rect(0, 0, this.viewGroup.getWidth(), this.viewGroup.getHeight()));
        canvas.drawColor(0, Mode.CLEAR);
        this.capturing = true;
        this.viewGroup.draw(canvas);
        this.capturing = false;
        return true;
    }

    public void blendCapturedBitmap(Bitmap bitmap, Rect bitmapRect, long animationDuration, BlendMode blendMode) {
        Util.checkPrecondition(animationDuration > 0);
        this.blendMode = blendMode;
        this.blendedBitmap = bitmap;
        if (this.blendedBitmap != null) {
            this.blendedBitmapSrcRect.set(bitmapRect);
            this.blendBitmapStartTimeMs = System.currentTimeMillis();
            this.blendBitmapDurationMs = animationDuration;
            this.viewGroup.setWillNotDraw(false);
            this.viewGroup.invalidate();
        }
    }

    private void clearBlendedBitmap() {
        if (this.blendedBitmap != null) {
            this.blendedBitmap = null;
            this.blendMode = null;
            this.blendBitmapStartTimeMs = 0;
            this.blendBitmapDurationMs = 0;
            this.blendedBitmapDstComputed = false;
            this.viewGroup.setWillNotDraw(true);
        }
    }

    public void draw(Canvas canvas) {
        if (this.blendMode != BlendMode.SHOW_SOURCE_HIDE_DESTINATION && (this.viewGroup instanceof BindingViewGroup)) {
            ((BindingViewGroup) this.viewGroup).superDrawProxy(canvas);
        }
        if (this.blendedBitmap != null && !this.capturing) {
            float blendFraction = ((float) (System.currentTimeMillis() - this.blendBitmapStartTimeMs)) / ((float) this.blendBitmapDurationMs);
            if (blendFraction >= 1.0f) {
                clearBlendedBitmap();
                return;
            }
            if (!this.blendedBitmapDstComputed) {
                Rect rect;
                this.blendedBitmapDstRect.left = 0;
                this.blendedBitmapDstRect.top = 0;
                this.blendedBitmapDstRect.right = this.viewGroup.getWidth();
                this.blendedBitmapDstRect.bottom = this.viewGroup.getHeight();
                if (this.blendedBitmapSrcRect.left < 0) {
                    rect = this.blendedBitmapDstRect;
                    rect.left += ((-this.blendedBitmapSrcRect.left) * this.viewGroup.getWidth()) / this.blendedBitmapSrcRect.width();
                }
                if (this.blendedBitmapSrcRect.top < 0) {
                    rect = this.blendedBitmapDstRect;
                    rect.top += ((-this.blendedBitmapSrcRect.top) * this.viewGroup.getHeight()) / this.blendedBitmapSrcRect.height();
                }
                if (this.blendedBitmapSrcRect.right > this.blendedBitmap.getWidth()) {
                    rect = this.blendedBitmapDstRect;
                    rect.right -= ((this.blendedBitmapSrcRect.right - this.blendedBitmap.getWidth()) * this.viewGroup.getWidth()) / this.blendedBitmapSrcRect.width();
                }
                if (this.blendedBitmapSrcRect.bottom > this.blendedBitmap.getHeight()) {
                    rect = this.blendedBitmapDstRect;
                    rect.bottom -= ((this.blendedBitmapSrcRect.bottom - this.blendedBitmap.getHeight()) * this.viewGroup.getHeight()) / this.blendedBitmapSrcRect.height();
                }
                this.blendedBitmapSrcRect.left = Math.max(0, this.blendedBitmapSrcRect.left);
                this.blendedBitmapSrcRect.top = Math.max(0, this.blendedBitmapSrcRect.top);
                this.blendedBitmapSrcRect.right = Math.min(this.blendedBitmap.getWidth(), this.blendedBitmapSrcRect.right);
                this.blendedBitmapSrcRect.bottom = Math.min(this.blendedBitmap.getHeight(), this.blendedBitmapSrcRect.bottom);
                this.blendedBitmapDstComputed = true;
            }
            if (this.blendMode == BlendMode.FADE_SOURCE_ONLY) {
                blendPaint.setAlpha((int) Math.floor((double) (255.0f * (1.0f - blendFraction))));
            }
            canvas.drawBitmap(this.blendedBitmap, this.blendedBitmapSrcRect, this.blendedBitmapDstRect, blendPaint);
            this.viewGroup.invalidate();
        }
    }

    public static void markDescendantsAsOwned(ViewGroup viewGroup) {
        for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof ViewGroup) {
                if (childView instanceof BindingViewGroup) {
                    ((BindingViewGroup) childView).setOwnedByParent(true);
                }
                markDescendantsAsOwned((ViewGroup) childView);
            }
        }
    }
}
