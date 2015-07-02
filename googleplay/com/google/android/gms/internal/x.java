package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface x extends IInterface {

    public static abstract class a extends Binder implements x {

        private static class a implements x {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void b(String str, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String c(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeString(str);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean c(boolean z) throws RemoteException {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    obtain.writeInt(z ? 1 : 0);
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static x g(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof x)) ? new a(iBinder) : (x) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean z = false;
            String id;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    id = getId();
                    reply.writeNoException();
                    reply.writeString(id);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    int i;
                    data.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    boolean c = c(data.readInt() != 0);
                    reply.writeNoException();
                    if (c) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    id = c(data.readString());
                    reply.writeNoException();
                    reply.writeString(id);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    id = data.readString();
                    if (data.readInt() != 0) {
                        z = true;
                    }
                    b(id, z);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void b(String str, boolean z) throws RemoteException;

    String c(String str) throws RemoteException;

    boolean c(boolean z) throws RemoteException;

    String getId() throws RemoteException;
}
