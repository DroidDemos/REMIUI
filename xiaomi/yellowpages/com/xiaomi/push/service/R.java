package com.xiaomi.push.service;

import com.xiaomi.f.a.c.b;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class R {
    private static final Pattern gf;
    private static ThreadPoolExecutor wq;

    static {
        gf = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
        wq = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public static void a() {
        if (wq.getActiveCount() <= 0) {
            wq.execute(new S());
        }
    }

    private static void b(String str) {
        Process exec;
        Throwable e;
        Throwable th;
        BufferedReader bufferedReader = null;
        b.a("Network Checkup: Begin to ping " + str);
        try {
            exec = Runtime.getRuntime().exec(String.format("ping -W 500 -i 0.2 -c 3 %s", new Object[]{str}));
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                try {
                    for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                        b.a("Network Checkup:" + readLine);
                    }
                    exec.waitFor();
                    try {
                        bufferedReader2.close();
                    } catch (IOException e2) {
                    }
                    if (exec == null) {
                        return;
                    }
                } catch (IOException e3) {
                    e = e3;
                    bufferedReader = bufferedReader2;
                    try {
                        b.a(e);
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                        }
                        if (exec == null) {
                            exec.destroy();
                        }
                        return;
                    } catch (Throwable e5) {
                        Throwable th2 = e5;
                        Process process = exec;
                        th = th2;
                        Process process2 = process;
                        e5 = th;
                        exec = process2;
                        try {
                            bufferedReader.close();
                        } catch (IOException e6) {
                        }
                        if (exec != null) {
                            exec.destroy();
                        }
                        throw e5;
                    }
                } catch (Exception e7) {
                    e5 = e7;
                    bufferedReader = bufferedReader2;
                    b.a(e5);
                    try {
                        bufferedReader.close();
                    } catch (IOException e8) {
                    }
                    if (exec == null) {
                        exec.destroy();
                    }
                    return;
                } catch (Throwable th3) {
                    e5 = th3;
                    bufferedReader = bufferedReader2;
                    bufferedReader.close();
                    if (exec != null) {
                        exec.destroy();
                    }
                    throw e5;
                }
            } catch (IOException e9) {
                e5 = e9;
                b.a(e5);
                bufferedReader.close();
                if (exec == null) {
                    exec.destroy();
                }
                return;
            } catch (Exception e10) {
                e5 = e10;
                b.a(e5);
                bufferedReader.close();
                if (exec == null) {
                    exec.destroy();
                }
                return;
            }
        } catch (Throwable th4) {
            e5 = th4;
            exec = null;
            b.a(e5);
            bufferedReader.close();
            if (exec == null) {
                exec.destroy();
            }
            return;
        } catch (Throwable th42) {
            e5 = th42;
            exec = null;
            b.a(e5);
            bufferedReader.close();
            if (exec == null) {
                exec.destroy();
            }
            return;
        } catch (Throwable th422) {
            e5 = th422;
            exec = null;
            bufferedReader.close();
            if (exec != null) {
                exec.destroy();
            }
            throw e5;
        }
        exec.destroy();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String c() {
        /*
        r2 = 0;
        r0 = java.lang.Runtime.getRuntime();	 Catch:{ IOException -> 0x0058, Exception -> 0x0067, all -> 0x0076 }
        r1 = "ip route";
        r1 = r0.exec(r1);	 Catch:{ IOException -> 0x0058, Exception -> 0x0067, all -> 0x0076 }
        r3 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x009a, Exception -> 0x008a, all -> 0x00bb }
        r0 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x009a, Exception -> 0x008a, all -> 0x00bb }
        r4 = r1.getInputStream();	 Catch:{ IOException -> 0x009a, Exception -> 0x008a, all -> 0x00bb }
        r0.<init>(r4);	 Catch:{ IOException -> 0x009a, Exception -> 0x008a, all -> 0x00bb }
        r3.<init>(r0);	 Catch:{ IOException -> 0x009a, Exception -> 0x008a, all -> 0x00bb }
        r0 = r3.readLine();	 Catch:{ IOException -> 0x009e, Exception -> 0x008e, all -> 0x0083 }
        r4 = android.text.TextUtils.isEmpty(r0);	 Catch:{ IOException -> 0x009e, Exception -> 0x008e, all -> 0x0083 }
        if (r4 != 0) goto L_0x00ac;
    L_0x0023:
        r4 = "default via";
        r4 = r0.startsWith(r4);	 Catch:{ IOException -> 0x009e, Exception -> 0x008e, all -> 0x0083 }
        if (r4 == 0) goto L_0x00ac;
    L_0x002b:
        r4 = " ";
        r5 = r0.split(r4);	 Catch:{ IOException -> 0x009e, Exception -> 0x008e, all -> 0x0083 }
        r6 = r5.length;	 Catch:{ IOException -> 0x009e, Exception -> 0x008e, all -> 0x0083 }
        r0 = 0;
        r4 = r0;
    L_0x0034:
        if (r4 >= r6) goto L_0x0049;
    L_0x0036:
        r0 = r5[r4];	 Catch:{ IOException -> 0x00a4, Exception -> 0x0094, all -> 0x0083 }
        r7 = gf;	 Catch:{ IOException -> 0x00a4, Exception -> 0x0094, all -> 0x0083 }
        r7 = r7.matcher(r0);	 Catch:{ IOException -> 0x00a4, Exception -> 0x0094, all -> 0x0083 }
        r7 = r7.matches();	 Catch:{ IOException -> 0x00a4, Exception -> 0x0094, all -> 0x0083 }
        if (r7 == 0) goto L_0x00aa;
    L_0x0044:
        r2 = r4 + 1;
        r4 = r2;
        r2 = r0;
        goto L_0x0034;
    L_0x0049:
        r1.waitFor();	 Catch:{ IOException -> 0x00a4, Exception -> 0x0094, all -> 0x0083 }
        r0 = r2;
    L_0x004d:
        if (r3 == 0) goto L_0x0052;
    L_0x004f:
        r3.close();	 Catch:{ IOException -> 0x00b4 }
    L_0x0052:
        if (r1 == 0) goto L_0x0057;
    L_0x0054:
        r1.destroy();
    L_0x0057:
        return r0;
    L_0x0058:
        r0 = move-exception;
        r3 = r0;
        r1 = r2;
        r0 = r2;
    L_0x005c:
        com.xiaomi.f.a.c.b.a(r3);	 Catch:{ all -> 0x00b6 }
        if (r2 == 0) goto L_0x0064;
    L_0x0061:
        r2.close();	 Catch:{ IOException -> 0x00b0 }
    L_0x0064:
        if (r1 == 0) goto L_0x0057;
    L_0x0066:
        goto L_0x0054;
    L_0x0067:
        r0 = move-exception;
        r3 = r0;
        r1 = r2;
        r0 = r2;
    L_0x006b:
        com.xiaomi.f.a.c.b.a(r3);	 Catch:{ all -> 0x00b6 }
        if (r2 == 0) goto L_0x0073;
    L_0x0070:
        r2.close();	 Catch:{ IOException -> 0x00b2 }
    L_0x0073:
        if (r1 == 0) goto L_0x0057;
    L_0x0075:
        goto L_0x0054;
    L_0x0076:
        r0 = move-exception;
        r1 = r2;
    L_0x0078:
        if (r2 == 0) goto L_0x007d;
    L_0x007a:
        r2.close();	 Catch:{ IOException -> 0x00ae }
    L_0x007d:
        if (r1 == 0) goto L_0x0082;
    L_0x007f:
        r1.destroy();
    L_0x0082:
        throw r0;
    L_0x0083:
        r0 = move-exception;
        r2 = r1;
        r1 = r3;
    L_0x0086:
        r8 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x0078;
    L_0x008a:
        r0 = move-exception;
        r3 = r0;
        r0 = r2;
        goto L_0x006b;
    L_0x008e:
        r0 = move-exception;
        r8 = r0;
        r0 = r2;
        r2 = r3;
        r3 = r8;
        goto L_0x006b;
    L_0x0094:
        r0 = move-exception;
        r8 = r0;
        r0 = r2;
        r2 = r3;
        r3 = r8;
        goto L_0x006b;
    L_0x009a:
        r0 = move-exception;
        r3 = r0;
        r0 = r2;
        goto L_0x005c;
    L_0x009e:
        r0 = move-exception;
        r8 = r0;
        r0 = r2;
        r2 = r3;
        r3 = r8;
        goto L_0x005c;
    L_0x00a4:
        r0 = move-exception;
        r8 = r0;
        r0 = r2;
        r2 = r3;
        r3 = r8;
        goto L_0x005c;
    L_0x00aa:
        r0 = r2;
        goto L_0x0044;
    L_0x00ac:
        r0 = r2;
        goto L_0x004d;
    L_0x00ae:
        r2 = move-exception;
        goto L_0x007d;
    L_0x00b0:
        r2 = move-exception;
        goto L_0x0064;
    L_0x00b2:
        r2 = move-exception;
        goto L_0x0073;
    L_0x00b4:
        r2 = move-exception;
        goto L_0x0052;
    L_0x00b6:
        r0 = move-exception;
        r8 = r2;
        r2 = r1;
        r1 = r8;
        goto L_0x0086;
    L_0x00bb:
        r0 = move-exception;
        goto L_0x0078;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.R.c():java.lang.String");
    }
}
