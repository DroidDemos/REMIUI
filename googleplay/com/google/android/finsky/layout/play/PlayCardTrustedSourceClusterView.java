package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.MarginLayoutParams;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.play.image.BitmapLoader;

public class PlayCardTrustedSourceClusterView extends PlayCardClusterView {
    private final int mContentVerticalMargin;

    public PlayCardTrustedSourceClusterView(Context context) {
        this(context, null);
    }

    public PlayCardTrustedSourceClusterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContentVerticalMargin = context.getResources().getDimensionPixelSize(R.dimen.play_merch_content_vmargin);
    }

    protected int getPlayStoreUiElementType() {
        return 411;
    }

    public void configurePersonProfile(NavigationManager navigationManager, BitmapLoader bitmapLoader, Document document, PlayStoreUiElementNode clusterNode) {
        PlayCardTrustedSourceClusterViewContent trustedSourceContent = this.mContent;
        MarginLayoutParams contentLp = (MarginLayoutParams) trustedSourceContent.getLayoutParams();
        contentLp.topMargin = this.mContentVerticalMargin;
        contentLp.bottomMargin = this.mContentVerticalMargin;
        trustedSourceContent.configurePersonProfile(navigationManager, bitmapLoader, document, clusterNode);
        logEmptyClusterImpression();
    }
}
