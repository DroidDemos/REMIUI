package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.util.a;
import com.google.android.gms.internal.kb.com/google/android/gms/internal/kb.b;
import com.google.android.gms.internal.kb.g;
import com.google.android.gms.people.Graph.LoadCirclesResult;
import com.google.android.gms.people.Graph.LoadPeopleResult;
import com.google.android.gms.people.PeopleClient.LoadPeopleOptions;
import com.google.android.gms.people.PeopleClient.OnDataChangedListener;
import com.google.android.gms.people.model.CircleBuffer;
import com.google.android.gms.people.model.PersonBuffer;
import java.util.Collection;
import java.util.HashMap;

public class rn extends kb<rg> {
    private static volatile Bundle aCy;
    private static volatile Bundle aCz;
    public final String Sw;
    private Long aCA;
    public final String aCw;
    private final HashMap<OnDataChangedListener, r> aCx;

    private final class af extends b<BaseImplementation.b<LoadPeopleResult>> implements LoadPeopleResult {
        private final Status EU;
        final /* synthetic */ rn aCC;
        private final PersonBuffer aCV;

        public af(rn rnVar, BaseImplementation.b<LoadPeopleResult> bVar, Status status, PersonBuffer personBuffer) {
            this.aCC = rnVar;
            super(rnVar, bVar);
            this.EU = status;
            this.aCV = personBuffer;
        }

        public PersonBuffer getPeople() {
            return this.aCV;
        }

        public Status getStatus() {
            return this.EU;
        }

        protected /* synthetic */ void i(Object obj) {
            p((BaseImplementation.b) obj);
        }

        protected void iQ() {
            release();
        }

        protected void p(BaseImplementation.b<LoadPeopleResult> bVar) {
            if (bVar != null) {
                bVar.d(this);
            }
        }

        public void release() {
            if (this.aCV != null) {
                this.aCV.close();
            }
        }
    }

    private final class c extends b<BaseImplementation.b<LoadCirclesResult>> implements LoadCirclesResult {
        private final Status EU;
        final /* synthetic */ rn aCC;
        private final CircleBuffer aCD;

        public c(rn rnVar, BaseImplementation.b<LoadCirclesResult> bVar, Status status, CircleBuffer circleBuffer) {
            this.aCC = rnVar;
            super(rnVar, bVar);
            this.EU = status;
            this.aCD = circleBuffer;
        }

        public CircleBuffer getCircles() {
            return this.aCD;
        }

        public Status getStatus() {
            return this.EU;
        }

        protected /* synthetic */ void i(Object obj) {
            p((BaseImplementation.b) obj);
        }

        protected void iQ() {
            release();
        }

        protected void p(BaseImplementation.b<LoadCirclesResult> bVar) {
            if (bVar != null) {
                bVar.d(this);
            }
        }

        public void release() {
            if (this.aCD != null) {
                this.aCD.close();
            }
        }
    }

    private final class e extends b<OnDataChangedListener> {
        private final String aBg;
        final /* synthetic */ rn aCC;
        private final int aCF;
        private final String mAccount;

        public e(rn rnVar, OnDataChangedListener onDataChangedListener, String str, String str2, int i) {
            this.aCC = rnVar;
            super(rnVar, onDataChangedListener);
            this.mAccount = str;
            this.aBg = str2;
            this.aCF = i;
        }

        protected void b(OnDataChangedListener onDataChangedListener) {
            if (onDataChangedListener != null) {
                synchronized (this.aCC.aCx) {
                    if (this.aCC.aCx.containsKey(onDataChangedListener)) {
                        onDataChangedListener.onDataChanged(this.mAccount, this.aBg, this.aCF);
                        return;
                    }
                }
            }
        }

        protected /* synthetic */ void i(Object obj) {
            b((OnDataChangedListener) obj);
        }

        protected void iQ() {
        }
    }

    private final class p extends ra {
        final /* synthetic */ rn aCC;
        private final BaseImplementation.b<LoadCirclesResult> auM;

        public p(rn rnVar, BaseImplementation.b<LoadCirclesResult> bVar) {
            this.aCC = rnVar;
            this.auM = bVar;
        }

        public void a(int i, Bundle bundle, DataHolder dataHolder) {
            CircleBuffer circleBuffer = null;
            if (ro.qQ()) {
                ro.t("PeopleClient", "Circles callback: status=" + i + "\nresolution=" + bundle + "\nholder=" + dataHolder);
            }
            Status b = rn.a(i, null, bundle);
            if (dataHolder != null) {
                circleBuffer = new CircleBuffer(dataHolder);
            }
            this.aCC.b(new c(this.aCC, this.auM, b, circleBuffer));
        }
    }

    private final class r extends ra {
        final /* synthetic */ rn aCC;
        private final OnDataChangedListener aCN;

        public void a(int i, Bundle bundle, Bundle bundle2) {
            if (ro.qQ()) {
                ro.t("PeopleClient", "Bundle callback: status=" + i + "\nresolution=" + bundle + "\nbundle=" + bundle2);
            }
            if (i != 0) {
                ro.w("PeopleClient", "Non-success data changed callback received.");
            } else {
                this.aCC.b(new e(this.aCC, this.aCN, bundle2.getString("account"), bundle2.getString("pagegaiaid"), bundle2.getInt("scope")));
            }
        }
    }

    private final class w extends ra {
        final /* synthetic */ rn aCC;
        private final BaseImplementation.b<LoadPeopleResult> auM;

        public w(rn rnVar, BaseImplementation.b<LoadPeopleResult> bVar) {
            this.aCC = rnVar;
            this.auM = bVar;
        }

        public void a(int i, Bundle bundle, DataHolder dataHolder) {
            if (ro.qQ()) {
                ro.t("PeopleClient", "People callback: status=" + i + "\nresolution=" + bundle + "\nholder=" + dataHolder);
            }
            this.aCC.b(new af(this.aCC, this.auM, rn.a(i, null, bundle), this.aCC.ac(dataHolder)));
        }
    }

    public rn(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, String str2) {
        super(context.getApplicationContext(), looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.aCx = new HashMap();
        this.aCA = null;
        this.aCw = str;
        this.Sw = str2;
    }

    @Deprecated
    public rn(Context context, GooglePlayServicesClient.ConnectionCallbacks connectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener onConnectionFailedListener, String str, String str2) {
        this(context.getApplicationContext(), context.getMainLooper(), new com.google.android.gms.internal.kb.c(connectionCallbacks), new g(onConnectionFailedListener), str, str2);
    }

    private static Status a(int i, String str, Bundle bundle) {
        return new Status(i, str, v(bundle));
    }

    private void a(BaseImplementation.b<LoadPeopleResult> bVar, String str, String str2, String str3, Collection<String> collection, int i, boolean z, long j, String str4, int i2, int i3, int i4) {
        oY();
        rf wVar = new w(this, bVar);
        try {
            qL().a(wVar, str, str2, str3, a.c(collection), i, z, j, str4, i2, i3, i4);
        } catch (RemoteException e) {
            wVar.a(8, null, null);
        }
    }

    private PersonBuffer ac(DataHolder dataHolder) {
        return dataHolder == null ? null : new PersonBuffer(dataHolder, new sb.b(aCz), new sb.a(aCy));
    }

    private static PendingIntent v(Bundle bundle) {
        return bundle == null ? null : (PendingIntent) bundle.getParcelable("pendingIntent");
    }

    protected void a(int i, IBinder iBinder, Bundle bundle) {
        if (i == 0 && bundle != null) {
            u(bundle.getBundle("post_init_configuration"));
        }
        super.a(i, iBinder, bundle == null ? null : bundle.getBundle("post_init_resolution"));
    }

    public void a(BaseImplementation.b<LoadPeopleResult> bVar, String str, String str2, LoadPeopleOptions loadPeopleOptions) {
        if (loadPeopleOptions == null) {
            loadPeopleOptions = LoadPeopleOptions.DEFAULT;
        }
        a(bVar, str, str2, loadPeopleOptions.getCircleId(), loadPeopleOptions.getQualifiedIds(), loadPeopleOptions.getProjection(), loadPeopleOptions.isPeopleOnly(), loadPeopleOptions.getChangedSince(), loadPeopleOptions.getQuery(), loadPeopleOptions.getSearchFields(), loadPeopleOptions.getSortOrder(), 0);
    }

    public void a(BaseImplementation.b<LoadCirclesResult> bVar, String str, String str2, String str3, int i, String str4, boolean z) {
        oY();
        rf pVar = new p(this, bVar);
        try {
            qL().a(pVar, str, str2, str3, i, str4, z);
        } catch (RemoteException e) {
            pVar.a(8, null, null);
        }
    }

    protected void a(kj kjVar, com.google.android.gms.internal.kb.e eVar) throws RemoteException {
        Bundle bundle = new Bundle();
        bundle.putString("social_client_application_id", this.aCw);
        bundle.putString("real_client_package_name", this.Sw);
        bundle.putBoolean("support_new_image_callback", true);
        kjVar.b(eVar, 6587000, getContext().getPackageName(), bundle);
    }

    public void b(b<?> bVar) {
        super.a((b) bVar);
    }

    protected String bK() {
        return "com.google.android.gms.people.service.START";
    }

    protected String bL() {
        return "com.google.android.gms.people.internal.IPeopleService";
    }

    public void disconnect() {
        synchronized (this.aCx) {
            try {
                if (isConnected()) {
                    for (rf a : this.aCx.values()) {
                        qL().a(a, false, null, null, 0);
                    }
                }
            } catch (Throwable e) {
                ro.b("PeopleClient", "Failed to unregister listener", e);
            } catch (Throwable e2) {
                ro.b("PeopleClient", "PeopleService is in unexpected state", e2);
            }
            this.aCx.clear();
        }
        super.disconnect();
    }

    protected rg dr(IBinder iBinder) {
        return rg.a.dq(iBinder);
    }

    protected void oY() {
        super.dR();
    }

    protected /* synthetic */ IInterface p(IBinder iBinder) {
        return dr(iBinder);
    }

    protected rg qL() throws DeadObjectException {
        return (rg) super.iP();
    }

    public synchronized void u(Bundle bundle) {
        if (bundle != null) {
            ry.V(bundle.getBoolean("use_contactables_api", true));
            rm.aCt.t(bundle);
            aCy = bundle.getBundle("config.email_type_map");
            aCz = bundle.getBundle("config.phone_type_map");
        }
    }
}
