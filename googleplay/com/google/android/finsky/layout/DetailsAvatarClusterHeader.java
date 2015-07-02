package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayCardClusterViewHeader;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.utils.BadgeUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;

public class DetailsAvatarClusterHeader extends PlayCardClusterViewHeader {
    private final int mAvatarSize;
    private FifeImageView mAvatarView;
    private final int mBackgroundHeight;
    private final int mRegularHeaderXPadding;

    public DetailsAvatarClusterHeader(Context context) {
        this(context, null);
    }

    public DetailsAvatarClusterHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mBackgroundHeight = res.getDimensionPixelSize(R.dimen.hero_image_height);
        this.mAvatarSize = res.getDimensionPixelSize(R.dimen.play_profile_avatar_size);
        this.mRegularHeaderXPadding = res.getDimensionPixelSize(R.dimen.play_cluster_header_xpadding);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAvatarView = (FifeImageView) findViewById(R.id.avatar);
    }

    @Deprecated
    public void setContent(BitmapLoader bitmapLoader, Document doc, Image backgroundImage, Image avatarImage, String titleMain, String titleSecondary, String more, OnClickListener clickListener) {
        setContent(bitmapLoader, doc.getBackend(), backgroundImage, avatarImage, doc.hasCreatorBadges() ? doc.getFirstCreatorBadge() : null, titleMain, titleSecondary, more, clickListener);
    }

    public void setContent(BitmapLoader bitmapLoader, int backend, Image backgroundImage, Image avatarImage, Badge creatorBadge, String titleMain, String titleSecondary, String more, OnClickListener clickListener) {
        Resources res = getContext().getResources();
        this.mHeaderImage.setBackgroundColor(res.getColor(R.color.play_fg_button_secondary));
        super.setContent(bitmapLoader, backend, backgroundImage, titleMain, titleSecondary, more, clickListener);
        BadgeUtils.configureBadge(creatorBadge, bitmapLoader, (DecoratedTextView) this.mTitleMain);
        int color = res.getColor(R.color.play_fg_primary);
        this.mHeaderImage.setColorFilter(Color.argb(51, Color.red(color), Color.green(color), Color.blue(color)));
        if (avatarImage != null) {
            this.mAvatarView.setImage(avatarImage.imageUrl, avatarImage.supportsFifeUrlOptions, bitmapLoader);
            this.mAvatarView.setVisibility(0);
            return;
        }
        this.mAvatarView.setVisibility(8);
    }

    public int getBackgroundImageHeight() {
        return this.mBackgroundHeight;
    }

    public int getContentBottom() {
        return this.mMoreView.getBottom();
    }

    public int getBackgroundImageTopMargin() {
        return this.mAvatarView.getVisibility() != 8 ? ((MarginLayoutParams) this.mHeaderImage.getLayoutParams()).topMargin : 0;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int availableHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        this.mHeaderImage.measure(MeasureSpec.makeMeasureSpec(availableWidth, 1073741824), MeasureSpec.makeMeasureSpec(this.mBackgroundHeight, 1073741824));
        if (this.mAvatarView.getVisibility() != 8) {
            int avatarMeasureSpec = MeasureSpec.makeMeasureSpec(this.mAvatarSize, 1073741824);
            this.mAvatarView.measure(avatarMeasureSpec, avatarMeasureSpec);
        }
        this.mTitleGroup.measure(0, 0);
        this.mMoreView.measure(0, 0);
        int height = ((this.mBackgroundHeight + getPaddingTop()) + getPaddingBottom()) + getBackgroundImageTopMargin();
        if (heightMode == 1073741824) {
            height = availableHeight;
        } else if (heightMode == Integer.MIN_VALUE) {
            height = Math.min(height, availableHeight);
        }
        setMeasuredDimension(availableWidth, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mHeaderImage.layout(paddingLeft, getBackgroundImageTopMargin() + paddingTop, this.mHeaderImage.getMeasuredWidth() + paddingLeft, (getBackgroundImageTopMargin() + paddingTop) + this.mHeaderImage.getMeasuredHeight());
        int centerX = width / 2;
        if (this.mAvatarView.getVisibility() != 8) {
            int avatarLeft = centerX - (this.mAvatarView.getMeasuredWidth() / 2);
            int avatarRight = avatarLeft + this.mAvatarView.getMeasuredWidth();
            this.mAvatarView.layout(avatarLeft, paddingTop, avatarRight, this.mAvatarView.getMeasuredHeight() + paddingTop);
            int titleTop = this.mAvatarView.getBottom();
            int titleLeft = centerX - (this.mTitleGroup.getMeasuredWidth() / 2);
            int titleRight = titleLeft + this.mTitleGroup.getMeasuredWidth();
            this.mTitleGroup.layout(titleLeft, titleTop, titleRight, this.mTitleGroup.getMeasuredHeight() + titleTop);
            int moreLeft = centerX - (this.mMoreView.getMeasuredWidth() / 2);
            int moreRight = moreLeft + this.mMoreView.getMeasuredWidth();
            this.mMoreView.layout(moreLeft, this.mTitleGroup.getBottom(), moreRight, this.mTitleGroup.getBottom() + this.mMoreView.getMeasuredHeight());
            return;
        }
        int contentBottom = (height * 2) / 3;
        int titleHeight = this.mTitleGroup.getMeasuredHeight();
        titleLeft = paddingLeft + this.mRegularHeaderXPadding;
        titleRight = titleLeft + this.mTitleGroup.getMeasuredWidth();
        this.mTitleGroup.layout(titleLeft, contentBottom - titleHeight, titleRight, contentBottom);
        int rightX = (width - getPaddingRight()) - this.mRegularHeaderXPadding;
        int moreWidth = this.mMoreView.getMeasuredWidth();
        int moreHeight = this.mMoreView.getMeasuredHeight();
        int moreBottom = contentBottom - ((titleHeight - moreHeight) / 2);
        this.mMoreView.layout(rightX - moreWidth, moreBottom - moreHeight, rightX, moreBottom);
    }
}
