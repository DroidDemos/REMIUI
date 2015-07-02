package com.google.android.finsky.utils;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.LibraryEntry;
import com.google.android.finsky.protos.ModifyLibrary.ModifyLibraryResponse;
import java.util.List;

public class WishlistHelper {
    private static long sLastWishlistMutationTimeMs;
    private static List<WishlistStatusListener> sWishlistStatusListeners;

    public interface WishlistStatusListener {
        void onWishlistStatusChanged(String str, boolean z, boolean z2);
    }

    static {
        sWishlistStatusListeners = Lists.newArrayList();
    }

    public static void addWishlistStatusListener(WishlistStatusListener listener) {
        sWishlistStatusListeners.add(listener);
    }

    public static void removeWishlistStatusListener(WishlistStatusListener listener) {
        sWishlistStatusListeners.remove(listener);
    }

    private static void invokeWishlistStatusListeners(String docId, boolean isInWishlist, boolean isCommited) {
        for (int i = sWishlistStatusListeners.size() - 1; i >= 0; i--) {
            ((WishlistStatusListener) sWishlistStatusListeners.get(i)).onWishlistStatusChanged(docId, isInWishlist, isCommited);
        }
    }

    public static boolean shouldHideWishlistAction(Document document, DfeApi dfeApi) {
        if (isInWishlist(document, FinskyApp.get().getCurrentAccount())) {
            return false;
        }
        if (document.hasDealOfTheDayInfo() || document.getDocumentType() == 20 || document.getDocumentType() == 30) {
            return true;
        }
        Libraries libraries = FinskyApp.get().getLibraries();
        Account owner = LibraryUtils.getOwnerWithCurrentAccount(document, libraries, dfeApi.getAccount());
        if (owner == null && document.getBackend() == 6 && document.hasSubscriptions()) {
            owner = LibraryUtils.getOwnerWithCurrentAccount(document.getSubscriptionsList(), libraries, FinskyApp.get().getCurrentAccount());
        }
        boolean isInstalled = false;
        if (document.getDocumentType() == 1) {
            if (FinskyApp.get().getPackageInfoRepository().get(document.getAppDetails().packageName) != null) {
                isInstalled = true;
            } else {
                isInstalled = false;
            }
        }
        if (owner != null || isInstalled) {
            return true;
        }
        return false;
    }

    public static boolean isInWishlist(Document document, Account account) {
        return FinskyApp.get().getLibraries().getAccountLibrary(account).contains(LibraryEntry.fromDocument(account.name, "u-wl", document, 1));
    }

    public static void processWishlistClick(final View view, Document document, final DfeApi dfeApi) {
        boolean z = true;
        if (document == null) {
            FinskyLog.w("Tried to wishlist an item but there is no document active", new Object[0]);
            return;
        }
        final boolean wasInWishlist = isInWishlist(document, dfeApi.getAccount());
        final String docId = document.getDocId();
        final String docTitle = document.getTitle();
        final Resources res = FinskyApp.get().getResources();
        Listener<ModifyLibraryResponse> modifyResponseListener = new Listener<ModifyLibraryResponse>() {
            public void onResponse(ModifyLibraryResponse response) {
                WishlistHelper.sLastWishlistMutationTimeMs = System.currentTimeMillis();
                FinskyApp.get().getLibraryReplicators().applyLibraryUpdates(dfeApi.getAccount(), "modifed_wishlist", new Runnable() {
                    public void run() {
                        WishlistHelper.invokeWishlistStatusListeners(docId, !wasInWishlist, true);
                    }
                }, response.libraryUpdate);
            }
        };
        ErrorListener errorListener = new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FinskyApp.get(), res.getString(wasInWishlist ? R.string.wishlist_remove_error : R.string.wishlist_add_error, new Object[]{docTitle}), 1).show();
                if (wasInWishlist) {
                    FinskyLog.e("Unable to remove from wishlist: %s", error);
                } else {
                    FinskyLog.e("Unable to add to wishlist: %s", error);
                }
                WishlistHelper.invokeWishlistStatusListeners(docId, wasInWishlist, true);
            }
        };
        final Context context = view.getContext();
        boolean isAccessibilityEnabled = UiUtils.isAccessibilityEnabled(context);
        if (wasInWishlist) {
            dfeApi.removeFromLibrary(Lists.newArrayList(docId), "u-wl", modifyResponseListener, errorListener);
        } else {
            if (!isAccessibilityEnabled) {
                Toast.makeText(context, R.string.wishlist_adding, 0).show();
            }
            dfeApi.addToLibrary(Lists.newArrayList(docId), "u-wl", modifyResponseListener, errorListener);
        }
        if (wasInWishlist) {
            z = false;
        }
        invokeWishlistStatusListeners(docId, z, false);
        if (isAccessibilityEnabled) {
            new Handler(Looper.myLooper()).post(new Runnable() {
                public void run() {
                    UiUtils.sendAccessibilityEventWithText(context, context.getString(wasInWishlist ? R.string.wishlist_removing : R.string.wishlist_adding), view);
                }
            });
        }
    }

    public static boolean hasMutationOccurredSince(long timeMs) {
        return timeMs < sLastWishlistMutationTimeMs;
    }
}
