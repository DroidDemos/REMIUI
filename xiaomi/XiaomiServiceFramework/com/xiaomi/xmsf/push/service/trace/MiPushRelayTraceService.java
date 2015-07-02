package com.xiaomi.xmsf.push.service.trace;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.xmsf.push.service.Constants;
import com.xiaomi.xmsf.push.service.MyLog;
import java.net.URL;
import org.apache.thrift.transport.TTransportException;

public class MiPushRelayTraceService extends IntentService {
    public MiPushRelayTraceService() {
        super("TraceLogService");
    }

    protected void onHandleIntent(Intent intent) {
        IMiuiAdsLogSender sLogSender = AdsLogSender.getInstance();
        if (intent != null && sLogSender != null) {
            int intentType = intent.getIntExtra(Constants.INTENT_FLAG_TYPE, -1);
            TraceLog tcell = new TraceLog();
            tcell.adId = intent.getLongExtra(Constants.JSON_TAG_ID, 0);
            tcell.showType = intent.getIntExtra(Constants.JSON_TAG_SHOWTYPE, 0);
            tcell.content = "";
            switch (intentType) {
                case TTransportException.NOT_OPEN /*1*/:
                    sLogSender.removeTrace(tcell);
                    return;
                case TTransportException.ALREADY_OPEN /*2*/:
                case TTransportException.TIMED_OUT /*3*/:
                    String actionUrl = intent.getStringExtra(Constants.JSON_TAG_ACTION_URL);
                    String adsType = intent.getStringExtra(Constants.JSON_TAG_ADSTYPE);
                    tcell.content = adsType;
                    sLogSender.clickTrace(tcell);
                    if (Constants.TYPE_ADS_WEB.equals(adsType)) {
                        if (!TextUtils.isEmpty(actionUrl)) {
                            try {
                                Intent dataIntent = new Intent("android.intent.action.VIEW");
                                dataIntent.addFlags(268435456);
                                dataIntent.setData(Uri.parse(actionUrl));
                                startActivity(dataIntent);
                                return;
                            } catch (Throwable e) {
                                MyLog.e(e);
                                return;
                            }
                        }
                        return;
                    } else if (Constants.TYPE_ADS_APP.equals(adsType)) {
                        if (!TextUtils.isEmpty(actionUrl)) {
                            try {
                                String protocol = new URL(actionUrl).getProtocol();
                                if ("http".equalsIgnoreCase(protocol) || "https".equalsIgnoreCase(protocol)) {
                                    ((DownloadManager) getSystemService("download")).enqueue(new Request(Uri.parse(actionUrl)));
                                    return;
                                }
                                return;
                            } catch (Throwable e2) {
                                MyLog.e(e2);
                                return;
                            }
                        }
                        return;
                    } else if (Constants.TYPE_ADS_OPEN.equals(adsType) && !TextUtils.isEmpty(actionUrl)) {
                        try {
                            Intent appIntent = Intent.parseUri(actionUrl, 1);
                            appIntent.addFlags(268435456);
                            startActivity(appIntent);
                            return;
                        } catch (Throwable e22) {
                            MyLog.e(e22);
                            return;
                        }
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
