package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.android.vending.R;

public class StarLabel extends View {
    private int mHeight;
    private int mMaxStars;
    private int mNumStars;
    private final Drawable mStarDrawable;
    private final int mStarPadding;

    public StarLabel(Context context) {
        this(context, null);
    }

    public StarLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNumStars(0);
        setWillNotDraw(false);
        Resources res = context.getResources();
        this.mStarDrawable = res.getDrawable(R.drawable.ic_star_rating_graph);
        this.mStarPadding = res.getDimensionPixelSize(R.dimen.details_histogram_star_padding);
    }

    public void setNumStars(int numStars) {
        this.mNumStars = numStars;
    }

    public void setMaxStars(int maxStars) {
        this.mMaxStars = maxStars;
    }

    public void setStarHeight(int height) {
        this.mHeight = height;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((this.mMaxStars * this.mStarDrawable.getIntrinsicWidth()) + ((this.mMaxStars - 1) * this.mStarPadding), this.mHeight);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mMaxStars > 0) {
            int starSize = this.mStarDrawable.getIntrinsicWidth();
            int x = getWidth();
            int y = (getHeight() - starSize) / 2;
            for (int i = 0; i < this.mNumStars; i++) {
                this.mStarDrawable.setBounds(x - starSize, y, x, y + starSize);
                this.mStarDrawable.draw(canvas);
                x -= this.mStarPadding + starSize;
            }
        }
    }
}
