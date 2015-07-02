package com.alipay.mobilesecuritysdk.deviceID;

import android.content.Context;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalDefine;
import com.alipay.sdk.cons.MiniDefine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeviceIdManager {
    public static Map strMap;
    private final Context mcontext;

    static {
        strMap = null;
    }

    public DeviceIdManager(Context context) {
        this.mcontext = context;
        LOG.init(context);
    }

    public String GetApDid(Map map) {
        Throwable th;
        List arrayList;
        String str = null;
        UpdateLog();
        try {
            DeviceIdModel deviceIdModel = new DeviceIdModel();
            strMap = deviceIdModel.GetPrivateData(this.mcontext);
            List arrayList2;
            if (strMap == null) {
                arrayList2 = new ArrayList();
                if (map.get(MiniDefine.ak) != null && ((String) map.get(MiniDefine.ak)).length() > 20) {
                    arrayList2.add(((String) map.get(MiniDefine.ak)).substring(0, 20));
                }
                if (map.get(GlobalDefine.l) != null && ((String) map.get(GlobalDefine.l)).length() > 20) {
                    arrayList2.add(((String) map.get(GlobalDefine.l)).substring(0, 20));
                }
                arrayList2.add("model.GetPrivateData(mcontext)  strMap is null");
                LOG.logMessage(arrayList2);
                Update(this.mcontext, map);
                return null;
            }
            String str2;
            if (deviceIdModel.CheckPrivateData(strMap)) {
                str2 = (String) strMap.get(DeviceIdModel.mDeviceId);
                try {
                    arrayList2 = new ArrayList();
                    if (map.get(MiniDefine.ak) != null && ((String) map.get(MiniDefine.ak)).length() > 20) {
                        arrayList2.add(((String) map.get(MiniDefine.ak)).substring(0, 20));
                    }
                    if (map.get(GlobalDefine.l) != null && ((String) map.get(GlobalDefine.l)).length() > 20) {
                        arrayList2.add(((String) map.get(GlobalDefine.l)).substring(0, 20));
                    }
                    arrayList2.add("GetApDid  deviceID is " + str2);
                    LOG.logMessage(arrayList2);
                } catch (Throwable e) {
                    th = e;
                    str = str2;
                    arrayList = new ArrayList();
                    arrayList.add(((String) map.get(MiniDefine.ak)).substring(0, 20));
                    arrayList.add(((String) map.get(GlobalDefine.l)).substring(0, 20));
                    arrayList.add(LOG.getStackString(th));
                    LOG.logMessage(arrayList);
                    return str;
                }
            }
            str2 = null;
            Update(this.mcontext, map);
            return str2;
        } catch (Throwable e2) {
            th = e2;
            arrayList = new ArrayList();
            if (map.get(MiniDefine.ak) != null && ((String) map.get(MiniDefine.ak)).length() > 20) {
                arrayList.add(((String) map.get(MiniDefine.ak)).substring(0, 20));
            }
            if (map.get(GlobalDefine.l) != null && ((String) map.get(GlobalDefine.l)).length() > 20) {
                arrayList.add(((String) map.get(GlobalDefine.l)).substring(0, 20));
            }
            arrayList.add(LOG.getStackString(th));
            LOG.logMessage(arrayList);
            return str;
        }
    }

    private synchronized void Update(final Context context, final Map map) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    DeviceIdModel deviceIdModel = new DeviceIdModel();
                    deviceIdModel.Init(context, map);
                    deviceIdModel.UpdateId(context, DeviceIdManager.strMap);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    List arrayList = new ArrayList();
                    if (map.get(MiniDefine.ak) != null && ((String) map.get(MiniDefine.ak)).length() > 20) {
                        arrayList.add(((String) map.get(MiniDefine.ak)).substring(0, 20));
                    }
                    if (map.get(GlobalDefine.l) != null && ((String) map.get(GlobalDefine.l)).length() > 20) {
                        arrayList.add(((String) map.get(GlobalDefine.l)).substring(0, 20));
                    }
                    arrayList.add(LOG.getStackString(th2));
                    LOG.logMessage(arrayList);
                }
            }
        }).start();
    }

    private void UpdateLog() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    LOG.uploadLogFile();
                } catch (Throwable th) {
                    List arrayList = new ArrayList();
                    arrayList.add(ConfigConstant.WIRELESS_FILENAME);
                    arrayList.add(ConfigConstant.WIRELESS_FILENAME);
                    arrayList.add(ConfigConstant.WIRELESS_FILENAME);
                    arrayList.add(LOG.getStackString(th));
                    LOG.logMessage(arrayList);
                }
            }
        }).start();
    }
}
