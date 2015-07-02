package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.ResolveLink.ResolvedLink;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface DocAnnotations {

    public static final class Badge extends MessageNano {
        private static volatile Badge[] _emptyArray;
        public String browseUrl;
        public String description;
        public boolean hasBrowseUrl;
        public boolean hasDescription;
        public boolean hasTitle;
        public Image[] image;
        public String title;

        public static Badge[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Badge[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Badge() {
            clear();
        }

        public Badge clear() {
            this.title = "";
            this.hasTitle = false;
            this.description = "";
            this.hasDescription = false;
            this.image = Image.emptyArray();
            this.browseUrl = "";
            this.hasBrowseUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.hasBrowseUrl || !this.browseUrl.equals("")) {
                output.writeString(3, this.browseUrl);
            }
            if (this.hasDescription || !this.description.equals("")) {
                output.writeString(4, this.description);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.hasBrowseUrl || !this.browseUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.browseUrl);
            }
            if (this.hasDescription || !this.description.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.description);
            }
            return size;
        }

        public Badge mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.image == null) {
                            i = 0;
                        } else {
                            i = this.image.length;
                        }
                        Image[] newArray = new Image[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.image, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Image();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Image();
                        input.readMessage(newArray[i]);
                        this.image = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.browseUrl = input.readString();
                        this.hasBrowseUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
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

    public static final class BadgeContainer extends MessageNano {
        private static volatile BadgeContainer[] _emptyArray;
        public Badge[] badge;
        public boolean hasTitle;
        public Image[] image;
        public String title;

        public static BadgeContainer[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BadgeContainer[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BadgeContainer() {
            clear();
        }

        public BadgeContainer clear() {
            this.title = "";
            this.hasTitle = false;
            this.image = Image.emptyArray();
            this.badge = Badge.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.badge != null && this.badge.length > 0) {
                for (Badge element2 : this.badge) {
                    if (element2 != null) {
                        output.writeMessage(3, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.badge != null && this.badge.length > 0) {
                for (Badge element2 : this.badge) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                    }
                }
            }
            return size;
        }

        public BadgeContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.image == null) {
                            i = 0;
                        } else {
                            i = this.image.length;
                        }
                        Image[] newArray = new Image[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.image, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Image();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Image();
                        input.readMessage(newArray[i]);
                        this.image = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.badge == null) {
                            i = 0;
                        } else {
                            i = this.badge.length;
                        }
                        Badge[] newArray2 = new Badge[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.badge, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Badge();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Badge();
                        input.readMessage(newArray2[i]);
                        this.badge = newArray2;
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

    public static final class Link extends MessageNano {
        private static volatile Link[] _emptyArray;
        public boolean hasUri;
        public boolean hasUriBackend;
        public ResolvedLink resolvedLink;
        public String uri;
        public int uriBackend;

        public static Link[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Link[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Link() {
            clear();
        }

        public Link clear() {
            this.uri = "";
            this.hasUri = false;
            this.uriBackend = 0;
            this.hasUriBackend = false;
            this.resolvedLink = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUri || !this.uri.equals("")) {
                output.writeString(1, this.uri);
            }
            if (this.resolvedLink != null) {
                output.writeMessage(2, this.resolvedLink);
            }
            if (this.uriBackend != 0 || this.hasUriBackend) {
                output.writeInt32(3, this.uriBackend);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUri || !this.uri.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.uri);
            }
            if (this.resolvedLink != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.resolvedLink);
            }
            if (this.uriBackend != 0 || this.hasUriBackend) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.uriBackend);
            }
            return size;
        }

        public Link mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.uri = input.readString();
                        this.hasUri = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.resolvedLink == null) {
                            this.resolvedLink = new ResolvedLink();
                        }
                        input.readMessage(this.resolvedLink);
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.uriBackend = value;
                                this.hasUriBackend = true;
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

    public static final class PromotedDoc extends MessageNano {
        private static volatile PromotedDoc[] _emptyArray;
        public String descriptionHtml;
        public String detailsUrl;
        public boolean hasDescriptionHtml;
        public boolean hasDetailsUrl;
        public boolean hasSubtitle;
        public boolean hasTitle;
        public Image[] image;
        public String subtitle;
        public String title;

        public static PromotedDoc[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PromotedDoc[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PromotedDoc() {
            clear();
        }

        public PromotedDoc clear() {
            this.title = "";
            this.hasTitle = false;
            this.subtitle = "";
            this.hasSubtitle = false;
            this.image = Image.emptyArray();
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.detailsUrl = "";
            this.hasDetailsUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasSubtitle || !this.subtitle.equals("")) {
                output.writeString(2, this.subtitle);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(4, this.descriptionHtml);
            }
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                output.writeString(5, this.detailsUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasSubtitle || !this.subtitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.subtitle);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.descriptionHtml);
            }
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.detailsUrl);
            }
            return size;
        }

        public PromotedDoc mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.subtitle = input.readString();
                        this.hasSubtitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.image == null) {
                            i = 0;
                        } else {
                            i = this.image.length;
                        }
                        Image[] newArray = new Image[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.image, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Image();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Image();
                        input.readMessage(newArray[i]);
                        this.image = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.detailsUrl = input.readString();
                        this.hasDetailsUrl = true;
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

    public static final class PurchaseHistoryDetails extends MessageNano {
        public boolean hasPurchaseDetailsHtml;
        public boolean hasPurchaseStatus;
        public boolean hasPurchaseTimestampMsec;
        public Offer offer;
        public String purchaseDetailsHtml;
        public String purchaseStatus;
        public long purchaseTimestampMsec;

        public PurchaseHistoryDetails() {
            clear();
        }

        public PurchaseHistoryDetails clear() {
            this.purchaseTimestampMsec = 0;
            this.hasPurchaseTimestampMsec = false;
            this.purchaseDetailsHtml = "";
            this.hasPurchaseDetailsHtml = false;
            this.offer = null;
            this.purchaseStatus = "";
            this.hasPurchaseStatus = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPurchaseTimestampMsec || this.purchaseTimestampMsec != 0) {
                output.writeInt64(2, this.purchaseTimestampMsec);
            }
            if (this.hasPurchaseDetailsHtml || !this.purchaseDetailsHtml.equals("")) {
                output.writeString(3, this.purchaseDetailsHtml);
            }
            if (this.offer != null) {
                output.writeMessage(5, this.offer);
            }
            if (this.hasPurchaseStatus || !this.purchaseStatus.equals("")) {
                output.writeString(6, this.purchaseStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPurchaseTimestampMsec || this.purchaseTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.purchaseTimestampMsec);
            }
            if (this.hasPurchaseDetailsHtml || !this.purchaseDetailsHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.purchaseDetailsHtml);
            }
            if (this.offer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.offer);
            }
            if (this.hasPurchaseStatus || !this.purchaseStatus.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(6, this.purchaseStatus);
            }
            return size;
        }

        public PurchaseHistoryDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.purchaseTimestampMsec = input.readInt64();
                        this.hasPurchaseTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.purchaseDetailsHtml = input.readString();
                        this.hasPurchaseDetailsHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.offer == null) {
                            this.offer = new Offer();
                        }
                        input.readMessage(this.offer);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.purchaseStatus = input.readString();
                        this.hasPurchaseStatus = true;
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

    public static final class SectionMetadata extends MessageNano {
        public String browseUrl;
        public String descriptionHtml;
        public boolean hasBrowseUrl;
        public boolean hasDescriptionHtml;
        public boolean hasHeader;
        public boolean hasListUrl;
        public String header;
        public String listUrl;

        public SectionMetadata() {
            clear();
        }

        public SectionMetadata clear() {
            this.header = "";
            this.hasHeader = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.listUrl = "";
            this.hasListUrl = false;
            this.browseUrl = "";
            this.hasBrowseUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasHeader || !this.header.equals("")) {
                output.writeString(1, this.header);
            }
            if (this.hasListUrl || !this.listUrl.equals("")) {
                output.writeString(2, this.listUrl);
            }
            if (this.hasBrowseUrl || !this.browseUrl.equals("")) {
                output.writeString(3, this.browseUrl);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(4, this.descriptionHtml);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasHeader || !this.header.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.header);
            }
            if (this.hasListUrl || !this.listUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.listUrl);
            }
            if (this.hasBrowseUrl || !this.browseUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.browseUrl);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.descriptionHtml);
            }
            return size;
        }

        public SectionMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.header = input.readString();
                        this.hasHeader = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.listUrl = input.readString();
                        this.hasListUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.browseUrl = input.readString();
                        this.hasBrowseUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
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

    public static final class Warning extends MessageNano {
        private static volatile Warning[] _emptyArray;
        public boolean hasLocalizedMessage;
        public String localizedMessage;

        public static Warning[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Warning[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Warning() {
            clear();
        }

        public Warning clear() {
            this.localizedMessage = "";
            this.hasLocalizedMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLocalizedMessage || !this.localizedMessage.equals("")) {
                output.writeString(1, this.localizedMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLocalizedMessage || !this.localizedMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.localizedMessage);
            }
            return size;
        }

        public Warning mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.localizedMessage = input.readString();
                        this.hasLocalizedMessage = true;
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
