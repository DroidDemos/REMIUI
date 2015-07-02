package com.google.android.finsky.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.fragments.DeepLinkShimFragment;

public class HighlightUtils {
    public static void trackClickUrl(Document doc) {
        if (doc != null && doc.isHighlighted()) {
            handleHighlight(doc, doc.getClickUrl());
        }
    }

    private static void handleHighlight(final Document doc, final String url) {
        if (TextUtils.isEmpty(url)) {
            FinskyLog.e("Empty URL for docid: %s", doc.getDocId());
            return;
        }
        FinskyApp.get().getRequestQueue().add(new StringRequest(url, new Listener<String>() {
            public void onResponse(String response) {
                FinskyLog.e("URL[%s] was not redirected.", url);
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (response.statusCode == 302 && response.headers.containsKey("Location")) {
                    if (TextUtils.isEmpty((String) response.headers.get("Location"))) {
                        FinskyLog.e("Empty Location header from 302 URL: %s", url);
                        return;
                    }
                    String referrer = Uri.parse((String) response.headers.get("Location")).getQueryParameter("referrer");
                    if (TextUtils.isEmpty(referrer)) {
                        FinskyLog.w("Missing referrer in location header field for URL[%s]", url);
                        return;
                    }
                    DeepLinkShimFragment.saveExternalReferrer(referrer, doc.getFullDocid());
                } else if ((error instanceof NoConnectionError) || (error instanceof TimeoutError)) {
                    FinskyLog.w("No connection error or timeout error", new Object[0]);
                } else {
                    FinskyLog.e("Unexpected error response for URL[%s], docid[%s]: %s", url, doc.getDocId(), error.getMessage());
                }
            }
        }));
    }
}
