package com.xiaomi.d;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.b.a.G;
import com.xiaomi.b.a.ab;
import com.xiaomi.b.a.j;
import com.xiaomi.b.a.z;
import com.xiaomi.d.c.f;
import com.xiaomi.d.c.f.b;
import com.xiaomi.d.e.c;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class h implements G {
    private j Af;

    public h(j jVar) {
        this.Af = jVar;
    }

    public boolean b(ab abVar) {
        Exception e;
        boolean z = false;
        boolean z2 = true;
        z cf = abVar.cf();
        if (cf != null) {
            try {
                if (this.Af.i()) {
                    Object a = cf.a(j.j("xm", "challenge"));
                    if (!TextUtils.isEmpty(a)) {
                        this.Af.a((String) a);
                        z = true;
                    }
                }
                try {
                    if (this.Af.b == null) {
                        this.Af.b = cf.a(j.j("xm", "sid"));
                        z = true;
                    }
                    if (this.Af.a == null) {
                        this.Af.a = cf.a(j.j("xm", "authid"));
                        z = true;
                    }
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                    newPullParser.setInput(new StringReader(cf.b()));
                    newPullParser.getEventType();
                    int next;
                    do {
                        next = newPullParser.next();
                        this.Af.bw();
                        if (next == 2 && !newPullParser.getName().equals("body")) {
                            if (newPullParser.getName().equals("message")) {
                                try {
                                    this.Af.c(c.d(newPullParser));
                                    z = true;
                                    continue;
                                } catch (Exception e2) {
                                    e = e2;
                                }
                            } else if (newPullParser.getName().equals("iq")) {
                                this.Af.c(c.a(newPullParser, this.Af));
                                z = true;
                                continue;
                            } else if (newPullParser.getName().equals("presence")) {
                                this.Af.c(c.e(newPullParser));
                                z = true;
                                continue;
                            } else if (newPullParser.getName().equals("challenge")) {
                                this.Af.a(newPullParser.nextText());
                                z = true;
                                continue;
                            } else if (newPullParser.getName().equals(ConfigConstant.LOG_JSON_STR_ERROR)) {
                                throw new v(c.g(newPullParser));
                            } else {
                                if (newPullParser.getName().equals("warning")) {
                                    newPullParser.next();
                                    if (newPullParser.getName().equals("multi-login")) {
                                        this.Af.a(new f(b.unavailable), 6, null);
                                        z = true;
                                        continue;
                                    }
                                } else if (newPullParser.getName().equals("bind")) {
                                    this.Af.c(c.f(newPullParser));
                                } else {
                                    continue;
                                }
                                z = true;
                                continue;
                            }
                        }
                    } while (next != 1);
                } catch (Exception e3) {
                    e = e3;
                    z2 = z;
                }
            } catch (Exception e4) {
                e = e4;
                z2 = false;
                if (!this.Af.j()) {
                    return z2;
                }
                this.Af.a(e);
                return z2;
            }
        }
        return z;
    }
}
