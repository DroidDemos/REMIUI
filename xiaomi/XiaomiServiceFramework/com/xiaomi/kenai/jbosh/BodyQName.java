package com.xiaomi.kenai.jbosh;

public final class BodyQName {
    static final String BOSH_NS_URI = "xm";
    private final QName qname;

    private BodyQName(QName wrapped) {
        this.qname = wrapped;
    }

    public static BodyQName create(String uri, String local) {
        return createWithPrefix(uri, local, null);
    }

    public static BodyQName createWithPrefix(String uri, String local, String prefix) {
        if (uri == null || uri.length() == 0) {
            throw new IllegalArgumentException("URI is required and may not be null/empty");
        } else if (local == null || local.length() == 0) {
            throw new IllegalArgumentException("Local arg is required and may not be null/empty");
        } else if (prefix == null || prefix.length() == 0) {
            return new BodyQName(new QName(uri, local));
        } else {
            return new BodyQName(new QName(uri, local, prefix));
        }
    }

    public String getNamespaceURI() {
        return this.qname.getNamespaceURI();
    }

    public String getLocalPart() {
        return this.qname.getLocalPart();
    }

    public String getPrefix() {
        return this.qname.getPrefix();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BodyQName)) {
            return false;
        }
        return this.qname.equals(((BodyQName) obj).qname);
    }

    public int hashCode() {
        return this.qname.hashCode();
    }

    public static BodyQName createBOSH(String local) {
        return createWithPrefix(BOSH_NS_URI, local, null);
    }

    boolean equalsQName(QName otherName) {
        return this.qname.equals(otherName);
    }
}
