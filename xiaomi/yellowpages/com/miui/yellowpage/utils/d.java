package com.miui.yellowpage.utils;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/* compiled from: Ui */
public class d extends ClickableSpan {
    private s iW;

    public d(s sVar) {
        this.iW = sVar;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(true);
        textPaint.setColor(-16776961);
    }

    public void onClick(View view) {
        if (this.iW != null) {
            this.iW.onClick();
        }
    }
}
