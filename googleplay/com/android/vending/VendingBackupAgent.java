package com.android.vending;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.BackupManager;
import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.UpdateChecker;
import com.google.android.finsky.services.RestoreService;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.VendingPreferences;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class VendingBackupAgent extends BackupAgentHelper {
    public static void registerWithBackup(Context context) {
        if (!((Boolean) VendingPreferences.BACKED_UP.get()).booleanValue()) {
            new BackupManager(context).dataChanged();
        }
    }

    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data, ParcelFileDescriptor newState) throws IOException {
        ByteArrayOutputStream bufStream = new ByteArrayOutputStream();
        DataOutputStream outWriter = new DataOutputStream(bufStream);
        FinskyLog.d("Backing up aid: %s", FinskyLog.scrubPii(Long.toHexString(((Long) DfeApiConfig.androidId.get()).longValue())));
        writeData(data, bufStream, outWriter, "vending", aid);
        writeData(data, bufStream, outWriter, "auto_update_enabled", ((Boolean) FinskyPreferences.AUTO_UPDATE_ENABLED.get()).booleanValue());
        writeData(data, bufStream, outWriter, "update_over_wifi_only", ((Boolean) FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.get()).booleanValue());
        writeData(data, bufStream, outWriter, "auto_add_shortcuts", ((Boolean) VendingPreferences.AUTO_ADD_SHORTCUTS.get()).booleanValue());
        writeData(data, bufStream, outWriter, "notify_updates", ((Boolean) VendingPreferences.NOTIFY_UPDATES.get()).booleanValue());
        writeData(data, bufStream, outWriter, "content-filter-level", ((Integer) FinskyPreferences.contentFilterLevel.get()).intValue());
        VendingPreferences.BACKED_UP.put(Boolean.valueOf(true));
    }

    private void writeData(BackupDataOutput data, ByteArrayOutputStream bufStream, DataOutputStream outWriter, String key, long value) throws IOException {
        outWriter.writeLong(value);
        flushBufferData(data, bufStream, key);
    }

    private void writeData(BackupDataOutput data, ByteArrayOutputStream bufStream, DataOutputStream outWriter, String key, int value) throws IOException {
        outWriter.writeInt(value);
        flushBufferData(data, bufStream, key);
    }

    private void writeData(BackupDataOutput data, ByteArrayOutputStream bufStream, DataOutputStream outWriter, String key, boolean value) throws IOException {
        outWriter.writeBoolean(value);
        flushBufferData(data, bufStream, key);
    }

    private void flushBufferData(BackupDataOutput data, ByteArrayOutputStream bufStream, String key) throws IOException {
        byte[] backupData = bufStream.toByteArray();
        data.writeEntityHeader(key, backupData.length);
        data.writeEntityData(backupData, backupData.length);
        bufStream.reset();
    }

    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState) throws IOException {
        FinskyLog.d("Entered onRestore", new Object[0]);
        boolean aidRestored = false;
        Boolean autoUpdateByDefault = null;
        Boolean autoUpdateEnabled = null;
        Boolean autoUpdateWifiOnly = null;
        while (data.readNextHeader()) {
            int dataSize = data.getDataSize();
            byte[] dataBuf = new byte[dataSize];
            data.readEntityData(dataBuf, 0, dataSize);
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(dataBuf));
            String key = data.getKey();
            FinskyLog.d("Restoring key %s", key);
            if ("vending".equals(key)) {
                String aidString = Long.toHexString(in.readLong());
                FinskyLog.d("Restored aid: %s", FinskyLog.scrubPii(aidString));
                RestoreService.restoreAccounts(this, aidString, null);
                VendingPreferences.RESTORED_ANDROID_ID.put(aidString);
                aidRestored = true;
            } else if ("auto_update_enabled".equals(key)) {
                autoUpdateEnabled = Boolean.valueOf(in.readBoolean());
            } else if ("auto_update_default".equals(key)) {
                autoUpdateByDefault = Boolean.valueOf(in.readBoolean());
            } else if ("update_over_wifi_only".equals(key)) {
                autoUpdateWifiOnly = Boolean.valueOf(in.readBoolean());
            } else if ("auto_add_shortcuts".equals(key)) {
                VendingPreferences.AUTO_ADD_SHORTCUTS.put(Boolean.valueOf(in.readBoolean()));
            } else if ("notify_updates".equals(key)) {
                VendingPreferences.NOTIFY_UPDATES.put(Boolean.valueOf(in.readBoolean()));
            } else if ("content-filter-level".equals(key)) {
                FinskyPreferences.contentFilterLevel.put(Integer.valueOf(in.readInt()));
            }
        }
        if (FinskyPreferences.AUTO_UPDATE_ENABLED.exists()) {
            FinskyLog.d("Skip restore auto-update - already set locally.", new Object[0]);
        } else if (autoUpdateEnabled != null) {
            FinskyPreferences.AUTO_UPDATE_ENABLED.put(autoUpdateEnabled);
        } else if (autoUpdateByDefault != null) {
            VendingPreferences.AUTO_UPDATE_BY_DEFAULT.put(autoUpdateByDefault);
            final AppStates appStates = FinskyApp.get().getAppStates();
            appStates.load(new Runnable() {
                public void run() {
                    UpdateChecker.migrateAutoUpdateSettings(appStates);
                }
            });
        }
        if (autoUpdateWifiOnly != null && FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.exists()) {
            FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.put(autoUpdateWifiOnly);
        }
        if (!aidRestored) {
            FinskyLog.w("Restore completed, no Market aid was found", new Object[0]);
        }
        FinskyLog.d("Finished onRestore", new Object[0]);
    }
}
