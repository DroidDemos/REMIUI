package com.google.android.gms.common.people.data;

import com.google.android.gms.internal.kn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class AudienceBuilder {
    public static final Audience EMPTY_AUDIENCE;
    private List<AudienceMember> WG;
    private int WH;
    private boolean WJ;

    static {
        EMPTY_AUDIENCE = new AudienceBuilder().build();
    }

    public AudienceBuilder() {
        this.WG = Collections.emptyList();
        this.WH = 0;
        this.WJ = false;
    }

    public Audience build() {
        return new Audience(this.WG, this.WH, this.WJ);
    }

    public AudienceBuilder setAudienceMembers(Collection<AudienceMember> audienceMembers) {
        this.WG = Collections.unmodifiableList(new ArrayList((Collection) kn.b((Object) audienceMembers, (Object) "Audience members must not be null.")));
        return this;
    }
}
