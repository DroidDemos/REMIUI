package com.google.android.gms.games.internal.game;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.kl;

public final class GameBadgeEntity extends GamesDowngradeableSafeParcel implements GameBadge {
    public static final GameBadgeEntityCreator CREATOR;
    private int EB;
    private String Yv;
    private Uri aiK;
    private String mDescription;
    private final int mVersionCode;

    static final class GameBadgeEntityCreatorCompat extends GameBadgeEntityCreator {
        GameBadgeEntityCreatorCompat() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return eN(x0);
        }

        public GameBadgeEntity eN(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(jy.iM()) || jy.bf(GameBadgeEntity.class.getCanonicalName())) {
                return super.eN(parcel);
            }
            int readInt = parcel.readInt();
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            return new GameBadgeEntity(1, readInt, readString, readString2, readString3 == null ? null : Uri.parse(readString3));
        }
    }

    static {
        CREATOR = new GameBadgeEntityCreatorCompat();
    }

    GameBadgeEntity(int versionCode, int type, String title, String description, Uri iconImageUri) {
        this.mVersionCode = versionCode;
        this.EB = type;
        this.Yv = title;
        this.mDescription = description;
        this.aiK = iconImageUri;
    }

    public GameBadgeEntity(GameBadge gameBadge) {
        this.mVersionCode = 1;
        this.EB = gameBadge.getType();
        this.Yv = gameBadge.getTitle();
        this.mDescription = gameBadge.getDescription();
        this.aiK = gameBadge.getIconImageUri();
    }

    static int a(GameBadge gameBadge) {
        return kl.hashCode(Integer.valueOf(gameBadge.getType()), gameBadge.getTitle(), gameBadge.getDescription(), gameBadge.getIconImageUri());
    }

    static boolean a(GameBadge gameBadge, Object obj) {
        if (!(obj instanceof GameBadge)) {
            return false;
        }
        if (gameBadge == obj) {
            return true;
        }
        GameBadge gameBadge2 = (GameBadge) obj;
        return kl.equal(Integer.valueOf(gameBadge2.getType()), gameBadge.getTitle()) && kl.equal(gameBadge2.getDescription(), gameBadge.getIconImageUri());
    }

    static String b(GameBadge gameBadge) {
        return kl.j(gameBadge).a("Type", Integer.valueOf(gameBadge.getType())).a("Title", gameBadge.getTitle()).a("Description", gameBadge.getDescription()).a("IconImageUri", gameBadge.getIconImageUri()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return nZ();
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Uri getIconImageUri() {
        return this.aiK;
    }

    public String getTitle() {
        return this.Yv;
    }

    public int getType() {
        return this.EB;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public GameBadge nZ() {
        return this;
    }

    public String toString() {
        return b((GameBadge) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (iN()) {
            dest.writeInt(this.EB);
            dest.writeString(this.Yv);
            dest.writeString(this.mDescription);
            dest.writeString(this.aiK == null ? null : this.aiK.toString());
            return;
        }
        GameBadgeEntityCreator.a(this, dest, flags);
    }
}
