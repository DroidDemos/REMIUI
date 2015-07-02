package com.google.android.libraries.bind.data;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.libraries.bind.view.ViewHeap;
import com.google.android.libraries.bind.widget.LoadingView;

public interface ViewProvider {
    public static final ViewProvider DEFAULT_EMPTY_VIEW_PROVIDER;
    public static final ViewProvider DEFAULT_ERROR_VIEW_PROVIDER;
    public static final ViewProvider DEFAULT_LOADING_VIEW_PROVIDER;

    View getView(ViewGroup viewGroup, ViewHeap viewHeap);

    static {
        DEFAULT_EMPTY_VIEW_PROVIDER = new ViewProvider() {
            public View getView(ViewGroup parent, ViewHeap viewHeap) {
                return new View(parent.getContext());
            }
        };
        DEFAULT_LOADING_VIEW_PROVIDER = new ViewProvider() {
            public View getView(ViewGroup parent, ViewHeap viewHeap) {
                return new LoadingView(parent.getContext());
            }
        };
        DEFAULT_ERROR_VIEW_PROVIDER = new ViewProvider() {
            public View getView(ViewGroup parent, ViewHeap viewHeap) {
                TextView errorView = new TextView(parent.getContext());
                errorView.setText("Error! (replace me)");
                return errorView;
            }
        };
    }
}
