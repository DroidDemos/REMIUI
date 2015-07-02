package com.google.android.gms.games.internal.game;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import java.util.ArrayList;

public interface ExtendedGame extends Parcelable, Freezable<ExtendedGame> {
    Game getGame();

    ArrayList<GameBadge> nN();

    int nO();

    boolean nP();

    int nQ();

    long nR();

    long nS();

    String nT();

    long nU();

    String nV();

    SnapshotMetadata nW();
}
