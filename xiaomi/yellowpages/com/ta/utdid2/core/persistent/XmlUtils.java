package com.ta.utdid2.core.persistent;

import android.util.Xml;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class XmlUtils {
    XmlUtils() {
    }

    public static void skipCurrentTag(XmlPullParser xmlPullParser) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            if (next == 3 && xmlPullParser.getDepth() <= depth) {
                return;
            }
        }
    }

    public static final int convertValueToList(CharSequence charSequence, String[] strArr, int i) {
        if (charSequence != null) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (charSequence.equals(strArr[i2])) {
                    return i2;
                }
            }
        }
        return i;
    }

    public static final boolean convertValueToBoolean(CharSequence charSequence, boolean z) {
        boolean z2 = false;
        if (charSequence == null) {
            return z;
        }
        if (charSequence.equals(GlobalConstants.d) || charSequence.equals(MiniDefine.F) || charSequence.equals("TRUE")) {
            z2 = true;
        }
        return z2;
    }

    public static final int convertValueToInt(CharSequence charSequence, int i) {
        int i2 = 1;
        if (charSequence == null) {
            return i;
        }
        int i3;
        int i4;
        String obj = charSequence.toString();
        int length = obj.length();
        if ('-' == obj.charAt(0)) {
            i3 = -1;
        } else {
            i3 = 1;
            i2 = 0;
        }
        if ('0' == obj.charAt(i2)) {
            if (i2 == length - 1) {
                return 0;
            }
            char charAt = obj.charAt(i2 + 1);
            if ('x' == charAt || 'X' == charAt) {
                i4 = i2 + 2;
                i2 = 16;
            } else {
                i4 = i2 + 1;
                i2 = 8;
            }
        } else if ('#' == obj.charAt(i2)) {
            i4 = i2 + 1;
            i2 = 16;
        } else {
            i4 = i2;
            i2 = 10;
        }
        return Integer.parseInt(obj.substring(i4), i2) * i3;
    }

    public static final int convertValueToUnsignedInt(String str, int i) {
        return str == null ? i : parseUnsignedIntAttribute(str);
    }

    public static final int parseUnsignedIntAttribute(CharSequence charSequence) {
        int i = 16;
        int i2 = 1;
        String obj = charSequence.toString();
        int length = obj.length();
        if ('0' == obj.charAt(0)) {
            if (0 == length - 1) {
                return 0;
            }
            char charAt = obj.charAt(1);
            if ('x' == charAt || 'X' == charAt) {
                i2 = 2;
            } else {
                i = 8;
            }
        } else if ('#' != obj.charAt(0)) {
            i = 10;
            i2 = 0;
        }
        return (int) Long.parseLong(obj.substring(i2), i);
    }

    public static final void writeMapXml(Map map, OutputStream outputStream) {
        XmlSerializer fastXmlSerializer = new FastXmlSerializer();
        fastXmlSerializer.setOutput(outputStream, "utf-8");
        fastXmlSerializer.startDocument(null, Boolean.valueOf(true));
        fastXmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        writeMapXml(map, null, fastXmlSerializer);
        fastXmlSerializer.endDocument();
    }

    public static final void writeListXml(List list, OutputStream outputStream) {
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(outputStream, "utf-8");
        newSerializer.startDocument(null, Boolean.valueOf(true));
        newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        writeListXml(list, null, newSerializer);
        newSerializer.endDocument();
    }

    public static final void writeMapXml(Map map, String str, XmlSerializer xmlSerializer) {
        if (map == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "map");
        if (str != null) {
            xmlSerializer.attribute(null, MiniDefine.l, str);
        }
        for (Entry entry : map.entrySet()) {
            writeValueXml(entry.getValue(), (String) entry.getKey(), xmlSerializer);
        }
        xmlSerializer.endTag(null, "map");
    }

    public static final void writeListXml(List list, String str, XmlSerializer xmlSerializer) {
        if (list == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "list");
        if (str != null) {
            xmlSerializer.attribute(null, MiniDefine.l, str);
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            writeValueXml(list.get(i), null, xmlSerializer);
        }
        xmlSerializer.endTag(null, "list");
    }

    public static final void writeByteArrayXml(byte[] bArr, String str, XmlSerializer xmlSerializer) {
        if (bArr == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "byte-array");
        if (str != null) {
            xmlSerializer.attribute(null, MiniDefine.l, str);
        }
        xmlSerializer.attribute(null, MiniDefine.an, Integer.toString(r2));
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b >> 4;
            stringBuilder.append(i >= 10 ? (i + 97) - 10 : i + 48);
            i = b & 255;
            if (i >= 10) {
                i = (i + 97) - 10;
            } else {
                i += 48;
            }
            stringBuilder.append(i);
        }
        xmlSerializer.text(stringBuilder.toString());
        xmlSerializer.endTag(null, "byte-array");
    }

    public static final void writeIntArrayXml(int[] iArr, String str, XmlSerializer xmlSerializer) {
        if (iArr == null) {
            xmlSerializer.startTag(null, "null");
            xmlSerializer.endTag(null, "null");
            return;
        }
        xmlSerializer.startTag(null, "int-array");
        if (str != null) {
            xmlSerializer.attribute(null, MiniDefine.l, str);
        }
        xmlSerializer.attribute(null, MiniDefine.an, Integer.toString(r1));
        for (int num : iArr) {
            xmlSerializer.startTag(null, "item");
            xmlSerializer.attribute(null, MiniDefine.a, Integer.toString(num));
            xmlSerializer.endTag(null, "item");
        }
        xmlSerializer.endTag(null, "int-array");
    }

    public static final void writeValueXml(Object obj, String str, XmlSerializer xmlSerializer) {
        if (obj == null) {
            xmlSerializer.startTag(null, "null");
            if (str != null) {
                xmlSerializer.attribute(null, MiniDefine.l, str);
            }
            xmlSerializer.endTag(null, "null");
        } else if (obj instanceof String) {
            xmlSerializer.startTag(null, "string");
            if (str != null) {
                xmlSerializer.attribute(null, MiniDefine.l, str);
            }
            xmlSerializer.text(obj.toString());
            xmlSerializer.endTag(null, "string");
        } else {
            String str2;
            if (obj instanceof Integer) {
                str2 = "int";
            } else if (obj instanceof Long) {
                str2 = "long";
            } else if (obj instanceof Float) {
                str2 = "float";
            } else if (obj instanceof Double) {
                str2 = "double";
            } else if (obj instanceof Boolean) {
                str2 = "boolean";
            } else if (obj instanceof byte[]) {
                writeByteArrayXml((byte[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof int[]) {
                writeIntArrayXml((int[]) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof Map) {
                writeMapXml((Map) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof List) {
                writeListXml((List) obj, str, xmlSerializer);
                return;
            } else if (obj instanceof CharSequence) {
                xmlSerializer.startTag(null, "string");
                if (str != null) {
                    xmlSerializer.attribute(null, MiniDefine.l, str);
                }
                xmlSerializer.text(obj.toString());
                xmlSerializer.endTag(null, "string");
                return;
            } else {
                throw new RuntimeException("writeValueXml: unable to write value " + obj);
            }
            xmlSerializer.startTag(null, str2);
            if (str != null) {
                xmlSerializer.attribute(null, MiniDefine.l, str);
            }
            xmlSerializer.attribute(null, MiniDefine.a, obj.toString());
            xmlSerializer.endTag(null, str2);
        }
    }

    public static final HashMap readMapXml(InputStream inputStream) {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, null);
        return (HashMap) readValueXml(newPullParser, new String[1]);
    }

    public static final ArrayList readListXml(InputStream inputStream) {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, null);
        return (ArrayList) readValueXml(newPullParser, new String[1]);
    }

    public static final HashMap readThisMapXml(XmlPullParser xmlPullParser, String str, String[] strArr) {
        HashMap hashMap = new HashMap();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                Object readThisValueXml = readThisValueXml(xmlPullParser, strArr);
                if (strArr[0] != null) {
                    hashMap.put(strArr[0], readThisValueXml);
                } else {
                    throw new XmlPullParserException("Map value without name attribute: " + xmlPullParser.getName());
                }
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return hashMap;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException("Document ended before " + str + " end tag");
    }

    public static final ArrayList readThisListXml(XmlPullParser xmlPullParser, String str, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        do {
            if (eventType == 2) {
                arrayList.add(readThisValueXml(xmlPullParser, strArr));
            } else if (eventType == 3) {
                if (xmlPullParser.getName().equals(str)) {
                    return arrayList;
                }
                throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
            }
            eventType = xmlPullParser.next();
        } while (eventType != 1);
        throw new XmlPullParserException("Document ended before " + str + " end tag");
    }

    public static final int[] readThisIntArrayXml(XmlPullParser xmlPullParser, String str, String[] strArr) {
        try {
            int[] iArr = new int[Integer.parseInt(xmlPullParser.getAttributeValue(null, MiniDefine.an))];
            int i = 0;
            int eventType = xmlPullParser.getEventType();
            do {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("item")) {
                        try {
                            iArr[i] = Integer.parseInt(xmlPullParser.getAttributeValue(null, MiniDefine.a));
                        } catch (NullPointerException e) {
                            throw new XmlPullParserException("Need value attribute in item");
                        } catch (NumberFormatException e2) {
                            throw new XmlPullParserException("Not a number in value attribute in item");
                        }
                    }
                    throw new XmlPullParserException("Expected item tag at: " + xmlPullParser.getName());
                } else if (eventType == 3) {
                    if (xmlPullParser.getName().equals(str)) {
                        return iArr;
                    }
                    if (xmlPullParser.getName().equals("item")) {
                        i++;
                    } else {
                        throw new XmlPullParserException("Expected " + str + " end tag at: " + xmlPullParser.getName());
                    }
                }
                eventType = xmlPullParser.next();
            } while (eventType != 1);
            throw new XmlPullParserException("Document ended before " + str + " end tag");
        } catch (NullPointerException e3) {
            throw new XmlPullParserException("Need num attribute in byte-array");
        } catch (NumberFormatException e4) {
            throw new XmlPullParserException("Not a number in num attribute in byte-array");
        }
    }

    public static final Object readValueXml(XmlPullParser xmlPullParser, String[] strArr) {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 2) {
            if (eventType == 3) {
                throw new XmlPullParserException("Unexpected end tag at: " + xmlPullParser.getName());
            } else if (eventType == 4) {
                throw new XmlPullParserException("Unexpected text: " + xmlPullParser.getText());
            } else {
                try {
                    eventType = xmlPullParser.next();
                    if (eventType == 1) {
                        throw new XmlPullParserException("Unexpected end of document");
                    }
                } catch (Exception e) {
                    throw new XmlPullParserException("Unexpected call next(): " + xmlPullParser.getName());
                }
            }
        }
        return readThisValueXml(xmlPullParser, strArr);
    }

    private static final Object readThisValueXml(XmlPullParser xmlPullParser, String[] strArr) {
        int next;
        Object obj = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, MiniDefine.l);
        String name = xmlPullParser.getName();
        if (!name.equals("null")) {
            if (name.equals("string")) {
                obj = ConfigConstant.WIRELESS_FILENAME;
                while (true) {
                    next = xmlPullParser.next();
                    if (next == 1) {
                        throw new XmlPullParserException("Unexpected end of document in <string>");
                    } else if (next == 3) {
                        break;
                    } else if (next == 4) {
                        obj = obj + xmlPullParser.getText();
                    } else if (next == 2) {
                        throw new XmlPullParserException("Unexpected start tag in <string>: " + xmlPullParser.getName());
                    }
                }
                if (xmlPullParser.getName().equals("string")) {
                    strArr[0] = attributeValue;
                    return obj;
                }
                throw new XmlPullParserException("Unexpected end tag in <string>: " + xmlPullParser.getName());
            } else if (name.equals("int")) {
                obj = Integer.valueOf(Integer.parseInt(xmlPullParser.getAttributeValue(null, MiniDefine.a)));
            } else if (name.equals("long")) {
                obj = Long.valueOf(xmlPullParser.getAttributeValue(null, MiniDefine.a));
            } else if (name.equals("float")) {
                Float f = new Float(xmlPullParser.getAttributeValue(null, MiniDefine.a));
            } else if (name.equals("double")) {
                Double d = new Double(xmlPullParser.getAttributeValue(null, MiniDefine.a));
            } else if (name.equals("boolean")) {
                obj = Boolean.valueOf(xmlPullParser.getAttributeValue(null, MiniDefine.a));
            } else if (name.equals("int-array")) {
                xmlPullParser.next();
                obj = readThisIntArrayXml(xmlPullParser, "int-array", strArr);
                strArr[0] = attributeValue;
                return obj;
            } else if (name.equals("map")) {
                xmlPullParser.next();
                obj = readThisMapXml(xmlPullParser, "map", strArr);
                strArr[0] = attributeValue;
                return obj;
            } else if (name.equals("list")) {
                xmlPullParser.next();
                obj = readThisListXml(xmlPullParser, "list", strArr);
                strArr[0] = attributeValue;
                return obj;
            } else {
                throw new XmlPullParserException("Unknown tag: " + name);
            }
        }
        do {
            next = xmlPullParser.next();
            if (next == 1) {
                throw new XmlPullParserException("Unexpected end of document in <" + name + ">");
            } else if (next == 3) {
                if (xmlPullParser.getName().equals(name)) {
                    strArr[0] = attributeValue;
                    return obj;
                }
                throw new XmlPullParserException("Unexpected end tag in <" + name + ">: " + xmlPullParser.getName());
            } else if (next == 4) {
                throw new XmlPullParserException("Unexpected text in <" + name + ">: " + xmlPullParser.getName());
            }
        } while (next != 2);
        throw new XmlPullParserException("Unexpected start tag in <" + name + ">: " + xmlPullParser.getName());
    }

    public static final void beginDocument(XmlPullParser xmlPullParser, String str) {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        } else if (!xmlPullParser.getName().equals(str)) {
            throw new XmlPullParserException("Unexpected start tag: found " + xmlPullParser.getName() + ", expected " + str);
        }
    }

    public static final void nextElement(XmlPullParser xmlPullParser) {
        int next;
        do {
            next = xmlPullParser.next();
            if (next == 2) {
                return;
            }
        } while (next != 1);
    }
}
