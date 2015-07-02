package com.google.android.gms.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.KeyEvent;
import com.google.android.gms.car.CarCall.Details;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public interface ak extends IInterface {

    public static abstract class a extends Binder implements ak {

        private static class a implements ak {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void dispatchPhoneKeyEvent(KeyEvent event) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (event != null) {
                        obtain.writeInt(1);
                        event.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onAudioStateChanged(boolean isMuted, int route, int supportedRouteMask) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (!isMuted) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(route);
                    obtain.writeInt(supportedRouteMask);
                    this.ld.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCallAdded(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCallDestroyed(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(11, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCallRemoved(CarCall call) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onCannedTextResponsesLoaded(CarCall call, List<String> cannedTextResponses) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringList(cannedTextResponses);
                    this.ld.transact(9, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onChildrenChanged(CarCall call, List<CarCall> children) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedList(children);
                    this.ld.transact(7, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onConferenceableCallsChanged(CarCall call, List<CarCall> conferenceableCalls) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeTypedList(conferenceableCalls);
                    this.ld.transact(12, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onDetailsChanged(CarCall call, Details details) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (details != null) {
                        obtain.writeInt(1);
                        details.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(8, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onParentChanged(CarCall call, CarCall parent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parent != null) {
                        obtain.writeInt(1);
                        parent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onPostDialWait(CarCall call, String remainingPostDialSequence) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(remainingPostDialSequence);
                    this.ld.transact(10, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onStateChanged(CarCall call, int state) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICarCallListener");
                    if (call != null) {
                        obtain.writeInt(1);
                        call.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(state);
                    this.ld.transact(5, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static ak an(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICarCallListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ak)) ? new a(iBinder) : (ak) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    dispatchPhoneKeyEvent(data.readInt() != 0 ? (KeyEvent) KeyEvent.CREATOR.createFromParcel(data) : null);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onAudioStateChanged(data.readInt() != 0, data.readInt(), data.readInt());
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onCallAdded(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onCallRemoved(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onStateChanged(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, data.readInt());
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onParentChanged(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onChildrenChanged(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, data.createTypedArrayList(CarCall.CREATOR));
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onDetailsChanged(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, data.readInt() != 0 ? (Details) Details.CREATOR.createFromParcel(data) : null);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onCannedTextResponsesLoaded(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, data.createStringArrayList());
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onPostDialWait(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, data.readString());
                    return true;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onCallDestroyed(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null);
                    return true;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    data.enforceInterface("com.google.android.gms.car.ICarCallListener");
                    onConferenceableCallsChanged(data.readInt() != 0 ? (CarCall) CarCall.CREATOR.createFromParcel(data) : null, data.createTypedArrayList(CarCall.CREATOR));
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICarCallListener");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void dispatchPhoneKeyEvent(KeyEvent keyEvent) throws RemoteException;

    void onAudioStateChanged(boolean z, int i, int i2) throws RemoteException;

    void onCallAdded(CarCall carCall) throws RemoteException;

    void onCallDestroyed(CarCall carCall) throws RemoteException;

    void onCallRemoved(CarCall carCall) throws RemoteException;

    void onCannedTextResponsesLoaded(CarCall carCall, List<String> list) throws RemoteException;

    void onChildrenChanged(CarCall carCall, List<CarCall> list) throws RemoteException;

    void onConferenceableCallsChanged(CarCall carCall, List<CarCall> list) throws RemoteException;

    void onDetailsChanged(CarCall carCall, Details details) throws RemoteException;

    void onParentChanged(CarCall carCall, CarCall carCall2) throws RemoteException;

    void onPostDialWait(CarCall carCall, String str) throws RemoteException;

    void onStateChanged(CarCall carCall, int i) throws RemoteException;
}
