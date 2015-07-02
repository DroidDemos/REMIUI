package com.miui.yellowpage.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.miui.yellowpage.base.exception.ParseException;
import com.miui.yellowpage.base.utils.Coder;
import com.miui.yellowpage.base.utils.Files;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.model.k;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: WebResourceManager */
public class I {
    private static boolean JH;
    private static I JI;
    private static Object sLock;

    static {
        sLock = new Object();
    }

    public static String ab(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/webview";
    }

    public static String af(Context context) {
        return context.getFilesDir().getAbsolutePath() + "/webview_temp";
    }

    public static String ag(Context context) {
        return af(context) + "/" + "build.hash";
    }

    public static String ah(Context context) {
        return ab(context) + "/" + "build.hash";
    }

    private I() {
    }

    public static synchronized I ik() {
        I i;
        synchronized (I.class) {
            if (JI == null) {
                JI = new I();
            }
            i = JI;
        }
        return i;
    }

    public static String H(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = "content://miui.yellowpage/web/static";
        int indexOf = str.indexOf(str2);
        if (indexOf < 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(ab(context));
        stringBuilder.append("/");
        int length = str.length();
        if (str.contains("?")) {
            length = str.indexOf("?");
        }
        stringBuilder.append(str.substring(str2.length() + indexOf, length));
        return stringBuilder.toString();
    }

    private boolean ai(Context context) {
        boolean z = Math.abs(System.currentTimeMillis() - aq(context)) <= TimeUnit.MINUTES.toMillis(5);
        if (z) {
            Log.d("WebResourceManager", "updated in 5 minutes");
        }
        return z;
    }

    public void aj(Context context) {
        Throwable th;
        synchronized (sLock) {
            if (JH) {
                Log.d("WebResourceManager", "updating web view resources, quit");
                return;
            }
            JH = true;
            File file = null;
            try {
                if (ai(context) && an(context)) {
                    Log.d("WebResourceManager", "local files are fresh and consistent, quit");
                    if (!(file == null || !file.exists() || Files.deleteFile(file))) {
                        Log.d("WebResourceManager", "Delete temp web view resources fails");
                    }
                    synchronized (sLock) {
                        JH = false;
                    }
                    return;
                }
                Log.d("WebResourceManager", "Start to update web view resources");
                File file2 = new File(af(context));
                try {
                    if (!h(file2)) {
                        Log.e("WebResourceManager", "failed to create tmp dir");
                        if (!(file2 == null || !file2.exists() || Files.deleteFile(file2))) {
                            Log.d("WebResourceManager", "Delete temp web view resources fails");
                        }
                        synchronized (sLock) {
                            JH = false;
                        }
                    } else if (al(context)) {
                        List I = I(context, ag(context));
                        if (I == null) {
                            throw new ParseException("failed to create build file list");
                        } else if (I.size() == 0) {
                            Log.v("WebResourceManager", "no differences");
                            i(context, System.currentTimeMillis());
                            if (!(file2 == null || !file2.exists() || Files.deleteFile(file2))) {
                                Log.d("WebResourceManager", "Delete temp web view resources fails");
                            }
                            synchronized (sLock) {
                                JH = false;
                            }
                        } else if (!a(context, I)) {
                            throw new IOException("failed to download resources");
                        } else if (!b(context, I)) {
                            Log.e("WebResourceManager", "failed to copy resources");
                            if (!(file2 == null || !file2.exists() || Files.deleteFile(file2))) {
                                Log.d("WebResourceManager", "Delete temp web view resources fails");
                            }
                            synchronized (sLock) {
                                JH = false;
                            }
                        } else if (am(context)) {
                            Log.d("WebResourceManager", "Download all web resources files successful");
                            i(context, System.currentTimeMillis());
                            context.sendBroadcast(new Intent("com.miui.yellowpage.web_page_resources_updated"));
                            ao(context);
                            if (!(file2 == null || !file2.exists() || Files.deleteFile(file2))) {
                                Log.d("WebResourceManager", "Delete temp web view resources fails");
                            }
                            synchronized (sLock) {
                                JH = false;
                            }
                        } else {
                            Log.e("WebResourceManager", "failed to copy build file");
                            if (!(file2 == null || !file2.exists() || Files.deleteFile(file2))) {
                                Log.d("WebResourceManager", "Delete temp web view resources fails");
                            }
                            synchronized (sLock) {
                                JH = false;
                            }
                        }
                    } else {
                        throw new IOException("Download build files fails");
                    }
                } catch (Throwable th2) {
                    th = th2;
                    file = file2;
                    Log.d("WebResourceManager", "Delete temp web view resources fails");
                    synchronized (sLock) {
                        JH = false;
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (!(file == null || !file.exists() || Files.deleteFile(file))) {
                    Log.d("WebResourceManager", "Delete temp web view resources fails");
                }
                synchronized (sLock) {
                    JH = false;
                }
                throw th;
            }
        }
    }

    public void ak(Context context) {
        try {
            aj(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean h(File file) {
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        Log.e("WebResourceManager", "create " + file.getName() + " failed");
        return false;
    }

    private boolean al(Context context) {
        return Files.downLoadFile(context, HostManager.URL_WEB_PAGE_RESOURCES_BUILD, ag(context));
    }

    private boolean a(Context context, List list) {
        for (k kVar : list) {
            String str = kVar.do();
            String str2 = af(context) + "/" + str;
            if (!Files.downLoadFile(context, kVar.dn(), str2)) {
                Log.e("WebResourceManager", "Download " + str + " fail, try again");
                if (!Files.downLoadFile(context, kVar.dn(), str2)) {
                    Log.e("WebResourceManager", "Download " + str + " failed again, stop updating web view page resources");
                    return false;
                }
            }
            if (!TextUtils.equals(kVar.getSha1(), Files.getFileSha1(str2))) {
                Log.e("WebResourceManager", "The downloaded file " + str2 + " is not consistent with remote");
                return false;
            }
        }
        return true;
    }

    private boolean am(Context context) {
        return Files.copyFile(ag(context), ah(context));
    }

    private boolean b(Context context, List list) {
        for (k kVar : list) {
            String str = af(context) + "/" + kVar.do();
            String str2 = ab(context) + "/" + kVar.do();
            if (!Files.copyFile(str, str2)) {
                Log.e("WebResourceManager", "Copy " + str + " to " + str2 + " fails");
                return false;
            }
        }
        return true;
    }

    private boolean an(Context context) {
        Log.d("WebResourceManager", "checking webview resource consistency");
        List I = I(context, ah(context));
        boolean z = I != null && I.size() == 0;
        if (!z) {
            Log.d("WebResourceManager", "local files not consistent");
        }
        return z;
    }

    private List I(Context context, String str) {
        List<k> cm = cm(str);
        if (cm == null) {
            return null;
        }
        List arrayList = new ArrayList();
        for (k kVar : cm) {
            if (!TextUtils.equals(kVar.getSha1(), Files.getFileSha1(ab(context) + "/" + kVar.do()))) {
                arrayList.add(kVar);
            }
        }
        return arrayList;
    }

    private List cm(String str) {
        Object fileString = Files.getFileString(str);
        if (TextUtils.isEmpty(fileString)) {
            return null;
        }
        return k.aF(Coder.decodeBase64(fileString));
    }

    public void ao(Context context) {
        Log.d("WebResourceManager", "start cleanup process");
        List<k> cm = cm(ah(context));
        if (cm == null || cm.size() == 0) {
            Log.e("WebResourceManager", "build file empty or corrupted, quit");
            return;
        }
        Set hashSet = new HashSet();
        hashSet.add(new File(ah(context)));
        for (k kVar : cm) {
            hashSet.add(new File(ab(context) + "/" + kVar.do()));
        }
        a(new File(ab(context)), hashSet);
    }

    private void a(File file, Set set) {
        if (file.isFile() && !set.contains(file.getAbsoluteFile())) {
            Log.d("WebResourceManager", file + " is useless, delete");
            file.delete();
        }
        if (file.isDirectory()) {
            for (File a : file.listFiles()) {
                a(a, set);
            }
            if (file.list().length == 0) {
                Log.d("WebResourceManager", file + " is empty, delete");
                file.delete();
            }
        }
    }

    public static void ap(Context context) {
        Preference.setLong(context, "pref_web_resource_last_update_time", Long.valueOf(Long.MIN_VALUE));
    }

    public static long aq(Context context) {
        return Preference.getLong(context, "pref_web_resource_last_update_time", 0);
    }

    public static void i(Context context, long j) {
        Preference.setLong(context, "pref_web_resource_last_update_time", Long.valueOf(j));
    }
}
