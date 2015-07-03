package com.xiaomi.passport.interfaces;

import android.app.Activity;

public interface ActivityInterface {
    void onPostCreate(Activity activity);

    void onPreCreate(Activity activity);

    boolean useTranslucentStatusBar(Activity activity);
}
