package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.push.service.PushConstants;
import com.xiaomi.smack.util.StringUtils;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Packet {
    protected static final String DEFAULT_LANGUAGE;
    private static String DEFAULT_XML_NS = null;
    public static final String ID_NOT_AVAILABLE = "ID_NOT_AVAILABLE";
    public static final DateFormat XEP_0082_UTC_FORMAT;
    private static long id;
    private static String prefix;
    private String chId;
    private XMPPError error;
    private String from;
    private String packageName;
    private List<CommonPacketExtension> packetExtensions;
    private String packetID;
    private final Map<String, Object> properties;
    private String to;
    private String xmlns;

    public abstract String toXML();

    static {
        DEFAULT_LANGUAGE = Locale.getDefault().getLanguage().toLowerCase();
        DEFAULT_XML_NS = null;
        XEP_0082_UTC_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        XEP_0082_UTC_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        prefix = StringUtils.randomString(5) + "-";
        id = 0;
    }

    public static synchronized String nextID() {
        String stringBuilder;
        synchronized (Packet.class) {
            StringBuilder append = new StringBuilder().append(prefix);
            long j = id;
            id = 1 + j;
            stringBuilder = append.append(Long.toString(j)).toString();
        }
        return stringBuilder;
    }

    public static void setDefaultXmlns(String defaultXmlns) {
        DEFAULT_XML_NS = defaultXmlns;
    }

    public Packet() {
        this.xmlns = DEFAULT_XML_NS;
        this.packetID = null;
        this.to = null;
        this.from = null;
        this.chId = null;
        this.packageName = null;
        this.packetExtensions = new CopyOnWriteArrayList();
        this.properties = new HashMap();
        this.error = null;
    }

    public Packet(Bundle b) {
        this.xmlns = DEFAULT_XML_NS;
        this.packetID = null;
        this.to = null;
        this.from = null;
        this.chId = null;
        this.packageName = null;
        this.packetExtensions = new CopyOnWriteArrayList();
        this.properties = new HashMap();
        this.error = null;
        this.to = b.getString(PushConstants.EXTRA_TO);
        this.from = b.getString(PushConstants.EXTRA_FROM);
        this.chId = b.getString(PushConstants.EXTRA_CHID);
        this.packetID = b.getString(PushConstants.EXTRA_PACKET_ID);
        Parcelable[] extBundles = b.getParcelableArray(PushConstants.EXTRA_EXTENSIONS);
        if (extBundles != null) {
            this.packetExtensions = new ArrayList(extBundles.length);
            for (Parcelable bund : extBundles) {
                CommonPacketExtension ext = CommonPacketExtension.parseFromBundle((Bundle) bund);
                if (ext != null) {
                    this.packetExtensions.add(ext);
                }
            }
        }
        Bundle errBundle = b.getBundle(PushConstants.EXTRA_ERROR);
        if (errBundle != null) {
            this.error = new XMPPError(errBundle);
        }
    }

    public String getPacketID() {
        if (ID_NOT_AVAILABLE.equals(this.packetID)) {
            return null;
        }
        if (this.packetID == null) {
            this.packetID = nextID();
        }
        return this.packetID;
    }

    public void setPacketID(String packetID) {
        this.packetID = packetID;
    }

    public String getChannelId() {
        return this.chId;
    }

    public void setChannelId(String appId) {
        this.chId = appId;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public XMPPError getError() {
        return this.error;
    }

    public void setError(XMPPError error) {
        this.error = error;
    }

    public synchronized Collection<CommonPacketExtension> getExtensions() {
        Collection<CommonPacketExtension> emptyList;
        if (this.packetExtensions == null) {
            emptyList = Collections.emptyList();
        } else {
            emptyList = Collections.unmodifiableList(new ArrayList(this.packetExtensions));
        }
        return emptyList;
    }

    public CommonPacketExtension getExtension(String elementName) {
        return getExtension(elementName, null);
    }

    public CommonPacketExtension getExtension(String elementName, String namespace) {
        for (CommonPacketExtension ext : this.packetExtensions) {
            if ((namespace == null || namespace.equals(ext.getNamespace())) && elementName.equals(ext.getElementName())) {
                return ext;
            }
        }
        return null;
    }

    public void addExtension(CommonPacketExtension extension) {
        this.packetExtensions.add(extension);
    }

    public void removeExtension(CommonPacketExtension extension) {
        this.packetExtensions.remove(extension);
    }

    public synchronized Object getProperty(String name) {
        Object obj;
        if (this.properties == null) {
            obj = null;
        } else {
            obj = this.properties.get(name);
        }
        return obj;
    }

    public synchronized void setProperty(String name, Object value) {
        if (value instanceof Serializable) {
            this.properties.put(name, value);
        } else {
            throw new IllegalArgumentException("Value must be serialiazble");
        }
    }

    public synchronized void deleteProperty(String name) {
        if (this.properties != null) {
            this.properties.remove(name);
        }
    }

    public synchronized Collection<String> getPropertyNames() {
        Collection<String> emptySet;
        if (this.properties == null) {
            emptySet = Collections.emptySet();
        } else {
            emptySet = Collections.unmodifiableSet(new HashSet(this.properties.keySet()));
        }
        return emptySet;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.xmlns)) {
            bundle.putString(PushConstants.EXTRA_EXTENSION_NAMESPACE, this.xmlns);
        }
        if (!TextUtils.isEmpty(this.from)) {
            bundle.putString(PushConstants.EXTRA_FROM, this.from);
        }
        if (!TextUtils.isEmpty(this.to)) {
            bundle.putString(PushConstants.EXTRA_TO, this.to);
        }
        if (!TextUtils.isEmpty(this.packetID)) {
            bundle.putString(PushConstants.EXTRA_PACKET_ID, this.packetID);
        }
        if (!TextUtils.isEmpty(this.chId)) {
            bundle.putString(PushConstants.EXTRA_CHID, this.chId);
        }
        if (this.error != null) {
            bundle.putBundle(PushConstants.EXTRA_ERROR, this.error.toBundle());
        }
        if (this.packetExtensions != null) {
            Bundle[] extBundle = new Bundle[this.packetExtensions.size()];
            int i = 0;
            for (CommonPacketExtension ext : this.packetExtensions) {
                Bundle subBundle = ext.toBundle();
                if (subBundle != null) {
                    int i2 = i + 1;
                    extBundle[i] = subBundle;
                    i = i2;
                }
            }
            bundle.putParcelableArray(PushConstants.EXTRA_EXTENSIONS, extBundle);
        }
        return bundle;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected synchronized java.lang.String getExtensionsXML() {
        /*
        r13 = this;
        monitor-enter(r13);
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0022 }
        r0.<init>();	 Catch:{ all -> 0x0022 }
        r11 = r13.getExtensions();	 Catch:{ all -> 0x0022 }
        r6 = r11.iterator();	 Catch:{ all -> 0x0022 }
    L_0x000e:
        r11 = r6.hasNext();	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x0025;
    L_0x0014:
        r5 = r6.next();	 Catch:{ all -> 0x0022 }
        r5 = (com.xiaomi.smack.packet.CommonPacketExtension) r5;	 Catch:{ all -> 0x0022 }
        r11 = r5.toXML();	 Catch:{ all -> 0x0022 }
        r0.append(r11);	 Catch:{ all -> 0x0022 }
        goto L_0x000e;
    L_0x0022:
        r11 = move-exception;
        monitor-exit(r13);
        throw r11;
    L_0x0025:
        r11 = r13.properties;	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x0144;
    L_0x0029:
        r11 = r13.properties;	 Catch:{ all -> 0x0022 }
        r11 = r11.isEmpty();	 Catch:{ all -> 0x0022 }
        if (r11 != 0) goto L_0x0144;
    L_0x0031:
        r11 = "<properties xmlns=\"http://www.jivesoftware.com/xmlns/xmpp/properties\">";
        r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = r13.getPropertyNames();	 Catch:{ all -> 0x0022 }
        r6 = r11.iterator();	 Catch:{ all -> 0x0022 }
    L_0x003e:
        r11 = r6.hasNext();	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x013f;
    L_0x0044:
        r7 = r6.next();	 Catch:{ all -> 0x0022 }
        r7 = (java.lang.String) r7;	 Catch:{ all -> 0x0022 }
        r10 = r13.getProperty(r7);	 Catch:{ all -> 0x0022 }
        r11 = "<property>";
        r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = "<name>";
        r11 = r0.append(r11);	 Catch:{ all -> 0x0022 }
        r12 = com.xiaomi.smack.util.StringUtils.escapeForXML(r7);	 Catch:{ all -> 0x0022 }
        r11 = r11.append(r12);	 Catch:{ all -> 0x0022 }
        r12 = "</name>";
        r11.append(r12);	 Catch:{ all -> 0x0022 }
        r11 = "<value type=\"";
        r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = r10 instanceof java.lang.Integer;	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x0084;
    L_0x006f:
        r11 = "integer\">";
        r11 = r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = r11.append(r10);	 Catch:{ all -> 0x0022 }
        r12 = "</value>";
        r11.append(r12);	 Catch:{ all -> 0x0022 }
    L_0x007e:
        r11 = "</property>";
        r0.append(r11);	 Catch:{ all -> 0x0022 }
        goto L_0x003e;
    L_0x0084:
        r11 = r10 instanceof java.lang.Long;	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x0098;
    L_0x0088:
        r11 = "long\">";
        r11 = r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = r11.append(r10);	 Catch:{ all -> 0x0022 }
        r12 = "</value>";
        r11.append(r12);	 Catch:{ all -> 0x0022 }
        goto L_0x007e;
    L_0x0098:
        r11 = r10 instanceof java.lang.Float;	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x00ac;
    L_0x009c:
        r11 = "float\">";
        r11 = r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = r11.append(r10);	 Catch:{ all -> 0x0022 }
        r12 = "</value>";
        r11.append(r12);	 Catch:{ all -> 0x0022 }
        goto L_0x007e;
    L_0x00ac:
        r11 = r10 instanceof java.lang.Double;	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x00c0;
    L_0x00b0:
        r11 = "double\">";
        r11 = r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = r11.append(r10);	 Catch:{ all -> 0x0022 }
        r12 = "</value>";
        r11.append(r12);	 Catch:{ all -> 0x0022 }
        goto L_0x007e;
    L_0x00c0:
        r11 = r10 instanceof java.lang.Boolean;	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x00d4;
    L_0x00c4:
        r11 = "boolean\">";
        r11 = r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = r11.append(r10);	 Catch:{ all -> 0x0022 }
        r12 = "</value>";
        r11.append(r12);	 Catch:{ all -> 0x0022 }
        goto L_0x007e;
    L_0x00d4:
        r11 = r10 instanceof java.lang.String;	 Catch:{ all -> 0x0022 }
        if (r11 == 0) goto L_0x00ec;
    L_0x00d8:
        r11 = "string\">";
        r0.append(r11);	 Catch:{ all -> 0x0022 }
        r10 = (java.lang.String) r10;	 Catch:{ all -> 0x0022 }
        r11 = com.xiaomi.smack.util.StringUtils.escapeForXML(r10);	 Catch:{ all -> 0x0022 }
        r0.append(r11);	 Catch:{ all -> 0x0022 }
        r11 = "</value>";
        r0.append(r11);	 Catch:{ all -> 0x0022 }
        goto L_0x007e;
    L_0x00ec:
        r1 = 0;
        r8 = 0;
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0120 }
        r2.<init>();	 Catch:{ Exception -> 0x0120 }
        r9 = new java.io.ObjectOutputStream;	 Catch:{ Exception -> 0x0159, all -> 0x0152 }
        r9.<init>(r2);	 Catch:{ Exception -> 0x0159, all -> 0x0152 }
        r9.writeObject(r10);	 Catch:{ Exception -> 0x015c, all -> 0x0155 }
        r11 = "java-object\">";
        r0.append(r11);	 Catch:{ Exception -> 0x015c, all -> 0x0155 }
        r11 = r2.toByteArray();	 Catch:{ Exception -> 0x015c, all -> 0x0155 }
        r4 = com.xiaomi.smack.util.StringUtils.encodeBase64(r11);	 Catch:{ Exception -> 0x015c, all -> 0x0155 }
        r11 = r0.append(r4);	 Catch:{ Exception -> 0x015c, all -> 0x0155 }
        r12 = "</value>";
        r11.append(r12);	 Catch:{ Exception -> 0x015c, all -> 0x0155 }
        if (r9 == 0) goto L_0x0116;
    L_0x0113:
        r9.close();	 Catch:{ Exception -> 0x014a }
    L_0x0116:
        if (r2 == 0) goto L_0x007e;
    L_0x0118:
        r2.close();	 Catch:{ Exception -> 0x011d }
        goto L_0x007e;
    L_0x011d:
        r11 = move-exception;
        goto L_0x007e;
    L_0x0120:
        r3 = move-exception;
    L_0x0121:
        r3.printStackTrace();	 Catch:{ all -> 0x0133 }
        if (r8 == 0) goto L_0x0129;
    L_0x0126:
        r8.close();	 Catch:{ Exception -> 0x014c }
    L_0x0129:
        if (r1 == 0) goto L_0x007e;
    L_0x012b:
        r1.close();	 Catch:{ Exception -> 0x0130 }
        goto L_0x007e;
    L_0x0130:
        r11 = move-exception;
        goto L_0x007e;
    L_0x0133:
        r11 = move-exception;
    L_0x0134:
        if (r8 == 0) goto L_0x0139;
    L_0x0136:
        r8.close();	 Catch:{ Exception -> 0x014e }
    L_0x0139:
        if (r1 == 0) goto L_0x013e;
    L_0x013b:
        r1.close();	 Catch:{ Exception -> 0x0150 }
    L_0x013e:
        throw r11;	 Catch:{ all -> 0x0022 }
    L_0x013f:
        r11 = "</properties>";
        r0.append(r11);	 Catch:{ all -> 0x0022 }
    L_0x0144:
        r11 = r0.toString();	 Catch:{ all -> 0x0022 }
        monitor-exit(r13);
        return r11;
    L_0x014a:
        r11 = move-exception;
        goto L_0x0116;
    L_0x014c:
        r11 = move-exception;
        goto L_0x0129;
    L_0x014e:
        r12 = move-exception;
        goto L_0x0139;
    L_0x0150:
        r12 = move-exception;
        goto L_0x013e;
    L_0x0152:
        r11 = move-exception;
        r1 = r2;
        goto L_0x0134;
    L_0x0155:
        r11 = move-exception;
        r8 = r9;
        r1 = r2;
        goto L_0x0134;
    L_0x0159:
        r3 = move-exception;
        r1 = r2;
        goto L_0x0121;
    L_0x015c:
        r3 = move-exception;
        r8 = r9;
        r1 = r2;
        goto L_0x0121;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.packet.Packet.getExtensionsXML():java.lang.String");
    }

    public String getXmlns() {
        return this.xmlns;
    }

    public static String getDefaultLanguage() {
        return DEFAULT_LANGUAGE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
        r5 = this;
        r1 = 1;
        r2 = 0;
        if (r5 != r6) goto L_0x0006;
    L_0x0004:
        r2 = r1;
    L_0x0005:
        return r2;
    L_0x0006:
        if (r6 == 0) goto L_0x0005;
    L_0x0008:
        r3 = r5.getClass();
        r4 = r6.getClass();
        if (r3 != r4) goto L_0x0005;
    L_0x0012:
        r0 = r6;
        r0 = (com.xiaomi.smack.packet.Packet) r0;
        r3 = r5.error;
        if (r3 == 0) goto L_0x0084;
    L_0x0019:
        r3 = r5.error;
        r4 = r0.error;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0023:
        r3 = r5.from;
        if (r3 == 0) goto L_0x008a;
    L_0x0027:
        r3 = r5.from;
        r4 = r0.from;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0031:
        r3 = r5.packetExtensions;
        r4 = r0.packetExtensions;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x003b:
        r3 = r5.packetID;
        if (r3 == 0) goto L_0x0090;
    L_0x003f:
        r3 = r5.packetID;
        r4 = r0.packetID;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0049:
        r3 = r5.chId;
        if (r3 == 0) goto L_0x0096;
    L_0x004d:
        r3 = r5.chId;
        r4 = r0.chId;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0057:
        r3 = r5.properties;
        if (r3 == 0) goto L_0x009c;
    L_0x005b:
        r3 = r5.properties;
        r4 = r0.properties;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0065:
        r3 = r5.to;
        if (r3 == 0) goto L_0x00a2;
    L_0x0069:
        r3 = r5.to;
        r4 = r0.to;
        r3 = r3.equals(r4);
        if (r3 == 0) goto L_0x0005;
    L_0x0073:
        r3 = r5.xmlns;
        if (r3 == 0) goto L_0x00a8;
    L_0x0077:
        r3 = r5.xmlns;
        r4 = r0.xmlns;
        r3 = r3.equals(r4);
        if (r3 != 0) goto L_0x0082;
    L_0x0081:
        r1 = r2;
    L_0x0082:
        r2 = r1;
        goto L_0x0005;
    L_0x0084:
        r3 = r0.error;
        if (r3 == 0) goto L_0x0023;
    L_0x0088:
        goto L_0x0005;
    L_0x008a:
        r3 = r0.from;
        if (r3 == 0) goto L_0x0031;
    L_0x008e:
        goto L_0x0005;
    L_0x0090:
        r3 = r0.packetID;
        if (r3 == 0) goto L_0x0049;
    L_0x0094:
        goto L_0x0005;
    L_0x0096:
        r3 = r0.chId;
        if (r3 == 0) goto L_0x0057;
    L_0x009a:
        goto L_0x0005;
    L_0x009c:
        r3 = r0.properties;
        if (r3 == 0) goto L_0x0065;
    L_0x00a0:
        goto L_0x0005;
    L_0x00a2:
        r3 = r0.to;
        if (r3 == 0) goto L_0x0073;
    L_0x00a6:
        goto L_0x0005;
    L_0x00a8:
        r3 = r0.xmlns;
        if (r3 != 0) goto L_0x0081;
    L_0x00ac:
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smack.packet.Packet.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int result;
        int hashCode;
        int i = 0;
        if (this.xmlns != null) {
            result = this.xmlns.hashCode();
        } else {
            result = 0;
        }
        int i2 = result * 31;
        if (this.packetID != null) {
            hashCode = this.packetID.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.to != null) {
            hashCode = this.to.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.from != null) {
            hashCode = this.from.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (i2 + hashCode) * 31;
        if (this.chId != null) {
            hashCode = this.chId.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (((((i2 + hashCode) * 31) + this.packetExtensions.hashCode()) * 31) + this.properties.hashCode()) * 31;
        if (this.error != null) {
            i = this.error.hashCode();
        }
        return hashCode + i;
    }
}
