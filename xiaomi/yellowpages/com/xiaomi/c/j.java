package com.xiaomi.c;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.net.wifi.WifiManager;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.deviceID.Profile;
import com.alipay.sdk.cons.MiniDefine;
import com.xiaomi.a.a.a.a.a;
import com.xiaomi.a.a.a.a.b;
import com.xiaomi.a.a.a.a.d;
import com.xiaomi.a.a.a.a.e;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j {
    private static e tJ;
    private static boolean tK;
    private static Map tL;
    private static String tM;
    private static String tN;
    private static j tO;
    private final long tP;
    private String tQ;
    private long tR;
    protected Map tS;
    private long tT;
    private Context tU;
    private f tV;
    private h tW;
    private String tX;

    static {
        tL = new HashMap();
        tK = false;
    }

    protected j(Context context, f fVar, h hVar, String str, String str2, String str3) {
        this.tS = new HashMap();
        this.tX = Profile.devicever;
        this.tT = 0;
        this.tP = 15;
        this.tR = 0;
        this.tQ = "isp_prov_city_country_ip";
        this.tU = context.getApplicationContext();
        if (this.tU == null) {
            this.tU = context;
        }
        this.tW = hVar;
        if (fVar == null) {
            this.tV = new p(this);
        } else {
            this.tV = fVar;
        }
        this.tX = str;
        if (str2 == null) {
            str2 = context.getPackageName();
        }
        tM = str2;
        if (str3 == null) {
            str3 = getVersionName();
        }
        tN = str3;
    }

    private String a(ArrayList arrayList, String str) {
        ArrayList arrayList2 = new ArrayList();
        List<NameValuePair> arrayList3 = new ArrayList();
        arrayList3.add(new BasicNameValuePair(MiniDefine.m, str));
        arrayList3.add(new BasicNameValuePair("uuid", this.tX));
        arrayList3.add(new BasicNameValuePair("list", b(arrayList, ",")));
        i aX = aX("resolver.gslb.mi-idc.com");
        String format = String.format("http://%1$s/gslb/gslb/getbucket.asp?ver=3.0", new Object[]{"resolver.gslb.mi-idc.com"});
        if (aX == null) {
            arrayList2.add(format);
        } else {
            arrayList2 = aX.aK(format);
        }
        Iterator it = arrayList2.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Builder buildUpon = Uri.parse((String) it.next()).buildUpon();
        for (NameValuePair nameValuePair : arrayList3) {
            buildUpon.appendQueryParameter(nameValuePair.getName(), nameValuePair.getValue());
        }
        return this.tW == null ? c.a(this.tU, new URL(buildUpon.toString())) : this.tW.a(buildUpon.toString());
    }

    public static String a(String[] strArr, String str) {
        if (strArr == null || strArr.length == 0) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            stringBuilder.append(str);
            stringBuilder.append(strArr[i]);
        }
        return stringBuilder.toString();
    }

    public static void a(Context context, f fVar, h hVar, String str, String str2, String str3) {
        synchronized (j.class) {
            try {
                if (tO == null) {
                    if (tJ == null) {
                        tO = new j(context, fVar, hVar, str, str2, str3);
                    } else {
                        tO = tJ.a(context, fVar, hVar, str);
                    }
                    if (tO != null) {
                        if (l.fn() == null) {
                            l.a(context);
                        }
                        l.fn().a(new q());
                    }
                }
            } catch (Throwable th) {
                Class cls = j.class;
            }
        }
    }

    private void aW(String str) {
        synchronized (this.tS) {
            this.tS.clear();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                m m = new m().m(jSONArray.getJSONObject(i));
                this.tS.put(m.getHost(), m);
            }
        }
    }

    private i aX(String str) {
        synchronized (this.tS) {
            ep();
            m mVar = (m) this.tS.get(str);
        }
        if (mVar != null) {
            i hb = mVar.hb();
            if (hb != null) {
                return hb;
            }
        }
        return null;
    }

    private String aY(String str) {
        return TextUtils.isEmpty(str) ? "unknown" : str.startsWith("WIFI") ? "WIFI" : str;
    }

    private i aZ(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return (i) c(arrayList).get(0);
    }

    public static String b(Collection collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return ConfigConstant.WIRELESS_FILENAME;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            stringBuilder.append(it.next());
            if (it.hasNext()) {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    private ArrayList c(ArrayList arrayList) {
        String str;
        int i;
        purge();
        synchronized (this.tS) {
            ep();
            for (String str2 : this.tS.keySet()) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        synchronized (tL) {
            for (String str22 : tL.keySet()) {
                if (!arrayList.contains(str22)) {
                    arrayList.add(str22);
                }
            }
        }
        if (!arrayList.contains("resolver.gslb.mi-idc.com")) {
            arrayList.add("resolver.gslb.mi-idc.com");
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (i = 0; i < arrayList.size(); i++) {
            arrayList2.add(null);
        }
        try {
            str22 = es() ? ConfigConstant.JSON_SECTION_WIFI : "wap";
            Object a = a(arrayList, str22);
            if (!TextUtils.isEmpty(a)) {
                JSONObject jSONObject = new JSONObject(a);
                if ("OK".equalsIgnoreCase(jSONObject.getString("S"))) {
                    jSONObject = jSONObject.getJSONObject("R");
                    String string = jSONObject.getString("province");
                    String string2 = jSONObject.getString("city");
                    String string3 = jSONObject.getString("isp");
                    String string4 = jSONObject.getString("ip");
                    String string5 = jSONObject.getString("country");
                    JSONObject jSONObject2 = jSONObject.getJSONObject(str22);
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        str22 = (String) arrayList.get(i2);
                        JSONArray jSONArray = jSONObject2.getJSONArray(str22);
                        i iVar = new i(str22);
                        for (i = 0; i < jSONArray.length(); i++) {
                            Object string6 = jSONArray.getString(i);
                            if (!TextUtils.isEmpty(string6)) {
                                iVar.b(new b(string6, jSONArray.length() - i));
                            }
                        }
                        arrayList2.set(i2, iVar);
                        iVar.g = string5;
                        iVar.c = string;
                        iVar.e = string3;
                        iVar.f = string4;
                        iVar.d = string2;
                        if (jSONObject.has("stat-percent")) {
                            iVar.a(jSONObject.getDouble("stat-percent"));
                        }
                        if (jSONObject.has("stat-domain")) {
                            iVar.c(jSONObject.getString("stat-domain"));
                        }
                        if (jSONObject.has("ttl")) {
                            iVar.a(1000 * ((long) jSONObject.getInt("ttl")));
                        }
                        bb(iVar.d());
                    }
                }
            }
        } catch (Throwable e) {
            Log.e("com.xiaomi.network", "failed to get bucket", e);
        } catch (Throwable e2) {
            Log.e("com.xiaomi.network", "failed to get bucket", e2);
        } catch (Throwable e22) {
            Log.e("com.xiaomi.network", "failed to get bucket", e22);
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            i iVar2 = (i) arrayList2.get(i3);
            if (iVar2 != null) {
                a((String) arrayList.get(i3), iVar2);
            }
        }
        et();
        return arrayList2;
    }

    public static j en() {
        synchronized (j.class) {
            try {
            } finally {
                Object obj = j.class;
            }
        }
        if (tO == null) {
            throw new IllegalStateException("the host manager is not initialized yet.");
        }
        j jVar = tO;
        return jVar;
    }

    private JSONArray eo() {
        JSONArray jSONArray;
        synchronized (this.tS) {
            jSONArray = new JSONArray();
            for (m hd : this.tS.values()) {
                jSONArray.put(hd.hd());
            }
        }
        return jSONArray;
    }

    private String getVersionName() {
        try {
            PackageInfo packageInfo = this.tU.getPackageManager().getPackageInfo(this.tU.getPackageName(), 16384);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (Exception e) {
        }
        return Profile.devicever;
    }

    public static void p(String str, String str2) {
        ArrayList arrayList = (ArrayList) tL.get(str);
        synchronized (tL) {
            if (arrayList == null) {
                arrayList = new ArrayList();
                arrayList.add(str2);
                tL.put(str, arrayList);
            } else if (!arrayList.contains(str2)) {
                arrayList.add(str2);
            }
        }
    }

    public void a(String str, i iVar) {
        if (TextUtils.isEmpty(str) || iVar == null) {
            throw new IllegalArgumentException("the argument is invalid " + str + ", " + iVar);
        } else if (this.tV.a(str)) {
            synchronized (this.tS) {
                ep();
                if (this.tS.containsKey(str)) {
                    ((m) this.tS.get(str)).b(iVar);
                } else {
                    m mVar = new m(str);
                    mVar.b(iVar);
                    this.tS.put(str, mVar);
                }
            }
        }
    }

    public i ba(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        i aX;
        if (this.tV.a(str)) {
            aX = aX(str);
            if (aX == null) {
                if (System.currentTimeMillis() - this.tR > 1000 * (60 * this.tT)) {
                    this.tR = System.currentTimeMillis();
                    aX = aZ(str);
                    if (aX != null) {
                        this.tT = 0;
                        return aX;
                    } else if (this.tT < 15) {
                        this.tT = 1 + this.tT;
                    }
                }
                ArrayList arrayList = (ArrayList) tL.get(str);
                synchronized (tL) {
                    if (arrayList != null) {
                        i iVar = new i(str);
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            iVar.b((String) it.next());
                        }
                        return iVar;
                    }
                    return null;
                }
            }
        }
        aX = null;
        return aX;
    }

    public void bb(String str) {
        this.tQ = str;
    }

    protected boolean ep() {
        IOException e;
        Throwable th;
        BufferedReader bufferedReader = null;
        synchronized (this.tS) {
            if (tK) {
                return true;
            }
            tK = true;
            this.tS.clear();
            try {
                BufferedReader bufferedReader2;
                File file = new File(this.tU.getFilesDir(), getProcessName());
                if (file.isFile()) {
                    bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            stringBuilder.append(readLine);
                        }
                        Object stringBuilder2 = stringBuilder.toString();
                        if (!TextUtils.isEmpty(stringBuilder2)) {
                            aW(stringBuilder2);
                            Log.v("HostManager", "loading the new hosts succeed");
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e2) {
                                }
                            }
                            return true;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader = bufferedReader2;
                        try {
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e4) {
                                }
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e6) {
                    }
                }
            } catch (IOException e7) {
                e = e7;
                e.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return false;
            } catch (Throwable th4) {
                th = th4;
                th.printStackTrace();
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                return false;
            }
            return false;
        }
    }

    public ArrayList eq() {
        ArrayList arrayList;
        synchronized (this.tS) {
            Map hashMap = new HashMap();
            for (String str : this.tS.keySet()) {
                m mVar = (m) this.tS.get(str);
                if (mVar != null) {
                    Iterator it = mVar.hc().iterator();
                    while (it.hasNext()) {
                        b bVar;
                        i iVar = (i) it.next();
                        b bVar2 = (b) hashMap.get(iVar.d());
                        if (bVar2 == null) {
                            bVar2 = new b();
                            bVar2.u("httpapi");
                            bVar2.y(iVar.f);
                            bVar2.x(aY(iVar.a));
                            bVar2.v(this.tX);
                            bVar2.w(tN);
                            bVar2.z(tM);
                            bVar2.A(this.tU.getPackageName());
                            bVar2.B(getVersionName());
                            e eVar = new e();
                            eVar.q(iVar.d);
                            eVar.o(iVar.g);
                            eVar.p(iVar.c);
                            eVar.r(iVar.e);
                            bVar2.c(eVar);
                            hashMap.put(iVar.d(), bVar2);
                            bVar = bVar2;
                        } else {
                            bVar = bVar2;
                        }
                        a aVar = new a();
                        aVar.n(iVar.b);
                        List arrayList2 = new ArrayList();
                        Iterator it2 = iVar.dE().iterator();
                        while (it2.hasNext()) {
                            b bVar3 = (b) it2.next();
                            ArrayList aj = bVar3.aj();
                            if (!aj.isEmpty()) {
                                d dVar = new d();
                                dVar.s(bVar3.a);
                                int i = 0;
                                int i2 = 0;
                                long j = 0;
                                int i3 = 0;
                                Map hashMap2 = new HashMap();
                                Iterator it3 = aj.iterator();
                                while (it3.hasNext()) {
                                    long b;
                                    int bW;
                                    int i4;
                                    k kVar = (k) it3.next();
                                    int i5;
                                    if (kVar.a() >= 0) {
                                        i5 = i + 1;
                                        i = i2;
                                        b = j + kVar.b();
                                        bW = (int) (((long) i3) + kVar.bW());
                                        i4 = i5;
                                    } else {
                                        CharSequence e = kVar.e();
                                        if (!TextUtils.isEmpty(e)) {
                                            hashMap2.put(e, Integer.valueOf(hashMap2.containsKey(e) ? ((Integer) hashMap2.get(e)).intValue() + 1 : 1));
                                        }
                                        i5 = i;
                                        i = i2 + 1;
                                        i4 = i5;
                                        b = j;
                                        bW = i3;
                                    }
                                    i3 = bW;
                                    j = b;
                                    i2 = i;
                                    i = i4;
                                }
                                dVar.a(hashMap2);
                                dVar.f(i);
                                dVar.e(i2);
                                dVar.e(j);
                                dVar.g(i3);
                                arrayList2.add(dVar);
                            }
                        }
                        if (!arrayList2.isEmpty()) {
                            aVar.a(arrayList2);
                            bVar.c(aVar);
                        }
                    }
                    continue;
                }
            }
            arrayList = new ArrayList();
            for (b bVar4 : hashMap.values()) {
                if (bVar4.g() > 0) {
                    arrayList.add(bVar4);
                }
            }
        }
        return arrayList;
    }

    public String er() {
        if (this.tU == null) {
            return "unknown";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) this.tU.getSystemService("connectivity");
        if (connectivityManager == null) {
            return "unknown";
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() != 1) {
                return activeNetworkInfo.getTypeName() + "-" + activeNetworkInfo.getSubtypeName();
            }
            WifiManager wifiManager = (WifiManager) this.tU.getSystemService(ConfigConstant.JSON_SECTION_WIFI);
            if (!(wifiManager == null || wifiManager.getConnectionInfo() == null)) {
                return "WIFI-" + wifiManager.getConnectionInfo().getSSID();
            }
            return "unknown";
        } catch (Exception e) {
        }
    }

    public boolean es() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.tU.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return 1 == activeNetworkInfo.getType();
        } catch (Exception e) {
            return false;
        }
    }

    public void et() {
        purge();
        synchronized (this.tS) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.tU.openFileOutput(getProcessName(), 0)));
                Object jSONArray = eo().toString();
                if (!TextUtils.isEmpty(jSONArray)) {
                    bufferedWriter.write(jSONArray);
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    protected String getProcessName() {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.tU.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return "com.xiaomi";
    }

    public void purge() {
        synchronized (this.tS) {
            for (m purge : this.tS.values()) {
                purge.purge();
            }
            Object obj = null;
            while (obj == null) {
                for (String str : this.tS.keySet()) {
                    if (((m) this.tS.get(str)).hc().isEmpty()) {
                        this.tS.remove(str);
                        obj = null;
                        break;
                    }
                }
                obj = 1;
            }
        }
    }
}
