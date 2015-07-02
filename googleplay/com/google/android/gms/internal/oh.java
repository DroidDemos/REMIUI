package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.location.copresence.SubscribedMessage;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public interface oh extends IInterface {

    public static abstract class a extends Binder implements oh {

        private static class a implements oh {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void onMessagesReceived(List<SubscribedMessage> messages) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.copresence.internal.IMessageListener");
                    obtain.writeTypedList(messages);
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStatusReceived(int statusCode) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.location.copresence.internal.IMessageListener");
                    obtain.writeInt(statusCode);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static oh cm(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.copresence.internal.IMessageListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof oh)) ? new a(iBinder) : (oh) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.location.copresence.internal.IMessageListener");
                    onMessagesReceived(data.createTypedArrayList(SubscribedMessage.CREATOR));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.location.copresence.internal.IMessageListener");
                    onStatusReceived(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.location.copresence.internal.IMessageListener");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void onMessagesReceived(List<SubscribedMessage> list) throws RemoteException;

    void onStatusReceived(int i) throws RemoteException;
}
