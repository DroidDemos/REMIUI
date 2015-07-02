package com.google.android.finsky.placesapi;

import android.location.Location;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.Utils;

public class PlacesService {
    private final String mApiKey;
    private final String mLanguage;
    private final AdrMicroformatParser mParser;

    public PlacesService(String apiKey, String language, AdrMicroformatParser parser) {
        this.mApiKey = apiKey;
        this.mLanguage = language;
        this.mParser = parser;
    }

    public PlaceAutocompleteRequest createAutocompleteRequest(String input, Location location, String country, Listener<PlaceAutocompleteResponse> listener, ErrorListener errorListener) {
        return new PlaceAutocompleteRequest(buildAutocompleteUrl(input, location, country), listener, errorListener);
    }

    String buildAutocompleteUrl(String input, Location location, String country) {
        StringBuilder urlBuilder = new StringBuilder("/maps/api/place/autocomplete/json").append("?input=");
        urlBuilder.append(Utils.urlEncode(input.trim()));
        urlBuilder.append("&language=").append(this.mLanguage);
        urlBuilder.append("&types=address");
        urlBuilder.append("&components=country:").append(country);
        if (location != null) {
            urlBuilder.append("&location=").append(location.getLatitude()).append(',').append(location.getLongitude());
            urlBuilder.append("&radius=").append(G.placesApiBiasRadiusMeters.get());
        }
        return buildRequestUrl(urlBuilder);
    }

    public PlaceDetailRequest createPlaceDetailsRequest(String reference, Listener<PlaceDetailResponse> listener, ErrorListener errorListener) {
        return new PlaceDetailRequest(buildPlaceDetailsUrl(reference), this.mParser, listener, errorListener);
    }

    String buildPlaceDetailsUrl(String reference) {
        return buildRequestUrl(new StringBuilder("/maps/api/place/details/json").append("?reference=").append(reference).append("&language=").append(this.mLanguage));
    }

    private String buildRequestUrl(StringBuilder relativeUrl) {
        relativeUrl.append("&key=").append(this.mApiKey).append("&sensor=true");
        return "https://maps.googleapis.com".concat(relativeUrl.toString());
    }
}
