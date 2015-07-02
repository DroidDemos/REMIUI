package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Common {

    public static final class Attribution extends MessageNano {
        public boolean hasLicenseTitle;
        public boolean hasLicenseUrl;
        public boolean hasSourceTitle;
        public boolean hasSourceUrl;
        public String licenseTitle;
        public String licenseUrl;
        public String sourceTitle;
        public String sourceUrl;

        public Attribution() {
            clear();
        }

        public Attribution clear() {
            this.sourceTitle = "";
            this.hasSourceTitle = false;
            this.sourceUrl = "";
            this.hasSourceUrl = false;
            this.licenseTitle = "";
            this.hasLicenseTitle = false;
            this.licenseUrl = "";
            this.hasLicenseUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSourceTitle || !this.sourceTitle.equals("")) {
                output.writeString(1, this.sourceTitle);
            }
            if (this.hasSourceUrl || !this.sourceUrl.equals("")) {
                output.writeString(2, this.sourceUrl);
            }
            if (this.hasLicenseTitle || !this.licenseTitle.equals("")) {
                output.writeString(3, this.licenseTitle);
            }
            if (this.hasLicenseUrl || !this.licenseUrl.equals("")) {
                output.writeString(4, this.licenseUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSourceTitle || !this.sourceTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.sourceTitle);
            }
            if (this.hasSourceUrl || !this.sourceUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.sourceUrl);
            }
            if (this.hasLicenseTitle || !this.licenseTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.licenseTitle);
            }
            if (this.hasLicenseUrl || !this.licenseUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.licenseUrl);
            }
            return size;
        }

        public Attribution mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.sourceTitle = input.readString();
                        this.hasSourceTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.sourceUrl = input.readString();
                        this.hasSourceUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.licenseTitle = input.readString();
                        this.hasLicenseTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.licenseUrl = input.readString();
                        this.hasLicenseUrl = true;
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

    public static final class Docid extends MessageNano {
        private static volatile Docid[] _emptyArray;
        public int backend;
        public String backendDocid;
        public boolean hasBackend;
        public boolean hasBackendDocid;
        public boolean hasType;
        public int type;

        public static Docid[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Docid[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Docid() {
            clear();
        }

        public Docid clear() {
            this.backendDocid = "";
            this.hasBackendDocid = false;
            this.type = 1;
            this.hasType = false;
            this.backend = 0;
            this.hasBackend = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasBackendDocid || !this.backendDocid.equals("")) {
                output.writeString(1, this.backendDocid);
            }
            if (this.type != 1 || this.hasType) {
                output.writeInt32(2, this.type);
            }
            if (this.backend != 0 || this.hasBackend) {
                output.writeInt32(3, this.backend);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasBackendDocid || !this.backendDocid.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.backendDocid);
            }
            if (this.type != 1 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.type);
            }
            if (this.backend != 0 || this.hasBackend) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.backend);
            }
            return size;
        }

        public Docid mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.backendDocid = input.readString();
                        this.hasBackendDocid = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
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
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                            case com.google.android.play.R.styleable.Theme_actionModePasteDrawable /*31*/:
                            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                            case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
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
                                this.backend = value;
                                this.hasBackend = true;
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

    public static final class GroupLicenseKey extends MessageNano {
        public long dasherCustomerId;
        public Docid docid;
        public boolean hasDasherCustomerId;
        public boolean hasLicensedOfferType;
        public boolean hasRentalPeriodDays;
        public boolean hasType;
        public int licensedOfferType;
        public int rentalPeriodDays;
        public int type;

        public GroupLicenseKey() {
            clear();
        }

        public GroupLicenseKey clear() {
            this.dasherCustomerId = 0;
            this.hasDasherCustomerId = false;
            this.docid = null;
            this.licensedOfferType = 1;
            this.hasLicensedOfferType = false;
            this.type = 0;
            this.hasType = false;
            this.rentalPeriodDays = 0;
            this.hasRentalPeriodDays = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDasherCustomerId || this.dasherCustomerId != 0) {
                output.writeFixed64(1, this.dasherCustomerId);
            }
            if (this.docid != null) {
                output.writeMessage(2, this.docid);
            }
            if (this.licensedOfferType != 1 || this.hasLicensedOfferType) {
                output.writeInt32(3, this.licensedOfferType);
            }
            if (this.type != 0 || this.hasType) {
                output.writeInt32(4, this.type);
            }
            if (this.hasRentalPeriodDays || this.rentalPeriodDays != 0) {
                output.writeInt32(5, this.rentalPeriodDays);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDasherCustomerId || this.dasherCustomerId != 0) {
                size += CodedOutputByteBufferNano.computeFixed64Size(1, this.dasherCustomerId);
            }
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.docid);
            }
            if (this.licensedOfferType != 1 || this.hasLicensedOfferType) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.licensedOfferType);
            }
            if (this.type != 0 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.type);
            }
            if (this.hasRentalPeriodDays || this.rentalPeriodDays != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.rentalPeriodDays);
            }
            return size;
        }

        public GroupLicenseKey mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                        this.dasherCustomerId = input.readFixed64();
                        this.hasDasherCustomerId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.docid == null) {
                            this.docid = new Docid();
                        }
                        input.readMessage(this.docid);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
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
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.licensedOfferType = value;
                                this.hasLicensedOfferType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.rentalPeriodDays = input.readInt32();
                        this.hasRentalPeriodDays = true;
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

    public static final class Image extends MessageNano {
        private static volatile Image[] _emptyArray;
        public String altTextLocalized;
        public Attribution attribution;
        public boolean autogen;
        public String backgroundColorRgb;
        public Citation citation;
        public Dimension dimension;
        public int durationSeconds;
        public String fillColorRgb;
        public boolean hasAltTextLocalized;
        public boolean hasAutogen;
        public boolean hasBackgroundColorRgb;
        public boolean hasDurationSeconds;
        public boolean hasFillColorRgb;
        public boolean hasImageType;
        public boolean hasImageUrl;
        public boolean hasPositionInSequence;
        public boolean hasSecureUrl;
        public boolean hasSupportsFifeUrlOptions;
        public int imageType;
        public String imageUrl;
        public int positionInSequence;
        public String secureUrl;
        public boolean supportsFifeUrlOptions;

        public static final class Citation extends MessageNano {
            public boolean hasTitleLocalized;
            public boolean hasUrl;
            public String titleLocalized;
            public String url;

            public Citation() {
                clear();
            }

            public Citation clear() {
                this.titleLocalized = "";
                this.hasTitleLocalized = false;
                this.url = "";
                this.hasUrl = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasTitleLocalized || !this.titleLocalized.equals("")) {
                    output.writeString(11, this.titleLocalized);
                }
                if (this.hasUrl || !this.url.equals("")) {
                    output.writeString(12, this.url);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasTitleLocalized || !this.titleLocalized.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(11, this.titleLocalized);
                }
                if (this.hasUrl || !this.url.equals("")) {
                    return size + CodedOutputByteBufferNano.computeStringSize(12, this.url);
                }
                return size;
            }

            public Citation mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case 90:
                            this.titleLocalized = input.readString();
                            this.hasTitleLocalized = true;
                            continue;
                        case 98:
                            this.url = input.readString();
                            this.hasUrl = true;
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

        public static final class Dimension extends MessageNano {
            public int aspectRatio;
            public boolean hasAspectRatio;
            public boolean hasHeight;
            public boolean hasWidth;
            public int height;
            public int width;

            public Dimension() {
                clear();
            }

            public Dimension clear() {
                this.width = 0;
                this.hasWidth = false;
                this.height = 0;
                this.hasHeight = false;
                this.aspectRatio = 0;
                this.hasAspectRatio = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasWidth || this.width != 0) {
                    output.writeInt32(3, this.width);
                }
                if (this.hasHeight || this.height != 0) {
                    output.writeInt32(4, this.height);
                }
                if (this.aspectRatio != 0 || this.hasAspectRatio) {
                    output.writeInt32(18, this.aspectRatio);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasWidth || this.width != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(3, this.width);
                }
                if (this.hasHeight || this.height != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(4, this.height);
                }
                if (this.aspectRatio != 0 || this.hasAspectRatio) {
                    return size + CodedOutputByteBufferNano.computeInt32Size(18, this.aspectRatio);
                }
                return size;
            }

            public Dimension mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                            this.width = input.readInt32();
                            this.hasWidth = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                            this.height = input.readInt32();
                            this.hasHeight = true;
                            continue;
                        case 144:
                            int value = input.readInt32();
                            switch (value) {
                                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                case R.styleable.WalletImFormEditText_required /*4*/:
                                case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                    this.aspectRatio = value;
                                    this.hasAspectRatio = true;
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

        public static Image[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Image[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Image() {
            clear();
        }

        public Image clear() {
            this.imageType = 0;
            this.hasImageType = false;
            this.positionInSequence = 0;
            this.hasPositionInSequence = false;
            this.dimension = null;
            this.imageUrl = "";
            this.hasImageUrl = false;
            this.secureUrl = "";
            this.hasSecureUrl = false;
            this.altTextLocalized = "";
            this.hasAltTextLocalized = false;
            this.supportsFifeUrlOptions = false;
            this.hasSupportsFifeUrlOptions = false;
            this.durationSeconds = 0;
            this.hasDurationSeconds = false;
            this.fillColorRgb = "";
            this.hasFillColorRgb = false;
            this.autogen = false;
            this.hasAutogen = false;
            this.attribution = null;
            this.backgroundColorRgb = "";
            this.hasBackgroundColorRgb = false;
            this.citation = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.imageType != 0 || this.hasImageType) {
                output.writeInt32(1, this.imageType);
            }
            if (this.dimension != null) {
                output.writeGroup(2, this.dimension);
            }
            if (this.hasImageUrl || !this.imageUrl.equals("")) {
                output.writeString(5, this.imageUrl);
            }
            if (this.hasAltTextLocalized || !this.altTextLocalized.equals("")) {
                output.writeString(6, this.altTextLocalized);
            }
            if (this.hasSecureUrl || !this.secureUrl.equals("")) {
                output.writeString(7, this.secureUrl);
            }
            if (this.hasPositionInSequence || this.positionInSequence != 0) {
                output.writeInt32(8, this.positionInSequence);
            }
            if (this.hasSupportsFifeUrlOptions || this.supportsFifeUrlOptions) {
                output.writeBool(9, this.supportsFifeUrlOptions);
            }
            if (this.citation != null) {
                output.writeGroup(10, this.citation);
            }
            if (this.hasDurationSeconds || this.durationSeconds != 0) {
                output.writeInt32(14, this.durationSeconds);
            }
            if (this.hasFillColorRgb || !this.fillColorRgb.equals("")) {
                output.writeString(15, this.fillColorRgb);
            }
            if (this.hasAutogen || this.autogen) {
                output.writeBool(16, this.autogen);
            }
            if (this.attribution != null) {
                output.writeMessage(17, this.attribution);
            }
            if (this.hasBackgroundColorRgb || !this.backgroundColorRgb.equals("")) {
                output.writeString(19, this.backgroundColorRgb);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.imageType != 0 || this.hasImageType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.imageType);
            }
            if (this.dimension != null) {
                size += CodedOutputByteBufferNano.computeGroupSize(2, this.dimension);
            }
            if (this.hasImageUrl || !this.imageUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.imageUrl);
            }
            if (this.hasAltTextLocalized || !this.altTextLocalized.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.altTextLocalized);
            }
            if (this.hasSecureUrl || !this.secureUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.secureUrl);
            }
            if (this.hasPositionInSequence || this.positionInSequence != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.positionInSequence);
            }
            if (this.hasSupportsFifeUrlOptions || this.supportsFifeUrlOptions) {
                size += CodedOutputByteBufferNano.computeBoolSize(9, this.supportsFifeUrlOptions);
            }
            if (this.citation != null) {
                size += CodedOutputByteBufferNano.computeGroupSize(10, this.citation);
            }
            if (this.hasDurationSeconds || this.durationSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(14, this.durationSeconds);
            }
            if (this.hasFillColorRgb || !this.fillColorRgb.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.fillColorRgb);
            }
            if (this.hasAutogen || this.autogen) {
                size += CodedOutputByteBufferNano.computeBoolSize(16, this.autogen);
            }
            if (this.attribution != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(17, this.attribution);
            }
            if (this.hasBackgroundColorRgb || !this.backgroundColorRgb.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(19, this.backgroundColorRgb);
            }
            return size;
        }

        public Image mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                                this.imageType = value;
                                this.hasImageType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                        if (this.dimension == null) {
                            this.dimension = new Dimension();
                        }
                        input.readGroup(this.dimension, 2);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.imageUrl = input.readString();
                        this.hasImageUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.altTextLocalized = input.readString();
                        this.hasAltTextLocalized = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.secureUrl = input.readString();
                        this.hasSecureUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.positionInSequence = input.readInt32();
                        this.hasPositionInSequence = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.supportsFifeUrlOptions = input.readBool();
                        this.hasSupportsFifeUrlOptions = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                        if (this.citation == null) {
                            this.citation = new Citation();
                        }
                        input.readGroup(this.citation, 10);
                        continue;
                    case 112:
                        this.durationSeconds = input.readInt32();
                        this.hasDurationSeconds = true;
                        continue;
                    case 122:
                        this.fillColorRgb = input.readString();
                        this.hasFillColorRgb = true;
                        continue;
                    case 128:
                        this.autogen = input.readBool();
                        this.hasAutogen = true;
                        continue;
                    case 138:
                        if (this.attribution == null) {
                            this.attribution = new Attribution();
                        }
                        input.readMessage(this.attribution);
                        continue;
                    case 154:
                        this.backgroundColorRgb = input.readString();
                        this.hasBackgroundColorRgb = true;
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

    public static final class Install extends MessageNano {
        private static volatile Install[] _emptyArray;
        public long androidId;
        public boolean bundled;
        public boolean hasAndroidId;
        public boolean hasBundled;
        public boolean hasLastUpdateTimestampMillis;
        public boolean hasPending;
        public boolean hasVersion;
        public long lastUpdateTimestampMillis;
        public boolean pending;
        public int version;

        public static Install[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Install[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Install() {
            clear();
        }

        public Install clear() {
            this.androidId = 0;
            this.hasAndroidId = false;
            this.version = 0;
            this.hasVersion = false;
            this.bundled = false;
            this.hasBundled = false;
            this.pending = false;
            this.hasPending = false;
            this.lastUpdateTimestampMillis = 0;
            this.hasLastUpdateTimestampMillis = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasAndroidId || this.androidId != 0) {
                output.writeFixed64(1, this.androidId);
            }
            if (this.hasVersion || this.version != 0) {
                output.writeInt32(2, this.version);
            }
            if (this.hasBundled || this.bundled) {
                output.writeBool(3, this.bundled);
            }
            if (this.hasPending || this.pending) {
                output.writeBool(4, this.pending);
            }
            if (this.hasLastUpdateTimestampMillis || this.lastUpdateTimestampMillis != 0) {
                output.writeInt64(5, this.lastUpdateTimestampMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasAndroidId || this.androidId != 0) {
                size += CodedOutputByteBufferNano.computeFixed64Size(1, this.androidId);
            }
            if (this.hasVersion || this.version != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.version);
            }
            if (this.hasBundled || this.bundled) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.bundled);
            }
            if (this.hasPending || this.pending) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.pending);
            }
            if (this.hasLastUpdateTimestampMillis || this.lastUpdateTimestampMillis != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(5, this.lastUpdateTimestampMillis);
            }
            return size;
        }

        public Install mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                        this.androidId = input.readFixed64();
                        this.hasAndroidId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.version = input.readInt32();
                        this.hasVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.bundled = input.readBool();
                        this.hasBundled = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.pending = input.readBool();
                        this.hasPending = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.lastUpdateTimestampMillis = input.readInt64();
                        this.hasLastUpdateTimestampMillis = true;
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

    public static final class LicenseTerms extends MessageNano {
        public GroupLicenseKey groupLicenseKey;

        public LicenseTerms() {
            clear();
        }

        public LicenseTerms clear() {
            this.groupLicenseKey = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.groupLicenseKey != null) {
                output.writeMessage(1, this.groupLicenseKey);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.groupLicenseKey != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.groupLicenseKey);
            }
            return size;
        }

        public LicenseTerms mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
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

    public static final class LicensedDocumentInfo extends MessageNano {
        public long assignedByGaiaId;
        public String assignmentId;
        public long[] gaiaGroupId;
        public String groupLicenseCheckoutOrderId;
        public GroupLicenseKey groupLicenseKey;
        public boolean hasAssignedByGaiaId;
        public boolean hasAssignmentId;
        public boolean hasGroupLicenseCheckoutOrderId;

        public LicensedDocumentInfo() {
            clear();
        }

        public LicensedDocumentInfo clear() {
            this.gaiaGroupId = WireFormatNano.EMPTY_LONG_ARRAY;
            this.groupLicenseCheckoutOrderId = "";
            this.hasGroupLicenseCheckoutOrderId = false;
            this.groupLicenseKey = null;
            this.assignedByGaiaId = 0;
            this.hasAssignedByGaiaId = false;
            this.assignmentId = "";
            this.hasAssignmentId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.gaiaGroupId != null && this.gaiaGroupId.length > 0) {
                for (long writeFixed64 : this.gaiaGroupId) {
                    output.writeFixed64(1, writeFixed64);
                }
            }
            if (this.hasGroupLicenseCheckoutOrderId || !this.groupLicenseCheckoutOrderId.equals("")) {
                output.writeString(2, this.groupLicenseCheckoutOrderId);
            }
            if (this.groupLicenseKey != null) {
                output.writeMessage(3, this.groupLicenseKey);
            }
            if (this.hasAssignedByGaiaId || this.assignedByGaiaId != 0) {
                output.writeFixed64(4, this.assignedByGaiaId);
            }
            if (this.hasAssignmentId || !this.assignmentId.equals("")) {
                output.writeString(5, this.assignmentId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.gaiaGroupId != null && this.gaiaGroupId.length > 0) {
                size = (size + (this.gaiaGroupId.length * 8)) + (this.gaiaGroupId.length * 1);
            }
            if (this.hasGroupLicenseCheckoutOrderId || !this.groupLicenseCheckoutOrderId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.groupLicenseCheckoutOrderId);
            }
            if (this.groupLicenseKey != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.groupLicenseKey);
            }
            if (this.hasAssignedByGaiaId || this.assignedByGaiaId != 0) {
                size += CodedOutputByteBufferNano.computeFixed64Size(4, this.assignedByGaiaId);
            }
            if (this.hasAssignmentId || !this.assignmentId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.assignmentId);
            }
            return size;
        }

        public LicensedDocumentInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                long[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 9);
                        i = this.gaiaGroupId == null ? 0 : this.gaiaGroupId.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gaiaGroupId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readFixed64();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readFixed64();
                        this.gaiaGroupId = newArray;
                        continue;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int length = input.readRawVarint32();
                        int limit = input.pushLimit(length);
                        arrayLength = length / 8;
                        i = this.gaiaGroupId == null ? 0 : this.gaiaGroupId.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gaiaGroupId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readFixed64();
                            i++;
                        }
                        this.gaiaGroupId = newArray;
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.groupLicenseCheckoutOrderId = input.readString();
                        this.hasGroupLicenseCheckoutOrderId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.groupLicenseKey == null) {
                            this.groupLicenseKey = new GroupLicenseKey();
                        }
                        input.readMessage(this.groupLicenseKey);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                        this.assignedByGaiaId = input.readFixed64();
                        this.hasAssignedByGaiaId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.assignmentId = input.readString();
                        this.hasAssignmentId = true;
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

    public static final class MonthAndDay extends MessageNano {
        public int day;
        public boolean hasDay;
        public boolean hasMonth;
        public int month;

        public MonthAndDay() {
            clear();
        }

        public MonthAndDay clear() {
            this.month = 0;
            this.hasMonth = false;
            this.day = 0;
            this.hasDay = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasMonth || this.month != 0) {
                output.writeUInt32(1, this.month);
            }
            if (this.hasDay || this.day != 0) {
                output.writeUInt32(2, this.day);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasMonth || this.month != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(1, this.month);
            }
            if (this.hasDay || this.day != 0) {
                return size + CodedOutputByteBufferNano.computeUInt32Size(2, this.day);
            }
            return size;
        }

        public MonthAndDay mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.month = input.readUInt32();
                        this.hasMonth = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.day = input.readUInt32();
                        this.hasDay = true;
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

    public static final class Offer extends MessageNano {
        private static volatile Offer[] _emptyArray;
        public boolean checkoutFlowRequired;
        public Offer[] convertedPrice;
        public String currencyCode;
        public String formattedAmount;
        public String formattedDescription;
        public String formattedFullAmount;
        public String formattedName;
        public long fullPriceMicros;
        public boolean hasCheckoutFlowRequired;
        public boolean hasCurrencyCode;
        public boolean hasFormattedAmount;
        public boolean hasFormattedDescription;
        public boolean hasFormattedFullAmount;
        public boolean hasFormattedName;
        public boolean hasFullPriceMicros;
        public boolean hasLicensedOfferType;
        public boolean hasMicros;
        public boolean hasOfferId;
        public boolean hasOfferType;
        public boolean hasOnSaleDate;
        public boolean hasOnSaleDateDisplayTimeZoneOffsetMsec;
        public boolean hasPreorder;
        public boolean hasPreorderFulfillmentDisplayDate;
        public boolean hasTemporarilyFree;
        public LicenseTerms licenseTerms;
        public int licensedOfferType;
        public long micros;
        public String offerId;
        public int offerType;
        public long onSaleDate;
        public int onSaleDateDisplayTimeZoneOffsetMsec;
        public boolean preorder;
        public long preorderFulfillmentDisplayDate;
        public String[] promotionLabel;
        public RentalTerms rentalTerms;
        public SubscriptionContentTerms subscriptionContentTerms;
        public SubscriptionTerms subscriptionTerms;
        public boolean temporarilyFree;
        public VoucherOfferTerms voucherTerms;

        public static Offer[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Offer[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Offer() {
            clear();
        }

        public Offer clear() {
            this.micros = 0;
            this.hasMicros = false;
            this.currencyCode = "";
            this.hasCurrencyCode = false;
            this.formattedAmount = "";
            this.hasFormattedAmount = false;
            this.formattedName = "";
            this.hasFormattedName = false;
            this.formattedDescription = "";
            this.hasFormattedDescription = false;
            this.fullPriceMicros = 0;
            this.hasFullPriceMicros = false;
            this.formattedFullAmount = "";
            this.hasFormattedFullAmount = false;
            this.convertedPrice = emptyArray();
            this.checkoutFlowRequired = false;
            this.hasCheckoutFlowRequired = false;
            this.temporarilyFree = false;
            this.hasTemporarilyFree = false;
            this.offerType = 1;
            this.hasOfferType = false;
            this.licensedOfferType = 1;
            this.hasLicensedOfferType = false;
            this.licenseTerms = null;
            this.rentalTerms = null;
            this.subscriptionTerms = null;
            this.subscriptionContentTerms = null;
            this.voucherTerms = null;
            this.preorder = false;
            this.hasPreorder = false;
            this.preorderFulfillmentDisplayDate = 0;
            this.hasPreorderFulfillmentDisplayDate = false;
            this.onSaleDate = 0;
            this.hasOnSaleDate = false;
            this.onSaleDateDisplayTimeZoneOffsetMsec = 0;
            this.hasOnSaleDateDisplayTimeZoneOffsetMsec = false;
            this.promotionLabel = WireFormatNano.EMPTY_STRING_ARRAY;
            this.offerId = "";
            this.hasOfferId = false;
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
            if (this.convertedPrice != null && this.convertedPrice.length > 0) {
                for (Offer element : this.convertedPrice) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.hasCheckoutFlowRequired || this.checkoutFlowRequired) {
                output.writeBool(5, this.checkoutFlowRequired);
            }
            if (this.hasFullPriceMicros || this.fullPriceMicros != 0) {
                output.writeInt64(6, this.fullPriceMicros);
            }
            if (this.hasFormattedFullAmount || !this.formattedFullAmount.equals("")) {
                output.writeString(7, this.formattedFullAmount);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                output.writeInt32(8, this.offerType);
            }
            if (this.rentalTerms != null) {
                output.writeMessage(9, this.rentalTerms);
            }
            if (this.hasOnSaleDate || this.onSaleDate != 0) {
                output.writeInt64(10, this.onSaleDate);
            }
            if (this.promotionLabel != null && this.promotionLabel.length > 0) {
                for (String element2 : this.promotionLabel) {
                    if (element2 != null) {
                        output.writeString(11, element2);
                    }
                }
            }
            if (this.subscriptionTerms != null) {
                output.writeMessage(12, this.subscriptionTerms);
            }
            if (this.hasFormattedName || !this.formattedName.equals("")) {
                output.writeString(13, this.formattedName);
            }
            if (this.hasFormattedDescription || !this.formattedDescription.equals("")) {
                output.writeString(14, this.formattedDescription);
            }
            if (this.hasPreorder || this.preorder) {
                output.writeBool(15, this.preorder);
            }
            if (this.hasOnSaleDateDisplayTimeZoneOffsetMsec || this.onSaleDateDisplayTimeZoneOffsetMsec != 0) {
                output.writeInt32(16, this.onSaleDateDisplayTimeZoneOffsetMsec);
            }
            if (this.licensedOfferType != 1 || this.hasLicensedOfferType) {
                output.writeInt32(17, this.licensedOfferType);
            }
            if (this.subscriptionContentTerms != null) {
                output.writeMessage(18, this.subscriptionContentTerms);
            }
            if (this.hasOfferId || !this.offerId.equals("")) {
                output.writeString(19, this.offerId);
            }
            if (this.hasPreorderFulfillmentDisplayDate || this.preorderFulfillmentDisplayDate != 0) {
                output.writeInt64(20, this.preorderFulfillmentDisplayDate);
            }
            if (this.licenseTerms != null) {
                output.writeMessage(21, this.licenseTerms);
            }
            if (this.hasTemporarilyFree || this.temporarilyFree) {
                output.writeBool(22, this.temporarilyFree);
            }
            if (this.voucherTerms != null) {
                output.writeMessage(23, this.voucherTerms);
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
                size += CodedOutputByteBufferNano.computeStringSize(3, this.formattedAmount);
            }
            if (this.convertedPrice != null && this.convertedPrice.length > 0) {
                for (Offer element : this.convertedPrice) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.hasCheckoutFlowRequired || this.checkoutFlowRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.checkoutFlowRequired);
            }
            if (this.hasFullPriceMicros || this.fullPriceMicros != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(6, this.fullPriceMicros);
            }
            if (this.hasFormattedFullAmount || !this.formattedFullAmount.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.formattedFullAmount);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.offerType);
            }
            if (this.rentalTerms != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.rentalTerms);
            }
            if (this.hasOnSaleDate || this.onSaleDate != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(10, this.onSaleDate);
            }
            if (this.promotionLabel != null && this.promotionLabel.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element2 : this.promotionLabel) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.subscriptionTerms != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.subscriptionTerms);
            }
            if (this.hasFormattedName || !this.formattedName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.formattedName);
            }
            if (this.hasFormattedDescription || !this.formattedDescription.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.formattedDescription);
            }
            if (this.hasPreorder || this.preorder) {
                size += CodedOutputByteBufferNano.computeBoolSize(15, this.preorder);
            }
            if (this.hasOnSaleDateDisplayTimeZoneOffsetMsec || this.onSaleDateDisplayTimeZoneOffsetMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(16, this.onSaleDateDisplayTimeZoneOffsetMsec);
            }
            if (this.licensedOfferType != 1 || this.hasLicensedOfferType) {
                size += CodedOutputByteBufferNano.computeInt32Size(17, this.licensedOfferType);
            }
            if (this.subscriptionContentTerms != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(18, this.subscriptionContentTerms);
            }
            if (this.hasOfferId || !this.offerId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.offerId);
            }
            if (this.hasPreorderFulfillmentDisplayDate || this.preorderFulfillmentDisplayDate != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(20, this.preorderFulfillmentDisplayDate);
            }
            if (this.licenseTerms != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(21, this.licenseTerms);
            }
            if (this.hasTemporarilyFree || this.temporarilyFree) {
                size += CodedOutputByteBufferNano.computeBoolSize(22, this.temporarilyFree);
            }
            if (this.voucherTerms != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(23, this.voucherTerms);
            }
            return size;
        }

        public Offer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int value;
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
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.convertedPrice == null) {
                            i = 0;
                        } else {
                            i = this.convertedPrice.length;
                        }
                        Offer[] newArray = new Offer[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.convertedPrice, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Offer();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Offer();
                        input.readMessage(newArray[i]);
                        this.convertedPrice = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.checkoutFlowRequired = input.readBool();
                        this.hasCheckoutFlowRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.fullPriceMicros = input.readInt64();
                        this.hasFullPriceMicros = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.formattedFullAmount = input.readString();
                        this.hasFormattedFullAmount = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
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
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.offerType = value;
                                this.hasOfferType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.rentalTerms == null) {
                            this.rentalTerms = new RentalTerms();
                        }
                        input.readMessage(this.rentalTerms);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.onSaleDate = input.readInt64();
                        this.hasOnSaleDate = true;
                        continue;
                    case 90:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                        i = this.promotionLabel == null ? 0 : this.promotionLabel.length;
                        String[] newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.promotionLabel, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.promotionLabel = newArray2;
                        continue;
                    case 98:
                        if (this.subscriptionTerms == null) {
                            this.subscriptionTerms = new SubscriptionTerms();
                        }
                        input.readMessage(this.subscriptionTerms);
                        continue;
                    case 106:
                        this.formattedName = input.readString();
                        this.hasFormattedName = true;
                        continue;
                    case 114:
                        this.formattedDescription = input.readString();
                        this.hasFormattedDescription = true;
                        continue;
                    case 120:
                        this.preorder = input.readBool();
                        this.hasPreorder = true;
                        continue;
                    case 128:
                        this.onSaleDateDisplayTimeZoneOffsetMsec = input.readInt32();
                        this.hasOnSaleDateDisplayTimeZoneOffsetMsec = true;
                        continue;
                    case 136:
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
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.licensedOfferType = value;
                                this.hasLicensedOfferType = true;
                                break;
                            default:
                                continue;
                        }
                    case 146:
                        if (this.subscriptionContentTerms == null) {
                            this.subscriptionContentTerms = new SubscriptionContentTerms();
                        }
                        input.readMessage(this.subscriptionContentTerms);
                        continue;
                    case 154:
                        this.offerId = input.readString();
                        this.hasOfferId = true;
                        continue;
                    case 160:
                        this.preorderFulfillmentDisplayDate = input.readInt64();
                        this.hasPreorderFulfillmentDisplayDate = true;
                        continue;
                    case 170:
                        if (this.licenseTerms == null) {
                            this.licenseTerms = new LicenseTerms();
                        }
                        input.readMessage(this.licenseTerms);
                        continue;
                    case 176:
                        this.temporarilyFree = input.readBool();
                        this.hasTemporarilyFree = true;
                        continue;
                    case 186:
                        if (this.voucherTerms == null) {
                            this.voucherTerms = new VoucherOfferTerms();
                        }
                        input.readMessage(this.voucherTerms);
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

    public static final class RedemptionRecordKey extends MessageNano {
        public long campaignId;
        public long codeGroupId;
        public boolean hasCampaignId;
        public boolean hasCodeGroupId;
        public boolean hasPublisherId;
        public boolean hasRecordId;
        public long publisherId;
        public long recordId;

        public RedemptionRecordKey() {
            clear();
        }

        public RedemptionRecordKey clear() {
            this.publisherId = 0;
            this.hasPublisherId = false;
            this.campaignId = 0;
            this.hasCampaignId = false;
            this.codeGroupId = 0;
            this.hasCodeGroupId = false;
            this.recordId = 0;
            this.hasRecordId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPublisherId || this.publisherId != 0) {
                output.writeInt64(1, this.publisherId);
            }
            if (this.hasCampaignId || this.campaignId != 0) {
                output.writeInt64(2, this.campaignId);
            }
            if (this.hasCodeGroupId || this.codeGroupId != 0) {
                output.writeInt64(3, this.codeGroupId);
            }
            if (this.hasRecordId || this.recordId != 0) {
                output.writeInt64(4, this.recordId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPublisherId || this.publisherId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.publisherId);
            }
            if (this.hasCampaignId || this.campaignId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.campaignId);
            }
            if (this.hasCodeGroupId || this.codeGroupId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.codeGroupId);
            }
            if (this.hasRecordId || this.recordId != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(4, this.recordId);
            }
            return size;
        }

        public RedemptionRecordKey mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.publisherId = input.readInt64();
                        this.hasPublisherId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.campaignId = input.readInt64();
                        this.hasCampaignId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.codeGroupId = input.readInt64();
                        this.hasCodeGroupId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.recordId = input.readInt64();
                        this.hasRecordId = true;
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

    public static final class RentalTerms extends MessageNano {
        public TimePeriod activatePeriod;
        public int dEPRECATEDActivatePeriodSeconds;
        public int dEPRECATEDGrantPeriodSeconds;
        public long grantEndTimeSeconds;
        public TimePeriod grantPeriod;
        public boolean hasDEPRECATEDActivatePeriodSeconds;
        public boolean hasDEPRECATEDGrantPeriodSeconds;
        public boolean hasGrantEndTimeSeconds;

        public RentalTerms() {
            clear();
        }

        public RentalTerms clear() {
            this.grantPeriod = null;
            this.activatePeriod = null;
            this.grantEndTimeSeconds = 0;
            this.hasGrantEndTimeSeconds = false;
            this.dEPRECATEDGrantPeriodSeconds = 0;
            this.hasDEPRECATEDGrantPeriodSeconds = false;
            this.dEPRECATEDActivatePeriodSeconds = 0;
            this.hasDEPRECATEDActivatePeriodSeconds = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDEPRECATEDGrantPeriodSeconds || this.dEPRECATEDGrantPeriodSeconds != 0) {
                output.writeInt32(1, this.dEPRECATEDGrantPeriodSeconds);
            }
            if (this.hasDEPRECATEDActivatePeriodSeconds || this.dEPRECATEDActivatePeriodSeconds != 0) {
                output.writeInt32(2, this.dEPRECATEDActivatePeriodSeconds);
            }
            if (this.grantPeriod != null) {
                output.writeMessage(3, this.grantPeriod);
            }
            if (this.activatePeriod != null) {
                output.writeMessage(4, this.activatePeriod);
            }
            if (this.hasGrantEndTimeSeconds || this.grantEndTimeSeconds != 0) {
                output.writeInt64(5, this.grantEndTimeSeconds);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDEPRECATEDGrantPeriodSeconds || this.dEPRECATEDGrantPeriodSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.dEPRECATEDGrantPeriodSeconds);
            }
            if (this.hasDEPRECATEDActivatePeriodSeconds || this.dEPRECATEDActivatePeriodSeconds != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.dEPRECATEDActivatePeriodSeconds);
            }
            if (this.grantPeriod != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.grantPeriod);
            }
            if (this.activatePeriod != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.activatePeriod);
            }
            if (this.hasGrantEndTimeSeconds || this.grantEndTimeSeconds != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(5, this.grantEndTimeSeconds);
            }
            return size;
        }

        public RentalTerms mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.dEPRECATEDGrantPeriodSeconds = input.readInt32();
                        this.hasDEPRECATEDGrantPeriodSeconds = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.dEPRECATEDActivatePeriodSeconds = input.readInt32();
                        this.hasDEPRECATEDActivatePeriodSeconds = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.grantPeriod == null) {
                            this.grantPeriod = new TimePeriod();
                        }
                        input.readMessage(this.grantPeriod);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.activatePeriod == null) {
                            this.activatePeriod = new TimePeriod();
                        }
                        input.readMessage(this.activatePeriod);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.grantEndTimeSeconds = input.readInt64();
                        this.hasGrantEndTimeSeconds = true;
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

    public static final class SeasonalSubscriptionInfo extends MessageNano {
        public MonthAndDay periodEnd;
        public MonthAndDay periodStart;

        public SeasonalSubscriptionInfo() {
            clear();
        }

        public SeasonalSubscriptionInfo clear() {
            this.periodStart = null;
            this.periodEnd = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.periodStart != null) {
                output.writeMessage(1, this.periodStart);
            }
            if (this.periodEnd != null) {
                output.writeMessage(2, this.periodEnd);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.periodStart != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.periodStart);
            }
            if (this.periodEnd != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.periodEnd);
            }
            return size;
        }

        public SeasonalSubscriptionInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.periodStart == null) {
                            this.periodStart = new MonthAndDay();
                        }
                        input.readMessage(this.periodStart);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.periodEnd == null) {
                            this.periodEnd = new MonthAndDay();
                        }
                        input.readMessage(this.periodEnd);
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

    public static final class SignedData extends MessageNano {
        public boolean hasSignature;
        public boolean hasSignedData;
        public String signature;
        public String signedData;

        public SignedData() {
            clear();
        }

        public SignedData clear() {
            this.signedData = "";
            this.hasSignedData = false;
            this.signature = "";
            this.hasSignature = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSignedData || !this.signedData.equals("")) {
                output.writeString(1, this.signedData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                output.writeString(2, this.signature);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSignedData || !this.signedData.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.signedData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.signature);
            }
            return size;
        }

        public SignedData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.signedData = input.readString();
                        this.hasSignedData = true;
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

    public static final class SubscriptionContentTerms extends MessageNano {
        public Docid requiredSubscription;

        public SubscriptionContentTerms() {
            clear();
        }

        public SubscriptionContentTerms clear() {
            this.requiredSubscription = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.requiredSubscription != null) {
                output.writeMessage(1, this.requiredSubscription);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.requiredSubscription != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.requiredSubscription);
            }
            return size;
        }

        public SubscriptionContentTerms mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.requiredSubscription == null) {
                            this.requiredSubscription = new Docid();
                        }
                        input.readMessage(this.requiredSubscription);
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

    public static final class SubscriptionTerms extends MessageNano {
        public String formattedPriceWithRecurrencePeriod;
        public TimePeriod gracePeriod;
        public boolean hasFormattedPriceWithRecurrencePeriod;
        public boolean hasResignup;
        public TimePeriod recurringPeriod;
        public Docid[] replaceDocid;
        public boolean resignup;
        public SeasonalSubscriptionInfo seasonalSubscriptionInfo;
        public TimePeriod trialPeriod;

        public SubscriptionTerms() {
            clear();
        }

        public SubscriptionTerms clear() {
            this.recurringPeriod = null;
            this.trialPeriod = null;
            this.formattedPriceWithRecurrencePeriod = "";
            this.hasFormattedPriceWithRecurrencePeriod = false;
            this.seasonalSubscriptionInfo = null;
            this.replaceDocid = Docid.emptyArray();
            this.gracePeriod = null;
            this.resignup = false;
            this.hasResignup = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.recurringPeriod != null) {
                output.writeMessage(1, this.recurringPeriod);
            }
            if (this.trialPeriod != null) {
                output.writeMessage(2, this.trialPeriod);
            }
            if (this.hasFormattedPriceWithRecurrencePeriod || !this.formattedPriceWithRecurrencePeriod.equals("")) {
                output.writeString(3, this.formattedPriceWithRecurrencePeriod);
            }
            if (this.seasonalSubscriptionInfo != null) {
                output.writeMessage(4, this.seasonalSubscriptionInfo);
            }
            if (this.replaceDocid != null && this.replaceDocid.length > 0) {
                for (Docid element : this.replaceDocid) {
                    if (element != null) {
                        output.writeMessage(5, element);
                    }
                }
            }
            if (this.gracePeriod != null) {
                output.writeMessage(6, this.gracePeriod);
            }
            if (this.hasResignup || this.resignup) {
                output.writeBool(7, this.resignup);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.recurringPeriod != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.recurringPeriod);
            }
            if (this.trialPeriod != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.trialPeriod);
            }
            if (this.hasFormattedPriceWithRecurrencePeriod || !this.formattedPriceWithRecurrencePeriod.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.formattedPriceWithRecurrencePeriod);
            }
            if (this.seasonalSubscriptionInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.seasonalSubscriptionInfo);
            }
            if (this.replaceDocid != null && this.replaceDocid.length > 0) {
                for (Docid element : this.replaceDocid) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element);
                    }
                }
            }
            if (this.gracePeriod != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.gracePeriod);
            }
            if (this.hasResignup || this.resignup) {
                return size + CodedOutputByteBufferNano.computeBoolSize(7, this.resignup);
            }
            return size;
        }

        public SubscriptionTerms mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.recurringPeriod == null) {
                            this.recurringPeriod = new TimePeriod();
                        }
                        input.readMessage(this.recurringPeriod);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.trialPeriod == null) {
                            this.trialPeriod = new TimePeriod();
                        }
                        input.readMessage(this.trialPeriod);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.formattedPriceWithRecurrencePeriod = input.readString();
                        this.hasFormattedPriceWithRecurrencePeriod = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.seasonalSubscriptionInfo == null) {
                            this.seasonalSubscriptionInfo = new SeasonalSubscriptionInfo();
                        }
                        input.readMessage(this.seasonalSubscriptionInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.replaceDocid == null) {
                            i = 0;
                        } else {
                            i = this.replaceDocid.length;
                        }
                        Docid[] newArray = new Docid[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.replaceDocid, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Docid();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Docid();
                        input.readMessage(newArray[i]);
                        this.replaceDocid = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.gracePeriod == null) {
                            this.gracePeriod = new TimePeriod();
                        }
                        input.readMessage(this.gracePeriod);
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.resignup = input.readBool();
                        this.hasResignup = true;
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

    public static final class TimePeriod extends MessageNano {
        public int count;
        public boolean hasCount;
        public boolean hasUnit;
        public int unit;

        public TimePeriod() {
            clear();
        }

        public TimePeriod clear() {
            this.unit = 0;
            this.hasUnit = false;
            this.count = 0;
            this.hasCount = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.unit != 0 || this.hasUnit) {
                output.writeInt32(1, this.unit);
            }
            if (this.hasCount || this.count != 0) {
                output.writeInt32(2, this.count);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.unit != 0 || this.hasUnit) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.unit);
            }
            if (this.hasCount || this.count != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.count);
            }
            return size;
        }

        public TimePeriod mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.unit = value;
                                this.hasUnit = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.count = input.readInt32();
                        this.hasCount = true;
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

    public static final class VoucherId extends MessageNano {
        public RedemptionRecordKey key;
        public Docid voucherDocid;

        public VoucherId() {
            clear();
        }

        public VoucherId clear() {
            this.voucherDocid = null;
            this.key = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.voucherDocid != null) {
                output.writeMessage(1, this.voucherDocid);
            }
            if (this.key != null) {
                output.writeMessage(2, this.key);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.voucherDocid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.voucherDocid);
            }
            if (this.key != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.key);
            }
            return size;
        }

        public VoucherId mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.voucherDocid == null) {
                            this.voucherDocid = new Docid();
                        }
                        input.readMessage(this.voucherDocid);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.key == null) {
                            this.key = new RedemptionRecordKey();
                        }
                        input.readMessage(this.key);
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

    public static final class VoucherOfferTerms extends MessageNano {
        public boolean hasVoucherFormattedAmount;
        public boolean hasVoucherPriceMicros;
        public Docid[] voucherDocid;
        public String voucherFormattedAmount;
        public long voucherPriceMicros;

        public VoucherOfferTerms() {
            clear();
        }

        public VoucherOfferTerms clear() {
            this.voucherDocid = Docid.emptyArray();
            this.voucherPriceMicros = 0;
            this.hasVoucherPriceMicros = false;
            this.voucherFormattedAmount = "";
            this.hasVoucherFormattedAmount = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.voucherDocid != null && this.voucherDocid.length > 0) {
                for (Docid element : this.voucherDocid) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasVoucherPriceMicros || this.voucherPriceMicros != 0) {
                output.writeInt64(2, this.voucherPriceMicros);
            }
            if (this.hasVoucherFormattedAmount || !this.voucherFormattedAmount.equals("")) {
                output.writeString(3, this.voucherFormattedAmount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.voucherDocid != null && this.voucherDocid.length > 0) {
                for (Docid element : this.voucherDocid) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasVoucherPriceMicros || this.voucherPriceMicros != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.voucherPriceMicros);
            }
            if (this.hasVoucherFormattedAmount || !this.voucherFormattedAmount.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.voucherFormattedAmount);
            }
            return size;
        }

        public VoucherOfferTerms mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.voucherDocid == null) {
                            i = 0;
                        } else {
                            i = this.voucherDocid.length;
                        }
                        Docid[] newArray = new Docid[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.voucherDocid, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Docid();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Docid();
                        input.readMessage(newArray[i]);
                        this.voucherDocid = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.voucherPriceMicros = input.readInt64();
                        this.hasVoucherPriceMicros = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.voucherFormattedAmount = input.readString();
                        this.hasVoucherFormattedAmount = true;
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
