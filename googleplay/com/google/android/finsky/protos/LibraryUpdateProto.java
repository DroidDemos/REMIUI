package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Docid;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface LibraryUpdateProto {

    public static final class ClientLibraryState extends MessageNano {
        private static volatile ClientLibraryState[] _emptyArray;
        public int corpus;
        public boolean hasCorpus;
        public boolean hasHashCodeSum;
        public boolean hasLibraryId;
        public boolean hasLibrarySize;
        public boolean hasServerToken;
        public long hashCodeSum;
        public String libraryId;
        public int librarySize;
        public byte[] serverToken;

        public static ClientLibraryState[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClientLibraryState[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClientLibraryState() {
            clear();
        }

        public ClientLibraryState clear() {
            this.corpus = 0;
            this.hasCorpus = false;
            this.libraryId = "";
            this.hasLibraryId = false;
            this.serverToken = WireFormatNano.EMPTY_BYTES;
            this.hasServerToken = false;
            this.hashCodeSum = 0;
            this.hasHashCodeSum = false;
            this.librarySize = 0;
            this.hasLibrarySize = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpus != 0 || this.hasCorpus) {
                output.writeInt32(1, this.corpus);
            }
            if (this.hasServerToken || !Arrays.equals(this.serverToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.serverToken);
            }
            if (this.hasHashCodeSum || this.hashCodeSum != 0) {
                output.writeInt64(3, this.hashCodeSum);
            }
            if (this.hasLibrarySize || this.librarySize != 0) {
                output.writeInt32(4, this.librarySize);
            }
            if (this.hasLibraryId || !this.libraryId.equals("")) {
                output.writeString(5, this.libraryId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpus != 0 || this.hasCorpus) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.corpus);
            }
            if (this.hasServerToken || !Arrays.equals(this.serverToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.serverToken);
            }
            if (this.hasHashCodeSum || this.hashCodeSum != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.hashCodeSum);
            }
            if (this.hasLibrarySize || this.librarySize != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.librarySize);
            }
            if (this.hasLibraryId || !this.libraryId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.libraryId);
            }
            return size;
        }

        public ClientLibraryState mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.corpus = value;
                                this.hasCorpus = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.serverToken = input.readBytes();
                        this.hasServerToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.hashCodeSum = input.readInt64();
                        this.hasHashCodeSum = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.librarySize = input.readInt32();
                        this.hasLibrarySize = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.libraryId = input.readString();
                        this.hasLibraryId = true;
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

    public static final class LibraryAppDetails extends MessageNano {
        public String[] certificateHash;
        public boolean hasPostDeliveryRefundWindowMsec;
        public boolean hasRefundTimeoutTimestampMsec;
        public long postDeliveryRefundWindowMsec;
        public long refundTimeoutTimestampMsec;

        public LibraryAppDetails() {
            clear();
        }

        public LibraryAppDetails clear() {
            this.certificateHash = WireFormatNano.EMPTY_STRING_ARRAY;
            this.refundTimeoutTimestampMsec = 0;
            this.hasRefundTimeoutTimestampMsec = false;
            this.postDeliveryRefundWindowMsec = 0;
            this.hasPostDeliveryRefundWindowMsec = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.certificateHash != null && this.certificateHash.length > 0) {
                for (String element : this.certificateHash) {
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            if (this.hasRefundTimeoutTimestampMsec || this.refundTimeoutTimestampMsec != 0) {
                output.writeInt64(3, this.refundTimeoutTimestampMsec);
            }
            if (this.hasPostDeliveryRefundWindowMsec || this.postDeliveryRefundWindowMsec != 0) {
                output.writeInt64(4, this.postDeliveryRefundWindowMsec);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.certificateHash != null && this.certificateHash.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.certificateHash) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasRefundTimeoutTimestampMsec || this.refundTimeoutTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.refundTimeoutTimestampMsec);
            }
            if (this.hasPostDeliveryRefundWindowMsec || this.postDeliveryRefundWindowMsec != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(4, this.postDeliveryRefundWindowMsec);
            }
            return size;
        }

        public LibraryAppDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        int i = this.certificateHash == null ? 0 : this.certificateHash.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.certificateHash, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.certificateHash = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.refundTimeoutTimestampMsec = input.readInt64();
                        this.hasRefundTimeoutTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.postDeliveryRefundWindowMsec = input.readInt64();
                        this.hasPostDeliveryRefundWindowMsec = true;
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

    public static final class LibraryInAppDetails extends MessageNano {
        public boolean hasSignature;
        public boolean hasSignedPurchaseData;
        public String signature;
        public String signedPurchaseData;

        public LibraryInAppDetails() {
            clear();
        }

        public LibraryInAppDetails clear() {
            this.signedPurchaseData = "";
            this.hasSignedPurchaseData = false;
            this.signature = "";
            this.hasSignature = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSignedPurchaseData || !this.signedPurchaseData.equals("")) {
                output.writeString(1, this.signedPurchaseData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                output.writeString(2, this.signature);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSignedPurchaseData || !this.signedPurchaseData.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.signedPurchaseData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.signature);
            }
            return size;
        }

        public LibraryInAppDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.signedPurchaseData = input.readString();
                        this.hasSignedPurchaseData = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.signature = input.readString();
                        this.hasSignature = true;
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

    public static final class LibraryMutation extends MessageNano {
        private static volatile LibraryMutation[] _emptyArray;
        public LibraryAppDetails appDetails;
        public boolean deleted;
        public Docid docid;
        public long documentHash;
        public boolean hasDeleted;
        public boolean hasDocumentHash;
        public boolean hasOfferType;
        public boolean hasPreordered;
        public boolean hasValidUntilTimestampMsec;
        public LibraryInAppDetails inAppDetails;
        public int offerType;
        public boolean preordered;
        public LibrarySubscriptionDetails subscriptionDetails;
        public long validUntilTimestampMsec;

        public static LibraryMutation[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LibraryMutation[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LibraryMutation() {
            clear();
        }

        public LibraryMutation clear() {
            this.docid = null;
            this.offerType = 1;
            this.hasOfferType = false;
            this.documentHash = 0;
            this.hasDocumentHash = false;
            this.deleted = false;
            this.hasDeleted = false;
            this.validUntilTimestampMsec = 0;
            this.hasValidUntilTimestampMsec = false;
            this.preordered = false;
            this.hasPreordered = false;
            this.appDetails = null;
            this.subscriptionDetails = null;
            this.inAppDetails = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docid != null) {
                output.writeMessage(1, this.docid);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                output.writeInt32(2, this.offerType);
            }
            if (this.hasDocumentHash || this.documentHash != 0) {
                output.writeInt64(3, this.documentHash);
            }
            if (this.hasDeleted || this.deleted) {
                output.writeBool(4, this.deleted);
            }
            if (this.appDetails != null) {
                output.writeMessage(5, this.appDetails);
            }
            if (this.subscriptionDetails != null) {
                output.writeMessage(6, this.subscriptionDetails);
            }
            if (this.inAppDetails != null) {
                output.writeMessage(7, this.inAppDetails);
            }
            if (this.hasValidUntilTimestampMsec || this.validUntilTimestampMsec != 0) {
                output.writeInt64(8, this.validUntilTimestampMsec);
            }
            if (this.hasPreordered || this.preordered) {
                output.writeBool(9, this.preordered);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.docid);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.offerType);
            }
            if (this.hasDocumentHash || this.documentHash != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.documentHash);
            }
            if (this.hasDeleted || this.deleted) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.deleted);
            }
            if (this.appDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.appDetails);
            }
            if (this.subscriptionDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.subscriptionDetails);
            }
            if (this.inAppDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.inAppDetails);
            }
            if (this.hasValidUntilTimestampMsec || this.validUntilTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(8, this.validUntilTimestampMsec);
            }
            if (this.hasPreordered || this.preordered) {
                return size + CodedOutputByteBufferNano.computeBoolSize(9, this.preordered);
            }
            return size;
        }

        public LibraryMutation mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.docid == null) {
                            this.docid = new Docid();
                        }
                        input.readMessage(this.docid);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
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
                                this.offerType = value;
                                this.hasOfferType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.documentHash = input.readInt64();
                        this.hasDocumentHash = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.deleted = input.readBool();
                        this.hasDeleted = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.appDetails == null) {
                            this.appDetails = new LibraryAppDetails();
                        }
                        input.readMessage(this.appDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.subscriptionDetails == null) {
                            this.subscriptionDetails = new LibrarySubscriptionDetails();
                        }
                        input.readMessage(this.subscriptionDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.inAppDetails == null) {
                            this.inAppDetails = new LibraryInAppDetails();
                        }
                        input.readMessage(this.inAppDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.validUntilTimestampMsec = input.readInt64();
                        this.hasValidUntilTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.preordered = input.readBool();
                        this.hasPreordered = true;
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

    public static final class LibrarySubscriptionDetails extends MessageNano {
        public boolean autoRenewing;
        public long deprecatedValidUntilTimestampMsec;
        public boolean hasAutoRenewing;
        public boolean hasDeprecatedValidUntilTimestampMsec;
        public boolean hasInitiationTimestampMsec;
        public boolean hasSignature;
        public boolean hasSignedPurchaseData;
        public boolean hasTrialUntilTimestampMsec;
        public long initiationTimestampMsec;
        public String signature;
        public String signedPurchaseData;
        public long trialUntilTimestampMsec;

        public LibrarySubscriptionDetails() {
            clear();
        }

        public LibrarySubscriptionDetails clear() {
            this.initiationTimestampMsec = 0;
            this.hasInitiationTimestampMsec = false;
            this.deprecatedValidUntilTimestampMsec = 0;
            this.hasDeprecatedValidUntilTimestampMsec = false;
            this.autoRenewing = false;
            this.hasAutoRenewing = false;
            this.trialUntilTimestampMsec = 0;
            this.hasTrialUntilTimestampMsec = false;
            this.signedPurchaseData = "";
            this.hasSignedPurchaseData = false;
            this.signature = "";
            this.hasSignature = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasInitiationTimestampMsec || this.initiationTimestampMsec != 0) {
                output.writeInt64(1, this.initiationTimestampMsec);
            }
            if (this.hasDeprecatedValidUntilTimestampMsec || this.deprecatedValidUntilTimestampMsec != 0) {
                output.writeInt64(2, this.deprecatedValidUntilTimestampMsec);
            }
            if (this.hasAutoRenewing || this.autoRenewing) {
                output.writeBool(3, this.autoRenewing);
            }
            if (this.hasTrialUntilTimestampMsec || this.trialUntilTimestampMsec != 0) {
                output.writeInt64(4, this.trialUntilTimestampMsec);
            }
            if (this.hasSignedPurchaseData || !this.signedPurchaseData.equals("")) {
                output.writeString(5, this.signedPurchaseData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                output.writeString(6, this.signature);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasInitiationTimestampMsec || this.initiationTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.initiationTimestampMsec);
            }
            if (this.hasDeprecatedValidUntilTimestampMsec || this.deprecatedValidUntilTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.deprecatedValidUntilTimestampMsec);
            }
            if (this.hasAutoRenewing || this.autoRenewing) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.autoRenewing);
            }
            if (this.hasTrialUntilTimestampMsec || this.trialUntilTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.trialUntilTimestampMsec);
            }
            if (this.hasSignedPurchaseData || !this.signedPurchaseData.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.signedPurchaseData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(6, this.signature);
            }
            return size;
        }

        public LibrarySubscriptionDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.deprecatedValidUntilTimestampMsec = input.readInt64();
                        this.hasDeprecatedValidUntilTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.autoRenewing = input.readBool();
                        this.hasAutoRenewing = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.trialUntilTimestampMsec = input.readInt64();
                        this.hasTrialUntilTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.signedPurchaseData = input.readString();
                        this.hasSignedPurchaseData = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.signature = input.readString();
                        this.hasSignature = true;
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

    public static final class LibraryUpdate extends MessageNano {
        private static volatile LibraryUpdate[] _emptyArray;
        public int corpus;
        public boolean hasCorpus;
        public boolean hasHasMore;
        public boolean hasLibraryId;
        public boolean hasMore;
        public boolean hasServerToken;
        public boolean hasStatus;
        public String libraryId;
        public LibraryMutation[] mutation;
        public byte[] serverToken;
        public int status;

        public static LibraryUpdate[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LibraryUpdate[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LibraryUpdate() {
            clear();
        }

        public LibraryUpdate clear() {
            this.status = 1;
            this.hasStatus = false;
            this.corpus = 0;
            this.hasCorpus = false;
            this.libraryId = "";
            this.hasLibraryId = false;
            this.serverToken = WireFormatNano.EMPTY_BYTES;
            this.hasServerToken = false;
            this.mutation = LibraryMutation.emptyArray();
            this.hasMore = false;
            this.hasHasMore = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 1 || this.hasStatus) {
                output.writeInt32(1, this.status);
            }
            if (this.corpus != 0 || this.hasCorpus) {
                output.writeInt32(2, this.corpus);
            }
            if (this.hasServerToken || !Arrays.equals(this.serverToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(3, this.serverToken);
            }
            if (this.mutation != null && this.mutation.length > 0) {
                for (LibraryMutation element : this.mutation) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.hasHasMore || this.hasMore) {
                output.writeBool(5, this.hasMore);
            }
            if (this.hasLibraryId || !this.libraryId.equals("")) {
                output.writeString(6, this.libraryId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 1 || this.hasStatus) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            if (this.corpus != 0 || this.hasCorpus) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.corpus);
            }
            if (this.hasServerToken || !Arrays.equals(this.serverToken, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(3, this.serverToken);
            }
            if (this.mutation != null && this.mutation.length > 0) {
                for (LibraryMutation element : this.mutation) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.hasHasMore || this.hasMore) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.hasMore);
            }
            if (this.hasLibraryId || !this.libraryId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(6, this.libraryId);
            }
            return size;
        }

        public LibraryUpdate mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.status = value;
                                this.hasStatus = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.corpus = value;
                                this.hasCorpus = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.serverToken = input.readBytes();
                        this.hasServerToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.mutation == null) {
                            i = 0;
                        } else {
                            i = this.mutation.length;
                        }
                        LibraryMutation[] newArray = new LibraryMutation[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.mutation, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LibraryMutation();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new LibraryMutation();
                        input.readMessage(newArray[i]);
                        this.mutation = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.hasMore = input.readBool();
                        this.hasHasMore = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.libraryId = input.readString();
                        this.hasLibraryId = true;
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
