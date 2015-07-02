package com.google.android.play.dfe.api;

import android.net.Uri;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;

public interface PlayDfeApi {
    public static final Uri BASE_URI;
    public static final Uri PLUS_PROFILE_URI;

    Request<?> getPlusProfile(Listener<PlusProfileResponse> listener, ErrorListener errorListener, boolean z);

    void invalidatePlusProfile(boolean z);

    static {
        BASE_URI = Uri.parse("https://android.clients.google.com/fdfe/");
        PLUS_PROFILE_URI = Uri.parse("api/plusProfile");
    }
}
