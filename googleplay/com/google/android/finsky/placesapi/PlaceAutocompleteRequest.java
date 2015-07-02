package com.google.android.finsky.placesapi;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceAutocompleteRequest extends JsonObjectRequest {

    static class InnerListener extends PlaceRequestListener<PlaceAutocompleteResponse> {
        public InnerListener(Listener<PlaceAutocompleteResponse> listener, ErrorListener errorListener) {
            super(listener, errorListener);
        }

        protected PlaceAutocompleteResponse parseFromJson(JSONObject response) throws JSONException {
            return PlaceAutocompleteResponse.parseFromJson(response);
        }
    }

    PlaceAutocompleteRequest(String url, Listener<PlaceAutocompleteResponse> listener, ErrorListener errorListener) {
        super(url, null, new InnerListener(listener, errorListener), errorListener);
    }
}
