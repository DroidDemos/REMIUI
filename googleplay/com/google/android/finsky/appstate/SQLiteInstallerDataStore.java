package com.google.android.finsky.appstate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData.Builder;
import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import java.util.ArrayList;
import java.util.Collection;

public class SQLiteInstallerDataStore implements InstallerDataStore {
    private static final String[] FULL_PROJECTION;
    private SQLiteDatabase mDb;
    private final Helper mHelper;

    private class Helper extends SQLiteOpenHelper {
        public Helper(Context context) {
            super(context, "localappstate.db", null, 16);
        }

        public void onCreate(SQLiteDatabase database) {
            database.execSQL("CREATE TABLE appstate (package_name STRING, auto_update INTEGER, desired_version INTEGER, download_uri STRING, delivery_data BLOB, delivery_data_timestamp_ms INTEGER,installer_state INTEGER, first_download_ms INTEGER, referrer STRING, account STRING, title STRING,flags INTEGER, continue_url STRING, last_notified_version INTEGER, last_update_timestamp_ms INTEGER, account_for_update STRING, auto_acquire_tags STRING, external_referrer_timestamp_ms INTEGER, persistent_flags INTEGER, permissions_version INTEGER, delivery_token STRING,PRIMARY KEY (package_name))");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onUpgrade(android.database.sqlite.SQLiteDatabase r2, int r3, int r4) {
            /*
            r1 = this;
            switch(r3) {
                case 7: goto L_0x0007;
                case 8: goto L_0x000c;
                case 9: goto L_0x0011;
                case 10: goto L_0x0016;
                case 11: goto L_0x001b;
                case 12: goto L_0x0020;
                case 13: goto L_0x0025;
                case 14: goto L_0x002a;
                case 15: goto L_0x0034;
                default: goto L_0x0003;
            };
        L_0x0003:
            r1.recreateDatabase(r2);
        L_0x0006:
            return;
        L_0x0007:
            r0 = "ALTER TABLE appstate ADD COLUMN continue_url STRING";
            r2.execSQL(r0);
        L_0x000c:
            r0 = "ALTER TABLE appstate ADD COLUMN delivery_data_timestamp_ms INTEGER";
            r2.execSQL(r0);
        L_0x0011:
            r0 = "ALTER TABLE appstate ADD COLUMN last_notified_version INTEGER";
            r2.execSQL(r0);
        L_0x0016:
            r0 = "ALTER TABLE appstate ADD COLUMN last_update_timestamp_ms INTEGER";
            r2.execSQL(r0);
        L_0x001b:
            r0 = "ALTER TABLE appstate ADD COLUMN account_for_update STRING";
            r2.execSQL(r0);
        L_0x0020:
            r0 = "ALTER TABLE appstate ADD COLUMN auto_acquire_tags STRING";
            r2.execSQL(r0);
        L_0x0025:
            r0 = "ALTER TABLE appstate ADD COLUMN external_referrer_timestamp_ms INTEGER";
            r2.execSQL(r0);
        L_0x002a:
            r0 = "ALTER TABLE appstate ADD COLUMN persistent_flags INTEGER";
            r2.execSQL(r0);
            r0 = "ALTER TABLE appstate ADD COLUMN permissions_version INTEGER";
            r2.execSQL(r0);
        L_0x0034:
            r0 = "ALTER TABLE appstate ADD COLUMN delivery_token STRING";
            r2.execSQL(r0);
            goto L_0x0006;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.appstate.SQLiteInstallerDataStore.Helper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
        }

        public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            FinskyLog.d("Downgrading InstallerDataStore from %d to %d", Integer.valueOf(oldVersion), Integer.valueOf(newVersion));
            recreateDatabase(database);
        }

        private void recreateDatabase(SQLiteDatabase database) {
            try {
                database.execSQL("DROP TABLE appstate");
            } catch (SQLException e) {
            }
            onCreate(database);
        }
    }

    static {
        FULL_PROJECTION = new String[]{"package_name", "auto_update", "desired_version", "download_uri", "delivery_data", "delivery_data_timestamp_ms", "installer_state", "first_download_ms", "referrer", "account", "title", "flags", "continue_url", "last_notified_version", "last_update_timestamp_ms", "account_for_update", "auto_acquire_tags", "external_referrer_timestamp_ms", "persistent_flags", "permissions_version", "delivery_token"};
    }

    public SQLiteInstallerDataStore(Context context) {
        this.mHelper = new Helper(context);
    }

    private void reopen() {
        Utils.ensureNotOnMainThread();
        this.mDb = this.mHelper.getWritableDatabase();
    }

    private void close() {
        Utils.ensureNotOnMainThread();
        this.mDb.close();
    }

    public synchronized InstallerData get(String packageName) {
        InstallerData installerData;
        reopen();
        Cursor cursor = this.mDb.query("appstate", FULL_PROJECTION, "package_name=?", new String[]{packageName}, null, null, null);
        try {
            if (cursor.getCount() != 1) {
                installerData = null;
            } else {
                cursor.moveToNext();
                installerData = localAppStateFromCursor(cursor);
                cursor.close();
                close();
            }
        } finally {
            cursor.close();
            close();
        }
        return installerData;
    }

    public synchronized Collection<InstallerData> getAll() {
        Collection<InstallerData> result;
        reopen();
        Cursor cursor = this.mDb.query("appstate", FULL_PROJECTION, null, null, null, null, null);
        result = new ArrayList(cursor.getCount());
        while (cursor.moveToNext()) {
            try {
                result.add(localAppStateFromCursor(cursor));
            } catch (Throwable th) {
                cursor.close();
                close();
            }
        }
        cursor.close();
        close();
        return result;
    }

    public synchronized void put(InstallerData state) {
        reopen();
        ContentValues values = new ContentValues();
        values.put("package_name", state.getPackageName());
        values.put("auto_update", Integer.valueOf(state.getAutoUpdate().ordinal()));
        values.put("desired_version", Integer.valueOf(state.getDesiredVersion()));
        values.put("download_uri", state.getDownloadUri());
        if (state.getDeliveryData() != null) {
            values.put("delivery_data", MessageNano.toByteArray(state.getDeliveryData()));
        } else {
            values.putNull("delivery_data");
        }
        values.put("delivery_data_timestamp_ms", Long.valueOf(state.getDeliveryDataTimestampMs()));
        values.put("installer_state", Integer.valueOf(state.getInstallerState()));
        values.put("first_download_ms", Long.valueOf(state.getFirstDownloadMs()));
        values.put("referrer", state.getExternalReferrer());
        values.put("account", state.getAccountName());
        values.put("title", state.getTitle());
        values.put("flags", Integer.valueOf(state.getFlags()));
        values.put("continue_url", state.getContinueUrl());
        values.put("last_notified_version", Integer.valueOf(state.getLastNotifiedVersion()));
        values.put("last_update_timestamp_ms", Long.valueOf(state.getLastUpdateTimestampMs()));
        values.put("account_for_update", state.getAccountForUpdate());
        values.put("auto_acquire_tags", Utils.commaPackStrings(state.getAutoAcquireTags()));
        values.put("external_referrer_timestamp_ms", Long.valueOf(state.getExternalReferrerTimestampMs()));
        values.put("persistent_flags", Integer.valueOf(state.getPersistentFlags()));
        values.put("permissions_version", Integer.valueOf(state.getPermissionsVersion()));
        values.put("delivery_token", state.getDeliveryToken());
        this.mDb.replace("appstate", null, values);
        close();
    }

    private InstallerData localAppStateFromCursor(Cursor cursor) {
        AutoUpdateState autoUpdate;
        int desiredVersion;
        int lastNotifiedVersion;
        String packageName = cursor.getString(0);
        if (cursor.isNull(1)) {
            autoUpdate = AutoUpdateState.DEFAULT;
        } else {
            autoUpdate = AutoUpdateState.valueOf((int) cursor.getLong(1));
        }
        if (cursor.isNull(2)) {
            desiredVersion = -1;
        } else {
            desiredVersion = cursor.getInt(2);
        }
        if (cursor.isNull(13)) {
            lastNotifiedVersion = -1;
        } else {
            lastNotifiedVersion = cursor.getInt(13);
        }
        AndroidAppDeliveryData deliveryData = null;
        if (!cursor.isNull(4)) {
            try {
                deliveryData = AndroidAppDeliveryData.parseFrom(cursor.getBlob(4));
            } catch (InvalidProtocolBufferNanoException ipbme) {
                FinskyLog.w("Couldn't parse blob as AndroidAppDeliveryData", ipbme);
            }
        }
        long deliveryDataTimestampMs = cursor.getLong(5);
        int installerState = cursor.getInt(6);
        String downloadUri = cursor.getString(3);
        long firstDownloadMs = cursor.getLong(7);
        String externalReferrer = cursor.getString(8);
        String account = cursor.getString(9);
        String title = cursor.getString(10);
        int flags = cursor.getInt(11);
        String continueUrl = cursor.getString(12);
        long lastUpdateTimestampMs = cursor.getLong(14);
        String accountForUpdate = cursor.getString(15);
        String packedAutoAcquireTags = null;
        if (!cursor.isNull(16)) {
            packedAutoAcquireTags = cursor.getString(16);
        }
        return migrateColumnData(new InstallerData(packageName, autoUpdate, desiredVersion, lastNotifiedVersion, deliveryData, deliveryDataTimestampMs, installerState, downloadUri, firstDownloadMs, externalReferrer, continueUrl, account, title, flags, lastUpdateTimestampMs, accountForUpdate, Utils.commaUnpackStrings(packedAutoAcquireTags), cursor.getLong(17), cursor.getInt(18), cursor.getInt(19), cursor.getString(20)));
    }

    InstallerData migrateColumnData(InstallerData installerData) {
        boolean rewrite = false;
        int flags = installerData.getFlags();
        int persistentFlags = installerData.getPersistentFlags();
        int permissionsVersion = installerData.getPermissionsVersion();
        if ((flags & 64) != 0) {
            rewrite = true;
            flags &= -65;
            persistentFlags |= 1;
        }
        if ((flags & 256) != 0) {
            rewrite = true;
            flags &= -257;
            permissionsVersion = 1;
        }
        if (!rewrite) {
            return installerData;
        }
        Builder b = new Builder(installerData.getPackageName());
        b.setFlags(flags);
        b.setPersistentFlags(persistentFlags);
        b.setPermissionsVersion(permissionsVersion);
        return b.build();
    }

    public synchronized void setAutoUpdate(String packageName, AutoUpdateState autoUpdate) {
        put(Builder.buildUpon(get(packageName), packageName).setAutoUpdate(autoUpdate).build());
    }

    public synchronized void setDeliveryData(String packageName, AndroidAppDeliveryData deliveryData, long timestampMs) {
        put(Builder.buildUpon(get(packageName), packageName).setDeliveryData(deliveryData, timestampMs).build());
    }

    public synchronized void setDesiredVersion(String packageName, int desiredVersion) {
        put(Builder.buildUpon(get(packageName), packageName).setDesiredVersion(desiredVersion).build());
    }

    public synchronized void setLastNotifiedVersion(String packageName, int lastNotifiedVersion) {
        put(Builder.buildUpon(get(packageName), packageName).setLastNotifiedVersion(lastNotifiedVersion).build());
    }

    public synchronized void setInstallerState(String packageName, int installerState, String downloadUri) {
        put(Builder.buildUpon(get(packageName), packageName).setInstallerState(installerState).setDownloadUri(downloadUri).build());
    }

    public synchronized void setFirstDownloadMs(String packageName, long firstDownloadMs) {
        put(Builder.buildUpon(get(packageName), packageName).setFirstDownloadMs(firstDownloadMs).build());
    }

    public synchronized void setExternalReferrer(String packageName, String externalReferrer) {
        put(Builder.buildUpon(get(packageName), packageName).setExternalReferrer(externalReferrer).build());
    }

    public synchronized void setAccount(String packageName, String accountName) {
        put(Builder.buildUpon(get(packageName), packageName).setAccountName(accountName).build());
    }

    public synchronized void setTitle(String packageName, String title) {
        put(Builder.buildUpon(get(packageName), packageName).setTitle(title).build());
    }

    public synchronized void setFlags(String packageName, int flags) {
        put(Builder.buildUpon(get(packageName), packageName).setFlags(flags).build());
    }

    public synchronized void setContinueUrl(String packageName, String continueUrl) {
        put(Builder.buildUpon(get(packageName), packageName).setContinueUrl(continueUrl).build());
    }

    public void setLastUpdateTimestampMs(String packageName, long lastUpdateTimestampMs) {
        put(Builder.buildUpon(get(packageName), packageName).setLastUpdateTimestampMs(lastUpdateTimestampMs).build());
    }

    public synchronized void setAccountForUpdate(String packageName, String accountForUpdate) {
        put(Builder.buildUpon(get(packageName), packageName).setAccountForUpdate(accountForUpdate).build());
    }

    public synchronized void setAutoAcquireTags(String packageName, String[] tags) {
        put(Builder.buildUpon(get(packageName), packageName).setAutoAcquireTags(tags).build());
    }

    public void setExternalReferrerTimestampMs(String packageName, long externalReferrerTimestampMs) {
        put(Builder.buildUpon(get(packageName), packageName).setExternalReferrerTimestampMs(externalReferrerTimestampMs).build());
    }

    public void setPersistentFlags(String packageName, int persistentFlags) {
        put(Builder.buildUpon(get(packageName), packageName).setPersistentFlags(persistentFlags).build());
    }

    public void setPermissionsVersion(String packageName, int permissionsVersion) {
        put(Builder.buildUpon(get(packageName), packageName).setPermissionsVersion(permissionsVersion).build());
    }

    public void setDeliveryToken(String packageName, String deliveryToken) {
        put(Builder.buildUpon(get(packageName), packageName).setDeliveryToken(deliveryToken).build());
    }

    public synchronized void removeLocalAppState(String packageName) {
        reopen();
        this.mDb.delete("appstate", "package_name=?", new String[]{packageName});
        close();
    }

    synchronized void clear() {
        reopen();
        this.mDb.execSQL("DELETE FROM appstate");
        close();
    }
}
