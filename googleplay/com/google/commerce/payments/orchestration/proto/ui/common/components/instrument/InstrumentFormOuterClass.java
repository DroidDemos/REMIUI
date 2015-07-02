package com.google.commerce.payments.orchestration.proto.ui.common.components.instrument;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.CreditCard.CreditCardFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.Dcb.DcbVerifyAssociationForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.Dcb.DcbVerifyAssociationFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.UsernamePassword.UsernamePasswordForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types.UsernamePassword.UsernamePasswordFormValue;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface InstrumentFormOuterClass {

    public static final class InstrumentForm extends MessageNano {
        public CreditCardForm creditCard;
        public DcbVerifyAssociationForm dcbVerifyAssociation;
        public UsernamePasswordForm usernamePassword;

        public InstrumentForm() {
            clear();
        }

        public InstrumentForm clear() {
            this.creditCard = null;
            this.dcbVerifyAssociation = null;
            this.usernamePassword = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.creditCard != null) {
                output.writeMessage(2, this.creditCard);
            }
            if (this.dcbVerifyAssociation != null) {
                output.writeMessage(5, this.dcbVerifyAssociation);
            }
            if (this.usernamePassword != null) {
                output.writeMessage(7, this.usernamePassword);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.creditCard != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.creditCard);
            }
            if (this.dcbVerifyAssociation != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.dcbVerifyAssociation);
            }
            if (this.usernamePassword != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.usernamePassword);
            }
            return size;
        }

        public InstrumentForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.creditCard == null) {
                            this.creditCard = new CreditCardForm();
                        }
                        input.readMessage(this.creditCard);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.dcbVerifyAssociation == null) {
                            this.dcbVerifyAssociation = new DcbVerifyAssociationForm();
                        }
                        input.readMessage(this.dcbVerifyAssociation);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.usernamePassword == null) {
                            this.usernamePassword = new UsernamePasswordForm();
                        }
                        input.readMessage(this.usernamePassword);
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

    public static final class InstrumentFormValue extends MessageNano {
        public CreditCardFormValue creditCard;
        public DcbVerifyAssociationFormValue dcbVerifyAssociation;
        public UsernamePasswordFormValue usernamePassword;

        public InstrumentFormValue() {
            clear();
        }

        public InstrumentFormValue clear() {
            this.creditCard = null;
            this.dcbVerifyAssociation = null;
            this.usernamePassword = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.creditCard != null) {
                output.writeMessage(1, this.creditCard);
            }
            if (this.dcbVerifyAssociation != null) {
                output.writeMessage(4, this.dcbVerifyAssociation);
            }
            if (this.usernamePassword != null) {
                output.writeMessage(6, this.usernamePassword);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.creditCard != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.creditCard);
            }
            if (this.dcbVerifyAssociation != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.dcbVerifyAssociation);
            }
            if (this.usernamePassword != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.usernamePassword);
            }
            return size;
        }

        public InstrumentFormValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.creditCard == null) {
                            this.creditCard = new CreditCardFormValue();
                        }
                        input.readMessage(this.creditCard);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.dcbVerifyAssociation == null) {
                            this.dcbVerifyAssociation = new DcbVerifyAssociationFormValue();
                        }
                        input.readMessage(this.dcbVerifyAssociation);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.usernamePassword == null) {
                            this.usernamePassword = new UsernamePasswordFormValue();
                        }
                        input.readMessage(this.usernamePassword);
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
