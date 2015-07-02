package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.LicensedDocumentInfo;
import com.google.android.finsky.protos.Common.RentalTerms;
import com.google.android.finsky.protos.Common.SignedData;
import com.google.android.finsky.protos.GroupLicense.GroupLicenseInfo;
import com.google.android.finsky.protos.Voucher.LibraryVoucher;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Ownership {

    public static final class OwnershipInfo extends MessageNano {
        public boolean autoRenewing;
        public SignedData developerPurchaseInfo;
        public GroupLicenseInfo groupLicenseInfo;
        public boolean hasAutoRenewing;
        public boolean hasHidden;
        public boolean hasInitiationTimestampMsec;
        public boolean hasLibraryExpirationTimestampMsec;
        public boolean hasPostDeliveryRefundWindowMsec;
        public boolean hasPreordered;
        public boolean hasQuantity;
        public boolean hasRefundTimeoutTimestampMsec;
        public boolean hasValidUntilTimestampMsec;
        public boolean hidden;
        public long initiationTimestampMsec;
        public long libraryExpirationTimestampMsec;
        public LibraryVoucher libraryVoucher;
        public LicensedDocumentInfo licensedDocumentInfo;
        public long postDeliveryRefundWindowMsec;
        public boolean preordered;
        public int quantity;
        public long refundTimeoutTimestampMsec;
        public RentalTerms rentalTerms;
        public long validUntilTimestampMsec;

        public OwnershipInfo() {
            clear();
        }

        public OwnershipInfo clear() {
            this.initiationTimestampMsec = 0;
            this.hasInitiationTimestampMsec = false;
            this.validUntilTimestampMsec = 0;
            this.hasValidUntilTimestampMsec = false;
            this.autoRenewing = false;
            this.hasAutoRenewing = false;
            this.libraryExpirationTimestampMsec = 0;
            this.hasLibraryExpirationTimestampMsec = false;
            this.refundTimeoutTimestampMsec = 0;
            this.hasRefundTimeoutTimestampMsec = false;
            this.postDeliveryRefundWindowMsec = 0;
            this.hasPostDeliveryRefundWindowMsec = false;
            this.developerPurchaseInfo = null;
            this.preordered = false;
            this.hasPreordered = false;
            this.hidden = false;
            this.hasHidden = false;
            this.rentalTerms = null;
            this.groupLicenseInfo = null;
            this.licensedDocumentInfo = null;
            this.quantity = 1;
            this.hasQuantity = false;
            this.libraryVoucher = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasInitiationTimestampMsec || this.initiationTimestampMsec != 0) {
                output.writeInt64(1, this.initiationTimestampMsec);
            }
            if (this.hasValidUntilTimestampMsec || this.validUntilTimestampMsec != 0) {
                output.writeInt64(2, this.validUntilTimestampMsec);
            }
            if (this.hasAutoRenewing || this.autoRenewing) {
                output.writeBool(3, this.autoRenewing);
            }
            if (this.hasRefundTimeoutTimestampMsec || this.refundTimeoutTimestampMsec != 0) {
                output.writeInt64(4, this.refundTimeoutTimestampMsec);
            }
            if (this.hasPostDeliveryRefundWindowMsec || this.postDeliveryRefundWindowMsec != 0) {
                output.writeInt64(5, this.postDeliveryRefundWindowMsec);
            }
            if (this.developerPurchaseInfo != null) {
                output.writeMessage(6, this.developerPurchaseInfo);
            }
            if (this.hasPreordered || this.preordered) {
                output.writeBool(7, this.preordered);
            }
            if (this.hasHidden || this.hidden) {
                output.writeBool(8, this.hidden);
            }
            if (this.rentalTerms != null) {
                output.writeMessage(9, this.rentalTerms);
            }
            if (this.groupLicenseInfo != null) {
                output.writeMessage(10, this.groupLicenseInfo);
            }
            if (this.licensedDocumentInfo != null) {
                output.writeMessage(11, this.licensedDocumentInfo);
            }
            if (this.hasQuantity || this.quantity != 1) {
                output.writeInt32(12, this.quantity);
            }
            if (this.hasLibraryExpirationTimestampMsec || this.libraryExpirationTimestampMsec != 0) {
                output.writeInt64(14, this.libraryExpirationTimestampMsec);
            }
            if (this.libraryVoucher != null) {
                output.writeMessage(15, this.libraryVoucher);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasInitiationTimestampMsec || this.initiationTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.initiationTimestampMsec);
            }
            if (this.hasValidUntilTimestampMsec || this.validUntilTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.validUntilTimestampMsec);
            }
            if (this.hasAutoRenewing || this.autoRenewing) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.autoRenewing);
            }
            if (this.hasRefundTimeoutTimestampMsec || this.refundTimeoutTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.refundTimeoutTimestampMsec);
            }
            if (this.hasPostDeliveryRefundWindowMsec || this.postDeliveryRefundWindowMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.postDeliveryRefundWindowMsec);
            }
            if (this.developerPurchaseInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.developerPurchaseInfo);
            }
            if (this.hasPreordered || this.preordered) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.preordered);
            }
            if (this.hasHidden || this.hidden) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.hidden);
            }
            if (this.rentalTerms != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.rentalTerms);
            }
            if (this.groupLicenseInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.groupLicenseInfo);
            }
            if (this.licensedDocumentInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.licensedDocumentInfo);
            }
            if (this.hasQuantity || this.quantity != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(12, this.quantity);
            }
            if (this.hasLibraryExpirationTimestampMsec || this.libraryExpirationTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(14, this.libraryExpirationTimestampMsec);
            }
            if (this.libraryVoucher != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(15, this.libraryVoucher);
            }
            return size;
        }

        public OwnershipInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.initiationTimestampMsec = input.readInt64();
                        this.hasInitiationTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.validUntilTimestampMsec = input.readInt64();
                        this.hasValidUntilTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.autoRenewing = input.readBool();
                        this.hasAutoRenewing = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.refundTimeoutTimestampMsec = input.readInt64();
                        this.hasRefundTimeoutTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.postDeliveryRefundWindowMsec = input.readInt64();
                        this.hasPostDeliveryRefundWindowMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.developerPurchaseInfo == null) {
                            this.developerPurchaseInfo = new SignedData();
                        }
                        input.readMessage(this.developerPurchaseInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.preordered = input.readBool();
                        this.hasPreordered = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.hidden = input.readBool();
                        this.hasHidden = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.rentalTerms == null) {
                            this.rentalTerms = new RentalTerms();
                        }
                        input.readMessage(this.rentalTerms);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.groupLicenseInfo == null) {
                            this.groupLicenseInfo = new GroupLicenseInfo();
                        }
                        input.readMessage(this.groupLicenseInfo);
                        continue;
                    case 90:
                        if (this.licensedDocumentInfo == null) {
                            this.licensedDocumentInfo = new LicensedDocumentInfo();
                        }
                        input.readMessage(this.licensedDocumentInfo);
                        continue;
                    case 96:
                        this.quantity = input.readInt32();
                        this.hasQuantity = true;
                        continue;
                    case 112:
                        this.libraryExpirationTimestampMsec = input.readInt64();
                        this.hasLibraryExpirationTimestampMsec = true;
                        continue;
                    case 122:
                        if (this.libraryVoucher == null) {
                            this.libraryVoucher = new LibraryVoucher();
                        }
                        input.readMessage(this.libraryVoucher);
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
