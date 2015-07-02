package com.google.android.gms.auth.firstparty.proximity.data;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Permit implements SafeParcelable {
    public static final d CREATOR;
    final String CB;
    final int Gf;
    final String Ie;
    final PermitAccess If;
    List<PermitAccess> Ig;
    final Map<String, PermitAccess> Ih;
    List<String> Ii;
    final Set<String> Ij;
    final String vc;

    static {
        CREATOR = new d();
    }

    Permit(int version, String id, String accountId, String type, PermitAccess license, List<PermitAccess> requesterAccessesCache, List<String> allowedChannelsCache) {
        this(version, id, accountId, type, license, e(requesterAccessesCache), new HashSet(allowedChannelsCache));
    }

    private Permit(int version, String id, String accountId, String type, PermitAccess license, Map<String, PermitAccess> requesterAccesses, Set<String> allowedChannels) {
        this.Gf = version;
        this.CB = kn.bk(id);
        this.Ie = kn.bk(accountId);
        this.vc = kn.bk(type);
        this.If = (PermitAccess) kn.k(license);
        this.Ih = requesterAccesses == null ? new HashMap() : new HashMap(requesterAccesses);
        this.Ij = allowedChannels == null ? new HashSet() : new HashSet(allowedChannels);
    }

    private static Map<String, PermitAccess> e(List<PermitAccess> list) {
        Map<String, PermitAccess> hashMap = new HashMap();
        for (PermitAccess permitAccess : list) {
            hashMap.put(permitAccess.getId(), permitAccess);
        }
        return hashMap;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Permit)) {
            return false;
        }
        Permit permit = (Permit) obj;
        return TextUtils.equals(this.Ie, permit.Ie) && TextUtils.equals(this.CB, permit.CB) && TextUtils.equals(this.vc, permit.vc) && this.If.equals(permit.If) && this.Ij.equals(permit.Ij) && this.Ih.equals(permit.Ih);
    }

    public int hashCode() {
        return (31 * (((((((((this.CB.hashCode() + 527) * 31) + this.Ie.hashCode()) * 31) + this.vc.hashCode()) * 31) + this.Ij.hashCode()) * 31) + this.If.hashCode())) + this.Ih.hashCode();
    }

    public void writeToParcel(Parcel dest, int flags) {
        this.Ig = new ArrayList(this.Ih.values());
        this.Ii = new ArrayList(this.Ij);
        d.a(this, dest, flags);
    }
}
