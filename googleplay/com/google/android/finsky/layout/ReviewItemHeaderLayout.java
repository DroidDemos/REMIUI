package com.google.android.finsky.layout;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.layout.play.SingleLineContainer;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.play.layout.StarRatingBar;

public class ReviewItemHeaderLayout extends SingleLineContainer {
    private TextView mDate;
    private TextView mEdited;
    private StarRatingBar mRating;
    private TextView mSource;

    public ReviewItemHeaderLayout(Context context) {
        super(context);
    }

    public ReviewItemHeaderLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRating = (StarRatingBar) findViewById(R.id.review_rating);
        this.mSource = (TextView) findViewById(R.id.review_source);
        this.mDate = (TextView) findViewById(R.id.review_date);
        this.mEdited = (TextView) findViewById(R.id.review_edited);
    }

    public void setReviewInfo(Review review) {
        String reviewSource = review.source;
        final String sourceUrl = review.url;
        if (TextUtils.isEmpty(reviewSource)) {
            this.mSource.setVisibility(8);
        } else {
            this.mSource.setText(reviewSource.toUpperCase());
            this.mSource.setVisibility(0);
            if (TextUtils.isEmpty(sourceUrl)) {
                this.mSource.setOnClickListener(null);
            } else {
                this.mSource.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        v.getContext().startActivity(IntentUtils.createViewIntentForUrl(Uri.parse(sourceUrl)));
                    }
                });
            }
        }
        if (review.hasStarRating) {
            this.mRating.setVisibility(0);
            this.mRating.setRating((float) review.starRating);
        } else {
            this.mRating.setVisibility(8);
        }
        if (review.hasTimestampMsec) {
            this.mDate.setText(DateUtils.formatShortDisplayDate(review.timestampMsec));
            this.mDate.setVisibility(0);
        } else {
            this.mDate.setVisibility(8);
        }
        this.mEdited.setVisibility(8);
        if (review.hasReplyText && review.hasReplyTimestampMsec && review.hasTimestampMsec && review.timestampMsec > review.replyTimestampMsec) {
            this.mEdited.setVisibility(0);
        }
    }
}
