package com.alipay.sdk.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

final class FileFetch implements Runnable {
    private String a;
    private String b;
    private FileDownloader c;
    private boolean d;
    private long e;
    private long f;

    final class FileAccess {
        final /* synthetic */ FileFetch a;
        private FileOutputStream b;

        public FileAccess(FileFetch fileFetch) {
            this.a = fileFetch;
            try {
                this.b = new FileOutputStream(fileFetch.b, true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public synchronized int a(byte[] bArr, int i, int i2) {
            this.b.write(bArr, i, i2);
            return i2;
        }

        public void a() {
            try {
                this.b.close();
            } catch (Exception e) {
            }
        }
    }

    public FileFetch(String str, String str2, FileDownloader fileDownloader) {
        this.d = false;
        this.a = str;
        this.b = str2;
        this.c = fileDownloader;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r13 = this;
        r12 = -1;
        r2 = 0;
        r4 = 1;
        r0 = r13.c;
        r0 = r0.a();
        if (r0 == 0) goto L_0x001e;
    L_0x000b:
        r0 = r13.f;
        r5 = 0;
        r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1));
        if (r0 <= 0) goto L_0x001b;
    L_0x0013:
        r0 = r13.e;
        r5 = r13.f;
        r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1));
        if (r0 < 0) goto L_0x001e;
    L_0x001b:
        r13.d = r4;
    L_0x001d:
        return;
    L_0x001e:
        r5 = new com.alipay.sdk.util.FileFetch$FileAccess;
        r5.<init>(r13);
    L_0x0023:
        r0 = r13.d;
        if (r0 != 0) goto L_0x007d;
    L_0x0027:
        r3 = 0;
        r0 = new org.apache.http.client.methods.HttpGet;	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r1 = r13.a;	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r0.<init>(r1);	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r1 = new org.apache.http.impl.client.DefaultHttpClient;	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r1.<init>();	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r6 = r13.c;	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r6 = r6.a();	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        if (r6 == 0) goto L_0x0062;
    L_0x003c:
        r6 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r6.<init>();	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r7 = "bytes=";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r7 = r13.e;	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r7 = "-";
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r7 = r13.f;	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r6 = r6.append(r7);	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r6 = r6.toString();	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r7 = "Range";
        r0.addHeader(r7, r6);	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
    L_0x0062:
        r0 = r1.execute(r0);	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r1 = r0.getStatusLine();	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        r1 = r1.getStatusCode();	 Catch:{ IOException -> 0x008a, SocketTimeoutException -> 0x00e7, Exception -> 0x0106 }
        switch(r1) {
            case 200: goto L_0x0081;
            case 201: goto L_0x0081;
            case 202: goto L_0x0081;
            case 203: goto L_0x0081;
            case 204: goto L_0x0081;
            case 205: goto L_0x0081;
            case 206: goto L_0x0081;
            case 207: goto L_0x0081;
            default: goto L_0x0071;
        };
    L_0x0071:
        r0 = 1;
        r13.d = r0;	 Catch:{ IOException -> 0x0122, SocketTimeoutException -> 0x0120, Exception -> 0x0106 }
    L_0x0074:
        r0 = r13.d;	 Catch:{ IOException -> 0x0122, SocketTimeoutException -> 0x0120, Exception -> 0x0106 }
        if (r0 == 0) goto L_0x0092;
    L_0x0078:
        if (r3 == 0) goto L_0x007d;
    L_0x007a:
        r3.close();	 Catch:{ Exception -> 0x011b }
    L_0x007d:
        r5.a();
        goto L_0x001d;
    L_0x0081:
        r0 = r0.getEntity();	 Catch:{ IOException -> 0x0122, SocketTimeoutException -> 0x0120, Exception -> 0x0106 }
        r3 = r0.getContent();	 Catch:{ IOException -> 0x0122, SocketTimeoutException -> 0x0120, Exception -> 0x0106 }
        goto L_0x0074;
    L_0x008a:
        r0 = move-exception;
        r1 = r2;
    L_0x008c:
        r0.printStackTrace();	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r0 = 1;
        r13.d = r0;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
    L_0x0092:
        if (r3 != 0) goto L_0x009c;
    L_0x0094:
        if (r3 == 0) goto L_0x0023;
    L_0x0096:
        r3.close();	 Catch:{ Exception -> 0x009a }
        goto L_0x0023;
    L_0x009a:
        r0 = move-exception;
        goto L_0x0023;
    L_0x009c:
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r6 = new byte[r0];	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
    L_0x00a0:
        r0 = 0;
        r7 = r6.length;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r7 = r3.read(r6, r0, r7);	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        if (r7 == r12) goto L_0x00b8;
    L_0x00a8:
        r8 = r13.e;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r0 = 0;
        r0 = r5.a(r6, r0, r7);	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r10 = (long) r0;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r8 = r8 + r10;
        r13.e = r8;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r0 = r13.c;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r0.d();	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
    L_0x00b8:
        r0 = r13.c;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r0 = r0.a();	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        if (r0 == 0) goto L_0x00e3;
    L_0x00c0:
        r8 = r13.e;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r10 = r13.f;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r0 >= 0) goto L_0x00e1;
    L_0x00c8:
        r0 = r4;
    L_0x00c9:
        r8 = r13.d;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        if (r8 != 0) goto L_0x00e5;
    L_0x00cd:
        if (r0 == 0) goto L_0x00e5;
    L_0x00cf:
        r0 = r4;
    L_0x00d0:
        if (r7 <= r12) goto L_0x00d4;
    L_0x00d2:
        if (r0 != 0) goto L_0x00a0;
    L_0x00d4:
        r0 = 1;
        r13.d = r0;	 Catch:{ SocketTimeoutException -> 0x0120, IOException -> 0x00f8, Exception -> 0x0106 }
        if (r3 == 0) goto L_0x0023;
    L_0x00d9:
        r3.close();	 Catch:{ Exception -> 0x00de }
        goto L_0x0023;
    L_0x00de:
        r0 = move-exception;
        goto L_0x0023;
    L_0x00e1:
        r0 = r2;
        goto L_0x00c9;
    L_0x00e3:
        r0 = r4;
        goto L_0x00c9;
    L_0x00e5:
        r0 = r2;
        goto L_0x00d0;
    L_0x00e7:
        r0 = move-exception;
        r1 = r2;
    L_0x00e9:
        if (r1 != 0) goto L_0x00ee;
    L_0x00eb:
        r0 = 1;
        r13.d = r0;	 Catch:{ all -> 0x0114 }
    L_0x00ee:
        if (r3 == 0) goto L_0x0023;
    L_0x00f0:
        r3.close();	 Catch:{ Exception -> 0x00f5 }
        goto L_0x0023;
    L_0x00f5:
        r0 = move-exception;
        goto L_0x0023;
    L_0x00f8:
        r0 = move-exception;
        r0 = 1;
        r13.d = r0;	 Catch:{ all -> 0x0114 }
        if (r3 == 0) goto L_0x0023;
    L_0x00fe:
        r3.close();	 Catch:{ Exception -> 0x0103 }
        goto L_0x0023;
    L_0x0103:
        r0 = move-exception;
        goto L_0x0023;
    L_0x0106:
        r0 = move-exception;
        r0 = 1;
        r13.d = r0;	 Catch:{ all -> 0x0114 }
        if (r3 == 0) goto L_0x0023;
    L_0x010c:
        r3.close();	 Catch:{ Exception -> 0x0111 }
        goto L_0x0023;
    L_0x0111:
        r0 = move-exception;
        goto L_0x0023;
    L_0x0114:
        r0 = move-exception;
        if (r3 == 0) goto L_0x011a;
    L_0x0117:
        r3.close();	 Catch:{ Exception -> 0x011e }
    L_0x011a:
        throw r0;
    L_0x011b:
        r0 = move-exception;
        goto L_0x007d;
    L_0x011e:
        r1 = move-exception;
        goto L_0x011a;
    L_0x0120:
        r0 = move-exception;
        goto L_0x00e9;
    L_0x0122:
        r0 = move-exception;
        goto L_0x008c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.FileFetch.run():void");
    }

    public final long a() {
        return this.e;
    }

    public final void a(long j) {
        this.e = j;
    }

    public final long b() {
        return this.f;
    }

    public final void b(long j) {
        this.f = j;
    }

    public final boolean c() {
        return this.d;
    }

    public final void d() {
        this.d = true;
    }
}
