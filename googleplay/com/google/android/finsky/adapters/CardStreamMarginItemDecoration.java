package com.google.android.finsky.adapters;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import com.android.vending.R;

public class CardStreamMarginItemDecoration extends ItemDecoration {
    private int mCardInset;

    public CardStreamMarginItemDecoration(Context context) {
        this.mCardInset = context.getResources().getDimensionPixelSize(R.dimen.play_card_default_inset);
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        if (view.getId() == R.id.editorial_description) {
            outRect.set(-this.mCardInset, -this.mCardInset, -this.mCardInset, 0);
        }
    }
}
