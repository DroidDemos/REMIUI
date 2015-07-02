package com.miui.yellowpage.utils;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.base.utils.Files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import miui.security.DigestUtils;
import miui.text.ExtraTextUtils;

/* compiled from: Antispam */
public class J {
    private static String Kx;
    private static File Ky;
    private static File Kz;

    static {
        Kx = "antispam";
    }

    public static File ar(Context context) {
        if (Kz == null) {
            Kz = new File(context.getFilesDir().getAbsoluteFile(), Kx + "/VERSION");
        }
        return Kz;
    }

    public static File as(Context context) {
        if (Ky == null) {
            Ky = new File(context.getFilesDir().getAbsoluteFile(), Kx + "/antispam.dat");
        }
        return Ky;
    }

    public static String at(Context context) {
        FileInputStream fileInputStream;
        IOException e;
        Throwable th;
        String str = ConfigConstant.WIRELESS_FILENAME;
        try {
            fileInputStream = new FileInputStream(as(context));
            try {
                str = ExtraTextUtils.toHexReadable(DigestUtils.get(fileInputStream, "MD5"));
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            e222 = e4;
            fileInputStream = null;
            e222.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return str;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
        return str;
    }

    public static long M(Context context) {
        IOException e;
        Throwable th;
        NumberFormatException e2;
        long j = 0;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            bufferedReader2 = new BufferedReader(new FileReader(ar(context)));
            try {
                Object readLine = bufferedReader2.readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    j = (long) Integer.parseInt(readLine);
                }
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (IOException e4) {
                e3 = e4;
                try {
                    e3.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    return j;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (NumberFormatException e6) {
                e2 = e6;
                bufferedReader = bufferedReader2;
                try {
                    e2.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                    return j;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (IOException e7) {
            e322 = e7;
            bufferedReader2 = null;
            e322.printStackTrace();
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return j;
        } catch (NumberFormatException e8) {
            e2 = e8;
            e2.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return j;
        }
        return j;
    }

    public static void j(Context context, long j) {
        IOException e;
        Throwable th;
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(ar(context)));
            try {
                bufferedWriter.write(String.valueOf(j));
                bufferedWriter.newLine();
                bufferedWriter.flush();
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            bufferedWriter = null;
            e22.printStackTrace();
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = null;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th;
        }
    }

    public static boolean au(Context context) {
        return Files.deleteFile(as(context));
    }

    public static boolean av(Context context) {
        return as(context).exists();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean aw(android.content.Context r9) {
        /*
        r3 = 0;
        r0 = 0;
        r5 = new java.io.File;
        r1 = r9.getFilesDir();
        r1 = r1.getAbsoluteFile();
        r2 = Kx;
        r5.<init>(r1, r2);
        r5.mkdirs();
        r1 = 0;
        r2 = miui.os.Environment.getMiuiDataDirectory();	 Catch:{ IOException -> 0x00e8 }
        r6 = new java.io.File;	 Catch:{ IOException -> 0x00e8 }
        r4 = "yellowpage/antispam.zip";
        r6.<init>(r2, r4);	 Catch:{ IOException -> 0x00e8 }
        r2 = r6.exists();	 Catch:{ IOException -> 0x00e8 }
        if (r2 != 0) goto L_0x0049;
    L_0x0026:
        r2 = "Antispam";
        r4 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00e8 }
        r4.<init>();	 Catch:{ IOException -> 0x00e8 }
        r5 = "cannot find ";
        r4 = r4.append(r5);	 Catch:{ IOException -> 0x00e8 }
        r4 = r4.append(r6);	 Catch:{ IOException -> 0x00e8 }
        r4 = r4.toString();	 Catch:{ IOException -> 0x00e8 }
        com.miui.yellowpage.base.utils.Log.e(r2, r4);	 Catch:{ IOException -> 0x00e8 }
        if (r3 == 0) goto L_0x0043;
    L_0x0040:
        r1.close();	 Catch:{ IOException -> 0x0044 }
    L_0x0043:
        return r0;
    L_0x0044:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0043;
    L_0x0049:
        r4 = new java.util.zip.ZipInputStream;	 Catch:{ IOException -> 0x00e8 }
        r1 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x00e8 }
        r2 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x00e8 }
        r2.<init>(r6);	 Catch:{ IOException -> 0x00e8 }
        r1.<init>(r2);	 Catch:{ IOException -> 0x00e8 }
        r4.<init>(r1);	 Catch:{ IOException -> 0x00e8 }
        r1 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r6 = new byte[r1];	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
    L_0x005c:
        r1 = r4.getNextEntry();	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        if (r1 == 0) goto L_0x00d3;
    L_0x0062:
        r7 = new java.io.File;	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        r1 = r1.getName();	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        r7.<init>(r5, r1);	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        r1 = "Antispam";
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        r2.<init>();	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        r8 = "inflating ";
        r2 = r2.append(r8);	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        r2 = r2.append(r7);	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        r2 = r2.toString();	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        com.miui.yellowpage.base.utils.Log.d(r1, r2);	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        r2 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00ec, all -> 0x00c6 }
        r2.<init>(r7);	 Catch:{ IOException -> 0x00ec, all -> 0x00c6 }
    L_0x0088:
        r1 = r4.read(r6);	 Catch:{ IOException -> 0x0094 }
        r7 = -1;
        if (r1 == r7) goto L_0x00b3;
    L_0x008f:
        r7 = 0;
        r2.write(r6, r7, r1);	 Catch:{ IOException -> 0x0094 }
        goto L_0x0088;
    L_0x0094:
        r1 = move-exception;
    L_0x0095:
        r1.printStackTrace();	 Catch:{ all -> 0x00ea }
        if (r2 == 0) goto L_0x005c;
    L_0x009a:
        r2.close();	 Catch:{ IOException -> 0x009e, all -> 0x00be }
        goto L_0x005c;
    L_0x009e:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        goto L_0x005c;
    L_0x00a3:
        r1 = move-exception;
        r3 = r4;
    L_0x00a5:
        r1.printStackTrace();	 Catch:{ all -> 0x00e6 }
        if (r3 == 0) goto L_0x0043;
    L_0x00aa:
        r3.close();	 Catch:{ IOException -> 0x00ae }
        goto L_0x0043;
    L_0x00ae:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0043;
    L_0x00b3:
        if (r2 == 0) goto L_0x005c;
    L_0x00b5:
        r2.close();	 Catch:{ IOException -> 0x00b9, all -> 0x00be }
        goto L_0x005c;
    L_0x00b9:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        goto L_0x005c;
    L_0x00be:
        r0 = move-exception;
        r3 = r4;
    L_0x00c0:
        if (r3 == 0) goto L_0x00c5;
    L_0x00c2:
        r3.close();	 Catch:{ IOException -> 0x00e1 }
    L_0x00c5:
        throw r0;
    L_0x00c6:
        r1 = move-exception;
        r2 = r3;
    L_0x00c8:
        if (r2 == 0) goto L_0x00cd;
    L_0x00ca:
        r2.close();	 Catch:{ IOException -> 0x00ce, all -> 0x00be }
    L_0x00cd:
        throw r1;	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
    L_0x00ce:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ IOException -> 0x00a3, all -> 0x00be }
        goto L_0x00cd;
    L_0x00d3:
        r0 = 1;
        if (r4 == 0) goto L_0x0043;
    L_0x00d6:
        r4.close();	 Catch:{ IOException -> 0x00db }
        goto L_0x0043;
    L_0x00db:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0043;
    L_0x00e1:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00c5;
    L_0x00e6:
        r0 = move-exception;
        goto L_0x00c0;
    L_0x00e8:
        r1 = move-exception;
        goto L_0x00a5;
    L_0x00ea:
        r1 = move-exception;
        goto L_0x00c8;
    L_0x00ec:
        r1 = move-exception;
        r2 = r3;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miui.yellowpage.utils.J.aw(android.content.Context):boolean");
    }
}
