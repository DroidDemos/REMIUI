package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.jw;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Api<O extends ApiOptions> {
    private final b<?, O> Sa;
    private final c<?> Sb;
    private final ArrayList<Scope> Sc;

    public interface b<T extends a, O> {
        T a(Context context, Looper looper, jw jwVar, O o, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener);

        int getPriority();
    }

    public interface ApiOptions {

        public interface HasOptions extends ApiOptions {
        }

        public interface NotRequiredOptions extends ApiOptions {
        }

        public static final class NoOptions implements NotRequiredOptions {
            private NoOptions() {
            }
        }
    }

    public interface a {
        void connect();

        void disconnect();

        boolean isConnected();
    }

    public static final class c<C extends a> {
    }

    public <C extends a> Api(b<C, O> clientBuilder, c<C> clientKey, Scope... impliedScopes) {
        this.Sa = clientBuilder;
        this.Sb = clientKey;
        this.Sc = new ArrayList(Arrays.asList(impliedScopes));
    }

    public b<?, O> hT() {
        return this.Sa;
    }

    public List<Scope> hU() {
        return this.Sc;
    }

    public c<?> hV() {
        return this.Sb;
    }
}
