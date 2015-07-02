package com.google.android.play.search;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.ArrayList;

public final class PlaySearchVoiceController extends BroadcastReceiver {
    private static final String VOICE_SEARCH_RESULT_ACTION;
    private static final Intent sVoiceIntent;
    private final PlaySearchController mController;
    private boolean mRegistered;

    static {
        VOICE_SEARCH_RESULT_ACTION = PlaySearchVoiceController.class.getPackage().getName() + "." + "VOICE_SEARCH_RESULT";
        sVoiceIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
    }

    public static boolean canPerformVoiceSearch(Context context) {
        return context.getPackageManager().queryIntentActivities(sVoiceIntent, 0).size() > 0;
    }

    public PlaySearchVoiceController(PlaySearchController controller) {
        this.mController = controller;
    }

    void requestVoiceSearch(Context context) {
        Intent result = new Intent(VOICE_SEARCH_RESULT_ACTION);
        result.setPackage(context.getPackageName());
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, result, 1073741824);
        Intent voiceSearchIntent = new Intent(sVoiceIntent);
        voiceSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        voiceSearchIntent.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", pending);
        register(context);
        context.startActivity(voiceSearchIntent);
    }

    private void register(Context context) {
        if (!this.mRegistered) {
            context.registerReceiver(this, new IntentFilter(VOICE_SEARCH_RESULT_ACTION));
            this.mRegistered = true;
        }
    }

    void cancelPendingVoiceSearch(Context context) {
        if (this.mRegistered) {
            context.unregisterReceiver(this);
            this.mRegistered = false;
        }
    }

    public void onReceive(Context context, Intent data) {
        ArrayList<String> matches = data.getStringArrayListExtra("android.speech.extra.RESULTS");
        if (!(matches == null || matches.isEmpty())) {
            this.mController.setQuery((String) matches.get(0));
        }
        cancelPendingVoiceSearch(context);
    }
}
