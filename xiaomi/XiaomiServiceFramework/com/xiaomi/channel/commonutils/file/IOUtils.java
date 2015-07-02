package com.xiaomi.channel.commonutils.file;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class IOUtils {
    private static final int BUFFER_SIZE = 1024;
    public static final String[] SUPPORTED_IMAGE_FORMATS;

    static {
        SUPPORTED_IMAGE_FORMATS = new String[]{"jpg", "png", "bmp", "gif", "webp"};
    }

    public static void zip(ZipOutputStream out, File f, String base, FileFilter filter) throws IOException {
        IOException e;
        Throwable th;
        if (base == null) {
            base = "";
        }
        InputStream in = null;
        try {
            if (f.isDirectory()) {
                File[] fl;
                if (filter != null) {
                    fl = f.listFiles(filter);
                } else {
                    fl = f.listFiles();
                }
                out.putNextEntry(new ZipEntry(base + File.separator));
                if (TextUtils.isEmpty(base)) {
                    base = "";
                } else {
                    base = base + File.separator;
                }
                int i = 0;
                while (true) {
                    int length = fl.length;
                    if (i >= r0) {
                        break;
                    }
                    zip(out, fl[i], base + fl[i].getName(), null);
                    i++;
                }
                File[] dirs = f.listFiles(new FileFilter() {
                    public boolean accept(File pathname) {
                        return pathname.isDirectory();
                    }
                });
                if (dirs != null) {
                    for (File subFile : dirs) {
                        zip(out, subFile, base + File.separator + subFile.getName(), filter);
                    }
                }
            } else {
                if (TextUtils.isEmpty(base)) {
                    out.putNextEntry(new ZipEntry(String.valueOf(new Date().getTime()) + ".txt"));
                } else {
                    out.putNextEntry(new ZipEntry(base));
                }
                InputStream in2 = new FileInputStream(f);
                try {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while (true) {
                        int bytesRead = in2.read(buffer);
                        if (bytesRead == -1) {
                            break;
                        }
                        out.write(buffer, 0, bytesRead);
                    }
                    in = in2;
                } catch (IOException e2) {
                    e = e2;
                    in = in2;
                    try {
                        MyLog.e("zipFiction failed with exception:" + e.toString());
                        closeQuietly(in);
                    } catch (Throwable th2) {
                        th = th2;
                        closeQuietly(in);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    in = in2;
                    closeQuietly(in);
                    throw th;
                }
            }
            closeQuietly(in);
        } catch (IOException e3) {
            e = e3;
            MyLog.e("zipFiction failed with exception:" + e.toString());
            closeQuietly(in);
        }
    }

    public static void zip(ZipOutputStream out, String fileName, InputStream inputStream) {
        try {
            if (TextUtils.isEmpty(fileName)) {
                out.putNextEntry(new ZipEntry(String.valueOf(new Date().getTime()) + ".txt"));
            } else {
                out.putNextEntry(new ZipEntry(fileName));
            }
            byte[] buffer = new byte[BUFFER_SIZE];
            while (true) {
                int bytesRead = inputStream.read(buffer);
                if (bytesRead != -1) {
                    out.write(buffer, 0, bytesRead);
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            MyLog.e("zipFiction failed with exception:" + e.toString());
        }
    }

    public static void closeQuietly(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(OutputStream os) {
        if (os != null) {
            try {
                os.flush();
            } catch (IOException e) {
            }
            try {
                os.close();
            } catch (IOException e2) {
            }
        }
    }

    public static void copyFile(File src, File dest) throws IOException {
        Throwable th;
        if (!src.getAbsolutePath().equals(dest.getAbsolutePath())) {
            InputStream in = null;
            OutputStream out = null;
            try {
                OutputStream out2;
                InputStream in2 = new FileInputStream(src);
                try {
                    out2 = new FileOutputStream(dest);
                } catch (Throwable th2) {
                    th = th2;
                    in = in2;
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                    throw th;
                }
                try {
                    byte[] buf = new byte[BUFFER_SIZE];
                    while (true) {
                        int len = in2.read(buf);
                        if (len < 0) {
                            break;
                        }
                        out2.write(buf, 0, len);
                    }
                    if (in2 != null) {
                        in2.close();
                    }
                    if (out2 != null) {
                        out2.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    out = out2;
                    in = in2;
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                throw th;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean unZip(java.lang.String r16, java.lang.String r17) {
        /*
        r14 = android.text.TextUtils.isEmpty(r17);
        if (r14 != 0) goto L_0x000c;
    L_0x0006:
        r14 = android.text.TextUtils.isEmpty(r16);
        if (r14 == 0) goto L_0x000e;
    L_0x000c:
        r14 = 0;
    L_0x000d:
        return r14;
    L_0x000e:
        r1 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r14 = "/";
        r0 = r17;
        r14 = r0.endsWith(r14);
        if (r14 != 0) goto L_0x002f;
    L_0x001a:
        r14 = new java.lang.StringBuilder;
        r14.<init>();
        r0 = r17;
        r14 = r14.append(r0);
        r15 = "/";
        r14 = r14.append(r15);
        r17 = r14.toString();
    L_0x002f:
        r4 = 0;
        r9 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x00a8 }
        r0 = r16;
        r9.<init>(r0);	 Catch:{ IOException -> 0x00a8 }
        r13 = new java.util.zip.ZipInputStream;	 Catch:{ IOException -> 0x00a8 }
        r14 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x00a8 }
        r14.<init>(r9);	 Catch:{ IOException -> 0x00a8 }
        r13.<init>(r14);	 Catch:{ IOException -> 0x00a8 }
        r14 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r3 = new byte[r14];	 Catch:{ IOException -> 0x00a8 }
        r5 = r4;
    L_0x0046:
        r7 = r13.getNextEntry();	 Catch:{ IOException -> 0x00bf }
        if (r7 == 0) goto L_0x00b9;
    L_0x004c:
        r12 = r7.getName();	 Catch:{ IOException -> 0x00bf }
        r8 = new java.io.File;	 Catch:{ IOException -> 0x00bf }
        r14 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00bf }
        r14.<init>();	 Catch:{ IOException -> 0x00bf }
        r0 = r17;
        r14 = r14.append(r0);	 Catch:{ IOException -> 0x00bf }
        r14 = r14.append(r12);	 Catch:{ IOException -> 0x00bf }
        r14 = r14.toString();	 Catch:{ IOException -> 0x00bf }
        r8.<init>(r14);	 Catch:{ IOException -> 0x00bf }
        r14 = "/";
        r14 = r12.endsWith(r14);	 Catch:{ IOException -> 0x00bf }
        if (r14 != 0) goto L_0x0046;
    L_0x0070:
        r11 = new java.io.File;	 Catch:{ IOException -> 0x00bf }
        r14 = r8.getParent();	 Catch:{ IOException -> 0x00bf }
        r11.<init>(r14);	 Catch:{ IOException -> 0x00bf }
        if (r11 == 0) goto L_0x008d;
    L_0x007b:
        r14 = r11.exists();	 Catch:{ IOException -> 0x00bf }
        if (r14 == 0) goto L_0x0087;
    L_0x0081:
        r14 = r11.isDirectory();	 Catch:{ IOException -> 0x00bf }
        if (r14 != 0) goto L_0x008d;
    L_0x0087:
        r11.mkdirs();	 Catch:{ IOException -> 0x00bf }
        hideFromMediaScanner(r11);	 Catch:{ IOException -> 0x00bf }
    L_0x008d:
        r10 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00bf }
        r10.<init>(r8);	 Catch:{ IOException -> 0x00bf }
        r4 = new java.io.BufferedOutputStream;	 Catch:{ IOException -> 0x00bf }
        r14 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r4.<init>(r10, r14);	 Catch:{ IOException -> 0x00bf }
    L_0x0099:
        r14 = 0;
        r15 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r2 = r13.read(r3, r14, r15);	 Catch:{ IOException -> 0x00a8 }
        r14 = -1;
        if (r2 == r14) goto L_0x00b1;
    L_0x00a3:
        r14 = 0;
        r4.write(r3, r14, r2);	 Catch:{ IOException -> 0x00a8 }
        goto L_0x0099;
    L_0x00a8:
        r6 = move-exception;
    L_0x00a9:
        r14 = "\u7459\uff45\ufffd\ufffd\u7f02\u2541\u3051\u7490\u30ef\ufffd\ufffd\u951b\ufffd\u951b\ufffd";
        com.xiaomi.channel.commonutils.logger.MyLog.e(r14, r6);
        r14 = 0;
        goto L_0x000d;
    L_0x00b1:
        r4.flush();	 Catch:{ IOException -> 0x00a8 }
        r4.close();	 Catch:{ IOException -> 0x00a8 }
        r5 = r4;
        goto L_0x0046;
    L_0x00b9:
        r13.close();	 Catch:{ IOException -> 0x00bf }
        r14 = 1;
        goto L_0x000d;
    L_0x00bf:
        r6 = move-exception;
        r4 = r5;
        goto L_0x00a9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.commonutils.file.IOUtils.unZip(java.lang.String, java.lang.String):boolean");
    }

    public static void hideFromMediaScanner(File root) {
        File file = new File(root, ".nomedia");
        if (!file.exists() || !file.isFile()) {
            try {
                file.createNewFile();
            } catch (Throwable e) {
                MyLog.e(e);
            }
        }
    }

    public static byte[] getFileSha1Digest(String fileName) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        FileInputStream inStream = new FileInputStream(new File(fileName));
        byte[] buffer = new byte[4096];
        while (true) {
            int readCount = inStream.read(buffer);
            if (readCount != -1) {
                md.update(buffer, 0, readCount);
            } else {
                try {
                    break;
                } catch (Throwable e) {
                    MyLog.e(e);
                }
            }
        }
        inStream.close();
        return md.digest();
    }

    public static byte[] getFileMD5Digest(String fileName) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream inStream = new FileInputStream(new File(fileName));
        byte[] buffer = new byte[4096];
        while (true) {
            int readCount = inStream.read(buffer);
            if (readCount != -1) {
                md.update(buffer, 0, readCount);
            } else {
                try {
                    break;
                } catch (Throwable e) {
                    MyLog.e(e);
                }
            }
        }
        inStream.close();
        return md.digest();
    }

    public static void deleteDirs(File file) {
        MyLog.v("deleteDirs filePath = " + file.getAbsolutePath());
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null && subFiles.length > 0) {
                for (File subF : subFiles) {
                    if (subF.isFile()) {
                        subF.delete();
                    } else {
                        deleteDirs(subF);
                    }
                }
            }
            file.delete();
        }
    }

    public static String getFileSuffix(String fileName) {
        int dotPos = fileName.lastIndexOf(46);
        if (dotPos > 0) {
            return fileName.substring(dotPos + 1);
        }
        return "";
    }

    public static boolean isSupportImageSuffix(String suffix) {
        if (TextUtils.isEmpty(suffix)) {
            return false;
        }
        for (String supported : SUPPORTED_IMAGE_FORMATS) {
            if (supported.equalsIgnoreCase(suffix)) {
                return true;
            }
        }
        return false;
    }
}
