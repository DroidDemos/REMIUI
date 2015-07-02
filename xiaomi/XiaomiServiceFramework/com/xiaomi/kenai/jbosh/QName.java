package com.xiaomi.kenai.jbosh;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class QName implements Serializable {
    private static final String emptyString;
    private String localPart;
    private String namespaceURI;
    private String prefix;

    static {
        emptyString = "".intern();
    }

    public QName(String localPart) {
        this(emptyString, localPart, emptyString);
    }

    public QName(String namespaceURI, String localPart) {
        this(namespaceURI, localPart, emptyString);
    }

    public QName(String namespaceURI, String localPart, String prefix) {
        this.namespaceURI = namespaceURI == null ? emptyString : namespaceURI.intern();
        if (localPart == null) {
            throw new IllegalArgumentException("invalid QName local part");
        }
        this.localPart = localPart.intern();
        if (prefix == null) {
            throw new IllegalArgumentException("invalid QName prefix");
        }
        this.prefix = prefix.intern();
    }

    public String getNamespaceURI() {
        return this.namespaceURI;
    }

    public String getLocalPart() {
        return this.localPart;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String toString() {
        return this.namespaceURI == emptyString ? this.localPart : '{' + this.namespaceURI + '}' + this.localPart;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QName)) {
            return false;
        }
        if (this.namespaceURI == ((QName) obj).namespaceURI && this.localPart == ((QName) obj).localPart) {
            return true;
        }
        return false;
    }

    public static QName valueOf(String s) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException("invalid QName literal");
        } else if (s.charAt(0) != '{') {
            return new QName(s);
        } else {
            int i = s.indexOf(125);
            if (i == -1) {
                throw new IllegalArgumentException("invalid QName literal");
            } else if (i != s.length() - 1) {
                return new QName(s.substring(1, i), s.substring(i + 1));
            } else {
                throw new IllegalArgumentException("invalid QName literal");
            }
        }
    }

    public final int hashCode() {
        return this.namespaceURI.hashCode() ^ this.localPart.hashCode();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.namespaceURI = this.namespaceURI.intern();
        this.localPart = this.localPart.intern();
        this.prefix = this.prefix.intern();
    }
}
