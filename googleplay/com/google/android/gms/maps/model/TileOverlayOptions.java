package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.aa;
import com.google.android.gms.maps.model.internal.o;
import com.google.android.gms.maps.model.internal.o.a;

public final class TileOverlayOptions implements SafeParcelable {
    public static final w CREATOR;
    private o aAI;
    private TileProvider aAJ;
    private boolean aAK;
    private float aAc;
    private boolean aAd;
    private final int mVersionCode;

    static {
        CREATOR = new w();
    }

    public TileOverlayOptions() {
        this.aAd = true;
        this.aAK = true;
        this.mVersionCode = 1;
    }

    TileOverlayOptions(int versionCode, IBinder delegate, boolean visible, float zIndex, boolean fadeIn) {
        this.aAd = true;
        this.aAK = true;
        this.mVersionCode = versionCode;
        this.aAI = a.dk(delegate);
        this.aAJ = this.aAI == null ? null : new TileProvider(this) {
            private final o aAL;
            final /* synthetic */ TileOverlayOptions aAM;

            {
                this.aAM = r2;
                this.aAL = this.aAM.aAI;
            }
        };
        this.aAd = visible;
        this.aAc = zIndex;
        this.aAK = fadeIn;
    }

    public int describeContents() {
        return 0;
    }

    public boolean getFadeIn() {
        return this.aAK;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.aAc;
    }

    public boolean isVisible() {
        return this.aAd;
    }

    IBinder qu() {
        return this.aAI.asBinder();
    }

    public void writeToParcel(Parcel out, int flags) {
        if (aa.qp()) {
            x.a(this, out, flags);
        } else {
            w.a(this, out, flags);
        }
    }
}
