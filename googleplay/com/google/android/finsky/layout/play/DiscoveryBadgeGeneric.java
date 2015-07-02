package com.google.android.finsky.layout.play;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import com.google.android.play.image.AvatarCropTransformation;

public class DiscoveryBadgeGeneric extends DiscoveryBadgeBase {
    private Paint mPaint;
    private AvatarCropTransformation mTransformation;

    public DiscoveryBadgeGeneric(Context context) {
        this(context, null);
    }

    public DiscoveryBadgeGeneric(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPaint = new Paint(1);
        this.mPaint.setStyle(Style.FILL_AND_STROKE);
        setWillNotDraw(false);
        this.mTransformation = new AvatarCropTransformation(getResources(), false);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth() / 2;
        int y = this.mBadgeRadius;
        this.mPaint.setColor(this.mCurrentFillColor);
        canvas.drawCircle((float) x, (float) y, (float) this.mBadgeRadius, this.mPaint);
    }

    protected int getPlayStoreUiElementType() {
        return 1801;
    }

    protected void onPreImageLoad() {
        this.mTransformation.setFillToSizeColor(this.mCurrentFillColor);
        this.mIcon.setBitmapTransformation(this.mTransformation);
    }
}
