package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.utils.IntMath;

public class ModuleDividerItemDecoration extends ItemDecoration {
    private final int mHalfSeparatorThickness;
    private final Paint mModuleDividerPaint;
    private final int mSectionSeparatorInset;

    public interface NoBottomDivider {
    }

    public interface NoTopDivider {
    }

    public ModuleDividerItemDecoration(Context context) {
        Resources res = context.getResources();
        this.mSectionSeparatorInset = res.getDimensionPixelSize(R.dimen.details_new_content_margin);
        this.mModuleDividerPaint = new Paint();
        this.mModuleDividerPaint.setColor(-16777216);
        int separatorThickness = res.getDimensionPixelSize(R.dimen.play_hairline_separator_thickness);
        this.mHalfSeparatorThickness = IntMath.ceil(separatorThickness, 2);
        this.mModuleDividerPaint.setStrokeWidth((float) separatorThickness);
    }

    public void onDrawOver(Canvas canvas, RecyclerView parent, State state) {
        boolean skipNextChild = false;
        float prevChildAlpha = 1.0f;
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View currChild = parent.getChildAt(i);
            if (!(skipNextChild || (currChild instanceof NoTopDivider))) {
                int dividerY = currChild.getTop() + Math.round(ViewCompat.getTranslationY(currChild));
                this.mModuleDividerPaint.setAlpha(Math.round(38.0f * Math.min(prevChildAlpha, ViewCompat.getAlpha(currChild))));
                canvas.drawLine((float) (currChild.getLeft() + this.mSectionSeparatorInset), (float) dividerY, (float) (currChild.getRight() - this.mSectionSeparatorInset), (float) dividerY, this.mModuleDividerPaint);
            }
            skipNextChild = currChild instanceof NoBottomDivider;
            prevChildAlpha = ViewCompat.getAlpha(currChild);
        }
        View lastChild = parent.getChildAt(childCount - 1);
        if (!(lastChild instanceof NoBottomDivider)) {
            int bottomY = lastChild.getBottom() - this.mHalfSeparatorThickness;
            canvas.drawLine((float) (lastChild.getLeft() + this.mSectionSeparatorInset), (float) bottomY, (float) (lastChild.getRight() - this.mSectionSeparatorInset), (float) bottomY, this.mModuleDividerPaint);
        }
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        outRect.set(0, 0, 0, 0);
    }
}
