package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.android.vending.R;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoTopDivider;
import com.google.android.finsky.layout.DetailsSectionStack.NoTopSeparator;
import com.google.android.play.utils.PlayTouchDelegate;
import java.util.List;

public class DetailsSummary extends ViewGroup implements NoTopDivider, DetailsPartialFadeSection, DetailsPeekingSection, NoTopSeparator {
    private View mBackgroundLayer;
    private View mBylines;
    private final int mContentMargin;
    private View mCreatorBlock;
    private View mCreatorPanel;
    private View mDetailsSummaryDynamic;
    private View mExtraLabels;
    private View mExtraLabelsBottom;
    private boolean mIsExtraLabelsInCompactMode;
    private final Rect mOldWishlistArea;
    private View mThumbnailFrame;
    private int mThumbnailMode;
    private int mThumbnailVerticalPeek;
    private View mTipperSticker;
    private View mTitle;
    private final int mTitleOffset;
    private final Rect mWishlistArea;
    private View mWishlistButton;
    private final int mWishlistTouchExtend;

    public DetailsSummary(Context context) {
        this(context, null);
    }

    public DetailsSummary(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mContentMargin = res.getDimensionPixelSize(R.dimen.details_new_content_margin);
        this.mThumbnailVerticalPeek = res.getDimensionPixelSize(R.dimen.summary_thumbnail_peek);
        this.mTitleOffset = res.getDimensionPixelSize(R.dimen.details_title_offset);
        this.mWishlistTouchExtend = res.getDimensionPixelSize(R.dimen.summary_wishlist_touch_extend);
        this.mWishlistArea = new Rect();
        this.mOldWishlistArea = new Rect();
        this.mThumbnailMode = 0;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mBackgroundLayer = findViewById(R.id.title_background);
        this.mThumbnailFrame = findViewById(R.id.title_thumbnail_frame);
        this.mTitle = findViewById(R.id.title_title);
        this.mWishlistButton = findViewById(R.id.title_wishlist_button);
        this.mCreatorPanel = findViewById(R.id.title_creator_panel);
        this.mCreatorBlock = findViewById(R.id.title_creator_block);
        this.mTipperSticker = findViewById(R.id.title_tipper_sticker);
        this.mBylines = findViewById(R.id.title_bylines);
        this.mExtraLabels = findViewById(R.id.title_extra_labels);
        this.mExtraLabelsBottom = findViewById(R.id.title_extra_labels_bottom);
        this.mDetailsSummaryDynamic = findViewById(R.id.title_details_summary_dynamic);
    }

    public void setThumbnailMode(int thumbnailMode) {
        boolean z = true;
        if (this.mThumbnailMode != thumbnailMode) {
            this.mThumbnailMode = thumbnailMode;
            int whiteColor = getResources().getColor(R.color.white);
            if (this.mThumbnailMode != 1) {
                z = false;
            }
            updatePeekBackground(whiteColor, z);
            requestLayout();
        }
    }

    public int getTopPeekAmount() {
        return this.mThumbnailMode == 1 ? this.mThumbnailVerticalPeek : 0;
    }

    public void updatePeekBackground(int color, boolean isPeeking) {
        if (isPeeking) {
            setBackgroundDrawable(new InsetDrawable(new PaintDrawable(color), 0, this.mThumbnailVerticalPeek, 0, 0));
        } else {
            setBackgroundColor(color);
        }
        setPadding(0, 0, 0, 0);
    }

    public void addParticipatingChildViews(List<View> participatingChildViewList) {
        participatingChildViewList.add(this.mTitle);
        participatingChildViewList.add(this.mWishlistButton);
        participatingChildViewList.add(this.mCreatorPanel);
        participatingChildViewList.add(this.mCreatorBlock);
        participatingChildViewList.add(this.mTipperSticker);
        participatingChildViewList.add(this.mBylines);
        participatingChildViewList.add(this.mExtraLabels);
        participatingChildViewList.add(this.mExtraLabelsBottom);
        participatingChildViewList.add(this.mDetailsSummaryDynamic);
    }

    public void addParticipatingChildViewIds(List<Integer> participatingChildViewIdList) {
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_title));
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_wishlist_button));
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_creator_panel));
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_creator_block));
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_tipper_sticker));
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_bylines));
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_extra_labels));
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_extra_labels_bottom));
        participatingChildViewIdList.add(Integer.valueOf(R.id.title_details_summary_dynamic));
    }

    public void setPadding(int left, int top, int right, int bottom) {
        if (this.mBackgroundLayer != null) {
            this.mBackgroundLayer.setPadding(left, top, right, bottom);
        }
    }

    public Drawable getBackground() {
        return this.mBackgroundLayer == null ? null : this.mBackgroundLayer.getBackground();
    }

    public void setBackgroundDrawable(Drawable background) {
        if (this.mBackgroundLayer != null) {
            this.mBackgroundLayer.setBackgroundDrawable(background);
        }
    }

    public void setBackground(Drawable background) {
        if (this.mBackgroundLayer != null) {
            this.mBackgroundLayer.setBackground(background);
        }
    }

    public void setBackgroundColor(int color) {
        if (this.mBackgroundLayer != null) {
            this.mBackgroundLayer.setBackgroundColor(color);
        }
    }

    public void setBackgroundResource(int resid) {
        if (this.mBackgroundLayer != null) {
            this.mBackgroundLayer.setBackgroundResource(resid);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int mainContentWidth = width - (this.mContentMargin * 2);
        int mainContentHeight = getTopPeekAmount() + this.mContentMargin;
        int thumbnailHeight = 0;
        if (this.mThumbnailFrame != null) {
            if (this.mThumbnailFrame.getVisibility() != 8) {
                LayoutParams thumbnailFrameLp = this.mThumbnailFrame.getLayoutParams();
                this.mThumbnailFrame.measure(MeasureSpec.makeMeasureSpec(thumbnailFrameLp.width, 1073741824), MeasureSpec.makeMeasureSpec(thumbnailFrameLp.height, 1073741824));
                mainContentWidth -= this.mThumbnailFrame.getMeasuredWidth();
                thumbnailHeight = thumbnailFrameLp.height;
                if (this.mThumbnailMode != 0) {
                    mainContentWidth -= this.mContentMargin;
                }
                int i = this.mThumbnailMode;
                if (r0 == 2) {
                    thumbnailHeight += this.mContentMargin;
                }
            }
        }
        int titleWidth = mainContentWidth;
        if (this.mWishlistButton.getVisibility() != 8) {
            this.mWishlistButton.measure(0, 0);
            titleWidth -= this.mWishlistButton.getMeasuredWidth();
        }
        this.mTitle.measure(MeasureSpec.makeMeasureSpec(titleWidth, 1073741824), 0);
        mainContentHeight += this.mTitle.getMeasuredHeight() - this.mTitleOffset;
        View bottomMostContentView = this.mTitle;
        if (this.mCreatorPanel.getVisibility() != 8) {
            this.mCreatorPanel.measure(MeasureSpec.makeMeasureSpec(mainContentWidth, Integer.MIN_VALUE), 0);
            mainContentHeight += this.mCreatorPanel.getMeasuredHeight();
            bottomMostContentView = this.mCreatorPanel;
        }
        if (this.mTipperSticker.getVisibility() != 8) {
            this.mTipperSticker.measure(MeasureSpec.makeMeasureSpec(mainContentWidth, Integer.MIN_VALUE), 0);
            mainContentHeight += this.mTipperSticker.getMeasuredHeight();
            bottomMostContentView = this.mTipperSticker;
        }
        if (this.mCreatorBlock.getVisibility() != 8) {
            this.mCreatorBlock.measure(MeasureSpec.makeMeasureSpec(mainContentWidth, Integer.MIN_VALUE), 0);
            mainContentHeight += this.mCreatorBlock.getMeasuredHeight() + (this.mContentMargin / 2);
            bottomMostContentView = this.mCreatorBlock;
        }
        if (this.mBylines.getVisibility() == 0) {
            this.mBylines.measure(MeasureSpec.makeMeasureSpec(mainContentWidth, Integer.MIN_VALUE), 0);
            mainContentHeight += this.mBylines.getMeasuredHeight();
            bottomMostContentView = this.mBylines;
        }
        if (this.mExtraLabels.getVisibility() == 0) {
            this.mExtraLabels.measure(MeasureSpec.makeMeasureSpec(mainContentWidth, Integer.MIN_VALUE), 0);
            this.mIsExtraLabelsInCompactMode = bottomMostContentView.getMeasuredWidth() + this.mExtraLabels.getMeasuredWidth() <= mainContentWidth;
            if (this.mIsExtraLabelsInCompactMode) {
                mainContentHeight += this.mExtraLabels.getMeasuredHeight() - bottomMostContentView.getMeasuredHeight();
            } else {
                mainContentHeight += this.mExtraLabels.getMeasuredHeight();
            }
        }
        int fullHeight = 0;
        if (this.mDetailsSummaryDynamic.getVisibility() != 8) {
            int maxSummaryDynamicWidth = width - (this.mContentMargin * 2);
            this.mDetailsSummaryDynamic.measure(MeasureSpec.makeMeasureSpec(maxSummaryDynamicWidth, Integer.MIN_VALUE), 0);
            int summaryDynamicWidth = this.mDetailsSummaryDynamic.getMeasuredWidth();
            int summaryDynamicHeight = this.mDetailsSummaryDynamic.getMeasuredHeight();
            int thumbnailHeightForDynamicFitting = thumbnailHeight;
            if (this.mThumbnailMode != 0) {
                thumbnailHeightForDynamicFitting -= this.mContentMargin;
            }
            if (summaryDynamicWidth > mainContentWidth || mainContentHeight + summaryDynamicHeight > thumbnailHeightForDynamicFitting) {
                boolean summaryIsItsOwnLine = true;
                if (this.mCreatorBlock.getVisibility() == 0) {
                    if (this.mBylines.getVisibility() == 8) {
                        if (this.mExtraLabels.getVisibility() == 8) {
                            int creatorBlockWidth = this.mCreatorBlock.getMeasuredWidth();
                            if (creatorBlockWidth + summaryDynamicWidth <= mainContentWidth) {
                                this.mDetailsSummaryDynamic.measure(MeasureSpec.makeMeasureSpec(mainContentWidth - creatorBlockWidth, 1073741824), 0);
                                fullHeight = Math.max(mainContentHeight, thumbnailHeight) - this.mContentMargin;
                                summaryIsItsOwnLine = false;
                            }
                        }
                    }
                }
                if (summaryIsItsOwnLine) {
                    this.mDetailsSummaryDynamic.measure(MeasureSpec.makeMeasureSpec(maxSummaryDynamicWidth, 1073741824), 0);
                    fullHeight = Math.max(mainContentHeight, thumbnailHeight) + this.mDetailsSummaryDynamic.getMeasuredHeight();
                }
            } else {
                this.mDetailsSummaryDynamic.measure(MeasureSpec.makeMeasureSpec(mainContentWidth, 1073741824), 0);
                fullHeight = thumbnailHeightForDynamicFitting;
            }
        } else {
            fullHeight = Math.max(mainContentHeight, thumbnailHeight) - this.mContentMargin;
        }
        if (this.mExtraLabelsBottom.getVisibility() != 8) {
            this.mExtraLabelsBottom.measure(MeasureSpec.makeMeasureSpec(width, 1073741824), 0);
            fullHeight = (fullHeight + this.mExtraLabelsBottom.getMeasuredHeight()) + this.mExtraLabelsBottom.getPaddingTop();
        } else {
            fullHeight += this.mContentMargin;
        }
        fullHeight = (fullHeight + this.mContentMargin) + (getPaddingTop() + getPaddingBottom());
        if (this.mBackgroundLayer.getVisibility() != 8) {
            this.mBackgroundLayer.measure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(fullHeight, 1073741824));
        }
        setMeasuredDimension(width, fullHeight);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int extraLabelsY;
        int width = getWidth();
        int height = getHeight();
        int mainContentX = this.mContentMargin;
        int mainContentY = getPaddingTop();
        int i = this.mThumbnailMode;
        if (r0 == 2) {
            mainContentY += this.mContentMargin;
        }
        if (this.mThumbnailFrame != null) {
            if (this.mThumbnailFrame.getVisibility() != 8) {
                int thumbnailFrameX;
                int thumbnailFrameWidth = this.mThumbnailFrame.getMeasuredWidth();
                if (this.mThumbnailMode == 0) {
                    thumbnailFrameX = 0;
                } else {
                    thumbnailFrameX = this.mContentMargin;
                }
                this.mThumbnailFrame.layout(thumbnailFrameX, mainContentY, thumbnailFrameX + thumbnailFrameWidth, this.mThumbnailFrame.getMeasuredHeight() + mainContentY);
                mainContentX += thumbnailFrameX + thumbnailFrameWidth;
            }
        }
        mainContentY += getTopPeekAmount();
        i = this.mThumbnailMode;
        if (r0 != 2) {
            mainContentY += this.mContentMargin;
        }
        if (this.mWishlistButton.getVisibility() != 8) {
            int wishlistButtonWidth = this.mWishlistButton.getMeasuredWidth();
            int wishlistButtonX = (width - wishlistButtonWidth) - this.mContentMargin;
            int wishlistButtonY = mainContentY;
            this.mWishlistButton.layout(wishlistButtonX, wishlistButtonY, wishlistButtonX + wishlistButtonWidth, this.mWishlistButton.getMeasuredHeight() + wishlistButtonY);
            this.mWishlistButton.getHitRect(this.mWishlistArea);
            this.mWishlistArea.inset(-this.mWishlistTouchExtend, -this.mWishlistTouchExtend);
            if (!this.mWishlistArea.equals(this.mOldWishlistArea)) {
                setTouchDelegate(new PlayTouchDelegate(this.mWishlistArea, this.mWishlistButton));
                this.mOldWishlistArea.set(this.mWishlistArea);
            }
        } else {
            this.mOldWishlistArea.setEmpty();
            setTouchDelegate(null);
        }
        mainContentY -= this.mTitleOffset;
        this.mTitle.layout(mainContentX, mainContentY, this.mTitle.getMeasuredWidth() + mainContentX, this.mTitle.getMeasuredHeight() + mainContentY);
        mainContentY += this.mTitle.getMeasuredHeight();
        if (this.mCreatorPanel.getVisibility() != 8) {
            this.mCreatorPanel.layout(mainContentX, mainContentY, this.mCreatorPanel.getMeasuredWidth() + mainContentX, this.mCreatorPanel.getMeasuredHeight() + mainContentY);
            mainContentY += this.mCreatorPanel.getMeasuredHeight();
        }
        if (this.mTipperSticker.getVisibility() == 0) {
            this.mTipperSticker.layout(mainContentX, mainContentY, this.mTipperSticker.getMeasuredWidth() + mainContentX, this.mTipperSticker.getMeasuredHeight() + mainContentY);
            mainContentY += this.mTipperSticker.getMeasuredHeight();
        }
        if (this.mCreatorBlock.getVisibility() != 8) {
            mainContentY += this.mContentMargin / 2;
            this.mCreatorBlock.layout(mainContentX, mainContentY, this.mCreatorBlock.getMeasuredWidth() + mainContentX, this.mCreatorBlock.getMeasuredHeight() + mainContentY);
            mainContentY += this.mCreatorBlock.getMeasuredHeight();
        }
        if (this.mBylines.getVisibility() == 0) {
            this.mBylines.layout(mainContentX, mainContentY, this.mBylines.getMeasuredWidth() + mainContentX, this.mBylines.getMeasuredHeight() + mainContentY);
            mainContentY += this.mBylines.getMeasuredHeight();
        }
        if (this.mIsExtraLabelsInCompactMode) {
            extraLabelsY = mainContentY - this.mExtraLabels.getMeasuredHeight();
        } else {
            extraLabelsY = mainContentY;
        }
        int bottomY = height - getPaddingBottom();
        if (this.mExtraLabelsBottom.getVisibility() != 8) {
            bottomY -= this.mExtraLabelsBottom.getPaddingTop();
            this.mExtraLabelsBottom.layout(0, bottomY - this.mExtraLabelsBottom.getMeasuredHeight(), this.mExtraLabelsBottom.getMeasuredWidth(), bottomY);
            bottomY -= this.mExtraLabelsBottom.getMeasuredHeight();
        } else {
            bottomY -= this.mContentMargin;
        }
        if (this.mDetailsSummaryDynamic.getVisibility() != 8) {
            int summaryDynamicHeight = this.mDetailsSummaryDynamic.getMeasuredHeight();
            int summaryDynamicWidth = this.mDetailsSummaryDynamic.getMeasuredWidth();
            int summaryDynamicX = (width - this.mContentMargin) - summaryDynamicWidth;
            this.mDetailsSummaryDynamic.layout(summaryDynamicX, bottomY - summaryDynamicHeight, summaryDynamicX + summaryDynamicWidth, bottomY);
            extraLabelsY = (bottomY - summaryDynamicHeight) - this.mExtraLabels.getMeasuredHeight();
        }
        if (this.mExtraLabels.getVisibility() != 8) {
            int extraLabelsWidth = this.mExtraLabels.getMeasuredWidth();
            int extraLabelsX = (width - extraLabelsWidth) - this.mContentMargin;
            this.mExtraLabels.layout(extraLabelsX, extraLabelsY, extraLabelsX + extraLabelsWidth, this.mExtraLabels.getMeasuredHeight() + extraLabelsY);
        }
        if (this.mBackgroundLayer.getVisibility() != 8) {
            this.mBackgroundLayer.layout(0, 0, width, height);
        }
    }
}
