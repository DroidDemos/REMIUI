package com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageOuterClass.LegalMessage;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Dcb {

    public static final class DcbVerifyAssociationForm extends MessageNano {
        public LegalMessage requiredMessage;
        public String smsMessage;
        public String smsPhoneNumber;

        public DcbVerifyAssociationForm() {
            clear();
        }

        public DcbVerifyAssociationForm clear() {
            this.smsPhoneNumber = "";
            this.smsMessage = "";
            this.requiredMessage = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.smsPhoneNumber.equals("")) {
                output.writeString(1, this.smsPhoneNumber);
            }
            if (!this.smsMessage.equals("")) {
                output.writeString(2, this.smsMessage);
            }
            if (this.requiredMessage != null) {
                output.writeMessage(3, this.requiredMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.smsPhoneNumber.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.smsPhoneNumber);
            }
            if (!this.smsMessage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.smsMessage);
            }
            if (this.requiredMessage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.requiredMessage);
            }
            return size;
        }

        public DcbVerifyAssociationForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.smsPhoneNumber = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.smsMessage = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.requiredMessage == null) {
                            this.requiredMessage = new LegalMessage();
                        }
                        input.readMessage(this.requiredMessage);
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

    public static final class DcbVerifyAssociationFormValue extends MessageNano {
        public String legalDocData;

        public DcbVerifyAssociationFormValue() {
            clear();
        }

        public DcbVerifyAssociationFormValue clear() {
            this.legalDocData = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.legalDocData.equals("")) {
                output.writeString(1, this.legalDocData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.legalDocData.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(1, this.legalDocData);
        }

        public DcbVerifyAssociationFormValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.legalDocData = input.readString();
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
