package com.google.android.finsky.utils;

import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.DetailedState;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Looper;
import com.google.android.finsky.activities.DebugActivity;
import com.google.android.finsky.config.G;
import com.google.android.play.utils.PlayUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class Utils {
    private static Pattern COMMA_PATTERN;
    private static String[] EMPTY_ARRAY;

    static {
        COMMA_PATTERN = Pattern.compile(",");
        EMPTY_ARRAY = new String[0];
    }

    public static void ensureOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("This method must be called from the UI thread.");
        }
    }

    public static void ensureNotOnMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("This method cannot be called from the UI thread.");
        }
    }

    public static <P> void executeMultiThreaded(AsyncTask<P, ?, ?> asyncTask, P... params) {
        if (VERSION.SDK_INT >= 11) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        } else {
            asyncTask.execute(params);
        }
    }

    public static String getPreferenceKey(String key, String accountName) {
        return accountName + ":" + key;
    }

    public static boolean isBackgroundDataEnabled(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (VERSION.SDK_INT < 14) {
            return connectivityManager.getBackgroundDataSetting();
        }
        for (NetworkInfo info : connectivityManager.getAllNetworkInfo()) {
            if (info != null && info.getDetailedState() == DetailedState.BLOCKED) {
                return false;
            }
        }
        return true;
    }

    public static String urlEncode(String decodedUrl) {
        try {
            return URLEncoder.encode(decodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            FinskyLog.wtf("%s", e);
            throw new RuntimeException(e);
        }
    }

    public static String urlDecode(String encodedUrl) {
        try {
            return URLDecoder.decode(encodedUrl, "UTF-8");
        } catch (IllegalArgumentException e) {
            FinskyLog.d("Unable to parse %s - %s", encodedUrl, e.getMessage());
            return null;
        } catch (UnsupportedEncodingException e2) {
            FinskyLog.wtf("%s", e2);
            throw new RuntimeException(e2);
        }
    }

    public static void checkUrlIsSecure(String url) {
        checkUrlIsSecure(url, PlayUtils.isTestDevice());
    }

    static void checkUrlIsSecure(String url, boolean isTestDevice) {
        Uri parsed = Uri.parse(url);
        if (!parsed.getScheme().equals("https")) {
            if (!isTestDevice || (!parsed.getHost().toLowerCase().endsWith("corp.google.com") && !parsed.getHost().startsWith("192.168.0") && !parsed.getHost().startsWith("127.0.0"))) {
                throw new RuntimeException("Insecure URL: " + url);
            }
        }
    }

    public static String commaPackStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        if (strings.length == 1) {
            return strings[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i != 0) {
                sb.append(',');
            }
            sb.append(strings[i]);
        }
        return sb.toString();
    }

    public static String[] commaUnpackStrings(String stringsList) {
        if (stringsList == null || stringsList.length() == 0) {
            return EMPTY_ARRAY;
        }
        if (stringsList.indexOf(44) != -1) {
            return COMMA_PATTERN.split(stringsList);
        }
        return new String[]{stringsList};
    }

    public static void syncDebugActivityStatus(Context context) {
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, DebugActivity.class), ((Boolean) G.debugOptionsEnabled.get()).booleanValue() ? 1 : 2, 1);
    }

    public static byte[] readBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            copy(in, out);
            byte[] toByteArray = out.toByteArray();
            return toByteArray;
        } finally {
            out.close();
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8192];
        while (true) {
            try {
                int bytesRead = in.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                out.write(buffer, 0, bytesRead);
            } finally {
                in.close();
            }
        }
    }

    public static void safeClose(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
            }
        }
    }

    public static boolean isEmptyOrSpaces(CharSequence text) {
        return text == null || text.length() == 0 || trim(text).length() == 0;
    }

    public static CharSequence trim(CharSequence text) {
        if (text == null) {
            return null;
        }
        int start = 0;
        int last = text.length() - 1;
        int end = last;
        while (start <= end && text.charAt(start) <= ' ') {
            start++;
        }
        while (end >= start && text.charAt(end) <= ' ') {
            end--;
        }
        return (start == 0 && end == last) ? text : text.subSequence(start, end + 1);
    }

    public static String getItalicSafeString(String source) {
        return source + " ";
    }
}
