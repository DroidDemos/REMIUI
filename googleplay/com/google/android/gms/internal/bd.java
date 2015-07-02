package com.google.android.gms.internal;

import android.content.Context;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@fd
public final class bd implements SafeParcelable {
    public static final be CREATOR;
    public final int height;
    public final int heightPixels;
    public final String or;
    public final boolean os;
    public final bd[] ot;
    public final int versionCode;
    public final int width;
    public final int widthPixels;

    static {
        CREATOR = new be();
    }

    public bd() {
        this(2, "interstitial_mb", 0, 0, true, 0, 0, null);
    }

    bd(int i, String str, int i2, int i3, boolean z, int i4, int i5, bd[] bdVarArr) {
        this.versionCode = i;
        this.or = str;
        this.height = i2;
        this.heightPixels = i3;
        this.os = z;
        this.width = i4;
        this.widthPixels = i5;
        this.ot = bdVarArr;
    }

    public bd(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    public bd(Context context, AdSize[] adSizeArr) {
        int i;
        int i2;
        int i3 = 0;
        AdSize adSize = adSizeArr[0];
        this.versionCode = 2;
        this.os = false;
        this.width = adSize.getWidth();
        this.height = adSize.getHeight();
        int i4 = this.width == -1 ? 1 : 0;
        int i5 = this.height == -2 ? 1 : 0;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (i4 != 0) {
            this.widthPixels = a(displayMetrics);
            i = (int) (((float) this.widthPixels) / displayMetrics.density);
        } else {
            i2 = this.width;
            this.widthPixels = gv.a(displayMetrics, this.width);
            i = i2;
        }
        i2 = i5 != 0 ? c(displayMetrics) : this.height;
        this.heightPixels = gv.a(displayMetrics, i2);
        if (i4 == 0 && i5 == 0) {
            this.or = adSize.toString();
        } else {
            this.or = i + "x" + i2 + "_as";
        }
        if (adSizeArr.length > 1) {
            this.ot = new bd[adSizeArr.length];
            while (i3 < adSizeArr.length) {
                this.ot[i3] = new bd(context, adSizeArr[i3]);
                i3++;
            }
            return;
        }
        this.ot = null;
    }

    public static int a(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    private static int c(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        return i <= 400 ? 32 : i <= 720 ? 50 : 90;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        be.a(this, out, flags);
    }
}
