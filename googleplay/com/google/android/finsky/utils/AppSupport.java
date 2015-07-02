package com.google.android.finsky.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.library.RevokeListenerWrapper;
import com.google.android.finsky.protos.RevokeResponse;

public class AppSupport {

    public interface RefundListener {
        void onRefundComplete(boolean z);

        void onRefundStart();
    }

    public static void silentRefund(final FragmentManager fragmentManager, final String packageName, String accountName, final boolean tryUninstall, final RefundListener listener) {
        listener.onRefundStart();
        DfeApi dfeApi = FinskyApp.get().getDfeApi(accountName);
        dfeApi.revoke(packageName, 1, new RevokeListenerWrapper(FinskyApp.get().getLibraryReplicators(), dfeApi.getAccount(), new Listener<RevokeResponse>() {
            public void onResponse(RevokeResponse revokeResponse) {
                OrderHistoryHelper.onMutationOccurred();
                if (tryUninstall) {
                    FinskyApp.get().getInstaller().uninstallAssetSilently(packageName);
                }
                if (listener != null) {
                    listener.onRefundComplete(true);
                }
            }
        }), new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (fragmentManager != null) {
                    AppSupport.showRefundFailureDialog(fragmentManager);
                }
                if (listener != null) {
                    listener.onRefundComplete(false);
                }
            }
        });
    }

    public static void showUninstallConfirmationDialog(String packageName, Fragment target, boolean isSystemPackage, boolean isOwned, boolean hasSubscriptions) {
        FragmentManager fragmentManager = target.getFragmentManager();
        if (fragmentManager.findFragmentByTag("uninstall_confirm") == null) {
            int messageId;
            int positiveId = R.string.ok;
            int negativeId = R.string.cancel;
            if (!isOwned) {
                messageId = R.string.uninstall_non_owned_app_msg;
            } else if (isSystemPackage) {
                messageId = R.string.uninstall_system_updates_msg;
            } else if (hasSubscriptions) {
                messageId = R.string.uninstall_app_msg_active_subscriptions;
                positiveId = R.string.yes;
                negativeId = R.string.no;
            } else {
                messageId = R.string.uninstall_app_msg;
            }
            Builder builder = new Builder();
            builder.setMessageId(messageId).setPositiveId(positiveId).setNegativeId(negativeId);
            Bundle extraArgs = new Bundle();
            extraArgs.putString("package_name", packageName);
            builder.setCallback(target, 1, extraArgs);
            builder.build().show(fragmentManager, "uninstall_confirm");
        }
    }

    public static void showRefundFailureDialog(FragmentManager fragmentManager) {
        Builder builder = new Builder();
        builder.setMessageId(R.string.uninstall_refund_fail_body).setPositiveId(R.string.ok);
        builder.build().show(fragmentManager, "refund_failure");
    }
}
