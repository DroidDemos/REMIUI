package com.google.android.finsky.placesapi;

import com.android.volley.ParseError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PlaceRequestListener<T> implements Listener<JSONObject> {
    private final ErrorListener mErrorListener;
    private final Listener<T> mListener;

    protected abstract T parseFromJson(JSONObject jSONObject) throws JSONException;

    public PlaceRequestListener(Listener<T> listener, ErrorListener errorListener) {
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    public void onResponse(JSONObject response) {
        if ("OK".equals(response.optString("status"))) {
            try {
                this.mListener.onResponse(parseFromJson(response));
            } catch (Throwable e) {
                if (this.mErrorListener != null) {
                    this.mErrorListener.onErrorResponse(new ParseError(e));
                }
            }
        } else if (this.mErrorListener != null) {
            this.mErrorListener.onErrorResponse(new VolleyError("Response status not OK"));
        }
    }
}
