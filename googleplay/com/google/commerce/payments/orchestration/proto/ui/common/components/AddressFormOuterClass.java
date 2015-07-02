package com.google.commerce.payments.orchestration.proto.ui.common.components;

import com.google.android.wallet.instrumentmanager.R;
import com.google.location.country.Postaladdress.PostalAddress;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface AddressFormOuterClass {

    public static final class AddressForm extends MessageNano {
        public String[] allowedCountryCode;
        public int[] hiddenField;
        public String hideFormFieldsToggleLabel;
        public String id;
        public String initialCountryI18NDataJson;
        public AddressFormValue initialValue;
        public boolean phoneNumberForm;
        public String[] postalCodeOnlyCountryCode;
        public int[] readOnlyField;
        public String recipientLabel;
        public String title;

        public AddressForm() {
            clear();
        }

        public AddressForm clear() {
            this.id = "";
            this.title = "";
            this.initialValue = null;
            this.initialCountryI18NDataJson = "";
            this.hideFormFieldsToggleLabel = "";
            this.allowedCountryCode = WireFormatNano.EMPTY_STRING_ARRAY;
            this.postalCodeOnlyCountryCode = WireFormatNano.EMPTY_STRING_ARRAY;
            this.phoneNumberForm = false;
            this.recipientLabel = "";
            this.readOnlyField = WireFormatNano.EMPTY_INT_ARRAY;
            this.hiddenField = WireFormatNano.EMPTY_INT_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.allowedCountryCode != null && this.allowedCountryCode.length > 0) {
                for (String element : this.allowedCountryCode) {
                    if (element != null) {
                        output.writeString(4, element);
                    }
                }
            }
            if (this.phoneNumberForm) {
                output.writeBool(5, this.phoneNumberForm);
            }
            if (!this.id.equals("")) {
                output.writeString(6, this.id);
            }
            if (this.initialValue != null) {
                output.writeMessage(7, this.initialValue);
            }
            if (this.postalCodeOnlyCountryCode != null && this.postalCodeOnlyCountryCode.length > 0) {
                for (String element2 : this.postalCodeOnlyCountryCode) {
                    if (element2 != null) {
                        output.writeString(9, element2);
                    }
                }
            }
            if (!this.recipientLabel.equals("")) {
                output.writeString(10, this.recipientLabel);
            }
            if (!this.hideFormFieldsToggleLabel.equals("")) {
                output.writeString(11, this.hideFormFieldsToggleLabel);
            }
            if (this.readOnlyField != null && this.readOnlyField.length > 0) {
                for (int writeInt32 : this.readOnlyField) {
                    output.writeInt32(12, writeInt32);
                }
            }
            if (!this.title.equals("")) {
                output.writeString(13, this.title);
            }
            if (this.hiddenField != null && this.hiddenField.length > 0) {
                for (int writeInt322 : this.hiddenField) {
                    output.writeInt32(14, writeInt322);
                }
            }
            if (!this.initialCountryI18NDataJson.equals("")) {
                output.writeString(15, this.initialCountryI18NDataJson);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.allowedCountryCode != null && this.allowedCountryCode.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.allowedCountryCode) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.phoneNumberForm) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.phoneNumberForm);
            }
            if (!this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.id);
            }
            if (this.initialValue != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.initialValue);
            }
            if (this.postalCodeOnlyCountryCode != null && this.postalCodeOnlyCountryCode.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.postalCodeOnlyCountryCode) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (!this.recipientLabel.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.recipientLabel);
            }
            if (!this.hideFormFieldsToggleLabel.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.hideFormFieldsToggleLabel);
            }
            if (this.readOnlyField != null && this.readOnlyField.length > 0) {
                dataSize = 0;
                for (int element3 : this.readOnlyField) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
                }
                size = (size + dataSize) + (this.readOnlyField.length * 1);
            }
            if (!this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.title);
            }
            if (this.hiddenField != null && this.hiddenField.length > 0) {
                dataSize = 0;
                for (int element32 : this.hiddenField) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element32);
                }
                size = (size + dataSize) + (this.hiddenField.length * 1);
            }
            if (this.initialCountryI18NDataJson.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(15, this.initialCountryI18NDataJson);
        }

        public AddressForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                int length;
                int[] validValues;
                int validCount;
                int value;
                int validCount2;
                int[] newArray2;
                int limit;
                int startPos;
                int i2;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        i = this.allowedCountryCode == null ? 0 : this.allowedCountryCode.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.allowedCountryCode, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.allowedCountryCode = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.phoneNumberForm = input.readBool();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.id = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.initialValue == null) {
                            this.initialValue = new AddressFormValue();
                        }
                        input.readMessage(this.initialValue);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 74);
                        i = this.postalCodeOnlyCountryCode == null ? 0 : this.postalCodeOnlyCountryCode.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.postalCodeOnlyCountryCode, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.postalCodeOnlyCountryCode = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.recipientLabel = input.readString();
                        continue;
                    case 90:
                        this.hideFormFieldsToggleLabel = input.readString();
                        continue;
                    case 96:
                        length = WireFormatNano.getRepeatedFieldArrayLength(input, 96);
                        validValues = new int[length];
                        i = 0;
                        validCount = 0;
                        while (i < length) {
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                case R.styleable.MapAttrs_uiZoomControls /*11*/:
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
                            i = this.readOnlyField == null ? 0 : this.readOnlyField.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray2 = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.readOnlyField, 0, newArray2, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray2, i, validCount);
                                this.readOnlyField = newArray2;
                                break;
                            }
                            this.readOnlyField = validValues;
                            break;
                        }
                        continue;
                    case 98:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.readOnlyField == null) {
                                i = 0;
                            } else {
                                i = this.readOnlyField.length;
                            }
                            newArray2 = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.readOnlyField, 0, newArray2, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                    case R.styleable.WalletImFormEditText_required /*4*/:
                                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                    case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                        i2 = i + 1;
                                        newArray2[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.readOnlyField = newArray2;
                        }
                        input.popLimit(limit);
                        continue;
                    case 106:
                        this.title = input.readString();
                        continue;
                    case 112:
                        length = WireFormatNano.getRepeatedFieldArrayLength(input, 112);
                        validValues = new int[length];
                        i = 0;
                        validCount = 0;
                        while (i < length) {
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                case R.styleable.MapAttrs_uiZoomControls /*11*/:
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
                            i = this.hiddenField == null ? 0 : this.hiddenField.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray2 = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.hiddenField, 0, newArray2, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray2, i, validCount);
                                this.hiddenField = newArray2;
                                break;
                            }
                            this.hiddenField = validValues;
                            break;
                        }
                        continue;
                    case 114:
                        limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.hiddenField == null) {
                                i = 0;
                            } else {
                                i = this.hiddenField.length;
                            }
                            newArray2 = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.hiddenField, 0, newArray2, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                    case R.styleable.WalletImFormEditText_required /*4*/:
                                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                    case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                        i2 = i + 1;
                                        newArray2[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.hiddenField = newArray2;
                        }
                        input.popLimit(limit);
                        continue;
                    case 122:
                        this.initialCountryI18NDataJson = input.readString();
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

    public static final class AddressFormValue extends MessageNano {
        public PostalAddress address;
        public boolean isHidden;
        public String phoneNumber;

        public AddressFormValue() {
            clear();
        }

        public AddressFormValue clear() {
            this.address = null;
            this.phoneNumber = "";
            this.isHidden = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.address != null) {
                output.writeMessage(1, this.address);
            }
            if (!this.phoneNumber.equals("")) {
                output.writeString(2, this.phoneNumber);
            }
            if (this.isHidden) {
                output.writeBool(3, this.isHidden);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.address != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.address);
            }
            if (!this.phoneNumber.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.phoneNumber);
            }
            if (this.isHidden) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.isHidden);
            }
            return size;
        }

        public AddressFormValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.address == null) {
                            this.address = new PostalAddress();
                        }
                        input.readMessage(this.address);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.phoneNumber = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.isHidden = input.readBool();
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

    public static final class CountrySelector extends MessageNano {
        public String[] allowedCountryCode;
        public String initialCountryCode;

        public CountrySelector() {
            clear();
        }

        public CountrySelector clear() {
            this.allowedCountryCode = WireFormatNano.EMPTY_STRING_ARRAY;
            this.initialCountryCode = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.allowedCountryCode != null && this.allowedCountryCode.length > 0) {
                for (String element : this.allowedCountryCode) {
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            if (!this.initialCountryCode.equals("")) {
                output.writeString(3, this.initialCountryCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.allowedCountryCode != null && this.allowedCountryCode.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.allowedCountryCode) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.initialCountryCode.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.initialCountryCode);
        }

        public CountrySelector mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        int i = this.allowedCountryCode == null ? 0 : this.allowedCountryCode.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.allowedCountryCode, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.allowedCountryCode = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.initialCountryCode = input.readString();
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
