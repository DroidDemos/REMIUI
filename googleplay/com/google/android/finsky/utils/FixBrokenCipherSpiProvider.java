package com.google.android.finsky.utils;

import android.os.Build.VERSION;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;

public class FixBrokenCipherSpiProvider extends Provider {
    private static final byte[] EMPTY_BYTE_ARRAY;

    public static class FixBrokenCipherSpi extends CipherSpi {
        private String mAlgorithm;
        private Cipher mInstance;
        private String mMode;
        private String mPadding;

        public static class AES extends FixBrokenCipherSpi {
            public AES() {
                super("AES");
            }
        }

        public FixBrokenCipherSpi(String algorithm) {
            this.mAlgorithm = algorithm;
        }

        private Cipher getInstance() {
            if (this.mInstance != null) {
                return this.mInstance;
            }
            String fullCipher;
            String cipherAlg = "Cipher." + this.mAlgorithm;
            if (this.mMode == null || this.mPadding == null) {
                fullCipher = this.mAlgorithm;
            } else {
                fullCipher = this.mAlgorithm + "/" + this.mMode + "/" + this.mPadding;
            }
            for (Provider provider : Security.getProviders(cipherAlg)) {
                if (!(provider instanceof FixBrokenCipherSpiProvider)) {
                    try {
                        Cipher instance = Cipher.getInstance(fullCipher, provider);
                        this.mInstance = instance;
                        return instance;
                    } catch (GeneralSecurityException e) {
                    }
                }
            }
            throw new RuntimeException("No other providers offer " + fullCipher);
        }

        protected void engineSetMode(String mode) {
            this.mMode = mode;
        }

        protected void engineSetPadding(String padding) {
            this.mPadding = padding;
        }

        protected void engineInit(int opmode, Key key, SecureRandom random) throws InvalidKeyException {
            getInstance().init(opmode, key, random);
        }

        protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
            getInstance().init(opmode, key, params, random);
        }

        protected void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
            getInstance().init(opmode, key, params, random);
        }

        protected AlgorithmParameters engineGetParameters() {
            return getInstance().getParameters();
        }

        protected byte[] engineGetIV() {
            return getInstance().getIV();
        }

        protected int engineGetBlockSize() {
            return getInstance().getBlockSize();
        }

        protected int engineGetOutputSize(int inputLen) {
            return getInstance().getOutputSize(inputLen);
        }

        protected byte[] engineUpdate(byte[] input, int offset, int len) {
            byte[] result = getInstance().update(input, offset, len);
            return result == null ? FixBrokenCipherSpiProvider.EMPTY_BYTE_ARRAY : result;
        }

        protected int engineUpdate(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException {
            return getInstance().update(input, inputOffset, inputLen, output, outputOffset);
        }

        protected byte[] engineDoFinal(byte[] input, int inputOffset, int inputLen) throws IllegalBlockSizeException, BadPaddingException {
            return getInstance().doFinal(input, inputOffset, inputLen);
        }

        protected int engineDoFinal(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws ShortBufferException, IllegalBlockSizeException, BadPaddingException {
            return getInstance().doFinal(input, inputOffset, inputLen, output, outputOffset);
        }
    }

    private static class Holder {
        private static FixBrokenCipherSpiProvider INSTANCE;

        static {
            INSTANCE = new FixBrokenCipherSpiProvider();
        }
    }

    static {
        EMPTY_BYTE_ARRAY = new byte[0];
    }

    public static void insertIfNeeded() {
        Holder.INSTANCE.poke();
    }

    private void poke() {
    }

    public FixBrokenCipherSpiProvider() {
        super("FixBrokenCipherSpiProvider", 1.0d, "Workaround for bug in pre-ICS Harmony");
        if (VERSION.SDK_INT < 14) {
            put("Cipher.AES", AES.class.getName());
            Security.insertProviderAt(this, 1);
        }
    }
}
