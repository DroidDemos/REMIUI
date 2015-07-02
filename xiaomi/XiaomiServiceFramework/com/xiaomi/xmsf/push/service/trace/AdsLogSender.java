package com.xiaomi.xmsf.push.service.trace;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.xmsf.push.service.Constants;
import com.xiaomi.xmsf.push.service.MyLog;
import com.xiaomi.xmsf.push.service.Utils;
import com.xiaomi.xmsf.push.service.Utils.NetState;
import com.xiaomi.xmsf.push.service.trace.TraceLogCache.CacheLine;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdsLogSender implements IMiuiAdsLogSender, IAdsTraceListener {
    private static final int MESSAGE_SEND_TRACELOG = 1;
    private static final int VALUE_MAX_TRACETASK = 10;
    private static final String mCacheFileName = "logcache";
    private static AdsLogSender sInstance;
    private String mAppId;
    private String mAppToken;
    private Context mContext;
    private Handler mHandler;
    private String mImei;
    private String mIp;
    private Deque<CacheLine> mLogBuffer;
    private TraceLogCache mLogCache;
    private HashMap<String, CacheLine> mSendingMap;
    private String mUserId;

    private AdsLogSender(Context context, String appId, String appToken) {
        this.mContext = context;
        this.mIp = Utils.getLocalIPAddress();
        this.mImei = Utils.getIMEI(context);
        this.mUserId = Utils.getXiaomiUserId(context);
        this.mAppId = appId;
        this.mAppToken = appToken;
        this.mLogCache = new TraceLogCache(this.mContext.getCacheDir().getAbsolutePath() + "/" + Constants.XMSF_ALIAS_PREFIX + ":" + mCacheFileName);
        this.mSendingMap = new HashMap(100);
        this.mLogBuffer = new ArrayDeque();
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case AdsLogSender.MESSAGE_SEND_TRACELOG /*1*/:
                        AdsLogSender.this.pushTraceLogs();
                        return;
                    default:
                        return;
                }
            }
        };
        if (Utils.getNetState(context) != NetState.NONE) {
            pushCellsInCacheFile();
        }
    }

    private void pushCellsInCacheFile() {
        this.mLogBuffer.addAll(this.mLogCache.readCacheLineFromFile());
        pushTraceLogs();
    }

    public static AdsLogSender getInstance(Context context, String appId, String appToken) {
        if (sInstance == null) {
            sInstance = new AdsLogSender(context, appId, appToken);
        }
        return sInstance;
    }

    public static synchronized AdsLogSender getInstance() {
        AdsLogSender adsLogSender;
        synchronized (AdsLogSender.class) {
            adsLogSender = sInstance;
        }
        return adsLogSender;
    }

    public void onNetStateChanged() {
        if (Utils.getNetState(this.mContext) != NetState.NONE) {
            this.mIp = Utils.getLocalIPAddress();
            pushCellsInCacheFile();
        }
    }

    public void onAccountChanged() {
        this.mUserId = Utils.getXiaomiUserId(this.mContext);
    }

    public void clickTrace(TraceLog cell) {
        if (cell.adId > 0) {
            ArrayList<TraceLog> cellList = new ArrayList();
            cellList.add(cell);
            tryToSendTraceLog(cellList, Constants.ACTIONTYPE_CLICK, cell.showType);
        }
    }

    public void removeTrace(TraceLog cell) {
        if (cell.adId > 0) {
            ArrayList<TraceLog> cellList = new ArrayList();
            cellList.add(cell);
            tryToSendTraceLog(cellList, Constants.ACTIONTYPE_REMOVE, cell.showType);
        }
    }

    public void receiveTrace(TraceLog cell) {
        if (cell.adId > 0) {
            ArrayList<TraceLog> cellList = new ArrayList();
            cellList.add(cell);
            tryToSendTraceLog(cellList, Constants.ACTIONTYPE_RECEIVED, cell.showType);
        }
    }

    private void tryToSendTraceLog(ArrayList<TraceLog> cellList, String actionType, int showType) {
        try {
            String base64 = getBase64NotifyJsonString(cellList, actionType);
            CacheLine cell = new CacheLine(showType, base64, AdsSaltUtil.getMd5Digest(base64));
            if (Utils.getNetState(this.mContext) != NetState.NONE) {
                executeTrackTask(cell);
            } else {
                cache2LogFile(cell);
            }
        } catch (JSONException e) {
        }
    }

    private void pushTraceLogs() {
        if (Utils.getNetState(this.mContext) != NetState.NONE) {
            int i = 0;
            while (!this.mLogBuffer.isEmpty() && i < VALUE_MAX_TRACETASK) {
                try {
                    executeTrackTask((CacheLine) this.mLogBuffer.peek());
                    i += MESSAGE_SEND_TRACELOG;
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (!this.mLogBuffer.isEmpty()) {
                MyLog.i("Too much cache, left: " + this.mLogBuffer.size() + " for the next resend.");
                Message msg = this.mHandler.obtainMessage();
                msg.what = MESSAGE_SEND_TRACELOG;
                this.mHandler.sendMessageDelayed(msg, 3000);
            }
        }
    }

    private void executeTrackTask(CacheLine cell) {
        if (!this.mSendingMap.containsKey(cell.mMd5)) {
            this.mSendingMap.put(cell.mMd5, cell);
            new SendTraceTask(this, this.mAppId, this.mAppToken, cell).execute(new String[0]);
        }
    }

    private void cache2LogFile(CacheLine cell) {
        this.mLogCache.appendInfo(cell);
        this.mLogCache.flushFile();
    }

    public void onTraceTaskFinished(Integer status, CacheLine cell) {
        if (status.intValue() != 0) {
            cache2LogFile(cell);
        }
        this.mSendingMap.remove(cell.mMd5);
    }

    private String getBase64NotifyJsonString(ArrayList<TraceLog> cellList, String actionType) throws JSONException {
        JSONObject log = new JSONObject();
        log.put(Constants.JSON_TAG_USERID, this.mUserId);
        log.put(Constants.JSON_TAG_IMEI, AdsSaltUtil.getMd5Digest(this.mImei));
        log.put(Constants.JSON_TAG_IP, this.mIp);
        log.put(Constants.JSON_TAG_ACTIONTYPE, actionType);
        log.put(Constants.JSON_TAG_ACTIONTIME, System.currentTimeMillis());
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < cellList.size(); i += MESSAGE_SEND_TRACELOG) {
            TraceLog cell = (TraceLog) cellList.get(i);
            JSONObject json = null;
            if (cell.content == null || TextUtils.isEmpty(cell.content)) {
                json = new JSONObject();
            } else {
                try {
                    json = new JSONObject(cell.content);
                } catch (Exception e) {
                    MyLog.e("content \u4e0d\u662fjson\u4e32");
                }
            }
            if (json != null) {
                json.put(Constants.JSON_TAG_ADID, ((TraceLog) cellList.get(i)).adId);
                jsonArray.put(json);
            }
        }
        log.put(Constants.JSON_TAG_ADLIST, jsonArray);
        return Base64.encodeToString(log.toString().getBytes(), 2);
    }

    public void release() {
        this.mHandler.removeMessages(MESSAGE_SEND_TRACELOG);
        for (CacheLine c : this.mSendingMap.values()) {
            this.mLogCache.appendInfo(c);
        }
        this.mLogCache.flushFile();
    }
}
