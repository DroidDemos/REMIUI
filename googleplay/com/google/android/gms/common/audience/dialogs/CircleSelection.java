package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import com.google.android.gms.common.people.data.AudienceMember;
import java.util.ArrayList;
import java.util.List;

public final class CircleSelection {

    public interface UpdateBuilder {
        Intent build();

        UpdateBuilder setAccountName(String str);

        UpdateBuilder setClientApplicationId(String str);

        UpdateBuilder setInitialCircles(List<AudienceMember> list);

        UpdateBuilder setUpdatePersonId(String str);
    }

    public static ArrayList<AudienceMember> getSelectedCirclesFromResult(Intent intent) {
        return AudienceSelectionIntentBuilder.getSelectedAudienceMembers(intent);
    }

    public static UpdateBuilder getUpdateCirclesBuilder() {
        return new AudienceSelectionIntentBuilder("com.google.android.gms.common.acl.UPDATE_CIRCLES");
    }
}
