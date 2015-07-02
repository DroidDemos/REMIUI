package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.im;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CastDevice implements SafeParcelable {
    public static final Creator<CastDevice> CREATOR;
    private String NS;
    String NT;
    private Inet4Address NU;
    private String NV;
    private String NW;
    private String NX;
    private int NY;
    private List<WebImage> NZ;
    private int Oa;
    private int Ob;
    private final int mVersionCode;

    static {
        CREATOR = new b();
    }

    private CastDevice() {
        this(3, null, null, null, null, null, -1, new ArrayList(), 0, -1);
    }

    CastDevice(int versionCode, String deviceId, String hostAddress, String friendlyName, String modelName, String deviceVersion, int servicePort, List<WebImage> icons, int capabilities, int status) {
        this.mVersionCode = versionCode;
        this.NS = deviceId;
        this.NT = hostAddress;
        if (this.NT != null) {
            try {
                InetAddress byName = InetAddress.getByName(this.NT);
                if (byName instanceof Inet4Address) {
                    this.NU = (Inet4Address) byName;
                }
            } catch (UnknownHostException e) {
                this.NU = null;
            }
        }
        this.NV = friendlyName;
        this.NW = modelName;
        this.NX = deviceVersion;
        this.NY = servicePort;
        this.NZ = icons;
        this.Oa = capabilities;
        this.Ob = status;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CastDevice)) {
            return false;
        }
        CastDevice castDevice = (CastDevice) obj;
        return getDeviceId() == null ? castDevice.getDeviceId() == null : im.a(this.NS, castDevice.NS) && im.a(this.NU, castDevice.NU) && im.a(this.NW, castDevice.NW) && im.a(this.NV, castDevice.NV) && im.a(this.NX, castDevice.NX) && this.NY == castDevice.NY && im.a(this.NZ, castDevice.NZ) && this.Oa == castDevice.Oa && this.Ob == castDevice.Ob;
    }

    public int getCapabilities() {
        return this.Oa;
    }

    public String getDeviceId() {
        return this.NS;
    }

    public String getDeviceVersion() {
        return this.NX;
    }

    public String getFriendlyName() {
        return this.NV;
    }

    public List<WebImage> getIcons() {
        return Collections.unmodifiableList(this.NZ);
    }

    public String getModelName() {
        return this.NW;
    }

    public int getServicePort() {
        return this.NY;
    }

    public int getStatus() {
        return this.Ob;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.NS == null ? 0 : this.NS.hashCode();
    }

    public String toString() {
        return String.format("\"%s\" (%s)", new Object[]{this.NV, this.NS});
    }

    public void writeToParcel(Parcel out, int flags) {
        b.a(this, out, flags);
    }
}
