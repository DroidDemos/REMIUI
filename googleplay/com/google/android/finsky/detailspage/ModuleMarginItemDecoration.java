package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.google.android.finsky.utils.UiUtils;

public class ModuleMarginItemDecoration extends ItemDecoration {
    private int mDefaultSideMargin;

    public interface MarginOffset {
        int getMarginOffset();
    }

    public interface EdgeToEdge {
    }

    public ModuleMarginItemDecoration(Context context, boolean useWideLayout) {
        this.mDefaultSideMargin = getDefaultSideMargin(context.getResources(), useWideLayout);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        int sideMargin = view instanceof EdgeToEdge ? 0 : this.mDefaultSideMargin;
        if (view instanceof MarginOffset) {
            sideMargin += ((MarginOffset) view).getMarginOffset();
        }
        outRect.set(sideMargin, 0, sideMargin, 0);
    }

    public static int getDefaultSideMargin(Resources res, boolean useWideLayout) {
        int contentWidth;
        int screenWidth = res.getDisplayMetrics().widthPixels;
        if (useWideLayout) {
            contentWidth = UiUtils.getGridColumnContentWidth(res);
        } else {
            contentWidth = screenWidth;
        }
        return (screenWidth - Math.min(screenWidth, contentWidth)) / 2;
    }
}
