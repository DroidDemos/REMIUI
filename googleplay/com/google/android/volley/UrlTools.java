package com.google.android.volley;

import android.content.Context;
import com.google.android.volley.guava.UrlRules;

public class UrlTools {
    public static String rewrite(Context context, String url) {
        return UrlRules.getRules(context.getContentResolver()).matchRule(url).apply(url);
    }
}
