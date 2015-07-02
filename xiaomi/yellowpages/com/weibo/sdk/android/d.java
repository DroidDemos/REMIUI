package com.weibo.sdk.android;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.util.ArrayList;

/* compiled from: WeiboParameters */
public class d {
    private ArrayList mKeys;
    private ArrayList ww;

    public d() {
        this.mKeys = new ArrayList();
        this.ww = new ArrayList();
    }

    public void t(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.mKeys.add(str);
            this.ww.add(str2);
        }
    }

    public void remove(String str) {
        int indexOf = this.mKeys.indexOf(str);
        if (indexOf >= 0) {
            this.mKeys.remove(indexOf);
            this.ww.remove(indexOf);
        }
    }

    private int bp(String str) {
        if (this.mKeys.contains(str)) {
            return this.mKeys.indexOf(str);
        }
        return -1;
    }

    public String J(int i) {
        if (i < 0 || i >= this.mKeys.size()) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        return (String) this.mKeys.get(i);
    }

    public String getValue(String str) {
        int bp = bp(str);
        if (bp < 0 || bp >= this.mKeys.size()) {
            return null;
        }
        return (String) this.ww.get(bp);
    }

    public String getValue(int i) {
        if (i < 0 || i >= this.mKeys.size()) {
            return null;
        }
        return (String) this.ww.get(i);
    }

    public int size() {
        return this.mKeys.size();
    }
}
