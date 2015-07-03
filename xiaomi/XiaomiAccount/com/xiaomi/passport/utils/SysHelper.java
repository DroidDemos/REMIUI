package com.xiaomi.passport.utils;

import android.accounts.Account;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import com.xiaomi.account.R;
import com.xiaomi.passport.Constants;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class SysHelper {
    private static final String FALSE = "false";
    private static final String TAG = "SysHelper";

    public static class IntentSpan extends ClickableSpan {
        private Context mContext;
        private Intent mIntent;

        public IntentSpan(Context context, Intent intent) {
            this.mContext = context;
            this.mIntent = intent;
        }

        public void onClick(View widget) {
            this.mContext.startActivity(this.mIntent);
        }
    }

    private static boolean atLeast2True(boolean b1, boolean b2, boolean b3) {
        return (b1 && b2) || ((b1 && b3) || (b2 && b3));
    }

    public static boolean checkPasswordPattern(String password) {
        if (password == null) {
            return false;
        }
        int len = password.length();
        if (len < 8 || len > 16) {
            return false;
        }
        boolean hasAlpha = false;
        boolean hasDigit = false;
        boolean hasOther = false;
        for (int i = 0; i < len; i++) {
            char ch = password.charAt(i);
            if (ch < ' ' || ch > '~') {
                return false;
            }
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                hasAlpha = true;
            } else if (ch < '0' || ch > '9') {
                hasOther = true;
            } else {
                hasDigit = true;
            }
        }
        return atLeast2True(hasAlpha, hasDigit, hasOther);
    }

    public static boolean isSubSyncOn(Context context, Account account, String authority) {
        return !FALSE.equals(MiAccountManager.get(context).getUserData(account, new StringBuilder().append(Constants.SUB_SYNC_PREFIX).append(authority).toString()));
    }

    public static void requestOrCancelSync(Account account, String authority, boolean syncOn) {
        if (syncOn) {
            Bundle extras = new Bundle();
            extras.putBoolean("force", true);
            ContentResolver.requestSync(account, authority, extras);
            return;
        }
        ContentResolver.cancelSync(account, authority);
    }

    public static Intent buildPreviousActivityIntent(Context context, Intent current, String actionName) {
        Intent intent = new Intent(actionName);
        intent.setPackage(context.getPackageName());
        if (!(current == null || current.getExtras() == null)) {
            intent.putExtras(current.getExtras());
        }
        intent.addFlags(67108864);
        return intent;
    }

    public static File saveAsFile(Context context, InputStream imgStream, String filename) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(imgStream);
        OutputStream os = new BufferedOutputStream(context.openFileOutput(filename, 0));
        byte[] buffer = new byte[1024];
        while (true) {
            try {
                int count = bis.read(buffer);
                if (count == -1) {
                    break;
                }
                os.write(buffer, 0, count);
            } finally {
                try {
                    os.flush();
                } catch (IOException e) {
                }
                try {
                    os.close();
                } catch (IOException e2) {
                }
            }
        }
        try {
            os.close();
        } catch (IOException e3) {
        }
        return context.getFileStreamPath(filename);
    }

    public static int getEditViewInputType(boolean showPassword) {
        return (showPassword ? 144 : 128) | 1;
    }

    public static void getbackPassword(Activity activity) {
        Locale locale = activity.getResources().getConfiguration().locale;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(getLocalizedPasswordRecoveryUrl(locale)));
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        activity.startActivity(intent);
    }

    public static String getQuantityStringWithUnit(long value) {
        String result = "";
        float v = (float) value;
        if (v > 1.07374184E8f) {
            return String.format("%1$.2fGB", new Object[]{Float.valueOf(((v / 1024.0f) / 1024.0f) / 1024.0f)});
        } else if (v > 104857.6f) {
            return String.format("%1$.2fMB", new Object[]{Float.valueOf((v / 1024.0f) / 1024.0f)});
        } else if (v > 0.0f) {
            return "0.1MB";
        } else {
            return "0MB";
        }
    }

    public static String addMaskToAString(String address, int startIndex, int endIndex) {
        if (address.length() <= endIndex) {
            endIndex = address.length();
        }
        if (Constants.MASK.length() + startIndex <= endIndex) {
            endIndex = startIndex + Constants.MASK.length();
        }
        endIndex = Math.min(Constants.MASK.length() + startIndex, Math.min(address.length(), endIndex));
        startIndex = Math.min(startIndex, endIndex);
        StringBuilder buffer = new StringBuilder();
        buffer.append(address.substring(0, startIndex));
        buffer.append(Constants.MASK.substring(0, endIndex - startIndex));
        buffer.append(address.substring(endIndex));
        return buffer.toString();
    }

    public static String addMaskToAPhone(String phone) {
        return addMaskToAString(phone, 3, 9);
    }

    public static String addMaskToAnEmail(String email) {
        int endIndex = email.indexOf("@");
        if (endIndex > 5) {
            endIndex--;
        }
        int startIndex = email.indexOf("@") + 2;
        return addMaskToAString(addMaskToAString(email, Math.min(2, endIndex), endIndex), startIndex, email.indexOf(".", startIndex));
    }

    public static boolean hasTelephonyFeature(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    public static void blurWallpaper(Activity activity) {
        activity.getWindow().setBackgroundDrawable(WallpaperManager.getInstance(activity).getFastDrawable());
    }

    public static void setSoftInputVisibility(Activity activity, int status) {
        ((InputMethodManager) activity.getSystemService("input_method")).toggleSoftInput(0, status);
    }

    public static void goToEmailPage(Activity activity, String url) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        intent.addFlags(8388608);
        activity.startActivity(intent);
    }

    public static void setOrientationPortrait(Activity activity) {
        activity.setRequestedOrientation(1);
        activity.setRequestedOrientation(14);
    }

    public static void displaySoftInputIfNeed(Context context, View focusedView, boolean tryDisplay) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        if (tryDisplay && context.getResources().getConfiguration().orientation == 1) {
            imm.showSoftInput(focusedView, 0);
        } else {
            imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    public static void setLicense(Context context, CheckBox view) {
        String license1 = context.getString(R.string.passport_user_agreement_p1);
        String license2 = context.getString(R.string.passport_user_agreement_p2);
        String license3 = context.getString(R.string.passport_user_agreement_p3);
        String license4 = context.getString(R.string.passport_user_agreement_p4);
        SpannableStringBuilder licenseStr = new SpannableStringBuilder();
        licenseStr.append(license1).append(license2).append(license3).append(license4);
        Intent userAgreementIntent = new Intent(Constants.ACTION_VIEW_LICENSE);
        userAgreementIntent.setPackage(context.getPackageName());
        userAgreementIntent.putExtra(Constants.LICENSE_TYPE, 1);
        licenseStr.setSpan(new IntentSpan(context, userAgreementIntent), license1.length(), license1.length() + license2.length(), 33);
        licenseStr.setSpan(new ForegroundColorSpan(R.color.passport_button_text_color_light), license1.length(), license1.length() + license2.length(), 33);
        Intent privacyPolicyIntent = new Intent(Constants.ACTION_VIEW_LICENSE);
        privacyPolicyIntent.setPackage(context.getPackageName());
        privacyPolicyIntent.putExtra(Constants.LICENSE_TYPE, 0);
        licenseStr.setSpan(new IntentSpan(context, privacyPolicyIntent), (license1.length() + license2.length()) + license3.length(), licenseStr.length(), 33);
        licenseStr.setSpan(new ForegroundColorSpan(R.color.passport_button_text_color_light), (license1.length() + license2.length()) + license3.length(), licenseStr.length(), 33);
        view.setText(licenseStr);
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void replaceToFragment(Activity activity, Fragment f, boolean clearBottom) {
        replaceToFragment(activity, f, clearBottom, 16908290);
    }

    public static void replaceToFragment(Activity activity, Fragment f, boolean clearBottom, int containerViewId) {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (clearBottom) {
            int count = fm.getBackStackEntryCount();
            for (int i = 0; i < count; i++) {
                fm.popBackStack();
            }
        }
        transaction.setTransition(4099);
        transaction.replace(containerViewId, f);
        if (!clearBottom) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    private static String getLocalizedPasswordRecoveryUrl(Locale locale) {
        if (locale == null) {
            return Constants.PASSWORD_RECOVERY_URL;
        }
        String localeStringInUrl;
        String localeString = locale.toString();
        if (localeString.equals(Locale.CHINA.toString())) {
            localeStringInUrl = "zh_CN";
        } else if (localeString.equals(Locale.TAIWAN.toString())) {
            localeStringInUrl = "zh_TW";
        } else {
            localeStringInUrl = "en_US";
        }
        return String.format("%s?_locale=%s", new Object[]{Constants.PASSWORD_RECOVERY_URL, localeStringInUrl});
    }
}
