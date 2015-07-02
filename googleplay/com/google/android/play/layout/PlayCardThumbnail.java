package com.google.android.play.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.play.R;

public class PlayCardThumbnail extends ViewGroup {
    private final int mAppThumbnailPadding;
    private int mCoverPadding;
    private final int mPersonThumbnailPadding;
    private ImageView mThumbnail;
    private final int mThumbnailId;

    public PlayCardThumbnail(Context context) {
        this(context, null);
    }

    public PlayCardThumbnail(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlayCardThumbnail);
        this.mThumbnailId = viewAttrs.getResourceId(R.styleable.PlayCardThumbnail_thumbnail_id, R.id.li_thumbnail);
        this.mAppThumbnailPadding = viewAttrs.getDimensionPixelSize(R.styleable.PlayCardThumbnail_app_thumbnail_padding, 0);
        this.mPersonThumbnailPadding = viewAttrs.getDimensionPixelSize(R.styleable.PlayCardThumbnail_person_thumbnail_padding, 0);
        viewAttrs.recycle();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mThumbnail = (ImageView) findViewById(this.mThumbnailId);
    }

    public int getBaseline() {
        return this.mCoverPadding + this.mThumbnail.getMeasuredHeight();
    }

    public ImageView getImageView() {
        return this.mThumbnail;
    }

    public void updateCoverPadding(int corpus) {
        int currCoverPadding = this.mCoverPadding;
        if (corpus == 3) {
            this.mCoverPadding = this.mAppThumbnailPadding;
        } else if (corpus == 9) {
            this.mCoverPadding = this.mPersonThumbnailPadding;
        } else {
            this.mCoverPadding = 0;
        }
        if (currCoverPadding != this.mCoverPadding) {
            requestLayout();
        }
    }

    public int getCoverPadding() {
        return this.mCoverPadding;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        this.mThumbnail.measure(MeasureSpec.makeMeasureSpec(Math.max(0, width - (this.mCoverPadding * 2)), 1073741824), MeasureSpec.makeMeasureSpec(Math.max(0, height - (this.mCoverPadding * 2)), 1073741824));
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.mThumbnail.layout(this.mCoverPadding, this.mCoverPadding, this.mCoverPadding + this.mThumbnail.getMeasuredWidth(), this.mCoverPadding + this.mThumbnail.getMeasuredHeight());
    }
}
