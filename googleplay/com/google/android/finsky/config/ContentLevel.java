package com.google.android.finsky.config;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.finsky.api.AccountHandler;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Utils;

public enum ContentLevel {
    EVERYONE(0),
    LOW_MATURITY(1),
    MEDIUM_MATURITY(2),
    HIGH_MATURITY(3),
    SHOW_ALL(4);
    
    private static final ContentLevel DEFAULT_VALUE;
    private final int mValue;

    static {
        DEFAULT_VALUE = convertFromLegacyValue(((Integer) G.vendingContentRating.get()).intValue());
    }

    private ContentLevel(int legacyValue) {
        this.mValue = legacyValue;
    }

    public int getValue() {
        return this.mValue;
    }

    public static ContentLevel importFromSettings(Context context) {
        int modernValue = ((Integer) FinskyPreferences.contentFilterLevel.get()).intValue();
        if (modernValue == -1) {
            int migratedModernValue = migrateOldLevels(context, FinskyPreferences.getPreferencesFile().open());
            if (migratedModernValue != -1) {
                FinskyPreferences.contentFilterLevel.put(Integer.valueOf(migratedModernValue));
                modernValue = migratedModernValue;
            }
        }
        if (modernValue >= 0) {
            for (ContentLevel level : values()) {
                if (level.mValue == modernValue) {
                    return level;
                }
            }
        }
        return DEFAULT_VALUE;
    }

    public static ContentLevel convertFromLegacyValue(int legacyValue) {
        if (legacyValue == -1) {
            return HIGH_MATURITY;
        }
        for (ContentLevel level : values()) {
            if (level.mValue == legacyValue) {
                return level;
            }
        }
        return HIGH_MATURITY;
    }

    public int getDfeValue() {
        if (this.mValue == SHOW_ALL.getValue()) {
            return HIGH_MATURITY.getValue();
        }
        return this.mValue;
    }

    private static int migrateOldLevels(Context context, SharedPreferences preferences) {
        boolean perAccountFound = false;
        int perAccountMinimum = HIGH_MATURITY.getDfeValue();
        Account[] listOfAccounts = AccountHandler.getAccounts(context);
        for (int accountNum = listOfAccounts.length - 1; accountNum >= 0; accountNum--) {
            int perAccountValue = preferences.getInt(Utils.getPreferenceKey(FinskyPreferences.contentFilterLevel.getKey(), listOfAccounts[accountNum].name), -1);
            if (perAccountValue >= 0) {
                perAccountFound = true;
                perAccountMinimum = Math.min(perAccountMinimum, perAccountValue);
            }
        }
        if (perAccountFound) {
            return perAccountMinimum;
        }
        SharedPreference<Integer> legacyContentRatingPreference = new PreferenceFile("vending_preferences", 0).value("content_rating", Integer.valueOf(-1));
        if (legacyContentRatingPreference.exists()) {
            int legacyValue = ((Integer) legacyContentRatingPreference.get()).intValue();
            for (ContentLevel level : values()) {
                if (level.mValue == legacyValue) {
                    return level.mValue;
                }
            }
        }
        return -1;
    }
}
