package org.apache.thrift.protocol;

public final class TMessage {
    public final String name;
    public final int seqid;
    public final byte type;

    public TMessage() {
        this("", (byte) 0, 0);
    }

    public TMessage(String n, byte t, int s) {
        this.name = n;
        this.type = t;
        this.seqid = s;
    }

    public String toString() {
        return "<TMessage name:'" + this.name + "' type: " + this.type + " seqid:" + this.seqid + ">";
    }

    public boolean equals(Object other) {
        if (other instanceof TMessage) {
            return equals((TMessage) other);
        }
        return false;
    }

    public boolean equals(TMessage other) {
        return this.name.equals(other.name) && this.type == other.type && this.seqid == other.seqid;
    }
}
