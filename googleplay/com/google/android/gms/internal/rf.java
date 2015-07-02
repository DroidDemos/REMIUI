package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.wallet.instrumentmanager.R;

public interface rf extends IInterface {

    public static abstract class a extends Binder implements rf {

        private static class a implements rf {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(int i, Bundle bundle, Bundle bundle2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleCallbacks");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void a(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleCallbacks");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void a(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleCallbacks");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(5, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void a(int i, Bundle bundle, DataHolder dataHolder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleCallbacks");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void a(int i, Bundle bundle, DataHolder[] dataHolderArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleCallbacks");
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedArray(dataHolderArr, 0);
                    this.ld.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }
        }

        public a() {
            attachInterface(this, "com.google.android.gms.people.internal.IPeopleCallbacks");
        }

        public static rf dp(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.people.internal.IPeopleCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof rf)) ? new a(iBinder) : (rf) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle bundle = null;
            int readInt;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleCallbacks");
                    a(data.readInt(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null, data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    DataHolder bO;
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleCallbacks");
                    readInt = data.readInt();
                    Bundle bundle2 = data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null;
                    if (data.readInt() != 0) {
                        bO = DataHolder.CREATOR.bO(data);
                    }
                    a(readInt, bundle2, bO);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleCallbacks");
                    a(data.readInt(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null, data.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data) : null);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleCallbacks");
                    readInt = data.readInt();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    a(readInt, bundle, (DataHolder[]) data.createTypedArray(DataHolder.CREATOR));
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleCallbacks");
                    a(data.readInt(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null, data.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data) : null, data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.people.internal.IPeopleCallbacks");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(int i, Bundle bundle, Bundle bundle2) throws RemoteException;

    void a(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void a(int i, Bundle bundle, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle2) throws RemoteException;

    void a(int i, Bundle bundle, DataHolder dataHolder) throws RemoteException;

    void a(int i, Bundle bundle, DataHolder[] dataHolderArr) throws RemoteException;
}
