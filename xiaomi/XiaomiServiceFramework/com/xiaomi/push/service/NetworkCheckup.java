package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class NetworkCheckup {
    private static final String GET_GATEWAY = "ip route";
    private static final Pattern IP_PATTERN;
    private static final String PING_TEMPLATE = "ping -W 500 -i 0.2 -c 3 %s";
    private static ThreadPoolExecutor sExecutor;

    static {
        IP_PATTERN = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
        sExecutor = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public static void doCheckup() {
        if (sExecutor.getActiveCount() <= 0) {
            sExecutor.execute(new Runnable() {
                public void run() {
                    String gateway = NetworkCheckup.getGateway();
                    if (TextUtils.isEmpty(gateway)) {
                        MyLog.warn("Network Checkup: cannot get gateway");
                    } else {
                        MyLog.warn("Network Checkup: get gateway:" + gateway);
                        NetworkCheckup.doPing(gateway);
                    }
                    try {
                        InetAddress addr = InetAddress.getByName("www.baidu.com");
                        MyLog.warn("Network Checkup: get address for www.baidu.com:" + addr.getAddress());
                        NetworkCheckup.doPing(addr.getHostAddress());
                    } catch (UnknownHostException e) {
                        MyLog.warn("Network Checkup: cannot resolve the host www.baidu.com");
                    } catch (Throwable e2) {
                        MyLog.warn("the checkup failure." + e2);
                    }
                }
            });
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getGateway() {
        /*
        r9 = 0;
        r7 = 0;
        r6 = 0;
        r12 = java.lang.Runtime.getRuntime();	 Catch:{ IOException -> 0x0059, Exception -> 0x0068 }
        r13 = "ip route";
        r6 = r12.exec(r13);	 Catch:{ IOException -> 0x0059, Exception -> 0x0068 }
        r8 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x0059, Exception -> 0x0068 }
        r12 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x0059, Exception -> 0x0068 }
        r13 = r6.getInputStream();	 Catch:{ IOException -> 0x0059, Exception -> 0x0068 }
        r12.<init>(r13);	 Catch:{ IOException -> 0x0059, Exception -> 0x0068 }
        r8.<init>(r12);	 Catch:{ IOException -> 0x0059, Exception -> 0x0068 }
        r4 = r8.readLine();	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        r12 = android.text.TextUtils.isEmpty(r4);	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        if (r12 != 0) goto L_0x004d;
    L_0x0025:
        r12 = "default via";
        r12 = r4.startsWith(r12);	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        if (r12 == 0) goto L_0x004d;
    L_0x002d:
        r12 = " ";
        r11 = r4.split(r12);	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        r0 = r11;
        r3 = r0.length;	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        r2 = 0;
    L_0x0036:
        if (r2 >= r3) goto L_0x004a;
    L_0x0038:
        r10 = r0[r2];	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        r12 = IP_PATTERN;	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        r5 = r12.matcher(r10);	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        r12 = r5.matches();	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
        if (r12 == 0) goto L_0x0047;
    L_0x0046:
        r9 = r10;
    L_0x0047:
        r2 = r2 + 1;
        goto L_0x0036;
    L_0x004a:
        r6.waitFor();	 Catch:{ IOException -> 0x008e, Exception -> 0x008b, all -> 0x0088 }
    L_0x004d:
        if (r8 == 0) goto L_0x0052;
    L_0x004f:
        r8.close();	 Catch:{ IOException -> 0x0086 }
    L_0x0052:
        if (r6 == 0) goto L_0x0057;
    L_0x0054:
        r6.destroy();
    L_0x0057:
        r7 = r8;
    L_0x0058:
        return r9;
    L_0x0059:
        r1 = move-exception;
    L_0x005a:
        com.xiaomi.channel.commonutils.logger.MyLog.e(r1);	 Catch:{ all -> 0x0074 }
        if (r7 == 0) goto L_0x0062;
    L_0x005f:
        r7.close();	 Catch:{ IOException -> 0x0082 }
    L_0x0062:
        if (r6 == 0) goto L_0x0058;
    L_0x0064:
        r6.destroy();
        goto L_0x0058;
    L_0x0068:
        r1 = move-exception;
    L_0x0069:
        com.xiaomi.channel.commonutils.logger.MyLog.e(r1);	 Catch:{ all -> 0x0074 }
        if (r7 == 0) goto L_0x0071;
    L_0x006e:
        r7.close();	 Catch:{ IOException -> 0x0084 }
    L_0x0071:
        if (r6 == 0) goto L_0x0058;
    L_0x0073:
        goto L_0x0064;
    L_0x0074:
        r12 = move-exception;
    L_0x0075:
        if (r7 == 0) goto L_0x007a;
    L_0x0077:
        r7.close();	 Catch:{ IOException -> 0x0080 }
    L_0x007a:
        if (r6 == 0) goto L_0x007f;
    L_0x007c:
        r6.destroy();
    L_0x007f:
        throw r12;
    L_0x0080:
        r13 = move-exception;
        goto L_0x007a;
    L_0x0082:
        r12 = move-exception;
        goto L_0x0062;
    L_0x0084:
        r12 = move-exception;
        goto L_0x0071;
    L_0x0086:
        r12 = move-exception;
        goto L_0x0052;
    L_0x0088:
        r12 = move-exception;
        r7 = r8;
        goto L_0x0075;
    L_0x008b:
        r1 = move-exception;
        r7 = r8;
        goto L_0x0069;
    L_0x008e:
        r1 = move-exception;
        r7 = r8;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.NetworkCheckup.getGateway():java.lang.String");
    }

    private static void doPing(String address) {
        Throwable e;
        Throwable th;
        MyLog.warn("Network Checkup: Begin to ping " + address);
        BufferedReader reader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(String.format(PING_TEMPLATE, new Object[]{address}));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(process.getInputStream()));
            try {
                for (String line = reader2.readLine(); line != null; line = reader2.readLine()) {
                    MyLog.warn("Network Checkup:" + line);
                }
                process.waitFor();
                try {
                    reader2.close();
                } catch (IOException e2) {
                }
                if (process != null) {
                    process.destroy();
                }
                reader = reader2;
            } catch (IOException e3) {
                e = e3;
                reader = reader2;
                try {
                    MyLog.e(e);
                    try {
                        reader.close();
                    } catch (IOException e4) {
                    }
                    if (process == null) {
                        return;
                    }
                    process.destroy();
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        reader.close();
                    } catch (IOException e5) {
                    }
                    if (process != null) {
                        process.destroy();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                reader = reader2;
                MyLog.e(e);
                try {
                    reader.close();
                } catch (IOException e7) {
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (Throwable th3) {
                th = th3;
                reader = reader2;
                reader.close();
                if (process != null) {
                    process.destroy();
                }
                throw th;
            }
        } catch (IOException e8) {
            e = e8;
            MyLog.e(e);
            reader.close();
            if (process == null) {
                return;
            }
            process.destroy();
        } catch (Exception e9) {
            e = e9;
            MyLog.e(e);
            reader.close();
            if (process != null) {
                process.destroy();
            }
        }
    }
}
