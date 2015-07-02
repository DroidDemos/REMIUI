package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class RateSuggestedContentResponse extends MessageNano {
    public RateSuggestedContentResponse() {
        clear();
    }

    public RateSuggestedContentResponse clear() {
        this.cachedSize = -1;
        return this;
    }

    public RateSuggestedContentResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
        int tag;
        do {
            tag = input.readTag();
            switch (tag) {
                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    break;
                default:
                    break;
            }
            return this;
        } while (WireFormatNano.parseUnknownField(input, tag));
        return this;
    }
}
