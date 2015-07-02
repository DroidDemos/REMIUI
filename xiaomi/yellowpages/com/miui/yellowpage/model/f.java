package com.miui.yellowpage.model;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.reference.RefMethods.Calls;
import com.miui.yellowpage.base.reference.RefMethods.Environment;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import miui.telephony.PhoneNumberUtils;

/* compiled from: CallRecord */
public class f {
    private static Context mContext;
    private ArrayList iH;
    private String[] iI;

    public f(Context context, String[] strArr) {
        this.iI = strArr;
        this.iH = new ArrayList();
        mContext = context;
        aN();
    }

    private boolean I(String str) {
        if (this.iI == null) {
            return false;
        }
        for (String compare : this.iI) {
            if (PhoneNumberUtils.compare(compare, str)) {
                return true;
            }
        }
        return false;
    }

    public s a(Long l, Long l2, String str) {
        if (this.iH == null) {
            return null;
        }
        s sVar;
        Long valueOf = Long.valueOf(l2.longValue() + ConfigConstant.LOCATE_INTERVAL_UINT);
        Iterator it = this.iH.iterator();
        while (it.hasNext()) {
            sVar = (s) it.next();
            if (sVar.Dc.longValue() >= l.longValue() && sVar.Dc.longValue() <= valueOf.longValue() && PhoneNumberUtils.compare(sVar.Db, str)) {
                break;
            }
        }
        sVar = null;
        return sVar;
    }

    private void aN() {
        String str = Environment.getMIUIExternalStorageDirectory().getAbsolutePath() + "/sound_recorder/call_rec";
        String[] list = new File(str).list();
        if (list != null) {
            for (String file : list) {
                String file2;
                File file3 = new File(str, file2);
                if (!file3.isDirectory() && file3.exists()) {
                    String absolutePath = file3.getAbsolutePath();
                    file2 = absolutePath.toLowerCase(Locale.US);
                    if (file2.endsWith(".3gpp") || file2.endsWith(".amr") || file2.endsWith(".mp3")) {
                        String[] recordPhoneNumbers = Calls.getRecordPhoneNumbers(mContext, absolutePath);
                        if (recordPhoneNumbers != null) {
                            for (String str2 : recordPhoneNumbers) {
                                Long valueOf = Long.valueOf(Calls.getCallRecordCreatedDate(mContext, absolutePath));
                                if (valueOf != null && I(str2)) {
                                    s sVar = new s(this);
                                    sVar.Da = absolutePath;
                                    sVar.Dc = valueOf;
                                    sVar.Db = str2;
                                    this.iH.add(sVar);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void a(Context context, View view, TextView textView, String str) {
        textView.setText(mContext.getResources().getString(R.string.contact_detail_calllog_call_record_tips));
        view.setOnClickListener(new u(str, context));
    }
}
