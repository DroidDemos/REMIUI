package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface bb extends IInterface {

    public static abstract class a extends Binder implements bb {

        private static class a implements bb {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(bc bcVar, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(bc bcVar) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
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

            public void b(bc bcVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(bc bcVar, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.ld.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String c(bc bcVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(bc bcVar, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.ld.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte[] d(bc bcVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    byte[] createByteArray = obtain2.createByteArray();
                    return createByteArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelFileDescriptor e(bc bcVar) throws RemoteException {
                ParcelFileDescriptor parcelFileDescriptor = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    this.ld.transact(5, obtain, obtain2, 0);
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

            public ParcelFileDescriptor f(bc bcVar) throws RemoteException {
                ParcelFileDescriptor parcelFileDescriptor = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarVendorExtension");
                    obtain.writeStrongBinder(bcVar != null ? bcVar.asBinder() : null);
                    this.ld.transact(8, obtain, obtain2, 0);
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
        }

        public static bb aD(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarVendorExtension");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof bb)) ? new a(iBinder) : (bb) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            ParcelFileDescriptor e;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    boolean a = a(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()));
                    reply.writeNoException();
                    if (a) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    b(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    String c = c(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()));
                    reply.writeNoException();
                    reply.writeString(c);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    byte[] d = d(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()));
                    reply.writeNoException();
                    reply.writeByteArray(d);
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    e = e(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()));
                    reply.writeNoException();
                    if (e != null) {
                        reply.writeInt(1);
                        e.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    a(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    b(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    e = f(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()));
                    reply.writeNoException();
                    if (e != null) {
                        reply.writeInt(1);
                        e.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    data.enforceInterface("com.google.android.gms.car.ICarVendorExtension");
                    c(com.google.android.gms.car.bc.a.aE(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarVendorExtension");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(bc bcVar, int i) throws RemoteException;

    boolean a(bc bcVar) throws RemoteException;

    void b(bc bcVar) throws RemoteException;

    void b(bc bcVar, int i) throws RemoteException;

    String c(bc bcVar) throws RemoteException;

    void c(bc bcVar, int i) throws RemoteException;

    byte[] d(bc bcVar) throws RemoteException;

    ParcelFileDescriptor e(bc bcVar) throws RemoteException;

    ParcelFileDescriptor f(bc bcVar) throws RemoteException;
}
