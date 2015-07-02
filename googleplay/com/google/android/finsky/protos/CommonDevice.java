package com.google.android.finsky.protos;

import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.CreateInstrument.CreateInstrumentFlowOption;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentFlowHandle;
import com.google.android.finsky.protos.CreateInstrument.DeviceCreateInstrumentMetadata;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface CommonDevice {

    public static final class BillingAddressSpec extends MessageNano {
        public int billingAddressType;
        public boolean hasBillingAddressType;
        public int[] requiredField;

        public BillingAddressSpec() {
            clear();
        }

        public BillingAddressSpec clear() {
            this.billingAddressType = 1;
            this.hasBillingAddressType = false;
            this.requiredField = WireFormatNano.EMPTY_INT_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.billingAddressType != 1 || this.hasBillingAddressType) {
                output.writeInt32(1, this.billingAddressType);
            }
            if (this.requiredField != null && this.requiredField.length > 0) {
                for (int writeInt32 : this.requiredField) {
                    output.writeInt32(2, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.billingAddressType != 1 || this.hasBillingAddressType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.billingAddressType);
            }
            if (this.requiredField == null || this.requiredField.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (int element : this.requiredField) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element);
            }
            return (size + dataSize) + (this.requiredField.length * 1);
        }

        public BillingAddressSpec mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int i;
                int[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                this.billingAddressType = value;
                                this.hasBillingAddressType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 16);
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
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        int arrayLength = 0;
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

    public static final class CarrierBillingCredentials extends MessageNano {
        public long expiration;
        public boolean hasExpiration;
        public boolean hasValue;
        public String value;

        public CarrierBillingCredentials() {
            clear();
        }

        public CarrierBillingCredentials clear() {
            this.value = "";
            this.hasValue = false;
            this.expiration = 0;
            this.hasExpiration = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasValue || !this.value.equals("")) {
                output.writeString(1, this.value);
            }
            if (this.hasExpiration || this.expiration != 0) {
                output.writeInt64(2, this.expiration);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasValue || !this.value.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.value);
            }
            if (this.hasExpiration || this.expiration != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(2, this.expiration);
            }
            return size;
        }

        public CarrierBillingCredentials mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.value = input.readString();
                        this.hasValue = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.expiration = input.readInt64();
                        this.hasExpiration = true;
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

    public static final class CarrierBillingInstrument extends MessageNano {
        public CarrierTos acceptedCarrierTos;
        public String accountType;
        public CarrierBillingCredentials credentials;
        public String currencyCode;
        public EncryptedSubscriberInfo encryptedSubscriberInfo;
        public boolean hasAccountType;
        public boolean hasCurrencyCode;
        public boolean hasInstrumentKey;
        public boolean hasSubscriberIdentifier;
        public boolean hasTransactionLimit;
        public String instrumentKey;
        public String subscriberIdentifier;
        public long transactionLimit;

        public CarrierBillingInstrument() {
            clear();
        }

        public CarrierBillingInstrument clear() {
            this.instrumentKey = "";
            this.hasInstrumentKey = false;
            this.accountType = "";
            this.hasAccountType = false;
            this.currencyCode = "";
            this.hasCurrencyCode = false;
            this.transactionLimit = 0;
            this.hasTransactionLimit = false;
            this.subscriberIdentifier = "";
            this.hasSubscriberIdentifier = false;
            this.encryptedSubscriberInfo = null;
            this.credentials = null;
            this.acceptedCarrierTos = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasInstrumentKey || !this.instrumentKey.equals("")) {
                output.writeString(1, this.instrumentKey);
            }
            if (this.hasAccountType || !this.accountType.equals("")) {
                output.writeString(2, this.accountType);
            }
            if (this.hasCurrencyCode || !this.currencyCode.equals("")) {
                output.writeString(3, this.currencyCode);
            }
            if (this.hasTransactionLimit || this.transactionLimit != 0) {
                output.writeInt64(4, this.transactionLimit);
            }
            if (this.hasSubscriberIdentifier || !this.subscriberIdentifier.equals("")) {
                output.writeString(5, this.subscriberIdentifier);
            }
            if (this.encryptedSubscriberInfo != null) {
                output.writeMessage(6, this.encryptedSubscriberInfo);
            }
            if (this.credentials != null) {
                output.writeMessage(7, this.credentials);
            }
            if (this.acceptedCarrierTos != null) {
                output.writeMessage(8, this.acceptedCarrierTos);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasInstrumentKey || !this.instrumentKey.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.instrumentKey);
            }
            if (this.hasAccountType || !this.accountType.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.accountType);
            }
            if (this.hasCurrencyCode || !this.currencyCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.currencyCode);
            }
            if (this.hasTransactionLimit || this.transactionLimit != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.transactionLimit);
            }
            if (this.hasSubscriberIdentifier || !this.subscriberIdentifier.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.subscriberIdentifier);
            }
            if (this.encryptedSubscriberInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.encryptedSubscriberInfo);
            }
            if (this.credentials != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.credentials);
            }
            if (this.acceptedCarrierTos != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(8, this.acceptedCarrierTos);
            }
            return size;
        }

        public CarrierBillingInstrument mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.instrumentKey = input.readString();
                        this.hasInstrumentKey = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.accountType = input.readString();
                        this.hasAccountType = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.currencyCode = input.readString();
                        this.hasCurrencyCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.transactionLimit = input.readInt64();
                        this.hasTransactionLimit = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.subscriberIdentifier = input.readString();
                        this.hasSubscriberIdentifier = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.encryptedSubscriberInfo == null) {
                            this.encryptedSubscriberInfo = new EncryptedSubscriberInfo();
                        }
                        input.readMessage(this.encryptedSubscriberInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.credentials == null) {
                            this.credentials = new CarrierBillingCredentials();
                        }
                        input.readMessage(this.credentials);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.acceptedCarrierTos == null) {
                            this.acceptedCarrierTos = new CarrierTos();
                        }
                        input.readMessage(this.acceptedCarrierTos);
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

    public static final class CarrierBillingInstrumentStatus extends MessageNano {
        public int apiVersion;
        public boolean associationRequired;
        public PasswordPrompt carrierPasswordPrompt;
        public String carrierSupportPhoneNumber;
        public CarrierTos carrierTos;
        public DeviceAssociation deviceAssociation;
        public boolean hasApiVersion;
        public boolean hasAssociationRequired;
        public boolean hasCarrierSupportPhoneNumber;
        public boolean hasName;
        public boolean hasPasswordRequired;
        public String name;
        public boolean passwordRequired;

        public CarrierBillingInstrumentStatus() {
            clear();
        }

        public CarrierBillingInstrumentStatus clear() {
            this.carrierTos = null;
            this.associationRequired = false;
            this.hasAssociationRequired = false;
            this.passwordRequired = false;
            this.hasPasswordRequired = false;
            this.carrierPasswordPrompt = null;
            this.apiVersion = 0;
            this.hasApiVersion = false;
            this.name = "";
            this.hasName = false;
            this.deviceAssociation = null;
            this.carrierSupportPhoneNumber = "";
            this.hasCarrierSupportPhoneNumber = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.carrierTos != null) {
                output.writeMessage(1, this.carrierTos);
            }
            if (this.hasAssociationRequired || this.associationRequired) {
                output.writeBool(2, this.associationRequired);
            }
            if (this.hasPasswordRequired || this.passwordRequired) {
                output.writeBool(3, this.passwordRequired);
            }
            if (this.carrierPasswordPrompt != null) {
                output.writeMessage(4, this.carrierPasswordPrompt);
            }
            if (this.hasApiVersion || this.apiVersion != 0) {
                output.writeInt32(5, this.apiVersion);
            }
            if (this.hasName || !this.name.equals("")) {
                output.writeString(6, this.name);
            }
            if (this.deviceAssociation != null) {
                output.writeMessage(7, this.deviceAssociation);
            }
            if (this.hasCarrierSupportPhoneNumber || !this.carrierSupportPhoneNumber.equals("")) {
                output.writeString(8, this.carrierSupportPhoneNumber);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.carrierTos != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.carrierTos);
            }
            if (this.hasAssociationRequired || this.associationRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.associationRequired);
            }
            if (this.hasPasswordRequired || this.passwordRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.passwordRequired);
            }
            if (this.carrierPasswordPrompt != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.carrierPasswordPrompt);
            }
            if (this.hasApiVersion || this.apiVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.apiVersion);
            }
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.name);
            }
            if (this.deviceAssociation != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.deviceAssociation);
            }
            if (this.hasCarrierSupportPhoneNumber || !this.carrierSupportPhoneNumber.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(8, this.carrierSupportPhoneNumber);
            }
            return size;
        }

        public CarrierBillingInstrumentStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.carrierTos == null) {
                            this.carrierTos = new CarrierTos();
                        }
                        input.readMessage(this.carrierTos);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.associationRequired = input.readBool();
                        this.hasAssociationRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.passwordRequired = input.readBool();
                        this.hasPasswordRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.carrierPasswordPrompt == null) {
                            this.carrierPasswordPrompt = new PasswordPrompt();
                        }
                        input.readMessage(this.carrierPasswordPrompt);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.apiVersion = input.readInt32();
                        this.hasApiVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.deviceAssociation == null) {
                            this.deviceAssociation = new DeviceAssociation();
                        }
                        input.readMessage(this.deviceAssociation);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.carrierSupportPhoneNumber = input.readString();
                        this.hasCarrierSupportPhoneNumber = true;
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

    public static final class CarrierTos extends MessageNano {
        public CarrierTosEntry dcbTos;
        public boolean hasNeedsDcbTosAcceptance;
        public boolean hasNeedsPiiTosAcceptance;
        public boolean needsDcbTosAcceptance;
        public boolean needsPiiTosAcceptance;
        public CarrierTosEntry piiTos;

        public CarrierTos() {
            clear();
        }

        public CarrierTos clear() {
            this.dcbTos = null;
            this.piiTos = null;
            this.needsDcbTosAcceptance = false;
            this.hasNeedsDcbTosAcceptance = false;
            this.needsPiiTosAcceptance = false;
            this.hasNeedsPiiTosAcceptance = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.dcbTos != null) {
                output.writeMessage(1, this.dcbTos);
            }
            if (this.piiTos != null) {
                output.writeMessage(2, this.piiTos);
            }
            if (this.hasNeedsDcbTosAcceptance || this.needsDcbTosAcceptance) {
                output.writeBool(3, this.needsDcbTosAcceptance);
            }
            if (this.hasNeedsPiiTosAcceptance || this.needsPiiTosAcceptance) {
                output.writeBool(4, this.needsPiiTosAcceptance);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.dcbTos != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.dcbTos);
            }
            if (this.piiTos != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.piiTos);
            }
            if (this.hasNeedsDcbTosAcceptance || this.needsDcbTosAcceptance) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.needsDcbTosAcceptance);
            }
            if (this.hasNeedsPiiTosAcceptance || this.needsPiiTosAcceptance) {
                return size + CodedOutputByteBufferNano.computeBoolSize(4, this.needsPiiTosAcceptance);
            }
            return size;
        }

        public CarrierTos mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.dcbTos == null) {
                            this.dcbTos = new CarrierTosEntry();
                        }
                        input.readMessage(this.dcbTos);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.piiTos == null) {
                            this.piiTos = new CarrierTosEntry();
                        }
                        input.readMessage(this.piiTos);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.needsDcbTosAcceptance = input.readBool();
                        this.hasNeedsDcbTosAcceptance = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.needsPiiTosAcceptance = input.readBool();
                        this.hasNeedsPiiTosAcceptance = true;
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

    public static final class CarrierTosEntry extends MessageNano {
        public boolean hasUrl;
        public boolean hasVersion;
        public String url;
        public String version;

        public CarrierTosEntry() {
            clear();
        }

        public CarrierTosEntry clear() {
            this.url = "";
            this.hasUrl = false;
            this.version = "";
            this.hasVersion = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUrl || !this.url.equals("")) {
                output.writeString(1, this.url);
            }
            if (this.hasVersion || !this.version.equals("")) {
                output.writeString(2, this.version);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUrl || !this.url.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
            }
            if (this.hasVersion || !this.version.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.version);
            }
            return size;
        }

        public CarrierTosEntry mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.url = input.readString();
                        this.hasUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.version = input.readString();
                        this.hasVersion = true;
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

    public static final class CreditCardInstrument extends MessageNano {
        public EfeParam[] escrowEfeParam;
        public String escrowHandle;
        public int expirationMonth;
        public int expirationYear;
        public boolean hasEscrowHandle;
        public boolean hasExpirationMonth;
        public boolean hasExpirationYear;
        public boolean hasLastDigits;
        public boolean hasType;
        public String lastDigits;
        public int type;

        public CreditCardInstrument() {
            clear();
        }

        public CreditCardInstrument clear() {
            this.type = 0;
            this.hasType = false;
            this.escrowHandle = "";
            this.hasEscrowHandle = false;
            this.lastDigits = "";
            this.hasLastDigits = false;
            this.expirationMonth = 0;
            this.hasExpirationMonth = false;
            this.expirationYear = 0;
            this.hasExpirationYear = false;
            this.escrowEfeParam = EfeParam.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            if (this.hasEscrowHandle || !this.escrowHandle.equals("")) {
                output.writeString(2, this.escrowHandle);
            }
            if (this.hasLastDigits || !this.lastDigits.equals("")) {
                output.writeString(3, this.lastDigits);
            }
            if (this.hasExpirationMonth || this.expirationMonth != 0) {
                output.writeInt32(4, this.expirationMonth);
            }
            if (this.hasExpirationYear || this.expirationYear != 0) {
                output.writeInt32(5, this.expirationYear);
            }
            if (this.escrowEfeParam != null && this.escrowEfeParam.length > 0) {
                for (EfeParam element : this.escrowEfeParam) {
                    if (element != null) {
                        output.writeMessage(6, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.hasEscrowHandle || !this.escrowHandle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.escrowHandle);
            }
            if (this.hasLastDigits || !this.lastDigits.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.lastDigits);
            }
            if (this.hasExpirationMonth || this.expirationMonth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.expirationMonth);
            }
            if (this.hasExpirationYear || this.expirationYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.expirationYear);
            }
            if (this.escrowEfeParam != null && this.escrowEfeParam.length > 0) {
                for (EfeParam element : this.escrowEfeParam) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element);
                    }
                }
            }
            return size;
        }

        public CreditCardInstrument mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.escrowHandle = input.readString();
                        this.hasEscrowHandle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.lastDigits = input.readString();
                        this.hasLastDigits = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.expirationMonth = input.readInt32();
                        this.hasExpirationMonth = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.expirationYear = input.readInt32();
                        this.hasExpirationYear = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.escrowEfeParam == null) {
                            i = 0;
                        } else {
                            i = this.escrowEfeParam.length;
                        }
                        EfeParam[] newArray = new EfeParam[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.escrowEfeParam, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new EfeParam();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new EfeParam();
                        input.readMessage(newArray[i]);
                        this.escrowEfeParam = newArray;
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

    public static final class DeviceAssociation extends MessageNano {
        public boolean hasUserTokenRequestAddress;
        public boolean hasUserTokenRequestMessage;
        public String userTokenRequestAddress;
        public String userTokenRequestMessage;

        public DeviceAssociation() {
            clear();
        }

        public DeviceAssociation clear() {
            this.userTokenRequestMessage = "";
            this.hasUserTokenRequestMessage = false;
            this.userTokenRequestAddress = "";
            this.hasUserTokenRequestAddress = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUserTokenRequestMessage || !this.userTokenRequestMessage.equals("")) {
                output.writeString(1, this.userTokenRequestMessage);
            }
            if (this.hasUserTokenRequestAddress || !this.userTokenRequestAddress.equals("")) {
                output.writeString(2, this.userTokenRequestAddress);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUserTokenRequestMessage || !this.userTokenRequestMessage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.userTokenRequestMessage);
            }
            if (this.hasUserTokenRequestAddress || !this.userTokenRequestAddress.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.userTokenRequestAddress);
            }
            return size;
        }

        public DeviceAssociation mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.userTokenRequestMessage = input.readString();
                        this.hasUserTokenRequestMessage = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.userTokenRequestAddress = input.readString();
                        this.hasUserTokenRequestAddress = true;
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

    public static final class DisabledInfo extends MessageNano {
        private static volatile DisabledInfo[] _emptyArray;
        public String disabledMessageHtml;
        public int disabledReason;
        public String errorMessage;
        public boolean hasDisabledMessageHtml;
        public boolean hasDisabledReason;
        public boolean hasErrorMessage;

        public static DisabledInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DisabledInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DisabledInfo() {
            clear();
        }

        public DisabledInfo clear() {
            this.disabledReason = 1;
            this.hasDisabledReason = false;
            this.disabledMessageHtml = "";
            this.hasDisabledMessageHtml = false;
            this.errorMessage = "";
            this.hasErrorMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.disabledReason != 1 || this.hasDisabledReason) {
                output.writeInt32(1, this.disabledReason);
            }
            if (this.hasDisabledMessageHtml || !this.disabledMessageHtml.equals("")) {
                output.writeString(2, this.disabledMessageHtml);
            }
            if (this.hasErrorMessage || !this.errorMessage.equals("")) {
                output.writeString(3, this.errorMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.disabledReason != 1 || this.hasDisabledReason) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.disabledReason);
            }
            if (this.hasDisabledMessageHtml || !this.disabledMessageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.disabledMessageHtml);
            }
            if (this.hasErrorMessage || !this.errorMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.errorMessage);
            }
            return size;
        }

        public DisabledInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                                this.disabledReason = value;
                                this.hasDisabledReason = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.disabledMessageHtml = input.readString();
                        this.hasDisabledMessageHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
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

    public static final class EfeParam extends MessageNano {
        private static volatile EfeParam[] _emptyArray;
        public boolean hasKey;
        public boolean hasValue;
        public int key;
        public String value;

        public static EfeParam[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EfeParam[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EfeParam() {
            clear();
        }

        public EfeParam clear() {
            this.key = 1;
            this.hasKey = false;
            this.value = "";
            this.hasValue = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.key != 1 || this.hasKey) {
                output.writeInt32(1, this.key);
            }
            if (this.hasValue || !this.value.equals("")) {
                output.writeString(2, this.value);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.key != 1 || this.hasKey) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.key);
            }
            if (this.hasValue || !this.value.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.value);
            }
            return size;
        }

        public EfeParam mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                this.key = value;
                                this.hasKey = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.value = input.readString();
                        this.hasValue = true;
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

    public static final class GenericInstrument extends MessageNano {
        public DeviceCreateInstrumentFlowHandle createInstrumentFlowHandle;
        public CreateInstrumentFlowOption[] createInstrumentFlowOption;
        public DeviceCreateInstrumentMetadata createInstrumentMetadata;
        public Image iconImage;

        public GenericInstrument() {
            clear();
        }

        public GenericInstrument clear() {
            this.iconImage = null;
            this.createInstrumentFlowOption = CreateInstrumentFlowOption.emptyArray();
            this.createInstrumentFlowHandle = null;
            this.createInstrumentMetadata = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.createInstrumentFlowOption != null && this.createInstrumentFlowOption.length > 0) {
                for (CreateInstrumentFlowOption element : this.createInstrumentFlowOption) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.createInstrumentFlowHandle != null) {
                output.writeMessage(3, this.createInstrumentFlowHandle);
            }
            if (this.createInstrumentMetadata != null) {
                output.writeMessage(4, this.createInstrumentMetadata);
            }
            if (this.iconImage != null) {
                output.writeMessage(5, this.iconImage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.createInstrumentFlowOption != null && this.createInstrumentFlowOption.length > 0) {
                for (CreateInstrumentFlowOption element : this.createInstrumentFlowOption) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.createInstrumentFlowHandle != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.createInstrumentFlowHandle);
            }
            if (this.createInstrumentMetadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.createInstrumentMetadata);
            }
            if (this.iconImage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.iconImage);
            }
            return size;
        }

        public GenericInstrument mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.createInstrumentFlowOption == null) {
                            i = 0;
                        } else {
                            i = this.createInstrumentFlowOption.length;
                        }
                        CreateInstrumentFlowOption[] newArray = new CreateInstrumentFlowOption[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.createInstrumentFlowOption, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new CreateInstrumentFlowOption();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new CreateInstrumentFlowOption();
                        input.readMessage(newArray[i]);
                        this.createInstrumentFlowOption = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.createInstrumentFlowHandle == null) {
                            this.createInstrumentFlowHandle = new DeviceCreateInstrumentFlowHandle();
                        }
                        input.readMessage(this.createInstrumentFlowHandle);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.createInstrumentMetadata == null) {
                            this.createInstrumentMetadata = new DeviceCreateInstrumentMetadata();
                        }
                        input.readMessage(this.createInstrumentMetadata);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.iconImage == null) {
                            this.iconImage = new Image();
                        }
                        input.readMessage(this.iconImage);
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

    public static final class Instrument extends MessageNano {
        private static volatile Instrument[] _emptyArray;
        public Address billingAddress;
        public BillingAddressSpec billingAddressSpec;
        public CarrierBillingInstrument carrierBilling;
        public CarrierBillingInstrumentStatus carrierBillingStatus;
        public CreditCardInstrument creditCard;
        public DisabledInfo[] disabledInfo;
        public String displayTitle;
        public String externalInstrumentId;
        public boolean hasDisplayTitle;
        public boolean hasExternalInstrumentId;
        public boolean hasInstrumentFamily;
        public boolean hasStatusDescription;
        public boolean hasVersion;
        public Image iconImage;
        public int instrumentFamily;
        public String statusDescription;
        public StoredValueInstrument storedValue;
        public TopupInfo topupInfoDeprecated;
        public int version;

        public static Instrument[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Instrument[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Instrument() {
            clear();
        }

        public Instrument clear() {
            this.externalInstrumentId = "";
            this.hasExternalInstrumentId = false;
            this.displayTitle = "";
            this.hasDisplayTitle = false;
            this.iconImage = null;
            this.instrumentFamily = 0;
            this.hasInstrumentFamily = false;
            this.billingAddress = null;
            this.billingAddressSpec = null;
            this.creditCard = null;
            this.carrierBilling = null;
            this.carrierBillingStatus = null;
            this.storedValue = null;
            this.topupInfoDeprecated = null;
            this.version = 0;
            this.hasVersion = false;
            this.disabledInfo = DisabledInfo.emptyArray();
            this.statusDescription = "";
            this.hasStatusDescription = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasExternalInstrumentId || !this.externalInstrumentId.equals("")) {
                output.writeString(1, this.externalInstrumentId);
            }
            if (this.billingAddress != null) {
                output.writeMessage(2, this.billingAddress);
            }
            if (this.creditCard != null) {
                output.writeMessage(3, this.creditCard);
            }
            if (this.carrierBilling != null) {
                output.writeMessage(4, this.carrierBilling);
            }
            if (this.billingAddressSpec != null) {
                output.writeMessage(5, this.billingAddressSpec);
            }
            if (this.instrumentFamily != 0 || this.hasInstrumentFamily) {
                output.writeInt32(6, this.instrumentFamily);
            }
            if (this.carrierBillingStatus != null) {
                output.writeMessage(7, this.carrierBillingStatus);
            }
            if (this.hasDisplayTitle || !this.displayTitle.equals("")) {
                output.writeString(8, this.displayTitle);
            }
            if (this.topupInfoDeprecated != null) {
                output.writeMessage(9, this.topupInfoDeprecated);
            }
            if (this.hasVersion || this.version != 0) {
                output.writeInt32(10, this.version);
            }
            if (this.storedValue != null) {
                output.writeMessage(11, this.storedValue);
            }
            if (this.disabledInfo != null && this.disabledInfo.length > 0) {
                for (DisabledInfo element : this.disabledInfo) {
                    if (element != null) {
                        output.writeMessage(12, element);
                    }
                }
            }
            if (this.hasStatusDescription || !this.statusDescription.equals("")) {
                output.writeString(13, this.statusDescription);
            }
            if (this.iconImage != null) {
                output.writeMessage(14, this.iconImage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasExternalInstrumentId || !this.externalInstrumentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.externalInstrumentId);
            }
            if (this.billingAddress != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.billingAddress);
            }
            if (this.creditCard != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.creditCard);
            }
            if (this.carrierBilling != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.carrierBilling);
            }
            if (this.billingAddressSpec != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.billingAddressSpec);
            }
            if (this.instrumentFamily != 0 || this.hasInstrumentFamily) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.instrumentFamily);
            }
            if (this.carrierBillingStatus != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.carrierBillingStatus);
            }
            if (this.hasDisplayTitle || !this.displayTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.displayTitle);
            }
            if (this.topupInfoDeprecated != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.topupInfoDeprecated);
            }
            if (this.hasVersion || this.version != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.version);
            }
            if (this.storedValue != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.storedValue);
            }
            if (this.disabledInfo != null && this.disabledInfo.length > 0) {
                for (DisabledInfo element : this.disabledInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(12, element);
                    }
                }
            }
            if (this.hasStatusDescription || !this.statusDescription.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.statusDescription);
            }
            if (this.iconImage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(14, this.iconImage);
            }
            return size;
        }

        public Instrument mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.externalInstrumentId = input.readString();
                        this.hasExternalInstrumentId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.billingAddress == null) {
                            this.billingAddress = new Address();
                        }
                        input.readMessage(this.billingAddress);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.creditCard == null) {
                            this.creditCard = new CreditCardInstrument();
                        }
                        input.readMessage(this.creditCard);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.carrierBilling == null) {
                            this.carrierBilling = new CarrierBillingInstrument();
                        }
                        input.readMessage(this.carrierBilling);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.billingAddressSpec == null) {
                            this.billingAddressSpec = new BillingAddressSpec();
                        }
                        input.readMessage(this.billingAddressSpec);
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
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
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.carrierBillingStatus == null) {
                            this.carrierBillingStatus = new CarrierBillingInstrumentStatus();
                        }
                        input.readMessage(this.carrierBillingStatus);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.displayTitle = input.readString();
                        this.hasDisplayTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.topupInfoDeprecated == null) {
                            this.topupInfoDeprecated = new TopupInfo();
                        }
                        input.readMessage(this.topupInfoDeprecated);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.version = input.readInt32();
                        this.hasVersion = true;
                        continue;
                    case 90:
                        if (this.storedValue == null) {
                            this.storedValue = new StoredValueInstrument();
                        }
                        input.readMessage(this.storedValue);
                        continue;
                    case 98:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 98);
                        if (this.disabledInfo == null) {
                            i = 0;
                        } else {
                            i = this.disabledInfo.length;
                        }
                        DisabledInfo[] newArray = new DisabledInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.disabledInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new DisabledInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new DisabledInfo();
                        input.readMessage(newArray[i]);
                        this.disabledInfo = newArray;
                        continue;
                    case 106:
                        this.statusDescription = input.readString();
                        this.hasStatusDescription = true;
                        continue;
                    case 114:
                        if (this.iconImage == null) {
                            this.iconImage = new Image();
                        }
                        input.readMessage(this.iconImage);
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

    public static final class Money extends MessageNano {
        public String currencyCode;
        public String formattedAmount;
        public boolean hasCurrencyCode;
        public boolean hasFormattedAmount;
        public boolean hasMicros;
        public long micros;

        public Money() {
            clear();
        }

        public Money clear() {
            this.micros = 0;
            this.hasMicros = false;
            this.currencyCode = "";
            this.hasCurrencyCode = false;
            this.formattedAmount = "";
            this.hasFormattedAmount = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasMicros || this.micros != 0) {
                output.writeInt64(1, this.micros);
            }
            if (this.hasCurrencyCode || !this.currencyCode.equals("")) {
                output.writeString(2, this.currencyCode);
            }
            if (this.hasFormattedAmount || !this.formattedAmount.equals("")) {
                output.writeString(3, this.formattedAmount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasMicros || this.micros != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.micros);
            }
            if (this.hasCurrencyCode || !this.currencyCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.currencyCode);
            }
            if (this.hasFormattedAmount || !this.formattedAmount.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.formattedAmount);
            }
            return size;
        }

        public Money mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.micros = input.readInt64();
                        this.hasMicros = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.currencyCode = input.readString();
                        this.hasCurrencyCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.formattedAmount = input.readString();
                        this.hasFormattedAmount = true;
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

    public static final class PasswordPrompt extends MessageNano {
        public String forgotPasswordUrl;
        public boolean hasForgotPasswordUrl;
        public boolean hasPrompt;
        public String prompt;

        public PasswordPrompt() {
            clear();
        }

        public PasswordPrompt clear() {
            this.prompt = "";
            this.hasPrompt = false;
            this.forgotPasswordUrl = "";
            this.hasForgotPasswordUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPrompt || !this.prompt.equals("")) {
                output.writeString(1, this.prompt);
            }
            if (this.hasForgotPasswordUrl || !this.forgotPasswordUrl.equals("")) {
                output.writeString(2, this.forgotPasswordUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPrompt || !this.prompt.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.prompt);
            }
            if (this.hasForgotPasswordUrl || !this.forgotPasswordUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.forgotPasswordUrl);
            }
            return size;
        }

        public PasswordPrompt mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.prompt = input.readString();
                        this.hasPrompt = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.forgotPasswordUrl = input.readString();
                        this.hasForgotPasswordUrl = true;
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

    public static final class StoredValueInstrument extends MessageNano {
        public Money balance;
        public boolean hasType;
        public TopupInfo topupInfo;
        public int type;

        public StoredValueInstrument() {
            clear();
        }

        public StoredValueInstrument clear() {
            this.type = 32;
            this.hasType = false;
            this.balance = null;
            this.topupInfo = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 32 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            if (this.balance != null) {
                output.writeMessage(2, this.balance);
            }
            if (this.topupInfo != null) {
                output.writeMessage(3, this.topupInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 32 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.balance != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.balance);
            }
            if (this.topupInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.topupInfo);
            }
            return size;
        }

        public StoredValueInstrument mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                            case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.balance == null) {
                            this.balance = new Money();
                        }
                        input.readMessage(this.balance);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.topupInfo == null) {
                            this.topupInfo = new TopupInfo();
                        }
                        input.readMessage(this.topupInfo);
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

    public static final class TopupInfo extends MessageNano {
        public boolean hasOptionsContainerDocidDeprecated;
        public boolean hasOptionsListUrl;
        public boolean hasSubtitle;
        public Docid optionsContainerDocid;
        public String optionsContainerDocidDeprecated;
        public String optionsListUrl;
        public String subtitle;

        public TopupInfo() {
            clear();
        }

        public TopupInfo clear() {
            this.optionsContainerDocidDeprecated = "";
            this.hasOptionsContainerDocidDeprecated = false;
            this.optionsContainerDocid = null;
            this.optionsListUrl = "";
            this.hasOptionsListUrl = false;
            this.subtitle = "";
            this.hasSubtitle = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasOptionsContainerDocidDeprecated || !this.optionsContainerDocidDeprecated.equals("")) {
                output.writeString(1, this.optionsContainerDocidDeprecated);
            }
            if (this.hasOptionsListUrl || !this.optionsListUrl.equals("")) {
                output.writeString(2, this.optionsListUrl);
            }
            if (this.hasSubtitle || !this.subtitle.equals("")) {
                output.writeString(3, this.subtitle);
            }
            if (this.optionsContainerDocid != null) {
                output.writeMessage(4, this.optionsContainerDocid);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasOptionsContainerDocidDeprecated || !this.optionsContainerDocidDeprecated.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.optionsContainerDocidDeprecated);
            }
            if (this.hasOptionsListUrl || !this.optionsListUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.optionsListUrl);
            }
            if (this.hasSubtitle || !this.subtitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.subtitle);
            }
            if (this.optionsContainerDocid != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.optionsContainerDocid);
            }
            return size;
        }

        public TopupInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.optionsContainerDocidDeprecated = input.readString();
                        this.hasOptionsContainerDocidDeprecated = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.optionsListUrl = input.readString();
                        this.hasOptionsListUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.subtitle = input.readString();
                        this.hasSubtitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.optionsContainerDocid == null) {
                            this.optionsContainerDocid = new Docid();
                        }
                        input.readMessage(this.optionsContainerDocid);
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
