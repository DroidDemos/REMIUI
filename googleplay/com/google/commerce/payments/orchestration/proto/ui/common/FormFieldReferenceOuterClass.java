package com.google.commerce.payments.orchestration.proto.ui.common;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface FormFieldReferenceOuterClass {

    public static final class FormFieldReference extends MessageNano {
        private static volatile FormFieldReference[] _emptyArray;
        public int fieldId;
        public String formId;
        public int repeatedFieldIndex;

        public static FormFieldReference[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FormFieldReference[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FormFieldReference() {
            clear();
        }

        public FormFieldReference clear() {
            this.formId = "";
            this.fieldId = 0;
            this.repeatedFieldIndex = -1;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.formId.equals("")) {
                output.writeString(1, this.formId);
            }
            if (this.fieldId != 0) {
                output.writeInt32(2, this.fieldId);
            }
            if (this.repeatedFieldIndex != -1) {
                output.writeInt32(3, this.repeatedFieldIndex);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.formId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.formId);
            }
            if (this.fieldId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.fieldId);
            }
            if (this.repeatedFieldIndex != -1) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.repeatedFieldIndex);
            }
            return size;
        }

        public FormFieldReference mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.formId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.fieldId = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.repeatedFieldIndex = input.readInt32();
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
