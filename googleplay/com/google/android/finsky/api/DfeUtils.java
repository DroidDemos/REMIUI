package com.google.android.finsky.api;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class DfeUtils {
    public static String getLegacyErrorCode(VolleyError error) {
        if (error instanceof ServerError) {
            return "SERVER";
        }
        if (error instanceof AuthFailureError) {
            return "AUTH";
        }
        if (error instanceof NetworkError) {
            return "NETWORK";
        }
        if (error instanceof TimeoutError) {
            return "TIMEOUT";
        }
        if (error instanceof ParseError) {
            return "SERVER";
        }
        return "SERVER";
    }

    private static Builder createSearchUrlBuilder(String query, int channelId) {
        if (channelId == 9) {
            channelId = 0;
        }
        return DfeApi.SEARCH_CHANNEL_URI.buildUpon().appendQueryParameter("c", Integer.toString(channelId)).appendQueryParameter("q", query);
    }

    public static String formSearchUrl(String query, int channelId) {
        return createSearchUrlBuilder(query, channelId).build().toString();
    }

    public static String formSearchUrlWithFprDisabled(String query, int channelId) {
        Builder builder = createSearchUrlBuilder(query, channelId);
        builder.appendQueryParameter("fpr", "0");
        return builder.build().toString();
    }

    public static String createDetailsUrlFromId(String docId) {
        return "details?doc=" + docId;
    }

    public static boolean isSameDocumentDetailsUrl(String url1, String url2) {
        if (url1 == null || url2 == null) {
            return false;
        }
        return TextUtils.equals(Uri.parse(url1).getQueryParameter("doc"), Uri.parse(url2).getQueryParameter("doc"));
    }
}
