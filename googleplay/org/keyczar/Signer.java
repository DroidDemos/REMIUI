package org.keyczar;

import java.nio.ByteBuffer;
import org.apache.log4j.Logger;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.exceptions.NoPrimaryKeyException;
import org.keyczar.interfaces.KeyczarReader;
import org.keyczar.interfaces.SigningStream;
import org.keyczar.util.Util;

public class Signer extends Verifier {
    private static final Logger LOG;
    private final StreamQueue<SigningStream> SIGN_QUEUE;

    static {
        LOG = Logger.getLogger(Signer.class);
    }

    public Signer(KeyczarReader reader) throws KeyczarException {
        super(reader);
        this.SIGN_QUEUE = new StreamQueue();
    }

    public int digestSize() throws KeyczarException {
        KeyczarKey signingKey = getPrimaryKey();
        if (signingKey != null) {
            return ((SigningStream) signingKey.getStream()).digestSize() + 5;
        }
        throw new NoPrimaryKeyException();
    }

    public byte[] attachedSign(byte[] blob, byte[] hidden) throws KeyczarException {
        KeyczarKey signingKey = getPrimaryKey();
        if (signingKey == null) {
            throw new NoPrimaryKeyException();
        }
        SigningStream stream = (SigningStream) this.SIGN_QUEUE.poll();
        if (stream == null) {
            stream = (SigningStream) signingKey.getStream();
        }
        stream.initSign();
        byte[] hiddenPlusLength = Util.fromInt(0);
        if (hidden.length > 0) {
            hiddenPlusLength = Util.lenPrefix(hidden);
        }
        stream.updateSign(ByteBuffer.wrap(blob));
        stream.updateSign(ByteBuffer.wrap(hiddenPlusLength));
        stream.updateSign(ByteBuffer.wrap(FORMAT_BYTES));
        ByteBuffer output = ByteBuffer.allocate(digestSize());
        output.mark();
        stream.sign(output);
        output.limit(output.position());
        byte[] signature = Util.cat(FORMAT_BYTES, signingKey.hash(), Util.lenPrefix(blob), output.array());
        this.SIGN_QUEUE.add(stream);
        return signature;
    }

    boolean isAcceptablePurpose(KeyPurpose purpose) {
        return purpose == KeyPurpose.SIGN_AND_VERIFY;
    }
}
