package com.google.android.gms.common;

import android.os.Bundle;

@Deprecated
public interface GooglePlayServicesClient {

    @Deprecated
    public interface ConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onDisconnected();
    }

    @Deprecated
    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }
}
