package com.google.android.finsky.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.ReviewFeedbackDialog.CommentRating;
import com.google.android.finsky.activities.ReviewFeedbackDialog.Listener;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeRateReview;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.RateReviewSection;
import com.google.android.finsky.layout.ReviewSamplesSection;
import com.google.android.finsky.utils.RateReviewHelper;
import com.google.android.finsky.utils.RateReviewHelper.RateReviewListener;

public class ReviewDialogListener implements Listener {
    private final String mAccountName;
    private final Context mContext;
    private final Document mDoc;
    private final RateReviewSection mRateReviewSection;
    private final ReviewSamplesSection mReviewSamplesSection;

    public ReviewDialogListener(Context context, Fragment parentFragment, String accountName, Document document, DfeDetails detailsData, ReviewSamplesSection reviewSamplesSection, RateReviewSection rateReviewSection) {
        this.mContext = context;
        this.mAccountName = accountName;
        this.mDoc = document;
        this.mReviewSamplesSection = reviewSamplesSection;
        this.mRateReviewSection = rateReviewSection;
    }

    public void onSaveReview(String docId, int rating, String title, String comment, Document author) {
        RateReviewHelper.updateReview(this.mAccountName, this.mDoc.getDocId(), this.mDoc.getDetailsUrl(), rating, title, comment, author, this.mContext, new RateReviewListener() {
            public void onRateReviewCommitted(int rating, String title, String content) {
                ReviewDialogListener.this.refreshUserReview();
            }

            public void onRateReviewFailed() {
                ReviewDialogListener.this.refreshUserReview();
            }
        }, 1203);
    }

    public void onDeleteReview(String docId) {
        RateReviewHelper.deleteReview(this.mAccountName, this.mDoc.getDocId(), this.mDoc.getDetailsUrl(), this.mContext, new RateReviewListener() {
            public void onRateReviewCommitted(int rating, String title, String content) {
                ReviewDialogListener.this.refreshUserReview();
            }

            public void onRateReviewFailed() {
                ReviewDialogListener.this.refreshUserReview();
            }
        });
    }

    public void onCancelReview() {
        refreshUserReview();
    }

    private void refreshUserReview() {
        if (this.mDoc != null) {
            this.mReviewSamplesSection.invalidateCurrentReviewUrl();
            this.mReviewSamplesSection.refresh();
            if (this.mRateReviewSection != null) {
                this.mRateReviewSection.refresh();
            }
        }
    }

    public void onReviewFeedback(String docId, String reviewId, final CommentRating newRating) {
        DfeRateReview request = new DfeRateReview(FinskyApp.get().getDfeApi(), docId, reviewId, newRating.getRpcId());
        request.addDataChangedListener(new OnDataChangedListener() {
            public void onDataChanged() {
                if (newRating == CommentRating.SPAM) {
                    ReviewDialogListener.this.mReviewSamplesSection.invalidateCurrentReviewUrl();
                    ReviewDialogListener.this.mReviewSamplesSection.refresh();
                }
            }
        });
        request.addErrorListener(new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                ReviewDialogListener.this.toast(R.string.review_feedback_posted_error, 0);
            }
        });
    }

    protected void toast(int resourceId, int duration) {
        Toast.makeText(this.mContext, resourceId, duration).show();
    }
}
