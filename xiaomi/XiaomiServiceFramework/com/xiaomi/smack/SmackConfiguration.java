package com.xiaomi.smack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;

public final class SmackConfiguration {
    private static final String SMACK_VERSION = "3.1.0";
    private static Vector<String> defaultMechs;
    private static int keepAliveInterval;
    private static int packetReplyTimeout;
    private static int pingInterval;
    private static int serverShutdownTimeout;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r12 = 330000; // 0x50910 float:4.62428E-40 double:1.630417E-318;
        r13 = 1;
        r11 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
        packetReplyTimeout = r11;
        keepAliveInterval = r12;
        r11 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        pingInterval = r11;
        serverShutdownTimeout = r12;
        r11 = new java.util.Vector;
        r11.<init>();
        defaultMechs = r11;
        r2 = getClassLoaders();	 Catch:{ Exception -> 0x00a6 }
        r0 = r2;
        r7 = r0.length;	 Catch:{ Exception -> 0x00a6 }
        r6 = 0;
    L_0x001f:
        if (r6 >= r7) goto L_0x00aa;
    L_0x0021:
        r1 = r0[r6];	 Catch:{ Exception -> 0x00a6 }
        r11 = "META-INF/smack-config.xml";
        r3 = r1.getResources(r11);	 Catch:{ Exception -> 0x00a6 }
    L_0x0029:
        r11 = r3.hasMoreElements();	 Catch:{ Exception -> 0x00a6 }
        if (r11 == 0) goto L_0x00c1;
    L_0x002f:
        r10 = r3.nextElement();	 Catch:{ Exception -> 0x00a6 }
        r10 = (java.net.URL) r10;	 Catch:{ Exception -> 0x00a6 }
        r9 = 0;
        r9 = r10.openStream();	 Catch:{ Exception -> 0x0084 }
        r11 = org.xmlpull.v1.XmlPullParserFactory.newInstance();	 Catch:{ Exception -> 0x0084 }
        r8 = r11.newPullParser();	 Catch:{ Exception -> 0x0084 }
        r11 = "http://xmlpull.org/v1/doc/features.html#process-namespaces";
        r12 = 1;
        r8.setFeature(r11, r12);	 Catch:{ Exception -> 0x0084 }
        r11 = "UTF-8";
        r8.setInput(r9, r11);	 Catch:{ Exception -> 0x0084 }
        r5 = r8.getEventType();	 Catch:{ Exception -> 0x0084 }
    L_0x0051:
        r11 = 2;
        if (r5 != r11) goto L_0x0063;
    L_0x0054:
        r11 = r8.getName();	 Catch:{ Exception -> 0x0084 }
        r12 = "className";
        r11 = r11.equals(r12);	 Catch:{ Exception -> 0x0084 }
        if (r11 == 0) goto L_0x006f;
    L_0x0060:
        parseClassToLoad(r8);	 Catch:{ Exception -> 0x0084 }
    L_0x0063:
        r5 = r8.next();	 Catch:{ Exception -> 0x0084 }
        if (r5 != r13) goto L_0x0051;
    L_0x0069:
        r9.close();	 Catch:{ Exception -> 0x006d }
        goto L_0x0029;
    L_0x006d:
        r11 = move-exception;
        goto L_0x0029;
    L_0x006f:
        r11 = r8.getName();	 Catch:{ Exception -> 0x0084 }
        r12 = "packetReplyTimeout";
        r11 = r11.equals(r12);	 Catch:{ Exception -> 0x0084 }
        if (r11 == 0) goto L_0x008c;
    L_0x007b:
        r11 = packetReplyTimeout;	 Catch:{ Exception -> 0x0084 }
        r11 = parseIntProperty(r8, r11);	 Catch:{ Exception -> 0x0084 }
        packetReplyTimeout = r11;	 Catch:{ Exception -> 0x0084 }
        goto L_0x0063;
    L_0x0084:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ all -> 0x00a1 }
        r9.close();	 Catch:{ Exception -> 0x006d }
        goto L_0x0029;
    L_0x008c:
        r11 = r8.getName();	 Catch:{ Exception -> 0x0084 }
        r12 = "keepAliveInterval";
        r11 = r11.equals(r12);	 Catch:{ Exception -> 0x0084 }
        if (r11 == 0) goto L_0x00ab;
    L_0x0098:
        r11 = keepAliveInterval;	 Catch:{ Exception -> 0x0084 }
        r11 = parseIntProperty(r8, r11);	 Catch:{ Exception -> 0x0084 }
        keepAliveInterval = r11;	 Catch:{ Exception -> 0x0084 }
        goto L_0x0063;
    L_0x00a1:
        r11 = move-exception;
        r9.close();	 Catch:{ Exception -> 0x00c5 }
    L_0x00a5:
        throw r11;	 Catch:{ Exception -> 0x00a6 }
    L_0x00a6:
        r4 = move-exception;
        r4.printStackTrace();
    L_0x00aa:
        return;
    L_0x00ab:
        r11 = r8.getName();	 Catch:{ Exception -> 0x0084 }
        r12 = "mechName";
        r11 = r11.equals(r12);	 Catch:{ Exception -> 0x0084 }
        if (r11 == 0) goto L_0x0063;
    L_0x00b7:
        r11 = defaultMechs;	 Catch:{ Exception -> 0x0084 }
        r12 = r8.nextText();	 Catch:{ Exception -> 0x0084 }
        r11.add(r12);	 Catch:{ Exception -> 0x0084 }
        goto L_0x0063;
    L_0x00c1:
        r6 = r6 + 1;
        goto L_0x001f;
    L_0x00c5:
        r12 = move-exception;
        goto L_0x00a5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.SmackConfiguration.<clinit>():void");
    }

    private SmackConfiguration() {
    }

    public static String getVersion() {
        return SMACK_VERSION;
    }

    public static int getPacketReplyTimeout() {
        if (packetReplyTimeout <= 0) {
            packetReplyTimeout = 5000;
        }
        return packetReplyTimeout;
    }

    public static void setPacketReplyTimeout(int timeout) {
        if (timeout <= 0) {
            throw new IllegalArgumentException();
        }
        packetReplyTimeout = timeout;
    }

    public static int getCheckAliveInterval() {
        return keepAliveInterval;
    }

    public static int getPingInteval() {
        return pingInterval;
    }

    public static int getServerShutdownTimeOut() {
        return serverShutdownTimeout;
    }

    public static void setKeepAliveInterval(int interval) {
        keepAliveInterval = interval;
    }

    public static void addSaslMech(String mech) {
        if (!defaultMechs.contains(mech)) {
            defaultMechs.add(mech);
        }
    }

    public static void addSaslMechs(Collection<String> mechs) {
        for (String mech : mechs) {
            addSaslMech(mech);
        }
    }

    public static void removeSaslMech(String mech) {
        if (defaultMechs.contains(mech)) {
            defaultMechs.remove(mech);
        }
    }

    public static void removeSaslMechs(Collection<String> mechs) {
        for (String mech : mechs) {
            removeSaslMech(mech);
        }
    }

    public static List<String> getSaslMechs() {
        return defaultMechs;
    }

    private static void parseClassToLoad(XmlPullParser parser) throws Exception {
        String className = parser.nextText();
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.err.println("Error! A startup class specified in smack-config.xml could not be loaded: " + className);
        }
    }

    private static int parseIntProperty(XmlPullParser parser, int defaultValue) throws Exception {
        try {
            defaultValue = Integer.parseInt(parser.nextText());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return defaultValue;
    }

    private static ClassLoader[] getClassLoaders() {
        ClassLoader[] classLoaders = new ClassLoader[]{SmackConfiguration.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        List<ClassLoader> loaders = new ArrayList();
        for (ClassLoader classLoader : classLoaders) {
            if (classLoader != null) {
                loaders.add(classLoader);
            }
        }
        return (ClassLoader[]) loaders.toArray(new ClassLoader[loaders.size()]);
    }
}
