package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.ResolveLink.ResolvedLink;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface Browse {

    public static final class BrowseLink extends MessageNano {
        private static volatile BrowseLink[] _emptyArray;
        public String dataUrl;
        public boolean hasDataUrl;
        public boolean hasName;
        public boolean hasServerLogsCookie;
        public String name;
        public byte[] serverLogsCookie;

        public static BrowseLink[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BrowseLink[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BrowseLink() {
            clear();
        }

        public BrowseLink clear() {
            this.name = "";
            this.hasName = false;
            this.dataUrl = "";
            this.hasDataUrl = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasName || !this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (this.hasDataUrl || !this.dataUrl.equals("")) {
                output.writeString(3, this.dataUrl);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.serverLogsCookie);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.hasDataUrl || !this.dataUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.dataUrl);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                return size + CodedOutputByteBufferNano.computeBytesSize(4, this.serverLogsCookie);
            }
            return size;
        }

        public BrowseLink mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.dataUrl = input.readString();
                        this.hasDataUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
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

    public static final class BrowseResponse extends MessageNano {
        public int backendId;
        public BrowseLink[] breadcrumb;
        public BrowseTab[] browseTab;
        public BrowseLink[] category;
        public String contentsUrl;
        public boolean hasBackendId;
        public boolean hasContentsUrl;
        public boolean hasLandingTabIndex;
        public boolean hasPromoUrl;
        public boolean hasQuickLinkFallbackTabIndex;
        public boolean hasQuickLinkTabIndex;
        public boolean hasServerLogsCookie;
        public boolean hasTitle;
        public int landingTabIndex;
        public String promoUrl;
        public QuickLink[] quickLink;
        public int quickLinkFallbackTabIndex;
        public int quickLinkTabIndex;
        public byte[] serverLogsCookie;
        public String title;

        public BrowseResponse() {
            clear();
        }

        public BrowseResponse clear() {
            this.title = "";
            this.hasTitle = false;
            this.backendId = 0;
            this.hasBackendId = false;
            this.browseTab = BrowseTab.emptyArray();
            this.landingTabIndex = 0;
            this.hasLandingTabIndex = false;
            this.quickLink = QuickLink.emptyArray();
            this.quickLinkTabIndex = 0;
            this.hasQuickLinkTabIndex = false;
            this.quickLinkFallbackTabIndex = 0;
            this.hasQuickLinkFallbackTabIndex = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.contentsUrl = "";
            this.hasContentsUrl = false;
            this.promoUrl = "";
            this.hasPromoUrl = false;
            this.category = BrowseLink.emptyArray();
            this.breadcrumb = BrowseLink.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasContentsUrl || !this.contentsUrl.equals("")) {
                output.writeString(1, this.contentsUrl);
            }
            if (this.hasPromoUrl || !this.promoUrl.equals("")) {
                output.writeString(2, this.promoUrl);
            }
            if (this.category != null && this.category.length > 0) {
                for (BrowseLink element : this.category) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.breadcrumb != null && this.breadcrumb.length > 0) {
                for (BrowseLink element2 : this.breadcrumb) {
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            if (this.quickLink != null && this.quickLink.length > 0) {
                for (QuickLink element3 : this.quickLink) {
                    if (element3 != null) {
                        output.writeMessage(5, element3);
                    }
                }
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.serverLogsCookie);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(7, this.title);
            }
            if (this.backendId != 0 || this.hasBackendId) {
                output.writeInt32(8, this.backendId);
            }
            if (this.browseTab != null && this.browseTab.length > 0) {
                for (BrowseTab element4 : this.browseTab) {
                    if (element4 != null) {
                        output.writeMessage(9, element4);
                    }
                }
            }
            if (this.hasLandingTabIndex || this.landingTabIndex != 0) {
                output.writeInt32(10, this.landingTabIndex);
            }
            if (this.hasQuickLinkTabIndex || this.quickLinkTabIndex != 0) {
                output.writeInt32(11, this.quickLinkTabIndex);
            }
            if (this.hasQuickLinkFallbackTabIndex || this.quickLinkFallbackTabIndex != 0) {
                output.writeInt32(12, this.quickLinkFallbackTabIndex);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasContentsUrl || !this.contentsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.contentsUrl);
            }
            if (this.hasPromoUrl || !this.promoUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.promoUrl);
            }
            if (this.category != null && this.category.length > 0) {
                for (BrowseLink element : this.category) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.breadcrumb != null && this.breadcrumb.length > 0) {
                for (BrowseLink element2 : this.breadcrumb) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            if (this.quickLink != null && this.quickLink.length > 0) {
                for (QuickLink element3 : this.quickLink) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element3);
                    }
                }
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.serverLogsCookie);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.title);
            }
            if (this.backendId != 0 || this.hasBackendId) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.backendId);
            }
            if (this.browseTab != null && this.browseTab.length > 0) {
                for (BrowseTab element4 : this.browseTab) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(9, element4);
                    }
                }
            }
            if (this.hasLandingTabIndex || this.landingTabIndex != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.landingTabIndex);
            }
            if (this.hasQuickLinkTabIndex || this.quickLinkTabIndex != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(11, this.quickLinkTabIndex);
            }
            if (this.hasQuickLinkFallbackTabIndex || this.quickLinkFallbackTabIndex != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(12, this.quickLinkFallbackTabIndex);
            }
            return size;
        }

        public BrowseResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                BrowseLink[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.contentsUrl = input.readString();
                        this.hasContentsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.promoUrl = input.readString();
                        this.hasPromoUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.category == null) {
                            i = 0;
                        } else {
                            i = this.category.length;
                        }
                        newArray = new BrowseLink[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.category, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new BrowseLink();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new BrowseLink();
                        input.readMessage(newArray[i]);
                        this.category = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.breadcrumb == null) {
                            i = 0;
                        } else {
                            i = this.breadcrumb.length;
                        }
                        newArray = new BrowseLink[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.breadcrumb, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new BrowseLink();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new BrowseLink();
                        input.readMessage(newArray[i]);
                        this.breadcrumb = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.quickLink == null) {
                            i = 0;
                        } else {
                            i = this.quickLink.length;
                        }
                        QuickLink[] newArray2 = new QuickLink[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.quickLink, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new QuickLink();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new QuickLink();
                        input.readMessage(newArray2[i]);
                        this.quickLink = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
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
                                this.backendId = value;
                                this.hasBackendId = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 74);
                        if (this.browseTab == null) {
                            i = 0;
                        } else {
                            i = this.browseTab.length;
                        }
                        BrowseTab[] newArray3 = new BrowseTab[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.browseTab, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new BrowseTab();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new BrowseTab();
                        input.readMessage(newArray3[i]);
                        this.browseTab = newArray3;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.landingTabIndex = input.readInt32();
                        this.hasLandingTabIndex = true;
                        continue;
                    case 88:
                        this.quickLinkTabIndex = input.readInt32();
                        this.hasQuickLinkTabIndex = true;
                        continue;
                    case 96:
                        this.quickLinkFallbackTabIndex = input.readInt32();
                        this.hasQuickLinkFallbackTabIndex = true;
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

    public static final class BrowseTab extends MessageNano {
        private static volatile BrowseTab[] _emptyArray;
        public BrowseLink[] category;
        public boolean hasListUrl;
        public boolean hasServerLogsCookie;
        public boolean hasTitle;
        public String listUrl;
        public byte[] serverLogsCookie;
        public String title;

        public static BrowseTab[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BrowseTab[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BrowseTab() {
            clear();
        }

        public BrowseTab clear() {
            this.title = "";
            this.hasTitle = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.listUrl = "";
            this.hasListUrl = false;
            this.category = BrowseLink.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.serverLogsCookie);
            }
            if (this.hasListUrl || !this.listUrl.equals("")) {
                output.writeString(3, this.listUrl);
            }
            if (this.category != null && this.category.length > 0) {
                for (BrowseLink element : this.category) {
                    if (element != null) {
                        output.writeMessage(4, element);
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
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.serverLogsCookie);
            }
            if (this.hasListUrl || !this.listUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.listUrl);
            }
            if (this.category != null && this.category.length > 0) {
                for (BrowseLink element : this.category) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            return size;
        }

        public BrowseTab mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.listUrl = input.readString();
                        this.hasListUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.category == null) {
                            i = 0;
                        } else {
                            i = this.category.length;
                        }
                        BrowseLink[] newArray = new BrowseLink[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.category, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new BrowseLink();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new BrowseLink();
                        input.readMessage(newArray[i]);
                        this.category = newArray;
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

    public static final class QuickLink extends MessageNano {
        private static volatile QuickLink[] _emptyArray;
        public int backendId;
        public boolean displayRequired;
        public boolean hasBackendId;
        public boolean hasDisplayRequired;
        public boolean hasName;
        public boolean hasPrismStyle;
        public boolean hasServerLogsCookie;
        public Image image;
        public ResolvedLink link;
        public String name;
        public boolean prismStyle;
        public byte[] serverLogsCookie;

        public static QuickLink[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new QuickLink[0];
                    }
                }
            }
            return _emptyArray;
        }

        public QuickLink() {
            clear();
        }

        public QuickLink clear() {
            this.name = "";
            this.hasName = false;
            this.image = null;
            this.link = null;
            this.displayRequired = false;
            this.hasDisplayRequired = false;
            this.backendId = 0;
            this.hasBackendId = false;
            this.prismStyle = false;
            this.hasPrismStyle = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasName || !this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (this.image != null) {
                output.writeMessage(2, this.image);
            }
            if (this.link != null) {
                output.writeMessage(3, this.link);
            }
            if (this.hasDisplayRequired || this.displayRequired) {
                output.writeBool(4, this.displayRequired);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(5, this.serverLogsCookie);
            }
            if (this.backendId != 0 || this.hasBackendId) {
                output.writeInt32(6, this.backendId);
            }
            if (this.hasPrismStyle || this.prismStyle) {
                output.writeBool(7, this.prismStyle);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.image != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.image);
            }
            if (this.link != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.link);
            }
            if (this.hasDisplayRequired || this.displayRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.displayRequired);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(5, this.serverLogsCookie);
            }
            if (this.backendId != 0 || this.hasBackendId) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.backendId);
            }
            if (this.hasPrismStyle || this.prismStyle) {
                return size + CodedOutputByteBufferNano.computeBoolSize(7, this.prismStyle);
            }
            return size;
        }

        public QuickLink mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.image == null) {
                            this.image = new Image();
                        }
                        input.readMessage(this.image);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.link == null) {
                            this.link = new ResolvedLink();
                        }
                        input.readMessage(this.link);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.displayRequired = input.readBool();
                        this.hasDisplayRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.backendId = value;
                                this.hasBackendId = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.prismStyle = input.readBool();
                        this.hasPrismStyle = true;
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
