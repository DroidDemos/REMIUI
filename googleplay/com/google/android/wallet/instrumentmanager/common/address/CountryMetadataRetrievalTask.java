package com.google.android.wallet.instrumentmanager.common.address;

import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Priority;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryMetadataRetrievalTask implements Listener<JSONObject> {
    private static final Uri PUBLIC_ADDRESS_DATA_SERVER;
    private final String mDesiredLanguageCode;
    private final ErrorListener mErrorListener;
    private final int mRegionCode;
    private final RequestQueue mRequestQueue;
    private final Listener<JSONObject> mResponseListener;

    public static class AdminAreaMetadataRetrievalRequest extends JsonObjectRequest {
        public AdminAreaMetadataRetrievalRequest(int regionCode, String adminAreaKey, Listener<JSONObject> responseListener, ErrorListener errorListener) {
            super(0, CountryMetadataRetrievalTask.buildCountryUri(regionCode, null).buildUpon().appendPath(adminAreaKey).toString(), null, responseListener, errorListener);
        }

        public Priority getPriority() {
            return Priority.HIGH;
        }
    }

    public static class CountryMetadataRetrievalRequest extends JsonObjectRequest {
        private static final HashMap<String, String> COUNTRY_DATA_DEFAULT;
        private final String mId;

        static {
            COUNTRY_DATA_DEFAULT = new HashMap();
            COUNTRY_DATA_DEFAULT.put("upper", "C");
            COUNTRY_DATA_DEFAULT.put("zip_name_type", "postal");
            COUNTRY_DATA_DEFAULT.put("fmt", "%N%n%O%n%A%n%C");
            COUNTRY_DATA_DEFAULT.put("require", "AC");
            COUNTRY_DATA_DEFAULT.put("state_name_type", "province");
            COUNTRY_DATA_DEFAULT.put("id", "data/ZZ");
            COUNTRY_DATA_DEFAULT.put("dir", "ltr");
        }

        public CountryMetadataRetrievalRequest(int regionCode, String languageCode, Listener<JSONObject> responseListener, ErrorListener errorListener) {
            super(0, CountryMetadataRetrievalTask.buildCountryUri(regionCode, languageCode).toString(), null, responseListener, errorListener);
            this.mId = CountryMetadataRetrievalTask.buildCountryId(regionCode, languageCode);
        }

        public Priority getPriority() {
            return Priority.HIGH;
        }

        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            Response<JSONObject> parsedResponse = super.parseNetworkResponse(response);
            if (!parsedResponse.isSuccess()) {
                return parsedResponse;
            }
            JSONObject parsedJson = parsedResponse.result;
            String id = AddressUtils.getAddressData(parsedJson, "id");
            if (TextUtils.isEmpty(id)) {
                id = this.mId;
                try {
                    parsedJson.put("id", id);
                } catch (JSONException e) {
                    throw new RuntimeException("Error adding id=" + id + " to response", e);
                }
            }
            if (TextUtils.isEmpty(AddressUtils.getAddressData(parsedJson, "key")) && !TextUtils.isEmpty(id)) {
                String keyFromId = id.substring(id.lastIndexOf("/") + 1);
                try {
                    parsedJson.put("key", keyFromId);
                } catch (JSONException e2) {
                    throw new RuntimeException("Error adding key=" + keyFromId + " to response", e2);
                }
            }
            for (String jsonKey : COUNTRY_DATA_DEFAULT.keySet()) {
                if (!parsedJson.has(jsonKey)) {
                    try {
                        parsedJson.put(jsonKey, COUNTRY_DATA_DEFAULT.get(jsonKey));
                    } catch (JSONException e22) {
                        throw new RuntimeException("Error adding country default data for key=" + jsonKey + " to response", e22);
                    }
                }
            }
            return Response.success(parsedJson, parsedResponse.cacheEntry);
        }
    }

    static {
        PUBLIC_ADDRESS_DATA_SERVER = Uri.parse("https://i18napis.appspot.com/address");
    }

    public CountryMetadataRetrievalTask(RequestQueue requestQueue, int regionCode, String desiredLanguageCode, Listener<JSONObject> responseListener, ErrorListener errorListener) {
        this.mRequestQueue = requestQueue;
        this.mRegionCode = regionCode;
        this.mDesiredLanguageCode = desiredLanguageCode;
        this.mResponseListener = responseListener;
        this.mErrorListener = errorListener;
    }

    public void run(String languageCode) {
        this.mRequestQueue.add(new CountryMetadataRetrievalRequest(this.mRegionCode, languageCode, this, this.mErrorListener));
    }

    public void onResponse(JSONObject countryData) {
        String alternativeLang = AddressUtils.getAlternativeLanguageCode(countryData, this.mDesiredLanguageCode);
        if (TextUtils.isEmpty(alternativeLang)) {
            this.mResponseListener.onResponse(countryData);
        } else {
            run(alternativeLang);
        }
    }

    private static String buildCountryId(int regionCode, String languageCode) {
        StringBuilder id = new StringBuilder("data").append("/").append(RegionCode.toCountryCode(regionCode));
        if (!TextUtils.isEmpty(languageCode)) {
            id.append("--").append(languageCode);
        }
        return id.toString();
    }

    private static Uri buildCountryUri(int regionCode, String languageCode) {
        return PUBLIC_ADDRESS_DATA_SERVER.buildUpon().appendEncodedPath(buildCountryId(regionCode, languageCode)).build();
    }
}
