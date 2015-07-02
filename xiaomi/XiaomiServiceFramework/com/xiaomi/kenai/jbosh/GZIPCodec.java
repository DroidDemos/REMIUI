package com.xiaomi.kenai.jbosh;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

final class GZIPCodec {
    private static final int BUFFER_SIZE = 512;

    private GZIPCodec() {
    }

    public static String getID() {
        return "gzip";
    }

    public static byte[] encode(byte[] data) throws IOException {
        Throwable th;
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        GZIPOutputStream gzOut = null;
        try {
            GZIPOutputStream gzOut2 = new GZIPOutputStream(byteOut);
            try {
                gzOut2.write(data);
                gzOut2.close();
                byteOut.close();
                byte[] toByteArray = byteOut.toByteArray();
                try {
                    gzOut2.close();
                    byteOut.close();
                } catch (IOException e) {
                }
                return toByteArray;
            } catch (Throwable th2) {
                th = th2;
                gzOut = gzOut2;
                try {
                    gzOut.close();
                    byteOut.close();
                } catch (IOException e2) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            gzOut.close();
            byteOut.close();
            throw th;
        }
    }

    public static byte[] decode(byte[] compressed) throws IOException {
        Throwable th;
        ByteArrayInputStream byteIn = new ByteArrayInputStream(compressed);
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        GZIPInputStream gzIn = null;
        try {
            GZIPInputStream gzIn2 = new GZIPInputStream(byteIn);
            try {
                byte[] buffer = new byte[BUFFER_SIZE];
                int read;
                do {
                    read = gzIn2.read(buffer);
                    if (read > 0) {
                        byteOut.write(buffer, 0, read);
                        continue;
                    }
                } while (read >= 0);
                byte[] toByteArray = byteOut.toByteArray();
                if (gzIn2 != null) {
                    gzIn2.close();
                }
                byteOut.close();
                return toByteArray;
            } catch (Throwable th2) {
                th = th2;
                gzIn = gzIn2;
                if (gzIn != null) {
                    gzIn.close();
                }
                byteOut.close();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (gzIn != null) {
                gzIn.close();
            }
            byteOut.close();
            throw th;
        }
    }
}
