package com.google.android.gms.games.achievement;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.c;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.internal.ju;

public final class AchievementRef extends c implements Achievement {
    public int describeContents() {
        return 0;
    }

    public Achievement freeze() {
        return new AchievementEntity(this);
    }

    public String getAchievementId() {
        return getString("external_achievement_id");
    }

    public int getCurrentSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        ju.K(z);
        return getInteger("current_steps");
    }

    public String getDescription() {
        return getString("description");
    }

    public String getFormattedCurrentSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        ju.K(z);
        return getString("formatted_current_steps");
    }

    public String getFormattedTotalSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        ju.K(z);
        return getString("formatted_total_steps");
    }

    public long getLastUpdatedTimestamp() {
        return getLong("last_updated_timestamp");
    }

    public String getName() {
        return getString("name");
    }

    public Player getPlayer() {
        return new PlayerRef(this.mDataHolder, this.TX);
    }

    public Uri getRevealedImageUri() {
        return ba("revealed_icon_image_uri");
    }

    public String getRevealedImageUrl() {
        return getString("revealed_icon_image_url");
    }

    public int getState() {
        return getInteger("state");
    }

    public int getTotalSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        ju.K(z);
        return getInteger("total_steps");
    }

    public int getType() {
        return getInteger("type");
    }

    public Uri getUnlockedImageUri() {
        return ba("unlocked_icon_image_uri");
    }

    public String getUnlockedImageUrl() {
        return getString("unlocked_icon_image_url");
    }

    public long getXpValue() {
        return (!aZ("instance_xp_value") || bb("instance_xp_value")) ? getLong("definition_xp_value") : getLong("instance_xp_value");
    }

    public String toString() {
        return AchievementEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((AchievementEntity) freeze()).writeToParcel(dest, flags);
    }
}
