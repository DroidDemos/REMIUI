package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface CheckPromoOffer {

    public static final class AddCreditCardPromoOffer extends MessageNano {
        public String descriptionHtml;
        public boolean hasDescriptionHtml;
        public boolean hasHeaderText;
        public boolean hasIntroductoryTextHtml;
        public boolean hasNoActionDescription;
        public boolean hasOfferTitle;
        public boolean hasTermsAndConditionsHtml;
        public String headerText;
        public Image image;
        public String introductoryTextHtml;
        public String noActionDescription;
        public String offerTitle;
        public String termsAndConditionsHtml;

        public AddCreditCardPromoOffer() {
            clear();
        }

        public AddCreditCardPromoOffer clear() {
            this.headerText = "";
            this.hasHeaderText = false;
            this.introductoryTextHtml = "";
            this.hasIntroductoryTextHtml = false;
            this.offerTitle = "";
            this.hasOfferTitle = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.termsAndConditionsHtml = "";
            this.hasTermsAndConditionsHtml = false;
            this.image = null;
            this.noActionDescription = "";
            this.hasNoActionDescription = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasHeaderText || !this.headerText.equals("")) {
                output.writeString(1, this.headerText);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(2, this.descriptionHtml);
            }
            if (this.image != null) {
                output.writeMessage(3, this.image);
            }
            if (this.hasIntroductoryTextHtml || !this.introductoryTextHtml.equals("")) {
                output.writeString(4, this.introductoryTextHtml);
            }
            if (this.hasOfferTitle || !this.offerTitle.equals("")) {
                output.writeString(5, this.offerTitle);
            }
            if (this.hasNoActionDescription || !this.noActionDescription.equals("")) {
                output.writeString(6, this.noActionDescription);
            }
            if (this.hasTermsAndConditionsHtml || !this.termsAndConditionsHtml.equals("")) {
                output.writeString(7, this.termsAndConditionsHtml);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasHeaderText || !this.headerText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.headerText);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.descriptionHtml);
            }
            if (this.image != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.image);
            }
            if (this.hasIntroductoryTextHtml || !this.introductoryTextHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.introductoryTextHtml);
            }
            if (this.hasOfferTitle || !this.offerTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.offerTitle);
            }
            if (this.hasNoActionDescription || !this.noActionDescription.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.noActionDescription);
            }
            if (this.hasTermsAndConditionsHtml || !this.termsAndConditionsHtml.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(7, this.termsAndConditionsHtml);
            }
            return size;
        }

        public AddCreditCardPromoOffer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.headerText = input.readString();
                        this.hasHeaderText = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.image == null) {
                            this.image = new Image();
                        }
                        input.readMessage(this.image);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.introductoryTextHtml = input.readString();
                        this.hasIntroductoryTextHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.offerTitle = input.readString();
                        this.hasOfferTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.noActionDescription = input.readString();
                        this.hasNoActionDescription = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.termsAndConditionsHtml = input.readString();
                        this.hasTermsAndConditionsHtml = true;
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

    public static final class AvailablePromoOffer extends MessageNano {
        private static volatile AvailablePromoOffer[] _emptyArray;
        public AddCreditCardPromoOffer addCreditCardOffer;

        public static AvailablePromoOffer[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AvailablePromoOffer[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AvailablePromoOffer() {
            clear();
        }

        public AvailablePromoOffer clear() {
            this.addCreditCardOffer = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.addCreditCardOffer != null) {
                output.writeMessage(1, this.addCreditCardOffer);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.addCreditCardOffer != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.addCreditCardOffer);
            }
            return size;
        }

        public AvailablePromoOffer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.addCreditCardOffer == null) {
                            this.addCreditCardOffer = new AddCreditCardPromoOffer();
                        }
                        input.readMessage(this.addCreditCardOffer);
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

    public static final class CheckPromoOfferResponse extends MessageNano {
        public AvailablePromoOffer[] availableOffer;
        public int availablePromoOfferStatus;
        public boolean checkoutTokenRequired;
        public boolean hasAvailablePromoOfferStatus;
        public boolean hasCheckoutTokenRequired;
        public RedeemedPromoOffer redeemedOffer;

        public CheckPromoOfferResponse() {
            clear();
        }

        public CheckPromoOfferResponse clear() {
            this.availableOffer = AvailablePromoOffer.emptyArray();
            this.redeemedOffer = null;
            this.checkoutTokenRequired = false;
            this.hasCheckoutTokenRequired = false;
            this.availablePromoOfferStatus = 0;
            this.hasAvailablePromoOfferStatus = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.availableOffer != null && this.availableOffer.length > 0) {
                for (AvailablePromoOffer element : this.availableOffer) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.redeemedOffer != null) {
                output.writeMessage(2, this.redeemedOffer);
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                output.writeBool(3, this.checkoutTokenRequired);
            }
            if (this.availablePromoOfferStatus != 0 || this.hasAvailablePromoOfferStatus) {
                output.writeInt32(4, this.availablePromoOfferStatus);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.availableOffer != null && this.availableOffer.length > 0) {
                for (AvailablePromoOffer element : this.availableOffer) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.redeemedOffer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.redeemedOffer);
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.checkoutTokenRequired);
            }
            if (this.availablePromoOfferStatus != 0 || this.hasAvailablePromoOfferStatus) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.availablePromoOfferStatus);
            }
            return size;
        }

        public CheckPromoOfferResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.availableOffer == null) {
                            i = 0;
                        } else {
                            i = this.availableOffer.length;
                        }
                        AvailablePromoOffer[] newArray = new AvailablePromoOffer[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.availableOffer, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AvailablePromoOffer();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AvailablePromoOffer();
                        input.readMessage(newArray[i]);
                        this.availableOffer = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.redeemedOffer == null) {
                            this.redeemedOffer = new RedeemedPromoOffer();
                        }
                        input.readMessage(this.redeemedOffer);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.checkoutTokenRequired = input.readBool();
                        this.hasCheckoutTokenRequired = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.availablePromoOfferStatus = value;
                                this.hasAvailablePromoOfferStatus = true;
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

    public static final class RedeemedPromoOffer extends MessageNano {
        public String descriptionHtml;
        public boolean hasDescriptionHtml;
        public boolean hasHeaderText;
        public String headerText;
        public Image image;

        public RedeemedPromoOffer() {
            clear();
        }

        public RedeemedPromoOffer clear() {
            this.headerText = "";
            this.hasHeaderText = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.image = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasHeaderText || !this.headerText.equals("")) {
                output.writeString(1, this.headerText);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(2, this.descriptionHtml);
            }
            if (this.image != null) {
                output.writeMessage(3, this.image);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasHeaderText || !this.headerText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.headerText);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.descriptionHtml);
            }
            if (this.image != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.image);
            }
            return size;
        }

        public RedeemedPromoOffer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.headerText = input.readString();
                        this.hasHeaderText = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.image == null) {
                            this.image = new Image();
                        }
                        input.readMessage(this.image);
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
