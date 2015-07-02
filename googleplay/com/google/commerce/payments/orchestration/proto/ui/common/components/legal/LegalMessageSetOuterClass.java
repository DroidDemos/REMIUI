package com.google.commerce.payments.orchestration.proto.ui.common.components.legal;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageOuterClass.LegalMessage;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface LegalMessageSetOuterClass {

    public static final class LegalMessageByCountry extends MessageNano {
        private static volatile LegalMessageByCountry[] _emptyArray;
        public String country;
        public LegalMessage message;

        public static LegalMessageByCountry[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LegalMessageByCountry[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LegalMessageByCountry() {
            clear();
        }

        public LegalMessageByCountry clear() {
            this.country = "";
            this.message = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.country.equals("")) {
                output.writeString(1, this.country);
            }
            if (this.message != null) {
                output.writeMessage(2, this.message);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.country.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.country);
            }
            if (this.message != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.message);
            }
            return size;
        }

        public LegalMessageByCountry mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.country = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.message == null) {
                            this.message = new LegalMessage();
                        }
                        input.readMessage(this.message);
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

    public static final class LegalMessageSet extends MessageNano {
        public LegalMessage defaultMessage;
        public LegalMessageByCountry[] messageByCountry;

        public LegalMessageSet() {
            clear();
        }

        public LegalMessageSet clear() {
            this.defaultMessage = null;
            this.messageByCountry = LegalMessageByCountry.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.defaultMessage != null) {
                output.writeMessage(1, this.defaultMessage);
            }
            if (this.messageByCountry != null && this.messageByCountry.length > 0) {
                for (LegalMessageByCountry element : this.messageByCountry) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.defaultMessage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.defaultMessage);
            }
            if (this.messageByCountry != null && this.messageByCountry.length > 0) {
                for (LegalMessageByCountry element : this.messageByCountry) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public LegalMessageSet mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.defaultMessage == null) {
                            this.defaultMessage = new LegalMessage();
                        }
                        input.readMessage(this.defaultMessage);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.messageByCountry == null) {
                            i = 0;
                        } else {
                            i = this.messageByCountry.length;
                        }
                        LegalMessageByCountry[] newArray = new LegalMessageByCountry[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.messageByCountry, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LegalMessageByCountry();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new LegalMessageByCountry();
                        input.readMessage(newArray[i]);
                        this.messageByCountry = newArray;
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
