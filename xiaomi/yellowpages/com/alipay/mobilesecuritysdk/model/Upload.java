package com.alipay.mobilesecuritysdk.model;

import HttpUtils.HttpFetcher;
import android.content.Context;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.datainfo.GeoResponseInfo;
import com.alipay.mobilesecuritysdk.datainfo.SdkConfig;
import com.alipay.mobilesecuritysdk.datainfo.UploadInfo;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import com.alipay.sdk.cons.GlobalConstants;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class Upload {
    private UploadInfo info;
    private Context mcontext;
    private DataProfile profile;

    public UploadInfo getInfo() {
        return this.info;
    }

    public void setInfo(UploadInfo uploadInfo) {
        this.info = uploadInfo;
    }

    public Upload(Context context) {
        this.profile = new DataProfile();
        this.mcontext = context;
    }

    public GeoResponseInfo uploadData(List list, SdkConfig sdkConfig) {
        GeoResponseInfo geoResponseInfo = new GeoResponseInfo();
        if (CommonUtils.isBlankCollection(list)) {
            geoResponseInfo.setSuccess(false);
        } else {
            String AppToString;
            if (this.info.getAppinfos().size() > 0) {
                this.profile.setTid(list);
                AppToString = this.profile.AppToString(new StringBuilder(String.valueOf(this.mcontext.getFilesDir().getPath())).append(File.separator).append(ConfigConstant.APP_UPLOAD_FILENAME).toString(), this.info.getAppinfos());
                if (SecurityClientMobile.isDebug()) {
                    Log.i("str app info", AppToString);
                }
                if (AppToString != null && AppToString.length() > 0) {
                    geoResponseInfo = uploadCollectedData(ConfigConstant.SERVICE_ID, AppToString, GlobalConstants.d);
                }
                if (geoResponseInfo.isSuccess()) {
                    this.profile.cleanUploadFiles(new StringBuilder(String.valueOf(this.mcontext.getFilesDir().getPath())).append(File.separator).append(ConfigConstant.APP_UPLOAD_FILENAME).toString());
                    Log.i("app write file", "upload  suceess  delete file");
                } else {
                    try {
                        CommonUtils.WriteFile(new StringBuilder(String.valueOf(this.mcontext.getFilesDir().getPath())).append(File.separator).append(ConfigConstant.APP_UPLOAD_FILENAME).toString(), AppToString);
                    } catch (IOException e) {
                        Log.d("app write file", e.getLocalizedMessage());
                    }
                }
            }
            if (this.info.getLocates().size() > 0) {
                this.profile.setTid(list);
                AppToString = this.profile.LocationToString(new StringBuilder(String.valueOf(this.mcontext.getFilesDir().getPath())).append(File.separator).append(ConfigConstant.LOCATE_UPLOAD_FILENAME).toString(), this.info.getLocates());
                if (SecurityClientMobile.isDebug()) {
                    Log.i("str aloc info", AppToString);
                }
                if (AppToString != null && AppToString.length() > 0) {
                    geoResponseInfo = uploadCollectedData(ConfigConstant.SERVICE_ID, AppToString, GlobalConstants.d);
                }
                if (geoResponseInfo.isSuccess()) {
                    this.profile.cleanUploadFiles(new StringBuilder(String.valueOf(this.mcontext.getFilesDir().getPath())).append(File.separator).append(ConfigConstant.LOCATE_UPLOAD_FILENAME).toString());
                    Log.i("location write file", "upload  suceess  delete file");
                } else {
                    try {
                        CommonUtils.WriteFile(new StringBuilder(String.valueOf(this.mcontext.getFilesDir().getPath())).append(File.separator).append(ConfigConstant.LOCATE_UPLOAD_FILENAME).toString(), AppToString);
                    } catch (IOException e2) {
                        Log.d("location write file", e2.getLocalizedMessage());
                    }
                }
            }
        }
        return geoResponseInfo;
    }

    public GeoResponseInfo communicateSwitch() {
        GeoResponseInfo geoResponseInfo = new GeoResponseInfo();
        if (!CommonUtils.isNetWorkActive(this.mcontext)) {
            return geoResponseInfo;
        }
        try {
            HttpResponse execute = new HttpFetcher().getHttpClient().execute(new HttpGet(ConfigConstant.MAIN_SWITCH_ADDRESS));
            if (execute.getStatusLine().getStatusCode() == ConfigConstant.RESPONSE_CODE) {
                return this.profile.analysisServerRespond(EntityUtils.toString(execute.getEntity()));
            }
            geoResponseInfo.setSuccess(false);
            return geoResponseInfo;
        } catch (Exception e) {
            geoResponseInfo.setSuccess(false);
            return geoResponseInfo;
        }
    }

    public GeoResponseInfo uploadCollectedData(String str, String str2, String str3) {
        GeoResponseInfo geoResponseInfo = new GeoResponseInfo();
        try {
            HttpResponse uploadCollectedData = new HttpFetcher().uploadCollectedData(this.mcontext, ConfigConstant.DATA_POST_ADDRESS, str, str2, str3, true);
            if (uploadCollectedData != null && uploadCollectedData.getStatusLine().getStatusCode() == ConfigConstant.RESPONSE_CODE) {
                return this.profile.analysisServerRespond(EntityUtils.toString(uploadCollectedData.getEntity()));
            }
            geoResponseInfo.setSuccess(false);
            return geoResponseInfo;
        } catch (IOException e) {
            Log.i("upload data  error", e.getLocalizedMessage());
        }
    }
}
