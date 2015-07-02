package com.google.android.finsky.placesapi;

import org.json.JSONException;
import org.json.JSONObject;

public class PlaceAutocompletePrediction {
    private final String mDescription;
    private final String mReference;

    public PlaceAutocompletePrediction(String description, String reference) {
        this.mDescription = description;
        this.mReference = reference;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getReference() {
        return this.mReference;
    }

    public static PlaceAutocompletePrediction parseFromJson(JSONObject json) throws JSONException {
        return new PlaceAutocompletePrediction(json.getString("description"), json.getString("reference"));
    }
}
