package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.c;
import com.google.android.gms.internal.sb.a;
import com.google.android.gms.internal.sb.b;
import com.google.android.gms.people.model.Person;

public final class rq extends c implements Person {
    public static final String[] alQ;
    private final Bundle Ue;
    private final b aCf;
    private final a aCg;
    private final boolean aCh;

    static {
        alQ = new String[]{"_id", "qualified_id", "gaia_id", "name", "sort_key", "sort_key_irank", "avatar", "profile_type", "v_circle_ids", "blocked", "in_viewer_domain", "last_modified", "name_verified", "given_name", "family_name", "affinity1", "affinity2", "affinity3", "affinity4", "affinity5", "people_in_common", "v_emails", "v_phones"};
    }

    public rq(DataHolder dataHolder, int i, Bundle bundle, b bVar, a aVar) {
        super(dataHolder, i);
        this.Ue = bundle;
        this.aCf = bVar;
        this.aCg = aVar;
        this.aCh = this.Ue.getBoolean("emails_with_affinities", false);
    }

    public String[] getBelongingCircleIds() {
        CharSequence string = getString("v_circle_ids");
        return TextUtils.isEmpty(string) ? rp.aDa : rp.aDb.split(string, -1);
    }
}
