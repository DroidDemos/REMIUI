package com.xiaomi.kenai.jbosh;

import com.xiaomi.push.service.PushServiceConstants;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public abstract class AbstractBody {
    public abstract Map<BodyQName, String> getAttributes();

    public abstract String toXML();

    AbstractBody() {
    }

    public final Set<BodyQName> getAttributeNames() {
        return Collections.unmodifiableSet(getAttributes().keySet());
    }

    public final String getAttribute(BodyQName attr) {
        return (String) getAttributes().get(attr);
    }

    static BodyQName getBodyQName() {
        return BodyQName.createBOSH(PushServiceConstants.EXTRA_BODY);
    }
}
