package com.google.android.finsky.layout.play;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.utils.ClientMutationCache;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;

public class PlayCardActionBannerClusterViewContent extends PlayCardClusterViewContent {
    private PlayAvatarPack mAvatarPack;
    private Document mDocument;
    private TextView mExploreButton;
    private boolean mHasTallCover;
    private FifeImageView mProfileCoverImage;
    private FrameLayout mProfileCoverImageFrame;
    private View mProfileInfoBlock;
    private TextView mProfileSubtitle;
    private TextView mProfileTitle;
    private final boolean mShouldLayoutVertically;

    public PlayCardActionBannerClusterViewContent(Context context) {
        this(context, null);
    }

    public PlayCardActionBannerClusterViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mShouldLayoutVertically = context.getResources().getBoolean(R.bool.play_follow_person_cluster_vertical_stack);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mProfileCoverImageFrame = (FrameLayout) findViewById(R.id.profile_cover_frame);
        this.mProfileCoverImage = (FifeImageView) findViewById(R.id.profile_cover);
        this.mProfileInfoBlock = findViewById(R.id.profile_info_block);
        this.mAvatarPack = (PlayAvatarPack) this.mProfileInfoBlock.findViewById(R.id.avatar_pack);
        this.mProfileTitle = (TextView) this.mProfileInfoBlock.findViewById(R.id.profile_title);
        this.mProfileSubtitle = (TextView) this.mProfileInfoBlock.findViewById(R.id.profile_subtitle);
        this.mExploreButton = (TextView) this.mProfileInfoBlock.findViewById(R.id.profile_action_button);
    }

    public void setMetadata(PlayCardClusterMetadata metadata, ClientMutationCache clientMutationCache) {
        super.setMetadata(metadata, clientMutationCache);
        int visibleCardCount = Math.min(this.mMetadata.getTileCount(), getDocCount());
        this.mHasTallCover = false;
        for (int tileIndex = 0; tileIndex < visibleCardCount; tileIndex++) {
            if (PlayCardClusterMetadata.getAspectRatio(getDoc(tileIndex).getDocumentType()) == 1.441f) {
                this.mHasTallCover = true;
                return;
            }
        }
    }

    protected int getIndexOfFirstCard() {
        return 2;
    }

    public void configureExtraContent(NavigationManager navigationManager, BitmapLoader bitmapLoader, Document document, DocV2 primaryPerson, DocV2[] secondaryPersons, String buttonText, PlayStoreUiElementNode parentNode, OnClickListener exploreClickListener) {
        this.mDocument = document;
        this.mAvatarPack.setData(primaryPerson, secondaryPersons, navigationManager, parentNode);
        Image image = (Image) this.mDocument.getImages(14).get(0);
        this.mProfileCoverImage.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
        this.mProfileCoverImage.setOnClickListener(exploreClickListener);
        this.mProfileCoverImage.setContentDescription(buttonText);
        this.mProfileTitle.setText(document.getTitle());
        this.mProfileSubtitle.setText(document.getSubtitle());
        if (buttonText != null) {
            this.mExploreButton.setVisibility(0);
            this.mExploreButton.setText(buttonText);
            this.mExploreButton.setOnClickListener(exploreClickListener);
            return;
        }
        this.mExploreButton.setVisibility(8);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cardContentHeight;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int measuredHeight = getMeasuredHeight();
        if (measuredHeight == 0) {
            cardContentHeight = 0;
        } else {
            cardContentHeight = (measuredHeight - this.mCardContentPaddingTop) - this.mCardContentPaddingBottom;
        }
        int totalHeight = cardContentHeight;
        int fullWidthSpec = MeasureSpec.makeMeasureSpec(width, 1073741824);
        if (this.mShouldLayoutVertically) {
            this.mProfileInfoBlock.measure(fullWidthSpec, 0);
            int profileInfoBlockHeight = this.mProfileInfoBlock.getMeasuredHeight();
            this.mProfileCoverImageFrame.measure(fullWidthSpec, MeasureSpec.makeMeasureSpec((profileInfoBlockHeight - ((int) (((float) this.mAvatarPack.getMeasuredHeight()) * 0.5f))) + ((int) (((float) cardContentHeight) * 0.85f)), 1073741824));
            totalHeight += profileInfoBlockHeight;
        } else {
            int cardContentHeightOverProfileImage;
            int infoBlockWidthSpec = MeasureSpec.makeMeasureSpec(getLeadingGap(width) + ((int) (((float) getExtraColumnOffset()) * getCellSize(width))), 1073741824);
            this.mProfileInfoBlock.measure(infoBlockWidthSpec, 0);
            int avatarPackHeight = this.mAvatarPack.getMeasuredHeight();
            int profileContentHeightUnderProfileImage = this.mProfileInfoBlock.getMeasuredHeight() - ((int) (((float) avatarPackHeight) * 0.5f));
            if (this.mHasTallCover) {
                cardContentHeightOverProfileImage = ((int) (((float) cardContentHeight) * 0.85f)) - ((int) (((float) avatarPackHeight) * 0.5f));
            } else {
                cardContentHeightOverProfileImage = ((int) (((float) avatarPackHeight) * 0.0f)) + ((int) (((float) cardContentHeight) * 0.85f));
            }
            int profileImageHeight = Math.max(profileContentHeightUnderProfileImage, cardContentHeightOverProfileImage);
            this.mProfileCoverImageFrame.measure(fullWidthSpec, MeasureSpec.makeMeasureSpec(profileImageHeight, 1073741824));
            int infoBlockFinalHeight = profileImageHeight + ((int) (((float) avatarPackHeight) * 0.5f));
            this.mProfileInfoBlock.measure(infoBlockWidthSpec, MeasureSpec.makeMeasureSpec(infoBlockFinalHeight, 1073741824));
            totalHeight = infoBlockFinalHeight + ((int) (((float) cardContentHeight) * 0.14999998f));
        }
        if (cardContentHeight > 0) {
            totalHeight += this.mCardContentPaddingTop + this.mCardContentPaddingBottom;
        }
        setMeasuredDimension(width, totalHeight);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int infoBlockX = 0;
        super.onLayout(changed, l, t, r, b);
        int width = getWidth();
        int coverImageTop = this.mCardContentPaddingTop + ((int) (((float) this.mAvatarPack.getMeasuredHeight()) * 0.5f));
        this.mProfileCoverImageFrame.layout(0, coverImageTop, width, this.mProfileCoverImageFrame.getMeasuredHeight() + coverImageTop);
        int infoBlockWidth = this.mProfileInfoBlock.getMeasuredWidth();
        int infoBlockHeight = this.mProfileInfoBlock.getMeasuredHeight();
        if (!this.mShouldLayoutVertically) {
            infoBlockX = this.mCardContentHorizontalPadding;
        }
        this.mProfileInfoBlock.layout(infoBlockX, this.mCardContentPaddingTop, infoBlockX + infoBlockWidth, this.mCardContentPaddingTop + infoBlockHeight);
    }

    public void onRecycle() {
        super.onRecycle();
        this.mAvatarPack.onRecycle();
        this.mProfileCoverImage.clearImage();
    }
}
