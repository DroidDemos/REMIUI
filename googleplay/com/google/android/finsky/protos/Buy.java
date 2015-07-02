package com.google.android.finsky.protos;

import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.protos.ChallengeProto.Challenge;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.DebugInfoProto.DebugInfo;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface Buy {

    public static final class BuyResponse extends MessageNano {
        public String addInstrumentPromptHtml;
        public String baseCheckoutUrl;
        public Challenge challenge;
        public CheckoutInfo checkoutInfo;
        public String checkoutServiceId;
        public boolean checkoutTokenRequired;
        public String confirmButtonText;
        public String continueViaUrl;
        public String encodedDeliveryToken;
        public boolean hasAddInstrumentPromptHtml;
        public boolean hasBaseCheckoutUrl;
        public boolean hasCheckoutServiceId;
        public boolean hasCheckoutTokenRequired;
        public boolean hasConfirmButtonText;
        public boolean hasContinueViaUrl;
        public boolean hasEncodedDeliveryToken;
        public boolean hasPermissionError;
        public boolean hasPermissionErrorMessageText;
        public boolean hasPermissionErrorTitleText;
        public boolean hasPurchaseCookie;
        public boolean hasPurchaseStatusUrl;
        public boolean hasServerLogsCookie;
        public int permissionError;
        public String permissionErrorMessageText;
        public String permissionErrorTitleText;
        public String purchaseCookie;
        public PurchaseNotificationResponse purchaseResponse;
        public PurchaseStatusResponse purchaseStatusResponse;
        public String purchaseStatusUrl;
        public byte[] serverLogsCookie;
        public String[] tosCheckboxHtml;

        public static final class CheckoutInfo extends MessageNano {
            public String addInstrumentUrl;
            public CheckoutOption[] checkoutOption;
            public String deprecatedCheckoutUrl;
            public Instrument[] eligibleInstrument;
            public int[] eligibleInstrumentFamily;
            public String[] footerHtml;
            public String[] footnoteHtml;
            public boolean hasAddInstrumentUrl;
            public boolean hasDeprecatedCheckoutUrl;
            public LineItem item;
            public LineItem[] subItem;

            public static final class CheckoutOption extends MessageNano {
                private static volatile CheckoutOption[] _emptyArray;
                public String[] disabledReason;
                public String encodedAdjustedCart;
                public String[] footerHtml;
                public String[] footnoteHtml;
                public String formOfPayment;
                public boolean hasEncodedAdjustedCart;
                public boolean hasFormOfPayment;
                public boolean hasInstrumentFamily;
                public boolean hasInstrumentId;
                public boolean hasPurchaseCookie;
                public boolean hasSelectedInstrument;
                public Instrument instrument;
                public int instrumentFamily;
                public String instrumentId;
                public LineItem[] item;
                public String purchaseCookie;
                public boolean selectedInstrument;
                public LineItem[] subItem;
                public LineItem summary;
                public LineItem total;

                public static CheckoutOption[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new CheckoutOption[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public CheckoutOption() {
                    clear();
                }

                public CheckoutOption clear() {
                    this.formOfPayment = "";
                    this.hasFormOfPayment = false;
                    this.instrumentId = "";
                    this.hasInstrumentId = false;
                    this.instrumentFamily = 0;
                    this.hasInstrumentFamily = false;
                    this.disabledReason = WireFormatNano.EMPTY_STRING_ARRAY;
                    this.selectedInstrument = false;
                    this.hasSelectedInstrument = false;
                    this.summary = null;
                    this.item = LineItem.emptyArray();
                    this.subItem = LineItem.emptyArray();
                    this.total = null;
                    this.footerHtml = WireFormatNano.EMPTY_STRING_ARRAY;
                    this.footnoteHtml = WireFormatNano.EMPTY_STRING_ARRAY;
                    this.encodedAdjustedCart = "";
                    this.hasEncodedAdjustedCart = false;
                    this.instrument = null;
                    this.purchaseCookie = "";
                    this.hasPurchaseCookie = false;
                    this.cachedSize = -1;
                    return this;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (this.hasFormOfPayment || !this.formOfPayment.equals("")) {
                        output.writeString(6, this.formOfPayment);
                    }
                    if (this.hasEncodedAdjustedCart || !this.encodedAdjustedCart.equals("")) {
                        output.writeString(7, this.encodedAdjustedCart);
                    }
                    if (this.hasInstrumentId || !this.instrumentId.equals("")) {
                        output.writeString(15, this.instrumentId);
                    }
                    if (this.item != null && this.item.length > 0) {
                        for (LineItem element : this.item) {
                            if (element != null) {
                                output.writeMessage(16, element);
                            }
                        }
                    }
                    if (this.subItem != null && this.subItem.length > 0) {
                        for (LineItem element2 : this.subItem) {
                            if (element2 != null) {
                                output.writeMessage(17, element2);
                            }
                        }
                    }
                    if (this.total != null) {
                        output.writeMessage(18, this.total);
                    }
                    if (this.footerHtml != null && this.footerHtml.length > 0) {
                        for (String element3 : this.footerHtml) {
                            if (element3 != null) {
                                output.writeString(19, element3);
                            }
                        }
                    }
                    if (this.instrumentFamily != 0 || this.hasInstrumentFamily) {
                        output.writeInt32(29, this.instrumentFamily);
                    }
                    if (this.hasSelectedInstrument || this.selectedInstrument) {
                        output.writeBool(32, this.selectedInstrument);
                    }
                    if (this.summary != null) {
                        output.writeMessage(33, this.summary);
                    }
                    if (this.footnoteHtml != null && this.footnoteHtml.length > 0) {
                        for (String element32 : this.footnoteHtml) {
                            if (element32 != null) {
                                output.writeString(35, element32);
                            }
                        }
                    }
                    if (this.instrument != null) {
                        output.writeMessage(43, this.instrument);
                    }
                    if (this.hasPurchaseCookie || !this.purchaseCookie.equals("")) {
                        output.writeString(45, this.purchaseCookie);
                    }
                    if (this.disabledReason != null && this.disabledReason.length > 0) {
                        for (String element322 : this.disabledReason) {
                            if (element322 != null) {
                                output.writeString(48, element322);
                            }
                        }
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int dataCount;
                    int dataSize;
                    int size = super.computeSerializedSize();
                    if (this.hasFormOfPayment || !this.formOfPayment.equals("")) {
                        size += CodedOutputByteBufferNano.computeStringSize(6, this.formOfPayment);
                    }
                    if (this.hasEncodedAdjustedCart || !this.encodedAdjustedCart.equals("")) {
                        size += CodedOutputByteBufferNano.computeStringSize(7, this.encodedAdjustedCart);
                    }
                    if (this.hasInstrumentId || !this.instrumentId.equals("")) {
                        size += CodedOutputByteBufferNano.computeStringSize(15, this.instrumentId);
                    }
                    if (this.item != null && this.item.length > 0) {
                        for (LineItem element : this.item) {
                            if (element != null) {
                                size += CodedOutputByteBufferNano.computeMessageSize(16, element);
                            }
                        }
                    }
                    if (this.subItem != null && this.subItem.length > 0) {
                        for (LineItem element2 : this.subItem) {
                            if (element2 != null) {
                                size += CodedOutputByteBufferNano.computeMessageSize(17, element2);
                            }
                        }
                    }
                    if (this.total != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(18, this.total);
                    }
                    if (this.footerHtml != null && this.footerHtml.length > 0) {
                        dataCount = 0;
                        dataSize = 0;
                        for (String element3 : this.footerHtml) {
                            if (element3 != null) {
                                dataCount++;
                                dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element3);
                            }
                        }
                        size = (size + dataSize) + (dataCount * 2);
                    }
                    if (this.instrumentFamily != 0 || this.hasInstrumentFamily) {
                        size += CodedOutputByteBufferNano.computeInt32Size(29, this.instrumentFamily);
                    }
                    if (this.hasSelectedInstrument || this.selectedInstrument) {
                        size += CodedOutputByteBufferNano.computeBoolSize(32, this.selectedInstrument);
                    }
                    if (this.summary != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(33, this.summary);
                    }
                    if (this.footnoteHtml != null && this.footnoteHtml.length > 0) {
                        dataCount = 0;
                        dataSize = 0;
                        for (String element32 : this.footnoteHtml) {
                            if (element32 != null) {
                                dataCount++;
                                dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element32);
                            }
                        }
                        size = (size + dataSize) + (dataCount * 2);
                    }
                    if (this.instrument != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(43, this.instrument);
                    }
                    if (this.hasPurchaseCookie || !this.purchaseCookie.equals("")) {
                        size += CodedOutputByteBufferNano.computeStringSize(45, this.purchaseCookie);
                    }
                    if (this.disabledReason == null || this.disabledReason.length <= 0) {
                        return size;
                    }
                    dataCount = 0;
                    dataSize = 0;
                    for (String element322 : this.disabledReason) {
                        if (element322 != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element322);
                        }
                    }
                    return (size + dataSize) + (dataCount * 2);
                }

                public CheckoutOption mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        int arrayLength;
                        int i;
                        LineItem[] newArray;
                        String[] newArray2;
                        switch (tag) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                break;
                            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                                this.formOfPayment = input.readString();
                                this.hasFormOfPayment = true;
                                continue;
                            case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                                this.encodedAdjustedCart = input.readString();
                                this.hasEncodedAdjustedCart = true;
                                continue;
                            case 122:
                                this.instrumentId = input.readString();
                                this.hasInstrumentId = true;
                                continue;
                            case 130:
                                arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 130);
                                if (this.item == null) {
                                    i = 0;
                                } else {
                                    i = this.item.length;
                                }
                                newArray = new LineItem[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.item, 0, newArray, 0, i);
                                }
                                while (i < newArray.length - 1) {
                                    newArray[i] = new LineItem();
                                    input.readMessage(newArray[i]);
                                    input.readTag();
                                    i++;
                                }
                                newArray[i] = new LineItem();
                                input.readMessage(newArray[i]);
                                this.item = newArray;
                                continue;
                            case 138:
                                arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 138);
                                if (this.subItem == null) {
                                    i = 0;
                                } else {
                                    i = this.subItem.length;
                                }
                                newArray = new LineItem[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.subItem, 0, newArray, 0, i);
                                }
                                while (i < newArray.length - 1) {
                                    newArray[i] = new LineItem();
                                    input.readMessage(newArray[i]);
                                    input.readTag();
                                    i++;
                                }
                                newArray[i] = new LineItem();
                                input.readMessage(newArray[i]);
                                this.subItem = newArray;
                                continue;
                            case 146:
                                if (this.total == null) {
                                    this.total = new LineItem();
                                }
                                input.readMessage(this.total);
                                continue;
                            case 154:
                                arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 154);
                                i = this.footerHtml == null ? 0 : this.footerHtml.length;
                                newArray2 = new String[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.footerHtml, 0, newArray2, 0, i);
                                }
                                while (i < newArray2.length - 1) {
                                    newArray2[i] = input.readString();
                                    input.readTag();
                                    i++;
                                }
                                newArray2[i] = input.readString();
                                this.footerHtml = newArray2;
                                continue;
                            case 232:
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
                                    case 100:
                                        this.instrumentFamily = value;
                                        this.hasInstrumentFamily = true;
                                        break;
                                    default:
                                        continue;
                                }
                            case 256:
                                this.selectedInstrument = input.readBool();
                                this.hasSelectedInstrument = true;
                                continue;
                            case 266:
                                if (this.summary == null) {
                                    this.summary = new LineItem();
                                }
                                input.readMessage(this.summary);
                                continue;
                            case 282:
                                arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 282);
                                i = this.footnoteHtml == null ? 0 : this.footnoteHtml.length;
                                newArray2 = new String[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.footnoteHtml, 0, newArray2, 0, i);
                                }
                                while (i < newArray2.length - 1) {
                                    newArray2[i] = input.readString();
                                    input.readTag();
                                    i++;
                                }
                                newArray2[i] = input.readString();
                                this.footnoteHtml = newArray2;
                                continue;
                            case 346:
                                if (this.instrument == null) {
                                    this.instrument = new Instrument();
                                }
                                input.readMessage(this.instrument);
                                continue;
                            case 362:
                                this.purchaseCookie = input.readString();
                                this.hasPurchaseCookie = true;
                                continue;
                            case 386:
                                arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 386);
                                i = this.disabledReason == null ? 0 : this.disabledReason.length;
                                newArray2 = new String[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.disabledReason, 0, newArray2, 0, i);
                                }
                                while (i < newArray2.length - 1) {
                                    newArray2[i] = input.readString();
                                    input.readTag();
                                    i++;
                                }
                                newArray2[i] = input.readString();
                                this.disabledReason = newArray2;
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

            public CheckoutInfo() {
                clear();
            }

            public CheckoutInfo clear() {
                this.item = null;
                this.subItem = LineItem.emptyArray();
                this.checkoutOption = CheckoutOption.emptyArray();
                this.deprecatedCheckoutUrl = "";
                this.hasDeprecatedCheckoutUrl = false;
                this.addInstrumentUrl = "";
                this.hasAddInstrumentUrl = false;
                this.footerHtml = WireFormatNano.EMPTY_STRING_ARRAY;
                this.footnoteHtml = WireFormatNano.EMPTY_STRING_ARRAY;
                this.eligibleInstrumentFamily = WireFormatNano.EMPTY_INT_ARRAY;
                this.eligibleInstrument = Instrument.emptyArray();
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.item != null) {
                    output.writeMessage(3, this.item);
                }
                if (this.subItem != null && this.subItem.length > 0) {
                    for (LineItem element : this.subItem) {
                        if (element != null) {
                            output.writeMessage(4, element);
                        }
                    }
                }
                if (this.checkoutOption != null && this.checkoutOption.length > 0) {
                    for (CheckoutOption element2 : this.checkoutOption) {
                        if (element2 != null) {
                            output.writeGroup(5, element2);
                        }
                    }
                }
                if (this.hasDeprecatedCheckoutUrl || !this.deprecatedCheckoutUrl.equals("")) {
                    output.writeString(10, this.deprecatedCheckoutUrl);
                }
                if (this.hasAddInstrumentUrl || !this.addInstrumentUrl.equals("")) {
                    output.writeString(11, this.addInstrumentUrl);
                }
                if (this.footerHtml != null && this.footerHtml.length > 0) {
                    for (String element3 : this.footerHtml) {
                        if (element3 != null) {
                            output.writeString(20, element3);
                        }
                    }
                }
                if (this.eligibleInstrumentFamily != null && this.eligibleInstrumentFamily.length > 0) {
                    for (int writeInt32 : this.eligibleInstrumentFamily) {
                        output.writeInt32(31, writeInt32);
                    }
                }
                if (this.footnoteHtml != null && this.footnoteHtml.length > 0) {
                    for (String element32 : this.footnoteHtml) {
                        if (element32 != null) {
                            output.writeString(36, element32);
                        }
                    }
                }
                if (this.eligibleInstrument != null && this.eligibleInstrument.length > 0) {
                    for (Instrument element4 : this.eligibleInstrument) {
                        if (element4 != null) {
                            output.writeMessage(44, element4);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int dataCount;
                int dataSize;
                int size = super.computeSerializedSize();
                if (this.item != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(3, this.item);
                }
                if (this.subItem != null && this.subItem.length > 0) {
                    for (LineItem element : this.subItem) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                        }
                    }
                }
                if (this.checkoutOption != null && this.checkoutOption.length > 0) {
                    for (CheckoutOption element2 : this.checkoutOption) {
                        if (element2 != null) {
                            size += CodedOutputByteBufferNano.computeGroupSize(5, element2);
                        }
                    }
                }
                if (this.hasDeprecatedCheckoutUrl || !this.deprecatedCheckoutUrl.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(10, this.deprecatedCheckoutUrl);
                }
                if (this.hasAddInstrumentUrl || !this.addInstrumentUrl.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(11, this.addInstrumentUrl);
                }
                if (this.footerHtml != null && this.footerHtml.length > 0) {
                    dataCount = 0;
                    dataSize = 0;
                    for (String element3 : this.footerHtml) {
                        if (element3 != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element3);
                        }
                    }
                    size = (size + dataSize) + (dataCount * 2);
                }
                if (this.eligibleInstrumentFamily != null && this.eligibleInstrumentFamily.length > 0) {
                    dataSize = 0;
                    for (int element4 : this.eligibleInstrumentFamily) {
                        dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element4);
                    }
                    size = (size + dataSize) + (this.eligibleInstrumentFamily.length * 2);
                }
                if (this.footnoteHtml != null && this.footnoteHtml.length > 0) {
                    dataCount = 0;
                    dataSize = 0;
                    for (String element32 : this.footnoteHtml) {
                        if (element32 != null) {
                            dataCount++;
                            dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element32);
                        }
                    }
                    size = (size + dataSize) + (dataCount * 2);
                }
                if (this.eligibleInstrument != null && this.eligibleInstrument.length > 0) {
                    for (Instrument element5 : this.eligibleInstrument) {
                        if (element5 != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(44, element5);
                        }
                    }
                }
                return size;
            }

            public CheckoutInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    int arrayLength;
                    int i;
                    String[] newArray;
                    int value;
                    int[] newArray2;
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            if (this.item == null) {
                                this.item = new LineItem();
                            }
                            input.readMessage(this.item);
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                            if (this.subItem == null) {
                                i = 0;
                            } else {
                                i = this.subItem.length;
                            }
                            LineItem[] newArray3 = new LineItem[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.subItem, 0, newArray3, 0, i);
                            }
                            while (i < newArray3.length - 1) {
                                newArray3[i] = new LineItem();
                                input.readMessage(newArray3[i]);
                                input.readTag();
                                i++;
                            }
                            newArray3[i] = new LineItem();
                            input.readMessage(newArray3[i]);
                            this.subItem = newArray3;
                            continue;
                        case com.google.android.play.R.styleable.Theme_homeAsUpIndicator /*43*/:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 43);
                            if (this.checkoutOption == null) {
                                i = 0;
                            } else {
                                i = this.checkoutOption.length;
                            }
                            CheckoutOption[] newArray4 = new CheckoutOption[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.checkoutOption, 0, newArray4, 0, i);
                            }
                            while (i < newArray4.length - 1) {
                                newArray4[i] = new CheckoutOption();
                                input.readGroup(newArray4[i], 5);
                                input.readTag();
                                i++;
                            }
                            newArray4[i] = new CheckoutOption();
                            input.readGroup(newArray4[i], 5);
                            this.checkoutOption = newArray4;
                            continue;
                        case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                            this.deprecatedCheckoutUrl = input.readString();
                            this.hasDeprecatedCheckoutUrl = true;
                            continue;
                        case 90:
                            this.addInstrumentUrl = input.readString();
                            this.hasAddInstrumentUrl = true;
                            continue;
                        case 162:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 162);
                            i = this.footerHtml == null ? 0 : this.footerHtml.length;
                            newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.footerHtml, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.footerHtml = newArray;
                            continue;
                        case 248:
                            int length = WireFormatNano.getRepeatedFieldArrayLength(input, 248);
                            int[] validValues = new int[length];
                            i = 0;
                            int validCount = 0;
                            while (i < length) {
                                int validCount2;
                                if (i != 0) {
                                    input.readTag();
                                }
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
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                    case 100:
                                        validCount2 = validCount + 1;
                                        validValues[validCount] = value;
                                        break;
                                    default:
                                        validCount2 = validCount;
                                        break;
                                }
                                i++;
                                validCount = validCount2;
                            }
                            if (validCount != 0) {
                                i = this.eligibleInstrumentFamily == null ? 0 : this.eligibleInstrumentFamily.length;
                                if (i != 0 || validCount != validValues.length) {
                                    newArray2 = new int[(i + validCount)];
                                    if (i != 0) {
                                        System.arraycopy(this.eligibleInstrumentFamily, 0, newArray2, 0, i);
                                    }
                                    System.arraycopy(validValues, 0, newArray2, i, validCount);
                                    this.eligibleInstrumentFamily = newArray2;
                                    break;
                                }
                                this.eligibleInstrumentFamily = validValues;
                                break;
                            }
                            continue;
                        case 250:
                            int limit = input.pushLimit(input.readRawVarint32());
                            arrayLength = 0;
                            int startPos = input.getPosition();
                            while (input.getBytesUntilLimit() > 0) {
                                switch (input.readInt32()) {
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
                                    case 100:
                                        arrayLength++;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            if (arrayLength != 0) {
                                input.rewindToPosition(startPos);
                                if (this.eligibleInstrumentFamily == null) {
                                    i = 0;
                                } else {
                                    i = this.eligibleInstrumentFamily.length;
                                }
                                newArray2 = new int[(i + arrayLength)];
                                if (i != 0) {
                                    System.arraycopy(this.eligibleInstrumentFamily, 0, newArray2, 0, i);
                                }
                                while (input.getBytesUntilLimit() > 0) {
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
                                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                        case 100:
                                            int i2 = i + 1;
                                            newArray2[i] = value;
                                            i = i2;
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                this.eligibleInstrumentFamily = newArray2;
                            }
                            input.popLimit(limit);
                            continue;
                        case 290:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 290);
                            i = this.footnoteHtml == null ? 0 : this.footnoteHtml.length;
                            newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.footnoteHtml, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.footnoteHtml = newArray;
                            continue;
                        case 354:
                            arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 354);
                            if (this.eligibleInstrument == null) {
                                i = 0;
                            } else {
                                i = this.eligibleInstrument.length;
                            }
                            Instrument[] newArray5 = new Instrument[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.eligibleInstrument, 0, newArray5, 0, i);
                            }
                            while (i < newArray5.length - 1) {
                                newArray5[i] = new Instrument();
                                input.readMessage(newArray5[i]);
                                input.readTag();
                                i++;
                            }
                            newArray5[i] = new Instrument();
                            input.readMessage(newArray5[i]);
                            this.eligibleInstrument = newArray5;
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

        public BuyResponse() {
            clear();
        }

        public BuyResponse clear() {
            this.purchaseResponse = null;
            this.purchaseStatusResponse = null;
            this.checkoutInfo = null;
            this.continueViaUrl = "";
            this.hasContinueViaUrl = false;
            this.checkoutTokenRequired = false;
            this.hasCheckoutTokenRequired = false;
            this.checkoutServiceId = "";
            this.hasCheckoutServiceId = false;
            this.baseCheckoutUrl = "";
            this.hasBaseCheckoutUrl = false;
            this.purchaseStatusUrl = "";
            this.hasPurchaseStatusUrl = false;
            this.tosCheckboxHtml = WireFormatNano.EMPTY_STRING_ARRAY;
            this.permissionError = 0;
            this.hasPermissionError = false;
            this.purchaseCookie = "";
            this.hasPurchaseCookie = false;
            this.challenge = null;
            this.addInstrumentPromptHtml = "";
            this.hasAddInstrumentPromptHtml = false;
            this.confirmButtonText = "";
            this.hasConfirmButtonText = false;
            this.permissionErrorTitleText = "";
            this.hasPermissionErrorTitleText = false;
            this.permissionErrorMessageText = "";
            this.hasPermissionErrorMessageText = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.encodedDeliveryToken = "";
            this.hasEncodedDeliveryToken = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.purchaseResponse != null) {
                output.writeMessage(1, this.purchaseResponse);
            }
            if (this.checkoutInfo != null) {
                output.writeGroup(2, this.checkoutInfo);
            }
            if (this.hasContinueViaUrl || !this.continueViaUrl.equals("")) {
                output.writeString(8, this.continueViaUrl);
            }
            if (this.hasPurchaseStatusUrl || !this.purchaseStatusUrl.equals("")) {
                output.writeString(9, this.purchaseStatusUrl);
            }
            if (this.hasCheckoutServiceId || !this.checkoutServiceId.equals("")) {
                output.writeString(12, this.checkoutServiceId);
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                output.writeBool(13, this.checkoutTokenRequired);
            }
            if (this.hasBaseCheckoutUrl || !this.baseCheckoutUrl.equals("")) {
                output.writeString(14, this.baseCheckoutUrl);
            }
            if (this.tosCheckboxHtml != null && this.tosCheckboxHtml.length > 0) {
                for (String element : this.tosCheckboxHtml) {
                    if (element != null) {
                        output.writeString(37, element);
                    }
                }
            }
            if (this.permissionError != 0 || this.hasPermissionError) {
                output.writeInt32(38, this.permissionError);
            }
            if (this.purchaseStatusResponse != null) {
                output.writeMessage(39, this.purchaseStatusResponse);
            }
            if (this.hasPurchaseCookie || !this.purchaseCookie.equals("")) {
                output.writeString(46, this.purchaseCookie);
            }
            if (this.challenge != null) {
                output.writeMessage(49, this.challenge);
            }
            if (this.hasAddInstrumentPromptHtml || !this.addInstrumentPromptHtml.equals("")) {
                output.writeString(50, this.addInstrumentPromptHtml);
            }
            if (this.hasConfirmButtonText || !this.confirmButtonText.equals("")) {
                output.writeString(51, this.confirmButtonText);
            }
            if (this.hasPermissionErrorTitleText || !this.permissionErrorTitleText.equals("")) {
                output.writeString(52, this.permissionErrorTitleText);
            }
            if (this.hasPermissionErrorMessageText || !this.permissionErrorMessageText.equals("")) {
                output.writeString(53, this.permissionErrorMessageText);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(54, this.serverLogsCookie);
            }
            if (this.hasEncodedDeliveryToken || !this.encodedDeliveryToken.equals("")) {
                output.writeString(55, this.encodedDeliveryToken);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.purchaseResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.purchaseResponse);
            }
            if (this.checkoutInfo != null) {
                size += CodedOutputByteBufferNano.computeGroupSize(2, this.checkoutInfo);
            }
            if (this.hasContinueViaUrl || !this.continueViaUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.continueViaUrl);
            }
            if (this.hasPurchaseStatusUrl || !this.purchaseStatusUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.purchaseStatusUrl);
            }
            if (this.hasCheckoutServiceId || !this.checkoutServiceId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.checkoutServiceId);
            }
            if (this.hasCheckoutTokenRequired || this.checkoutTokenRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(13, this.checkoutTokenRequired);
            }
            if (this.hasBaseCheckoutUrl || !this.baseCheckoutUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.baseCheckoutUrl);
            }
            if (this.tosCheckboxHtml != null && this.tosCheckboxHtml.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.tosCheckboxHtml) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 2);
            }
            if (this.permissionError != 0 || this.hasPermissionError) {
                size += CodedOutputByteBufferNano.computeInt32Size(38, this.permissionError);
            }
            if (this.purchaseStatusResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(39, this.purchaseStatusResponse);
            }
            if (this.hasPurchaseCookie || !this.purchaseCookie.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(46, this.purchaseCookie);
            }
            if (this.challenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(49, this.challenge);
            }
            if (this.hasAddInstrumentPromptHtml || !this.addInstrumentPromptHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(50, this.addInstrumentPromptHtml);
            }
            if (this.hasConfirmButtonText || !this.confirmButtonText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(51, this.confirmButtonText);
            }
            if (this.hasPermissionErrorTitleText || !this.permissionErrorTitleText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(52, this.permissionErrorTitleText);
            }
            if (this.hasPermissionErrorMessageText || !this.permissionErrorMessageText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(53, this.permissionErrorMessageText);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(54, this.serverLogsCookie);
            }
            if (this.hasEncodedDeliveryToken || !this.encodedDeliveryToken.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(55, this.encodedDeliveryToken);
            }
            return size;
        }

        public BuyResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.purchaseResponse == null) {
                            this.purchaseResponse = new PurchaseNotificationResponse();
                        }
                        input.readMessage(this.purchaseResponse);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                        if (this.checkoutInfo == null) {
                            this.checkoutInfo = new CheckoutInfo();
                        }
                        input.readGroup(this.checkoutInfo, 2);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.continueViaUrl = input.readString();
                        this.hasContinueViaUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.purchaseStatusUrl = input.readString();
                        this.hasPurchaseStatusUrl = true;
                        continue;
                    case 98:
                        this.checkoutServiceId = input.readString();
                        this.hasCheckoutServiceId = true;
                        continue;
                    case 104:
                        this.checkoutTokenRequired = input.readBool();
                        this.hasCheckoutTokenRequired = true;
                        continue;
                    case 114:
                        this.baseCheckoutUrl = input.readString();
                        this.hasBaseCheckoutUrl = true;
                        continue;
                    case 298:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 298);
                        int i = this.tosCheckboxHtml == null ? 0 : this.tosCheckboxHtml.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.tosCheckboxHtml, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.tosCheckboxHtml = newArray;
                        continue;
                    case 304:
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
                                this.permissionError = value;
                                this.hasPermissionError = true;
                                break;
                            default:
                                continue;
                        }
                    case 314:
                        if (this.purchaseStatusResponse == null) {
                            this.purchaseStatusResponse = new PurchaseStatusResponse();
                        }
                        input.readMessage(this.purchaseStatusResponse);
                        continue;
                    case 370:
                        this.purchaseCookie = input.readString();
                        this.hasPurchaseCookie = true;
                        continue;
                    case 394:
                        if (this.challenge == null) {
                            this.challenge = new Challenge();
                        }
                        input.readMessage(this.challenge);
                        continue;
                    case 402:
                        this.addInstrumentPromptHtml = input.readString();
                        this.hasAddInstrumentPromptHtml = true;
                        continue;
                    case 410:
                        this.confirmButtonText = input.readString();
                        this.hasConfirmButtonText = true;
                        continue;
                    case 418:
                        this.permissionErrorTitleText = input.readString();
                        this.hasPermissionErrorTitleText = true;
                        continue;
                    case 426:
                        this.permissionErrorMessageText = input.readString();
                        this.hasPermissionErrorMessageText = true;
                        continue;
                    case 434:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case 442:
                        this.encodedDeliveryToken = input.readString();
                        this.hasEncodedDeliveryToken = true;
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

    public static final class LineItem extends MessageNano {
        private static volatile LineItem[] _emptyArray;
        public Money amount;
        public String description;
        public boolean hasDescription;
        public boolean hasName;
        public String name;
        public Offer offer;

        public static LineItem[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LineItem[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LineItem() {
            clear();
        }

        public LineItem clear() {
            this.name = "";
            this.hasName = false;
            this.description = "";
            this.hasDescription = false;
            this.offer = null;
            this.amount = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasName || !this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (this.hasDescription || !this.description.equals("")) {
                output.writeString(2, this.description);
            }
            if (this.offer != null) {
                output.writeMessage(3, this.offer);
            }
            if (this.amount != null) {
                output.writeMessage(4, this.amount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.hasDescription || !this.description.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.description);
            }
            if (this.offer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.offer);
            }
            if (this.amount != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.amount);
            }
            return size;
        }

        public LineItem mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.description = input.readString();
                        this.hasDescription = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.offer == null) {
                            this.offer = new Offer();
                        }
                        input.readMessage(this.offer);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.amount == null) {
                            this.amount = new Money();
                        }
                        input.readMessage(this.amount);
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

    public static final class Money extends MessageNano {
        public String currencyCode;
        public String formattedAmount;
        public boolean hasCurrencyCode;
        public boolean hasFormattedAmount;
        public boolean hasMicros;
        public long micros;

        public Money() {
            clear();
        }

        public Money clear() {
            this.micros = 0;
            this.hasMicros = false;
            this.currencyCode = "";
            this.hasCurrencyCode = false;
            this.formattedAmount = "";
            this.hasFormattedAmount = false;
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
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.formattedAmount);
            }
            return size;
        }

        public Money mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
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

    public static final class PurchaseNotificationResponse extends MessageNano {
        public DebugInfo debugInfo;
        public boolean hasLocalizedErrorMessage;
        public boolean hasPurchaseId;
        public boolean hasStatus;
        public String localizedErrorMessage;
        public String purchaseId;
        public int status;

        public PurchaseNotificationResponse() {
            clear();
        }

        public PurchaseNotificationResponse clear() {
            this.status = 0;
            this.hasStatus = false;
            this.purchaseId = "";
            this.hasPurchaseId = false;
            this.localizedErrorMessage = "";
            this.hasLocalizedErrorMessage = false;
            this.debugInfo = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 0 || this.hasStatus) {
                output.writeInt32(1, this.status);
            }
            if (this.debugInfo != null) {
                output.writeMessage(2, this.debugInfo);
            }
            if (this.hasLocalizedErrorMessage || !this.localizedErrorMessage.equals("")) {
                output.writeString(3, this.localizedErrorMessage);
            }
            if (this.hasPurchaseId || !this.purchaseId.equals("")) {
                output.writeString(4, this.purchaseId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 0 || this.hasStatus) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            if (this.debugInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.debugInfo);
            }
            if (this.hasLocalizedErrorMessage || !this.localizedErrorMessage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.localizedErrorMessage);
            }
            if (this.hasPurchaseId || !this.purchaseId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.purchaseId);
            }
            return size;
        }

        public PurchaseNotificationResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.status = value;
                                this.hasStatus = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.debugInfo == null) {
                            this.debugInfo = new DebugInfo();
                        }
                        input.readMessage(this.debugInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.localizedErrorMessage = input.readString();
                        this.hasLocalizedErrorMessage = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.purchaseId = input.readString();
                        this.hasPurchaseId = true;
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

    public static final class PurchaseStatusResponse extends MessageNano {
        public AndroidAppDeliveryData appDeliveryData;
        public String briefMessage;
        public boolean hasBriefMessage;
        public boolean hasInfoUrl;
        public boolean hasStatus;
        public boolean hasStatusMsg;
        public boolean hasStatusTitle;
        public String infoUrl;
        public LibraryUpdate libraryUpdate;
        public Instrument rejectedInstrument;
        public int status;
        public String statusMsg;
        public String statusTitle;

        public PurchaseStatusResponse() {
            clear();
        }

        public PurchaseStatusResponse clear() {
            this.status = 1;
            this.hasStatus = false;
            this.statusMsg = "";
            this.hasStatusMsg = false;
            this.statusTitle = "";
            this.hasStatusTitle = false;
            this.briefMessage = "";
            this.hasBriefMessage = false;
            this.infoUrl = "";
            this.hasInfoUrl = false;
            this.libraryUpdate = null;
            this.rejectedInstrument = null;
            this.appDeliveryData = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.status != 1 || this.hasStatus) {
                output.writeInt32(1, this.status);
            }
            if (this.hasStatusMsg || !this.statusMsg.equals("")) {
                output.writeString(2, this.statusMsg);
            }
            if (this.hasStatusTitle || !this.statusTitle.equals("")) {
                output.writeString(3, this.statusTitle);
            }
            if (this.hasBriefMessage || !this.briefMessage.equals("")) {
                output.writeString(4, this.briefMessage);
            }
            if (this.hasInfoUrl || !this.infoUrl.equals("")) {
                output.writeString(5, this.infoUrl);
            }
            if (this.libraryUpdate != null) {
                output.writeMessage(6, this.libraryUpdate);
            }
            if (this.rejectedInstrument != null) {
                output.writeMessage(7, this.rejectedInstrument);
            }
            if (this.appDeliveryData != null) {
                output.writeMessage(8, this.appDeliveryData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.status != 1 || this.hasStatus) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.status);
            }
            if (this.hasStatusMsg || !this.statusMsg.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.statusMsg);
            }
            if (this.hasStatusTitle || !this.statusTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.statusTitle);
            }
            if (this.hasBriefMessage || !this.briefMessage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.briefMessage);
            }
            if (this.hasInfoUrl || !this.infoUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.infoUrl);
            }
            if (this.libraryUpdate != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.libraryUpdate);
            }
            if (this.rejectedInstrument != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.rejectedInstrument);
            }
            if (this.appDeliveryData != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(8, this.appDeliveryData);
            }
            return size;
        }

        public PurchaseStatusResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.status = value;
                                this.hasStatus = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.statusMsg = input.readString();
                        this.hasStatusMsg = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.statusTitle = input.readString();
                        this.hasStatusTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.briefMessage = input.readString();
                        this.hasBriefMessage = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.infoUrl = input.readString();
                        this.hasInfoUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.libraryUpdate == null) {
                            this.libraryUpdate = new LibraryUpdate();
                        }
                        input.readMessage(this.libraryUpdate);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.rejectedInstrument == null) {
                            this.rejectedInstrument = new Instrument();
                        }
                        input.readMessage(this.rejectedInstrument);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.appDeliveryData == null) {
                            this.appDeliveryData = new AndroidAppDeliveryData();
                        }
                        input.readMessage(this.appDeliveryData);
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
