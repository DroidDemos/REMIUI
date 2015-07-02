package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.wallet.instrumentmanager.R;

public interface jg extends IInterface {

    public static abstract class a extends Binder implements jg {

        private static class a implements jg {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(jf jfVar, LogEventParcelable logEventParcelable) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    if (jfVar != null) {
                        iBinder = jfVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (logEventParcelable != null) {
                        obtain.writeInt(1);
                        logEventParcelable.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }
        }

        public static jg aR(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof jg)) ? new a(iBinder) : (jg) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    a(com.google.android.gms.internal.jf.a.aQ(data.readStrongBinder()), data.readInt() != 0 ? LogEventParcelable.CREATOR.bL(data) : null);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(jf jfVar, LogEventParcelable logEventParcelable) throws RemoteException;
}
