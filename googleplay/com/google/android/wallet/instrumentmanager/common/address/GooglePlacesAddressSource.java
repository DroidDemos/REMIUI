package com.google.android.wallet.instrumentmanager.common.address;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.Pair;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.google.android.play.R;
import com.google.android.wallet.instrumentmanager.api.InstrumentManagerRequestQueue;
import com.google.android.wallet.instrumentmanager.config.G.googleplaces;
import com.google.location.country.Postaladdress.PostalAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlacesAddressSource implements AddressSource {
    private final Context mContext;

    public GooglePlacesAddressSource(Context context) {
        this.mContext = context;
    }

    public static boolean isCountrySupported(int regionCode) {
        return ((String) googleplaces.supportedCountries.get()).contains(RegionCode.toCountryCode(regionCode));
    }

    private static boolean isFieldSupported(int regionCode, char addressField) {
        if (isCountrySupported(regionCode) && getRequestTypeForField(addressField) != null) {
            return true;
        }
        return false;
    }

    public String getName() {
        return "GooglePlacesAddressSource";
    }

    public List<AddressSourceResult> getAddresses(CharSequence prefix, char addressField, char[] remainingFields, int regionCode, String languageCode) {
        if (prefix == null || prefix.length() < getThresholdForField(addressField) || !isFieldSupported(regionCode, addressField)) {
            return null;
        }
        return convertJsonObjectToAddressSourceResults(fetchJsonObject(buildPlacesAutocompleteUrl(prefix, addressField, regionCode, languageCode)), prefix, addressField);
    }

    public PostalAddress getAddress(String reference, String languageCode) {
        if (TextUtils.isEmpty(reference)) {
            return null;
        }
        return convertJsonObjectToPostalAddress(fetchJsonObject(buildPlaceDetailsUrl(reference, languageCode)), languageCode);
    }

    private JSONObject fetchJsonObject(String urlSpec) {
        try {
            RequestFuture<JSONObject> future = RequestFuture.newFuture();
            InstrumentManagerRequestQueue.getApiRequestQueue(this.mContext).add(new JsonObjectRequest(urlSpec, null, future, future));
            return (JSONObject) future.get(5000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            Log.w("GooglePlacesAddressSour", "TimeoutException while retrieving addresses from GooglePlaces", e);
            return null;
        } catch (InterruptedException e2) {
            Log.w("GooglePlacesAddressSour", "InterruptedException while retrieving addresses from GooglePlaces", e2);
            return null;
        } catch (ExecutionException e3) {
            Log.w("GooglePlacesAddressSour", "ExecutionException while retrieving addresses from GooglePlaces", e3);
            return null;
        }
    }

    static int getThresholdForField(char field) {
        switch (field) {
            case R.styleable.Theme_dividerVertical /*49*/:
                return ((Integer) googleplaces.thresholdAddressLine1.get()).intValue();
            default:
                return ((Integer) googleplaces.thresholdDefault.get()).intValue();
        }
    }

    static String getRequestTypeForField(char field) {
        switch (field) {
            case R.styleable.Theme_dividerVertical /*49*/:
                return "geocode";
            case R.styleable.Theme_listPreferredItemPaddingRight /*67*/:
                return "(cities)";
            case R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                return "(regions)";
            default:
                return null;
        }
    }

    private String buildPlacesAutocompleteUrl(CharSequence query, char addressField, int regionCode, String languageCode) {
        ArrayList<NameValuePair> parameters = new ArrayList();
        parameters.add(new BasicNameValuePair("input", query.toString()));
        parameters.add(new BasicNameValuePair("key", "AIzaSyCgACP5TTubzmLhxFL5ONXq6B5l2eH_EXc"));
        parameters.add(new BasicNameValuePair("types", getRequestTypeForField(addressField)));
        Location location = getLastKnownLocation();
        if (location != null) {
            parameters.add(new BasicNameValuePair("location", location.getLatitude() + "," + location.getLongitude()));
            parameters.add(new BasicNameValuePair("radius", String.valueOf(80000)));
        }
        parameters.add(new BasicNameValuePair("sensor", location != null ? "true" : "false"));
        parameters.add(new BasicNameValuePair("components", "country:" + RegionCode.toCountryCode(regionCode).toLowerCase(Locale.US)));
        if (!TextUtils.isEmpty(languageCode)) {
            parameters.add(new BasicNameValuePair("language", languageCode));
        }
        return "https://maps.googleapis.com/maps/api/place/autocomplete/json?" + URLEncodedUtils.format(parameters, "utf-8");
    }

    private String buildPlaceDetailsUrl(String reference, String languageCode) {
        ArrayList<NameValuePair> parameters = new ArrayList();
        parameters.add(new BasicNameValuePair("reference", reference));
        parameters.add(new BasicNameValuePair("sensor", getLastKnownLocation() != null ? "true" : "false"));
        parameters.add(new BasicNameValuePair("key", "AIzaSyCgACP5TTubzmLhxFL5ONXq6B5l2eH_EXc"));
        if (!TextUtils.isEmpty(languageCode)) {
            parameters.add(new BasicNameValuePair("language", languageCode));
        }
        return "https://maps.googleapis.com/maps/api/place/details/json?" + URLEncodedUtils.format(parameters, "utf-8");
    }

    private Location getLastKnownLocation() {
        return ((LocationManager) this.mContext.getSystemService("location")).getLastKnownLocation("network");
    }

    private static ArrayList<AddressSourceResult> convertJsonObjectToAddressSourceResults(JSONObject response, CharSequence prefix, char addressField) {
        if (response == null) {
            return null;
        }
        String status = response.optString("status");
        if ("OK".equalsIgnoreCase(status)) {
            try {
                JSONArray predictions = response.getJSONArray("predictions");
                ArrayList<AddressSourceResult> results = new ArrayList();
                int numPredictions = predictions.length();
                for (int i = 0; i < numPredictions; i++) {
                    try {
                        JSONObject prediction = predictions.getJSONObject(i);
                        CharSequence description = prediction.getString("description");
                        if (description.length() != 0) {
                            String reference = prediction.getString("reference");
                            if (reference.length() != 0 && hasTypeForField(prediction, addressField)) {
                                String matchingTerm = getMatchingTerm(prediction);
                                if (matchingTerm != null && (!shouldValidateMatchingTerm(addressField) || matchingTerm.toLowerCase().startsWith(prefix.toString().toLowerCase()))) {
                                    List<Pair<Integer, Integer>> matchedSubstrings = getMatchedSubstrings(prediction);
                                    if (!matchedSubstrings.isEmpty()) {
                                        CharSequence description2 = new SpannableString(description);
                                        for (Pair<Integer, Integer> matchedSubstring : matchedSubstrings) {
                                            SpannableString spannableString = (SpannableString) description2;
                                            spannableString.setSpan(new StyleSpan(1), ((Integer) matchedSubstring.first).intValue(), ((Integer) matchedSubstring.second).intValue() + ((Integer) matchedSubstring.first).intValue(), 0);
                                        }
                                        description = description2;
                                    }
                                    results.add(new AddressSourceResult(matchingTerm, description, "GooglePlacesAddressSource", reference));
                                }
                            }
                        }
                    } catch (JSONException e) {
                    }
                }
                return results;
            } catch (JSONException e2) {
                Log.w("GooglePlacesAddressSour", "Response does not contain predictions");
                return null;
            }
        }
        Log.w("GooglePlacesAddressSour", "Response has invalid status: " + status);
        return null;
    }

    private static List<Pair<Integer, Integer>> getMatchedSubstrings(JSONObject prediction) {
        List<Pair<Integer, Integer>> result = new ArrayList();
        try {
            JSONArray matchedSubstrings = prediction.getJSONArray("matched_substrings");
            int numSubstrings = matchedSubstrings.length();
            for (int i = 0; i < numSubstrings; i++) {
                JSONObject matchedSubstring = matchedSubstrings.getJSONObject(i);
                result.add(Pair.create(Integer.valueOf(matchedSubstring.getInt("offset")), Integer.valueOf(matchedSubstring.getInt("length"))));
            }
        } catch (JSONException e) {
        }
        return result;
    }

    private static String getMatchingTerm(JSONObject prediction) throws JSONException {
        int offset = prediction.getJSONArray("matched_substrings").getJSONObject(0).getInt("offset");
        JSONArray terms = prediction.getJSONArray("terms");
        int numTerms = terms.length();
        for (int i = 0; i < numTerms; i++) {
            JSONObject term = terms.getJSONObject(i);
            if (offset < term.getInt("offset") + term.getString("value").length()) {
                return term.getString("value");
            }
        }
        return null;
    }

    private static boolean hasTypeForField(JSONObject object, char field) {
        String responseTypeForField;
        switch (field) {
            case R.styleable.Theme_dividerVertical /*49*/:
                responseTypeForField = "route";
                break;
            case R.styleable.Theme_listPreferredItemPaddingRight /*67*/:
                responseTypeForField = "locality";
                break;
            case R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                responseTypeForField = "administrative_area_level_1";
                break;
            case 'Z':
                responseTypeForField = "locality";
                break;
            default:
                responseTypeForField = null;
                break;
        }
        return hasType(object, responseTypeForField);
    }

    private static boolean hasType(JSONObject object, String type) {
        if (TextUtils.isEmpty(type)) {
            return false;
        }
        try {
            JSONArray types = object.getJSONArray("types");
            int numTypes = types.length();
            for (int t = 0; t < numTypes; t++) {
                if (type.equalsIgnoreCase(types.getString(t))) {
                    return true;
                }
            }
            return false;
        } catch (JSONException e) {
            return false;
        }
    }

    private static boolean shouldValidateMatchingTerm(char field) {
        switch (field) {
            case R.styleable.Theme_dividerVertical /*49*/:
            case R.styleable.Theme_listPreferredItemPaddingRight /*67*/:
                return false;
            default:
                return true;
        }
    }

    private static com.google.location.country.Postaladdress.PostalAddress convertJsonObjectToPostalAddress(org.json.JSONObject r23, java.lang.String r24) {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:42)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:66)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        if (r23 != 0) goto L_0x0004;
    L_0x0002:
        r2 = 0;
    L_0x0003:
        return r2;
    L_0x0004:
        r19 = "status";
        r0 = r23;
        r1 = r19;
        r14 = r0.optString(r1);
        r19 = "OK";
        r0 = r19;
        r19 = r0.equalsIgnoreCase(r14);
        if (r19 != 0) goto L_0x0034;
    L_0x0018:
        r19 = "GooglePlacesAddressSour";
        r20 = new java.lang.StringBuilder;
        r20.<init>();
        r21 = "Response has invalid status: ";
        r20 = r20.append(r21);
        r0 = r20;
        r20 = r0.append(r14);
        r20 = r20.toString();
        android.util.Log.w(r19, r20);
        r2 = 0;
        goto L_0x0003;
    L_0x0034:
        r19 = "result";	 Catch:{ JSONException -> 0x0063 }
        r0 = r23;	 Catch:{ JSONException -> 0x0063 }
        r1 = r19;	 Catch:{ JSONException -> 0x0063 }
        r12 = r0.getJSONObject(r1);	 Catch:{ JSONException -> 0x0063 }
        r19 = "address_components";	 Catch:{ JSONException -> 0x0066 }
        r0 = r19;	 Catch:{ JSONException -> 0x0066 }
        r3 = r12.getJSONArray(r0);	 Catch:{ JSONException -> 0x0066 }
        r8 = new java.util.HashMap;
        r8.<init>();
        r6 = 0;
        r7 = r3.length();
    L_0x0050:
        if (r6 >= r7) goto L_0x00a8;
    L_0x0052:
        r4 = r3.getJSONObject(r6);	 Catch:{ JSONException -> 0x019c }
        r19 = "postal_code_prefix";	 Catch:{ JSONException -> 0x019c }
        r0 = r19;	 Catch:{ JSONException -> 0x019c }
        r19 = hasType(r4, r0);	 Catch:{ JSONException -> 0x019c }
        if (r19 == 0) goto L_0x0069;	 Catch:{ JSONException -> 0x019c }
    L_0x0060:
        r6 = r6 + 1;	 Catch:{ JSONException -> 0x019c }
        goto L_0x0050;	 Catch:{ JSONException -> 0x019c }
    L_0x0063:
        r5 = move-exception;	 Catch:{ JSONException -> 0x019c }
        r2 = 0;	 Catch:{ JSONException -> 0x019c }
        goto L_0x0003;	 Catch:{ JSONException -> 0x019c }
    L_0x0066:
        r5 = move-exception;	 Catch:{ JSONException -> 0x019c }
        r2 = 0;	 Catch:{ JSONException -> 0x019c }
        goto L_0x0003;	 Catch:{ JSONException -> 0x019c }
    L_0x0069:
        r19 = "administrative_area_level_1";	 Catch:{ JSONException -> 0x019c }
        r0 = r19;	 Catch:{ JSONException -> 0x019c }
        r19 = hasType(r4, r0);	 Catch:{ JSONException -> 0x019c }
        if (r19 != 0) goto L_0x007d;	 Catch:{ JSONException -> 0x019c }
    L_0x0073:
        r19 = "country";	 Catch:{ JSONException -> 0x019c }
        r0 = r19;	 Catch:{ JSONException -> 0x019c }
        r19 = hasType(r4, r0);	 Catch:{ JSONException -> 0x019c }
        if (r19 == 0) goto L_0x00a5;	 Catch:{ JSONException -> 0x019c }
    L_0x007d:
        r10 = "short_name";	 Catch:{ JSONException -> 0x019c }
    L_0x007f:
        r9 = r4.getString(r10);	 Catch:{ JSONException -> 0x019c }
        r19 = "types";	 Catch:{ JSONException -> 0x019c }
        r0 = r19;	 Catch:{ JSONException -> 0x019c }
        r18 = r4.getJSONArray(r0);	 Catch:{ JSONException -> 0x019c }
        r16 = 0;	 Catch:{ JSONException -> 0x019c }
        r11 = r18.length();	 Catch:{ JSONException -> 0x019c }
    L_0x0091:
        r0 = r16;	 Catch:{ JSONException -> 0x019c }
        if (r0 >= r11) goto L_0x0060;	 Catch:{ JSONException -> 0x019c }
    L_0x0095:
        r0 = r18;	 Catch:{ JSONException -> 0x019c }
        r1 = r16;	 Catch:{ JSONException -> 0x019c }
        r17 = r0.getString(r1);	 Catch:{ JSONException -> 0x019c }
        r0 = r17;	 Catch:{ JSONException -> 0x019c }
        r8.put(r0, r9);	 Catch:{ JSONException -> 0x019c }
        r16 = r16 + 1;	 Catch:{ JSONException -> 0x019c }
        goto L_0x0091;	 Catch:{ JSONException -> 0x019c }
    L_0x00a5:
        r10 = "long_name";	 Catch:{ JSONException -> 0x019c }
        goto L_0x007f;
    L_0x00a8:
        r2 = new com.google.location.country.Postaladdress$PostalAddress;
        r2.<init>();
        r19 = "street_number";
        r0 = r19;
        r19 = r8.containsKey(r0);
        if (r19 != 0) goto L_0x00c1;
    L_0x00b7:
        r19 = "route";
        r0 = r19;
        r19 = r8.containsKey(r0);
        if (r19 == 0) goto L_0x00eb;
    L_0x00c1:
        r19 = "street_number";
        r0 = r19;
        r15 = r8.get(r0);
        r15 = (java.lang.String) r15;
        r19 = "route";
        r0 = r19;
        r13 = r8.get(r0);
        r13 = (java.lang.String) r13;
        r19 = android.text.TextUtils.isEmpty(r15);
        if (r19 == 0) goto L_0x0157;
    L_0x00db:
        r19 = 1;
        r0 = r19;
        r0 = new java.lang.String[r0];
        r19 = r0;
        r20 = 0;
        r19[r20] = r13;
        r0 = r19;
        r2.addressLine = r0;
    L_0x00eb:
        r19 = "locality";
        r0 = r19;
        r19 = r8.containsKey(r0);
        if (r19 == 0) goto L_0x0103;
    L_0x00f5:
        r19 = "locality";
        r0 = r19;
        r19 = r8.get(r0);
        r19 = (java.lang.String) r19;
        r0 = r19;
        r2.localityName = r0;
    L_0x0103:
        r19 = "administrative_area_level_1";
        r0 = r19;
        r19 = r8.containsKey(r0);
        if (r19 == 0) goto L_0x011b;
    L_0x010d:
        r19 = "administrative_area_level_1";
        r0 = r19;
        r19 = r8.get(r0);
        r19 = (java.lang.String) r19;
        r0 = r19;
        r2.administrativeAreaName = r0;
    L_0x011b:
        r19 = "postal_code";
        r0 = r19;
        r19 = r8.containsKey(r0);
        if (r19 == 0) goto L_0x0133;
    L_0x0125:
        r19 = "postal_code";
        r0 = r19;
        r19 = r8.get(r0);
        r19 = (java.lang.String) r19;
        r0 = r19;
        r2.postalCodeNumber = r0;
    L_0x0133:
        r19 = "country";
        r0 = r19;
        r19 = r8.containsKey(r0);
        if (r19 == 0) goto L_0x014b;
    L_0x013d:
        r19 = "country";
        r0 = r19;
        r19 = r8.get(r0);
        r19 = (java.lang.String) r19;
        r0 = r19;
        r2.countryNameCode = r0;
    L_0x014b:
        r19 = android.text.TextUtils.isEmpty(r24);
        if (r19 != 0) goto L_0x0003;
    L_0x0151:
        r0 = r24;
        r2.languageCode = r0;
        goto L_0x0003;
    L_0x0157:
        r19 = android.text.TextUtils.isEmpty(r13);
        if (r19 == 0) goto L_0x016f;
    L_0x015d:
        r19 = 1;
        r0 = r19;
        r0 = new java.lang.String[r0];
        r19 = r0;
        r20 = 0;
        r19[r20] = r15;
        r0 = r19;
        r2.addressLine = r0;
        goto L_0x00eb;
    L_0x016f:
        r19 = 1;
        r0 = r19;
        r0 = new java.lang.String[r0];
        r19 = r0;
        r20 = 0;
        r21 = new java.lang.StringBuilder;
        r21.<init>();
        r0 = r21;
        r21 = r0.append(r15);
        r22 = " ";
        r21 = r21.append(r22);
        r0 = r21;
        r21 = r0.append(r13);
        r21 = r21.toString();
        r19[r20] = r21;
        r0 = r19;
        r2.addressLine = r0;
        goto L_0x00eb;
    L_0x019c:
        r19 = move-exception;
        goto L_0x0060;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.wallet.instrumentmanager.common.address.GooglePlacesAddressSource.convertJsonObjectToPostalAddress(org.json.JSONObject, java.lang.String):com.google.location.country.Postaladdress$PostalAddress");
    }
}
