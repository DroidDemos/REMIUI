package org.keyczar.exceptions;

import org.keyczar.i18n.Messages;

public class ShortSignatureException extends KeyczarException {
    public ShortSignatureException(int len) {
        super(Messages.getString("SignatureTooShort", Integer.valueOf(len)));
    }
}
