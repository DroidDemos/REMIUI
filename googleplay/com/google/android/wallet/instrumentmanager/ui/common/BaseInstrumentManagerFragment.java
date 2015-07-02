package com.google.android.wallet.instrumentmanager.ui.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseInstrumentManagerFragment extends Fragment {
    private int mThemeResourceId;
    private ContextThemeWrapper mThemedContext;
    private LayoutInflater mThemedInflater;

    protected abstract View onCreateThemedView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    protected static Bundle createArgs(int themeResourceId) {
        Bundle args = new Bundle();
        args.putInt("themeResourceId", themeResourceId);
        return args;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mThemeResourceId = getArguments().getInt("themeResourceId");
        if (this.mThemeResourceId <= 0) {
            throw new IllegalArgumentException("Invalid theme resource id: " + this.mThemeResourceId);
        }
        this.mThemedContext = new ContextThemeWrapper(getActivity(), this.mThemeResourceId);
    }

    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mThemedInflater = inflater.cloneInContext(this.mThemedContext);
        return onCreateThemedView(this.mThemedInflater, container, savedInstanceState);
    }

    protected int getThemeResourceId() {
        return this.mThemeResourceId;
    }

    protected ContextThemeWrapper getThemedContext() {
        return this.mThemedContext;
    }

    protected LayoutInflater getThemedLayoutInflater() {
        return this.mThemedInflater;
    }
}
