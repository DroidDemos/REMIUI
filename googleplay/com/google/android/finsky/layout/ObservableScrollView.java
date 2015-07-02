package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class ObservableScrollView extends ScrollView {
    private static final boolean HAS_IN_LAYOUT_METHOD;
    private boolean mIsInLayout;
    private List<ScrollListener> mOnScrollListeners;

    public interface ScrollListener {
        void onScroll(int i, int i2);
    }

    static {
        HAS_IN_LAYOUT_METHOD = VERSION.SDK_INT >= 18;
    }

    public ObservableScrollView(Context context) {
        this(context, null);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addOnScrollListener(ScrollListener onScrollListener) {
        if (this.mOnScrollListeners == null) {
            this.mOnScrollListeners = Lists.newArrayList();
        }
        this.mOnScrollListeners.add(onScrollListener);
    }

    public void removeOnScrollListener(ScrollListener scrollListener) {
        if (this.mOnScrollListeners != null) {
            this.mOnScrollListeners.remove(scrollListener);
        }
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.mOnScrollListeners != null) {
            for (int i = this.mOnScrollListeners.size() - 1; i >= 0; i--) {
                ((ScrollListener) this.mOnScrollListeners.get(i)).onScroll(l, t);
            }
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.mIsInLayout = true;
        super.onLayout(changed, l, t, r, b);
        this.mIsInLayout = false;
    }

    public boolean isViewInLayout() {
        return HAS_IN_LAYOUT_METHOD ? isInLayout() : this.mIsInLayout;
    }
}
