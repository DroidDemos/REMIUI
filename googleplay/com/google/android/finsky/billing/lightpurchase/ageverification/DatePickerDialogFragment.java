package com.google.android.finsky.billing.lightpurchase.ageverification;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment {
    private DatePickerDialog mDatePickerDialog;

    public static DatePickerDialogFragment newInstance(Calendar calendar) {
        if (calendar == null) {
            throw new IllegalStateException("Calendar is not set");
        }
        DatePickerDialogFragment fragment = new DatePickerDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("DatePickerDialogFragment.calendar", calendar);
        fragment.setArguments(bundle);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        OnDateSetListener constructorListener;
        final OnDateSetListener listener = getListener();
        if (VERSION.SDK_INT >= 16) {
            constructorListener = null;
        } else {
            constructorListener = listener;
        }
        if (savedInstanceState == null) {
            Calendar calendar = (Calendar) getArguments().getSerializable("DatePickerDialogFragment.calendar");
            this.mDatePickerDialog = new DatePickerDialog(getActivity(), constructorListener, calendar.get(1), calendar.get(2), calendar.get(5));
        } else {
            this.mDatePickerDialog = new DatePickerDialog(getActivity(), constructorListener, 0, 0, 0);
            this.mDatePickerDialog.onRestoreInstanceState(savedInstanceState.getBundle("DatePickerDialogFragment.calendar"));
        }
        if (VERSION.SDK_INT >= 16) {
            this.mDatePickerDialog.setButton(-1, getActivity().getString(17039370), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null) {
                        DatePicker dp = DatePickerDialogFragment.this.mDatePickerDialog.getDatePicker();
                        listener.onDateSet(dp, dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
                        return;
                    }
                    FinskyLog.wtf("No listener found.", new Object[0]);
                }
            });
            this.mDatePickerDialog.setButton(-2, getActivity().getString(17039360), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
        return this.mDatePickerDialog;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("DatePickerDialogFragment.calendar", this.mDatePickerDialog.onSaveInstanceState());
    }

    public void setTargetFragment(Fragment targetFragment) {
        setTargetFragment(targetFragment, 0);
    }

    private OnDateSetListener getListener() {
        Fragment fragment = getTargetFragment();
        if (fragment instanceof OnDateSetListener) {
            return (OnDateSetListener) fragment;
        }
        Activity activity = getActivity();
        if (activity instanceof OnDateSetListener) {
            return (OnDateSetListener) activity;
        }
        return null;
    }
}
