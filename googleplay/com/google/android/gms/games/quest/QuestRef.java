package com.google.android.gms.games.quest;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.c;
import com.google.android.gms.games.Game;
import java.util.ArrayList;
import java.util.List;

public final class QuestRef extends c implements Quest {
    private final int akf;
    private final Game apr;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return QuestEntity.a(this, obj);
    }

    public Quest freeze() {
        return new QuestEntity(this);
    }

    public long getAcceptedTimestamp() {
        return getLong("accepted_ts");
    }

    public Uri getBannerImageUri() {
        return ba("quest_banner_image_uri");
    }

    public String getBannerImageUrl() {
        return getString("quest_banner_image_url");
    }

    public String getDescription() {
        return getString("quest_description");
    }

    public long getEndTimestamp() {
        return getLong("quest_end_ts");
    }

    public Game getGame() {
        return this.apr;
    }

    public Uri getIconImageUri() {
        return ba("quest_icon_image_uri");
    }

    public String getIconImageUrl() {
        return getString("quest_icon_image_url");
    }

    public long getLastUpdatedTimestamp() {
        return getLong("quest_last_updated_ts");
    }

    public String getName() {
        return getString("quest_name");
    }

    public String getQuestId() {
        return getString("external_quest_id");
    }

    public long getStartTimestamp() {
        return getLong("quest_start_ts");
    }

    public int getState() {
        return getInteger("quest_state");
    }

    public int getType() {
        return getInteger("quest_type");
    }

    public int hashCode() {
        return QuestEntity.a(this);
    }

    public List<Milestone> oF() {
        List arrayList = new ArrayList(this.akf);
        for (int i = 0; i < this.akf; i++) {
            arrayList.add(new MilestoneRef(this.mDataHolder, this.TX + i));
        }
        return arrayList;
    }

    public long oG() {
        return getLong("notification_ts");
    }

    public String toString() {
        return QuestEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((QuestEntity) freeze()).writeToParcel(dest, flags);
    }
}
