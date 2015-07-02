package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.VoucherId;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Voucher {

    public static final class LibraryVoucher extends MessageNano {
        public VoucherId voucherId;

        public LibraryVoucher() {
            clear();
        }

        public LibraryVoucher clear() {
            this.voucherId = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.voucherId != null) {
                output.writeMessage(1, this.voucherId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.voucherId != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.voucherId);
            }
            return size;
        }

        public LibraryVoucher mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.voucherId == null) {
                            this.voucherId = new VoucherId();
                        }
                        input.readMessage(this.voucherId);
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
}
