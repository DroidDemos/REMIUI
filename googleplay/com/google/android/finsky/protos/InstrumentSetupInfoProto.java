package com.google.android.finsky.protos;

import com.google.android.finsky.protos.ChallengeProto.AddressChallenge;
import com.google.android.finsky.protos.CommonDevice.Money;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface InstrumentSetupInfoProto {

    public static final class InstrumentSetupInfo extends MessageNano {
        private static volatile InstrumentSetupInfo[] _emptyArray;
        public AddressChallenge addressChallenge;
        public Money balance;
        public String[] footerHtml;
        public boolean hasInstrumentFamily;
        public boolean hasSupported;
        public int instrumentFamily;
        public boolean supported;

        public static InstrumentSetupInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InstrumentSetupInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InstrumentSetupInfo() {
            clear();
        }

        public InstrumentSetupInfo clear() {
            this.instrumentFamily = 0;
            this.hasInstrumentFamily = false;
            this.supported = false;
            this.hasSupported = false;
            this.addressChallenge = null;
            this.balance = null;
            this.footerHtml = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.instrumentFamily != 0 || this.hasInstrumentFamily) {
                output.writeInt32(1, this.instrumentFamily);
            }
            if (this.hasSupported || this.supported) {
                output.writeBool(2, this.supported);
            }
            if (this.addressChallenge != null) {
                output.writeMessage(3, this.addressChallenge);
            }
            if (this.balance != null) {
                output.writeMessage(4, this.balance);
            }
            if (this.footerHtml != null && this.footerHtml.length > 0) {
                for (String element : this.footerHtml) {
                    if (element != null) {
                        output.writeString(5, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.instrumentFamily != 0 || this.hasInstrumentFamily) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.instrumentFamily);
            }
            if (this.hasSupported || this.supported) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.supported);
            }
            if (this.addressChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.addressChallenge);
            }
            if (this.balance != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.balance);
            }
            if (this.footerHtml == null || this.footerHtml.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element : this.footerHtml) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public InstrumentSetupInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case 100:
                                this.instrumentFamily = value;
                                this.hasInstrumentFamily = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.supported = input.readBool();
                        this.hasSupported = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.addressChallenge == null) {
                            this.addressChallenge = new AddressChallenge();
                        }
                        input.readMessage(this.addressChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.balance == null) {
                            this.balance = new Money();
                        }
                        input.readMessage(this.balance);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        int i = this.footerHtml == null ? 0 : this.footerHtml.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.footerHtml, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.footerHtml = newArray;
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
