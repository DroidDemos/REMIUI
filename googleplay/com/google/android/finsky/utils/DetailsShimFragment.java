package com.google.android.finsky.utils;

import android.os.Bundle;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.fragments.UrlBasedPageFragment;

public class DetailsShimFragment extends UrlBasedPageFragment {
    private DfeDetails mDetailsData;

    public static DetailsShimFragment newInstance(String url, String continueUrl, String overrideAccount) {
        DetailsShimFragment fragment = new DetailsShimFragment();
        fragment.setDfeAccount(overrideAccount);
        fragment.setDfeTocAndUrl(FinskyApp.get().getToc(), url);
        fragment.setArgument("finsky.DetailsFragment.continueUrl", continueUrl);
        fragment.setArgument("finsky.DetailsFragment.overrideAccount", overrideAccount);
        return fragment;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestData();
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.mDetailsData != null) {
            this.mDetailsData.removeDataChangedListener(this);
            this.mDetailsData.removeErrorListener(this);
        }
    }

    protected int getLayoutRes() {
        return 0;
    }

    public boolean isDataReady() {
        return false;
    }

    protected void onInitViewBinders() {
    }

    protected void rebindViews() {
    }

    protected void requestData() {
        if (this.mDetailsData != null) {
            this.mDetailsData.removeDataChangedListener(this);
            this.mDetailsData.removeErrorListener(this);
        }
        this.mDetailsData = new DfeDetails(this.mDfeApi, this.mUrl, false, VoucherUtils.getVoucherIds(FinskyApp.get().getLibraries().getAccountLibrary(this.mDfeApi.getAccount())));
        this.mDetailsData.addDataChangedListener(this);
        this.mDetailsData.addErrorListener(this);
        switchToBlank();
        switchToLoading();
    }

    public void onDataChanged() {
        if (this.mDetailsData.getDocument() == null) {
            this.mPageFragmentHost.showErrorDialog(null, this.mContext.getString(R.string.details_page_error), true);
        } else {
            this.mNavigationManager.replaceDetailsShimWithDocPage(this.mDetailsData.getDocument(), this.mUrl, getArguments().getString("finsky.DetailsFragment.continueUrl"), getArguments().getString("finsky.DetailsFragment.overrideAccount"));
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return null;
    }
}
