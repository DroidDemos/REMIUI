package com.google.android.wallet.instrumentmanager.ui.common;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;

public class BaseInstrumentManagerDialogFragment extends DialogFragment {
    protected static Bundle createArgs(int themeResourceId) {
        Bundle args = new Bundle();
        args.putInt("themeResourceId", themeResourceId);
        return args;
    }

    protected final ContextThemeWrapper getThemedContext() {
        int themeResourceId = getArguments().getInt("themeResourceId");
        if (themeResourceId > 0) {
            return new ContextThemeWrapper(getActivity(), themeResourceId);
        }
        throw new IllegalArgumentException("Invalid theme resource id: " + themeResourceId);
    }

    protected final LayoutInflater getThemedLayoutInflater() {
        return getActivity().getLayoutInflater().cloneInContext(getThemedContext());
    }
}
