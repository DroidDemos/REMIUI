package com.google.android.play.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.play.R;
import com.google.android.play.cardview.CardViewGroupDelegate;
import com.google.android.play.cardview.CardViewGroupDelegates;
import com.google.android.play.utils.PlayTouchDelegate;

public abstract class PlayCardViewBase extends ForegroundRelativeLayout {
    protected static final boolean DISABLE_NESTED_FOCUS_TRAVERSAL;
    protected final int mAvatarSnippetMarginLeft;
    protected int mBackendId;
    private final int mCardInset;
    protected Object mData;
    protected PlayTextView mDescription;
    private Drawable mDisabledDrawable;
    protected TextView mHighlightLabel;
    protected TextView mHighlightSubtitle;
    protected boolean mIsItemOwned;
    protected PlayTextView mItemBadge;
    protected PlayCardLabelView mLabel;
    protected View mLoadingIndicator;
    protected Object mLoggingData;
    private final Rect mOldOverflowArea;
    protected ImageView mOverflow;
    private final Rect mOverflowArea;
    private final int mOverflowTouchExtend;
    private final int mOwnershipRenderingType;
    protected StarRatingBar mRatingBar;
    private final boolean mShowInlineCreatorBadge;
    protected PlayCardSnippet mSnippet1;
    protected PlayCardSnippet mSnippet2;
    protected PlayTextView mSubtitle;
    protected boolean mSupportsSubtitleAndRating;
    protected final int mTextOnlySnippetMarginLeft;
    protected PlayCardThumbnail mThumbnail;
    protected float mThumbnailAspectRatio;
    protected TextView mTitle;
    private boolean mToDisplayAsDisabled;

    public abstract int getCardType();

    static {
        DISABLE_NESTED_FOCUS_TRAVERSAL = VERSION.SDK_INT <= 13;
    }

    public PlayCardViewBase(Context context) {
        this(context, null, 0);
    }

    public PlayCardViewBase(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayCardViewBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mOverflowTouchExtend = context.getResources().getDimensionPixelSize(R.dimen.play_card_overflow_touch_extend);
        this.mOverflowArea = new Rect();
        this.mOldOverflowArea = new Rect();
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlayCardBaseView);
        this.mShowInlineCreatorBadge = viewAttrs.getBoolean(R.styleable.PlayCardBaseView_card_show_inline_creator_badge, false);
        this.mSupportsSubtitleAndRating = viewAttrs.getBoolean(R.styleable.PlayCardBaseView_card_supports_subtitle_and_rating, false);
        this.mTextOnlySnippetMarginLeft = viewAttrs.getDimensionPixelSize(R.styleable.PlayCardBaseView_card_text_only_snippet_margin_left, context.getResources().getDimensionPixelSize(R.dimen.play_card_snippet_text_extra_margin_left));
        this.mAvatarSnippetMarginLeft = viewAttrs.getDimensionPixelSize(R.styleable.PlayCardBaseView_card_avatar_snippet_margin_left, 0);
        this.mOwnershipRenderingType = viewAttrs.getInt(R.styleable.PlayCardBaseView_card_owned_status_rendering_type, 1);
        viewAttrs.recycle();
        this.mCardInset = context.getResources().getDimensionPixelSize(R.dimen.play_card_default_inset);
        setForegroundPadding(this.mCardInset, this.mCardInset, this.mCardInset, this.mCardInset);
        getCardViewGroupDelegate().initialize(this, context, attrs, defStyle);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        PlayCardWindowLifecycleTracker.getInstance().cardAttachedToWindow(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        PlayCardWindowLifecycleTracker.getInstance().cardDetachedFromWindow(this);
    }

    public CardViewGroupDelegate getCardViewGroupDelegate() {
        return CardViewGroupDelegates.IMPL;
    }

    public void setBackgroundColor(int color) {
        getCardViewGroupDelegate().setBackgroundColor(this, color);
    }

    public void setBackgroundResource(int resId) {
        getCardViewGroupDelegate().setBackgroundResource(this, resId);
    }

    public void setData(Object data, int backendId) {
        this.mData = data;
        this.mBackendId = backendId;
    }

    public Object getData() {
        return this.mData;
    }

    public void setLoggingData(Object loggingData) {
        this.mLoggingData = loggingData;
    }

    public Object getLoggingData() {
        return this.mLoggingData;
    }

    public PlayCardThumbnail getThumbnail() {
        return this.mThumbnail;
    }

    public TextView getTitle() {
        return this.mTitle;
    }

    public TextView getHighlightLabel() {
        return this.mHighlightLabel;
    }

    public TextView getHighlightSubtitle() {
        return this.mHighlightSubtitle;
    }

    public PlayTextView getSubtitle() {
        return this.mSubtitle;
    }

    public StarRatingBar getRatingBar() {
        return this.mRatingBar;
    }

    public PlayTextView getItemBadge() {
        return this.mItemBadge;
    }

    public PlayCardLabelView getLabel() {
        return this.mLabel;
    }

    public PlayTextView getDescription() {
        return this.mDescription;
    }

    public ImageView getOverflow() {
        return this.mOverflow;
    }

    public PlayCardSnippet getSnippet1() {
        return this.mSnippet1;
    }

    public PlayCardSnippet getSnippet2() {
        return this.mSnippet2;
    }

    public View getLoadingIndicator() {
        return this.mLoadingIndicator;
    }

    public boolean shouldShowInlineCreatorBadge() {
        return this.mShowInlineCreatorBadge;
    }

    public boolean supportsSubtitleAndRating() {
        return this.mSupportsSubtitleAndRating;
    }

    public int getOwnershipRenderingType() {
        return this.mOwnershipRenderingType;
    }

    public int getTextOnlySnippetMarginLeft() {
        return this.mTextOnlySnippetMarginLeft;
    }

    public int getAvatarSnippetMarginLeft() {
        return this.mAvatarSnippetMarginLeft;
    }

    public void setDisplayAsDisabled(boolean displayAsDisabled) {
        if (this.mToDisplayAsDisabled != displayAsDisabled) {
            this.mToDisplayAsDisabled = displayAsDisabled;
            setDescendantFocusability(this.mToDisplayAsDisabled ? 393216 : 131072);
            invalidate();
        }
    }

    public void setItemOwned(boolean isItemOwned) {
        this.mIsItemOwned = isItemOwned;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mThumbnail = (PlayCardThumbnail) findViewById(R.id.li_thumbnail_frame);
        this.mTitle = (TextView) findViewById(R.id.li_title);
        this.mSubtitle = (PlayTextView) findViewById(R.id.li_subtitle);
        this.mRatingBar = (StarRatingBar) findViewById(R.id.li_rating);
        this.mItemBadge = (PlayTextView) findViewById(R.id.li_badge);
        this.mDescription = (PlayTextView) findViewById(R.id.li_description);
        this.mOverflow = (ImageView) findViewById(R.id.li_overflow);
        this.mLabel = (PlayCardLabelView) findViewById(R.id.li_label);
        this.mSnippet1 = (PlayCardSnippet) findViewById(R.id.li_snippet_1);
        this.mSnippet2 = (PlayCardSnippet) findViewById(R.id.li_snippet_2);
        this.mLoadingIndicator = findViewById(R.id.loading_progress_bar);
        this.mHighlightLabel = (TextView) findViewById(R.id.li_highlight_label);
        this.mHighlightSubtitle = (TextView) findViewById(R.id.li_highlight_subtitle);
        if (DISABLE_NESTED_FOCUS_TRAVERSAL) {
            setNextFocusRightId(-1);
            if (this.mOverflow != null) {
                this.mOverflow.setFocusable(false);
                this.mOverflow.setNextFocusLeftId(-1);
            }
        }
    }

    protected void measureThumbnailSpanningWidth(int widthMeasureSpec) {
        int availableWidth = MeasureSpec.getSize(widthMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        if (this.mThumbnail.getVisibility() != 8) {
            thumbnailLp.height = (int) (this.mThumbnailAspectRatio * ((float) ((((availableWidth - paddingLeft) - paddingRight) - thumbnailLp.leftMargin) - thumbnailLp.rightMargin)));
            return;
        }
        thumbnailLp.height = 0;
    }

    protected void measureThumbnailSpanningHeight(int heightMeasureSpec) {
        int availableHeight = MeasureSpec.getSize(heightMeasureSpec);
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        MarginLayoutParams thumbnailLp = (MarginLayoutParams) this.mThumbnail.getLayoutParams();
        if (this.mThumbnail.getVisibility() != 8) {
            thumbnailLp.width = (int) (((float) ((((availableHeight - paddingTop) - paddingBottom) - thumbnailLp.topMargin) - thumbnailLp.bottomMargin)) / this.mThumbnailAspectRatio);
        } else {
            thumbnailLp.width = 0;
        }
    }

    public void setThumbnailAspectRatio(float aspectRatio) {
        if (this.mThumbnailAspectRatio != aspectRatio) {
            this.mThumbnailAspectRatio = aspectRatio;
            requestLayout();
        }
    }

    public void bindLoading() {
        setOnClickListener(null);
        setClickable(false);
        setContentDescription(null);
        this.mLoadingIndicator.setVisibility(0);
        this.mTitle.setVisibility(8);
        if (this.mSubtitle != null) {
            this.mSubtitle.setVisibility(8);
        }
        this.mThumbnail.setVisibility(8);
        if (this.mLabel != null) {
            this.mLabel.setVisibility(8);
        }
        if (this.mRatingBar != null) {
            this.mRatingBar.setVisibility(8);
        }
        if (this.mItemBadge != null) {
            this.mItemBadge.setVisibility(8);
        }
        if (this.mDescription != null) {
            this.mDescription.setVisibility(8);
        }
        if (this.mOverflow != null) {
            this.mOverflow.setVisibility(8);
        }
        if (this.mHighlightLabel != null) {
            this.mHighlightLabel.setVisibility(8);
        }
        if (this.mHighlightSubtitle != null) {
            this.mHighlightSubtitle.setVisibility(8);
        }
        setVisibility(0);
    }

    public void clearCardState() {
        this.mThumbnail.setVisibility(8);
        setVisibility(4);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mDescription != null && this.mDescription.getVisibility() == 0 && !TextUtils.isEmpty(this.mDescription.getText())) {
            int descriptionHeight = this.mDescription.getMeasuredHeight();
            Layout layout = this.mDescription.getLayout();
            if (layout != null) {
                int line = 0;
                while (line < layout.getLineCount()) {
                    if (layout.getLineBottom(line) > descriptionHeight) {
                        this.mDescription.setVisibility(line >= 2 ? 0 : 4);
                        return;
                    }
                    line++;
                }
            }
        }
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        recomputeOverflowAreaIfNeeded();
    }

    protected final void recomputeOverflowAreaIfNeeded() {
        if (this.mOverflow != null && this.mOverflow.getVisibility() != 8) {
            this.mOverflow.getHitRect(this.mOverflowArea);
            Rect rect = this.mOverflowArea;
            rect.top -= this.mOverflowTouchExtend;
            rect = this.mOverflowArea;
            rect.bottom += this.mOverflowTouchExtend;
            rect = this.mOverflowArea;
            rect.left -= this.mOverflowTouchExtend;
            rect = this.mOverflowArea;
            rect.right += this.mOverflowTouchExtend;
            if (this.mOverflowArea.top != this.mOldOverflowArea.top || this.mOverflowArea.bottom != this.mOldOverflowArea.bottom || this.mOverflowArea.left != this.mOldOverflowArea.left || this.mOverflowArea.right != this.mOldOverflowArea.right) {
                setTouchDelegate(new PlayTouchDelegate(this.mOverflowArea, this.mOverflow));
                this.mOldOverflowArea.set(this.mOverflowArea);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.mToDisplayAsDisabled) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.mToDisplayAsDisabled) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.mToDisplayAsDisabled) {
            if (this.mDisabledDrawable == null) {
                this.mDisabledDrawable = new PaintDrawable(getResources().getColor(R.color.play_dismissed_overlay));
            }
            this.mDisabledDrawable.setBounds(0, 0, width, height);
            this.mDisabledDrawable.draw(canvas);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        boolean shouldMarkAsDisabled;
        boolean result = super.dispatchPopulateAccessibilityEvent(event);
        if (this.mToDisplayAsDisabled && event.getEventType() == 8) {
            shouldMarkAsDisabled = true;
        } else {
            shouldMarkAsDisabled = false;
        }
        if (!shouldMarkAsDisabled) {
            return result;
        }
        event.setEnabled(false);
        return true;
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setEnabled(!this.mToDisplayAsDisabled);
    }
}
