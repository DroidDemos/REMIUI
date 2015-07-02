package org.keyczar;

import com.google.android.wallet.instrumentmanager.R;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.keyczar.enums.RsaPadding;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.exceptions.UnsupportedTypeException;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.KeyType.Builder;

public enum DefaultKeyType implements KeyType {
    AES((String) Arrays.asList(new Integer[]{Integer.valueOf(128), Integer.valueOf(192), Integer.valueOf(256)}), 0),
    HMAC_SHA1((String) Arrays.asList(new Integer[]{Integer.valueOf(256)}), 20),
    DSA_PRIV((String) Arrays.asList(new Integer[]{Integer.valueOf(1024)}), 48),
    DSA_PUB((String) Arrays.asList(new Integer[]{Integer.valueOf(1024)}), 48),
    RSA_PRIV((String) Arrays.asList(new Integer[]{Integer.valueOf(4096), Integer.valueOf(2048), Integer.valueOf(1024)}), (int) Arrays.asList(new Integer[]{Integer.valueOf(512), Integer.valueOf(256), Integer.valueOf(128)})),
    RSA_PUB((String) Arrays.asList(new Integer[]{Integer.valueOf(4096), Integer.valueOf(2048), Integer.valueOf(1024)}), (int) Arrays.asList(new Integer[]{Integer.valueOf(512), Integer.valueOf(256), Integer.valueOf(128)})),
    EC_PRIV((String) Arrays.asList(new Integer[]{Integer.valueOf(256), Integer.valueOf(384), Integer.valueOf(521), Integer.valueOf(192)}), 70),
    EC_PUB((String) Arrays.asList(new Integer[]{Integer.valueOf(256), Integer.valueOf(384), Integer.valueOf(521), Integer.valueOf(192)}), 70),
    TEST((String) Arrays.asList(new Integer[]{Integer.valueOf(1)}), 0);
    
    private static Map<String, KeyType> typeMapping;
    private final List<Integer> acceptableSizes;
    private final Map<Integer, Integer> outputSizeMap;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$keyczar$DefaultKeyType;

        static {
            $SwitchMap$org$keyczar$DefaultKeyType = new int[DefaultKeyType.values().length];
            try {
                $SwitchMap$org$keyczar$DefaultKeyType[DefaultKeyType.AES.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$keyczar$DefaultKeyType[DefaultKeyType.HMAC_SHA1.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$keyczar$DefaultKeyType[DefaultKeyType.DSA_PRIV.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$keyczar$DefaultKeyType[DefaultKeyType.DSA_PUB.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$org$keyczar$DefaultKeyType[DefaultKeyType.RSA_PRIV.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$org$keyczar$DefaultKeyType[DefaultKeyType.RSA_PUB.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private class DefaultKeyBuilder implements Builder {
        private final RsaPadding padding;

        private DefaultKeyBuilder() {
            this.padding = null;
        }

        public KeyczarKey read(String key) throws KeyczarException {
            switch (AnonymousClass1.$SwitchMap$org$keyczar$DefaultKeyType[DefaultKeyType.this.ordinal()]) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    return AesKey.read(key);
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return HmacKey.read(key);
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    return DsaPrivateKey.read(key);
                case R.styleable.WalletImFormEditText_required /*4*/:
                    return DsaPublicKey.read(key);
                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                    return RsaPrivateKey.read(key);
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    return RsaPublicKey.read(key);
                default:
                    throw new UnsupportedTypeException(DefaultKeyType.this);
            }
        }
    }

    private DefaultKeyType(List<Integer> sizes, int outputSize) {
        this.outputSizeMap = new HashMap();
        this.acceptableSizes = sizes;
        for (Integer intValue : this.acceptableSizes) {
            this.outputSizeMap.put(Integer.valueOf(intValue.intValue()), Integer.valueOf(outputSize));
        }
        addToMapping(name(), this);
    }

    private DefaultKeyType(List<Integer> sizes, List<Integer> outputSizeList) {
        this.outputSizeMap = new HashMap();
        this.acceptableSizes = sizes;
        for (int i = 0; i < sizes.size(); i++) {
            this.outputSizeMap.put(this.acceptableSizes.get(i), outputSizeList.get(i));
        }
        addToMapping(name(), this);
    }

    private static void addToMapping(String s, KeyType type) {
        if (typeMapping == null) {
            typeMapping = new HashMap();
        }
        typeMapping.put(s, type);
    }

    public int defaultSize() {
        return ((Integer) this.acceptableSizes.get(0)).intValue();
    }

    public int getOutputSize(int keySize) {
        return ((Integer) this.outputSizeMap.get(Integer.valueOf(keySize))).intValue();
    }

    public int getOutputSize() {
        return getOutputSize(defaultSize());
    }

    public boolean isAcceptableSize(int size) {
        return this.acceptableSizes.contains(Integer.valueOf(size));
    }

    public List<Integer> getAcceptableSizes() {
        return Collections.unmodifiableList(this.acceptableSizes);
    }

    public String getName() {
        return name();
    }

    public Builder getBuilder() {
        return new DefaultKeyBuilder();
    }
}
