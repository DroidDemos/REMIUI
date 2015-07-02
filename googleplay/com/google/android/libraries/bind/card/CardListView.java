package com.google.android.libraries.bind.card;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.libraries.bind.async.JankLock;
import com.google.android.libraries.bind.data.BindingDataAdapter;
import com.google.android.libraries.bind.data.BindingViewGroup;
import com.google.android.libraries.bind.data.BindingViewGroup.BlendMode;
import com.google.android.libraries.bind.data.DataAdapter;
import com.google.android.libraries.bind.data.DataList;
import com.google.android.libraries.bind.data.DataView;
import com.google.android.libraries.bind.logging.Logd;
import com.google.android.libraries.bind.util.ParcelUtil;
import com.google.android.libraries.bind.util.RectUtil;
import com.google.android.libraries.bind.util.Util;
import com.google.android.libraries.bind.widget.MulticastOnScrollListener;
import com.google.android.libraries.bind.widget.WidgetUtil;
import java.util.HashMap;
import java.util.Map;

public class CardListView extends ListView {
    protected static final boolean ENABLE_ANIMATION;
    private static final Logd LOGD;
    private static final int[] a11yTempCount;
    private static final Interpolator alphaInterpolator;
    private static Bitmap listScrapBitmap;
    private static final Interpolator translationInterpolator;
    private boolean animateChanges;
    private boolean blendAnimationOnNextInvalidation;
    private final Map<Object, CaptureData> captures;
    private int invisibleHeight;
    private OnScrollListener legacyOnScrollListener;
    private final MulticastOnScrollListener multicastOnScrollListener;
    private final DataSetObserver postUpdateObserver;
    private final DataSetObserver preUpdateObserver;
    private SavedState stashedSavedState;
    private final RectF tempRect;

    private static class CaptureData {
        long animationDuration;
        BlendMode blendMode;
        Rect position;

        private CaptureData() {
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        public final Object cardId;
        public final int offsetFromTop;

        SavedState(Parcelable superState, Object cardId, int offsetFromTop) {
            super(superState);
            this.cardId = cardId;
            this.offsetFromTop = offsetFromTop;
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            ParcelUtil.writeObjectToParcel(this.cardId, out, flags);
            out.writeInt(this.offsetFromTop);
        }

        public String toString() {
            return this.cardId.toString();
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

        private SavedState(Parcel in) {
            super(in);
            this.cardId = ParcelUtil.readObjectFromParcel(in, SavedState.class.getClassLoader());
            this.offsetFromTop = in.readInt();
        }
    }

    static {
        LOGD = Logd.get(CardListView.class);
        ENABLE_ANIMATION = VERSION.SDK_INT >= 14;
        translationInterpolator = new AccelerateDecelerateInterpolator();
        alphaInterpolator = new LinearInterpolator();
        a11yTempCount = new int[3];
    }

    public CardListView(Context context) {
        this(context, null, 0);
    }

    public CardListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.multicastOnScrollListener = new MulticastOnScrollListener();
        this.animateChanges = true;
        this.captures = new HashMap();
        this.tempRect = new RectF();
        this.invisibleHeight = 0;
        setSelector(17170445);
        super.setOnScrollListener(this.multicastOnScrollListener);
        this.postUpdateObserver = new DataSetObserver() {
            public void onChanged() {
                CardListView.this.restoreStashedStateIfNeeded();
            }
        };
        this.preUpdateObserver = new DataSetObserver() {
            public void onChanged() {
                if (CardListView.this.stashedSavedState == null) {
                    CardListView.this.stashSavedState();
                }
                CardListView.this.prepareInvalidationAnimation();
            }
        };
    }

    public void setInvisibleHeight(int invisibleHeight) {
        this.invisibleHeight = invisibleHeight;
    }

    public int getInvisibleHeight() {
        return this.invisibleHeight;
    }

    public int getVisibleHeight() {
        return getHeight() - this.invisibleHeight;
    }

    public void setAnimateChanges(boolean animateChanges) {
        if (this.animateChanges != animateChanges) {
            this.animateChanges = animateChanges;
            DataAdapter adapter = (DataAdapter) getAdapter();
            if (adapter == null) {
                return;
            }
            if (animateChanges) {
                adapter.registerDataSetObserver(this.preUpdateObserver, -1);
            } else {
                adapter.unregisterDataSetObserver(this.preUpdateObserver);
            }
        }
    }

    protected boolean getAnimateChanges() {
        return this.animateChanges;
    }

    protected void prepareInvalidationAnimation() {
        captureCardPositions();
    }

    public void setAdapter(ListAdapter adapter) {
        ListAdapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterDataSetObserver(this.postUpdateObserver);
            if (this.animateChanges) {
                oldAdapter.unregisterDataSetObserver(this.preUpdateObserver);
            }
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            DataAdapter dataAdapter = (DataAdapter) adapter;
            dataAdapter.registerDataSetObserver(this.postUpdateObserver, 1);
            if (this.animateChanges) {
                dataAdapter.registerDataSetObserver(this.preUpdateObserver, -1);
            }
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        if (listener != null) {
            Util.checkPrecondition(this.legacyOnScrollListener == null);
            this.legacyOnScrollListener = listener;
            addOnScrollListener(this.legacyOnScrollListener);
        } else if (this.legacyOnScrollListener != null) {
            removeOnScrollListener(this.legacyOnScrollListener);
            this.legacyOnScrollListener = null;
        }
    }

    public void addOnScrollListener(OnScrollListener listener) {
        this.multicastOnScrollListener.add(listener);
    }

    public void removeOnScrollListener(OnScrollListener listener) {
        this.multicastOnScrollListener.remove(listener);
    }

    public void clearOnScrollListeners() {
        this.multicastOnScrollListener.clear();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setAdapter(null);
        clearOnScrollListeners();
    }

    protected void dispatchDraw(Canvas canvas) {
        animateIfNeeded();
        super.dispatchDraw(canvas);
    }

    private static boolean supportScrapBitmap() {
        return !Util.isLowMemoryDevice();
    }

    protected Bitmap getScrapBitmap() {
        if (supportScrapBitmap()) {
            if (!(listScrapBitmap == null || (listScrapBitmap.getWidth() == getWidth() && listScrapBitmap.getHeight() == getHeight()))) {
                clearScrapBitmap();
            }
            if (listScrapBitmap == null) {
                listScrapBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
            }
        }
        return listScrapBitmap;
    }

    public static void clearScrapBitmap() {
        if (listScrapBitmap != null) {
            listScrapBitmap.recycle();
            listScrapBitmap = null;
        }
    }

    void captureCardPositions() {
        LOGD.i("captureCardPositions", new Object[0]);
        if (!ENABLE_ANIMATION || !this.captures.isEmpty()) {
            return;
        }
        if (!WidgetUtil.isVisibleOnScreen(this)) {
            LOGD.i("Skipping capture since we're offscreen", new Object[0]);
        } else if (getChildCount() == 0) {
            LOGD.i("Skipping capture since we're offscreen", new Object[0]);
        } else {
            LOGD.i("capturing", new Object[0]);
            capture(this);
        }
    }

    private void capture(View view) {
        if (this.blendAnimationOnNextInvalidation) {
            getScrapBitmap();
        }
        traverse(view, true);
        this.blendAnimationOnNextInvalidation = false;
    }

    private static RectF capturePosition(View view, View ancestor, RectF result) {
        result.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        transformRelativeToParent(result, view, ancestor);
        return result;
    }

    private static void transformRelativeToParent(RectF rect, View view, View ancestorView) {
        if (view != ancestorView) {
            rect.offset((float) view.getLeft(), (float) view.getTop());
            transformRelativeToParent(rect, (View) view.getParent(), ancestorView);
        }
    }

    protected void traverse(View view, boolean capture) {
        if (view instanceof DataView) {
            DataList dataRow = ((DataView) view).getDataRow();
            if (dataRow != null && dataRow.getCount() > 0) {
                Object id = dataRow.getItemId(0);
                if (capture) {
                    captureView(id, view, this.blendAnimationOnNextInvalidation ? BlendMode.FADE_SOURCE_ONLY : null, 250);
                    view.animate().cancel();
                    view.setAlpha(1.0f);
                    view.setTranslationX(0.0f);
                    view.setTranslationY(0.0f);
                    view.setRotation(0.0f);
                    return;
                }
                CaptureData captureData = (CaptureData) this.captures.remove(id);
                if (captureData != null) {
                    Rect originalPosition = captureData.position;
                    capturePosition(view, this, this.tempRect);
                    float tx = ((float) originalPosition.centerX()) - this.tempRect.centerX();
                    float ty = ((float) originalPosition.centerY()) - this.tempRect.centerY();
                    boolean blendBitmap = captureData.blendMode != null && (view instanceof BindingViewGroup);
                    if (Math.abs(tx) > 5.0f || Math.abs(ty) > 5.0f || blendBitmap) {
                        if (blendBitmap) {
                            ((BindingViewGroup) view).blendCapturedBitmap(listScrapBitmap, captureData.position, captureData.animationDuration, captureData.blendMode);
                            view.setScaleX(((float) originalPosition.width()) / ((float) view.getWidth()));
                            view.setScaleY(((float) originalPosition.height()) / ((float) view.getHeight()));
                        }
                        view.setTranslationX(tx);
                        view.setTranslationY(ty);
                        view.setRotation(0.0f);
                        view.animate().translationX(0.0f).translationY(0.0f).scaleX(1.0f).scaleY(1.0f).setDuration(captureData.animationDuration).setInterpolator(translationInterpolator).setStartDelay(0);
                        disableClipChildren(view.getParent());
                        return;
                    }
                    return;
                }
                view.setAlpha(0.0f);
                view.animate().alpha(1.0f).rotation(0.0f).setStartDelay(250).setInterpolator(alphaInterpolator).setDuration(250);
                if (VERSION.SDK_INT >= 16) {
                    view.animate().withLayer();
                }
            }
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                traverse(viewGroup.getChildAt(i), capture);
            }
        }
    }

    protected void captureView(Object viewId, View view, BlendMode optBlendMode, long animationDuration) {
        CaptureData captureData = new CaptureData();
        captureData.position = RectUtil.round(capturePosition(view, this, new RectF()), new Rect());
        captureData.animationDuration = animationDuration;
        if (!(!(view instanceof BindingViewGroup) || optBlendMode == null || getScrapBitmap() == null)) {
            if (!((BindingViewGroup) view).captureToBitmap(listScrapBitmap, (float) captureData.position.left, (float) captureData.position.top)) {
                optBlendMode = null;
            }
            captureData.blendMode = optBlendMode;
        }
        this.captures.put(viewId, captureData);
    }

    protected void disableClipChildren(ViewParent viewParent) {
        if (viewParent instanceof ViewGroup) {
            ((ViewGroup) viewParent).setClipChildren(false);
            ViewParent parent = viewParent.getParent();
            if ((parent instanceof ViewGroup) && viewParent != this) {
                disableClipChildren(parent);
            }
        }
    }

    protected void animateIfNeeded() {
        if (this.animateChanges && !this.captures.isEmpty()) {
            LOGD.i("animateTransition", new Object[0]);
            if (ENABLE_ANIMATION) {
                traverse(this, false);
                this.captures.clear();
                JankLock.global.pauseTemporarily(500);
            }
        }
    }

    private boolean computeAccessibilityFirstAndLast() {
        ListAdapter adapter = getAdapter();
        if (!(adapter instanceof BindingDataAdapter)) {
            return false;
        }
        int i;
        BindingDataAdapter bindingAdapter = (BindingDataAdapter) adapter;
        int count = 0;
        for (i = 0; i < getFirstVisiblePosition(); i++) {
            LOGD.i("position %d, count: %d", Integer.valueOf(i), Integer.valueOf(bindingAdapter.getA11yRowCount(i)));
            count += rowCount;
        }
        a11yTempCount[0] = count;
        for (i = getFirstVisiblePosition(); i < getLastVisiblePosition(); i++) {
            LOGD.i("position %d, count: %d", Integer.valueOf(i), Integer.valueOf(bindingAdapter.getA11yRowCount(i)));
            count += rowCount;
        }
        a11yTempCount[1] = count;
        for (i = getLastVisiblePosition(); i < bindingAdapter.getCount(); i++) {
            LOGD.i("position %d, count: %d", Integer.valueOf(i), Integer.valueOf(bindingAdapter.getA11yRowCount(i)));
            count += rowCount;
        }
        a11yTempCount[2] = count;
        return true;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        if (computeAccessibilityFirstAndLast()) {
            event.setFromIndex(a11yTempCount[0]);
            if (VERSION.SDK_INT >= 14) {
                event.setToIndex(a11yTempCount[1] - 1);
            }
            event.setItemCount(a11yTempCount[2]);
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = captureState();
        return savedState == null ? super.onSaveInstanceState() : savedState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        LOGD.i("onRestoreInstanceState", new Object[0]);
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;
            super.onRestoreInstanceState(savedState.getSuperState());
            restoreSavedState(savedState);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public void stashSavedState() {
        this.stashedSavedState = captureState();
    }

    private SavedState captureState() {
        if (getChildCount() > 0) {
            if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0) {
                return captureState(0);
            }
            if (getFirstVisiblePosition() + getChildCount() == getAdapter().getCount() && getChildAt(getChildCount() - 1).getBottom() <= getHeight()) {
                return captureState(getChildAt(getChildCount() - 1).getBottom() - 1);
            }
        }
        return captureState(getHeight() / 2);
    }

    private SavedState captureState(int preserveRowAtYPosition) {
        ListAdapter adapter = getAdapter();
        if (adapter instanceof BindingDataAdapter) {
            BindingDataAdapter bindingDataAdapter = (BindingDataAdapter) adapter;
            int rowIndex = pointToPosition(getWidth() / 2, preserveRowAtYPosition);
            if (rowIndex != -1) {
                Object cardIdObject = bindingDataAdapter.getRowFirstCardId(rowIndex);
                if (cardIdObject != null) {
                    LOGD.i("Saving state - cardId: %s", cardIdObject);
                    return new SavedState(super.onSaveInstanceState(), cardIdObject, getChildAt(rowIndex - getFirstVisiblePosition()).getTop());
                }
            }
        }
        return null;
    }

    private void restoreSavedState(SavedState savedState) {
        Object cardId = savedState.cardId;
        ListAdapter adapter = getAdapter();
        if (adapter instanceof BindingDataAdapter) {
            BindingDataAdapter bindingDataAdapter = (BindingDataAdapter) adapter;
            if (bindingDataAdapter.hasRefreshedOnce()) {
                int position = bindingDataAdapter.findRowWithCardId(cardId);
                if (position != -1) {
                    setSelectionFromTop(position, savedState.offsetFromTop);
                    LOGD.i("Restoring for cardId %s to position %d", cardId, Integer.valueOf(position));
                    return;
                }
                return;
            }
            this.stashedSavedState = savedState;
            LOGD.i("Stashing restore state", new Object[0]);
        }
    }

    void restoreStashedStateIfNeeded() {
        if (this.stashedSavedState != null) {
            SavedState savedState = this.stashedSavedState;
            this.stashedSavedState = null;
            LOGD.i("Trying to restore stashed state", new Object[0]);
            restoreSavedState(savedState);
        }
    }
}
