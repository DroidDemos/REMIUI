package com.google.android.finsky.billing;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.PlayStore.PromptForFopData;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.config.G;
import com.google.android.finsky.config.PreferenceFile.SharedPreference;
import com.google.android.finsky.protos.BuyInstruments.CheckInstrumentResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Utils;

public class PromptForFopHelper {
    public static void refreshHasFopCacheIfNecessary(DfeApi dfeApi) {
        final String accountName = dfeApi.getAccountName();
        long nowMs = System.currentTimeMillis();
        if (isExperimentEnabled(accountName)) {
            if (!isHasFopCacheValid(accountName, nowMs)) {
                if (!isSnoozed(accountName, nowMs)) {
                    dfeApi.checkInstrument(new Listener<CheckInstrumentResponse>() {
                        public void onResponse(CheckInstrumentResponse checkInstrumentResponse) {
                            PromptForFopHelper.updateHasValidFopCache(accountName, checkInstrumentResponse.userHasValidInstrument);
                        }
                    }, new ErrorListener() {
                        public void onErrorResponse(VolleyError volleyError) {
                            FinskyLog.e("Error while checking for offers: %s", volleyError);
                        }
                    });
                } else if (FinskyLog.DEBUG) {
                    FinskyLog.d("Not checking for valid FOP because snoozed. (account=%s)", FinskyLog.scrubPii(accountName));
                }
            }
        } else if (FinskyLog.DEBUG) {
            FinskyLog.d("Not checking for valid FOP because experiment is disabled. (account=%s)", FinskyLog.scrubPii(accountName));
        }
    }

    public static boolean shouldPromptForFop(String accountName) {
        long nowMs = System.currentTimeMillis();
        return isExperimentEnabled(accountName) && isHasFopCacheValid(accountName, nowMs) && !((Boolean) FinskyPreferences.accountHasFop.get(accountName).get()).booleanValue() && !isSnoozed(accountName, nowMs);
    }

    public static void expireHasNoFop(String accountName) {
        FinskyPreferences.accountHasFopLastUpdateMs.get(accountName).remove();
        FinskyLog.d("Invalidated has_fop cache. (account=%s)", FinskyLog.scrubPii(accountName));
        snooze(accountName);
    }

    public static void snooze(String accountName) {
        FinskyPreferences.promptForFopLastSnoozedTimestampMs.get(accountName).put(Long.valueOf(System.currentTimeMillis()));
        int oldNumSnoozed = ((Integer) FinskyPreferences.promptForFopNumSnoozed.get(accountName).get()).intValue();
        FinskyPreferences.promptForFopNumSnoozed.get(accountName).put(Integer.valueOf(oldNumSnoozed + 1));
        long snoozePeriodMs = getSnoozePeriodMs(oldNumSnoozed + 1);
        FinskyLog.d("Snoozing for %d ms (account=%s)", Long.valueOf(snoozePeriodMs), FinskyLog.scrubPii(accountName));
    }

    public static boolean isSnoozed(String accountName, long nowMs) {
        int numSnoozed = ((Integer) FinskyPreferences.promptForFopNumSnoozed.get(accountName).get()).intValue();
        if (numSnoozed < 1) {
            return false;
        }
        boolean isSnoozed;
        long snoozedTimestampMs = ((Long) FinskyPreferences.promptForFopLastSnoozedTimestampMs.get(accountName).get()).longValue();
        long snoozePeriodMs = getSnoozePeriodMs(numSnoozed);
        if (snoozePeriodMs < 0 || snoozedTimestampMs + snoozePeriodMs > nowMs) {
            isSnoozed = true;
        } else {
            isSnoozed = false;
        }
        if (FinskyLog.DEBUG) {
            FinskyLog.d("is_snoozed=%b (account=%s)", Boolean.valueOf(isSnoozed), FinskyLog.scrubPii(accountName));
        }
        return isSnoozed;
    }

    private static long getSnoozePeriodMs(int snoozeNum) {
        long j = -1;
        String snoozeScheduleM = (String) G.promptForFopSnoozeScheduleM.get();
        if (snoozeScheduleM == null) {
            FinskyLog.d("No snooze schedule.", new Object[0]);
            return j;
        }
        String[] splitSnoozeScheduleM = Utils.commaUnpackStrings(snoozeScheduleM);
        int scheduleIndex = Math.min(snoozeNum - 1, splitSnoozeScheduleM.length - 1);
        if (scheduleIndex < 0) {
            FinskyLog.e("Invalid snooze schedule: %s", snoozeScheduleM);
            return j;
        }
        try {
            return (Long.parseLong(snoozePeriodM) * 60) * 1000;
        } catch (NumberFormatException e) {
            FinskyLog.e("Invalid snooze period: %s. Schedule: %s", splitSnoozeScheduleM[scheduleIndex], snoozeScheduleM);
            return j;
        }
    }

    private static void updateHasValidFopCache(String accountName, boolean hasFop) {
        FinskyPreferences.accountHasFop.get(accountName).put(Boolean.valueOf(hasFop));
        FinskyPreferences.accountHasFopLastUpdateMs.get(accountName).put(Long.valueOf(System.currentTimeMillis()));
        FinskyLog.d("has_fop cache updated to: %b (account=%s)", Boolean.valueOf(hasFop), FinskyLog.scrubPii(accountName));
    }

    private static boolean isHasFopCacheValid(String accountName, long nowMs) {
        if (((Boolean) FinskyPreferences.accountHasFop.get(accountName).get()).booleanValue()) {
            if (FinskyLog.DEBUG) {
                FinskyLog.d("has_fop=true cache valid. (account=%s)", FinskyLog.scrubPii(accountName));
            }
            return true;
        }
        if (((Long) G.hasFopCacheTimeoutMs.get()).longValue() + ((Long) FinskyPreferences.accountHasFopLastUpdateMs.get(accountName).get()).longValue() > nowMs) {
            if (FinskyLog.DEBUG) {
                FinskyLog.d("has_fop=false cache valid. (account=%s)", FinskyLog.scrubPii(accountName));
            }
            return true;
        }
        FinskyLog.d("has_fop=false cache invalid. (account=%s)", FinskyLog.scrubPii(accountName));
        return false;
    }

    private static boolean isExperimentEnabled(String accountName) {
        return FinskyApp.get().getExperiments(accountName).isEnabled("cl:billing.prompt_for_fop");
    }

    public static void recordDialogImpression(String accountName) {
        SharedPreference<Integer> pref = FinskyPreferences.promptForFopNumDialogImpressions.get(accountName);
        pref.put(Integer.valueOf(((Integer) pref.get()).intValue() + 1));
    }

    public static void recordFopSelectorImpression(String accountName) {
        SharedPreference<Integer> pref = FinskyPreferences.promptForFopNumFopSelectorImpressions.get(accountName);
        pref.put(Integer.valueOf(((Integer) pref.get()).intValue() + 1));
    }

    public static void recordFopAdded(String accountName) {
        FinskyPreferences.promptForFopAddedFop.get(accountName).put(Boolean.valueOf(true));
    }

    public static PromptForFopData getSessionLoggingData(String accountName) {
        PromptForFopData result = new PromptForFopData();
        result.hasFop = ((Boolean) FinskyPreferences.accountHasFop.get(accountName).get()).booleanValue();
        result.hasHasFop = true;
        result.fopAdded = ((Boolean) FinskyPreferences.promptForFopAddedFop.get(accountName).get()).booleanValue();
        result.hasFopAdded = true;
        result.numDialogShown = ((Integer) FinskyPreferences.promptForFopNumDialogImpressions.get(accountName).get()).intValue();
        result.hasNumDialogShown = true;
        result.numFopSelectorShown = ((Integer) FinskyPreferences.promptForFopNumFopSelectorImpressions.get(accountName).get()).intValue();
        result.hasNumFopSelectorShown = true;
        result.numSnooze = ((Integer) FinskyPreferences.promptForFopNumSnoozed.get(accountName).get()).intValue();
        result.hasNumSnooze = true;
        return result;
    }
}
