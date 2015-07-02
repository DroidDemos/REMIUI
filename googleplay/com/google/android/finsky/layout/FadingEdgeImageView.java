package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewDebug.ExportedProperty;
import com.google.android.play.image.FifeImageView;

public class FadingEdgeImageView extends FifeImageView {
    private int mFadingEdgeBackgroundColor;
    private boolean mHasFadingLeftEdge;
    private boolean mHasFadingRightEdge;

    public FadingEdgeImageView(Context context) {
        super(context);
    }

    public FadingEdgeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void configureFadingEdges(boolean hasFadingLeftEdge, boolean hasFadingRightEdge, int fadingEdgeLength, int fadingEdgeBackgroundColor) {
        setHorizontalFadingEdgeEnabled(true);
        setFadingEdgeLength(fadingEdgeLength);
        this.mHasFadingLeftEdge = hasFadingLeftEdge;
        this.mHasFadingRightEdge = hasFadingRightEdge;
        this.mFadingEdgeBackgroundColor = fadingEdgeBackgroundColor;
    }

    public void clearFadingEdges() {
        setHorizontalFadingEdgeEnabled(false);
        setFadingEdgeLength(0);
        this.mHasFadingLeftEdge = false;
        this.mHasFadingRightEdge = false;
    }

    protected float getLeftFadingEdgeStrength() {
        return this.mHasFadingLeftEdge ? 1.0f : 0.0f;
    }

    protected float getRightFadingEdgeStrength() {
        return this.mHasFadingRightEdge ? 1.0f : 0.0f;
    }

    @ExportedProperty(category = "drawing")
    public int getSolidColor() {
        if (this.mHasFadingLeftEdge || this.mHasFadingRightEdge) {
            return this.mFadingEdgeBackgroundColor;
        }
        return super.getSolidColor();
    }

    public boolean hasOverlappingRendering() {
        return true;
    }

    protected boolean onSetAlpha(int alpha) {
        return false;
    }
}
