package com.xiaomi.kenai.jbosh;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public final class ComposableBody extends AbstractBody {
    private static final Pattern BOSH_START;
    private final Map<BodyQName, String> attrs;
    private final AtomicReference<String> computed;
    private final String payload;

    public static final class Builder {
        private boolean doMapCopy;
        private Map<BodyQName, String> map;
        private String payloadXML;

        private Builder() {
        }

        private static Builder fromBody(ComposableBody source) {
            Builder result = new Builder();
            result.map = source.getAttributes();
            result.doMapCopy = true;
            result.payloadXML = source.payload;
            return result;
        }

        public Builder setPayloadXML(String xml) {
            if (xml == null) {
                throw new IllegalArgumentException("payload XML argument cannot be null");
            }
            this.payloadXML = xml;
            return this;
        }

        public Builder setAttribute(BodyQName name, String value) {
            if (this.map == null) {
                this.map = new HashMap();
            } else if (this.doMapCopy) {
                this.map = new HashMap(this.map);
                this.doMapCopy = false;
            }
            if (value == null) {
                this.map.remove(name);
            } else {
                this.map.put(name, value);
            }
            return this;
        }

        public Builder setNamespaceDefinition(String prefix, String uri) {
            return setAttribute(BodyQName.createWithPrefix("http://www.w3.org/XML/1998/namespace", prefix, "xmlns"), uri);
        }

        public ComposableBody build() {
            if (this.map == null) {
                this.map = new HashMap();
            }
            if (this.payloadXML == null) {
                this.payloadXML = "";
            }
            return new ComposableBody(this.map, this.payloadXML);
        }
    }

    static {
        BOSH_START = Pattern.compile("<body(?:[\t\n\r ][^>]*?)?(/>|>)", 64);
    }

    private ComposableBody(Map<BodyQName, String> attrMap, String payloadXML) {
        this.computed = new AtomicReference();
        this.attrs = attrMap;
        this.payload = payloadXML;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder rebuild() {
        return Builder.fromBody(this);
    }

    public Map<BodyQName, String> getAttributes() {
        return Collections.unmodifiableMap(this.attrs);
    }

    public String toXML() {
        String comp = (String) this.computed.get();
        if (comp != null) {
            return comp;
        }
        comp = computeXML();
        this.computed.set(comp);
        return comp;
    }

    public String getPayloadXML() {
        return this.payload;
    }

    private String escape(String value) {
        return value.replace("'", "&apos;");
    }

    private String computeXML() {
        BodyQName bodyName = AbstractBody.getBodyQName();
        StringBuilder builder = new StringBuilder();
        builder.append("<");
        builder.append(bodyName.getLocalPart());
        for (Entry<BodyQName, String> entry : this.attrs.entrySet()) {
            builder.append(" ");
            BodyQName name = (BodyQName) entry.getKey();
            String prefix = name.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                builder.append(prefix);
                builder.append(":");
            }
            builder.append(name.getLocalPart());
            builder.append("='");
            builder.append(escape((String) entry.getValue()));
            builder.append("'");
        }
        builder.append(" ");
        builder.append("xmlns");
        builder.append("='");
        builder.append(bodyName.getNamespaceURI());
        builder.append("'>");
        if (this.payload != null) {
            builder.append(this.payload);
        }
        builder.append("</body>");
        return builder.toString();
    }
}
