package com.google.commerce.payments.orchestration.proto.ui.common.components;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface InfoMessageOuterClass {

    public static final class InfoMessage extends MessageNano {
        public String detailedMessageHtml;
        public String messageHtml;
        public String showDetailedMessageLabel;

        public InfoMessage() {
            clear();
        }

        public InfoMessage clear() {
            this.messageHtml = "";
            this.detailedMessageHtml = "";
            this.showDetailedMessageLabel = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.messageHtml.equals("")) {
                output.writeString(1, this.messageHtml);
            }
            if (!this.detailedMessageHtml.equals("")) {
                output.writeString(2, this.detailedMessageHtml);
            }
            if (!this.showDetailedMessageLabel.equals("")) {
                output.writeString(3, this.showDetailedMessageLabel);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.messageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.messageHtml);
            }
            if (!this.detailedMessageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.detailedMessageHtml);
            }
            if (this.showDetailedMessageLabel.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.showDetailedMessageLabel);
        }

        public InfoMessage mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.messageHtml = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.detailedMessageHtml = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.showDetailedMessageLabel = input.readString();
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
