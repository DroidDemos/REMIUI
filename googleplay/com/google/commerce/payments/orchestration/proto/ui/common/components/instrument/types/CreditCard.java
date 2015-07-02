package com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.components.AddressFormOuterClass.AddressForm;
import com.google.commerce.payments.orchestration.proto.ui.common.components.AddressFormOuterClass.AddressFormValue;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageSetOuterClass.LegalMessageSet;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.ImageWithCaptionOuterClass.ImageWithCaption;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface CreditCard {

    public static final class BinRange extends MessageNano {
        private static volatile BinRange[] _emptyArray;
        public int cardNumberLength;
        public int[] digitGrouping;
        public String end;
        public String errorMessage;
        public String start;

        public static BinRange[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BinRange[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BinRange() {
            clear();
        }

        public BinRange clear() {
            this.start = "";
            this.end = "";
            this.cardNumberLength = 0;
            this.digitGrouping = WireFormatNano.EMPTY_INT_ARRAY;
            this.errorMessage = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.start.equals("")) {
                output.writeString(1, this.start);
            }
            if (!this.end.equals("")) {
                output.writeString(2, this.end);
            }
            if (this.cardNumberLength != 0) {
                output.writeInt32(3, this.cardNumberLength);
            }
            if (this.digitGrouping != null && this.digitGrouping.length > 0) {
                for (int writeInt32 : this.digitGrouping) {
                    output.writeInt32(4, writeInt32);
                }
            }
            if (!this.errorMessage.equals("")) {
                output.writeString(5, this.errorMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.start.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.start);
            }
            if (!this.end.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.end);
            }
            if (this.cardNumberLength != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.cardNumberLength);
            }
            if (this.digitGrouping != null && this.digitGrouping.length > 0) {
                int dataSize = 0;
                for (int element : this.digitGrouping) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.digitGrouping.length * 1);
            }
            if (this.errorMessage.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(5, this.errorMessage);
        }

        public BinRange mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.start = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.end = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.cardNumberLength = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 32);
                        i = this.digitGrouping == null ? 0 : this.digitGrouping.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.digitGrouping, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.digitGrouping = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.digitGrouping == null ? 0 : this.digitGrouping.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.digitGrouping, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.digitGrouping = newArray;
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.errorMessage = input.readString();
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

    public static final class CardType extends MessageNano {
        private static volatile CardType[] _emptyArray;
        public BinRange[] binRange;
        public int cvcLength;
        public ImageWithCaption icon;
        public byte[] typeToken;

        public static CardType[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CardType[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CardType() {
            clear();
        }

        public CardType clear() {
            this.binRange = BinRange.emptyArray();
            this.icon = null;
            this.cvcLength = 0;
            this.typeToken = WireFormatNano.EMPTY_BYTES;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.binRange != null && this.binRange.length > 0) {
                for (BinRange element : this.binRange) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.cvcLength != 0) {
                output.writeInt32(3, this.cvcLength);
            }
            if (!Arrays.equals(this.typeToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.typeToken);
            }
            if (this.icon != null) {
                output.writeMessage(9, this.icon);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.binRange != null && this.binRange.length > 0) {
                for (BinRange element : this.binRange) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.cvcLength != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.cvcLength);
            }
            if (!Arrays.equals(this.typeToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(4, this.typeToken);
            }
            if (this.icon != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(9, this.icon);
            }
            return size;
        }

        public CardType mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.binRange == null) {
                            i = 0;
                        } else {
                            i = this.binRange.length;
                        }
                        BinRange[] newArray = new BinRange[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.binRange, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new BinRange();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new BinRange();
                        input.readMessage(newArray[i]);
                        this.binRange = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.cvcLength = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.typeToken = input.readBytes();
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.icon == null) {
                            this.icon = new ImageWithCaption();
                        }
                        input.readMessage(this.icon);
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

    public static final class CreditCardExpirationDateForm extends MessageNano {
        public String cardLabel;
        public int cvcLength;
        public ImageWithCaption icon;
        public String id;
        public int maxMonth;
        public int maxYear;
        public int minMonth;
        public int minYear;

        public CreditCardExpirationDateForm() {
            clear();
        }

        public CreditCardExpirationDateForm clear() {
            this.id = "";
            this.icon = null;
            this.cardLabel = "";
            this.cvcLength = 0;
            this.minMonth = 0;
            this.minYear = 0;
            this.maxMonth = 0;
            this.maxYear = 0;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.cardLabel.equals("")) {
                output.writeString(2, this.cardLabel);
            }
            if (this.cvcLength != 0) {
                output.writeInt32(3, this.cvcLength);
            }
            if (this.minMonth != 0) {
                output.writeInt32(4, this.minMonth);
            }
            if (this.minYear != 0) {
                output.writeInt32(5, this.minYear);
            }
            if (this.maxMonth != 0) {
                output.writeInt32(6, this.maxMonth);
            }
            if (this.maxYear != 0) {
                output.writeInt32(7, this.maxYear);
            }
            if (!this.id.equals("")) {
                output.writeString(8, this.id);
            }
            if (this.icon != null) {
                output.writeMessage(9, this.icon);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.cardLabel.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.cardLabel);
            }
            if (this.cvcLength != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.cvcLength);
            }
            if (this.minMonth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.minMonth);
            }
            if (this.minYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.minYear);
            }
            if (this.maxMonth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.maxMonth);
            }
            if (this.maxYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.maxYear);
            }
            if (!this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.id);
            }
            if (this.icon != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(9, this.icon);
            }
            return size;
        }

        public CreditCardExpirationDateForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.cardLabel = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.cvcLength = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.minMonth = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.minYear = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.maxMonth = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.maxYear = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.id = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.icon == null) {
                            this.icon = new ImageWithCaption();
                        }
                        input.readMessage(this.icon);
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

    public static final class CreditCardExpirationDateFormValue extends MessageNano {
        public String cvc;
        public int newMonth;
        public int newYear;

        public CreditCardExpirationDateFormValue() {
            clear();
        }

        public CreditCardExpirationDateFormValue clear() {
            this.newMonth = 0;
            this.newYear = 0;
            this.cvc = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.newMonth != 0) {
                output.writeInt32(1, this.newMonth);
            }
            if (this.newYear != 0) {
                output.writeInt32(2, this.newYear);
            }
            if (!this.cvc.equals("")) {
                output.writeString(3, this.cvc);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.newMonth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.newMonth);
            }
            if (this.newYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.newYear);
            }
            if (this.cvc.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.cvc);
        }

        public CreditCardExpirationDateFormValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.newMonth = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.newYear = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.cvc = input.readString();
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

    public static final class CreditCardForm extends MessageNano {
        public boolean allowCameraInput;
        public CardType[] allowedCardType;
        public int[] allowedType;
        public AddressForm billingAddress;
        public String id;
        public CreditCardFormValue initialValue;
        public BinRange[] invalidBin;
        public LegalMessageSet legalMessages;
        public int maxExpirationMonth;
        public int maxExpirationYear;
        public int minExpirationMonth;
        public int minExpirationYear;
        public String title;

        public CreditCardForm() {
            clear();
        }

        public CreditCardForm clear() {
            this.id = "";
            this.title = "";
            this.allowedCardType = CardType.emptyArray();
            this.invalidBin = BinRange.emptyArray();
            this.allowedType = WireFormatNano.EMPTY_INT_ARRAY;
            this.billingAddress = null;
            this.initialValue = null;
            this.legalMessages = null;
            this.minExpirationMonth = 0;
            this.minExpirationYear = 0;
            this.maxExpirationMonth = 0;
            this.maxExpirationYear = 0;
            this.allowCameraInput = true;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.allowedType != null && this.allowedType.length > 0) {
                for (int writeInt32 : this.allowedType) {
                    output.writeInt32(1, writeInt32);
                }
            }
            if (this.billingAddress != null) {
                output.writeMessage(2, this.billingAddress);
            }
            if (this.initialValue != null) {
                output.writeMessage(3, this.initialValue);
            }
            if (this.legalMessages != null) {
                output.writeMessage(4, this.legalMessages);
            }
            if (!this.id.equals("")) {
                output.writeString(5, this.id);
            }
            if (this.allowedCardType != null && this.allowedCardType.length > 0) {
                for (CardType element : this.allowedCardType) {
                    if (element != null) {
                        output.writeMessage(6, element);
                    }
                }
            }
            if (this.invalidBin != null && this.invalidBin.length > 0) {
                for (BinRange element2 : this.invalidBin) {
                    if (element2 != null) {
                        output.writeMessage(7, element2);
                    }
                }
            }
            if (this.minExpirationMonth != 0) {
                output.writeInt32(8, this.minExpirationMonth);
            }
            if (this.minExpirationYear != 0) {
                output.writeInt32(9, this.minExpirationYear);
            }
            if (this.maxExpirationMonth != 0) {
                output.writeInt32(10, this.maxExpirationMonth);
            }
            if (this.maxExpirationYear != 0) {
                output.writeInt32(11, this.maxExpirationYear);
            }
            if (!this.allowCameraInput) {
                output.writeBool(12, this.allowCameraInput);
            }
            if (!this.title.equals("")) {
                output.writeString(13, this.title);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.allowedType != null && this.allowedType.length > 0) {
                int dataSize = 0;
                for (int element : this.allowedType) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
                }
                size = (size + dataSize) + (this.allowedType.length * 1);
            }
            if (this.billingAddress != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.billingAddress);
            }
            if (this.initialValue != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.initialValue);
            }
            if (this.legalMessages != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.legalMessages);
            }
            if (!this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.id);
            }
            if (this.allowedCardType != null && this.allowedCardType.length > 0) {
                for (CardType element2 : this.allowedCardType) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element2);
                    }
                }
            }
            if (this.invalidBin != null && this.invalidBin.length > 0) {
                for (BinRange element3 : this.invalidBin) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element3);
                    }
                }
            }
            if (this.minExpirationMonth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.minExpirationMonth);
            }
            if (this.minExpirationYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.minExpirationYear);
            }
            if (this.maxExpirationMonth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.maxExpirationMonth);
            }
            if (this.maxExpirationYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(11, this.maxExpirationYear);
            }
            if (!this.allowCameraInput) {
                size += CodedOutputByteBufferNano.computeBoolSize(12, this.allowCameraInput);
            }
            if (this.title.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(13, this.title);
        }

        public CreditCardForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int i;
                int value;
                int[] newArray;
                int arrayLength;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                        int[] validValues = new int[length];
                        i = 0;
                        int validCount = 0;
                        while (i < length) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                    validCount2 = validCount + 1;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i++;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            i = this.allowedType == null ? 0 : this.allowedType.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.allowedType, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.allowedType = newArray;
                                break;
                            }
                            this.allowedType = validValues;
                            break;
                        }
                        continue;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.allowedType == null) {
                                i = 0;
                            } else {
                                i = this.allowedType.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.allowedType, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                    case R.styleable.WalletImFormEditText_required /*4*/:
                                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.allowedType = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.billingAddress == null) {
                            this.billingAddress = new AddressForm();
                        }
                        input.readMessage(this.billingAddress);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.initialValue == null) {
                            this.initialValue = new CreditCardFormValue();
                        }
                        input.readMessage(this.initialValue);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.legalMessages == null) {
                            this.legalMessages = new LegalMessageSet();
                        }
                        input.readMessage(this.legalMessages);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.id = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.allowedCardType == null) {
                            i = 0;
                        } else {
                            i = this.allowedCardType.length;
                        }
                        CardType[] newArray2 = new CardType[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.allowedCardType, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new CardType();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new CardType();
                        input.readMessage(newArray2[i]);
                        this.allowedCardType = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.invalidBin == null) {
                            i = 0;
                        } else {
                            i = this.invalidBin.length;
                        }
                        BinRange[] newArray3 = new BinRange[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.invalidBin, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new BinRange();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new BinRange();
                        input.readMessage(newArray3[i]);
                        this.invalidBin = newArray3;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.minExpirationMonth = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.minExpirationYear = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.maxExpirationMonth = input.readInt32();
                        continue;
                    case 88:
                        this.maxExpirationYear = input.readInt32();
                        continue;
                    case 96:
                        this.allowCameraInput = input.readBool();
                        continue;
                    case 106:
                        this.title = input.readString();
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

    public static final class CreditCardFormValue extends MessageNano {
        public AddressFormValue billingAddress;
        public String cardNumber;
        public String cardholderName;
        public String cvc;
        public int expirationMonth;
        public int expirationYear;
        public String lastFourDigits;
        public String legalDocData;
        public int type;
        public byte[] typeToken;

        public CreditCardFormValue() {
            clear();
        }

        public CreditCardFormValue clear() {
            this.cardholderName = "";
            this.cardNumber = "";
            this.cvc = "";
            this.expirationMonth = 0;
            this.expirationYear = 0;
            this.billingAddress = null;
            this.lastFourDigits = "";
            this.type = 0;
            this.typeToken = WireFormatNano.EMPTY_BYTES;
            this.legalDocData = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.cardNumber.equals("")) {
                output.writeString(1, this.cardNumber);
            }
            if (!this.cvc.equals("")) {
                output.writeString(2, this.cvc);
            }
            if (this.expirationMonth != 0) {
                output.writeInt32(3, this.expirationMonth);
            }
            if (this.expirationYear != 0) {
                output.writeInt32(4, this.expirationYear);
            }
            if (!this.lastFourDigits.equals("")) {
                output.writeString(6, this.lastFourDigits);
            }
            if (this.type != 0) {
                output.writeInt32(7, this.type);
            }
            if (!this.cardholderName.equals("")) {
                output.writeString(9, this.cardholderName);
            }
            if (this.billingAddress != null) {
                output.writeMessage(10, this.billingAddress);
            }
            if (!Arrays.equals(this.typeToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(11, this.typeToken);
            }
            if (!this.legalDocData.equals("")) {
                output.writeString(12, this.legalDocData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.cardNumber.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.cardNumber);
            }
            if (!this.cvc.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.cvc);
            }
            if (this.expirationMonth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.expirationMonth);
            }
            if (this.expirationYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.expirationYear);
            }
            if (!this.lastFourDigits.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.lastFourDigits);
            }
            if (this.type != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.type);
            }
            if (!this.cardholderName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.cardholderName);
            }
            if (this.billingAddress != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.billingAddress);
            }
            if (!Arrays.equals(this.typeToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(11, this.typeToken);
            }
            if (this.legalDocData.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(12, this.legalDocData);
        }

        public CreditCardFormValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.cardNumber = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.cvc = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.expirationMonth = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.expirationYear = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.lastFourDigits = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                this.type = value;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.cardholderName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.billingAddress == null) {
                            this.billingAddress = new AddressFormValue();
                        }
                        input.readMessage(this.billingAddress);
                        continue;
                    case 90:
                        this.typeToken = input.readBytes();
                        continue;
                    case 98:
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
