package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.play.image.BitmapLoader;

public class PlayCardActionBannerClusterView extends PlayCardClusterView {
    private final int mContentVerticalMargin;

    public PlayCardActionBannerClusterView(Context context) {
        this(context, null);
    }

    public PlayCardActionBannerClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContentVerticalMargin = context.getResources().getDimensionPixelSize(R.dimen.play_merch_content_vmargin);
    }

    protected int getPlayStoreUiElementType() {
        return 414;
    }

    public void configureExtraContent(NavigationManager navigationManager, BitmapLoader bitmapLoader, Document document, DocV2 primaryPerson, DocV2[] secondaryPersons, String buttonText, PlayStoreUiElementNode parentNode, OnClickListener exploreClickListener) {
        PlayCardActionBannerClusterViewContent actionBannerContent = this.mContent;
        MarginLayoutParams contentLp = (MarginLayoutParams) actionBannerContent.getLayoutParams();
        contentLp.topMargin = this.mContentVerticalMargin;
        contentLp.bottomMargin = this.mContentVerticalMargin;
        actionBannerContent.configureExtraContent(navigationManager, bitmapLoader, document, primaryPerson, secondaryPersons, buttonText, parentNode, exploreClickListener);
        logEmptyClusterImpression();
    }
}
