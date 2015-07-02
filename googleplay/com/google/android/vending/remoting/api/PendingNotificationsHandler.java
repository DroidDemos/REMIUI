package com.google.android.vending.remoting.api;

import android.content.Context;
import com.google.android.finsky.protos.VendingProtos.PendingNotificationsProto;

public interface PendingNotificationsHandler {
    boolean handlePendingNotifications(Context context, String str, PendingNotificationsProto pendingNotificationsProto, boolean z);
}
