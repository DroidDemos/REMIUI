package miui.external;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.passport.widget.AlertDialog;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import miui.external.SdkConstants.SdkError;
import miui.external.SdkConstants.SdkReturnCode;

public class SdkErrorActivity extends Activity implements SdkConstants {
    private String g;
    private OnClickListener h;
    private OnClickListener i;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] j;

        static {
            j = new int[SdkError.values().length];
            try {
                j[SdkError.NO_SDK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                j[SdkError.LOW_SDK_VERSION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    class a extends DialogFragment {
        final /* synthetic */ SdkErrorActivity b;
        private Dialog p;

        public a(SdkErrorActivity sdkErrorActivity, Dialog dialog) {
            this.b = sdkErrorActivity;
            this.p = dialog;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            return this.p;
        }
    }

    public SdkErrorActivity() {
        this.h = new a(this);
        this.i = new c(this);
    }

    protected void onCreate(Bundle bundle) {
        Dialog b;
        setTheme(16973909);
        super.onCreate(bundle);
        this.g = Locale.getDefault().getLanguage();
        SdkError sdkError = null;
        Intent intent = getIntent();
        if (intent != null) {
            sdkError = (SdkError) intent.getSerializableExtra(SdkError.INTENT_EXTRA_KEY);
        }
        if (sdkError == null) {
            sdkError = SdkError.GENERIC;
        }
        switch (AnonymousClass1.j[sdkError.ordinal()]) {
            case SdkReturnCode.LOW_SDK_VERSION /*1*/:
                b = b();
                break;
            case AlertDialog.THEME_DARK /*2*/:
                b = c();
                break;
            default:
                b = a();
                break;
        }
        new a(this, b).show(getFragmentManager(), "SdkErrorPromptDialog");
    }

    private Dialog a(String str, String str2, OnClickListener onClickListener) {
        return new Builder(this).setTitle(str).setMessage(str2).setPositiveButton(17039370, onClickListener).setIcon(17301543).setCancelable(false).create();
    }

    private Dialog a(String str, String str2, OnClickListener onClickListener, OnClickListener onClickListener2) {
        return new Builder(this).setTitle(str).setMessage(str2).setPositiveButton(17039370, onClickListener).setNegativeButton(17039360, onClickListener2).setIcon(17301543).setCancelable(false).create();
    }

    private Dialog a() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "MIUI SDK\u53d1\u751f\u9519\u8bef";
            str2 = "\u8bf7\u91cd\u65b0\u5b89\u88c5MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
        } else {
            str = "MIUI SDK encounter errors";
            str2 = "Please re-install MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.h);
    }

    private Dialog b() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "\u6ca1\u6709\u627e\u5230MIUI SDK";
            str2 = "\u8bf7\u5148\u5b89\u88c5MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
        } else {
            str = "MIUI SDK not found";
            str2 = "Please install MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.h);
    }

    private Dialog c() {
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
            return a(str, str2, this.i, this.h);
        }
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "MIUI SDK\u7248\u672c\u8fc7\u4f4e";
            str2 = "\u8bf7\u5148\u5347\u7ea7MIUI SDK\u518d\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
        } else {
            str = "MIUI SDK too old";
            str2 = "Please upgrade MIUI SDK and then re-run this application.";
        }
        return a(str, str2, this.h);
    }

    private Dialog d() {
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

    private Dialog e() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "MIUI SDK\u66f4\u65b0\u5b8c\u6210";
            str2 = "\u8bf7\u91cd\u65b0\u8fd0\u884c\u672c\u7a0b\u5e8f\u3002";
        } else {
            str = "MIUI SDK updated";
            str2 = "Please re-run this application.";
        }
        return a(str, str2, this.h);
    }

    private Dialog f() {
        String str;
        String str2;
        if (Locale.CHINESE.getLanguage().equals(this.g)) {
            str = "MIUI SDK\u66f4\u65b0\u5931\u8d25";
            str2 = "\u8bf7\u7a0d\u540e\u91cd\u8bd5\u3002";
        } else {
            str = "MIUI SDK update failed";
            str2 = "Please try it later.";
        }
        return a(str, str2, this.h);
    }

    private boolean g() {
        try {
            return ((Boolean) Class.forName(SdkConstants.a).getMethod("supportUpdate", new Class[]{Map.class}).invoke(null, new Object[]{null})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean h() {
        try {
            HashMap hashMap = new HashMap();
            return ((Boolean) Class.forName(SdkConstants.a).getMethod("update", new Class[]{Map.class}).invoke(null, new Object[]{hashMap})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
