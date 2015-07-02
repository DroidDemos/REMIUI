package org.keyczar;

import com.google.gson.annotations.Expose;
import java.nio.ByteBuffer;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.keyczar.enums.CipherMode;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.DecryptingStream;
import org.keyczar.interfaces.EncryptingStream;
import org.keyczar.interfaces.SigningStream;
import org.keyczar.interfaces.Stream;
import org.keyczar.util.Base64Coder;
import org.keyczar.util.Util;

public class AesKey extends KeyczarKey {
    private static final CipherMode DEFAULT_MODE;
    private SecretKey aesKey;
    @Expose
    private final String aesKeyString;
    private final byte[] hash;
    @Expose
    private final HmacKey hmacKey;
    @Expose
    private final CipherMode mode;

    private class AesStream implements DecryptingStream, EncryptingStream {
        private final Cipher decryptingCipher;
        private final Cipher encryptingCipher;
        boolean ivRead;
        private final SigningStream signStream;

        public AesStream() throws KeyczarException {
            this.ivRead = false;
            IvParameterSpec zeroIv = new IvParameterSpec(new byte[16]);
            try {
                this.encryptingCipher = Cipher.getInstance(AesKey.this.mode.getMode());
                this.encryptingCipher.init(1, AesKey.this.aesKey, zeroIv);
                this.decryptingCipher = Cipher.getInstance(AesKey.this.mode.getMode());
                this.decryptingCipher.init(2, AesKey.this.aesKey, zeroIv);
                this.signStream = (SigningStream) AesKey.this.hmacKey.getStream();
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public SigningStream getSigningStream() {
            return this.signStream;
        }

        public int initEncrypt(ByteBuffer output) throws KeyczarException {
            byte[] ivPreImage = new byte[16];
            Util.rand(ivPreImage);
            try {
                return this.encryptingCipher.update(ByteBuffer.wrap(ivPreImage), output);
            } catch (ShortBufferException e) {
                throw new org.keyczar.exceptions.ShortBufferException(e);
            }
        }

        public int updateEncrypt(ByteBuffer input, ByteBuffer output) throws KeyczarException {
            try {
                return this.encryptingCipher.update(input, output);
            } catch (ShortBufferException e) {
                throw new org.keyczar.exceptions.ShortBufferException(e);
            }
        }

        public int doFinalEncrypt(ByteBuffer input, ByteBuffer output) throws KeyczarException {
            try {
                return this.encryptingCipher.doFinal(input, output);
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }

        public int maxOutputSize(int inputLen) {
            return AesKey.this.mode.getOutputSize(16, inputLen);
        }
    }

    static {
        DEFAULT_MODE = CipherMode.CBC;
    }

    public AesKey(byte[] aesKeyBytes, HmacKey hmacKey) throws KeyczarException {
        super(aesKeyBytes.length * 8);
        this.hash = new byte[4];
        this.aesKeyString = Base64Coder.encodeWebSafe(aesKeyBytes);
        this.mode = DEFAULT_MODE;
        this.hmacKey = hmacKey;
        initJceKey(aesKeyBytes);
    }

    private AesKey() {
        super(0);
        this.hash = new byte[4];
        this.aesKeyString = null;
        this.hmacKey = null;
        this.mode = null;
    }

    static AesKey generate(int keySize) throws KeyczarException {
        return new AesKey(Util.rand(keySize / 8), HmacKey.generate());
    }

    protected byte[] hash() {
        return this.hash;
    }

    static AesKey read(String input) throws KeyczarException {
        AesKey key = (AesKey) Util.gson().fromJson(input, AesKey.class);
        key.hmacKey.initFromJson();
        key.initJceKey(Base64Coder.decodeWebSafe(key.aesKeyString));
        return key;
    }

    private void initJceKey(byte[] aesBytes) throws KeyczarException {
        this.aesKey = new SecretKeySpec(aesBytes, "AES");
        System.arraycopy(Util.hash(Util.fromInt(16), aesBytes, this.hmacKey.getEncoded()), 0, this.hash, 0, this.hash.length);
    }

    protected Stream getStream() throws KeyczarException {
        return new AesStream();
    }
}
