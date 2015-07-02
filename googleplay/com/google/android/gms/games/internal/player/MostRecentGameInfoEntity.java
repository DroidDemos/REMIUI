package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class MostRecentGameInfoEntity implements SafeParcelable, MostRecentGameInfo {
    public static final MostRecentGameInfoEntityCreator CREATOR;
    private final String aoG;
    private final String aoH;
    private final long aoI;
    private final Uri aoJ;
    private final Uri aoK;
    private final Uri aoL;
    private final int mVersionCode;

    static {
        CREATOR = new MostRecentGameInfoEntityCreator();
    }

    MostRecentGameInfoEntity(int versionCode, String gameId, String gameName, long activityTimestampMillis, Uri gameIconImageUri, Uri gameHiResIconImageUri, Uri gameFeaturedImageUri) {
        this.mVersionCode = versionCode;
        this.aoG = gameId;
        this.aoH = gameName;
        this.aoI = activityTimestampMillis;
        this.aoJ = gameIconImageUri;
        this.aoK = gameHiResIconImageUri;
        this.aoL = gameFeaturedImageUri;
    }

    public MostRecentGameInfoEntity(MostRecentGameInfo info) {
        this.mVersionCode = 2;
        this.aoG = info.ol();
        this.aoH = info.om();
        this.aoI = info.on();
        this.aoJ = info.oo();
        this.aoK = info.op();
        this.aoL = info.oq();
    }

    static int a(MostRecentGameInfo mostRecentGameInfo) {
        return kl.hashCode(mostRecentGameInfo.ol(), mostRecentGameInfo.om(), Long.valueOf(mostRecentGameInfo.on()), mostRecentGameInfo.oo(), mostRecentGameInfo.op(), mostRecentGameInfo.oq());
    }

    static boolean a(MostRecentGameInfo mostRecentGameInfo, Object obj) {
        if (!(obj instanceof MostRecentGameInfo)) {
            return false;
        }
        if (mostRecentGameInfo == obj) {
            return true;
        }
        MostRecentGameInfo mostRecentGameInfo2 = (MostRecentGameInfo) obj;
        return kl.equal(mostRecentGameInfo2.ol(), mostRecentGameInfo.ol()) && kl.equal(mostRecentGameInfo2.om(), mostRecentGameInfo.om()) && kl.equal(Long.valueOf(mostRecentGameInfo2.on()), Long.valueOf(mostRecentGameInfo.on())) && kl.equal(mostRecentGameInfo2.oo(), mostRecentGameInfo.oo()) && kl.equal(mostRecentGameInfo2.op(), mostRecentGameInfo.op()) && kl.equal(mostRecentGameInfo2.oq(), mostRecentGameInfo.oq());
    }

    static String b(MostRecentGameInfo mostRecentGameInfo) {
        return kl.j(mostRecentGameInfo).a("GameId", mostRecentGameInfo.ol()).a("GameName", mostRecentGameInfo.om()).a("ActivityTimestampMillis", Long.valueOf(mostRecentGameInfo.on())).a("GameIconUri", mostRecentGameInfo.oo()).a("GameHiResUri", mostRecentGameInfo.op()).a("GameFeaturedUri", mostRecentGameInfo.oq()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return or();
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public String ol() {
        return this.aoG;
    }

    public String om() {
        return this.aoH;
    }

    public long on() {
        return this.aoI;
    }

    public Uri oo() {
        return this.aoJ;
    }

    public Uri op() {
        return this.aoK;
    }

    public Uri oq() {
        return this.aoL;
    }

    public MostRecentGameInfo or() {
        return this;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        MostRecentGameInfoEntityCreator.a(this, out, flags);
    }
}
