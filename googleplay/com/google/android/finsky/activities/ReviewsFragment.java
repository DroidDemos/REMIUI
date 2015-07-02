package com.google.android.finsky.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ReviewFeedbackDialog.CommentRating;
import com.google.android.finsky.activities.ReviewFeedbackDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeRateReview;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.PageFragment;

public class ReviewsFragment extends PageFragment implements Listener, ReviewsFilterOptionsDialog.Listener, ReviewsSortingDialog.Listener {
    protected DfeDetails mDfeDetails;
    protected Document mDocument;
    private boolean mFilterByDevice;
    private boolean mFilterByVersion;
    private boolean mIsRottenTomatoesReviews;
    protected final ReviewListViewBinder mReviewsBinder;
    private DfeReviews mReviewsData;
    private String mReviewsUrl;
    private Bundle mSavedInstanceState;
    private int mSortType;
    private PlayStoreUiElement mUiElementProto;

    public ReviewsFragment() {
        this.mReviewsBinder = new ReviewListViewBinder();
        this.mSavedInstanceState = new Bundle();
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(302);
    }

    public static ReviewsFragment newInstance(Document document, String reviewsUrlOverride, boolean isRottenTomatoesReviews) {
        String reviewsUrl;
        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setTheme(R.style.LightDialogWhenLarge);
        fragment.setDfeToc(FinskyApp.get().getToc());
        fragment.setArgument("finsky.ReviewsFragment.document", (Parcelable) document);
        if (reviewsUrlOverride == null) {
            reviewsUrl = document.getReviewsUrl();
        } else {
            reviewsUrl = reviewsUrlOverride;
        }
        fragment.setArgument("finsky.ReviewsFragment.reviewsUrl", reviewsUrl);
        fragment.setArgument("finsky.ReviewsFragment.isRottenTomatoesReviews", isRottenTomatoesReviews);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDocument = (Document) getArguments().getParcelable("finsky.ReviewsFragment.document");
        this.mReviewsUrl = getArguments().getString("finsky.ReviewsFragment.reviewsUrl");
        this.mIsRottenTomatoesReviews = getArguments().getBoolean("finsky.ReviewsFragment.isRottenTomatoesReviews");
        setRetainInstance(true);
    }

    protected int getLayoutRes() {
        return R.layout.generic_reviews;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && this.mSavedInstanceState.isEmpty()) {
            this.mSavedInstanceState = savedInstanceState;
        }
        int defaultSortType = this.mIsRottenTomatoesReviews ? -1 : 4;
        this.mFilterByDevice = this.mSavedInstanceState.getBoolean("finsky.PageFragment.ReviewsFragment.filterByDevice", false);
        this.mFilterByVersion = this.mSavedInstanceState.getBoolean("finsky.PageFragment.ReviewsFragment.filterByVersion", false);
        this.mSortType = this.mSavedInstanceState.getInt("finsky.PageFragment.ReviewsFragment.sortType", defaultSortType);
        this.mDfeDetails = new DfeDetails(this.mDfeApi, this.mDocument.getDetailsUrl());
        if (this.mReviewsData == null) {
            this.mReviewsData = new DfeReviews(this.mDfeApi, this.mReviewsUrl, this.mDocument.getVersionCode(), true);
            this.mReviewsData.addDataChangedListener(this);
            this.mReviewsData.addErrorListener(this);
        }
        this.mReviewsData.setFilters(this.mFilterByVersion, this.mFilterByDevice);
        this.mReviewsData.setSortType(this.mSortType);
        if (!isDataReady()) {
            switchToLoading();
            requestData();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        recordState();
        bundle.putAll(this.mSavedInstanceState);
        super.onSaveInstanceState(bundle);
    }

    private void recordState() {
        if (isDataReady()) {
            this.mSavedInstanceState.putBoolean("finsky.PageFragment.ReviewsFragment.filterByDevice", this.mFilterByDevice);
            this.mSavedInstanceState.putBoolean("finsky.PageFragment.ReviewsFragment.filterByVersion", this.mFilterByVersion);
            if (this.mReviewsData != null) {
                this.mSavedInstanceState.putInt("finsky.PageFragment.ReviewsFragment.sortType", this.mReviewsData.getSortType());
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = super.onCreateView(inflater, container, savedInstanceState);
        if (isDataReady()) {
            onDataChanged();
        }
        return result;
    }

    public boolean isDataReady() {
        return this.mReviewsData != null && this.mReviewsData.isReady();
    }

    protected void onInitViewBinders() {
        this.mReviewsBinder.init(this.mContext, this, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, this);
    }

    protected void rebindViews() {
        rebindActionBar();
        this.mReviewsBinder.bind(this.mDataView, this.mDocument, this.mIsRottenTomatoesReviews);
    }

    public void rebindActionBar() {
        this.mPageFragmentHost.updateCurrentBackendId(this.mDocument.getBackend(), false);
        this.mPageFragmentHost.updateBreadcrumb(this.mDocument.getTitle());
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    protected void requestData() {
        this.mReviewsData.startLoadItems();
    }

    public void onDataChanged() {
        FinskyEventLog.setServerLogCookie(this.mUiElementProto, this.mDocument.getServerLogsCookie());
        this.mReviewsData.removeDataChangedListener(this);
        this.mReviewsData.removeErrorListener(this);
        this.mReviewsBinder.setData(this.mReviewsData);
        super.onDataChanged();
    }

    public void onDestroyView() {
        this.mReviewsBinder.onDestroyView();
        if (this.mReviewsData != null) {
            this.mReviewsData.removeDataChangedListener(this);
            this.mReviewsData.removeErrorListener(this);
        }
        super.onDestroyView();
    }

    public void onReviewFilterChanged(boolean filterByVersion, boolean filterByDevice) {
        this.mFilterByVersion = filterByVersion;
        this.mFilterByDevice = filterByDevice;
        this.mReviewsData.setFilters(filterByVersion, filterByDevice);
        this.mReviewsData.refetchReviews();
    }

    public void onSortingChanged(int dataSortType) {
        this.mReviewsData.setSortType(dataSortType);
        this.mReviewsData.refetchReviews();
    }

    public void onReviewFeedback(String docId, String reviewId, final CommentRating newRating) {
        DfeRateReview request = new DfeRateReview(FinskyApp.get().getDfeApi(), docId, reviewId, newRating.getRpcId());
        request.addDataChangedListener(new OnDataChangedListener() {
            public void onDataChanged() {
                if (newRating == CommentRating.SPAM) {
                    ReviewsFragment.this.reloadReviews();
                }
            }
        });
        request.addErrorListener(new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                ReviewsFragment.this.toast(R.string.review_feedback_posted_error, 0);
            }
        });
    }

    private void reloadReviews() {
        FinskyApp.get().getDfeApi().invalidateReviewsCache(this.mReviewsUrl, this.mReviewsData.shouldFilterByDevice(), this.mReviewsData.getVersionFilter(), this.mReviewsData.getRatingFilter(), this.mReviewsData.getSortType(), true);
        this.mReviewsData.resetItems();
        switchToLoading();
        this.mReviewsData.addDataChangedListener(this);
        this.mReviewsData.addErrorListener(this);
        requestData();
    }

    protected void toast(int resourceId, int duration) {
        Toast.makeText(this.mContext, resourceId, duration).show();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }
}
