package com.google.android.finsky.layout;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.play.image.FifeImageView;

public class AvailablePromoOfferContent extends LinearLayout {
    private TextView mDescriptionTextView;
    private final int mEdgeToEdgeThreshold;
    private FifeImageView mImageView;
    private TextView mIntroTextView;
    private TextView mTermsTextView;

    public AvailablePromoOfferContent(Context context) {
        this(context, null);
    }

    public AvailablePromoOfferContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mEdgeToEdgeThreshold = context.getResources().getDimensionPixelSize(R.dimen.setup_wizard_promo_image_threshold);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mIntroTextView = (TextView) findViewById(R.id.promo_offer_introductory_text);
        this.mImageView = (FifeImageView) findViewById(R.id.promo_offer_image);
        this.mDescriptionTextView = (TextView) findViewById(R.id.promo_offer_description);
        this.mTermsTextView = (TextView) findViewById(R.id.promo_offer_terms);
    }

    public void configure(CharSequence intro, Image image, CharSequence description, CharSequence terms) {
        this.mIntroTextView.setText(intro);
        if (image != null) {
            this.mImageView.setImage(image.imageUrl, image.supportsFifeUrlOptions, FinskyApp.get().getBitmapLoader());
            this.mImageView.setVisibility(0);
        }
        if (!TextUtils.isEmpty(description)) {
            this.mDescriptionTextView.setText(description);
            this.mDescriptionTextView.setVisibility(0);
        }
        if (!TextUtils.isEmpty(terms)) {
            this.mTermsTextView.setText(terms);
            this.mTermsTextView.setMovementMethod(LinkMovementMethod.getInstance());
            this.mTermsTextView.setVisibility(0);
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        int contentWidth = (width - getPaddingLeft()) - getPaddingRight();
        int contentWidthSpec = MeasureSpec.makeMeasureSpec(contentWidth, 1073741824);
        this.mIntroTextView.measure(contentWidthSpec, 0);
        height += this.mIntroTextView.getMeasuredHeight();
        if (this.mImageView.getVisibility() != 8) {
            MarginLayoutParams imageViewLp = (MarginLayoutParams) this.mImageView.getLayoutParams();
            int imageHeight = contentWidth <= this.mEdgeToEdgeThreshold ? (int) (((float) contentWidth) * 0.5625f) : imageViewLp.height;
            this.mImageView.measure(contentWidthSpec, MeasureSpec.makeMeasureSpec(imageHeight, 1073741824));
            height += (imageViewLp.topMargin + imageHeight) + imageViewLp.bottomMargin;
        }
        if (this.mDescriptionTextView.getVisibility() != 8) {
            this.mDescriptionTextView.measure(contentWidthSpec, 0);
            height += this.mDescriptionTextView.getMeasuredHeight();
        }
        if (this.mTermsTextView.getVisibility() != 8) {
            this.mTermsTextView.measure(contentWidthSpec, 0);
            height += this.mTermsTextView.getMeasuredHeight();
        }
        setMeasuredDimension(width, height);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int contentLeft = getPaddingLeft();
        int contentTop = getPaddingTop();
        this.mIntroTextView.layout(contentLeft, contentTop, this.mIntroTextView.getMeasuredWidth() + contentLeft, this.mIntroTextView.getMeasuredHeight() + contentTop);
        contentTop += this.mIntroTextView.getMeasuredHeight();
        if (this.mImageView.getVisibility() != 8) {
            MarginLayoutParams imageViewLp = (MarginLayoutParams) this.mImageView.getLayoutParams();
            contentTop += imageViewLp.topMargin;
            this.mImageView.layout(contentLeft, contentTop, this.mImageView.getMeasuredWidth() + contentLeft, this.mImageView.getMeasuredHeight() + contentTop);
            contentTop += this.mImageView.getMeasuredHeight() + imageViewLp.bottomMargin;
        }
        if (this.mDescriptionTextView.getVisibility() != 8) {
            this.mDescriptionTextView.layout(contentLeft, contentTop, this.mDescriptionTextView.getMeasuredWidth() + contentLeft, this.mDescriptionTextView.getMeasuredHeight() + contentTop);
            contentTop += this.mDescriptionTextView.getMeasuredHeight();
        }
        if (this.mTermsTextView.getVisibility() != 8) {
            this.mTermsTextView.layout(contentLeft, contentTop, this.mTermsTextView.getMeasuredWidth() + contentLeft, this.mTermsTextView.getMeasuredHeight() + contentTop);
        }
    }
}
