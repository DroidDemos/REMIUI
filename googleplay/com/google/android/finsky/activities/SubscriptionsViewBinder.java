package com.google.android.finsky.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.SubscriptionView.CancelListener;
import com.google.android.finsky.layout.SubscriptionsSection;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.LibraryEntry;
import com.google.android.finsky.library.LibraryInAppSubscriptionEntry;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import com.google.android.finsky.library.RevokeListenerWrapper;
import com.google.android.finsky.navigationmanager.NavigationManager;
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

public class SubscriptionsViewBinder extends DetailsViewBinder implements CancelListener, Listener {
    private boolean mDestroyed;
    private Document mDocument;
    private Fragment mFragment;
    private Libraries mLibraries;
    PlayStoreUiElementNode mParentNode;
    private Bundle mSavedState;
    private int mSubscriptionItemLayoutId;
    private SubscriptionsSection mSubscriptionsSection;

    public void init(Context context, DfeApi api, NavigationManager navManager, Libraries libraries) {
        super.init(context, api, navManager);
        this.mLibraries = libraries;
    }

    public void onDestroyView() {
        this.mDestroyed = true;
        if (this.mLibraries != null) {
            this.mLibraries.removeListener(this);
        }
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        rebindViews();
    }

    public void bind(Fragment fragment, SubscriptionsSection subscriptionsSection, DfeApi dfeApi, Document doc, int subscriptionItemLayoutId, Bundle savedState, PlayStoreUiElementNode parentNode) {
        this.mFragment = fragment;
        this.mSubscriptionsSection = subscriptionsSection;
        this.mDfeApi = dfeApi;
        this.mDocument = doc;
        this.mSavedState = savedState;
        this.mSubscriptionItemLayoutId = subscriptionItemLayoutId;
        this.mParentNode = parentNode;
        this.mLibraries.removeListener(this);
        this.mLibraries.addListener(this);
        this.mDestroyed = false;
        rebindViews();
    }

    private void rebindViews() {
        if (this.mSubscriptionsSection != null) {
            if (this.mSubscriptionsSection.getChildCount() == 0) {
                this.mSubscriptionsSection.setVisibility(8);
            }
            if (this.mDocument.getDocumentType() != 1) {
                this.mSubscriptionsSection.clearSubscriptions();
                if (this.mDocument.getBackend() == 6) {
                    if (this.mDocument.hasSubscriptions()) {
                        for (Document subscriptionDoc : this.mDocument.getSubscriptionsList()) {
                            LibrarySubscriptionEntry subsEntry = getLibraryMagazineSubscriptionEntry(subscriptionDoc);
                            if (subsEntry != null) {
                                this.mSubscriptionsSection.addSubscription(subscriptionDoc, subsEntry, this.mSubscriptionItemLayoutId, this, this.mSavedState, this.mParentNode);
                            }
                        }
                    }
                    if (this.mSubscriptionsSection.getChildCount() > 0) {
                        this.mSubscriptionsSection.setVisibility(0);
                    }
                }
            } else if ("com.google.android.music".equals(this.mDocument.getBackendDocId())) {
                handleMusicSubscriptions();
            } else {
                handleAppSubscriptions();
            }
        }
    }

    private void handleAppSubscriptions() {
        Map<String, LibrarySubscriptionEntry> subscriptionEntries = Maps.newHashMap();
        AccountLibrary currentLibrary = this.mLibraries.getAccountLibrary(this.mDfeApi.getAccount());
        for (AccountLibrary library : this.mLibraries.getAccountLibraries()) {
            if (library != currentLibrary) {
                addAppSubscriptionsToMap(library, subscriptionEntries);
            }
        }
        addAppSubscriptionsToMap(currentLibrary, subscriptionEntries);
        fetchSubscriptionDocs(subscriptionEntries.keySet(), subscriptionEntries);
    }

    private void addAppSubscriptionsToMap(AccountLibrary library, Map<String, LibrarySubscriptionEntry> subscriptionEntries) {
        for (LibraryInAppSubscriptionEntry entry : library.getSubscriptionPurchasesForPackage(this.mDocument.getBackendDocId())) {
            subscriptionEntries.put(entry.getDocId(), entry);
        }
    }

    private void handleMusicSubscriptions() {
        List<String> subscriptionDocids = Lists.newArrayList();
        Map<String, LibrarySubscriptionEntry> subscriptionEntries = Maps.newHashMap();
        String commaSeparatedBlacklist = (String) G.musicAppSubscriptionBackendDocidBlacklist.get();
        Set<Object> blacklistedDocids = Sets.newHashSet();
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

    private void fetchSubscriptionDocs(Collection<String> subscriptionDocids, final Map<String, LibrarySubscriptionEntry> subscriptionEntries) {
        if (!subscriptionDocids.isEmpty()) {
            this.mDfeApi.getDetails(subscriptionDocids, new Response.Listener<BulkDetailsResponse>() {
                public void onResponse(BulkDetailsResponse bulkDetailsResponse) {
                    if (SubscriptionsViewBinder.this.mDestroyed) {
                        FinskyLog.d("Destroyed, ignoring response.", new Object[0]);
                        return;
                    }
                    SubscriptionsViewBinder.this.mSubscriptionsSection.clearSubscriptions();
                    for (BulkDetailsEntry bulkDetailsEntry : bulkDetailsResponse.entry) {
                        if (bulkDetailsEntry.doc == null) {
                            FinskyLog.e("Received response entry without doc.", new Object[0]);
                        } else {
                            LibrarySubscriptionEntry subscriptionEntry = (LibrarySubscriptionEntry) subscriptionEntries.get(bulkDetailsEntry.doc.backendDocid);
                            if (subscriptionEntry == null) {
                                FinskyLog.e("Subscription entry not available for: %s", backendDocid);
                            } else {
                                SubscriptionsViewBinder.this.mSubscriptionsSection.addSubscription(new Document(bulkDetailsEntry.doc), subscriptionEntry, SubscriptionsViewBinder.this.mSubscriptionItemLayoutId, SubscriptionsViewBinder.this, SubscriptionsViewBinder.this.mSavedState, SubscriptionsViewBinder.this.mParentNode);
                            }
                        }
                    }
                    if (SubscriptionsViewBinder.this.mSubscriptionsSection.getChildCount() > 0) {
                        SubscriptionsViewBinder.this.mSubscriptionsSection.setVisibility(0);
                    } else {
                        SubscriptionsViewBinder.this.mSubscriptionsSection.setVisibility(8);
                    }
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

    public void onCancel(Document doc, LibrarySubscriptionEntry libraryEntry) {
        FragmentManager fragmentManager = this.mFragment.getFragmentManager();
        if (fragmentManager.findFragmentByTag("SubscriptionsViewBinder.confirm_cancel_dialog") == null) {
            int confirmRes = System.currentTimeMillis() < libraryEntry.trialUntilTimestampMs ? R.string.confirm_trial_subscription_cancel : R.string.confirm_renewing_subscription_cancel;
            Builder builder = new Builder();
            builder.setMessage(this.mContext.getResources().getString(confirmRes, new Object[]{doc.getTitle()})).setPositiveId(R.string.yes).setNegativeId(R.string.no).setEventLog(304, doc.getServerLogsCookie(), 243, 244, null);
            Bundle extraArguments = new Bundle();
            extraArguments.putString("authAccount", libraryEntry.getAccountName());
            extraArguments.putParcelable("subscription_doc", doc);
            builder.setCallback(this.mFragment, 3, extraArguments);
            builder.build().show(fragmentManager, "SubscriptionsViewBinder.confirm_cancel_dialog");
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        cancelSubscription(extraArguments.getString("authAccount"), (Document) extraArguments.getParcelable("subscription_doc"));
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        rebindViews();
    }

    public void saveInstanceState(Bundle bundle) {
        if (this.mSubscriptionsSection != null) {
            this.mSubscriptionsSection.saveInstanceState(bundle);
        }
    }

    private void cancelSubscription(String accountName, Document doc) {
        DfeApi dfeApi = FinskyApp.get().getDfeApi(accountName);
        dfeApi.revoke(doc.getDocId(), 1, new RevokeListenerWrapper(FinskyApp.get().getLibraryReplicators(), dfeApi.getAccount(), new Response.Listener<RevokeResponse>() {
            public void onResponse(RevokeResponse response) {
                Toast.makeText(SubscriptionsViewBinder.this.mContext, R.string.cancel_subscription_okay, 0).show();
            }
        }), new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                SubscriptionsViewBinder.this.rebindViews();
                Toast.makeText(SubscriptionsViewBinder.this.mContext, R.string.cancel_subscription_error, 0).show();
            }
        });
    }

    private LibrarySubscriptionEntry getLibraryMagazineSubscriptionEntry(Document subscriptionDoc) {
        return this.mLibraries.getAccountLibrary(this.mDfeApi.getAccount()).getMagazinesSubscriptionEntry(subscriptionDoc.getBackendDocId());
    }
}
