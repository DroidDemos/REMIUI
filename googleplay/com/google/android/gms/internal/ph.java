package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Locale;

public class ph implements SafeParcelable {
    public static final pi CREATOR;
    private final String alx;
    private final int atm;
    private final short ato;
    private final double atp;
    private final double atq;
    private final float atr;
    private final int ats;
    private final int att;
    private final long avT;
    private final int mVersionCode;

    static {
        CREATOR = new pi();
    }

    public ph(int i, String str, int i2, short s, double d, double d2, float f, long j, int i3, int i4) {
        cD(str);
        b(f);
        a(d, d2);
        int ia = ia(i2);
        this.mVersionCode = i;
        this.ato = s;
        this.alx = str;
        this.atp = d;
        this.atq = d2;
        this.atr = f;
        this.avT = j;
        this.atm = ia;
        this.ats = i3;
        this.att = i4;
    }

    private static void a(double d, double d2) {
        if (d > 90.0d || d < -90.0d) {
            throw new IllegalArgumentException("invalid latitude: " + d);
        } else if (d2 > 180.0d || d2 < -180.0d) {
            throw new IllegalArgumentException("invalid longitude: " + d2);
        }
    }

    private static void b(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("invalid radius: " + f);
        }
    }

    private static void cD(String str) {
        if (str == null || str.length() > 100) {
            throw new IllegalArgumentException("requestId is null or too long: " + str);
        }
    }

    private static int ia(int i) {
        int i2 = i & 7;
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("No supported transition specified: " + i);
    }

    private static String ib(int i) {
        switch (i) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return "CIRCLE";
            default:
                return null;
        }
    }

    public int describeContents() {
        pi piVar = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ph)) {
            return false;
        }
        ph phVar = (ph) obj;
        if (this.atr != phVar.atr) {
            return false;
        }
        if (this.atp != phVar.atp) {
            return false;
        }
        if (this.atq != phVar.atq) {
            return false;
        }
        return this.ato == phVar.ato;
    }

    public long getExpirationTime() {
        return this.avT;
    }

    public double getLatitude() {
        return this.atp;
    }

    public double getLongitude() {
        return this.atq;
    }

    public int getNotificationResponsiveness() {
        return this.ats;
    }

    public String getRequestId() {
        return this.alx;
    }

    public int getTransitionTypes() {
        return this.atm;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.atp);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31;
        long doubleToLongBits2 = Double.doubleToLongBits(this.atq);
        return (((((((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.atr)) * 31) + this.ato) * 31) + this.atm;
    }

    public int pA() {
        return this.att;
    }

    public short py() {
        return this.ato;
    }

    public float pz() {
        return this.atr;
    }

    public String toString() {
        return String.format(Locale.US, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[]{ib(this.ato), this.alx, Integer.valueOf(this.atm), Double.valueOf(this.atp), Double.valueOf(this.atq), Float.valueOf(this.atr), Integer.valueOf(this.ats / 1000), Integer.valueOf(this.att), Long.valueOf(this.avT)});
    }

    public void writeToParcel(Parcel parcel, int flags) {
        pi piVar = CREATOR;
        pi.a(this, parcel, flags);
    }
}
