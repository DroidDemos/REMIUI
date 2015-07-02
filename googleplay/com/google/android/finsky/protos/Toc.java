package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Toc {

    public static final class BillingConfig extends MessageNano {
        public CarrierBillingConfig carrierBillingConfig;
        public boolean hasMaxIabApiVersion;
        public int maxIabApiVersion;

        public BillingConfig() {
            clear();
        }

        public BillingConfig clear() {
            this.carrierBillingConfig = null;
            this.maxIabApiVersion = 0;
            this.hasMaxIabApiVersion = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.carrierBillingConfig != null) {
                output.writeMessage(1, this.carrierBillingConfig);
            }
            if (this.hasMaxIabApiVersion || this.maxIabApiVersion != 0) {
                output.writeInt32(2, this.maxIabApiVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.carrierBillingConfig != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.carrierBillingConfig);
            }
            if (this.hasMaxIabApiVersion || this.maxIabApiVersion != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.maxIabApiVersion);
            }
            return size;
        }

        public BillingConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.carrierBillingConfig == null) {
                            this.carrierBillingConfig = new CarrierBillingConfig();
                        }
                        input.readMessage(this.carrierBillingConfig);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.maxIabApiVersion = input.readInt32();
                        this.hasMaxIabApiVersion = true;
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

    public static final class CarrierBillingConfig extends MessageNano {
        public int apiVersion;
        public String credentialsUrl;
        public boolean hasApiVersion;
        public boolean hasCredentialsUrl;
        public boolean hasId;
        public boolean hasName;
        public boolean hasPerTransactionCredentialsRequired;
        public boolean hasProvisioningUrl;
        public boolean hasSendSubscriberIdWithCarrierBillingRequests;
        public boolean hasTosRequired;
        public String id;
        public String name;
        public boolean perTransactionCredentialsRequired;
        public String provisioningUrl;
        public boolean sendSubscriberIdWithCarrierBillingRequests;
        public boolean tosRequired;

        public CarrierBillingConfig() {
            clear();
        }

        public CarrierBillingConfig clear() {
            this.id = "";
            this.hasId = false;
            this.name = "";
            this.hasName = false;
            this.apiVersion = 0;
            this.hasApiVersion = false;
            this.provisioningUrl = "";
            this.hasProvisioningUrl = false;
            this.credentialsUrl = "";
            this.hasCredentialsUrl = false;
            this.tosRequired = false;
            this.hasTosRequired = false;
            this.perTransactionCredentialsRequired = false;
            this.hasPerTransactionCredentialsRequired = false;
            this.sendSubscriberIdWithCarrierBillingRequests = false;
            this.hasSendSubscriberIdWithCarrierBillingRequests = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasId || !this.id.equals("")) {
                output.writeString(1, this.id);
            }
            if (this.hasName || !this.name.equals("")) {
                output.writeString(2, this.name);
            }
            if (this.hasApiVersion || this.apiVersion != 0) {
                output.writeInt32(3, this.apiVersion);
            }
            if (this.hasProvisioningUrl || !this.provisioningUrl.equals("")) {
                output.writeString(4, this.provisioningUrl);
            }
            if (this.hasCredentialsUrl || !this.credentialsUrl.equals("")) {
                output.writeString(5, this.credentialsUrl);
            }
            if (this.hasTosRequired || this.tosRequired) {
                output.writeBool(6, this.tosRequired);
            }
            if (this.hasPerTransactionCredentialsRequired || this.perTransactionCredentialsRequired) {
                output.writeBool(7, this.perTransactionCredentialsRequired);
            }
            if (this.hasSendSubscriberIdWithCarrierBillingRequests || this.sendSubscriberIdWithCarrierBillingRequests) {
                output.writeBool(8, this.sendSubscriberIdWithCarrierBillingRequests);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasId || !this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
            }
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.name);
            }
            if (this.hasApiVersion || this.apiVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.apiVersion);
            }
            if (this.hasProvisioningUrl || !this.provisioningUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.provisioningUrl);
            }
            if (this.hasCredentialsUrl || !this.credentialsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.credentialsUrl);
            }
            if (this.hasTosRequired || this.tosRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.tosRequired);
            }
            if (this.hasPerTransactionCredentialsRequired || this.perTransactionCredentialsRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.perTransactionCredentialsRequired);
            }
            if (this.hasSendSubscriberIdWithCarrierBillingRequests || this.sendSubscriberIdWithCarrierBillingRequests) {
                return size + CodedOutputByteBufferNano.computeBoolSize(8, this.sendSubscriberIdWithCarrierBillingRequests);
            }
            return size;
        }

        public CarrierBillingConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.id = input.readString();
                        this.hasId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.apiVersion = input.readInt32();
                        this.hasApiVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.provisioningUrl = input.readString();
                        this.hasProvisioningUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.credentialsUrl = input.readString();
                        this.hasCredentialsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.tosRequired = input.readBool();
                        this.hasTosRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.perTransactionCredentialsRequired = input.readBool();
                        this.hasPerTransactionCredentialsRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.sendSubscriberIdWithCarrierBillingRequests = input.readBool();
                        this.hasSendSubscriberIdWithCarrierBillingRequests = true;
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

    public static final class CorpusMetadata extends MessageNano {
        private static volatile CorpusMetadata[] _emptyArray;
        public int backend;
        public boolean hasBackend;
        public boolean hasLandingUrl;
        public boolean hasLibraryName;
        public boolean hasName;
        public boolean hasRecsWidgetUrl;
        public boolean hasShopName;
        public String landingUrl;
        public String libraryName;
        public String name;
        public String recsWidgetUrl;
        public String shopName;

        public static CorpusMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CorpusMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CorpusMetadata() {
            clear();
        }

        public CorpusMetadata clear() {
            this.backend = 0;
            this.hasBackend = false;
            this.name = "";
            this.hasName = false;
            this.landingUrl = "";
            this.hasLandingUrl = false;
            this.libraryName = "";
            this.hasLibraryName = false;
            this.shopName = "";
            this.hasShopName = false;
            this.recsWidgetUrl = "";
            this.hasRecsWidgetUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.backend != 0 || this.hasBackend) {
                output.writeInt32(1, this.backend);
            }
            if (this.hasName || !this.name.equals("")) {
                output.writeString(2, this.name);
            }
            if (this.hasLandingUrl || !this.landingUrl.equals("")) {
                output.writeString(3, this.landingUrl);
            }
            if (this.hasLibraryName || !this.libraryName.equals("")) {
                output.writeString(4, this.libraryName);
            }
            if (this.hasRecsWidgetUrl || !this.recsWidgetUrl.equals("")) {
                output.writeString(6, this.recsWidgetUrl);
            }
            if (this.hasShopName || !this.shopName.equals("")) {
                output.writeString(7, this.shopName);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.backend != 0 || this.hasBackend) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.backend);
            }
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.name);
            }
            if (this.hasLandingUrl || !this.landingUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.landingUrl);
            }
            if (this.hasLibraryName || !this.libraryName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.libraryName);
            }
            if (this.hasRecsWidgetUrl || !this.recsWidgetUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.recsWidgetUrl);
            }
            if (this.hasShopName || !this.shopName.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(7, this.shopName);
            }
            return size;
        }

        public CorpusMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.backend = value;
                                this.hasBackend = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.landingUrl = input.readString();
                        this.hasLandingUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.libraryName = input.readString();
                        this.hasLibraryName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.recsWidgetUrl = input.readString();
                        this.hasRecsWidgetUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.shopName = input.readString();
                        this.hasShopName = true;
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

    public static final class Experiments extends MessageNano {
        public String[] experimentId;

        public Experiments() {
            clear();
        }

        public Experiments clear() {
            this.experimentId = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.experimentId != null && this.experimentId.length > 0) {
                for (String element : this.experimentId) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.experimentId == null || this.experimentId.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element : this.experimentId) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public Experiments mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        int i = this.experimentId == null ? 0 : this.experimentId.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.experimentId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.experimentId = newArray;
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

    public static final class SelfUpdateConfig extends MessageNano {
        public boolean hasLatestClientVersionCode;
        public int latestClientVersionCode;

        public SelfUpdateConfig() {
            clear();
        }

        public SelfUpdateConfig clear() {
            this.latestClientVersionCode = 0;
            this.hasLatestClientVersionCode = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLatestClientVersionCode || this.latestClientVersionCode != 0) {
                output.writeInt32(1, this.latestClientVersionCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLatestClientVersionCode || this.latestClientVersionCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.latestClientVersionCode);
            }
            return size;
        }

        public SelfUpdateConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.latestClientVersionCode = input.readInt32();
                        this.hasLatestClientVersionCode = true;
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

    public static final class TocResponse extends MessageNano {
        public boolean ageVerificationRequired;
        public BillingConfig billingConfig;
        public CorpusMetadata[] corpus;
        public Experiments experiments;
        public boolean gplusSignupEnabled;
        public boolean hasAgeVerificationRequired;
        public boolean hasGplusSignupEnabled;
        public boolean hasHelpUrl;
        public boolean hasHomeUrl;
        public boolean hasIconOverrideUrl;
        public boolean hasRecsWidgetUrl;
        public boolean hasRedeemEnabled;
        public boolean hasRequiresUploadDeviceConfig;
        public boolean hasSocialHomeUrl;
        public boolean hasThemeId;
        public boolean hasTosCheckboxTextMarketingEmails;
        public boolean hasTosContent;
        public boolean hasTosToken;
        public boolean hasTosVersionDeprecated;
        public String helpUrl;
        public String homeUrl;
        public String iconOverrideUrl;
        public String recsWidgetUrl;
        public boolean redeemEnabled;
        public boolean requiresUploadDeviceConfig;
        public SelfUpdateConfig selfUpdateConfig;
        public String socialHomeUrl;
        public int themeId;
        public String tosCheckboxTextMarketingEmails;
        public String tosContent;
        public String tosToken;
        public int tosVersionDeprecated;
        public UserSettings userSettings;

        public TocResponse() {
            clear();
        }

        public TocResponse clear() {
            this.corpus = CorpusMetadata.emptyArray();
            this.tosVersionDeprecated = 0;
            this.hasTosVersionDeprecated = false;
            this.tosContent = "";
            this.hasTosContent = false;
            this.tosCheckboxTextMarketingEmails = "";
            this.hasTosCheckboxTextMarketingEmails = false;
            this.tosToken = "";
            this.hasTosToken = false;
            this.homeUrl = "";
            this.hasHomeUrl = false;
            this.experiments = null;
            this.userSettings = null;
            this.iconOverrideUrl = "";
            this.hasIconOverrideUrl = false;
            this.selfUpdateConfig = null;
            this.requiresUploadDeviceConfig = false;
            this.hasRequiresUploadDeviceConfig = false;
            this.billingConfig = null;
            this.recsWidgetUrl = "";
            this.hasRecsWidgetUrl = false;
            this.socialHomeUrl = "";
            this.hasSocialHomeUrl = false;
            this.ageVerificationRequired = false;
            this.hasAgeVerificationRequired = false;
            this.gplusSignupEnabled = false;
            this.hasGplusSignupEnabled = false;
            this.redeemEnabled = false;
            this.hasRedeemEnabled = false;
            this.helpUrl = "";
            this.hasHelpUrl = false;
            this.themeId = 0;
            this.hasThemeId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.corpus != null && this.corpus.length > 0) {
                for (CorpusMetadata element : this.corpus) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasTosVersionDeprecated || this.tosVersionDeprecated != 0) {
                output.writeInt32(2, this.tosVersionDeprecated);
            }
            if (this.hasTosContent || !this.tosContent.equals("")) {
                output.writeString(3, this.tosContent);
            }
            if (this.hasHomeUrl || !this.homeUrl.equals("")) {
                output.writeString(4, this.homeUrl);
            }
            if (this.experiments != null) {
                output.writeMessage(5, this.experiments);
            }
            if (this.hasTosCheckboxTextMarketingEmails || !this.tosCheckboxTextMarketingEmails.equals("")) {
                output.writeString(6, this.tosCheckboxTextMarketingEmails);
            }
            if (this.hasTosToken || !this.tosToken.equals("")) {
                output.writeString(7, this.tosToken);
            }
            if (this.userSettings != null) {
                output.writeMessage(8, this.userSettings);
            }
            if (this.hasIconOverrideUrl || !this.iconOverrideUrl.equals("")) {
                output.writeString(9, this.iconOverrideUrl);
            }
            if (this.selfUpdateConfig != null) {
                output.writeMessage(10, this.selfUpdateConfig);
            }
            if (this.hasRequiresUploadDeviceConfig || this.requiresUploadDeviceConfig) {
                output.writeBool(11, this.requiresUploadDeviceConfig);
            }
            if (this.billingConfig != null) {
                output.writeMessage(12, this.billingConfig);
            }
            if (this.hasRecsWidgetUrl || !this.recsWidgetUrl.equals("")) {
                output.writeString(13, this.recsWidgetUrl);
            }
            if (this.hasSocialHomeUrl || !this.socialHomeUrl.equals("")) {
                output.writeString(15, this.socialHomeUrl);
            }
            if (this.hasAgeVerificationRequired || this.ageVerificationRequired) {
                output.writeBool(16, this.ageVerificationRequired);
            }
            if (this.hasGplusSignupEnabled || this.gplusSignupEnabled) {
                output.writeBool(17, this.gplusSignupEnabled);
            }
            if (this.hasRedeemEnabled || this.redeemEnabled) {
                output.writeBool(18, this.redeemEnabled);
            }
            if (this.hasHelpUrl || !this.helpUrl.equals("")) {
                output.writeString(19, this.helpUrl);
            }
            if (this.themeId != 0 || this.hasThemeId) {
                output.writeInt32(20, this.themeId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.corpus != null && this.corpus.length > 0) {
                for (CorpusMetadata element : this.corpus) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasTosVersionDeprecated || this.tosVersionDeprecated != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.tosVersionDeprecated);
            }
            if (this.hasTosContent || !this.tosContent.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.tosContent);
            }
            if (this.hasHomeUrl || !this.homeUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.homeUrl);
            }
            if (this.experiments != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.experiments);
            }
            if (this.hasTosCheckboxTextMarketingEmails || !this.tosCheckboxTextMarketingEmails.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.tosCheckboxTextMarketingEmails);
            }
            if (this.hasTosToken || !this.tosToken.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.tosToken);
            }
            if (this.userSettings != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.userSettings);
            }
            if (this.hasIconOverrideUrl || !this.iconOverrideUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.iconOverrideUrl);
            }
            if (this.selfUpdateConfig != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.selfUpdateConfig);
            }
            if (this.hasRequiresUploadDeviceConfig || this.requiresUploadDeviceConfig) {
                size += CodedOutputByteBufferNano.computeBoolSize(11, this.requiresUploadDeviceConfig);
            }
            if (this.billingConfig != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.billingConfig);
            }
            if (this.hasRecsWidgetUrl || !this.recsWidgetUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.recsWidgetUrl);
            }
            if (this.hasSocialHomeUrl || !this.socialHomeUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.socialHomeUrl);
            }
            if (this.hasAgeVerificationRequired || this.ageVerificationRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(16, this.ageVerificationRequired);
            }
            if (this.hasGplusSignupEnabled || this.gplusSignupEnabled) {
                size += CodedOutputByteBufferNano.computeBoolSize(17, this.gplusSignupEnabled);
            }
            if (this.hasRedeemEnabled || this.redeemEnabled) {
                size += CodedOutputByteBufferNano.computeBoolSize(18, this.redeemEnabled);
            }
            if (this.hasHelpUrl || !this.helpUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.helpUrl);
            }
            if (this.themeId != 0 || this.hasThemeId) {
                return size + CodedOutputByteBufferNano.computeInt32Size(20, this.themeId);
            }
            return size;
        }

        public TocResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.corpus == null) {
                            i = 0;
                        } else {
                            i = this.corpus.length;
                        }
                        CorpusMetadata[] newArray = new CorpusMetadata[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.corpus, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new CorpusMetadata();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new CorpusMetadata();
                        input.readMessage(newArray[i]);
                        this.corpus = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.tosVersionDeprecated = input.readInt32();
                        this.hasTosVersionDeprecated = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.tosContent = input.readString();
                        this.hasTosContent = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.homeUrl = input.readString();
                        this.hasHomeUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.experiments == null) {
                            this.experiments = new Experiments();
                        }
                        input.readMessage(this.experiments);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.tosCheckboxTextMarketingEmails = input.readString();
                        this.hasTosCheckboxTextMarketingEmails = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.tosToken = input.readString();
                        this.hasTosToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.userSettings == null) {
                            this.userSettings = new UserSettings();
                        }
                        input.readMessage(this.userSettings);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.iconOverrideUrl = input.readString();
                        this.hasIconOverrideUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.selfUpdateConfig == null) {
                            this.selfUpdateConfig = new SelfUpdateConfig();
                        }
                        input.readMessage(this.selfUpdateConfig);
                        continue;
                    case 88:
                        this.requiresUploadDeviceConfig = input.readBool();
                        this.hasRequiresUploadDeviceConfig = true;
                        continue;
                    case 98:
                        if (this.billingConfig == null) {
                            this.billingConfig = new BillingConfig();
                        }
                        input.readMessage(this.billingConfig);
                        continue;
                    case 106:
                        this.recsWidgetUrl = input.readString();
                        this.hasRecsWidgetUrl = true;
                        continue;
                    case 122:
                        this.socialHomeUrl = input.readString();
                        this.hasSocialHomeUrl = true;
                        continue;
                    case 128:
                        this.ageVerificationRequired = input.readBool();
                        this.hasAgeVerificationRequired = true;
                        continue;
                    case 136:
                        this.gplusSignupEnabled = input.readBool();
                        this.hasGplusSignupEnabled = true;
                        continue;
                    case 144:
                        this.redeemEnabled = input.readBool();
                        this.hasRedeemEnabled = true;
                        continue;
                    case 154:
                        this.helpUrl = input.readString();
                        this.hasHelpUrl = true;
                        continue;
                    case 160:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                this.themeId = value;
                                this.hasThemeId = true;
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

    public static final class UserSettings extends MessageNano {
        public boolean hasTosCheckboxMarketingEmailsOptedIn;
        public boolean tosCheckboxMarketingEmailsOptedIn;

        public UserSettings() {
            clear();
        }

        public UserSettings clear() {
            this.tosCheckboxMarketingEmailsOptedIn = false;
            this.hasTosCheckboxMarketingEmailsOptedIn = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTosCheckboxMarketingEmailsOptedIn || this.tosCheckboxMarketingEmailsOptedIn) {
                output.writeBool(1, this.tosCheckboxMarketingEmailsOptedIn);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTosCheckboxMarketingEmailsOptedIn || this.tosCheckboxMarketingEmailsOptedIn) {
                return size + CodedOutputByteBufferNano.computeBoolSize(1, this.tosCheckboxMarketingEmailsOptedIn);
            }
            return size;
        }

        public UserSettings mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.tosCheckboxMarketingEmailsOptedIn = input.readBool();
                        this.hasTosCheckboxMarketingEmailsOptedIn = true;
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
