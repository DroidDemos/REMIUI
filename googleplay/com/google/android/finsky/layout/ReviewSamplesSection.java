package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.ReviewFeedbackDialog;
import com.google.android.finsky.adapters.ReviewsAdapter;
import com.google.android.finsky.adapters.ReviewsAdapter.ReviewFeedbackHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.IntMath;
import com.google.android.play.utils.PlayCorpusUtils;

public class ReviewSamplesSection extends ForegroundLinearLayout implements ReviewFeedbackHandler, OnDataChangedListener {
    private ReviewsAdapter mAdapter;
    private TextView mAllReviewsFooter;
    private Fragment mContainerFragment;
    private DfeReviews mData;
    protected DfeApi mDfeApi;
    private Document mDoc;
    protected NavigationManager mNavigationManager;
    private PlayStoreUiElementNode mParentNode;
    private LinearLayout mReviewHolder;
    private HistogramView mReviewStats;
    private final int mReviewsMaxRows;
    private final int mReviewsPerRow;

    public ReviewSamplesSection(Context context) {
        this(context, null);
    }

    public ReviewSamplesSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        this.mReviewsPerRow = res.getInteger(R.integer.sample_reviews_per_row);
        this.mReviewsMaxRows = res.getInteger(R.integer.sample_reviews_max_rows);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mReviewStats = (HistogramView) findViewById(R.id.reviews_statistics_panel);
        this.mReviewHolder = (LinearLayout) findViewById(R.id.section_content_sample_reviews);
        this.mAllReviewsFooter = (TextView) findViewById(R.id.all_reviews_footer);
    }

    public void bind(DfeApi dfeApi, Document doc, boolean hasDetailsLoaded, Fragment containerFragment, NavigationManager navManager, PlayStoreUiElementNode parentNode) {
        if (!hasDetailsLoaded) {
            setVisibility(8);
        } else if (doc != null && !TextUtils.isEmpty(doc.getReviewsUrl())) {
            this.mContainerFragment = containerFragment;
            this.mParentNode = parentNode;
            this.mNavigationManager = navManager;
            this.mDoc = doc;
            this.mDfeApi = dfeApi;
            this.mReviewStats.bind(this.mDoc);
            View reviewSummaryBox = this.mReviewStats.getSummaryBox();
            if (this.mReviewStats.getVisibility() == 0 && reviewSummaryBox != null && reviewSummaryBox.getVisibility() == 0) {
                setNextFocusDownId(reviewSummaryBox.getId());
                reviewSummaryBox.setNextFocusUpId(getId());
            }
            refresh();
        }
    }

    public void refresh() {
        if (this.mDoc != null) {
            if (this.mData != null) {
                this.mData.removeDataChangedListener(this);
            }
            this.mData = new DfeReviews(this.mDfeApi, this.mDoc.getReviewsUrl(), this.mDoc.getVersionCode(), false);
            this.mData.setSortType(4);
            this.mAdapter = new ReviewsAdapter(getContext(), this.mDoc, this.mData, false, 3, this, null, this.mNavigationManager, this.mParentNode);
            this.mData.addDataChangedListener(this);
            this.mData.startLoadItems();
        }
    }

    public void invalidateCurrentReviewUrl() {
        if (this.mData != null) {
            this.mDfeApi.invalidateReviewsCache(this.mDoc.getReviewsUrl(), this.mData.shouldFilterByDevice(), this.mData.getVersionFilter(), this.mData.getRatingFilter(), this.mData.getSortType(), true);
        }
    }

    public void onDataChanged() {
        int reviewCount = this.mData.getCount();
        if (reviewCount == 0) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.mReviewHolder.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        int firstReviewViewIndex = this.mAdapter.getFirstReviewViewIndex();
        int maxReviewsToShow = this.mReviewsPerRow * this.mReviewsMaxRows;
        int reviewsToShow = Math.min(maxReviewsToShow, reviewCount);
        int reviewRows = IntMath.ceil(reviewsToShow, this.mReviewsPerRow);
        for (int rowIndex = 0; rowIndex < reviewRows; rowIndex++) {
            BucketRow row = (BucketRow) inflater.inflate(R.layout.review_samples_row, this.mReviewHolder, false);
            row.setSameChildHeight(true);
            for (int colIndex = 0; colIndex < this.mReviewsPerRow; colIndex++) {
                int reviewIndex = (this.mReviewsPerRow * rowIndex) + colIndex;
                if (reviewIndex < reviewsToShow) {
                    row.addView(this.mAdapter.getView(reviewIndex + firstReviewViewIndex, null, row));
                } else {
                    View dummy = new View(getContext());
                    dummy.setVisibility(4);
                    row.addView(dummy);
                }
            }
            this.mReviewHolder.addView(row);
        }
        if (reviewCount > maxReviewsToShow) {
            Context context = getContext();
            this.mAllReviewsFooter.setVisibility(0);
            this.mAllReviewsFooter.setText(context.getString(R.string.all_reviews).toUpperCase());
            this.mAllReviewsFooter.setTextColor(PlayCorpusUtils.getPrimaryTextColor(context, this.mDoc.getBackend()));
        } else {
            this.mAllReviewsFooter.setVisibility(8);
        }
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ReviewSamplesSection.this.mNavigationManager.goToAllReviews(ReviewSamplesSection.this.mDoc, ReviewSamplesSection.this.mDoc.getReviewsUrl(), false);
            }
        });
    }

    protected void onDetachedFromWindow() {
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter = null;
        }
        if (this.mData != null) {
            this.mData.removeDataChangedListener(this);
            this.mData = null;
        }
        super.onDetachedFromWindow();
    }

    public void onReviewFeedback(Review review) {
        FragmentManager fragmentManager = this.mContainerFragment.getFragmentManager();
        if (fragmentManager.findFragmentByTag("rate_review_dialog") == null) {
            ReviewFeedbackDialog dialog = ReviewFeedbackDialog.newInstance(this.mDoc.getDocId(), review.commentId, null);
            dialog.setTargetFragment(this.mContainerFragment, 0);
            dialog.show(fragmentManager, "rate_review_dialog");
        }
    }
}
