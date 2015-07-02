package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public interface aj extends IInterface {

    public static abstract class a extends Binder implements aj {

        private static class a implements aj {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public boolean a(ak akVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    obtain.writeStrongBinder(akVar != null ? akVar.asBinder() : null);
                    this.ld.transact(7, obtain, obtain2, 0);
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

            public void answerCall(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public boolean b(ak akVar) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    obtain.writeStrongBinder(akVar != null ? akVar.asBinder() : null);
                    this.ld.transact(8, obtain, obtain2, 0);
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

            public void conference(CarCall call, CarCall otherCall) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (otherCall != null) {
                        obtain.writeInt(1);
                        otherCall.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void disconnectCall(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getAudioRoute() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    this.ld.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<CarCall> getCalls() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    List<CarCall> createTypedArrayList = obtain2.createTypedArrayList(CarCall.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getMuted() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
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

            public int getSupportedAudioRouteMask() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void holdCall(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void placeCall(String number) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    obtain.writeString(number);
                    this.ld.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void playDtmfTone(CarCall call, char digit) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(digit);
                    this.ld.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void postDialContinue(CarCall call, boolean proceed) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!proceed) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void rejectCall(CarCall call, boolean rejectWithMessage, String textMessage) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!rejectWithMessage) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    obtain.writeString(textMessage);
                    this.ld.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAudioRoute(int audioRoute) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    obtain.writeInt(audioRoute);
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setMuted(boolean state) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (state) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void splitFromConference(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopDtmfTone(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unholdCall(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCall");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static aj am(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarCall");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof aj)) ? new a(iBinder) : (aj) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean z = false;
            boolean muted;
            int supportedAudioRouteMask;
            int i;
            CarCall carCall;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    List calls = getCalls();
                    reply.writeNoException();
                    reply.writeTypedList(calls);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    muted = getMuted();
                    reply.writeNoException();
                    reply.writeInt(muted ? 1 : 0);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    if (data.readInt() != 0) {
                        z = true;
                    }
                    setMuted(z);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    supportedAudioRouteMask = getSupportedAudioRouteMask();
                    reply.writeNoException();
                    reply.writeInt(supportedAudioRouteMask);
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    supportedAudioRouteMask = getAudioRoute();
                    reply.writeNoException();
                    reply.writeInt(supportedAudioRouteMask);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    setAudioRoute(data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    muted = a(com.google.android.gms.car.ak.a.an(data.readStrongBinder()));
                    reply.writeNoException();
                    if (muted) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    muted = b(com.google.android.gms.car.ak.a.an(data.readStrongBinder()));
                    reply.writeNoException();
                    if (muted) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    answerCall(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    carCall = data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null;
                    if (data.readInt() != 0) {
                        z = true;
                    }
                    rejectCall(carCall, z, data.readString());
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    disconnectCall(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    holdCall(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    unholdCall(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    playDtmfTone(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, (char) data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    stopDtmfTone(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    carCall = data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null;
                    if (data.readInt() != 0) {
                        z = true;
                    }
                    postDialContinue(carCall, z);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    conference(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    splitFromConference(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCall");
                    placeCall(data.readString());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarCall");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    boolean a(ak akVar) throws RemoteException;

    void answerCall(CarCall carCall) throws RemoteException;

    boolean b(ak akVar) throws RemoteException;

    void conference(CarCall carCall, CarCall carCall2) throws RemoteException;

    void disconnectCall(CarCall carCall) throws RemoteException;

    int getAudioRoute() throws RemoteException;

    List<CarCall> getCalls() throws RemoteException;

    boolean getMuted() throws RemoteException;

    int getSupportedAudioRouteMask() throws RemoteException;

    void holdCall(CarCall carCall) throws RemoteException;

    void placeCall(String str) throws RemoteException;

    void playDtmfTone(CarCall carCall, char c) throws RemoteException;

    void postDialContinue(CarCall carCall, boolean z) throws RemoteException;

    void rejectCall(CarCall carCall, boolean z, String str) throws RemoteException;

    void setAudioRoute(int i) throws RemoteException;

    void setMuted(boolean z) throws RemoteException;

    void splitFromConference(CarCall carCall) throws RemoteException;

    void stopDtmfTone(CarCall carCall) throws RemoteException;

    void unholdCall(CarCall carCall) throws RemoteException;
}
