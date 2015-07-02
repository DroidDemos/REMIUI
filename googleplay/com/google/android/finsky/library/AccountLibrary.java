package com.google.android.finsky.library;

import android.accounts.Account;
import android.os.Handler;
import android.util.Log;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AccountLibrary implements Library {
    private static final String[] EMPTY_AUTO_ACQUIRE_TAGS;
    public static final String[] LIBRARY_IDS;
    public static final String LIBRARY_ID_APPS;
    public static final String LIBRARY_ID_COMMERCE;
    public static final String LIBRARY_ID_MAGAZINE;
    public static final String LIBRARY_ID_MUSIC;
    public static final String LIBRARY_ID_OCEAN;
    public static final String LIBRARY_ID_YOUTUBE;
    private final Account mAccount;
    private String[] mAutoAcquireTags;
    private final Map<String, HashingLibrary> mLibraries;
    private final List<Listener> mListeners;
    private boolean mListenersEnabled;
    private final Handler mNotificationHandler;
    private final Map<String, byte[]> mTokens;

    public interface Listener {
        void onLibraryChange();
    }

    static {
        LIBRARY_ID_APPS = String.valueOf(3);
        LIBRARY_ID_OCEAN = String.valueOf(1);
        LIBRARY_ID_YOUTUBE = String.valueOf(4);
        LIBRARY_ID_MUSIC = String.valueOf(2);
        LIBRARY_ID_MAGAZINE = String.valueOf(6);
        LIBRARY_ID_COMMERCE = String.valueOf(10);
        LIBRARY_IDS = new String[]{LIBRARY_ID_APPS, LIBRARY_ID_OCEAN, LIBRARY_ID_YOUTUBE, LIBRARY_ID_MUSIC, LIBRARY_ID_MAGAZINE, LIBRARY_ID_COMMERCE, "u-wl"};
        EMPTY_AUTO_ACQUIRE_TAGS = new String[0];
    }

    public static String getLibraryIdFromBackend(int backend) {
        switch (backend) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return LIBRARY_ID_OCEAN;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return LIBRARY_ID_MUSIC;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return LIBRARY_ID_APPS;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return LIBRARY_ID_YOUTUBE;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return LIBRARY_ID_MAGAZINE;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return LIBRARY_ID_COMMERCE;
            default:
                return null;
        }
    }

    public static int getBackendFromLibraryId(String libraryId) {
        if (LIBRARY_ID_APPS.equals(libraryId)) {
            return 3;
        }
        if (LIBRARY_ID_OCEAN.equals(libraryId)) {
            return 1;
        }
        if (LIBRARY_ID_YOUTUBE.equals(libraryId)) {
            return 4;
        }
        if (LIBRARY_ID_MUSIC.equals(libraryId)) {
            return 2;
        }
        if (LIBRARY_ID_MAGAZINE.equals(libraryId)) {
            return 6;
        }
        if (LIBRARY_ID_COMMERCE.equals(libraryId)) {
            return 10;
        }
        if ("u-wl".equals(libraryId)) {
            return 0;
        }
        throw new IllegalArgumentException("Invalid libraryId: " + libraryId);
    }

    public AccountLibrary(Account account, Handler notificationHandler) {
        this.mLibraries = new HashMap();
        this.mTokens = new HashMap();
        this.mListeners = Lists.newArrayList();
        this.mListenersEnabled = true;
        this.mAutoAcquireTags = EMPTY_AUTO_ACQUIRE_TAGS;
        this.mAccount = account;
        this.mNotificationHandler = notificationHandler;
        this.mLibraries.put(LIBRARY_ID_APPS, new AppLibrary(new SumHasher()));
        this.mLibraries.put(LIBRARY_ID_MUSIC, new HashMapLibrary(2, new SumHasher()));
        this.mLibraries.put(LIBRARY_ID_OCEAN, new HashMapLibrary(1, new SumHasher()));
        this.mLibraries.put(LIBRARY_ID_YOUTUBE, new HashMapLibrary(4, new SumHasher()));
        this.mLibraries.put(LIBRARY_ID_MAGAZINE, new MagazinesLibrary(new SumHasher()));
        this.mLibraries.put(LIBRARY_ID_COMMERCE, new HashMapLibrary(10, new SumHasher()));
        this.mLibraries.put("u-wl", new HashMapLibrary(0, new SumHasher()));
    }

    public Account getAccount() {
        return this.mAccount;
    }

    public synchronized void disableListeners() {
        this.mListenersEnabled = false;
    }

    public synchronized void enableListeners() {
        this.mListenersEnabled = true;
        notifyListeners();
    }

    public boolean supportsLibrary(String libraryId) {
        return this.mLibraries.containsKey(libraryId);
    }

    private void notifyListeners() {
        if (this.mListenersEnabled) {
            final List<Listener> listeners = new ArrayList(this.mListeners);
            this.mNotificationHandler.post(new Runnable() {
                public void run() {
                    for (Listener listener : listeners) {
                        listener.onLibraryChange();
                    }
                }
            });
        }
    }

    public synchronized void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public synchronized LibraryAppEntry getAppEntry(String docId) {
        return ((AppLibrary) this.mLibraries.get(LIBRARY_ID_APPS)).getAppEntry(docId);
    }

    public synchronized LibraryInAppEntry getInAppEntry(String docId) {
        return ((AppLibrary) this.mLibraries.get(LIBRARY_ID_APPS)).getInAppEntry(docId);
    }

    public synchronized List<LibraryInAppSubscriptionEntry> getInAppSubscriptionsList() {
        return ((AppLibrary) this.mLibraries.get(LIBRARY_ID_APPS)).getSubscriptionsList();
    }

    public synchronized List<LibraryInAppEntry> getInAppPurchasesForPackage(String packageName) {
        return ((AppLibrary) this.mLibraries.get(LIBRARY_ID_APPS)).getInAppPurchasesForPackage(packageName);
    }

    public synchronized List<LibraryInAppSubscriptionEntry> getSubscriptionPurchasesForPackage(String packageName) {
        return ((AppLibrary) this.mLibraries.get(LIBRARY_ID_APPS)).getSubscriptionPurchasesForPackage(packageName);
    }

    public synchronized LibrarySubscriptionEntry getMagazinesSubscriptionEntry(String docId) {
        return ((MagazinesLibrary) this.mLibraries.get(LIBRARY_ID_MAGAZINE)).getSubscriptionEntry(docId);
    }

    public synchronized LibraryEntry get(LibraryEntry key) {
        Library library;
        library = (Library) this.mLibraries.get(key.getLibraryId());
        return library != null ? library.get(key) : null;
    }

    public synchronized boolean contains(LibraryEntry key) {
        Library library;
        library = (Library) this.mLibraries.get(key.getLibraryId());
        return library != null ? library.contains(key) : false;
    }

    public synchronized void add(LibraryEntry entry) {
        if (this.mAccount.name.equals(entry.getAccountName())) {
            HashingLibrary library = (HashingLibrary) this.mLibraries.get(entry.getLibraryId());
            if (library != null) {
                library.add(entry);
                notifyListeners();
            }
        } else {
            throw new IllegalArgumentException("Invalid account.");
        }
    }

    public synchronized void addAll(Collection<? extends LibraryEntry> entries) {
        for (LibraryEntry entry : entries) {
            add(entry);
        }
    }

    public synchronized void remove(LibraryEntry key) {
        if (this.mAccount.name.equals(key.getAccountName())) {
            Library library = (Library) this.mLibraries.get(key.getLibraryId());
            if (library != null) {
                library.remove(key);
                notifyListeners();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public synchronized void setServerToken(String libraryId, byte[] token) {
        this.mTokens.put(libraryId, token);
    }

    public synchronized byte[] getServerToken(String libraryId) {
        return (byte[]) this.mTokens.get(libraryId);
    }

    public synchronized void setAutoAcquireTags(String[] autoAcquireTags) {
        this.mAutoAcquireTags = autoAcquireTags;
    }

    public synchronized String[] getAutoAcquireTags() {
        return this.mAutoAcquireTags;
    }

    public synchronized int size() {
        int size;
        size = 0;
        for (HashingLibrary library : this.mLibraries.values()) {
            size += library.size();
        }
        return size;
    }

    public HashingLibrary getLibrary(String libraryId) {
        return (HashingLibrary) this.mLibraries.get(libraryId);
    }

    public synchronized void resetLibrary(String libraryId) {
        HashingLibrary library = (HashingLibrary) this.mLibraries.get(libraryId);
        if (library == null) {
            FinskyLog.w("Cannot reset: %s", libraryId);
        } else {
            library.reset();
        }
        notifyListeners();
    }

    public Iterator<LibraryEntry> iterator() {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return String.format("{account=%s numapps=%d}", new Object[]{FinskyLog.scrubPii(this.mAccount.name), Integer.valueOf(((HashingLibrary) this.mLibraries.get(LIBRARY_ID_APPS)).size())});
    }

    public void dumpState(String indent) {
        String scrubbedAccount = FinskyLog.scrubPii(this.mAccount.name);
        Log.d("FinskyLibrary", indent + "AccountLibrary (account=" + scrubbedAccount + ") {");
        for (String library : this.mLibraries.keySet()) {
            ((HashingLibrary) this.mLibraries.get(library)).dumpState("library=" + library, indent + "  ");
        }
        Log.d("FinskyLibrary", indent + "} (account=" + scrubbedAccount + ")");
    }
}
