package com.google.android.finsky.layout;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.DiscoveryBadgeBase;
import com.google.android.finsky.layout.play.DiscoveryBadgeGeneric;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Details.DiscoveryBadge;
import com.google.android.play.image.BitmapLoader;

public class DiscoveryBar extends LinearLayout implements PlayStoreUiElementNode {
    private ViewGroup mBadgeContainer;
    private BitmapLoader mBitmapLoader;
    private DfeToc mDfeToc;
    private DiscoveryBadge[] mDiscoveryBadges;
    private Document mDoc;
    private boolean mHasConfigured;
    private NavigationManager mNavigationManager;
    private boolean mNeedsToRestoreScrollPosition;
    private PackageManager mPackageManager;
    private PlayStoreUiElementNode mParentNode;
    private int mRestoreScrollPosition;
    private HorizontalScrollView mScrollBar;
    private PlayStoreUiElement mUiElementProto;

    public DiscoveryBar(Context context) {
        this(context, null);
    }

    public DiscoveryBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHasConfigured = false;
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(1800);
    }

    public void configure(NavigationManager navigationManager, BitmapLoader bitmapLoader, Document doc, DiscoveryBadge[] discoveryBadges, DfeToc dfeToc, PackageManager packageManager, boolean hasDetailsLoaded, boolean hasRestoreScrollPosition, int restoreScrollPosition, PlayStoreUiElementNode parentNode) {
        if (!hasDetailsLoaded || discoveryBadges == null || discoveryBadges.length == 0) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.mHasConfigured = true;
        this.mDoc = doc;
        this.mDiscoveryBadges = discoveryBadges;
        this.mBitmapLoader = bitmapLoader;
        this.mNavigationManager = navigationManager;
        this.mDfeToc = dfeToc;
        this.mPackageManager = packageManager;
        this.mParentNode = parentNode;
        this.mNeedsToRestoreScrollPosition = hasRestoreScrollPosition;
        this.mRestoreScrollPosition = restoreScrollPosition;
        populateView();
    }

    public boolean hasConfigured() {
        return this.mHasConfigured;
    }

    public int getScrollPosition() {
        return this.mScrollBar.getScrollX();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mScrollBar = (HorizontalScrollView) findViewById(R.id.content_scroller);
        this.mBadgeContainer = (ViewGroup) findViewById(R.id.badge_container);
    }

    public void showPlaceholderView() {
        this.mBadgeContainer.removeAllViews();
        DiscoveryBadgeGeneric badgeView = (DiscoveryBadgeGeneric) LayoutInflater.from(getContext()).inflate(R.layout.discovery_badge_generic, this.mBadgeContainer, false);
        badgeView.setVisibility(4);
        this.mBadgeContainer.addView(badgeView);
    }

    private void populateView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        this.mBadgeContainer.removeAllViews();
        for (DiscoveryBadge badge : this.mDiscoveryBadges) {
            DiscoveryBadgeBase badgeView;
            if (badge.hasAggregateRating) {
                badgeView = (DiscoveryBadgeBase) inflater.inflate(R.layout.discovery_badge_rating, this.mBadgeContainer, false);
            } else if (badge.isPlusOne) {
                badgeView = (DiscoveryBadgeBase) inflater.inflate(R.layout.discovery_badge_social_plus_one, this.mBadgeContainer, false);
            } else if (badge.hasUserStarRating) {
                badgeView = (DiscoveryBadgeBase) inflater.inflate(R.layout.discovery_badge_social_rating, this.mBadgeContainer, false);
            } else if (badge.hasDownloadCount) {
                badgeView = (DiscoveryBadgeBase) inflater.inflate(R.layout.discovery_badge_dowload_count, this.mBadgeContainer, false);
            } else if (badge.playerBadge != null) {
                badgeView = (DiscoveryBadgeBase) inflater.inflate(R.layout.discovery_badge_social_player, this.mBadgeContainer, false);
            } else {
                badgeView = (DiscoveryBadgeBase) inflater.inflate(R.layout.discovery_badge_generic, this.mBadgeContainer, false);
            }
            badgeView.bind(badge, this.mBitmapLoader, this.mNavigationManager, this.mDoc, this.mDfeToc, this.mPackageManager, this);
            this.mBadgeContainer.addView(badgeView);
        }
    }

    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.mNeedsToRestoreScrollPosition) {
            this.mNeedsToRestoreScrollPosition = false;
            this.mScrollBar.scrollTo(this.mRestoreScrollPosition, 0);
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }
}
