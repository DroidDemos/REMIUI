package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface lo extends IInterface {

    public static abstract class a extends Binder implements lo {

        private static class a implements lo {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(ln lnVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.deviceconnection.internal.IDeviceConnectionService");
                    obtain.writeStrongBinder(lnVar != null ? lnVar.asBinder() : null);
                    this.ld.transact(1002, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ln lnVar, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.deviceconnection.internal.IDeviceConnectionService");
                    obtain.writeStrongBinder(lnVar != null ? lnVar.asBinder() : null);
                    obtain.writeStringArray(strArr);
                    this.ld.transact(1001, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }
        }

        public static lo bj(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.deviceconnection.internal.IDeviceConnectionService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof lo)) ? new a(iBinder) : (lo) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1001:
                    data.enforceInterface("com.google.android.gms.deviceconnection.internal.IDeviceConnectionService");
                    a(com.google.android.gms.internal.ln.a.bi(data.readStrongBinder()), data.createStringArray());
                    reply.writeNoException();
                    return true;
                case 1002:
                    data.enforceInterface("com.google.android.gms.deviceconnection.internal.IDeviceConnectionService");
                    a(com.google.android.gms.internal.ln.a.bi(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.deviceconnection.internal.IDeviceConnectionService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(ln lnVar) throws RemoteException;

    void a(ln lnVar, String[] strArr) throws RemoteException;
}
