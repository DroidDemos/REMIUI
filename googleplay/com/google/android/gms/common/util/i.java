package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.wallet.instrumentmanager.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class i {
    private static final Pattern XJ;
    private static final Pattern XK;

    static {
        XJ = Pattern.compile("\\\\.");
        XK = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    public static String bp(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = XK.matcher(str);
        StringBuffer stringBuffer = null;
        while (matcher.find()) {
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            switch (matcher.group().charAt(0)) {
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    matcher.appendReplacement(stringBuffer, "\\\\b");
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    matcher.appendReplacement(stringBuffer, "\\\\t");
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    matcher.appendReplacement(stringBuffer, "\\\\n");
                    break;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    matcher.appendReplacement(stringBuffer, "\\\\f");
                    break;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    matcher.appendReplacement(stringBuffer, "\\\\r");
                    break;
                case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                    matcher.appendReplacement(stringBuffer, "\\\\\\\"");
                    break;
                case com.google.android.play.R.styleable.Theme_selectableItemBackground /*47*/:
                    matcher.appendReplacement(stringBuffer, "\\\\/");
                    break;
                case '\\':
                    matcher.appendReplacement(stringBuffer, "\\\\\\\\");
                    break;
                default:
                    break;
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
