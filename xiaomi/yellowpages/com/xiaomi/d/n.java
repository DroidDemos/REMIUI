package com.xiaomi.d;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.xiaomi.d.c.g;
import com.xiaomi.d.e.c;
import com.xiaomi.f.a.c.b;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

class n {
    private Thread FT;
    private x FU;
    private XmlPullParser FV;
    private boolean d;

    protected n(x xVar) {
        this.FU = xVar;
        a();
    }

    private void a(g gVar) {
        if (gVar != null) {
            for (a a : this.FU.ev.values()) {
                a.a(gVar);
            }
        }
    }

    private void e() {
        this.FV = XmlPullParserFactory.newInstance().newPullParser();
        this.FV.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        this.FV.setInput(this.FU.Gc);
    }

    private void f() {
        e();
        int eventType = this.FV.getEventType();
        String str = ConfigConstant.WIRELESS_FILENAME;
        do {
            this.FU.bw();
            if (eventType == 2) {
                String name = this.FV.getName();
                if (this.FV.getName().equals("message")) {
                    a(c.d(this.FV));
                    str = name;
                } else {
                    try {
                        if (this.FV.getName().equals("iq")) {
                            a(c.a(this.FV, this.FU));
                            str = name;
                        } else if (this.FV.getName().equals("presence")) {
                            a(c.e(this.FV));
                            str = name;
                        } else if (this.FV.getName().equals("stream")) {
                            str = ConfigConstant.WIRELESS_FILENAME;
                            for (eventType = 0; eventType < this.FV.getAttributeCount(); eventType++) {
                                if (this.FV.getAttributeName(eventType).equals("from")) {
                                    this.FU.Ge.a(this.FV.getAttributeValue(eventType));
                                } else if (this.FV.getAttributeName(eventType).equals("challenge")) {
                                    str = this.FV.getAttributeValue(eventType);
                                }
                            }
                            this.FU.a(str);
                            str = name;
                        } else if (this.FV.getName().equals(ConfigConstant.LOG_JSON_STR_ERROR)) {
                            throw new v(c.g(this.FV));
                        } else {
                            if (this.FV.getName().equals("warning")) {
                                this.FV.next();
                                if (this.FV.getName().equals("multi-login")) {
                                    a(6, null);
                                    str = name;
                                }
                            } else if (this.FV.getName().equals("bind")) {
                                a(c.f(this.FV));
                                str = name;
                            }
                            str = name;
                        }
                    } catch (Throwable e) {
                        b.a(e);
                        if (this.d) {
                            b.b("reader is shutdown, ignore the exception.");
                            return;
                        } else {
                            a(9, e);
                            return;
                        }
                    }
                }
            } else if (eventType == 3 && this.FV.getName().equals("stream")) {
                a(13, null);
            }
            eventType = this.FV.next();
            if (this.d) {
                break;
            }
        } while (eventType != 1);
        if (eventType == 1) {
            throw new Exception("SMACK: server close the connection or timeout happened, last element name=" + str + " host=" + this.FU.e());
        }
    }

    protected void a() {
        this.d = false;
        this.FT = new q(this, "Smack Packet Reader (" + this.FU.l + ")");
    }

    void a(int i, Exception exception) {
        this.d = true;
        this.FU.a(i, exception);
    }

    public void b() {
        this.FT.start();
    }

    public void c() {
        this.d = true;
    }

    void d() {
        this.FU.ev.clear();
    }
}
