package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import org.json.JSONArray;

public class JsonArrayRequest extends JsonRequest<JSONArray> {
    public JsonArrayRequest(String url, Listener<JSONArray> listener, ErrorListener errorListener) {
        super(0, url, null, listener, errorListener);
    }

    protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(new JSONArray(new String(response.data, HttpHeaderParser.parseCharset(response.headers))), HttpHeaderParser.parseCacheHeaders(response));
        } catch (Throwable e) {
            return Response.error(new ParseError(e));
        } catch (Throwable je) {
            return Response.error(new ParseError(je));
        }
    }
}
