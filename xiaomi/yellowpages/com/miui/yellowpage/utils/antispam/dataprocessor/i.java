package com.miui.yellowpage.utils.antispam.dataprocessor;

/* compiled from: Tuple */
public class i {
    private String BL;
    private String BM;
    private String category;

    i(String str, String str2, String str3) {
        this.category = str;
        this.BL = str2;
        this.BM = str3;
    }

    public String toString() {
        return String.format("[cat:%s, p:%s, cnt:%s]", new Object[]{getCategory(), getProvider(), gO()});
    }

    public String getCategory() {
        return this.category;
    }

    public String getProvider() {
        return this.BL;
    }

    public String gO() {
        return this.BM;
    }
}
