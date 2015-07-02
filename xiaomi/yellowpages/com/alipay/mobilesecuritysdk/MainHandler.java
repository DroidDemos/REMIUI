package com.alipay.mobilesecuritysdk;

import android.content.Context;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.datainfo.GeoResponseInfo;
import com.alipay.mobilesecuritysdk.datainfo.SdkConfig;
import com.alipay.mobilesecuritysdk.datainfo.UploadInfo;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.mobilesecuritysdk.model.CollectedInfo;
import com.alipay.mobilesecuritysdk.model.DataProfile;
import com.alipay.mobilesecuritysdk.model.Upload;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.io.File;
import java.util.List;

public class MainHandler {
    public int mainhandler(Context context, List list, boolean z) {
        if (!z) {
            return 1;
        }
        DataProfile dataProfile = new DataProfile();
        Upload upload = new Upload(context);
        UploadInfo uploadInfo = new UploadInfo();
        CollectedInfo collectedInfo = new CollectedInfo();
        try {
            if (CommonUtils.isBlankCollection(list)) {
                if (SecurityClientMobile.isDebug()) {
                    Log.i(ConfigConstant.LOG_TAG, "tid is empty, quit!");
                }
                return 1;
            }
            long currentTimeMillis = System.currentTimeMillis();
            SdkConfig configs = dataProfile.getConfigs(context.getFilesDir().getPath());
            if (configs == null) {
                if (SecurityClientMobile.isDebug()) {
                    Log.i(ConfigConstant.LOG_TAG, "loadConfig is null");
                }
                return 1;
            } else if (Thread.currentThread().isInterrupted()) {
                return 1;
            } else {
                if (CommonUtils.outOfDate(configs.getMainSwitchLUT(), ConfigConstant.MAIN_SWITCH_INTERVAL_UINT, configs.getMainSwitchInterval())) {
                    GeoResponseInfo communicateSwitch = upload.communicateSwitch();
                    if (communicateSwitch != null && communicateSwitch.isSuccess()) {
                        if (!CommonUtils.isBlank(communicateSwitch.getMainSwitchState())) {
                            if (SecurityClientMobile.isDebug()) {
                                Log.i(ConfigConstant.LOG_TAG, "main switch updated.");
                            }
                            if (CommonUtils.equalsIgnoreCase(communicateSwitch.getMainSwitchState(), ConfigConstant.MAIN_SWITCH_STATE_ON)) {
                                configs.setMainSwitchState(ConfigConstant.MAIN_SWITCH_STATE_ON);
                            } else {
                                configs.setMainSwitchState(ConfigConstant.MAIN_SWITCH_STATE_OFF);
                            }
                        }
                        configs.setMainSwitchLUT(currentTimeMillis);
                        dataProfile.saveConfigs(configs, new StringBuilder(String.valueOf(context.getFilesDir().getPath())).append(File.separator).append(ConfigConstant.CONFIG_FILENAME).toString());
                    }
                }
                if (Thread.currentThread().isInterrupted()) {
                    return 1;
                }
                if (CommonUtils.equalsIgnoreCase(ConfigConstant.MAIN_SWITCH_STATE_ON, configs.getMainSwitchState())) {
                    if (CommonUtils.outOfDate(configs.getLocateLUT(), ConfigConstant.LOCATE_INTERVAL_UINT, configs.getLocateInterval())) {
                        List collectLocateInfos = collectedInfo.collectLocateInfos(context);
                        if (collectLocateInfos != null && collectLocateInfos.size() > 0) {
                            if (SecurityClientMobile.isDebug()) {
                                Log.i(ConfigConstant.LOG_TAG, "location collected.");
                            }
                            uploadInfo.setLocates(collectLocateInfos);
                            configs.setLocateLUT(currentTimeMillis);
                        }
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        return 1;
                    }
                    if (CommonUtils.outOfDate(configs.getAppLUT(), ConfigConstant.MAIN_SWITCH_INTERVAL_UINT, configs.getAppInterval())) {
                        List collectappInfos = collectedInfo.collectappInfos(context);
                        if (collectappInfos != null && collectappInfos.size() > 0) {
                            if (SecurityClientMobile.isDebug()) {
                                Log.i(ConfigConstant.LOG_TAG, "app info collected.");
                            }
                            uploadInfo.setAppinfos(collectappInfos);
                            configs.setAppLUT(currentTimeMillis);
                        }
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        return 1;
                    }
                    upload.setInfo(uploadInfo);
                    GeoResponseInfo uploadData = upload.uploadData(list, configs);
                    if (uploadData != null && uploadData.isSuccess()) {
                        if (SecurityClientMobile.isDebug()) {
                            Log.i(ConfigConstant.LOG_TAG, "data have been upload.");
                        }
                        if (uploadData.getMainSwitchInterval() > 0) {
                            configs.setMainSwitchInterval(uploadData.getMainSwitchInterval());
                        }
                        if (uploadData.getLocateInterval() > 0) {
                            configs.setLocateInterval(uploadData.getLocateInterval());
                        }
                        if (uploadData.getAppInterval() > 0) {
                            configs.setAppInterval(uploadData.getAppInterval());
                        }
                        if (uploadData.getLocationMaxLines() > 0) {
                            configs.setLocationMaxLines(uploadData.getLocationMaxLines());
                        }
                        dataProfile.cleanUploadFiles(context.getFilesDir().getPath());
                    }
                    dataProfile.saveConfigs(configs, new StringBuilder(String.valueOf(context.getFilesDir().getPath())).append(File.separator).append(ConfigConstant.CONFIG_FILENAME).toString());
                    return 0;
                }
                if (SecurityClientMobile.isDebug()) {
                    Log.i(ConfigConstant.LOG_TAG, "main switch is off, quit!");
                }
                return 0;
            }
        } catch (Exception e) {
            return 1;
        }
    }
}
