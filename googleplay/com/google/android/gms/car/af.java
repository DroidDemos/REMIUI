package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface af extends IInterface {

    public static abstract class a extends Binder implements af {

        private static class a implements af {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(ag agVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ag agVar, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.ld.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public ParcelFileDescriptor b(ag agVar) throws RemoteException {
                ParcelFileDescriptor parcelFileDescriptor = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    this.ld.transact(2, obtain, obtain2, 0);
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

            public void b(ag agVar, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.ld.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(ag agVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(ag agVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(ag agVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    this.ld.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(ag agVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void g(ag agVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudioTrack");
                    obtain.writeStrongBinder(agVar != null ? agVar.asBinder() : null);
                    this.ld.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static af ai(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarAudioTrack");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof af)) ? new a(iBinder) : (af) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    a(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    ParcelFileDescriptor b = b(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()));
                    reply.writeNoException();
                    if (b != null) {
                        reply.writeInt(1);
                        b.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    c(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    d(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    e(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    f(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    g(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    a(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudioTrack");
                    b(com.google.android.gms.car.ag.a.aj(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarAudioTrack");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(ag agVar) throws RemoteException;

    void a(ag agVar, int i) throws RemoteException;

    ParcelFileDescriptor b(ag agVar) throws RemoteException;

    void b(ag agVar, int i) throws RemoteException;

    void c(ag agVar) throws RemoteException;

    void d(ag agVar) throws RemoteException;

    void e(ag agVar) throws RemoteException;

    void f(ag agVar) throws RemoteException;

    void g(ag agVar) throws RemoteException;
}
