package com.google.android.gms.deviceconnection;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.deviceconnection.features.DeviceFeatureBuffer;
import com.google.android.gms.internal.jw;
import com.google.android.gms.internal.lm;

public final class DeviceConnections {
    public static final Api<NoOptions> API;
    private static final com.google.android.gms.common.api.Api.b<lm, NoOptions> CLIENT_BUILDER;
    static final c<lm> CLIENT_KEY;

    public static abstract class a<R extends Result> extends com.google.android.gms.common.api.BaseImplementation.a<R, lm> {
        public a(GoogleApiClient googleApiClient) {
            super(DeviceConnections.CLIENT_KEY, googleApiClient);
        }
    }

    private static abstract class b extends a<GetDeviceFeaturesResult> {
        private b(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result b(Status status) {
            return v(status);
        }

        public GetDeviceFeaturesResult v(final Status status) {
            return new GetDeviceFeaturesResult(this) {
                final /* synthetic */ b XW;

                public Status getStatus() {
                    return status;
                }

                public DeviceFeatureBuffer getSummaries() {
                    return new DeviceFeatureBuffer(DataHolder.cX(status.getStatusCode()));
                }

                public void release() {
                }
            };
        }
    }

    public interface GetDeviceFeaturesResult extends Releasable, Result {
        DeviceFeatureBuffer getSummaries();
    }

    static {
        CLIENT_KEY = new c();
        CLIENT_BUILDER = new com.google.android.gms.common.api.Api.b<lm, NoOptions>() {
            public /* synthetic */ com.google.android.gms.common.api.Api.a a(Context context, Looper looper, jw jwVar, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return h(context, looper, jwVar, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
            }

            public int getPriority() {
                return Integer.MAX_VALUE;
            }

            public lm h(Context context, Looper looper, jw jwVar, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
                return new lm(context, looper, connectionCallbacks, onConnectionFailedListener, jwVar.iI());
            }
        };
        API = new Api(CLIENT_BUILDER, CLIENT_KEY, new Scope[0]);
    }

    public static PendingResult<GetDeviceFeaturesResult> getDeviceFeaturesRestricted(GoogleApiClient apiClient) {
        return apiClient.a(new b(apiClient) {
            protected void a(lm lmVar) {
                lmVar.g(this);
            }
        });
    }
}
