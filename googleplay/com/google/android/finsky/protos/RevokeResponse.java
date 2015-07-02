package com.google.android.finsky.protos;

import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class RevokeResponse extends MessageNano {
    public LibraryUpdate libraryUpdate;

    public RevokeResponse() {
        clear();
    }

    public RevokeResponse clear() {
        this.libraryUpdate = null;
        this.cachedSize = -1;
        return this;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.libraryUpdate != null) {
            output.writeMessage(1, this.libraryUpdate);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int size = super.computeSerializedSize();
        if (this.libraryUpdate != null) {
            return size + CodedOutputByteBufferNano.computeMessageSize(1, this.libraryUpdate);
        }
        return size;
    }

    public RevokeResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
