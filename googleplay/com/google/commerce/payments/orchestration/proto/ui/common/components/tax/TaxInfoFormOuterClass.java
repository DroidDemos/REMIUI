package com.google.commerce.payments.orchestration.proto.ui.common.components.tax;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiFieldValue;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface TaxInfoFormOuterClass {

    public static final class TaxInfoForm extends MessageNano {
        private static volatile TaxInfoForm[] _emptyArray;
        public String id;
        public String label;
        public UiField[] taxInfoField;

        public static TaxInfoForm[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new TaxInfoForm[0];
                    }
                }
            }
            return _emptyArray;
        }

        public TaxInfoForm() {
            clear();
        }

        public TaxInfoForm clear() {
            this.id = "";
            this.label = "";
            this.taxInfoField = UiField.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.id.equals("")) {
                output.writeString(1, this.id);
            }
            if (!this.label.equals("")) {
                output.writeString(2, this.label);
            }
            if (this.taxInfoField != null && this.taxInfoField.length > 0) {
                for (UiField element : this.taxInfoField) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
            }
            if (!this.label.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.label);
            }
            if (this.taxInfoField != null && this.taxInfoField.length > 0) {
                for (UiField element : this.taxInfoField) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            return size;
        }

        public TaxInfoForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.id = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.label = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.taxInfoField == null) {
                            i = 0;
                        } else {
                            i = this.taxInfoField.length;
                        }
                        UiField[] newArray = new UiField[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.taxInfoField, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UiField();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UiField();
                        input.readMessage(newArray[i]);
                        this.taxInfoField = newArray;
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

    public static final class TaxInfoFormValue extends MessageNano {
        public String taxInfoFormId;
        public UiFieldValue[] taxInfoValue;

        public TaxInfoFormValue() {
            clear();
        }

        public TaxInfoFormValue clear() {
            this.taxInfoFormId = "";
            this.taxInfoValue = UiFieldValue.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.taxInfoFormId.equals("")) {
                output.writeString(1, this.taxInfoFormId);
            }
            if (this.taxInfoValue != null && this.taxInfoValue.length > 0) {
                for (UiFieldValue element : this.taxInfoValue) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.taxInfoFormId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.taxInfoFormId);
            }
            if (this.taxInfoValue != null && this.taxInfoValue.length > 0) {
                for (UiFieldValue element : this.taxInfoValue) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public TaxInfoFormValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.taxInfoFormId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.taxInfoValue == null) {
                            i = 0;
                        } else {
                            i = this.taxInfoValue.length;
                        }
                        UiFieldValue[] newArray = new UiFieldValue[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.taxInfoValue, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new UiFieldValue();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new UiFieldValue();
                        input.readMessage(newArray[i]);
                        this.taxInfoValue = newArray;
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
