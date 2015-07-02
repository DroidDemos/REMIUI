package com.google.android.finsky.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.WindowManager;
import com.android.volley.Response.ErrorListener;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.protos.DeviceConfigurationProto;
import com.google.android.finsky.protos.UploadDeviceConfig.UploadDeviceConfigResponse;
import com.google.android.wallet.instrumentmanager.R;
import java.util.ArrayList;
import java.util.List;

public class DeviceConfigurationHelper {
    private static DeviceConfigurationProto sDeviceConfiguration;
    private static ArrayList<RequestRecord> sRequests;

    public interface Listener {
        void onError(VolleyError volleyError);

        void onSuccess();
    }

    private static class RequestRecord {
        public final DfeApi dfeApi;
        public final boolean gcmOnly;
        public final Listener listener;

        public RequestRecord(DfeApi dfeApi, boolean gcmOnly, Listener listener) {
            this.dfeApi = dfeApi;
            this.gcmOnly = gcmOnly;
            this.listener = listener;
        }
    }

    static {
        sRequests = Lists.newArrayList(2);
    }

    public static String getToken() {
        return (String) FinskyPreferences.deviceConfigToken.get();
    }

    public static void invalidateToken() {
        FinskyPreferences.deviceConfigToken.remove();
    }

    public static void requestToken(DfeApi dfeApi, Listener listener) {
        postUploadRequest(dfeApi, false, listener);
    }

    public static void uploadGcmRegistrationId(DfeApi dfeApi) {
        postUploadRequest(dfeApi, true, null);
    }

    private static void postUploadRequest(DfeApi dfeApi, boolean gcmOnly, Listener listener) {
        RequestRecord request = new RequestRecord(dfeApi, gcmOnly, listener);
        sRequests.add(request);
        if (sRequests.size() == 1) {
            doUploadDeviceConfiguration(request);
        }
    }

    private static void doNextRequest(RequestRecord completedRequest) {
        if (sRequests.size() == 0) {
            FinskyLog.wtf("Empty request queue", new Object[0]);
            return;
        }
        if (((RequestRecord) sRequests.remove(0)) != completedRequest) {
            FinskyLog.wtf("Completed request mismatch", new Object[0]);
        }
        if (sRequests.size() > 0) {
            doUploadDeviceConfiguration((RequestRecord) sRequests.get(0));
        }
    }

    private static void doUploadDeviceConfiguration(final RequestRecord request) {
        DeviceConfigurationProto deviceConfiguration;
        String gcmResult;
        final DfeApi dfeApi = request.dfeApi;
        final Listener listener = request.listener;
        final FinskyEventLog eventLog = FinskyApp.get().getEventLogger();
        if (request.gcmOnly) {
            deviceConfiguration = null;
        } else {
            try {
                deviceConfiguration = getDeviceConfiguration();
            } catch (Exception e) {
                FinskyLog.wtf(e, "Exception while getting device configuration.", new Object[0]);
                if (listener != null) {
                    listener.onError(new ServerError());
                }
                doNextRequest(request);
                return;
            }
        }
        try {
            gcmResult = GcmRegistrationIdHelper.getRegistrationId(FinskyApp.get());
        } catch (Exception e2) {
            FinskyLog.wtf(e2, "Exception while getting gcm registration id.", new Object[0]);
            gcmResult = null;
        }
        final String gcmRegistrationId = gcmResult;
        String token = getToken();
        final RequestRecord requestRecord = request;
        AnonymousClass1 anonymousClass1 = new com.android.volley.Response.Listener<UploadDeviceConfigResponse>() {
            public void onResponse(UploadDeviceConfigResponse response) {
                eventLog.logBackgroundEvent(120, null, null, 0, null, null);
                if (!TextUtils.isEmpty(gcmRegistrationId)) {
                    GcmRegistrationIdHelper.setRegisteredOnServer(gcmRegistrationId);
                }
                if (deviceConfiguration != null) {
                    if (TextUtils.isEmpty(response.uploadDeviceConfigToken)) {
                        FinskyLog.wtf("Unexpected - missing UploadDeviceConfigToken", new Object[0]);
                        if (listener != null) {
                            listener.onError(new ServerError());
                        }
                    } else {
                        FinskyLog.d("Received device config token %s", response.uploadDeviceConfigToken);
                        FinskyPreferences.deviceConfigToken.put(token);
                        GetTocHelper.changedDeviceConfigToken(dfeApi);
                        GetSelfUpdateHelper.changedDeviceConfigToken(dfeApi);
                        if (listener != null) {
                            listener.onSuccess();
                        }
                    }
                }
                DeviceConfigurationHelper.doNextRequest(requestRecord);
            }
        };
        dfeApi.uploadDeviceConfig(deviceConfiguration, gcmRegistrationId, token, anonymousClass1, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                eventLog.logBackgroundEvent(120, null, null, 0, error.getClass().getSimpleName(), null);
                FinskyLog.e("Couldn't upload device config", new Object[0]);
                if (listener != null) {
                    listener.onError(error);
                }
                DeviceConfigurationHelper.doNextRequest(request);
            }
        });
    }

    public static synchronized DeviceConfigurationProto getDeviceConfiguration() {
        DeviceConfigurationProto deviceConfigurationProto;
        synchronized (DeviceConfigurationHelper.class) {
            if (sDeviceConfiguration == null) {
                boolean z;
                sDeviceConfiguration = new DeviceConfigurationProto();
                Context context = FinskyApp.get();
                ConfigurationInfo ci = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo();
                Pair<Integer, Integer> screenDimensions = VendingUtils.getScreenDimensions(context);
                WindowManager wm = (WindowManager) context.getSystemService("window");
                DisplayMetrics metrics = new DisplayMetrics();
                wm.getDefaultDisplay().getMetrics(metrics);
                sDeviceConfiguration.touchScreen = getTouchScreenId(ci.reqTouchScreen);
                sDeviceConfiguration.hasTouchScreen = true;
                sDeviceConfiguration.keyboard = getKeyboardConfigId(ci.reqKeyboardType);
                sDeviceConfiguration.hasKeyboard = true;
                sDeviceConfiguration.navigation = getNavigationId(ci.reqNavigation);
                sDeviceConfiguration.hasNavigation = true;
                sDeviceConfiguration.glEsVersion = ci.reqGlEsVersion;
                sDeviceConfiguration.hasGlEsVersion = true;
                sDeviceConfiguration.screenWidth = ((Integer) screenDimensions.first).intValue();
                sDeviceConfiguration.hasScreenWidth = true;
                sDeviceConfiguration.screenHeight = ((Integer) screenDimensions.second).intValue();
                sDeviceConfiguration.hasScreenHeight = true;
                sDeviceConfiguration.screenDensity = metrics.densityDpi;
                sDeviceConfiguration.hasScreenDensity = true;
                DeviceConfigurationProto deviceConfigurationProto2 = sDeviceConfiguration;
                if ((ci.reqInputFeatures & 1) > 0) {
                    z = true;
                } else {
                    z = false;
                }
                deviceConfigurationProto2.hasHardKeyboard = z;
                sDeviceConfiguration.hasHasHardKeyboard = true;
                deviceConfigurationProto2 = sDeviceConfiguration;
                if ((ci.reqInputFeatures & 2) > 0) {
                    z = true;
                } else {
                    z = false;
                }
                deviceConfigurationProto2.hasFiveWayNavigation = z;
                sDeviceConfiguration.hasHasFiveWayNavigation = true;
                Configuration config = context.getResources().getConfiguration();
                sDeviceConfiguration.screenLayout = getScreenLayoutSizeId(config.screenLayout);
                sDeviceConfiguration.hasScreenLayout = true;
                if (VERSION.SDK_INT >= 13) {
                    writeSmallestScreenWithDp(sDeviceConfiguration, config);
                }
                sDeviceConfiguration.systemSharedLibrary = context.getPackageManager().getSystemSharedLibraryNames();
                sDeviceConfiguration.systemSupportedLocale = FinskyApp.get().getAssets().getLocales();
                List<String> glExtensions = new GlExtensionReader().getGlExtensions();
                sDeviceConfiguration.glExtension = (String[]) glExtensions.toArray(new String[0]);
                customizeDeviceConfiguration(context, sDeviceConfiguration);
            }
            deviceConfigurationProto = sDeviceConfiguration;
        }
        return deviceConfigurationProto;
    }

    private static void writeSmallestScreenWithDp(DeviceConfigurationProto deviceConfiguration, Configuration config) {
        deviceConfiguration.smallestScreenWidthDp = config.smallestScreenWidthDp;
        deviceConfiguration.hasSmallestScreenWidthDp = true;
    }

    private static void customizeDeviceConfiguration(Context context, DeviceConfigurationProto deviceConfiguration) {
        FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
        if (systemAvailableFeatures != null) {
            List<String> featureNames = Lists.newArrayList();
            for (FeatureInfo feature : systemAvailableFeatures) {
                if (feature.name != null) {
                    featureNames.add(feature.name);
                }
            }
            deviceConfiguration.systemAvailableFeature = (String[]) featureNames.toArray(new String[0]);
        }
        if (VERSION.SDK_INT >= 21) {
            deviceConfiguration.nativePlatform = Build.SUPPORTED_ABIS;
        } else if (Build.CPU_ABI2.equals("unknown")) {
            deviceConfiguration.nativePlatform = new String[]{Build.CPU_ABI};
        } else {
            deviceConfiguration.nativePlatform = new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        }
    }

    private static int getKeyboardConfigId(int configValue) {
        switch (configValue) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 1;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 2;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 3;
            default:
                return 0;
        }
    }

    private static int getTouchScreenId(int touchScreenValue) {
        switch (touchScreenValue) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 1;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 2;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 3;
            default:
                return 0;
        }
    }

    private static int getNavigationId(int navigationValue) {
        switch (navigationValue) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 1;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 2;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 3;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return 4;
            default:
                return 0;
        }
    }

    private static int getScreenLayoutSizeId(int screenLayoutSizeValue) {
        switch (screenLayoutSizeValue & 15) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return 1;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return 2;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return 3;
            case R.styleable.WalletImFormEditText_required /*4*/:
                return 4;
            default:
                return 0;
        }
    }
}
