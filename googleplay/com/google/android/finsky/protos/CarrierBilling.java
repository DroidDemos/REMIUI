package com.google.android.finsky.protos;

import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.CommonDevice.CarrierTos;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface CarrierBilling {

    public static final class InitiateAssociationResponse extends MessageNano {
        public boolean hasUserToken;
        public String userToken;

        public InitiateAssociationResponse() {
            clear();
        }

        public InitiateAssociationResponse clear() {
            this.userToken = "";
            this.hasUserToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUserToken || !this.userToken.equals("")) {
                output.writeString(1, this.userToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUserToken || !this.userToken.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.userToken);
            }
            return size;
        }

        public InitiateAssociationResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.userToken = input.readString();
                        this.hasUserToken = true;
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

    public static final class VerifyAssociationResponse extends MessageNano {
        public Address billingAddress;
        public String carrierErrorMessage;
        public CarrierTos carrierTos;
        public boolean hasCarrierErrorMessage;
        public boolean hasStatus;
        public int status;

        public VerifyAssociationResponse() {
            clear();
        }

        public VerifyAssociationResponse clear() {
            this.status = 1;
            this.hasStatus = false;
            this.billingAddress = null;
            this.carrierTos = null;
            this.carrierErrorMessage = "";
            this.hasCarrierErrorMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 1 || this.hasStatus) {
                output.writeInt32(1, this.status);
            }
            if (this.billingAddress != null) {
                output.writeMessage(2, this.billingAddress);
            }
            if (this.carrierTos != null) {
                output.writeMessage(3, this.carrierTos);
            }
            if (this.hasCarrierErrorMessage || !this.carrierErrorMessage.equals("")) {
                output.writeString(4, this.carrierErrorMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 1 || this.hasStatus) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            if (this.billingAddress != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.billingAddress);
            }
            if (this.carrierTos != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.carrierTos);
            }
            if (this.hasCarrierErrorMessage || !this.carrierErrorMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.carrierErrorMessage);
            }
            return size;
        }

        public VerifyAssociationResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.status = value;
                                this.hasStatus = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.billingAddress == null) {
                            this.billingAddress = new Address();
                        }
                        input.readMessage(this.billingAddress);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.carrierTos == null) {
                            this.carrierTos = new CarrierTos();
                        }
                        input.readMessage(this.carrierTos);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.carrierErrorMessage = input.readString();
                        this.hasCarrierErrorMessage = true;
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
