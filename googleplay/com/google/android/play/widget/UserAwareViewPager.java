package com.google.android.play.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import com.google.android.libraries.bind.bidi.BidiAwareViewPager;

public class UserAwareViewPager extends BidiAwareViewPager {
    protected boolean mFirstLayout;
    private boolean mIsSettingCurrentItem;

    public UserAwareViewPager(Context context) {
        super(context);
        this.mFirstLayout = true;
    }

    public UserAwareViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFirstLayout = true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    public void setAdapter(PagerAdapter adapter) {
        this.mFirstLayout = true;
        super.setAdapter(adapter);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        this.mFirstLayout = false;
    }

    public void setCurrentItem(int item) {
        this.mIsSettingCurrentItem = true;
        super.setCurrentItem(item);
        this.mIsSettingCurrentItem = false;
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        this.mIsSettingCurrentItem = true;
        super.setCurrentItem(item, smoothScroll);
        this.mIsSettingCurrentItem = false;
    }
}
