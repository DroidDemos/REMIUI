package com.google.android.play.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.android.play.R;
import java.text.NumberFormat;

public class StarRatingBar extends View {
    private static NumberFormat sContentDescriptionFormatter;
    private float mGap;
    private float mHalfStarWidth;
    private Path mLeftHalfStarPath;
    private double mRadius;
    private float mRange;
    private float mRating;
    private Path mRightHalfStarPath;
    private double mShortRadius;
    private Paint mStarBackgroundPaint;
    private final double mStarHeight;
    private Paint mStarPaint;
    private Path mStarPath;
    private PointF[] mVertices;

    public StarRatingBar(Context context) {
        this(context, null);
    }

    public StarRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.StarRatingBar);
        this.mGap = (float) viewAttrs.getDimensionPixelSize(R.styleable.StarRatingBar_gap, 0);
        this.mRating = viewAttrs.getFloat(R.styleable.StarRatingBar_rating, 0.0f);
        this.mStarHeight = (double) viewAttrs.getDimensionPixelSize(R.styleable.StarRatingBar_star_height, res.getDimensionPixelSize(R.dimen.play_star_height_default));
        this.mRange = (float) viewAttrs.getInt(R.styleable.StarRatingBar_range, 5);
        int starColor = viewAttrs.getColor(R.styleable.StarRatingBar_star_color, res.getColor(R.color.play_white));
        int starBackgroundColor = viewAttrs.getColor(R.styleable.StarRatingBar_star_bg_color, res.getColor(R.color.play_transparent));
        viewAttrs.recycle();
        this.mStarPaint = new Paint(1);
        this.mStarPaint.setColor(starColor);
        this.mStarPaint.setStyle(Style.FILL);
        this.mStarBackgroundPaint = new Paint(1);
        this.mStarBackgroundPaint.setColor(starBackgroundColor);
        this.mStarBackgroundPaint.setStyle(Style.FILL);
        this.mStarPath = new Path();
        this.mStarPath.setFillType(FillType.EVEN_ODD);
        this.mLeftHalfStarPath = new Path();
        this.mLeftHalfStarPath.setFillType(FillType.EVEN_ODD);
        this.mRightHalfStarPath = new Path();
        this.mRightHalfStarPath.setFillType(FillType.EVEN_ODD);
        this.mRadius = this.mStarHeight / (1.0d + Math.sin(0.9424777960769379d));
        this.mShortRadius = (Math.sin(0.39269908169872414d) * this.mRadius) / Math.sin(2.1205750411731104d);
        this.mHalfStarWidth = (float) (this.mRadius * Math.sin(1.2566370614359172d));
        this.mVertices = new PointF[10];
        for (int i = 0; i < 10; i++) {
            this.mVertices[i] = new PointF();
        }
        initializeStarVertices();
        initializeStarPaths();
        setWillNotDraw(false);
    }

    public float getRating() {
        return this.mRating;
    }

    public void setRating(float rating) {
        this.mRating = rating;
        if (sContentDescriptionFormatter == null) {
            sContentDescriptionFormatter = NumberFormat.getNumberInstance();
            sContentDescriptionFormatter.setMinimumFractionDigits(0);
            sContentDescriptionFormatter.setMaximumFractionDigits(1);
        }
        String formattedRating = sContentDescriptionFormatter.format((double) rating);
        setContentDescription(getResources().getString(R.string.play_star_rating_content_description, new Object[]{formattedRating}));
        invalidate();
    }

    public int getBaseline() {
        return getMeasuredHeight();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int numOfFullStars = (int) this.mRating;
        int numOfHalfStar = Float.compare(this.mRating % 1.0f, 0.0f) > 0 ? 1 : 0;
        int currentStarToDraw = 0;
        float x = ((float) paddingLeft) + this.mHalfStarWidth;
        float y = ((float) paddingTop) + ((float) this.mRadius);
        while (currentStarToDraw < numOfFullStars) {
            canvas.save();
            canvas.translate((((float) currentStarToDraw) * ((this.mHalfStarWidth * 2.0f) + this.mGap)) + x, y);
            canvas.drawPath(this.mStarPath, this.mStarPaint);
            canvas.restore();
            currentStarToDraw++;
        }
        if (numOfHalfStar == 1) {
            canvas.save();
            canvas.translate((((float) currentStarToDraw) * ((this.mHalfStarWidth * 2.0f) + this.mGap)) + x, y);
            canvas.drawPath(this.mLeftHalfStarPath, this.mStarPaint);
            canvas.drawPath(this.mRightHalfStarPath, this.mStarBackgroundPaint);
            canvas.restore();
            currentStarToDraw++;
        }
        while (((float) currentStarToDraw) < this.mRange) {
            canvas.save();
            canvas.translate((((float) currentStarToDraw) * ((this.mHalfStarWidth * 2.0f) + this.mGap)) + x, y);
            canvas.drawPath(this.mStarPath, this.mStarBackgroundPaint);
            canvas.restore();
            currentStarToDraw++;
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) == 1073741824 && MeasureSpec.getMode(heightMeasureSpec) == 1073741824) {
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
        } else {
            setMeasuredDimension((int) ((((float) (getPaddingLeft() + getPaddingRight())) + ((this.mRange * 2.0f) * this.mHalfStarWidth)) + ((this.mRange - 1.0f) * this.mGap)), (int) (((double) (getPaddingTop() + getPaddingBottom())) + this.mStarHeight));
        }
    }

    private void initializeStarVertices() {
        this.mVertices[0].x = 0.0f;
        this.mVertices[0].y = ((float) this.mRadius) * -1.0f;
        this.mVertices[1].x = (float) (this.mShortRadius * Math.sin(0.6283185307179586d));
        this.mVertices[1].y = ((float) (this.mShortRadius * Math.cos(0.6283185307179586d))) * -1.0f;
        this.mVertices[2].x = (float) (this.mRadius * Math.sin(1.2566370614359172d));
        this.mVertices[2].y = ((float) (this.mRadius * Math.cos(1.2566370614359172d))) * -1.0f;
        this.mVertices[3].x = (float) (this.mShortRadius * Math.sin(1.2566370614359172d));
        this.mVertices[3].y = (float) (this.mShortRadius * Math.cos(1.2566370614359172d));
        this.mVertices[4].x = (float) (this.mRadius * Math.sin(0.6283185307179586d));
        this.mVertices[4].y = (float) (((double) ((float) this.mRadius)) * Math.cos(0.6283185307179586d));
        this.mVertices[5].x = 0.0f;
        this.mVertices[5].y = (float) this.mShortRadius;
        this.mVertices[6].x = this.mVertices[4].x * -1.0f;
        this.mVertices[6].y = this.mVertices[4].y;
        this.mVertices[7].x = this.mVertices[3].x * -1.0f;
        this.mVertices[7].y = this.mVertices[3].y;
        this.mVertices[8].x = this.mVertices[2].x * -1.0f;
        this.mVertices[8].y = this.mVertices[2].y;
        this.mVertices[9].x = this.mVertices[1].x * -1.0f;
        this.mVertices[9].y = this.mVertices[1].y;
    }

    private void initializeStarPaths() {
        int i;
        this.mStarPath.reset();
        this.mStarPath.moveTo(this.mVertices[0].x, this.mVertices[0].y);
        for (i = 1; i < this.mVertices.length; i++) {
            this.mStarPath.lineTo(this.mVertices[i].x, this.mVertices[i].y);
        }
        this.mStarPath.close();
        this.mLeftHalfStarPath.reset();
        this.mLeftHalfStarPath.moveTo(this.mVertices[0].x, this.mVertices[0].y);
        for (i = 5; i < this.mVertices.length; i++) {
            this.mLeftHalfStarPath.lineTo(this.mVertices[i].x, this.mVertices[i].y);
        }
        this.mLeftHalfStarPath.close();
        this.mRightHalfStarPath.reset();
        this.mRightHalfStarPath.moveTo(this.mVertices[0].x, this.mVertices[0].y);
        for (i = 1; i <= 5; i++) {
            this.mRightHalfStarPath.lineTo(this.mVertices[i].x, this.mVertices[i].y);
        }
        this.mRightHalfStarPath.close();
    }
}
