package com.google.android.gms.games.internal.game;

import android.os.Parcel;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.internal.jy;
import com.google.android.gms.internal.kl;
import java.util.ArrayList;

public final class ExtendedGameEntity extends GamesDowngradeableSafeParcel implements ExtendedGame {
    public static final ExtendedGameEntityCreator CREATOR;
    private final long aoA;
    private final String aoB;
    private final ArrayList<GameBadgeEntity> aoC;
    private final SnapshotMetadataEntity aoD;
    private final GameEntity aot;
    private final int aou;
    private final boolean aov;
    private final int aow;
    private final long aox;
    private final long aoy;
    private final String aoz;
    private final int mVersionCode;

    static final class ExtendedGameEntityCreatorCompat extends ExtendedGameEntityCreator {
        ExtendedGameEntityCreatorCompat() {
        }

        public /* synthetic */ Object createFromParcel(Parcel x0) {
            return eM(x0);
        }

        public ExtendedGameEntity eM(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.c(jy.iM()) || jy.bf(ExtendedGameEntity.class.getCanonicalName())) {
                return super.eM(parcel);
            }
            GameEntity gameEntity = (GameEntity) GameEntity.CREATOR.createFromParcel(parcel);
            int readInt = parcel.readInt();
            boolean z = parcel.readInt() == 1;
            int readInt2 = parcel.readInt();
            long readLong = parcel.readLong();
            long readLong2 = parcel.readLong();
            String readString = parcel.readString();
            long readLong3 = parcel.readLong();
            String readString2 = parcel.readString();
            int readInt3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt3);
            for (int i = 0; i < readInt3; i++) {
                arrayList.add(GameBadgeEntity.CREATOR.eN(parcel));
            }
            return new ExtendedGameEntity(2, gameEntity, readInt, z, readInt2, readLong, readLong2, readString, readLong3, readString2, arrayList, null);
        }
    }

    static {
        CREATOR = new ExtendedGameEntityCreatorCompat();
    }

    ExtendedGameEntity(int versionCode, GameEntity game, int availability, boolean owned, int achievementUnlockedCount, long lastPlayedServerTimestamp, long priceMicros, String formattedPrice, long fullPriceMicros, String formattedFullPrice, ArrayList<GameBadgeEntity> badges, SnapshotMetadataEntity snapshot) {
        this.mVersionCode = versionCode;
        this.aot = game;
        this.aou = availability;
        this.aov = owned;
        this.aow = achievementUnlockedCount;
        this.aox = lastPlayedServerTimestamp;
        this.aoy = priceMicros;
        this.aoz = formattedPrice;
        this.aoA = fullPriceMicros;
        this.aoB = formattedFullPrice;
        this.aoC = badges;
        this.aoD = snapshot;
    }

    public ExtendedGameEntity(ExtendedGame extendedGame) {
        SnapshotMetadataEntity snapshotMetadataEntity = null;
        this.mVersionCode = 2;
        Game game = extendedGame.getGame();
        this.aot = game == null ? null : new GameEntity(game);
        this.aou = extendedGame.nO();
        this.aov = extendedGame.nP();
        this.aow = extendedGame.nQ();
        this.aox = extendedGame.nR();
        this.aoy = extendedGame.nS();
        this.aoz = extendedGame.nT();
        this.aoA = extendedGame.nU();
        this.aoB = extendedGame.nV();
        SnapshotMetadata nW = extendedGame.nW();
        if (nW != null) {
            snapshotMetadataEntity = new SnapshotMetadataEntity(nW);
        }
        this.aoD = snapshotMetadataEntity;
        ArrayList nN = extendedGame.nN();
        int size = nN.size();
        this.aoC = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            this.aoC.add((GameBadgeEntity) ((GameBadge) nN.get(i)).freeze());
        }
    }

    static int a(ExtendedGame extendedGame) {
        return kl.hashCode(extendedGame.getGame(), Integer.valueOf(extendedGame.nO()), Boolean.valueOf(extendedGame.nP()), Integer.valueOf(extendedGame.nQ()), Long.valueOf(extendedGame.nR()), Long.valueOf(extendedGame.nS()), extendedGame.nT(), Long.valueOf(extendedGame.nU()), extendedGame.nV());
    }

    static boolean a(ExtendedGame extendedGame, Object obj) {
        if (!(obj instanceof ExtendedGame)) {
            return false;
        }
        if (extendedGame == obj) {
            return true;
        }
        ExtendedGame extendedGame2 = (ExtendedGame) obj;
        return kl.equal(extendedGame2.getGame(), extendedGame.getGame()) && kl.equal(Integer.valueOf(extendedGame2.nO()), Integer.valueOf(extendedGame.nO())) && kl.equal(Boolean.valueOf(extendedGame2.nP()), Boolean.valueOf(extendedGame.nP())) && kl.equal(Integer.valueOf(extendedGame2.nQ()), Integer.valueOf(extendedGame.nQ())) && kl.equal(Long.valueOf(extendedGame2.nR()), Long.valueOf(extendedGame.nR())) && kl.equal(Long.valueOf(extendedGame2.nS()), Long.valueOf(extendedGame.nS())) && kl.equal(extendedGame2.nT(), extendedGame.nT()) && kl.equal(Long.valueOf(extendedGame2.nU()), Long.valueOf(extendedGame.nU())) && kl.equal(extendedGame2.nV(), extendedGame.nV());
    }

    static String b(ExtendedGame extendedGame) {
        return kl.j(extendedGame).a("Game", extendedGame.getGame()).a("Availability", Integer.valueOf(extendedGame.nO())).a("Owned", Boolean.valueOf(extendedGame.nP())).a("AchievementUnlockedCount", Integer.valueOf(extendedGame.nQ())).a("LastPlayedServerTimestamp", Long.valueOf(extendedGame.nR())).a("PriceMicros", Long.valueOf(extendedGame.nS())).a("FormattedPrice", extendedGame.nT()).a("FullPriceMicros", Long.valueOf(extendedGame.nU())).a("FormattedFullPrice", extendedGame.nV()).a("Snapshot", extendedGame.nW()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public /* synthetic */ Object freeze() {
        return nY();
    }

    public /* synthetic */ Game getGame() {
        return nX();
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public ArrayList<GameBadge> nN() {
        return new ArrayList(this.aoC);
    }

    public int nO() {
        return this.aou;
    }

    public boolean nP() {
        return this.aov;
    }

    public int nQ() {
        return this.aow;
    }

    public long nR() {
        return this.aox;
    }

    public long nS() {
        return this.aoy;
    }

    public String nT() {
        return this.aoz;
    }

    public long nU() {
        return this.aoA;
    }

    public String nV() {
        return this.aoB;
    }

    public SnapshotMetadata nW() {
        return this.aoD;
    }

    public GameEntity nX() {
        return this.aot;
    }

    public ExtendedGame nY() {
        return this;
    }

    public String toString() {
        return b((ExtendedGame) this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 0;
        if (iN()) {
            this.aot.writeToParcel(dest, flags);
            dest.writeInt(this.aou);
            dest.writeInt(this.aov ? 1 : 0);
            dest.writeInt(this.aow);
            dest.writeLong(this.aox);
            dest.writeLong(this.aoy);
            dest.writeString(this.aoz);
            dest.writeLong(this.aoA);
            dest.writeString(this.aoB);
            int size = this.aoC.size();
            dest.writeInt(size);
            while (i < size) {
                ((GameBadgeEntity) this.aoC.get(i)).writeToParcel(dest, flags);
                i++;
            }
            return;
        }
        ExtendedGameEntityCreator.a(this, dest, flags);
    }
}
