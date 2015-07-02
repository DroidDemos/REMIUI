package com.google.android.finsky.protos;

import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class ConsumePurchaseResponse extends MessageNano {
    public boolean hasStatus;
    public LibraryUpdate libraryUpdate;
    public int status;

    public ConsumePurchaseResponse() {
        clear();
    }

    public ConsumePurchaseResponse clear() {
        this.libraryUpdate = null;
        this.status = 0;
        this.hasStatus = false;
        this.cachedSize = -1;
        return this;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.libraryUpdate != null) {
            output.writeMessage(1, this.libraryUpdate);
        }
        if (this.status != 0 || this.hasStatus) {
            output.writeInt32(2, this.status);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.libraryUpdate != null) {
            size += CodedOutputByteBufferNano.computeMessageSize(1, this.libraryUpdate);
        }
        if (this.status != 0 || this.hasStatus) {
            return size + CodedOutputByteBufferNano.computeInt32Size(2, this.status);
        }
        return size;
    }

    public ConsumePurchaseResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            switch (tag) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    if (this.libraryUpdate == null) {
                        this.libraryUpdate = new LibraryUpdate();
                    }
                    input.readMessage(this.libraryUpdate);
                    continue;
                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                    int value = input.readInt32();
                    switch (value) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            this.status = value;
                            this.hasStatus = true;
                            break;
                        default:
                            continue;
                    }
                default:
                    if (!WireFormatNano.parseUnknownField(input, tag)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }
}
