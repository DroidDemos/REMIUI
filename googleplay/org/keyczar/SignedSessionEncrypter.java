package org.keyczar;

import java.util.concurrent.atomic.AtomicReference;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.util.Base64Coder;
import org.keyczar.util.Util;

public class SignedSessionEncrypter {
    private final Encrypter encrypter;
    private AtomicReference<SessionMaterial> session;
    private final Signer signer;

    public SignedSessionEncrypter(Encrypter encrypter, Signer signer) {
        this.session = new AtomicReference();
        this.encrypter = encrypter;
        this.signer = signer;
    }

    public String newSession() throws KeyczarException {
        return newSession(((Integer) DefaultKeyType.AES.getAcceptableSizes().get(0)).intValue());
    }

    public String newSession(int aesKeySize) throws KeyczarException {
        if (DefaultKeyType.AES.isAcceptableSize(aesKeySize)) {
            AesKey aesKey = AesKey.generate(aesKeySize);
            byte[] nonce = new byte[16];
            Util.rand(nonce);
            this.session.set(new SessionMaterial(aesKey, Base64Coder.encodeWebSafe(nonce)));
            return this.encrypter.encrypt(((SessionMaterial) this.session.get()).toString());
        }
        throw new KeyczarException("Unsupported key size requested for session");
    }

    public byte[] encrypt(byte[] plainText) throws KeyczarException {
        if (this.session.get() == null) {
            throw new KeyczarException("Session not initialized.");
        }
        SessionMaterial material = (SessionMaterial) this.session.get();
        return this.signer.attachedSign(new Crypter(new ImportedKeyReader(material.getKey())).encrypt(plainText), Base64Coder.decodeWebSafe(material.getNonce()));
    }
}
