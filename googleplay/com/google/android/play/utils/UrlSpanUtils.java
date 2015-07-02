package com.google.android.play.utils;

import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.View;

public class UrlSpanUtils {

    public interface Listener {
        void onClick(View view, String str);
    }

    private static class SelfishUrlSpan extends URLSpan {
        private final Listener listener;

        public SelfishUrlSpan(String url, Listener listener) {
            super(url);
            this.listener = listener;
        }

        public void onClick(View view) {
            this.listener.onClick(view, getURL());
        }
    }

    public static void selfishifyUrlSpans(CharSequence string, Listener listener) {
        selfishifyUrlSpans(string, null, listener);
    }

    public static void selfishifyUrlSpans(CharSequence string, CharSequence targetUrl, Listener listener) {
        if (listener == null) {
            throw new IllegalStateException("listener should not be null");
        } else if (string instanceof Spannable) {
            Spannable spannable = (Spannable) string;
            for (URLSpan span : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
                String url = span.getURL();
                if (targetUrl == null || targetUrl.equals(url)) {
                    int start = spannable.getSpanStart(span);
                    int end = spannable.getSpanEnd(span);
                    spannable.removeSpan(span);
                    spannable.setSpan(new SelfishUrlSpan(url, listener), start, end, 0);
                }
            }
        }
    }
}
