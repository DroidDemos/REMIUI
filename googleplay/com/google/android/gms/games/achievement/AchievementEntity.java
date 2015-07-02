package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kl.a;

public final class AchievementEntity implements SafeParcelable, Achievement {
    public static final AchievementEntityCreator CREATOR;
    private final int EB;
    private final String ajC;
    private final Uri ajD;
    private final String ajE;
    private final Uri ajF;
    private final String ajG;
    private final int ajH;
    private final String ajI;
    private final PlayerEntity ajJ;
    private final int ajK;
    private final String ajL;
    private final long ajM;
    private final long ajN;
    private final String mDescription;
    private final String mName;
    private final int mState;
    private final int mVersionCode;

    static {
        CREATOR = new AchievementEntityCreator();
    }

    AchievementEntity(int versionCode, String achievementId, int type, String name, String description, Uri unlockedImageUri, String unlockedImageUrl, Uri revealedImageUri, String revealedImageUrl, int totalSteps, String formattedTotalSteps, PlayerEntity player, int state, int currentSteps, String formattedCurrentSteps, long lastUpdatedTimestamp, long xpValue) {
        this.mVersionCode = versionCode;
        this.ajC = achievementId;
        this.EB = type;
        this.mName = name;
        this.mDescription = description;
        this.ajD = unlockedImageUri;
        this.ajE = unlockedImageUrl;
        this.ajF = revealedImageUri;
        this.ajG = revealedImageUrl;
        this.ajH = totalSteps;
        this.ajI = formattedTotalSteps;
        this.ajJ = player;
        this.mState = state;
        this.ajK = currentSteps;
        this.ajL = formattedCurrentSteps;
        this.ajM = lastUpdatedTimestamp;
        this.ajN = xpValue;
    }

    public AchievementEntity(Achievement achievement) {
        this.mVersionCode = 1;
        this.ajC = achievement.getAchievementId();
        this.EB = achievement.getType();
        this.mName = achievement.getName();
        this.mDescription = achievement.getDescription();
        this.ajD = achievement.getUnlockedImageUri();
        this.ajE = achievement.getUnlockedImageUrl();
        this.ajF = achievement.getRevealedImageUri();
        this.ajG = achievement.getRevealedImageUrl();
        this.ajJ = (PlayerEntity) achievement.getPlayer().freeze();
        this.mState = achievement.getState();
        this.ajM = achievement.getLastUpdatedTimestamp();
        this.ajN = achievement.getXpValue();
        if (achievement.getType() == 1) {
            this.ajH = achievement.getTotalSteps();
            this.ajI = achievement.getFormattedTotalSteps();
            this.ajK = achievement.getCurrentSteps();
            this.ajL = achievement.getFormattedCurrentSteps();
        } else {
            this.ajH = 0;
            this.ajI = null;
            this.ajK = 0;
            this.ajL = null;
        }
        ju.h(this.ajC);
        ju.h(this.mDescription);
    }

    static int a(Achievement achievement) {
        int currentSteps;
        int totalSteps;
        if (achievement.getType() == 1) {
            currentSteps = achievement.getCurrentSteps();
            totalSteps = achievement.getTotalSteps();
        } else {
            totalSteps = 0;
            currentSteps = 0;
        }
        return kl.hashCode(achievement.getAchievementId(), achievement.getName(), Integer.valueOf(achievement.getType()), achievement.getDescription(), Long.valueOf(achievement.getXpValue()), Integer.valueOf(achievement.getState()), Long.valueOf(achievement.getLastUpdatedTimestamp()), achievement.getPlayer(), Integer.valueOf(currentSteps), Integer.valueOf(totalSteps));
    }

    static boolean a(Achievement achievement, Object obj) {
        if (!(obj instanceof Achievement)) {
            return false;
        }
        if (achievement == obj) {
            return true;
        }
        boolean equal;
        boolean equal2;
        Achievement achievement2 = (Achievement) obj;
        if (achievement.getType() == 1) {
            equal = kl.equal(Integer.valueOf(achievement2.getCurrentSteps()), Integer.valueOf(achievement.getCurrentSteps()));
            equal2 = kl.equal(Integer.valueOf(achievement2.getTotalSteps()), Integer.valueOf(achievement.getTotalSteps()));
        } else {
            equal2 = true;
            equal = true;
        }
        return kl.equal(achievement2.getAchievementId(), achievement.getAchievementId()) && kl.equal(achievement2.getName(), achievement.getName()) && kl.equal(Integer.valueOf(achievement2.getType()), Integer.valueOf(achievement.getType())) && kl.equal(achievement2.getDescription(), achievement.getDescription()) && kl.equal(Long.valueOf(achievement2.getXpValue()), Long.valueOf(achievement.getXpValue())) && kl.equal(Integer.valueOf(achievement2.getState()), Integer.valueOf(achievement.getState())) && kl.equal(Long.valueOf(achievement2.getLastUpdatedTimestamp()), Long.valueOf(achievement.getLastUpdatedTimestamp())) && kl.equal(achievement2.getPlayer(), achievement.getPlayer()) && equal && equal2;
    }

    static String b(Achievement achievement) {
        a a = kl.j(achievement).a("Id", achievement.getAchievementId()).a("Type", Integer.valueOf(achievement.getType())).a("Name", achievement.getName()).a("Description", achievement.getDescription()).a("Player", achievement.getPlayer()).a("State", Integer.valueOf(achievement.getState()));
        if (achievement.getType() == 1) {
            a.a("CurrentSteps", Integer.valueOf(achievement.getCurrentSteps()));
            a.a("TotalSteps", Integer.valueOf(achievement.getTotalSteps()));
        }
        return a.toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Achievement freeze() {
        return this;
    }

    public String getAchievementId() {
        return this.ajC;
    }

    public int getCurrentSteps() {
        return this.ajK;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getFormattedCurrentSteps() {
        return this.ajL;
    }

    public String getFormattedTotalSteps() {
        return this.ajI;
    }

    public long getLastUpdatedTimestamp() {
        return this.ajM;
    }

    public String getName() {
        return this.mName;
    }

    public Player getPlayer() {
        return this.ajJ;
    }

    public Uri getRevealedImageUri() {
        return this.ajF;
    }

    public String getRevealedImageUrl() {
        return this.ajG;
    }

    public int getState() {
        return this.mState;
    }

    public int getTotalSteps() {
        return this.ajH;
    }

    public int getType() {
        return this.EB;
    }

    public Uri getUnlockedImageUri() {
        return this.ajD;
    }

    public String getUnlockedImageUrl() {
        return this.ajE;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public long getXpValue() {
        return this.ajN;
    }

    public int hashCode() {
        return a(this);
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        AchievementEntityCreator.a(this, dest, flags);
    }
}
