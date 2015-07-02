package org.keyczar.exceptions;

import org.keyczar.i18n.Messages;

public class NoPrimaryKeyException extends KeyNotFoundException {
    public NoPrimaryKeyException() {
        super(Messages.getString("NoPrimaryKeyFound", new Object[0]));
    }
}
