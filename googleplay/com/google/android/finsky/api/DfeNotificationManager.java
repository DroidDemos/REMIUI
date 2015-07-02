package com.google.android.finsky.api;

import com.google.android.finsky.protos.Notifications.Notification;

public interface DfeNotificationManager {
    void processNotification(Notification notification);
}
