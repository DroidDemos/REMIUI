package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.kl;

public final class GameEntity extends GamesDowngradeableSafeParcel implements Game {
    public static final Creator<GameEntity> CREATOR;
    private final String NA;
    private final String WN;
    private final String aiH;
    private final String aiI;
    private final String aiJ;
    private final Uri aiK;
    private final Uri aiL;
    private final Uri aiM;
    private final boolean aiN;
    private final boolean aiO;
    private final String aiP;
    private final int aiQ;
    private final int aiR;
    private final int aiS;
    private final boolean aiT;
    private final boolean aiU;
    private final String aiV;
    private final String aiW;
    private final String aiX;
    private final boolean aiY;
    private final boolean aiZ;
    private final boolean aja;
    private final String ajb;
    private final String mDescription;
    private final int mVersionCode;

    static final class GameEntityCreatorCompat extends GameEntityCreator {
        GameEntityCreatorCompat() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return eD(x0);
        }

        public GameEntity eD(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(jy.iM()) || jy.bf(GameEntity.class.getCanonicalName())) {
                return super.eD(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            String readString5 = parcel.readString();
            String readString6 = parcel.readString();
            String readString7 = parcel.readString();
            Uri parse = readString7 == null ? null : Uri.parse(readString7);
            readString7 = parcel.readString();
            Uri parse2 = readString7 == null ? null : Uri.parse(readString7);
            readString7 = parcel.readString();
            return new GameEntity(5, readString, readString2, readString3, readString4, readString5, readString6, parse, parse2, readString7 == null ? null : Uri.parse(readString7), parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, null, null, null, false, false, false, null);
        }
    }

    static {
        CREATOR = new GameEntityCreatorCompat();
    }

    GameEntity(int versionCode, String applicationId, String displayName, String primaryCategory, String secondaryCategory, String description, String developerName, Uri iconImageUri, Uri hiResImageUri, Uri featuredImageUri, boolean playEnabledGame, boolean instanceInstalled, String instancePackageName, int gameplayAclStatus, int achievementTotalCount, int leaderboardCount, boolean realTimeEnabled, boolean turnBasedEnabled, String iconImageUrl, String hiResImageUrl, String featuredImageUrl, boolean muted, boolean identitySharingConfirmed, boolean snapshotsEnabled, String themeColor) {
        this.mVersionCode = versionCode;
        this.NA = applicationId;
        this.WN = displayName;
        this.aiH = primaryCategory;
        this.aiI = secondaryCategory;
        this.mDescription = description;
        this.aiJ = developerName;
        this.aiK = iconImageUri;
        this.aiV = iconImageUrl;
        this.aiL = hiResImageUri;
        this.aiW = hiResImageUrl;
        this.aiM = featuredImageUri;
        this.aiX = featuredImageUrl;
        this.aiN = playEnabledGame;
        this.aiO = instanceInstalled;
        this.aiP = instancePackageName;
        this.aiQ = gameplayAclStatus;
        this.aiR = achievementTotalCount;
        this.aiS = leaderboardCount;
        this.aiT = realTimeEnabled;
        this.aiU = turnBasedEnabled;
        this.aiY = muted;
        this.aiZ = identitySharingConfirmed;
        this.aja = snapshotsEnabled;
        this.ajb = themeColor;
    }

    public GameEntity(Game game) {
        this.mVersionCode = 5;
        this.NA = game.getApplicationId();
        this.aiH = game.getPrimaryCategory();
        this.aiI = game.getSecondaryCategory();
        this.mDescription = game.getDescription();
        this.aiJ = game.getDeveloperName();
        this.WN = game.getDisplayName();
        this.aiK = game.getIconImageUri();
        this.aiV = game.getIconImageUrl();
        this.aiL = game.getHiResImageUri();
        this.aiW = game.getHiResImageUrl();
        this.aiM = game.getFeaturedImageUri();
        this.aiX = game.getFeaturedImageUrl();
        this.aiN = game.mn();
        this.aiO = game.mp();
        this.aiP = game.mq();
        this.aiQ = game.mr();
        this.aiR = game.getAchievementTotalCount();
        this.aiS = game.getLeaderboardCount();
        this.aiT = game.isRealTimeMultiplayerEnabled();
        this.aiU = game.isTurnBasedMultiplayerEnabled();
        this.aiY = game.isMuted();
        this.aiZ = game.mo();
        this.aja = game.areSnapshotsEnabled();
        this.ajb = game.getThemeColor();
    }

    static int a(Game game) {
        return kl.hashCode(game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), Boolean.valueOf(game.mn()), Boolean.valueOf(game.mp()), game.mq(), Integer.valueOf(game.mr()), Integer.valueOf(game.getAchievementTotalCount()), Integer.valueOf(game.getLeaderboardCount()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isTurnBasedMultiplayerEnabled()), Boolean.valueOf(game.isMuted()), Boolean.valueOf(game.mo()), Boolean.valueOf(game.areSnapshotsEnabled()), game.getThemeColor());
    }

    static boolean a(Game game, Object obj) {
        if (!(obj instanceof Game)) {
            return false;
        }
        if (game == obj) {
            return true;
        }
        Game game2 = (Game) obj;
        if (kl.equal(game2.getApplicationId(), game.getApplicationId()) && kl.equal(game2.getDisplayName(), game.getDisplayName()) && kl.equal(game2.getPrimaryCategory(), game.getPrimaryCategory()) && kl.equal(game2.getSecondaryCategory(), game.getSecondaryCategory()) && kl.equal(game2.getDescription(), game.getDescription()) && kl.equal(game2.getDeveloperName(), game.getDeveloperName()) && kl.equal(game2.getIconImageUri(), game.getIconImageUri()) && kl.equal(game2.getHiResImageUri(), game.getHiResImageUri()) && kl.equal(game2.getFeaturedImageUri(), game.getFeaturedImageUri()) && kl.equal(Boolean.valueOf(game2.mn()), Boolean.valueOf(game.mn())) && kl.equal(Boolean.valueOf(game2.mp()), Boolean.valueOf(game.mp())) && kl.equal(game2.mq(), game.mq()) && kl.equal(Integer.valueOf(game2.mr()), Integer.valueOf(game.mr())) && kl.equal(Integer.valueOf(game2.getAchievementTotalCount()), Integer.valueOf(game.getAchievementTotalCount())) && kl.equal(Integer.valueOf(game2.getLeaderboardCount()), Integer.valueOf(game.getLeaderboardCount())) && kl.equal(Boolean.valueOf(game2.isRealTimeMultiplayerEnabled()), Boolean.valueOf(game.isRealTimeMultiplayerEnabled()))) {
            Boolean valueOf = Boolean.valueOf(game2.isTurnBasedMultiplayerEnabled());
            boolean z = game.isTurnBasedMultiplayerEnabled() && kl.equal(Boolean.valueOf(game2.isMuted()), Boolean.valueOf(game.isMuted())) && kl.equal(Boolean.valueOf(game2.mo()), Boolean.valueOf(game.mo()));
            if (kl.equal(valueOf, Boolean.valueOf(z)) && kl.equal(Boolean.valueOf(game2.areSnapshotsEnabled()), Boolean.valueOf(game.areSnapshotsEnabled())) && kl.equal(game2.getThemeColor(), game.getThemeColor())) {
                return true;
            }
        }
        return false;
    }

    static String b(Game game) {
        return kl.j(game).a("ApplicationId", game.getApplicationId()).a("DisplayName", game.getDisplayName()).a("PrimaryCategory", game.getPrimaryCategory()).a("SecondaryCategory", game.getSecondaryCategory()).a("Description", game.getDescription()).a("DeveloperName", game.getDeveloperName()).a("IconImageUri", game.getIconImageUri()).a("IconImageUrl", game.getIconImageUrl()).a("HiResImageUri", game.getHiResImageUri()).a("HiResImageUrl", game.getHiResImageUrl()).a("FeaturedImageUri", game.getFeaturedImageUri()).a("FeaturedImageUrl", game.getFeaturedImageUrl()).a("PlayEnabledGame", Boolean.valueOf(game.mn())).a("InstanceInstalled", Boolean.valueOf(game.mp())).a("InstancePackageName", game.mq()).a("AchievementTotalCount", Integer.valueOf(game.getAchievementTotalCount())).a("LeaderboardCount", Integer.valueOf(game.getLeaderboardCount())).a("RealTimeMultiplayerEnabled", Boolean.valueOf(game.isRealTimeMultiplayerEnabled())).a("TurnBasedMultiplayerEnabled", Boolean.valueOf(game.isTurnBasedMultiplayerEnabled())).a("AreSnapshotsEnabled", Boolean.valueOf(game.areSnapshotsEnabled())).a("ThemeColor", game.getThemeColor()).toString();
    }

    public boolean areSnapshotsEnabled() {
        return this.aja;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Game freeze() {
        return this;
    }

    public int getAchievementTotalCount() {
        return this.aiR;
    }

    public String getApplicationId() {
        return this.NA;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getDeveloperName() {
        return this.aiJ;
    }

    public String getDisplayName() {
        return this.WN;
    }

    public Uri getFeaturedImageUri() {
        return this.aiM;
    }

    public String getFeaturedImageUrl() {
        return this.aiX;
    }

    public Uri getHiResImageUri() {
        return this.aiL;
    }

    public String getHiResImageUrl() {
        return this.aiW;
    }

    public Uri getIconImageUri() {
        return this.aiK;
    }

    public String getIconImageUrl() {
        return this.aiV;
    }

    public int getLeaderboardCount() {
        return this.aiS;
    }

    public String getPrimaryCategory() {
        return this.aiH;
    }

    public String getSecondaryCategory() {
        return this.aiI;
    }

    public String getThemeColor() {
        return this.ajb;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public boolean isMuted() {
        return this.aiY;
    }

    public boolean isRealTimeMultiplayerEnabled() {
        return this.aiT;
    }

    public boolean isTurnBasedMultiplayerEnabled() {
        return this.aiU;
    }

    public boolean mn() {
        return this.aiN;
    }

    public boolean mo() {
        return this.aiZ;
    }

    public boolean mp() {
        return this.aiO;
    }

    public String mq() {
        return this.aiP;
    }

    public int mr() {
        return this.aiQ;
    }

    public String toString() {
        return b((Game) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        String str = null;
        if (iN()) {
            dest.writeString(this.NA);
            dest.writeString(this.WN);
            dest.writeString(this.aiH);
            dest.writeString(this.aiI);
            dest.writeString(this.mDescription);
            dest.writeString(this.aiJ);
            dest.writeString(this.aiK == null ? null : this.aiK.toString());
            dest.writeString(this.aiL == null ? null : this.aiL.toString());
            if (this.aiM != null) {
                str = this.aiM.toString();
            }
            dest.writeString(str);
            dest.writeInt(this.aiN ? 1 : 0);
            if (!this.aiO) {
                i = 0;
            }
            dest.writeInt(i);
            dest.writeString(this.aiP);
            dest.writeInt(this.aiQ);
            dest.writeInt(this.aiR);
            dest.writeInt(this.aiS);
            return;
        }
        GameEntityCreator.a(this, dest, flags);
    }
}
