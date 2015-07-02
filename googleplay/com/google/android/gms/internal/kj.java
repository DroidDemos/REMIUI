package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.wallet.instrumentmanager.R;

public interface kj extends IInterface {

    public static abstract class a extends Binder implements kj {

        private static class a implements kj {
            private IBinder ld;

            a(IBinder iBinder) {
                this.ld = iBinder;
            }

            public void a(ki kiVar, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    this.ld.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str, IBinder iBinder, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.ld.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str, String str2, String str3, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStringArray(strArr);
                    this.ld.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str, String str2, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    this.ld.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str, String str2, String[] strArr, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str, String str2, String[] strArr, String str3, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    obtain.writeString(str3);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, int i, String str, String str2, String[] strArr, String str3, IBinder iBinder, String str4, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str4);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void a(ki kiVar, int i, String str, String[] strArr, String str2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    obtain.writeString(str2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(ki kiVar, jz jzVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    if (jzVar != null) {
                        obtain.writeInt(1);
                        jzVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.ld;
            }

            public void b(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void b(ki kiVar, int i, String str, String str2, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    this.ld.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void c(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void d(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void e(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void f(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void g(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void g(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void h(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void h(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void i(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void i(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void j(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void j(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void k(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void k(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void l(ki kiVar, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.ld.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void l(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void m(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void n(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void o(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void p(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void q(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void r(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void s(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void t(ki kiVar, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(kiVar != null ? kiVar.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.ld.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static kj ba(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof kj)) ? new a(iBinder) : (kj) queryLocalInterface;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            jz jzVar = null;
            ki aZ;
            int readInt;
            String readString;
            Bundle bundle;
            switch (code) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.readString(), data.createStringArray(), data.readString(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    a(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt());
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    b(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    c(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    d(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    e(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.readString(), data.createStringArray(), data.readString(), data.readStrongBinder(), data.readString(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.readString(), data.createStringArray());
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    f(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    g(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    h(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    i(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    j(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    k(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    l(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    m(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.readStrongBinder(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.createStringArray(), data.readString(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    b(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    c(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    n(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    d(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    o(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    e(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    p(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    b(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.readString(), data.createStringArray());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.readString(), data.createStringArray(), data.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(data) : null);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModePasteDrawable /*31*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    f(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    g(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.readString(), data.readString(), data.createStringArray());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    a(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    h(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionModePopupWindowStyle /*36*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    i(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_textAppearanceLargePopupMenu /*37*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    q(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_textAppearanceSmallPopupMenu /*38*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    r(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    j(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_spinnerStyle /*41*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    s(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    k(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_homeAsUpIndicator /*43*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    readInt = data.readInt();
                    readString = data.readString();
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    }
                    t(aZ, readInt, readString, bundle);
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_actionButtonStyle /*44*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    l(com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder()), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case com.google.android.play.R.styleable.Theme_buttonBarButtonStyle /*46*/:
                    data.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    aZ = com.google.android.gms.internal.ki.a.aZ(data.readStrongBinder());
                    if (data.readInt() != 0) {
                        jzVar = (jz) jz.CREATOR.createFromParcel(data);
                    }
                    a(aZ, jzVar);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString("com.google.android.gms.common.internal.IGmsServiceBroker");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void a(ki kiVar, int i) throws RemoteException;

    void a(ki kiVar, int i, String str) throws RemoteException;

    void a(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void a(ki kiVar, int i, String str, IBinder iBinder, Bundle bundle) throws RemoteException;

    void a(ki kiVar, int i, String str, String str2) throws RemoteException;

    void a(ki kiVar, int i, String str, String str2, String str3, String[] strArr) throws RemoteException;

    void a(ki kiVar, int i, String str, String str2, String[] strArr) throws RemoteException;

    void a(ki kiVar, int i, String str, String str2, String[] strArr, Bundle bundle) throws RemoteException;

    void a(ki kiVar, int i, String str, String str2, String[] strArr, String str3, Bundle bundle) throws RemoteException;

    void a(ki kiVar, int i, String str, String str2, String[] strArr, String str3, IBinder iBinder, String str4, Bundle bundle) throws RemoteException;

    void a(ki kiVar, int i, String str, String[] strArr, String str2, Bundle bundle) throws RemoteException;

    void a(ki kiVar, jz jzVar) throws RemoteException;

    void b(ki kiVar, int i, String str) throws RemoteException;

    void b(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void b(ki kiVar, int i, String str, String str2, String[] strArr) throws RemoteException;

    void c(ki kiVar, int i, String str) throws RemoteException;

    void c(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void d(ki kiVar, int i, String str) throws RemoteException;

    void d(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void e(ki kiVar, int i, String str) throws RemoteException;

    void e(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void f(ki kiVar, int i, String str) throws RemoteException;

    void f(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void g(ki kiVar, int i, String str) throws RemoteException;

    void g(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void h(ki kiVar, int i, String str) throws RemoteException;

    void h(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void i(ki kiVar, int i, String str) throws RemoteException;

    void i(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void j(ki kiVar, int i, String str) throws RemoteException;

    void j(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void k(ki kiVar, int i, String str) throws RemoteException;

    void k(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void l(ki kiVar, int i, String str) throws RemoteException;

    void l(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void m(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void n(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void o(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void p(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void q(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void r(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void s(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;

    void t(ki kiVar, int i, String str, Bundle bundle) throws RemoteException;
}
