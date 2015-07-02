package org.keyczar.enums;

import com.google.android.wallet.instrumentmanager.R;
import java.security.interfaces.RSAPublicKey;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.util.Util;

public enum RsaPadding {
    OAEP("RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING"),
    PKCS("RSA/ECB/PKCS1PADDING");
    
    private final String cryptAlgorithm;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$keyczar$enums$RsaPadding;

        static {
            $SwitchMap$org$keyczar$enums$RsaPadding = new int[RsaPadding.values().length];
            try {
                $SwitchMap$org$keyczar$enums$RsaPadding[RsaPadding.OAEP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$keyczar$enums$RsaPadding[RsaPadding.PKCS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private RsaPadding(String cryptAlgorithm) {
        this.cryptAlgorithm = cryptAlgorithm;
    }

    public String getCryptAlgorithm() {
        return this.cryptAlgorithm;
    }

    public byte[] computeFullHash(RSAPublicKey key) throws KeyczarException {
        switch (AnonymousClass1.$SwitchMap$org$keyczar$enums$RsaPadding[ordinal()]) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return Util.prefixHash(Util.stripLeadingZeros(key.getModulus().toByteArray()), Util.stripLeadingZeros(key.getPublicExponent().toByteArray()));
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return Util.prefixHash(key.getModulus().toByteArray(), key.getPublicExponent().toByteArray());
            default:
                throw new KeyczarException("Bug! Unknown padding type");
        }
    }
}
