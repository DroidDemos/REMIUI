package com.google.android.gms.common.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ApiOptions.NotRequiredOptions;
import com.google.android.gms.common.api.Api.a;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.kn;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GoogleApiClient {

    public interface ConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener extends com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public static final class Builder {
        private String Fl;
        private Looper JL;
        private OnConnectionFailedListener SA;
        private final Set<ConnectionCallbacks> SB;
        private final Set<OnConnectionFailedListener> SC;
        private final Set<String> St;
        private int Su;
        private View Sv;
        private String Sw;
        private final Map<Api<?>, ApiOptions> Sx;
        private FragmentActivity Sy;
        private int Sz;
        private final Context mContext;

        public Builder(Context context) {
            this.St = new HashSet();
            this.Sx = new HashMap();
            this.Sz = -1;
            this.SB = new HashSet();
            this.SC = new HashSet();
            this.mContext = context;
            this.JL = context.getMainLooper();
            this.Sw = context.getPackageName();
        }

        public Builder(Context context, ConnectionCallbacks connectedListener, OnConnectionFailedListener connectionFailedListener) {
            this(context);
            kn.b((Object) connectedListener, (Object) "Must provide a connected listener");
            this.SB.add(connectedListener);
            kn.b((Object) connectionFailedListener, (Object) "Must provide a connection failed listener");
            this.SC.add(connectionFailedListener);
        }

        private GoogleApiClient ie() {
            g a = g.a(this.Sy);
            GoogleApiClient cP = a.cP(this.Sz);
            if (cP == null) {
                cP = new c(this.mContext.getApplicationContext(), this.JL, id(), this.Sx, this.SB, this.SC, this.Sz);
            }
            a.a(this.Sz, cP, this.SA);
            return cP;
        }

        public Builder addApi(Api<? extends NotRequiredOptions> api) {
            this.Sx.put(api, null);
            List hU = api.hU();
            int size = hU.size();
            for (int i = 0; i < size; i++) {
                this.St.add(((Scope) hU.get(i)).ik());
            }
            return this;
        }

        public <O extends HasOptions> Builder addApi(Api<O> api, O options) {
            kn.b((Object) options, (Object) "Null options are not permitted for this Api");
            this.Sx.put(api, options);
            List hU = api.hU();
            int size = hU.size();
            for (int i = 0; i < size; i++) {
                this.St.add(((Scope) hU.get(i)).ik());
            }
            return this;
        }

        public Builder addConnectionCallbacks(ConnectionCallbacks listener) {
            this.SB.add(listener);
            return this;
        }

        public Builder addOnConnectionFailedListener(OnConnectionFailedListener listener) {
            this.SC.add(listener);
            return this;
        }

        public GoogleApiClient build() {
            kn.b(!this.Sx.isEmpty(), (Object) "must call addApi() to add at least one API");
            return this.Sz >= 0 ? ie() : new c(this.mContext, this.JL, id(), this.Sx, this.SB, this.SC, -1);
        }

        public jw id() {
            return new jw(this.Fl, this.St, this.Su, this.Sv, this.Sw);
        }
    }

    <C extends a> C a(c<C> cVar);

    <A extends a, R extends Result, T extends BaseImplementation.a<R, A>> T a(T t);

    void connect();

    void disconnect();

    Looper getLooper();

    boolean isConnected();

    boolean isConnecting();

    void registerConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    void registerConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);

    void unregisterConnectionCallbacks(ConnectionCallbacks connectionCallbacks);

    void unregisterConnectionFailedListener(OnConnectionFailedListener onConnectionFailedListener);
}
