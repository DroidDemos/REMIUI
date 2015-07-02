package com.google.android.finsky.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import com.android.vending.R;

public class ReviewFeedbackDialog extends DialogFragment {
    CommentRating mRating;

    public interface Listener {
        void onReviewFeedback(String str, String str2, CommentRating commentRating);
    }

    public enum CommentRating {
        SPAM(3, 2, R.string.review_feedback_dialog_choice_spam),
        HELPFUL(1, 0, R.string.review_feedback_dialog_choice_helpful),
        NOT_HELPFUL(2, 1, R.string.review_feedback_dialog_choice_unhelpful);
        
        private int mDisplayStringId;
        private int mIndex;
        private int mRpcId;

        public int getRpcId() {
            return this.mRpcId;
        }

        private int getIndex() {
            return this.mIndex;
        }

        private int getTextResourceId() {
            return this.mDisplayStringId;
        }

        private CommentRating(int rpcId, int index, int resId) {
            this.mRpcId = rpcId;
            this.mIndex = index;
            this.mDisplayStringId = resId;
        }
    }

    public ReviewFeedbackDialog() {
        this.mRating = null;
    }

    public static ReviewFeedbackDialog newInstance(String docId, String reviewId, CommentRating initialRating) {
        ReviewFeedbackDialog dialogFragment = new ReviewFeedbackDialog();
        Bundle arguments = new Bundle();
        arguments.putString("doc_id", docId);
        arguments.putString("rating_id", reviewId);
        if (initialRating != null) {
            arguments.putInt("previous_rating", initialRating.getIndex());
        }
        dialogFragment.setArguments(arguments);
        return dialogFragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle source;
        Context context = new ContextThemeWrapper(getActivity(), R.style.FinskyTheme);
        Bundle arguments = getArguments();
        final String reviewId = arguments.getString("rating_id");
        final String docId = arguments.getString("doc_id");
        if (savedInstanceState != null) {
            source = savedInstanceState;
        } else {
            source = arguments;
        }
        int initialRatingIndex = source.getInt("previous_rating", -1);
        this.mRating = getRatingForIndex(initialRatingIndex);
        CharSequence[] choices = new CharSequence[CommentRating.values().length];
        for (CommentRating rating : CommentRating.values()) {
            choices[rating.getIndex()] = context.getString(rating.getTextResourceId());
        }
        Builder builder = new Builder(context);
        builder.setTitle(R.string.review_feedback_dialog_title);
        builder.setCancelable(true);
        builder.setSingleChoiceItems(choices, initialRatingIndex, new OnClickListener() {
            public void onClick(DialogInterface arg0, int newSelection) {
                ReviewFeedbackDialog.this.setRating(newSelection);
                ReviewFeedbackDialog.this.syncOkButtonState();
            }
        });
        builder.setPositiveButton(17039370, new OnClickListener() {
            public void onClick(DialogInterface arg0, int buttonIndex) {
                Listener l = ReviewFeedbackDialog.this.getListener();
                if (l != null && ReviewFeedbackDialog.this.mRating != null) {
                    l.onReviewFeedback(docId, reviewId, ReviewFeedbackDialog.this.mRating);
                }
            }
        });
        builder.setNegativeButton(17039360, null);
        return builder.create();
    }

    private CommentRating getRatingForIndex(int index) {
        for (CommentRating rating : CommentRating.values()) {
            if (rating.getIndex() == index) {
                return rating;
            }
        }
        return null;
    }

    public void onStart() {
        super.onStart();
        syncOkButtonState();
    }

    private void setRating(int index) {
        this.mRating = getRatingForIndex(index);
        syncOkButtonState();
    }

    private void syncOkButtonState() {
        ((AlertDialog) getDialog()).getButton(-1).setEnabled(this.mRating != null);
    }

    public void onSaveInstanceState(Bundle icicle) {
        if (this.mRating != null) {
            icicle.putInt("previous_rating", this.mRating.getIndex());
        }
    }

    private Listener getListener() {
        Fragment f = getTargetFragment();
        if (f instanceof Listener) {
            return (Listener) f;
        }
        Activity a = getActivity();
        if (a instanceof Listener) {
            return (Listener) a;
        }
        return null;
    }
}
