package org.keyczar;

import java.nio.ByteBuffer;
import org.apache.log4j.Logger;
import org.keyczar.enums.KeyPurpose;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.exceptions.NoPrimaryKeyException;
import org.keyczar.i18n.Messages;
import org.keyczar.interfaces.EncryptingStream;
import org.keyczar.interfaces.KeyczarReader;
import org.keyczar.interfaces.SigningStream;
import org.keyczar.util.Base64Coder;

public class Encrypter extends Keyczar {
    private static final Logger LOG;
    private final StreamQueue<EncryptingStream> ENCRYPT_QUEUE;

    static {
        LOG = Logger.getLogger(Encrypter.class);
    }

    public Encrypter(KeyczarReader reader) throws KeyczarException {
        super(reader);
        this.ENCRYPT_QUEUE = new StreamQueue();
    }

    public int ciphertextSize(int inputLength) throws KeyczarException {
        EncryptingStream cryptStream = (EncryptingStream) this.ENCRYPT_QUEUE.poll();
        if (cryptStream == null) {
            KeyczarKey encryptingKey = getPrimaryKey();
            if (encryptingKey == null) {
                throw new NoPrimaryKeyException();
            }
            cryptStream = (EncryptingStream) encryptingKey.getStream();
        }
        int outputSize = (cryptStream.maxOutputSize(inputLength) + 5) + cryptStream.getSigningStream().digestSize();
        this.ENCRYPT_QUEUE.add(cryptStream);
        return outputSize;
    }

    public byte[] encrypt(byte[] input) throws KeyczarException {
        ByteBuffer output = ByteBuffer.allocate(ciphertextSize(input.length));
        encrypt(ByteBuffer.wrap(input), output);
        output.reset();
        byte[] outputBytes = new byte[output.remaining()];
        output.get(outputBytes);
        return outputBytes;
    }

    public void encrypt(ByteBuffer input, ByteBuffer output) throws KeyczarException {
        LOG.debug(Messages.getString("Encrypter.Encrypting", Integer.valueOf(input.remaining())));
        KeyczarKey encryptingKey = getPrimaryKey();
        if (encryptingKey == null) {
            throw new NoPrimaryKeyException();
        }
        EncryptingStream cryptStream = (EncryptingStream) this.ENCRYPT_QUEUE.poll();
        if (cryptStream == null) {
            cryptStream = (EncryptingStream) encryptingKey.getStream();
        }
        SigningStream signStream = cryptStream.getSigningStream();
        signStream.initSign();
        output.mark();
        ByteBuffer outputToSign = output.asReadOnlyBuffer();
        encryptingKey.copyHeader(output);
        cryptStream.initEncrypt(output);
        ByteBuffer inputCopy = input.asReadOnlyBuffer();
        while (inputCopy.remaining() > 1024) {
            ByteBuffer inputChunk = inputCopy.slice();
            inputChunk.limit(1024);
            cryptStream.updateEncrypt(inputChunk, output);
            inputCopy.position(inputCopy.position() + 1024);
            outputToSign.limit(output.position());
            signStream.updateSign(outputToSign);
            outputToSign.position(output.position());
        }
        cryptStream.doFinalEncrypt(inputCopy, output);
        output.limit(output.position() + signStream.digestSize());
        outputToSign.limit(output.position());
        signStream.updateSign(outputToSign);
        signStream.sign(output);
        this.ENCRYPT_QUEUE.add(cryptStream);
    }

    public String encrypt(String input) throws KeyczarException {
        try {
            return Base64Coder.encodeWebSafe(encrypt(input.getBytes("UTF-8")));
        } catch (Throwable e) {
            throw new KeyczarException(e);
        }
    }

    boolean isAcceptablePurpose(KeyPurpose purpose) {
        return purpose == KeyPurpose.ENCRYPT || purpose == KeyPurpose.DECRYPT_AND_ENCRYPT;
    }
}
