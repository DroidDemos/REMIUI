package com.google.android.finsky.protos;

import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.SingleFopPaymentsIntegrator.SingleFopPaymentsIntegratorContext;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface ChallengeProto {

    public static final class AddressChallenge extends MessageNano {
        public Address address;
        public FormCheckbox[] checkbox;
        public String descriptionHtml;
        public String errorHtml;
        public InputValidationError[] errorInputField;
        public boolean hasDescriptionHtml;
        public boolean hasErrorHtml;
        public boolean hasResponseAddressParam;
        public boolean hasResponseCheckboxesParam;
        public boolean hasTitle;
        public int[] requiredField;
        public String responseAddressParam;
        public String responseCheckboxesParam;
        public Country[] supportedCountry;
        public String title;

        public AddressChallenge() {
            clear();
        }

        public AddressChallenge clear() {
            this.responseAddressParam = "";
            this.hasResponseAddressParam = false;
            this.responseCheckboxesParam = "";
            this.hasResponseCheckboxesParam = false;
            this.title = "";
            this.hasTitle = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.checkbox = FormCheckbox.emptyArray();
            this.address = null;
            this.errorInputField = InputValidationError.emptyArray();
            this.errorHtml = "";
            this.hasErrorHtml = false;
            this.requiredField = WireFormatNano.EMPTY_INT_ARRAY;
            this.supportedCountry = Country.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasResponseAddressParam || !this.responseAddressParam.equals("")) {
                output.writeString(1, this.responseAddressParam);
            }
            if (this.hasResponseCheckboxesParam || !this.responseCheckboxesParam.equals("")) {
                output.writeString(2, this.responseCheckboxesParam);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(3, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(4, this.descriptionHtml);
            }
            if (this.checkbox != null && this.checkbox.length > 0) {
                for (FormCheckbox element : this.checkbox) {
                    if (element != null) {
                        output.writeMessage(5, element);
                    }
                }
            }
            if (this.address != null) {
                output.writeMessage(6, this.address);
            }
            if (this.errorInputField != null && this.errorInputField.length > 0) {
                for (InputValidationError element2 : this.errorInputField) {
                    if (element2 != null) {
                        output.writeMessage(7, element2);
                    }
                }
            }
            if (this.hasErrorHtml || !this.errorHtml.equals("")) {
                output.writeString(8, this.errorHtml);
            }
            if (this.requiredField != null && this.requiredField.length > 0) {
                for (int writeInt32 : this.requiredField) {
                    output.writeInt32(9, writeInt32);
                }
            }
            if (this.supportedCountry != null && this.supportedCountry.length > 0) {
                for (Country element3 : this.supportedCountry) {
                    if (element3 != null) {
                        output.writeMessage(10, element3);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasResponseAddressParam || !this.responseAddressParam.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.responseAddressParam);
            }
            if (this.hasResponseCheckboxesParam || !this.responseCheckboxesParam.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.responseCheckboxesParam);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.descriptionHtml);
            }
            if (this.checkbox != null && this.checkbox.length > 0) {
                for (FormCheckbox element : this.checkbox) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element);
                    }
                }
            }
            if (this.address != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.address);
            }
            if (this.errorInputField != null && this.errorInputField.length > 0) {
                for (InputValidationError element2 : this.errorInputField) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element2);
                    }
                }
            }
            if (this.hasErrorHtml || !this.errorHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.errorHtml);
            }
            if (this.requiredField != null && this.requiredField.length > 0) {
                int dataSize = 0;
                for (int element3 : this.requiredField) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
                }
                size = (size + dataSize) + (this.requiredField.length * 1);
            }
            if (this.supportedCountry != null && this.supportedCountry.length > 0) {
                for (Country element4 : this.supportedCountry) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(10, element4);
                    }
                }
            }
            return size;
        }

        public AddressChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int value;
                int[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.responseAddressParam = input.readString();
                        this.hasResponseAddressParam = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.responseCheckboxesParam = input.readString();
                        this.hasResponseCheckboxesParam = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.checkbox == null) {
                            i = 0;
                        } else {
                            i = this.checkbox.length;
                        }
                        FormCheckbox[] newArray2 = new FormCheckbox[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.checkbox, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new FormCheckbox();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new FormCheckbox();
                        input.readMessage(newArray2[i]);
                        this.checkbox = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.address == null) {
                            this.address = new Address();
                        }
                        input.readMessage(this.address);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.errorInputField == null) {
                            i = 0;
                        } else {
                            i = this.errorInputField.length;
                        }
                        InputValidationError[] newArray3 = new InputValidationError[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.errorInputField, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new InputValidationError();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new InputValidationError();
                        input.readMessage(newArray3[i]);
                        this.errorInputField = newArray3;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.errorHtml = input.readString();
                        this.hasErrorHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 72);
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
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
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
                            i = this.requiredField == null ? 0 : this.requiredField.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.requiredField, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.requiredField = newArray;
                                break;
                            }
                            this.requiredField = validValues;
                            break;
                        }
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
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
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                                case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                                case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                                case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                                case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                                case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                                case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.requiredField == null) {
                                i = 0;
                            } else {
                                i = this.requiredField.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.requiredField, 0, newArray, 0, i);
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
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                    case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                    case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                    case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                                    case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                                    case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                                    case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                                    case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.requiredField = newArray;
                        }
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 82);
                        if (this.supportedCountry == null) {
                            i = 0;
                        } else {
                            i = this.supportedCountry.length;
                        }
                        Country[] newArray4 = new Country[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.supportedCountry, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new Country();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = new Country();
                        input.readMessage(newArray4[i]);
                        this.supportedCountry = newArray4;
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

    public static final class AgeChallenge extends MessageNano {
        public FormTextField birthdate;
        public FormRadioSelector carrierSelection;
        public FormCheckbox citizenship;
        public String descriptionHtml;
        public FormTextField fullName;
        public FormRadioSelector genderSelection;
        public boolean hasDescriptionHtml;
        public boolean hasSubmitFooterHtml;
        public boolean hasTitle;
        public FormTextField phoneNumber;
        public FormButton submitButton;
        public String submitFooterHtml;
        public String title;

        public AgeChallenge() {
            clear();
        }

        public AgeChallenge clear() {
            this.title = "";
            this.hasTitle = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.fullName = null;
            this.birthdate = null;
            this.phoneNumber = null;
            this.genderSelection = null;
            this.carrierSelection = null;
            this.citizenship = null;
            this.submitFooterHtml = "";
            this.hasSubmitFooterHtml = false;
            this.submitButton = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(2, this.descriptionHtml);
            }
            if (this.fullName != null) {
                output.writeMessage(3, this.fullName);
            }
            if (this.birthdate != null) {
                output.writeMessage(4, this.birthdate);
            }
            if (this.phoneNumber != null) {
                output.writeMessage(5, this.phoneNumber);
            }
            if (this.genderSelection != null) {
                output.writeMessage(6, this.genderSelection);
            }
            if (this.carrierSelection != null) {
                output.writeMessage(7, this.carrierSelection);
            }
            if (this.citizenship != null) {
                output.writeMessage(8, this.citizenship);
            }
            if (this.hasSubmitFooterHtml || !this.submitFooterHtml.equals("")) {
                output.writeString(9, this.submitFooterHtml);
            }
            if (this.submitButton != null) {
                output.writeMessage(10, this.submitButton);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.descriptionHtml);
            }
            if (this.fullName != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.fullName);
            }
            if (this.birthdate != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.birthdate);
            }
            if (this.phoneNumber != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.phoneNumber);
            }
            if (this.genderSelection != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.genderSelection);
            }
            if (this.carrierSelection != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.carrierSelection);
            }
            if (this.citizenship != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.citizenship);
            }
            if (this.hasSubmitFooterHtml || !this.submitFooterHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.submitFooterHtml);
            }
            if (this.submitButton != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(10, this.submitButton);
            }
            return size;
        }

        public AgeChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.fullName == null) {
                            this.fullName = new FormTextField();
                        }
                        input.readMessage(this.fullName);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.birthdate == null) {
                            this.birthdate = new FormTextField();
                        }
                        input.readMessage(this.birthdate);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.phoneNumber == null) {
                            this.phoneNumber = new FormTextField();
                        }
                        input.readMessage(this.phoneNumber);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.genderSelection == null) {
                            this.genderSelection = new FormRadioSelector();
                        }
                        input.readMessage(this.genderSelection);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.carrierSelection == null) {
                            this.carrierSelection = new FormRadioSelector();
                        }
                        input.readMessage(this.carrierSelection);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.citizenship == null) {
                            this.citizenship = new FormCheckbox();
                        }
                        input.readMessage(this.citizenship);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.submitFooterHtml = input.readString();
                        this.hasSubmitFooterHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.submitButton == null) {
                            this.submitButton = new FormButton();
                        }
                        input.readMessage(this.submitButton);
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

    public static final class AuthenticationChallenge extends MessageNano {
        public int authenticationType;
        public String documentTitle;
        public String formattedPrice;
        public String gaiaDescriptionTextHtml;
        public String gaiaFooterTextHtml;
        public String gaiaHeaderText;
        public FormCheckbox gaiaOptOutCheckbox;
        public String gaiaOptOutDescriptionTextHtml;
        public boolean hasAuthenticationType;
        public boolean hasDocumentTitle;
        public boolean hasFormattedPrice;
        public boolean hasGaiaDescriptionTextHtml;
        public boolean hasGaiaFooterTextHtml;
        public boolean hasGaiaHeaderText;
        public boolean hasGaiaOptOutDescriptionTextHtml;
        public boolean hasInstrumentDisplayTitle;
        public boolean hasResponseAuthenticationTypeParam;
        public boolean hasResponseRetryCountParam;
        public String instrumentDisplayTitle;
        public String responseAuthenticationTypeParam;
        public String responseRetryCountParam;

        public AuthenticationChallenge() {
            clear();
        }

        public AuthenticationChallenge clear() {
            this.authenticationType = 1;
            this.hasAuthenticationType = false;
            this.responseAuthenticationTypeParam = "";
            this.hasResponseAuthenticationTypeParam = false;
            this.responseRetryCountParam = "";
            this.hasResponseRetryCountParam = false;
            this.gaiaHeaderText = "";
            this.hasGaiaHeaderText = false;
            this.gaiaDescriptionTextHtml = "";
            this.hasGaiaDescriptionTextHtml = false;
            this.gaiaFooterTextHtml = "";
            this.hasGaiaFooterTextHtml = false;
            this.gaiaOptOutCheckbox = null;
            this.gaiaOptOutDescriptionTextHtml = "";
            this.hasGaiaOptOutDescriptionTextHtml = false;
            this.documentTitle = "";
            this.hasDocumentTitle = false;
            this.formattedPrice = "";
            this.hasFormattedPrice = false;
            this.instrumentDisplayTitle = "";
            this.hasInstrumentDisplayTitle = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.authenticationType != 1 || this.hasAuthenticationType) {
                output.writeInt32(1, this.authenticationType);
            }
            if (this.hasResponseAuthenticationTypeParam || !this.responseAuthenticationTypeParam.equals("")) {
                output.writeString(2, this.responseAuthenticationTypeParam);
            }
            if (this.hasResponseRetryCountParam || !this.responseRetryCountParam.equals("")) {
                output.writeString(3, this.responseRetryCountParam);
            }
            if (this.hasGaiaHeaderText || !this.gaiaHeaderText.equals("")) {
                output.writeString(6, this.gaiaHeaderText);
            }
            if (this.hasGaiaDescriptionTextHtml || !this.gaiaDescriptionTextHtml.equals("")) {
                output.writeString(7, this.gaiaDescriptionTextHtml);
            }
            if (this.hasGaiaFooterTextHtml || !this.gaiaFooterTextHtml.equals("")) {
                output.writeString(8, this.gaiaFooterTextHtml);
            }
            if (this.gaiaOptOutCheckbox != null) {
                output.writeMessage(9, this.gaiaOptOutCheckbox);
            }
            if (this.hasGaiaOptOutDescriptionTextHtml || !this.gaiaOptOutDescriptionTextHtml.equals("")) {
                output.writeString(10, this.gaiaOptOutDescriptionTextHtml);
            }
            if (this.hasDocumentTitle || !this.documentTitle.equals("")) {
                output.writeString(11, this.documentTitle);
            }
            if (this.hasFormattedPrice || !this.formattedPrice.equals("")) {
                output.writeString(12, this.formattedPrice);
            }
            if (this.hasInstrumentDisplayTitle || !this.instrumentDisplayTitle.equals("")) {
                output.writeString(13, this.instrumentDisplayTitle);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.authenticationType != 1 || this.hasAuthenticationType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.authenticationType);
            }
            if (this.hasResponseAuthenticationTypeParam || !this.responseAuthenticationTypeParam.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.responseAuthenticationTypeParam);
            }
            if (this.hasResponseRetryCountParam || !this.responseRetryCountParam.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.responseRetryCountParam);
            }
            if (this.hasGaiaHeaderText || !this.gaiaHeaderText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.gaiaHeaderText);
            }
            if (this.hasGaiaDescriptionTextHtml || !this.gaiaDescriptionTextHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.gaiaDescriptionTextHtml);
            }
            if (this.hasGaiaFooterTextHtml || !this.gaiaFooterTextHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.gaiaFooterTextHtml);
            }
            if (this.gaiaOptOutCheckbox != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.gaiaOptOutCheckbox);
            }
            if (this.hasGaiaOptOutDescriptionTextHtml || !this.gaiaOptOutDescriptionTextHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.gaiaOptOutDescriptionTextHtml);
            }
            if (this.hasDocumentTitle || !this.documentTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.documentTitle);
            }
            if (this.hasFormattedPrice || !this.formattedPrice.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.formattedPrice);
            }
            if (this.hasInstrumentDisplayTitle || !this.instrumentDisplayTitle.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(13, this.instrumentDisplayTitle);
            }
            return size;
        }

        public AuthenticationChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.authenticationType = value;
                                this.hasAuthenticationType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.responseAuthenticationTypeParam = input.readString();
                        this.hasResponseAuthenticationTypeParam = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.responseRetryCountParam = input.readString();
                        this.hasResponseRetryCountParam = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.gaiaHeaderText = input.readString();
                        this.hasGaiaHeaderText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.gaiaDescriptionTextHtml = input.readString();
                        this.hasGaiaDescriptionTextHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.gaiaFooterTextHtml = input.readString();
                        this.hasGaiaFooterTextHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.gaiaOptOutCheckbox == null) {
                            this.gaiaOptOutCheckbox = new FormCheckbox();
                        }
                        input.readMessage(this.gaiaOptOutCheckbox);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.gaiaOptOutDescriptionTextHtml = input.readString();
                        this.hasGaiaOptOutDescriptionTextHtml = true;
                        continue;
                    case 90:
                        this.documentTitle = input.readString();
                        this.hasDocumentTitle = true;
                        continue;
                    case 98:
                        this.formattedPrice = input.readString();
                        this.hasFormattedPrice = true;
                        continue;
                    case 106:
                        this.instrumentDisplayTitle = input.readString();
                        this.hasInstrumentDisplayTitle = true;
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

    public static final class Challenge extends MessageNano {
        private static volatile Challenge[] _emptyArray;
        public AddressChallenge addressChallenge;
        public AgeChallenge ageChallenge;
        public AuthenticationChallenge authenticationChallenge;
        public CvnChallenge cvnChallenge;
        public ChallengeError error;
        public PaymentsUpdateChallenge paymentsUpdateChallenge;
        public RapChallenge rapChallenge;
        public SmsCodeChallenge smsCodeChallenge;
        public ChallengeSuccess success;
        public WebViewChallenge webViewChallenge;

        public static Challenge[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Challenge[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Challenge() {
            clear();
        }

        public Challenge clear() {
            this.addressChallenge = null;
            this.authenticationChallenge = null;
            this.webViewChallenge = null;
            this.ageChallenge = null;
            this.smsCodeChallenge = null;
            this.cvnChallenge = null;
            this.paymentsUpdateChallenge = null;
            this.rapChallenge = null;
            this.error = null;
            this.success = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.addressChallenge != null) {
                output.writeMessage(1, this.addressChallenge);
            }
            if (this.authenticationChallenge != null) {
                output.writeMessage(2, this.authenticationChallenge);
            }
            if (this.webViewChallenge != null) {
                output.writeMessage(3, this.webViewChallenge);
            }
            if (this.ageChallenge != null) {
                output.writeMessage(4, this.ageChallenge);
            }
            if (this.smsCodeChallenge != null) {
                output.writeMessage(5, this.smsCodeChallenge);
            }
            if (this.error != null) {
                output.writeMessage(6, this.error);
            }
            if (this.cvnChallenge != null) {
                output.writeMessage(7, this.cvnChallenge);
            }
            if (this.paymentsUpdateChallenge != null) {
                output.writeMessage(8, this.paymentsUpdateChallenge);
            }
            if (this.rapChallenge != null) {
                output.writeMessage(9, this.rapChallenge);
            }
            if (this.success != null) {
                output.writeMessage(10, this.success);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.addressChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.addressChallenge);
            }
            if (this.authenticationChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.authenticationChallenge);
            }
            if (this.webViewChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.webViewChallenge);
            }
            if (this.ageChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.ageChallenge);
            }
            if (this.smsCodeChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.smsCodeChallenge);
            }
            if (this.error != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.error);
            }
            if (this.cvnChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.cvnChallenge);
            }
            if (this.paymentsUpdateChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.paymentsUpdateChallenge);
            }
            if (this.rapChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.rapChallenge);
            }
            if (this.success != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(10, this.success);
            }
            return size;
        }

        public Challenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.addressChallenge == null) {
                            this.addressChallenge = new AddressChallenge();
                        }
                        input.readMessage(this.addressChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.authenticationChallenge == null) {
                            this.authenticationChallenge = new AuthenticationChallenge();
                        }
                        input.readMessage(this.authenticationChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.webViewChallenge == null) {
                            this.webViewChallenge = new WebViewChallenge();
                        }
                        input.readMessage(this.webViewChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.ageChallenge == null) {
                            this.ageChallenge = new AgeChallenge();
                        }
                        input.readMessage(this.ageChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.smsCodeChallenge == null) {
                            this.smsCodeChallenge = new SmsCodeChallenge();
                        }
                        input.readMessage(this.smsCodeChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.error == null) {
                            this.error = new ChallengeError();
                        }
                        input.readMessage(this.error);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.cvnChallenge == null) {
                            this.cvnChallenge = new CvnChallenge();
                        }
                        input.readMessage(this.cvnChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.paymentsUpdateChallenge == null) {
                            this.paymentsUpdateChallenge = new PaymentsUpdateChallenge();
                        }
                        input.readMessage(this.paymentsUpdateChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.rapChallenge == null) {
                            this.rapChallenge = new RapChallenge();
                        }
                        input.readMessage(this.rapChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.success == null) {
                            this.success = new ChallengeSuccess();
                        }
                        input.readMessage(this.success);
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

    public static final class ChallengeError extends MessageNano {
        public FormButton cancelButton;
        public String descriptionHtml;
        public boolean hasDescriptionHtml;
        public boolean hasTitle;
        public FormButton submitButton;
        public String title;

        public ChallengeError() {
            clear();
        }

        public ChallengeError clear() {
            this.title = "";
            this.hasTitle = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.submitButton = null;
            this.cancelButton = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(2, this.descriptionHtml);
            }
            if (this.submitButton != null) {
                output.writeMessage(3, this.submitButton);
            }
            if (this.cancelButton != null) {
                output.writeMessage(4, this.cancelButton);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.descriptionHtml);
            }
            if (this.submitButton != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.submitButton);
            }
            if (this.cancelButton != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.cancelButton);
            }
            return size;
        }

        public ChallengeError mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.submitButton == null) {
                            this.submitButton = new FormButton();
                        }
                        input.readMessage(this.submitButton);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.cancelButton == null) {
                            this.cancelButton = new FormButton();
                        }
                        input.readMessage(this.cancelButton);
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

    public static final class ChallengeSuccess extends MessageNano {
        public String description;
        public boolean hasDescription;
        public boolean hasTitle;
        public String title;

        public ChallengeSuccess() {
            clear();
        }

        public ChallengeSuccess clear() {
            this.title = "";
            this.hasTitle = false;
            this.description = "";
            this.hasDescription = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasDescription || !this.description.equals("")) {
                output.writeString(2, this.description);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasDescription || !this.description.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.description);
            }
            return size;
        }

        public ChallengeSuccess mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.description = input.readString();
                        this.hasDescription = true;
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

    public static final class Country extends MessageNano {
        private static volatile Country[] _emptyArray;
        public String displayName;
        public boolean hasDisplayName;
        public boolean hasRegionCode;
        public String regionCode;

        public static Country[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Country[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Country() {
            clear();
        }

        public Country clear() {
            this.regionCode = "";
            this.hasRegionCode = false;
            this.displayName = "";
            this.hasDisplayName = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasRegionCode || !this.regionCode.equals("")) {
                output.writeString(1, this.regionCode);
            }
            if (this.hasDisplayName || !this.displayName.equals("")) {
                output.writeString(2, this.displayName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasRegionCode || !this.regionCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.regionCode);
            }
            if (this.hasDisplayName || !this.displayName.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.displayName);
            }
            return size;
        }

        public Country mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.regionCode = input.readString();
                        this.hasRegionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.displayName = input.readString();
                        this.hasDisplayName = true;
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

    public static final class CvnChallenge extends MessageNano {
        public int creditCardType;
        public String descriptionHtml;
        public String escrowHandleParam;
        public boolean hasCreditCardType;
        public boolean hasDescriptionHtml;
        public boolean hasEscrowHandleParam;
        public boolean hasTitle;
        public String title;

        public CvnChallenge() {
            clear();
        }

        public CvnChallenge clear() {
            this.title = "";
            this.hasTitle = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.creditCardType = 0;
            this.hasCreditCardType = false;
            this.escrowHandleParam = "";
            this.hasEscrowHandleParam = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(2, this.descriptionHtml);
            }
            if (this.creditCardType != 0 || this.hasCreditCardType) {
                output.writeInt32(3, this.creditCardType);
            }
            if (this.hasEscrowHandleParam || !this.escrowHandleParam.equals("")) {
                output.writeString(4, this.escrowHandleParam);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.descriptionHtml);
            }
            if (this.creditCardType != 0 || this.hasCreditCardType) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.creditCardType);
            }
            if (this.hasEscrowHandleParam || !this.escrowHandleParam.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.escrowHandleParam);
            }
            return size;
        }

        public CvnChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                                this.creditCardType = value;
                                this.hasCreditCardType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.escrowHandleParam = input.readString();
                        this.hasEscrowHandleParam = true;
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

    public static final class FormButton extends MessageNano {
        public Challenge[] actionChallenge;
        public boolean actionFailChallenge;
        public String actionUrl;
        public boolean hasActionFailChallenge;
        public boolean hasActionUrl;
        public boolean hasLabel;
        public String label;

        public FormButton() {
            clear();
        }

        public FormButton clear() {
            this.label = "";
            this.hasLabel = false;
            this.actionUrl = "";
            this.hasActionUrl = false;
            this.actionFailChallenge = false;
            this.hasActionFailChallenge = false;
            this.actionChallenge = Challenge.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLabel || !this.label.equals("")) {
                output.writeString(1, this.label);
            }
            if (this.hasActionUrl || !this.actionUrl.equals("")) {
                output.writeString(2, this.actionUrl);
            }
            if (this.hasActionFailChallenge || this.actionFailChallenge) {
                output.writeBool(3, this.actionFailChallenge);
            }
            if (this.actionChallenge != null && this.actionChallenge.length > 0) {
                for (Challenge element : this.actionChallenge) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLabel || !this.label.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.label);
            }
            if (this.hasActionUrl || !this.actionUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.actionUrl);
            }
            if (this.hasActionFailChallenge || this.actionFailChallenge) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.actionFailChallenge);
            }
            if (this.actionChallenge != null && this.actionChallenge.length > 0) {
                for (Challenge element : this.actionChallenge) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            return size;
        }

        public FormButton mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.label = input.readString();
                        this.hasLabel = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.actionUrl = input.readString();
                        this.hasActionUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.actionFailChallenge = input.readBool();
                        this.hasActionFailChallenge = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.actionChallenge == null) {
                            i = 0;
                        } else {
                            i = this.actionChallenge.length;
                        }
                        Challenge[] newArray = new Challenge[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.actionChallenge, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Challenge();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Challenge();
                        input.readMessage(newArray[i]);
                        this.actionChallenge = newArray;
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

    public static final class FormCheckbox extends MessageNano {
        private static volatile FormCheckbox[] _emptyArray;
        public boolean checked;
        public String description;
        public boolean hasChecked;
        public boolean hasDescription;
        public boolean hasId;
        public boolean hasPostParam;
        public boolean hasRequired;
        public String id;
        public String postParam;
        public boolean required;

        public static FormCheckbox[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FormCheckbox[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FormCheckbox() {
            clear();
        }

        public FormCheckbox clear() {
            this.description = "";
            this.hasDescription = false;
            this.checked = false;
            this.hasChecked = false;
            this.required = false;
            this.hasRequired = false;
            this.id = "";
            this.hasId = false;
            this.postParam = "";
            this.hasPostParam = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDescription || !this.description.equals("")) {
                output.writeString(1, this.description);
            }
            if (this.hasChecked || this.checked) {
                output.writeBool(2, this.checked);
            }
            if (this.hasRequired || this.required) {
                output.writeBool(3, this.required);
            }
            if (this.hasId || !this.id.equals("")) {
                output.writeString(4, this.id);
            }
            if (this.hasPostParam || !this.postParam.equals("")) {
                output.writeString(5, this.postParam);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDescription || !this.description.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.description);
            }
            if (this.hasChecked || this.checked) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.checked);
            }
            if (this.hasRequired || this.required) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.required);
            }
            if (this.hasId || !this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.id);
            }
            if (this.hasPostParam || !this.postParam.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.postParam);
            }
            return size;
        }

        public FormCheckbox mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.description = input.readString();
                        this.hasDescription = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.checked = input.readBool();
                        this.hasChecked = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.required = input.readBool();
                        this.hasRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.id = input.readString();
                        this.hasId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.postParam = input.readString();
                        this.hasPostParam = true;
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

    public static final class FormRadioButton extends MessageNano {
        private static volatile FormRadioButton[] _emptyArray;
        public boolean hasLabel;
        public boolean hasSelected;
        public boolean hasValue;
        public String label;
        public boolean selected;
        public String value;

        public static FormRadioButton[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FormRadioButton[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FormRadioButton() {
            clear();
        }

        public FormRadioButton clear() {
            this.label = "";
            this.hasLabel = false;
            this.value = "";
            this.hasValue = false;
            this.selected = false;
            this.hasSelected = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLabel || !this.label.equals("")) {
                output.writeString(1, this.label);
            }
            if (this.hasValue || !this.value.equals("")) {
                output.writeString(2, this.value);
            }
            if (this.hasSelected || this.selected) {
                output.writeBool(3, this.selected);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLabel || !this.label.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.label);
            }
            if (this.hasValue || !this.value.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.value);
            }
            if (this.hasSelected || this.selected) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.selected);
            }
            return size;
        }

        public FormRadioButton mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.label = input.readString();
                        this.hasLabel = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.value = input.readString();
                        this.hasValue = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.selected = input.readBool();
                        this.hasSelected = true;
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

    public static final class FormRadioSelector extends MessageNano {
        public boolean hasPostParam;
        public String postParam;
        public FormRadioButton[] radioButton;

        public FormRadioSelector() {
            clear();
        }

        public FormRadioSelector clear() {
            this.radioButton = FormRadioButton.emptyArray();
            this.postParam = "";
            this.hasPostParam = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.radioButton != null && this.radioButton.length > 0) {
                for (FormRadioButton element : this.radioButton) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasPostParam || !this.postParam.equals("")) {
                output.writeString(2, this.postParam);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.radioButton != null && this.radioButton.length > 0) {
                for (FormRadioButton element : this.radioButton) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasPostParam || !this.postParam.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.postParam);
            }
            return size;
        }

        public FormRadioSelector mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.radioButton == null) {
                            i = 0;
                        } else {
                            i = this.radioButton.length;
                        }
                        FormRadioButton[] newArray = new FormRadioButton[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.radioButton, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FormRadioButton();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new FormRadioButton();
                        input.readMessage(newArray[i]);
                        this.radioButton = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.postParam = input.readString();
                        this.hasPostParam = true;
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

    public static final class FormTextField extends MessageNano {
        public String defaultValue;
        public String error;
        public boolean hasDefaultValue;
        public boolean hasError;
        public boolean hasHint;
        public boolean hasLabel;
        public boolean hasPostParam;
        public String hint;
        public String label;
        public String postParam;

        public FormTextField() {
            clear();
        }

        public FormTextField clear() {
            this.label = "";
            this.hasLabel = false;
            this.defaultValue = "";
            this.hasDefaultValue = false;
            this.hint = "";
            this.hasHint = false;
            this.error = "";
            this.hasError = false;
            this.postParam = "";
            this.hasPostParam = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLabel || !this.label.equals("")) {
                output.writeString(1, this.label);
            }
            if (this.hasDefaultValue || !this.defaultValue.equals("")) {
                output.writeString(2, this.defaultValue);
            }
            if (this.hasHint || !this.hint.equals("")) {
                output.writeString(3, this.hint);
            }
            if (this.hasError || !this.error.equals("")) {
                output.writeString(4, this.error);
            }
            if (this.hasPostParam || !this.postParam.equals("")) {
                output.writeString(5, this.postParam);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLabel || !this.label.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.label);
            }
            if (this.hasDefaultValue || !this.defaultValue.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.defaultValue);
            }
            if (this.hasHint || !this.hint.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.hint);
            }
            if (this.hasError || !this.error.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.error);
            }
            if (this.hasPostParam || !this.postParam.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.postParam);
            }
            return size;
        }

        public FormTextField mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.label = input.readString();
                        this.hasLabel = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.defaultValue = input.readString();
                        this.hasDefaultValue = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.hint = input.readString();
                        this.hasHint = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.error = input.readString();
                        this.hasError = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.postParam = input.readString();
                        this.hasPostParam = true;
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

    public static final class InputValidationError extends MessageNano {
        private static volatile InputValidationError[] _emptyArray;
        public String errorMessage;
        public boolean hasErrorMessage;
        public boolean hasInputField;
        public int inputField;

        public static InputValidationError[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new InputValidationError[0];
                    }
                }
            }
            return _emptyArray;
        }

        public InputValidationError() {
            clear();
        }

        public InputValidationError clear() {
            this.inputField = 0;
            this.hasInputField = false;
            this.errorMessage = "";
            this.hasErrorMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.inputField != 0 || this.hasInputField) {
                output.writeInt32(1, this.inputField);
            }
            if (this.hasErrorMessage || !this.errorMessage.equals("")) {
                output.writeString(2, this.errorMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.inputField != 0 || this.hasInputField) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.inputField);
            }
            if (this.hasErrorMessage || !this.errorMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.errorMessage);
            }
            return size;
        }

        public InputValidationError mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                                this.inputField = value;
                                this.hasInputField = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.errorMessage = input.readString();
                        this.hasErrorMessage = true;
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

    public static final class PaymentsUpdateChallenge extends MessageNano {
        public boolean hasUseClientCart;
        public SingleFopPaymentsIntegratorContext paymentsIntegratorContext;
        public boolean useClientCart;

        public PaymentsUpdateChallenge() {
            clear();
        }

        public PaymentsUpdateChallenge clear() {
            this.paymentsIntegratorContext = null;
            this.useClientCart = false;
            this.hasUseClientCart = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.paymentsIntegratorContext != null) {
                output.writeMessage(1, this.paymentsIntegratorContext);
            }
            if (this.hasUseClientCart || this.useClientCart) {
                output.writeBool(2, this.useClientCart);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.paymentsIntegratorContext != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.paymentsIntegratorContext);
            }
            if (this.hasUseClientCart || this.useClientCart) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.useClientCart);
            }
            return size;
        }

        public PaymentsUpdateChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.paymentsIntegratorContext == null) {
                            this.paymentsIntegratorContext = new SingleFopPaymentsIntegratorContext();
                        }
                        input.readMessage(this.paymentsIntegratorContext);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.useClientCart = input.readBool();
                        this.hasUseClientCart = true;
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

    public static final class RapChallenge extends MessageNano {
        public String description;
        public boolean hasDescription;
        public boolean hasTitle;
        public RapRefundParams refundParams;
        public FormButton submitButton;
        public String title;

        public RapChallenge() {
            clear();
        }

        public RapChallenge clear() {
            this.title = "";
            this.hasTitle = false;
            this.description = "";
            this.hasDescription = false;
            this.submitButton = null;
            this.refundParams = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasDescription || !this.description.equals("")) {
                output.writeString(2, this.description);
            }
            if (this.submitButton != null) {
                output.writeMessage(3, this.submitButton);
            }
            if (this.refundParams != null) {
                output.writeMessage(4, this.refundParams);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasDescription || !this.description.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.description);
            }
            if (this.submitButton != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.submitButton);
            }
            if (this.refundParams != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.refundParams);
            }
            return size;
        }

        public RapChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.description = input.readString();
                        this.hasDescription = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.submitButton == null) {
                            this.submitButton = new FormButton();
                        }
                        input.readMessage(this.submitButton);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.refundParams == null) {
                            this.refundParams = new RapRefundParams();
                        }
                        input.readMessage(this.refundParams);
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

    public static final class RapRefundParams extends MessageNano {
        public String docId;
        public boolean hasDocId;
        public boolean hasOrderId;
        public String orderId;
        public FormTextField requestReason;

        public RapRefundParams() {
            clear();
        }

        public RapRefundParams clear() {
            this.docId = "";
            this.hasDocId = false;
            this.orderId = "";
            this.hasOrderId = false;
            this.requestReason = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDocId || !this.docId.equals("")) {
                output.writeString(1, this.docId);
            }
            if (this.hasOrderId || !this.orderId.equals("")) {
                output.writeString(2, this.orderId);
            }
            if (this.requestReason != null) {
                output.writeMessage(3, this.requestReason);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDocId || !this.docId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.docId);
            }
            if (this.hasOrderId || !this.orderId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.orderId);
            }
            if (this.requestReason != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.requestReason);
            }
            return size;
        }

        public RapRefundParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.docId = input.readString();
                        this.hasDocId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.orderId = input.readString();
                        this.hasOrderId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.requestReason == null) {
                            this.requestReason = new FormTextField();
                        }
                        input.readMessage(this.requestReason);
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

    public static final class SmsCodeChallenge extends MessageNano {
        public String descriptionHtml;
        public boolean hasDescriptionHtml;
        public boolean hasTitle;
        public FormButton resendCodeButton;
        public FormTextField smsCode;
        public FormButton submitButton;
        public String title;

        public SmsCodeChallenge() {
            clear();
        }

        public SmsCodeChallenge clear() {
            this.title = "";
            this.hasTitle = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.smsCode = null;
            this.resendCodeButton = null;
            this.submitButton = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(2, this.descriptionHtml);
            }
            if (this.smsCode != null) {
                output.writeMessage(3, this.smsCode);
            }
            if (this.resendCodeButton != null) {
                output.writeMessage(4, this.resendCodeButton);
            }
            if (this.submitButton != null) {
                output.writeMessage(5, this.submitButton);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.descriptionHtml);
            }
            if (this.smsCode != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.smsCode);
            }
            if (this.resendCodeButton != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.resendCodeButton);
            }
            if (this.submitButton != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.submitButton);
            }
            return size;
        }

        public SmsCodeChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.smsCode == null) {
                            this.smsCode = new FormTextField();
                        }
                        input.readMessage(this.smsCode);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.resendCodeButton == null) {
                            this.resendCodeButton = new FormButton();
                        }
                        input.readMessage(this.resendCodeButton);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.submitButton == null) {
                            this.submitButton = new FormButton();
                        }
                        input.readMessage(this.submitButton);
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

    public static final class WebViewChallenge extends MessageNano {
        public String cancelButtonDisplayLabel;
        public String cancelUrlRegexp;
        public boolean hasCancelButtonDisplayLabel;
        public boolean hasCancelUrlRegexp;
        public boolean hasResponseTargetUrlParam;
        public boolean hasStartUrl;
        public boolean hasTargetUrlRegexp;
        public boolean hasTitle;
        public String responseTargetUrlParam;
        public String startUrl;
        public String targetUrlRegexp;
        public String title;

        public WebViewChallenge() {
            clear();
        }

        public WebViewChallenge clear() {
            this.startUrl = "";
            this.hasStartUrl = false;
            this.targetUrlRegexp = "";
            this.hasTargetUrlRegexp = false;
            this.cancelUrlRegexp = "";
            this.hasCancelUrlRegexp = false;
            this.title = "";
            this.hasTitle = false;
            this.cancelButtonDisplayLabel = "";
            this.hasCancelButtonDisplayLabel = false;
            this.responseTargetUrlParam = "";
            this.hasResponseTargetUrlParam = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasStartUrl || !this.startUrl.equals("")) {
                output.writeString(1, this.startUrl);
            }
            if (this.hasTargetUrlRegexp || !this.targetUrlRegexp.equals("")) {
                output.writeString(2, this.targetUrlRegexp);
            }
            if (this.hasCancelButtonDisplayLabel || !this.cancelButtonDisplayLabel.equals("")) {
                output.writeString(3, this.cancelButtonDisplayLabel);
            }
            if (this.hasResponseTargetUrlParam || !this.responseTargetUrlParam.equals("")) {
                output.writeString(4, this.responseTargetUrlParam);
            }
            if (this.hasCancelUrlRegexp || !this.cancelUrlRegexp.equals("")) {
                output.writeString(5, this.cancelUrlRegexp);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(6, this.title);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasStartUrl || !this.startUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.startUrl);
            }
            if (this.hasTargetUrlRegexp || !this.targetUrlRegexp.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.targetUrlRegexp);
            }
            if (this.hasCancelButtonDisplayLabel || !this.cancelButtonDisplayLabel.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.cancelButtonDisplayLabel);
            }
            if (this.hasResponseTargetUrlParam || !this.responseTargetUrlParam.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.responseTargetUrlParam);
            }
            if (this.hasCancelUrlRegexp || !this.cancelUrlRegexp.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.cancelUrlRegexp);
            }
            if (this.hasTitle || !this.title.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(6, this.title);
            }
            return size;
        }

        public WebViewChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.startUrl = input.readString();
                        this.hasStartUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.targetUrlRegexp = input.readString();
                        this.hasTargetUrlRegexp = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.cancelButtonDisplayLabel = input.readString();
                        this.hasCancelButtonDisplayLabel = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.responseTargetUrlParam = input.readString();
                        this.hasResponseTargetUrlParam = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.cancelUrlRegexp = input.readString();
                        this.hasCancelUrlRegexp = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.title = input.readString();
                        this.hasTitle = true;
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
