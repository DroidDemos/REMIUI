package com.android.providers.telephony;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings.System;
import android.util.Log;
import com.android.common.Search;

public class AccountsChangedBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "AccountsChangedBroadcastReceiver";

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getBundleExtra("extra_bundle");
        int type = intent.getIntExtra("extra_update_type", -1);
        if (type == 1) {
            MmsSmsUtils.clearWildMsgState(context);
            MmsSmsUtils.clearOldMsgState(context);
            MmsSmsUtils.clearCommonMarkers(context);
            if (bundle.getBoolean("extra_wipe_data", false)) {
                Log.d(TAG, "Xiaomi account removed. Wipe all message data.");
                removeEverything(context);
            } else {
                Log.d(TAG, "Xiaomi account removed. Clear all the sync states.");
                clearSyncStates(context);
            }
            MmsSmsUtils.setXiaomiAccountName(null);
        } else if (type == 2) {
            Log.d(TAG, "Xiaomi account added.");
            MmsSmsUtils.clearWildMsgState(context);
            Account account = (Account) intent.getParcelableExtra("extra_account");
            MmsSmsUtils.setXiaomiAccountName(account.name);
            checkOldMessages(context, account);
        }
    }

    private void checkOldMessages(Context context, Account account) {
        Cursor c = MmsSmsDatabaseHelper.getInstance(context).getReadableDatabase().rawQuery("select account from sms where account is not null and account != ? union select account from pdu where account is not null and account != ?", new String[]{account.name, account.name});
        if (c != null) {
            try {
                if (c.getCount() > 0) {
                    Log.i(TAG, "Found old messages for " + c.getCount() + " accounts.");
                    StringBuilder b = new StringBuilder();
                    while (c.moveToNext()) {
                        if (b.length() > 0) {
                            b.append(",");
                        }
                        b.append(c.getString(0));
                    }
                    System.putInt(context.getContentResolver(), "mms_upload_old_msg_state", 1);
                    System.putString(context.getContentResolver(), "mms_upload_old_msg_accounts", b.toString());
                }
                c.close();
            } catch (Throwable th) {
                c.close();
            }
        }
    }

    private static void removeEverything(Context context) {
        SQLiteDatabase db = MmsSmsDatabaseHelper.getInstance(context).getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("DELETE FROM canonical_addresses");
            db.execSQL("DELETE FROM hms");
            db.execSQL("DELETE FROM pending_msgs");
            db.execSQL("DELETE FROM sms");
            db.execSQL("DELETE FROM raw");
            db.execSQL("DELETE FROM attachments");
            db.execSQL("DELETE FROM threads");
            db.execSQL("DELETE FROM sr_pending");
            db.execSQL("DELETE FROM pdu;");
            db.execSQL("DELETE FROM addr;");
            db.execSQL("DELETE FROM part;");
            db.execSQL("DELETE FROM rate;");
            db.execSQL("DELETE FROM drm;");
            db.execSQL("DELETE FROM sim_cards;");
            db.execSQL("DELETE FROM private_addresses;");
            db.execSQL("DELETE FROM blocked_threads;");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private static void clearSyncStates(Context context) {
        SQLiteDatabase db = MmsSmsDatabaseHelper.getInstance(context).getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues v = new ContentValues();
            v.put("marker", Integer.valueOf(0));
            v.put("sync_state", Integer.valueOf(0));
            v.put(Search.SOURCE, (String) null);
            int smsDeleted = db.delete("sms", "deleted!=0 AND block_type<2", null);
            int smsUpdated = db.update("sms", v, null, null);
            int mmsDeleted = db.delete("pdu", "(deleted!=0 OR need_download!=0) AND block_type<2", null);
            int mmsUpdated = db.update("pdu", v, null, null);
            int privateAddrDeleted = db.delete(MmsSmsProvider.TABLE_PRIVATE_ADDRESSES, "deleted!=0", null);
            int privateAddrUpdated = db.update(MmsSmsProvider.TABLE_PRIVATE_ADDRESSES, v, null, null);
            int threadsUpdated = db.update(MmsSmsProvider.TABLE_THREADS, v, null, null);
            int simCardsDeleted = db.delete(MmsSmsProvider.TABLE_SIM_CARDS, null, null);
            db.setTransactionSuccessful();
            Log.d(TAG, String.format("Xiaomi account logged off. %d sim cards deleted, %d sms deleted, %d sms updated, %d mms deleted, %d mms updated, %d private addr deleted, %d private addr updated and %d threads updated", new Object[]{Integer.valueOf(simCardsDeleted), Integer.valueOf(smsDeleted), Integer.valueOf(smsUpdated), Integer.valueOf(mmsDeleted), Integer.valueOf(mmsUpdated), Integer.valueOf(privateAddrDeleted), Integer.valueOf(privateAddrUpdated), Integer.valueOf(threadsUpdated)}));
        } finally {
            db.endTransaction();
        }
    }
}
