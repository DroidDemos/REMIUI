package com.google.android.finsky.activities.myapps;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeBulkDetails;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryInAppSubscriptionEntry;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.finsky.utils.Sets;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyAppsSubscriptionsTab extends MyAppsTab<DfeBulkDetails> {
    private MyAppsSubscriptionsAdapter mAdapter;
    private boolean mAdapterInitialized;
    private ObjectMap mSavedInstanceState;
    Map<String, LibraryInAppSubscriptionEntry> mSubscriptionsInLibrary;
    private ListView mSubscriptionsListView;
    private ViewGroup mSubscriptionsView;

    public MyAppsSubscriptionsTab(AuthenticatedActivity authenticatedActivity, DfeApi dfeApi, DfeToc dfeToc, NavigationManager navigationManager, BitmapLoader bitmapLoader) {
        super(authenticatedActivity, dfeApi, dfeToc, navigationManager);
        this.mAdapterInitialized = false;
        this.mSubscriptionsInLibrary = Maps.newHashMap();
        this.mSavedInstanceState = new ObjectMap();
        this.mAdapter = new MyAppsSubscriptionsAdapter(authenticatedActivity, this.mLayoutInflater, bitmapLoader, this);
    }

    protected void requestData() {
        clearState();
        this.mSubscriptionsInLibrary.clear();
        this.mAdapter.clear();
        Libraries libraries = FinskyApp.get().getLibraries();
        Set<String> docIds = Sets.newHashSet();
        AccountLibrary currentLibrary = libraries.getAccountLibrary(this.mDfeApi.getAccount());
        for (AccountLibrary library : libraries.getAccountLibraries()) {
            if (library != currentLibrary) {
                for (LibraryInAppSubscriptionEntry entry : library.getInAppSubscriptionsList()) {
                    String entryDocId = entry.getDocId();
                    this.mSubscriptionsInLibrary.put(entryDocId, entry);
                    docIds.add(entryDocId);
                    docIds.add(DocUtils.getPackageNameForSubscription(entryDocId));
                }
            }
        }
        for (LibraryInAppSubscriptionEntry entry2 : currentLibrary.getInAppSubscriptionsList()) {
            entryDocId = entry2.getDocId();
            this.mSubscriptionsInLibrary.put(entryDocId, entry2);
            docIds.add(entryDocId);
            docIds.add(DocUtils.getPackageNameForSubscription(entryDocId));
        }
        List fetchDocIds = Lists.newArrayList();
        fetchDocIds.addAll(docIds);
        this.mDfeModel = new DfeBulkDetails(this.mDfeApi, fetchDocIds);
        ((DfeBulkDetails) this.mDfeModel).addDataChangedListener(this);
        ((DfeBulkDetails) this.mDfeModel).addErrorListener(this);
    }

    public void onDataChanged() {
        super.onDataChanged();
        List<Document> docs = ((DfeBulkDetails) this.mDfeModel).getDocuments();
        Map<String, Document> subsDocs = Maps.newHashMap();
        Map<String, Document> appsDocs = Maps.newHashMap();
        for (Document doc : docs) {
            switch (doc.getDocumentType()) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    appsDocs.put(doc.getDocId(), doc);
                    break;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    subsDocs.put(doc.getDocId(), doc);
                    break;
                default:
                    break;
            }
        }
        for (Entry<String, LibraryInAppSubscriptionEntry> libEntry : this.mSubscriptionsInLibrary.entrySet()) {
            String subsDocId = (String) libEntry.getKey();
            Document subDoc = (Document) subsDocs.get(subsDocId);
            Document appDoc = (Document) appsDocs.get(DocUtils.getPackageNameForSubscription(subsDocId));
            if (subDoc == null) {
                FinskyLog.w("Subscription %s is unavailable, ignoring this entry", subsDocId);
            } else if (appDoc == null) {
                FinskyLog.w("Parent app %s of subscription %s is unavailable, ignoring this entry", appDocId, subsDocId);
            } else {
                this.mAdapter.addEntry((LibraryInAppSubscriptionEntry) libEntry.getValue(), subDoc, appDoc);
            }
        }
        this.mAdapter.sortDocs();
        if (!this.mAdapterInitialized) {
            this.mSubscriptionsListView = (ListView) this.mSubscriptionsView.findViewById(com.android.vending.R.id.my_apps_content_list);
            int horizontalPadding = UiUtils.getGridHorizontalPadding(this.mSubscriptionsListView.getResources());
            this.mSubscriptionsListView.setPadding(horizontalPadding, this.mSubscriptionsListView.getPaddingTop(), horizontalPadding, this.mSubscriptionsListView.getPaddingBottom());
            this.mSubscriptionsListView.setAdapter(this.mAdapter);
            this.mSubscriptionsListView.setItemsCanFocus(true);
            if (this.mSavedInstanceState.containsKey("MyAppsTab.KeyListParcel")) {
                this.mSubscriptionsListView.onRestoreInstanceState((Parcelable) this.mSavedInstanceState.get("MyAppsTab.KeyListParcel"));
            }
            this.mAdapterInitialized = true;
        }
        syncViewToState();
    }

    protected View getFullView() {
        return this.mSubscriptionsView;
    }

    public View getView(int backendId) {
        if (this.mSubscriptionsView == null) {
            this.mSubscriptionsView = (ViewGroup) this.mLayoutInflater.inflate(com.android.vending.R.layout.my_apps_subscriptions, null);
        }
        return this.mSubscriptionsView;
    }

    protected void retryFromError() {
        requestData();
    }

    protected MyAppsListAdapter getAdapter() {
        return this.mAdapter;
    }

    protected ListView getListView() {
        return this.mSubscriptionsListView;
    }

    protected Document getDocumentForView(View view) {
        return (Document) view.getTag();
    }

    public void onRestoreInstanceState(ObjectMap savedInstanceState) {
        if (savedInstanceState != null) {
            this.mSavedInstanceState = savedInstanceState;
        }
    }

    public ObjectMap onSaveInstanceState() {
        if (this.mSubscriptionsListView != null) {
            this.mSavedInstanceState.put("MyAppsTab.KeyListParcel", this.mSubscriptionsListView.onSaveInstanceState());
        }
        return this.mSavedInstanceState;
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        if (event == InstallerPackageEvent.INSTALLED || event == InstallerPackageEvent.UNINSTALLED) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        requestData();
    }
}
