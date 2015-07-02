package com.google.android.gms.games;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;

public final class GameRef extends c implements Game {
    public GameRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public boolean areSnapshotsEnabled() {
        return getInteger("snapshots_enabled") > 0;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return GameEntity.a(this, obj);
    }

    public Game freeze() {
        return new GameEntity(this);
    }

    public int getAchievementTotalCount() {
        return getInteger("achievement_total_count");
    }

    public String getApplicationId() {
        return getString("external_game_id");
    }

    public String getDescription() {
        return getString("game_description");
    }

    public String getDeveloperName() {
        return getString("developer_name");
    }

    public String getDisplayName() {
        return getString("display_name");
    }

    public Uri getFeaturedImageUri() {
        return ba("featured_image_uri");
    }

    public String getFeaturedImageUrl() {
        return getString("featured_image_url");
    }

    public Uri getHiResImageUri() {
        return ba("game_hi_res_image_uri");
    }

    public String getHiResImageUrl() {
        return getString("game_hi_res_image_url");
    }

    public Uri getIconImageUri() {
        return ba("game_icon_image_uri");
    }

    public String getIconImageUrl() {
        return getString("game_icon_image_url");
    }

    public int getLeaderboardCount() {
        return getInteger("leaderboard_count");
    }

    public String getPrimaryCategory() {
        return getString("primary_category");
    }

    public String getSecondaryCategory() {
        return getString("secondary_category");
    }

    public String getThemeColor() {
        return getString("theme_color");
    }

    public int hashCode() {
        return GameEntity.a(this);
    }

    public boolean isMuted() {
        return getBoolean("muted");
    }

    public boolean isRealTimeMultiplayerEnabled() {
        return getInteger("real_time_support") > 0;
    }

    public boolean isTurnBasedMultiplayerEnabled() {
        return getInteger("turn_based_support") > 0;
    }

    public boolean mn() {
        return getBoolean("play_enabled_game");
    }

    public boolean mo() {
        return getBoolean("identity_sharing_confirmed");
    }

    public boolean mp() {
        return getInteger("installed") > 0;
    }

    public String mq() {
        return getString("package_name");
    }

    public int mr() {
        return getInteger("gameplay_acl_status");
    }

    public String toString() {
        return GameEntity.b((Game) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((GameEntity) freeze()).writeToParcel(dest, flags);
    }
}
