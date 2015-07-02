package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ao extends IInterface {

    public static abstract class a extends Binder implements ao {

        private static class a implements ao {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(ap apVar, int i, String str, int i2, boolean z, boolean z2, boolean z3) throws RemoteException {
                int i3 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    obtain.writeStrongBinder(apVar != null ? apVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (!z3) {
                        i3 = 0;
                    }
                    obtain.writeInt(i3);
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ap apVar, String str, String str2, String str3, byte[] bArr, String str4, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    obtain.writeStrongBinder(apVar != null ? apVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str4);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(ap apVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    obtain.writeStrongBinder(apVar != null ? apVar.asBinder() : null);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public boolean b(ap apVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    obtain.writeStrongBinder(apVar != null ? apVar.asBinder() : null);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ao ar(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarMediaPlaybackStatus");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ao)) ? new a(iBinder) : (ao) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            boolean a;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    a(com.google.android.gms.car.ap.a.as(data.readStrongBinder()), data.readInt(), data.readString(), data.readInt(), data.readInt() != 0, data.readInt() != 0, data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    a(com.google.android.gms.car.ap.a.as(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createByteArray(), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    a = a(com.google.android.gms.car.ap.a.as(data.readStrongBinder()));
                    reply.writeNoException();
                    if (a) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    a = b(com.google.android.gms.car.ap.a.as(data.readStrongBinder()));
                    reply.writeNoException();
                    if (a) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarMediaPlaybackStatus");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(ap apVar, int i, String str, int i2, boolean z, boolean z2, boolean z3) throws RemoteException;

    void a(ap apVar, String str, String str2, String str3, byte[] bArr, String str4, int i, int i2) throws RemoteException;

    boolean a(ap apVar) throws RemoteException;

    boolean b(ap apVar) throws RemoteException;
}
