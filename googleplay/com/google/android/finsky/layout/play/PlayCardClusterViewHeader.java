package com.google.android.finsky.layout.play;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import com.google.android.play.layout.ForegroundRelativeLayout;

public class PlayCardClusterViewHeader extends ForegroundRelativeLayout implements Identifiable {
    protected FifeImageView mHeaderImage;
    private final int mHeaderXPadding;
    private String mIdentifier;
    private final int mMinHeight;
    protected TextView mMoreView;
    protected View mTitleGroup;
    protected TextView mTitleMain;
    private TextView mTitleSecondary;

    public PlayCardClusterViewHeader(Context context) {
        this(context, null);
    }

    public PlayCardClusterViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlayCardClusterViewHeader);
        this.mMinHeight = viewAttrs.getDimensionPixelSize(0, 0);
        this.mHeaderXPadding = viewAttrs.getDimensionPixelSize(1, 0);
        viewAttrs.recycle();
        setPadding(this.mHeaderXPadding, 0, this.mHeaderXPadding, 0);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeaderImage = (FifeImageView) findViewById(R.id.cluster_image);
        this.mTitleGroup = findViewById(R.id.cluster_title);
        this.mTitleMain = (TextView) this.mTitleGroup.findViewById(R.id.header_title_main);
        this.mTitleSecondary = (TextView) this.mTitleGroup.findViewById(R.id.header_title_secondary);
        this.mMoreView = (TextView) findViewById(R.id.header_more);
    }

    public void setContent(int backendId, String titleMain, String titleSecondary, String more, OnClickListener clickListener) {
        setContent(null, backendId, null, titleMain, titleSecondary, more, clickListener);
    }

    public void setContent(BitmapLoader bitmapLoader, int backendId, Image image, String titleMain, String titleSecondary, String more, OnClickListener clickListener) {
        if (image != null) {
            this.mHeaderImage.setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
            this.mHeaderImage.setVisibility(0);
        } else {
            this.mHeaderImage.setVisibility(8);
        }
        this.mTitleMain.setText(titleMain);
        if (TextUtils.isEmpty(titleSecondary)) {
            this.mTitleSecondary.setVisibility(8);
        } else {
            this.mTitleSecondary.setText(Html.fromHtml(titleSecondary));
            this.mTitleSecondary.setVisibility(0);
        }
        if (TextUtils.isEmpty(more)) {
            this.mMoreView.setVisibility(8);
        } else {
            this.mMoreView.setText(more.toUpperCase());
            this.mMoreView.setVisibility(0);
            int morePaddingLeft = this.mMoreView.getPaddingLeft();
            int morePaddingRight = this.mMoreView.getPaddingRight();
            int morePaddingTop = this.mMoreView.getPaddingTop();
            int morePaddingBottom = this.mMoreView.getPaddingBottom();
            this.mMoreView.setBackgroundResource(CorpusResourceUtils.getPlayActionButtonBaseBackgroundDrawable(getContext(), backendId));
            this.mMoreView.setPadding(morePaddingLeft, morePaddingTop, morePaddingRight, morePaddingBottom);
        }
        setOnClickListener(clickListener);
        setClickable(clickListener != null);
    }

    public void setExtraHorizontalPadding(int extraHorizontalPadding) {
        setPadding(this.mHeaderXPadding + extraHorizontalPadding, 0, this.mHeaderXPadding + extraHorizontalPadding, 0);
    }

    public void replaceTitles(CharSequence titleMainNew, CharSequence titleSecondaryNew) {
        int i;
        int i2 = 8;
        this.mTitleMain.setText(titleMainNew);
        TextView textView = this.mTitleMain;
        if (TextUtils.isEmpty(titleMainNew)) {
            i = 8;
        } else {
            i = 0;
        }
        textView.setVisibility(i);
        this.mTitleSecondary.setText(titleSecondaryNew);
        TextView textView2 = this.mTitleSecondary;
        if (!TextUtils.isEmpty(titleSecondaryNew)) {
            i2 = 0;
        }
        textView2.setVisibility(i2);
    }

    public void setIdentifier(String identifier) {
        this.mIdentifier = identifier;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int titleGroupWidth = (width - getPaddingLeft()) - getPaddingRight();
        int maxContentHeight = 0;
        if (this.mHeaderImage.getVisibility() != 8) {
            MarginLayoutParams imageLp = (MarginLayoutParams) this.mHeaderImage.getLayoutParams();
            this.mHeaderImage.measure(MeasureSpec.makeMeasureSpec(imageLp.width, 1073741824), MeasureSpec.makeMeasureSpec(imageLp.height, 1073741824));
            maxContentHeight = this.mHeaderImage.getMeasuredHeight();
            titleGroupWidth -= this.mHeaderImage.getMeasuredWidth() + imageLp.rightMargin;
        }
        if (this.mMoreView.getVisibility() != 8) {
            this.mMoreView.measure(0, 0);
            maxContentHeight = Math.max(maxContentHeight, this.mMoreView.getMeasuredHeight());
            titleGroupWidth -= this.mMoreView.getMeasuredWidth();
        }
        this.mTitleGroup.measure(MeasureSpec.makeMeasureSpec(titleGroupWidth, 1073741824), 0);
        setMeasuredDimension(width, Math.max(Math.max(maxContentHeight, this.mTitleGroup.getMeasuredHeight()), this.mMinHeight) + getPaddingTop());
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int paddingTop = getPaddingTop();
        int x = getPaddingLeft();
        if (this.mHeaderImage.getVisibility() != 8) {
            int imageWidth = this.mHeaderImage.getMeasuredWidth();
            int imageHeight = this.mHeaderImage.getMeasuredHeight();
            int imageTop = paddingTop + (((height - imageHeight) - paddingTop) / 2);
            this.mHeaderImage.layout(x, imageTop, x + imageWidth, imageTop + imageHeight);
            x += ((MarginLayoutParams) this.mHeaderImage.getLayoutParams()).rightMargin + imageWidth;
        }
        this.mTitleGroup.layout(x, paddingTop, this.mTitleGroup.getMeasuredWidth() + x, height);
        if (this.mMoreView.getVisibility() != 8) {
            int rightX = width - getPaddingRight();
            int moreWidth = this.mMoreView.getMeasuredWidth();
            int moreHeight = this.mMoreView.getMeasuredHeight();
            int moreTop = paddingTop + (((height - moreHeight) - paddingTop) / 2);
            this.mMoreView.layout(rightX - moreWidth, moreTop, rightX, moreTop + moreHeight);
        }
    }
}
