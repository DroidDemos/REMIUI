package com.google.android.finsky.layout;

import android.view.View;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;

public class HeaderLayoutSwitcher extends LayoutSwitcher {
    public HeaderLayoutSwitcher(View pageLayout, int dataLayoutId, int errorLayoutId, int loadingLayoutId, RetryButtonListener listener, int initialState) {
        super(pageLayout, dataLayoutId, errorLayoutId, loadingLayoutId, listener, initialState);
    }

    protected void setDataVisible(boolean show, boolean goingToLoadingMode) {
        if (this.mDataLayoutId > 0) {
            FinskyHeaderListLayout dataView = (FinskyHeaderListLayout) this.mContentLayout.findViewById(this.mDataLayoutId);
            if (dataView == null) {
                return;
            }
            if (show) {
                dataView.setVisibility(0);
                dataView.showContentView();
            } else if (goingToLoadingMode) {
                dataView.setVisibility(0);
                dataView.hideContentView();
            } else {
                dataView.setVisibility(8);
            }
        }
    }
}
