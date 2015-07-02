package com.google.commerce.payments.orchestration.proto.ui.common;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.FormFieldReferenceOuterClass.FormFieldReference;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface UiErrorOuterClass {

    public static final class FormFieldMessage extends MessageNano {
        private static volatile FormFieldMessage[] _emptyArray;
        public FormFieldReference formFieldReference;
        public String message;

        public static FormFieldMessage[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FormFieldMessage[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FormFieldMessage() {
            clear();
        }

        public FormFieldMessage clear() {
            this.formFieldReference = null;
            this.message = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.message.equals("")) {
                output.writeString(3, this.message);
            }
            if (this.formFieldReference != null) {
                output.writeMessage(5, this.formFieldReference);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.message.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.message);
            }
            if (this.formFieldReference != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.formFieldReference);
            }
            return size;
        }

        public FormFieldMessage mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.message = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.formFieldReference == null) {
                            this.formFieldReference = new FormFieldReference();
                        }
                        input.readMessage(this.formFieldReference);
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

    public static final class UiError extends MessageNano {
        public int action;
        public String errorCode;
        public FormFieldMessage[] formFieldMessage;
        public String internalDetails;
        public String message;

        public UiError() {
            clear();
        }

        public UiError clear() {
            this.message = "";
            this.action = 1;
            this.formFieldMessage = FormFieldMessage.emptyArray();
            this.errorCode = "";
            this.internalDetails = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.message.equals("")) {
                output.writeString(1, this.message);
            }
            if (this.formFieldMessage != null && this.formFieldMessage.length > 0) {
                for (FormFieldMessage element : this.formFieldMessage) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (!this.errorCode.equals("")) {
                output.writeString(3, this.errorCode);
            }
            if (!this.internalDetails.equals("")) {
                output.writeString(4, this.internalDetails);
            }
            if (this.action != 1) {
                output.writeInt32(5, this.action);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.message.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.message);
            }
            if (this.formFieldMessage != null && this.formFieldMessage.length > 0) {
                for (FormFieldMessage element : this.formFieldMessage) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (!this.errorCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.errorCode);
            }
            if (!this.internalDetails.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.internalDetails);
            }
            if (this.action != 1) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.action);
            }
            return size;
        }

        public UiError mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.message = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.formFieldMessage == null) {
                            i = 0;
                        } else {
                            i = this.formFieldMessage.length;
                        }
                        FormFieldMessage[] newArray = new FormFieldMessage[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.formFieldMessage, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FormFieldMessage();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new FormFieldMessage();
                        input.readMessage(newArray[i]);
                        this.formFieldMessage = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.errorCode = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.internalDetails = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.action = value;
                                break;
                            default:
                                continue;
                        }
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
