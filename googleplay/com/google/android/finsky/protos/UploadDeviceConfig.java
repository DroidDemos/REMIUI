package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface UploadDeviceConfig {

    public static final class UploadDeviceConfigRequest extends MessageNano {
        public DeviceConfigurationProto deviceConfiguration;
        public String gcmRegistrationId;
        public boolean hasGcmRegistrationId;
        public boolean hasManufacturer;
        public String manufacturer;

        public UploadDeviceConfigRequest() {
            clear();
        }

        public UploadDeviceConfigRequest clear() {
            this.deviceConfiguration = null;
            this.manufacturer = "";
            this.hasManufacturer = false;
            this.gcmRegistrationId = "";
            this.hasGcmRegistrationId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deviceConfiguration != null) {
                output.writeMessage(1, this.deviceConfiguration);
            }
            if (this.hasManufacturer || !this.manufacturer.equals("")) {
                output.writeString(2, this.manufacturer);
            }
            if (this.hasGcmRegistrationId || !this.gcmRegistrationId.equals("")) {
                output.writeString(3, this.gcmRegistrationId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deviceConfiguration != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.deviceConfiguration);
            }
            if (this.hasManufacturer || !this.manufacturer.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.manufacturer);
            }
            if (this.hasGcmRegistrationId || !this.gcmRegistrationId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.gcmRegistrationId);
            }
            return size;
        }

        public UploadDeviceConfigRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.deviceConfiguration == null) {
                            this.deviceConfiguration = new DeviceConfigurationProto();
                        }
                        input.readMessage(this.deviceConfiguration);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.manufacturer = input.readString();
                        this.hasManufacturer = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.gcmRegistrationId = input.readString();
                        this.hasGcmRegistrationId = true;
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

    public static final class UploadDeviceConfigResponse extends MessageNano {
        public boolean hasUploadDeviceConfigToken;
        public String uploadDeviceConfigToken;

        public UploadDeviceConfigResponse() {
            clear();
        }

        public UploadDeviceConfigResponse clear() {
            this.uploadDeviceConfigToken = "";
            this.hasUploadDeviceConfigToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUploadDeviceConfigToken || !this.uploadDeviceConfigToken.equals("")) {
                output.writeString(1, this.uploadDeviceConfigToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUploadDeviceConfigToken || !this.uploadDeviceConfigToken.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.uploadDeviceConfigToken);
            }
            return size;
        }

        public UploadDeviceConfigResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.uploadDeviceConfigToken = input.readString();
                        this.hasUploadDeviceConfigToken = true;
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
