package com.google.android.wallet.instrumentmanager.common.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;
import android.view.WindowManager;
import com.android.volley.toolbox.ImageLoader;
import com.google.android.wallet.instrumentmanager.R;
import com.google.android.wallet.instrumentmanager.api.InstrumentManagerRequestQueue;
import com.google.android.wallet.instrumentmanager.config.G;
import com.google.android.wallet.instrumentmanager.config.G.images;
import com.google.commerce.payments.orchestration.proto.common.ContextOuterClass.NativeClientContext;
import com.google.commerce.payments.orchestration.proto.ui.common.RequestContextOuterClass.RequestContext;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CardType;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageOuterClass.LegalMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageSetOuterClass.LegalMessageSet;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import paymentfraud.mobile.DeviceFingerprinting.Parsed;
import paymentfraud.mobile.DeviceFingerprinting.Parsed.Properties;
import paymentfraud.mobile.DeviceFingerprinting.Parsed.State;

public final class PaymentUtils {
    private static final SparseIntArray EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID;
    private static final Pattern NON_NUMERIC_PATTERN;
    private static ImageLoader sImageLoader;

    static {
        NON_NUMERIC_PATTERN = Pattern.compile("[^\\d]");
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID = new SparseIntArray(8);
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.put(1, R.drawable.wallet_im_card_full_amex);
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.put(2, R.drawable.wallet_im_card_full_discover);
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.put(3, R.drawable.wallet_im_card_full_jcb);
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.put(4, R.drawable.wallet_im_card_full_mastercard);
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.put(5, R.drawable.wallet_im_card_full_visa);
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.put(6, R.drawable.wallet_im_card_full_diners);
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.put(15, R.drawable.wallet_im_card_full_elo);
        EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.put(21, R.drawable.wallet_im_card_general);
    }

    public static String removeNonNumericDigits(String s) {
        return s == null ? null : NON_NUMERIC_PATTERN.matcher(s).replaceAll("");
    }

    public static boolean shouldAutoCompleteBeEnabled(Context context) {
        return !AndroidUtils.isTouchAccessibilityEnabled(context) || VERSION.SDK_INT >= ((Integer) G.minApiLevelToShowAutocompleteForAccessibility.get()).intValue();
    }

    public static boolean isEmbeddedImageUri(String imageUri) {
        return imageUri.startsWith("embedded:");
    }

    public static int embeddedImageUriToDrawableResourceId(String embeddedImageUri) {
        if (isEmbeddedImageUri("embedded:")) {
            int embeddedImageId = Integer.parseInt(embeddedImageUri.substring("embedded:".length()));
            int drawableResourceId = EMBEDDED_IMAGE_ID_TO_DRAWABLE_RESOURCE_ID.get(embeddedImageId, -1);
            if (drawableResourceId != -1) {
                return drawableResourceId;
            }
            throw new IllegalArgumentException("Invalid embedded image id: " + embeddedImageId);
        }
        throw new IllegalArgumentException("Invalid embedded image uri: " + embeddedImageUri);
    }

    public static boolean equals(CardType type1, CardType type2) {
        if (type1 == type2) {
            return true;
        }
        if (type1 == null || type2 == null) {
            return false;
        }
        return Arrays.equals(type1.typeToken, type2.typeToken);
    }

    public static RequestContext createRequestContext(Context context, byte[] sessionData) {
        NativeClientContext nativeClientContext = new NativeClientContext();
        nativeClientContext.osVersion = Integer.toString(VERSION.SDK_INT);
        nativeClientContext.device = Build.DEVICE;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            String subscriberId = telephonyManager.getSubscriberId();
            if (!TextUtils.isEmpty(subscriberId)) {
                nativeClientContext.imsiHash = sha1HashAsBase64(subscriberId);
            }
            String simOperator = telephonyManager.getSimOperator();
            if (!TextUtils.isEmpty(simOperator)) {
                nativeClientContext.mccMnc = simOperator;
            }
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        nativeClientContext.screenWidthPx = metrics.widthPixels;
        nativeClientContext.screenHeightPx = metrics.heightPixels;
        nativeClientContext.screenXDpi = metrics.xdpi;
        nativeClientContext.screenYDpi = metrics.ydpi;
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            nativeClientContext.packageName = packageInfo.packageName;
            nativeClientContext.packageVersionCode = Integer.toString(packageInfo.versionCode);
            nativeClientContext.packageVersionName = packageInfo.versionName;
        } catch (NameNotFoundException e) {
        }
        Parsed riskData = new Parsed();
        riskData.properties = getProperties(telephonyManager);
        riskData.state = getState(context, packageInfo, telephonyManager);
        nativeClientContext.riskData = riskData;
        RequestContext requestContext = new RequestContext();
        if (sessionData != null) {
            requestContext.sessionData = sessionData;
        }
        requestContext.nativeContext = nativeClientContext;
        requestContext.languageCode = toBcp47LanguageCode(context.getResources().getConfiguration().locale);
        requestContext.clientType = 2;
        requestContext.clientVersion = 10302000;
        return requestContext;
    }

    private static Properties getProperties(TelephonyManager telephonyManager) {
        Properties properties = new Properties();
        properties.operatingSystem = 0;
        if (telephonyManager != null) {
            String deviceId = telephonyManager.getDeviceId();
            if (!TextUtils.isEmpty(deviceId)) {
                switch (telephonyManager.getPhoneType()) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        properties.imei = deviceId;
                        break;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        if (deviceId.length() > 8) {
                            properties.meid = deviceId;
                            break;
                        }
                        properties.esn = deviceId;
                        break;
                }
            }
            String line1Number = telephonyManager.getLine1Number();
            if (!TextUtils.isEmpty(line1Number)) {
                properties.phoneNumber = line1Number;
            }
        }
        properties.androidId = ((Long) G.androidId.get()).longValue();
        properties.deviceName = Build.DEVICE;
        properties.productName = Build.PRODUCT;
        properties.modelName = Build.MODEL;
        properties.manufacturer = Build.MANUFACTURER;
        properties.buildFingerprint = Build.FINGERPRINT;
        properties.osVersion = VERSION.RELEASE;
        properties.androidBuildBrand = Build.BRAND;
        return properties;
    }

    private static State getState(Context context, PackageInfo packageInfo, TelephonyManager telephonyManager) {
        State state = new State();
        if (packageInfo != null) {
            State.PackageInfo pi = new State.PackageInfo();
            if (!TextUtils.isEmpty(packageInfo.packageName)) {
                pi.name = packageInfo.packageName;
            }
            pi.versionCode = Integer.toString(packageInfo.versionCode);
            pi.firstInstallTime = packageInfo.firstInstallTime;
            pi.lastUpdateTime = packageInfo.lastUpdateTime;
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null) {
                if (!TextUtils.isEmpty(applicationInfo.sourceDir)) {
                    pi.installLocation = applicationInfo.sourceDir;
                }
            }
            state.installedPackages = new State.PackageInfo[]{pi};
        }
        Location location = ((LocationManager) context.getSystemService("location")).getLastKnownLocation("network");
        if (location != null) {
            State.Location loc = new State.Location();
            loc.latitude = location.getLatitude();
            loc.longitude = location.getLongitude();
            loc.timeInMs = (double) location.getTime();
            loc.altitude = location.getAltitude();
            loc.accuracy = location.getAccuracy();
            state.lastGpsLocation = loc;
        }
        Intent batteryData = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (batteryData != null) {
            int level = batteryData.getIntExtra("level", -1);
            int scale = batteryData.getIntExtra("scale", -1);
            if (scale > 0) {
                state.percentBattery = (level * 100) / scale;
            }
        }
        state.gmtOffsetMillis = (long) TimeZone.getDefault().getRawOffset();
        ContentResolver contentResolver = context.getContentResolver();
        if (VERSION.SDK_INT < 17) {
            state.devModeOn = 1 == Secure.getInt(contentResolver, "adb_enabled", 0);
            state.nonPlayInstallAllowed = 1 == Secure.getInt(contentResolver, "install_non_market_apps", 0);
        } else {
            state.devModeOn = 1 == Global.getInt(contentResolver, "adb_enabled", 0);
            state.nonPlayInstallAllowed = 1 == Global.getInt(contentResolver, "install_non_market_apps", 0);
        }
        Locale locale = context.getResources().getConfiguration().locale;
        state.language = locale.getISO3Language();
        state.locale = locale.toString();
        ArrayList<InetAddress> inetAddresses = NetUtils.getNonLoopbackInetAddresses();
        state.ipAddr = new String[inetAddresses.size()];
        int length = inetAddresses.size();
        for (int i = 0; i < length; i++) {
            state.ipAddr[i] = ((InetAddress) inetAddresses.get(i)).getHostAddress();
        }
        if (telephonyManager != null) {
            String cellOperator = telephonyManager.getNetworkOperator();
            if (!TextUtils.isEmpty(cellOperator)) {
                state.cellOperator = cellOperator;
            }
            String simOperator = telephonyManager.getSimOperator();
            if (!TextUtils.isEmpty(simOperator)) {
                state.simOperator = simOperator;
            }
        }
        return state;
    }

    public static String toBcp47LanguageCode(Locale locale) {
        StringBuilder builder = new StringBuilder();
        builder.append(locale.getLanguage());
        String country = locale.getCountry();
        String variant = locale.getVariant();
        if (!TextUtils.isEmpty(country)) {
            builder.append('-').append(country);
        }
        if (!TextUtils.isEmpty(variant)) {
            builder.append('-').append(variant);
        }
        return builder.toString();
    }

    public static LegalMessage findLegalMessageByCountry(LegalMessageSet legalMessageSet, String countryCode) {
        if (legalMessageSet == null) {
            return null;
        }
        int length = legalMessageSet.messageByCountry.length;
        for (int i = 0; i < length; i++) {
            if (Objects.equals(countryCode, legalMessageSet.messageByCountry[i].country)) {
                return legalMessageSet.messageByCountry[i].message;
            }
        }
        return legalMessageSet.defaultMessage;
    }

    public static synchronized ImageLoader getImageLoader(Context applicationContext) {
        ImageLoader imageLoader;
        synchronized (PaymentUtils.class) {
            if (sImageLoader == null) {
                sImageLoader = new ImageLoader(InstrumentManagerRequestQueue.getImageRequestQueue(applicationContext), new BitmapLruCache(applicationContext, ((Integer) images.inMemoryCacheSizeDp.get()).intValue()));
            }
            imageLoader = sImageLoader;
        }
        return imageLoader;
    }

    public static String sha1HashAsBase64(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            try {
                messageDigest.update(input.getBytes("UTF-8"));
                return Base64.encodeToString(messageDigest.digest(), 11);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }
}
