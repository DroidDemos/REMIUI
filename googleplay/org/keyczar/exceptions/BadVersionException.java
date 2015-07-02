package org.keyczar.exceptions;

import org.keyczar.i18n.Messages;

public class BadVersionException extends KeyczarException {
    public BadVersionException(byte badVersion) {
        super(Messages.getString("BadVersionNumber", Byte.valueOf(badVersion)));
    }
}
