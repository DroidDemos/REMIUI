package com.google.commerce.payments.orchestration.proto.ui.common.components.legal;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.components.InfoMessageOuterClass.InfoMessage;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface LegalMessageOuterClass {

    public static final class LegalMessage extends MessageNano {
        public InfoMessage messageText;
        public String opaqueData;

        public LegalMessage() {
            clear();
        }

        public LegalMessage clear() {
            this.messageText = null;
            this.opaqueData = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.opaqueData.equals("")) {
                output.writeString(3, this.opaqueData);
            }
            if (this.messageText != null) {
                output.writeMessage(5, this.messageText);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.opaqueData.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.opaqueData);
            }
            if (this.messageText != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.messageText);
            }
            return size;
        }

        public LegalMessage mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.opaqueData = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.messageText == null) {
                            this.messageText = new InfoMessage();
                        }
                        input.readMessage(this.messageText);
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
