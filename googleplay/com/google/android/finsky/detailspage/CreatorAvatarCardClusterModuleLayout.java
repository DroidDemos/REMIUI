package com.google.android.finsky.detailspage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import com.google.android.finsky.detailspage.CardClusterModuleLayout.CardBinder;
import com.google.android.finsky.layout.DetailsAvatarClusterHeader;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.play.image.BitmapLoader;

public class CreatorAvatarCardClusterModuleLayout extends CardClusterModuleLayout {
    public CreatorAvatarCardClusterModuleLayout(Context context) {
        this(context, null);
    }

    public CreatorAvatarCardClusterModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(CardBinder cardBinder, BitmapLoader bitmapLoader, int backend, String header, String subHeader, String more, boolean showSecondRow, Image avatarImage, Image backgroundImage, Badge creatorBadge, OnClickListener moreOnClickListener, OnClickListener headerOnClickListener) {
        super.bind(cardBinder, backend, header, subHeader, more, showSecondRow, moreOnClickListener);
        ((DetailsAvatarClusterHeader) this.mHeaderView).setContent(bitmapLoader, backend, backgroundImage, avatarImage, creatorBadge, header, subHeader, more, headerOnClickListener);
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
        int height = this.mBucketRowFirst.getMeasuredHeight() + getCardYOffset();
        if (this.mBucketRowSecond != null) {
            height += this.mBucketRowSecond.getMeasuredHeight();
        }
        if (heightMode == 1073741824) {
            height = availableHeight;
        } else if (heightMode == Integer.MIN_VALUE) {
            height = Math.min(height, availableHeight);
        }
        setMeasuredDimension(availableWidth, (getPaddingTop() + height) + getPaddingBottom());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mHeaderView.layout(paddingLeft, paddingTop, this.mHeaderView.getMeasuredWidth() + paddingLeft, this.mHeaderView.getMeasuredHeight() + paddingTop);
        int listingTop = paddingTop + getCardYOffset();
        this.mBucketRowFirst.layout(paddingLeft, listingTop, this.mBucketRowFirst.getMeasuredWidth() + paddingLeft, this.mBucketRowFirst.getMeasuredHeight() + listingTop);
        if (this.mBucketRowSecond != null) {
            int secondRowTop = this.mBucketRowFirst.getBottom();
            this.mBucketRowSecond.layout(paddingLeft, secondRowTop, this.mBucketRowSecond.getMeasuredWidth() + paddingLeft, this.mBucketRowSecond.getMeasuredHeight() + secondRowTop);
        }
    }
}
