package com.xiaomi.kenai.jbosh;

import java.lang.ref.SoftReference;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

final class BodyParserXmlPull implements BodyParser {
    private static final Logger LOG;
    private static final ThreadLocal<SoftReference<XmlPullParser>> XPP_PARSER;

    BodyParserXmlPull() {
    }

    static {
        LOG = Logger.getLogger(BodyParserXmlPull.class.getName());
        XPP_PARSER = new ThreadLocal<SoftReference<XmlPullParser>>() {
            protected SoftReference<XmlPullParser> initialValue() {
                return new SoftReference(null);
            }
        };
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.kenai.jbosh.BodyParserResults parse(java.lang.String r25) throws com.xiaomi.kenai.jbosh.BOSHException {
        /*
        r24 = this;
        r15 = new com.xiaomi.kenai.jbosh.BodyParserResults;
        r15.<init>();
        r20 = getXmlPullParser();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = new java.io.StringReader;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r21;
        r1 = r25;
        r0.<init>(r1);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r20.setInput(r21);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r9 = r20.getEventType();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
    L_0x0019:
        r21 = 1;
        r0 = r21;
        if (r9 == r0) goto L_0x01cb;
    L_0x001f:
        r21 = 2;
        r0 = r21;
        if (r9 != r0) goto L_0x013e;
    L_0x0025:
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = r21.isLoggable(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        if (r21 == 0) goto L_0x004b;
    L_0x002f:
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "Start tag: ";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = r20.getName();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21.finest(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
    L_0x004b:
        r14 = r20.getPrefix();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        if (r14 != 0) goto L_0x0053;
    L_0x0051:
        r14 = "";
    L_0x0053:
        r18 = r20.getNamespace();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r12 = r20.getName();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r13 = new com.xiaomi.kenai.jbosh.QName;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r18;
        r13.<init>(r0, r12, r14);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = r21.isLoggable(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        if (r21 == 0) goto L_0x00c3;
    L_0x006c:
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = "Start element: ";
        r21.finest(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "    prefix: ";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r22;
        r22 = r0.append(r14);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21.finest(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "    URI: ";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r22;
        r1 = r18;
        r22 = r0.append(r1);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21.finest(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "    local: ";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r22;
        r22 = r0.append(r12);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21.finest(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
    L_0x00c3:
        r8 = com.xiaomi.kenai.jbosh.AbstractBody.getBodyQName();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = r8.equalsQName(r13);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        if (r21 != 0) goto L_0x0144;
    L_0x00cd:
        r21 = new java.lang.IllegalStateException;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "Root element was not '";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = r8.getLocalPart();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "' in the '";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = r8.getNamespaceURI();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "' namespace.  (Was '";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r22;
        r22 = r0.append(r12);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "' in '";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r22;
        r1 = r18;
        r22 = r0.append(r1);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "')";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21.<init>(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        throw r21;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
    L_0x0118:
        r16 = move-exception;
        r17 = r16;
    L_0x011b:
        r21 = new com.xiaomi.kenai.jbosh.BOSHException;
        r22 = new java.lang.StringBuilder;
        r22.<init>();
        r23 = "Could not parse body:\n";
        r22 = r22.append(r23);
        r0 = r22;
        r1 = r25;
        r22 = r0.append(r1);
        r22 = r22.toString();
        r0 = r21;
        r1 = r22;
        r2 = r17;
        r0.<init>(r1, r2);
        throw r21;
    L_0x013e:
        r9 = r20.next();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        goto L_0x0019;
    L_0x0144:
        r10 = 0;
    L_0x0145:
        r21 = r20.getAttributeCount();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r21;
        if (r10 >= r0) goto L_0x01cb;
    L_0x014d:
        r0 = r20;
        r6 = r0.getAttributeNamespace(r10);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = r6.length();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        if (r21 != 0) goto L_0x015f;
    L_0x0159:
        r21 = 0;
        r6 = r20.getNamespace(r21);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
    L_0x015f:
        r0 = r20;
        r5 = r0.getAttributePrefix(r10);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        if (r5 != 0) goto L_0x0169;
    L_0x0167:
        r5 = "";
    L_0x0169:
        r0 = r20;
        r4 = r0.getAttributeName(r10);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r20;
        r7 = r0.getAttributeValue(r10);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r3 = com.xiaomi.kenai.jbosh.BodyQName.createWithPrefix(r6, r4, r5);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = java.util.logging.Level.FINEST;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21 = r21.isLoggable(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        if (r21 == 0) goto L_0x01bb;
    L_0x0183:
        r21 = LOG;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = new java.lang.StringBuilder;	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22.<init>();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "        Attribute: {";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r22;
        r22 = r0.append(r6);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "}";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r22;
        r22 = r0.append(r4);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = " = '";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r0 = r22;
        r22 = r0.append(r7);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r23 = "'";
        r22 = r22.append(r23);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r22 = r22.toString();	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r21.finest(r22);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
    L_0x01bb:
        r15.addBodyAttributeValue(r3, r7);	 Catch:{ RuntimeException -> 0x0118, XmlPullParserException -> 0x01c1, IOException -> 0x01c6 }
        r10 = r10 + 1;
        goto L_0x0145;
    L_0x01c1:
        r19 = move-exception;
        r17 = r19;
        goto L_0x011b;
    L_0x01c6:
        r11 = move-exception;
        r17 = r11;
        goto L_0x011b;
    L_0x01cb:
        return r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.kenai.jbosh.BodyParserXmlPull.parse(java.lang.String):com.xiaomi.kenai.jbosh.BodyParserResults");
    }

    private static XmlPullParser getXmlPullParser() {
        Exception ex;
        XmlPullParser result = (XmlPullParser) ((SoftReference) XPP_PARSER.get()).get();
        if (result != null) {
            return result;
        }
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            result = factory.newPullParser();
            SoftReference<XmlPullParser> ref = new SoftReference(result);
            try {
                XPP_PARSER.set(ref);
                return result;
            } catch (Exception e) {
                ex = e;
                SoftReference<XmlPullParser> softReference = ref;
                throw new IllegalStateException("Could not create XmlPull parser", ex);
            }
        } catch (Exception e2) {
            ex = e2;
            throw new IllegalStateException("Could not create XmlPull parser", ex);
        }
    }
}
