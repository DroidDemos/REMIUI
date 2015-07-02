package com.google.android.finsky.activities;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.utils.ReviewsSortingUtils;

public class ReviewsSortingDialog extends DialogFragment {

    public interface Listener {
        void onSortingChanged(int i);
    }

    public static ReviewsSortingDialog newInstance(DfeReviews data) {
        ReviewsSortingDialog filterOptionsDialog = new ReviewsSortingDialog();
        Bundle arguments = new Bundle();
        arguments.putInt("sorting_type", ReviewsSortingUtils.convertDataSortTypeToDisplayIndex(data));
        filterOptionsDialog.setArguments(arguments);
        return filterOptionsDialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        Bundle arguments = getArguments();
        Builder builder = new Builder(context);
        builder.setTitle(R.string.sort_reviews);
        builder.setSingleChoiceItems(ReviewsSortingUtils.getAllDisplayStrings(context), arguments.getInt("sorting_type"), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ReviewsSortingDialog.this.dismiss();
                ReviewsSortingDialog.this.getListener().onSortingChanged(ReviewsSortingUtils.convertDisplayIndexToDataSortType(which));
            }
        });
        return builder.create();
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
