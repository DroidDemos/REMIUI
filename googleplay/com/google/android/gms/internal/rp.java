package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class rp {
    public static final Map<String, Integer> aCX;
    public static Iterable<?> aCY;
    public static final Handler aCZ;
    public static final String[] aDa;
    public static final Pattern aDb;
    public static final Pattern aDc;
    public static final Pattern aDd;
    public static final Pattern aDe;
    public static final String aDf;
    public static final String aDg;
    public static final SecureRandom aDh;
    private static final ThreadLocal<StringBuilder> aDi;
    private static final ThreadLocal<String[]> aDj;
    private static final ThreadLocal<String[]> aDk;
    private static final ThreadLocal<String[]> aDl;
    private static final ThreadLocal<String[]> aDm;
    private static final ThreadLocal<String[]> aDn;

    static {
        aCX = new HashMap<String, Integer>() {
            {
                put("circle", Integer.valueOf(-1));
                put("extendedCircles", Integer.valueOf(4));
                put("myCircles", Integer.valueOf(3));
                put("domain", Integer.valueOf(2));
                put("public", Integer.valueOf(1));
                put(null, Integer.valueOf(-2));
            }
        };
        aCY = new re();
        aCZ = new Handler(Looper.getMainLooper());
        aDa = new String[0];
        aDb = Pattern.compile("\\,");
        aDc = Pattern.compile("[\u2028\u2029 \u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000\t\u000b\f\u001c\u001d\u001e\u001f\n\r]+");
        aDd = Pattern.compile(Pattern.quote(String.valueOf('\u0001')));
        aDe = Pattern.compile(Pattern.quote(String.valueOf('\u0002')));
        aDf = String.valueOf('\u0001');
        aDg = String.valueOf('\u0002');
        aDh = new SecureRandom();
        aDi = new ThreadLocal<StringBuilder>() {
            protected /* synthetic */ Object initialValue() {
                return qS();
            }

            protected StringBuilder qS() {
                return new StringBuilder();
            }
        };
        aDj = new ThreadLocal<String[]>() {
            protected /* synthetic */ Object initialValue() {
                return qT();
            }

            protected String[] qT() {
                return new String[1];
            }
        };
        aDk = new ThreadLocal<String[]>() {
            protected /* synthetic */ Object initialValue() {
                return qT();
            }

            protected String[] qT() {
                return new String[2];
            }
        };
        aDl = new ThreadLocal<String[]>() {
            protected /* synthetic */ Object initialValue() {
                return qT();
            }

            protected String[] qT() {
                return new String[3];
            }
        };
        aDm = new ThreadLocal<String[]>() {
            protected /* synthetic */ Object initialValue() {
                return qT();
            }

            protected String[] qT() {
                return new String[4];
            }
        };
        aDn = new ThreadLocal<String[]>() {
            protected /* synthetic */ Object initialValue() {
                return qT();
            }

            protected String[] qT() {
                return new String[5];
            }
        };
    }

    public static void K(String str, String str2) {
        kn.b(str, (Object) str2);
        boolean z = str.startsWith("g:") || str.startsWith("e:");
        kn.b(z, str2 + ": Expecting qualified-id, not gaia-id");
    }
}
