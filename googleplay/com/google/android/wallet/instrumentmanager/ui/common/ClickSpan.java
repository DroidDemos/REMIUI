package com.google.android.wallet.instrumentmanager.ui.common;

import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;

public class ClickSpan extends URLSpan {
    private final OnClickListener mListener;

    public interface OnClickListener {
        void onClick(View view, String str);
    }

    private ClickSpan(String url, OnClickListener listener) {
        super(url);
        if (listener == null) {
            throw new IllegalStateException("listener should not be null");
        }
        this.mListener = listener;
    }

    public void onClick(View view) {
        this.mListener.onClick(view, getURL());
    }

    public static void clickify(TextView textView, String source, OnClickListener listener) {
        SpannableString text = new SpannableString(Html.fromHtml(source));
        for (URLSpan span : (URLSpan[]) text.getSpans(0, text.length(), URLSpan.class)) {
            String url = span.getURL();
            int spanStart = text.getSpanStart(span);
            int spanEnd = text.getSpanEnd(span);
            int spanFlags = text.getSpanFlags(span);
            text.removeSpan(span);
            text.setSpan(new ClickSpan(url, listener), spanStart, spanEnd, spanFlags);
        }
        textView.setText(text);
        if (!(textView.getMovementMethod() instanceof LinkMovementMethod)) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
