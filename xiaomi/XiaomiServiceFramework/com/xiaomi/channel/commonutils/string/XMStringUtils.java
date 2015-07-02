package com.xiaomi.channel.commonutils.string;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class XMStringUtils {
    public static String join(Object[] array) {
        return join(array, null);
    }

    public static String join(Object[] array, char separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        int bufSize = endIndex - startIndex;
        if (bufSize <= 0) {
            return "";
        }
        int i;
        if (array[startIndex] == null) {
            i = 16;
        } else {
            i = array[startIndex].toString().length();
        }
        StringBuffer buf = new StringBuffer(bufSize * (i + 1));
        for (int i2 = startIndex; i2 < endIndex; i2++) {
            if (i2 > startIndex) {
                buf.append(separator);
            }
            if (array[i2] != null) {
                buf.append(array[i2]);
            }
        }
        return buf.toString();
    }

    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        int bufSize = endIndex - startIndex;
        if (bufSize <= 0) {
            return "";
        }
        int i;
        if (array[startIndex] == null) {
            i = 16;
        } else {
            i = array[startIndex].toString().length();
        }
        StringBuffer buf = new StringBuffer(bufSize * (i + separator.length()));
        for (int i2 = startIndex; i2 < endIndex; i2++) {
            if (i2 > startIndex) {
                buf.append(separator);
            }
            if (array[i2] != null) {
                buf.append(array[i2]);
            }
        }
        return buf.toString();
    }

    public static String join(Iterator<?> iterator, char separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first.toString();
        }
        StringBuffer buf = new StringBuffer(256);
        if (first != null) {
            buf.append(first);
        }
        while (iterator.hasNext()) {
            buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first.toString();
        }
        StringBuffer buf = new StringBuffer(256);
        if (first != null) {
            buf.append(first);
        }
        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String join(Collection<?> collection, char separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    public static String join(Collection<?> collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    public static String generateRandomString(int len) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return sb.toString();
    }

    public static int getStringUTF8Length(String str) {
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                i = str.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException e) {
            }
        }
        return i;
    }

    public static boolean contains(String searchableString, String restriction) {
        int i = 0;
        int j = 0;
        while (i < restriction.length() && j < searchableString.length()) {
            if (restriction.charAt(i) == searchableString.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        if (i == restriction.length()) {
            return true;
        }
        return false;
    }

    public static String getHexString(byte[] b) {
        String result = "";
        for (byte b2 : b) {
            result = result + Integer.toString((b2 & 255) + 256, 16).substring(1);
        }
        return result;
    }

    public static String getStringNotNull(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static int stringToInt(String src, int defaultValue) {
        try {
            return Integer.parseInt(src);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String getMd5Digest(String pInput) {
        if (pInput == null) {
            return null;
        }
        try {
            MessageDigest lDigest = MessageDigest.getInstance("MD5");
            lDigest.update(getBytes(pInput));
            BigInteger lHashInt = new BigInteger(1, lDigest.digest());
            return String.format("%1$032X", new Object[]{lHashInt});
        } catch (NoSuchAlgorithmException e) {
            return pInput;
        }
    }

    public static String getSHA1Digest(String pInput) {
        if (pInput == null) {
            return null;
        }
        try {
            MessageDigest lDigest = MessageDigest.getInstance("SHA1");
            lDigest.update(getBytes(pInput));
            BigInteger lHashInt = new BigInteger(1, lDigest.digest());
            return String.format("%1$032X", new Object[]{lHashInt});
        } catch (NoSuchAlgorithmException e) {
            return pInput;
        }
    }

    public static byte[] getBytes(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s.getBytes();
        }
    }

    public static String[] toStrArray(List<String> arrList) {
        String[] r = new String[arrList.size()];
        arrList.toArray(r);
        return r;
    }

    public static long[] toLongArray(List<Long> arrList) {
        long[] r = new long[arrList.size()];
        for (int i = 0; i < arrList.size(); i++) {
            r[i] = ((Long) arrList.get(i)).longValue();
        }
        return r;
    }

    public static int[] toIntArray(List<Integer> l) {
        int[] r = new int[l.size()];
        for (int i = 0; i < l.size(); i++) {
            r[i] = ((Integer) l.get(i)).intValue();
        }
        return r;
    }
}
