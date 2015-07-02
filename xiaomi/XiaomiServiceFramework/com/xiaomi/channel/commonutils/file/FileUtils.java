package com.xiaomi.channel.commonutils.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class FileUtils {
    private static final HashMap<String, String> mFileTypes;

    static {
        mFileTypes = new HashMap();
        mFileTypes.put("FFD8FF", "jpg");
        mFileTypes.put("89504E47", "png");
        mFileTypes.put("47494638", "gif");
        mFileTypes.put("474946", "gif");
        mFileTypes.put("424D", "bmp");
    }

    public static boolean isGif(String filePath) {
        return "gif".equals(getFileType(filePath));
    }

    public static String getFileType(String filePath) {
        return (String) mFileTypes.get(getFileHeader(filePath));
    }

    private static String getFileHeader(String filePath) {
        Throwable th;
        FileInputStream is = null;
        String value = null;
        try {
            FileInputStream is2 = new FileInputStream(filePath);
            try {
                byte[] b = new byte[3];
                is2.read(b, 0, b.length);
                value = bytesToHexString(b);
                if (is2 != null) {
                    try {
                        is2.close();
                        is = is2;
                    } catch (IOException e) {
                        is = is2;
                    }
                }
            } catch (Exception e2) {
                is = is2;
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e3) {
                    }
                }
                return value;
            } catch (Throwable th2) {
                th = th2;
                is = is2;
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            if (is != null) {
                is.close();
            }
            return value;
        } catch (Throwable th3) {
            th = th3;
            if (is != null) {
                is.close();
            }
            throw th;
        }
        return value;
    }

    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            String hv = Integer.toHexString(b & 255).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }
}
