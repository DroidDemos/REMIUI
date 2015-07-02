package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.protos.Common.Image;

public class DetailsAvatarClusterSection extends DetailsPackSection {
    private int mAvatarImageType;
    private int mBackgroundImageType;
    private String mMoreLabel;

    public DetailsAvatarClusterSection(Context context) {
        this(context, null);
    }

    public DetailsAvatarClusterSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(400);
    }

    public void bind(Document doc, String header, String subheader, String url, String moreLabel, int avatarImageType, int backgroundImageType, int maxItemsPerRow, int maxRowCount, boolean hasDetailsLoaded, PlayStoreUiElementNode parentNode) {
        super.bind(doc, header, subheader, url, null, maxItemsPerRow, maxRowCount, hasDetailsLoaded, parentNode);
        this.mMoreLabel = moreLabel;
        this.mAvatarImageType = avatarImageType;
        this.mBackgroundImageType = backgroundImageType;
    }

    protected void populateHeader() {
        Image backgroundImage;
        Image avatarImage;
        if (this.mDoc.hasImages(this.mBackgroundImageType)) {
            backgroundImage = (Image) this.mDoc.getImages(this.mBackgroundImageType).get(0);
        } else {
            backgroundImage = null;
        }
        if (this.mDoc.hasImages(this.mAvatarImageType)) {
            avatarImage = (Image) this.mDoc.getImages(this.mAvatarImageType).get(0);
        } else {
            avatarImage = null;
        }
        ((DetailsAvatarClusterHeader) this.mHeaderView).setContent(this.mBitmapLoader, this.mDoc, backgroundImage, avatarImage, this.mHeader, this.mSubheader, this.mMoreLabel, this.mNavigationManager.getClickListener(this.mDoc, this));
    }

    private int getCardYOffset() {
        DetailsAvatarClusterHeader headerView = this.mHeaderView;
        return ((headerView.getBackgroundImageHeight() * 2) / 3) + headerView.getBackgroundImageTopMargin();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int availableHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = this.mListingContent.getMeasuredHeight() + getCardYOffset();
        if (heightMode == 1073741824) {
            height = availableHeight;
        } else if (heightMode == Integer.MIN_VALUE) {
            height = Math.min(height, availableHeight);
        }
        setMeasuredDimension(availableWidth, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mHeaderView.layout(paddingLeft, paddingTop, this.mHeaderView.getMeasuredWidth() + paddingLeft, this.mHeaderView.getMeasuredHeight() + paddingTop);
        int listingTop = paddingTop + getCardYOffset();
        this.mListingContent.layout(paddingLeft, listingTop, this.mListingContent.getMeasuredWidth() + paddingLeft, this.mListingContent.getMeasuredHeight() + listingTop);
    }
}
