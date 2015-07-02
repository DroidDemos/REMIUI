package com.google.android.gms.games.quest;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;

public final class MilestoneEntity implements SafeParcelable, Milestone {
    public static final MilestoneEntityCreator CREATOR;
    private final String akj;
    private final String alr;
    private final long aqG;
    private final long aqH;
    private final byte[] aqI;
    private final int mState;
    private final int mVersionCode;

    static {
        CREATOR = new MilestoneEntityCreator();
    }

    MilestoneEntity(int versionCode, String milestoneId, long currentProgress, long targetProgress, byte[] completionBlob, int state, String eventId) {
        this.mVersionCode = versionCode;
        this.alr = milestoneId;
        this.aqG = currentProgress;
        this.aqH = targetProgress;
        this.aqI = completionBlob;
        this.mState = state;
        this.akj = eventId;
    }

    public MilestoneEntity(Milestone milestone) {
        this.mVersionCode = 4;
        this.alr = milestone.getMilestoneId();
        this.aqG = milestone.getCurrentProgress();
        this.aqH = milestone.getTargetProgress();
        this.mState = milestone.getState();
        this.akj = milestone.getEventId();
        Object completionRewardData = milestone.getCompletionRewardData();
        if (completionRewardData == null) {
            this.aqI = null;
            return;
        }
        this.aqI = new byte[completionRewardData.length];
        System.arraycopy(completionRewardData, 0, this.aqI, 0, completionRewardData.length);
    }

    static int a(Milestone milestone) {
        return kl.hashCode(milestone.getMilestoneId(), Long.valueOf(milestone.getCurrentProgress()), Long.valueOf(milestone.getTargetProgress()), Integer.valueOf(milestone.getState()), milestone.getEventId());
    }

    static boolean a(Milestone milestone, Object obj) {
        if (!(obj instanceof Milestone)) {
            return false;
        }
        if (milestone == obj) {
            return true;
        }
        Milestone milestone2 = (Milestone) obj;
        return kl.equal(milestone2.getMilestoneId(), milestone.getMilestoneId()) && kl.equal(Long.valueOf(milestone2.getCurrentProgress()), Long.valueOf(milestone.getCurrentProgress())) && kl.equal(Long.valueOf(milestone2.getTargetProgress()), Long.valueOf(milestone.getTargetProgress())) && kl.equal(Integer.valueOf(milestone2.getState()), Integer.valueOf(milestone.getState())) && kl.equal(milestone2.getEventId(), milestone.getEventId());
    }

    static String b(Milestone milestone) {
        return kl.j(milestone).a("MilestoneId", milestone.getMilestoneId()).a("CurrentProgress", Long.valueOf(milestone.getCurrentProgress())).a("TargetProgress", Long.valueOf(milestone.getTargetProgress())).a("State", Integer.valueOf(milestone.getState())).a("CompletionRewardData", milestone.getCompletionRewardData()).a("EventId", milestone.getEventId()).toString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return a(this, obj);
    }

    public Milestone freeze() {
        return this;
    }

    public byte[] getCompletionRewardData() {
        return this.aqI;
    }

    public long getCurrentProgress() {
        return this.aqG;
    }

    public String getEventId() {
        return this.akj;
    }

    public String getMilestoneId() {
        return this.alr;
    }

    public int getState() {
        return this.mState;
    }

    public long getTargetProgress() {
        return this.aqH;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return a(this);
    }

    public String toString() {
        return b(this);
    }

    public void writeToParcel(Parcel out, int flags) {
        MilestoneEntityCreator.a(this, out, flags);
    }
}
