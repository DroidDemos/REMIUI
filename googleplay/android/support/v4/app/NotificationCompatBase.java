package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.RemoteInputCompatBase.RemoteInput;

class NotificationCompatBase {

    public static abstract class Action {

        public interface Factory {
        }

        protected abstract PendingIntent getActionIntent();

        protected abstract Bundle getExtras();

        protected abstract int getIcon();

        protected abstract RemoteInput[] getRemoteInputs();

        protected abstract CharSequence getTitle();
    }
}
