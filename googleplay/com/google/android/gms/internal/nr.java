package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.Api.c;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class nr implements np {
    private final c<nq> asg;

    private static abstract class a extends com.google.android.gms.common.api.BaseImplementation.a<Status, nq> {
        public a(c<nq> cVar, GoogleApiClient googleApiClient) {
            super(cVar, googleApiClient);
        }

        public /* synthetic */ Result b(Status status) {
            return f(status);
        }

        public Status f(Status status) {
            return status;
        }
    }

    public nr(c<nq> cVar) {
        this.asg = cVar;
    }

    public PendingResult<Status> a(GoogleApiClient googleApiClient, Integer num, Long l, Integer num2, Integer num3, Bundle bundle) {
        final Integer num4 = num;
        final Long l2 = l;
        final Integer num5 = num2;
        final Integer num6 = num3;
        final Bundle bundle2 = bundle;
        return googleApiClient.a(new a(this, this.asg, googleApiClient) {
            final /* synthetic */ nr asm;

            protected void a(nq nqVar) {
                Bundle bundle = new Bundle();
                if (num4 != null) {
                    bundle.putInt("latency_micros", num4.intValue());
                }
                if (l2 != null) {
                    bundle.putLong("latency_bps", l2.longValue());
                }
                if (num5 != null) {
                    bundle.putInt("latitude_e6", num5.intValue());
                }
                if (num6 != null) {
                    bundle.putInt("longitude_e6", num6.intValue());
                }
                if (!(bundle.isEmpty() && (bundle2 == null || bundle2.isEmpty()))) {
                    nqVar.b(bundle, bundle2);
                }
                b(Status.Th);
            }
        });
    }
}
