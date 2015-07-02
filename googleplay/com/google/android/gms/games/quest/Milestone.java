package com.google.android.gms.games.quest;

import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;

public interface Milestone extends Parcelable, Freezable<Milestone> {
    byte[] getCompletionRewardData();

    long getCurrentProgress();

    String getEventId();

    String getMilestoneId();

    int getState();

    long getTargetProgress();
}
