package com.google.android.finsky.activities.myapps;

import android.os.AsyncTask;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.MultiWayUpdateController;
import com.google.android.finsky.appstate.GmsCoreHelper;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class MyAppsInstalledTab extends MyAppsTab<MultiWayUpdateController> implements BucketsChangedListener {
    private MyAppsInstalledAdapter mAdapter;
    private Map<String, List<String>> mDocIdsByAccount;
    private ViewGroup mInstalledView;
    private boolean mListInitialized;
    private ListView mMyAppsList;
    private ObjectMap mSavedInstanceState;

    public MyAppsInstalledTab(AuthenticatedActivity authenticatedActivity, DfeApi dfeApi, DfeToc dfeToc, NavigationManager navigationManager, BitmapLoader bitmapLoader, PlayStoreUiElementNode parentNode) {
        super(authenticatedActivity, dfeApi, dfeToc, navigationManager);
        this.mDocIdsByAccount = Maps.newHashMap();
        this.mSavedInstanceState = new ObjectMap();
        PlayStoreUiElementNode mUiElementNode = new GenericUiElementNode(405, null, null, parentNode);
        this.mAdapter = new MyAppsInstalledAdapter(authenticatedActivity, this.mInstaller, FinskyApp.get().getInstallPolicies(), FinskyApp.get().getAppStates(), bitmapLoader, this, this, mUiElementNode);
    }

    protected void requestData() {
        new AsyncTask<Void, Void, Map<String, List<String>>>() {
            protected Map<String, List<String>> doInBackground(Void... params) {
                return FinskyApp.get().getAppStates().getOwnedByAccountBlocking(FinskyApp.get().getLibraries(), false);
            }

            protected void onPostExecute(Map<String, List<String>> docIdsByAccount) {
                for (List<String> accountDocs : docIdsByAccount.values()) {
                    GmsCoreHelper.removeGmsCore(accountDocs);
                }
                MultiWayUpdateController.selectAccountsForUpdateChecks(FinskyApp.get().getInstallerDataStore(), MyAppsInstalledTab.this.mDfeApi.getAccountName(), docIdsByAccount);
                if (MyAppsInstalledTab.this.mDfeModel == null || !MyAppsInstalledTab.this.mDocIdsByAccount.equals(docIdsByAccount)) {
                    MyAppsInstalledTab.this.mDocIdsByAccount = docIdsByAccount;
                    MyAppsInstalledTab.this.clearState();
                    MyAppsInstalledTab.this.mDfeModel = new MultiWayUpdateController(FinskyApp.get().getInstallerDataStore(), FinskyApp.get().getLibraries());
                    ((MultiWayUpdateController) MyAppsInstalledTab.this.mDfeModel).addDataChangedListener(MyAppsInstalledTab.this);
                    ((MultiWayUpdateController) MyAppsInstalledTab.this.mDfeModel).addErrorListener(MyAppsInstalledTab.this);
                    ((MultiWayUpdateController) MyAppsInstalledTab.this.mDfeModel).addRequests(docIdsByAccount);
                    for (Entry<String, List<String>> entry : docIdsByAccount.entrySet()) {
                        if (((List) entry.getValue()).size() > 0) {
                            return;
                        }
                    }
                    MyAppsInstalledTab.this.onDataChanged();
                    return;
                }
                MyAppsInstalledTab.this.onDataChanged();
            }
        }.execute(new Void[0]);
    }

    protected View getFullView() {
        return this.mInstalledView;
    }

    public View getView(int backendId) {
        if (this.mInstalledView == null) {
            this.mInstalledView = (ViewGroup) this.mLayoutInflater.inflate(R.layout.my_apps_installed, null);
        }
        return this.mInstalledView;
    }

    protected void retryFromError() {
        clearState();
        requestData();
    }

    public void onDataChanged() {
        super.onDataChanged();
        if (!this.mListInitialized) {
            this.mInstalledView.findViewById(R.id.lists_loading_indicator).setVisibility(8);
            this.mMyAppsList = (ListView) this.mInstalledView.findViewById(R.id.my_apps_content_list);
            int horizontalPadding = UiUtils.getGridHorizontalPadding(this.mMyAppsList.getResources());
            this.mMyAppsList.setPadding(horizontalPadding, this.mMyAppsList.getPaddingTop(), horizontalPadding, this.mMyAppsList.getPaddingBottom());
            this.mMyAppsList.setAdapter(this.mAdapter);
            this.mMyAppsList.setItemsCanFocus(true);
            this.mListInitialized = true;
            if (this.mSavedInstanceState.containsKey("MyAppsTab.KeyListParcel")) {
                this.mMyAppsList.onRestoreInstanceState((Parcelable) this.mSavedInstanceState.get("MyAppsTab.KeyListParcel"));
            }
            configureEmptyUI(false, R.string.empty_myapps_description_installed);
        }
        Collection<Document> docs = ((MultiWayUpdateController) this.mDfeModel).getDocuments();
        if (docs != null) {
            this.mAdapter.addDocs(docs);
        }
        syncViewToState();
    }

    protected Document getDocumentForView(View view) {
        return MyAppsInstalledAdapter.getViewDoc(view);
    }

    protected ListView getListView() {
        return this.mMyAppsList;
    }

    protected MyAppsListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void bucketsChanged() {
    }

    public void onRestoreInstanceState(ObjectMap savedInstanceState) {
        if (savedInstanceState != null) {
            this.mSavedInstanceState = savedInstanceState;
        }
    }

    public ObjectMap onSaveInstanceState() {
        if (this.mMyAppsList != null) {
            this.mSavedInstanceState.put("MyAppsTab.KeyListParcel", this.mMyAppsList.onSaveInstanceState());
        }
        return this.mSavedInstanceState;
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        if (event == InstallerPackageEvent.DOWNLOADING || event == InstallerPackageEvent.INSTALLING) {
            this.mAdapter.invalidateInstallingItem(packageName, getListView());
        } else {
            requestData();
        }
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        requestData();
    }
}
