package com.alipay.mobilesecuritysdk.datainfo;

import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.math.BigDecimal;
import java.util.List;

public class LocationInfo {
    private final int DEFINE_NUM;
    private final double MAX_LATITUDE;
    private final double MAX_LONGITUDE;
    private final double MIN_LATITUDE;
    private final double MIN_LONGITUDE;
    private String cid;
    private String lac;
    private String latitude;
    private String longitude;
    private String mcc;
    private String mnc;
    private String phonetype;
    private List tid;
    private String time;
    private List wifi;

    public LocationInfo() {
        this.DEFINE_NUM = 14400;
        this.MAX_LONGITUDE = 180.0d;
        this.MIN_LONGITUDE = -180.0d;
        this.MAX_LATITUDE = 90.0d;
        this.MIN_LATITUDE = -90.0d;
    }

    public List getTid() {
        return this.tid;
    }

    public void setTid(List list) {
        this.tid = list;
    }

    public List getWifi() {
        return this.wifi;
    }

    public void setWifi(List list) {
        this.wifi = list;
    }

    public String getPhonetype() {
        return this.phonetype;
    }

    public void setPhonetype(String str) {
        this.phonetype = str;
    }

    public String getMcc() {
        return this.mcc;
    }

    public void setMcc(String str) {
        this.mcc = str;
    }

    public String getMnc() {
        return this.mnc;
    }

    public void setMnc(String str) {
        this.mnc = str;
    }

    public boolean validate() {
        if ((CommonUtils.isBlank(this.latitude) || CommonUtils.isBlank(this.longitude)) && (CommonUtils.isBlank(this.cid) || CommonUtils.isBlank(this.lac))) {
            return false;
        }
        return true;
    }

    private String toString(double d) {
        return String.valueOf(new BigDecimal(d).setScale(5, 4).doubleValue());
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public void setLongitude(double d) {
        if (d < 180.0d && d > -180.0d) {
            this.longitude = toString(d);
        }
    }

    public void setLongitude(int i) {
        setLongitude(((double) i) / 14400.0d);
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        if (d < 90.0d && d > -90.0d) {
            this.latitude = toString(d);
        }
    }

    public void setLatitude(int i) {
        setLatitude(((double) i) / 14400.0d);
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getLac() {
        return this.lac;
    }

    public void setLac(String str) {
        this.lac = str;
    }
}
