package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Details.DiscoveryBadge;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.StarRatingBar;

public class DiscoveryBadgeSocialRating extends DiscoveryBadgeBase {
    private StarRatingBar mRatingBar;
    private View mRatingBarContainer;
    private GradientDrawable mRatingBarContainerBackground;

    public DiscoveryBadgeSocialRating(Context context) {
        this(context, null);
    }

    public DiscoveryBadgeSocialRating(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mRatingBarContainerBackground = (GradientDrawable) getResources().getDrawable(R.drawable.social_rating_background);
        setWillNotDraw(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRatingBar = (StarRatingBar) findViewById(R.id.user_rating_bar);
        this.mRatingBarContainer = findViewById(R.id.user_rating_bar_container);
    }

    public void bind(DiscoveryBadge badge, BitmapLoader bitmapLoader, NavigationManager navigationManager, Document doc, DfeToc dfeToc, PackageManager packageManager, PlayStoreUiElementNode parentNode) {
        super.bind(badge, bitmapLoader, navigationManager, doc, dfeToc, packageManager, parentNode);
        this.mRatingBar.setRating((float) badge.userStarRating);
        this.mRatingBarContainerBackground.setColor(CorpusResourceUtils.getPrimaryColor(getContext(), doc.getBackend()));
        if (VERSION.SDK_INT >= 16) {
            this.mRatingBarContainer.setBackground(this.mRatingBarContainerBackground);
        } else {
            this.mRatingBarContainer.setBackgroundDrawable(this.mRatingBarContainerBackground);
        }
        this.mRatingBar.setContentDescription(null);
    }

    protected int getPlayStoreUiElementType() {
        return 1803;
    }
}
