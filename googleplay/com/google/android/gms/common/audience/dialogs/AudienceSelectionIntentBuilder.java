package com.google.android.gms.common.audience.dialogs;

import android.content.Intent;
import com.google.android.gms.common.audience.dialogs.CircleSelection.UpdateBuilder;
import com.google.android.gms.common.internal.safeparcel.c;
import com.google.android.gms.common.people.data.AudienceMember;
import com.google.android.gms.internal.rp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AudienceSelectionIntentBuilder implements UpdateBuilder {
    private final Intent mIntent;

    public AudienceSelectionIntentBuilder(Intent intent) {
        this.mIntent = new Intent(intent);
    }

    public AudienceSelectionIntentBuilder(String action) {
        this(new Intent(action).setPackage("com.google.android.gms"));
    }

    private static <T> ArrayList<T> g(List<T> list) {
        return list instanceof ArrayList ? (ArrayList) list : new ArrayList(list);
    }

    public static ArrayList<AudienceMember> getAddedAudienceDelta(Intent intent) {
        return intent.getParcelableArrayListExtra("com.google.android.gms.common.acl.EXTRA_ADDED_AUDIENCE");
    }

    public static List<AudienceMember> getInitialAudienceMembers(Intent intent) {
        return intent.hasExtra("com.google.android.gms.common.acl.EXTRA_INITIAL_AUDIENCE") ? intent.getParcelableArrayListExtra("com.google.android.gms.common.acl.EXTRA_INITIAL_AUDIENCE") : Collections.emptyList();
    }

    public static ArrayList<AudienceMember> getRemovedAudienceDelta(Intent intent) {
        return intent.getParcelableArrayListExtra("com.google.android.gms.common.acl.EXTRA_REMOVED_AUDIENCE");
    }

    public static ArrayList<AudienceMember> getSelectedAudienceMembers(Intent intent) {
        ArrayList<AudienceMember> arrayList = new ArrayList();
        Collection initialAudienceMembers = getInitialAudienceMembers(intent);
        if (!(initialAudienceMembers == null || initialAudienceMembers.isEmpty())) {
            arrayList.addAll(initialAudienceMembers);
        }
        initialAudienceMembers = getRemovedAudienceDelta(intent);
        if (initialAudienceMembers != null) {
            arrayList.removeAll(initialAudienceMembers);
        }
        initialAudienceMembers = getAddedAudienceDelta(intent);
        if (initialAudienceMembers != null) {
            arrayList.addAll(initialAudienceMembers);
        }
        return arrayList;
    }

    public Intent build() {
        return this.mIntent;
    }

    public AudienceSelectionIntentBuilder setAccountName(String accountName) {
        this.mIntent.putExtra("com.google.android.gms.common.acl.EXTRA_ACCOUNT_NAME", accountName);
        return this;
    }

    public AudienceSelectionIntentBuilder setClientApplicationId(String clientApplicationId) {
        this.mIntent.putExtra("com.google.android.gms.common.acl.EXTRA_CLIENT_APPLICATION_ID", clientApplicationId);
        return this;
    }

    public AudienceSelectionIntentBuilder setInitialAudience(List<AudienceMember> audience) {
        if (audience == null) {
            audience = Collections.EMPTY_LIST;
        }
        this.mIntent.putParcelableArrayListExtra("com.google.android.gms.common.acl.EXTRA_INITIAL_AUDIENCE", g(audience));
        return this;
    }

    public AudienceSelectionIntentBuilder setInitialCircles(List<AudienceMember> circles) {
        setInitialAudience(circles);
        return this;
    }

    @Deprecated
    public AudienceSelectionIntentBuilder setUpdatePersonId(String peopleQualifiedId) {
        rp.K(peopleQualifiedId, "People qualified ID");
        c.a(AudienceMember.forPersonWithPeopleQualifiedId(peopleQualifiedId, null, null), this.mIntent, "com.google.android.gms.common.acl.EXTRA_UPDATE_PERSON");
        return this;
    }
}
