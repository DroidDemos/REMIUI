package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.p;

public class ContactsSuggestionItem extends RelativeLayout {
    private TextView ts;
    private TextView tt;

    public ContactsSuggestionItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.ts = (TextView) findViewById(R.id.normalized_number);
        this.tt = (TextView) findViewById(R.id.display_name);
    }

    public void a(p pVar, String str) {
        this.tt.setText(pVar.getDisplayName());
        this.ts.setText(o(pVar.getNormalizedNumber(), str));
    }

    private Spannable o(String str, String str2) {
        Spannable spannableStringBuilder = new SpannableStringBuilder(str);
        if (!TextUtils.isEmpty(str2)) {
            int indexOf = str.indexOf(str2);
            if (indexOf >= 0) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.highlight_text_color)), indexOf, str2.length() + indexOf, 33);
            }
        }
        return spannableStringBuilder;
    }
}
