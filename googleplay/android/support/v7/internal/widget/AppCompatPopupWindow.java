package android.support.v7.internal.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

public class AppCompatPopupWindow extends PopupWindow {
    private final boolean mOverlapAnchor;

    public AppCompatPopupWindow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.PopupWindow, defStyleAttr, 0);
        this.mOverlapAnchor = a.getBoolean(R.styleable.PopupWindow_overlapAnchor, false);
        setBackgroundDrawable(a.getDrawable(R.styleable.PopupWindow_android_popupBackground));
        a.recycle();
    }

    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (VERSION.SDK_INT < 21 && this.mOverlapAnchor) {
            yoff -= anchor.getHeight();
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }

    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (VERSION.SDK_INT < 21 && this.mOverlapAnchor) {
            yoff -= anchor.getHeight();
        }
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    public void update(View anchor, int xoff, int yoff, int width, int height) {
        if (VERSION.SDK_INT < 21 && this.mOverlapAnchor) {
            yoff -= anchor.getHeight();
        }
        super.update(anchor, xoff, yoff, width, height);
    }
}
