package com.xiaomi.d.e;

import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import com.xiaomi.d.B;
import com.xiaomi.d.c.a;
import com.xiaomi.d.c.b;
import com.xiaomi.d.c.d;
import com.xiaomi.d.c.f;
import com.xiaomi.d.c.g;
import com.xiaomi.d.c.h;
import com.xiaomi.d.c.i;
import com.xiaomi.d.c.j;
import com.xiaomi.d.s;
import com.xiaomi.push.service.G;
import com.xiaomi.push.service.m;
import com.xiaomi.push.service.q;
import com.xiaomi.push.service.v;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class c {
    private static XmlPullParser Jr;

    static {
        Jr = null;
    }

    public static i a(XmlPullParser xmlPullParser, s sVar) {
        String attributeValue = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "id");
        String attributeValue2 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "to");
        String attributeValue3 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "from");
        String attributeValue4 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "chid");
        a E = a.E(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, MiniDefine.m));
        Object obj = null;
        i iVar = null;
        com.xiaomi.d.c.c cVar = null;
        while (obj == null) {
            i iVar2;
            Object obj2;
            int next = xmlPullParser.next();
            Object obj3;
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals(ConfigConstant.LOG_JSON_STR_ERROR)) {
                    cVar = h(xmlPullParser);
                } else {
                    iVar = new i();
                    iVar.a(a(name, namespace, xmlPullParser));
                }
                obj3 = obj;
                iVar2 = iVar;
                obj2 = obj3;
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                iVar2 = iVar;
                int i = 1;
            } else {
                obj3 = obj;
                iVar2 = iVar;
                obj2 = obj3;
            }
            i iVar3 = iVar2;
            obj = obj2;
            iVar = iVar3;
        }
        if (iVar == null) {
            if (a.gU == E || a.gV == E) {
                g dVar = new d();
                dVar.cw(attributeValue);
                dVar.cy(attributeValue3);
                dVar.cz(attributeValue2);
                dVar.a(a.gX);
                dVar.cx(attributeValue4);
                dVar.a(new com.xiaomi.d.c.c(b.Fm));
                sVar.a(dVar);
                com.xiaomi.f.a.c.b.c("iq usage error. send packet in packet parser.");
                return null;
            }
            iVar = new e();
        }
        iVar.cw(attributeValue);
        iVar.cy(attributeValue2);
        iVar.cx(attributeValue4);
        iVar.cz(attributeValue3);
        iVar.a(E);
        iVar.a(cVar);
        return iVar;
    }

    public static j a(String str, String str2, XmlPullParser xmlPullParser) {
        Object h = com.xiaomi.d.b.c.bT().h("all", "xm:chat");
        return (h == null || !(h instanceof v)) ? null : ((v) h).b(xmlPullParser);
    }

    public static g d(XmlPullParser xmlPullParser) {
        boolean z = false;
        String str = null;
        String attributeValue;
        if (GlobalConstants.d.equals(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "s"))) {
            attributeValue = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "chid");
            String attributeValue2 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "id");
            String attributeValue3 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "from");
            String attributeValue4 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "to");
            String attributeValue5 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, MiniDefine.m);
            m r = q.fp().r(attributeValue, attributeValue4);
            m r2 = r == null ? q.fp().r(attributeValue, attributeValue3) : r;
            if (r2 == null) {
                throw new com.xiaomi.d.v("the channel id is wrong while receiving a encrypted message");
            }
            g gVar = null;
            while (!z) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (!"s".equals(xmlPullParser.getName())) {
                        throw new com.xiaomi.d.v("error while receiving a encrypted message with wrong format");
                    } else if (xmlPullParser.next() != 4) {
                        throw new com.xiaomi.d.v("error while receiving a encrypted message with wrong format");
                    } else {
                        String text = xmlPullParser.getText();
                        if ("5".equals(attributeValue)) {
                            g hVar = new h();
                            hVar.cx(attributeValue);
                            hVar.b(true);
                            hVar.cz(attributeValue3);
                            hVar.cy(attributeValue4);
                            hVar.cw(attributeValue2);
                            hVar.f(attributeValue5);
                            j jVar = new j("s", null, (String[]) null, (String[]) null);
                            jVar.b(text);
                            hVar.a(jVar);
                            return hVar;
                        }
                        f(G.b(G.s(r2.i, attributeValue2), text));
                        Jr.next();
                        gVar = d(Jr);
                    }
                } else if (next == 3 && xmlPullParser.getName().equals("message")) {
                    z = true;
                }
            }
            if (gVar != null) {
                return gVar;
            }
            throw new com.xiaomi.d.v("error while receiving a encrypted message with wrong format");
        }
        Object attributeValue6;
        Object attributeValue7;
        g hVar2 = new h();
        String attributeValue8 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "id");
        if (attributeValue8 == null) {
            attributeValue8 = "ID_NOT_AVAILABLE";
        }
        hVar2.cw(attributeValue8);
        hVar2.cy(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "to"));
        hVar2.cz(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "from"));
        hVar2.cx(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "chid"));
        hVar2.a(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "appid"));
        try {
            attributeValue6 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "transient");
        } catch (Exception e) {
            attributeValue6 = null;
        }
        try {
            attributeValue = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "seq");
            if (!TextUtils.isEmpty(attributeValue)) {
                hVar2.b(attributeValue);
            }
        } catch (Exception e2) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "mseq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                hVar2.c(attributeValue7);
            }
        } catch (Exception e3) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "fseq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                hVar2.d(attributeValue7);
            }
        } catch (Exception e4) {
        }
        try {
            attributeValue7 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, MiniDefine.b);
            if (!TextUtils.isEmpty(attributeValue7)) {
                hVar2.e(attributeValue7);
            }
        } catch (Exception e5) {
        }
        boolean z2 = !TextUtils.isEmpty(attributeValue6) && attributeValue6.equalsIgnoreCase(MiniDefine.F);
        hVar2.a(z2);
        hVar2.f(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, MiniDefine.m));
        attributeValue8 = j(xmlPullParser);
        if (attributeValue8 == null || ConfigConstant.WIRELESS_FILENAME.equals(attributeValue8.trim())) {
            g.iV();
        } else {
            hVar2.cE(attributeValue8);
        }
        while (!z) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                attributeValue = xmlPullParser.getName();
                attributeValue8 = xmlPullParser.getNamespace();
                if (TextUtils.isEmpty(attributeValue8)) {
                    attributeValue8 = "xm";
                }
                if (attributeValue.equals("subject")) {
                    if (j(xmlPullParser) == null) {
                        hVar2.g(i(xmlPullParser));
                    } else {
                        hVar2.g(i(xmlPullParser));
                    }
                } else if (attributeValue.equals("body")) {
                    attributeValue6 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "encode");
                    attributeValue = i(xmlPullParser);
                    if (TextUtils.isEmpty(attributeValue6)) {
                        hVar2.cC(attributeValue);
                    } else {
                        hVar2.a(attributeValue, attributeValue6);
                    }
                } else if (attributeValue.equals("thread")) {
                    if (str == null) {
                        str = xmlPullParser.nextText();
                    }
                } else if (attributeValue.equals(ConfigConstant.LOG_JSON_STR_ERROR)) {
                    hVar2.a(h(xmlPullParser));
                } else {
                    hVar2.a(a(attributeValue, attributeValue8, xmlPullParser));
                }
            } else if (next2 == 3 && xmlPullParser.getName().equals("message")) {
                z = true;
            }
        }
        hVar2.cD(str);
        return hVar2;
    }

    public static f e(XmlPullParser xmlPullParser) {
        f.b bVar = f.b.available;
        String attributeValue = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, MiniDefine.m);
        if (!(attributeValue == null || attributeValue.equals(ConfigConstant.WIRELESS_FILENAME))) {
            try {
                bVar = f.b.valueOf(attributeValue);
            } catch (IllegalArgumentException e) {
                System.err.println("Found invalid presence type " + attributeValue);
            }
        }
        f fVar = new f(bVar);
        fVar.cy(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "to"));
        fVar.cz(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "from"));
        fVar.cx(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "id");
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        fVar.cw(attributeValue2);
        int i = 0;
        while (i == 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals(MiniDefine.b)) {
                    fVar.a(xmlPullParser.nextText());
                } else if (name.equals("priority")) {
                    try {
                        fVar.a(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException e2) {
                    } catch (IllegalArgumentException e3) {
                        fVar.a(0);
                    }
                } else if (name.equals("show")) {
                    name = xmlPullParser.nextText();
                    try {
                        fVar.a(f.a.valueOf(name));
                    } catch (IllegalArgumentException e4) {
                        System.err.println("Found invalid presence mode " + name);
                    }
                } else if (name.equals(ConfigConstant.LOG_JSON_STR_ERROR)) {
                    fVar.a(h(xmlPullParser));
                } else {
                    fVar.a(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                i = 1;
            }
        }
        return fVar;
    }

    public static com.xiaomi.d.b f(XmlPullParser xmlPullParser) {
        com.xiaomi.d.b bVar = new com.xiaomi.d.b();
        String attributeValue = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "id");
        String attributeValue2 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "to");
        String attributeValue3 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "from");
        String attributeValue4 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "chid");
        B co = B.co(xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, MiniDefine.m));
        bVar.cw(attributeValue);
        bVar.cy(attributeValue2);
        bVar.cz(attributeValue3);
        bVar.cx(attributeValue4);
        bVar.a(co);
        Object obj = null;
        com.xiaomi.d.c.c cVar = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(ConfigConstant.LOG_JSON_STR_ERROR)) {
                    cVar = h(xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getName().equals("bind")) {
                obj = 1;
            }
        }
        bVar.a(cVar);
        return bVar;
    }

    private static void f(byte[] bArr) {
        if (Jr == null) {
            try {
                Jr = XmlPullParserFactory.newInstance().newPullParser();
                Jr.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        Jr.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }

    public static d g(XmlPullParser xmlPullParser) {
        d dVar = null;
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                dVar = new d(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals(ConfigConstant.LOG_JSON_STR_ERROR)) {
                obj = 1;
            }
        }
        return dVar;
    }

    public static com.xiaomi.d.c.c h(XmlPullParser xmlPullParser) {
        String attributeValue;
        Object obj = null;
        String str = null;
        List arrayList = new ArrayList();
        String str2 = "-1";
        String str3 = null;
        String str4 = null;
        int i = 0;
        while (i < xmlPullParser.getAttributeCount()) {
            String attributeValue2 = xmlPullParser.getAttributeName(i).equals("code") ? xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "code") : str2;
            attributeValue = xmlPullParser.getAttributeName(i).equals(MiniDefine.m) ? xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, MiniDefine.m) : str3;
            if (xmlPullParser.getAttributeName(i).equals("reason")) {
                str4 = xmlPullParser.getAttributeValue(ConfigConstant.WIRELESS_FILENAME, "reason");
            }
            i++;
            str2 = attributeValue2;
            str3 = attributeValue;
        }
        attributeValue = null;
        while (obj == null) {
            i = xmlPullParser.next();
            if (i == 2) {
                if (xmlPullParser.getName().equals(MiniDefine.ax)) {
                    attributeValue = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    attributeValue2 = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(attributeValue2)) {
                        str = name;
                    } else {
                        arrayList.add(a(name, attributeValue2, xmlPullParser));
                    }
                }
            } else if (i == 3) {
                if (xmlPullParser.getName().equals(ConfigConstant.LOG_JSON_STR_ERROR)) {
                    obj = 1;
                }
            } else if (i == 4) {
                attributeValue = xmlPullParser.getText();
            }
        }
        return new com.xiaomi.d.c.c(Integer.parseInt(str2), str3 == null ? "cancel" : str3, str4, str, attributeValue, arrayList);
    }

    private static String i(XmlPullParser xmlPullParser) {
        String str = ConfigConstant.WIRELESS_FILENAME;
        int depth = xmlPullParser.getDepth();
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    private static String j(XmlPullParser xmlPullParser) {
        int i = 0;
        while (i < xmlPullParser.getAttributeCount()) {
            String attributeName = xmlPullParser.getAttributeName(i);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i)))) {
                return xmlPullParser.getAttributeValue(i);
            }
            i++;
        }
        return null;
    }
}
