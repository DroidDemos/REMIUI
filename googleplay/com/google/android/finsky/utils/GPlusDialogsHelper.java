package com.google.android.finsky.utils;

import android.support.v4.app.FragmentManager;
import android.widget.Toast;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;

public class GPlusDialogsHelper {

    public static class GPlusSignUpAndPublicReviewsDialog extends SimpleAlertDialog {
        public static GPlusSignUpAndPublicReviewsDialog newInstance() {
            GPlusSignUpAndPublicReviewsDialog dialog = new GPlusSignUpAndPublicReviewsDialog();
            Builder builder = new Builder();
            builder.setMessageId(R.string.gplus_public_reviews).setPositiveId(R.string.join_google_plus).setNegativeId(R.string.cancel);
            builder.configureDialog(dialog);
            return dialog;
        }

        protected void onPositiveClick() {
            super.onPositiveClick();
            GPlusUtils.launchGPlusSignUp(getActivity());
            FinskyPreferences.acceptedPlusReviews.get(FinskyApp.get().getCurrentAccountName()).put(Boolean.valueOf(true));
        }
    }

    public static class GPlusSignUpDialog extends SimpleAlertDialog {
        public static GPlusSignUpDialog newInstance() {
            GPlusSignUpDialog dialog = new GPlusSignUpDialog();
            Builder builder = new Builder();
            builder.setMessageId(R.string.gplus_sign_up_prompt).setPositiveId(R.string.join_google_plus).setNegativeId(R.string.cancel);
            builder.configureDialog(dialog);
            return dialog;
        }

        protected void onPositiveClick() {
            super.onPositiveClick();
            GPlusUtils.launchGPlusSignUp(getActivity());
        }
    }

    public static class PublicReviewsDialog extends SimpleAlertDialog {
        public static PublicReviewsDialog newInstance() {
            PublicReviewsDialog dialog = new PublicReviewsDialog();
            Builder builder = new Builder();
            builder.setMessageId(R.string.gplus_public_reviews).setPositiveId(R.string.ok).setNegativeId(R.string.cancel);
            builder.configureDialog(dialog);
            return dialog;
        }

        protected void onPositiveClick() {
            super.onPositiveClick();
            Toast.makeText(getActivity(), R.string.gplus_public_reviews_confirmed, 0).show();
            FinskyPreferences.acceptedPlusReviews.get(FinskyApp.get().getCurrentAccountName()).put(Boolean.valueOf(true));
        }
    }

    public static void showGPlusSignUpDialog(FragmentManager fm) {
        if (FinskyApp.get().getToc().isGplusSignupEnabled()) {
            GPlusSignUpDialog.newInstance().show(fm, null);
            return;
        }
        Toast.makeText(FinskyApp.get(), FinskyApp.get().getResources().getText(R.string.google_plus_disabled_error), 0).show();
    }

    public static void showGPlusSignUpAndPublicReviewsDialog(FragmentManager fm) {
        if (FinskyApp.get().getToc().isGplusSignupEnabled()) {
            GPlusSignUpAndPublicReviewsDialog.newInstance().show(fm, null);
            return;
        }
        Toast.makeText(FinskyApp.get(), FinskyApp.get().getResources().getText(R.string.google_plus_disabled_error), 0).show();
    }

    public static boolean showPublicReviewsDialog(FragmentManager fm) {
        if (((Boolean) FinskyPreferences.acceptedPlusReviews.get(FinskyApp.get().getCurrentAccountName()).get()).booleanValue()) {
            return false;
        }
        PublicReviewsDialog.newInstance().show(fm, null);
        return true;
    }
}
