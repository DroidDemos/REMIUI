package com.google.android.finsky.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.Notifications.Notification;
import com.google.android.finsky.utils.FinskyLog;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;

public class ServerNotificationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE") && "google.com".equals(intent.getStringExtra("from")) && intent.getCategories().contains("SERVER_NOTIFICATION")) {
            setResultCode(-1);
            Bundle bundle = intent.getExtras();
            if (bundle.containsKey("NOTIFICATION_PAYLOAD")) {
                byte[] notificationData = Base64.decode(bundle.getString("NOTIFICATION_PAYLOAD"), 11);
                if (notificationData != null) {
                    Notification notification = null;
                    try {
                        notification = Notification.parseFrom(notificationData);
                    } catch (InvalidProtocolBufferNanoException e) {
                        FinskyLog.e("Received download tickle with malformed notification proto data.", new Object[0]);
                    }
                    if (notification != null) {
                        FinskyLog.d("Handling notificationId=[%s]", notification.notificationId);
                        FinskyApp.get().getDfeNotificationManager().processNotification(notification);
                        return;
                    }
                    return;
                }
                return;
            }
            FinskyLog.d("Ignoring server broadcast due to empty notification string.", new Object[0]);
            tryHandleOldStyleNotification(context, intent);
        }
    }

    private void tryHandleOldStyleNotification(Context context, Intent intent) {
        String notificationMessage = intent.getStringExtra("server_notification_message");
        String dialogMessage = intent.getStringExtra("server_dialog_message");
        if (notificationMessage == null || dialogMessage == null) {
            FinskyLog.d("Could not handle old style notification.", new Object[0]);
            return;
        }
        FinskyApp.get().getNotifier().showMessage(intent.hasExtra("server_notification_title") ? intent.getStringExtra("server_notification_title") : context.getString(R.string.notification_server_notification_title), intent.hasExtra("server_notification_status") ? intent.getStringExtra("server_notification_status") : context.getString(R.string.notification_server_notification_status), notificationMessage);
        FinskyLog.d("Handled old style notification.", new Object[0]);
    }
}
