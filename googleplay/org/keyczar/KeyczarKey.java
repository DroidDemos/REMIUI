package org.keyczar;

import com.google.gson.annotations.Expose;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.Stream;
import org.keyczar.util.Util;

public abstract class KeyczarKey {
    @Expose
    final int size;

    protected abstract Stream getStream() throws KeyczarException;

    protected abstract byte[] hash();

    protected KeyczarKey(int size) {
        this.size = size;
    }

    void copyHeader(ByteBuffer dest) {
        dest.put((byte) 0);
        dest.put(hash());
    }

    public boolean equals(Object o) {
        try {
            return Arrays.equals(((KeyczarKey) o).hash(), hash());
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        return Util.toInt(hash());
    }

    public String toString() {
        return Util.gson().toJson((Object) this);
    }

    static KeyczarKey readKey(KeyType type, String key) throws KeyczarException {
        return type.getBuilder().read(key);
    }
}
