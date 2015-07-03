package com.xiaomi.passport.utils;

import android.content.Context;
import android.util.Log;
import com.xiaomi.account.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PhoneNumUtil {
    private static final String TAG = "PhoneNumUtil";
    private static String pattern;
    private static Pattern regex;
    private static Context sContext;
    private static HashMap<String, CountryPhoneNumData> sMapCountryPhoneData;
    private static HashMap<String, CountryPhoneNumData> sMapRecommendCountryPhoneData;

    public static class CountryPhoneNumData implements Comparable<CountryPhoneNumData> {
        public String countryCode;
        public String countryISO;
        public String countryName;
        ArrayList<Integer> lengths;
        ArrayList<String> prefix;

        public CountryPhoneNumData(String name, String code, String iso) {
            this.countryName = name;
            this.countryCode = code;
            this.countryISO = iso;
        }

        public int compareTo(CountryPhoneNumData another) {
            return this.countryName.compareTo(another.countryName);
        }
    }

    static {
        pattern = "(\\+)?\\d{1,20}";
        regex = Pattern.compile(pattern);
    }

    public static void initializeCountryPhoneData(Context context) {
        if (sContext == null) {
            sContext = context;
        }
    }

    public static synchronized void ensureDataLoaded() {
        synchronized (PhoneNumUtil.class) {
            if (sMapCountryPhoneData == null || sMapRecommendCountryPhoneData == null) {
                sMapCountryPhoneData = new HashMap();
                sMapRecommendCountryPhoneData = new HashMap();
                String strCountries = "";
                try {
                    InputStream is = sContext.getResources().openRawResource(R.raw.passport_countries);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[512];
                    while (true) {
                        int length = is.read(buffer);
                        if (length == -1) {
                            break;
                        }
                        baos.write(buffer, 0, length);
                    }
                    strCountries = baos.toString();
                    baos.close();
                    JSONObject obj = new JSONObject(strCountries);
                    sMapCountryPhoneData = processCountryDataFromJson(obj.getJSONArray("countries"));
                    sMapRecommendCountryPhoneData = processCountryDataFromJson(obj.getJSONArray("recommend countries"));
                } catch (IOException e) {
                    Log.e(TAG, "error when load area codes", e);
                } catch (JSONException e2) {
                    Log.e(TAG, "error when parse json", e2);
                }
            }
        }
    }

    public static HashMap<String, CountryPhoneNumData> processCountryDataFromJson(JSONArray arrayObj) throws JSONException {
        HashMap<String, CountryPhoneNumData> mapData = new HashMap();
        for (int i = 0; i < arrayObj.length(); i++) {
            int j;
            JSONObject oneObj = arrayObj.getJSONObject(i);
            String name = oneObj.getString("cn");
            String code = oneObj.getString("ic");
            String iso = oneObj.getString("iso");
            CountryPhoneNumData data = new CountryPhoneNumData(name, code, iso);
            JSONArray lengthAry = oneObj.optJSONArray("len");
            if (lengthAry != null) {
                ArrayList<Integer> lengths = new ArrayList();
                for (j = 0; j < lengthAry.length(); j++) {
                    lengths.add(Integer.valueOf(lengthAry.getInt(j)));
                }
                data.lengths = lengths;
            }
            JSONArray prefixAry = oneObj.optJSONArray("mc");
            if (prefixAry != null) {
                ArrayList<String> prefixes = new ArrayList();
                for (j = 0; j < prefixAry.length(); j++) {
                    prefixes.add(prefixAry.getString(j));
                }
                data.prefix = prefixes;
            }
            mapData.put(iso, data);
        }
        return mapData;
    }

    public static List<String> getCountryList() {
        ensureDataLoaded();
        ArrayList<String> countries = new ArrayList(sMapCountryPhoneData.size());
        for (Entry<String, CountryPhoneNumData> oneEntry : sMapCountryPhoneData.entrySet()) {
            countries.add(((CountryPhoneNumData) oneEntry.getValue()).countryName);
        }
        Collections.sort(countries);
        return countries;
    }

    public static List<CountryPhoneNumData> getCountryPhoneNumDataList() {
        return getCountryPhoneNumDataListFromData(sMapCountryPhoneData);
    }

    public static List<CountryPhoneNumData> getRecommendCountryPhoneNumDataList() {
        return getCountryPhoneNumDataListFromData(sMapRecommendCountryPhoneData);
    }

    private static List<CountryPhoneNumData> getCountryPhoneNumDataListFromData(HashMap<String, CountryPhoneNumData> mapData) {
        ensureDataLoaded();
        ArrayList<CountryPhoneNumData> dataList = new ArrayList(mapData.size());
        for (CountryPhoneNumData data : mapData.values()) {
            dataList.add(data);
        }
        Collections.sort(dataList);
        return dataList;
    }

    public static String getCountryISOFromPhoneNum(String phone) {
        if (!phone.startsWith("+")) {
            return "CN";
        }
        ensureDataLoaded();
        for (Entry<String, CountryPhoneNumData> oneEntry : sMapCountryPhoneData.entrySet()) {
            if (phone.startsWith("+" + ((CountryPhoneNumData) oneEntry.getValue()).countryCode)) {
                return (String) oneEntry.getKey();
            }
        }
        return "";
    }

    public static CountryPhoneNumData getCounrtyPhoneDataFromIso(String iso) {
        ensureDataLoaded();
        CountryPhoneNumData data = (CountryPhoneNumData) sMapRecommendCountryPhoneData.get(iso.toUpperCase());
        return data != null ? data : (CountryPhoneNumData) sMapCountryPhoneData.get(iso.toUpperCase());
    }

    public static String checkNumber(String phoneNumber, CountryPhoneNumData data) {
        if (phoneNumber.startsWith("+") || phoneNumber.startsWith("00") || data == null || !regex.matcher(phoneNumber).matches()) {
            Log.e(TAG, "phoneNumber \u4e3a\u7a7a\u6216\u8005\u662f data\u4e3a\u7a7a phoneNumber = " + phoneNumber + " data = " + data.toString());
            return null;
        }
        Iterator i$;
        if (data.lengths != null) {
            boolean matchLength = false;
            i$ = data.lengths.iterator();
            while (i$.hasNext()) {
                if (phoneNumber.length() == ((Integer) i$.next()).intValue()) {
                    matchLength = true;
                    break;
                }
            }
            if (!matchLength) {
                return null;
            }
        }
        if (data.prefix != null) {
            boolean matchPrefix = false;
            i$ = data.prefix.iterator();
            while (i$.hasNext()) {
                String prefix = (String) i$.next();
                if (prefix.startsWith("x")) {
                    int lastX = prefix.lastIndexOf("x");
                    if (lastX <= phoneNumber.length() && phoneNumber.substring(lastX + 1).startsWith(prefix.substring(lastX + 1))) {
                        matchPrefix = true;
                        break;
                    }
                } else if (phoneNumber.startsWith(prefix)) {
                    matchPrefix = true;
                    break;
                }
            }
            if (!matchPrefix) {
                return null;
            }
        }
        if (data.countryISO.equals("CN")) {
            return phoneNumber;
        }
        StringBuilder phoneNumBuilder = new StringBuilder();
        phoneNumBuilder.append("+").append(data.countryCode).append(phoneNumber);
        return phoneNumBuilder.toString();
    }
}
