package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Acquisition.SuccessInfo;
import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.protos.ChallengeProto.Challenge;
import com.google.android.finsky.protos.CommonDevice.Instrument;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface Purchase {

    public static final class ApplicableVoucher extends MessageNano {
        private static volatile ApplicableVoucher[] _emptyArray;
        public boolean applied;
        public DocV2 doc;
        public String formattedDiscountAmount;
        public boolean hasApplied;
        public boolean hasFormattedDiscountAmount;

        public static ApplicableVoucher[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ApplicableVoucher[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ApplicableVoucher() {
            clear();
        }

        public ApplicableVoucher clear() {
            this.doc = null;
            this.formattedDiscountAmount = "";
            this.hasFormattedDiscountAmount = false;
            this.applied = false;
            this.hasApplied = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.doc != null) {
                output.writeMessage(1, this.doc);
            }
            if (this.hasFormattedDiscountAmount || !this.formattedDiscountAmount.equals("")) {
                output.writeString(2, this.formattedDiscountAmount);
            }
            if (this.hasApplied || this.applied) {
                output.writeBool(3, this.applied);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.doc != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.doc);
            }
            if (this.hasFormattedDiscountAmount || !this.formattedDiscountAmount.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.formattedDiscountAmount);
            }
            if (this.hasApplied || this.applied) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.applied);
            }
            return size;
        }

        public ApplicableVoucher mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.doc == null) {
                            this.doc = new DocV2();
                        }
                        input.readMessage(this.doc);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.formattedDiscountAmount = input.readString();
                        this.hasFormattedDiscountAmount = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.applied = input.readBool();
                        this.hasApplied = true;
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

    public static final class ChangeSubscription extends MessageNano {
        public String descriptionHtml;
        public boolean hasDescriptionHtml;
        public boolean hasTitle;
        public String title;

        public ChangeSubscription() {
            clear();
        }

        public ChangeSubscription clear() {
            this.title = "";
            this.hasTitle = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(2, this.descriptionHtml);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.descriptionHtml);
            }
            return size;
        }

        public ChangeSubscription mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static final class ClientCart extends MessageNano {
        public String addInstrumentPromptHtml;
        public DocV2[] applicableVoucher;
        public ApplicableVoucher[] applicableVouchers;
        public int[] appliedVoucherIndex;
        public String buttonText;
        public Challenge completePurchaseChallenge;
        public String[] detailHtml;
        public String[] extendedDetailHtml;
        public String footerHtml;
        public String formattedOriginalPrice;
        public String formattedPrice;
        public boolean hasAddInstrumentPromptHtml;
        public boolean hasButtonText;
        public boolean hasFooterHtml;
        public boolean hasFormattedOriginalPrice;
        public boolean hasFormattedPrice;
        public boolean hasPriceByline;
        public boolean hasPurchaseContextToken;
        public boolean hasTitle;
        public Instrument instrument;
        public String priceByline;
        public String purchaseContextToken;
        public String title;

        public ClientCart() {
            clear();
        }

        public ClientCart clear() {
            this.title = "";
            this.hasTitle = false;
            this.formattedPrice = "";
            this.hasFormattedPrice = false;
            this.formattedOriginalPrice = "";
            this.hasFormattedOriginalPrice = false;
            this.priceByline = "";
            this.hasPriceByline = false;
            this.purchaseContextToken = "";
            this.hasPurchaseContextToken = false;
            this.instrument = null;
            this.detailHtml = WireFormatNano.EMPTY_STRING_ARRAY;
            this.extendedDetailHtml = WireFormatNano.EMPTY_STRING_ARRAY;
            this.footerHtml = "";
            this.hasFooterHtml = false;
            this.addInstrumentPromptHtml = "";
            this.hasAddInstrumentPromptHtml = false;
            this.buttonText = "";
            this.hasButtonText = false;
            this.completePurchaseChallenge = null;
            this.applicableVoucher = DocV2.emptyArray();
            this.appliedVoucherIndex = WireFormatNano.EMPTY_INT_ARRAY;
            this.applicableVouchers = ApplicableVoucher.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.hasFormattedPrice || !this.formattedPrice.equals("")) {
                output.writeString(2, this.formattedPrice);
            }
            if (this.hasPurchaseContextToken || !this.purchaseContextToken.equals("")) {
                output.writeString(3, this.purchaseContextToken);
            }
            if (this.instrument != null) {
                output.writeMessage(4, this.instrument);
            }
            if (this.extendedDetailHtml != null && this.extendedDetailHtml.length > 0) {
                for (String element : this.extendedDetailHtml) {
                    if (element != null) {
                        output.writeString(5, element);
                    }
                }
            }
            if (this.hasFooterHtml || !this.footerHtml.equals("")) {
                output.writeString(6, this.footerHtml);
            }
            if (this.hasAddInstrumentPromptHtml || !this.addInstrumentPromptHtml.equals("")) {
                output.writeString(7, this.addInstrumentPromptHtml);
            }
            if (this.hasButtonText || !this.buttonText.equals("")) {
                output.writeString(8, this.buttonText);
            }
            if (this.completePurchaseChallenge != null) {
                output.writeMessage(9, this.completePurchaseChallenge);
            }
            if (this.hasPriceByline || !this.priceByline.equals("")) {
                output.writeString(10, this.priceByline);
            }
            if (this.detailHtml != null && this.detailHtml.length > 0) {
                for (String element2 : this.detailHtml) {
                    if (element2 != null) {
                        output.writeString(11, element2);
                    }
                }
            }
            if (this.applicableVoucher != null && this.applicableVoucher.length > 0) {
                for (DocV2 element3 : this.applicableVoucher) {
                    if (element3 != null) {
                        output.writeMessage(12, element3);
                    }
                }
            }
            if (this.appliedVoucherIndex != null && this.appliedVoucherIndex.length > 0) {
                for (int writeInt32 : this.appliedVoucherIndex) {
                    output.writeInt32(13, writeInt32);
                }
            }
            if (this.applicableVouchers != null && this.applicableVouchers.length > 0) {
                for (ApplicableVoucher element4 : this.applicableVouchers) {
                    if (element4 != null) {
                        output.writeMessage(14, element4);
                    }
                }
            }
            if (this.hasFormattedOriginalPrice || !this.formattedOriginalPrice.equals("")) {
                output.writeString(15, this.formattedOriginalPrice);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.hasFormattedPrice || !this.formattedPrice.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.formattedPrice);
            }
            if (this.hasPurchaseContextToken || !this.purchaseContextToken.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.purchaseContextToken);
            }
            if (this.instrument != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.instrument);
            }
            if (this.extendedDetailHtml != null && this.extendedDetailHtml.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.extendedDetailHtml) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasFooterHtml || !this.footerHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.footerHtml);
            }
            if (this.hasAddInstrumentPromptHtml || !this.addInstrumentPromptHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.addInstrumentPromptHtml);
            }
            if (this.hasButtonText || !this.buttonText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.buttonText);
            }
            if (this.completePurchaseChallenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.completePurchaseChallenge);
            }
            if (this.hasPriceByline || !this.priceByline.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.priceByline);
            }
            if (this.detailHtml != null && this.detailHtml.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.detailHtml) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.applicableVoucher != null && this.applicableVoucher.length > 0) {
                for (DocV2 element3 : this.applicableVoucher) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(12, element3);
                    }
                }
            }
            if (this.appliedVoucherIndex != null && this.appliedVoucherIndex.length > 0) {
                dataSize = 0;
                for (int element4 : this.appliedVoucherIndex) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element4);
                }
                size = (size + dataSize) + (this.appliedVoucherIndex.length * 1);
            }
            if (this.applicableVouchers != null && this.applicableVouchers.length > 0) {
                for (ApplicableVoucher element5 : this.applicableVouchers) {
                    if (element5 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(14, element5);
                    }
                }
            }
            if (this.hasFormattedOriginalPrice || !this.formattedOriginalPrice.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(15, this.formattedOriginalPrice);
            }
            return size;
        }

        public ClientCart mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                int[] newArray2;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.formattedPrice = input.readString();
                        this.hasFormattedPrice = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.purchaseContextToken = input.readString();
                        this.hasPurchaseContextToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.instrument == null) {
                            this.instrument = new Instrument();
                        }
                        input.readMessage(this.instrument);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        i = this.extendedDetailHtml == null ? 0 : this.extendedDetailHtml.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.extendedDetailHtml, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.extendedDetailHtml = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.footerHtml = input.readString();
                        this.hasFooterHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.addInstrumentPromptHtml = input.readString();
                        this.hasAddInstrumentPromptHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.buttonText = input.readString();
                        this.hasButtonText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.completePurchaseChallenge == null) {
                            this.completePurchaseChallenge = new Challenge();
                        }
                        input.readMessage(this.completePurchaseChallenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.priceByline = input.readString();
                        this.hasPriceByline = true;
                        continue;
                    case 90:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                        i = this.detailHtml == null ? 0 : this.detailHtml.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.detailHtml, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.detailHtml = newArray;
                        continue;
                    case 98:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 98);
                        if (this.applicableVoucher == null) {
                            i = 0;
                        } else {
                            i = this.applicableVoucher.length;
                        }
                        DocV2[] newArray3 = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.applicableVoucher, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new DocV2();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new DocV2();
                        input.readMessage(newArray3[i]);
                        this.applicableVoucher = newArray3;
                        continue;
                    case 104:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 104);
                        i = this.appliedVoucherIndex == null ? 0 : this.appliedVoucherIndex.length;
                        newArray2 = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appliedVoucherIndex, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readInt32();
                        this.appliedVoucherIndex = newArray2;
                        continue;
                    case 106:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.appliedVoucherIndex == null ? 0 : this.appliedVoucherIndex.length;
                        newArray2 = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appliedVoucherIndex, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length) {
                            newArray2[i] = input.readInt32();
                            i++;
                        }
                        this.appliedVoucherIndex = newArray2;
                        input.popLimit(limit);
                        continue;
                    case 114:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 114);
                        if (this.applicableVouchers == null) {
                            i = 0;
                        } else {
                            i = this.applicableVouchers.length;
                        }
                        ApplicableVoucher[] newArray4 = new ApplicableVoucher[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.applicableVouchers, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new ApplicableVoucher();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = new ApplicableVoucher();
                        input.readMessage(newArray4[i]);
                        this.applicableVouchers = newArray4;
                        continue;
                    case 122:
                        this.formattedOriginalPrice = input.readString();
                        this.hasFormattedOriginalPrice = true;
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

    public static final class CommitPurchaseResponse extends MessageNano {
        public AndroidAppDeliveryData appDeliveryData;
        public Challenge challenge;
        public String encodedDeliveryToken;
        public boolean hasEncodedDeliveryToken;
        public boolean hasServerLogsCookie;
        public LibraryUpdate[] libraryUpdate;
        public PurchaseStatus purchaseStatus;
        public byte[] serverLogsCookie;
        public SuccessInfo successInfo;

        public CommitPurchaseResponse() {
            clear();
        }

        public CommitPurchaseResponse clear() {
            this.purchaseStatus = null;
            this.challenge = null;
            this.libraryUpdate = LibraryUpdate.emptyArray();
            this.appDeliveryData = null;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.encodedDeliveryToken = "";
            this.hasEncodedDeliveryToken = false;
            this.successInfo = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.purchaseStatus != null) {
                output.writeMessage(1, this.purchaseStatus);
            }
            if (this.challenge != null) {
                output.writeMessage(2, this.challenge);
            }
            if (this.libraryUpdate != null && this.libraryUpdate.length > 0) {
                for (LibraryUpdate element : this.libraryUpdate) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.appDeliveryData != null) {
                output.writeMessage(4, this.appDeliveryData);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(5, this.serverLogsCookie);
            }
            if (this.hasEncodedDeliveryToken || !this.encodedDeliveryToken.equals("")) {
                output.writeString(6, this.encodedDeliveryToken);
            }
            if (this.successInfo != null) {
                output.writeMessage(7, this.successInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.purchaseStatus != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.purchaseStatus);
            }
            if (this.challenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.challenge);
            }
            if (this.libraryUpdate != null && this.libraryUpdate.length > 0) {
                for (LibraryUpdate element : this.libraryUpdate) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.appDeliveryData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.appDeliveryData);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(5, this.serverLogsCookie);
            }
            if (this.hasEncodedDeliveryToken || !this.encodedDeliveryToken.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.encodedDeliveryToken);
            }
            if (this.successInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.successInfo);
            }
            return size;
        }

        public CommitPurchaseResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.purchaseStatus == null) {
                            this.purchaseStatus = new PurchaseStatus();
                        }
                        input.readMessage(this.purchaseStatus);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.challenge == null) {
                            this.challenge = new Challenge();
                        }
                        input.readMessage(this.challenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.libraryUpdate == null) {
                            i = 0;
                        } else {
                            i = this.libraryUpdate.length;
                        }
                        LibraryUpdate[] newArray = new LibraryUpdate[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.libraryUpdate, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LibraryUpdate();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new LibraryUpdate();
                        input.readMessage(newArray[i]);
                        this.libraryUpdate = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.appDeliveryData == null) {
                            this.appDeliveryData = new AndroidAppDeliveryData();
                        }
                        input.readMessage(this.appDeliveryData);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.encodedDeliveryToken = input.readString();
                        this.hasEncodedDeliveryToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.successInfo == null) {
                            this.successInfo = new SuccessInfo();
                        }
                        input.readMessage(this.successInfo);
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

    public static final class PreparePurchaseResponse extends MessageNano {
        public DocV2[] applicableVoucher;
        public int[] appliedVoucherIndex;
        public ClientCart cart;
        public Challenge challenge;
        public ChangeSubscription changeSubscription;
        public boolean hasServerLogsCookie;
        public LibraryUpdate[] libraryUpdate;
        public PurchaseStatus purchaseStatus;
        public byte[] serverLogsCookie;

        public PreparePurchaseResponse() {
            clear();
        }

        public PreparePurchaseResponse clear() {
            this.purchaseStatus = null;
            this.challenge = null;
            this.cart = null;
            this.changeSubscription = null;
            this.libraryUpdate = LibraryUpdate.emptyArray();
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.applicableVoucher = DocV2.emptyArray();
            this.appliedVoucherIndex = WireFormatNano.EMPTY_INT_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.purchaseStatus != null) {
                output.writeMessage(1, this.purchaseStatus);
            }
            if (this.challenge != null) {
                output.writeMessage(2, this.challenge);
            }
            if (this.cart != null) {
                output.writeMessage(3, this.cart);
            }
            if (this.libraryUpdate != null && this.libraryUpdate.length > 0) {
                for (LibraryUpdate element : this.libraryUpdate) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(5, this.serverLogsCookie);
            }
            if (this.applicableVoucher != null && this.applicableVoucher.length > 0) {
                for (DocV2 element2 : this.applicableVoucher) {
                    if (element2 != null) {
                        output.writeMessage(6, element2);
                    }
                }
            }
            if (this.appliedVoucherIndex != null && this.appliedVoucherIndex.length > 0) {
                for (int writeInt32 : this.appliedVoucherIndex) {
                    output.writeInt32(7, writeInt32);
                }
            }
            if (this.changeSubscription != null) {
                output.writeMessage(8, this.changeSubscription);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.purchaseStatus != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.purchaseStatus);
            }
            if (this.challenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.challenge);
            }
            if (this.cart != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.cart);
            }
            if (this.libraryUpdate != null && this.libraryUpdate.length > 0) {
                for (LibraryUpdate element : this.libraryUpdate) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(5, this.serverLogsCookie);
            }
            if (this.applicableVoucher != null && this.applicableVoucher.length > 0) {
                for (DocV2 element2 : this.applicableVoucher) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element2);
                    }
                }
            }
            if (this.appliedVoucherIndex != null && this.appliedVoucherIndex.length > 0) {
                int dataSize = 0;
                for (int element3 : this.appliedVoucherIndex) {
                    dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
                }
                size = (size + dataSize) + (this.appliedVoucherIndex.length * 1);
            }
            if (this.changeSubscription != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(8, this.changeSubscription);
            }
            return size;
        }

        public PreparePurchaseResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.purchaseStatus == null) {
                            this.purchaseStatus = new PurchaseStatus();
                        }
                        input.readMessage(this.purchaseStatus);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.challenge == null) {
                            this.challenge = new Challenge();
                        }
                        input.readMessage(this.challenge);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.cart == null) {
                            this.cart = new ClientCart();
                        }
                        input.readMessage(this.cart);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.libraryUpdate == null) {
                            i = 0;
                        } else {
                            i = this.libraryUpdate.length;
                        }
                        LibraryUpdate[] newArray2 = new LibraryUpdate[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.libraryUpdate, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new LibraryUpdate();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new LibraryUpdate();
                        input.readMessage(newArray2[i]);
                        this.libraryUpdate = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.applicableVoucher == null) {
                            i = 0;
                        } else {
                            i = this.applicableVoucher.length;
                        }
                        DocV2[] newArray3 = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.applicableVoucher, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new DocV2();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new DocV2();
                        input.readMessage(newArray3[i]);
                        this.applicableVoucher = newArray3;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 56);
                        i = this.appliedVoucherIndex == null ? 0 : this.appliedVoucherIndex.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appliedVoucherIndex, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.appliedVoucherIndex = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.appliedVoucherIndex == null ? 0 : this.appliedVoucherIndex.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appliedVoucherIndex, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.appliedVoucherIndex = newArray;
                        input.popLimit(limit);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.changeSubscription == null) {
                            this.changeSubscription = new ChangeSubscription();
                        }
                        input.readMessage(this.changeSubscription);
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

    public static final class PurchaseStatus extends MessageNano {
        public String errorMessageHtml;
        public boolean hasErrorMessageHtml;
        public boolean hasPermissionError;
        public boolean hasStatusCode;
        public int permissionError;
        public int statusCode;

        public PurchaseStatus() {
            clear();
        }

        public PurchaseStatus clear() {
            this.statusCode = 0;
            this.hasStatusCode = false;
            this.errorMessageHtml = "";
            this.hasErrorMessageHtml = false;
            this.permissionError = 0;
            this.hasPermissionError = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.statusCode != 0 || this.hasStatusCode) {
                output.writeInt32(1, this.statusCode);
            }
            if (this.hasErrorMessageHtml || !this.errorMessageHtml.equals("")) {
                output.writeString(2, this.errorMessageHtml);
            }
            if (this.permissionError != 0 || this.hasPermissionError) {
                output.writeInt32(3, this.permissionError);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.statusCode != 0 || this.hasStatusCode) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.statusCode);
            }
            if (this.hasErrorMessageHtml || !this.errorMessageHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.errorMessageHtml);
            }
            if (this.permissionError != 0 || this.hasPermissionError) {
                return size + CodedOutputByteBufferNano.computeInt32Size(3, this.permissionError);
            }
            return size;
        }

        public PurchaseStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.statusCode = value;
                                this.hasStatusCode = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.errorMessageHtml = input.readString();
                        this.hasErrorMessageHtml = true;
                        continue;
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
