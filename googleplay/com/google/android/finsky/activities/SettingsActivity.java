package com.google.android.finsky.activities;

import android.app.AlertDialog.Builder;
import android.app.backup.BackupManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.view.MenuItem;
import com.android.vending.R;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.SettingsListPreference.SettingsListEntry;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.auth.GmsCoreAuthApi;
import com.google.android.finsky.config.ContentLevel;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PurchaseAuth;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.SelfUpdate.SelfUpdateResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.GetSelfUpdateHelper;
import com.google.android.finsky.utils.GetSelfUpdateHelper.Listener;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.utils.SelfUpdateScheduler;
import com.google.android.finsky.utils.Utils;
import com.google.android.finsky.utils.VendingPreferences;
import com.google.android.gms.auth.firstparty.dataservice.GoogleAccountDataServiceClient;
import com.google.android.gms.auth.firstparty.dataservice.ReauthSettingsRequest;
import com.google.android.gms.auth.firstparty.dataservice.ReauthSettingsResponse;

public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
    private static Boolean sSelfUpdateChecked;
    private String mAccountName;
    private FinskyEventLog mEventLog;
    private boolean mIsResumed;
    private NavigationManager mNavigationManager;
    private PlayStoreUiElementNode mPageNode;

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$finsky$activities$SettingsActivity$AutoUpdateEntry;

        static {
            $SwitchMap$com$google$android$finsky$activities$SettingsActivity$AutoUpdateEntry = new int[AutoUpdateEntry.values().length];
            try {
                $SwitchMap$com$google$android$finsky$activities$SettingsActivity$AutoUpdateEntry[AutoUpdateEntry.AUTO_UPDATE_NEVER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$android$finsky$activities$SettingsActivity$AutoUpdateEntry[AutoUpdateEntry.AUTO_UPDATE_ALWAYS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$android$finsky$activities$SettingsActivity$AutoUpdateEntry[AutoUpdateEntry.AUTO_UPDATE_WIFI.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private enum AutoUpdateEntry implements SettingsListEntry {
        AUTO_UPDATE_NEVER(R.string.auto_update_value_disabled),
        AUTO_UPDATE_ALWAYS(R.string.auto_update_value_enabled),
        AUTO_UPDATE_WIFI(R.string.auto_update_value_wifi_only);
        
        private final int mResource;

        private AutoUpdateEntry(int resource) {
            this.mResource = resource;
        }

        public int getResource() {
            return this.mResource;
        }
    }

    private enum PurchaseAuthEntry implements SettingsListEntry {
        PASSWORD_ALWAYS(2, R.string.purchase_auth_value_always),
        PASSWORD_SESSION(1, R.string.purchase_auth_value_session),
        NEVER(0, R.string.purchase_auth_value_never);
        
        private final int mPurchaseAuth;
        private final int mResource;

        private PurchaseAuthEntry(int purchaseAuth, int resource) {
            this.mPurchaseAuth = purchaseAuth;
            this.mResource = resource;
        }

        public int getResource() {
            return this.mResource;
        }
    }

    public SettingsActivity() {
        this.mNavigationManager = new FakeNavigationManager(this);
    }

    static {
        sSelfUpdateChecked = null;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        this.mEventLog = FinskyApp.get().getEventLogger();
        this.mAccountName = FinskyApp.get().getCurrentAccountName();
        if (this.mAccountName == null) {
            FinskyLog.d("Exit SettingsActivity - no current account.", new Object[0]);
            finish();
            return;
        }
        this.mPageNode = new GenericUiElementNode(12, null, null, null);
        if (savedInstanceState == null) {
            this.mEventLog.logPathImpression(0, this.mPageNode);
        }
        getListView().setCacheColorHint(getResources().getColor(R.color.play_main_background));
    }

    protected void onResume() {
        super.onResume();
        this.mIsResumed = true;
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        configureUpdateNotifications(preferenceScreen);
        configureAutoUpdateMode(preferenceScreen);
        configureAutoAddShortcuts(preferenceScreen);
        configureUserControlsSection(preferenceScreen);
        configureAboutSection(preferenceScreen);
        preferenceScreen.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    protected void onPause() {
        super.onPause();
        this.mIsResumed = false;
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        String key = preference.getKey();
        boolean doBackup = false;
        if ("update-notifications".equals(key)) {
            VendingPreferences.NOTIFY_UPDATES.put(Boolean.valueOf(((CheckBoxPreference) preference).isChecked()));
        } else if ("auto-add-shortcuts".equals(key)) {
            VendingPreferences.AUTO_ADD_SHORTCUTS.put(Boolean.valueOf(((CheckBoxPreference) preference).isChecked()));
            doBackup = true;
        } else if ("clear-history".equals(key)) {
            FinskyApp.get().getRecentSuggestions().clearHistory();
        } else if ("content-level".equals(key)) {
            startActivityForResult(IntentUtils.createAccountSpecificIntent(this, ContentFilterActivity.class, "authAccount", this.mAccountName), 30);
        } else if ("os-licenses".equals(key)) {
            startActivity(WebViewDialog.getIntent(this, R.string.os_licenses_label, "file:///android_asset/licenses.html"));
        } else if ("build-version".equals(key)) {
            doSelfUpdateCheck();
        }
        if (doBackup) {
            new BackupManager(this).dataChanged();
        }
        return true;
    }

    private void doSelfUpdateCheck() {
        this.mEventLog.logClickEvent(282, null, this.mPageNode);
        if (!((Boolean) G.userCheckForSelfUpdateEnabled.get()).booleanValue()) {
            return;
        }
        if (sSelfUpdateChecked != null) {
            showSelfUpdateCheckResult(sSelfUpdateChecked.booleanValue());
            return;
        }
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        dfeApi.invalidateSelfUpdateCache();
        final String accountName = dfeApi.getAccountName();
        GetSelfUpdateHelper.getSelfUpdate(dfeApi, new Listener() {
            public void onResponse(SelfUpdateResponse response) {
                SelfUpdateScheduler selfUpdateScheduler = FinskyApp.get().getSelfUpdateScheduler();
                SettingsActivity.sSelfUpdateChecked = Boolean.valueOf(selfUpdateScheduler.checkForSelfUpdate(selfUpdateScheduler.getNewVersion(response), accountName));
                SettingsActivity.this.showSelfUpdateCheckResult(SettingsActivity.sSelfUpdateChecked.booleanValue());
            }

            public void onErrorResponse(VolleyError error) {
                SettingsActivity.this.showSelfUpdateCheckResult(false);
            }
        });
    }

    private void showSelfUpdateCheckResult(boolean selfUpdateFound) {
        if (this.mIsResumed) {
            Builder builder = new Builder(this);
            builder.setMessage(selfUpdateFound ? R.string.settings_self_update_new_version_yes : R.string.settings_self_update_new_version_no);
            builder.setPositiveButton(R.string.ok, null);
            builder.create().show();
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if ("auto-update-mode".equals(key)) {
            SettingsListPreference listPreference = (SettingsListPreference) getPreferenceScreen().findPreference(key);
            AutoUpdateEntry entry = AutoUpdateEntry.valueOf(listPreference.getValue());
            boolean autoUpdateEnabled = false;
            boolean autoUpdateWifiOnly = false;
            switch (AnonymousClass4.$SwitchMap$com$google$android$finsky$activities$SettingsActivity$AutoUpdateEntry[entry.ordinal()]) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    autoUpdateEnabled = true;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    autoUpdateEnabled = true;
                    autoUpdateWifiOnly = true;
                    break;
                default:
                    FinskyLog.wtf("Unexpected list pref value %s", value);
                    break;
            }
            this.mEventLog.logSettingsBackgroundEvent(402, Integer.valueOf(entry.ordinal()), Integer.valueOf(getCurrentAutoUpdateEntry().ordinal()), null);
            FinskyPreferences.AUTO_UPDATE_ENABLED.put(Boolean.valueOf(autoUpdateEnabled));
            FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.put(Boolean.valueOf(autoUpdateWifiOnly));
            new BackupManager(this).dataChanged();
            listPreference.updateListPreferenceSummary();
        } else if ("purchase-auth".equals(key)) {
            int newPurchaseAuth = PurchaseAuthEntry.valueOf(((ListPreference) getPreferenceScreen().findPreference(key)).getValue()).mPurchaseAuth;
            int previousPurchaseAuth = PurchaseAuth.getPurchaseAuthType(this.mAccountName);
            if (previousPurchaseAuth != newPurchaseAuth) {
                startAuthChallenge(previousPurchaseAuth, newPurchaseAuth);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle extraParams;
        if (requestCode == 30 && resultCode == -1) {
            int contentFilterLevel = data.getIntExtra("ContentFilterActivity_selectedFilterLevel", -100);
            if (contentFilterLevel == -100) {
                FinskyLog.wtf("Content filter returned code 'OK' but no level to set", new Object[0]);
                return;
            }
            extraParams = new Bundle();
            extraParams.putInt("content-level-to-set", contentFilterLevel);
            String currentPin = (String) FinskyPreferences.contentPin.get();
            if (TextUtils.isEmpty(currentPin)) {
                startActivityForResult(PinEntryDialog.getIntent(this, R.string.pin_setup_label, R.string.pin_setup_summary, null, extraParams), 33);
            } else {
                startActivityForResult(PinEntryDialog.getIntent(this, R.string.pin_entry_label, R.string.pin_entry_summary, currentPin, extraParams), 31);
            }
        } else if (requestCode == 33 && resultCode == -1) {
            extraParams = data.getBundleExtra("PinEntryDialog-extra-params");
            newPin = data.getStringExtra("PinEntryDialog-result-pin");
            if (TextUtils.isEmpty(newPin)) {
                FinskyLog.wtf("Create PIN result OK but no PIN sent back.", new Object[0]);
            } else {
                startActivityForResult(PinEntryDialog.getIntent(this, R.string.pin_confirm_label, R.string.pin_confirm_summary, newPin, extraParams), 34);
            }
        } else if (requestCode == 34 && resultCode == -1) {
            newPin = data.getStringExtra("PinEntryDialog-result-pin");
            if (TextUtils.isEmpty(newPin)) {
                FinskyLog.wtf("Confirm PIN result OK but no PIN sent back.", new Object[0]);
                return;
            }
            FinskyPreferences.contentPin.put(newPin);
            setContentFilterLevel(data.getBundleExtra("PinEntryDialog-extra-params"));
        } else if (requestCode == 31 && resultCode == -1) {
            extraParams = data.getBundleExtra("PinEntryDialog-extra-params");
            if (extraParams.getInt("content-level-to-set", -100) == ContentLevel.SHOW_ALL.getValue()) {
                FinskyPreferences.contentPin.remove();
            }
            setContentFilterLevel(extraParams);
        } else if (requestCode == 32 && resultCode == -1) {
            extraParams = data.getBundleExtra("GaiaAuthActivity_extraParams");
            int previousPurchaseAuth = extraParams.getInt("purchase-auth-previous", -1);
            int newPurchaseAuth = extraParams.getInt("purchase-auth-new", -1);
            if (newPurchaseAuth == -1) {
                FinskyLog.wtf("Missing new value for PurchaseAuth", new Object[0]);
            } else {
                PurchaseAuth.setAndLogPurchaseAuth(this.mAccountName, newPurchaseAuth, Integer.valueOf(previousPurchaseAuth), this.mEventLog, "settings-page");
                if (newPurchaseAuth != 1) {
                    FinskyPreferences.lastGaiaAuthTimestamp.get(this.mAccountName).remove();
                }
            }
            ((SettingsListPreference) getPreferenceScreen().findPreference("purchase-auth")).updateListPreferenceSummary();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void startAuthChallenge(final int previousPurchaseAuth, final int newPurchaseAuth) {
        if (GmsCoreAuthApi.isAvailable()) {
            new AsyncTask<Void, Void, ReauthSettingsResponse>() {
                protected ReauthSettingsResponse doInBackground(Void... params) {
                    return new GoogleAccountDataServiceClient(FinskyApp.get()).getReauthSettings(new ReauthSettingsRequest(SettingsActivity.this.mAccountName, false));
                }

                protected void onPostExecute(ReauthSettingsResponse response) {
                    if (!SettingsActivity.this.isDestroyed()) {
                        if (response != null) {
                            SettingsActivity.this.handleReauthSettingsResponse(response, previousPurchaseAuth, newPurchaseAuth);
                        } else {
                            SettingsActivity.this.getReauthSettingsOverNetwork(previousPurchaseAuth, newPurchaseAuth);
                        }
                    }
                }
            }.execute(new Void[0]);
        } else {
            startGaiaAuthActivity(previousPurchaseAuth, newPurchaseAuth, false, false);
        }
    }

    private void getReauthSettingsOverNetwork(final int previousPurchaseAuth, final int newPurchaseAuth) {
        Utils.executeMultiThreaded(new AsyncTask<Void, Void, ReauthSettingsResponse>() {
            protected ReauthSettingsResponse doInBackground(Void... params) {
                return new GoogleAccountDataServiceClient(FinskyApp.get()).getReauthSettings(new ReauthSettingsRequest(SettingsActivity.this.mAccountName, true));
            }

            protected void onPostExecute(ReauthSettingsResponse response) {
                if (!SettingsActivity.this.isDestroyed()) {
                    SettingsActivity.this.handleReauthSettingsResponse(response, previousPurchaseAuth, newPurchaseAuth);
                }
            }
        }, new Void[0]);
    }

    private void handleReauthSettingsResponse(ReauthSettingsResponse response, int previousPurchaseAuth, int newPurchaseAuth) {
        boolean useGmsCoreForAuth = false;
        boolean usePinBasedAuth = false;
        if (response != null && response.status == 0) {
            useGmsCoreForAuth = true;
            if (response.pin != null && "ACTIVE".equals(response.pin.status)) {
                usePinBasedAuth = true;
            }
        }
        startGaiaAuthActivity(previousPurchaseAuth, newPurchaseAuth, useGmsCoreForAuth, usePinBasedAuth);
    }

    private void startGaiaAuthActivity(int previousPurchaseAuth, int newPurchaseAuth, boolean useGmsCoreForAuth, boolean usePinBasedAuth) {
        boolean showWarning = newPurchaseAuth != 2;
        Bundle extraParams = new Bundle();
        extraParams.putInt("purchase-auth-previous", previousPurchaseAuth);
        extraParams.putInt("purchase-auth-new", newPurchaseAuth);
        startActivityForResult(GaiaAuthActivity.getIntent(this, this.mAccountName, showWarning, useGmsCoreForAuth, usePinBasedAuth, extraParams), 32);
    }

    private void setContentFilterLevel(Bundle extraParams) {
        int contentFilterLevel = extraParams.getInt("content-level-to-set", -100);
        if (contentFilterLevel == -100) {
            FinskyLog.wtf("Content filter authenticated but no level to set", new Object[0]);
            return;
        }
        this.mEventLog.logSettingsBackgroundEvent(401, Integer.valueOf(contentFilterLevel), (Integer) FinskyPreferences.contentFilterLevel.get(), "settings-page");
        FinskyPreferences.contentFilterLevel.put(Integer.valueOf(contentFilterLevel));
        setResult(40);
        finish();
    }

    private void configureAboutSection(PreferenceScreen preferenceScreen) {
        preferenceScreen.findPreference("build-version").setSummary(getString(R.string.market_version, new Object[]{FinskyApp.get().getPackageInfoRepository().getVersionName(FinskyApp.get().getPackageName())}));
    }

    private void configureUserControlsSection(PreferenceScreen preferenceScreen) {
        if (((Boolean) G.vendingHideContentRating.get()).booleanValue()) {
            preferenceScreen.removePreference(preferenceScreen.findPreference("content-level"));
        }
        SettingsListPreference listPreference = (SettingsListPreference) preferenceScreen.findPreference("purchase-auth");
        listPreference.setTitle(R.string.purchase_auth_settings_label);
        listPreference.setEntriesAndValues(PurchaseAuthEntry.values());
        listPreference.setValueAndUpdateSummary(getCurrentPurchaseAuthEntry());
    }

    private PurchaseAuthEntry getCurrentPurchaseAuthEntry() {
        int purchaseAuth = PurchaseAuth.getPurchaseAuthType(this.mAccountName);
        for (PurchaseAuthEntry entry : PurchaseAuthEntry.values()) {
            if (entry.mPurchaseAuth == purchaseAuth) {
                return entry;
            }
        }
        throw new IllegalStateException("PurchaseAuth undefined in PurchaseAuthEntry: " + purchaseAuth);
    }

    private void configureUpdateNotifications(PreferenceScreen preferenceScreen) {
        ((CheckBoxPreference) preferenceScreen.findPreference("update-notifications")).setChecked(((Boolean) VendingPreferences.NOTIFY_UPDATES.get()).booleanValue());
    }

    private void configureAutoUpdateMode(PreferenceScreen preferenceScreen) {
        SettingsListPreference listPreference = (SettingsListPreference) preferenceScreen.findPreference("auto-update-mode");
        AutoUpdateEntry[] entries = AutoUpdateEntry.values();
        if (!FinskyApp.get().getInstallPolicies().hasMobileNetwork()) {
            AutoUpdateEntry[] entriesWithNoWifi = new AutoUpdateEntry[2];
            System.arraycopy(entries, 0, entriesWithNoWifi, 0, 2);
            entries = entriesWithNoWifi;
        }
        listPreference.setEntriesAndValues(entries);
        listPreference.setValueAndUpdateSummary(getCurrentAutoUpdateEntry());
    }

    private AutoUpdateEntry getCurrentAutoUpdateEntry() {
        boolean showWifiOption = FinskyApp.get().getInstallPolicies().hasMobileNetwork();
        boolean enabled = ((Boolean) FinskyPreferences.AUTO_UPDATE_ENABLED.get()).booleanValue();
        boolean wifiOnly = ((Boolean) FinskyPreferences.AUTO_UPDATE_WIFI_ONLY.get()).booleanValue();
        if (!enabled) {
            return AutoUpdateEntry.AUTO_UPDATE_NEVER;
        }
        if (showWifiOption && wifiOnly) {
            return AutoUpdateEntry.AUTO_UPDATE_WIFI;
        }
        return AutoUpdateEntry.AUTO_UPDATE_ALWAYS;
    }

    private void configureAutoAddShortcuts(PreferenceScreen preferenceScreen) {
        ((CheckBoxPreference) preferenceScreen.findPreference("auto-add-shortcuts")).setChecked(((Boolean) VendingPreferences.AUTO_ADD_SHORTCUTS.get()).booleanValue());
    }
}
