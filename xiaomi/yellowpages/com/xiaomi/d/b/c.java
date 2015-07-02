package com.xiaomi.d.b;

import com.xiaomi.d.c.e;
import com.xiaomi.d.c.i;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class c {
    private static c ny;
    private Map iJ;
    private Map nz;

    private c() {
        this.iJ = new ConcurrentHashMap();
        this.nz = new ConcurrentHashMap();
        b();
    }

    private String b(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(str).append("/>");
        if (str != null) {
            stringBuilder.append("<").append(str2).append("/>");
        }
        return stringBuilder.toString();
    }

    public static c bT() {
        synchronized (c.class) {
            try {
            } finally {
                Object obj = c.class;
            }
        }
        if (ny == null) {
            ny = new c();
        }
        c cVar = ny;
        return cVar;
    }

    private ClassLoader[] bU() {
        int i = 0;
        ClassLoader[] classLoaderArr = new ClassLoader[]{c.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
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

    public void a(String str, String str2, Object obj) {
        if ((obj instanceof b) || (obj instanceof Class)) {
            this.iJ.put(b(str, str2), obj);
            return;
        }
        throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
    }

    protected void b() {
        try {
            for (ClassLoader resources : bU()) {
                Enumeration resources2 = resources.getResources("META-INF/smack.providers");
                while (resources2.hasMoreElements()) {
                    InputStream inputStream = null;
                    inputStream = ((URL) resources2.nextElement()).openStream();
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                    newPullParser.setInput(inputStream, "UTF-8");
                    int eventType = newPullParser.getEventType();
                    do {
                        if (eventType == 2) {
                            String nextText;
                            String nextText2;
                            String nextText3;
                            Class cls;
                            if (newPullParser.getName().equals("iqProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                nextText = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText2 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText3 = newPullParser.nextText();
                                nextText = b(nextText, nextText2);
                                if (!this.nz.containsKey(nextText)) {
                                    try {
                                        cls = Class.forName(nextText3);
                                        if (a.class.isAssignableFrom(cls)) {
                                            this.nz.put(nextText, cls.newInstance());
                                        } else if (i.class.isAssignableFrom(cls)) {
                                            this.nz.put(nextText, cls);
                                        }
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (Throwable th) {
                                        try {
                                            inputStream.close();
                                        } catch (Exception e2) {
                                        }
                                    }
                                }
                            } else if (newPullParser.getName().equals("extensionProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                nextText = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText2 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                nextText3 = newPullParser.nextText();
                                nextText = b(nextText, nextText2);
                                if (!this.iJ.containsKey(nextText)) {
                                    try {
                                        cls = Class.forName(nextText3);
                                        if (b.class.isAssignableFrom(cls)) {
                                            this.iJ.put(nextText, cls.newInstance());
                                        } else if (e.class.isAssignableFrom(cls)) {
                                            this.iJ.put(nextText, cls);
                                        }
                                    } catch (ClassNotFoundException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            }
                        }
                        eventType = newPullParser.next();
                    } while (eventType != 1);
                    try {
                        inputStream.close();
                    } catch (Exception e4) {
                    }
                }
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    public Object h(String str, String str2) {
        return this.iJ.get(b(str, str2));
    }
}
