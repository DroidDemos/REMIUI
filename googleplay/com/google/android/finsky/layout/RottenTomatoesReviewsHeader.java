package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Rev.CriticReviewsResponse;
import com.google.android.finsky.utils.IntMath;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;

public class RottenTomatoesReviewsHeader extends RelativeLayout {
    private RottenTomatoesMeter mFavorablePercentBar;
    private TextView mFavorablePercentValue;
    private final int mHalfSeparatorThickness;
    private TextView mReviewsCount;
    private FifeImageView mSentimentImage;
    private final int mSeparatorInset;
    private final Paint mSeparatorPaint;
    private TextView mSource;
    private TextView mTitle;

    public RottenTomatoesReviewsHeader(Context context) {
        this(context, null);
    }

    public RottenTomatoesReviewsHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        int separatorThickness = res.getDimensionPixelSize(R.dimen.play_hairline_separator_thickness);
        this.mHalfSeparatorThickness = IntMath.ceil(separatorThickness, 2);
        this.mSeparatorPaint = new Paint();
        this.mSeparatorPaint.setColor(res.getColor(R.color.play_translucent_separator_line));
        this.mSeparatorPaint.setStrokeWidth((float) separatorThickness);
        this.mSeparatorInset = res.getDimensionPixelSize(R.dimen.details_new_content_margin);
        setWillNotDraw(false);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitle = (TextView) findViewById(R.id.title);
        this.mSentimentImage = (FifeImageView) findViewById(R.id.sentiment_image);
        this.mFavorablePercentValue = (TextView) findViewById(R.id.favorable_percent_value);
        this.mReviewsCount = (TextView) findViewById(R.id.reviews_count);
        this.mFavorablePercentBar = (RottenTomatoesMeter) findViewById(R.id.favorable_percent_bar);
        this.mSource = (TextView) findViewById(R.id.source);
    }

    public void bind(final CriticReviewsResponse reviewsResponse, final NavigationManager navigationManager, final DfeToc dfeToc, BitmapLoader bitmapLoader) {
        this.mTitle.setText(reviewsResponse.title.toUpperCase());
        this.mSentimentImage.setImage(reviewsResponse.aggregateSentiment.imageUrl, reviewsResponse.aggregateSentiment.supportsFifeUrlOptions, bitmapLoader);
        this.mFavorablePercentValue.setText(Integer.toString(reviewsResponse.percentFavorable));
        if (reviewsResponse.hasTotalNumReviews) {
            this.mReviewsCount.setText(Html.fromHtml(getResources().getString(R.string.reviews_count_label, new Object[]{Integer.valueOf(reviewsResponse.totalNumReviews)})));
            this.mReviewsCount.setVisibility(0);
        } else {
            this.mReviewsCount.setVisibility(8);
        }
        this.mFavorablePercentBar.setPercentValue(reviewsResponse.percentFavorable);
        this.mSource.setText(reviewsResponse.sourceText);
        if (reviewsResponse.source != null) {
            this.mSource.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    navigationManager.resolveLink(reviewsResponse.source, dfeToc, RottenTomatoesReviewsHeader.this.getContext().getPackageManager());
                }
            });
        } else {
            this.mSource.setOnClickListener(null);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bottomY = getHeight() - this.mHalfSeparatorThickness;
        canvas.drawLine((float) this.mSeparatorInset, (float) bottomY, (float) (getWidth() - this.mSeparatorInset), (float) bottomY, this.mSeparatorPaint);
    }
}
