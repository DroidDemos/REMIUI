package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Details.DiscoveryBadge;
import com.google.android.finsky.protos.Details.DiscoveryBadgeLink;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;

public abstract class DiscoveryBadgeBase extends LinearLayout implements OnClickListener, PlayStoreUiElementNode {
    private DiscoveryBadge mBadge;
    protected int mBadgeRadius;
    protected int mCurrentFillColor;
    private DfeToc mDfeToc;
    private Document mDoc;
    protected FifeImageView mIcon;
    private NavigationManager mNavigationManager;
    private PackageManager mPackageManager;
    private PlayStoreUiElementNode mParentNode;
    protected TextView mTitle;
    protected PlayStoreUiElement mUiElement;

    protected abstract int getPlayStoreUiElementType();

    public DiscoveryBadgeBase(Context context) {
        this(context, null);
    }

    public DiscoveryBadgeBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mBadgeRadius = context.getResources().getDimensionPixelSize(R.dimen.discovery_badge_radius);
        setWillNotDraw(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (FifeImageView) findViewById(R.id.icon);
        this.mTitle = (TextView) findViewById(R.id.title);
    }

    public void bind(DiscoveryBadge badge, BitmapLoader bitmapLoader, NavigationManager navigationManager, Document doc, DfeToc dfeToc, PackageManager packageManager, PlayStoreUiElementNode parentNode) {
        this.mBadge = badge;
        this.mNavigationManager = navigationManager;
        this.mDfeToc = dfeToc;
        this.mPackageManager = packageManager;
        this.mDoc = doc;
        this.mParentNode = parentNode;
        if (badge.hasBackgroundColor) {
            this.mCurrentFillColor = badge.backgroundColor;
        } else {
            this.mCurrentFillColor = CorpusResourceUtils.getPrimaryColor(getContext(), doc.getBackend());
        }
        if (!(badge.image == null || this.mIcon == null)) {
            onPreImageLoad();
            this.mIcon.setImage(badge.image.imageUrl, badge.image.supportsFifeUrlOptions, bitmapLoader);
        }
        this.mTitle.setText(badge.title);
        DiscoveryBadgeLink badgeLink = this.mBadge.discoveryBadgeLink;
        if (badgeLink != null && (badgeLink.hasUserReviewsUrl || badgeLink.hasCriticReviewsUrl || badgeLink.link != null)) {
            setOnClickListener(this);
        }
        this.mUiElement = FinskyEventLog.obtainPlayStoreUiElement(getPlayStoreUiElementType());
        FinskyEventLog.setServerLogCookie(this.mUiElement, badge.serverLogsCookie);
        this.mTitle.setContentDescription(null);
        setContentDescription(badge.contentDescription);
        this.mParentNode.childImpression(this);
    }

    public void onClick(View v) {
        DiscoveryBadgeLink badgeLink = this.mBadge.discoveryBadgeLink;
        if (badgeLink.hasUserReviewsUrl) {
            this.mNavigationManager.goToAllReviews(this.mDoc, badgeLink.userReviewsUrl, false);
        } else if (badgeLink.hasCriticReviewsUrl) {
            this.mNavigationManager.goToAllReviews(this.mDoc, badgeLink.criticReviewsUrl, true);
        } else if (badgeLink.link != null) {
            this.mNavigationManager.resolveLink(badgeLink.link, this.mBadge.title, this.mDfeToc, this.mPackageManager);
        }
        FinskyApp.get().getEventLogger().logClickEvent((PlayStoreUiElementNode) this);
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        throw new IllegalStateException("unwanted children");
    }

    protected void onPreImageLoad() {
    }
}
