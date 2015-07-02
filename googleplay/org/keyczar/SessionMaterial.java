package org.keyczar;

import com.google.gson.annotations.Expose;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.util.Util;

public class SessionMaterial {
    @Expose
    private AesKey key;
    @Expose
    private String nonce;

    public SessionMaterial() {
        this.key = null;
        this.nonce = "";
    }

    public SessionMaterial(AesKey key, String nonce) {
        this.key = null;
        this.nonce = "";
        this.key = key;
        this.nonce = nonce;
    }

    public AesKey getKey() throws KeyczarException {
        if (this.key != null) {
            return this.key;
        }
        throw new KeyczarException("Key has not been initialized");
    }

    public String getNonce() {
        return this.nonce;
    }

    public String toString() {
        return Util.gson().toJson((Object) this);
    }
}
