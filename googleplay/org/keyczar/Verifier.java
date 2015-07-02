package org.keyczar;

import java.nio.ByteBuffer;
import org.apache.log4j.Logger;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.exceptions.BadVersionException;
import org.keyczar.exceptions.KeyNotFoundException;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.exceptions.ShortSignatureException;
import org.keyczar.i18n.Messages;
import org.keyczar.interfaces.KeyczarReader;
import org.keyczar.interfaces.VerifyingStream;

public class Verifier extends Keyczar {
    private static final Logger LOG;
    private final StreamCache<VerifyingStream> VERIFY_CACHE;

    static {
        LOG = Logger.getLogger(Verifier.class);
    }

    public Verifier(KeyczarReader reader) throws KeyczarException {
        super(reader);
        this.VERIFY_CACHE = new StreamCache();
    }

    public boolean verify(byte[] data, byte[] signature) throws KeyczarException {
        return verify(ByteBuffer.wrap(data), ByteBuffer.wrap(signature));
    }

    public boolean verify(ByteBuffer data, ByteBuffer signature) throws KeyczarException {
        return verify(data, null, signature);
    }

    boolean verify(ByteBuffer data, ByteBuffer hidden, ByteBuffer signature) throws KeyczarException {
        LOG.debug(Messages.getString("Verifier.Verifying", Integer.valueOf(data.remaining())));
        if (signature.remaining() < 5) {
            throw new ShortSignatureException(signature.remaining());
        }
        byte[] hash = checkFormatAndGetHash(signature);
        KeyczarKey key = getVerifyingKey(hash);
        if (key == null) {
            throw new KeyNotFoundException(hash);
        }
        VerifyingStream stream = (VerifyingStream) this.VERIFY_CACHE.get(key);
        if (stream == null) {
            stream = (VerifyingStream) key.getStream();
        }
        stream.initVerify();
        if (hidden != null) {
            stream.updateVerify(hidden);
        }
        stream.updateVerify(data);
        stream.updateVerify(ByteBuffer.wrap(FORMAT_BYTES));
        boolean result = stream.verify(signature);
        this.VERIFY_CACHE.put(key, stream);
        return result;
    }

    private byte[] checkFormatAndGetHash(ByteBuffer signature) throws BadVersionException {
        byte version = signature.get();
        if (version != (byte) 0) {
            throw new BadVersionException(version);
        }
        byte[] hash = new byte[4];
        signature.get(hash);
        return hash;
    }

    private KeyczarKey getVerifyingKey(byte[] hash) throws KeyNotFoundException {
        KeyczarKey key = getKey(hash);
        if (key != null) {
            return key;
        }
        throw new KeyNotFoundException(hash);
    }

    boolean isAcceptablePurpose(KeyPurpose purpose) {
        return purpose == KeyPurpose.VERIFY || purpose == KeyPurpose.SIGN_AND_VERIFY;
    }
}
