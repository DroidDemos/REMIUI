package com.xiaomi.xmsf.push.service;

import android.content.Context;
import android.os.AsyncTask;
import com.xiaomi.xmsf.push.service.BusinessFilter.FilterRet;
import com.xiaomi.xmsf.push.service.data.BusinessMessage;
import com.xiaomi.xmsf.push.service.data.BusinessNotification;
import org.apache.thrift.transport.TTransportException;
import org.json.JSONException;
import org.json.JSONObject;

class BusinessMessageParser extends AsyncTask<String, Integer, Integer> {
    private Callback mAdsListener;
    private BusinessMessage mCell;
    private Context mContext;
    private String mJson;

    public interface Callback {
        void onAdsReceived(int i, BusinessMessage businessMessage);
    }

    public BusinessMessageParser(Context context, String adsJsonString, Callback listener) {
        this.mContext = context;
        this.mAdsListener = listener;
        this.mJson = adsJsonString;
    }

    protected Integer doInBackground(String... params) {
        try {
            FilterRet ret = BusinessFilter.filter(new JSONObject(this.mJson), this.mContext);
            if (ret.adsStatusCode == 0) {
                switch (ret.adsType) {
                    case TTransportException.ALREADY_OPEN /*2*/:
                        this.mCell = new BusinessNotification(ret.adsToBeDelivered);
                        break;
                    default:
                        this.mCell = new BusinessMessage(ret.adsToBeDelivered);
                        break;
                }
            }
            return Integer.valueOf(ret.adsStatusCode);
        } catch (JSONException e) {
            e.printStackTrace();
            return Integer.valueOf(-1);
        }
    }

    protected void onPostExecute(Integer statusCode) {
        super.onPostExecute(statusCode);
        if (this.mAdsListener != null) {
            this.mAdsListener.onAdsReceived(statusCode.intValue(), this.mCell);
        }
    }
}
