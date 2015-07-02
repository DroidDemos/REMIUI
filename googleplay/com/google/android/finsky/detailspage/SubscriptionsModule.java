package com.google.android.finsky.detailspage;

import android.accounts.Account;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.SubscriptionView.CancelListener;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.LibraryEntry;
import com.google.android.finsky.library.LibraryInAppSubscriptionEntry;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.library.RevokeListenerWrapper;
import com.google.android.finsky.protos.Details.BulkDetailsEntry;
import com.google.android.finsky.protos.Details.BulkDetailsResponse;
import com.google.android.finsky.protos.RevokeResponse;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubscriptionsModule extends FinskyModule<Data> implements CancelListener, Listener {
    private boolean mDestroyed;
    private boolean mNeedsRefresh;

    protected static class Data extends ModuleData {
        Document detailsDoc;
        boolean hasFinishedFetchingSubscriptions;
        Bundle savedInstanceState;
        List<Document> subscriptionDocuments;
        List<LibrarySubscriptionEntry> subscriptionEntries;

        protected Data() {
        }
    }

    public int getLayoutResId() {
        return R.layout.subscriptions_module;
    }

    public boolean readyForDisplay() {
        return (this.mModuleData == null || !((Data) this.mModuleData).hasFinishedFetchingSubscriptions || ((Data) this.mModuleData).subscriptionDocuments.isEmpty() || ((Data) this.mModuleData).subscriptionEntries.isEmpty()) ? false : true;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null) {
            this.mLibraries.addListener(this);
            if (!((Data) this.mModuleData).hasFinishedFetchingSubscriptions) {
                fetchSubscriptions();
            }
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).detailsDoc = detailsDoc;
            ((Data) this.mModuleData).savedInstanceState = new Bundle();
            ((Data) this.mModuleData).subscriptionDocuments = Lists.newArrayList();
            ((Data) this.mModuleData).subscriptionEntries = Lists.newArrayList();
            this.mLibraries.addListener(this);
            fetchSubscriptions();
        }
    }

    public void bindView(View view) {
        SubscriptionsModuleLayout subscriptionsModuleLayout = (SubscriptionsModuleLayout) view;
        if (!subscriptionsModuleLayout.hasBinded() || this.mNeedsRefresh) {
            subscriptionsModuleLayout.bind(((Data) this.mModuleData).subscriptionDocuments, ((Data) this.mModuleData).subscriptionEntries, this, ((Data) this.mModuleData).detailsDoc.getBackend(), ((Data) this.mModuleData).savedInstanceState, this.mParentNode);
            ((Data) this.mModuleData).savedInstanceState.clear();
            this.mNeedsRefresh = false;
        }
    }

    public void onRecycleView(View moduleView) {
        ((SubscriptionsModuleLayout) moduleView).saveInstanceState(((Data) this.mModuleData).savedInstanceState);
    }

    public void onDestroyModule() {
        this.mLibraries.removeListener(this);
        this.mDestroyed = true;
    }

    private void refreshView() {
        if (readyForDisplay()) {
            this.mNeedsRefresh = true;
            this.mFinskyModuleController.refreshModule(this, true);
            return;
        }
        this.mFinskyModuleController.removeModule(this);
    }

    private void fetchSubscriptions() {
        Document doc = ((Data) this.mModuleData).detailsDoc;
        if (doc.getDocumentType() == 1) {
            if ("com.google.android.music".equals(doc.getBackendDocId())) {
                fetchMusicSubscriptions();
            } else {
                fetchAppSubscriptions();
            }
        } else if (doc.getBackend() == 6) {
            fetchMagazinesSubscriptions();
        }
    }

    private void fetchMusicSubscriptions() {
        List<String> subscriptionDocids = Lists.newArrayList();
        Map<String, LibrarySubscriptionEntry> subscriptionEntries = Maps.newHashMap();
        String commaSeparatedBlacklist = (String) G.musicAppSubscriptionBackendDocidBlacklist.get();
        Set<String> blacklistedDocids = Sets.newHashSet();
        if (commaSeparatedBlacklist != null) {
            Collections.addAll(blacklistedDocids, commaSeparatedBlacklist.split(","));
        }
        for (LibraryEntry libraryEntry : this.mLibraries.getAccountLibrary(this.mDfeApi.getAccount()).getLibrary(AccountLibrary.getLibraryIdFromBackend(2))) {
            if (libraryEntry instanceof LibrarySubscriptionEntry) {
                LibrarySubscriptionEntry subscriptionEntry = (LibrarySubscriptionEntry) libraryEntry;
                String subscriptionDocId = subscriptionEntry.getDocId();
                if (!blacklistedDocids.contains(subscriptionDocId)) {
                    subscriptionEntries.put(subscriptionDocId, subscriptionEntry);
                    subscriptionDocids.add(DocUtils.getMusicSubscriptionDocid(subscriptionDocId));
                } else if (FinskyLog.DEBUG) {
                    FinskyLog.v("Ignoring blacklisted subscription: %s", subscriptionDocId);
                }
            }
        }
        fetchSubscriptionDocs(subscriptionDocids, subscriptionEntries);
    }

    private void fetchAppSubscriptions() {
        Document doc = ((Data) this.mModuleData).detailsDoc;
        Map<String, LibrarySubscriptionEntry> subscriptionEntries = Maps.newHashMap();
        AccountLibrary currentLibrary = this.mLibraries.getAccountLibrary(this.mDfeApi.getAccount());
        for (AccountLibrary library : this.mLibraries.getAccountLibraries()) {
            if (library != currentLibrary) {
                addAppSubscriptionsToMap(doc, library, subscriptionEntries);
            }
        }
        addAppSubscriptionsToMap(doc, currentLibrary, subscriptionEntries);
        fetchSubscriptionDocs(subscriptionEntries.keySet(), subscriptionEntries);
    }

    private void addAppSubscriptionsToMap(Document doc, AccountLibrary library, Map<String, LibrarySubscriptionEntry> subscriptionEntries) {
        for (LibraryInAppSubscriptionEntry entry : library.getSubscriptionPurchasesForPackage(doc.getBackendDocId())) {
            subscriptionEntries.put(entry.getDocId(), entry);
        }
    }

    private void fetchMagazinesSubscriptions() {
        Document doc = ((Data) this.mModuleData).detailsDoc;
        ((Data) this.mModuleData).subscriptionDocuments.clear();
        ((Data) this.mModuleData).subscriptionEntries.clear();
        if (doc.hasSubscriptions()) {
            Account owningAccount = this.mDfeApi.getAccount();
            for (Document subscriptionDoc : doc.getSubscriptionsList()) {
                LibrarySubscriptionEntry subsEntry = this.mLibraries.getAccountLibrary(owningAccount).getMagazinesSubscriptionEntry(subscriptionDoc.getBackendDocId());
                if (subsEntry != null) {
                    ((Data) this.mModuleData).subscriptionDocuments.add(subscriptionDoc);
                    ((Data) this.mModuleData).subscriptionEntries.add(subsEntry);
                }
            }
            ((Data) this.mModuleData).hasFinishedFetchingSubscriptions = true;
            refreshView();
        }
    }

    private void fetchSubscriptionDocs(Collection<String> subscriptionDocids, final Map<String, LibrarySubscriptionEntry> subscriptionEntries) {
        if (!subscriptionDocids.isEmpty()) {
            this.mDfeApi.getDetails(subscriptionDocids, new Response.Listener<BulkDetailsResponse>() {
                public void onResponse(BulkDetailsResponse bulkDetailsResponse) {
                    if (SubscriptionsModule.this.mDestroyed) {
                        FinskyLog.d("Destroyed, ignoring response.", new Object[0]);
                        return;
                    }
                    ((Data) SubscriptionsModule.this.mModuleData).subscriptionDocuments.clear();
                    ((Data) SubscriptionsModule.this.mModuleData).subscriptionEntries.clear();
                    for (BulkDetailsEntry bulkDetailsEntry : bulkDetailsResponse.entry) {
                        if (bulkDetailsEntry.doc == null) {
                            FinskyLog.e("Received response entry without doc.", new Object[0]);
                        } else {
                            LibrarySubscriptionEntry subscriptionEntry = (LibrarySubscriptionEntry) subscriptionEntries.get(bulkDetailsEntry.doc.backendDocid);
                            if (subscriptionEntry == null) {
                                FinskyLog.e("Subscription entry not available for: %s", backendDocid);
                            } else {
                                ((Data) SubscriptionsModule.this.mModuleData).subscriptionDocuments.add(new Document(bulkDetailsEntry.doc));
                                ((Data) SubscriptionsModule.this.mModuleData).subscriptionEntries.add(subscriptionEntry);
                            }
                        }
                    }
                    ((Data) SubscriptionsModule.this.mModuleData).hasFinishedFetchingSubscriptions = true;
                    SubscriptionsModule.this.refreshView();
                }
            }, new ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    FinskyLog.e("Could not retrieve subscription docs: %s", volleyError);
                }
            });
        } else if (FinskyLog.DEBUG) {
            FinskyLog.v("No active subscriptions.", new Object[0]);
        }
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        fetchSubscriptions();
    }

    public void onCancel(Document doc, LibrarySubscriptionEntry libraryEntry) {
        FragmentManager fragmentManager = this.mDetailsFragment2.getFragmentManager();
        if (fragmentManager.findFragmentByTag("SubscriptionsViewBinder.confirm_cancel_dialog") == null) {
            int confirmRes = System.currentTimeMillis() < libraryEntry.trialUntilTimestampMs ? R.string.confirm_trial_subscription_cancel : R.string.confirm_renewing_subscription_cancel;
            Builder builder = new Builder();
            builder.setMessage(this.mContext.getResources().getString(confirmRes, new Object[]{doc.getTitle()})).setPositiveId(R.string.yes).setNegativeId(R.string.no).setEventLog(304, doc.getServerLogsCookie(), 243, 244, null);
            Bundle extraArguments = new Bundle();
            extraArguments.putString("authAccount", libraryEntry.getAccountName());
            extraArguments.putParcelable("subscription_doc", doc);
            builder.setCallback(this.mDetailsFragment2, 3, extraArguments);
            builder.build().show(fragmentManager, "SubscriptionsViewBinder.confirm_cancel_dialog");
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        if (requestCode == 3) {
            cancelSubscription(extraArguments.getString("authAccount"), (Document) extraArguments.getParcelable("subscription_doc"));
        }
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
    }

    private void cancelSubscription(String accountName, Document doc) {
        DfeApi dfeApi = FinskyApp.get().getDfeApi(accountName);
        dfeApi.revoke(doc.getDocId(), 1, new RevokeListenerWrapper(FinskyApp.get().getLibraryReplicators(), dfeApi.getAccount(), new Response.Listener<RevokeResponse>() {
            public void onResponse(RevokeResponse response) {
                Toast.makeText(SubscriptionsModule.this.mContext, R.string.cancel_subscription_okay, 0).show();
            }
        }), new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SubscriptionsModule.this.mContext, R.string.cancel_subscription_error, 0).show();
            }
        });
    }
}
