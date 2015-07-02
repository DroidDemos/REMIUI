package com.ta.utdid2.device;

import android.content.Context;
import android.provider.Settings.System;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.IntUtils;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.core.persistent.PersistentConfiguration;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.zip.Adler32;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class UTUtdid {
    private static final Object CREATE_LOCK;
    private static final String HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
    private static final String S_GLOBAL_PERSISTENT_CONFIG_DIR;
    private static final String S_GLOBAL_PERSISTENT_CONFIG_KEY = "Alvin2";
    private static final String S_LOCAL_STORAGE_KEY = "ContextData";
    private static final String S_LOCAL_STORAGE_NAME = ".DataStorage";
    static final String UM_SETTINGS_STORAGE = "dxCRMxhQkdGePGnp";
    static final String UM_SETTINGS_STORAGE_NEW = "mqBRboGZkQPcAkyk";
    private static UTUtdid s_umutdid;
    private String mCBDomain;
    private String mCBKey;
    private Context mContext;
    private PersistentConfiguration mPC;
    private Pattern mPattern;
    private PersistentConfiguration mTaoPC;
    private String mUtdid;
    private UTUtdidHelper mUtdidHelper;

    static {
        CREATE_LOCK = new Object();
        s_umutdid = null;
        S_GLOBAL_PERSISTENT_CONFIG_DIR = ".UTSystemConfig" + File.separator + "Global";
    }

    public UTUtdid(Context context) {
        this.mContext = null;
        this.mUtdid = null;
        this.mUtdidHelper = null;
        this.mCBKey = "xx_utdid_key";
        this.mCBDomain = "xx_utdid_domain";
        this.mPC = null;
        this.mTaoPC = null;
        this.mPattern = Pattern.compile("[^0-9a-zA-Z=/+]+");
        this.mContext = context;
        this.mTaoPC = new PersistentConfiguration(context, S_GLOBAL_PERSISTENT_CONFIG_DIR, S_GLOBAL_PERSISTENT_CONFIG_KEY, false, true);
        this.mPC = new PersistentConfiguration(context, S_LOCAL_STORAGE_NAME, S_LOCAL_STORAGE_KEY, false, true);
        this.mUtdidHelper = new UTUtdidHelper();
        this.mCBKey = String.format("K_%d", new Object[]{Integer.valueOf(StringUtils.hashCode(this.mCBKey))});
        this.mCBDomain = String.format("D_%d", new Object[]{Integer.valueOf(StringUtils.hashCode(this.mCBDomain))});
    }

    public static UTUtdid instance(Context context) {
        if (context != null && s_umutdid == null) {
            synchronized (CREATE_LOCK) {
                if (s_umutdid == null) {
                    s_umutdid = new UTUtdid(context);
                }
            }
        }
        return s_umutdid;
    }

    static long getMetadataCheckSum(Device device) {
        if (device != null) {
            String format = String.format("%s%s%s%s%s", new Object[]{device.getUtdid(), device.getDeviceId(), Long.valueOf(device.getCreateTimestamp()), device.getImsi(), device.getImei()});
            if (!StringUtils.isEmpty(format)) {
                Adler32 adler32 = new Adler32();
                adler32.reset();
                adler32.update(format.getBytes());
                return adler32.getValue();
            }
        }
        return 0;
    }

    private void saveUtdidToTaoPPC(String str) {
        if (isValidUTDID(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() == 24 && this.mTaoPC != null) {
                String string = this.mTaoPC.getString("UTDID");
                String string2 = this.mTaoPC.getString("EI");
                if (StringUtils.isEmpty(string2)) {
                    string2 = PhoneInfoUtils.getImei(this.mContext);
                }
                String string3 = this.mTaoPC.getString("SI");
                if (StringUtils.isEmpty(string3)) {
                    string3 = PhoneInfoUtils.getImsi(this.mContext);
                }
                String string4 = this.mTaoPC.getString("DID");
                if (StringUtils.isEmpty(string4)) {
                    string4 = string2;
                }
                if (string == null || !string.equals(str)) {
                    Device device = new Device();
                    device.setImei(string2);
                    device.setImsi(string3);
                    device.setUtdid(str);
                    device.setDeviceId(string4);
                    device.setCreateTimestamp(System.currentTimeMillis());
                    this.mTaoPC.putString("UTDID", str);
                    this.mTaoPC.putString("EI", device.getImei());
                    this.mTaoPC.putString("SI", device.getImsi());
                    this.mTaoPC.putString("DID", device.getDeviceId());
                    this.mTaoPC.putLong("timestamp", device.getCreateTimestamp());
                    this.mTaoPC.putLong("S", getMetadataCheckSum(device));
                    this.mTaoPC.commit();
                }
            }
        }
    }

    private void saveUtdidToLocalStorage(String str) {
        if (str != null && this.mPC != null && !str.equals(this.mPC.getString(this.mCBKey))) {
            this.mPC.putString(this.mCBKey, str);
            this.mPC.commit();
        }
    }

    private void saveUtdidToNewSettings(String str) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && isValidUTDID(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length() && !isValidUTDID(System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW))) {
                System.putString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW, str);
            }
        }
    }

    private void syncUTDIDToSettings(String str) {
        if (!str.equals(System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE))) {
            System.putString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE, str);
        }
    }

    private void saveUtdidToSettings(String str) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0 && str != null) {
            syncUTDIDToSettings(str);
        }
    }

    private String getUtdidFromTaoPPC() {
        if (this.mTaoPC != null) {
            String string = this.mTaoPC.getString("UTDID");
            if (!(StringUtils.isEmpty(string) || this.mUtdidHelper.packUtdidStr(string) == null)) {
                return string;
            }
        }
        return null;
    }

    private boolean isValidUTDID(String str) {
        if (str == null) {
            return false;
        }
        if (str.endsWith("\n")) {
            str = str.substring(0, str.length() - 1);
        }
        if (24 != str.length() || this.mPattern.matcher(str).find()) {
            return false;
        }
        return true;
    }

    public synchronized String getValue() {
        String str;
        if (this.mUtdid != null) {
            str = this.mUtdid;
        } else {
            str = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE_NEW);
            if (!isValidUTDID(str)) {
                UTUtdidHelper2 uTUtdidHelper2 = new UTUtdidHelper2();
                Object obj = null;
                String string = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
                if (StringUtils.isEmpty(string)) {
                    obj = 1;
                } else {
                    str = uTUtdidHelper2.dePackWithBase64(string);
                    if (isValidUTDID(str)) {
                        saveUtdidToNewSettings(str);
                    } else {
                        str = uTUtdidHelper2.dePack(string);
                        if (isValidUTDID(str)) {
                            str = this.mUtdidHelper.packUtdidStr(str);
                            if (!StringUtils.isEmpty(str)) {
                                saveUtdidToSettings(str);
                                str = System.getString(this.mContext.getContentResolver(), UM_SETTINGS_STORAGE);
                                string = this.mUtdidHelper.dePack(str);
                                if (isValidUTDID(string)) {
                                    this.mUtdid = string;
                                    saveUtdidToTaoPPC(string);
                                    saveUtdidToLocalStorage(str);
                                    saveUtdidToNewSettings(this.mUtdid);
                                    str = this.mUtdid;
                                }
                            }
                        }
                        str = string;
                        string = this.mUtdidHelper.dePack(str);
                        if (isValidUTDID(string)) {
                            this.mUtdid = string;
                            saveUtdidToTaoPPC(string);
                            saveUtdidToLocalStorage(str);
                            saveUtdidToNewSettings(this.mUtdid);
                            str = this.mUtdid;
                        }
                    }
                }
                str = getUtdidFromTaoPPC();
                if (isValidUTDID(str)) {
                    string = this.mUtdidHelper.packUtdidStr(str);
                    if (obj != null) {
                        saveUtdidToSettings(string);
                    }
                    saveUtdidToNewSettings(str);
                    saveUtdidToLocalStorage(string);
                    this.mUtdid = str;
                } else {
                    string = this.mPC.getString(this.mCBKey);
                    if (!StringUtils.isEmpty(string)) {
                        str = uTUtdidHelper2.dePack(string);
                        if (!isValidUTDID(str)) {
                            str = this.mUtdidHelper.dePack(string);
                        }
                        if (isValidUTDID(str)) {
                            string = this.mUtdidHelper.packUtdidStr(str);
                            if (!StringUtils.isEmpty(str)) {
                                this.mUtdid = str;
                                if (obj != null) {
                                    saveUtdidToSettings(string);
                                }
                                saveUtdidToTaoPPC(this.mUtdid);
                                str = this.mUtdid;
                            }
                        }
                    }
                    try {
                        byte[] _generateUtdid = _generateUtdid();
                        if (_generateUtdid != null) {
                            this.mUtdid = Base64.encodeToString(_generateUtdid, 2);
                            saveUtdidToTaoPPC(this.mUtdid);
                            str = this.mUtdidHelper.pack(_generateUtdid);
                            if (str != null) {
                                if (obj != null) {
                                    saveUtdidToSettings(str);
                                }
                                saveUtdidToLocalStorage(str);
                            }
                            str = this.mUtdid;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    str = null;
                }
            }
        }
        return str;
    }

    private final byte[] _generateUtdid() {
        String imei;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nextInt = new Random().nextInt();
        byte[] bytes = IntUtils.getBytes(currentTimeMillis);
        byte[] bytes2 = IntUtils.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            imei = PhoneInfoUtils.getImei(this.mContext);
        } catch (Exception e) {
            imei = ConfigConstant.WIRELESS_FILENAME + new Random().nextInt();
        }
        byteArrayOutputStream.write(IntUtils.getBytes(StringUtils.hashCode(imei)), 0, 4);
        imei = ConfigConstant.WIRELESS_FILENAME;
        byteArrayOutputStream.write(IntUtils.getBytes(StringUtils.hashCode(_calcHmac(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    private static String _calcHmac(byte[] bArr) {
        String str = HMAC_KEY;
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(str.getBytes(), instance.getAlgorithm()));
        return Base64.encodeToString(instance.doFinal(bArr), 2);
    }
}
