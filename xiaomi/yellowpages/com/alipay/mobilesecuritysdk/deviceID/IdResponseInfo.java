package com.alipay.mobilesecuritysdk.deviceID;

import java.util.List;

public class IdResponseInfo {
    private List arrList;
    private String fuction;
    private String mapdid;
    private String mapdtk;
    private String mcheckcode;
    private String merrorcode;
    private String mrule;
    private boolean msuccess;
    private String mtime;
    private String mversion;

    public String getFuction() {
        return this.fuction;
    }

    public void setFuction(String str) {
        this.fuction = str;
    }

    public List getArrList() {
        return this.arrList;
    }

    public void setArrList(List list) {
        this.arrList = list;
    }

    public String getMcheckcode() {
        return this.mcheckcode;
    }

    public void setMcheckcode(String str) {
        this.mcheckcode = str;
    }

    public String getMtime() {
        return this.mtime;
    }

    public void setMtime(String str) {
        this.mtime = str;
    }

    public boolean isMsuccess() {
        return this.msuccess;
    }

    public void setMsuccess(boolean z) {
        this.msuccess = z;
    }

    public String isMerrorcode() {
        return this.merrorcode;
    }

    public void setMerrorcode(String str) {
        this.merrorcode = str;
    }

    public String getMversion() {
        return this.mversion;
    }

    public void setMversion(String str) {
        this.mversion = str;
    }

    public String getMapdid() {
        return this.mapdid;
    }

    public void setMapdid(String str) {
        this.mapdid = str;
    }

    public String getMapdtk() {
        return this.mapdtk;
    }

    public void setMapdtk(String str) {
        this.mapdtk = str;
    }

    public String getMrule() {
        return this.mrule;
    }

    public void setMrule(String str) {
        this.mrule = str;
    }
}
