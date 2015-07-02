package com.google.android.finsky.detailspage;

import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ReviewFeedbackDialog;
import com.google.android.finsky.activities.ReviewFeedbackDialog.CommentRating;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeRateReview;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.detailspage.ReviewsSamplesModuleLayout.ReviewSamplesClickListener;
import com.google.android.finsky.protos.DocumentV2.Review;

public class ReviewsSamplesModule extends FinskyModule<Data> implements OnDataChangedListener, ReviewSamplesClickListener {
    private boolean mIsDestroyed;
    private boolean mNeedsRefresh;

    protected static class Data extends ModuleData {
        Document detailsDoc;
        DfeReviews dfeReviews;

        protected Data() {
        }
    }

    public int getLayoutResId() {
        return R.layout.reviews_samples_module;
    }

    public boolean readyForDisplay() {
        return (this.mModuleData == null || ((Data) this.mModuleData).dfeReviews == null || !((Data) this.mModuleData).dfeReviews.isReady() || ((Data) this.mModuleData).dfeReviews.getCount() == 0) ? false : true;
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (socialDetailsDoc != null && !TextUtils.isEmpty(socialDetailsDoc.getReviewsUrl()) && this.mModuleData == null) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).detailsDoc = socialDetailsDoc;
            DfeReviews dfeReviews = new DfeReviews(this.mSocialDfeApi, socialDetailsDoc.getReviewsUrl(), detailsDoc.getVersionCode(), false);
            dfeReviews.setSortType(4);
            dfeReviews.addDataChangedListener(this);
            dfeReviews.startLoadItems();
            ((Data) this.mModuleData).dfeReviews = dfeReviews;
        }
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null && ((Data) this.mModuleData).dfeReviews != null && !((Data) this.mModuleData).dfeReviews.isReady()) {
            ((Data) this.mModuleData).dfeReviews.addDataChangedListener(this);
            ((Data) this.mModuleData).dfeReviews.startLoadItems();
        }
    }

    public void onDestroyModule() {
        if (!(this.mModuleData == null || ((Data) this.mModuleData).dfeReviews == null)) {
            ((Data) this.mModuleData).dfeReviews.removeDataChangedListener(this);
        }
        this.mIsDestroyed = true;
    }

    public void onDataChanged() {
        if (((Data) this.mModuleData).dfeReviews.getCount() != 0) {
            this.mFinskyModuleController.refreshModule(this, true);
        }
    }

    public void bindView(View view) {
        ReviewsSamplesModuleLayout reviewsSamplesModuleLayout = (ReviewsSamplesModuleLayout) view;
        if (!reviewsSamplesModuleLayout.hasBinded() || this.mNeedsRefresh) {
            reviewsSamplesModuleLayout.bind(((Data) this.mModuleData).dfeReviews, ((Data) this.mModuleData).detailsDoc, this, this.mNavigationManager, this.mParentNode);
            this.mNeedsRefresh = false;
        }
    }

    public void onReviewFeedbackClick(Review review) {
        FragmentManager fragmentManager = this.mDetailsFragment2.getFragmentManager();
        if (fragmentManager.findFragmentByTag("rate_review_dialog") == null) {
            ReviewFeedbackDialog dialog = ReviewFeedbackDialog.newInstance(((Data) this.mModuleData).detailsDoc.getDocId(), review.commentId, null);
            dialog.setTargetFragment(this.mDetailsFragment2, 0);
            dialog.show(fragmentManager, "rate_review_dialog");
        }
    }

    public void onReviewFeedback(String docId, String reviewId, final CommentRating newRating) {
        DfeRateReview request = new DfeRateReview(FinskyApp.get().getDfeApi(), docId, reviewId, newRating.getRpcId());
        request.addDataChangedListener(new OnDataChangedListener() {
            public void onDataChanged() {
                if (newRating == CommentRating.SPAM) {
                    DfeReviews dfeReviews = ((Data) ReviewsSamplesModule.this.mModuleData).dfeReviews;
                    ReviewsSamplesModule.this.mSocialDfeApi.invalidateReviewsCache(((Data) ReviewsSamplesModule.this.mModuleData).detailsDoc.getReviewsUrl(), dfeReviews.shouldFilterByDevice(), dfeReviews.getVersionFilter(), dfeReviews.getRatingFilter(), dfeReviews.getSortType(), true);
                    dfeReviews = new DfeReviews(ReviewsSamplesModule.this.mSocialDfeApi, ((Data) ReviewsSamplesModule.this.mModuleData).detailsDoc.getReviewsUrl(), ((Data) ReviewsSamplesModule.this.mModuleData).detailsDoc.getVersionCode(), false);
                    dfeReviews.setSortType(4);
                    ((Data) ReviewsSamplesModule.this.mModuleData).dfeReviews = dfeReviews;
                    if (!ReviewsSamplesModule.this.mIsDestroyed) {
                        dfeReviews.addDataChangedListener(ReviewsSamplesModule.this);
                        dfeReviews.startLoadItems();
                        ReviewsSamplesModule.this.mNeedsRefresh = true;
                    }
                }
            }
        });
        request.addErrorListener(new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReviewsSamplesModule.this.mContext, R.string.review_feedback_posted_error, 0).show();
            }
        });
    }
}
