package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Docid;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface ResolveLink {

    public static final class DirectPurchase extends MessageNano {
        public String detailsUrl;
        public boolean hasDetailsUrl;
        public boolean hasOfferType;
        public boolean hasParentDocid;
        public boolean hasPurchaseDocid;
        public int offerType;
        public String parentDocid;
        public String purchaseDocid;

        public DirectPurchase() {
            clear();
        }

        public DirectPurchase clear() {
            this.detailsUrl = "";
            this.hasDetailsUrl = false;
            this.purchaseDocid = "";
            this.hasPurchaseDocid = false;
            this.parentDocid = "";
            this.hasParentDocid = false;
            this.offerType = 1;
            this.hasOfferType = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                output.writeString(1, this.detailsUrl);
            }
            if (this.hasPurchaseDocid || !this.purchaseDocid.equals("")) {
                output.writeString(2, this.purchaseDocid);
            }
            if (this.hasParentDocid || !this.parentDocid.equals("")) {
                output.writeString(3, this.parentDocid);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                output.writeInt32(4, this.offerType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.detailsUrl);
            }
            if (this.hasPurchaseDocid || !this.purchaseDocid.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.purchaseDocid);
            }
            if (this.hasParentDocid || !this.parentDocid.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.parentDocid);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.offerType);
            }
            return size;
        }

        public DirectPurchase mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.detailsUrl = input.readString();
                        this.hasDetailsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.purchaseDocid = input.readString();
                        this.hasPurchaseDocid = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.parentDocid = input.readString();
                        this.hasParentDocid = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
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

    public static final class RedeemGiftCard extends MessageNano {
        public boolean hasPartnerPayload;
        public boolean hasPrefillCode;
        public String partnerPayload;
        public String prefillCode;

        public RedeemGiftCard() {
            clear();
        }

        public RedeemGiftCard clear() {
            this.prefillCode = "";
            this.hasPrefillCode = false;
            this.partnerPayload = "";
            this.hasPartnerPayload = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPrefillCode || !this.prefillCode.equals("")) {
                output.writeString(1, this.prefillCode);
            }
            if (this.hasPartnerPayload || !this.partnerPayload.equals("")) {
                output.writeString(2, this.partnerPayload);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPrefillCode || !this.prefillCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.prefillCode);
            }
            if (this.hasPartnerPayload || !this.partnerPayload.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.partnerPayload);
            }
            return size;
        }

        public RedeemGiftCard mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.prefillCode = input.readString();
                        this.hasPrefillCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.partnerPayload = input.readString();
                        this.hasPartnerPayload = true;
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

    public static final class ResolvedLink extends MessageNano {
        public int backend;
        public String browseUrl;
        public String detailsUrl;
        public DirectPurchase directPurchase;
        public Docid docid;
        public boolean hasBackend;
        public boolean hasBrowseUrl;
        public boolean hasDetailsUrl;
        public boolean hasHomeUrl;
        public boolean hasMyAccountUrl;
        public boolean hasQuery;
        public boolean hasSearchUrl;
        public boolean hasServerLogsCookie;
        public boolean hasWishlistUrl;
        public String homeUrl;
        public String myAccountUrl;
        public String query;
        public RedeemGiftCard redeemGiftCard;
        public String searchUrl;
        public byte[] serverLogsCookie;
        public String wishlistUrl;

        public ResolvedLink() {
            clear();
        }

        public ResolvedLink clear() {
            this.detailsUrl = "";
            this.hasDetailsUrl = false;
            this.browseUrl = "";
            this.hasBrowseUrl = false;
            this.searchUrl = "";
            this.hasSearchUrl = false;
            this.wishlistUrl = "";
            this.hasWishlistUrl = false;
            this.myAccountUrl = "";
            this.hasMyAccountUrl = false;
            this.directPurchase = null;
            this.homeUrl = "";
            this.hasHomeUrl = false;
            this.redeemGiftCard = null;
            this.docid = null;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.backend = 0;
            this.hasBackend = false;
            this.query = "";
            this.hasQuery = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                output.writeString(1, this.detailsUrl);
            }
            if (this.hasBrowseUrl || !this.browseUrl.equals("")) {
                output.writeString(2, this.browseUrl);
            }
            if (this.hasSearchUrl || !this.searchUrl.equals("")) {
                output.writeString(3, this.searchUrl);
            }
            if (this.directPurchase != null) {
                output.writeMessage(4, this.directPurchase);
            }
            if (this.hasHomeUrl || !this.homeUrl.equals("")) {
                output.writeString(5, this.homeUrl);
            }
            if (this.redeemGiftCard != null) {
                output.writeMessage(6, this.redeemGiftCard);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.serverLogsCookie);
            }
            if (this.docid != null) {
                output.writeMessage(8, this.docid);
            }
            if (this.hasWishlistUrl || !this.wishlistUrl.equals("")) {
                output.writeString(9, this.wishlistUrl);
            }
            if (this.backend != 0 || this.hasBackend) {
                output.writeInt32(10, this.backend);
            }
            if (this.hasQuery || !this.query.equals("")) {
                output.writeString(11, this.query);
            }
            if (this.hasMyAccountUrl || !this.myAccountUrl.equals("")) {
                output.writeString(12, this.myAccountUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.detailsUrl);
            }
            if (this.hasBrowseUrl || !this.browseUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.browseUrl);
            }
            if (this.hasSearchUrl || !this.searchUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.searchUrl);
            }
            if (this.directPurchase != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.directPurchase);
            }
            if (this.hasHomeUrl || !this.homeUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.homeUrl);
            }
            if (this.redeemGiftCard != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.redeemGiftCard);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(7, this.serverLogsCookie);
            }
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.docid);
            }
            if (this.hasWishlistUrl || !this.wishlistUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.wishlistUrl);
            }
            if (this.backend != 0 || this.hasBackend) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.backend);
            }
            if (this.hasQuery || !this.query.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.query);
            }
            if (this.hasMyAccountUrl || !this.myAccountUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(12, this.myAccountUrl);
            }
            return size;
        }

        public ResolvedLink mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.detailsUrl = input.readString();
                        this.hasDetailsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.browseUrl = input.readString();
                        this.hasBrowseUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.searchUrl = input.readString();
                        this.hasSearchUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.directPurchase == null) {
                            this.directPurchase = new DirectPurchase();
                        }
                        input.readMessage(this.directPurchase);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.homeUrl = input.readString();
                        this.hasHomeUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.redeemGiftCard == null) {
                            this.redeemGiftCard = new RedeemGiftCard();
                        }
                        input.readMessage(this.redeemGiftCard);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.docid == null) {
                            this.docid = new Docid();
                        }
                        input.readMessage(this.docid);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.wishlistUrl = input.readString();
                        this.hasWishlistUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
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
                    case 90:
                        this.query = input.readString();
                        this.hasQuery = true;
                        continue;
                    case 98:
                        this.myAccountUrl = input.readString();
                        this.hasMyAccountUrl = true;
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
