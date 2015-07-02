package com.google.android.play.image;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.google.android.play.utils.config.PlayG;
import com.google.android.wallet.instrumentmanager.R;

public class FifeUrlUtils {
    private static boolean WEBP_ENABLED;
    private static NetworkInfoCache sNetworkInfoCacheInstance;

    public interface NetworkInfoCache {
        NetworkInfo getNetworkInfo(Context context);
    }

    static {
        boolean z = ((Boolean) PlayG.webpFifeImagesEnabled.get()).booleanValue() && VERSION.SDK_INT >= 17;
        WEBP_ENABLED = z;
    }

    public static void setNetworkInfoCacheInstance(NetworkInfoCache networkInfoCache) {
        sNetworkInfoCacheInstance = networkInfoCache;
    }

    private static NetworkInfo getNetworkInfo(Context context) {
        return sNetworkInfoCacheInstance != null ? sNetworkInfoCacheInstance.getNetworkInfo(context) : null;
    }

    public static float getNetworkScaleFactor(Context context) {
        NetworkInfo currentNetworkInfo = getNetworkInfo(context);
        float scaleFactor = ((Float) PlayG.percentOfImageSize3G.get()).floatValue();
        if (currentNetworkInfo == null) {
            return scaleFactor;
        }
        if (currentNetworkInfo.getType() != 1) {
            if (currentNetworkInfo.getType() == 0) {
                switch (currentNetworkInfo.getSubtype()) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    case R.styleable.WalletImFormEditText_required /*4*/:
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    case R.styleable.MapAttrs_uiZoomControls /*11*/:
                        scaleFactor = ((Float) PlayG.percentOfImageSize2G.get()).floatValue();
                        break;
                    case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                        scaleFactor = ((Float) PlayG.percentOfImageSize4G.get()).floatValue();
                        break;
                    default:
                        scaleFactor = ((Float) PlayG.percentOfImageSize3G.get()).floatValue();
                        break;
                }
            }
        }
        scaleFactor = ((Float) PlayG.percentOfImageSizeWifi.get()).floatValue();
        return scaleFactor;
    }

    public static String buildFifeUrl(String url, int desiredWidth, int desiredHeight) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        StringBuilder options = new StringBuilder();
        if (WEBP_ENABLED) {
            options.append("rw");
        }
        if (desiredWidth > 0) {
            if (options.length() != 0) {
                options.append('-');
            }
            options.append('w').append(desiredWidth);
        }
        if (desiredHeight > 0) {
            if (options.length() != 0) {
                options.append('-');
            }
            options.append('h').append(desiredHeight);
        }
        return options.length() != 0 ? addFifeOptions(url, options) : url;
    }

    private static String addFifeOptions(String url, StringBuilder options) {
        String path = Uri.parse(url).getEncodedPath();
        if (path.length() <= 1 || path.indexOf(47, 1) <= 0 || url.endsWith("?fife")) {
            return "=" + options;
        }
        options.insert(0, "/");
        return url.lastIndexOf("/");
    }
}
