package org.keyczar.exceptions;

import org.keyczar.i18n.Messages;

public class KeyNotFoundException extends KeyczarException {
    public KeyNotFoundException(byte[] hash) {
        super(Messages.getString("KeyWithHashIdentifier", Integer.toHexString(((((hash[0] & 255) << 24) | ((hash[1] & 255) << 16)) | ((hash[2] & 255) << 8)) | (hash[3] & 255))));
    }

    KeyNotFoundException(String string) {
        super(string);
    }
}
