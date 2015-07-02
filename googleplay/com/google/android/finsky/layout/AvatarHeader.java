package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DetailsSectionStack.NoBottomSeparator;
import com.google.android.finsky.layout.DetailsSectionStack.NoTopSeparator;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.image.AvatarCropTransformation;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardThumbnail;
import com.google.android.play.layout.PlayTextView;

public class AvatarHeader extends ViewGroup implements DetailsPeekingSection, NoBottomSeparator, NoTopSeparator {
    private int mAvatarVerticalPeek;
    private BitmapLoader mBitmapLoader;
    private Document mDoc;
    private boolean mHasAvatar;
    private NavigationManager mNavigationManager;
    private final int mNoAvatarTitlePadding;
    private PlayCardThumbnail mThumbnailFrame;
    private PlayTextView mTitle;

    public AvatarHeader(Context context) {
        this(context, null);
    }

    public AvatarHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mAvatarVerticalPeek = res.getDimensionPixelSize(R.dimen.talent_avatar_peek);
        this.mNoAvatarTitlePadding = res.getDimensionPixelSize(R.dimen.talent_noavatar_title_top_padding);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mThumbnailFrame = (PlayCardThumbnail) findViewById(R.id.avatar_thumbnail_frame);
        this.mTitle = (PlayTextView) findViewById(R.id.title);
    }

    public int getTopPeekAmount() {
        return this.mAvatarVerticalPeek;
    }

    public void updatePeekBackground(int color, boolean isPeeking) {
        if (isPeeking) {
            setBackgroundDrawable(new InsetDrawable(new PaintDrawable(color), 0, getTopPeekAmount(), 0, 0));
        } else {
            this.mAvatarVerticalPeek = 0;
            setBackgroundColor(color);
        }
        setPadding(0, 0, 0, 0);
    }

    public void bind(NavigationManager navManager, BitmapLoader bitmapLoader, Document doc, final int avatarImageType) {
        this.mNavigationManager = navManager;
        this.mBitmapLoader = bitmapLoader;
        this.mDoc = doc;
        int docType = this.mDoc.getDocumentType();
        Resources res = getContext().getResources();
        this.mTitle.setText(this.mDoc.getTitle());
        this.mTitle.setSelected(true);
        this.mHasAvatar = doc.hasImages(avatarImageType);
        updatePeekBackground(getResources().getColor(R.color.white), this.mHasAvatar);
        if (this.mHasAvatar) {
            this.mThumbnailFrame.setVisibility(0);
            this.mThumbnailFrame.updateCoverPadding(this.mDoc.getBackend());
            LayoutParams thumbnailLp = this.mThumbnailFrame.getLayoutParams();
            thumbnailLp.width = CorpusResourceUtils.getRegularDetailsIconWidth(getContext(), docType);
            thumbnailLp.height = CorpusResourceUtils.getRegularDetailsIconHeight(getContext(), docType);
            DocImageView thumbnailCover = (DocImageView) this.mThumbnailFrame.getImageView();
            thumbnailCover.setScaleType(ScaleType.FIT_START);
            thumbnailCover.bind(this.mDoc, this.mBitmapLoader, avatarImageType);
            thumbnailCover.setContentDescription(CorpusResourceUtils.getItemThumbnailContentDescription(this.mDoc, res));
            thumbnailCover.setBitmapTransformation(AvatarCropTransformation.getFullAvatarCrop(res));
            thumbnailCover.setFocusable(true);
            thumbnailCover.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (AvatarHeader.this.mDoc.hasImages(avatarImageType)) {
                        AvatarHeader.this.mNavigationManager.goToImagesLightbox(AvatarHeader.this.mDoc, 0, avatarImageType);
                    }
                }
            });
            return;
        }
        this.mThumbnailFrame.setVisibility(8);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;
        if (this.mHasAvatar) {
            LayoutParams thumbnailFrameLp = this.mThumbnailFrame.getLayoutParams();
            this.mThumbnailFrame.measure(MeasureSpec.makeMeasureSpec(thumbnailFrameLp.width, 1073741824), MeasureSpec.makeMeasureSpec(thumbnailFrameLp.height, 1073741824));
        } else {
            height = 0 + this.mNoAvatarTitlePadding;
        }
        this.mTitle.measure(0, 0);
        setMeasuredDimension(width, (getPaddingTop() + (height + (this.mTitle.getMeasuredHeight() + this.mThumbnailFrame.getMeasuredHeight()))) + getPaddingBottom());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int centerX = getWidth() / 2;
        int topY = getPaddingTop();
        if (this.mHasAvatar) {
            int thumbnailFrameWidth = this.mThumbnailFrame.getMeasuredWidth();
            int thumbnailFrameX = centerX - (thumbnailFrameWidth / 2);
            this.mThumbnailFrame.layout(thumbnailFrameX, topY, thumbnailFrameX + thumbnailFrameWidth, this.mThumbnailFrame.getMeasuredHeight() + topY);
            topY += this.mThumbnailFrame.getMeasuredHeight();
        } else {
            topY += this.mNoAvatarTitlePadding;
        }
        int titleWidth = this.mTitle.getMeasuredWidth();
        int titleX = centerX - (titleWidth / 2);
        this.mTitle.layout(titleX, topY, titleX + titleWidth, this.mTitle.getMeasuredHeight() + topY);
    }
}
