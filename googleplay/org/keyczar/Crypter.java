package org.keyczar;

import org.apache.log4j.Logger;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.DecryptingStream;
import org.keyczar.interfaces.KeyczarReader;

public class Crypter extends Encrypter {
    private static final Logger LOG;
    private final StreamCache<DecryptingStream> CRYPT_CACHE;

    static {
        LOG = Logger.getLogger(Crypter.class);
    }

    public Crypter(KeyczarReader reader) throws KeyczarException {
        super(reader);
        this.CRYPT_CACHE = new StreamCache();
    }

    boolean isAcceptablePurpose(KeyPurpose purpose) {
        return purpose == KeyPurpose.DECRYPT_AND_ENCRYPT;
    }
}
