package com.google.android.finsky.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.HeaderLayoutSwitcher;
import com.google.android.finsky.layout.HeroGraphicView;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.actionbar.ActionBarBackgroundUpdater;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.FinskyConfigurator;
import com.google.android.play.headerlist.PlayHeaderListLayout;

public class PeopleDetailsFragment extends DetailsDataBasedFragment {
    private ActionBarBackgroundUpdater mActionBarBackgroundUpdater;
    private HeroGraphicView mBackgroundView;
    private PeopleDetailsStreamViewBinder mStreamViewBinder;

    public PeopleDetailsFragment() {
        this.mStreamViewBinder = new PeopleDetailsStreamViewBinder();
    }

    public static PeopleDetailsFragment newInstance(Document doc, String detailsUrl) {
        PeopleDetailsFragment fragment = new PeopleDetailsFragment();
        fragment.setDfeAccount(FinskyApp.get().getCurrentAccountName());
        fragment.setDfeTocAndUrl(FinskyApp.get().getToc(), detailsUrl);
        fragment.setInitialDocument(doc);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContentFrame frame = (ContentFrame) super.onCreateView(inflater, container, savedInstanceState);
        FinskyHeaderListLayout headerListLayout = this.mDataView;
        headerListLayout.configure(new FinskyConfigurator(headerListLayout.getContext()) {
            protected void addBackgroundView(LayoutInflater inflater, ViewGroup root) {
                PeopleDetailsFragment.this.mBackgroundView = (HeroGraphicView) inflater.inflate(R.layout.hero_graphic, root, false);
                root.addView(PeopleDetailsFragment.this.mBackgroundView);
            }

            protected void addContentView(LayoutInflater inflater, ViewGroup root) {
                inflater.inflate(R.layout.people_details, root);
            }

            protected int getListViewId() {
                return R.id.people_details_stream_list;
            }

            protected float getBackgroundViewParallaxRatio() {
                return 0.8f;
            }

            protected boolean alwaysUseFloatingBackground() {
                return false;
            }
        });
        headerListLayout.setContentViewId(R.id.people_details);
        headerListLayout.configureEventInterception(this.mBackgroundView);
        this.mActionBarBackgroundUpdater = new ActionBarBackgroundUpdater(headerListLayout);
        headerListLayout.setOnLayoutChangedListener(this.mActionBarBackgroundUpdater);
        this.mActionBarBackgroundUpdater.updateBackground();
        return frame;
    }

    protected LayoutSwitcher createLayoutSwitcher(ContentFrame frame) {
        return new HeaderLayoutSwitcher(frame, R.id.page_content, R.id.page_error_indicator, R.id.loading_indicator, this, 2);
    }

    protected int getLayoutRes() {
        return R.layout.header_list_container;
    }

    protected void onInitViewBinders() {
        this.mStreamViewBinder.init(this.mContext, this.mDfeApi, this.mNavigationManager, this.mBitmapLoader, getToc(), FinskyApp.get().getClientMutationCache(FinskyApp.get().getCurrentAccountName()), this);
    }

    protected void rebindViews(Bundle savedInstanceState) {
        rebindActionBar();
        this.mActionBarController.enableActionBarOverlay();
        this.mStreamViewBinder.initRecyclerView(this.mDataView);
        if (hasDetailsDataLoaded()) {
            Document doc = getDocument();
            this.mStreamViewBinder.bind(this.mDataView, doc);
            if (this.mBackgroundView != null) {
                this.mBackgroundView.bindProfile(doc);
            }
        }
    }

    public void rebindActionBar() {
        if (this.mDataView != null) {
            this.mDataView.setFloatingControlsBackground(new ColorDrawable(getActionBarColor()));
        }
        this.mPageFragmentHost.updateBreadcrumb(getDocument().getTitle());
        this.mPageFragmentHost.updateCurrentBackendId(0, true);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    public void onDestroyView() {
        this.mStreamViewBinder.onDestroyView();
        if (this.mDataView instanceof PlayHeaderListLayout) {
            this.mDataView.detach();
            if (this.mActionBarBackgroundUpdater != null) {
                this.mActionBarBackgroundUpdater.resetBackground();
                this.mActionBarBackgroundUpdater = null;
            }
        }
        super.onDestroyView();
    }

    protected int getPlayStoreUiElementType() {
        return 4;
    }

    protected void recordState(Bundle savedInstanceState) {
    }
}
