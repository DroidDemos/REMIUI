package miui.external;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import miui.external.SdkConstants.SdkError;

public class SdkErrorActivity extends Activity implements SdkConstants {
    private OnClickListener EY;
    private OnClickListener EZ;
    private String g;

    public SdkErrorActivity() {
        this.EY = new c(this);
        this.EZ = new a(this);
    }

    protected void onCreate(Bundle bundle) {
        Dialog hn;
        setTheme(16973909);
        super.onCreate(bundle);
        this.g = Locale.getDefault().getLanguage();
        SdkError sdkError = null;
        Intent intent = getIntent();
        if (intent != null) {
            sdkError = (SdkError) intent.getSerializableExtra("com.miui.sdk.error");
        }
        if (sdkError == null) {
            sdkError = SdkError.GENERIC;
        }
        switch (f.oP[sdkError.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                hn = hn();
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                hn = ho();
                break;
            default:
                hn = hm();
                break;
        }
        new g(this, hn).show(getFragmentManager(), "SdkErrorPromptDialog");
    }

    private Dialog a(String str, String str2, OnClickListener onClickListener) {
        return new Builder(this).setTitle(str).setMessage(str2).setPositiveButton(17039370, onClickListener).setIcon(17301543).setCancelable(false).create();
    }

    private Dialog a(String str, String str2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new Builder(this).setTitle(str).setMessage(str2).setPositiveButton(17039370, onClickListener).setNegativeButton(17039360, onClickListener2).setIcon(17301543).setCancelable(false).create();
    }

    private Dialog hm() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "MIUI SDK\u53d1\u751f\u9519\u8bef";
            str2 = "\u8bf7\u91cd\u65b0\u5b89\u88c5MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
        } else {
            str = "MIUI SDK encounter errors";
            str2 = "Please re-install MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.EY);
    }

    private Dialog hn() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "\u6ca1\u6709\u627e\u5230MIUI SDK";
            str2 = "\u8bf7\u5148\u5b89\u88c5MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
        } else {
            str = "MIUI SDK not found";
            str2 = "Please install MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.EY);
    }

    private Dialog ho() {
        String str;
        String str2;
        if (g()) {
            if (Locale.CHINESE.getLanguage().equals(this.g)) {
                str = "MIUI SDK\u7248\u672c\u8fc7\u4f4e";
                str2 = "\u8bf7\u5148\u5347\u7ea7MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002\u662f\u5426\u73b0\u5728\u5347\u7ea7\uff1f";
            } else {
                str = "MIUI SDK too old";
                str2 = "Please upgrade MIUI SDK and then re-run this application. Upgrade now?";
            }
            return a(str, str2, this.EZ, this.EY);
        }
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "MIUI SDK\u7248\u672c\u8fc7\u4f4e";
            str2 = "\u8bf7\u5148\u5347\u7ea7MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
        } else {
            str = "MIUI SDK too old";
            str2 = "Please upgrade MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.EY);
    }

    private Dialog hp() {
        CharSequence charSequence;
        CharSequence charSequence2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            charSequence = "MIUI SDK\u6b63\u5728\u66f4\u65b0";
            charSequence2 = "\u8bf7\u7a0d\u5019...";
        } else {
            charSequence = "MIUI SDK updating";
            charSequence2 = "Please wait...";
        }
        return ProgressDialog.show(this, charSequence, charSequence2, true, false);
    }

    private Dialog hq() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "MIUI SDK\u66f4\u65b0\u5b8c\u6210";
            str2 = "\u8bf7\u91cd\u65b0\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
        } else {
            str = "MIUI SDK updated";
            str2 = "Please re-run this application.";
        }
        return a(str, str2, this.EY);
    }

    private Dialog hr() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "MIUI SDK\u66f4\u65b0\u5931\u8d25";
            str2 = "\u8bf7\u7a0d\u540e\u91cd\u8bd5\u3002";
        } else {
            str = "MIUI SDK update failed";
            str2 = "Please try it later.";
        }
        return a(str, str2, this.EY);
    }

    private boolean g() {
        try {
            return ((Boolean) Class.forName("com.miui.internal.core.SdkManager").getMethod("supportUpdate", new Class[]{Map.class}).invoke(null, new Object[]{null})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean an() {
        try {
            HashMap hashMap = new HashMap();
            return ((Boolean) Class.forName("com.miui.internal.core.SdkManager").getMethod("update", new Class[]{Map.class}).invoke(null, new Object[]{hashMap})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
