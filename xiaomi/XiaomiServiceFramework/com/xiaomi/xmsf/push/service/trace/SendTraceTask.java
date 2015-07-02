package com.xiaomi.xmsf.push.service.trace;

import android.os.AsyncTask;
import com.xiaomi.xmsf.push.service.trace.TraceLogCache.CacheLine;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

class SendTraceTask extends AsyncTask<String, Integer, Integer> {
    private final String HTTP_KEY_APPID;
    private final String HTTP_KEY_LOGVALUE;
    private final String HTTP_KEY_SHOWTYPE;
    private final String HTTP_KEY_SIGNATURE;
    private String mAppId;
    private String mAppToken;
    private CacheLine mCell;
    private IAdsTraceListener mTraceListener;

    public SendTraceTask(IAdsTraceListener traceListener, String appId, String appToken, CacheLine cell) {
        this.HTTP_KEY_LOGVALUE = "logValue";
        this.HTTP_KEY_APPID = "appId";
        this.HTTP_KEY_SHOWTYPE = Constants.JSON_TAG_SHOWTYPE;
        this.HTTP_KEY_SIGNATURE = "s";
        this.mAppId = appId;
        this.mAppToken = appToken;
        this.mCell = cell;
        this.mTraceListener = traceListener;
    }

    protected Integer doInBackground(String... params) {
        UnsupportedEncodingException e;
        HttpPost httpPost;
        ClientProtocolException e2;
        IOException e3;
        List<NameValuePair> paramList = new LinkedList();
        paramList.add(new BasicNameValuePair("logValue", this.mCell.mBase64));
        paramList.add(new BasicNameValuePair("appId", this.mAppId));
        paramList.add(new BasicNameValuePair(Constants.JSON_TAG_SHOWTYPE, this.mCell.mShowType + ""));
        paramList.add(new BasicNameValuePair("s", AdsSaltUtil.getKeyFromParams(paramList, this.mAppToken)));
        try {
            HttpPost post = new HttpPost("http://new.api.ad.xiaomi.com/logNotificationAdActions");
            try {
                post.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
                if (200 == new DefaultHttpClient().execute(post).getStatusLine().getStatusCode()) {
                    return Integer.valueOf(0);
                }
            } catch (UnsupportedEncodingException e4) {
                e = e4;
                httpPost = post;
                e.printStackTrace();
                return Integer.valueOf(1);
            } catch (ClientProtocolException e5) {
                e2 = e5;
                httpPost = post;
                e2.printStackTrace();
                return Integer.valueOf(1);
            } catch (IOException e6) {
                e3 = e6;
                httpPost = post;
                e3.printStackTrace();
                return Integer.valueOf(1);
            }
        } catch (UnsupportedEncodingException e7) {
            e = e7;
            e.printStackTrace();
            return Integer.valueOf(1);
        } catch (ClientProtocolException e8) {
            e2 = e8;
            e2.printStackTrace();
            return Integer.valueOf(1);
        } catch (IOException e9) {
            e3 = e9;
            e3.printStackTrace();
            return Integer.valueOf(1);
        }
        return Integer.valueOf(1);
    }

    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        if (this.mTraceListener != null) {
            this.mTraceListener.onTraceTaskFinished(integer, this.mCell);
        }
    }

    protected void onCancelled() {
        super.onCancelled();
        if (this.mTraceListener != null) {
            this.mTraceListener.onTraceTaskFinished(Integer.valueOf(1), this.mCell);
        }
    }
}
