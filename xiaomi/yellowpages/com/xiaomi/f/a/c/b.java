package com.xiaomi.f.a.c;

import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class b {
    private static c CI;
    private static HashMap CJ;
    private static HashMap CK;
    private static final Integer CL;
    private static AtomicInteger CM;
    private static int a;

    static {
        a = 2;
        CI = new a();
        CJ = new HashMap();
        CK = new HashMap();
        CL = Integer.valueOf(-1);
        CM = new AtomicInteger(1);
    }

    public static void a(int i) {
        if (i < 0 || i > 5) {
            a(2, "set log level as " + i);
        }
        a = i;
    }

    public static void a(int i, String str) {
        if (i >= a) {
            CI.log(str);
        }
    }

    public static void a(int i, String str, Throwable th) {
        if (i >= a) {
            CI.a(str, th);
        }
    }

    public static void a(int i, Throwable th) {
        if (i >= a) {
            CI.a(ConfigConstant.WIRELESS_FILENAME, th);
        }
    }

    public static void a(Integer num) {
        if (a <= 1 && CJ.containsKey(num)) {
            long currentTimeMillis = System.currentTimeMillis() - ((Long) CJ.remove(num)).longValue();
            CI.log(((String) CK.remove(num)) + " ends in " + currentTimeMillis + " ms");
        }
    }

    public static void a(String str) {
        a(2, str);
    }

    public static void a(Throwable th) {
        a(4, th);
    }

    public static void b(String str) {
        a(1, str);
    }

    public static void b(String str, Throwable th) {
        a(4, str, th);
    }

    public static void c(String str) {
        a(4, str);
    }

    public static Integer cd(String str) {
        if (a > 1) {
            return CL;
        }
        Integer valueOf = Integer.valueOf(CM.incrementAndGet());
        CJ.put(valueOf, Long.valueOf(System.currentTimeMillis()));
        CK.put(valueOf, str);
        CI.log(str + " starts");
        return valueOf;
    }

    public static void e(String str) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println(str);
        printWriter.println(String.format("Current thread id (%s); thread name (%s)", new Object[]{Long.valueOf(Thread.currentThread().getId()), Thread.currentThread().getName()}));
        new Throwable("Call stack").printStackTrace(printWriter);
        b(stringWriter.toString());
    }
}
