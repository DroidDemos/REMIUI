package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface au extends IInterface {

    public static abstract class a extends Binder implements au {

        private static class a implements au {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(av avVar, CarPhoneStatus carPhoneStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarPhoneStatus");
                    obtain.writeStrongBinder(avVar != null ? avVar.asBinder() : null);
                    if (carPhoneStatus != null) {
                        obtain.writeInt(1);
                        carPhoneStatus.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(av avVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarPhoneStatus");
                    obtain.writeStrongBinder(avVar != null ? avVar.asBinder() : null);
                    this.ld.transact(2, obtain, obtain2, 0);
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

            public boolean b(av avVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarPhoneStatus");
                    obtain.writeStrongBinder(avVar != null ? avVar.asBinder() : null);
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
        }

        public static au ax(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarPhoneStatus");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof au)) ? new a(iBinder) : (au) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            boolean a;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarPhoneStatus");
                    a(com.google.android.gms.car.av.a.ay(data.readStrongBinder()), data.readInt() != 0 ? (CarPhoneStatus) CarPhoneStatus.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarPhoneStatus");
                    a = a(com.google.android.gms.car.av.a.ay(data.readStrongBinder()));
                    reply.writeNoException();
                    if (a) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarPhoneStatus");
                    a = b(com.google.android.gms.car.av.a.ay(data.readStrongBinder()));
                    reply.writeNoException();
                    if (a) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarPhoneStatus");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(av avVar, CarPhoneStatus carPhoneStatus) throws RemoteException;

    boolean a(av avVar) throws RemoteException;

    boolean b(av avVar) throws RemoteException;
}
