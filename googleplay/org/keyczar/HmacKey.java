package org.keyczar;

import com.google.gson.annotations.Expose;
import java.nio.ByteBuffer;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.SigningStream;
import org.keyczar.interfaces.Stream;
import org.keyczar.interfaces.VerifyingStream;
import org.keyczar.util.Base64Coder;
import org.keyczar.util.Util;

public class HmacKey extends KeyczarKey {
    private final byte[] hash;
    private SecretKey hmacKey;
    @Expose
    private final String hmacKeyString;

    private class HmacStream implements SigningStream, VerifyingStream {
        private final Mac hmac;

        public HmacStream() throws KeyczarException {
            try {
                this.hmac = Mac.getInstance("HMACSHA1");
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public int digestSize() {
            return HmacKey.this.getType().getOutputSize();
        }

        public void initSign() throws KeyczarException {
            try {
                this.hmac.init(HmacKey.this.hmacKey);
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public void initVerify() throws KeyczarException {
            initSign();
        }

        public void sign(ByteBuffer output) {
            output.put(this.hmac.doFinal());
        }

        public void updateSign(ByteBuffer input) {
            this.hmac.update(input);
        }

        public void updateVerify(ByteBuffer input) {
            updateSign(input);
        }

        public boolean verify(ByteBuffer signature) {
            byte[] sigBytes = new byte[signature.remaining()];
            signature.get(sigBytes);
            return Util.safeArrayEquals(this.hmac.doFinal(), sigBytes);
        }
    }

    public HmacKey(byte[] keyBytes) throws KeyczarException {
        super(keyBytes.length * 8);
        this.hash = new byte[4];
        this.hmacKeyString = Base64Coder.encodeWebSafe(keyBytes);
        initJceKey(keyBytes);
    }

    private HmacKey() {
        super(0);
        this.hash = new byte[4];
        this.hmacKeyString = null;
    }

    static HmacKey generate() throws KeyczarException {
        return generate(DefaultKeyType.HMAC_SHA1.defaultSize());
    }

    static HmacKey generate(int keySize) throws KeyczarException {
        return new HmacKey(Util.rand(keySize / 8));
    }

    void initFromJson() throws KeyczarException {
        initJceKey(Base64Coder.decodeWebSafe(this.hmacKeyString));
    }

    private void initJceKey(byte[] keyBytes) throws KeyczarException {
        this.hmacKey = new SecretKeySpec(keyBytes, "HMACSHA1");
        System.arraycopy(Util.hash(keyBytes), 0, this.hash, 0, this.hash.length);
    }

    byte[] getEncoded() {
        return this.hmacKey.getEncoded();
    }

    protected Stream getStream() throws KeyczarException {
        return new HmacStream();
    }

    public KeyType getType() {
        return DefaultKeyType.HMAC_SHA1;
    }

    protected byte[] hash() {
        return this.hash;
    }

    static HmacKey read(String input) throws KeyczarException {
        HmacKey key = (HmacKey) Util.gson().fromJson(input, HmacKey.class);
        key.initFromJson();
        return key;
    }
}
