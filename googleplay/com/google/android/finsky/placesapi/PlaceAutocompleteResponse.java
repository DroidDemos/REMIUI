package com.google.android.finsky.placesapi;

import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceAutocompleteResponse {
    private final List<PlaceAutocompletePrediction> mPredictions;

    private PlaceAutocompleteResponse(List<PlaceAutocompletePrediction> predictions) {
        this.mPredictions = predictions;
    }

    public List<PlaceAutocompletePrediction> getPredictions() {
        return this.mPredictions;
    }

    public static PlaceAutocompleteResponse parseFromJson(JSONObject json) throws JSONException {
        JSONArray predictions = json.getJSONArray("predictions");
        ArrayList<PlaceAutocompletePrediction> predictionsParsed = Lists.newArrayList(predictions.length());
        for (int i = 0; i < predictions.length(); i++) {
            predictionsParsed.add(PlaceAutocompletePrediction.parseFromJson(predictions.getJSONObject(i)));
        }
        return new PlaceAutocompleteResponse(predictionsParsed);
    }
}
