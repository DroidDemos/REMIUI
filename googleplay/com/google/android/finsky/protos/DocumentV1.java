package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.DocDetails.DocumentDetails;
import com.google.android.finsky.protos.DocumentV2.PlusOneData;
import com.google.android.finsky.protos.FilterRules.Availability;
import com.google.android.finsky.protos.Rating.AggregateRating;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface DocumentV1 {

    public static final class DocV1 extends MessageNano {
        private static volatile DocV1[] _emptyArray;
        public String creator;
        public String descriptionHtml;
        public DocumentDetails details;
        public String detailsUrl;
        public String docid;
        public OBSOLETE_FinskyDoc finskyDoc;
        public boolean hasCreator;
        public boolean hasDescriptionHtml;
        public boolean hasDetailsUrl;
        public boolean hasDocid;
        public boolean hasMoreByBrowseUrl;
        public boolean hasMoreByHeader;
        public boolean hasMoreByListUrl;
        public boolean hasRelatedBrowseUrl;
        public boolean hasRelatedHeader;
        public boolean hasRelatedListUrl;
        public boolean hasReviewsUrl;
        public boolean hasShareUrl;
        public boolean hasTitle;
        public boolean hasWarningMessage;
        public String moreByBrowseUrl;
        public String moreByHeader;
        public String moreByListUrl;
        public PlusOneData plusOneData;
        public String relatedBrowseUrl;
        public String relatedHeader;
        public String relatedListUrl;
        public String reviewsUrl;
        public String shareUrl;
        public String title;
        public String warningMessage;

        public static DocV1[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DocV1[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DocV1() {
            clear();
        }

        public DocV1 clear() {
            this.finskyDoc = null;
            this.docid = "";
            this.hasDocid = false;
            this.detailsUrl = "";
            this.hasDetailsUrl = false;
            this.reviewsUrl = "";
            this.hasReviewsUrl = false;
            this.relatedListUrl = "";
            this.hasRelatedListUrl = false;
            this.relatedBrowseUrl = "";
            this.hasRelatedBrowseUrl = false;
            this.relatedHeader = "";
            this.hasRelatedHeader = false;
            this.moreByListUrl = "";
            this.hasMoreByListUrl = false;
            this.moreByBrowseUrl = "";
            this.hasMoreByBrowseUrl = false;
            this.moreByHeader = "";
            this.hasMoreByHeader = false;
            this.shareUrl = "";
            this.hasShareUrl = false;
            this.title = "";
            this.hasTitle = false;
            this.creator = "";
            this.hasCreator = false;
            this.details = null;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.plusOneData = null;
            this.warningMessage = "";
            this.hasWarningMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.finskyDoc != null) {
                output.writeMessage(1, this.finskyDoc);
            }
            if (this.hasDocid || !this.docid.equals("")) {
                output.writeString(2, this.docid);
            }
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                output.writeString(3, this.detailsUrl);
            }
            if (this.hasReviewsUrl || !this.reviewsUrl.equals("")) {
                output.writeString(4, this.reviewsUrl);
            }
            if (this.hasRelatedListUrl || !this.relatedListUrl.equals("")) {
                output.writeString(5, this.relatedListUrl);
            }
            if (this.hasMoreByListUrl || !this.moreByListUrl.equals("")) {
                output.writeString(6, this.moreByListUrl);
            }
            if (this.hasShareUrl || !this.shareUrl.equals("")) {
                output.writeString(7, this.shareUrl);
            }
            if (this.hasCreator || !this.creator.equals("")) {
                output.writeString(8, this.creator);
            }
            if (this.details != null) {
                output.writeMessage(9, this.details);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(10, this.descriptionHtml);
            }
            if (this.hasRelatedBrowseUrl || !this.relatedBrowseUrl.equals("")) {
                output.writeString(11, this.relatedBrowseUrl);
            }
            if (this.hasMoreByBrowseUrl || !this.moreByBrowseUrl.equals("")) {
                output.writeString(12, this.moreByBrowseUrl);
            }
            if (this.hasRelatedHeader || !this.relatedHeader.equals("")) {
                output.writeString(13, this.relatedHeader);
            }
            if (this.hasMoreByHeader || !this.moreByHeader.equals("")) {
                output.writeString(14, this.moreByHeader);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(15, this.title);
            }
            if (this.plusOneData != null) {
                output.writeMessage(16, this.plusOneData);
            }
            if (this.hasWarningMessage || !this.warningMessage.equals("")) {
                output.writeString(17, this.warningMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.finskyDoc != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.finskyDoc);
            }
            if (this.hasDocid || !this.docid.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.docid);
            }
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.detailsUrl);
            }
            if (this.hasReviewsUrl || !this.reviewsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.reviewsUrl);
            }
            if (this.hasRelatedListUrl || !this.relatedListUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.relatedListUrl);
            }
            if (this.hasMoreByListUrl || !this.moreByListUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.moreByListUrl);
            }
            if (this.hasShareUrl || !this.shareUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.shareUrl);
            }
            if (this.hasCreator || !this.creator.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.creator);
            }
            if (this.details != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.details);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.descriptionHtml);
            }
            if (this.hasRelatedBrowseUrl || !this.relatedBrowseUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.relatedBrowseUrl);
            }
            if (this.hasMoreByBrowseUrl || !this.moreByBrowseUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.moreByBrowseUrl);
            }
            if (this.hasRelatedHeader || !this.relatedHeader.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.relatedHeader);
            }
            if (this.hasMoreByHeader || !this.moreByHeader.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.moreByHeader);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.title);
            }
            if (this.plusOneData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(16, this.plusOneData);
            }
            if (this.hasWarningMessage || !this.warningMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(17, this.warningMessage);
            }
            return size;
        }

        public DocV1 mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.finskyDoc == null) {
                            this.finskyDoc = new OBSOLETE_FinskyDoc();
                        }
                        input.readMessage(this.finskyDoc);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.docid = input.readString();
                        this.hasDocid = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.detailsUrl = input.readString();
                        this.hasDetailsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.reviewsUrl = input.readString();
                        this.hasReviewsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.relatedListUrl = input.readString();
                        this.hasRelatedListUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.moreByListUrl = input.readString();
                        this.hasMoreByListUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.shareUrl = input.readString();
                        this.hasShareUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.creator = input.readString();
                        this.hasCreator = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.details == null) {
                            this.details = new DocumentDetails();
                        }
                        input.readMessage(this.details);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case 90:
                        this.relatedBrowseUrl = input.readString();
                        this.hasRelatedBrowseUrl = true;
                        continue;
                    case 98:
                        this.moreByBrowseUrl = input.readString();
                        this.hasMoreByBrowseUrl = true;
                        continue;
                    case 106:
                        this.relatedHeader = input.readString();
                        this.hasRelatedHeader = true;
                        continue;
                    case 114:
                        this.moreByHeader = input.readString();
                        this.hasMoreByHeader = true;
                        continue;
                    case 122:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case 130:
                        if (this.plusOneData == null) {
                            this.plusOneData = new PlusOneData();
                        }
                        input.readMessage(this.plusOneData);
                        continue;
                    case 138:
                        this.warningMessage = input.readString();
                        this.hasWarningMessage = true;
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

    public static final class OBSOLETE_FinskyDoc extends MessageNano {
        private static volatile OBSOLETE_FinskyDoc[] _emptyArray;
        public AggregateRating aggregateRating;
        public Availability availability;
        public OBSOLETE_FinskyDoc[] child;
        public Docid docid;
        public Docid fetchDocid;
        public boolean hasTitle;
        public boolean hasUrl;
        public Image[] image;
        public Offer[] offer;
        public Offer priceDeprecated;
        public Docid sampleDocid;
        public String title;
        public OBSOLETE_FinskyTranslatedText[] translatedSnippet;
        public String url;

        public static OBSOLETE_FinskyDoc[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OBSOLETE_FinskyDoc[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OBSOLETE_FinskyDoc() {
            clear();
        }

        public OBSOLETE_FinskyDoc clear() {
            this.docid = null;
            this.fetchDocid = null;
            this.sampleDocid = null;
            this.title = "";
            this.hasTitle = false;
            this.url = "";
            this.hasUrl = false;
            this.translatedSnippet = OBSOLETE_FinskyTranslatedText.emptyArray();
            this.priceDeprecated = null;
            this.offer = Offer.emptyArray();
            this.availability = null;
            this.image = Image.emptyArray();
            this.child = emptyArray();
            this.aggregateRating = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docid != null) {
                output.writeMessage(1, this.docid);
            }
            if (this.fetchDocid != null) {
                output.writeMessage(2, this.fetchDocid);
            }
            if (this.sampleDocid != null) {
                output.writeMessage(3, this.sampleDocid);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(4, this.title);
            }
            if (this.hasUrl || !this.url.equals("")) {
                output.writeString(5, this.url);
            }
            if (this.priceDeprecated != null) {
                output.writeMessage(7, this.priceDeprecated);
            }
            if (this.availability != null) {
                output.writeMessage(9, this.availability);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        output.writeMessage(10, element);
                    }
                }
            }
            if (this.child != null && this.child.length > 0) {
                for (OBSOLETE_FinskyDoc element2 : this.child) {
                    if (element2 != null) {
                        output.writeMessage(11, element2);
                    }
                }
            }
            if (this.aggregateRating != null) {
                output.writeMessage(13, this.aggregateRating);
            }
            if (this.offer != null && this.offer.length > 0) {
                for (Offer element3 : this.offer) {
                    if (element3 != null) {
                        output.writeMessage(14, element3);
                    }
                }
            }
            if (this.translatedSnippet != null && this.translatedSnippet.length > 0) {
                for (OBSOLETE_FinskyTranslatedText element4 : this.translatedSnippet) {
                    if (element4 != null) {
                        output.writeMessage(15, element4);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.docid);
            }
            if (this.fetchDocid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.fetchDocid);
            }
            if (this.sampleDocid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.sampleDocid);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.title);
            }
            if (this.hasUrl || !this.url.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.url);
            }
            if (this.priceDeprecated != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.priceDeprecated);
            }
            if (this.availability != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.availability);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(10, element);
                    }
                }
            }
            if (this.child != null && this.child.length > 0) {
                for (OBSOLETE_FinskyDoc element2 : this.child) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(11, element2);
                    }
                }
            }
            if (this.aggregateRating != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(13, this.aggregateRating);
            }
            if (this.offer != null && this.offer.length > 0) {
                for (Offer element3 : this.offer) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(14, element3);
                    }
                }
            }
            if (this.translatedSnippet != null && this.translatedSnippet.length > 0) {
                for (OBSOLETE_FinskyTranslatedText element4 : this.translatedSnippet) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(15, element4);
                    }
                }
            }
            return size;
        }

        public OBSOLETE_FinskyDoc mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.docid == null) {
                            this.docid = new Docid();
                        }
                        input.readMessage(this.docid);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.fetchDocid == null) {
                            this.fetchDocid = new Docid();
                        }
                        input.readMessage(this.fetchDocid);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.sampleDocid == null) {
                            this.sampleDocid = new Docid();
                        }
                        input.readMessage(this.sampleDocid);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.url = input.readString();
                        this.hasUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.priceDeprecated == null) {
                            this.priceDeprecated = new Offer();
                        }
                        input.readMessage(this.priceDeprecated);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.availability == null) {
                            this.availability = new Availability();
                        }
                        input.readMessage(this.availability);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 82);
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
                    case 90:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                        if (this.child == null) {
                            i = 0;
                        } else {
                            i = this.child.length;
                        }
                        OBSOLETE_FinskyDoc[] newArray2 = new OBSOLETE_FinskyDoc[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.child, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new OBSOLETE_FinskyDoc();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new OBSOLETE_FinskyDoc();
                        input.readMessage(newArray2[i]);
                        this.child = newArray2;
                        continue;
                    case 106:
                        if (this.aggregateRating == null) {
                            this.aggregateRating = new AggregateRating();
                        }
                        input.readMessage(this.aggregateRating);
                        continue;
                    case 114:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 114);
                        if (this.offer == null) {
                            i = 0;
                        } else {
                            i = this.offer.length;
                        }
                        Offer[] newArray3 = new Offer[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.offer, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new Offer();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new Offer();
                        input.readMessage(newArray3[i]);
                        this.offer = newArray3;
                        continue;
                    case 122:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 122);
                        if (this.translatedSnippet == null) {
                            i = 0;
                        } else {
                            i = this.translatedSnippet.length;
                        }
                        OBSOLETE_FinskyTranslatedText[] newArray4 = new OBSOLETE_FinskyTranslatedText[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.translatedSnippet, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new OBSOLETE_FinskyTranslatedText();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = new OBSOLETE_FinskyTranslatedText();
                        input.readMessage(newArray4[i]);
                        this.translatedSnippet = newArray4;
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

    public static final class OBSOLETE_FinskyTranslatedText extends MessageNano {
        private static volatile OBSOLETE_FinskyTranslatedText[] _emptyArray;
        public boolean hasSourceLocale;
        public boolean hasTargetLocale;
        public boolean hasText;
        public String sourceLocale;
        public String targetLocale;
        public String text;

        public static OBSOLETE_FinskyTranslatedText[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OBSOLETE_FinskyTranslatedText[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OBSOLETE_FinskyTranslatedText() {
            clear();
        }

        public OBSOLETE_FinskyTranslatedText clear() {
            this.text = "";
            this.hasText = false;
            this.sourceLocale = "";
            this.hasSourceLocale = false;
            this.targetLocale = "";
            this.hasTargetLocale = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasText || !this.text.equals("")) {
                output.writeString(1, this.text);
            }
            if (this.hasSourceLocale || !this.sourceLocale.equals("")) {
                output.writeString(2, this.sourceLocale);
            }
            if (this.hasTargetLocale || !this.targetLocale.equals("")) {
                output.writeString(3, this.targetLocale);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasText || !this.text.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.text);
            }
            if (this.hasSourceLocale || !this.sourceLocale.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.sourceLocale);
            }
            if (this.hasTargetLocale || !this.targetLocale.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.targetLocale);
            }
            return size;
        }

        public OBSOLETE_FinskyTranslatedText mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.text = input.readString();
                        this.hasText = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.sourceLocale = input.readString();
                        this.hasSourceLocale = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.targetLocale = input.readString();
                        this.hasTargetLocale = true;
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
