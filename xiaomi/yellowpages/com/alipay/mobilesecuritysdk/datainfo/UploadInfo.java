package com.alipay.mobilesecuritysdk.datainfo;

import java.util.ArrayList;
import java.util.List;

public class UploadInfo {
    private List appinfos;
    private List locates;
    private List tid;

    public UploadInfo() {
        this.locates = new ArrayList();
        this.appinfos = new ArrayList();
    }

    public List getTid() {
        return this.tid;
    }

    public void setTid(List list) {
        this.tid = list;
    }

    public List getLocates() {
        return this.locates;
    }

    public void setLocates(List list) {
        this.locates = list;
    }

    public List getAppinfos() {
        return this.appinfos;
    }

    public void setAppinfos(List list) {
        this.appinfos = list;
    }
}
