package com.alipay.mobilesecuritysdk.deviceID;

import android.provider.BaseColumns;

public class DeviceMetaData implements BaseColumns {
    public static final String AUTH = "content://com.alipay.mobilesecuritysdk.deviceID.DeviceContentProvider";
    public static final String DATABASE_NAME = "device.db";
    public static final int DATABASE_VER = 1;
    public static final String DEVICE_TABLE_NAME = "devices";
    private String mah1;
    private String mah10;
    private String mah2;
    private String mah3;
    private String mah4;
    private String mah5;
    private String mah6;
    private String mah7;
    private String mah8;
    private String mah9;
    private String mapdtk;
    private String mappId;
    private String mas1;
    private String mas2;
    private String mas3;
    private String mas4;
    private String mcheckCode;
    private String mdeviceId;
    private String mpriDeviceId;
    private String mrule;
    private String mtid;
    private String mtime;
    private String mutdid;

    public DeviceMetaData() {
        this.mah1 = null;
        this.mah2 = null;
        this.mah3 = null;
        this.mah4 = null;
        this.mah5 = null;
        this.mah6 = null;
        this.mah7 = null;
        this.mah8 = null;
        this.mah9 = null;
        this.mah10 = null;
        this.mas1 = null;
        this.mas2 = null;
        this.mas3 = null;
        this.mas4 = null;
        this.mtime = null;
        this.mtid = null;
        this.mutdid = null;
        this.mappId = null;
    }

    public String getMappId() {
        return this.mappId;
    }

    public void setMappId(String str) {
        this.mappId = str;
    }

    public String getMdeviceId() {
        return this.mdeviceId;
    }

    public void setMdeviceId(String str) {
        this.mdeviceId = str;
    }

    public String getMpriDeviceId() {
        return this.mpriDeviceId;
    }

    public void setMpriDeviceId(String str) {
        this.mpriDeviceId = str;
    }

    public String getMcheckCode() {
        return this.mcheckCode;
    }

    public void setMcheckCode(String str) {
        this.mcheckCode = str;
    }

    public String getMrule() {
        return this.mrule;
    }

    public void setMrule(String str) {
        this.mrule = str;
    }

    public String getMtid() {
        return this.mtid;
    }

    public void setMtid(String str) {
        this.mtid = str;
    }

    public String getMutdid() {
        return this.mutdid;
    }

    public void setMutdid(String str) {
        this.mutdid = str;
    }

    public String getMah1() {
        return this.mah1;
    }

    public void setMah1(String str) {
        this.mah1 = str;
    }

    public String getMah2() {
        return this.mah2;
    }

    public void setMah2(String str) {
        this.mah2 = str;
    }

    public String getMah3() {
        return this.mah3;
    }

    public void setMah3(String str) {
        this.mah3 = str;
    }

    public String getMah4() {
        return this.mah4;
    }

    public void setMah4(String str) {
        this.mah4 = str;
    }

    public String getMah5() {
        return this.mah5;
    }

    public void setMah5(String str) {
        this.mah5 = str;
    }

    public String getMah6() {
        return this.mah6;
    }

    public void setMah6(String str) {
        this.mah6 = str;
    }

    public String getMah7() {
        return this.mah7;
    }

    public void setMah7(String str) {
        this.mah7 = str;
    }

    public String getMah8() {
        return this.mah8;
    }

    public void setMah8(String str) {
        this.mah8 = str;
    }

    public String getMah9() {
        return this.mah9;
    }

    public void setMah9(String str) {
        this.mah9 = str;
    }

    public String getMah10() {
        return this.mah10;
    }

    public void setMah10(String str) {
        this.mah10 = str;
    }

    public String getMtime() {
        return this.mtime;
    }

    public void setMtime(String str) {
        this.mtime = str;
    }

    public String getMapdtk() {
        return this.mapdtk;
    }

    public void setMapdtk(String str) {
        this.mapdtk = str;
    }

    public String getMas1() {
        return this.mas1;
    }

    public void setMas1(String str) {
        this.mas1 = str;
    }

    public String getMas2() {
        return this.mas2;
    }

    public void setMas2(String str) {
        this.mas2 = str;
    }

    public String getMas3() {
        return this.mas3;
    }

    public void setMas3(String str) {
        this.mas3 = str;
    }

    public String getMas4() {
        return this.mas4;
    }

    public void setMas4(String str) {
        this.mas4 = str;
    }
}
