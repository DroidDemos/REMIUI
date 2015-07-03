package com.android.common.userhappiness;

import android.content.Context;
import android.content.Intent;
import com.android.common.speech.LoggingEvents;
import com.android.common.speech.LoggingEvents.VoiceIme;

public class UserHappinessSignals {
    private static boolean mHasVoiceLoggingInfo;

    static {
        mHasVoiceLoggingInfo = false;
    }

    public static void setHasVoiceLoggingInfo(boolean hasVoiceLogging) {
        mHasVoiceLoggingInfo = hasVoiceLogging;
    }

    public static void userAcceptedImeText(Context context) {
        if (mHasVoiceLoggingInfo) {
            Intent i = new Intent(LoggingEvents.ACTION_LOG_EVENT);
            i.putExtra(LoggingEvents.EXTRA_APP_NAME, VoiceIme.APP_NAME);
            i.putExtra(LoggingEvents.EXTRA_EVENT, 21);
            i.putExtra(LoggingEvents.EXTRA_CALLING_APP_NAME, context.getPackageName());
            i.putExtra(LoggingEvents.EXTRA_TIMESTAMP, System.currentTimeMillis());
            context.sendBroadcast(i);
            setHasVoiceLoggingInfo(false);
        }
    }
}
