package com.xiaomi.account.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.xiaomi.account.R;
import com.xiaomi.account.data.SetupData;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.ui.SimpleDialogFragment;
import com.xiaomi.passport.ui.SimpleDialogFragment.AlertDialogFragmentBuilder;
import java.util.concurrent.Executor;
import miui.analytics.Analytics;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;
import miui.cloud.sync.SyncInfoHelper;
import miui.cloud.sync.SyncInfoUnavailableException;
import miui.telephony.exception.IllegalDeviceException;

public final class LogoutModel {
    private static final int RESULT_DELETE_DEVICE_FAILED = 4;
    private static final int RESULT_DEVICE_ID_GET_FAILED = 3;
    private static final int RESULT_REMOVE_ACCOUNT_FAILED = 1;
    private static final int RESULT_REMOVE_ACCOUNT_SUCCEED = 0;
    private static final int RESULT_TURN_OFF_FIND_DEVICE_FAILTED = 2;
    private static final String TAG = "LogoutModel";
    private Account mAccount;
    private AlertDialog mAlertDialog;
    private Analytics mAnalytics;
    private AsyncTask<Void, Void, Integer> mRemoveAccountTask;

    public LogoutModel(Account account) {
        this.mAccount = account;
    }

    private void showWipeDataDialog(final Activity activity) {
        int dirtyContactsCount = RESULT_REMOVE_ACCOUNT_SUCCEED;
        try {
            dirtyContactsCount = SyncInfoHelper.getUnsyncedDataCount(activity, "com.android.contacts");
        } catch (SyncInfoUnavailableException e) {
            e.printStackTrace();
        }
        int dirtyMmsCount = RESULT_REMOVE_ACCOUNT_SUCCEED;
        try {
            dirtyMmsCount = SyncInfoHelper.getUnsyncedDataCount(activity, "sms");
        } catch (SyncInfoUnavailableException e2) {
            e2.printStackTrace();
        }
        int dirtyNoteCount = RESULT_REMOVE_ACCOUNT_SUCCEED;
        try {
            dirtyNoteCount = SyncInfoHelper.getUnsyncedDataCount(activity, "notes");
        } catch (SyncInfoUnavailableException e22) {
            e22.printStackTrace();
        }
        if (dirtyContactsCount == 0 && dirtyMmsCount == 0 && dirtyNoteCount == 0) {
            startRemoveAccount(activity, true);
            return;
        }
        Object[] objArr;
        Resources res = activity.getResources();
        StringBuilder sb = new StringBuilder();
        if (dirtyContactsCount > 0) {
            objArr = new Object[RESULT_REMOVE_ACCOUNT_FAILED];
            objArr[RESULT_REMOVE_ACCOUNT_SUCCEED] = Integer.valueOf(dirtyContactsCount);
            sb.append(res.getQuantityString(R.plurals.dirty_contacts_count, dirtyContactsCount, objArr));
        }
        if (dirtyMmsCount > 0) {
            if (sb.length() > 0) {
                sb.append(activity.getString(R.string.wipe_data_dialog_msg_separator));
            }
            objArr = new Object[RESULT_REMOVE_ACCOUNT_FAILED];
            objArr[RESULT_REMOVE_ACCOUNT_SUCCEED] = Integer.valueOf(dirtyMmsCount);
            sb.append(res.getQuantityString(R.plurals.dirty_mms_count, dirtyMmsCount, objArr));
        }
        if (dirtyNoteCount > 0) {
            if (sb.length() > 0) {
                sb.append(activity.getString(R.string.wipe_data_dialog_msg_separator));
            }
            objArr = new Object[RESULT_REMOVE_ACCOUNT_FAILED];
            objArr[RESULT_REMOVE_ACCOUNT_SUCCEED] = Integer.valueOf(dirtyNoteCount);
            sb.append(res.getQuantityString(R.plurals.dirty_note_count, dirtyNoteCount, objArr));
        }
        objArr = new Object[RESULT_REMOVE_ACCOUNT_FAILED];
        objArr[RESULT_REMOVE_ACCOUNT_SUCCEED] = sb.toString();
        new Builder(activity).setTitle(R.string.wipe_data_dialog_title).setMessage(activity.getString(R.string.wipe_data_dialog_msg, objArr)).setNegativeButton(17039360, null).setPositiveButton(17039370, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                LogoutModel.this.startRemoveAccount(activity, true);
            }
        }).create().show();
    }

    public void confirmRemoveAccount(Activity activity, Button deleteAccountButton, Analytics analytics) {
        this.mAnalytics = analytics;
        confirmRemoveAccount(activity, deleteAccountButton);
    }

    public void confirmRemoveAccount(final Activity activity, Button deleteAccountButton) {
        View v = LayoutInflater.from(activity).inflate(R.layout.remove_account_alert, null);
        if (v != null) {
            ((Button) v.findViewById(R.id.btn_keep_data)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LogoutModel.this.startRemoveAccount(activity, false);
                    LogoutModel.this.dismissRemoveAccount();
                }
            });
            ((Button) v.findViewById(R.id.btn_wipe_data)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LogoutModel.this.showWipeDataDialog(activity);
                    LogoutModel.this.dismissRemoveAccount();
                }
            });
            ((Button) v.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SetupData.setUserVerified(false);
                    LogoutModel.this.dismissRemoveAccount();
                }
            });
            this.mAlertDialog = new Builder(activity, miui.R.style.Theme_Light_Dialog_Alert).setView(v).create();
            this.mAlertDialog.setTitle(R.string.alert_title_remove_account);
            this.mAlertDialog.setMessage(activity.getResources().getString(R.string.remove_account_notice));
            this.mAlertDialog.show();
        }
    }

    public void cancelLogout() {
        if (this.mRemoveAccountTask != null) {
            this.mRemoveAccountTask.cancel(true);
            this.mRemoveAccountTask = null;
        }
    }

    private void dismissRemoveAccount() {
        if (this.mAlertDialog != null) {
            this.mAlertDialog.dismiss();
        }
    }

    private void startRemoveAccount(final Activity activity, final boolean wipe) {
        AnonymousClass5 anonymousClass5 = new AsyncTask<Void, Void, Integer>() {
            DialogFragment mProgressFragment;

            protected void onPostExecute(Integer result) {
                this.mProgressFragment.dismissAllowingStateLoss();
                if (result != null) {
                    int reasonResId;
                    switch (result.intValue()) {
                        case LogoutModel.RESULT_REMOVE_ACCOUNT_SUCCEED /*0*/:
                            if (LogoutModel.this.mAnalytics != null) {
                                AnalyticsHelper.trackEvent(LogoutModel.this.mAnalytics, AnalyticsHelper.USER_ACCOUNT_SIGN_OUT_SUCCESSFULLY);
                            }
                            activity.finish();
                            return;
                        case LogoutModel.RESULT_TURN_OFF_FIND_DEVICE_FAILTED /*2*/:
                            reasonResId = R.string.timeout_retry;
                            break;
                        case LogoutModel.RESULT_DEVICE_ID_GET_FAILED /*3*/:
                        case LogoutModel.RESULT_DELETE_DEVICE_FAILED /*4*/:
                            reasonResId = R.string.no_response_retry;
                            break;
                        default:
                            reasonResId = R.string.error_unknown;
                            break;
                    }
                    if (reasonResId != -1) {
                        Builder builder = new Builder(activity);
                        builder.setTitle(R.string.logout_failed);
                        builder.setMessage(reasonResId);
                        builder.setPositiveButton(17039370, null);
                        builder.show();
                    }
                }
            }

            protected void onPreExecute() {
                AlertDialogFragmentBuilder builder = new AlertDialogFragmentBuilder(LogoutModel.RESULT_TURN_OFF_FIND_DEVICE_FAILTED);
                builder.setCancelable(false);
                builder.setMessage(activity.getString(R.string.removing_account));
                SimpleDialogFragment f = builder.create();
                f.show(activity.getFragmentManager(), "RemoveAccount");
                this.mProgressFragment = f;
            }

            protected Integer doInBackground(Void... params) {
                Log.d(LogoutModel.TAG, "turnOffFindDevice start");
                if (!CloudHelper.turnOffFindDevice(activity, LogoutModel.this.mAccount)) {
                    return Integer.valueOf(LogoutModel.RESULT_TURN_OFF_FIND_DEVICE_FAILTED);
                }
                Log.d(LogoutModel.TAG, "safeGetHashedDeviceId start");
                try {
                    String deviceId = CloudHelper.safeGetHashedDeviceId();
                    Log.d(LogoutModel.TAG, "deleteBindedDevice start");
                    if (SysHelper.deleteBindedDevice(activity, deviceId)) {
                        LogoutModel.this.notifyAccountPreChanged(activity, wipe);
                        AccountManagerFuture<Boolean> f = AccountManager.get(activity).removeAccount(LogoutModel.this.mAccount, null, null);
                        Boolean removed = Boolean.valueOf(false);
                        try {
                            removed = (Boolean) f.getResult();
                        } catch (Exception e) {
                            Log.e(LogoutModel.TAG, "error when remove account", e);
                        }
                        SetupData.setUserVerified(false);
                        if (!removed.booleanValue()) {
                            return Integer.valueOf(LogoutModel.RESULT_REMOVE_ACCOUNT_FAILED);
                        }
                        Log.i(LogoutModel.TAG, "Xiaomi account removed: " + LogoutModel.this.mAccount.name);
                        Intent accountChangedIntent = new Intent("android.accounts.LOGIN_ACCOUNTS_POST_CHANGED");
                        accountChangedIntent.putExtra(Constants.EXTRA_ACCOUNT, LogoutModel.this.mAccount);
                        accountChangedIntent.putExtra("extra_update_type", LogoutModel.RESULT_REMOVE_ACCOUNT_FAILED);
                        activity.sendBroadcast(accountChangedIntent);
                        return Integer.valueOf(LogoutModel.RESULT_REMOVE_ACCOUNT_SUCCEED);
                    }
                    Log.i(LogoutModel.TAG, "failed to delete device");
                    return Integer.valueOf(LogoutModel.RESULT_DELETE_DEVICE_FAILED);
                } catch (IllegalDeviceException e2) {
                    Log.e(LogoutModel.TAG, "failed to get device id", e2);
                    return Integer.valueOf(LogoutModel.RESULT_DEVICE_ID_GET_FAILED);
                }
            }
        };
        Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
        Void[] voidArr = new Void[RESULT_REMOVE_ACCOUNT_FAILED];
        voidArr[RESULT_REMOVE_ACCOUNT_SUCCEED] = (Void) null;
        this.mRemoveAccountTask = anonymousClass5.executeOnExecutor(executor, voidArr);
    }

    private void notifyAccountPreChanged(Activity activity, boolean wipe) {
        Log.i(TAG, "removing Xiaomi account, wipe data: " + wipe);
        Intent intent = new Intent("android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED");
        intent.putExtra(Constants.EXTRA_ACCOUNT, this.mAccount);
        intent.putExtra("extra_update_type", RESULT_REMOVE_ACCOUNT_FAILED);
        Bundle bundle = new Bundle();
        bundle.putBoolean("extra_wipe_data", wipe);
        intent.putExtra("extra_bundle", bundle);
        activity.sendBroadcast(intent);
        activity.sendBroadcast(new Intent("miui.intent.action.ACTION_IMPORT_SINA_WEIBO_CANCELED"));
    }
}
