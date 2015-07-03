package com.android.providers.telephony;

import android.content.ContentProvider;

public class Injector {
    public static final void setMmsSmsProviderOps(ContentProvider contentProvider) {
        if (contentProvider instanceof MmsProvider) {
            contentProvider.setAppOps(47, 48);
        } else {
            contentProvider.setAppOps(21, 22);
        }
    }
}
