package com.miui.yellowpage.business.recharge;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.miui.yellowpage.R;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RechargeFragment */
class z {
    final /* synthetic */ D dw;
    private SpannableStringBuilder lH;
    private Map lI;

    public void a(char c, String str) {
        this.lI.put(Character.valueOf(c), str);
    }

    public z(D d, SpannableStringBuilder spannableStringBuilder) {
        this.dw = d;
        this.lH = spannableStringBuilder;
        this.lI = new HashMap();
    }

    public Spannable P(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int indexOf = str.indexOf(37, i);
            if (indexOf == -1) {
                break;
            }
            if (i < indexOf) {
                this.lH.append(str.substring(i, indexOf));
            }
            Object a = a(str, indexOf);
            if (TextUtils.isEmpty(a)) {
                i = 1;
            } else {
                CharSequence spannableStringBuilder = new SpannableStringBuilder(a);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(this.dw.mActivity.getResources().getColor(R.color.highlight_text_color)), 0, a.length(), 33);
                this.lH.append(spannableStringBuilder);
                i = 4;
            }
            i += indexOf;
        }
        if (i < length) {
            this.lH.append(str.substring(i, length));
        }
        return this.lH;
    }

    private String a(String str, int i) {
        if (i < 0 || i + 1 >= str.length()) {
            return null;
        }
        return (String) this.lI.get(Character.valueOf(str.charAt(i + 1)));
    }
}
