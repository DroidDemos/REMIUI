package com.google.android.finsky.protos;

import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface MyAccount {

    public static final class MyAccountResponse extends MessageNano {
        public SectionMetadata purchaseHistoryMetadata;

        public MyAccountResponse() {
            clear();
        }

        public MyAccountResponse clear() {
            this.purchaseHistoryMetadata = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.purchaseHistoryMetadata != null) {
                output.writeMessage(1, this.purchaseHistoryMetadata);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.purchaseHistoryMetadata != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.purchaseHistoryMetadata);
            }
            return size;
        }

        public MyAccountResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.purchaseHistoryMetadata == null) {
                            this.purchaseHistoryMetadata = new SectionMetadata();
                        }
                        input.readMessage(this.purchaseHistoryMetadata);
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
