package com.google.android.finsky.protos;

import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface CreateInstrument {

    public static final class AddressFormField extends MessageNano {
        public boolean hasType;
        public int type;

        public AddressFormField() {
            clear();
        }

        public AddressFormField clear() {
            this.type = 0;
            this.hasType = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0 || this.hasType) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            return size;
        }

        public AddressFormField mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.type = value;
                                this.hasType = true;
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

    public static final class AddressFormInput extends MessageNano {
        public Address address;
        public int addressType;
        public boolean hasAddressType;

        public AddressFormInput() {
            clear();
        }

        public AddressFormInput clear() {
            this.address = null;
            this.addressType = 0;
            this.hasAddressType = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.address != null) {
                output.writeMessage(1, this.address);
            }
            if (this.addressType != 0 || this.hasAddressType) {
                output.writeInt32(2, this.addressType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.address != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.address);
            }
            if (this.addressType != 0 || this.hasAddressType) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.addressType);
            }
            return size;
        }

        public AddressFormInput mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.address == null) {
                            this.address = new Address();
                        }
                        input.readMessage(this.address);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.addressType = value;
                                this.hasAddressType = true;
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

    public static final class CreateInstrumentFlowHandle extends MessageNano {
        public int apiVersion;
        public byte[] flowHandle;
        public int flowType;
        public boolean hasApiVersion;
        public boolean hasFlowHandle;
        public boolean hasFlowType;
        public boolean hasInstrumentType;
        public String instrumentType;

        public CreateInstrumentFlowHandle() {
            clear();
        }

        public CreateInstrumentFlowHandle clear() {
            this.instrumentType = "";
            this.hasInstrumentType = false;
            this.flowHandle = WireFormatNano.EMPTY_BYTES;
            this.hasFlowHandle = false;
            this.flowType = 0;
            this.hasFlowType = false;
            this.apiVersion = 0;
            this.hasApiVersion = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasInstrumentType || !this.instrumentType.equals("")) {
                output.writeString(1, this.instrumentType);
            }
            if (this.hasFlowHandle || !Arrays.equals(this.flowHandle, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.flowHandle);
            }
            if (this.flowType != 0 || this.hasFlowType) {
                output.writeInt32(3, this.flowType);
            }
            if (this.hasApiVersion || this.apiVersion != 0) {
                output.writeInt32(4, this.apiVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasInstrumentType || !this.instrumentType.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.instrumentType);
            }
            if (this.hasFlowHandle || !Arrays.equals(this.flowHandle, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.flowHandle);
            }
            if (this.flowType != 0 || this.hasFlowType) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.flowType);
            }
            if (this.hasApiVersion || this.apiVersion != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.apiVersion);
            }
            return size;
        }

        public CreateInstrumentFlowHandle mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.instrumentType = input.readString();
                        this.hasInstrumentType = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.flowHandle = input.readBytes();
                        this.hasFlowHandle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                this.flowType = value;
                                this.hasFlowType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.apiVersion = input.readInt32();
                        this.hasApiVersion = true;
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

    public static final class CreateInstrumentFlowOption extends MessageNano {
        private static volatile CreateInstrumentFlowOption[] _emptyArray;
        public int apiMaxVersion;
        public int apiMinVersion;
        public boolean hasApiMaxVersion;
        public boolean hasApiMinVersion;
        public CreateInstrumentFlowHandle initialFlowHandle;

        public static CreateInstrumentFlowOption[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CreateInstrumentFlowOption[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CreateInstrumentFlowOption() {
            clear();
        }

        public CreateInstrumentFlowOption clear() {
            this.initialFlowHandle = null;
            this.apiMinVersion = 0;
            this.hasApiMinVersion = false;
            this.apiMaxVersion = 0;
            this.hasApiMaxVersion = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.initialFlowHandle != null) {
                output.writeMessage(1, this.initialFlowHandle);
            }
            if (this.hasApiMinVersion || this.apiMinVersion != 0) {
                output.writeInt32(2, this.apiMinVersion);
            }
            if (this.hasApiMaxVersion || this.apiMaxVersion != 0) {
                output.writeInt32(3, this.apiMaxVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.initialFlowHandle != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.initialFlowHandle);
            }
            if (this.hasApiMinVersion || this.apiMinVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.apiMinVersion);
            }
            if (this.hasApiMaxVersion || this.apiMaxVersion != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.apiMaxVersion);
            }
            return size;
        }

        public CreateInstrumentFlowOption mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.initialFlowHandle == null) {
                            this.initialFlowHandle = new CreateInstrumentFlowHandle();
                        }
                        input.readMessage(this.initialFlowHandle);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.apiMinVersion = input.readInt32();
                        this.hasApiMinVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.apiMaxVersion = input.readInt32();
                        this.hasApiMaxVersion = true;
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

    public static final class DeviceCreateInstrumentFlowHandle extends MessageNano {
        public boolean hasToken;
        public byte[] token;

        public DeviceCreateInstrumentFlowHandle() {
            clear();
        }

        public DeviceCreateInstrumentFlowHandle clear() {
            this.token = WireFormatNano.EMPTY_BYTES;
            this.hasToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasToken || !Arrays.equals(this.token, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(1, this.token);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasToken || !Arrays.equals(this.token, WireFormatNano.EMPTY_BYTES)) {
                return size + CodedOutputByteBufferNano.computeBytesSize(1, this.token);
            }
            return size;
        }

        public DeviceCreateInstrumentFlowHandle mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.token = input.readBytes();
                        this.hasToken = true;
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

    public static final class DeviceCreateInstrumentFlowState extends MessageNano {
        public DeviceCreateInstrumentFlowHandle handle;
        public ProfileForm profileForm;
        public UsernamePasswordForm usernamePasswordForm;

        public DeviceCreateInstrumentFlowState() {
            clear();
        }

        public DeviceCreateInstrumentFlowState clear() {
            this.handle = null;
            this.profileForm = null;
            this.usernamePasswordForm = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.handle != null) {
                output.writeMessage(1, this.handle);
            }
            if (this.profileForm != null) {
                output.writeMessage(2, this.profileForm);
            }
            if (this.usernamePasswordForm != null) {
                output.writeMessage(3, this.usernamePasswordForm);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.handle != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.handle);
            }
            if (this.profileForm != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.profileForm);
            }
            if (this.usernamePasswordForm != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.usernamePasswordForm);
            }
            return size;
        }

        public DeviceCreateInstrumentFlowState mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.handle == null) {
                            this.handle = new DeviceCreateInstrumentFlowHandle();
                        }
                        input.readMessage(this.handle);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.profileForm == null) {
                            this.profileForm = new ProfileForm();
                        }
                        input.readMessage(this.profileForm);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.usernamePasswordForm == null) {
                            this.usernamePasswordForm = new UsernamePasswordForm();
                        }
                        input.readMessage(this.usernamePasswordForm);
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

    public static final class DeviceCreateInstrumentMetadata extends MessageNano {
        public int flowType;
        public boolean hasFlowType;
        public boolean hasInstrumentType;
        public String instrumentType;

        public DeviceCreateInstrumentMetadata() {
            clear();
        }

        public DeviceCreateInstrumentMetadata clear() {
            this.instrumentType = "";
            this.hasInstrumentType = false;
            this.flowType = 0;
            this.hasFlowType = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasInstrumentType || !this.instrumentType.equals("")) {
                output.writeString(1, this.instrumentType);
            }
            if (this.flowType != 0 || this.hasFlowType) {
                output.writeInt32(2, this.flowType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasInstrumentType || !this.instrumentType.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.instrumentType);
            }
            if (this.flowType != 0 || this.hasFlowType) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.flowType);
            }
            return size;
        }

        public DeviceCreateInstrumentMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.instrumentType = input.readString();
                        this.hasInstrumentType = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                this.flowType = value;
                                this.hasFlowType = true;
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

    public static final class FormInputRegexValidation extends MessageNano {
        public boolean hasRegex;
        public boolean hasRegexErrorMessage;
        public String regex;
        public String regexErrorMessage;

        public FormInputRegexValidation() {
            clear();
        }

        public FormInputRegexValidation clear() {
            this.regexErrorMessage = "";
            this.hasRegexErrorMessage = false;
            this.regex = "";
            this.hasRegex = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasRegexErrorMessage || !this.regexErrorMessage.equals("")) {
                output.writeString(1, this.regexErrorMessage);
            }
            if (this.hasRegex || !this.regex.equals("")) {
                output.writeString(2, this.regex);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasRegexErrorMessage || !this.regexErrorMessage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.regexErrorMessage);
            }
            if (this.hasRegex || !this.regex.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.regex);
            }
            return size;
        }

        public FormInputRegexValidation mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.regexErrorMessage = input.readString();
                        this.hasRegexErrorMessage = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.regex = input.readString();
                        this.hasRegex = true;
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

    public static final class PaypalAuthResponse extends MessageNano {
        public byte[] encryptedAuthMessage;
        public boolean hasEncryptedAuthMessage;
        public boolean hasHashedDeviceId;
        public String hashedDeviceId;

        public PaypalAuthResponse() {
            clear();
        }

        public PaypalAuthResponse clear() {
            this.encryptedAuthMessage = WireFormatNano.EMPTY_BYTES;
            this.hasEncryptedAuthMessage = false;
            this.hashedDeviceId = "";
            this.hasHashedDeviceId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasEncryptedAuthMessage || !Arrays.equals(this.encryptedAuthMessage, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(1, this.encryptedAuthMessage);
            }
            if (this.hasHashedDeviceId || !this.hashedDeviceId.equals("")) {
                output.writeString(2, this.hashedDeviceId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasEncryptedAuthMessage || !Arrays.equals(this.encryptedAuthMessage, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(1, this.encryptedAuthMessage);
            }
            if (this.hasHashedDeviceId || !this.hashedDeviceId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.hashedDeviceId);
            }
            return size;
        }

        public PaypalAuthResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.encryptedAuthMessage = input.readBytes();
                        this.hasEncryptedAuthMessage = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.hashedDeviceId = input.readString();
                        this.hasHashedDeviceId = true;
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

    public static final class ProfileForm extends MessageNano {
        public AddressFormField addressField;
        public TosFormField tosField;

        public ProfileForm() {
            clear();
        }

        public ProfileForm clear() {
            this.addressField = null;
            this.tosField = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.addressField != null) {
                output.writeMessage(1, this.addressField);
            }
            if (this.tosField != null) {
                output.writeMessage(2, this.tosField);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.addressField != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.addressField);
            }
            if (this.tosField != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.tosField);
            }
            return size;
        }

        public ProfileForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.addressField == null) {
                            this.addressField = new AddressFormField();
                        }
                        input.readMessage(this.addressField);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.tosField == null) {
                            this.tosField = new TosFormField();
                        }
                        input.readMessage(this.tosField);
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

    public static final class ProfileFormInput extends MessageNano {
        public AddressFormInput customerAddress;
        public TosAcceptanceFormInput tos;

        public ProfileFormInput() {
            clear();
        }

        public ProfileFormInput clear() {
            this.customerAddress = null;
            this.tos = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.customerAddress != null) {
                output.writeMessage(1, this.customerAddress);
            }
            if (this.tos != null) {
                output.writeMessage(2, this.tos);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.customerAddress != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.customerAddress);
            }
            if (this.tos != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.tos);
            }
            return size;
        }

        public ProfileFormInput mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.customerAddress == null) {
                            this.customerAddress = new AddressFormInput();
                        }
                        input.readMessage(this.customerAddress);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.tos == null) {
                            this.tos = new TosAcceptanceFormInput();
                        }
                        input.readMessage(this.tos);
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

    public static final class ResponseFormat extends MessageNano {
        public byte[] encryptionKey;
        public boolean hasEncryptionKey;
        public boolean hasResponseFormatType;
        public boolean hasVendorSpecificSalt;
        public int responseFormatType;
        public String vendorSpecificSalt;

        public ResponseFormat() {
            clear();
        }

        public ResponseFormat clear() {
            this.responseFormatType = 0;
            this.hasResponseFormatType = false;
            this.encryptionKey = WireFormatNano.EMPTY_BYTES;
            this.hasEncryptionKey = false;
            this.vendorSpecificSalt = "";
            this.hasVendorSpecificSalt = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.responseFormatType != 0 || this.hasResponseFormatType) {
                output.writeInt32(1, this.responseFormatType);
            }
            if (this.hasEncryptionKey || !Arrays.equals(this.encryptionKey, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.encryptionKey);
            }
            if (this.hasVendorSpecificSalt || !this.vendorSpecificSalt.equals("")) {
                output.writeString(3, this.vendorSpecificSalt);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.responseFormatType != 0 || this.hasResponseFormatType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.responseFormatType);
            }
            if (this.hasEncryptionKey || !Arrays.equals(this.encryptionKey, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.encryptionKey);
            }
            if (this.hasVendorSpecificSalt || !this.vendorSpecificSalt.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.vendorSpecificSalt);
            }
            return size;
        }

        public ResponseFormat mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.responseFormatType = value;
                                this.hasResponseFormatType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.encryptionKey = input.readBytes();
                        this.hasEncryptionKey = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.vendorSpecificSalt = input.readString();
                        this.hasVendorSpecificSalt = true;
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

    public static final class TextFormField extends MessageNano {
        public int contentType;
        public String defaultValue;
        public String errorMessage;
        public boolean hasContentType;
        public boolean hasDefaultValue;
        public boolean hasErrorMessage;
        public boolean hasLabel;
        public boolean hasUseBestGuess;
        public String label;
        public FormInputRegexValidation regexValidation;
        public ResponseFormat responseFormat;
        public boolean useBestGuess;

        public TextFormField() {
            clear();
        }

        public TextFormField clear() {
            this.label = "";
            this.hasLabel = false;
            this.contentType = 0;
            this.hasContentType = false;
            this.responseFormat = null;
            this.regexValidation = null;
            this.errorMessage = "";
            this.hasErrorMessage = false;
            this.defaultValue = "";
            this.hasDefaultValue = false;
            this.useBestGuess = false;
            this.hasUseBestGuess = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLabel || !this.label.equals("")) {
                output.writeString(1, this.label);
            }
            if (this.contentType != 0 || this.hasContentType) {
                output.writeInt32(2, this.contentType);
            }
            if (this.responseFormat != null) {
                output.writeMessage(3, this.responseFormat);
            }
            if (this.regexValidation != null) {
                output.writeMessage(4, this.regexValidation);
            }
            if (this.hasErrorMessage || !this.errorMessage.equals("")) {
                output.writeString(5, this.errorMessage);
            }
            if (this.hasDefaultValue || !this.defaultValue.equals("")) {
                output.writeString(6, this.defaultValue);
            }
            if (this.hasUseBestGuess || this.useBestGuess) {
                output.writeBool(7, this.useBestGuess);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLabel || !this.label.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.label);
            }
            if (this.contentType != 0 || this.hasContentType) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.contentType);
            }
            if (this.responseFormat != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.responseFormat);
            }
            if (this.regexValidation != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.regexValidation);
            }
            if (this.hasErrorMessage || !this.errorMessage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.errorMessage);
            }
            if (this.hasDefaultValue || !this.defaultValue.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.defaultValue);
            }
            if (this.hasUseBestGuess || this.useBestGuess) {
                return size + CodedOutputByteBufferNano.computeBoolSize(7, this.useBestGuess);
            }
            return size;
        }

        public TextFormField mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.label = input.readString();
                        this.hasLabel = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
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
                                this.contentType = value;
                                this.hasContentType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.responseFormat == null) {
                            this.responseFormat = new ResponseFormat();
                        }
                        input.readMessage(this.responseFormat);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.regexValidation == null) {
                            this.regexValidation = new FormInputRegexValidation();
                        }
                        input.readMessage(this.regexValidation);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.errorMessage = input.readString();
                        this.hasErrorMessage = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.defaultValue = input.readString();
                        this.hasDefaultValue = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.useBestGuess = input.readBool();
                        this.hasUseBestGuess = true;
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

    public static final class TextFormInput extends MessageNano {
        public boolean hasPlaintextResponse;
        public PaypalAuthResponse paypalAuthResponse;
        public String plaintextResponse;

        public TextFormInput() {
            clear();
        }

        public TextFormInput clear() {
            this.plaintextResponse = "";
            this.hasPlaintextResponse = false;
            this.paypalAuthResponse = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPlaintextResponse || !this.plaintextResponse.equals("")) {
                output.writeString(1, this.plaintextResponse);
            }
            if (this.paypalAuthResponse != null) {
                output.writeMessage(2, this.paypalAuthResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPlaintextResponse || !this.plaintextResponse.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.plaintextResponse);
            }
            if (this.paypalAuthResponse != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.paypalAuthResponse);
            }
            return size;
        }

        public TextFormInput mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.plaintextResponse = input.readString();
                        this.hasPlaintextResponse = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.paypalAuthResponse == null) {
                            this.paypalAuthResponse = new PaypalAuthResponse();
                        }
                        input.readMessage(this.paypalAuthResponse);
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

    public static final class TosAcceptanceFormInput extends MessageNano {
        public boolean hasTosId;
        public String tosId;

        public TosAcceptanceFormInput() {
            clear();
        }

        public TosAcceptanceFormInput clear() {
            this.tosId = "";
            this.hasTosId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTosId || !this.tosId.equals("")) {
                output.writeString(1, this.tosId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTosId || !this.tosId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.tosId);
            }
            return size;
        }

        public TosAcceptanceFormInput mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.tosId = input.readString();
                        this.hasTosId = true;
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

    public static final class TosFormField extends MessageNano {
        public boolean hasTosHtml;
        public boolean hasTosHtmlShort;
        public boolean hasTosId;
        public boolean hasTosUrl;
        public String tosHtml;
        public String tosHtmlShort;
        public String tosId;
        public String tosUrl;

        public TosFormField() {
            clear();
        }

        public TosFormField clear() {
            this.tosId = "";
            this.hasTosId = false;
            this.tosHtml = "";
            this.hasTosHtml = false;
            this.tosHtmlShort = "";
            this.hasTosHtmlShort = false;
            this.tosUrl = "";
            this.hasTosUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTosId || !this.tosId.equals("")) {
                output.writeString(1, this.tosId);
            }
            if (this.hasTosHtml || !this.tosHtml.equals("")) {
                output.writeString(2, this.tosHtml);
            }
            if (this.hasTosHtmlShort || !this.tosHtmlShort.equals("")) {
                output.writeString(3, this.tosHtmlShort);
            }
            if (this.hasTosUrl || !this.tosUrl.equals("")) {
                output.writeString(4, this.tosUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTosId || !this.tosId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.tosId);
            }
            if (this.hasTosHtml || !this.tosHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.tosHtml);
            }
            if (this.hasTosHtmlShort || !this.tosHtmlShort.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.tosHtmlShort);
            }
            if (this.hasTosUrl || !this.tosUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.tosUrl);
            }
            return size;
        }

        public TosFormField mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.tosId = input.readString();
                        this.hasTosId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.tosHtml = input.readString();
                        this.hasTosHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.tosHtmlShort = input.readString();
                        this.hasTosHtmlShort = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.tosUrl = input.readString();
                        this.hasTosUrl = true;
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

    public static final class UsernamePasswordForm extends MessageNano {
        public String actionTitleText;
        public boolean hasActionTitleText;
        public boolean hasHelpUrl;
        public String helpUrl;
        public Image logoImage;
        public TextFormField passwordField;
        public TosFormField tosField;
        public TextFormField usernameField;

        public UsernamePasswordForm() {
            clear();
        }

        public UsernamePasswordForm clear() {
            this.usernameField = null;
            this.passwordField = null;
            this.tosField = null;
            this.actionTitleText = "";
            this.hasActionTitleText = false;
            this.logoImage = null;
            this.helpUrl = "";
            this.hasHelpUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.usernameField != null) {
                output.writeMessage(1, this.usernameField);
            }
            if (this.passwordField != null) {
                output.writeMessage(2, this.passwordField);
            }
            if (this.tosField != null) {
                output.writeMessage(3, this.tosField);
            }
            if (this.hasActionTitleText || !this.actionTitleText.equals("")) {
                output.writeString(4, this.actionTitleText);
            }
            if (this.hasHelpUrl || !this.helpUrl.equals("")) {
                output.writeString(6, this.helpUrl);
            }
            if (this.logoImage != null) {
                output.writeMessage(7, this.logoImage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.usernameField != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.usernameField);
            }
            if (this.passwordField != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.passwordField);
            }
            if (this.tosField != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.tosField);
            }
            if (this.hasActionTitleText || !this.actionTitleText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.actionTitleText);
            }
            if (this.hasHelpUrl || !this.helpUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.helpUrl);
            }
            if (this.logoImage != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.logoImage);
            }
            return size;
        }

        public UsernamePasswordForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.usernameField == null) {
                            this.usernameField = new TextFormField();
                        }
                        input.readMessage(this.usernameField);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.passwordField == null) {
                            this.passwordField = new TextFormField();
                        }
                        input.readMessage(this.passwordField);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.tosField == null) {
                            this.tosField = new TosFormField();
                        }
                        input.readMessage(this.tosField);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.actionTitleText = input.readString();
                        this.hasActionTitleText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.helpUrl = input.readString();
                        this.hasHelpUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.logoImage == null) {
                            this.logoImage = new Image();
                        }
                        input.readMessage(this.logoImage);
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

    public static final class UsernamePasswordFormInput extends MessageNano {
        public TextFormInput password;
        public TosAcceptanceFormInput tos;
        public TextFormInput username;

        public UsernamePasswordFormInput() {
            clear();
        }

        public UsernamePasswordFormInput clear() {
            this.username = null;
            this.password = null;
            this.tos = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.username != null) {
                output.writeMessage(1, this.username);
            }
            if (this.password != null) {
                output.writeMessage(2, this.password);
            }
            if (this.tos != null) {
                output.writeMessage(3, this.tos);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.username != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.username);
            }
            if (this.password != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.password);
            }
            if (this.tos != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.tos);
            }
            return size;
        }

        public UsernamePasswordFormInput mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.username == null) {
                            this.username = new TextFormInput();
                        }
                        input.readMessage(this.username);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.password == null) {
                            this.password = new TextFormInput();
                        }
                        input.readMessage(this.password);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.tos == null) {
                            this.tos = new TosAcceptanceFormInput();
                        }
                        input.readMessage(this.tos);
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
