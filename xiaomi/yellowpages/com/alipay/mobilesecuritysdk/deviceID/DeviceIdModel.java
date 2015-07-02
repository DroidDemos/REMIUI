package com.alipay.mobilesecuritysdk.deviceID;

import HttpUtils.HttpFetcher;
import android.content.Context;
import android.os.Environment;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceIdModel {
    public static final String PREFS_NAME = "profiles";
    public static final String PRIVATE_NAME = "deviceid";
    public static final String SERVICEID = "deviceFingerprint";
    public static final String VER = "1";
    public static final String mApdtk = "apdtk";
    public static final String mAppId = "appId";
    public static final String mCheckCode = "checkcode";
    public static final String mDeviceId = "deviceId";
    public static final String mDeviceInfo = "deviceInfo";
    public static final String mPriDeviceId = "priDeviceId";
    public static final String mRule = "rule";
    public static final String mah1 = "AH1";
    public static final String mah10 = "AH10";
    public static final String mah2 = "AH2";
    public static final String mah3 = "AH3";
    public static final String mah4 = "AH4";
    public static final String mah5 = "AH5";
    public static final String mah6 = "AH6";
    public static final String mah7 = "AH7";
    public static final String mah8 = "AH8";
    public static final String mah9 = "AH9";
    public static final String mas1 = "AS1";
    public static final String mas2 = "AS2";
    public static final String mas3 = "AS3";
    public static final String mas4 = "AS4";
    public static final String mtid = "AC1";
    public static final String mtime = "time";
    public static final String mutdid = "AC2";
    private DeviceMetaData dv;
    private Profile profile;

    public DeviceIdModel() {
        this.dv = new DeviceMetaData();
        this.profile = new Profile();
    }

    public void Init(Context context, Map map) {
        CollectDeviceInfo instance = CollectDeviceInfo.getInstance();
        LOG.init(context);
        if (map != null) {
            try {
                if (map.size() > 0) {
                    if (!CommonUtils.isBlank((String) map.get(MiniDefine.ak))) {
                        this.dv.setMtid((String) map.get(MiniDefine.ak));
                    }
                    if (!CommonUtils.isBlank((String) map.get(GlobalDefine.l))) {
                        this.dv.setMutdid((String) map.get(GlobalDefine.l));
                    }
                }
            } catch (Throwable e) {
                Log(LOG.getStackString(e));
                return;
            }
        }
        if (!CommonUtils.isBlank(instance.getImei(context))) {
            this.dv.setMah1(instance.getImei(context));
        }
        if (!CommonUtils.isBlank(instance.getImsi(context))) {
            this.dv.setMah2(instance.getImsi(context));
        }
        if (!CommonUtils.isBlank(instance.getMacAddress(context))) {
            this.dv.setMah3(instance.getMacAddress(context));
        }
        if (!CommonUtils.isBlank(instance.getCpuFre())) {
            this.dv.setMah4(instance.getCpuFre());
        }
        if (!CommonUtils.isBlank(instance.getCpuNum())) {
            this.dv.setMah5(instance.getCpuNum());
        }
        if (!CommonUtils.isBlank(instance.getBluMac())) {
            this.dv.setMah6(instance.getBluMac());
        }
        if (!CommonUtils.isBlank(Long.toString(instance.getTotalMemory()))) {
            this.dv.setMah7(Long.toString(instance.getTotalMemory()));
        }
        if (!CommonUtils.isBlank(Long.toString(instance.getSDCardMemory()))) {
            this.dv.setMah8(Long.toString(instance.getSDCardMemory()));
        }
        if (!CommonUtils.isBlank(instance.getDeviceMx(context))) {
            this.dv.setMah9(instance.getDeviceMx(context));
        }
        if (!CommonUtils.isBlank(instance.getPhoneModel())) {
            this.dv.setMah10(instance.getPhoneModel());
        }
        if (!CommonUtils.isBlank(instance.getRomName())) {
            this.dv.setMas1(instance.getRomName());
        }
        if (!CommonUtils.isBlank(instance.getSDKVer())) {
            this.dv.setMas2(instance.getSDKVer());
        }
        if (!CommonUtils.isBlank(instance.getBandVer())) {
            this.dv.setMas3(instance.getBandVer());
        }
        if (!CommonUtils.isBlank(instance.getOsVer())) {
            this.dv.setMas4(instance.getOsVer());
        }
        if (!CommonUtils.isBlank(instance.getPackageName(context))) {
            this.dv.setMappId(instance.getPackageName(context));
        }
        Map GetPrivateData = GetPrivateData(context);
        if (GetPrivateData != null && GetPrivateData.size() > 0) {
            if (!CommonUtils.isBlank((String) GetPrivateData.get(mApdtk))) {
                this.dv.setMapdtk((String) GetPrivateData.get(mApdtk));
            }
            if (!CommonUtils.isBlank((String) GetPrivateData.get(mDeviceId))) {
                this.dv.setMpriDeviceId((String) GetPrivateData.get(mDeviceId));
            }
            if (!CommonUtils.isBlank((String) GetPrivateData.get(mtime))) {
                this.dv.setMtime((String) GetPrivateData.get(mtime));
            }
            if (!CommonUtils.isBlank((String) GetPrivateData.get(mRule))) {
                this.dv.setMrule((String) GetPrivateData.get(mRule));
            }
        }
        if (!CommonUtils.isBlank(readDataFromSettings()) && readDataFromSettings().length() > 32) {
            this.dv.setMdeviceId(readDataFromSettings().substring(0, 32));
        } else if (!CommonUtils.isBlank(readDataFromSdCard()) && readDataFromSdCard().length() > 32) {
            this.dv.setMdeviceId(readDataFromSdCard().substring(0, 32));
        }
    }

    public String UpdateId(Context context, Map map) {
        if (map == null) {
            return UpdateId(context);
        }
        boolean hasInPublic = hasInPublic();
        if (CheckPrivateData(map)) {
            String stringBuilder;
            if (!(hasInPublic || CommonUtils.isBlank((String) map.get(mPriDeviceId)) || CommonUtils.isBlank((String) map.get(mtime)))) {
                stringBuilder = new StringBuilder(String.valueOf((String) map.get(mPriDeviceId))).append((String) map.get(mtime)).toString();
                WriteDataToSettings(stringBuilder);
                WriteDataToSdCard(stringBuilder);
            }
            stringBuilder = (String) map.get(mCheckCode);
            String generaterCheckCode = generaterCheckCode();
            if (checkApdid() && checkCheckCode(stringBuilder, generaterCheckCode)) {
                return (String) map.get("apdid");
            }
        }
        return UpdateId(context);
    }

    public Map GetUploadInfo() {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        if (CommonUtils.isBlank(this.dv.getMah1())) {
            hashMap2.put(mah1, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah1, this.dv.getMah1());
        }
        if (CommonUtils.isBlank(this.dv.getMah2())) {
            hashMap2.put(mah2, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah2, this.dv.getMah2());
        }
        if (CommonUtils.isBlank(this.dv.getMah3())) {
            hashMap2.put(mah3, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah3, this.dv.getMah3());
        }
        if (CommonUtils.isBlank(this.dv.getMah4())) {
            hashMap2.put(mah4, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah4, this.dv.getMah4());
        }
        if (CommonUtils.isBlank(this.dv.getMah5())) {
            hashMap2.put(mah4, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah5, this.dv.getMah5());
        }
        if (CommonUtils.isBlank(this.dv.getMah6())) {
            hashMap2.put(mah6, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah6, this.dv.getMah6());
        }
        if (CommonUtils.isBlank(this.dv.getMah7())) {
            hashMap2.put(mah7, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah7, this.dv.getMah7());
        }
        if (CommonUtils.isBlank(this.dv.getMah8())) {
            hashMap2.put(mah8, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah8, this.dv.getMah8());
        }
        if (CommonUtils.isBlank(this.dv.getMah9())) {
            hashMap2.put(mah9, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah9, this.dv.getMah9());
        }
        if (CommonUtils.isBlank(this.dv.getMah10())) {
            hashMap2.put(mah10, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mah10, this.dv.getMah10());
        }
        if (CommonUtils.isBlank(this.dv.getMas1())) {
            hashMap2.put(mas1, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mas1, this.dv.getMas1());
        }
        if (CommonUtils.isBlank(this.dv.getMas2())) {
            hashMap2.put(mas2, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mas2, this.dv.getMas2());
        }
        if (CommonUtils.isBlank(this.dv.getMas3())) {
            hashMap2.put(mas3, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mas3, this.dv.getMas3());
        }
        if (CommonUtils.isBlank(this.dv.getMas4())) {
            hashMap2.put(mas4, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mas4, this.dv.getMas4());
        }
        if (CommonUtils.isBlank(this.dv.getMtid())) {
            hashMap2.put(mtid, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mtid, this.dv.getMtid());
        }
        if (CommonUtils.isBlank(this.dv.getMutdid())) {
            hashMap2.put(mutdid, ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap2.put(mutdid, this.dv.getMutdid());
        }
        hashMap.put(mDeviceInfo, hashMap2);
        if (!CommonUtils.isBlank(this.dv.getMdeviceId())) {
            hashMap.put(mDeviceId, this.dv.getMdeviceId());
        }
        if (!CommonUtils.isBlank(this.dv.getMpriDeviceId())) {
            hashMap.put(mPriDeviceId, this.dv.getMpriDeviceId());
        }
        if (!CommonUtils.isBlank(this.dv.getMappId())) {
            hashMap.put(mAppId, this.dv.getMappId());
        }
        if (!CommonUtils.isBlank(this.dv.getMtime())) {
            hashMap.put(mtime, this.dv.getMtime());
        }
        if (!CommonUtils.isBlank(this.dv.getMapdtk())) {
            hashMap.put(mApdtk, this.dv.getMapdtk());
        }
        return hashMap;
    }

    public Map GetLocalInfo() {
        Map hashMap = new HashMap();
        hashMap.put(mDeviceId, this.dv.getMdeviceId());
        hashMap.put(mPriDeviceId, this.dv.getMpriDeviceId());
        hashMap.put(mAppId, this.dv.getMappId());
        hashMap.put(mtime, this.dv.getMtime());
        hashMap.put(mApdtk, this.dv.getMapdtk());
        return hashMap;
    }

    public boolean CheckPrivateData(Map map) {
        if (map != null && map.size() >= 0 && map.containsKey(mDeviceId) && map.containsKey(mCheckCode) && map.containsKey(mApdtk) && map.containsKey(mtime) && map.containsKey(mRule)) {
            return true;
        }
        return false;
    }

    public Map GetPrivateData(Context context) {
        String GetDataFromSharedPre = this.profile.GetDataFromSharedPre(context.getSharedPreferences(PREFS_NAME, 0), PRIVATE_NAME);
        if (CommonUtils.isBlank(GetDataFromSharedPre)) {
            return null;
        }
        GetDataFromSharedPre = SecurityUtils.decrypt(SecurityUtils.getSeed(), GetDataFromSharedPre);
        if (CommonUtils.isBlank(GetDataFromSharedPre)) {
            return null;
        }
        return new Profile().getMap(GetDataFromSharedPre);
    }

    public Map GetShareData(Context context) {
        return null;
    }

    public void WritePrivateData(Context context, String str) {
        String encrypt = SecurityUtils.encrypt(SecurityUtils.getSeed(), str);
        if (!CommonUtils.isBlank(encrypt)) {
            Map hashMap = new HashMap();
            hashMap.put(PRIVATE_NAME, encrypt);
            this.profile.SetDataToSharePre(context.getSharedPreferences(PREFS_NAME, 0), hashMap);
        }
    }

    public boolean hasInPublic() {
        return hasDataInSettings() && hasDataInSdcard();
    }

    public String readDataFromSettings() {
        String property = System.getProperty(PRIVATE_NAME);
        if (CommonUtils.isBlank(property)) {
            return null;
        }
        try {
            property = new JSONObject(property).getString("device");
        } catch (Throwable e) {
            Log(LOG.getStackString(e));
            property = null;
        }
        if (CommonUtils.isBlank(property)) {
            return null;
        }
        return SecurityUtils.decrypt(SecurityUtils.getSeed(), property);
    }

    public void WriteDataToSettings(String str) {
        if (!CommonUtils.isBlank(str)) {
            String encrypt = SecurityUtils.encrypt(SecurityUtils.getSeed(), str);
            if (!CommonUtils.isBlank(encrypt)) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("device", encrypt);
                    System.setProperty(PRIVATE_NAME, jSONObject.toString());
                } catch (Throwable e) {
                    Log(LOG.getStackString(e));
                }
            }
        }
    }

    public void WriteDataToSdCard(String str) {
        try {
            if (CommonUtils.GetSdCardFile()) {
                String encrypt = SecurityUtils.encrypt(SecurityUtils.getSeed(), str);
                File file = new File(Environment.getExternalStorageDirectory(), ConfigConstant.SD_FILE);
                if (file != null) {
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("device", encrypt);
                    } catch (Throwable e) {
                        Log(LOG.getStackString(e));
                    }
                    try {
                        CommonUtils.WriteFile(file.getAbsolutePath() + File.separator + "data", jSONObject.toString());
                    } catch (Throwable e2) {
                        Log(LOG.getStackString(e2));
                    }
                }
            }
        } catch (Throwable e22) {
            Log(LOG.getStackString(e22));
        }
    }

    public String readDataFromSdCard() {
        try {
            if (!CommonUtils.GetSdCardFile()) {
                return null;
            }
            File file = new File(Environment.getExternalStorageDirectory(), ConfigConstant.SD_FILE);
            if (!(file == null || file.exists())) {
                file.mkdir();
            }
            String ReadFile = CommonUtils.ReadFile(file.getAbsolutePath() + File.separator + "data");
            if (CommonUtils.isBlank(ReadFile)) {
                return null;
            }
            try {
                ReadFile = new JSONObject(ReadFile).getString("device");
            } catch (Throwable e) {
                Log(LOG.getStackString(e));
                ReadFile = null;
            }
            if (CommonUtils.isBlank(ReadFile)) {
                return null;
            }
            return SecurityUtils.decrypt(SecurityUtils.getSeed(), ReadFile);
        } catch (Throwable e2) {
            Log(LOG.getStackString(e2));
            return null;
        }
    }

    private boolean hasDataInSettings() {
        if (!CommonUtils.isBlank(readDataFromSettings()) && readDataFromSettings().length() > 0) {
            return true;
        }
        return false;
    }

    private boolean hasDataInSdcard() {
        if (!CommonUtils.isBlank(readDataFromSdCard()) && readDataFromSdCard().length() > 0) {
            return true;
        }
        return false;
    }

    public boolean checkCheckCode(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    public String generaterCheckCode() {
        String checkCodeString = getCheckCodeString();
        if (checkCodeString == null) {
            checkCodeString = ConfigConstant.WIRELESS_FILENAME;
        }
        checkCodeString = CommonUtils.MD5(checkCodeString);
        if (checkCodeString == null) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        return checkCodeString;
    }

    private String getCheckCodeString() {
        if (CommonUtils.isBlank(this.dv.getMrule())) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONObject(this.dv.getMrule()).getJSONArray(MiniDefine.aM);
            if (jSONArray == null) {
                return null;
            }
            Object str = new String();
            for (int i = 0; i != jSONArray.length(); i++) {
                if (jSONArray.getString(i).equals(mtid)) {
                    if (CommonUtils.isBlank(this.dv.getMtid())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMtid()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mutdid)) {
                    if (CommonUtils.isBlank(this.dv.getMutdid())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMutdid()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah1)) {
                    if (CommonUtils.isBlank(this.dv.getMah1())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah1()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah2)) {
                    if (CommonUtils.isBlank(this.dv.getMah2())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah2()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah3)) {
                    if (CommonUtils.isBlank(this.dv.getMah3())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah3()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah4)) {
                    if (CommonUtils.isBlank(this.dv.getMah4())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah4()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah5)) {
                    String stringBuilder;
                    if (CommonUtils.isBlank(this.dv.getMah5())) {
                        stringBuilder = new StringBuilder(String.valueOf(stringBuilder)).toString();
                    } else {
                        stringBuilder = new StringBuilder(String.valueOf(stringBuilder)).append(this.dv.getMah5()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah6)) {
                    if (CommonUtils.isBlank(this.dv.getMah6())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah6()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah7)) {
                    if (CommonUtils.isBlank(this.dv.getMah7())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah7()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah8)) {
                    if (CommonUtils.isBlank(this.dv.getMah8())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah8()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah9)) {
                    if (CommonUtils.isBlank(this.dv.getMah9())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah9()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mah10)) {
                    if (CommonUtils.isBlank(this.dv.getMah10())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMah10()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mas1)) {
                    if (CommonUtils.isBlank(this.dv.getMas1())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMas1()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mas2)) {
                    if (CommonUtils.isBlank(this.dv.getMas2())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMas2()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mas3)) {
                    if (CommonUtils.isBlank(this.dv.getMas3())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMas3()).toString();
                    }
                }
                if (jSONArray.getString(i).equals(mas4)) {
                    if (CommonUtils.isBlank(this.dv.getMas4())) {
                        str = new StringBuilder(String.valueOf(str)).toString();
                    } else {
                        str = new StringBuilder(String.valueOf(str)).append(this.dv.getMas4()).toString();
                    }
                }
            }
            return str;
        } catch (Throwable e) {
            Log(LOG.getStackString(e));
            return null;
        }
    }

    public boolean checkApdid() {
        String readDataFromSettings = readDataFromSettings();
        String readDataFromSdCard = readDataFromSdCard();
        if (!CommonUtils.isBlank(readDataFromSettings)) {
            return this.dv.getMpriDeviceId().equals(readDataFromSettings.substring(0, 32));
        }
        if (CommonUtils.isBlank(readDataFromSdCard)) {
            return false;
        }
        return this.dv.getMpriDeviceId().equals(readDataFromSdCard.subSequence(0, 32));
    }

    public IdResponseInfo UploadData(Context context) {
        IdResponseInfo idResponseInfo = new IdResponseInfo();
        idResponseInfo.setMsuccess(false);
        String generateUploadData = this.profile.generateUploadData(GetUploadInfo());
        if (generateUploadData == null || generateUploadData.length() < 0) {
            return idResponseInfo;
        }
        try {
            HttpResponse uploadCollectedData = new HttpFetcher().uploadCollectedData(context, ConfigConstant.DATA_POST_ADDRESS, SERVICEID, generateUploadData, VER, false);
            if (uploadCollectedData != null && uploadCollectedData.getStatusLine().getStatusCode() == ConfigConstant.RESPONSE_CODE) {
                return new Profile().ParseResponse(EntityUtils.toString(uploadCollectedData.getEntity()));
            }
            idResponseInfo.setMsuccess(false);
            return idResponseInfo;
        } catch (Throwable e) {
            Log(LOG.getStackString(e));
        }
    }

    public String UpdateId(Context context) {
        IdResponseInfo UploadData = UploadData(context);
        if (UploadData != null) {
            try {
                if (UploadData.isMsuccess()) {
                    String str = UploadData.getMapdid() + UploadData.getMtime();
                    Map hashMap = new HashMap();
                    hashMap.put(mDeviceId, UploadData.getMapdid());
                    hashMap.put(mPriDeviceId, UploadData.getMapdid());
                    hashMap.put(mtime, UploadData.getMtime());
                    hashMap.put(mCheckCode, UploadData.getMcheckcode());
                    hashMap.put(mRule, UploadData.getMrule());
                    hashMap.put(mApdtk, UploadData.getMapdtk());
                    try {
                        WritePrivateData(context, new Profile().generatePrivateData(hashMap));
                    } catch (JSONException e) {
                    }
                    WriteDataToSettings(str);
                    WriteDataToSdCard(str);
                    return UploadData.getMapdid();
                }
            } catch (Throwable e2) {
                Log(LOG.getStackString(e2));
            }
        }
        return null;
    }

    private void Log(String str) {
        List arrayList = new ArrayList();
        if (CommonUtils.isBlank(this.dv.getMtid())) {
            arrayList.add(this.dv.getMtid().substring(0, 20));
        }
        if (CommonUtils.isBlank(this.dv.getMutdid())) {
            arrayList.add(this.dv.getMutdid().substring(0, 20));
        }
        if (CommonUtils.isBlank(this.dv.getMappId())) {
            arrayList.add(this.dv.getMappId().substring(0, 20));
        }
        arrayList.add(str);
        LOG.logMessage(arrayList);
    }
}
