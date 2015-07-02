package com.google.android.play.layout;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.play.R;

public class PlayCardSnippet extends PlaySeparatorLayout {
    private final int mAvatarLargeSize;
    private int mAvatarMarginLeft;
    private final int mAvatarRegularSize;
    private int mAvatarSize;
    private int mMode;
    private ImageView mSnippetAvatar;
    private TextView mSnippetText;
    private final int mTextLargeSize;
    private int mTextOnlyMarginLeft;
    private final int mTextRegularSize;

    public PlayCardSnippet(Context context) {
        this(context, null);
    }

    public PlayCardSnippet(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mAvatarRegularSize = res.getDimensionPixelSize(R.dimen.play_card_snippet_avatar_size);
        this.mAvatarLargeSize = res.getDimensionPixelSize(R.dimen.play_card_snippet_avatar_large_size);
        this.mTextRegularSize = res.getDimensionPixelSize(R.dimen.play_snippet_regular_size);
        this.mTextLargeSize = res.getDimensionPixelSize(R.dimen.play_snippet_large_size);
        this.mMode = 0;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSnippetText = (TextView) findViewById(R.id.li_snippet_text);
        this.mSnippetAvatar = (ImageView) findViewById(R.id.li_snippet_avatar);
        syncWithCurrentSizeMode();
    }

    private void syncWithCurrentSizeMode() {
        this.mAvatarSize = this.mMode == 0 ? this.mAvatarRegularSize : this.mAvatarLargeSize;
        this.mSnippetText.setTextSize(0, this.mMode == 0 ? (float) this.mTextRegularSize : (float) this.mTextLargeSize);
    }

    public void setSizeMode(int mode) {
        if (mode != 0 && mode != 1) {
            throw new IllegalArgumentException("Unsupported size mode: " + mode);
        } else if (this.mMode != mode) {
            this.mMode = mode;
            syncWithCurrentSizeMode();
            requestLayout();
            invalidate();
        }
    }

    public ImageView getImageView() {
        return this.mSnippetAvatar;
    }

    public void setSnippetText(CharSequence snippet, int textOnlyMarginLeft, int avatarMarginLeft) {
        this.mSnippetText.setText(snippet);
        this.mTextOnlyMarginLeft = textOnlyMarginLeft;
        this.mAvatarMarginLeft = avatarMarginLeft;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean isImageGone;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int snippetWidth = (width - getPaddingLeft()) - getPaddingRight();
        if (this.mSnippetAvatar.getVisibility() == 8) {
            isImageGone = true;
        } else {
            isImageGone = false;
        }
        if (isImageGone) {
            snippetWidth -= this.mTextOnlyMarginLeft;
        } else {
            MarginLayoutParams imageLp = (MarginLayoutParams) this.mSnippetAvatar.getLayoutParams();
            int avatarSpec = MeasureSpec.makeMeasureSpec(this.mAvatarSize, 1073741824);
            this.mSnippetAvatar.measure(avatarSpec, avatarSpec);
            snippetWidth -= (this.mAvatarSize + imageLp.rightMargin) + this.mAvatarMarginLeft;
        }
        this.mSnippetText.measure(MeasureSpec.makeMeasureSpec(snippetWidth, 1073741824), 0);
        setMeasuredDimension(width, (getPaddingTop() + Math.max(this.mAvatarSize, this.mSnippetText.getMeasuredHeight())) + getPaddingBottom());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = getHeight();
        int paddingTop = getPaddingTop();
        int snippetHeight = this.mSnippetText.getMeasuredHeight();
        int snippetWidth = this.mSnippetText.getMeasuredWidth();
        if (this.mSnippetAvatar.getVisibility() == 8) {
            int textTop = paddingTop + (((height - snippetHeight) - paddingTop) / 2);
            this.mSnippetText.layout(this.mTextOnlyMarginLeft, textTop, this.mTextOnlyMarginLeft + snippetWidth, textTop + snippetHeight);
            return;
        }
        int imageHeight = this.mSnippetAvatar.getMeasuredHeight();
        int imageWidth = this.mSnippetAvatar.getMeasuredWidth();
        int textX = (this.mAvatarMarginLeft + imageWidth) + ((MarginLayoutParams) this.mSnippetAvatar.getLayoutParams()).rightMargin;
        if (imageHeight > snippetHeight) {
            int imageY = paddingTop + (((height - imageHeight) - paddingTop) / 2);
            this.mSnippetAvatar.layout(this.mAvatarMarginLeft, imageY, this.mAvatarMarginLeft + imageWidth, imageY + imageHeight);
            int textY = paddingTop + (((height - snippetHeight) - paddingTop) / 2);
            this.mSnippetText.layout(textX, textY, textX + snippetWidth, textY + snippetHeight);
            return;
        }
        int contentY = paddingTop + (((height - snippetHeight) - paddingTop) / 2);
        this.mSnippetAvatar.layout(this.mAvatarMarginLeft, contentY, this.mAvatarMarginLeft + imageWidth, contentY + imageHeight);
        this.mSnippetText.layout(textX, contentY, textX + snippetWidth, contentY + snippetHeight);
    }
}
