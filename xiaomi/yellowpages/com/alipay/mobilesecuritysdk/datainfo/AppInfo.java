package com.alipay.mobilesecuritysdk.datainfo;

import com.alipay.mobilesecuritysdk.util.CommonUtils;

public class AppInfo {
    private String pkeyhash;
    private String pkgName;

    public boolean validate() {
        if (CommonUtils.isBlank(this.pkgName) || CommonUtils.isBlank(this.pkeyhash)) {
            return false;
        }
        return true;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getPkeyhash() {
        return this.pkeyhash;
    }

    public void setPkeyhash(String str) {
        this.pkeyhash = str;
    }
}
