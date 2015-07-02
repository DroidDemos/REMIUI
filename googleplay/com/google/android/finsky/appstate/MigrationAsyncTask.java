package com.google.android.finsky.appstate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.appstate.AppStates.AppState;
import com.google.android.finsky.appstate.InstallerDataStore.AutoUpdateState;
import com.google.android.finsky.config.PurchaseAuth;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.wallet.instrumentmanager.R;
import java.io.File;
import java.util.Map;

public class MigrationAsyncTask extends AsyncTask<Void, Void, Map<String, AutoUpdateState>> {
    private final Boolean FINSKY_AUTOUPDATE_IS_STRINGS;
    private final String FINSKY_COLUMN_AUTO_UPDATE;
    private final String FINSKY_COLUMN_PACKAGE_NAME;
    private final String FINSKY_DATABASE_NAME;
    private final String FINSKY_TABLE_NAME;
    private final Boolean GRANOLA_AUTOUPDATE_IS_STRINGS;
    private final String GRANOLA_COLUMN_AUTO_UPDATE;
    private final String GRANOLA_COLUMN_PACKAGE_NAME;
    private final String GRANOLA_DATABASE_NAME;
    private final String GRANOLA_TABLE_NAME;
    private final AppStates mAppStates;
    private final Context mContext;

    public MigrationAsyncTask(Context context) {
        this.GRANOLA_DATABASE_NAME = "assets14.db";
        this.GRANOLA_TABLE_NAME = "assets10";
        this.GRANOLA_COLUMN_PACKAGE_NAME = "package_name";
        this.GRANOLA_COLUMN_AUTO_UPDATE = "auto_update";
        this.GRANOLA_AUTOUPDATE_IS_STRINGS = Boolean.valueOf(false);
        this.FINSKY_DATABASE_NAME = "market_assets.db";
        this.FINSKY_TABLE_NAME = "assets";
        this.FINSKY_COLUMN_PACKAGE_NAME = "PACKAGE";
        this.FINSKY_COLUMN_AUTO_UPDATE = "AUTO_UPDATE";
        this.FINSKY_AUTOUPDATE_IS_STRINGS = Boolean.valueOf(true);
        this.mContext = context;
        this.mAppStates = FinskyApp.get().getAppStates();
    }

    protected Map<String, AutoUpdateState> doInBackground(Void... voids) {
        this.mAppStates.blockingLoad();
        Map<String, AutoUpdateState> data = Maps.newHashMap();
        collectLegacyData(data, "assets14.db", "assets10", "package_name", "auto_update", this.GRANOLA_AUTOUPDATE_IS_STRINGS.booleanValue());
        collectLegacyData(data, "market_assets.db", "assets", "PACKAGE", "AUTO_UPDATE", this.FINSKY_AUTOUPDATE_IS_STRINGS.booleanValue());
        return data;
    }

    protected void onPostExecute(Map<String, AutoUpdateState> legacyMap) {
        for (String packageName : legacyMap.keySet()) {
            AppState appState = this.mAppStates.getApp(packageName);
            if (!(appState == null || appState.packageManagerState == null)) {
                if (appState.installerData == null || appState.installerData.getAutoUpdate() == AutoUpdateState.DEFAULT) {
                    FinskyLog.d("Migrating %s autoupdate = %s", packageName, (AutoUpdateState) legacyMap.get(packageName));
                    this.mAppStates.getInstallerDataStore().setAutoUpdate(packageName, newState);
                }
            }
        }
        this.mContext.deleteDatabase("assets14.db");
        this.mContext.deleteDatabase("market_assets.db");
        UpdateChecker.migrateAutoUpdateSettings(this.mAppStates);
        PurchaseAuth.migrateAndCleanupPreferences(AccountHandler.getAccounts(this.mContext));
    }

    private void collectLegacyData(Map<String, AutoUpdateState> data, String dbName, String tableName, String columnPackageName, String columnAutoUpdate, boolean autoUpdateIsStrings) {
        File dbFile = this.mContext.getDatabasePath(dbName);
        if (dbFile.exists()) {
            try {
                SQLiteDatabase db = SQLiteDatabase.openDatabase(dbFile.getAbsolutePath(), null, 1);
                FinskyLog.d("Reading from legacy database %s", dbName);
                Cursor cursor = db.query(tableName, new String[]{columnPackageName, columnAutoUpdate}, null, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            String packageName = cursor.getString(0);
                            if (!TextUtils.isEmpty(packageName)) {
                                int autoUpdateValue = 0;
                                if (autoUpdateIsStrings) {
                                    String autoUpdateLabel = cursor.getString(1);
                                    if ("DISABLED".equals(autoUpdateLabel)) {
                                        autoUpdateValue = 1;
                                    } else if ("ENABLED".equals(autoUpdateLabel)) {
                                        autoUpdateValue = 2;
                                    }
                                } else {
                                    autoUpdateValue = cursor.getInt(1);
                                }
                                switch (autoUpdateValue) {
                                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                        data.put(packageName, AutoUpdateState.DISABLED);
                                        break;
                                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                        data.put(packageName, AutoUpdateState.USE_GLOBAL);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } finally {
                            cursor.close();
                        }
                    }
                }
                db.close();
            } catch (SQLiteException e) {
                FinskyLog.e("Unable to open %s because %s", dbName, e.toString());
            }
        }
    }
}
