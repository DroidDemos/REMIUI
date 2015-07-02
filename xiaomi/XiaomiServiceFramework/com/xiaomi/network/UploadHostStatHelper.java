package com.xiaomi.network;

import android.content.Context;
import android.util.Log;
import com.xiaomi.channel.commonutils.misc.DateTimeHelper;
import com.xiaomi.common.logger.thrift.Common;
import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import com.xiaomi.common.logger.thrift.mfs.HttpLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TCompactProtocol.Factory;

public class UploadHostStatHelper {
    private static final int HOST_STAT_DELAY = 60000;
    public static final String SALT = "56C6A520%$C99119A0&^229(!@2746C7";
    public static final String TAG = "uploadhoststat";
    @Deprecated
    public static final String UPLOAD_HOST = "f3.mi-stat.gslb.mi-idc.com";
    public static final String UPLOAD_HOST_NEW = "f3.mi-stat.gslb.mi-idc.com";
    public static final int UPLOAD_RATIO_MULTIPLE = 10000;
    public static final String UPLOAD_URL = "http://%1$s/diagnoses/v1/report";
    private static UploadHostStatHelper sInstance;
    private List<HttpRecordCallback> callbacks;
    private final Random mRandomGenerator;
    private boolean mTaskPending;
    private Timer mTimer;
    private Context sAppContext;

    public interface HttpRecordCallback {
        List<HttpApi> generateStat();

        double getPercentage();
    }

    private UploadHostStatHelper(Context context) {
        this.callbacks = new ArrayList();
        this.mRandomGenerator = new Random();
        this.mTimer = new Timer("Upload Http Record Timer");
        this.mTaskPending = false;
        this.sAppContext = null;
        this.sAppContext = context.getApplicationContext();
    }

    public static synchronized UploadHostStatHelper getInstance() {
        UploadHostStatHelper uploadHostStatHelper;
        synchronized (UploadHostStatHelper.class) {
            uploadHostStatHelper = sInstance;
        }
        return uploadHostStatHelper;
    }

    public static synchronized void init(Context context) {
        synchronized (UploadHostStatHelper.class) {
            if (sInstance == null) {
                sInstance = new UploadHostStatHelper(context);
            }
        }
    }

    public void fireHostEvent() {
        if (!this.mTaskPending) {
            this.mTaskPending = true;
            this.mTimer.schedule(new TimerTask() {
                public void run() {
                    for (HttpRecordCallback callback : UploadHostStatHelper.this.callbacks) {
                        List<HttpApi> httpApis = callback.generateStat();
                        double percent = callback.getPercentage();
                        if (httpApis != null) {
                            try {
                                if (httpApis.size() > 0) {
                                    UploadHostStatHelper.this.uploadHostStat((List) httpApis, percent);
                                }
                            } catch (TException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    UploadHostStatHelper.this.mTaskPending = false;
                }
            }, DateTimeHelper.sMinuteInMilliseconds);
        }
    }

    private void uploadHostStat(List<HttpApi> httpApis, double percent) throws TException {
        for (HttpApi api : httpApis) {
            HttpLog httpLog = new HttpLog();
            httpLog.setCategory("httpapi");
            httpLog.setHttpApi(api);
            httpLog.setCommon(new Common());
            String stat = new String(Base64Coder.encode(new TSerializer(new Factory()).serialize(httpLog)));
            if (((double) this.mRandomGenerator.nextInt(UPLOAD_RATIO_MULTIPLE)) < 10000.0d * percent) {
                try {
                    uploadHostStat(UPLOAD_HOST_NEW, stat);
                } catch (IOException e) {
                    Log.e(TAG, null, e);
                } catch (Exception e2) {
                    Log.e(TAG, null, e2);
                }
            }
        }
    }

    private void uploadHostStat(String host, String stat) throws IOException {
        List<NameValuePair> nameValuePairs = new ArrayList();
        String nonce = String.valueOf(System.nanoTime());
        String ts = String.valueOf(System.currentTimeMillis());
        nameValuePairs.add(new BasicNameValuePair("n", nonce));
        nameValuePairs.add(new BasicNameValuePair("d", stat));
        nameValuePairs.add(new BasicNameValuePair("t", ts));
        nameValuePairs.add(new BasicNameValuePair("s", getMd5Digest(nonce + stat + ts + SALT)));
        Network.doHttpPost(this.sAppContext, String.format(UPLOAD_URL, new Object[]{host}), nameValuePairs);
    }

    private String getMd5Digest(String pInput) {
        try {
            MessageDigest lDigest = MessageDigest.getInstance("MD5");
            lDigest.update(getBytes(pInput));
            BigInteger lHashInt = new BigInteger(1, lDigest.digest());
            return String.format("%1$032X", new Object[]{lHashInt});
        } catch (NoSuchAlgorithmException lException) {
            throw new RuntimeException(lException);
        }
    }

    private byte[] getBytes(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s.getBytes();
        }
    }

    public void addCallBack(HttpRecordCallback callback) {
        this.callbacks.add(callback);
    }

    public void removeCallBack(HttpRecordCallback callback) {
        this.callbacks.remove(callback);
    }

    public void removeAllCallBack() {
        this.callbacks.clear();
    }
}
