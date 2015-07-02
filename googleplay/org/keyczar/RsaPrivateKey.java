package org.keyczar;

import com.google.gson.annotations.Expose;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import javax.crypto.Cipher;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.DecryptingStream;
import org.keyczar.interfaces.EncryptingStream;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.SigningStream;
import org.keyczar.interfaces.Stream;
import org.keyczar.interfaces.VerifyingStream;
import org.keyczar.util.Util;

public class RsaPrivateKey extends KeyczarKey {
    @Expose
    private final String crtCoefficient;
    private RSAPrivateCrtKey jcePrivateKey;
    @Expose
    private final String primeExponentP;
    @Expose
    private final String primeExponentQ;
    @Expose
    private final String primeP;
    @Expose
    private final String primeQ;
    @Expose
    private final String privateExponent;
    @Expose
    private final RsaPublicKey publicKey;

    private class RsaPrivateStream implements DecryptingStream, EncryptingStream, SigningStream, VerifyingStream {
        private Cipher cipher;
        private EncryptingStream encryptingStream;
        private Signature signature;
        private VerifyingStream verifyingStream;

        public RsaPrivateStream() throws KeyczarException {
            try {
                this.signature = Signature.getInstance("SHA1withRSA");
                this.verifyingStream = (VerifyingStream) RsaPrivateKey.this.publicKey.getStream();
                this.cipher = Cipher.getInstance(RsaPrivateKey.this.publicKey.getPadding().getCryptAlgorithm());
                this.encryptingStream = (EncryptingStream) RsaPrivateKey.this.publicKey.getStream();
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public int digestSize() {
            return RsaPrivateKey.this.getType().getOutputSize();
        }

        public int doFinalEncrypt(ByteBuffer input, ByteBuffer output) throws KeyczarException {
            return this.encryptingStream.doFinalEncrypt(input, output);
        }

        public SigningStream getSigningStream() throws KeyczarException {
            return this.encryptingStream.getSigningStream();
        }

        public int initEncrypt(ByteBuffer output) throws KeyczarException {
            return this.encryptingStream.initEncrypt(output);
        }

        public void initSign() throws KeyczarException {
            try {
                this.signature.initSign(RsaPrivateKey.this.jcePrivateKey);
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public void initVerify() throws KeyczarException {
            this.verifyingStream.initVerify();
        }

        public int maxOutputSize(int inputLen) {
            return RsaPrivateKey.this.getType().getOutputSize(RsaPrivateKey.this.size);
        }

        public void sign(ByteBuffer output) throws KeyczarException {
            try {
                output.put(this.signature.sign());
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public int updateEncrypt(ByteBuffer input, ByteBuffer output) throws KeyczarException {
            return this.encryptingStream.updateEncrypt(input, output);
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

    static RsaPrivateKey read(String input) throws KeyczarException {
        return ((RsaPrivateKey) Util.gson().fromJson(input, RsaPrivateKey.class)).initFromJson();
    }

    private RsaPrivateKey() {
        super(0);
        this.publicKey = null;
        this.privateExponent = null;
        this.primeP = null;
        this.primeQ = null;
        this.primeExponentP = null;
        this.primeExponentQ = null;
        this.crtCoefficient = null;
        this.jcePrivateKey = null;
    }

    protected Stream getStream() throws KeyczarException {
        return new RsaPrivateStream();
    }

    public KeyType getType() {
        return DefaultKeyType.RSA_PRIV;
    }

    protected byte[] hash() {
        return this.publicKey.hash();
    }

    private RsaPrivateKey initFromJson() throws KeyczarException {
        this.publicKey.initFromJson();
        try {
            this.jcePrivateKey = (RSAPrivateCrtKey) KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(Util.decodeBigInteger(this.publicKey.modulus), Util.decodeBigInteger(this.publicKey.publicExponent), Util.decodeBigInteger(this.privateExponent), Util.decodeBigInteger(this.primeP), Util.decodeBigInteger(this.primeQ), Util.decodeBigInteger(this.primeExponentP), Util.decodeBigInteger(this.primeExponentQ), Util.decodeBigInteger(this.crtCoefficient)));
            return this;
        } catch (Throwable e) {
            throw new KeyczarException(e);
        }
    }
}
