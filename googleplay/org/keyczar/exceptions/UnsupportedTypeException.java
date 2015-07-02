package org.keyczar.exceptions;

import org.keyczar.i18n.Messages;
import org.keyczar.interfaces.KeyType;

public class UnsupportedTypeException extends KeyczarException {
    public UnsupportedTypeException(KeyType type) {
        super(Messages.getString("InvalidTypeInInput", type));
    }
}
