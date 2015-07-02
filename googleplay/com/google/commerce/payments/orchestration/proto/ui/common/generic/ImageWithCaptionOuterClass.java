package com.google.commerce.payments.orchestration.proto.ui.common.generic;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface ImageWithCaptionOuterClass {

    public static final class ImageWithCaption extends MessageNano {
        public String altText;
        public String imageUri;

        public ImageWithCaption() {
            clear();
        }

        public ImageWithCaption clear() {
            this.imageUri = "";
            this.altText = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.imageUri.equals("")) {
                output.writeString(1, this.imageUri);
            }
            if (!this.altText.equals("")) {
                output.writeString(3, this.altText);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.imageUri.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.imageUri);
            }
            if (this.altText.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.altText);
        }

        public ImageWithCaption mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.imageUri = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.altText = input.readString();
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
