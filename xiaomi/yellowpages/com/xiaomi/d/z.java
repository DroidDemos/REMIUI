package com.xiaomi.d;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;

public final class z {
    private static Vector IX;
    private static int a;
    private static int b;
    private static int c;
    private static int d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r1 = 330000; // 0x50910 float:4.62428E-40 double:1.630417E-318;
        r8 = 1;
        r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        a = r0;
        b = r1;
        r0 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        c = r0;
        d = r1;
        r0 = new java.util.Vector;
        r0.<init>();
        IX = r0;
        r3 = ih();	 Catch:{ Exception -> 0x00a6 }
        r4 = r3.length;	 Catch:{ Exception -> 0x00a6 }
        r0 = 0;
        r2 = r0;
    L_0x001f:
        if (r2 >= r4) goto L_0x00aa;
    L_0x0021:
        r0 = r3[r2];	 Catch:{ Exception -> 0x00a6 }
        r1 = "META-INF/smack-config.xml";
        r5 = r0.getResources(r1);	 Catch:{ Exception -> 0x00a6 }
    L_0x0029:
        r0 = r5.hasMoreElements();	 Catch:{ Exception -> 0x00a6 }
        if (r0 == 0) goto L_0x00c1;
    L_0x002f:
        r0 = r5.nextElement();	 Catch:{ Exception -> 0x00a6 }
        r0 = (java.net.URL) r0;	 Catch:{ Exception -> 0x00a6 }
        r1 = 0;
        r1 = r0.openStream();	 Catch:{ Exception -> 0x0084 }
        r0 = org.xmlpull.v1.XmlPullParserFactory.newInstance();	 Catch:{ Exception -> 0x0084 }
        r6 = r0.newPullParser();	 Catch:{ Exception -> 0x0084 }
        r0 = "http://xmlpull.org/v1/doc/features.html#process-namespaces";
        r7 = 1;
        r6.setFeature(r0, r7);	 Catch:{ Exception -> 0x0084 }
        r0 = "UTF-8";
        r6.setInput(r1, r0);	 Catch:{ Exception -> 0x0084 }
        r0 = r6.getEventType();	 Catch:{ Exception -> 0x0084 }
    L_0x0051:
        r7 = 2;
        if (r0 != r7) goto L_0x0063;
    L_0x0054:
        r0 = r6.getName();	 Catch:{ Exception -> 0x0084 }
        r7 = "className";
        r0 = r0.equals(r7);	 Catch:{ Exception -> 0x0084 }
        if (r0 == 0) goto L_0x006f;
    L_0x0060:
        c(r6);	 Catch:{ Exception -> 0x0084 }
    L_0x0063:
        r0 = r6.next();	 Catch:{ Exception -> 0x0084 }
        if (r0 != r8) goto L_0x0051;
    L_0x0069:
        r1.close();	 Catch:{ Exception -> 0x006d }
        goto L_0x0029;
    L_0x006d:
        r0 = move-exception;
        goto L_0x0029;
    L_0x006f:
        r0 = r6.getName();	 Catch:{ Exception -> 0x0084 }
        r7 = "packetReplyTimeout";
        r0 = r0.equals(r7);	 Catch:{ Exception -> 0x0084 }
        if (r0 == 0) goto L_0x008c;
    L_0x007b:
        r0 = a;	 Catch:{ Exception -> 0x0084 }
        r0 = a(r6, r0);	 Catch:{ Exception -> 0x0084 }
        a = r0;	 Catch:{ Exception -> 0x0084 }
        goto L_0x0063;
    L_0x0084:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00a1 }
        r1.close();	 Catch:{ Exception -> 0x006d }
        goto L_0x0029;
    L_0x008c:
        r0 = r6.getName();	 Catch:{ Exception -> 0x0084 }
        r7 = "keepAliveInterval";
        r0 = r0.equals(r7);	 Catch:{ Exception -> 0x0084 }
        if (r0 == 0) goto L_0x00ab;
    L_0x0098:
        r0 = b;	 Catch:{ Exception -> 0x0084 }
        r0 = a(r6, r0);	 Catch:{ Exception -> 0x0084 }
        b = r0;	 Catch:{ Exception -> 0x0084 }
        goto L_0x0063;
    L_0x00a1:
        r0 = move-exception;
        r1.close();	 Catch:{ Exception -> 0x00c6 }
    L_0x00a5:
        throw r0;	 Catch:{ Exception -> 0x00a6 }
    L_0x00a6:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x00aa:
        return;
    L_0x00ab:
        r0 = r6.getName();	 Catch:{ Exception -> 0x0084 }
        r7 = "mechName";
        r0 = r0.equals(r7);	 Catch:{ Exception -> 0x0084 }
        if (r0 == 0) goto L_0x0063;
    L_0x00b7:
        r0 = IX;	 Catch:{ Exception -> 0x0084 }
        r7 = r6.nextText();	 Catch:{ Exception -> 0x0084 }
        r0.add(r7);	 Catch:{ Exception -> 0x0084 }
        goto L_0x0063;
    L_0x00c1:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x001f;
    L_0x00c6:
        r1 = move-exception;
        goto L_0x00a5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.d.z.<clinit>():void");
    }

    private z() {
    }

    private static int a(XmlPullParser xmlPullParser, int i) {
        try {
            i = Integer.parseInt(xmlPullParser.nextText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static String a() {
        return "3.1.0";
    }

    public static int ah() {
        if (a <= 0) {
            a = ConfigConstant.SO_TIMEOUT;
        }
        return a;
    }

    public static int c() {
        return b;
    }

    private static void c(XmlPullParser xmlPullParser) {
        String nextText = xmlPullParser.nextText();
        try {
            Class.forName(nextText);
        } catch (ClassNotFoundException e) {
            System.err.println("Error! A startup class specified in smack-config.xml could not be loaded: " + nextText);
        }
    }

    public static int ig() {
        return c;
    }

    private static ClassLoader[] ih() {
        int i = 0;
        ClassLoader[] classLoaderArr = new ClassLoader[]{z.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        List arrayList = new ArrayList();
        int length = classLoaderArr.length;
        while (i < length) {
            Object obj = classLoaderArr[i];
            if (obj != null) {
                arrayList.add(obj);
            }
            i++;
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }
}
