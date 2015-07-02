package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Acquisition.SuccessInfo;
import com.google.android.finsky.protos.BillingAddress.Address;
import com.google.android.finsky.protos.ChallengeProto.AddressChallenge;
import com.google.android.finsky.protos.CheckPromoOffer.RedeemedPromoOffer;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.SingleFopPaymentsIntegrator.SingleFopPaymentsIntegratorContext;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface PromoCode {

    public static final class RedeemCodeRequest extends MessageNano {
        public Address address;
        public String[] addressCheckedCheckboxId;
        public String code;
        public long consumptionAppVersionCode;
        public Docid docid;
        public boolean hasCode;
        public boolean hasConsumptionAppVersionCode;
        public boolean hasHasUserConfirmation;
        public boolean hasOfferType;
        public boolean hasPartnerPayload;
        public boolean hasPaymentsIntegratorClientContextToken;
        public boolean hasRedemptionContext;
        public boolean hasToken;
        public boolean hasUserConfirmation;
        public int offerType;
        public String partnerPayload;
        public byte[] paymentsIntegratorClientContextToken;
        public int redemptionContext;
        public String token;

        public RedeemCodeRequest() {
            clear();
        }

        public RedeemCodeRequest clear() {
            this.code = "";
            this.hasCode = false;
            this.hasUserConfirmation = false;
            this.hasHasUserConfirmation = false;
            this.address = null;
            this.addressCheckedCheckboxId = WireFormatNano.EMPTY_STRING_ARRAY;
            this.token = "";
            this.hasToken = false;
            this.redemptionContext = 1;
            this.hasRedemptionContext = false;
            this.partnerPayload = "";
            this.hasPartnerPayload = false;
            this.docid = null;
            this.offerType = 1;
            this.hasOfferType = false;
            this.consumptionAppVersionCode = 0;
            this.hasConsumptionAppVersionCode = false;
            this.paymentsIntegratorClientContextToken = WireFormatNano.EMPTY_BYTES;
            this.hasPaymentsIntegratorClientContextToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasCode || !this.code.equals("")) {
                output.writeString(1, this.code);
            }
            if (this.hasHasUserConfirmation || this.hasUserConfirmation) {
                output.writeBool(2, this.hasUserConfirmation);
            }
            if (this.address != null) {
                output.writeMessage(3, this.address);
            }
            if (this.addressCheckedCheckboxId != null && this.addressCheckedCheckboxId.length > 0) {
                for (String element : this.addressCheckedCheckboxId) {
                    if (element != null) {
                        output.writeString(4, element);
                    }
                }
            }
            if (this.hasToken || !this.token.equals("")) {
                output.writeString(5, this.token);
            }
            if (this.redemptionContext != 1 || this.hasRedemptionContext) {
                output.writeInt32(6, this.redemptionContext);
            }
            if (this.hasPartnerPayload || !this.partnerPayload.equals("")) {
                output.writeString(7, this.partnerPayload);
            }
            if (this.docid != null) {
                output.writeMessage(8, this.docid);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                output.writeInt32(9, this.offerType);
            }
            if (this.hasConsumptionAppVersionCode || this.consumptionAppVersionCode != 0) {
                output.writeInt64(10, this.consumptionAppVersionCode);
            }
            if (this.hasPaymentsIntegratorClientContextToken || !Arrays.equals(this.paymentsIntegratorClientContextToken, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(11, this.paymentsIntegratorClientContextToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasCode || !this.code.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.code);
            }
            if (this.hasHasUserConfirmation || this.hasUserConfirmation) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.hasUserConfirmation);
            }
            if (this.address != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.address);
            }
            if (this.addressCheckedCheckboxId != null && this.addressCheckedCheckboxId.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.addressCheckedCheckboxId) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasToken || !this.token.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.token);
            }
            if (this.redemptionContext != 1 || this.hasRedemptionContext) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.redemptionContext);
            }
            if (this.hasPartnerPayload || !this.partnerPayload.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.partnerPayload);
            }
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.docid);
            }
            if (this.offerType != 1 || this.hasOfferType) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.offerType);
            }
            if (this.hasConsumptionAppVersionCode || this.consumptionAppVersionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(10, this.consumptionAppVersionCode);
            }
            if (this.hasPaymentsIntegratorClientContextToken || !Arrays.equals(this.paymentsIntegratorClientContextToken, WireFormatNano.EMPTY_BYTES)) {
                return size + CodedOutputByteBufferNano.computeBytesSize(11, this.paymentsIntegratorClientContextToken);
            }
            return size;
        }

        public RedeemCodeRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.code = input.readString();
                        this.hasCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.hasUserConfirmation = input.readBool();
                        this.hasHasUserConfirmation = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.address == null) {
                            this.address = new Address();
                        }
                        input.readMessage(this.address);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        int i = this.addressCheckedCheckboxId == null ? 0 : this.addressCheckedCheckboxId.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.addressCheckedCheckboxId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.addressCheckedCheckboxId = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.token = input.readString();
                        this.hasToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                this.redemptionContext = value;
                                this.hasRedemptionContext = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.partnerPayload = input.readString();
                        this.hasPartnerPayload = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.docid == null) {
                            this.docid = new Docid();
                        }
                        input.readMessage(this.docid);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
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
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.consumptionAppVersionCode = input.readInt64();
                        this.hasConsumptionAppVersionCode = true;
                        continue;
                    case 90:
                        this.paymentsIntegratorClientContextToken = input.readBytes();
                        this.hasPaymentsIntegratorClientContextToken = true;
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

    public static final class RedeemCodeResponse extends MessageNano {
        public AddressChallenge addressChallenge;
        public Docid consumptionAppDocid;
        public DocV2 doc;
        public String errorMessageHtml;
        public boolean hasErrorMessageHtml;
        public boolean hasResult;
        public boolean hasServerLogsCookie;
        public boolean hasStoredValueInstrumentId;
        public boolean hasToken;
        public SingleFopPaymentsIntegratorContext paymentsIntegratorContext;
        public RedeemedPromoOffer redeemedOffer;
        public int result;
        public byte[] serverLogsCookie;
        public String storedValueInstrumentId;
        public SuccessInfo successInfo;
        public String token;
        public UserConfirmationChallenge userConfirmationChallenge;

        public RedeemCodeResponse() {
            clear();
        }

        public RedeemCodeResponse clear() {
            this.result = 1;
            this.hasResult = false;
            this.token = "";
            this.hasToken = false;
            this.userConfirmationChallenge = null;
            this.addressChallenge = null;
            this.successInfo = null;
            this.errorMessageHtml = "";
            this.hasErrorMessageHtml = false;
            this.redeemedOffer = null;
            this.storedValueInstrumentId = "";
            this.hasStoredValueInstrumentId = false;
            this.doc = null;
            this.consumptionAppDocid = null;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.paymentsIntegratorContext = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.result != 1 || this.hasResult) {
                output.writeInt32(1, this.result);
            }
            if (this.hasToken || !this.token.equals("")) {
                output.writeString(2, this.token);
            }
            if (this.userConfirmationChallenge != null) {
                output.writeMessage(3, this.userConfirmationChallenge);
            }
            if (this.addressChallenge != null) {
                output.writeMessage(4, this.addressChallenge);
            }
            if (this.successInfo != null) {
                output.writeMessage(5, this.successInfo);
            }
            if (this.hasErrorMessageHtml || !this.errorMessageHtml.equals("")) {
                output.writeString(6, this.errorMessageHtml);
            }
            if (this.redeemedOffer != null) {
                output.writeMessage(7, this.redeemedOffer);
            }
            if (this.hasStoredValueInstrumentId || !this.storedValueInstrumentId.equals("")) {
                output.writeString(8, this.storedValueInstrumentId);
            }
            if (this.consumptionAppDocid != null) {
                output.writeMessage(10, this.consumptionAppDocid);
            }
            if (this.doc != null) {
                output.writeMessage(11, this.doc);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(12, this.serverLogsCookie);
            }
            if (this.paymentsIntegratorContext != null) {
                output.writeMessage(13, this.paymentsIntegratorContext);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.result != 1 || this.hasResult) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.result);
            }
            if (this.hasToken || !this.token.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.token);
            }
            if (this.userConfirmationChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.userConfirmationChallenge);
            }
            if (this.addressChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.addressChallenge);
            }
            if (this.successInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.successInfo);
            }
            if (this.hasErrorMessageHtml || !this.errorMessageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.errorMessageHtml);
            }
            if (this.redeemedOffer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.redeemedOffer);
            }
            if (this.hasStoredValueInstrumentId || !this.storedValueInstrumentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.storedValueInstrumentId);
            }
            if (this.consumptionAppDocid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.consumptionAppDocid);
            }
            if (this.doc != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.doc);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(12, this.serverLogsCookie);
            }
            if (this.paymentsIntegratorContext != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(13, this.paymentsIntegratorContext);
            }
            return size;
        }

        public RedeemCodeResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.result = value;
                                this.hasResult = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.token = input.readString();
                        this.hasToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.userConfirmationChallenge == null) {
                            this.userConfirmationChallenge = new UserConfirmationChallenge();
                        }
                        input.readMessage(this.userConfirmationChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.addressChallenge == null) {
                            this.addressChallenge = new AddressChallenge();
                        }
                        input.readMessage(this.addressChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.successInfo == null) {
                            this.successInfo = new SuccessInfo();
                        }
                        input.readMessage(this.successInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.errorMessageHtml = input.readString();
                        this.hasErrorMessageHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.redeemedOffer == null) {
                            this.redeemedOffer = new RedeemedPromoOffer();
                        }
                        input.readMessage(this.redeemedOffer);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.storedValueInstrumentId = input.readString();
                        this.hasStoredValueInstrumentId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.consumptionAppDocid == null) {
                            this.consumptionAppDocid = new Docid();
                        }
                        input.readMessage(this.consumptionAppDocid);
                        continue;
                    case 90:
                        if (this.doc == null) {
                            this.doc = new DocV2();
                        }
                        input.readMessage(this.doc);
                        continue;
                    case 98:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case 106:
                        if (this.paymentsIntegratorContext == null) {
                            this.paymentsIntegratorContext = new SingleFopPaymentsIntegratorContext();
                        }
                        input.readMessage(this.paymentsIntegratorContext);
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

    public static final class UserConfirmationChallenge extends MessageNano {
        public String buttonLabel;
        public String footerHtml;
        public String formattedPrice;
        public boolean hasButtonLabel;
        public boolean hasFooterHtml;
        public boolean hasFormattedPrice;
        public boolean hasMessageHtml;
        public boolean hasPriceBylineHtml;
        public boolean hasTitle;
        public boolean hasTitleBylineHtml;
        public String messageHtml;
        public String priceBylineHtml;
        public Image thumbnailImage;
        public String title;
        public String titleBylineHtml;

        public UserConfirmationChallenge() {
            clear();
        }

        public UserConfirmationChallenge clear() {
            this.title = "";
            this.hasTitle = false;
            this.titleBylineHtml = "";
            this.hasTitleBylineHtml = false;
            this.formattedPrice = "";
            this.hasFormattedPrice = false;
            this.priceBylineHtml = "";
            this.hasPriceBylineHtml = false;
            this.buttonLabel = "";
            this.hasButtonLabel = false;
            this.thumbnailImage = null;
            this.messageHtml = "";
            this.hasMessageHtml = false;
            this.footerHtml = "";
            this.hasFooterHtml = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasTitleBylineHtml || !this.titleBylineHtml.equals("")) {
                output.writeString(2, this.titleBylineHtml);
            }
            if (this.hasFormattedPrice || !this.formattedPrice.equals("")) {
                output.writeString(3, this.formattedPrice);
            }
            if (this.hasPriceBylineHtml || !this.priceBylineHtml.equals("")) {
                output.writeString(4, this.priceBylineHtml);
            }
            if (this.hasButtonLabel || !this.buttonLabel.equals("")) {
                output.writeString(5, this.buttonLabel);
            }
            if (this.thumbnailImage != null) {
                output.writeMessage(6, this.thumbnailImage);
            }
            if (this.hasMessageHtml || !this.messageHtml.equals("")) {
                output.writeString(7, this.messageHtml);
            }
            if (this.hasFooterHtml || !this.footerHtml.equals("")) {
                output.writeString(8, this.footerHtml);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasTitleBylineHtml || !this.titleBylineHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.titleBylineHtml);
            }
            if (this.hasFormattedPrice || !this.formattedPrice.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.formattedPrice);
            }
            if (this.hasPriceBylineHtml || !this.priceBylineHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.priceBylineHtml);
            }
            if (this.hasButtonLabel || !this.buttonLabel.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.buttonLabel);
            }
            if (this.thumbnailImage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.thumbnailImage);
            }
            if (this.hasMessageHtml || !this.messageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.messageHtml);
            }
            if (this.hasFooterHtml || !this.footerHtml.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(8, this.footerHtml);
            }
            return size;
        }

        public UserConfirmationChallenge mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.titleBylineHtml = input.readString();
                        this.hasTitleBylineHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.formattedPrice = input.readString();
                        this.hasFormattedPrice = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.priceBylineHtml = input.readString();
                        this.hasPriceBylineHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.buttonLabel = input.readString();
                        this.hasButtonLabel = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.thumbnailImage == null) {
                            this.thumbnailImage = new Image();
                        }
                        input.readMessage(this.thumbnailImage);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.messageHtml = input.readString();
                        this.hasMessageHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.footerHtml = input.readString();
                        this.hasFooterHtml = true;
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
