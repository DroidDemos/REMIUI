package android.support.v4.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.wallet.instrumentmanager.R;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class SlidingPaneLayout extends ViewGroup {
    static final SlidingPanelLayoutImpl IMPL;
    private boolean mCanSlide;
    private int mCoveredFadeColor;
    private final ViewDragHelper mDragHelper;
    private boolean mFirstLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsUnableToDrag;
    private final int mOverhangSize;
    private PanelSlideListener mPanelSlideListener;
    private int mParallaxBy;
    private float mParallaxOffset;
    private final ArrayList<DisableLayerRunnable> mPostedRunnables;
    private boolean mPreservedOpenState;
    private Drawable mShadowDrawableLeft;
    private Drawable mShadowDrawableRight;
    private float mSlideOffset;
    private int mSlideRange;
    private View mSlideableView;
    private int mSliderFadeColor;
    private final Rect mTmpRect;

    private class DisableLayerRunnable implements Runnable {
        final View mChildView;

        DisableLayerRunnable(View childView) {
            this.mChildView = childView;
        }

        public void run() {
            if (this.mChildView.getParent() == SlidingPaneLayout.this) {
                ViewCompat.setLayerType(this.mChildView, 0, null);
                SlidingPaneLayout.this.invalidateChildRegion(this.mChildView);
            }
            SlidingPaneLayout.this.mPostedRunnables.remove(this);
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        private static final int[] ATTRS;
        Paint dimPaint;
        boolean dimWhenOffset;
        boolean slideable;
        public float weight;

        static {
            ATTRS = new int[]{16843137};
        }

        public LayoutParams() {
            super(-1, -1);
            this.weight = 0.0f;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
            this.weight = 0.0f;
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
            this.weight = 0.0f;
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.weight = 0.0f;
            TypedArray a = c.obtainStyledAttributes(attrs, ATTRS);
            this.weight = a.getFloat(0, 0.0f);
            a.recycle();
        }
    }

    public interface PanelSlideListener {
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        boolean isOpen;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.isOpen = in.readInt() != 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.isOpen ? 1 : 0);
        }

        static {
            CREATOR = new Creator<SavedState>() {
                public SavedState createFromParcel(Parcel in) {
                    return new SavedState(in);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
        }
    }

    interface SlidingPanelLayoutImpl {
        void invalidateChildRegion(SlidingPaneLayout slidingPaneLayout, View view);
    }

    static class SlidingPanelLayoutImplBase implements SlidingPanelLayoutImpl {
        SlidingPanelLayoutImplBase() {
        }

        public void invalidateChildRegion(SlidingPaneLayout parent, View child) {
            ViewCompat.postInvalidateOnAnimation(parent, child.getLeft(), child.getTop(), child.getRight(), child.getBottom());
        }
    }

    static class SlidingPanelLayoutImplJB extends SlidingPanelLayoutImplBase {
        private Method mGetDisplayList;
        private Field mRecreateDisplayList;

        SlidingPanelLayoutImplJB() {
            try {
                this.mGetDisplayList = View.class.getDeclaredMethod("getDisplayList", (Class[]) null);
            } catch (NoSuchMethodException e) {
                Log.e("SlidingPaneLayout", "Couldn't fetch getDisplayList method; dimming won't work right.", e);
            }
            try {
                this.mRecreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                this.mRecreateDisplayList.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e("SlidingPaneLayout", "Couldn't fetch mRecreateDisplayList field; dimming will be slow.", e2);
            }
        }

        public void invalidateChildRegion(SlidingPaneLayout parent, View child) {
            if (this.mGetDisplayList == null || this.mRecreateDisplayList == null) {
                child.invalidate();
                return;
            }
            try {
                this.mRecreateDisplayList.setBoolean(child, true);
                this.mGetDisplayList.invoke(child, (Object[]) null);
            } catch (Exception e) {
                Log.e("SlidingPaneLayout", "Error refreshing display list state", e);
            }
            super.invalidateChildRegion(parent, child);
        }
    }

    static class SlidingPanelLayoutImplJBMR1 extends SlidingPanelLayoutImplBase {
        SlidingPanelLayoutImplJBMR1() {
        }

        public void invalidateChildRegion(SlidingPaneLayout parent, View child) {
            ViewCompat.setLayerPaint(child, ((LayoutParams) child.getLayoutParams()).dimPaint);
        }
    }

    static {
        int deviceVersion = VERSION.SDK_INT;
        if (deviceVersion >= 17) {
            IMPL = new SlidingPanelLayoutImplJBMR1();
        } else if (deviceVersion >= 16) {
            IMPL = new SlidingPanelLayoutImplJB();
        } else {
            IMPL = new SlidingPanelLayoutImplBase();
        }
    }

    public void setParallaxDistance(int parallaxBy) {
        this.mParallaxBy = parallaxBy;
        requestLayout();
    }

    public int getParallaxDistance() {
        return this.mParallaxBy;
    }

    public void setSliderFadeColor(int color) {
        this.mSliderFadeColor = color;
    }

    public int getSliderFadeColor() {
        return this.mSliderFadeColor;
    }

    public void setCoveredFadeColor(int color) {
        this.mCoveredFadeColor = color;
    }

    public int getCoveredFadeColor() {
        return this.mCoveredFadeColor;
    }

    public void setPanelSlideListener(PanelSlideListener listener) {
        this.mPanelSlideListener = listener;
    }

    void updateObscuredViewsVisibility(View panel) {
        int endBound;
        int left;
        boolean isLayoutRtl = isLayoutRtlSupport();
        int startBound = isLayoutRtl ? getWidth() - getPaddingRight() : getPaddingLeft();
        if (isLayoutRtl) {
            endBound = getPaddingLeft();
        } else {
            endBound = getWidth() - getPaddingRight();
        }
        int topBound = getPaddingTop();
        int bottomBound = getHeight() - getPaddingBottom();
        int bottom;
        int top;
        int right;
        if (panel == null || !viewIsOpaque(panel)) {
            bottom = 0;
            top = 0;
            right = 0;
            left = 0;
        } else {
            left = panel.getLeft();
            right = panel.getRight();
            top = panel.getTop();
            bottom = panel.getBottom();
        }
        int i = 0;
        int childCount = getChildCount();
        while (i < childCount) {
            View child = getChildAt(i);
            if (child != panel) {
                int i2;
                int vis;
                if (isLayoutRtl) {
                    i2 = endBound;
                } else {
                    i2 = startBound;
                }
                int clampedChildLeft = Math.max(i2, child.getLeft());
                int clampedChildTop = Math.max(topBound, child.getTop());
                if (isLayoutRtl) {
                    i2 = startBound;
                } else {
                    i2 = endBound;
                }
                int clampedChildRight = Math.min(i2, child.getRight());
                int clampedChildBottom = Math.min(bottomBound, child.getBottom());
                if (clampedChildLeft < left || clampedChildTop < top || clampedChildRight > right || clampedChildBottom > bottom) {
                    vis = 0;
                } else {
                    vis = 4;
                }
                child.setVisibility(vis);
                i++;
            } else {
                return;
            }
        }
    }

    void setAllChildrenVisible() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 4) {
                child.setVisibility(0);
            }
        }
    }

    private static boolean viewIsOpaque(View v) {
        if (ViewCompat.isOpaque(v)) {
            return true;
        }
        if (VERSION.SDK_INT >= 18) {
            return false;
        }
        Drawable bg = v.getBackground();
        if (bg == null) {
            return false;
        }
        if (bg.getOpacity() != -1) {
            return false;
        }
        return true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
        int count = this.mPostedRunnables.size();
        for (int i = 0; i < count; i++) {
            ((DisableLayerRunnable) this.mPostedRunnables.get(i)).run();
        }
        this.mPostedRunnables.clear();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int i2;
        int childHeightSpec;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode != 1073741824) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
            } else if (widthMode != Integer.MIN_VALUE) {
                if (widthMode == 0) {
                    widthSize = 300;
                }
            }
        } else if (heightMode == 0) {
            if (!isInEditMode()) {
                throw new IllegalStateException("Height must not be UNSPECIFIED");
            } else if (heightMode == 0) {
                heightMode = Integer.MIN_VALUE;
                heightSize = 300;
            }
        }
        int layoutHeight = 0;
        int maxLayoutHeight = -1;
        switch (heightMode) {
            case Integer.MIN_VALUE:
                maxLayoutHeight = (heightSize - getPaddingTop()) - getPaddingBottom();
                break;
            case 1073741824:
                maxLayoutHeight = (heightSize - getPaddingTop()) - getPaddingBottom();
                layoutHeight = maxLayoutHeight;
                break;
        }
        float weightSum = 0.0f;
        boolean canSlide = false;
        int widthAvailable = (widthSize - getPaddingLeft()) - getPaddingRight();
        int widthRemaining = widthAvailable;
        int childCount = getChildCount();
        if (childCount > 2) {
            Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
        }
        this.mSlideableView = null;
        for (i = 0; i < childCount; i++) {
            int childWidthSpec;
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (child.getVisibility() == 8) {
                lp.dimWhenOffset = false;
            } else {
                if (lp.weight > 0.0f) {
                    weightSum += lp.weight;
                    if (lp.width == 0) {
                    }
                }
                int horizontalMargin = lp.leftMargin + lp.rightMargin;
                i2 = lp.width;
                if (r0 == -2) {
                    childWidthSpec = MeasureSpec.makeMeasureSpec(widthAvailable - horizontalMargin, Integer.MIN_VALUE);
                } else {
                    i2 = lp.width;
                    if (r0 == -1) {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(widthAvailable - horizontalMargin, 1073741824);
                    } else {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, 1073741824);
                    }
                }
                i2 = lp.height;
                if (r0 == -2) {
                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                } else {
                    i2 = lp.height;
                    if (r0 == -1) {
                        childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                    } else {
                        childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                    }
                }
                child.measure(childWidthSpec, childHeightSpec);
                int childWidth = child.getMeasuredWidth();
                int childHeight = child.getMeasuredHeight();
                if (heightMode == Integer.MIN_VALUE && childHeight > layoutHeight) {
                    layoutHeight = Math.min(childHeight, maxLayoutHeight);
                }
                widthRemaining -= childWidth;
                boolean z = widthRemaining < 0;
                lp.slideable = z;
                canSlide |= z;
                if (lp.slideable) {
                    this.mSlideableView = child;
                }
            }
        }
        if (canSlide || weightSum > 0.0f) {
            int fixedPanelWidthLimit = widthAvailable - this.mOverhangSize;
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                if (child.getVisibility() != 8) {
                    lp = (LayoutParams) child.getLayoutParams();
                    if (child.getVisibility() != 8) {
                        boolean skippedFirstPass;
                        int measuredWidth;
                        View view;
                        int newWidth;
                        if (lp.width == 0) {
                            if (lp.weight > 0.0f) {
                                skippedFirstPass = true;
                                measuredWidth = skippedFirstPass ? 0 : child.getMeasuredWidth();
                                if (canSlide) {
                                    view = this.mSlideableView;
                                    if (child != r0) {
                                        if (lp.width >= 0) {
                                            if (measuredWidth <= fixedPanelWidthLimit) {
                                                if (lp.weight <= 0.0f) {
                                                }
                                            }
                                            if (skippedFirstPass) {
                                                childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824);
                                            } else {
                                                i2 = lp.height;
                                                if (r0 != -2) {
                                                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                                                } else {
                                                    i2 = lp.height;
                                                    if (r0 != -1) {
                                                        childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                                                    } else {
                                                        childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                                                    }
                                                }
                                            }
                                            child.measure(MeasureSpec.makeMeasureSpec(fixedPanelWidthLimit, 1073741824), childHeightSpec);
                                        }
                                    }
                                }
                                if (lp.weight <= 0.0f) {
                                    if (lp.width != 0) {
                                        i2 = lp.height;
                                        if (r0 != -2) {
                                            childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                                        } else {
                                            i2 = lp.height;
                                            if (r0 != -1) {
                                                childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                                            } else {
                                                childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                                            }
                                        }
                                    } else {
                                        childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824);
                                    }
                                    if (canSlide) {
                                        child.measure(MeasureSpec.makeMeasureSpec(measuredWidth + ((int) ((lp.weight * ((float) Math.max(0, widthRemaining))) / weightSum)), 1073741824), childHeightSpec);
                                    } else {
                                        newWidth = widthAvailable - (lp.leftMargin + lp.rightMargin);
                                        childWidthSpec = MeasureSpec.makeMeasureSpec(newWidth, 1073741824);
                                        if (measuredWidth != newWidth) {
                                            child.measure(childWidthSpec, childHeightSpec);
                                        }
                                    }
                                }
                            }
                        }
                        skippedFirstPass = false;
                        if (skippedFirstPass) {
                        }
                        if (canSlide) {
                            view = this.mSlideableView;
                            if (child != r0) {
                                if (lp.width >= 0) {
                                    if (measuredWidth <= fixedPanelWidthLimit) {
                                        if (lp.weight <= 0.0f) {
                                        }
                                    }
                                    if (skippedFirstPass) {
                                        childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824);
                                    } else {
                                        i2 = lp.height;
                                        if (r0 != -2) {
                                            i2 = lp.height;
                                            if (r0 != -1) {
                                                childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                                            } else {
                                                childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                                            }
                                        } else {
                                            childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                                        }
                                    }
                                    child.measure(MeasureSpec.makeMeasureSpec(fixedPanelWidthLimit, 1073741824), childHeightSpec);
                                }
                            }
                        }
                        if (lp.weight <= 0.0f) {
                            if (lp.width != 0) {
                                childHeightSpec = MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), 1073741824);
                            } else {
                                i2 = lp.height;
                                if (r0 != -2) {
                                    i2 = lp.height;
                                    if (r0 != -1) {
                                        childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, 1073741824);
                                    } else {
                                        childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, 1073741824);
                                    }
                                } else {
                                    childHeightSpec = MeasureSpec.makeMeasureSpec(maxLayoutHeight, Integer.MIN_VALUE);
                                }
                            }
                            if (canSlide) {
                                child.measure(MeasureSpec.makeMeasureSpec(measuredWidth + ((int) ((lp.weight * ((float) Math.max(0, widthRemaining))) / weightSum)), 1073741824), childHeightSpec);
                            } else {
                                newWidth = widthAvailable - (lp.leftMargin + lp.rightMargin);
                                childWidthSpec = MeasureSpec.makeMeasureSpec(newWidth, 1073741824);
                                if (measuredWidth != newWidth) {
                                    child.measure(childWidthSpec, childHeightSpec);
                                }
                            }
                        }
                    }
                }
            }
        }
        setMeasuredDimension(widthSize, (getPaddingTop() + layoutHeight) + getPaddingBottom());
        this.mCanSlide = canSlide;
        if (this.mDragHelper.getViewDragState() != 0 && !canSlide) {
            this.mDragHelper.abort();
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        boolean isLayoutRtl = isLayoutRtlSupport();
        if (isLayoutRtl) {
            this.mDragHelper.setEdgeTrackingEnabled(2);
        } else {
            this.mDragHelper.setEdgeTrackingEnabled(1);
        }
        int width = r - l;
        int paddingStart = isLayoutRtl ? getPaddingRight() : getPaddingLeft();
        int paddingEnd = isLayoutRtl ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int childCount = getChildCount();
        int xStart = paddingStart;
        int nextXStart = xStart;
        if (this.mFirstLayout) {
            float f = (this.mCanSlide && this.mPreservedOpenState) ? 1.0f : 0.0f;
            this.mSlideOffset = f;
        }
        for (i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                int childRight;
                int childLeft;
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth();
                int offset = 0;
                if (lp.slideable) {
                    int range = (Math.min(nextXStart, (width - paddingEnd) - this.mOverhangSize) - xStart) - (lp.leftMargin + lp.rightMargin);
                    this.mSlideRange = range;
                    int lpMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
                    lp.dimWhenOffset = ((xStart + lpMargin) + range) + (childWidth / 2) > width - paddingEnd;
                    int pos = (int) (((float) range) * this.mSlideOffset);
                    xStart += pos + lpMargin;
                    this.mSlideOffset = ((float) pos) / ((float) this.mSlideRange);
                } else if (!this.mCanSlide || this.mParallaxBy == 0) {
                    xStart = nextXStart;
                } else {
                    offset = (int) ((1.0f - this.mSlideOffset) * ((float) this.mParallaxBy));
                    xStart = nextXStart;
                }
                if (isLayoutRtl) {
                    childRight = (width - xStart) + offset;
                    childLeft = childRight - childWidth;
                } else {
                    childLeft = xStart - offset;
                    childRight = childLeft + childWidth;
                }
                child.layout(childLeft, paddingTop, childRight, paddingTop + child.getMeasuredHeight());
                nextXStart += child.getWidth();
            }
        }
        if (this.mFirstLayout) {
            if (this.mCanSlide) {
                if (this.mParallaxBy != 0) {
                    parallaxOtherViews(this.mSlideOffset);
                }
                if (((LayoutParams) this.mSlideableView.getLayoutParams()).dimWhenOffset) {
                    dimChildView(this.mSlideableView, this.mSlideOffset, this.mSliderFadeColor);
                }
            } else {
                for (i = 0; i < childCount; i++) {
                    dimChildView(getChildAt(i), 0.0f, this.mSliderFadeColor);
                }
            }
            updateObscuredViewsVisibility(this.mSlideableView);
        }
        this.mFirstLayout = false;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            this.mFirstLayout = true;
        }
    }

    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        if (!isInTouchMode() && !this.mCanSlide) {
            this.mPreservedOpenState = child == this.mSlideableView;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        if (!this.mCanSlide && action == 0 && getChildCount() > 1) {
            View secondChild = getChildAt(1);
            if (secondChild != null) {
                this.mPreservedOpenState = !this.mDragHelper.isViewUnder(secondChild, (int) ev.getX(), (int) ev.getY());
            }
        }
        if (!this.mCanSlide || (this.mIsUnableToDrag && action != 0)) {
            this.mDragHelper.cancel();
            return super.onInterceptTouchEvent(ev);
        } else if (action == 3 || action == 1) {
            this.mDragHelper.cancel();
            return false;
        } else {
            boolean interceptTap = false;
            float x;
            float y;
            switch (action) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    this.mIsUnableToDrag = false;
                    x = ev.getX();
                    y = ev.getY();
                    this.mInitialMotionX = x;
                    this.mInitialMotionY = y;
                    if (this.mDragHelper.isViewUnder(this.mSlideableView, (int) x, (int) y) && isDimmed(this.mSlideableView)) {
                        interceptTap = true;
                        break;
                    }
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    x = ev.getX();
                    y = ev.getY();
                    float adx = Math.abs(x - this.mInitialMotionX);
                    float ady = Math.abs(y - this.mInitialMotionY);
                    if (adx > ((float) this.mDragHelper.getTouchSlop()) && ady > adx) {
                        this.mDragHelper.cancel();
                        this.mIsUnableToDrag = true;
                        return false;
                    }
            }
            if (this.mDragHelper.shouldInterceptTouchEvent(ev) || interceptTap) {
                return true;
            }
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (!this.mCanSlide) {
            return super.onTouchEvent(ev);
        }
        this.mDragHelper.processTouchEvent(ev);
        float x;
        float y;
        switch (ev.getAction() & 255) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                x = ev.getX();
                y = ev.getY();
                this.mInitialMotionX = x;
                this.mInitialMotionY = y;
                return true;
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                if (!isDimmed(this.mSlideableView)) {
                    return true;
                }
                x = ev.getX();
                y = ev.getY();
                float dx = x - this.mInitialMotionX;
                float dy = y - this.mInitialMotionY;
                int slop = this.mDragHelper.getTouchSlop();
                if ((dx * dx) + (dy * dy) >= ((float) (slop * slop)) || !this.mDragHelper.isViewUnder(this.mSlideableView, (int) x, (int) y)) {
                    return true;
                }
                closePane(this.mSlideableView, 0);
                return true;
            default:
                return true;
        }
    }

    private boolean closePane(View pane, int initialVelocity) {
        if (!this.mFirstLayout && !smoothSlideTo(0.0f, initialVelocity)) {
            return false;
        }
        this.mPreservedOpenState = false;
        return true;
    }

    private boolean openPane(View pane, int initialVelocity) {
        if (!this.mFirstLayout && !smoothSlideTo(1.0f, initialVelocity)) {
            return false;
        }
        this.mPreservedOpenState = true;
        return true;
    }

    public boolean openPane() {
        return openPane(this.mSlideableView, 0);
    }

    public boolean closePane() {
        return closePane(this.mSlideableView, 0);
    }

    public boolean isOpen() {
        return !this.mCanSlide || this.mSlideOffset == 1.0f;
    }

    public boolean isSlideable() {
        return this.mCanSlide;
    }

    private void dimChildView(View v, float mag, int fadeColor) {
        LayoutParams lp = (LayoutParams) v.getLayoutParams();
        if (mag > 0.0f && fadeColor != 0) {
            int color = (((int) (((float) ((-16777216 & fadeColor) >>> 24)) * mag)) << 24) | (16777215 & fadeColor);
            if (lp.dimPaint == null) {
                lp.dimPaint = new Paint();
            }
            lp.dimPaint.setColorFilter(new PorterDuffColorFilter(color, Mode.SRC_OVER));
            if (ViewCompat.getLayerType(v) != 2) {
                ViewCompat.setLayerType(v, 2, lp.dimPaint);
            }
            invalidateChildRegion(v);
        } else if (ViewCompat.getLayerType(v) != 0) {
            if (lp.dimPaint != null) {
                lp.dimPaint.setColorFilter(null);
            }
            DisableLayerRunnable dlr = new DisableLayerRunnable(v);
            this.mPostedRunnables.add(dlr);
            ViewCompat.postOnAnimation(this, dlr);
        }
    }

    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        boolean result;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int save = canvas.save(2);
        if (!(!this.mCanSlide || lp.slideable || this.mSlideableView == null)) {
            canvas.getClipBounds(this.mTmpRect);
            if (isLayoutRtlSupport()) {
                this.mTmpRect.left = Math.max(this.mTmpRect.left, this.mSlideableView.getRight());
            } else {
                this.mTmpRect.right = Math.min(this.mTmpRect.right, this.mSlideableView.getLeft());
            }
            canvas.clipRect(this.mTmpRect);
        }
        if (VERSION.SDK_INT >= 11) {
            result = super.drawChild(canvas, child, drawingTime);
        } else if (!lp.dimWhenOffset || this.mSlideOffset <= 0.0f) {
            if (child.isDrawingCacheEnabled()) {
                child.setDrawingCacheEnabled(false);
            }
            result = super.drawChild(canvas, child, drawingTime);
        } else {
            if (!child.isDrawingCacheEnabled()) {
                child.setDrawingCacheEnabled(true);
            }
            Bitmap cache = child.getDrawingCache();
            if (cache != null) {
                canvas.drawBitmap(cache, (float) child.getLeft(), (float) child.getTop(), lp.dimPaint);
                result = false;
            } else {
                Log.e("SlidingPaneLayout", "drawChild: child view " + child + " returned null drawing cache");
                result = super.drawChild(canvas, child, drawingTime);
            }
        }
        canvas.restoreToCount(save);
        return result;
    }

    private void invalidateChildRegion(View v) {
        IMPL.invalidateChildRegion(this, v);
    }

    boolean smoothSlideTo(float slideOffset, int velocity) {
        if (!this.mCanSlide) {
            return false;
        }
        int x;
        LayoutParams lp = (LayoutParams) this.mSlideableView.getLayoutParams();
        if (isLayoutRtlSupport()) {
            x = (int) (((float) getWidth()) - ((((float) (getPaddingRight() + lp.rightMargin)) + (((float) this.mSlideRange) * slideOffset)) + ((float) this.mSlideableView.getWidth())));
        } else {
            x = (int) (((float) (getPaddingLeft() + lp.leftMargin)) + (((float) this.mSlideRange) * slideOffset));
        }
        if (!this.mDragHelper.smoothSlideViewTo(this.mSlideableView, x, this.mSlideableView.getTop())) {
            return false;
        }
        setAllChildrenVisible();
        ViewCompat.postInvalidateOnAnimation(this);
        return true;
    }

    public void computeScroll() {
        if (!this.mDragHelper.continueSettling(true)) {
            return;
        }
        if (this.mCanSlide) {
            ViewCompat.postInvalidateOnAnimation(this);
        } else {
            this.mDragHelper.abort();
        }
    }

    @Deprecated
    public void setShadowDrawable(Drawable d) {
        setShadowDrawableLeft(d);
    }

    public void setShadowDrawableLeft(Drawable d) {
        this.mShadowDrawableLeft = d;
    }

    public void setShadowDrawableRight(Drawable d) {
        this.mShadowDrawableRight = d;
    }

    @Deprecated
    public void setShadowResource(int resId) {
        setShadowDrawable(getResources().getDrawable(resId));
    }

    public void setShadowResourceLeft(int resId) {
        setShadowDrawableLeft(getResources().getDrawable(resId));
    }

    public void setShadowResourceRight(int resId) {
        setShadowDrawableRight(getResources().getDrawable(resId));
    }

    public void draw(Canvas c) {
        Drawable shadowDrawable;
        super.draw(c);
        if (isLayoutRtlSupport()) {
            shadowDrawable = this.mShadowDrawableRight;
        } else {
            shadowDrawable = this.mShadowDrawableLeft;
        }
        View shadowView = getChildCount() > 1 ? getChildAt(1) : null;
        if (shadowView != null && shadowDrawable != null) {
            int left;
            int right;
            int top = shadowView.getTop();
            int bottom = shadowView.getBottom();
            int shadowWidth = shadowDrawable.getIntrinsicWidth();
            if (isLayoutRtlSupport()) {
                left = shadowView.getRight();
                right = left + shadowWidth;
            } else {
                right = shadowView.getLeft();
                left = right - shadowWidth;
            }
            shadowDrawable.setBounds(left, top, right, bottom);
            shadowDrawable.draw(c);
        }
    }

    private void parallaxOtherViews(float slideOffset) {
        boolean dimViews;
        int childCount;
        int i;
        View v;
        int dx;
        boolean isLayoutRtl = isLayoutRtlSupport();
        LayoutParams slideLp = (LayoutParams) this.mSlideableView.getLayoutParams();
        if (slideLp.dimWhenOffset) {
            if ((isLayoutRtl ? slideLp.rightMargin : slideLp.leftMargin) <= 0) {
                dimViews = true;
                childCount = getChildCount();
                for (i = 0; i < childCount; i++) {
                    v = getChildAt(i);
                    if (v == this.mSlideableView) {
                        int oldOffset = (int) ((1.0f - this.mParallaxOffset) * ((float) this.mParallaxBy));
                        this.mParallaxOffset = slideOffset;
                        dx = oldOffset - ((int) ((1.0f - slideOffset) * ((float) this.mParallaxBy)));
                        if (isLayoutRtl) {
                            dx = -dx;
                        }
                        v.offsetLeftAndRight(dx);
                        if (!dimViews) {
                            dimChildView(v, isLayoutRtl ? this.mParallaxOffset - 1.0f : 1.0f - this.mParallaxOffset, this.mCoveredFadeColor);
                        }
                    }
                }
            }
        }
        dimViews = false;
        childCount = getChildCount();
        for (i = 0; i < childCount; i++) {
            v = getChildAt(i);
            if (v == this.mSlideableView) {
                int oldOffset2 = (int) ((1.0f - this.mParallaxOffset) * ((float) this.mParallaxBy));
                this.mParallaxOffset = slideOffset;
                dx = oldOffset2 - ((int) ((1.0f - slideOffset) * ((float) this.mParallaxBy)));
                if (isLayoutRtl) {
                    dx = -dx;
                }
                v.offsetLeftAndRight(dx);
                if (!dimViews) {
                    if (isLayoutRtl) {
                    }
                    dimChildView(v, isLayoutRtl ? this.mParallaxOffset - 1.0f : 1.0f - this.mParallaxOffset, this.mCoveredFadeColor);
                }
            }
        }
    }

    boolean isDimmed(View child) {
        if (child == null) {
            return false;
        }
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (this.mCanSlide && lp.dimWhenOffset && this.mSlideOffset > 0.0f) {
            return true;
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) p) : new LayoutParams(p);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.isOpen = isSlideable() ? isOpen() : this.mPreservedOpenState;
        return ss;
    }

    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        if (ss.isOpen) {
            openPane();
        } else {
            closePane();
        }
        this.mPreservedOpenState = ss.isOpen;
    }

    private boolean isLayoutRtlSupport() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }
}
