package com.google.android.finsky.utils;

import android.content.Context;
import com.android.vending.R;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.volley.DisplayMessageError;

public class ErrorStrings {
    public static String get(Context context, VolleyError error) {
        if (error instanceof DisplayMessageError) {
            return ((DisplayMessageError) error).getDisplayErrorHtml();
        }
        if (error instanceof AuthFailureError) {
            return context.getString(R.string.auth_required_error);
        }
        if (error instanceof ServerError) {
            return context.getString(R.string.server_error);
        }
        if (error instanceof TimeoutError) {
            return context.getString(R.string.timeout_error);
        }
        if (error instanceof NetworkError) {
            return context.getString(R.string.network_error);
        }
        FinskyLog.d("No specific error message for: %s", error);
        return context.getString(R.string.network_error);
    }

    public static String getTitle(Context context, VolleyError error) {
        if (error instanceof NetworkError) {
            return context.getString(R.string.network_error_title);
        }
        return context.getString(R.string.error);
    }
}
