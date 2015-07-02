package com.google.android.finsky.placesapi;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceDetailRequest extends JsonObjectRequest {

    static class InnerListener extends PlaceRequestListener<PlaceDetailResponse> {
        private AdrMicroformatParser mParser;

        public InnerListener(AdrMicroformatParser parser, Listener<PlaceDetailResponse> listener, ErrorListener errorListener) {
            super(listener, errorListener);
            this.mParser = parser;
        }

        protected PlaceDetailResponse parseFromJson(JSONObject response) throws JSONException {
            return PlaceDetailResponse.parseFromJson(response.getJSONObject("result"), this.mParser);
        }
    }

    PlaceDetailRequest(String url, AdrMicroformatParser parser, Listener<PlaceDetailResponse> listener, ErrorListener errorListener) {
        super(url, null, new InnerListener(parser, listener, errorListener), errorListener);
    }
}
