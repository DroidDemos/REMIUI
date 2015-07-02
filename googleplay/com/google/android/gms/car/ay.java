package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ay extends IInterface {

    public static abstract class a extends Binder implements ay {

        private static class a implements ay {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(int i, az azVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarSensor");
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(azVar != null ? azVar.asBinder() : null);
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(int i, ba baVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarSensor");
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(baVar != null ? baVar.asBinder() : null);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean a(int i, int i2, az azVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarSensor");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(azVar != null ? azVar.asBinder() : null);
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

            public boolean a(int i, int i2, ba baVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarSensor");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(baVar != null ? baVar.asBinder() : null);
                    this.ld.transact(2, obtain, obtain2, 0);
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

            public boolean a(az azVar, CarSensorEvent carSensorEvent) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarSensor");
                    obtain.writeStrongBinder(azVar != null ? azVar.asBinder() : null);
                    if (carSensorEvent != null) {
                        obtain.writeInt(1);
                        carSensorEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(7, obtain, obtain2, 0);
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

            public CarSensorEvent getLatestSensorEvent(int sensorType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarSensor");
                    obtain.writeInt(sensorType);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    CarSensorEvent carSensorEvent = obtain2.readInt() != 0 ? (CarSensorEvent) CarSensorEvent.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return carSensorEvent;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int[] getSupportedSensors() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarSensor");
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int[] createIntArray = obtain2.createIntArray();
                    return createIntArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static ay aA(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarSensor");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ay)) ? new a(iBinder) : (ay) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            boolean a;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarSensor");
                    int[] supportedSensors = getSupportedSensors();
                    reply.writeNoException();
                    reply.writeIntArray(supportedSensors);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarSensor");
                    a = a(data.readInt(), data.readInt(), com.google.android.gms.car.ba.a.aC(data.readStrongBinder()));
                    reply.writeNoException();
                    reply.writeInt(a ? 1 : 0);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarSensor");
                    CarSensorEvent latestSensorEvent = getLatestSensorEvent(data.readInt());
                    reply.writeNoException();
                    if (latestSensorEvent != null) {
                        reply.writeInt(1);
                        latestSensorEvent.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarSensor");
                    a(data.readInt(), com.google.android.gms.car.ba.a.aC(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarSensor");
                    a = a(data.readInt(), data.readInt(), com.google.android.gms.car.az.a.aB(data.readStrongBinder()));
                    reply.writeNoException();
                    if (a) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarSensor");
                    a(data.readInt(), com.google.android.gms.car.az.a.aB(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICarSensor");
                    a = a(com.google.android.gms.car.az.a.aB(data.readStrongBinder()), data.readInt() != 0 ? (CarSensorEvent) CarSensorEvent.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    if (a) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarSensor");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(int i, az azVar) throws RemoteException;

    void a(int i, ba baVar) throws RemoteException;

    boolean a(int i, int i2, az azVar) throws RemoteException;

    boolean a(int i, int i2, ba baVar) throws RemoteException;

    boolean a(az azVar, CarSensorEvent carSensorEvent) throws RemoteException;

    CarSensorEvent getLatestSensorEvent(int i) throws RemoteException;

    int[] getSupportedSensors() throws RemoteException;
}
