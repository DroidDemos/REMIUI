package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ae extends IInterface {

    public static abstract class a extends Binder implements ae {

        private static class a implements ae {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }
        }

        public static ae ah(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarAudioRecordCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ae)) ? new a(iBinder) : (ae) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarAudioRecordCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
