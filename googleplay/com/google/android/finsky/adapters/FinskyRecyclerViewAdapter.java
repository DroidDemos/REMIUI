package com.google.android.finsky.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.finsky.api.model.ContainerList;
import com.google.android.finsky.layout.play.PlayRecyclerView;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ErrorStrings;

public abstract class FinskyRecyclerViewAdapter extends PaginatedRecyclerViewAdapter {
    protected ContainerList<?> mContainerList;

    public FinskyRecyclerViewAdapter(Context context, NavigationManager navManager, ContainerList containerList) {
        super(context, navManager, containerList.inErrorState(), containerList.isMoreAvailable());
        this.mContainerList = containerList;
        this.mContainerList.addDataChangedListener(this);
    }

    public void onDestroy() {
        this.mContainerList.removeDataChangedListener(this);
    }

    public void onSaveInstanceState(PlayRecyclerView view, Bundle bundle) {
        bundle.putParcelable("ListTab.RecyclerViewParcelKey", view.saveInstanceState());
    }

    protected boolean isMoreDataAvailable() {
        return this.mContainerList.isMoreAvailable();
    }

    protected void retryLoadingItems() {
        this.mContainerList.retryLoadItems();
    }

    protected String getLastRequestError() {
        return ErrorStrings.get(this.mContext, this.mContainerList.getVolleyError());
    }

    public void updateAdapterData(ContainerList<?> containerList) {
        this.mContainerList = containerList;
        notifyDataSetChanged();
    }

    public void onRestoreInstanceState(PlayRecyclerView view, Bundle restoreBundle) {
        Parcelable recyclerViewParcel = restoreBundle.getParcelable("ListTab.RecyclerViewParcelKey");
        if (recyclerViewParcel != null) {
            view.restoreInstanceState(recyclerViewParcel);
        }
    }
}
