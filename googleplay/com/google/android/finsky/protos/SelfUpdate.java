package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface SelfUpdate {

    public static final class SelfUpdateResponse extends MessageNano {
        public boolean hasLatestClientVersionCode;
        public boolean hasRequiresUploadDeviceConfig;
        public int latestClientVersionCode;
        public boolean requiresUploadDeviceConfig;

        public SelfUpdateResponse() {
            clear();
        }

        public SelfUpdateResponse clear() {
            this.requiresUploadDeviceConfig = false;
            this.hasRequiresUploadDeviceConfig = false;
            this.latestClientVersionCode = 0;
            this.hasLatestClientVersionCode = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasRequiresUploadDeviceConfig || this.requiresUploadDeviceConfig) {
                output.writeBool(1, this.requiresUploadDeviceConfig);
            }
            if (this.hasLatestClientVersionCode || this.latestClientVersionCode != 0) {
                output.writeInt32(2, this.latestClientVersionCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasRequiresUploadDeviceConfig || this.requiresUploadDeviceConfig) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.requiresUploadDeviceConfig);
            }
            if (this.hasLatestClientVersionCode || this.latestClientVersionCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.latestClientVersionCode);
            }
            return size;
        }

        public SelfUpdateResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.requiresUploadDeviceConfig = input.readBool();
                        this.hasRequiresUploadDeviceConfig = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.latestClientVersionCode = input.readInt32();
                        this.hasLatestClientVersionCode = true;
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
