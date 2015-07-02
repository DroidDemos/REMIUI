package com.google.android.finsky.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.wallet.instrumentmanager.R;

public abstract class AbsHorizontalStrip extends ViewGroup {
    protected Context mContext;
    protected final float mDeceleration;
    protected int mGapMargin;
    private boolean mIsBeingDragged;
    private float mLastMotionX;
    protected int mLeadingMargin;
    private EdgeEffectCompat mLeftEdge;
    protected float mOriginalPixelOffsetOfFirstChild;
    private EdgeEffectCompat mRightEdge;
    private AnimationRunnable mScrollAnimation;
    private final int mScrollThreshold;
    protected float mTotalChildrenWidth;
    private VelocityTracker mVelocityTracker;
    private float mXDistanceScrolledSinceLastDown;

    private class AnimationRunnable implements Runnable {
        private float mDurationSec;
        private long mStartTimeNs;
        private float mVelocity;

        public AnimationRunnable(float velocity, long durationMs) {
            this.mStartTimeNs = System.nanoTime();
            this.mDurationSec = ((float) durationMs) / 1000.0f;
            this.mVelocity = velocity;
        }

        public void run() {
            float elapsedSec = ((float) (System.nanoTime() - this.mStartTimeNs)) / 1.0E9f;
            if (elapsedSec > this.mDurationSec) {
                elapsedSec = this.mDurationSec;
            } else {
                scheduleFrame();
            }
            float travelled = (Math.abs(this.mVelocity) * elapsedSec) - (((AbsHorizontalStrip.this.mDeceleration * elapsedSec) * elapsedSec) / 2.0f);
            if (this.mVelocity < 0.0f) {
                travelled = -travelled;
            }
            AbsHorizontalStrip.this.updateScrollPosition(AbsHorizontalStrip.this.mOriginalPixelOffsetOfFirstChild + ((float) Math.round(travelled)));
        }

        public void start() {
            scheduleFrame();
        }

        private void scheduleFrame() {
            if (VERSION.SDK_INT >= 16) {
                AbsHorizontalStrip.this.postOnAnimation(this);
            } else {
                AbsHorizontalStrip.this.post(this);
            }
        }
    }

    abstract float getLeftEdgeOfChild(int i);

    abstract float getLeftEdgeOfChildOnLeft(float f);

    abstract float getLeftEdgeOfChildOnRight(float f);

    public AbsHorizontalStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.mOriginalPixelOffsetOfFirstChild = 0.0f;
        this.mContext = context;
        this.mScrollThreshold = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mDeceleration = (1158.2634f * (context.getResources().getDisplayMetrics().density * 160.0f)) * ViewConfiguration.getScrollFriction();
        this.mLeftEdge = new EdgeEffectCompat(context);
        this.mRightEdge = new EdgeEffectCompat(context);
        setWillNotDraw(false);
    }

    protected AnimationRunnable createScrollAnimation(float velocity, long scrollDurationMs) {
        this.mOriginalPixelOffsetOfFirstChild = getScrollPosition();
        return new AnimationRunnable(velocity, scrollDurationMs);
    }

    protected float clampToTotalStripWidth(float value) {
        if (this.mTotalChildrenWidth == 0.0f) {
            return value;
        }
        while (value < 0.0f) {
            value += this.mTotalChildrenWidth;
        }
        while (value >= this.mTotalChildrenWidth) {
            value -= this.mTotalChildrenWidth;
        }
        return value;
    }

    private void onTouchEventDown(float x, float y) {
        this.mLastMotionX = x;
        this.mXDistanceScrolledSinceLastDown = 0.0f;
    }

    private void onTouchEventMove(float x, float y) {
        float scrollX = this.mLastMotionX - x;
        this.mLastMotionX = x;
        int width = getWidth();
        if (this.mTotalChildrenWidth > ((float) width)) {
            updateScrollPosition(getScrollPosition() - scrollX);
        }
        boolean needsInvalidation = false;
        if (getScrollPosition() == 0.0f && scrollX < 0.0f) {
            needsInvalidation = this.mLeftEdge.onPull((-scrollX) / ((float) width));
        } else if (((float) width) - getScrollPosition() == this.mTotalChildrenWidth) {
            needsInvalidation = this.mRightEdge.onPull(scrollX / ((float) width));
        }
        if (needsInvalidation) {
            invalidate();
        }
    }

    private void startInterceptingEvents() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    private void onTouchEventDone(float x, float velocity, boolean isCancel) {
        float totalDistance;
        float f;
        float absVelocity = Math.abs(velocity);
        if (absVelocity > ((float) ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity())) {
            if (this.mTotalChildrenWidth > ((float) getWidth())) {
                float duration = absVelocity / this.mDeceleration;
                totalDistance = (absVelocity * duration) - (((this.mDeceleration * duration) * duration) / 2.0f);
                boolean movingToLeft = velocity < 0.0f;
                float scrollPosition = getScrollPosition();
                if (movingToLeft) {
                    f = -totalDistance;
                } else {
                    f = totalDistance;
                }
                float offsetAtStop = clampToTotalStripWidth(f + scrollPosition);
                if (movingToLeft) {
                    velocity = -FloatMath.sqrt((2.0f * this.mDeceleration) * (totalDistance + (getLeftEdgeOfChildOnRight(clampToTotalStripWidth(this.mTotalChildrenWidth - offsetAtStop)) - clampToTotalStripWidth(this.mTotalChildrenWidth - offsetAtStop))));
                } else {
                    velocity = FloatMath.sqrt((2.0f * this.mDeceleration) * (totalDistance + (clampToTotalStripWidth(this.mTotalChildrenWidth - offsetAtStop) - getLeftEdgeOfChildOnLeft(clampToTotalStripWidth(this.mTotalChildrenWidth - offsetAtStop)))));
                }
                runScrollAnimation(velocity, Math.abs(velocity) / this.mDeceleration);
                if (this.mIsBeingDragged) {
                    boolean needsLeftInvalidation = this.mLeftEdge.onRelease();
                    boolean needsRightInvalidation = this.mRightEdge.onRelease();
                    if (needsLeftInvalidation || needsRightInvalidation) {
                        invalidate();
                    }
                }
                this.mIsBeingDragged = false;
                return;
            }
        }
        if (this.mXDistanceScrolledSinceLastDown > ((float) this.mScrollThreshold) || isCancel || this.mIsBeingDragged) {
            if (this.mTotalChildrenWidth > ((float) getWidth())) {
                float currPos = clampToTotalStripWidth(this.mTotalChildrenWidth - getScrollPosition());
                totalDistance = 0.0f;
                int currLeft = 0;
                int i = 0;
                while (i < getChildCount()) {
                    int currRight = currLeft + getChildAt(i).getWidth();
                    if (((float) currRight) >= currPos) {
                        totalDistance = currPos - clampToTotalStripWidth(((float) currRight) - currPos > currPos - ((float) currLeft) ? getLeftEdgeOfChildOnLeft(currPos) : getLeftEdgeOfChildOnRight(currPos));
                        absVelocity = FloatMath.sqrt(Math.abs((2.0f * this.mDeceleration) * totalDistance));
                        duration = absVelocity / this.mDeceleration;
                        if (totalDistance >= 0.0f) {
                            f = -absVelocity;
                        } else {
                            f = absVelocity;
                        }
                        runScrollAnimation(f, duration);
                    } else {
                        currLeft = currRight + this.mGapMargin;
                        i++;
                    }
                }
                absVelocity = FloatMath.sqrt(Math.abs((2.0f * this.mDeceleration) * totalDistance));
                duration = absVelocity / this.mDeceleration;
                if (totalDistance >= 0.0f) {
                    f = absVelocity;
                } else {
                    f = -absVelocity;
                }
                runScrollAnimation(f, duration);
            }
            if (this.mIsBeingDragged) {
                boolean needsLeftInvalidation2 = this.mLeftEdge.onRelease();
                boolean needsRightInvalidation2 = this.mRightEdge.onRelease();
                invalidate();
            }
            this.mIsBeingDragged = false;
            return;
        }
        this.mOriginalPixelOffsetOfFirstChild = 0.0f;
    }

    protected void onChildAcquiredFocus(int childIndex) {
        if (this.mTotalChildrenWidth > ((float) getWidth())) {
            float scrollDistance = (-getLeftEdgeOfChild(childIndex)) - getScrollPosition();
            float absVelocity = FloatMath.sqrt(Math.abs((2.0f * this.mDeceleration) * scrollDistance));
            float duration = absVelocity / this.mDeceleration;
            if (scrollDistance < 0.0f) {
                absVelocity = -absVelocity;
            }
            runScrollAnimation(absVelocity, duration);
        }
    }

    private void initOrResetVelocityTracker() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            this.mVelocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        float y = event.getY();
        float x = event.getX();
        int action = event.getAction();
        if (action == 2 && this.mIsBeingDragged) {
            return true;
        }
        switch (action & 255) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                initOrResetVelocityTracker();
                this.mVelocityTracker.addMovement(event);
                this.mIsBeingDragged = false;
                onTouchEventDown(x, y);
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                float scrollX = this.mLastMotionX - x;
                this.mLastMotionX = x;
                this.mXDistanceScrolledSinceLastDown += Math.abs(scrollX);
                if (this.mXDistanceScrolledSinceLastDown > ((float) this.mScrollThreshold)) {
                    initVelocityTrackerIfNotExists();
                    this.mVelocityTracker.addMovement(event);
                    startInterceptingEvents();
                    this.mIsBeingDragged = true;
                    break;
                }
                break;
        }
        return this.mIsBeingDragged;
    }

    public synchronized boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        float x = event.getX();
        initVelocityTrackerIfNotExists();
        this.mVelocityTracker.addMovement(event);
        int action = event.getAction();
        switch (action & 255) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                this.mVelocityTracker.computeCurrentVelocity(1000, 1250.0f);
                onTouchEventDone(x, this.mVelocityTracker.getXVelocity(), action == 3);
                recycleVelocityTracker();
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                onTouchEventMove(x, y);
                break;
        }
        return true;
    }

    private void updateScrollPosition(float targetValue) {
        scrollTo(-((int) limitScrollPosition(targetValue)), 0);
        invalidate();
    }

    protected void runScrollAnimation(float velocity, float duration) {
        this.mScrollAnimation = createScrollAnimation(velocity, (long) Math.abs(1000.0f * duration));
        this.mScrollAnimation.start();
    }

    protected float getScrollPosition() {
        return (float) (-getScrollX());
    }

    protected float limitScrollPosition(float targetValue) {
        if (targetValue > 0.0f) {
            targetValue = 0.0f;
        }
        int maxFromRight = (int) (this.mTotalChildrenWidth - ((float) getWidth()));
        if ((-targetValue) > ((float) maxFromRight)) {
            return (float) (-maxFromRight);
        }
        return targetValue;
    }

    public void setMargins(int leadingMargin, int gapMargin) {
        this.mLeadingMargin = leadingMargin;
        this.mGapMargin = gapMargin;
        requestLayout();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean needsInvalidate = false;
        if (!this.mLeftEdge.isFinished()) {
            int restoreCount = canvas.save();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            canvas.rotate(270.0f);
            canvas.translate((float) ((-height) + getPaddingTop()), 0.0f);
            this.mLeftEdge.setSize(height, (int) this.mTotalChildrenWidth);
            needsInvalidate = false | this.mLeftEdge.draw(canvas);
            canvas.restoreToCount(restoreCount);
        }
        if (!this.mRightEdge.isFinished()) {
            restoreCount = canvas.save();
            height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            canvas.rotate(90.0f);
            canvas.translate((float) (-getPaddingTop()), -this.mTotalChildrenWidth);
            this.mRightEdge.setSize(height, (int) this.mTotalChildrenWidth);
            needsInvalidate |= this.mRightEdge.draw(canvas);
            canvas.restoreToCount(restoreCount);
        }
        if (needsInvalidate) {
            invalidate();
        }
    }
}
