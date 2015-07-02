package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import java.util.ArrayList;

class MaterialProgressDrawable extends Drawable implements Animatable {
    private static final Interpolator EASE_INTERPOLATOR;
    private static final Interpolator END_CURVE_INTERPOLATOR;
    private static final Interpolator LINEAR_INTERPOLATOR;
    private static final Interpolator START_CURVE_INTERPOLATOR;
    private final int[] COLORS;
    private Animation mAnimation;
    private final ArrayList<Animation> mAnimators;
    private final Callback mCallback;
    boolean mFinishing;
    private double mHeight;
    private View mParent;
    private Resources mResources;
    private final Ring mRing;
    private float mRotation;
    private float mRotationCount;
    private double mWidth;

    private static class EndCurveInterpolator extends AccelerateDecelerateInterpolator {
        private EndCurveInterpolator() {
        }

        public float getInterpolation(float input) {
            return super.getInterpolation(Math.max(0.0f, (input - 0.5f) * 2.0f));
        }
    }

    private static class Ring {
        private int mAlpha;
        private Path mArrow;
        private int mArrowHeight;
        private final Paint mArrowPaint;
        private float mArrowScale;
        private int mArrowWidth;
        private int mBackgroundColor;
        private final Callback mCallback;
        private final Paint mCirclePaint;
        private int mColorIndex;
        private int[] mColors;
        private float mEndTrim;
        private final Paint mPaint;
        private double mRingCenterRadius;
        private float mRotation;
        private boolean mShowArrow;
        private float mStartTrim;
        private float mStartingEndTrim;
        private float mStartingRotation;
        private float mStartingStartTrim;
        private float mStrokeInset;
        private float mStrokeWidth;
        private final RectF mTempBounds;

        public Ring(Callback callback) {
            this.mTempBounds = new RectF();
            this.mPaint = new Paint();
            this.mArrowPaint = new Paint();
            this.mStartTrim = 0.0f;
            this.mEndTrim = 0.0f;
            this.mRotation = 0.0f;
            this.mStrokeWidth = 5.0f;
            this.mStrokeInset = 2.5f;
            this.mCirclePaint = new Paint();
            this.mCallback = callback;
            this.mPaint.setStrokeCap(Cap.SQUARE);
            this.mPaint.setAntiAlias(true);
            this.mPaint.setStyle(Style.STROKE);
            this.mArrowPaint.setStyle(Style.FILL);
            this.mArrowPaint.setAntiAlias(true);
        }

        public void setBackgroundColor(int color) {
            this.mBackgroundColor = color;
        }

        public void setArrowDimensions(float width, float height) {
            this.mArrowWidth = (int) width;
            this.mArrowHeight = (int) height;
        }

        public void draw(Canvas c, Rect bounds) {
            RectF arcBounds = this.mTempBounds;
            arcBounds.set(bounds);
            arcBounds.inset(this.mStrokeInset, this.mStrokeInset);
            float startAngle = (this.mStartTrim + this.mRotation) * 360.0f;
            float sweepAngle = ((this.mEndTrim + this.mRotation) * 360.0f) - startAngle;
            this.mPaint.setColor(this.mColors[this.mColorIndex]);
            c.drawArc(arcBounds, startAngle, sweepAngle, false, this.mPaint);
            drawTriangle(c, startAngle, sweepAngle, bounds);
            if (this.mAlpha < 255) {
                this.mCirclePaint.setColor(this.mBackgroundColor);
                this.mCirclePaint.setAlpha(255 - this.mAlpha);
                c.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), (float) (bounds.width() / 2), this.mCirclePaint);
            }
        }

        private void drawTriangle(Canvas c, float startAngle, float sweepAngle, Rect bounds) {
            if (this.mShowArrow) {
                if (this.mArrow == null) {
                    this.mArrow = new Path();
                    this.mArrow.setFillType(FillType.EVEN_ODD);
                } else {
                    this.mArrow.reset();
                }
                float inset = ((float) (((int) this.mStrokeInset) / 2)) * this.mArrowScale;
                float x = (float) ((this.mRingCenterRadius * Math.cos(0.0d)) + ((double) bounds.exactCenterX()));
                float y = (float) ((this.mRingCenterRadius * Math.sin(0.0d)) + ((double) bounds.exactCenterY()));
                this.mArrow.moveTo(0.0f, 0.0f);
                this.mArrow.lineTo(((float) this.mArrowWidth) * this.mArrowScale, 0.0f);
                this.mArrow.lineTo((((float) this.mArrowWidth) * this.mArrowScale) / 2.0f, ((float) this.mArrowHeight) * this.mArrowScale);
                this.mArrow.offset(x - inset, y);
                this.mArrow.close();
                this.mArrowPaint.setColor(this.mColors[this.mColorIndex]);
                c.rotate((startAngle + sweepAngle) - 5.0f, bounds.exactCenterX(), bounds.exactCenterY());
                c.drawPath(this.mArrow, this.mArrowPaint);
            }
        }

        public void setColors(int[] colors) {
            this.mColors = colors;
            setColorIndex(0);
        }

        public void setColorIndex(int index) {
            this.mColorIndex = index;
        }

        public void goToNextColor() {
            this.mColorIndex = (this.mColorIndex + 1) % this.mColors.length;
        }

        public void setColorFilter(ColorFilter filter) {
            this.mPaint.setColorFilter(filter);
            invalidateSelf();
        }

        public void setAlpha(int alpha) {
            this.mAlpha = alpha;
        }

        public int getAlpha() {
            return this.mAlpha;
        }

        public void setStrokeWidth(float strokeWidth) {
            this.mStrokeWidth = strokeWidth;
            this.mPaint.setStrokeWidth(strokeWidth);
            invalidateSelf();
        }

        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        public void setStartTrim(float startTrim) {
            this.mStartTrim = startTrim;
            invalidateSelf();
        }

        public float getStartTrim() {
            return this.mStartTrim;
        }

        public float getStartingStartTrim() {
            return this.mStartingStartTrim;
        }

        public float getStartingEndTrim() {
            return this.mStartingEndTrim;
        }

        public void setEndTrim(float endTrim) {
            this.mEndTrim = endTrim;
            invalidateSelf();
        }

        public float getEndTrim() {
            return this.mEndTrim;
        }

        public void setRotation(float rotation) {
            this.mRotation = rotation;
            invalidateSelf();
        }

        public void setInsets(int width, int height) {
            float insets;
            float minEdge = (float) Math.min(width, height);
            if (this.mRingCenterRadius <= 0.0d || minEdge < 0.0f) {
                insets = (float) Math.ceil((double) (this.mStrokeWidth / 2.0f));
            } else {
                insets = (float) (((double) (minEdge / 2.0f)) - this.mRingCenterRadius);
            }
            this.mStrokeInset = insets;
        }

        public void setCenterRadius(double centerRadius) {
            this.mRingCenterRadius = centerRadius;
        }

        public double getCenterRadius() {
            return this.mRingCenterRadius;
        }

        public void setShowArrow(boolean show) {
            if (this.mShowArrow != show) {
                this.mShowArrow = show;
                invalidateSelf();
            }
        }

        public void setArrowScale(float scale) {
            if (scale != this.mArrowScale) {
                this.mArrowScale = scale;
                invalidateSelf();
            }
        }

        public float getStartingRotation() {
            return this.mStartingRotation;
        }

        public void storeOriginals() {
            this.mStartingStartTrim = this.mStartTrim;
            this.mStartingEndTrim = this.mEndTrim;
            this.mStartingRotation = this.mRotation;
        }

        public void resetOriginals() {
            this.mStartingStartTrim = 0.0f;
            this.mStartingEndTrim = 0.0f;
            this.mStartingRotation = 0.0f;
            setStartTrim(0.0f);
            setEndTrim(0.0f);
            setRotation(0.0f);
        }

        private void invalidateSelf() {
            this.mCallback.invalidateDrawable(null);
        }
    }

    private static class StartCurveInterpolator extends AccelerateDecelerateInterpolator {
        private StartCurveInterpolator() {
        }

        public float getInterpolation(float input) {
            return super.getInterpolation(Math.min(1.0f, 2.0f * input));
        }
    }

    static {
        LINEAR_INTERPOLATOR = new LinearInterpolator();
        END_CURVE_INTERPOLATOR = new EndCurveInterpolator();
        START_CURVE_INTERPOLATOR = new StartCurveInterpolator();
        EASE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    }

    public MaterialProgressDrawable(Context context, View parent) {
        this.COLORS = new int[]{-16777216};
        this.mAnimators = new ArrayList();
        this.mCallback = new Callback() {
            public void invalidateDrawable(Drawable d) {
                MaterialProgressDrawable.this.invalidateSelf();
            }

            public void scheduleDrawable(Drawable d, Runnable what, long when) {
                MaterialProgressDrawable.this.scheduleSelf(what, when);
            }

            public void unscheduleDrawable(Drawable d, Runnable what) {
                MaterialProgressDrawable.this.unscheduleSelf(what);
            }
        };
        this.mParent = parent;
        this.mResources = context.getResources();
        this.mRing = new Ring(this.mCallback);
        this.mRing.setColors(this.COLORS);
        updateSizes(1);
        setupAnimators();
    }

    private void setSizeParameters(double progressCircleWidth, double progressCircleHeight, double centerRadius, double strokeWidth, float arrowWidth, float arrowHeight) {
        Ring ring = this.mRing;
        float screenDensity = this.mResources.getDisplayMetrics().density;
        this.mWidth = ((double) screenDensity) * progressCircleWidth;
        this.mHeight = ((double) screenDensity) * progressCircleHeight;
        ring.setStrokeWidth(((float) strokeWidth) * screenDensity);
        ring.setCenterRadius(((double) screenDensity) * centerRadius);
        ring.setColorIndex(0);
        ring.setArrowDimensions(arrowWidth * screenDensity, arrowHeight * screenDensity);
        ring.setInsets((int) this.mWidth, (int) this.mHeight);
    }

    public void updateSizes(int size) {
        if (size == 0) {
            setSizeParameters(56.0d, 56.0d, 12.5d, 3.0d, 12.0f, 6.0f);
        } else {
            setSizeParameters(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        }
    }

    public void showArrow(boolean show) {
        this.mRing.setShowArrow(show);
    }

    public void setArrowScale(float scale) {
        this.mRing.setArrowScale(scale);
    }

    public void setStartEndTrim(float startAngle, float endAngle) {
        this.mRing.setStartTrim(startAngle);
        this.mRing.setEndTrim(endAngle);
    }

    public void setProgressRotation(float rotation) {
        this.mRing.setRotation(rotation);
    }

    public void setBackgroundColor(int color) {
        this.mRing.setBackgroundColor(color);
    }

    public void setColorSchemeColors(int... colors) {
        this.mRing.setColors(colors);
        this.mRing.setColorIndex(0);
    }

    public int getIntrinsicHeight() {
        return (int) this.mHeight;
    }

    public int getIntrinsicWidth() {
        return (int) this.mWidth;
    }

    public void draw(Canvas c) {
        Rect bounds = getBounds();
        int saveCount = c.save();
        c.rotate(this.mRotation, bounds.exactCenterX(), bounds.exactCenterY());
        this.mRing.draw(c, bounds);
        c.restoreToCount(saveCount);
    }

    public void setAlpha(int alpha) {
        this.mRing.setAlpha(alpha);
    }

    public int getAlpha() {
        return this.mRing.getAlpha();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mRing.setColorFilter(colorFilter);
    }

    void setRotation(float rotation) {
        this.mRotation = rotation;
        invalidateSelf();
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList<Animation> animators = this.mAnimators;
        int N = animators.size();
        for (int i = 0; i < N; i++) {
            Animation animator = (Animation) animators.get(i);
            if (animator.hasStarted() && !animator.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void start() {
        this.mAnimation.reset();
        this.mRing.storeOriginals();
        if (this.mRing.getEndTrim() != this.mRing.getStartTrim()) {
            this.mFinishing = true;
            this.mAnimation.setDuration(666);
            this.mParent.startAnimation(this.mAnimation);
            return;
        }
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
        this.mAnimation.setDuration(1333);
        this.mParent.startAnimation(this.mAnimation);
    }

    public void stop() {
        this.mParent.clearAnimation();
        setRotation(0.0f);
        this.mRing.setShowArrow(false);
        this.mRing.setColorIndex(0);
        this.mRing.resetOriginals();
    }

    private void applyFinishTranslation(float interpolatedTime, Ring ring) {
        float targetRotation = (float) (Math.floor((double) (ring.getStartingRotation() / 0.8f)) + 1.0d);
        ring.setStartTrim(ring.getStartingStartTrim() + ((ring.getStartingEndTrim() - ring.getStartingStartTrim()) * interpolatedTime));
        ring.setRotation(ring.getStartingRotation() + ((targetRotation - ring.getStartingRotation()) * interpolatedTime));
    }

    private void setupAnimators() {
        final Ring ring = this.mRing;
        Animation animation = new Animation() {
            public void applyTransformation(float interpolatedTime, Transformation t) {
                if (MaterialProgressDrawable.this.mFinishing) {
                    MaterialProgressDrawable.this.applyFinishTranslation(interpolatedTime, ring);
                    return;
                }
                float minProgressArc = (float) Math.toRadians(((double) ring.getStrokeWidth()) / (6.283185307179586d * ring.getCenterRadius()));
                float startingEndTrim = ring.getStartingEndTrim();
                float startingTrim = ring.getStartingStartTrim();
                float startingRotation = ring.getStartingRotation();
                ring.setEndTrim(startingEndTrim + (MaterialProgressDrawable.START_CURVE_INTERPOLATOR.getInterpolation(interpolatedTime) * (0.8f - minProgressArc)));
                ring.setStartTrim(startingTrim + (0.8f * MaterialProgressDrawable.END_CURVE_INTERPOLATOR.getInterpolation(interpolatedTime)));
                ring.setRotation(startingRotation + (0.25f * interpolatedTime));
                MaterialProgressDrawable.this.setRotation((144.0f * interpolatedTime) + (720.0f * (MaterialProgressDrawable.this.mRotationCount / 5.0f)));
            }
        };
        animation.setRepeatCount(-1);
        animation.setRepeatMode(1);
        animation.setInterpolator(LINEAR_INTERPOLATOR);
        animation.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
                MaterialProgressDrawable.this.mRotationCount = 0.0f;
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
                ring.storeOriginals();
                ring.goToNextColor();
                ring.setStartTrim(ring.getEndTrim());
                if (MaterialProgressDrawable.this.mFinishing) {
                    MaterialProgressDrawable.this.mFinishing = false;
                    animation.setDuration(1333);
                    ring.setShowArrow(false);
                    return;
                }
                MaterialProgressDrawable.this.mRotationCount = (MaterialProgressDrawable.this.mRotationCount + 1.0f) % 5.0f;
            }
        });
        this.mAnimation = animation;
    }
}
