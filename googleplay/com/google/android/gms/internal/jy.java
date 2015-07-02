package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class jy implements SafeParcelable {
    private static final Object VD;
    private static ClassLoader VE;
    private static Integer VF;
    private boolean VG;

    static {
        VD = new Object();
        VE = null;
        VF = null;
    }

    public jy() {
        this.VG = false;
    }

    private static boolean a(Class<?> cls) {
        boolean z = false;
        try {
            z = SafeParcelable.NULL.equals(cls.getField("NULL").get(null));
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e2) {
        }
        return z;
    }

    protected static boolean bf(String str) {
        ClassLoader iL = iL();
        if (iL == null) {
            return true;
        }
        try {
            return a(iL.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    protected static ClassLoader iL() {
        ClassLoader classLoader;
        synchronized (VD) {
            classLoader = VE;
        }
        return classLoader;
    }

    protected static Integer iM() {
        Integer num;
        synchronized (VD) {
            num = VF;
        }
        return num;
    }

    protected boolean iN() {
        return this.VG;
    }
}
