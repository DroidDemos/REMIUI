package com.google.android.finsky.billing;

import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.NoCache;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.billing.carrierbilling.api.DcbApi;
import com.google.android.finsky.billing.carrierbilling.api.DcbApiContext;
import com.google.android.finsky.billing.carrierbilling.model.CarrierBillingStorage;
import com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country;
import com.google.android.finsky.remoting.GoogleProxyHttpClientStack;
import com.google.android.finsky.remoting.RadioConnectionFactoryImpl;
import com.google.android.finsky.remoting.RadioHttpClient;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import java.util.List;

public class BillingLocator {
    private static boolean isInitialized;
    private static Context sApplicationContext;
    private static CarrierBillingStorage sCarrierBillingStorage;
    private static boolean sDeviceInService;

    static {
        isInitialized = false;
        sDeviceInService = false;
    }

    public static void initSingleton() {
        if (isInitialized) {
            throw new IllegalStateException("BillingLocator already initialized.");
        }
        isInitialized = true;
        sApplicationContext = FinskyApp.get();
        sCarrierBillingStorage = new CarrierBillingStorage(sApplicationContext);
    }

    public static void initCarrierBillingStorage(Runnable runnable) {
        Utils.ensureOnMainThread();
        setupServiceStateListener();
        sCarrierBillingStorage.init(runnable);
    }

    private static void setupServiceStateListener() {
        TelephonyManager telephonyManager = (TelephonyManager) sApplicationContext.getSystemService("phone");
        if (telephonyManager != null) {
            telephonyManager.listen(new PhoneStateListener() {
                public void onServiceStateChanged(ServiceState serviceState) {
                    if (serviceState.getState() == 0) {
                        BillingLocator.sDeviceInService = true;
                    } else {
                        BillingLocator.sDeviceInService = false;
                    }
                }
            }, 1);
        }
    }

    public static CarrierBillingStorage getCarrierBillingStorage() {
        Utils.ensureOnMainThread();
        if (sCarrierBillingStorage != null || sApplicationContext == null) {
            return sCarrierBillingStorage;
        }
        throw new IllegalStateException("CarrierBillingStorage has not been initialized.");
    }

    public static DcbApi createDcbApi() {
        Utils.ensureOnMainThread();
        if (sApplicationContext == null) {
            return null;
        }
        return new DcbApi(new RequestQueue(new NoCache(), new BasicNetwork(new RadioHttpClient(new GoogleProxyHttpClientStack(sApplicationContext), new RadioConnectionFactoryImpl((ConnectivityManager) sApplicationContext.getSystemService("connectivity"))))), new DcbApiContext(getCarrierBillingStorage(), getLine1NumberFromTelephony(), getSubscriberIdFromTelephony()));
    }

    public static String getLine1NumberFromTelephony() {
        Utils.ensureOnMainThread();
        if (sApplicationContext == null) {
            throw new IllegalStateException("BillingLocator has not been initialized.");
        }
        String line1Number = ((TelephonyManager) sApplicationContext.getSystemService("phone")).getLine1Number();
        if (line1Number == null) {
            return "";
        }
        return line1Number;
    }

    public static String getSubscriberIdFromTelephony() {
        TelephonyManager telephonyManager = (TelephonyManager) sApplicationContext.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getSubscriberId();
    }

    public static String getDeviceIdFromTelephony() {
        TelephonyManager telephonyManager = (TelephonyManager) sApplicationContext.getSystemService("phone");
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getDeviceId();
    }

    public static List<Country> getBillingCountries() {
        Utils.ensureOnMainThread();
        List<Country> countries = Lists.newArrayList();
        String countriesString = (String) BillingPreferences.BILLING_COUNTRIES.get();
        if (countriesString == null) {
            return null;
        }
        for (String codeCountryString : countriesString.split("\\}\\{")) {
            String codeCountryString2;
            if (codeCountryString2.length() == 0) {
                FinskyLog.w("Got empty billing country string.", new Object[0]);
            } else {
                if (codeCountryString2.charAt(0) == '{') {
                    codeCountryString2 = codeCountryString2.substring(1);
                }
                if (codeCountryString2.charAt(codeCountryString2.length() - 1) == '}') {
                    codeCountryString2 = codeCountryString2.substring(0, codeCountryString2.length() - 1);
                }
                String[] codeCountryParts = codeCountryString2.split(",");
                if (codeCountryParts.length < 2) {
                    FinskyLog.w("Invalid country string: %s. Expected at least 2 parts, got %d.", codeCountryString2, Integer.valueOf(codeCountryParts.length));
                } else {
                    Country country = new Country();
                    country.countryCode = codeCountryParts[0];
                    country.hasCountryCode = true;
                    country.countryName = codeCountryParts[1];
                    country.hasCountryName = true;
                    if (codeCountryParts.length >= 3) {
                        if (codeCountryParts[2].equals("1") || codeCountryParts[2].equals("0")) {
                            country.allowsReducedBillingAddress = codeCountryParts[2].equals("1");
                            country.hasAllowsReducedBillingAddress = true;
                        } else {
                            FinskyLog.w("Invalid reducedBillingAddress flag: " + codeCountryParts[2], new Object[0]);
                        }
                    }
                    countries.add(country);
                }
            }
        }
        return countries;
    }

    public static void setBillingCountries(Country[] billingCountries) {
        Utils.ensureOnMainThread();
        StringBuilder sb = new StringBuilder();
        for (Country country : billingCountries) {
            sb.append('{').append(country.countryCode).append(',');
            sb.append(country.countryName).append(',');
            sb.append(country.allowsReducedBillingAddress ? '1' : '0').append('}');
        }
        BillingPreferences.BILLING_COUNTRIES.put(sb.toString());
        BillingPreferences.LAST_BILLING_COUNTRIES_REFRESH_MILLIS.put(Long.valueOf(System.currentTimeMillis()));
    }
}
