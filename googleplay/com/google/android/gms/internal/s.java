package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.RecoveryDecision;
import com.google.android.gms.auth.RecoveryReadResponse;
import com.google.android.gms.auth.RecoveryWriteRequest;
import com.google.android.gms.auth.RecoveryWriteResponse;
import com.google.android.wallet.instrumentmanager.R;

public interface s extends IInterface {

    public static abstract class a extends Binder implements s {

        private static class a implements s {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public RecoveryDecision a(String str, String str2, boolean z, Bundle bundle) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.auth.IRecoveryService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    RecoveryDecision ab = obtain2.readInt() != 0 ? RecoveryDecision.CREATOR.ab(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ab;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public RecoveryReadResponse a(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.auth.IRecoveryService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    RecoveryReadResponse ac = obtain2.readInt() != 0 ? RecoveryReadResponse.CREATOR.ac(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ac;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public RecoveryWriteResponse a(RecoveryWriteRequest recoveryWriteRequest, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.auth.IRecoveryService");
                    if (recoveryWriteRequest != null) {
                        obtain.writeInt(1);
                        recoveryWriteRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    RecoveryWriteResponse ae = obtain2.readInt() != 0 ? RecoveryWriteResponse.CREATOR.ae(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return ae;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }
        }

        public static s b(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.auth.IRecoveryService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof s)) ? new a(iBinder) : (s) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            RecoveryWriteRequest recoveryWriteRequest = null;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    Bundle bundle;
                    data.enforceInterface("com.google.android.auth.IRecoveryService");
                    String readString = data.readString();
                    String readString2 = data.readString();
                    boolean z = data.readInt() != 0;
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    RecoveryDecision a = a(readString, readString2, z, bundle);
                    reply.writeNoException();
                    if (a != null) {
                        reply.writeInt(1);
                        a.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.auth.IRecoveryService");
                    RecoveryReadResponse a2 = a(data.readString(), data.readString());
                    reply.writeNoException();
                    if (a2 != null) {
                        reply.writeInt(1);
                        a2.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.auth.IRecoveryService");
                    if (data.readInt() != 0) {
                        recoveryWriteRequest = RecoveryWriteRequest.CREATOR.ad(data);
                    }
                    RecoveryWriteResponse a3 = a(recoveryWriteRequest, data.readString());
                    reply.writeNoException();
                    if (a3 != null) {
                        reply.writeInt(1);
                        a3.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.auth.IRecoveryService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    RecoveryDecision a(String str, String str2, boolean z, Bundle bundle) throws RemoteException;

    RecoveryReadResponse a(String str, String str2) throws RemoteException;

    RecoveryWriteResponse a(RecoveryWriteRequest recoveryWriteRequest, String str) throws RemoteException;
}
