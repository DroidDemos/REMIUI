package com.xiaomi.b.a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

final class g {
    public static byte[] a(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        Throwable th;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.close();
                byteArrayOutputStream.close();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                try {
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                }
                return toByteArray;
            } catch (Throwable th2) {
                th = th2;
                try {
                    gZIPOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = null;
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            throw th;
        }
    }

    public static byte[] g(byte[] bArr) {
        Throwable th;
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPInputStream gZIPInputStream;
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                byte[] bArr2 = new byte[512];
                int read;
                do {
                    read = gZIPInputStream.read(bArr2);
                    if (read > 0) {
                        byteArrayOutputStream.write(bArr2, 0, read);
                        continue;
                    }
                } while (read >= 0);
                bArr2 = byteArrayOutputStream.toByteArray();
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                byteArrayOutputStream.close();
                return bArr2;
            } catch (Throwable th2) {
                th = th2;
                if (gZIPInputStream != null) {
                    gZIPInputStream.close();
                }
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            gZIPInputStream = null;
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            byteArrayOutputStream.close();
            throw th;
        }
    }
}
