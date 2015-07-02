package com.google.android.finsky.activities;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import com.android.vending.R;

public class ReviewsFilterOptionsDialog extends DialogFragment {
    private final boolean[] mChecked;

    public interface Listener {
        void onReviewFilterChanged(boolean z, boolean z2);
    }

    public ReviewsFilterOptionsDialog() {
        this.mChecked = new boolean[2];
    }

    public static ReviewsFilterOptionsDialog newInstance(boolean filterByVersion, boolean filterByDevice) {
        ReviewsFilterOptionsDialog filterOptionsDialog = new ReviewsFilterOptionsDialog();
        Bundle arguments = new Bundle();
        arguments.putBoolean("filterByVersion", filterByVersion);
        arguments.putBoolean("filterByDevice", filterByDevice);
        filterOptionsDialog.setArguments(arguments);
        return filterOptionsDialog;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();
        Bundle arguments = getArguments();
        Builder builder = new Builder(context);
        builder.setTitle(R.string.reviews_filter_options);
        String[] filters = new String[this.mChecked.length];
        filters[0] = context.getString(R.string.reviews_filter_by_version);
        filters[1] = context.getString(R.string.reviews_filter_by_device_model);
        this.mChecked[0] = arguments.getBoolean("filterByVersion");
        this.mChecked[1] = arguments.getBoolean("filterByDevice");
        builder.setMultiChoiceItems(filters, this.mChecked, new OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                ReviewsFilterOptionsDialog.this.mChecked[which] = isChecked;
            }
        });
        builder.setPositiveButton(17039370, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Listener l = ReviewsFilterOptionsDialog.this.getListener();
                if (l != null) {
                    l.onReviewFilterChanged(ReviewsFilterOptionsDialog.this.mChecked[0], ReviewsFilterOptionsDialog.this.mChecked[1]);
                }
                ReviewsFilterOptionsDialog.this.dismiss();
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
