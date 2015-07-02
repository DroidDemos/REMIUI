package com.google.android.finsky.utils.hats;

import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.experiments.FinskyExperiments;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.FinskyPreferences;

public class HatsUtils {
    private static boolean isSurveyEligible(String siteId) {
        SharedPreference<Long> pref = FinskyPreferences.lastSurveyActionMs.get(siteId);
        if (pref != null && pref.exists()) {
            return false;
        }
        return true;
    }

    public static void persistSurveyTimestampAction(String siteId) {
        SharedPreference<Long> pref = FinskyPreferences.lastSurveyActionMs.get(siteId);
        if (pref != null) {
            pref.put(Long.valueOf(System.currentTimeMillis()));
        }
    }

    public static String getSiteId(NavigationManager navigationManager) {
        FinskyExperiments experiments = FinskyApp.get().getExperiments();
        if (navigationManager.canPromptSearchSurveyForCurrent()) {
            if (experiments.isEnabled("cl:ui.happiness_survey_in_search_with_snows") && isSurveyEligible((String) G.surveySiteIdSearchResults.get())) {
                return (String) G.surveySiteIdSearchResults.get();
            }
            if (experiments.isEnabled("cl:ui.happiness_survey_in_search_no_snows") && isSurveyEligible((String) G.surveySiteIdSearchResultsControlGroup.get())) {
                return (String) G.surveySiteIdSearchResultsControlGroup.get();
            }
        }
        if (experiments.isEnabled("cl:ui.happiness_survey_in_home") && isSurveyEligible((String) G.surveySiteIdOverallApp.get())) {
            return (String) G.surveySiteIdOverallApp.get();
        }
        return null;
    }
}
