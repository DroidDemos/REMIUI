package android.support.v4.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.google.android.wallet.instrumentmanager.R;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewPager extends ViewGroup {
    private static final Comparator<ItemInfo> COMPARATOR;
    private static final int[] LAYOUT_ATTRS;
    private static final Interpolator sInterpolator;
    private static final ViewPositionComparator sPositionComparator;
    private int mActivePointerId;
    private PagerAdapter mAdapter;
    private OnAdapterChangeListener mAdapterChangeListener;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mChildHeightMeasureSpec;
    private int mChildWidthMeasureSpec;
    private int mCloseEnough;
    private int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    private EdgeEffectCompat mLeftEdge;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private boolean mNeedCalculatePageOffsets;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit;
    private OnPageChangeListener mOnPageChangeListener;
    private int mPageMargin;
    private PageTransformer mPageTransformer;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    private EdgeEffectCompat mRightEdge;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private Method mSetChildrenDrawingOrderEnabled;
    private final ItemInfo mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    interface Decor {
    }

    static class ItemInfo {
        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;

        ItemInfo() {
        }
    }

    public static class LayoutParams extends android.view.ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor;

        public LayoutParams() {
            super(-1, -1);
            this.widthFactor = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.widthFactor = 0.0f;
            TypedArray a = context.obtainStyledAttributes(attrs, ViewPager.LAYOUT_ATTRS);
            this.gravity = a.getInteger(0, 48);
            a.recycle();
        }
    }

    class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        MyAccessibilityDelegate() {
        }

        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat recordCompat = AccessibilityRecordCompat.obtain();
            recordCompat.setScrollable(canScroll());
            if (event.getEventType() == 4096 && ViewPager.this.mAdapter != null) {
                recordCompat.setItemCount(ViewPager.this.mAdapter.getCount());
                recordCompat.setFromIndex(ViewPager.this.mCurItem);
                recordCompat.setToIndex(ViewPager.this.mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(ViewPager.class.getName());
            info.setScrollable(canScroll());
            if (ViewPager.this.canScrollHorizontally(1)) {
                info.addAction(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                info.addAction(8192);
            }
        }

        public boolean performAccessibilityAction(View host, int action, Bundle args) {
            if (super.performAccessibilityAction(host, action, args)) {
                return true;
            }
            switch (action) {
                case 4096:
                    if (!ViewPager.this.canScrollHorizontally(1)) {
                        return false;
                    }
                    ViewPager.this.setCurrentItem(ViewPager.this.mCurItem + 1);
                    return true;
                case 8192:
                    if (!ViewPager.this.canScrollHorizontally(-1)) {
                        return false;
                    }
                    ViewPager.this.setCurrentItem(ViewPager.this.mCurItem - 1);
                    return true;
                default:
                    return false;
            }
        }

        private boolean canScroll() {
            return ViewPager.this.mAdapter != null && ViewPager.this.mAdapter.getCount() > 1;
        }
    }

    interface OnAdapterChangeListener {
        void onAdapterChanged(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, int i2);

        void onPageSelected(int i);
    }

    public interface PageTransformer {
        void transformPage(View view, float f);
    }

    private class PagerObserver extends DataSetObserver {
        private PagerObserver() {
        }

        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }

        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.position);
            out.writeParcelable(this.adapterState, flags);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + "}";
        }

        static {
            CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<SavedState>() {
                public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                    return new SavedState(in, loader);
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            });
        }

        SavedState(Parcel in, ClassLoader loader) {
            super(in);
            if (loader == null) {
                loader = getClass().getClassLoader();
            }
            this.position = in.readInt();
            this.adapterState = in.readParcelable(loader);
            this.loader = loader;
        }
    }

    static class ViewPositionComparator implements Comparator<View> {
        ViewPositionComparator() {
        }

        public int compare(View lhs, View rhs) {
            LayoutParams llp = (LayoutParams) lhs.getLayoutParams();
            LayoutParams rlp = (LayoutParams) rhs.getLayoutParams();
            if (llp.isDecor != rlp.isDecor) {
                return llp.isDecor ? 1 : -1;
            } else {
                return llp.position - rlp.position;
            }
        }
    }

    static {
        LAYOUT_ATTRS = new int[]{16842931};
        COMPARATOR = new Comparator<ItemInfo>() {
            public int compare(ItemInfo lhs, ItemInfo rhs) {
                return lhs.position - rhs.position;
            }
        };
        sInterpolator = new Interpolator() {
            public float getInterpolation(float t) {
                t -= 1.0f;
                return ((((t * t) * t) * t) * t) + 1.0f;
            }
        };
        sPositionComparator = new ViewPositionComparator();
    }

    public ViewPager(Context context) {
        super(context);
        this.mItems = new ArrayList();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = new Runnable() {
            public void run() {
                ViewPager.this.setScrollState(0);
                ViewPager.this.populate();
            }
        };
        this.mScrollState = 0;
        initViewPager();
    }

    public ViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mItems = new ArrayList();
        this.mTempItem = new ItemInfo();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mNeedCalculatePageOffsets = false;
        this.mEndScrollRunnable = /* anonymous class already generated */;
        this.mScrollState = 0;
        initViewPager();
    }

    void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        float density = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
        this.mMinimumVelocity = (int) (400.0f * density);
        this.mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffectCompat(context);
        this.mRightEdge = new EdgeEffectCompat(context);
        this.mFlingDistance = (int) (25.0f * density);
        this.mCloseEnough = (int) (2.0f * density);
        this.mDefaultGutterSize = (int) (16.0f * density);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        super.onDetachedFromWindow();
    }

    private void setScrollState(int newState) {
        if (this.mScrollState != newState) {
            this.mScrollState = newState;
            if (this.mPageTransformer != null) {
                enableLayers(newState != 0);
            }
            if (this.mOnPageChangeListener != null) {
                this.mOnPageChangeListener.onPageScrollStateChanged(newState);
            }
        }
    }

    public void setAdapter(PagerAdapter adapter) {
        if (this.mAdapter != null) {
            this.mAdapter.unregisterDataSetObserver(this.mObserver);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i = 0; i < this.mItems.size(); i++) {
                ItemInfo ii = (ItemInfo) this.mItems.get(i);
                this.mAdapter.destroyItem((ViewGroup) this, ii.position, ii.object);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            removeNonDecorViews();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter oldAdapter = this.mAdapter;
        this.mAdapter = adapter;
        this.mExpectedAdapterCount = 0;
        if (this.mAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new PagerObserver();
            }
            this.mAdapter.registerDataSetObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean wasFirstLayout = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (wasFirstLayout) {
                requestLayout();
            } else {
                populate();
            }
        }
        if (this.mAdapterChangeListener != null && oldAdapter != adapter) {
            this.mAdapterChangeListener.onAdapterChanged(oldAdapter, adapter);
        }
    }

    private void removeNonDecorViews() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((LayoutParams) getChildAt(i).getLayoutParams()).isDecor) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    void setOnAdapterChangeListener(OnAdapterChangeListener listener) {
        this.mAdapterChangeListener = listener;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int item) {
        boolean z;
        this.mPopulatePending = false;
        if (this.mFirstLayout) {
            z = false;
        } else {
            z = true;
        }
        setCurrentItemInternal(item, z, false);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        this.mPopulatePending = false;
        setCurrentItemInternal(item, smoothScroll, false);
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    void setCurrentItemInternal(int item, boolean smoothScroll, boolean always) {
        setCurrentItemInternal(item, smoothScroll, always, 0);
    }

    void setCurrentItemInternal(int item, boolean smoothScroll, boolean always, int velocity) {
        boolean dispatchSelected = true;
        if (this.mAdapter == null || this.mAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (always || this.mCurItem != item || this.mItems.size() == 0) {
            if (item < 0) {
                item = 0;
            } else if (item >= this.mAdapter.getCount()) {
                item = this.mAdapter.getCount() - 1;
            }
            int pageLimit = this.mOffscreenPageLimit;
            if (item > this.mCurItem + pageLimit || item < this.mCurItem - pageLimit) {
                for (int i = 0; i < this.mItems.size(); i++) {
                    ((ItemInfo) this.mItems.get(i)).scrolling = true;
                }
            }
            if (this.mCurItem == item) {
                dispatchSelected = false;
            }
            if (this.mFirstLayout) {
                this.mCurItem = item;
                if (dispatchSelected && this.mOnPageChangeListener != null) {
                    this.mOnPageChangeListener.onPageSelected(item);
                }
                if (dispatchSelected && this.mInternalPageChangeListener != null) {
                    this.mInternalPageChangeListener.onPageSelected(item);
                }
                requestLayout();
                return;
            }
            populate(item);
            scrollToItem(item, smoothScroll, velocity, dispatchSelected);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    private void scrollToItem(int item, boolean smoothScroll, int velocity, boolean dispatchSelected) {
        ItemInfo curInfo = infoForPosition(item);
        int destX = 0;
        if (curInfo != null) {
            destX = (int) (((float) getClientWidth()) * Math.max(this.mFirstOffset, Math.min(curInfo.offset, this.mLastOffset)));
        }
        if (smoothScroll) {
            smoothScrollTo(destX, 0, velocity);
            if (dispatchSelected && this.mOnPageChangeListener != null) {
                this.mOnPageChangeListener.onPageSelected(item);
            }
            if (dispatchSelected && this.mInternalPageChangeListener != null) {
                this.mInternalPageChangeListener.onPageSelected(item);
                return;
            }
            return;
        }
        if (dispatchSelected && this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(item);
        }
        if (dispatchSelected && this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageSelected(item);
        }
        completeScroll(false);
        scrollTo(destX, 0);
        pageScrolled(destX);
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.mOnPageChangeListener = listener;
    }

    void setChildrenDrawingOrderEnabledCompat(boolean enable) {
        if (VERSION.SDK_INT >= 7) {
            if (this.mSetChildrenDrawingOrderEnabled == null) {
                try {
                    this.mSetChildrenDrawingOrderEnabled = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", new Class[]{Boolean.TYPE});
                } catch (NoSuchMethodException e) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e);
                }
            }
            try {
                this.mSetChildrenDrawingOrderEnabled.invoke(this, new Object[]{Boolean.valueOf(enable)});
            } catch (Exception e2) {
                Log.e("ViewPager", "Error changing children drawing order", e2);
            }
        }
    }

    protected int getChildDrawingOrder(int childCount, int i) {
        int index;
        if (this.mDrawingOrder == 2) {
            index = (childCount - 1) - i;
        } else {
            index = i;
        }
        return ((LayoutParams) ((View) this.mDrawingOrderedChildren.get(index)).getLayoutParams()).childIndex;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public void setOffscreenPageLimit(int limit) {
        if (limit < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + limit + " too small; defaulting to " + 1);
            limit = 1;
        }
        if (limit != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = limit;
            populate();
        }
    }

    public void setPageMargin(int marginPixels) {
        int oldMargin = this.mPageMargin;
        this.mPageMargin = marginPixels;
        int width = getWidth();
        recomputeScrollPosition(width, width, marginPixels, oldMargin);
        requestLayout();
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    public void setPageMarginDrawable(Drawable d) {
        this.mMarginDrawable = d;
        if (d != null) {
            refreshDrawableState();
        }
        setWillNotDraw(d == null);
        invalidate();
    }

    public void setPageMarginDrawable(int resId) {
        setPageMarginDrawable(getContext().getResources().getDrawable(resId));
    }

    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || who == this.mMarginDrawable;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable d = this.mMarginDrawable;
        if (d != null && d.isStateful()) {
            d.setState(getDrawableState());
        }
    }

    float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    void smoothScrollTo(int x, int y, int velocity) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int sx = getScrollX();
        int sy = getScrollY();
        int dx = x - sx;
        int dy = y - sy;
        if (dx == 0 && dy == 0) {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        int duration;
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int width = getClientWidth();
        int halfWidth = width / 2;
        float distance = ((float) halfWidth) + (((float) halfWidth) * distanceInfluenceForSnapDuration(Math.min(1.0f, (1.0f * ((float) Math.abs(dx))) / ((float) width))));
        velocity = Math.abs(velocity);
        if (velocity > 0) {
            duration = Math.round(1000.0f * Math.abs(distance / ((float) velocity))) * 4;
        } else {
            duration = (int) ((1.0f + (((float) Math.abs(dx)) / (((float) this.mPageMargin) + (((float) width) * this.mAdapter.getPageWidth(this.mCurItem))))) * 100.0f);
        }
        this.mScroller.startScroll(sx, sy, dx, dy, Math.min(duration, 600));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    ItemInfo addNewItem(int position, int index) {
        ItemInfo ii = new ItemInfo();
        ii.position = position;
        ii.object = this.mAdapter.instantiateItem((ViewGroup) this, position);
        ii.widthFactor = this.mAdapter.getPageWidth(position);
        if (index < 0 || index >= this.mItems.size()) {
            this.mItems.add(ii);
        } else {
            this.mItems.add(index, ii);
        }
        return ii;
    }

    void dataSetChanged() {
        boolean needPopulate;
        int adapterCount = this.mAdapter.getCount();
        this.mExpectedAdapterCount = adapterCount;
        if (this.mItems.size() >= (this.mOffscreenPageLimit * 2) + 1 || this.mItems.size() >= adapterCount) {
            needPopulate = false;
        } else {
            needPopulate = true;
        }
        int newCurrItem = this.mCurItem;
        boolean isUpdating = false;
        int i = 0;
        while (i < this.mItems.size()) {
            ItemInfo ii = (ItemInfo) this.mItems.get(i);
            int newPos = this.mAdapter.getItemPosition(ii.object);
            if (newPos != -1) {
                if (newPos == -2) {
                    this.mItems.remove(i);
                    i--;
                    if (!isUpdating) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        isUpdating = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, ii.position, ii.object);
                    needPopulate = true;
                    if (this.mCurItem == ii.position) {
                        newCurrItem = Math.max(0, Math.min(this.mCurItem, adapterCount - 1));
                        needPopulate = true;
                    }
                } else if (ii.position != newPos) {
                    if (ii.position == this.mCurItem) {
                        newCurrItem = newPos;
                    }
                    ii.position = newPos;
                    needPopulate = true;
                }
            }
            i++;
        }
        if (isUpdating) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (needPopulate) {
            int childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                LayoutParams lp = (LayoutParams) getChildAt(i).getLayoutParams();
                if (!lp.isDecor) {
                    lp.widthFactor = 0.0f;
                }
            }
            setCurrentItemInternal(newCurrItem, false, true);
            requestLayout();
        }
    }

    void populate() {
        populate(this.mCurItem);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void populate(int r31) {
        /*
        r30 = this;
        r21 = 0;
        r15 = 2;
        r0 = r30;
        r0 = r0.mCurItem;
        r27 = r0;
        r0 = r27;
        r1 = r31;
        if (r0 == r1) goto L_0x0031;
    L_0x000f:
        r0 = r30;
        r0 = r0.mCurItem;
        r27 = r0;
        r0 = r27;
        r1 = r31;
        if (r0 >= r1) goto L_0x003d;
    L_0x001b:
        r15 = 66;
    L_0x001d:
        r0 = r30;
        r0 = r0.mCurItem;
        r27 = r0;
        r0 = r30;
        r1 = r27;
        r21 = r0.infoForPosition(r1);
        r0 = r31;
        r1 = r30;
        r1.mCurItem = r0;
    L_0x0031:
        r0 = r30;
        r0 = r0.mAdapter;
        r27 = r0;
        if (r27 != 0) goto L_0x0040;
    L_0x0039:
        r30.sortChildDrawingOrder();
    L_0x003c:
        return;
    L_0x003d:
        r15 = 17;
        goto L_0x001d;
    L_0x0040:
        r0 = r30;
        r0 = r0.mPopulatePending;
        r27 = r0;
        if (r27 == 0) goto L_0x004c;
    L_0x0048:
        r30.sortChildDrawingOrder();
        goto L_0x003c;
    L_0x004c:
        r27 = r30.getWindowToken();
        if (r27 == 0) goto L_0x003c;
    L_0x0052:
        r0 = r30;
        r0 = r0.mAdapter;
        r27 = r0;
        r0 = r27;
        r1 = r30;
        r0.startUpdate(r1);
        r0 = r30;
        r0 = r0.mOffscreenPageLimit;
        r22 = r0;
        r27 = 0;
        r0 = r30;
        r0 = r0.mCurItem;
        r28 = r0;
        r28 = r28 - r22;
        r26 = java.lang.Math.max(r27, r28);
        r0 = r30;
        r0 = r0.mAdapter;
        r27 = r0;
        r4 = r27.getCount();
        r27 = r4 + -1;
        r0 = r30;
        r0 = r0.mCurItem;
        r28 = r0;
        r28 = r28 + r22;
        r12 = java.lang.Math.min(r27, r28);
        r0 = r30;
        r0 = r0.mExpectedAdapterCount;
        r27 = r0;
        r0 = r27;
        if (r4 == r0) goto L_0x0106;
    L_0x0095:
        r27 = r30.getResources();	 Catch:{ NotFoundException -> 0x00fc }
        r28 = r30.getId();	 Catch:{ NotFoundException -> 0x00fc }
        r24 = r27.getResourceName(r28);	 Catch:{ NotFoundException -> 0x00fc }
    L_0x00a1:
        r27 = new java.lang.IllegalStateException;
        r28 = new java.lang.StringBuilder;
        r28.<init>();
        r29 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ";
        r28 = r28.append(r29);
        r0 = r30;
        r0 = r0.mExpectedAdapterCount;
        r29 = r0;
        r28 = r28.append(r29);
        r29 = ", found: ";
        r28 = r28.append(r29);
        r0 = r28;
        r28 = r0.append(r4);
        r29 = " Pager id: ";
        r28 = r28.append(r29);
        r0 = r28;
        r1 = r24;
        r28 = r0.append(r1);
        r29 = " Pager class: ";
        r28 = r28.append(r29);
        r29 = r30.getClass();
        r28 = r28.append(r29);
        r29 = " Problematic adapter: ";
        r28 = r28.append(r29);
        r0 = r30;
        r0 = r0.mAdapter;
        r29 = r0;
        r29 = r29.getClass();
        r28 = r28.append(r29);
        r28 = r28.toString();
        r27.<init>(r28);
        throw r27;
    L_0x00fc:
        r11 = move-exception;
        r27 = r30.getId();
        r24 = java.lang.Integer.toHexString(r27);
        goto L_0x00a1;
    L_0x0106:
        r8 = -1;
        r9 = 0;
        r8 = 0;
    L_0x0109:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r27 = r27.size();
        r0 = r27;
        if (r8 >= r0) goto L_0x014b;
    L_0x0117:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r17 = r0.get(r8);
        r17 = (android.support.v4.view.ViewPager.ItemInfo) r17;
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r30;
        r0 = r0.mCurItem;
        r28 = r0;
        r0 = r27;
        r1 = r28;
        if (r0 < r1) goto L_0x0260;
    L_0x0137:
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r30;
        r0 = r0.mCurItem;
        r28 = r0;
        r0 = r27;
        r1 = r28;
        if (r0 != r1) goto L_0x014b;
    L_0x0149:
        r9 = r17;
    L_0x014b:
        if (r9 != 0) goto L_0x015d;
    L_0x014d:
        if (r4 <= 0) goto L_0x015d;
    L_0x014f:
        r0 = r30;
        r0 = r0.mCurItem;
        r27 = r0;
        r0 = r30;
        r1 = r27;
        r9 = r0.addNewItem(r1, r8);
    L_0x015d:
        if (r9 == 0) goto L_0x01e1;
    L_0x015f:
        r13 = 0;
        r18 = r8 + -1;
        if (r18 < 0) goto L_0x0264;
    L_0x0164:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r27 = r0.get(r1);
        r27 = (android.support.v4.view.ViewPager.ItemInfo) r27;
        r17 = r27;
    L_0x0176:
        r7 = r30.getClientWidth();
        if (r7 > 0) goto L_0x0268;
    L_0x017c:
        r19 = 0;
    L_0x017e:
        r0 = r30;
        r0 = r0.mCurItem;
        r27 = r0;
        r23 = r27 + -1;
    L_0x0186:
        if (r23 < 0) goto L_0x0194;
    L_0x0188:
        r27 = (r13 > r19 ? 1 : (r13 == r19 ? 0 : -1));
        if (r27 < 0) goto L_0x02d9;
    L_0x018c:
        r0 = r23;
        r1 = r26;
        if (r0 >= r1) goto L_0x02d9;
    L_0x0192:
        if (r17 != 0) goto L_0x0282;
    L_0x0194:
        r14 = r9.widthFactor;
        r18 = r8 + 1;
        r27 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r27 = (r14 > r27 ? 1 : (r14 == r27 ? 0 : -1));
        if (r27 >= 0) goto L_0x01da;
    L_0x019e:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r27 = r27.size();
        r0 = r18;
        r1 = r27;
        if (r0 >= r1) goto L_0x0337;
    L_0x01ae:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r27 = r0.get(r1);
        r27 = (android.support.v4.view.ViewPager.ItemInfo) r27;
        r17 = r27;
    L_0x01c0:
        if (r7 > 0) goto L_0x033b;
    L_0x01c2:
        r25 = 0;
    L_0x01c4:
        r0 = r30;
        r0 = r0.mCurItem;
        r27 = r0;
        r23 = r27 + 1;
    L_0x01cc:
        r0 = r23;
        if (r0 >= r4) goto L_0x01da;
    L_0x01d0:
        r27 = (r14 > r25 ? 1 : (r14 == r25 ? 0 : -1));
        if (r27 < 0) goto L_0x03b0;
    L_0x01d4:
        r0 = r23;
        if (r0 <= r12) goto L_0x03b0;
    L_0x01d8:
        if (r17 != 0) goto L_0x034f;
    L_0x01da:
        r0 = r30;
        r1 = r21;
        r0.calculatePageOffsets(r9, r8, r1);
    L_0x01e1:
        r0 = r30;
        r0 = r0.mAdapter;
        r28 = r0;
        r0 = r30;
        r0 = r0.mCurItem;
        r29 = r0;
        if (r9 == 0) goto L_0x0428;
    L_0x01ef:
        r0 = r9.object;
        r27 = r0;
    L_0x01f3:
        r0 = r28;
        r1 = r30;
        r2 = r29;
        r3 = r27;
        r0.setPrimaryItem(r1, r2, r3);
        r0 = r30;
        r0 = r0.mAdapter;
        r27 = r0;
        r0 = r27;
        r1 = r30;
        r0.finishUpdate(r1);
        r6 = r30.getChildCount();
        r16 = 0;
    L_0x0211:
        r0 = r16;
        if (r0 >= r6) goto L_0x042c;
    L_0x0215:
        r0 = r30;
        r1 = r16;
        r5 = r0.getChildAt(r1);
        r20 = r5.getLayoutParams();
        r20 = (android.support.v4.view.ViewPager.LayoutParams) r20;
        r0 = r16;
        r1 = r20;
        r1.childIndex = r0;
        r0 = r20;
        r0 = r0.isDecor;
        r27 = r0;
        if (r27 != 0) goto L_0x025d;
    L_0x0231:
        r0 = r20;
        r0 = r0.widthFactor;
        r27 = r0;
        r28 = 0;
        r27 = (r27 > r28 ? 1 : (r27 == r28 ? 0 : -1));
        if (r27 != 0) goto L_0x025d;
    L_0x023d:
        r0 = r30;
        r17 = r0.infoForChild(r5);
        if (r17 == 0) goto L_0x025d;
    L_0x0245:
        r0 = r17;
        r0 = r0.widthFactor;
        r27 = r0;
        r0 = r27;
        r1 = r20;
        r1.widthFactor = r0;
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r27;
        r1 = r20;
        r1.position = r0;
    L_0x025d:
        r16 = r16 + 1;
        goto L_0x0211;
    L_0x0260:
        r8 = r8 + 1;
        goto L_0x0109;
    L_0x0264:
        r17 = 0;
        goto L_0x0176;
    L_0x0268:
        r27 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r9.widthFactor;
        r28 = r0;
        r27 = r27 - r28;
        r28 = r30.getPaddingLeft();
        r0 = r28;
        r0 = (float) r0;
        r28 = r0;
        r0 = (float) r7;
        r29 = r0;
        r28 = r28 / r29;
        r19 = r27 + r28;
        goto L_0x017e;
    L_0x0282:
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r23;
        r1 = r27;
        if (r0 != r1) goto L_0x02d2;
    L_0x028e:
        r0 = r17;
        r0 = r0.scrolling;
        r27 = r0;
        if (r27 != 0) goto L_0x02d2;
    L_0x0296:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r0.remove(r1);
        r0 = r30;
        r0 = r0.mAdapter;
        r27 = r0;
        r0 = r17;
        r0 = r0.object;
        r28 = r0;
        r0 = r27;
        r1 = r30;
        r2 = r23;
        r3 = r28;
        r0.destroyItem(r1, r2, r3);
        r18 = r18 + -1;
        r8 = r8 + -1;
        if (r18 < 0) goto L_0x02d6;
    L_0x02c0:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r27 = r0.get(r1);
        r27 = (android.support.v4.view.ViewPager.ItemInfo) r27;
        r17 = r27;
    L_0x02d2:
        r23 = r23 + -1;
        goto L_0x0186;
    L_0x02d6:
        r17 = 0;
        goto L_0x02d2;
    L_0x02d9:
        if (r17 == 0) goto L_0x0309;
    L_0x02db:
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r23;
        r1 = r27;
        if (r0 != r1) goto L_0x0309;
    L_0x02e7:
        r0 = r17;
        r0 = r0.widthFactor;
        r27 = r0;
        r13 = r13 + r27;
        r18 = r18 + -1;
        if (r18 < 0) goto L_0x0306;
    L_0x02f3:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r27 = r0.get(r1);
        r27 = (android.support.v4.view.ViewPager.ItemInfo) r27;
        r17 = r27;
    L_0x0305:
        goto L_0x02d2;
    L_0x0306:
        r17 = 0;
        goto L_0x0305;
    L_0x0309:
        r27 = r18 + 1;
        r0 = r30;
        r1 = r23;
        r2 = r27;
        r17 = r0.addNewItem(r1, r2);
        r0 = r17;
        r0 = r0.widthFactor;
        r27 = r0;
        r13 = r13 + r27;
        r8 = r8 + 1;
        if (r18 < 0) goto L_0x0334;
    L_0x0321:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r27 = r0.get(r1);
        r27 = (android.support.v4.view.ViewPager.ItemInfo) r27;
        r17 = r27;
    L_0x0333:
        goto L_0x02d2;
    L_0x0334:
        r17 = 0;
        goto L_0x0333;
    L_0x0337:
        r17 = 0;
        goto L_0x01c0;
    L_0x033b:
        r27 = r30.getPaddingRight();
        r0 = r27;
        r0 = (float) r0;
        r27 = r0;
        r0 = (float) r7;
        r28 = r0;
        r27 = r27 / r28;
        r28 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r25 = r27 + r28;
        goto L_0x01c4;
    L_0x034f:
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r23;
        r1 = r27;
        if (r0 != r1) goto L_0x03a9;
    L_0x035b:
        r0 = r17;
        r0 = r0.scrolling;
        r27 = r0;
        if (r27 != 0) goto L_0x03a9;
    L_0x0363:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r0.remove(r1);
        r0 = r30;
        r0 = r0.mAdapter;
        r27 = r0;
        r0 = r17;
        r0 = r0.object;
        r28 = r0;
        r0 = r27;
        r1 = r30;
        r2 = r23;
        r3 = r28;
        r0.destroyItem(r1, r2, r3);
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r27 = r27.size();
        r0 = r18;
        r1 = r27;
        if (r0 >= r1) goto L_0x03ad;
    L_0x0397:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r27 = r0.get(r1);
        r27 = (android.support.v4.view.ViewPager.ItemInfo) r27;
        r17 = r27;
    L_0x03a9:
        r23 = r23 + 1;
        goto L_0x01cc;
    L_0x03ad:
        r17 = 0;
        goto L_0x03a9;
    L_0x03b0:
        if (r17 == 0) goto L_0x03ee;
    L_0x03b2:
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r23;
        r1 = r27;
        if (r0 != r1) goto L_0x03ee;
    L_0x03be:
        r0 = r17;
        r0 = r0.widthFactor;
        r27 = r0;
        r14 = r14 + r27;
        r18 = r18 + 1;
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r27 = r27.size();
        r0 = r18;
        r1 = r27;
        if (r0 >= r1) goto L_0x03eb;
    L_0x03d8:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r27 = r0.get(r1);
        r27 = (android.support.v4.view.ViewPager.ItemInfo) r27;
        r17 = r27;
    L_0x03ea:
        goto L_0x03a9;
    L_0x03eb:
        r17 = 0;
        goto L_0x03ea;
    L_0x03ee:
        r0 = r30;
        r1 = r23;
        r2 = r18;
        r17 = r0.addNewItem(r1, r2);
        r18 = r18 + 1;
        r0 = r17;
        r0 = r0.widthFactor;
        r27 = r0;
        r14 = r14 + r27;
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r27 = r27.size();
        r0 = r18;
        r1 = r27;
        if (r0 >= r1) goto L_0x0425;
    L_0x0412:
        r0 = r30;
        r0 = r0.mItems;
        r27 = r0;
        r0 = r27;
        r1 = r18;
        r27 = r0.get(r1);
        r27 = (android.support.v4.view.ViewPager.ItemInfo) r27;
        r17 = r27;
    L_0x0424:
        goto L_0x03a9;
    L_0x0425:
        r17 = 0;
        goto L_0x0424;
    L_0x0428:
        r27 = 0;
        goto L_0x01f3;
    L_0x042c:
        r30.sortChildDrawingOrder();
        r27 = r30.hasFocus();
        if (r27 == 0) goto L_0x003c;
    L_0x0435:
        r10 = r30.findFocus();
        if (r10 == 0) goto L_0x048c;
    L_0x043b:
        r0 = r30;
        r17 = r0.infoForAnyChild(r10);
    L_0x0441:
        if (r17 == 0) goto L_0x0455;
    L_0x0443:
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r30;
        r0 = r0.mCurItem;
        r28 = r0;
        r0 = r27;
        r1 = r28;
        if (r0 == r1) goto L_0x003c;
    L_0x0455:
        r16 = 0;
    L_0x0457:
        r27 = r30.getChildCount();
        r0 = r16;
        r1 = r27;
        if (r0 >= r1) goto L_0x003c;
    L_0x0461:
        r0 = r30;
        r1 = r16;
        r5 = r0.getChildAt(r1);
        r0 = r30;
        r17 = r0.infoForChild(r5);
        if (r17 == 0) goto L_0x0489;
    L_0x0471:
        r0 = r17;
        r0 = r0.position;
        r27 = r0;
        r0 = r30;
        r0 = r0.mCurItem;
        r28 = r0;
        r0 = r27;
        r1 = r28;
        if (r0 != r1) goto L_0x0489;
    L_0x0483:
        r27 = r5.requestFocus(r15);
        if (r27 != 0) goto L_0x003c;
    L_0x0489:
        r16 = r16 + 1;
        goto L_0x0457;
    L_0x048c:
        r17 = 0;
        goto L_0x0441;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.populate(int):void");
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            if (this.mDrawingOrderedChildren == null) {
                this.mDrawingOrderedChildren = new ArrayList();
            } else {
                this.mDrawingOrderedChildren.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.mDrawingOrderedChildren.add(getChildAt(i));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    private void calculatePageOffsets(ItemInfo curItem, int curIndex, ItemInfo oldCurInfo) {
        float offset;
        int pos;
        ItemInfo ii;
        int N = this.mAdapter.getCount();
        int width = getClientWidth();
        float marginOffset = width > 0 ? ((float) this.mPageMargin) / ((float) width) : 0.0f;
        if (oldCurInfo != null) {
            int oldCurPosition = oldCurInfo.position;
            int itemIndex;
            if (oldCurPosition < curItem.position) {
                itemIndex = 0;
                offset = (oldCurInfo.offset + oldCurInfo.widthFactor) + marginOffset;
                pos = oldCurPosition + 1;
                while (pos <= curItem.position && itemIndex < this.mItems.size()) {
                    ii = (ItemInfo) this.mItems.get(itemIndex);
                    while (pos > ii.position && itemIndex < this.mItems.size() - 1) {
                        itemIndex++;
                        ii = (ItemInfo) this.mItems.get(itemIndex);
                    }
                    while (pos < ii.position) {
                        offset += this.mAdapter.getPageWidth(pos) + marginOffset;
                        pos++;
                    }
                    ii.offset = offset;
                    offset += ii.widthFactor + marginOffset;
                    pos++;
                }
            } else if (oldCurPosition > curItem.position) {
                itemIndex = this.mItems.size() - 1;
                offset = oldCurInfo.offset;
                pos = oldCurPosition - 1;
                while (pos >= curItem.position && itemIndex >= 0) {
                    ii = (ItemInfo) this.mItems.get(itemIndex);
                    while (pos < ii.position && itemIndex > 0) {
                        itemIndex--;
                        ii = (ItemInfo) this.mItems.get(itemIndex);
                    }
                    while (pos > ii.position) {
                        offset -= this.mAdapter.getPageWidth(pos) + marginOffset;
                        pos--;
                    }
                    offset -= ii.widthFactor + marginOffset;
                    ii.offset = offset;
                    pos--;
                }
            }
        }
        int itemCount = this.mItems.size();
        offset = curItem.offset;
        pos = curItem.position - 1;
        this.mFirstOffset = curItem.position == 0 ? curItem.offset : -3.4028235E38f;
        this.mLastOffset = curItem.position == N + -1 ? (curItem.offset + curItem.widthFactor) - 1.0f : Float.MAX_VALUE;
        int i = curIndex - 1;
        while (i >= 0) {
            ii = (ItemInfo) this.mItems.get(i);
            while (pos > ii.position) {
                offset -= this.mAdapter.getPageWidth(pos) + marginOffset;
                pos--;
            }
            offset -= ii.widthFactor + marginOffset;
            ii.offset = offset;
            if (ii.position == 0) {
                this.mFirstOffset = offset;
            }
            i--;
            pos--;
        }
        offset = (curItem.offset + curItem.widthFactor) + marginOffset;
        pos = curItem.position + 1;
        i = curIndex + 1;
        while (i < itemCount) {
            ii = (ItemInfo) this.mItems.get(i);
            while (pos < ii.position) {
                offset += this.mAdapter.getPageWidth(pos) + marginOffset;
                pos++;
            }
            if (ii.position == N - 1) {
                this.mLastOffset = (ii.widthFactor + offset) - 1.0f;
            }
            ii.offset = offset;
            offset += ii.widthFactor + marginOffset;
            i++;
            pos++;
        }
        this.mNeedCalculatePageOffsets = false;
    }

    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.position = this.mCurItem;
        if (this.mAdapter != null) {
            ss.adapterState = this.mAdapter.saveState();
        }
        return ss;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState ss = (SavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            if (this.mAdapter != null) {
                this.mAdapter.restoreState(ss.adapterState, ss.loader);
                setCurrentItemInternal(ss.position, false, true);
                return;
            }
            this.mRestoredCurItem = ss.position;
            this.mRestoredAdapterState = ss.adapterState;
            this.mRestoredClassLoader = ss.loader;
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        if (!checkLayoutParams(params)) {
            params = generateLayoutParams(params);
        }
        LayoutParams lp = (LayoutParams) params;
        lp.isDecor |= child instanceof Decor;
        if (!this.mInLayout) {
            super.addView(child, index, params);
        } else if (lp == null || !lp.isDecor) {
            lp.needsMeasure = true;
            addViewInLayout(child, index, params);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    ItemInfo infoForChild(View child) {
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = (ItemInfo) this.mItems.get(i);
            if (this.mAdapter.isViewFromObject(child, ii.object)) {
                return ii;
            }
        }
        return null;
    }

    ItemInfo infoForAnyChild(View child) {
        while (true) {
            View parent = child.getParent();
            if (parent == this) {
                return infoForChild(child);
            }
            if (parent != null && (parent instanceof View)) {
                child = parent;
            }
        }
        return null;
    }

    ItemInfo infoForPosition(int position) {
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = (ItemInfo) this.mItems.get(i);
            if (ii.position == position) {
                return ii;
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        int childWidthSize = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int childHeightSize = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int size = getChildCount();
        for (i = 0; i < size; i++) {
            LayoutParams lp;
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                lp = (LayoutParams) child.getLayoutParams();
                if (lp != null && lp.isDecor) {
                    int hgrav = lp.gravity & 7;
                    int vgrav = lp.gravity & 112;
                    int widthMode = Integer.MIN_VALUE;
                    int heightMode = Integer.MIN_VALUE;
                    boolean consumeVertical = vgrav == 48 || vgrav == 80;
                    boolean consumeHorizontal = hgrav == 3 || hgrav == 5;
                    if (consumeVertical) {
                        widthMode = 1073741824;
                    } else if (consumeHorizontal) {
                        heightMode = 1073741824;
                    }
                    int widthSize = childWidthSize;
                    int heightSize = childHeightSize;
                    int i2 = lp.width;
                    if (r0 != -2) {
                        widthMode = 1073741824;
                        i2 = lp.width;
                        if (r0 != -1) {
                            widthSize = lp.width;
                        }
                    }
                    i2 = lp.height;
                    if (r0 != -2) {
                        heightMode = 1073741824;
                        i2 = lp.height;
                        if (r0 != -1) {
                            heightSize = lp.height;
                        }
                    }
                    child.measure(MeasureSpec.makeMeasureSpec(widthSize, widthMode), MeasureSpec.makeMeasureSpec(heightSize, heightMode));
                    if (consumeVertical) {
                        childHeightSize -= child.getMeasuredHeight();
                    } else if (consumeHorizontal) {
                        childWidthSize -= child.getMeasuredWidth();
                    }
                }
            }
        }
        this.mChildWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, 1073741824);
        this.mChildHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightSize, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        size = getChildCount();
        for (i = 0; i < size; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != 8) {
                lp = (LayoutParams) child.getLayoutParams();
                if (lp == null || !lp.isDecor) {
                    child.measure(MeasureSpec.makeMeasureSpec((int) (((float) childWidthSize) * lp.widthFactor), 1073741824), this.mChildHeightMeasureSpec);
                }
            }
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) {
            recomputeScrollPosition(w, oldw, this.mPageMargin, this.mPageMargin);
        }
    }

    private void recomputeScrollPosition(int width, int oldWidth, int margin, int oldMargin) {
        if (oldWidth <= 0 || this.mItems.isEmpty()) {
            ItemInfo ii = infoForPosition(this.mCurItem);
            int scrollPos = (int) (((float) ((width - getPaddingLeft()) - getPaddingRight())) * (ii != null ? Math.min(ii.offset, this.mLastOffset) : 0.0f));
            if (scrollPos != getScrollX()) {
                completeScroll(false);
                scrollTo(scrollPos, getScrollY());
                return;
            }
            return;
        }
        int newOffsetPixels = (int) (((float) (((width - getPaddingLeft()) - getPaddingRight()) + margin)) * (((float) getScrollX()) / ((float) (((oldWidth - getPaddingLeft()) - getPaddingRight()) + oldMargin))));
        scrollTo(newOffsetPixels, getScrollY());
        if (!this.mScroller.isFinished()) {
            this.mScroller.startScroll(newOffsetPixels, 0, (int) (infoForPosition(this.mCurItem).offset * ((float) width)), 0, this.mScroller.getDuration() - this.mScroller.timePassed());
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        LayoutParams lp;
        int childLeft;
        int childTop;
        int count = getChildCount();
        int width = r - l;
        int height = b - t;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int decorCount = 0;
        for (i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                lp = (LayoutParams) child.getLayoutParams();
                if (lp.isDecor) {
                    int vgrav = lp.gravity & 112;
                    switch (lp.gravity & 7) {
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            childLeft = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            childLeft = paddingLeft;
                            paddingLeft += child.getMeasuredWidth();
                            break;
                        case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            childLeft = (width - paddingRight) - child.getMeasuredWidth();
                            paddingRight += child.getMeasuredWidth();
                            break;
                        default:
                            childLeft = paddingLeft;
                            break;
                    }
                    switch (vgrav) {
                        case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            childTop = Math.max((height - child.getMeasuredHeight()) / 2, paddingTop);
                            break;
                        case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                            childTop = paddingTop;
                            paddingTop += child.getMeasuredHeight();
                            break;
                        case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                            childTop = (height - paddingBottom) - child.getMeasuredHeight();
                            paddingBottom += child.getMeasuredHeight();
                            break;
                        default:
                            childTop = paddingTop;
                            break;
                    }
                    childLeft += scrollX;
                    child.layout(childLeft, childTop, child.getMeasuredWidth() + childLeft, child.getMeasuredHeight() + childTop);
                    decorCount++;
                }
            }
        }
        int childWidth = (width - paddingLeft) - paddingRight;
        for (i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != 8) {
                lp = (LayoutParams) child.getLayoutParams();
                if (!lp.isDecor) {
                    ItemInfo ii = infoForChild(child);
                    if (ii != null) {
                        childLeft = paddingLeft + ((int) (((float) childWidth) * ii.offset));
                        childTop = paddingTop;
                        if (lp.needsMeasure) {
                            lp.needsMeasure = false;
                            int makeMeasureSpec = MeasureSpec.makeMeasureSpec((int) (((float) childWidth) * lp.widthFactor), 1073741824);
                            child.measure(widthSpec, MeasureSpec.makeMeasureSpec((height - paddingTop) - paddingBottom, 1073741824));
                        }
                        child.layout(childLeft, childTop, child.getMeasuredWidth() + childLeft, child.getMeasuredHeight() + childTop);
                    }
                }
            }
        }
        this.mTopPageBounds = paddingTop;
        this.mBottomPageBounds = height - paddingBottom;
        this.mDecorChildCount = decorCount;
        if (this.mFirstLayout) {
            scrollToItem(this.mCurItem, false, 0, false);
        }
        this.mFirstLayout = false;
    }

    public void computeScroll() {
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            completeScroll(true);
            return;
        }
        int oldX = getScrollX();
        int oldY = getScrollY();
        int x = this.mScroller.getCurrX();
        int y = this.mScroller.getCurrY();
        if (!(oldX == x && oldY == y)) {
            scrollTo(x, y);
            if (!pageScrolled(x)) {
                this.mScroller.abortAnimation();
                scrollTo(0, y);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private boolean pageScrolled(int xpos) {
        if (this.mItems.size() == 0) {
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        ItemInfo ii = infoForCurrentScrollPosition();
        int width = getClientWidth();
        int widthWithMargin = width + this.mPageMargin;
        float marginOffset = ((float) this.mPageMargin) / ((float) width);
        int currentPage = ii.position;
        float pageOffset = ((((float) xpos) / ((float) width)) - ii.offset) / (ii.widthFactor + marginOffset);
        int offsetPixels = (int) (((float) widthWithMargin) * pageOffset);
        this.mCalledSuper = false;
        onPageScrolled(currentPage, pageOffset, offsetPixels);
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        int scrollX;
        int childCount;
        int i;
        View child;
        if (this.mDecorChildCount > 0) {
            scrollX = getScrollX();
            int paddingLeft = getPaddingLeft();
            int paddingRight = getPaddingRight();
            int width = getWidth();
            childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.isDecor) {
                    int childLeft;
                    switch (lp.gravity & 7) {
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            childLeft = Math.max((width - child.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            childLeft = paddingLeft;
                            paddingLeft += child.getWidth();
                            break;
                        case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            childLeft = (width - paddingRight) - child.getMeasuredWidth();
                            paddingRight += child.getMeasuredWidth();
                            break;
                        default:
                            childLeft = paddingLeft;
                            break;
                    }
                    int childOffset = (childLeft + scrollX) - child.getLeft();
                    if (childOffset != 0) {
                        child.offsetLeftAndRight(childOffset);
                    }
                }
            }
        }
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrolled(position, offset, offsetPixels);
        }
        if (this.mInternalPageChangeListener != null) {
            this.mInternalPageChangeListener.onPageScrolled(position, offset, offsetPixels);
        }
        if (this.mPageTransformer != null) {
            scrollX = getScrollX();
            childCount = getChildCount();
            for (i = 0; i < childCount; i++) {
                child = getChildAt(i);
                if (!((LayoutParams) child.getLayoutParams()).isDecor) {
                    this.mPageTransformer.transformPage(child, ((float) (child.getLeft() - scrollX)) / ((float) getClientWidth()));
                }
            }
        }
        this.mCalledSuper = true;
    }

    private void completeScroll(boolean postEvents) {
        boolean needPopulate;
        if (this.mScrollState == 2) {
            needPopulate = true;
        } else {
            needPopulate = false;
        }
        if (needPopulate) {
            setScrollingCacheEnabled(false);
            this.mScroller.abortAnimation();
            int oldX = getScrollX();
            int oldY = getScrollY();
            int x = this.mScroller.getCurrX();
            int y = this.mScroller.getCurrY();
            if (!(oldX == x && oldY == y)) {
                scrollTo(x, y);
            }
        }
        this.mPopulatePending = false;
        for (int i = 0; i < this.mItems.size(); i++) {
            ItemInfo ii = (ItemInfo) this.mItems.get(i);
            if (ii.scrolling) {
                needPopulate = true;
                ii.scrolling = false;
            }
        }
        if (!needPopulate) {
            return;
        }
        if (postEvents) {
            ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
        } else {
            this.mEndScrollRunnable.run();
        }
    }

    private boolean isGutterDrag(float x, float dx) {
        return (x < ((float) this.mGutterSize) && dx > 0.0f) || (x > ((float) (getWidth() - this.mGutterSize)) && dx < 0.0f);
    }

    private void enableLayers(boolean enable) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewCompat.setLayerType(getChildAt(i), enable ? 2 : 0, null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction() & 255;
        if (action == 3 || action == 1) {
            this.mIsBeingDragged = false;
            this.mIsUnableToDrag = false;
            this.mActivePointerId = -1;
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        switch (action) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                float x = ev.getX();
                this.mInitialMotionX = x;
                this.mLastMotionX = x;
                x = ev.getY();
                this.mInitialMotionY = x;
                this.mLastMotionY = x;
                this.mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                this.mIsUnableToDrag = false;
                this.mScroller.computeScrollOffset();
                if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
                    this.mScroller.abortAnimation();
                    this.mPopulatePending = false;
                    populate();
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    break;
                }
                completeScroll(false);
                this.mIsBeingDragged = false;
                break;
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                int activePointerId = this.mActivePointerId;
                if (activePointerId != -1) {
                    int pointerIndex = MotionEventCompat.findPointerIndex(ev, activePointerId);
                    float x2 = MotionEventCompat.getX(ev, pointerIndex);
                    float dx = x2 - this.mLastMotionX;
                    float xDiff = Math.abs(dx);
                    float y = MotionEventCompat.getY(ev, pointerIndex);
                    float yDiff = Math.abs(y - this.mInitialMotionY);
                    if (dx == 0.0f || isGutterDrag(this.mLastMotionX, dx) || !canScroll(this, false, (int) dx, (int) x2, (int) y)) {
                        if (xDiff > ((float) this.mTouchSlop) && 0.5f * xDiff > yDiff) {
                            this.mIsBeingDragged = true;
                            requestParentDisallowInterceptTouchEvent(true);
                            setScrollState(1);
                            this.mLastMotionX = dx > 0.0f ? this.mInitialMotionX + ((float) this.mTouchSlop) : this.mInitialMotionX - ((float) this.mTouchSlop);
                            this.mLastMotionY = y;
                            setScrollingCacheEnabled(true);
                        } else if (yDiff > ((float) this.mTouchSlop)) {
                            this.mIsUnableToDrag = true;
                        }
                        if (this.mIsBeingDragged && performDrag(x2)) {
                            ViewCompat.postInvalidateOnAnimation(this);
                            break;
                        }
                    }
                    this.mLastMotionX = x2;
                    this.mLastMotionY = y;
                    this.mIsUnableToDrag = true;
                    return false;
                }
                break;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                onSecondaryPointerUp(ev);
                break;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(ev);
        return this.mIsBeingDragged;
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (this.mFakeDragging) {
            return true;
        }
        if (ev.getAction() == 0 && ev.getEdgeFlags() != 0) {
            return false;
        }
        if (this.mAdapter != null) {
            if (this.mAdapter.getCount() != 0) {
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                this.mVelocityTracker.addMovement(ev);
                boolean needsInvalidate = false;
                float x;
                switch (ev.getAction() & 255) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        this.mScroller.abortAnimation();
                        this.mPopulatePending = false;
                        populate();
                        x = ev.getX();
                        this.mInitialMotionX = x;
                        this.mLastMotionX = x;
                        x = ev.getY();
                        this.mInitialMotionY = x;
                        this.mLastMotionY = x;
                        this.mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                        break;
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        if (this.mIsBeingDragged) {
                            VelocityTracker velocityTracker = this.mVelocityTracker;
                            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                            int initialVelocity = (int) VelocityTrackerCompat.getXVelocity(velocityTracker, this.mActivePointerId);
                            this.mPopulatePending = true;
                            int width = getClientWidth();
                            int scrollX = getScrollX();
                            ItemInfo ii = infoForCurrentScrollPosition();
                            int currentPage = ii.position;
                            x = (float) scrollX;
                            float f = (float) width;
                            f = ii.offset;
                            float pageOffset = ((r0 / r0) - r0) / ii.widthFactor;
                            int activePointerIndex = MotionEventCompat.findPointerIndex(ev, this.mActivePointerId);
                            setCurrentItemInternal(determineTargetPage(currentPage, pageOffset, initialVelocity, (int) (MotionEventCompat.getX(ev, activePointerIndex) - this.mInitialMotionX)), true, true, initialVelocity);
                            this.mActivePointerId = -1;
                            endDrag();
                            needsInvalidate = this.mLeftEdge.onRelease() | this.mRightEdge.onRelease();
                            break;
                        }
                        break;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        if (!this.mIsBeingDragged) {
                            int pointerIndex = MotionEventCompat.findPointerIndex(ev, this.mActivePointerId);
                            float x2 = MotionEventCompat.getX(ev, pointerIndex);
                            float xDiff = Math.abs(x2 - this.mLastMotionX);
                            float y = MotionEventCompat.getY(ev, pointerIndex);
                            float yDiff = Math.abs(y - this.mLastMotionY);
                            if (xDiff > ((float) this.mTouchSlop) && xDiff > yDiff) {
                                this.mIsBeingDragged = true;
                                requestParentDisallowInterceptTouchEvent(true);
                                if (x2 - this.mInitialMotionX > 0.0f) {
                                    x = this.mInitialMotionX + ((float) this.mTouchSlop);
                                } else {
                                    x = this.mInitialMotionX - ((float) this.mTouchSlop);
                                }
                                this.mLastMotionX = x;
                                this.mLastMotionY = y;
                                setScrollState(1);
                                setScrollingCacheEnabled(true);
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                }
                            }
                        }
                        if (this.mIsBeingDragged) {
                            needsInvalidate = false | performDrag(MotionEventCompat.getX(ev, MotionEventCompat.findPointerIndex(ev, this.mActivePointerId)));
                            break;
                        }
                        break;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        if (this.mIsBeingDragged) {
                            scrollToItem(this.mCurItem, true, 0, false);
                            this.mActivePointerId = -1;
                            endDrag();
                            needsInvalidate = this.mLeftEdge.onRelease() | this.mRightEdge.onRelease();
                            break;
                        }
                        break;
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                        int index = MotionEventCompat.getActionIndex(ev);
                        this.mLastMotionX = MotionEventCompat.getX(ev, index);
                        this.mActivePointerId = MotionEventCompat.getPointerId(ev, index);
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        onSecondaryPointerUp(ev);
                        this.mLastMotionX = MotionEventCompat.getX(ev, MotionEventCompat.findPointerIndex(ev, this.mActivePointerId));
                        break;
                }
                if (needsInvalidate) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
                return true;
            }
        }
        return false;
    }

    private void requestParentDisallowInterceptTouchEvent(boolean disallowIntercept) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(disallowIntercept);
        }
    }

    private boolean performDrag(float x) {
        boolean needsInvalidate = false;
        float deltaX = this.mLastMotionX - x;
        this.mLastMotionX = x;
        float scrollX = ((float) getScrollX()) + deltaX;
        int width = getClientWidth();
        float leftBound = ((float) width) * this.mFirstOffset;
        float rightBound = ((float) width) * this.mLastOffset;
        boolean leftAbsolute = true;
        boolean rightAbsolute = true;
        ItemInfo firstItem = (ItemInfo) this.mItems.get(0);
        ItemInfo lastItem = (ItemInfo) this.mItems.get(this.mItems.size() - 1);
        if (firstItem.position != 0) {
            leftAbsolute = false;
            leftBound = firstItem.offset * ((float) width);
        }
        if (lastItem.position != this.mAdapter.getCount() - 1) {
            rightAbsolute = false;
            rightBound = lastItem.offset * ((float) width);
        }
        float f;
        if (scrollX < leftBound) {
            if (leftAbsolute) {
                f = (float) width;
                needsInvalidate = this.mLeftEdge.onPull(Math.abs(leftBound - scrollX) / r0);
            }
            scrollX = leftBound;
        } else if (scrollX > rightBound) {
            if (rightAbsolute) {
                f = (float) width;
                needsInvalidate = this.mRightEdge.onPull(Math.abs(scrollX - rightBound) / r0);
            }
            scrollX = rightBound;
        }
        this.mLastMotionX += scrollX - ((float) ((int) scrollX));
        scrollTo((int) scrollX, getScrollY());
        pageScrolled((int) scrollX);
        return needsInvalidate;
    }

    private ItemInfo infoForCurrentScrollPosition() {
        float scrollOffset;
        float marginOffset = 0.0f;
        int width = getClientWidth();
        if (width > 0) {
            scrollOffset = ((float) getScrollX()) / ((float) width);
        } else {
            scrollOffset = 0.0f;
        }
        if (width > 0) {
            marginOffset = ((float) this.mPageMargin) / ((float) width);
        }
        int lastPos = -1;
        float lastOffset = 0.0f;
        float lastWidth = 0.0f;
        boolean first = true;
        ItemInfo lastItem = null;
        int i = 0;
        while (i < this.mItems.size()) {
            ItemInfo ii = (ItemInfo) this.mItems.get(i);
            if (!(first || ii.position == lastPos + 1)) {
                ii = this.mTempItem;
                ii.offset = (lastOffset + lastWidth) + marginOffset;
                ii.position = lastPos + 1;
                ii.widthFactor = this.mAdapter.getPageWidth(ii.position);
                i--;
            }
            float offset = ii.offset;
            float leftBound = offset;
            float rightBound = (ii.widthFactor + offset) + marginOffset;
            if (!first && scrollOffset < leftBound) {
                return lastItem;
            }
            if (scrollOffset < rightBound || i == this.mItems.size() - 1) {
                return ii;
            }
            first = false;
            lastPos = ii.position;
            lastOffset = offset;
            lastWidth = ii.widthFactor;
            lastItem = ii;
            i++;
        }
        return lastItem;
    }

    private int determineTargetPage(int currentPage, float pageOffset, int velocity, int deltaX) {
        int targetPage;
        if (Math.abs(deltaX) <= this.mFlingDistance || Math.abs(velocity) <= this.mMinimumVelocity) {
            targetPage = (int) ((((float) currentPage) + pageOffset) + (currentPage >= this.mCurItem ? 0.4f : 0.6f));
        } else {
            targetPage = velocity > 0 ? currentPage : currentPage + 1;
        }
        if (this.mItems.size() <= 0) {
            return targetPage;
        }
        return Math.max(((ItemInfo) this.mItems.get(0)).position, Math.min(targetPage, ((ItemInfo) this.mItems.get(this.mItems.size() - 1)).position));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        boolean needsInvalidate = false;
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        if (overScrollMode == 0 || (overScrollMode == 1 && this.mAdapter != null && this.mAdapter.getCount() > 1)) {
            int restoreCount;
            int height;
            int width;
            if (!this.mLeftEdge.isFinished()) {
                restoreCount = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.mFirstOffset * ((float) width));
                this.mLeftEdge.setSize(height, width);
                needsInvalidate = false | this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(restoreCount);
            }
            if (!this.mRightEdge.isFinished()) {
                restoreCount = canvas.save();
                width = getWidth();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.mLastOffset + 1.0f)) * ((float) width));
                this.mRightEdge.setSize(height, width);
                needsInvalidate |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(restoreCount);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (needsInvalidate) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float marginOffset = ((float) this.mPageMargin) / ((float) width);
            int itemIndex = 0;
            ItemInfo ii = (ItemInfo) this.mItems.get(0);
            float offset = ii.offset;
            int itemCount = this.mItems.size();
            int firstPos = ii.position;
            int lastPos = ((ItemInfo) this.mItems.get(itemCount - 1)).position;
            int pos = firstPos;
            while (pos < lastPos) {
                float drawAt;
                while (pos > ii.position && itemIndex < itemCount) {
                    itemIndex++;
                    ii = (ItemInfo) this.mItems.get(itemIndex);
                }
                if (pos == ii.position) {
                    drawAt = (ii.offset + ii.widthFactor) * ((float) width);
                    offset = (ii.offset + ii.widthFactor) + marginOffset;
                } else {
                    float widthFactor = this.mAdapter.getPageWidth(pos);
                    drawAt = (offset + widthFactor) * ((float) width);
                    offset += widthFactor + marginOffset;
                }
                if (((float) this.mPageMargin) + drawAt > ((float) scrollX)) {
                    this.mMarginDrawable.setBounds((int) drawAt, this.mTopPageBounds, (int) ((((float) this.mPageMargin) + drawAt) + 0.5f), this.mBottomPageBounds);
                    this.mMarginDrawable.draw(canvas);
                }
                if (drawAt <= ((float) (scrollX + width))) {
                    pos++;
                } else {
                    return;
                }
            }
        }
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        int pointerIndex = MotionEventCompat.getActionIndex(ev);
        if (MotionEventCompat.getPointerId(ev, pointerIndex) == this.mActivePointerId) {
            int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            this.mLastMotionX = MotionEventCompat.getX(ev, newPointerIndex);
            this.mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void setScrollingCacheEnabled(boolean enabled) {
        if (this.mScrollingCacheEnabled != enabled) {
            this.mScrollingCacheEnabled = enabled;
        }
    }

    public boolean canScrollHorizontally(int direction) {
        boolean z = true;
        if (this.mAdapter == null) {
            return false;
        }
        int width = getClientWidth();
        int scrollX = getScrollX();
        if (direction < 0) {
            if (scrollX <= ((int) (((float) width) * this.mFirstOffset))) {
                z = false;
            }
            return z;
        } else if (direction <= 0) {
            return false;
        } else {
            if (scrollX >= ((int) (((float) width) * this.mLastOffset))) {
                z = false;
            }
            return z;
        }
    }

    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int scrollX = v.getScrollX();
            int scrollY = v.getScrollY();
            for (int i = group.getChildCount() - 1; i >= 0; i--) {
                View child = group.getChildAt(i);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() && y + scrollY >= child.getTop() && y + scrollY < child.getBottom()) {
                    if (canScroll(child, true, dx, (x + scrollX) - child.getLeft(), (y + scrollY) - child.getTop())) {
                        return true;
                    }
                }
            }
        }
        return checkV && ViewCompat.canScrollHorizontally(v, -dx);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event) || executeKeyEvent(event);
    }

    public boolean executeKeyEvent(KeyEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        switch (event.getKeyCode()) {
            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                return arrowScroll(17);
            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                return arrowScroll(66);
            case com.google.android.play.R.styleable.Theme_textColorSearchUrl /*61*/:
                if (VERSION.SDK_INT < 11) {
                    return false;
                }
                if (KeyEventCompat.hasNoModifiers(event)) {
                    return arrowScroll(2);
                }
                if (KeyEventCompat.hasModifiers(event, 1)) {
                    return arrowScroll(1);
                }
                return false;
            default:
                return false;
        }
    }

    public boolean arrowScroll(int direction) {
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        } else if (currentFocused != null) {
            boolean isChild = false;
            for (ViewPager parent = currentFocused.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                if (parent == this) {
                    isChild = true;
                    break;
                }
            }
            if (!isChild) {
                StringBuilder sb = new StringBuilder();
                sb.append(currentFocused.getClass().getSimpleName());
                for (ViewParent parent2 = currentFocused.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                    sb.append(" => ").append(parent2.getClass().getSimpleName());
                }
                Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + sb.toString());
                currentFocused = null;
            }
        }
        boolean handled = false;
        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused, direction);
        if (nextFocused == null || nextFocused == currentFocused) {
            if (direction == 17 || direction == 1) {
                handled = pageLeft();
            } else if (direction == 66 || direction == 2) {
                handled = pageRight();
            }
        } else if (direction == 17) {
            handled = (currentFocused == null || getChildRectInPagerCoordinates(this.mTempRect, nextFocused).left < getChildRectInPagerCoordinates(this.mTempRect, currentFocused).left) ? nextFocused.requestFocus() : pageLeft();
        } else if (direction == 66) {
            handled = (currentFocused == null || getChildRectInPagerCoordinates(this.mTempRect, nextFocused).left > getChildRectInPagerCoordinates(this.mTempRect, currentFocused).left) ? nextFocused.requestFocus() : pageRight();
        }
        if (handled) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(direction));
        }
        return handled;
    }

    private Rect getChildRectInPagerCoordinates(Rect outRect, View child) {
        if (outRect == null) {
            outRect = new Rect();
        }
        if (child == null) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.left = child.getLeft();
            outRect.right = child.getRight();
            outRect.top = child.getTop();
            outRect.bottom = child.getBottom();
            ViewGroup parent = child.getParent();
            while ((parent instanceof ViewGroup) && parent != this) {
                ViewGroup group = parent;
                outRect.left += group.getLeft();
                outRect.right += group.getRight();
                outRect.top += group.getTop();
                outRect.bottom += group.getBottom();
                parent = group.getParent();
            }
        }
        return outRect;
    }

    boolean pageLeft() {
        if (this.mCurItem <= 0) {
            return false;
        }
        setCurrentItem(this.mCurItem - 1, true);
        return true;
    }

    boolean pageRight() {
        if (this.mAdapter == null || this.mCurItem >= this.mAdapter.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem + 1, true);
        return true;
    }

    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        int focusableCount = views.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child.getVisibility() == 0) {
                    ItemInfo ii = infoForChild(child);
                    if (ii != null && ii.position == this.mCurItem) {
                        child.addFocusables(views, direction, focusableMode);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && focusableCount != views.size()) || !isFocusable()) {
            return;
        }
        if (((focusableMode & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && views != null) {
            views.add(this);
        }
    }

    public void addTouchables(ArrayList<View> views) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                ItemInfo ii = infoForChild(child);
                if (ii != null && ii.position == this.mCurItem) {
                    child.addTouchables(views);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        int index;
        int increment;
        int end;
        int count = getChildCount();
        if ((direction & 2) != 0) {
            index = 0;
            increment = 1;
            end = count;
        } else {
            index = count - 1;
            increment = -1;
            end = -1;
        }
        for (int i = index; i != end; i += increment) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                ItemInfo ii = infoForChild(child);
                if (ii != null && ii.position == this.mCurItem && child.requestFocus(direction, previouslyFocusedRect)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(event);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0) {
                ItemInfo ii = infoForChild(child);
                if (ii != null && ii.position == this.mCurItem && child.dispatchPopulateAccessibilityEvent(event)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return generateDefaultLayoutParams();
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return (p instanceof LayoutParams) && super.checkLayoutParams(p);
    }

    public android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }
}
