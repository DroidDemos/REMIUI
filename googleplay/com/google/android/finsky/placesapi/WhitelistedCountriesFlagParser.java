package com.google.android.finsky.placesapi;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Sets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class WhitelistedCountriesFlagParser {
    private Set<String> mEnabledCountries;

    private static class ParseException extends Exception {
        ParseException(String detailMessage) {
            super(detailMessage);
        }
    }

    public WhitelistedCountriesFlagParser(Context context) {
        try {
            this.mEnabledCountries = parse((String) G.whitelistedPlacesApiCountries.get(), getApplicationVersion(context));
        } catch (ParseException e) {
            FinskyLog.e("Malformatted format for places api whitelisting flag: %s", e);
        }
    }

    public boolean isCountryEnabled(String countryCode) {
        return this.mEnabledCountries != null && this.mEnabledCountries.contains(countryCode);
    }

    static Set<String> parse(String flag, int applicationVersion) throws ParseException {
        if (TextUtils.isEmpty(flag)) {
            return Collections.emptySet();
        }
        Set<String> enabledCountries = Sets.newHashSet();
        String[] arr$ = flag.split(";");
        int len$ = arr$.length;
        int i$ = 0;
        while (i$ < len$) {
            String part = arr$[i$];
            String[] versionCountries = part.split(":");
            if (versionCountries.length != 2) {
                throw new ParseException("The following part must have exactly one ':': " + part);
            }
            try {
                if (applicationVersion >= Integer.parseInt(versionCountries[0])) {
                    enabledCountries.addAll(Arrays.asList(versionCountries[1].split(",")));
                }
                i$++;
            } catch (NumberFormatException e) {
                throw new ParseException("Can't parse version: " + versionCountries[0]);
            }
        }
        return enabledCountries;
    }

    private static int getApplicationVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
