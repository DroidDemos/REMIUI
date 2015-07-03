package com.xiaomi.account.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.xiaomi.account.R;

public class IntentSpan extends ClickableSpan {
    private Context mContext;
    private int mType;

    public IntentSpan(Context context, int type) {
        this.mContext = context;
        this.mType = type;
    }

    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(true);
        ds.setColor(this.mContext.getResources().getColor(R.color.hyperlink_text_color));
    }

    public void onClick(View widget) {
        Intent intent = new Intent("android.intent.action.VIEW_LICENSE");
        intent.putExtra("android.intent.extra.LICENSE_TYPE", this.mType);
        this.mContext.startActivity(intent);
    }
}
