package com.xiaomi.d;

import android.text.TextUtils;
import com.xiaomi.c.i;
import java.net.URI;

public class k extends r {
    private i FR;
    private boolean a;
    private String b;
    private String d;

    public k(boolean z, i iVar, int i, String str, String str2, t tVar) {
        super(null, i, str2, tVar);
        this.FR = null;
        this.d = "mibind.chat.gslb.mi-idc.com";
        this.FR = iVar;
        this.a = z;
        if (str == null) {
            str = "/";
        }
        this.b = str;
    }

    public String b() {
        return this.d;
    }

    public void c(i iVar) {
        if (iVar != null) {
            this.FR = iVar;
            this.d = "mibind.chat.gslb.mi-idc.com";
            if (!this.FR.dD().isEmpty()) {
                String str = (String) this.FR.dD().get(0);
                if (!TextUtils.isEmpty(str)) {
                    this.d = str;
                }
            }
        }
    }

    public i hu() {
        return this.FR;
    }

    public URI hv() {
        if (this.b.charAt(0) != '/') {
            this.b = '/' + this.b;
        }
        return new URI((this.a ? "https://" : "http://") + this.d + ":" + g() + this.b);
    }
}
