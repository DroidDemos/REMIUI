package com.google.android.finsky.layout;

import com.google.android.finsky.placesapi.PlaceAutocompletePrediction;
import java.util.List;

public interface AddressSuggestionProvider {
    List<PlaceAutocompletePrediction> getSuggestions(CharSequence charSequence);

    void onSuggestionAccepted(PlaceAutocompletePrediction placeAutocompletePrediction);
}
