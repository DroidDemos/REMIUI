package com.xiaomi.b.a;

import java.lang.ref.SoftReference;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

final class h implements l {
    private static final Logger nR;
    private static final ThreadLocal nY;

    static {
        nR = Logger.getLogger(h.class.getName());
        nY = new k();
    }

    h() {
    }

    private static XmlPullParser ci() {
        XmlPullParser xmlPullParser = (XmlPullParser) ((SoftReference) nY.get()).get();
        if (xmlPullParser != null) {
            return xmlPullParser;
        }
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            newInstance.setValidating(false);
            xmlPullParser = newInstance.newPullParser();
            nY.set(new SoftReference(xmlPullParser));
            return xmlPullParser;
        } catch (Throwable e) {
            throw new IllegalStateException("Could not create XmlPull parser", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.b.a.i V(java.lang.String r11) {
        /*
        r10 = this;
        r3 = new com.xiaomi.b.a.i;
        r3.<init>();
        r4 = ci();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = new java.io.StringReader;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0.<init>(r11);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r4.setInput(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r4.getEventType();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
    L_0x0015:
        r1 = 1;
        if (r0 == r1) goto L_0x0190;
    L_0x0018:
        r1 = 2;
        if (r0 != r1) goto L_0x0118;
    L_0x001b:
        r0 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r0.isLoggable(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        if (r0 == 0) goto L_0x0041;
    L_0x0025:
        r0 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r2 = "Start tag: ";
        r1 = r1.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r2 = r4.getName();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = r1.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = r1.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0.finest(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
    L_0x0041:
        r0 = r4.getPrefix();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        if (r0 != 0) goto L_0x0049;
    L_0x0047:
        r0 = "";
    L_0x0049:
        r1 = r4.getNamespace();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r2 = r4.getName();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r5 = new com.xiaomi.b.a.an;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r5.<init>(r1, r2, r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = r6.isLoggable(r7);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        if (r6 == 0) goto L_0x00af;
    L_0x0060:
        r6 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7 = "Start element: ";
        r6.finest(r7);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r8 = "    prefix: ";
        r7 = r7.append(r8);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r7.append(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r0.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6.finest(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7 = "    URI: ";
        r6 = r6.append(r7);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = r6.append(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = r6.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0.finest(r6);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7 = "    local: ";
        r6 = r6.append(r7);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = r6.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = r6.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0.finest(r6);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
    L_0x00af:
        r0 = com.xiaomi.b.a.z.cv();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r5 = r0.a(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        if (r5 != 0) goto L_0x018d;
    L_0x00b9:
        r3 = new java.lang.IllegalStateException;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r4 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r4.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r5 = "Root element was not '";
        r4 = r4.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r5 = r0.b();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r4 = r4.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r5 = "' in the '";
        r4 = r4.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r0.a();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r4.append(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r4 = "' namespace.  (Was '";
        r0 = r0.append(r4);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r0.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r2 = "' in '";
        r0 = r0.append(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r0.append(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = "')";
        r0 = r0.append(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r0.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r3.<init>(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        throw r3;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
    L_0x00fe:
        r0 = move-exception;
    L_0x00ff:
        r1 = new com.xiaomi.b.a.aa;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Could not parse body:\n";
        r2 = r2.append(r3);
        r2 = r2.append(r11);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x0118:
        r0 = r4.next();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        goto L_0x0015;
    L_0x011e:
        r0 = r4.getAttributeCount();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        if (r2 >= r0) goto L_0x0190;
    L_0x0124:
        r0 = r4.getAttributeNamespace(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = r0.length();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        if (r1 != 0) goto L_0x018b;
    L_0x012e:
        r0 = 0;
        r0 = r4.getNamespace(r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = r0;
    L_0x0134:
        r0 = r4.getAttributePrefix(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        if (r0 != 0) goto L_0x013c;
    L_0x013a:
        r0 = "";
    L_0x013c:
        r5 = r4.getAttributeName(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r6 = r4.getAttributeValue(r2);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = com.xiaomi.b.a.j.b(r1, r5, r0);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r8 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7 = r7.isLoggable(r8);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        if (r7 == 0) goto L_0x0184;
    L_0x0152:
        r7 = nR;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r8 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r8.<init>();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r9 = "        Attribute: {";
        r8 = r8.append(r9);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = r8.append(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r8 = "}";
        r1 = r1.append(r8);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = r1.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r5 = " = '";
        r1 = r1.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = r1.append(r6);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r5 = "'";
        r1 = r1.append(r5);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r1 = r1.toString();	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r7.finest(r1);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
    L_0x0184:
        r3.a(r0, r6);	 Catch:{ RuntimeException -> 0x00fe, XmlPullParserException -> 0x0191, IOException -> 0x0194 }
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x011e;
    L_0x018b:
        r1 = r0;
        goto L_0x0134;
    L_0x018d:
        r0 = 0;
        r2 = r0;
        goto L_0x011e;
    L_0x0190:
        return r3;
    L_0x0191:
        r0 = move-exception;
        goto L_0x00ff;
    L_0x0194:
        r0 = move-exception;
        goto L_0x00ff;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.b.a.h.V(java.lang.String):com.xiaomi.b.a.i");
    }
}
