package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.BucketRow;
import com.google.android.finsky.layout.ForegroundLinearLayout;
import com.google.android.finsky.layout.HistogramView;
import com.google.android.finsky.layout.ReviewItemLayout;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.IntMath;
import com.google.android.play.utils.PlayCorpusUtils;

public class ReviewsSamplesModuleLayout extends ForegroundLinearLayout {
    private TextView mAllReviewsFooter;
    private boolean mHasBinded;
    private LayoutInflater mLayoutInflater;
    protected NavigationManager mNavigationManager;
    private PlayStoreUiElementNode mParentNode;
    private LinearLayout mReviewHolder;
    private ReviewSamplesClickListener mReviewSamplesClickListener;
    private HistogramView mReviewStats;
    private final int mReviewsMaxRows;
    private final int mReviewsPerRow;

    public interface ReviewSamplesClickListener {
        void onReviewFeedbackClick(Review review);
    }

    public ReviewsSamplesModuleLayout(Context context) {
        this(context, null);
    }

    public ReviewsSamplesModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mReviewsPerRow = res.getInteger(R.integer.sample_reviews_per_row);
        this.mReviewsMaxRows = res.getInteger(R.integer.sample_reviews_max_rows);
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mReviewStats = (HistogramView) findViewById(R.id.reviews_statistics_panel);
        this.mReviewHolder = (LinearLayout) findViewById(R.id.section_content_sample_reviews);
        this.mAllReviewsFooter = (TextView) findViewById(R.id.all_reviews_footer);
    }

    public void bind(DfeReviews dfeReviews, Document doc, ReviewSamplesClickListener reviewFeedbackHandler, NavigationManager navManager, PlayStoreUiElementNode parentNode) {
        this.mParentNode = parentNode;
        this.mReviewSamplesClickListener = reviewFeedbackHandler;
        this.mNavigationManager = navManager;
        this.mHasBinded = true;
        this.mReviewStats.bind(doc);
        View reviewSummaryBox = this.mReviewStats.getSummaryBox();
        if (this.mReviewStats.getVisibility() == 0 && reviewSummaryBox != null && reviewSummaryBox.getVisibility() == 0) {
            setNextFocusDownId(reviewSummaryBox.getId());
            reviewSummaryBox.setNextFocusUpId(getId());
        }
        this.mReviewHolder.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        int reviewCount = dfeReviews.getCount();
        int maxReviewsToShow = this.mReviewsPerRow * this.mReviewsMaxRows;
        int reviewsToShow = Math.min(maxReviewsToShow, reviewCount);
        int reviewRows = IntMath.ceil(reviewsToShow, this.mReviewsPerRow);
        for (int rowIndex = 0; rowIndex < reviewRows; rowIndex++) {
            BucketRow row = (BucketRow) inflater.inflate(R.layout.review_samples_row, this.mReviewHolder, false);
            row.setSameChildHeight(true);
            int colIndex = 0;
            while (true) {
                int i = this.mReviewsPerRow;
                if (colIndex >= r0) {
                    break;
                }
                int reviewIndex = (this.mReviewsPerRow * rowIndex) + colIndex;
                if (reviewIndex < reviewsToShow) {
                    row.addView(buildReviewItemView((Review) dfeReviews.getItem(reviewIndex), doc, this.mReviewHolder));
                } else {
                    View dummy = new View(getContext());
                    dummy.setVisibility(4);
                    row.addView(dummy);
                }
                colIndex++;
            }
            this.mReviewHolder.addView(row);
        }
        if (reviewCount > maxReviewsToShow) {
            Context context = getContext();
            this.mAllReviewsFooter.setVisibility(0);
            this.mAllReviewsFooter.setText(context.getString(R.string.all_reviews).toUpperCase());
            this.mAllReviewsFooter.setTextColor(PlayCorpusUtils.getPrimaryTextColor(context, doc.getBackend()));
        } else {
            this.mAllReviewsFooter.setVisibility(8);
        }
        final Document document = doc;
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ReviewsSamplesModuleLayout.this.mNavigationManager.goToAllReviews(document, document.getReviewsUrl(), false);
            }
        });
    }

    public boolean hasBinded() {
        return this.mHasBinded;
    }

    private View buildReviewItemView(final Review review, Document doc, ViewGroup parent) {
        boolean canGiveFeedback;
        ReviewItemLayout reviewItemLayout = (ReviewItemLayout) this.mLayoutInflater.inflate(R.layout.review_item, parent, false);
        if (TextUtils.isEmpty(review.commentId)) {
            canGiveFeedback = false;
        } else {
            canGiveFeedback = true;
        }
        reviewItemLayout.setReviewInfo(doc, review, 3, this.mNavigationManager, false, this.mParentNode);
        if (canGiveFeedback) {
            reviewItemLayout.setActionClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ReviewsSamplesModuleLayout.this.mReviewSamplesClickListener.onReviewFeedbackClick(review);
                }
            });
        } else {
            reviewItemLayout.setActionClickListener(null);
        }
        return reviewItemLayout;
    }
}
