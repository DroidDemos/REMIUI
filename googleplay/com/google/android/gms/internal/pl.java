package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class pl extends Place implements SafeParcelable {
    public static final pm CREATOR;
    private final String CB;
    private final String agh;
    private final LatLng awP;
    private final List<PlaceType> awQ;
    private final Bundle awT;
    private final float awU;
    private final LatLngBounds awV;
    private final String awW;
    private final Uri awX;
    private final boolean awY;
    private final float awZ;
    private final int axa;
    private final long axb;
    private final String axc;
    private final List<String> axd;
    @Deprecated
    private final pn axe;
    private final boolean axf;
    private final Map<PlaceType, String> axg;
    private final TimeZone axh;
    private Locale axi;
    private pp axj;
    private final String mName;
    private final String mPhoneNumber;
    final int mVersionCode;

    static {
        CREATOR = new pm();
    }

    pl(int i, String str, List<PlaceType> list, Bundle bundle, String str2, String str3, String str4, String str5, List<String> list2, LatLng latLng, float f, LatLngBounds latLngBounds, String str6, Uri uri, boolean z, float f2, int i2, long j, boolean z2, pn pnVar) {
        this.mVersionCode = i;
        this.CB = str;
        this.awQ = Collections.unmodifiableList(list);
        this.awT = bundle;
        this.mName = str2;
        this.agh = str3;
        this.mPhoneNumber = str4;
        this.axc = str5;
        this.axd = list2;
        this.awP = latLng;
        this.awU = f;
        this.awV = latLngBounds;
        this.awW = str6;
        this.awX = uri;
        this.awY = z;
        this.awZ = f2;
        this.axa = i2;
        this.axb = j;
        Map hashMap = new HashMap();
        for (String str7 : bundle.keySet()) {
            hashMap.put(PlaceType.create(str7), bundle.getString(str7));
        }
        this.axg = Collections.unmodifiableMap(hashMap);
        this.axh = TimeZone.getTimeZone(this.awW);
        this.axi = null;
        this.axf = z2;
        this.axe = pnVar;
    }

    private void cE(String str) {
        if (pF() && this.axj != null) {
            this.axj.log(this.CB, str);
        }
    }

    public int describeContents() {
        pm pmVar = CREATOR;
        return 0;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof pl)) {
            return false;
        }
        pl plVar = (pl) object;
        return this.CB.equals(plVar.CB) && kl.equal(this.axi, plVar.axi) && this.axb == plVar.axb;
    }

    public String getAddress() {
        cE("getAddress");
        return this.agh;
    }

    public String getId() {
        cE("getId");
        return this.CB;
    }

    public LatLng getLatLng() {
        cE("getLatLng");
        return this.awP;
    }

    public float getLevelNumber() {
        cE("getLevelNumber");
        return this.awU;
    }

    public String getName() {
        cE("getName");
        return this.mName;
    }

    public String getPhoneNumber() {
        cE("getPhoneNumber");
        return this.mPhoneNumber;
    }

    public int getPriceLevel() {
        cE("getPriceLevel");
        return this.axa;
    }

    public float getRating() {
        cE("getRating");
        return this.awZ;
    }

    public List<PlaceType> getTypes() {
        cE("getTypes");
        return this.awQ;
    }

    public LatLngBounds getViewport() {
        cE("getViewport");
        return this.awV;
    }

    public Uri getWebsiteUri() {
        cE("getWebsiteUri");
        return this.awX;
    }

    public int hashCode() {
        return kl.hashCode(this.CB, this.axi, Long.valueOf(this.axb));
    }

    public boolean isPermanentlyClosed() {
        cE("isPermanentlyClosed");
        return this.awY;
    }

    public String pC() {
        cE("getRegularOpenHours");
        return this.axc;
    }

    public List<String> pD() {
        cE("getAttributions");
        return this.axd;
    }

    public long pE() {
        return this.axb;
    }

    public boolean pF() {
        return this.axf;
    }

    public Bundle pG() {
        return this.awT;
    }

    public String pH() {
        return this.awW;
    }

    @Deprecated
    public pn pI() {
        return this.axe;
    }

    public String toString() {
        return kl.j(this).a("id", this.CB).a("types", this.awQ).a("locale", this.axi).a("name", this.mName).a("address", this.agh).a("phoneNumber", this.mPhoneNumber).a("regularOpenHours", this.axc).a("latlng", this.awP).a("levelNumber", Float.valueOf(this.awU)).a("viewport", this.awV).a("timeZone", this.awW).a("websiteUri", this.awX).a("isPermanentlyClosed", Boolean.valueOf(this.awY)).a("priceLevel", Integer.valueOf(this.axa)).a("timestampSecs", Long.valueOf(this.axb)).toString();
    }

    public void writeToParcel(Parcel parcel, int flags) {
        pm pmVar = CREATOR;
        pm.a(this, parcel, flags);
    }
}
