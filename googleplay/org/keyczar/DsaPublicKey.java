package org.keyczar;

import com.google.gson.annotations.Expose;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.Stream;
import org.keyczar.interfaces.VerifyingStream;
import org.keyczar.util.Base64Coder;
import org.keyczar.util.Util;

public class DsaPublicKey extends KeyczarPublicKey {
    @Expose
    final String g;
    private final byte[] hash;
    private DSAPublicKey jcePublicKey;
    @Expose
    final String p;
    @Expose
    final String q;
    @Expose
    final String y;

    private class DsaVerifyingStream implements VerifyingStream {
        private Signature signature;

        public DsaVerifyingStream() throws KeyczarException {
            try {
                this.signature = Signature.getInstance("SHA1withDSA");
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public void initVerify() throws KeyczarException {
            try {
                this.signature.initVerify(DsaPublicKey.this.jcePublicKey);
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public void updateVerify(ByteBuffer input) throws KeyczarException {
            try {
                this.signature.update(input);
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public boolean verify(ByteBuffer sig) throws KeyczarException {
            try {
                return this.signature.verify(sig.array(), sig.position(), sig.limit() - sig.position());
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }
    }

    static DsaPublicKey read(String input) throws KeyczarException {
        DsaPublicKey key = (DsaPublicKey) Util.gson().fromJson(input, DsaPublicKey.class);
        key.initFromJson();
        return key;
    }

    private DsaPublicKey() {
        super(0);
        this.hash = new byte[4];
        this.jcePublicKey = null;
        this.g = null;
        this.q = null;
        this.p = null;
        this.y = null;
    }

    protected Stream getStream() throws KeyczarException {
        return new DsaVerifyingStream();
    }

    public byte[] hash() {
        return this.hash;
    }

    void initFromJson() throws KeyczarException {
        initializeJceKey(new BigInteger(Base64Coder.decodeWebSafe(this.y)), new BigInteger(Base64Coder.decodeWebSafe(this.p)), new BigInteger(Base64Coder.decodeWebSafe(this.q)), new BigInteger(Base64Coder.decodeWebSafe(this.g)));
        initializeHash();
    }

    private void initializeJceKey(BigInteger yVal, BigInteger pVal, BigInteger qVal, BigInteger gVal) throws KeyczarException {
        try {
            this.jcePublicKey = (DSAPublicKey) KeyFactory.getInstance("DSA").generatePublic(new DSAPublicKeySpec(yVal, pVal, qVal, gVal));
        } catch (Throwable e) {
            throw new KeyczarException(e);
        }
    }

    private void initializeHash() throws KeyczarException {
        DSAParams dsaParams = this.jcePublicKey.getParams();
        System.arraycopy(Util.prefixHash(Util.stripLeadingZeros(dsaParams.getP().toByteArray()), Util.stripLeadingZeros(dsaParams.getQ().toByteArray()), Util.stripLeadingZeros(dsaParams.getG().toByteArray()), Util.stripLeadingZeros(this.jcePublicKey.getY().toByteArray())), 0, this.hash, 0, this.hash.length);
    }
}
