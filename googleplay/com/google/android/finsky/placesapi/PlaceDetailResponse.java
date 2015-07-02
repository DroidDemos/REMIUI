package com.google.android.finsky.placesapi;

import com.google.android.finsky.protos.BillingAddress.Address;
import org.json.JSONException;
import org.json.JSONObject;

public class PlaceDetailResponse {
    private Address mAddress;

    public PlaceDetailResponse(Address address) {
        this.mAddress = address;
    }

    public Address getAddress() {
        return this.mAddress;
    }

    public static PlaceDetailResponse parseFromJson(JSONObject json, AdrMicroformatParser parser) throws JSONException {
        try {
            return new PlaceDetailResponse(parser.parse(json.getString("adr_address")));
        } catch (AdrMicroformatParserException e) {
            JSONException jsonException = new JSONException(e.getMessage());
            jsonException.initCause(e);
            throw jsonException;
        }
    }
}
