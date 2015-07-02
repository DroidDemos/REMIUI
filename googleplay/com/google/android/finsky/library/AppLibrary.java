package com.google.android.finsky.library;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.finsky.utils.DocUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import java.util.List;
import java.util.Set;

public class AppLibrary extends HashMapLibrary {
    private Set<String> mInAppDocIdSet;
    private Set<String> mSubscriptionsDocIdSet;

    public AppLibrary(LibraryHasher hasher) {
        super(3, hasher);
        this.mSubscriptionsDocIdSet = Sets.newHashSet();
        this.mInAppDocIdSet = Sets.newHashSet();
    }

    public void add(LibraryEntry entry) {
        int entryDocType = entry.getDocType();
        String entryDocId = entry.getDocId();
        if (entryDocType == 15) {
            this.mSubscriptionsDocIdSet.add(entryDocId);
        } else if (entryDocType == 11) {
            this.mInAppDocIdSet.add(entryDocId);
        }
        super.add(entry);
    }

    public void remove(LibraryEntry entry) {
        int entryDocType = entry.getDocType();
        String entryDocId = entry.getDocId();
        if (entryDocType == 15) {
            this.mSubscriptionsDocIdSet.remove(entryDocId);
        } else if (entryDocType == 11) {
            this.mInAppDocIdSet.remove(entryDocId);
        }
        super.remove(entry);
    }

    List<LibraryInAppSubscriptionEntry> getSubscriptionsList() {
        List<LibraryInAppSubscriptionEntry> subsList = Lists.newArrayList();
        for (String subsDocId : this.mSubscriptionsDocIdSet) {
            subsList.add(getSubscriptionEntry(subsDocId));
        }
        return subsList;
    }

    List<LibraryInAppEntry> getInAppPurchasesForPackage(String packageName) {
        List<LibraryInAppEntry> inAppList = Lists.newArrayList();
        for (String inAppDocId : this.mInAppDocIdSet) {
            if (TextUtils.equals(DocUtils.getPackageNameForInApp(inAppDocId), packageName)) {
                inAppList.add(getInAppEntry(inAppDocId));
            }
        }
        return inAppList;
    }

    List<LibraryInAppSubscriptionEntry> getSubscriptionPurchasesForPackage(String packageName) {
        List<LibraryInAppSubscriptionEntry> subsList = Lists.newArrayList();
        for (String subsDocId : this.mSubscriptionsDocIdSet) {
            if (TextUtils.equals(DocUtils.getPackageNameForSubscription(subsDocId), packageName)) {
                subsList.add(getSubscriptionEntry(subsDocId));
            }
        }
        return subsList;
    }

    public LibraryAppEntry getAppEntry(String docId) {
        return (LibraryAppEntry) get(new LibraryEntry(null, AccountLibrary.LIBRARY_ID_APPS, 3, docId, 1, 1));
    }

    public LibraryInAppSubscriptionEntry getSubscriptionEntry(String docId) {
        return (LibraryInAppSubscriptionEntry) get(new LibraryEntry(null, AccountLibrary.LIBRARY_ID_APPS, 3, docId, 15, 1));
    }

    public LibraryInAppEntry getInAppEntry(String docId) {
        return (LibraryInAppEntry) get(new LibraryEntry(null, AccountLibrary.LIBRARY_ID_APPS, 3, docId, 11, 1));
    }

    public synchronized void reset() {
        this.mSubscriptionsDocIdSet.clear();
        this.mInAppDocIdSet.clear();
        super.reset();
    }

    public String toString() {
        return String.format("{num apps=%d}", new Object[]{Integer.valueOf(size())});
    }

    public void dumpState(String label, String indent) {
        Log.d("FinskyLibrary", indent + "AppLibrary (" + label + ") {");
        Log.d("FinskyLibrary", indent + "  totalCount=" + size());
        Log.d("FinskyLibrary", indent + "  subscriptionsCount=" + this.mSubscriptionsDocIdSet.size());
        Log.d("FinskyLibrary", indent + "}");
    }
}
