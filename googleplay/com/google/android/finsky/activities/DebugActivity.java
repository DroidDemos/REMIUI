package com.google.android.finsky.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.DfeApiConfig;
import com.google.android.finsky.config.G;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DebugSettings.DebugSettingsResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.utils.config.GservicesValue;
import com.google.android.play.utils.config.PlayG;
import com.google.android.volley.guava.UrlRules;
import com.google.android.volley.guava.UrlRules.Rule;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

public class DebugActivity extends PreferenceActivity {
    private static final String ORIGINAL_DFE_URL;
    private static final String ORIGINAL_VENDING_API_URL;
    private final ICsvSelectorHelper<CarrierOverride> mCarrierHelper;
    private final ICsvSelectorHelper<EnvironmentOverride> mEnvironmentHelper;
    private NavigationManager mNavigationManager;

    public interface ICsvSelectorHelper<T> {
        T fromStringList(String[] strArr);

        boolean isSelected(T t);

        void selectItem(T t);
    }

    public static class CarrierOverride {
        public final String countryCode;
        public final String countryName;
        public final String simCode;

        public CarrierOverride(String countryName, String countryCode, String simCode) {
            this.countryName = countryName;
            this.countryCode = countryCode;
            this.simCode = simCode;
        }
    }

    public static class EnvironmentOverride {
        public final String dfeBaseUrl;
        public final String escrowUrl;
        public final String vendingBaseUrl;

        public EnvironmentOverride(String dfeBaseUrl, String vendingBaseUrl, String escrowUrl) {
            this.dfeBaseUrl = dfeBaseUrl;
            this.vendingBaseUrl = vendingBaseUrl;
            this.escrowUrl = escrowUrl;
        }
    }

    public DebugActivity() {
        this.mNavigationManager = new FakeNavigationManager(this);
        this.mCarrierHelper = new ICsvSelectorHelper<CarrierOverride>() {
            public CarrierOverride fromStringList(String[] list) {
                if (list != null) {
                    return new CarrierOverride(list[0], list[1], list[2]);
                }
                return new CarrierOverride(null, null, null);
            }

            public boolean isSelected(CarrierOverride country) {
                return TextUtils.equals((String) DfeApiConfig.ipCountryOverride.get(), country.countryCode) && TextUtils.equals((String) DfeApiConfig.mccMncOverride.get(), country.simCode);
            }

            public void selectItem(CarrierOverride newCarrier) {
                DebugActivity.this.selectCarrier(newCarrier);
            }
        };
        this.mEnvironmentHelper = new ICsvSelectorHelper<EnvironmentOverride>() {
            public EnvironmentOverride fromStringList(String[] list) {
                if (list != null) {
                    return new EnvironmentOverride(list[1], list[2], list[4]);
                }
                return new EnvironmentOverride(null, null, null);
            }

            public boolean isSelected(EnvironmentOverride environment) {
                String url = environment.dfeBaseUrl;
                Rule rule = UrlRules.getRules(DebugActivity.this.getContentResolver()).matchRule(DebugActivity.ORIGINAL_DFE_URL);
                return TextUtils.equals(url, rule == Rule.DEFAULT ? DebugActivity.ORIGINAL_DFE_URL : rule.apply(DebugActivity.ORIGINAL_DFE_URL));
            }

            public void selectItem(EnvironmentOverride newEnvironment) {
                DebugActivity.this.selectEnvironment(newEnvironment);
            }
        };
    }

    static {
        ORIGINAL_DFE_URL = DfeApi.BASE_URI.toString();
        ORIGINAL_VENDING_API_URL = "https://android.clients.google.com/vending/api/ApiRequest".replace("api/ApiRequest", "");
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (((Boolean) G.debugOptionsEnabled.get()).booleanValue()) {
            addPreferencesFromResource(R.xml.debug);
            setupImageDebugging();
            setupSkipDfeCache();
            setupShowStagingMerchData();
            setupPrexDisabledCheckbox();
            setupVolleyLogging();
            setupFakeItemRater();
            setupDetailsPageV2();
            getListView().setCacheColorHint(getResources().getColor(R.color.play_main_background));
            return;
        }
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                FinskyApp.get().getLibraryReplicators().replicateAllAccounts(new Runnable() {
                    public void run() {
                        FinskyLog.d("Replicated libraries for all accounts.", new Object[0]);
                    }
                }, "DebugActivity");
                return;
            default:
                return;
        }
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean z = true;
        String key = preference.getKey();
        boolean z2;
        if ("image_debugging".equals(key)) {
            GservicesValue gservicesValue = PlayG.debugImageSizes;
            if (((Boolean) PlayG.debugImageSizes.get()).booleanValue()) {
                z2 = false;
            } else {
                z2 = true;
            }
            override(gservicesValue, Boolean.toString(z2).toLowerCase());
            return true;
        }
        if ("skip_all_caches".equals(key)) {
            gservicesValue = DfeApiConfig.skipAllCaches;
            if (((Boolean) DfeApiConfig.skipAllCaches.get()).booleanValue()) {
                z = false;
            }
            override(gservicesValue, Boolean.toString(z).toLowerCase());
        } else if ("environments".equals(key)) {
            buildSelectEnvironmentDialog();
            return true;
        } else if ("clear_cache".equals(key)) {
            clearCacheAndRestart(false);
            return true;
        } else if ("show_staging_data".equals(key)) {
            gservicesValue = DfeApiConfig.showStagingData;
            if (((Boolean) DfeApiConfig.showStagingData.get()).booleanValue()) {
                z2 = false;
            } else {
                z2 = true;
            }
            override(gservicesValue, Boolean.toString(z2).toLowerCase());
            clearCacheAndRestart(false);
            return true;
        } else if ("disable_personalization".equals(key)) {
            gservicesValue = DfeApiConfig.prexDisabled;
            if (((Boolean) DfeApiConfig.prexDisabled.get()).booleanValue()) {
                z2 = false;
            } else {
                z2 = true;
            }
            override(gservicesValue, Boolean.toString(z2));
            clearCacheAndRestart(false);
            return true;
        } else if ("reset_all".equals(key)) {
            override(PlayG.debugImageSizes, "false");
            selectEnvironment(new EnvironmentOverride(null, null, null));
            selectCarrier(new CarrierOverride(null, null, null));
            clearCacheAndRestart(false);
            return true;
        } else if ("country".equals(key)) {
            buildSelectCountryDialog();
            return true;
        } else if ("play_country".equals(key)) {
            loadPlayCountryAndShowDialog();
            return true;
        } else if ("export_library".equals(key)) {
            exportDatabase("localappstate.db");
            exportDatabase("library.db");
            Toast.makeText(this, "Finished database exports", 0).show();
            return true;
        } else if ("dump_library_state".equals(key)) {
            Log.d("FinskyLibrary", "------------ LIBRARY DUMP BEGIN");
            FinskyApp.get().getLibraries().dumpState();
            FinskyApp.get().getLibraryReplicators().dumpState();
            Log.d("FinskyLibrary", "------------ LIBRARY DUMP END");
            Toast.makeText(this, "Library state dumped to logcat.", 0).show();
            return true;
        } else if ("fake_item_rater".equals(key)) {
            r4 = FinskyPreferences.internalFakeItemRaterEnabled;
            if (((Boolean) FinskyPreferences.internalFakeItemRaterEnabled.get()).booleanValue()) {
                z = false;
            }
            r4.put(Boolean.valueOf(z));
        } else if ("details_page_v2".equals(key)) {
            r4 = FinskyPreferences.internalDetailsPageV2Enabled;
            if (((Boolean) FinskyPreferences.internalDetailsPageV2Enabled.get()).booleanValue()) {
                z = false;
            }
            r4.put(Boolean.valueOf(z));
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void exportDatabase(String name) {
        boolean exported = false;
        try {
            File inputFile = getDatabasePath(name);
            if (inputFile.exists() && inputFile.canRead()) {
                File outputFile = new File(Environment.getExternalStorageDirectory(), name);
                FileInputStream inputStream = new FileInputStream(inputFile);
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                FileChannel input = inputStream.getChannel();
                FileChannel output = outputStream.getChannel();
                input.transferTo(0, input.size(), output);
                input.close();
                output.close();
                inputStream.close();
                outputStream.close();
                exported = true;
            }
        } catch (IOException e) {
            FinskyLog.e("Unable to export: %s", e.getMessage());
        }
        if (!exported) {
            Toast.makeText(this, "Unable to export " + name, 0).show();
        }
    }

    private void setupImageDebugging() {
        ((CheckBoxPreference) findPreference("image_debugging")).setChecked(((Boolean) PlayG.debugImageSizes.get()).booleanValue());
    }

    private void setupSkipDfeCache() {
        ((CheckBoxPreference) findPreference("skip_all_caches")).setChecked(((Boolean) DfeApiConfig.skipAllCaches.get()).booleanValue());
    }

    private void setupShowStagingMerchData() {
        ((CheckBoxPreference) findPreference("show_staging_data")).setChecked(((Boolean) DfeApiConfig.showStagingData.get()).booleanValue());
    }

    private void setupPrexDisabledCheckbox() {
        ((CheckBoxPreference) findPreference("disable_personalization")).setChecked(((Boolean) DfeApiConfig.prexDisabled.get()).booleanValue());
    }

    private void setupVolleyLogging() {
        CheckBoxPreference pref = (CheckBoxPreference) findPreference("verbose_volley_logging");
        pref.setChecked(Log.isLoggable("Volley", 2));
        pref.setEnabled(false);
    }

    private void setupFakeItemRater() {
        ((CheckBoxPreference) findPreference("fake_item_rater")).setChecked(((Boolean) FinskyPreferences.internalFakeItemRaterEnabled.get()).booleanValue());
    }

    private void setupDetailsPageV2() {
        ((CheckBoxPreference) findPreference("details_page_v2")).setChecked(((Boolean) FinskyPreferences.internalDetailsPageV2Enabled.get()).booleanValue());
    }

    public void buildSelectCountryDialog() {
        buildSelectorFromCsv(this.mCarrierHelper, "carriers.csv", "Switching country...", R.string.select_carrier);
    }

    private void loadPlayCountryAndShowDialog() {
        FinskyApp.get().getDfeApi().debugSettings(null, new Listener<DebugSettingsResponse>() {
            public void onResponse(DebugSettingsResponse response) {
                DebugActivity.this.buildPlayCountryDialog(response.playCountryOverride);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DebugActivity.this, DebugActivity.this.getString(R.string.debug_play_country_override_failure), 1).show();
                FinskyLog.d("%s Volley error message: %s", userMessage, error.getMessage());
            }
        });
    }

    private void buildPlayCountryDialog(String existingOverride) {
        View layout = LayoutInflater.from(this).inflate(R.layout.debug_play_country_dialog, null);
        TextView current = (TextView) layout.findViewById(R.id.current);
        if (TextUtils.isEmpty(existingOverride)) {
            existingOverride = getString(R.string.debug_play_country_override_none);
        }
        current.setText(getString(R.string.debug_play_country_override_current, new Object[]{existingOverride}));
        final EditText edit = (EditText) layout.findViewById(R.id.edit);
        AlertDialog dialog = new Builder(this).setTitle(getString(R.string.debug_play_country_override_title)).setView(layout).setPositiveButton(getString(R.string.ok), new OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                DebugActivity.this.updatePlayCountry(edit.getText().toString());
            }
        }).setNegativeButton(getString(R.string.cancel), new OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        }).create();
        dialog.setOnShowListener(new OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                if (edit.requestFocus()) {
                    ((InputMethodManager) DebugActivity.this.getSystemService("input_method")).showSoftInput(edit, 0);
                }
            }
        });
        dialog.show();
    }

    private void updatePlayCountry(final String playCountryCode) {
        FinskyApp.get().getDfeApi().debugSettings(playCountryCode, new Listener<DebugSettingsResponse>() {
            public void onResponse(DebugSettingsResponse response) {
                if (playCountryCode.equals(response.playCountryOverride)) {
                    DebugActivity debugActivity = DebugActivity.this;
                    Object[] objArr = new Object[1];
                    objArr[0] = TextUtils.isEmpty(response.playCountryOverride) ? DebugActivity.this.getString(R.string.debug_play_country_override_none) : response.playCountryOverride;
                    Toast.makeText(DebugActivity.this, debugActivity.getString(R.string.debug_play_country_override_update_success, objArr), 1).show();
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            DebugActivity.this.clearCacheAndRestart(true);
                        }
                    }, 3000);
                    return;
                }
                Toast.makeText(DebugActivity.this, R.string.debug_play_country_override_update_failed, 1).show();
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DebugActivity.this, R.string.debug_play_country_override_update_failed, 1).show();
            }
        });
    }

    public void buildSelectEnvironmentDialog() {
        buildSelectorFromCsv(this.mEnvironmentHelper, "marketenvs.csv", "Switching environment...", R.string.select_environment);
    }

    public <T> void buildSelectorFromCsv(final ICsvSelectorHelper<T> helper, String filename, final String toastText, int dialogTitle) {
        final List<T> list = Lists.newArrayList();
        List<String> options = Lists.newArrayList();
        int checkedIndex = getOptionsFromCsv(filename, list, options, helper);
        Builder dialog = new Builder(this);
        dialog.setTitle(dialogTitle);
        dialog.setSingleChoiceItems((String[]) options.toArray(new String[options.size()]), checkedIndex, new OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                dialog.dismiss();
                Toast.makeText(DebugActivity.this, toastText, 1).show();
                helper.selectItem(list.get(item));
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        DebugActivity.this.clearCacheAndRestart(true);
                    }
                }, 3000);
            }
        });
        dialog.create().show();
    }

    private <T> int getOptionsFromCsv(String filename, List<T> list, List<String> options, ICsvSelectorHelper<T> helper) {
        Exception e;
        Throwable th;
        list.add(helper.fromStringList(null));
        options.add("Default");
        int checkedIndex = 0;
        BufferedReader bufferedReader = null;
        try {
            File external = Environment.getExternalStorageDirectory();
            File csvFile = new File(external, filename);
            if (!csvFile.exists()) {
                csvFile = new File(new File(external, "Download"), filename);
            }
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            while (true) {
                try {
                    String line = reader.readLine();
                    if (line != null) {
                        line = line.trim();
                        if (!(line.startsWith("#") || TextUtils.isEmpty(line))) {
                            String[] fields = line.split(",");
                            String name = fields[0];
                            if (TextUtils.isEmpty(name)) {
                                FinskyLog.d("Skipping an item from csv without a name", new Object[0]);
                            } else {
                                options.add(name);
                                T item = helper.fromStringList(fields);
                                list.add(item);
                                if (helper.isSelected(item)) {
                                    checkedIndex = list.size() - 1;
                                }
                            }
                        }
                    } else {
                        Utils.safeClose(reader);
                        bufferedReader = reader;
                        return checkedIndex;
                    }
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = reader;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = reader;
                }
            }
        } catch (Exception e3) {
            e = e3;
            try {
                FinskyLog.e("Unable to build selector: %s", e.getMessage());
                Toast.makeText(this, "Unable to build selector.", 1).show();
                Utils.safeClose(bufferedReader);
                return 0;
            } catch (Throwable th3) {
                th = th3;
                Utils.safeClose(bufferedReader);
                throw th;
            }
        }
    }

    private void selectEnvironment(EnvironmentOverride newEnvironment) {
        Intent intent = new Intent("com.google.gservices.intent.action.GSERVICES_OVERRIDE");
        String urlRewrite = null;
        String vendingUrlRewrite = null;
        String walletEscrowUrl = null;
        if (newEnvironment != null) {
            urlRewrite = newEnvironment.dfeBaseUrl == null ? null : ORIGINAL_DFE_URL + " rewrite " + newEnvironment.dfeBaseUrl;
            vendingUrlRewrite = newEnvironment.vendingBaseUrl == null ? null : ORIGINAL_VENDING_API_URL + " rewrite " + newEnvironment.vendingBaseUrl;
            walletEscrowUrl = newEnvironment.escrowUrl;
        }
        intent.putExtra("url:finsky_dfe_url", urlRewrite);
        intent.putExtra("url:vending_machine_ssl_url", vendingUrlRewrite);
        intent.putExtra("url:licensing_url", vendingUrlRewrite);
        intent.putExtra("finsky.wallet_escrow_url", walletEscrowUrl);
        sendBroadcast(intent);
    }

    private void selectCarrier(CarrierOverride newCarrier) {
        override(DfeApiConfig.ipCountryOverride, newCarrier.countryCode);
        override(DfeApiConfig.mccMncOverride, newCarrier.simCode);
    }

    private void override(GservicesValue<? extends Object> setting, String value) {
        Intent intent = new Intent("com.google.gservices.intent.action.GSERVICES_OVERRIDE");
        intent.putExtra(setting.getKey(), value);
        sendBroadcast(intent);
    }

    private void clearCacheAndRestart(final boolean replicateLibraries) {
        FinskyApp.get().clearCacheAsync(new Runnable() {
            public void run() {
                Intent i = DebugActivity.this.getBaseContext().getPackageManager().getLaunchIntentForPackage(DebugActivity.this.getBaseContext().getPackageName()).addFlags(67108864).addFlags(32768).addFlags(268435456).addCategory("android.intent.category.HOME");
                if (replicateLibraries) {
                    DebugActivity.this.startActivityForResult(i, 1);
                } else {
                    DebugActivity.this.startActivityForResult(i, 0);
                }
            }
        });
    }
}
