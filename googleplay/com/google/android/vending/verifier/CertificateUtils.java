package com.google.android.vending.verifier;

import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Utils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CertificateUtils {
    public static byte[][] collectCertificates(String path) {
        byte[][] bArr;
        Exception ex;
        Throwable th;
        JarFile jarFile = null;
        try {
            JarFile jarFile2 = new JarFile(path);
            try {
                Certificate[] certs = loadCertificates(jarFile2, jarFile2.getJarEntry("AndroidManifest.xml"));
                if (certs == null || certs.length == 0) {
                    jarFile2.close();
                    bArr = (byte[][]) null;
                    if (jarFile2 != null) {
                        try {
                            jarFile2.close();
                        } catch (IOException ex2) {
                            FinskyLog.e(ex2, "Error closing apk file while collecting certificates", new Object[0]);
                        }
                    }
                    jarFile = jarFile2;
                    return bArr;
                }
                jarFile2.close();
                byte[][] encodedCerts = new byte[certs.length][];
                for (int i = 0; i < certs.length; i++) {
                    encodedCerts[i] = certs[i].getEncoded();
                }
                if (jarFile2 != null) {
                    try {
                        jarFile2.close();
                    } catch (IOException ex22) {
                        FinskyLog.e(ex22, "Error closing apk file while collecting certificates", new Object[0]);
                    }
                }
                jarFile = jarFile2;
                return encodedCerts;
            } catch (Exception e) {
                ex = e;
                jarFile = jarFile2;
                try {
                    FinskyLog.e(ex, "Error while collecting certificates", new Object[0]);
                    bArr = (byte[][]) null;
                    if (jarFile != null) {
                        return bArr;
                    }
                    try {
                        jarFile.close();
                        return bArr;
                    } catch (IOException ex222) {
                        FinskyLog.e(ex222, "Error closing apk file while collecting certificates", new Object[0]);
                        return bArr;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (jarFile != null) {
                        try {
                            jarFile.close();
                        } catch (IOException ex2222) {
                            FinskyLog.e(ex2222, "Error closing apk file while collecting certificates", new Object[0]);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                jarFile = jarFile2;
                if (jarFile != null) {
                    jarFile.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            ex = e2;
            FinskyLog.e(ex, "Error while collecting certificates", new Object[0]);
            bArr = (byte[][]) null;
            if (jarFile != null) {
                return bArr;
            }
            jarFile.close();
            return bArr;
        }
    }

    private static Certificate[] loadCertificates(JarFile jarFile, JarEntry entry) throws IOException {
        Throwable th;
        byte[] readBuffer = new byte[8192];
        InputStream inputStream = null;
        try {
            InputStream inputStream2 = new BufferedInputStream(jarFile.getInputStream(entry));
            do {
                try {
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = inputStream2;
                }
            } while (inputStream2.read(readBuffer, 0, readBuffer.length) != -1);
            Utils.safeClose(inputStream2);
            return entry != null ? entry.getCertificates() : null;
        } catch (Throwable th3) {
            th = th3;
            Utils.safeClose(inputStream);
            throw th;
        }
    }
}
