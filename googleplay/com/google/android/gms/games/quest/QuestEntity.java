package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;
import java.util.List;

public final class QuestEntity implements SafeParcelable, Quest {
    public static final QuestEntityCreator CREATOR;
    private final int EB;
    private final long ajM;
    private final GameEntity aot;
    private final String aqJ;
    private final long aqK;
    private final Uri aqL;
    private final String aqM;
    private final long aqN;
    private final Uri aqO;
    private final String aqP;
    private final long aqQ;
    private final long aqR;
    private final ArrayList<MilestoneEntity> aqS;
    private final String mDescription;
    private final String mName;
    private final int mState;
    private final int mVersionCode;

    static {
        CREATOR = new QuestEntityCreator();
    }

    QuestEntity(int versionCode, GameEntity game, String questId, long acceptedTimestamp, Uri bannerImageUri, String bannerImageUrl, String description, long endTimestamp, long lastUpdatedTimestamp, Uri iconImageUri, String iconImageUrl, String name, long notifyTimestamp, long startTimestamp, int state, int type, ArrayList<MilestoneEntity> milestones) {
        this.mVersionCode = versionCode;
        this.aot = game;
        this.aqJ = questId;
        this.aqK = acceptedTimestamp;
        this.aqL = bannerImageUri;
        this.aqM = bannerImageUrl;
        this.mDescription = description;
        this.aqN = endTimestamp;
        this.ajM = lastUpdatedTimestamp;
        this.aqO = iconImageUri;
        this.aqP = iconImageUrl;
        this.mName = name;
        this.aqQ = notifyTimestamp;
        this.aqR = startTimestamp;
        this.mState = state;
        this.EB = type;
        this.aqS = milestones;
    }

    public QuestEntity(Quest quest) {
        this.mVersionCode = 2;
        this.aot = new GameEntity(quest.getGame());
        this.aqJ = quest.getQuestId();
        this.aqK = quest.getAcceptedTimestamp();
        this.mDescription = quest.getDescription();
        this.aqL = quest.getBannerImageUri();
        this.aqM = quest.getBannerImageUrl();
        this.aqN = quest.getEndTimestamp();
        this.aqO = quest.getIconImageUri();
        this.aqP = quest.getIconImageUrl();
        this.ajM = quest.getLastUpdatedTimestamp();
        this.mName = quest.getName();
        this.aqQ = quest.oG();
        this.aqR = quest.getStartTimestamp();
        this.mState = quest.getState();
        this.EB = quest.getType();
        List oF = quest.oF();
        int size = oF.size();
        this.aqS = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.aqS.add((MilestoneEntity) ((Milestone) oF.get(i)).freeze());
        }
    }

    static int a(Quest quest) {
        return kl.hashCode(quest.getGame(), quest.getQuestId(), Long.valueOf(quest.getAcceptedTimestamp()), quest.getBannerImageUri(), quest.getDescription(), Long.valueOf(quest.getEndTimestamp()), quest.getIconImageUri(), Long.valueOf(quest.getLastUpdatedTimestamp()), quest.oF(), quest.getName(), Long.valueOf(quest.oG()), Long.valueOf(quest.getStartTimestamp()), Integer.valueOf(quest.getState()));
    }

    static boolean a(Quest quest, Object obj) {
        if (!(obj instanceof Quest)) {
            return false;
        }
        if (quest == obj) {
            return true;
        }
        Quest quest2 = (Quest) obj;
        return kl.equal(quest2.getGame(), quest.getGame()) && kl.equal(quest2.getQuestId(), quest.getQuestId()) && kl.equal(Long.valueOf(quest2.getAcceptedTimestamp()), Long.valueOf(quest.getAcceptedTimestamp())) && kl.equal(quest2.getBannerImageUri(), quest.getBannerImageUri()) && kl.equal(quest2.getDescription(), quest.getDescription()) && kl.equal(Long.valueOf(quest2.getEndTimestamp()), Long.valueOf(quest.getEndTimestamp())) && kl.equal(quest2.getIconImageUri(), quest.getIconImageUri()) && kl.equal(Long.valueOf(quest2.getLastUpdatedTimestamp()), Long.valueOf(quest.getLastUpdatedTimestamp())) && kl.equal(quest2.oF(), quest.oF()) && kl.equal(quest2.getName(), quest.getName()) && kl.equal(Long.valueOf(quest2.oG()), Long.valueOf(quest.oG())) && kl.equal(Long.valueOf(quest2.getStartTimestamp()), Long.valueOf(quest.getStartTimestamp())) && kl.equal(Integer.valueOf(quest2.getState()), Integer.valueOf(quest.getState()));
    }

    static String b(Quest quest) {
        return kl.j(quest).a("Game", quest.getGame()).a("QuestId", quest.getQuestId()).a("AcceptedTimestamp", Long.valueOf(quest.getAcceptedTimestamp())).a("BannerImageUri", quest.getBannerImageUri()).a("BannerImageUrl", quest.getBannerImageUrl()).a("Description", quest.getDescription()).a("EndTimestamp", Long.valueOf(quest.getEndTimestamp())).a("IconImageUri", quest.getIconImageUri()).a("IconImageUrl", quest.getIconImageUrl()).a("LastUpdatedTimestamp", Long.valueOf(quest.getLastUpdatedTimestamp())).a("Milestones", quest.oF()).a("Name", quest.getName()).a("NotifyTimestamp", Long.valueOf(quest.oG())).a("StartTimestamp", Long.valueOf(quest.getStartTimestamp())).a("State", Integer.valueOf(quest.getState())).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Quest freeze() {
        return this;
    }

    public long getAcceptedTimestamp() {
        return this.aqK;
    }

    public Uri getBannerImageUri() {
        return this.aqL;
    }

    public String getBannerImageUrl() {
        return this.aqM;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public long getEndTimestamp() {
        return this.aqN;
    }

    public Game getGame() {
        return this.aot;
    }

    public Uri getIconImageUri() {
        return this.aqO;
    }

    public String getIconImageUrl() {
        return this.aqP;
    }

    public long getLastUpdatedTimestamp() {
        return this.ajM;
    }

    public String getName() {
        return this.mName;
    }

    public String getQuestId() {
        return this.aqJ;
    }

    public long getStartTimestamp() {
        return this.aqR;
    }

    public int getState() {
        return this.mState;
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

    public List<Milestone> oF() {
        return new ArrayList(this.aqS);
    }

    public long oG() {
        return this.aqQ;
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        QuestEntityCreator.a(this, out, flags);
    }
}
