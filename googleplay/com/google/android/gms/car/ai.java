package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ai extends IInterface {

    public static abstract class a extends Binder implements ai {

        private static class a implements ai {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void onCarDelayedPairing() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarBluetoothClient");
                    this.ld.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onDisabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarBluetoothClient");
                    this.ld.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarBluetoothClient");
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onHfpConnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarBluetoothClient");
                    this.ld.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onHfpDisconnected() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarBluetoothClient");
                    this.ld.transact(7, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPaired() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarBluetoothClient");
                    this.ld.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onUnpaired() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarBluetoothClient");
                    this.ld.transact(5, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static ai al(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarBluetoothClient");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ai)) ? new a(iBinder) : (ai) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarBluetoothClient");
                    onEnabled();
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarBluetoothClient");
                    onDisabled();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarBluetoothClient");
                    onCarDelayedPairing();
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarBluetoothClient");
                    onPaired();
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarBluetoothClient");
                    onUnpaired();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarBluetoothClient");
                    onHfpConnected();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICarBluetoothClient");
                    onHfpDisconnected();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarBluetoothClient");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onCarDelayedPairing() throws RemoteException;

    void onDisabled() throws RemoteException;

    void onEnabled() throws RemoteException;

    void onHfpConnected() throws RemoteException;

    void onHfpDisconnected() throws RemoteException;

    void onPaired() throws RemoteException;

    void onUnpaired() throws RemoteException;
}
