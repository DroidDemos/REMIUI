package com.xiaomi.xmsf.push.service.trace;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.http.NameValuePair;

class AdsSaltUtil {
    AdsSaltUtil() {
    }

    public static String getKeyFromParams(List<NameValuePair> nameValuePairs, String publishId) {
        Collections.sort(nameValuePairs, new Comparator<NameValuePair>() {
            public int compare(NameValuePair p1, NameValuePair p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
        StringBuilder keyBuilder = new StringBuilder();
        boolean isFirst = true;
        for (NameValuePair nvp : nameValuePairs) {
            if (!isFirst) {
                keyBuilder.append("&");
            }
            keyBuilder.append(nvp.getName()).append("=").append(nvp.getValue());
            isFirst = false;
        }
        keyBuilder.append("&").append(publishId);
        return getMd5Digest(new String(Base64.encode(getBytes(keyBuilder.toString()), 2)));
    }

    private static byte[] getBytes(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s.getBytes();
        }
    }

    public static String getMd5Digest(String pInput) {
        if (pInput == null) {
            return "";
        }
        try {
            MessageDigest lDigest = MessageDigest.getInstance("MD5");
            lDigest.update(getBytes(pInput));
            BigInteger lHashInt = new BigInteger(1, lDigest.digest());
            return String.format("%1$032X", new Object[]{lHashInt});
        } catch (NoSuchAlgorithmException lException) {
            throw new RuntimeException(lException);
        }
    }
}
