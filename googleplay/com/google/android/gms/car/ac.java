package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ac extends IInterface {

    public static abstract class a extends Binder implements ac {

        private static class a implements ac {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }
        }

        public static ac af(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarAudioCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ac)) ? new a(iBinder) : (ac) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarAudioCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }
}
