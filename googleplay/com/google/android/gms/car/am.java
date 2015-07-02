package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface am extends IInterface {

    public static abstract class a extends Binder implements am {

        private static class a implements am {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(an anVar, CarMediaBrowserListNode carMediaBrowserListNode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaBrowser");
                    obtain.writeStrongBinder(anVar != null ? anVar.asBinder() : null);
                    if (carMediaBrowserListNode != null) {
                        obtain.writeInt(1);
                        carMediaBrowserListNode.writeToParcel(obtain, 0);
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

            public void a(an anVar, CarMediaBrowserRootNode carMediaBrowserRootNode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaBrowser");
                    obtain.writeStrongBinder(anVar != null ? anVar.asBinder() : null);
                    if (carMediaBrowserRootNode != null) {
                        obtain.writeInt(1);
                        carMediaBrowserRootNode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(an anVar, CarMediaBrowserSongNode carMediaBrowserSongNode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaBrowser");
                    obtain.writeStrongBinder(anVar != null ? anVar.asBinder() : null);
                    if (carMediaBrowserSongNode != null) {
                        obtain.writeInt(1);
                        carMediaBrowserSongNode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(an anVar, CarMediaBrowserSourceNode carMediaBrowserSourceNode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaBrowser");
                    obtain.writeStrongBinder(anVar != null ? anVar.asBinder() : null);
                    if (carMediaBrowserSourceNode != null) {
                        obtain.writeInt(1);
                        carMediaBrowserSourceNode.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(an anVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaBrowser");
                    obtain.writeStrongBinder(anVar != null ? anVar.asBinder() : null);
                    this.ld.transact(5, obtain, obtain2, 0);
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

            public boolean b(an anVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarMediaBrowser");
                    obtain.writeStrongBinder(anVar != null ? anVar.asBinder() : null);
                    this.ld.transact(6, obtain, obtain2, 0);
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

        public static am ap(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarMediaBrowser");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof am)) ? new a(iBinder) : (am) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            CarMediaBrowserSourceNode carMediaBrowserSourceNode = null;
            an aq;
            boolean a;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    CarMediaBrowserListNode carMediaBrowserListNode;
                    data.enforceInterface("com.google.android.gms.car.ICarMediaBrowser");
                    aq = com.google.android.gms.car.an.a.aq(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        carMediaBrowserListNode = (CarMediaBrowserListNode) CarMediaBrowserListNode.CREATOR.createFromParcel(data);
                    }
                    a(aq, carMediaBrowserListNode);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    CarMediaBrowserRootNode carMediaBrowserRootNode;
                    data.enforceInterface("com.google.android.gms.car.ICarMediaBrowser");
                    aq = com.google.android.gms.car.an.a.aq(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        carMediaBrowserRootNode = (CarMediaBrowserRootNode) CarMediaBrowserRootNode.CREATOR.createFromParcel(data);
                    }
                    a(aq, carMediaBrowserRootNode);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    CarMediaBrowserSongNode carMediaBrowserSongNode;
                    data.enforceInterface("com.google.android.gms.car.ICarMediaBrowser");
                    aq = com.google.android.gms.car.an.a.aq(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        carMediaBrowserSongNode = (CarMediaBrowserSongNode) CarMediaBrowserSongNode.CREATOR.createFromParcel(data);
                    }
                    a(aq, carMediaBrowserSongNode);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaBrowser");
                    aq = com.google.android.gms.car.an.a.aq(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        carMediaBrowserSourceNode = (CarMediaBrowserSourceNode) CarMediaBrowserSourceNode.CREATOR.createFromParcel(data);
                    }
                    a(aq, carMediaBrowserSourceNode);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaBrowser");
                    a = a(com.google.android.gms.car.an.a.aq(data.readStrongBinder()));
                    reply.writeNoException();
                    reply.writeInt(a ? 1 : 0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarMediaBrowser");
                    a = b(com.google.android.gms.car.an.a.aq(data.readStrongBinder()));
                    reply.writeNoException();
                    if (a) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarMediaBrowser");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(an anVar, CarMediaBrowserListNode carMediaBrowserListNode) throws RemoteException;

    void a(an anVar, CarMediaBrowserRootNode carMediaBrowserRootNode) throws RemoteException;

    void a(an anVar, CarMediaBrowserSongNode carMediaBrowserSongNode) throws RemoteException;

    void a(an anVar, CarMediaBrowserSourceNode carMediaBrowserSourceNode) throws RemoteException;

    boolean a(an anVar) throws RemoteException;

    boolean b(an anVar) throws RemoteException;
}
