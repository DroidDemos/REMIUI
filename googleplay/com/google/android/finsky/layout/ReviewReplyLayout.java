package com.google.android.finsky.layout;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.DateUtils;

public class ReviewReplyLayout extends LinearLayout {
    private boolean mIsExpanded;
    TextView mReplyHeader;
    TextView mReplyText;
    ImageView mReplyToggle;

    public ReviewReplyLayout(Context context) {
        this(context, null);
    }

    public ReviewReplyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mReplyHeader = (TextView) findViewById(R.id.review_reply_header);
        this.mReplyText = (TextView) findViewById(R.id.review_reply_text);
        this.mReplyToggle = (ImageView) findViewById(R.id.review_reply_toggle);
    }

    public void setReviewInfo(Document doc, Review review) {
        if (!review.hasReplyText) {
            setVisibility(8);
        }
        Context context = getContext();
        String docCreator = doc.getCreator();
        if (review.hasReplyTimestampMsec) {
            long replyTimestampMsec = review.replyTimestampMsec;
            String formattedReplyDate = DateUtils.formatShortDisplayDate(replyTimestampMsec);
            if (!review.hasTimestampMsec || review.timestampMsec <= replyTimestampMsec) {
                disableToggle();
                this.mReplyHeader.setText(Html.fromHtml(context.getString(R.string.review_reply_title, new Object[]{docCreator, formattedReplyDate})));
            } else {
                enableToggle();
                showMoreIndicator();
                this.mReplyHeader.setText(Html.fromHtml(context.getString(R.string.review_reply_after_edit_title, new Object[]{docCreator, formattedReplyDate})));
            }
        } else {
            disableToggle();
            this.mReplyHeader.setText(context.getString(R.string.review_reply_no_date_title, new Object[]{docCreator}));
        }
        this.mReplyText.setText(review.replyText);
        setVisibility(0);
    }

    private void showMoreIndicator() {
        this.mReplyToggle.setImageResource(R.drawable.ic_more_arrow_down);
    }

    private void showLessIndicator() {
        this.mReplyToggle.setImageResource(R.drawable.ic_more_arrow_up);
    }

    private void disableToggle() {
        this.mReplyToggle.setVisibility(8);
        this.mReplyText.setVisibility(0);
        setOnClickListener(null);
    }

    private void enableToggle() {
        this.mIsExpanded = false;
        this.mReplyToggle.setVisibility(0);
        this.mReplyText.setVisibility(8);
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                boolean z = false;
                if (ReviewReplyLayout.this.mIsExpanded) {
                    ReviewReplyLayout.this.showMoreIndicator();
                    ReviewReplyLayout.this.mReplyText.setVisibility(8);
                } else {
                    ReviewReplyLayout.this.showLessIndicator();
                    ReviewReplyLayout.this.mReplyText.setVisibility(0);
                }
                ReviewReplyLayout reviewReplyLayout = ReviewReplyLayout.this;
                if (!ReviewReplyLayout.this.mIsExpanded) {
                    z = true;
                }
                reviewReplyLayout.mIsExpanded = z;
            }
        });
    }
}
