package com.google.android.finsky.local;

import com.google.android.finsky.download.obb.Obb;
import com.google.android.finsky.download.obb.ObbFactory;
import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.protos.AndroidAppDelivery.AppFileMetadata;
import com.google.android.finsky.protos.DocDetails.AppDetails;
import com.google.android.finsky.protos.DocDetails.FileMetadata;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.wallet.instrumentmanager.R;

public class AssetUtils {
    public static String makeAssetId(AppDetails appDetails) {
        return makeAssetId(appDetails.packageName, appDetails.versionCode);
    }

    public static String makeAssetId(String packageName, int versionCode) {
        return "v2:" + packageName + ":1:" + versionCode;
    }

    public static Obb extractObb(AndroidAppDeliveryData deliveryData, String packageName, boolean extractPatch) {
        int seekFileType = extractPatch ? 1 : 0;
        for (AppFileMetadata fileData : deliveryData.additionalFile) {
            if (fileData.fileType == seekFileType) {
                return ObbFactory.create(extractPatch, packageName, fileData.versionCode, fileData.downloadUrl, fileData.size, 4);
            }
        }
        return null;
    }

    public static long totalDeliverySize(AppDetails appDetails) {
        long result = 0;
        for (FileMetadata file : appDetails.file) {
            int fileType = file.fileType;
            switch (fileType) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    result += file.size;
                    break;
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    Obb obb = ObbFactory.create(fileType == 2, appDetails.packageName, file.versionCode, null, file.size, 4);
                    if (obb.getState() != 4) {
                        break;
                    }
                    result += obb.getSize();
                    break;
                default:
                    FinskyLog.w("Bad file type %d in %s entry# %d", Integer.valueOf(fileType), appDetails.packageName, Integer.valueOf(i));
                    break;
            }
        }
        return result;
    }
}
