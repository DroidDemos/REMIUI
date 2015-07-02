package com.google.android.gms.games.internal.player;

import android.text.TextUtils;

public final class PlayerColumnNames {
    public final String aoM;
    public final String aoN;
    public final String aoO;
    public final String aoP;
    public final String aoQ;
    public final String aoR;
    public final String aoS;
    public final String aoT;
    public final String aoU;
    public final String aoV;
    public final String aoW;
    public final String aoX;
    public final String aoY;
    public final String aoZ;
    public final String apa;
    public final String apb;
    public final String apc;
    public final String apd;
    public final String ape;
    public final String apf;
    public final String apg;
    public final String aph;
    public final String api;
    public final String apj;
    public final String apk;

    public PlayerColumnNames(String prefix) {
        if (TextUtils.isEmpty(prefix)) {
            this.aoM = "external_player_id";
            this.aoN = "profile_name";
            this.aoO = "profile_icon_image_uri";
            this.aoP = "profile_icon_image_url";
            this.aoQ = "profile_hi_res_image_uri";
            this.aoR = "profile_hi_res_image_url";
            this.aoS = "last_updated";
            this.aoT = "is_in_circles";
            this.aoU = "played_with_timestamp";
            this.aoV = "current_xp_total";
            this.aoW = "current_level";
            this.aoX = "current_level_min_xp";
            this.aoY = "current_level_max_xp";
            this.aoZ = "next_level";
            this.apa = "next_level_max_xp";
            this.apb = "last_level_up_timestamp";
            this.apc = "player_title";
            this.apd = "has_all_public_acls";
            this.ape = "is_profile_visible";
            this.apf = "most_recent_external_game_id";
            this.apg = "most_recent_game_name";
            this.aph = "most_recent_activity_timestamp";
            this.api = "most_recent_game_icon_uri";
            this.apj = "most_recent_game_hi_res_uri";
            this.apk = "most_recent_game_featured_uri";
            return;
        }
        this.aoM = prefix + "external_player_id";
        this.aoN = prefix + "profile_name";
        this.aoO = prefix + "profile_icon_image_uri";
        this.aoP = prefix + "profile_icon_image_url";
        this.aoQ = prefix + "profile_hi_res_image_uri";
        this.aoR = prefix + "profile_hi_res_image_url";
        this.aoS = prefix + "last_updated";
        this.aoT = prefix + "is_in_circles";
        this.aoU = prefix + "played_with_timestamp";
        this.aoV = prefix + "current_xp_total";
        this.aoW = prefix + "current_level";
        this.aoX = prefix + "current_level_min_xp";
        this.aoY = prefix + "current_level_max_xp";
        this.aoZ = prefix + "next_level";
        this.apa = prefix + "next_level_max_xp";
        this.apb = prefix + "last_level_up_timestamp";
        this.apc = prefix + "player_title";
        this.apd = prefix + "has_all_public_acls";
        this.ape = prefix + "is_profile_visible";
        this.apf = prefix + "most_recent_external_game_id";
        this.apg = prefix + "most_recent_game_name";
        this.aph = prefix + "most_recent_activity_timestamp";
        this.api = prefix + "most_recent_game_icon_uri";
        this.apj = prefix + "most_recent_game_hi_res_uri";
        this.apk = prefix + "most_recent_game_featured_uri";
    }
}
