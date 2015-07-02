package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface ab extends IInterface {

    public static abstract class a extends Binder implements ab {

        private static class a implements ab {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public af a(ac acVar, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeStrongBinder(acVar != null ? acVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.ld.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    af ai = com.google.android.gms.car.af.a.ai(obtain2.readStrongBinder());
                    return ai;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public ad b(ac acVar, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeStrongBinder(acVar != null ? acVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.ld.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    ad ag = com.google.android.gms.car.ad.a.ag(obtain2.readStrongBinder());
                    return ag;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CarAudioConfiguration g(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    CarAudioConfiguration carAudioConfiguration = obtain2.readInt() != 0 ? (CarAudioConfiguration) CarAudioConfiguration.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return carAudioConfiguration;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CarAudioConfiguration[] getAudioRecordConfigurations(int streamType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeInt(streamType);
                    this.ld.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    CarAudioConfiguration[] carAudioConfigurationArr = (CarAudioConfiguration[]) obtain2.createTypedArray(CarAudioConfiguration.CREATOR);
                    return carAudioConfigurationArr;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getAudioRecordMinBufferSize(int streamType, int configurationIndex) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeInt(streamType);
                    obtain.writeInt(configurationIndex);
                    this.ld.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int[] getAudioRecordStreams() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int[] createIntArray = obtain2.createIntArray();
                    return createIntArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CarAudioConfiguration[] getAudioTrackConfigurations(int streamType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeInt(streamType);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    CarAudioConfiguration[] carAudioConfigurationArr = (CarAudioConfiguration[]) obtain2.createTypedArray(CarAudioConfiguration.CREATOR);
                    return carAudioConfigurationArr;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getAudioTrackMinBufferSize(int streamType, int configurationIndex) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeInt(streamType);
                    obtain.writeInt(configurationIndex);
                    this.ld.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int[] getAudioTrackStreams() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int[] createIntArray = obtain2.createIntArray();
                    return createIntArray;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CarAudioConfiguration h(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    CarAudioConfiguration carAudioConfiguration = obtain2.readInt() != 0 ? (CarAudioConfiguration) CarAudioConfiguration.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return carAudioConfiguration;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean waitForPlayback(long timeoutMs) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeLong(timeoutMs);
                    this.ld.transact(12, obtain, obtain2, 0);
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

            public boolean waitForSilence(long timeoutMs) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarAudio");
                    obtain.writeLong(timeoutMs);
                    this.ld.transact(11, obtain, obtain2, 0);
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

        public static ab ae(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarAudio");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ab)) ? new a(iBinder) : (ab) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            IBinder iBinder = null;
            int i = 0;
            int[] audioTrackStreams;
            Parcelable[] audioTrackConfigurations;
            CarAudioConfiguration g;
            int audioTrackMinBufferSize;
            boolean waitForSilence;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    audioTrackStreams = getAudioTrackStreams();
                    reply.writeNoException();
                    reply.writeIntArray(audioTrackStreams);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    audioTrackStreams = getAudioRecordStreams();
                    reply.writeNoException();
                    reply.writeIntArray(audioTrackStreams);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    audioTrackConfigurations = getAudioTrackConfigurations(data.readInt());
                    reply.writeNoException();
                    reply.writeTypedArray(audioTrackConfigurations, 1);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    g = g(data.readInt(), data.readInt());
                    reply.writeNoException();
                    if (g != null) {
                        reply.writeInt(1);
                        g.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    audioTrackConfigurations = getAudioRecordConfigurations(data.readInt());
                    reply.writeNoException();
                    reply.writeTypedArray(audioTrackConfigurations, 1);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    g = h(data.readInt(), data.readInt());
                    reply.writeNoException();
                    if (g != null) {
                        reply.writeInt(1);
                        g.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    audioTrackMinBufferSize = getAudioTrackMinBufferSize(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(audioTrackMinBufferSize);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    audioTrackMinBufferSize = getAudioRecordMinBufferSize(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(audioTrackMinBufferSize);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    af a = a(com.google.android.gms.car.ac.a.af(data.readStrongBinder()), data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    if (a != null) {
                        iBinder = a.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    ad b = b(com.google.android.gms.car.ac.a.af(data.readStrongBinder()), data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    if (b != null) {
                        iBinder = b.asBinder();
                    }
                    reply.writeStrongBinder(iBinder);
                    return true;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    waitForSilence = waitForSilence(data.readLong());
                    reply.writeNoException();
                    reply.writeInt(waitForSilence ? 1 : 0);
                    return true;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    data.enforceInterface("com.google.android.gms.car.ICarAudio");
                    waitForSilence = waitForPlayback(data.readLong());
                    reply.writeNoException();
                    if (waitForSilence) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarAudio");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    af a(ac acVar, int i, int i2, int i3) throws RemoteException;

    ad b(ac acVar, int i, int i2, int i3) throws RemoteException;

    CarAudioConfiguration g(int i, int i2) throws RemoteException;

    CarAudioConfiguration[] getAudioRecordConfigurations(int i) throws RemoteException;

    int getAudioRecordMinBufferSize(int i, int i2) throws RemoteException;

    int[] getAudioRecordStreams() throws RemoteException;

    CarAudioConfiguration[] getAudioTrackConfigurations(int i) throws RemoteException;

    int getAudioTrackMinBufferSize(int i, int i2) throws RemoteException;

    int[] getAudioTrackStreams() throws RemoteException;

    CarAudioConfiguration h(int i, int i2) throws RemoteException;

    boolean waitForPlayback(long j) throws RemoteException;

    boolean waitForSilence(long j) throws RemoteException;
}
