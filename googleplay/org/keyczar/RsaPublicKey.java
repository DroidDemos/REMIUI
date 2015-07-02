package org.keyczar;

import com.google.gson.annotations.Expose;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import org.keyczar.enums.RsaPadding;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.exceptions.UnsupportedTypeException;
import org.keyczar.interfaces.EncryptingStream;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.SigningStream;
import org.keyczar.interfaces.Stream;
import org.keyczar.interfaces.VerifyingStream;
import org.keyczar.util.Util;

public class RsaPublicKey extends KeyczarPublicKey {
    private final byte[] hash;
    private RSAPublicKey jcePublicKey;
    @Expose
    final String modulus;
    @Expose
    final RsaPadding padding;
    @Expose
    final String publicExponent;

    private class RsaStream implements EncryptingStream, VerifyingStream {
        private Cipher cipher;
        private Signature signature;

        RsaStream() throws KeyczarException {
            try {
                this.signature = Signature.getInstance("SHA1withRSA");
                this.cipher = Cipher.getInstance(RsaPublicKey.this.getPadding().getCryptAlgorithm());
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public int doFinalEncrypt(ByteBuffer input, ByteBuffer output) throws KeyczarException {
            try {
                int ciphertextSize = this.cipher.getOutputSize(input.limit());
                int outputCapacity = output.limit() - output.position();
                ByteBuffer tmpOutput = ByteBuffer.allocate(ciphertextSize);
                this.cipher.doFinal(input, tmpOutput);
                if (ciphertextSize == outputCapacity) {
                    output.put(tmpOutput.array());
                } else if (ciphertextSize == outputCapacity + 1 && tmpOutput.array()[ciphertextSize - 1] == (byte) 0) {
                    output.put(tmpOutput.array(), 0, outputCapacity);
                } else {
                    throw new KeyczarException("Expected " + outputCapacity + " bytes from encryption " + "operation but got " + ciphertextSize);
                }
                return outputCapacity;
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public SigningStream getSigningStream() {
            return new SigningStream() {
                public int digestSize() {
                    return 0;
                }

                public void initSign() {
                }

                public void sign(ByteBuffer output) {
                }

                public void updateSign(ByteBuffer input) {
                }
            };
        }

        public int initEncrypt(ByteBuffer output) throws KeyczarException {
            try {
                this.cipher.init(1, RsaPublicKey.this.jcePublicKey);
                return 0;
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public void initVerify() throws KeyczarException {
            try {
                this.signature.initVerify(RsaPublicKey.this.jcePublicKey);
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public int maxOutputSize(int inputLen) {
            return RsaPublicKey.this.getType().getOutputSize(RsaPublicKey.this.size);
        }

        public int updateEncrypt(ByteBuffer input, ByteBuffer output) throws KeyczarException {
            try {
                return this.cipher.update(input, output);
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

    static RsaPublicKey read(String input) throws KeyczarException {
        RsaPublicKey key = (RsaPublicKey) Util.gson().fromJson(input, RsaPublicKey.class);
        if (key.getType() == DefaultKeyType.RSA_PUB) {
            return key.initFromJson();
        }
        throw new UnsupportedTypeException(key.getType());
    }

    public byte[] hash() {
        return this.hash;
    }

    protected Stream getStream() throws KeyczarException {
        return new RsaStream();
    }

    public KeyType getType() {
        return DefaultKeyType.RSA_PUB;
    }

    private RsaPublicKey() {
        super(0);
        this.hash = new byte[4];
        this.publicExponent = null;
        this.modulus = null;
        this.padding = null;
    }

    RsaPublicKey initFromJson() throws KeyczarException {
        initializeJceKey(Util.decodeBigInteger(this.modulus), Util.decodeBigInteger(this.publicExponent));
        initializeHash();
        return this;
    }

    private void initializeJceKey(BigInteger publicModulus, BigInteger publicExponent) throws KeyczarException {
        try {
            this.jcePublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(publicModulus, publicExponent));
        } catch (Throwable e) {
            throw new KeyczarException(e);
        }
    }

    private void initializeHash() throws KeyczarException {
        System.arraycopy(getPadding().computeFullHash(this.jcePublicKey), 0, this.hash, 0, this.hash.length);
    }

    public RsaPadding getPadding() {
        if (this.padding == null || this.padding == RsaPadding.OAEP) {
            return RsaPadding.OAEP;
        }
        return RsaPadding.PKCS;
    }
}
