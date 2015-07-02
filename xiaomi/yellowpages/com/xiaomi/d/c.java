package com.xiaomi.d;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.GlobalConstants;
import com.xiaomi.d.c.g;
import com.xiaomi.d.c.j;
import com.xiaomi.f.a.a.b;
import com.xiaomi.push.service.m;
import java.util.HashMap;
import java.util.Map;

public class c extends g {
    final /* synthetic */ y ve;

    public c(y yVar, m mVar, String str, s sVar) {
        Object obj;
        this.ve = yVar;
        Map hashMap = new HashMap();
        int k = sVar.k();
        hashMap.put("challenge", str);
        hashMap.put("token", mVar.c);
        hashMap.put("chid", mVar.h);
        hashMap.put("from", mVar.b);
        hashMap.put("id", k());
        hashMap.put("to", "xiaomi.com");
        if (mVar.e) {
            hashMap.put("kick", GlobalConstants.d);
        } else {
            hashMap.put("kick", Profile.devicever);
        }
        if (sVar.hx() > 0) {
            String format = String.format("conn:%1$d,t:%2$d", new Object[]{Integer.valueOf(k), Long.valueOf(sVar.hx())});
            hashMap.put("pf", format);
            sVar.ao();
            sVar.bu();
            obj = format;
        } else {
            obj = null;
        }
        if (TextUtils.isEmpty(mVar.f)) {
            hashMap.put("client_attrs", ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap.put("client_attrs", mVar.f);
        }
        if (TextUtils.isEmpty(mVar.g)) {
            hashMap.put("cloud_attrs", ConfigConstant.WIRELESS_FILENAME);
        } else {
            hashMap.put("cloud_attrs", mVar.g);
        }
        String a = (mVar.d.equals("XIAOMI-PASS") || mVar.d.equals("XMPUSH-PASS")) ? b.a(mVar.d, null, hashMap, mVar.i) : mVar.d.equals("XIAOMI-SASL") ? null : null;
        cx(mVar.h);
        cz(mVar.b);
        cy("xiaomi.com");
        j jVar = new j("token", null, (String[]) null, (String[]) null);
        jVar.b(mVar.c);
        a(jVar);
        jVar = new j("kick", null, (String[]) null, (String[]) null);
        jVar.b(mVar.e ? GlobalConstants.d : Profile.devicever);
        a(jVar);
        jVar = new j("sig", null, (String[]) null, (String[]) null);
        jVar.b(a);
        a(jVar);
        j jVar2 = new j("method", null, (String[]) null, (String[]) null);
        if (TextUtils.isEmpty(mVar.d)) {
            jVar2.b("XIAOMI-SASL");
        } else {
            jVar2.b(mVar.d);
        }
        a(jVar2);
        jVar2 = new j("client_attrs", null, (String[]) null, (String[]) null);
        jVar2.b(mVar.f == null ? ConfigConstant.WIRELESS_FILENAME : com.xiaomi.d.e.g.a(mVar.f));
        a(jVar2);
        jVar2 = new j("cloud_attrs", null, (String[]) null, (String[]) null);
        jVar2.b(mVar.g == null ? ConfigConstant.WIRELESS_FILENAME : com.xiaomi.d.e.g.a(mVar.g));
        a(jVar2);
        if (!TextUtils.isEmpty(obj)) {
            jVar2 = new j("pf", null, (String[]) null, (String[]) null);
            jVar2.b(obj);
            a(jVar2);
        }
    }

    public String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<bind ");
        if (k() != null) {
            stringBuilder.append("id=\"" + k() + "\" ");
        }
        if (m() != null) {
            stringBuilder.append("to=\"").append(com.xiaomi.d.e.g.a(m())).append("\" ");
        }
        if (iW() != null) {
            stringBuilder.append("from=\"").append(com.xiaomi.d.e.g.a(iW())).append("\" ");
        }
        if (l() != null) {
            stringBuilder.append("chid=\"").append(com.xiaomi.d.e.g.a(l())).append("\">");
        }
        if (iY() != null) {
            for (j d : iY()) {
                stringBuilder.append(d.d());
            }
        }
        stringBuilder.append("</bind>");
        return stringBuilder.toString();
    }
}
