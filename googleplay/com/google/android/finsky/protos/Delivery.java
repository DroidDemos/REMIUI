package com.google.android.finsky.protos;

import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Delivery {

    public static final class DeliveryResponse extends MessageNano {
        public AndroidAppDeliveryData appDeliveryData;
        public boolean hasStatus;
        public int status;

        public DeliveryResponse() {
            clear();
        }

        public DeliveryResponse clear() {
            this.status = 1;
            this.hasStatus = false;
            this.appDeliveryData = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 1 || this.hasStatus) {
                output.writeInt32(1, this.status);
            }
            if (this.appDeliveryData != null) {
                output.writeMessage(2, this.appDeliveryData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 1 || this.hasStatus) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            if (this.appDeliveryData != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.appDeliveryData);
            }
            return size;
        }

        public DeliveryResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                this.status = value;
                                this.hasStatus = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.appDeliveryData == null) {
                            this.appDeliveryData = new AndroidAppDeliveryData();
                        }
                        input.readMessage(this.appDeliveryData);
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
