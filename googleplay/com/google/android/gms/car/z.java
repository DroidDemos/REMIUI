package com.google.android.gms.car;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public interface z extends IInterface {

    public static abstract class a extends Binder implements z {

        private static class a implements z {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(CarFacet carFacet) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    if (carFacet != null) {
                        obtain.writeInt(1);
                        carFacet.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(aa aaVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeStrongBinder(aaVar != null ? aaVar.asBinder() : null);
                    this.ld.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(al alVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeStrongBinder(alVar != null ? alVar.asBinder() : null);
                    this.ld.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String aF(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeString(str);
                    this.ld.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public bb aH(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeString(str);
                    this.ld.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    bb aD = com.google.android.gms.car.bb.a.aD(obtain2.readStrongBinder());
                    return aD;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void b(aa aaVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeStrongBinder(aaVar != null ? aaVar.asBinder() : null);
                    this.ld.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(al alVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeStrongBinder(alVar != null ? alVar.asBinder() : null);
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean g(String str, boolean z) throws RemoteException {
                boolean z2 = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.ld.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ay gC() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    ay aA = com.google.android.gms.car.ay.a.aA(obtain2.readStrongBinder());
                    return aA;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ab gD() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    ab ae = com.google.android.gms.car.ab.a.ae(obtain2.readStrongBinder());
                    return ae;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public as gE() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    as av = com.google.android.gms.car.as.a.av(obtain2.readStrongBinder());
                    return av;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public aj gF() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    aj am = com.google.android.gms.car.aj.a.am(obtain2.readStrongBinder());
                    return am;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ao gG() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    ao ar = com.google.android.gms.car.ao.a.ar(obtain2.readStrongBinder());
                    return ar;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public am gH() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    am ap = com.google.android.gms.car.am.a.ap(obtain2.readStrongBinder());
                    return ap;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public au gI() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    au ax = com.google.android.gms.car.au.a.ax(obtain2.readStrongBinder());
                    return ax;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public aq gJ() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    aq at = com.google.android.gms.car.aq.a.at(obtain2.readStrongBinder());
                    return at;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ah gK() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    ah ak = com.google.android.gms.car.ah.a.ak(obtain2.readStrongBinder());
                    return ak;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean gh() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(3, obtain, obtain2, 0);
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

            public int gi() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CarInfo gj() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    CarInfo carInfo = obtain2.readInt() != 0 ? (CarInfo) CarInfo.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return carInfo;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CarUiInfo gk() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    CarUiInfo carUiInfo = obtain2.readInt() != 0 ? (CarUiInfo) CarUiInfo.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return carUiInfo;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void gs() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void gt() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    this.ld.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void h(String str, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean k(Intent intent) throws RemoteException {
                boolean z = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(10, obtain, obtain2, 0);
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

            public String o(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void p(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<ResolveInfo> queryIntentCarProjectionServices(Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.car.ICar");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    List<ResolveInfo> createTypedArrayList = obtain2.createTypedArrayList(ResolveInfo.CREATOR);
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static z ac(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.car.ICar");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof z)) ? new a(iBinder) : (z) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean z = false;
            CarFacet carFacet = null;
            boolean gh;
            IBinder asBinder;
            Intent intent;
            int i;
            String o;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    CarInfo gj = gj();
                    reply.writeNoException();
                    if (gj != null) {
                        reply.writeInt(1);
                        gj.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    CarUiInfo gk = gk();
                    reply.writeNoException();
                    if (gk != null) {
                        reply.writeInt(1);
                        gk.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    gh = gh();
                    reply.writeNoException();
                    reply.writeInt(gh ? 1 : 0);
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    int gi = gi();
                    reply.writeNoException();
                    reply.writeInt(gi);
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    a(com.google.android.gms.car.al.a.ao(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    b(com.google.android.gms.car.al.a.ao(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    ay gC = gC();
                    reply.writeNoException();
                    if (gC != null) {
                        asBinder = gC.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    ab gD = gD();
                    reply.writeNoException();
                    if (gD != null) {
                        asBinder = gD.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    as gE = gE();
                    reply.writeNoException();
                    if (gE != null) {
                        asBinder = gE.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    if (data.readInt() != 0) {
                        intent = (Intent) Intent.CREATOR.createFromParcel(data);
                    }
                    gh = k(intent);
                    reply.writeNoException();
                    if (gh) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    if (data.readInt() != 0) {
                        intent = (Intent) Intent.CREATOR.createFromParcel(data);
                    }
                    List queryIntentCarProjectionServices = queryIntentCarProjectionServices(intent);
                    reply.writeNoException();
                    reply.writeTypedList(queryIntentCarProjectionServices);
                    return true;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    aj gF = gF();
                    reply.writeNoException();
                    if (gF != null) {
                        asBinder = gF.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    bb aH = aH(data.readString());
                    reply.writeNoException();
                    if (aH != null) {
                        asBinder = aH.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    ao gG = gG();
                    reply.writeNoException();
                    if (gG != null) {
                        asBinder = gG.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    am gH = gH();
                    reply.writeNoException();
                    if (gH != null) {
                        asBinder = gH.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    au gI = gI();
                    reply.writeNoException();
                    if (gI != null) {
                        asBinder = gI.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    aq gJ = gJ();
                    reply.writeNoException();
                    if (gJ != null) {
                        asBinder = gJ.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    ah gK = gK();
                    reply.writeNoException();
                    if (gK != null) {
                        asBinder = gK.asBinder();
                    }
                    reply.writeStrongBinder(asBinder);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    gh = g(data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    if (gh) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    a(com.google.android.gms.car.aa.a.ad(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    b(com.google.android.gms.car.aa.a.ad(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    gs();
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    o = o(data.readString(), data.readString());
                    reply.writeNoException();
                    reply.writeString(o);
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    p(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    o = data.readString();
                    if (data.readInt() != 0) {
                        z = true;
                    }
                    h(o, z);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    o = aF(data.readString());
                    reply.writeNoException();
                    reply.writeString(o);
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    gt();
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                    data.enforceInterface("com.google.android.gms.car.ICar");
                    if (data.readInt() != 0) {
                        carFacet = (CarFacet) CarFacet.CREATOR.createFromParcel(data);
                    }
                    a(carFacet);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.car.ICar");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(CarFacet carFacet) throws RemoteException;

    void a(aa aaVar) throws RemoteException;

    void a(al alVar) throws RemoteException;

    String aF(String str) throws RemoteException;

    bb aH(String str) throws RemoteException;

    void b(aa aaVar) throws RemoteException;

    void b(al alVar) throws RemoteException;

    boolean g(String str, boolean z) throws RemoteException;

    ay gC() throws RemoteException;

    ab gD() throws RemoteException;

    as gE() throws RemoteException;

    aj gF() throws RemoteException;

    ao gG() throws RemoteException;

    am gH() throws RemoteException;

    au gI() throws RemoteException;

    aq gJ() throws RemoteException;

    ah gK() throws RemoteException;

    boolean gh() throws RemoteException;

    int gi() throws RemoteException;

    CarInfo gj() throws RemoteException;

    CarUiInfo gk() throws RemoteException;

    void gs() throws RemoteException;

    void gt() throws RemoteException;

    void h(String str, boolean z) throws RemoteException;

    boolean k(Intent intent) throws RemoteException;

    String o(String str, String str2) throws RemoteException;

    void p(String str, String str2) throws RemoteException;

    List<ResolveInfo> queryIntentCarProjectionServices(Intent intent) throws RemoteException;
}
