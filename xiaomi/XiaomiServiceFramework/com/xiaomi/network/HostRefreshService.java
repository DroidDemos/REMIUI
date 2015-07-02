package com.xiaomi.network;

import android.app.IntentService;
import android.content.Intent;

public class HostRefreshService extends IntentService {
    public HostRefreshService(String name) {
        super(name);
    }

    protected void onHandleIntent(Intent intent) {
        HostManager.getInstance().refreshFallbacks();
    }
}
