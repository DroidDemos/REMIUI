package com.xiaomi.kenai.jbosh;

import java.util.HashMap;
import java.util.Map;

final class BodyParserResults {
    private final Map<BodyQName, String> attrs;

    BodyParserResults() {
        this.attrs = new HashMap();
    }

    void addBodyAttributeValue(BodyQName name, String value) {
        this.attrs.put(name, value);
    }

    Map<BodyQName, String> getAttributes() {
        return this.attrs;
    }
}
