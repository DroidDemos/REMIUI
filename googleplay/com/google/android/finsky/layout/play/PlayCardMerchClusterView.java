package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import com.android.vending.R;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.play.image.BitmapLoader;

public class PlayCardMerchClusterView extends PlayCardClusterView {
    private final int mContentVerticalMargin;
    private final int mContentVerticalPadding;

    public PlayCardMerchClusterView(Context context) {
        this(context, null);
    }

    public PlayCardMerchClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mContentVerticalMargin = res.getDimensionPixelSize(R.dimen.play_merch_content_vmargin);
        this.mContentVerticalPadding = res.getDimensionPixelSize(R.dimen.play_merch_content_vpadding);
    }

    protected int getPlayStoreUiElementType() {
        return 407;
    }

    public void configureMerch(BitmapLoader bitmapLoader, int merchImagePosition, Image merchImage, String clusterTitle, OnClickListener merchClickListener) {
        PlayCardMerchClusterViewContent merchContent = this.mContent;
        MarginLayoutParams contentLp = (MarginLayoutParams) merchContent.getLayoutParams();
        contentLp.topMargin = this.mContentVerticalMargin;
        contentLp.bottomMargin = this.mContentVerticalMargin;
        merchContent.setCardContentVerticalPadding(this.mContentVerticalPadding);
        merchContent.configureMerch(bitmapLoader, merchImagePosition, merchImage, clusterTitle, merchClickListener);
    }

    public void configureNoMerch() {
        PlayCardMerchClusterViewContent merchContent = this.mContent;
        MarginLayoutParams contentLp = (MarginLayoutParams) merchContent.getLayoutParams();
        contentLp.topMargin = 0;
        contentLp.bottomMargin = 0;
        merchContent.setCardContentVerticalPadding(0);
    }

    public void onRecycle() {
        super.onRecycle();
        this.mContent.onRecycle();
    }
}
