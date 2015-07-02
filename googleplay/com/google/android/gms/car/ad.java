package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ad extends IInterface {

    public static abstract class a extends Binder implements ad {

        private static class a implements ad {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(ae aeVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioRecord");
                    obtain.writeStrongBinder(aeVar != null ? aeVar.asBinder() : null);
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ae aeVar, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioRecord");
                    obtain.writeStrongBinder(aeVar != null ? aeVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void b(ae aeVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioRecord");
                    obtain.writeStrongBinder(aeVar != null ? aeVar.asBinder() : null);
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean b(ae aeVar, int i) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioRecord");
                    obtain.writeStrongBinder(aeVar != null ? aeVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.ld.transact(7, obtain, obtain2, 0);
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

            public void c(ae aeVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioRecord");
                    obtain.writeStrongBinder(aeVar != null ? aeVar.asBinder() : null);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelFileDescriptor d(ae aeVar) throws RemoteException {
                ParcelFileDescriptor parcelFileDescriptor = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioRecord");
                    obtain.writeStrongBinder(aeVar != null ? aeVar.asBinder() : null);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return parcelFileDescriptor;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(ae aeVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioRecord");
                    obtain.writeStrongBinder(aeVar != null ? aeVar.asBinder() : null);
                    this.ld.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ad ag(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarAudioRecord");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ad)) ? new a(iBinder) : (ad) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioRecord");
                    a(com.google.android.gms.car.ae.a.ah(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioRecord");
                    b(com.google.android.gms.car.ae.a.ah(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioRecord");
                    c(com.google.android.gms.car.ae.a.ah(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioRecord");
                    ParcelFileDescriptor d = d(com.google.android.gms.car.ae.a.ah(data.readStrongBinder()));
                    reply.writeNoException();
                    if (d != null) {
                        reply.writeInt(1);
                        d.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioRecord");
                    e(com.google.android.gms.car.ae.a.ah(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioRecord");
                    a(com.google.android.gms.car.ae.a.ah(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioRecord");
                    boolean b = b(com.google.android.gms.car.ae.a.ah(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    if (b) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarAudioRecord");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(ae aeVar) throws RemoteException;

    void a(ae aeVar, int i) throws RemoteException;

    void b(ae aeVar) throws RemoteException;

    boolean b(ae aeVar, int i) throws RemoteException;

    void c(ae aeVar) throws RemoteException;

    ParcelFileDescriptor d(ae aeVar) throws RemoteException;

    void e(ae aeVar) throws RemoteException;
}
