package com.google.android.finsky.detailspage;

import android.os.Bundle;
import com.android.vending.R;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.DetailsExpandedContainer;
import com.google.android.finsky.layout.DetailsTextSection.ExpandedData;
import com.google.android.finsky.layout.LayoutSwitcher;

public class ExpandedDescriptionFragment extends PageFragment {
    private ExpandedData mExpandedData;
    private PlayStoreUiElement mUiElementProto;

    public ExpandedDescriptionFragment() {
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(0);
    }

    public static ExpandedDescriptionFragment newInstance(ExpandedData expandedData, DfeToc dfeToc) {
        ExpandedDescriptionFragment fragment = new ExpandedDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable("expanded_data", expandedData);
        fragment.setArguments(args);
        fragment.setDfeToc(dfeToc);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mExpandedData = (ExpandedData) getArguments().getParcelable("expanded_data");
        rebindActionBar();
        rebindViews();
        this.mActionBarController.disableActionBarOverlay();
    }

    protected LayoutSwitcher createLayoutSwitcher(ContentFrame frame) {
        return new LayoutSwitcher(frame, R.id.page_content, R.id.page_error_indicator, R.id.loading_indicator, this, 2);
    }

    public void rebindActionBar() {
        this.mPageFragmentHost.updateBreadcrumb(this.mExpandedData.title);
        this.mPageFragmentHost.updateCurrentBackendId(this.mExpandedData.backend, false);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    protected int getLayoutRes() {
        return R.layout.expanded_description_fragment;
    }

    public boolean isDataReady() {
        return true;
    }

    protected void onInitViewBinders() {
    }

    protected void requestData() {
    }

    protected void rebindViews() {
        DetailsExpandedContainer expandedContainer = (DetailsExpandedContainer) getView().findViewById(R.id.details_expanded_container);
        expandedContainer.populateFromSection(-1, this.mExpandedData, this.mNavigationManager, this.mExpandedData.backend, this, null);
        this.mActionBarController.enterActionBarSectionExpandedMode(this.mExpandedData.title, expandedContainer);
    }

    public void onDestroyView() {
        this.mActionBarController.exitActionBarSectionExpandedMode();
        super.onDestroyView();
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }
}
