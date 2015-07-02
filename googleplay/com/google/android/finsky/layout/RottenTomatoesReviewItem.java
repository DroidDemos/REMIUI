package com.google.android.finsky.layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;

public class RottenTomatoesReviewItem extends RelativeLayout {
    private TextView mAuthor;
    private TextView mComment;
    private View mExternalLinkAction;
    private FifeImageView mSentimentImage;
    private TextView mSource;

    public RottenTomatoesReviewItem(Context context) {
        this(context, null);
    }

    public RottenTomatoesReviewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSentimentImage = (FifeImageView) findViewById(R.id.sentiment_image);
        this.mExternalLinkAction = findViewById(R.id.external_link_action_image);
        this.mComment = (TextView) findViewById(R.id.review_comment);
        this.mAuthor = (TextView) findViewById(R.id.review_author);
        this.mSource = (TextView) findViewById(R.id.review_source);
    }

    public void bind(final Review review, NavigationManager navigationManager, BitmapLoader bitmapLoader) {
        this.mSentimentImage.setImage(review.sentiment.imageUrl, review.sentiment.supportsFifeUrlOptions, bitmapLoader);
        if (TextUtils.isEmpty(review.url)) {
            this.mExternalLinkAction.setVisibility(4);
        } else {
            this.mExternalLinkAction.setVisibility(0);
            this.mExternalLinkAction.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(review.url));
                    RottenTomatoesReviewItem.this.getContext().startActivity(intent);
                }
            });
        }
        this.mComment.setText(review.comment);
        this.mAuthor.setText(review.authorName);
        this.mSource.setText(review.source);
    }
}
