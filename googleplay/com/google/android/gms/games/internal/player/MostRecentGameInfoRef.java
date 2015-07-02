package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;

public final class MostRecentGameInfoRef extends c implements MostRecentGameInfo {
    private final PlayerColumnNames ajA;

    public MostRecentGameInfoRef(DataHolder holder, int dataRow, PlayerColumnNames columnNames) {
        super(holder, dataRow);
        this.ajA = columnNames;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return MostRecentGameInfoEntity.a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return or();
    }

    public int hashCode() {
        return MostRecentGameInfoEntity.a(this);
    }

    public String ol() {
        return getString(this.ajA.apf);
    }

    public String om() {
        return getString(this.ajA.apg);
    }

    public long on() {
        return getLong(this.ajA.aph);
    }

    public Uri oo() {
        return ba(this.ajA.api);
    }

    public Uri op() {
        return ba(this.ajA.apj);
    }

    public Uri oq() {
        return ba(this.ajA.apk);
    }

    public MostRecentGameInfo or() {
        return new MostRecentGameInfoEntity(this);
    }

    public String toString() {
        return MostRecentGameInfoEntity.b(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((MostRecentGameInfoEntity) or()).writeToParcel(dest, flags);
    }
}
