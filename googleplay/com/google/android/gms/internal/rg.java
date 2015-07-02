package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.people.model.AvatarReference;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public interface rg extends IInterface {

    public static abstract class a extends Binder implements rg {

        private static class a implements rg {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public Bundle I(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle J(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle a(rf rfVar, boolean z, String str, String str2, int i) throws RemoteException {
                Bundle bundle = null;
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.ld.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle a(String str, String str2, long j, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle a(String str, String str2, long j, boolean z, boolean z2) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(205, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh a(rf rfVar, DataHolder dataHolder, int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    if (dataHolder != null) {
                        obtain.writeInt(1);
                        dataHolder.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.ld.transact(602, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh a(rf rfVar, qp qpVar, qy qyVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    if (qpVar != null) {
                        obtain.writeInt(1);
                        qpVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (qyVar != null) {
                        obtain.writeInt(1);
                        qyVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(601, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh a(rf rfVar, AvatarReference avatarReference, rk rkVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    if (avatarReference != null) {
                        obtain.writeInt(1);
                        avatarReference.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (rkVar != null) {
                        obtain.writeInt(1);
                        rkVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(508, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh a(rf rfVar, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.ld.transact(509, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh a(rf rfVar, String str, String str2, boolean z, String str3, String str4, int i, int i2, int i3, boolean z2) throws RemoteException {
                int i4 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (!z2) {
                        i4 = 0;
                    }
                    obtain.writeInt(i4);
                    this.ld.transact(507, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, long j, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeLong(j);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(302, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, qp qpVar, List<String> list, qw qwVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    if (qpVar != null) {
                        obtain.writeInt(1);
                        qpVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringList(list);
                    if (qwVar != null) {
                        obtain.writeInt(1);
                        qwVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(501, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    this.ld.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ld.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.ld.transact(403, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ld.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, Uri uri) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
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

            public void a(rf rfVar, String str, String str2, Uri uri, boolean z) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.ld.transact(204, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, int i, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeString(str4);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, int i, String str4, boolean z) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeString(str4);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    this.ld.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4) throws RemoteException {
                int i4 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    if (z) {
                        i4 = 1;
                    }
                    obtain.writeInt(i4);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeString(str4);
                    this.ld.transact(202, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4, boolean z2) throws RemoteException {
                int i4 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeString(str4);
                    if (!z2) {
                        i4 = 0;
                    }
                    obtain.writeInt(i4);
                    this.ld.transact(203, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4, boolean z2, int i4, int i5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeString(str4);
                    obtain.writeInt(z2 ? 1 : 0);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    this.ld.transact(402, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    this.ld.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, String str4, int i, String str5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    obtain.writeInt(i);
                    obtain.writeString(str5);
                    this.ld.transact(303, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    this.ld.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, List<String> list, int i, boolean z, long j) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    obtain.writeInt(i);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeLong(j);
                    obtain.writeString(str4);
                    obtain.writeInt(i2);
                    this.ld.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeLong(j);
                    obtain.writeString(str4);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.ld.transact(401, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeLong(j);
                    obtain.writeString(str4);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.ld.transact(404, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, List<String> list, List<String> list2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    obtain.writeStringList(list2);
                    this.ld.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, List<String> list, List<String> list2, ld ldVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringList(list);
                    obtain.writeStringList(list2);
                    if (ldVar != null) {
                        obtain.writeInt(1);
                        ldVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, boolean z, int i) throws RemoteException {
                int i2 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (z) {
                        i2 = 1;
                    }
                    obtain.writeInt(i2);
                    obtain.writeInt(i);
                    this.ld.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, String str2, String str3, boolean z, int i, int i2) throws RemoteException {
                int i3 = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (z) {
                        i3 = 1;
                    }
                    obtain.writeInt(i3);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ld.transact(201, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, String str, boolean z, String[] strArr) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    obtain.writeStringArray(strArr);
                    this.ld.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, boolean z, boolean z2, String str, String str2) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(rf rfVar, boolean z, boolean z2, String str, String str2, int i) throws RemoteException {
                int i2 = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.ld.transact(305, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public Bundle b(String str, String str2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    this.ld.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh b(rf rfVar, long j, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeLong(j);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(503, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh b(rf rfVar, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    this.ld.transact(504, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh b(rf rfVar, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ld.transact(502, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh b(rf rfVar, String str, String str2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.ld.transact(505, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(rf rfVar, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(304, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(rf rfVar, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(101, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(rf rfVar, String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.ld.transact(301, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(rf rfVar, String str, String str2, String str3, int i, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeString(str4);
                    this.ld.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(rf rfVar, String str, String str2, String str3, boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(603, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public kh c(rf rfVar, String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.ld.transact(506, obtain, obtain2, 0);
                    obtain2.readException();
                    kh aY = com.google.android.gms.internal.kh.a.aY(obtain2.readStrongBinder());
                    return aY;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(rf rfVar, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    obtain.writeStrongBinder(rfVar != null ? rfVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(102, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isSyncToContactsEnabled() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    this.ld.transact(16, obtain, obtain2, 0);
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

            public Bundle j(Uri uri) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setSyncToContactsEnabled(boolean enable) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.people.internal.IPeopleService");
                    if (enable) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.ld.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static rg dq(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.people.internal.IPeopleService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof rg)) ? new a(iBinder) : (rg) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle j;
            kh b;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0, data.readInt() != 0, data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.readInt(), data.readInt() != 0, data.readLong());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readLong(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    j = j(data.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    if (j != null) {
                        reply.writeInt(1);
                        j.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt() != 0, data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readInt() != 0, data.createStringArray());
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    j = a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0, data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    if (j != null) {
                        reply.writeInt(1);
                        j.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    j = I(data.readString(), data.readString());
                    reply.writeNoException();
                    if (j != null) {
                        reply.writeInt(1);
                        j.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.createStringArrayList());
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    setSyncToContactsEnabled(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    boolean isSyncToContactsEnabled = isSyncToContactsEnabled();
                    reply.writeNoException();
                    reply.writeInt(isSyncToContactsEnabled ? 1 : 0);
                    return true;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    j = J(data.readString(), data.readString());
                    reply.writeNoException();
                    if (j != null) {
                        reply.writeInt(1);
                        j.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(data) : null, data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    j = b(data.readString(), data.readString(), data.readLong());
                    reply.writeNoException();
                    if (j != null) {
                        reply.writeInt(1);
                        j.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.readInt(), data.readInt() != 0, data.readLong(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.createStringArrayList(), data.readInt() != 0 ? ld.CREATOR.bX(data) : null);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    j = a(data.readString(), data.readString(), data.readLong(), data.readInt() != 0);
                    reply.writeNoException();
                    if (j != null) {
                        reply.writeInt(1);
                        j.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 101:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case 102:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    c(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case 201:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt() != 0, data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 202:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readInt() != 0, data.readInt(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 203:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readInt() != 0, data.readInt(), data.readInt(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 204:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case 205:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    j = a(data.readString(), data.readString(), data.readLong(), data.readInt() != 0, data.readInt() != 0);
                    reply.writeNoException();
                    if (j != null) {
                        reply.writeInt(1);
                        j.writeToParcel(reply, 1);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                case 301:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 302:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case 303:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readString(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 304:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case 305:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0, data.readInt() != 0, data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 401:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.readInt(), data.readInt() != 0, data.readLong(), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 402:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt(), data.readInt() != 0, data.readInt(), data.readInt(), data.readString(), data.readInt() != 0, data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 403:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 404:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.createStringArrayList(), data.readInt(), data.readInt() != 0, data.readLong(), data.readString(), data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 501:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0 ? qp.CREATOR.gG(data) : null, data.createStringArrayList(), data.readInt() != 0 ? qw.CREATOR.gH(data) : null);
                    reply.writeNoException();
                    return true;
                case 502:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 503:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readLong(), data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 504:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString());
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 505:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 506:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = c(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 507:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readInt() != 0, data.readString(), data.readString(), data.readInt(), data.readInt(), data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 508:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0 ? AvatarReference.CREATOR.gL(data) : null, data.readInt() != 0 ? rk.CREATOR.gJ(data) : null);
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 509:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readInt());
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 601:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0 ? qp.CREATOR.gG(data) : null, data.readInt() != 0 ? qy.CREATOR.gI(data) : null);
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 602:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b = a(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readInt() != 0 ? DataHolder.CREATOR.bO(data) : null, data.readInt(), data.readInt(), data.readLong());
                    reply.writeNoException();
                    reply.writeStrongBinder(b != null ? b.asBinder() : null);
                    return true;
                case 603:
                    data.enforceInterface("com.google.android.gms.people.internal.IPeopleService");
                    b(com.google.android.gms.internal.rf.a.dp(data.readStrongBinder()), data.readString(), data.readString(), data.readString(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.people.internal.IPeopleService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    Bundle I(String str, String str2) throws RemoteException;

    Bundle J(String str, String str2) throws RemoteException;

    Bundle a(rf rfVar, boolean z, String str, String str2, int i) throws RemoteException;

    Bundle a(String str, String str2, long j, boolean z) throws RemoteException;

    Bundle a(String str, String str2, long j, boolean z, boolean z2) throws RemoteException;

    kh a(rf rfVar, DataHolder dataHolder, int i, int i2, long j) throws RemoteException;

    kh a(rf rfVar, qp qpVar, qy qyVar) throws RemoteException;

    kh a(rf rfVar, AvatarReference avatarReference, rk rkVar) throws RemoteException;

    kh a(rf rfVar, String str, int i) throws RemoteException;

    kh a(rf rfVar, String str, String str2, boolean z, String str3, String str4, int i, int i2, int i3, boolean z2) throws RemoteException;

    void a(rf rfVar, long j, boolean z) throws RemoteException;

    void a(rf rfVar, Bundle bundle) throws RemoteException;

    void a(rf rfVar, qp qpVar, List<String> list, qw qwVar) throws RemoteException;

    void a(rf rfVar, String str) throws RemoteException;

    void a(rf rfVar, String str, int i, int i2) throws RemoteException;

    void a(rf rfVar, String str, String str2) throws RemoteException;

    void a(rf rfVar, String str, String str2, int i) throws RemoteException;

    void a(rf rfVar, String str, String str2, int i, int i2) throws RemoteException;

    void a(rf rfVar, String str, String str2, Uri uri) throws RemoteException;

    void a(rf rfVar, String str, String str2, Uri uri, boolean z) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, int i, String str4) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, int i, String str4, boolean z) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4, boolean z2) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, int i, boolean z, int i2, int i3, String str4, boolean z2, int i4, int i5) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, String str4) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, String str4, int i, String str5) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, List<String> list) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, List<String> list, int i, boolean z, long j) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2, int i3) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, List<String> list, int i, boolean z, long j, String str4, int i2, int i3, int i4) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, List<String> list, List<String> list2) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, List<String> list, List<String> list2, ld ldVar) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, boolean z) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, boolean z, int i) throws RemoteException;

    void a(rf rfVar, String str, String str2, String str3, boolean z, int i, int i2) throws RemoteException;

    void a(rf rfVar, String str, boolean z, String[] strArr) throws RemoteException;

    void a(rf rfVar, boolean z, boolean z2, String str, String str2) throws RemoteException;

    void a(rf rfVar, boolean z, boolean z2, String str, String str2, int i) throws RemoteException;

    Bundle b(String str, String str2, long j) throws RemoteException;

    kh b(rf rfVar, long j, boolean z) throws RemoteException;

    kh b(rf rfVar, String str) throws RemoteException;

    kh b(rf rfVar, String str, int i, int i2) throws RemoteException;

    kh b(rf rfVar, String str, String str2, int i, int i2) throws RemoteException;

    void b(rf rfVar, Bundle bundle) throws RemoteException;

    void b(rf rfVar, String str, String str2) throws RemoteException;

    void b(rf rfVar, String str, String str2, int i) throws RemoteException;

    void b(rf rfVar, String str, String str2, String str3, int i, String str4) throws RemoteException;

    void b(rf rfVar, String str, String str2, String str3, boolean z) throws RemoteException;

    kh c(rf rfVar, String str, String str2, int i) throws RemoteException;

    void c(rf rfVar, String str, String str2) throws RemoteException;

    boolean isSyncToContactsEnabled() throws RemoteException;

    Bundle j(Uri uri) throws RemoteException;

    void setSyncToContactsEnabled(boolean z) throws RemoteException;
}
