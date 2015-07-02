package com.alipay.mobilesecuritysdk.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Base64;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.constant.LocationNameEnum;
import com.alipay.mobilesecuritysdk.datainfo.AppInfo;
import com.alipay.mobilesecuritysdk.datainfo.LocationInfo;
import com.alipay.mobilesecuritysdk.datainfo.WifiCollectInfo;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollectedInfo {
    private final int MODULUS_FIX;
    private DataProfile profile;

    public CollectedInfo() {
        this.MODULUS_FIX = 8;
        this.profile = new DataProfile();
    }

    public String GetLocationInfo(Context context, List list) {
        List collectLocateInfos = collectLocateInfos(context);
        this.profile.setTid(list);
        return this.profile.LocationToString(new StringBuilder(String.valueOf(context.getFilesDir().getPath())).append(File.separator).toString(), collectLocateInfos);
    }

    public List collectLocateInfos(Context context) {
        List arrayList = new ArrayList();
        try {
            Object obj;
            TelephonyManager telephonyManager;
            List GetWifiInfos;
            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setTime(CommonUtils.convertDate2String(new Date()));
            locationInfo.setCid(ConfigConstant.WIRELESS_FILENAME);
            locationInfo.setLac(ConfigConstant.WIRELESS_FILENAME);
            locationInfo.setLatitude(ConfigConstant.WIRELESS_FILENAME);
            locationInfo.setLongitude(ConfigConstant.WIRELESS_FILENAME);
            locationInfo.setMcc(ConfigConstant.WIRELESS_FILENAME);
            locationInfo.setMnc(ConfigConstant.WIRELESS_FILENAME);
            locationInfo.setPhonetype(ConfigConstant.WIRELESS_FILENAME);
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (locationManager.isProviderEnabled("network")) {
                LocationListener secLocationListener = new SecLocationListener();
                locationManager.requestLocationUpdates("network", ConfigConstant.REQUEST_LOCATE_INTERVAL, 0.0f, secLocationListener, Looper.getMainLooper());
                locationManager.removeUpdates(secLocationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation("network");
                if (lastKnownLocation != null) {
                    locationInfo.setLatitude(lastKnownLocation.getLatitude());
                    locationInfo.setLongitude(lastKnownLocation.getLongitude());
                    obj = 1;
                    telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager.getPhoneType() != 2) {
                        locationInfo.setPhonetype(LocationNameEnum.CDMA.getValue());
                        if (obj == null) {
                            SetPhoneType(telephonyManager, locationInfo, 2);
                        }
                    } else {
                        locationInfo.setPhonetype(LocationNameEnum.GSM.getValue());
                        SetPhoneType(telephonyManager, locationInfo, 1);
                    }
                    GetWifiInfos = GetWifiInfos(context);
                    if (GetWifiInfos != null && GetWifiInfos.size() > 0) {
                        locationInfo.setWifi(GetWifiInfos);
                    }
                    arrayList.add(locationInfo);
                    if (arrayList.size() <= 0) {
                        return arrayList;
                    }
                    return null;
                }
            }
            obj = null;
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager.getPhoneType() != 2) {
                locationInfo.setPhonetype(LocationNameEnum.GSM.getValue());
                SetPhoneType(telephonyManager, locationInfo, 1);
            } else {
                locationInfo.setPhonetype(LocationNameEnum.CDMA.getValue());
                if (obj == null) {
                    SetPhoneType(telephonyManager, locationInfo, 2);
                }
            }
            GetWifiInfos = GetWifiInfos(context);
            locationInfo.setWifi(GetWifiInfos);
            arrayList.add(locationInfo);
            if (arrayList.size() <= 0) {
                return null;
            }
            return arrayList;
        } catch (Exception e) {
            Log.i(ConfigConstant.LOG_TAG, e.getMessage());
            return null;
        }
    }

    private void SetPhoneType(TelephonyManager telephonyManager, LocationInfo locationInfo, int i) {
        String str;
        Exception exception;
        String str2 = ConfigConstant.WIRELESS_FILENAME;
        String str3 = ConfigConstant.WIRELESS_FILENAME;
        String str4 = ConfigConstant.WIRELESS_FILENAME;
        String str5 = ConfigConstant.WIRELESS_FILENAME;
        if (i == 2) {
            try {
                CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
                if (cdmaCellLocation != null && CommonUtils.isBlank(locationInfo.getLatitude()) && CommonUtils.isBlank(locationInfo.getLongitude())) {
                    str5 = String.valueOf(cdmaCellLocation.getNetworkId());
                    str2 = telephonyManager.getNetworkOperator().substring(0, 3);
                    str3 = String.valueOf(cdmaCellLocation.getSystemId());
                    str4 = String.valueOf(cdmaCellLocation.getBaseStationId());
                    locationInfo.setLatitude(cdmaCellLocation.getBaseStationLatitude());
                    locationInfo.setLongitude(cdmaCellLocation.getBaseStationLongitude());
                    str = str5;
                    str5 = str4;
                    str4 = str3;
                    str3 = str2;
                }
                str = str5;
                str5 = str4;
                str4 = str3;
                str3 = str2;
            } catch (Exception e) {
                exception = e;
                str = str5;
                str5 = str4;
                str4 = str3;
                str3 = str2;
                Log.i("gettelphonetype PHONE_TYPE_CDMA", exception.getLocalizedMessage());
            }
        } else {
            try {
                GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
                if (gsmCellLocation != null) {
                    str2 = telephonyManager.getNetworkOperator().substring(0, 3);
                    str3 = telephonyManager.getNetworkOperator().substring(3, 5);
                    str4 = String.valueOf(gsmCellLocation.getCid());
                    str = String.valueOf(gsmCellLocation.getLac());
                    str5 = str4;
                    str4 = str3;
                    str3 = str2;
                }
                str = str5;
                str5 = str4;
                str4 = str3;
                str3 = str2;
            } catch (Exception e2) {
                exception = e2;
                str = str4;
                str4 = str3;
                str3 = str2;
                Log.i("gettelphonetype", exception.getLocalizedMessage());
                String str6 = str5;
                str5 = str;
                str = str6;
            }
        }
        locationInfo.setMcc(str3);
        locationInfo.setMnc(str4);
        locationInfo.setCid(str5);
        locationInfo.setLac(str);
    }

    public List collectappInfos(Context context) {
        try {
            List arrayList = new ArrayList();
            PackageManager packageManager = context.getPackageManager();
            for (PackageInfo packageInfo : packageManager.getInstalledPackages(4096)) {
                if ((packageManager.checkPermission(ConfigConstant.PERPERMISSION_READ_SMS, packageInfo.packageName) == 0 || packageManager.checkPermission(ConfigConstant.PERPERMISSION_RECEIVE_SMS, packageInfo.packageName) == 0) && (packageManager.checkPermission(ConfigConstant.PERPERMISSION_SEND_SMS, packageInfo.packageName) == 0 || packageManager.checkPermission(ConfigConstant.PERPERMISSION_INTERNET, packageInfo.packageName) == 0)) {
                    AppInfo appInfo = new AppInfo();
                    appInfo.setPkgName(packageInfo.packageName);
                    appInfo.setPkeyhash(getSignatureHash(packageManager.getPackageInfo(packageInfo.packageName, 64).signatures[0].toByteArray()));
                    if (appInfo.validate()) {
                        arrayList.add(appInfo);
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            Log.i(ConfigConstant.LOG_TAG, e.getMessage());
            return null;
        }
    }

    private String getSignatureHash(byte[] bArr) {
        try {
            String trim;
            String obj = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString();
            int indexOf = obj.indexOf("modulus");
            int indexOf2 = obj.indexOf("\n", indexOf + 8);
            int indexOf3 = obj.indexOf(",", indexOf + 8);
            if (indexOf2 >= 0 || indexOf3 <= 0) {
                if (indexOf3 < 0 && indexOf2 > 0) {
                    indexOf3 = indexOf2;
                } else if (indexOf2 < indexOf3) {
                    indexOf3 = indexOf2;
                } else if (indexOf3 >= indexOf2) {
                    indexOf3 = -1;
                }
            }
            if (indexOf3 < 0) {
                trim = obj.substring(indexOf + 8).trim();
            } else {
                trim = obj.substring(indexOf + 8, indexOf3).trim();
            }
            return CommonUtils.MD5(trim);
        } catch (Exception e) {
            Log.i(ConfigConstant.LOG_TAG, e.getMessage());
            return null;
        }
    }

    private List GetWifiInfos(Context context) {
        List arrayList = new ArrayList();
        WifiManager wifiManager = (WifiManager) context.getSystemService(ConfigConstant.JSON_SECTION_WIFI);
        try {
            if (wifiManager.isWifiEnabled()) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                WifiCollectInfo wifiCollectInfo = new WifiCollectInfo();
                wifiCollectInfo.setMbssid(connectionInfo.getBSSID());
                wifiCollectInfo.setMssid(Base64.encodeToString(connectionInfo.getSSID().getBytes(), 8));
                wifiCollectInfo.setMlevel(connectionInfo.getRssi());
                wifiCollectInfo.setMiscurrent(true);
                arrayList.add(wifiCollectInfo);
                for (ScanResult scanResult : wifiManager.getScanResults()) {
                    if (!(scanResult.BSSID.equals(connectionInfo.getBSSID()) || scanResult.SSID.equals(connectionInfo.getSSID()))) {
                        WifiCollectInfo wifiCollectInfo2 = new WifiCollectInfo();
                        wifiCollectInfo2.setMbssid(scanResult.BSSID);
                        wifiCollectInfo2.setMssid(Base64.encodeToString(scanResult.SSID.getBytes(), 8));
                        wifiCollectInfo2.setMlevel(scanResult.level);
                        wifiCollectInfo2.setMiscurrent(false);
                        arrayList.add(wifiCollectInfo2);
                    }
                }
                return arrayList;
            }
        } catch (Exception e) {
            Log.d("GetWifiInfos", e.getLocalizedMessage());
        }
        return null;
    }
}
