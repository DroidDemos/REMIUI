package com.google.android.gms.car;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.kn;

public final class Car {
    public static final Api<CarOptions> API;
    private static final com.google.android.gms.common.api.Api.b<e, CarOptions> CLIENT_BUILDER;
    static final c<e> CLIENT_KEY;
    public static final CarApi CarApi;
    public static final CarFirstPartyApi CarFirstPartyApi;

    public interface CarConnectionListener {
        void onConnected(int i);

        void onDisconnected();
    }

    public interface CarActivityStartListener {
        void onActivityStarted(Intent intent);

        void onNewActivityRequest(Intent intent);
    }

    public interface CarApi {
        boolean isConnectedToCar(GoogleApiClient googleApiClient) throws IllegalStateException;
    }

    public interface CarFirstPartyApi {
    }

    public static final class CarOptions implements HasOptions {
        final CarConnectionListener IT;

        public static final class Builder {
            private final CarConnectionListener IU;

            private Builder(CarConnectionListener carConnectionListener) {
                this.IU = carConnectionListener;
            }

            public CarOptions build() {
                return new CarOptions();
            }
        }

        private CarOptions(Builder builder) {
            this.IT = builder.IU;
        }

        public static Builder builder(CarConnectionListener carConnectionListener) {
            return new Builder(carConnectionListener);
        }
    }

    private static final class a implements CarApi {
        private a() {
        }

        public boolean isConnectedToCar(GoogleApiClient client) throws IllegalStateException {
            Car.b(client);
            return ((e) client.a(Car.CLIENT_KEY)).gh();
        }
    }

    private static final class b implements CarFirstPartyApi {
        private b() {
        }
    }

    static {
        CLIENT_KEY = new c();
        CLIENT_BUILDER = new com.google.android.gms.common.api.Api.b<e, CarOptions>() {
            public e a(Context context, Looper looper, jw jwVar, CarOptions carOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                kn.b((Object) carOptions, (Object) "Setting the API options is required.");
                return new e(context, looper, carOptions.IT, connectionCallbacks, onConnectionFailedListener);
            }

            public int getPriority() {
                return Integer.MAX_VALUE;
            }
        };
        API = new Api(CLIENT_BUILDER, CLIENT_KEY, new Scope[0]);
        CarApi = new a();
        CarFirstPartyApi = new b();
    }

    private static void b(GoogleApiClient googleApiClient) {
        if (googleApiClient == null) {
            throw new NullPointerException("GoogleApiClient is null");
        }
    }

    public static GoogleApiClient buildGoogleApiClientForCar(Context context, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener connectionFailedListener, CarConnectionListener carConnectionListener) {
        return new Builder(context).addApi(API, CarOptions.builder(carConnectionListener).build()).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(connectionFailedListener).build();
    }
}
