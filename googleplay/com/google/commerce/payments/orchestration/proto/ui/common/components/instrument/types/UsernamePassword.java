package com.google.commerce.payments.orchestration.proto.ui.common.components.instrument.types;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.components.legal.LegalMessageOuterClass.LegalMessage;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiField;
import com.google.commerce.payments.orchestration.proto.ui.common.generic.UiFieldOuterClass.UiFieldValue;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface UsernamePassword {

    public static final class UsernamePasswordForm extends MessageNano {
        public byte[] credentialsEncryptionKey;
        public int encryptionType;
        public String id;
        public LegalMessage legalMessage;
        public String loginHelpHtml;
        public UiField passwordField;
        public String title;
        public UiField usernameField;
        public String vendorSpecificSalt;

        public UsernamePasswordForm() {
            clear();
        }

        public UsernamePasswordForm clear() {
            this.id = "";
            this.title = "";
            this.encryptionType = 0;
            this.credentialsEncryptionKey = WireFormatNano.EMPTY_BYTES;
            this.vendorSpecificSalt = "";
            this.usernameField = null;
            this.passwordField = null;
            this.loginHelpHtml = "";
            this.legalMessage = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.id.equals("")) {
                output.writeString(1, this.id);
            }
            if (this.encryptionType != 0) {
                output.writeInt32(2, this.encryptionType);
            }
            if (!Arrays.equals(this.credentialsEncryptionKey, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.credentialsEncryptionKey);
            }
            if (this.usernameField != null) {
                output.writeMessage(5, this.usernameField);
            }
            if (this.passwordField != null) {
                output.writeMessage(6, this.passwordField);
            }
            if (!this.loginHelpHtml.equals("")) {
                output.writeString(7, this.loginHelpHtml);
            }
            if (this.legalMessage != null) {
                output.writeMessage(8, this.legalMessage);
            }
            if (!this.title.equals("")) {
                output.writeString(9, this.title);
            }
            if (!this.vendorSpecificSalt.equals("")) {
                output.writeString(10, this.vendorSpecificSalt);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
            }
            if (this.encryptionType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.encryptionType);
            }
            if (!Arrays.equals(this.credentialsEncryptionKey, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(4, this.credentialsEncryptionKey);
            }
            if (this.usernameField != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.usernameField);
            }
            if (this.passwordField != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.passwordField);
            }
            if (!this.loginHelpHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.loginHelpHtml);
            }
            if (this.legalMessage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.legalMessage);
            }
            if (!this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.title);
            }
            if (this.vendorSpecificSalt.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(10, this.vendorSpecificSalt);
        }

        public UsernamePasswordForm mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.id = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.encryptionType = value;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.credentialsEncryptionKey = input.readBytes();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.usernameField == null) {
                            this.usernameField = new UiField();
                        }
                        input.readMessage(this.usernameField);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.passwordField == null) {
                            this.passwordField = new UiField();
                        }
                        input.readMessage(this.passwordField);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.loginHelpHtml = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.legalMessage == null) {
                            this.legalMessage = new LegalMessage();
                        }
                        input.readMessage(this.legalMessage);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.title = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.vendorSpecificSalt = input.readString();
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

    public static final class UsernamePasswordFormValue extends MessageNano {
        public int encryptionType;
        public String hashedDeviceId;
        public String legalDocData;
        public UiFieldValue password;
        public UiFieldValue username;

        public UsernamePasswordFormValue() {
            clear();
        }

        public UsernamePasswordFormValue clear() {
            this.username = null;
            this.password = null;
            this.hashedDeviceId = "";
            this.encryptionType = 0;
            this.legalDocData = "";
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
            if (this.encryptionType != 0) {
                output.writeInt32(3, this.encryptionType);
            }
            if (!this.legalDocData.equals("")) {
                output.writeString(4, this.legalDocData);
            }
            if (!this.hashedDeviceId.equals("")) {
                output.writeString(5, this.hashedDeviceId);
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
            if (this.encryptionType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.encryptionType);
            }
            if (!this.legalDocData.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.legalDocData);
            }
            if (this.hashedDeviceId.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(5, this.hashedDeviceId);
        }

        public UsernamePasswordFormValue mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.username == null) {
                            this.username = new UiFieldValue();
                        }
                        input.readMessage(this.username);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.password == null) {
                            this.password = new UiFieldValue();
                        }
                        input.readMessage(this.password);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.encryptionType = value;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.legalDocData = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.hashedDeviceId = input.readString();
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
