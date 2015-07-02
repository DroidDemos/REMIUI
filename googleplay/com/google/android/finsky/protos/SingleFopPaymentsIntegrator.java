package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface SingleFopPaymentsIntegrator {

    public static final class SingleFopPaymentsIntegratorContext extends MessageNano {
        public byte[] commonToken;
        public boolean hasCommonToken;
        public boolean hasInstrumentToken;
        public byte[] instrumentToken;

        public SingleFopPaymentsIntegratorContext() {
            clear();
        }

        public SingleFopPaymentsIntegratorContext clear() {
            this.commonToken = WireFormatNano.EMPTY_BYTES;
            this.hasCommonToken = false;
            this.instrumentToken = WireFormatNano.EMPTY_BYTES;
            this.hasInstrumentToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasCommonToken || !Arrays.equals(this.commonToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(1, this.commonToken);
            }
            if (this.hasInstrumentToken || !Arrays.equals(this.instrumentToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.instrumentToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasCommonToken || !Arrays.equals(this.commonToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(1, this.commonToken);
            }
            if (this.hasInstrumentToken || !Arrays.equals(this.instrumentToken, WireFormatNano.EMPTY_BYTES)) {
                return size + CodedOutputByteBufferNano.computeBytesSize(2, this.instrumentToken);
            }
            return size;
        }

        public SingleFopPaymentsIntegratorContext mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.commonToken = input.readBytes();
                        this.hasCommonToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.instrumentToken = input.readBytes();
                        this.hasInstrumentToken = true;
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
