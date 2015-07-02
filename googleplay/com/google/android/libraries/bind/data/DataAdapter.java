package com.google.android.libraries.bind.data;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import com.google.android.libraries.bind.R;
import com.google.android.libraries.bind.util.StringUtil;
import com.google.android.libraries.bind.view.ViewHeap;

public abstract class DataAdapter implements ListAdapter, SpinnerAdapter {
    protected DataList dataList;
    private boolean dataListRegistered;
    protected ViewProvider emptyViewProvider;
    protected ViewProvider errorViewProvider;
    protected ViewProvider loadingViewProvider;
    private final PriorityDataSetObservable observable;
    private final DataObserver observer;
    private boolean supportsEmptyView;
    private boolean supportsErrorView;
    private boolean supportsLoadingView;
    protected final ViewHeap viewHeap;

    public abstract View getView(int i, View view, ViewGroup viewGroup, Data data);

    public static long objectToLong(Object value) {
        if (value instanceof Long) {
            return ((Long) value).longValue();
        }
        if (value instanceof Integer) {
            return (long) ((Integer) value).intValue();
        }
        if (value != null) {
            return StringUtil.getLongHash(value.toString());
        }
        return 0;
    }

    public LayoutParams makeLayoutParams() {
        return new AbsListView.LayoutParams(-1, -2);
    }

    protected void validateLayoutParams(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null && !(layoutParams instanceof AbsListView.LayoutParams)) {
            LayoutParams layoutParams2 = new AbsListView.LayoutParams(layoutParams);
            view.setLayoutParams(layoutParams2);
            layoutParams = layoutParams2;
        }
    }

    private void updateDataListRegistration() {
        if (this.dataList == null || this.observable.size() == 0) {
            if (this.dataListRegistered) {
                this.dataList.unregisterDataObserver(this.observer);
                this.dataListRegistered = false;
            }
        } else if (!this.dataListRegistered) {
            this.dataList.registerDataObserver(this.observer);
            this.dataListRegistered = true;
        }
    }

    protected boolean showLoadingView() {
        return this.supportsLoadingView && isLoading();
    }

    protected boolean showEmptyView() {
        return this.supportsEmptyView && !((this.dataList != null && !this.dataList.isEmpty()) || showLoadingView() || showErrorView());
    }

    protected boolean showErrorView() {
        return this.supportsErrorView && this.dataList != null && this.dataList.didLastRefreshFail();
    }

    protected boolean showOutOfBandView() {
        return showLoadingView() || showEmptyView() || showErrorView();
    }

    protected boolean isLoading() {
        return (this.dataList == null || !hasRefreshedOnce()) && !showErrorView();
    }

    public int getCount() {
        if (showOutOfBandView()) {
            return 1;
        }
        return this.dataList == null ? 0 : this.dataList.getCount();
    }

    public Data getItem(int position) {
        return showOutOfBandView() ? null : this.dataList.getData(position);
    }

    public long getItemId(int position) {
        if (showLoadingView()) {
            return Long.MAX_VALUE;
        }
        if (showEmptyView()) {
            return 9223372036854775806L;
        }
        if (showErrorView()) {
            return 9223372036854775805L;
        }
        return objectToLong(this.dataList.getItemId(position));
    }

    public int getItemViewType(int position) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEmpty() {
        if (showOutOfBandView()) {
            return false;
        }
        return this.dataList == null || this.dataList.isEmpty();
    }

    public boolean hasRefreshedOnce() {
        return this.dataList == null ? false : this.dataList.hasRefreshedOnce();
    }

    public void registerDataSetObserver(DataSetObserver observer, int priority) {
        this.observable.add(observer, priority);
        updateDataListRegistration();
    }

    public void registerDataSetObserver(DataSetObserver observer) {
        registerDataSetObserver(observer, 0);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        this.observable.remove(observer);
        updateDataListRegistration();
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    public boolean isEnabled(int position) {
        return true;
    }

    public final View getView(int position, View convertView, ViewGroup parent) {
        if (showLoadingView()) {
            return getLoadingView(parent);
        }
        if (showEmptyView()) {
            return getEmptyView(parent);
        }
        if (showErrorView()) {
            return getErrorView(parent);
        }
        if (this.dataList.isInvalidPosition(position)) {
            return null;
        }
        View view = getView(position, convertView, parent, this.dataList.getData(position));
        validateLayoutParams(view);
        return view;
    }

    protected View getLoadingView(ViewGroup parent) {
        View loadingView = this.loadingViewProvider.getView(parent, this.viewHeap);
        loadingView.setLayoutParams(getFullScreenLayoutParams(parent));
        return loadingView;
    }

    private View getEmptyView(ViewGroup parent) {
        View emptyView = this.emptyViewProvider.getView(parent, this.viewHeap);
        emptyView.setLayoutParams(getFullScreenLayoutParams(parent));
        return emptyView;
    }

    private View getErrorView(ViewGroup parent) {
        return this.errorViewProvider.getView(parent, this.viewHeap);
    }

    public static LayoutParams getFullScreenLayoutParams(ViewGroup parent) {
        return new AbsListView.LayoutParams((parent.getWidth() - parent.getPaddingLeft()) - parent.getPaddingRight(), ((parent.getHeight() - parent.getPaddingTop()) - parent.getPaddingBottom()) - (parent.getResources().getDimensionPixelSize(R.dimen.bind__card_list_view_padding) * 2));
    }
}
