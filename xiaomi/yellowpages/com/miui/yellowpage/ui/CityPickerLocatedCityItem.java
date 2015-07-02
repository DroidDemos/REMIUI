package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class CityPickerLocatedCityItem extends LinearLayout {
    private boolean iO;

    public CityPickerLocatedCityItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        super.onInterceptTouchEvent(motionEvent);
        return !this.iO;
    }

    public void setSelectable(boolean z) {
        this.iO = z;
    }
}
