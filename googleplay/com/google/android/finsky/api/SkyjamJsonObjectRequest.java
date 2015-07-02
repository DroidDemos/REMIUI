package com.google.android.finsky.api;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class SkyjamJsonObjectRequest extends JsonObjectRequest {
    public SkyjamJsonObjectRequest(int method, String url, JSONObject jsonRequest, Listener<JSONObject> listener, ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap();
        headers.put("X-Device-Logging-ID", DfeApiConfig.loggingId.get());
        headers.put("X-Device-ID", Long.toHexString(((Long) DfeApiConfig.androidId.get()).longValue()));
        return headers;
    }
}
