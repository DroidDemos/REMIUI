package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.internal.kn;

public class g extends Fragment implements OnCancelListener, LoaderCallbacks<ConnectionResult> {
    private boolean Tn;
    private int To;
    private ConnectionResult Tp;
    private final Handler Tq;
    private final SparseArray<b> Tr;

    static class a extends Loader<ConnectionResult> implements ConnectionCallbacks, OnConnectionFailedListener {
        public final GoogleApiClient Ts;
        private boolean Tt;
        private ConnectionResult Tu;

        public a(Context context, GoogleApiClient googleApiClient) {
            super(context);
            this.Ts = googleApiClient;
        }

        private void a(ConnectionResult connectionResult) {
            this.Tu = connectionResult;
            if (isStarted() && !isAbandoned()) {
                deliverResult(connectionResult);
            }
        }

        public void in() {
            if (this.Tt) {
                this.Tt = false;
                if (isStarted() && !isAbandoned()) {
                    this.Ts.connect();
                }
            }
        }

        public void onConnected(Bundle connectionHint) {
            this.Tt = false;
            a(ConnectionResult.Rj);
        }

        public void onConnectionFailed(ConnectionResult result) {
            this.Tt = true;
            a(result);
        }

        public void onConnectionSuspended(int cause) {
        }

        protected void onReset() {
            this.Tu = null;
            this.Tt = false;
            this.Ts.unregisterConnectionCallbacks(this);
            this.Ts.unregisterConnectionFailedListener(this);
            this.Ts.disconnect();
        }

        protected void onStartLoading() {
            super.onStartLoading();
            this.Ts.registerConnectionCallbacks(this);
            this.Ts.registerConnectionFailedListener(this);
            if (this.Tu != null) {
                deliverResult(this.Tu);
            }
            if (!this.Ts.isConnected() && !this.Ts.isConnecting() && !this.Tt) {
                this.Ts.connect();
            }
        }

        protected void onStopLoading() {
            this.Ts.disconnect();
        }
    }

    private static class b {
        public final GoogleApiClient Ts;
        public final OnConnectionFailedListener Tv;

        private b(GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
            this.Ts = googleApiClient;
            this.Tv = onConnectionFailedListener;
        }
    }

    private class c implements Runnable {
        private final int Tw;
        private final ConnectionResult Tx;
        final /* synthetic */ g Ty;

        public c(g gVar, int i, ConnectionResult connectionResult) {
            this.Ty = gVar;
            this.Tw = i;
            this.Tx = connectionResult;
        }

        public void run() {
            if (this.Tx.hasResolution()) {
                try {
                    this.Tx.startResolutionForResult(this.Ty.getActivity(), ((this.Ty.getActivity().getSupportFragmentManager().getFragments().indexOf(this.Ty) + 1) << 16) + 1);
                } catch (SendIntentException e) {
                    this.Ty.im();
                }
            } else if (GooglePlayServicesUtil.isUserRecoverableError(this.Tx.getErrorCode())) {
                GooglePlayServicesUtil.showErrorDialogFragment(this.Tx.getErrorCode(), this.Ty.getActivity(), this.Ty, 2, this.Ty);
            } else {
                this.Ty.b(this.Tw, this.Tx);
            }
        }
    }

    public g() {
        this.To = -1;
        this.Tq = new Handler(Looper.getMainLooper());
        this.Tr = new SparseArray();
    }

    public static g a(FragmentActivity fragmentActivity) {
        kn.bd("Must be called from main thread of process");
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        try {
            g gVar = (g) supportFragmentManager.findFragmentByTag("GmsSupportLifecycleFragment");
            if (gVar != null && !gVar.isRemoving()) {
                return gVar;
            }
            Fragment gVar2 = new g();
            supportFragmentManager.beginTransaction().add(gVar2, "GmsSupportLifecycleFragment").commit();
            supportFragmentManager.executePendingTransactions();
            return gVar2;
        } catch (Throwable e) {
            throw new IllegalStateException("Fragment with tag GmsSupportLifecycleFragment is not a SupportLifecycleFragment", e);
        }
    }

    private void a(int i, ConnectionResult connectionResult) {
        if (!this.Tn) {
            this.Tn = true;
            this.To = i;
            this.Tp = connectionResult;
            this.Tq.post(new c(this, i, connectionResult));
        }
    }

    private void b(int i, ConnectionResult connectionResult) {
        Log.w("GmsSupportLifecycleFragment", "Unresolved error while connecting client. Stopping auto-manage.");
        b bVar = (b) this.Tr.get(i);
        if (bVar != null) {
            cQ(i);
            OnConnectionFailedListener onConnectionFailedListener = bVar.Tv;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
        im();
    }

    private void cS(int i) {
        if (i == this.To) {
            im();
        }
    }

    private void im() {
        int i = 0;
        this.Tn = false;
        this.To = -1;
        this.Tp = null;
        LoaderManager loaderManager = getLoaderManager();
        while (i < this.Tr.size()) {
            int keyAt = this.Tr.keyAt(i);
            a cR = cR(keyAt);
            if (cR != null) {
                cR.in();
            }
            loaderManager.initLoader(keyAt, null, this);
            i++;
        }
    }

    public void a(int i, GoogleApiClient googleApiClient, OnConnectionFailedListener onConnectionFailedListener) {
        kn.b((Object) googleApiClient, (Object) "GoogleApiClient instance cannot be null");
        kn.a(this.Tr.indexOfKey(i) < 0, "Already managing a GoogleApiClient with id " + i);
        this.Tr.put(i, new b(googleApiClient, onConnectionFailedListener));
        if (getActivity() != null) {
            getLoaderManager().initLoader(i, null, this);
        }
    }

    public void a(Loader<ConnectionResult> loader, ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            cS(loader.getId());
        } else {
            a(loader.getId(), connectionResult);
        }
    }

    public GoogleApiClient cP(int i) {
        if (getActivity() != null) {
            a cR = cR(i);
            if (cR != null) {
                return cR.Ts;
            }
        }
        return null;
    }

    public void cQ(int i) {
        getLoaderManager().destroyLoader(i);
        this.Tr.remove(i);
    }

    a cR(int i) {
        try {
            return (a) getLoaderManager().getLoader(i);
        } catch (Throwable e) {
            throw new IllegalStateException("Unknown loader in SupportLifecycleFragment", e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
        r3 = this;
        r0 = 1;
        r1 = 0;
        switch(r4) {
            case 1: goto L_0x0017;
            case 2: goto L_0x000c;
            default: goto L_0x0005;
        };
    L_0x0005:
        r0 = r1;
    L_0x0006:
        if (r0 == 0) goto L_0x001b;
    L_0x0008:
        r3.im();
    L_0x000b:
        return;
    L_0x000c:
        r2 = r3.getActivity();
        r2 = com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable(r2);
        if (r2 != 0) goto L_0x0005;
    L_0x0016:
        goto L_0x0006;
    L_0x0017:
        r2 = -1;
        if (r5 != r2) goto L_0x0005;
    L_0x001a:
        goto L_0x0006;
    L_0x001b:
        r0 = r3.To;
        r1 = r3.Tp;
        r3.b(r0, r1);
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.g.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        int i = 0;
        while (i < this.Tr.size()) {
            int keyAt = this.Tr.keyAt(i);
            a cR = cR(keyAt);
            if (cR == null || ((b) this.Tr.valueAt(i)).Ts == cR.Ts) {
                getLoaderManager().initLoader(keyAt, null, this);
            } else {
                getLoaderManager().restartLoader(keyAt, null, this);
            }
            i++;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        b(this.To, this.Tp);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.Tn = savedInstanceState.getBoolean("resolving_error", false);
            this.To = savedInstanceState.getInt("failed_client_id", -1);
            if (this.To >= 0) {
                this.Tp = new ConnectionResult(savedInstanceState.getInt("failed_status"), (PendingIntent) savedInstanceState.getParcelable("failed_resolution"));
            }
        }
    }

    public Loader<ConnectionResult> onCreateLoader(int id, Bundle args) {
        return new a(getActivity(), ((b) this.Tr.get(id)).Ts);
    }

    public /* synthetic */ void onLoadFinished(Loader x0, Object x1) {
        a(x0, (ConnectionResult) x1);
    }

    public void onLoaderReset(Loader<ConnectionResult> loader) {
        if (loader.getId() == this.To) {
            im();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("resolving_error", this.Tn);
        if (this.To >= 0) {
            outState.putInt("failed_client_id", this.To);
            outState.putInt("failed_status", this.Tp.getErrorCode());
            outState.putParcelable("failed_resolution", this.Tp.getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        if (!this.Tn) {
            for (int i = 0; i < this.Tr.size(); i++) {
                getLoaderManager().initLoader(this.Tr.keyAt(i), null, this);
            }
        }
    }
}
