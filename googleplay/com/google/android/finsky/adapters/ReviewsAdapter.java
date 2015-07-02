package com.google.android.finsky.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.HistogramView;
import com.google.android.finsky.layout.ReviewItemLayout;
import com.google.android.finsky.layout.ReviewsControlContainer;
import com.google.android.finsky.layout.RottenTomatoesReviewItem;
import com.google.android.finsky.layout.RottenTomatoesReviewsHeader;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.wallet.instrumentmanager.R;

public class ReviewsAdapter extends PaginatedListAdapter implements ErrorListener {
    private final DfeReviews mData;
    private final Document mDoc;
    private final boolean mIsRottenTomatoesReviews;
    private final LayoutInflater mLayoutInflater;
    private final ChooseListingOptionsHandler mListingOptionsHandler;
    private final NavigationManager mNavigationManager;
    private final PlayStoreUiElementNode mParentNode;
    private final ReviewFeedbackHandler mReviewFeedbackHandler;
    private final int mReviewTextMaxLines;

    public interface ChooseListingOptionsHandler {
        void onChooseFilterOptions();

        void onChooseSortingOptions();
    }

    public interface ReviewFeedbackHandler {
        void onReviewFeedback(Review review);
    }

    public ReviewsAdapter(Context context, Document doc, DfeReviews reviews, boolean isRottenTomatoesReviews, int reviewTextMaxLines, ReviewFeedbackHandler ratingHandler, ChooseListingOptionsHandler listingOptionsHandler, NavigationManager navigationManager, PlayStoreUiElementNode parentNode) {
        super(context, null, reviews.inErrorState(), reviews.isMoreAvailable());
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mDoc = doc;
        this.mData = reviews;
        this.mIsRottenTomatoesReviews = isRottenTomatoesReviews;
        this.mData.addDataChangedListener(this);
        this.mData.addErrorListener(this);
        this.mReviewTextMaxLines = reviewTextMaxLines;
        this.mReviewFeedbackHandler = ratingHandler;
        this.mListingOptionsHandler = listingOptionsHandler;
        this.mNavigationManager = navigationManager;
        this.mParentNode = parentNode;
    }

    public int getCount() {
        int result = this.mData.getCount();
        if (statsVisible()) {
            result++;
        }
        if (filtersVisible()) {
            result++;
        }
        if (rottenTomatoesHeaderVisible()) {
            result++;
        }
        if (getFooterMode() != FooterMode.NONE) {
            return result + 1;
        }
        if (this.mData.getCount() == 0) {
            return result + 1;
        }
        return result;
    }

    public int getViewTypeCount() {
        return 8;
    }

    public int getItemViewType(int position) {
        boolean isLastRow;
        if (position == getCount() - 1) {
            isLastRow = true;
        } else {
            isLastRow = false;
        }
        if (statsVisible()) {
            if (position == 0) {
                return 0;
            }
            position--;
        }
        if (filtersVisible()) {
            if (position == 0) {
                return 1;
            }
            position--;
        }
        if (rottenTomatoesHeaderVisible()) {
            if (position == 0) {
                return 2;
            }
            position--;
        }
        if (this.mData.getCount() == 0) {
            return this.mData.isMoreAvailable() ? 6 : 3;
        } else {
            FooterMode footerMode = getFooterMode();
            if (footerMode == FooterMode.NONE || !isLastRow) {
                if (this.mIsRottenTomatoesReviews) {
                    return 5;
                }
                return 4;
            } else if (footerMode == FooterMode.LOADING) {
                return 6;
            } else {
                if (footerMode == FooterMode.ERROR) {
                    return 7;
                }
                FinskyLog.wtf("No footer or item in last row", new Object[0]);
                return 7;
            }
        }
    }

    public Review getItem(int position) {
        int reviewPosition = position - getFirstReviewViewIndex();
        if (reviewPosition < 0 || reviewPosition >= this.mData.getCount()) {
            return null;
        }
        return (Review) this.mData.getItem(reviewPosition);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return getStatisticsView(convertView, parent);
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return getFiltersView(convertView, parent);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return getRottenTomatoesHeader(convertView, parent);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return getNoMatchingView(convertView, parent);
            case R.styleable.WalletImFormEditText_required /*4*/:
                return getReviewItemView(position, convertView, parent);
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return getRottenTomatoesReview(position, convertView, parent);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return getLoadingFooterView(convertView, parent);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return getErrorFooterView(convertView, parent);
            default:
                throw new IllegalStateException("Unknown type for getView " + getItemViewType(position));
        }
    }

    public int getFirstReviewViewIndex() {
        if (this.mData.getCount() == 0) {
            return -1;
        }
        int firstReviewView = 0;
        if (statsVisible()) {
            firstReviewView = 0 + 1;
        }
        if (filtersVisible()) {
            firstReviewView++;
        }
        if (rottenTomatoesHeaderVisible()) {
            return firstReviewView + 1;
        }
        return firstReviewView;
    }

    private View getStatisticsView(View convertView, ViewGroup parent) {
        HistogramView statsView = convertView == null ? (HistogramView) inflate(com.android.vending.R.layout.reviews_statistics_expanded, parent, false) : (HistogramView) convertView;
        statsView.bind(this.mDoc);
        return statsView;
    }

    private View getFiltersView(View convertView, ViewGroup parent) {
        ReviewsControlContainer filtersView = convertView == null ? (ReviewsControlContainer) inflate(com.android.vending.R.layout.reviews_filters, parent, false) : (ReviewsControlContainer) convertView;
        filtersView.configure(this.mData, this.mListingOptionsHandler);
        return filtersView;
    }

    private View getRottenTomatoesHeader(View convertView, ViewGroup parent) {
        RottenTomatoesReviewsHeader headerView = convertView == null ? (RottenTomatoesReviewsHeader) inflate(com.android.vending.R.layout.rotten_tomatoes_reviews_header, parent, false) : (RottenTomatoesReviewsHeader) convertView;
        FinskyApp finskyApp = FinskyApp.get();
        headerView.bind(this.mData.getRottenTomatoesData(), this.mNavigationManager, finskyApp.getToc(), finskyApp.getBitmapLoader());
        return headerView;
    }

    private View getNoMatchingView(View convertView, ViewGroup parent) {
        if (convertView == null) {
            return inflate(com.android.vending.R.layout.reviews_no_matching, parent, false);
        }
        return convertView;
    }

    private View getReviewItemView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.mLayoutInflater.inflate(com.android.vending.R.layout.review_item, parent, false);
        }
        bindReviewItem(convertView, (ReviewItemLayout) convertView, position);
        return convertView;
    }

    private void bindReviewItem(View view, ReviewItemLayout reviewItemLayout, int position) {
        boolean canGiveFeedback;
        final Review review = getItem(position);
        if (TextUtils.isEmpty(review.commentId)) {
            canGiveFeedback = false;
        } else {
            canGiveFeedback = true;
        }
        reviewItemLayout.setReviewInfo(this.mDoc, review, this.mReviewTextMaxLines, this.mNavigationManager, false, this.mParentNode);
        if (canGiveFeedback) {
            reviewItemLayout.setActionClickListener(new OnClickListener() {
                public void onClick(View v) {
                    ReviewsAdapter.this.mReviewFeedbackHandler.onReviewFeedback(review);
                }
            });
        } else {
            reviewItemLayout.setActionClickListener(null);
        }
    }

    private View getRottenTomatoesReview(int position, View convertView, ViewGroup parent) {
        RottenTomatoesReviewItem reviewView = convertView == null ? (RottenTomatoesReviewItem) inflate(com.android.vending.R.layout.rotten_tomatoes_review_item, parent, false) : (RottenTomatoesReviewItem) convertView;
        reviewView.bind(getItem(position), this.mNavigationManager, FinskyApp.get().getBitmapLoader());
        return reviewView;
    }

    protected String getLastRequestError() {
        return ErrorStrings.get(this.mContext, this.mData.getVolleyError());
    }

    protected boolean isMoreDataAvailable() {
        return this.mData.isMoreAvailable();
    }

    protected void retryLoadingItems() {
        this.mData.retryLoadItems();
    }

    public void onDestroyView() {
        this.mData.removeDataChangedListener(this);
        this.mData.removeErrorListener(this);
    }

    public void onErrorResponse(VolleyError error) {
        triggerFooterErrorMode();
    }

    private boolean statsVisible() {
        return (this.mDoc == null || !this.mDoc.hasReviewHistogramData() || this.mIsRottenTomatoesReviews) ? false : true;
    }

    private boolean filtersVisible() {
        return (this.mDoc == null || this.mDoc.getDocumentType() != 1 || this.mIsRottenTomatoesReviews) ? false : true;
    }

    private boolean rottenTomatoesHeaderVisible() {
        return this.mIsRottenTomatoesReviews && this.mData.getRottenTomatoesData() != null;
    }
}
