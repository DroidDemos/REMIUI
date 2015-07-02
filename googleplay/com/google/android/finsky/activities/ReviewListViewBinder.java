package com.google.android.finsky.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.adapters.ReviewsAdapter;
import com.google.android.finsky.adapters.ReviewsAdapter.ChooseListingOptionsHandler;
import com.google.android.finsky.adapters.ReviewsAdapter.ReviewFeedbackHandler;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.ViewBinder;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.play.image.BitmapLoader;

public class ReviewListViewBinder extends ViewBinder<DfeReviews> implements ErrorListener, ChooseListingOptionsHandler, ReviewFeedbackHandler, OnDataChangedListener {
    private ReviewsAdapter mAdapter;
    private Fragment mContainerFragment;
    private ViewGroup mContentLayout;
    private Document mDocument;
    private boolean mHasLoadedAtLeastOnce;
    private boolean mIsRottenTomatoesReviews;
    private PlayStoreUiElementNode mParentNode;
    private ListView mReviewList;

    public void init(Context context, Fragment containerFragment, DfeApi dfeApi, NavigationManager nm, BitmapLoader bitmapLoader, PlayStoreUiElementNode parentNode) {
        DfeReviews currentData = this.mData;
        super.init(context, nm, bitmapLoader);
        this.mData = currentData;
        this.mContainerFragment = containerFragment;
        this.mParentNode = parentNode;
    }

    public void bind(View reviewsView, Document document, boolean isRottenTomatoesReviews) {
        this.mContentLayout = (ViewGroup) reviewsView;
        this.mReviewList = (ListView) this.mContentLayout.findViewById(R.id.all_reviews_list);
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
        }
        this.mDocument = document;
        this.mIsRottenTomatoesReviews = isRottenTomatoesReviews;
        this.mAdapter = new ReviewsAdapter(this.mContext, this.mDocument, (DfeReviews) this.mData, this.mIsRottenTomatoesReviews, Integer.MAX_VALUE, this, this, this.mNavManager, this.mParentNode);
        if (this.mHasLoadedAtLeastOnce) {
            this.mReviewList.setEmptyView(null);
        } else {
            this.mReviewList.setEmptyView(this.mContentLayout.findViewById(R.id.no_results_view));
        }
        this.mReviewList.setItemsCanFocus(true);
        this.mReviewList.setAdapter(this.mAdapter);
    }

    public void onDataChanged() {
        if (this.mReviewList != null && this.mData != null) {
            if (this.mHasLoadedAtLeastOnce) {
                this.mAdapter.notifyDataSetChanged();
                return;
            }
            this.mReviewList.setEmptyView(this.mContentLayout.findViewById(R.id.no_results_view));
            this.mHasLoadedAtLeastOnce = true;
        }
    }

    public void setData(DfeReviews data) {
        this.mHasLoadedAtLeastOnce = false;
        if (this.mData != null) {
            ((DfeReviews) this.mData).removeDataChangedListener(this);
            ((DfeReviews) this.mData).removeErrorListener(this);
        }
        super.setData(data);
        ((DfeReviews) this.mData).clearTransientState();
        ((DfeReviews) this.mData).addDataChangedListener(this);
        ((DfeReviews) this.mData).addErrorListener(this);
    }

    public void onErrorResponse(VolleyError error) {
        if (this.mReviewList != null) {
            this.mAdapter.triggerFooterErrorMode();
        }
    }

    public void onDestroyView() {
        if (this.mAdapter != null) {
            this.mAdapter.onDestroyView();
            this.mAdapter = null;
        }
        this.mReviewList = null;
        if (this.mData != null) {
            ((DfeReviews) this.mData).removeDataChangedListener(this);
            ((DfeReviews) this.mData).removeErrorListener(this);
            this.mData = null;
        }
    }

    public void onChooseFilterOptions() {
        if (this.mData != null) {
            FragmentManager fragmentManager = this.mContainerFragment.getFragmentManager();
            if (fragmentManager.findFragmentByTag("filter_options_dialog") == null) {
                ReviewsFilterOptionsDialog dialog = ReviewsFilterOptionsDialog.newInstance(((DfeReviews) this.mData).currentlyFilteringByVersion(), ((DfeReviews) this.mData).shouldFilterByDevice());
                dialog.setTargetFragment(this.mContainerFragment, 0);
                dialog.show(fragmentManager, "filter_options_dialog");
            }
        }
    }

    public void onChooseSortingOptions() {
        if (this.mData != null) {
            FragmentManager fragmentManager = this.mContainerFragment.getFragmentManager();
            if (fragmentManager.findFragmentByTag("sorting_dialog") == null) {
                ReviewsSortingDialog dialog = ReviewsSortingDialog.newInstance((DfeReviews) this.mData);
                dialog.setTargetFragment(this.mContainerFragment, 0);
                dialog.show(fragmentManager, "sorting_dialog");
            }
        }
    }

    public void onReviewFeedback(Review review) {
        FragmentManager fragmentManager = this.mContainerFragment.getFragmentManager();
        if (fragmentManager.findFragmentByTag("rate_review_dialog") == null) {
            ReviewFeedbackDialog dialog = ReviewFeedbackDialog.newInstance(this.mDocument.getDocId(), review.commentId, null);
            dialog.setTargetFragment(this.mContainerFragment, 0);
            dialog.show(fragmentManager, "rate_review_dialog");
        }
    }
}
