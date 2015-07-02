package com.google.android.gms.clearcut;

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
import com.google.android.gms.internal.jd;
import com.google.android.gms.internal.je;
import com.google.android.gms.internal.jw;

public final class ClearcutLogger {
    public static final Api<NoOptions> API;
    public static final b<je, NoOptions> CLIENT_BUILDER;
    public static final c<je> CLIENT_KEY;
    public static final ClearcutLoggerApi ClearcutLoggerApi;

    public interface MessageProducer {
    }

    static {
        CLIENT_KEY = new c();
        CLIENT_BUILDER = new b<je, NoOptions>() {
            public /* synthetic */ a a(Context context, Looper looper, jw jwVar, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return e(context, looper, jwVar, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
            }

            public je e(Context context, Looper looper, jw jwVar, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return new je(context, looper, connectionCallbacks, onConnectionFailedListener);
            }

            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api(CLIENT_BUILDER, CLIENT_KEY, new Scope[0]);
        ClearcutLoggerApi = new jd();
    }
}
