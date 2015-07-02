package com.google.android.finsky.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.LayoutSwitcher;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.layout.actionbar.ActionBarController;
import com.google.android.finsky.layout.actionbar.ActionBarController.ActionBarSearchModeListener;
import com.google.android.finsky.layout.actionbar.FinskySearchToolbar;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.layout.play.RootUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.headerlist.PlayHeaderListLayout;
import com.google.android.play.image.BitmapLoader;

public abstract class PageFragment extends Fragment implements ErrorListener, OnDataChangedListener, RetryButtonListener, ActionBarSearchModeListener, RootUiElementNode {
    protected ActionBarController mActionBarController;
    protected BitmapLoader mBitmapLoader;
    protected Context mContext;
    protected ViewGroup mDataView;
    private String mDfeAccount;
    protected DfeApi mDfeApi;
    private DfeToc mDfeToc;
    private Handler mImpressionHandler;
    private long mImpressionId;
    private LayoutSwitcher mLayoutSwitcher;
    protected NavigationManager mNavigationManager;
    protected PageFragmentHost mPageFragmentHost;
    protected boolean mRefreshRequired;
    protected boolean mSaveInstanceStateCalled;
    private int mTheme;

    protected abstract int getLayoutRes();

    public abstract boolean isDataReady();

    protected abstract void onInitViewBinders();

    protected abstract void rebindViews();

    protected abstract void requestData();

    protected PageFragment() {
        this.mTheme = R.style.FinskyTheme;
        this.mImpressionId = FinskyEventLog.getNextImpressionId();
        setArguments(new Bundle());
        if (NavigationManager.areTransitionsEnabled()) {
            setExitTransition(TransitionInflater.from(FinskyApp.get()).inflateTransition(R.transition.fade_page));
        }
    }

    protected void setTheme(int themeResId) {
        this.mTheme = themeResId;
    }

    public boolean onBackPressed() {
        return false;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContentFrame frame = (ContentFrame) inflater.inflate(R.layout.generic_frame, container, false);
        this.mDataView = frame.inflateDataLayout(inflater, getLayoutRes(), R.id.page_content);
        if (this.mDataView != null) {
            frame.addView(this.mDataView);
        }
        this.mSaveInstanceStateCalled = false;
        this.mLayoutSwitcher = createLayoutSwitcher(frame);
        if (this.mPageFragmentHost != null && getFragmentManager().findFragmentByTag("hats-survey") == null) {
            this.mPageFragmentHost.maybeShowSatisfactionSurvey(this, false);
        }
        FinskyLog.logTiming("Views inflated", new Object[0]);
        return frame;
    }

    protected LayoutSwitcher createLayoutSwitcher(ContentFrame frame) {
        return new LayoutSwitcher(frame, R.id.page_content, R.id.page_error_indicator, R.id.loading_indicator, this, 2);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mDataView = null;
        this.mLayoutSwitcher = null;
        if (this.mActionBarController != null) {
            this.mActionBarController.setActionBarSearchModeListener(null);
        }
    }

    public void onAttach(Activity activity) {
        this.mImpressionHandler = new Handler(activity.getMainLooper());
        super.onAttach(activity);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (((PageFragmentHost) getActivity()) != this.mPageFragmentHost) {
            this.mPageFragmentHost = (PageFragmentHost) getActivity();
            this.mContext = getActivity();
            this.mNavigationManager = this.mPageFragmentHost.getNavigationManager();
            this.mActionBarController = this.mPageFragmentHost.getActionBarController();
            this.mDfeApi = this.mPageFragmentHost.getDfeApi(this.mDfeAccount);
            this.mBitmapLoader = this.mPageFragmentHost.getBitmapLoader();
            onInitViewBinders();
        }
        this.mSaveInstanceStateCalled = false;
        if (this.mActionBarController != null) {
            this.mActionBarController.setActionBarSearchModeListener(this);
        }
        FinskyLog.logTiming("Views bound", new Object[0]);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDfeAccount = getArguments().getString("finsky.PageFragment.dfeAccount");
        this.mDfeToc = (DfeToc) getArguments().getParcelable("finsky.PageFragment.toc");
        this.mSaveInstanceStateCalled = false;
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("finsky.PageFragment.theme", this.mTheme);
        this.mSaveInstanceStateCalled = true;
    }

    public void refresh() {
        this.mRefreshRequired = false;
        requestData();
        FinskyLog.logTiming("requestData called", new Object[0]);
    }

    public void refreshOnResume() {
        this.mRefreshRequired = true;
    }

    public void onResume() {
        super.onResume();
        FinskyEventLog.startNewImpression(this);
        this.mSaveInstanceStateCalled = false;
        if (this.mRefreshRequired) {
            refresh();
        }
    }

    public void onRetry() {
        refresh();
    }

    public DfeToc getToc() {
        return this.mDfeToc;
    }

    public boolean canChangeFragmentManagerState() {
        Activity activity = getActivity();
        return (this.mSaveInstanceStateCalled || activity == null || ((activity instanceof AuthenticatedActivity) && ((AuthenticatedActivity) activity).isStateSaved())) ? false : true;
    }

    public void onDataChanged() {
        if (isAdded()) {
            this.mRefreshRequired = false;
            switchToData();
            rebindViews();
            FinskyLog.logTiming("Views rebound", new Object[0]);
        } else {
            this.mRefreshRequired = true;
        }
        if (this.mPageFragmentHost != null) {
            this.mPageFragmentHost.maybeShowSatisfactionSurvey(this, false);
        }
    }

    public void rebindActionBar() {
    }

    protected void switchToBlank() {
        this.mLayoutSwitcher.switchToBlankMode();
    }

    protected void switchToLoading() {
        this.mLayoutSwitcher.switchToLoadingDelayed(350);
    }

    protected void switchToLoadingImmediately() {
        this.mLayoutSwitcher.switchToLoadingMode();
    }

    protected void switchToError(String msg) {
        if (this.mLayoutSwitcher == null) {
            boolean isActivityNull;
            Activity activity = getActivity();
            if (activity == null) {
                isActivityNull = true;
            } else {
                isActivityNull = false;
            }
            boolean isAuth = false;
            boolean isStateSaved = false;
            if (!isActivityNull) {
                isAuth = activity instanceof AuthenticatedActivity;
                if (isAuth) {
                    isStateSaved = ((AuthenticatedActivity) activity).isStateSaved();
                }
            }
            FinskyLog.wtf("mSaveInstanceStateCalled=[%s], activityNull=[%s], isAuthenticatedActivity=[%s], isStateSaved=[%s]", Boolean.valueOf(this.mSaveInstanceStateCalled), Boolean.valueOf(isActivityNull), Boolean.valueOf(isAuth), Boolean.valueOf(isStateSaved));
            return;
        }
        this.mLayoutSwitcher.switchToErrorMode(msg);
    }

    protected void switchToData() {
        this.mLayoutSwitcher.switchToDataMode();
    }

    public void onErrorResponse(VolleyError error) {
        if (canChangeFragmentManagerState()) {
            switchToError(ErrorStrings.get(this.mContext, error));
        }
    }

    protected void setDfeAccount(String dfeAccount) {
        if (!TextUtils.isEmpty(dfeAccount)) {
            setArgument("finsky.PageFragment.dfeAccount", dfeAccount);
        }
    }

    protected void setDfeToc(DfeToc toc) {
        setArgument("finsky.PageFragment.toc", (Parcelable) toc);
    }

    protected void setArgument(String key, String value) {
        getArguments().putString(key, value);
    }

    protected void setArgument(String key, int value) {
        getArguments().putInt(key, value);
    }

    protected void setArgument(String key, Parcelable value) {
        getArguments().putParcelable(key, value);
    }

    protected void setArgument(String key, boolean value) {
        getArguments().putBoolean(key, value);
    }

    public final PlayStoreUiElementNode getParentNode() {
        return null;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.rootImpression(this.mImpressionHandler, this.mImpressionId, this, childNode);
    }

    public void startNewImpression() {
        this.mImpressionId = FinskyEventLog.getNextImpressionId();
    }

    public void flushImpression() {
        FinskyEventLog.flushImpression(this.mImpressionHandler, this.mImpressionId, this);
    }

    public void onEnterActionBarSearchMode() {
        if ((this.mDataView instanceof PlayHeaderListLayout) && FinskySearchToolbar.supportsSearchBoxOnlyMode()) {
            PlayHeaderListLayout headerListLayout = this.mDataView;
            headerListLayout.setFloatingControlsBackground(new ColorDrawable(0), true);
            headerListLayout.setHeaderShadowMode(2);
        }
    }

    public void onExitActionBarSearchMode() {
        if ((this.mDataView instanceof PlayHeaderListLayout) && FinskySearchToolbar.supportsSearchBoxOnlyMode()) {
            final PlayHeaderListLayout headerListLayout = this.mDataView;
            headerListLayout.setFloatingControlsBackground(new ColorDrawable(getActionBarColor()), true);
            headerListLayout.postDelayed(new Runnable() {
                public void run() {
                    headerListLayout.setHeaderShadowMode(PageFragment.this.getDefaultHeaderShadowMode());
                }
            }, 200);
        }
    }

    protected int getActionBarColor() {
        return CorpusResourceUtils.getPrimaryColor(this.mContext, 0);
    }

    protected int getDefaultHeaderShadowMode() {
        return 1;
    }
}
