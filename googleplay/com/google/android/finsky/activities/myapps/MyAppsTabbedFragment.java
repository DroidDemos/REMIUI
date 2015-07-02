package com.google.android.finsky.activities.myapps;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.android.vending.R;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.activities.ErrorDialog;
import com.google.android.finsky.activities.MultiInstallActivity;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeBulkDetails;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.billing.ProgressDialogFragment;
import com.google.android.finsky.fragments.PageFragment;
import com.google.android.finsky.layout.ContentFrame;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout.FinskyConfigurator;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.protos.ModifyLibrary.ModifyLibraryResponse;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.headerlist.PlayHeaderListLayout;
import java.util.ArrayList;
import java.util.List;

public class MyAppsTabbedFragment extends PageFragment implements OnPageChangeListener, Listener {
    private String mBreadcrumb;
    private ObjectMap mFragmentObjectMap;
    private Bundle mFrameworkBundle;
    Installer mInstaller;
    private ProgressDialogFragment mProgressDialog;
    private MyAppsTabbedAdapter mTabbedAdapter;
    private PlayStoreUiElement mUiElementProto;
    private ViewPager mViewPager;

    public MyAppsTabbedFragment() {
        this.mFrameworkBundle = new Bundle();
        this.mFragmentObjectMap = new ObjectMap();
        this.mUiElementProto = FinskyEventLog.obtainPlayStoreUiElement(0);
    }

    public static MyAppsTabbedFragment newInstance(DfeToc dfeToc) {
        MyAppsTabbedFragment fragment = new MyAppsTabbedFragment();
        fragment.setDfeToc(dfeToc);
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
            protected void addContentView(LayoutInflater inflater, ViewGroup root) {
                inflater.inflate(R.layout.header_list_pager, root);
            }

            protected int getHeaderHeight() {
                return FinskyHeaderListLayout.getMinimumHeaderHeight(this.mContext, 0, 0);
            }

            protected boolean hasViewPager() {
                return true;
            }

            protected int getViewPagerId() {
                return R.id.viewpager;
            }

            protected int getTabMode() {
                return 0;
            }

            protected int getTabPaddingMode() {
                return 1;
            }

            protected int getHeaderMode() {
                return 1;
            }

            protected int getListViewId() {
                return R.id.my_apps_content_list;
            }
        });
        headerListLayout.setContentViewId(R.id.viewpager);
        headerListLayout.setFloatingControlsBackground(new ColorDrawable(getActionBarColor()));
        return frame;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String breadcrumbText = CorpusResourceUtils.getCorpusMyCollectionDescription(3);
        if (TextUtils.isEmpty(breadcrumbText)) {
            breadcrumbText = this.mContext.getString(R.string.my_downloads_menu);
        }
        this.mBreadcrumb = breadcrumbText;
        if (savedInstanceState != null && this.mFrameworkBundle.isEmpty()) {
            this.mFrameworkBundle = savedInstanceState;
        }
        this.mInstaller = FinskyApp.get().getInstaller();
        FinskyApp.get().getNotifier().hideUpdatesAvailableMessage();
        if (isDataReady()) {
            rebindViews();
        } else {
            switchToLoading();
            requestData();
            rebindActionBar();
        }
        this.mActionBarController.enableActionBarOverlay();
    }

    public void onStart() {
        super.onStart();
        this.mProgressDialog = (ProgressDialogFragment) getFragmentManager().findFragmentByTag("progress_dialog");
        dismissProgressDialog();
    }

    public void refresh() {
        if (isDataReady()) {
            rebindViews();
        } else {
            super.refresh();
        }
    }

    protected int getLayoutRes() {
        return R.layout.header_list_container;
    }

    public boolean isDataReady() {
        return true;
    }

    public void rebindViews() {
        switchToData();
        rebindActionBar();
        if (this.mViewPager == null || this.mTabbedAdapter == null) {
            this.mTabbedAdapter = new MyAppsTabbedAdapter((AuthenticatedActivity) getActivity(), this.mNavigationManager, this.mDfeApi, getToc(), this.mBitmapLoader, FinskyApp.get().getLibraries().hasSubscriptions(), this.mFragmentObjectMap, this);
            configureViewPager();
            int defaultTabType = this.mFrameworkBundle.containsKey("MyAppsTabbedAdapter.CurrentTabType") ? this.mFrameworkBundle.getInt("MyAppsTabbedAdapter.CurrentTabType") : 1;
            int indexOfSelectedItem = 0;
            for (int i = 0; i < this.mTabbedAdapter.getCount(); i++) {
                if (this.mTabbedAdapter.getTabType(i) == defaultTabType) {
                    indexOfSelectedItem = i;
                    break;
                }
            }
            onPageScrolled(indexOfSelectedItem, 0.0f, 0);
            this.mViewPager.setCurrentItem(indexOfSelectedItem, false);
        }
    }

    public void rebindActionBar() {
        this.mPageFragmentHost.updateBreadcrumb(this.mBreadcrumb);
        this.mPageFragmentHost.updateCurrentBackendId(3, true);
        this.mPageFragmentHost.updateActionBarMode(false);
    }

    protected void onInitViewBinders() {
    }

    protected void requestData() {
        clearState();
    }

    private void clearState() {
        if (this.mDataView != null) {
            this.mDataView.setOnPageChangeListener(null);
        }
        if (this.mViewPager != null) {
            this.mViewPager.setAdapter(null);
            this.mViewPager = null;
        }
        this.mTabbedAdapter = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        recordState();
        bundle.putAll(this.mFrameworkBundle);
        super.onSaveInstanceState(bundle);
    }

    protected int getActionBarColor() {
        return CorpusResourceUtils.getPrimaryColor(getActivity(), 3);
    }

    private void recordState() {
        if (isDataReady()) {
            if (this.mTabbedAdapter != null) {
                this.mTabbedAdapter.onSaveInstanceState(this.mFragmentObjectMap);
            }
            if (this.mViewPager != null) {
                this.mFrameworkBundle.putInt("MyAppsTabbedAdapter.CurrentTabType", this.mTabbedAdapter.getTabType(this.mViewPager.getCurrentItem()));
            }
        }
    }

    public void onDestroyView() {
        cleanupActionModeIfNecessary();
        recordState();
        clearState();
        if (this.mDataView instanceof PlayHeaderListLayout) {
            ((PlayHeaderListLayout) this.mDataView).detach();
        }
        super.onDestroyView();
    }

    private boolean cleanupActionModeIfNecessary() {
        return this.mTabbedAdapter.finishActiveMode();
    }

    public void onPageScrollStateChanged(int scrollState) {
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        this.mTabbedAdapter.onPageSelected(position);
        if (!TextUtils.isEmpty(this.mTabbedAdapter.getPageTitle(position))) {
            UiUtils.sendAccessibilityEventWithText(this.mContext, this.mContext.getString(R.string.accessibility_event_tab_selected, new Object[]{selectedTabTitle}), this.mViewPager);
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                ArrayList<String> docids = extraArguments.getStringArrayList("docid_list");
                cleanupActionModeIfNecessary();
                archiveDocs(docids);
                return;
            default:
                FinskyLog.wtf("Unexpected requestCode %d", Integer.valueOf(requestCode));
                return;
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                refresh();
                return;
            default:
                return;
        }
    }

    private void configureViewPager() {
        this.mViewPager = (ViewPager) this.mDataView.findViewById(R.id.viewpager);
        if (this.mViewPager != null) {
            this.mViewPager.setAdapter(this.mTabbedAdapter);
            this.mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.swipey_tab_gutter_width));
            PlayHeaderListLayout headerListLayout = this.mDataView;
            headerListLayout.notifyPagerAdapterChanged();
            headerListLayout.setOnPageChangeListener(this);
            headerListLayout.setFloatingControlsBackground(new ColorDrawable(CorpusResourceUtils.getPrimaryColor(getActivity(), 3)));
        }
    }

    private void dismissProgressDialog() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
    }

    public void confirmArchiveDocs(List<Document> docs) {
        if (getFragmentManager().findFragmentByTag("archive_confirm") == null) {
            String message;
            if (docs.size() == 1) {
                message = getString(R.string.archiving_no_uninstall_confirmation, ((Document) docs.get(0)).getTitle());
            } else {
                message = getString(R.string.archiving_no_uninstall_confirmation_multiple);
            }
            Builder builder = new Builder();
            builder.setMessage(message).setPositiveId(R.string.ok).setNegativeId(R.string.cancel);
            ArrayList<String> docids = getDocIdList(docs);
            Bundle params = new Bundle();
            params.putStringArrayList("docid_list", docids);
            builder.setCallback(this, 6, params);
            builder.setEventLog(317, null, 269, 270, null);
            builder.build().show(getFragmentManager(), "archive_confirm");
        }
    }

    private void archiveDocs(final List<String> docids) {
        FinskyEventLog eventLogger = FinskyApp.get().getEventLogger();
        int numDocIds = docids.size();
        for (int i = 0; i < numDocIds; i++) {
            eventLogger.logBackgroundEvent(506, (String) docids.get(i), null, 0, null, null);
        }
        this.mProgressDialog = ProgressDialogFragment.newInstance((int) R.string.archiving);
        this.mProgressDialog.show(getFragmentManager(), "progress_dialog");
        this.mDfeApi.archiveFromLibrary(docids, AccountLibrary.LIBRARY_ID_APPS, new Response.Listener<ModifyLibraryResponse>() {
            public void onResponse(ModifyLibraryResponse modifyLibraryResponse) {
                FinskyApp.get().getLibraryReplicators().applyLibraryUpdates(MyAppsTabbedFragment.this.mDfeApi.getAccount(), "myapps-archive", new Runnable() {
                    public void run() {
                        if (MyAppsTabbedFragment.this.canChangeFragmentManagerState()) {
                            MyAppsTabbedFragment.this.dismissProgressDialog();
                        }
                        MyAppsTabbedFragment.this.mTabbedAdapter.removeLibraryItems(docids);
                    }
                }, modifyLibraryResponse.libraryUpdate);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (MyAppsTabbedFragment.this.canChangeFragmentManagerState()) {
                    MyAppsTabbedFragment.this.dismissProgressDialog();
                    ErrorDialog.show(MyAppsTabbedFragment.this.getFragmentManager(), null, ErrorStrings.get(MyAppsTabbedFragment.this.getActivity(), volleyError), false);
                }
            }
        });
    }

    private void fetchPermissionsAndInstall(List<Document> documents) {
        if (getFragmentManager().findFragmentByTag("progress_dialog") == null) {
            cleanupActionModeIfNecessary();
            this.mProgressDialog = ProgressDialogFragment.newInstance(null);
            this.mProgressDialog.show(getFragmentManager(), "progress_dialog");
            List docIds = getDocIdList(documents);
            final String accountName = FinskyApp.get().getCurrentAccountName();
            final DfeBulkDetails dfeModel = new DfeBulkDetails(this.mDfeApi, docIds);
            dfeModel.addDataChangedListener(new OnDataChangedListener() {
                public void onDataChanged() {
                    if (MyAppsTabbedFragment.this.canChangeFragmentManagerState()) {
                        MyAppsTabbedFragment.this.dismissProgressDialog();
                        MyAppsTabbedFragment.this.mContext.startActivity(MultiInstallActivity.getInstallIntent(MyAppsTabbedFragment.this.mContext, dfeModel.getDocuments(), accountName));
                        return;
                    }
                    FinskyLog.w("Bulk install cannot start because no longer active.", new Object[0]);
                }
            });
            dfeModel.addErrorListener(new ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    if (MyAppsTabbedFragment.this.canChangeFragmentManagerState()) {
                        MyAppsTabbedFragment.this.dismissProgressDialog();
                        ErrorDialog.show(MyAppsTabbedFragment.this.getFragmentManager(), null, ErrorStrings.get(MyAppsTabbedFragment.this.getActivity(), volleyError), false);
                    }
                }
            });
        }
    }

    private static ArrayList<String> getDocIdList(List<Document> docs) {
        ArrayList<String> docids = Lists.newArrayList(docs.size());
        for (Document doc : docs) {
            docids.add(doc.getDocId());
        }
        return docids;
    }

    boolean handleMenuItem(List<Document> documents, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.install_button:
                fetchPermissionsAndInstall(documents);
                return true;
            default:
                return false;
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mUiElementProto;
    }

    public boolean onBackPressed() {
        return cleanupActionModeIfNecessary();
    }

    public void onEnterActionBarSearchMode() {
    }

    public void onExitActionBarSearchMode() {
    }
}
