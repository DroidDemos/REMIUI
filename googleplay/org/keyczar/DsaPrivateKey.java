package org.keyczar;

import com.google.gson.annotations.Expose;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.spec.DSAPrivateKeySpec;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.SigningStream;
import org.keyczar.interfaces.Stream;
import org.keyczar.interfaces.VerifyingStream;
import org.keyczar.util.Base64Coder;
import org.keyczar.util.Util;

public class DsaPrivateKey extends KeyczarKey {
    private DSAPrivateKey jcePrivateKey;
    @Expose
    private final DsaPublicKey publicKey;
    @Expose
    private final String x;

    private class DsaSigningStream implements SigningStream, VerifyingStream {
        private Signature signature;
        private VerifyingStream verifyingStream;

        public DsaSigningStream() throws KeyczarException {
            try {
                this.signature = Signature.getInstance("SHA1withDSA");
                this.verifyingStream = (VerifyingStream) DsaPrivateKey.this.publicKey.getStream();
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public int digestSize() {
            return DsaPrivateKey.this.getType().getOutputSize();
        }

        public void initSign() throws KeyczarException {
            try {
                this.signature.initSign(DsaPrivateKey.this.jcePrivateKey);
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public void initVerify() throws KeyczarException {
            this.verifyingStream.initVerify();
        }

        public void sign(ByteBuffer output) throws KeyczarException {
            try {
                output.put(this.signature.sign());
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public void updateSign(ByteBuffer input) throws KeyczarException {
            try {
                this.signature.update(input);
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public void updateVerify(ByteBuffer input) throws KeyczarException {
            this.verifyingStream.updateVerify(input);
        }

        public boolean verify(ByteBuffer sig) throws KeyczarException {
            return this.verifyingStream.verify(sig);
        }
    }

    static DsaPrivateKey read(String input) throws KeyczarException {
        return ((DsaPrivateKey) Util.gson().fromJson(input, DsaPrivateKey.class)).initFromJson();
    }

    private DsaPrivateKey() {
        super(0);
        this.publicKey = null;
        this.x = null;
    }

    protected byte[] hash() {
        return getPublic().hash();
    }

    public KeyczarPublicKey getPublic() {
        return this.publicKey;
    }

    protected Stream getStream() throws KeyczarException {
        return new DsaSigningStream();
    }

    public KeyType getType() {
        return DefaultKeyType.DSA_PRIV;
    }

    private DsaPrivateKey initFromJson() throws KeyczarException {
        this.publicKey.initFromJson();
        try {
            this.jcePrivateKey = (DSAPrivateKey) KeyFactory.getInstance("DSA").generatePrivate(new DSAPrivateKeySpec(new BigInteger(Base64Coder.decodeWebSafe(this.x)), new BigInteger(Base64Coder.decodeWebSafe(this.publicKey.p)), new BigInteger(Base64Coder.decodeWebSafe(this.publicKey.q)), new BigInteger(Base64Coder.decodeWebSafe(this.publicKey.g))));
            return this;
        } catch (Throwable e) {
            throw new KeyczarException(e);
        }
    }
}
