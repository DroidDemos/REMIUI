package com.google.commerce.payments.orchestration.proto.ui.common;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface ResponseContextOuterClass {

    public static final class ResponseContext extends MessageNano {
        public byte[] logToken;
        public long responseTimeMillis;
        public byte[] sessionData;

        public ResponseContext() {
            clear();
        }

        public ResponseContext clear() {
            this.responseTimeMillis = -1;
            this.sessionData = WireFormatNano.EMPTY_BYTES;
            this.logToken = WireFormatNano.EMPTY_BYTES;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.responseTimeMillis != -1) {
                output.writeInt64(1, this.responseTimeMillis);
            }
            if (!Arrays.equals(this.sessionData, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.sessionData);
            }
            if (!Arrays.equals(this.logToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(5, this.logToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.responseTimeMillis != -1) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.responseTimeMillis);
            }
            if (!Arrays.equals(this.sessionData, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.sessionData);
            }
            if (Arrays.equals(this.logToken, WireFormatNano.EMPTY_BYTES)) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeBytesSize(5, this.logToken);
        }

        public ResponseContext mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.responseTimeMillis = input.readInt64();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.sessionData = input.readBytes();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.logToken = input.readBytes();
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
