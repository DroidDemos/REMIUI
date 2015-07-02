package com.xiaomi.network;

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
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.common.logger.thrift.mfs.HostInfo;
import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import com.xiaomi.common.logger.thrift.mfs.LandNodeInfo;
import com.xiaomi.common.logger.thrift.mfs.Location;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.network.UploadHostStatHelper.HttpRecordCallback;
import com.xiaomi.push.service.PushServiceConstants;
import com.xiaomi.xmsf.push.service.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManager {
    private static final String BUCKET_URL = "http://%1$s/gslb/gslb/getbucket.asp?ver=3.0";
    private static final String HOST = "resolver.gslb.mi-idc.com";
    private static final String TAG = "HostManager";
    private static HostManagerFactory factory;
    private static boolean hostLoaded;
    private static Map<String, ArrayList<String>> mReservedHosts;
    private static String sAppName;
    private static String sAppVersion;
    private static HostManager sInstance;
    private final long MAX_REQUEST_FAILURE_CNT;
    private String currentISP;
    private long lastRemoteRequestTimestamp;
    protected Map<String, Fallbacks> mHostsMapping;
    private long remoteRequestFailureCount;
    private Context sAppContext;
    private HostFilter sHostFilter;
    private HttpGet sHttpGetter;
    private String sUserId;

    public interface HostManagerFactory {
        HostManager createHostManager(Context context, HostFilter hostFilter, HttpGet httpGet, String str);
    }

    public interface HttpGet {
        String doGet(String str) throws IOException;
    }

    static {
        mReservedHosts = new HashMap();
        hostLoaded = false;
    }

    public static synchronized HostManager getInstance() {
        HostManager hostManager;
        synchronized (HostManager.class) {
            if (sInstance == null) {
                throw new IllegalStateException("the host manager is not initialized yet.");
            }
            hostManager = sInstance;
        }
        return hostManager;
    }

    public static void setHostManagerFactory(HostManagerFactory factory) {
        factory = factory;
    }

    public static synchronized void init(Context context, HostFilter hostFilter, HttpGet httpGet, String userId) {
        synchronized (HostManager.class) {
            init(context, hostFilter, httpGet, userId, null, null);
        }
    }

    public static synchronized void init(Context context, HostFilter hostFilter, HttpGet httpGet, String userId, String appName, String appVersion) {
        synchronized (HostManager.class) {
            if (sInstance == null) {
                if (factory == null) {
                    sInstance = new HostManager(context, hostFilter, httpGet, userId, appName, appVersion);
                } else {
                    sInstance = factory.createHostManager(context, hostFilter, httpGet, userId);
                }
                if (sInstance != null) {
                    if (UploadHostStatHelper.getInstance() == null) {
                        UploadHostStatHelper.init(context);
                    }
                    UploadHostStatHelper.getInstance().addCallBack(new HttpRecordCallback() {
                        public List<HttpApi> generateStat() {
                            try {
                                return HostManager.sInstance.generateHostStats();
                            } catch (JSONException e) {
                                return null;
                            }
                        }

                        public double getPercentage() {
                            Fallback fb = HostManager.sInstance.getFallbacksByHost(UploadHostStatHelper.UPLOAD_HOST_NEW);
                            if (fb != null) {
                                return fb.getPercent();
                            }
                            return Fallback.DEFAULT_UPLOAD_RATIO;
                        }
                    });
                }
            }
        }
    }

    protected HostManager(Context context, HostFilter hostFilter, HttpGet httpGet, String userId) {
        this(context, hostFilter, httpGet, userId, null, null);
    }

    protected HostManager(Context context, HostFilter hostFilter, HttpGet httpGet, String userId, String appName, String appVersion) {
        this.mHostsMapping = new HashMap();
        this.sUserId = "0";
        this.remoteRequestFailureCount = 0;
        this.MAX_REQUEST_FAILURE_CNT = 15;
        this.lastRemoteRequestTimestamp = 0;
        this.currentISP = "isp_prov_city_country_ip";
        this.sAppContext = context.getApplicationContext();
        if (this.sAppContext == null) {
            this.sAppContext = context;
        }
        this.sHttpGetter = httpGet;
        if (hostFilter == null) {
            this.sHostFilter = new HostFilter() {
                public boolean accept(String host) {
                    return true;
                }
            };
        } else {
            this.sHostFilter = hostFilter;
        }
        this.sUserId = userId;
        if (appName == null) {
            appName = context.getPackageName();
        }
        sAppName = appName;
        if (appVersion == null) {
            appVersion = getVersionName();
        }
        sAppVersion = appVersion;
    }

    public String getActiveNetworkLabel() {
        if (this.sAppContext == null) {
            return "unknown";
        }
        try {
            ConnectivityManager cm = (ConnectivityManager) this.sAppContext.getSystemService("connectivity");
            if (cm == null) {
                return "unknown";
            }
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni == null) {
                return "unknown";
            }
            if (ni.getType() != 1) {
                return ni.getTypeName() + "-" + ni.getSubtypeName();
            }
            WifiManager wifi = (WifiManager) this.sAppContext.getSystemService(Network.NETWORK_TYPE_WIFI);
            if (!(wifi == null || wifi.getConnectionInfo() == null)) {
                return "WIFI-" + wifi.getConnectionInfo().getSSID();
            }
            return "unknown";
        } catch (Exception e) {
        }
    }

    public Fallback getFallbacksByURL(String url) throws MalformedURLException {
        if (!TextUtils.isEmpty(url)) {
            return getFallbacksByHost(new URL(url).getHost());
        }
        throw new IllegalArgumentException("the url is empty");
    }

    public Fallback getFallbacksByHost(String host) {
        if (TextUtils.isEmpty(host)) {
            throw new IllegalArgumentException("the host is empty");
        } else if (!this.sHostFilter.accept(host)) {
            return null;
        } else {
            Fallback localFB = getLocalFallback(host);
            if (localFB != null) {
                return localFB;
            }
            if (System.currentTimeMillis() - this.lastRemoteRequestTimestamp > (this.remoteRequestFailureCount * 60) * 1000) {
                this.lastRemoteRequestTimestamp = System.currentTimeMillis();
                Fallback remoteFallback = requestRemoteFallback(host);
                if (remoteFallback != null) {
                    this.remoteRequestFailureCount = 0;
                    return remoteFallback;
                } else if (this.remoteRequestFailureCount < 15) {
                    this.remoteRequestFailureCount++;
                }
            }
            ArrayList<String> hosts = (ArrayList) mReservedHosts.get(host);
            synchronized (mReservedHosts) {
                if (hosts != null) {
                    Fallback fb = new Fallback(host);
                    Iterator i$ = hosts.iterator();
                    while (i$.hasNext()) {
                        fb.addHost((String) i$.next());
                    }
                    return fb;
                }
                return null;
            }
        }
    }

    private Fallback getLocalFallback(String host) {
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            Fallbacks fallbacks = (Fallbacks) this.mHostsMapping.get(host);
        }
        if (fallbacks != null) {
            Fallback cfb = fallbacks.getFallback();
            if (cfb != null) {
                return cfb;
            }
        }
        return null;
    }

    private Fallback requestRemoteFallback(String host) {
        ArrayList<String> hosts = new ArrayList();
        hosts.add(host);
        return (Fallback) requestRemoteFallbacks(hosts).get(0);
    }

    private ArrayList<Fallback> requestRemoteFallbacks(ArrayList<String> hosts) {
        int i;
        Fallback fb;
        purge();
        synchronized (this.mHostsMapping) {
            checkHostMapping();
            for (String existHost : this.mHostsMapping.keySet()) {
                if (!hosts.contains(existHost)) {
                    hosts.add(existHost);
                }
            }
        }
        synchronized (mReservedHosts) {
            for (String existHost2 : mReservedHosts.keySet()) {
                if (!hosts.contains(existHost2)) {
                    hosts.add(existHost2);
                }
            }
        }
        if (!hosts.contains(HOST)) {
            hosts.add(HOST);
        }
        ArrayList<Fallback> fallbacks = new ArrayList(hosts.size());
        for (i = 0; i < hosts.size(); i++) {
            fallbacks.add(null);
        }
        try {
            String networkType = isWIFIConnected() ? Network.NETWORK_TYPE_WIFI : "wap";
            String result = getRemoteFallbackJSON(hosts, networkType);
            if (!TextUtils.isEmpty(result)) {
                JSONObject jSONObject = new JSONObject(result);
                if ("OK".equalsIgnoreCase(jSONObject.getString("S"))) {
                    JSONObject response = jSONObject.getJSONObject("R");
                    String province = response.getString("province");
                    String city = response.getString("city");
                    String isp = response.getString("isp");
                    String ip = response.getString(Constants.JSON_TAG_IP);
                    String country = response.getString("country");
                    JSONObject wifiHosts = response.getJSONObject(networkType);
                    for (i = 0; i < hosts.size(); i++) {
                        String host = (String) hosts.get(i);
                        JSONArray fallbackHosts = wifiHosts.getJSONArray(host);
                        fb = new Fallback(host);
                        for (int j = 0; j < fallbackHosts.length(); j++) {
                            String fallbackHost = fallbackHosts.getString(j);
                            if (!TextUtils.isEmpty(fallbackHost)) {
                                fb.addHost(new WeightedHost(fallbackHost, fallbackHosts.length() - j));
                            }
                        }
                        fallbacks.set(i, fb);
                        fb.country = country;
                        fb.province = province;
                        fb.isp = isp;
                        fb.ip = ip;
                        fb.city = city;
                        if (response.has("stat-percent")) {
                            fb.setPercent(response.getDouble("stat-percent"));
                        }
                        if (response.has("stat-domain")) {
                            fb.setDomainName(response.getString("stat-domain"));
                        }
                        if (response.has("ttl")) {
                            fb.setEffectiveDuration(((long) response.getInt("ttl")) * 1000);
                        }
                        setCurrentISP(fb.getISP());
                    }
                }
            }
        } catch (JSONException e) {
        } catch (IOException e2) {
        } catch (Exception e3) {
        }
        for (i = 0; i < hosts.size(); i++) {
            fb = (Fallback) fallbacks.get(i);
            if (fb != null) {
                updateFallbacks((String) hosts.get(i), fb);
            }
        }
        persist();
        return fallbacks;
    }

    private String getRemoteFallbackJSON(ArrayList<String> hosts, String networkType) throws IOException {
        ArrayList<String> urls = new ArrayList();
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair(Constants.JSON_TAG_ADSTYPE, networkType));
        params.add(new BasicNameValuePair(PushServiceConstants.EXTRA_UUID, this.sUserId));
        params.add(new BasicNameValuePair("list", join((Collection) hosts, MiPushClient.ACCEPT_TIME_SEPARATOR)));
        Fallback localFallback = getLocalFallback(HOST);
        String originalURL = String.format(BUCKET_URL, new Object[]{HOST});
        if (localFallback == null) {
            urls.add(originalURL);
        } else {
            urls = localFallback.getUrls(originalURL);
        }
        Iterator i$ = urls.iterator();
        if (!i$.hasNext()) {
            return null;
        }
        Builder uri = Uri.parse((String) i$.next()).buildUpon();
        for (NameValuePair pair : params) {
            uri.appendQueryParameter(pair.getName(), pair.getValue());
        }
        if (this.sHttpGetter == null) {
            return Network.downloadXml(this.sAppContext, new URL(uri.toString()));
        }
        return this.sHttpGetter.doGet(uri.toString());
    }

    public boolean isWIFIConnected() {
        boolean z = true;
        try {
            ConnectivityManager cm = (ConnectivityManager) this.sAppContext.getSystemService("connectivity");
            if (cm == null) {
                return false;
            }
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) {
                return false;
            }
            if (1 != info.getType()) {
                z = false;
            }
            return z;
        } catch (Exception e) {
            return false;
        }
    }

    public void clear() {
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
        }
    }

    public String dump() {
        StringBuilder sb = new StringBuilder();
        synchronized (this.mHostsMapping) {
            for (Entry<String, Fallbacks> entry : this.mHostsMapping.entrySet()) {
                sb.append((String) entry.getKey());
                sb.append(":\n");
                sb.append(((Fallbacks) entry.getValue()).toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void updateFallbacks(String host, Fallback fb) {
        if (TextUtils.isEmpty(host) || fb == null) {
            throw new IllegalArgumentException("the argument is invalid " + host + ", " + fb);
        } else if (this.sHostFilter.accept(host)) {
            synchronized (this.mHostsMapping) {
                checkHostMapping();
                if (this.mHostsMapping.containsKey(host)) {
                    ((Fallbacks) this.mHostsMapping.get(host)).addFallback(fb);
                } else {
                    Fallbacks newfbs = new Fallbacks(host);
                    newfbs.addFallback(fb);
                    this.mHostsMapping.put(host, newfbs);
                }
            }
        }
    }

    public void refreshFallbacks() {
        Throwable th;
        synchronized (this.mHostsMapping) {
            try {
                checkHostMapping();
                ArrayList<String> hosts = new ArrayList(this.mHostsMapping.keySet());
                try {
                    int i;
                    for (i = hosts.size() - 1; i >= 0; i--) {
                        Fallbacks fbs = (Fallbacks) this.mHostsMapping.get(hosts.get(i));
                        if (!(fbs == null || fbs.getFallback() == null)) {
                            hosts.remove(i);
                        }
                    }
                    ArrayList<Fallback> fbs2 = requestRemoteFallbacks(hosts);
                    for (i = 0; i < hosts.size(); i++) {
                        if (fbs2.get(i) != null) {
                            updateFallbacks((String) hosts.get(i), (Fallback) fbs2.get(i));
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    ArrayList<String> arrayList = hosts;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    protected boolean checkHostMapping() {
        IOException e;
        Throwable th;
        Throwable t;
        synchronized (this.mHostsMapping) {
            if (hostLoaded) {
                return true;
            }
            hostLoaded = true;
            this.mHostsMapping.clear();
            BufferedReader reader = null;
            try {
                File file = new File(this.sAppContext.getFilesDir(), getProcessName());
                if (file.isFile()) {
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    try {
                        String str;
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            str = reader2.readLine();
                            if (str == null) {
                                break;
                            }
                            sb.append(str);
                        }
                        str = sb.toString();
                        if (TextUtils.isEmpty(str)) {
                            reader = reader2;
                        } else {
                            fromJSON(str);
                            Log.v(TAG, "loading the new hosts succeed");
                            if (reader2 != null) {
                                try {
                                    reader2.close();
                                } catch (IOException e2) {
                                }
                            }
                            return true;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        reader = reader2;
                        try {
                            e.printStackTrace();
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException e4) {
                                }
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        reader = reader2;
                        if (reader != null) {
                            reader.close();
                        }
                        throw th;
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e6) {
                    }
                }
            } catch (IOException e7) {
                e = e7;
                e.printStackTrace();
                if (reader != null) {
                    reader.close();
                }
                return false;
            } catch (Throwable th4) {
                t = th4;
                t.printStackTrace();
                if (reader != null) {
                    reader.close();
                }
                return false;
            }
            return false;
        }
    }

    public static void addReservedHost(String host, String fallback) {
        ArrayList<String> reservedHosts = (ArrayList) mReservedHosts.get(host);
        synchronized (mReservedHosts) {
            if (reservedHosts == null) {
                try {
                    ArrayList<String> reservedHosts2 = new ArrayList();
                    try {
                        reservedHosts2.add(fallback);
                        mReservedHosts.put(host, reservedHosts2);
                        reservedHosts = reservedHosts2;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        reservedHosts = reservedHosts2;
                        throw th2;
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            } else if (!reservedHosts.contains(fallback)) {
                reservedHosts.add(fallback);
            }
        }
    }

    public void persist() {
        purge();
        synchronized (this.mHostsMapping) {
            try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.sAppContext.openFileOutput(getProcessName(), 0)));
                String jsonStr = toJSON().toString();
                if (!TextUtils.isEmpty(jsonStr)) {
                    bw.write(jsonStr);
                }
                bw.close();
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
        List<RunningAppProcessInfo> infos = ((ActivityManager) this.sAppContext.getSystemService("activity")).getRunningAppProcesses();
        if (infos != null) {
            for (RunningAppProcessInfo info : infos) {
                if (info.pid == Process.myPid()) {
                    return info.processName;
                }
            }
        }
        return "com.xiaomi";
    }

    public static boolean hasNetwork(Context context) {
        return getActiveNetworkType(context) >= 0;
    }

    private static int getActiveNetworkType(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
            if (cm == null) {
                return -1;
            }
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.getType();
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public static <T> String join(Collection<T> elements, String seperator) {
        if (elements == null || elements.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(seperator);
            }
        }
        return sb.toString();
    }

    public static String join(String[] elements, String seperator) {
        if (elements == null || elements.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(elements[0]);
        for (int i = 1; i < elements.length; i++) {
            sb.append(seperator);
            sb.append(elements[i]);
        }
        return sb.toString();
    }

    public ArrayList<HttpApi> generateHostStats() throws JSONException {
        ArrayList<HttpApi> result;
        synchronized (this.mHostsMapping) {
            Map<String, HttpApi> ISPToRecord = new HashMap();
            for (String host : this.mHostsMapping.keySet()) {
                Fallbacks fbs = (Fallbacks) this.mHostsMapping.get(host);
                if (fbs != null) {
                    Iterator it = fbs.getFallbacks().iterator();
                    while (it.hasNext()) {
                        Fallback fb = (Fallback) it.next();
                        HttpApi httpApi = (HttpApi) ISPToRecord.get(fb.getISP());
                        if (httpApi == null) {
                            httpApi = new HttpApi();
                            httpApi.setCategory("httpapi");
                            httpApi.setClient_ip(fb.ip);
                            httpApi.setNetwork(processNetwork(fb.networkLabel));
                            httpApi.setUuid(this.sUserId);
                            httpApi.setVersion(sAppVersion);
                            httpApi.setVersion_type(sAppName);
                            httpApi.setApp_name(this.sAppContext.getPackageName());
                            httpApi.setApp_version(getVersionName());
                            Location location = new Location();
                            location.setCity(fb.city);
                            location.setContry(fb.country);
                            location.setProvince(fb.province);
                            location.setIsp(fb.isp);
                            httpApi.setLocation(location);
                            ISPToRecord.put(fb.getISP(), httpApi);
                        }
                        HostInfo fallbackRecord = new HostInfo();
                        fallbackRecord.setHost(fb.host);
                        List<LandNodeInfo> hostsInfos = new ArrayList();
                        Iterator it2 = fb.getWeightedHost().iterator();
                        while (it2.hasNext()) {
                            WeightedHost wh = (WeightedHost) it2.next();
                            ArrayList<AccessHistory> ahs = wh.getUnTouchedAccessHistory();
                            if (!ahs.isEmpty()) {
                                LandNodeInfo info = new LandNodeInfo();
                                info.setIp(wh.host);
                                int succeedCnt = 0;
                                int failedCnt = 0;
                                long duration = 0;
                                int totalSize = 0;
                                Map<String, Integer> exptsCnt = new HashMap();
                                Iterator i$ = ahs.iterator();
                                while (i$.hasNext()) {
                                    AccessHistory ah = (AccessHistory) i$.next();
                                    if (ah.getWeight() >= 0) {
                                        succeedCnt++;
                                        duration += ah.getCost();
                                        totalSize = (int) (((long) totalSize) + ah.getSize());
                                    } else {
                                        String except = ah.getException();
                                        if (!TextUtils.isEmpty(except)) {
                                            exptsCnt.put(except, Integer.valueOf(exptsCnt.containsKey(except) ? ((Integer) exptsCnt.get(except)).intValue() + 1 : 1));
                                        }
                                        failedCnt++;
                                    }
                                }
                                info.setExp_info(exptsCnt);
                                info.setSuccess_count(succeedCnt);
                                info.setFailed_count(failedCnt);
                                info.setDuration(duration);
                                info.setSize(totalSize);
                                hostsInfos.add(info);
                            }
                        }
                        if (!hostsInfos.isEmpty()) {
                            fallbackRecord.setLand_node_info(hostsInfos);
                            httpApi.addToHost_info(fallbackRecord);
                        }
                    }
                    continue;
                }
            }
            result = new ArrayList();
            for (HttpApi api : ISPToRecord.values()) {
                if (api.getHost_infoSize() > 0) {
                    result.add(api);
                }
            }
        }
        return result;
    }

    private String processNetwork(String networkLabel) {
        if (TextUtils.isEmpty(networkLabel)) {
            return "unknown";
        }
        if (networkLabel.startsWith("WIFI")) {
            return "WIFI";
        }
        return networkLabel;
    }

    private String getVersionName() {
        try {
            PackageInfo info = this.sAppContext.getPackageManager().getPackageInfo(this.sAppContext.getPackageName(), 16384);
            if (info != null) {
                return info.versionName;
            }
        } catch (Exception e) {
        }
        return "0";
    }

    public void purge() {
        synchronized (this.mHostsMapping) {
            for (Fallbacks fbs : this.mHostsMapping.values()) {
                fbs.purge();
            }
            boolean done = false;
            while (!done) {
                done = true;
                for (String host : this.mHostsMapping.keySet()) {
                    if (((Fallbacks) this.mHostsMapping.get(host)).getFallbacks().isEmpty()) {
                        this.mHostsMapping.remove(host);
                        done = false;
                        break;
                    }
                }
            }
        }
    }

    public String getCurrentISP() {
        return this.currentISP;
    }

    public void setCurrentISP(String currentISP) {
        this.currentISP = currentISP;
    }

    public void updateHostsMapping(Map<String, Fallbacks> mapping) {
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
            this.mHostsMapping.putAll(mapping);
        }
    }

    private JSONArray toJSON() throws JSONException {
        JSONArray jsonArray;
        synchronized (this.mHostsMapping) {
            jsonArray = new JSONArray();
            for (Fallbacks fbs : this.mHostsMapping.values()) {
                jsonArray.put(fbs.toJSON());
            }
        }
        return jsonArray;
    }

    private void fromJSON(String jsonStr) throws JSONException {
        synchronized (this.mHostsMapping) {
            this.mHostsMapping.clear();
            JSONArray ja = new JSONArray(jsonStr);
            for (int i = 0; i < ja.length(); i++) {
                Fallbacks fbs = new Fallbacks().fromJSON(ja.getJSONObject(i));
                this.mHostsMapping.put(fbs.getHost(), fbs);
            }
        }
    }
}
