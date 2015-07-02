package com.xiaomi.d.c;

import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.d.e.g;

public class h extends g {
    private String a;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private boolean i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private boolean o;

    public h() {
        this.a = null;
        this.d = null;
        this.i = false;
        this.k = ConfigConstant.WIRELESS_FILENAME;
        this.l = ConfigConstant.WIRELESS_FILENAME;
        this.m = ConfigConstant.WIRELESS_FILENAME;
        this.n = ConfigConstant.WIRELESS_FILENAME;
        this.o = false;
    }

    public h(Bundle bundle) {
        super(bundle);
        this.a = null;
        this.d = null;
        this.i = false;
        this.k = ConfigConstant.WIRELESS_FILENAME;
        this.l = ConfigConstant.WIRELESS_FILENAME;
        this.m = ConfigConstant.WIRELESS_FILENAME;
        this.n = ConfigConstant.WIRELESS_FILENAME;
        this.o = false;
        this.a = bundle.getString("ext_msg_type");
        this.e = bundle.getString("ext_msg_lang");
        this.d = bundle.getString("ext_msg_thread");
        this.f = bundle.getString("ext_msg_sub");
        this.g = bundle.getString("ext_msg_body");
        this.h = bundle.getString("ext_body_encode");
        this.j = bundle.getString("ext_msg_appid");
        this.i = bundle.getBoolean("ext_msg_trans", false);
        this.k = bundle.getString("ext_msg_seq");
        this.l = bundle.getString("ext_msg_mseq");
        this.m = bundle.getString("ext_msg_fseq");
        this.n = bundle.getString("ext_msg_status");
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<message");
        if (jb() != null) {
            stringBuilder.append(" xmlns=\"").append(jb()).append("\"");
        }
        if (this.e != null) {
            stringBuilder.append(" xml:lang=\"").append(i()).append("\"");
        }
        if (k() != null) {
            stringBuilder.append(" id=\"").append(k()).append("\"");
        }
        if (m() != null) {
            stringBuilder.append(" to=\"").append(g.a(m())).append("\"");
        }
        if (!TextUtils.isEmpty(e())) {
            stringBuilder.append(" seq=\"").append(e()).append("\"");
        }
        if (!TextUtils.isEmpty(f())) {
            stringBuilder.append(" mseq=\"").append(f()).append("\"");
        }
        if (!TextUtils.isEmpty(g())) {
            stringBuilder.append(" fseq=\"").append(g()).append("\"");
        }
        if (!TextUtils.isEmpty(h())) {
            stringBuilder.append(" status=\"").append(h()).append("\"");
        }
        if (iW() != null) {
            stringBuilder.append(" from=\"").append(g.a(iW())).append("\"");
        }
        if (l() != null) {
            stringBuilder.append(" chid=\"").append(g.a(l())).append("\"");
        }
        if (this.i) {
            stringBuilder.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.j)) {
            stringBuilder.append(" appid=\"").append(d()).append("\"");
        }
        if (!TextUtils.isEmpty(this.a)) {
            stringBuilder.append(" type=\"").append(this.a).append("\"");
        }
        if (this.o) {
            stringBuilder.append(" s=\"1\"");
        }
        stringBuilder.append(">");
        if (this.f != null) {
            stringBuilder.append("<subject>").append(g.a(this.f));
            stringBuilder.append("</subject>");
        }
        if (this.g != null) {
            stringBuilder.append("<body");
            if (!TextUtils.isEmpty(this.h)) {
                stringBuilder.append(" encode=\"").append(this.h).append("\"");
            }
            stringBuilder.append(">").append(g.a(this.g)).append("</body>");
        }
        if (this.d != null) {
            stringBuilder.append("<thread>").append(this.d).append("</thread>");
        }
        if (ConfigConstant.LOG_JSON_STR_ERROR.equalsIgnoreCase(this.a)) {
            c iX = iX();
            if (iX != null) {
                stringBuilder.append(iX.d());
            }
        }
        stringBuilder.append(ja());
        stringBuilder.append("</message>");
        return stringBuilder.toString();
    }

    public void a(String str) {
        this.j = str;
    }

    public void a(String str, String str2) {
        this.g = str;
        this.h = str2;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public String b() {
        return this.a;
    }

    public void b(String str) {
        this.k = str;
    }

    public void b(boolean z) {
        this.o = z;
    }

    public void c(String str) {
        this.l = str;
    }

    public void cC(String str) {
        this.g = str;
    }

    public void cD(String str) {
        this.d = str;
    }

    public void cE(String str) {
        this.e = str;
    }

    public String d() {
        return this.j;
    }

    public void d(String str) {
        this.m = str;
    }

    public String e() {
        return this.k;
    }

    public void e(String str) {
        this.n = str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r1 = 0;
        r0 = 1;
        if (r4 != r5) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r5 == 0) goto L_0x006e;
    L_0x0008:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 != r3) goto L_0x006e;
    L_0x0012:
        r5 = (com.xiaomi.d.c.h) r5;
        r2 = super.equals(r5);
        if (r2 == 0) goto L_0x006e;
    L_0x001a:
        r2 = r4.g;
        if (r2 == 0) goto L_0x005a;
    L_0x001e:
        r2 = r4.g;
        r3 = r5.g;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x006e;
    L_0x0028:
        r2 = r4.e;
        if (r2 == 0) goto L_0x005f;
    L_0x002c:
        r2 = r4.e;
        r3 = r5.e;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x006e;
    L_0x0036:
        r2 = r4.f;
        if (r2 == 0) goto L_0x0064;
    L_0x003a:
        r2 = r4.f;
        r3 = r5.f;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x006e;
    L_0x0044:
        r2 = r4.d;
        if (r2 == 0) goto L_0x0069;
    L_0x0048:
        r2 = r4.d;
        r3 = r5.d;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x006e;
    L_0x0052:
        r2 = r4.a;
        r3 = r5.a;
        if (r2 != r3) goto L_0x0005;
    L_0x0058:
        r1 = r0;
        goto L_0x0005;
    L_0x005a:
        r2 = r5.g;
        if (r2 == 0) goto L_0x0028;
    L_0x005e:
        goto L_0x0005;
    L_0x005f:
        r2 = r5.e;
        if (r2 == 0) goto L_0x0036;
    L_0x0063:
        goto L_0x0005;
    L_0x0064:
        r2 = r5.f;
        if (r2 == 0) goto L_0x0044;
    L_0x0068:
        goto L_0x0005;
    L_0x0069:
        r2 = r5.d;
        if (r2 == 0) goto L_0x0052;
    L_0x006d:
        goto L_0x0005;
    L_0x006e:
        r0 = r1;
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.d.c.h.equals(java.lang.Object):boolean");
    }

    public String f() {
        return this.l;
    }

    public void f(String str) {
        this.a = str;
    }

    public String g() {
        return this.m;
    }

    public void g(String str) {
        this.f = str;
    }

    public String h() {
        return this.n;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.e != null ? this.e.hashCode() : 0) + (((this.d != null ? this.d.hashCode() : 0) + (((this.g != null ? this.g.hashCode() : 0) + ((this.a != null ? this.a.hashCode() : 0) * 31)) * 31)) * 31)) * 31;
        if (this.f != null) {
            i = this.f.hashCode();
        }
        return hashCode + i;
    }

    public String i() {
        return this.e;
    }

    public Bundle iK() {
        Bundle iK = super.iK();
        if (!TextUtils.isEmpty(this.a)) {
            iK.putString("ext_msg_type", this.a);
        }
        if (this.e != null) {
            iK.putString("ext_msg_lang", this.e);
        }
        if (this.f != null) {
            iK.putString("ext_msg_sub", this.f);
        }
        if (this.g != null) {
            iK.putString("ext_msg_body", this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            iK.putString("ext_body_encode", this.h);
        }
        if (this.d != null) {
            iK.putString("ext_msg_thread", this.d);
        }
        if (this.j != null) {
            iK.putString("ext_msg_appid", this.j);
        }
        if (this.i) {
            iK.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty(this.k)) {
            iK.putString("ext_msg_seq", this.k);
        }
        if (!TextUtils.isEmpty(this.l)) {
            iK.putString("ext_msg_mseq", this.l);
        }
        if (!TextUtils.isEmpty(this.m)) {
            iK.putString("ext_msg_fseq", this.m);
        }
        if (!TextUtils.isEmpty(this.n)) {
            iK.putString("ext_msg_status", this.n);
        }
        return iK;
    }
}
