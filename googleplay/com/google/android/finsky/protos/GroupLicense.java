package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.GroupLicenseKey;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface GroupLicense {

    public static final class GroupLicenseInfo extends MessageNano {
        public long gaiaGroupId;
        public GroupLicenseKey groupLicenseKey;
        public boolean hasGaiaGroupId;
        public boolean hasLicensedOfferType;
        public int licensedOfferType;

        public GroupLicenseInfo() {
            clear();
        }

        public GroupLicenseInfo clear() {
            this.licensedOfferType = 1;
            this.hasLicensedOfferType = false;
            this.gaiaGroupId = 0;
            this.hasGaiaGroupId = false;
            this.groupLicenseKey = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.licensedOfferType != 1 || this.hasLicensedOfferType) {
                output.writeInt32(1, this.licensedOfferType);
            }
            if (this.hasGaiaGroupId || this.gaiaGroupId != 0) {
                output.writeFixed64(2, this.gaiaGroupId);
            }
            if (this.groupLicenseKey != null) {
                output.writeMessage(3, this.groupLicenseKey);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.licensedOfferType != 1 || this.hasLicensedOfferType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.licensedOfferType);
            }
            if (this.hasGaiaGroupId || this.gaiaGroupId != 0) {
                size += CodedOutputByteBufferNano.computeFixed64Size(2, this.gaiaGroupId);
            }
            if (this.groupLicenseKey != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.groupLicenseKey);
            }
            return size;
        }

        public GroupLicenseInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.licensedOfferType = value;
                                this.hasLicensedOfferType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                        this.gaiaGroupId = input.readFixed64();
                        this.hasGaiaGroupId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.groupLicenseKey == null) {
                            this.groupLicenseKey = new GroupLicenseKey();
                        }
                        input.readMessage(this.groupLicenseKey);
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
