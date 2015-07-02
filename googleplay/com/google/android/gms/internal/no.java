package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.a;
import com.google.android.gms.common.api.Api.b;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;

public final class no {
    public static final Api<NoOptions> API;
    static final b<nq, NoOptions> CLIENT_BUILDER;
    static final c<nq> CLIENT_KEY;
    public static final np asf;

    static {
        CLIENT_KEY = new c();
        CLIENT_BUILDER = new b<nq, NoOptions>() {
            public /* synthetic */ a a(Context context, Looper looper, jw jwVar, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return k(context, looper, jwVar, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
            }

            public int getPriority() {
                return Integer.MAX_VALUE;
            }

            public nq k(Context context, Looper looper, jw jwVar, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return new nq(context, looper, connectionCallbacks, onConnectionFailedListener);
            }
        };
        API = new Api(CLIENT_BUILDER, CLIENT_KEY, new Scope[0]);
        asf = new nr(CLIENT_KEY);
    }
}
