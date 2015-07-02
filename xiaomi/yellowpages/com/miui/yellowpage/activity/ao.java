package com.miui.yellowpage.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.base.exception.NetworkUnavailableException;
import com.miui.yellowpage.base.exception.ServerException;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Network;
import java.net.UnknownServiceException;

/* compiled from: BaseActivity */
class ao implements Runnable {
    final /* synthetic */ BaseActivity ti;

    private ao(BaseActivity baseActivity) {
        this.ti = baseActivity;
    }

    public void run() {
        Log.d("BaseActivity", "banner location:" + this.ti.getBannerLocation());
        String str = HostManager.getActivityBannerUrl() + this.ti.getBannerLocation();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.ti);
        aa bl = aa.bl(defaultSharedPreferences.getString(str, ConfigConstant.WIRELESS_FILENAME));
        if (bl.fc() || !Network.isNetWorkConnected(this.ti)) {
            Log.d("BaseActivity", "cache is refresh");
        } else {
            HttpRequest httpRequest = new HttpRequest(this.ti, HostManager.getActivityBannerUrl(), 0);
            httpRequest.addParam("activity", this.ti.getBannerLocation());
            httpRequest.setRequireLocId(true);
            try {
                String requestServer = httpRequest.requestServer();
                Log.d("BaseActivity", "result:" + requestServer);
                bl = aa.bl(requestServer);
                defaultSharedPreferences.edit().putString(str, bl.toString()).commit();
            } catch (NetworkUnavailableException e) {
                e.printStackTrace();
            } catch (ServerException e2) {
                e2.printStackTrace();
            } catch (UnknownServiceException e3) {
                e3.printStackTrace();
            }
        }
        if (bl.fb()) {
            this.ti.runOnUiThread(new G(this.ti, bl));
        }
    }
}
