package com.google.android.finsky.layout.actionbar;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.android.vending.R;
import com.google.android.play.headerlist.PlayHeaderListLayout;
import com.google.android.play.headerlist.PlayHeaderListLayout.LayoutListener;

public class ActionBarBackgroundUpdater implements LayoutListener {
    private final PlayHeaderListLayout mHeaderListLayout;
    private boolean mIsHeaderListFloating;
    private final Drawable mProtectionBackground;
    private final Drawable mTransparentBackground;

    public ActionBarBackgroundUpdater(PlayHeaderListLayout headerListLayout) {
        this.mHeaderListLayout = headerListLayout;
        this.mTransparentBackground = new ColorDrawable(0);
        this.mProtectionBackground = headerListLayout.getResources().getDrawable(R.drawable.action_bar_overlay);
    }

    public void onPlayHeaderListLayoutChanged() {
        boolean isFloating = this.mHeaderListLayout.isHeaderFloating();
        if (this.mIsHeaderListFloating != isFloating) {
            this.mIsHeaderListFloating = isFloating;
            updateBackground();
        }
    }

    public void updateBackground() {
        setBackground(this.mHeaderListLayout.isHeaderFloating() ? this.mTransparentBackground : this.mProtectionBackground);
    }

    public void resetBackground() {
        setBackground(this.mTransparentBackground);
    }

    private void setBackground(Drawable background) {
        this.mHeaderListLayout.getActionBarView().setBackgroundDrawable(background);
    }
}
