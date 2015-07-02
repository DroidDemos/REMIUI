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
import com.google.android.play.image.BitmapLoader;

public class DiscoveryBadgeSocialPlusOne extends DiscoveryBadgeBase {
    private GradientDrawable mPlusOneBackground;
    private View mPlusOneContainer;

    public DiscoveryBadgeSocialPlusOne(Context context) {
        this(context, null);
    }

    public DiscoveryBadgeSocialPlusOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPlusOneBackground = (GradientDrawable) getResources().getDrawable(R.drawable.social_pluseone_background);
        setWillNotDraw(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mPlusOneContainer = findViewById(R.id.plus_one_container);
    }

    public void bind(DiscoveryBadge badge, BitmapLoader bitmapLoader, NavigationManager navigationManager, Document doc, DfeToc dfeToc, PackageManager packageManager, PlayStoreUiElementNode parentNode) {
        super.bind(badge, bitmapLoader, navigationManager, doc, dfeToc, packageManager, parentNode);
        this.mPlusOneBackground.setColor(getResources().getColor(R.color.discovery_plus_one_background));
        if (VERSION.SDK_INT >= 16) {
            this.mPlusOneContainer.setBackground(this.mPlusOneBackground);
        } else {
            this.mPlusOneContainer.setBackgroundDrawable(this.mPlusOneBackground);
        }
        findViewById(R.id.plus_one).setContentDescription(null);
    }

    protected int getPlayStoreUiElementType() {
        return 1804;
    }
}
