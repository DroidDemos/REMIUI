package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.protos.Containers.ContainerMetadata;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.protos.DocAnnotations.BadgeContainer;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.DocAnnotations.PromotedDoc;
import com.google.android.finsky.protos.DocAnnotations.PurchaseHistoryDetails;
import com.google.android.finsky.protos.DocAnnotations.SectionMetadata;
import com.google.android.finsky.protos.DocAnnotations.Warning;
import com.google.android.finsky.protos.DocDetails.DocumentDetails;
import com.google.android.finsky.protos.DocDetails.ProductDetails;
import com.google.android.finsky.protos.FilterRules.Availability;
import com.google.android.finsky.protos.PlusData.OBSOLETE_PlusProfile;
import com.google.android.finsky.protos.Rating.AggregateRating;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface DocumentV2 {

    public static final class ActionBanner extends MessageNano {
        public CallToAction[] action;
        public DocV2 primaryFace;
        public DocV2[] secondaryFace;

        public ActionBanner() {
            clear();
        }

        public ActionBanner clear() {
            this.action = CallToAction.emptyArray();
            this.primaryFace = null;
            this.secondaryFace = DocV2.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.action != null && this.action.length > 0) {
                for (CallToAction element : this.action) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.primaryFace != null) {
                output.writeMessage(2, this.primaryFace);
            }
            if (this.secondaryFace != null && this.secondaryFace.length > 0) {
                for (DocV2 element2 : this.secondaryFace) {
                    if (element2 != null) {
                        output.writeMessage(4, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.action != null && this.action.length > 0) {
                for (CallToAction element : this.action) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.primaryFace != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.primaryFace);
            }
            if (this.secondaryFace != null && this.secondaryFace.length > 0) {
                for (DocV2 element2 : this.secondaryFace) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element2);
                    }
                }
            }
            return size;
        }

        public ActionBanner mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.action == null) {
                            i = 0;
                        } else {
                            i = this.action.length;
                        }
                        CallToAction[] newArray = new CallToAction[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.action, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new CallToAction();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new CallToAction();
                        input.readMessage(newArray[i]);
                        this.action = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.primaryFace == null) {
                            this.primaryFace = new DocV2();
                        }
                        input.readMessage(this.primaryFace);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.secondaryFace == null) {
                            i = 0;
                        } else {
                            i = this.secondaryFace.length;
                        }
                        DocV2[] newArray2 = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.secondaryFace, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new DocV2();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new DocV2();
                        input.readMessage(newArray2[i]);
                        this.secondaryFace = newArray2;
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

    public static final class AddToCirclesContainer extends MessageNano {
        public AddToCirclesContainer() {
            clear();
        }

        public AddToCirclesContainer clear() {
            this.cachedSize = -1;
            return this;
        }

        public AddToCirclesContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class Annotations extends MessageNano {
        public String applicableVoucherDescription;
        public String attributionHtml;
        public Badge badgeForContentRating;
        public Badge[] badgeForCreator;
        public Badge[] badgeForDoc;
        public DocV2 creatorDoc;
        public BadgeContainer[] docBadgeContainer;
        public boolean hasApplicableVoucherDescription;
        public boolean hasAttributionHtml;
        public boolean hasOfferNote;
        public boolean hasPrivacyPolicyUrl;
        public Link link;
        public OBSOLETE_Reason oBSOLETEReason;
        public String offerNote;
        public Warning optimalDeviceClassWarning;
        public OverflowLink[] overflowLink;
        public PlusOneData plusOneData;
        public String privacyPolicyUrl;
        public PromotedDoc[] promotedDoc;
        public PurchaseHistoryDetails purchaseHistoryDetails;
        public SectionMetadata sectionBodyOfWork;
        public SectionMetadata sectionCoreContent;
        public SectionMetadata sectionCrossSell;
        public SectionMetadata sectionFeaturedApps;
        public SectionMetadata sectionMoreBy;
        public SectionMetadata sectionPurchaseCrossSell;
        public SectionMetadata sectionRateAndReview;
        public SectionMetadata sectionRelated;
        public SectionMetadata sectionRelatedDocType;
        public SectionMetadata sectionSuggestForRating;
        public DocV2[] subscription;
        public SuggestionReasons suggestionReasons;
        public Template template;
        public VoucherInfo[] voucherInfo;
        public Warning[] warning;

        public Annotations() {
            clear();
        }

        public Annotations clear() {
            this.sectionRelated = null;
            this.sectionRelatedDocType = null;
            this.sectionMoreBy = null;
            this.sectionBodyOfWork = null;
            this.sectionCoreContent = null;
            this.sectionCrossSell = null;
            this.sectionPurchaseCrossSell = null;
            this.sectionSuggestForRating = null;
            this.sectionRateAndReview = null;
            this.sectionFeaturedApps = null;
            this.plusOneData = null;
            this.warning = Warning.emptyArray();
            this.optimalDeviceClassWarning = null;
            this.link = null;
            this.template = null;
            this.badgeForCreator = Badge.emptyArray();
            this.badgeForDoc = Badge.emptyArray();
            this.badgeForContentRating = null;
            this.docBadgeContainer = BadgeContainer.emptyArray();
            this.promotedDoc = PromotedDoc.emptyArray();
            this.offerNote = "";
            this.hasOfferNote = false;
            this.subscription = DocV2.emptyArray();
            this.suggestionReasons = null;
            this.oBSOLETEReason = null;
            this.privacyPolicyUrl = "";
            this.hasPrivacyPolicyUrl = false;
            this.overflowLink = OverflowLink.emptyArray();
            this.creatorDoc = null;
            this.attributionHtml = "";
            this.hasAttributionHtml = false;
            this.purchaseHistoryDetails = null;
            this.voucherInfo = VoucherInfo.emptyArray();
            this.applicableVoucherDescription = "";
            this.hasApplicableVoucherDescription = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.sectionRelated != null) {
                output.writeMessage(1, this.sectionRelated);
            }
            if (this.sectionMoreBy != null) {
                output.writeMessage(2, this.sectionMoreBy);
            }
            if (this.plusOneData != null) {
                output.writeMessage(3, this.plusOneData);
            }
            if (this.warning != null && this.warning.length > 0) {
                for (Warning element : this.warning) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.sectionBodyOfWork != null) {
                output.writeMessage(5, this.sectionBodyOfWork);
            }
            if (this.sectionCoreContent != null) {
                output.writeMessage(6, this.sectionCoreContent);
            }
            if (this.template != null) {
                output.writeMessage(7, this.template);
            }
            if (this.badgeForCreator != null && this.badgeForCreator.length > 0) {
                for (Badge element2 : this.badgeForCreator) {
                    if (element2 != null) {
                        output.writeMessage(8, element2);
                    }
                }
            }
            if (this.badgeForDoc != null && this.badgeForDoc.length > 0) {
                for (Badge element22 : this.badgeForDoc) {
                    if (element22 != null) {
                        output.writeMessage(9, element22);
                    }
                }
            }
            if (this.link != null) {
                output.writeMessage(10, this.link);
            }
            if (this.sectionCrossSell != null) {
                output.writeMessage(11, this.sectionCrossSell);
            }
            if (this.sectionRelatedDocType != null) {
                output.writeMessage(12, this.sectionRelatedDocType);
            }
            if (this.promotedDoc != null && this.promotedDoc.length > 0) {
                for (PromotedDoc element3 : this.promotedDoc) {
                    if (element3 != null) {
                        output.writeMessage(13, element3);
                    }
                }
            }
            if (this.hasOfferNote || !this.offerNote.equals("")) {
                output.writeString(14, this.offerNote);
            }
            if (this.subscription != null && this.subscription.length > 0) {
                for (DocV2 element4 : this.subscription) {
                    if (element4 != null) {
                        output.writeMessage(16, element4);
                    }
                }
            }
            if (this.oBSOLETEReason != null) {
                output.writeMessage(17, this.oBSOLETEReason);
            }
            if (this.hasPrivacyPolicyUrl || !this.privacyPolicyUrl.equals("")) {
                output.writeString(18, this.privacyPolicyUrl);
            }
            if (this.suggestionReasons != null) {
                output.writeMessage(19, this.suggestionReasons);
            }
            if (this.optimalDeviceClassWarning != null) {
                output.writeMessage(20, this.optimalDeviceClassWarning);
            }
            if (this.docBadgeContainer != null && this.docBadgeContainer.length > 0) {
                for (BadgeContainer element5 : this.docBadgeContainer) {
                    if (element5 != null) {
                        output.writeMessage(21, element5);
                    }
                }
            }
            if (this.sectionSuggestForRating != null) {
                output.writeMessage(22, this.sectionSuggestForRating);
            }
            if (this.sectionRateAndReview != null) {
                output.writeMessage(23, this.sectionRateAndReview);
            }
            if (this.sectionPurchaseCrossSell != null) {
                output.writeMessage(24, this.sectionPurchaseCrossSell);
            }
            if (this.overflowLink != null && this.overflowLink.length > 0) {
                for (OverflowLink element6 : this.overflowLink) {
                    if (element6 != null) {
                        output.writeMessage(25, element6);
                    }
                }
            }
            if (this.creatorDoc != null) {
                output.writeMessage(26, this.creatorDoc);
            }
            if (this.hasAttributionHtml || !this.attributionHtml.equals("")) {
                output.writeString(27, this.attributionHtml);
            }
            if (this.purchaseHistoryDetails != null) {
                output.writeMessage(28, this.purchaseHistoryDetails);
            }
            if (this.badgeForContentRating != null) {
                output.writeMessage(29, this.badgeForContentRating);
            }
            if (this.voucherInfo != null && this.voucherInfo.length > 0) {
                for (VoucherInfo element7 : this.voucherInfo) {
                    if (element7 != null) {
                        output.writeMessage(30, element7);
                    }
                }
            }
            if (this.sectionFeaturedApps != null) {
                output.writeMessage(32, this.sectionFeaturedApps);
            }
            if (this.hasApplicableVoucherDescription || !this.applicableVoucherDescription.equals("")) {
                output.writeString(33, this.applicableVoucherDescription);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.sectionRelated != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.sectionRelated);
            }
            if (this.sectionMoreBy != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.sectionMoreBy);
            }
            if (this.plusOneData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.plusOneData);
            }
            if (this.warning != null && this.warning.length > 0) {
                for (Warning element : this.warning) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.sectionBodyOfWork != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.sectionBodyOfWork);
            }
            if (this.sectionCoreContent != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.sectionCoreContent);
            }
            if (this.template != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.template);
            }
            if (this.badgeForCreator != null && this.badgeForCreator.length > 0) {
                for (Badge element2 : this.badgeForCreator) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(8, element2);
                    }
                }
            }
            if (this.badgeForDoc != null && this.badgeForDoc.length > 0) {
                for (Badge element22 : this.badgeForDoc) {
                    if (element22 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(9, element22);
                    }
                }
            }
            if (this.link != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.link);
            }
            if (this.sectionCrossSell != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.sectionCrossSell);
            }
            if (this.sectionRelatedDocType != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.sectionRelatedDocType);
            }
            if (this.promotedDoc != null && this.promotedDoc.length > 0) {
                for (PromotedDoc element3 : this.promotedDoc) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(13, element3);
                    }
                }
            }
            if (this.hasOfferNote || !this.offerNote.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.offerNote);
            }
            if (this.subscription != null && this.subscription.length > 0) {
                for (DocV2 element4 : this.subscription) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(16, element4);
                    }
                }
            }
            if (this.oBSOLETEReason != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(17, this.oBSOLETEReason);
            }
            if (this.hasPrivacyPolicyUrl || !this.privacyPolicyUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(18, this.privacyPolicyUrl);
            }
            if (this.suggestionReasons != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(19, this.suggestionReasons);
            }
            if (this.optimalDeviceClassWarning != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(20, this.optimalDeviceClassWarning);
            }
            if (this.docBadgeContainer != null && this.docBadgeContainer.length > 0) {
                for (BadgeContainer element5 : this.docBadgeContainer) {
                    if (element5 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(21, element5);
                    }
                }
            }
            if (this.sectionSuggestForRating != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(22, this.sectionSuggestForRating);
            }
            if (this.sectionRateAndReview != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(23, this.sectionRateAndReview);
            }
            if (this.sectionPurchaseCrossSell != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(24, this.sectionPurchaseCrossSell);
            }
            if (this.overflowLink != null && this.overflowLink.length > 0) {
                for (OverflowLink element6 : this.overflowLink) {
                    if (element6 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(25, element6);
                    }
                }
            }
            if (this.creatorDoc != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(26, this.creatorDoc);
            }
            if (this.hasAttributionHtml || !this.attributionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(27, this.attributionHtml);
            }
            if (this.purchaseHistoryDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(28, this.purchaseHistoryDetails);
            }
            if (this.badgeForContentRating != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(29, this.badgeForContentRating);
            }
            if (this.voucherInfo != null && this.voucherInfo.length > 0) {
                for (VoucherInfo element7 : this.voucherInfo) {
                    if (element7 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(30, element7);
                    }
                }
            }
            if (this.sectionFeaturedApps != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(32, this.sectionFeaturedApps);
            }
            if (this.hasApplicableVoucherDescription || !this.applicableVoucherDescription.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(33, this.applicableVoucherDescription);
            }
            return size;
        }

        public Annotations mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                Badge[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.sectionRelated == null) {
                            this.sectionRelated = new SectionMetadata();
                        }
                        input.readMessage(this.sectionRelated);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.sectionMoreBy == null) {
                            this.sectionMoreBy = new SectionMetadata();
                        }
                        input.readMessage(this.sectionMoreBy);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.plusOneData == null) {
                            this.plusOneData = new PlusOneData();
                        }
                        input.readMessage(this.plusOneData);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.warning == null) {
                            i = 0;
                        } else {
                            i = this.warning.length;
                        }
                        Warning[] newArray2 = new Warning[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.warning, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Warning();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Warning();
                        input.readMessage(newArray2[i]);
                        this.warning = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.sectionBodyOfWork == null) {
                            this.sectionBodyOfWork = new SectionMetadata();
                        }
                        input.readMessage(this.sectionBodyOfWork);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.sectionCoreContent == null) {
                            this.sectionCoreContent = new SectionMetadata();
                        }
                        input.readMessage(this.sectionCoreContent);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.template == null) {
                            this.template = new Template();
                        }
                        input.readMessage(this.template);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.badgeForCreator == null) {
                            i = 0;
                        } else {
                            i = this.badgeForCreator.length;
                        }
                        newArray = new Badge[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.badgeForCreator, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Badge();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Badge();
                        input.readMessage(newArray[i]);
                        this.badgeForCreator = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 74);
                        if (this.badgeForDoc == null) {
                            i = 0;
                        } else {
                            i = this.badgeForDoc.length;
                        }
                        newArray = new Badge[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.badgeForDoc, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Badge();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Badge();
                        input.readMessage(newArray[i]);
                        this.badgeForDoc = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.link == null) {
                            this.link = new Link();
                        }
                        input.readMessage(this.link);
                        continue;
                    case 90:
                        if (this.sectionCrossSell == null) {
                            this.sectionCrossSell = new SectionMetadata();
                        }
                        input.readMessage(this.sectionCrossSell);
                        continue;
                    case 98:
                        if (this.sectionRelatedDocType == null) {
                            this.sectionRelatedDocType = new SectionMetadata();
                        }
                        input.readMessage(this.sectionRelatedDocType);
                        continue;
                    case 106:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 106);
                        if (this.promotedDoc == null) {
                            i = 0;
                        } else {
                            i = this.promotedDoc.length;
                        }
                        PromotedDoc[] newArray3 = new PromotedDoc[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.promotedDoc, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new PromotedDoc();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new PromotedDoc();
                        input.readMessage(newArray3[i]);
                        this.promotedDoc = newArray3;
                        continue;
                    case 114:
                        this.offerNote = input.readString();
                        this.hasOfferNote = true;
                        continue;
                    case 130:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 130);
                        if (this.subscription == null) {
                            i = 0;
                        } else {
                            i = this.subscription.length;
                        }
                        DocV2[] newArray4 = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.subscription, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new DocV2();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = new DocV2();
                        input.readMessage(newArray4[i]);
                        this.subscription = newArray4;
                        continue;
                    case 138:
                        if (this.oBSOLETEReason == null) {
                            this.oBSOLETEReason = new OBSOLETE_Reason();
                        }
                        input.readMessage(this.oBSOLETEReason);
                        continue;
                    case 146:
                        this.privacyPolicyUrl = input.readString();
                        this.hasPrivacyPolicyUrl = true;
                        continue;
                    case 154:
                        if (this.suggestionReasons == null) {
                            this.suggestionReasons = new SuggestionReasons();
                        }
                        input.readMessage(this.suggestionReasons);
                        continue;
                    case 162:
                        if (this.optimalDeviceClassWarning == null) {
                            this.optimalDeviceClassWarning = new Warning();
                        }
                        input.readMessage(this.optimalDeviceClassWarning);
                        continue;
                    case 170:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 170);
                        if (this.docBadgeContainer == null) {
                            i = 0;
                        } else {
                            i = this.docBadgeContainer.length;
                        }
                        BadgeContainer[] newArray5 = new BadgeContainer[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.docBadgeContainer, 0, newArray5, 0, i);
                        }
                        while (i < newArray5.length - 1) {
                            newArray5[i] = new BadgeContainer();
                            input.readMessage(newArray5[i]);
                            input.readTag();
                            i++;
                        }
                        newArray5[i] = new BadgeContainer();
                        input.readMessage(newArray5[i]);
                        this.docBadgeContainer = newArray5;
                        continue;
                    case 178:
                        if (this.sectionSuggestForRating == null) {
                            this.sectionSuggestForRating = new SectionMetadata();
                        }
                        input.readMessage(this.sectionSuggestForRating);
                        continue;
                    case 186:
                        if (this.sectionRateAndReview == null) {
                            this.sectionRateAndReview = new SectionMetadata();
                        }
                        input.readMessage(this.sectionRateAndReview);
                        continue;
                    case 194:
                        if (this.sectionPurchaseCrossSell == null) {
                            this.sectionPurchaseCrossSell = new SectionMetadata();
                        }
                        input.readMessage(this.sectionPurchaseCrossSell);
                        continue;
                    case 202:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 202);
                        if (this.overflowLink == null) {
                            i = 0;
                        } else {
                            i = this.overflowLink.length;
                        }
                        OverflowLink[] newArray6 = new OverflowLink[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.overflowLink, 0, newArray6, 0, i);
                        }
                        while (i < newArray6.length - 1) {
                            newArray6[i] = new OverflowLink();
                            input.readMessage(newArray6[i]);
                            input.readTag();
                            i++;
                        }
                        newArray6[i] = new OverflowLink();
                        input.readMessage(newArray6[i]);
                        this.overflowLink = newArray6;
                        continue;
                    case 210:
                        if (this.creatorDoc == null) {
                            this.creatorDoc = new DocV2();
                        }
                        input.readMessage(this.creatorDoc);
                        continue;
                    case 218:
                        this.attributionHtml = input.readString();
                        this.hasAttributionHtml = true;
                        continue;
                    case 226:
                        if (this.purchaseHistoryDetails == null) {
                            this.purchaseHistoryDetails = new PurchaseHistoryDetails();
                        }
                        input.readMessage(this.purchaseHistoryDetails);
                        continue;
                    case 234:
                        if (this.badgeForContentRating == null) {
                            this.badgeForContentRating = new Badge();
                        }
                        input.readMessage(this.badgeForContentRating);
                        continue;
                    case 242:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 242);
                        if (this.voucherInfo == null) {
                            i = 0;
                        } else {
                            i = this.voucherInfo.length;
                        }
                        VoucherInfo[] newArray7 = new VoucherInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.voucherInfo, 0, newArray7, 0, i);
                        }
                        while (i < newArray7.length - 1) {
                            newArray7[i] = new VoucherInfo();
                            input.readMessage(newArray7[i]);
                            input.readTag();
                            i++;
                        }
                        newArray7[i] = new VoucherInfo();
                        input.readMessage(newArray7[i]);
                        this.voucherInfo = newArray7;
                        continue;
                    case 258:
                        if (this.sectionFeaturedApps == null) {
                            this.sectionFeaturedApps = new SectionMetadata();
                        }
                        input.readMessage(this.sectionFeaturedApps);
                        continue;
                    case 266:
                        this.applicableVoucherDescription = input.readString();
                        this.hasApplicableVoucherDescription = true;
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

    public static final class CallToAction extends MessageNano {
        private static volatile CallToAction[] _emptyArray;
        public Image buttonIcon;
        public String buttonText;
        public String dismissalUrl;
        public boolean hasButtonText;
        public boolean hasDismissalUrl;
        public boolean hasType;
        public Link link;
        public int type;

        public static CallToAction[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CallToAction[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CallToAction() {
            clear();
        }

        public CallToAction clear() {
            this.type = 1;
            this.hasType = false;
            this.buttonText = "";
            this.hasButtonText = false;
            this.buttonIcon = null;
            this.dismissalUrl = "";
            this.hasDismissalUrl = false;
            this.link = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 1 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            if (this.hasButtonText || !this.buttonText.equals("")) {
                output.writeString(2, this.buttonText);
            }
            if (this.buttonIcon != null) {
                output.writeMessage(3, this.buttonIcon);
            }
            if (this.hasDismissalUrl || !this.dismissalUrl.equals("")) {
                output.writeString(4, this.dismissalUrl);
            }
            if (this.link != null) {
                output.writeMessage(5, this.link);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 1 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.hasButtonText || !this.buttonText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.buttonText);
            }
            if (this.buttonIcon != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.buttonIcon);
            }
            if (this.hasDismissalUrl || !this.dismissalUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.dismissalUrl);
            }
            if (this.link != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.link);
            }
            return size;
        }

        public CallToAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.buttonText = input.readString();
                        this.hasButtonText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.buttonIcon == null) {
                            this.buttonIcon = new Image();
                        }
                        input.readMessage(this.buttonIcon);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.dismissalUrl = input.readString();
                        this.hasDismissalUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.link == null) {
                            this.link = new Link();
                        }
                        input.readMessage(this.link);
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

    public static final class ContainerWithBanner extends MessageNano {
        public String colorThemeArgb;
        public boolean hasColorThemeArgb;

        public ContainerWithBanner() {
            clear();
        }

        public ContainerWithBanner clear() {
            this.colorThemeArgb = "";
            this.hasColorThemeArgb = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                output.writeString(1, this.colorThemeArgb);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.colorThemeArgb);
            }
            return size;
        }

        public ContainerWithBanner mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.colorThemeArgb = input.readString();
                        this.hasColorThemeArgb = true;
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

    public static final class DealOfTheDay extends MessageNano {
        public String colorThemeArgb;
        public String featuredHeader;
        public boolean hasColorThemeArgb;
        public boolean hasFeaturedHeader;

        public DealOfTheDay() {
            clear();
        }

        public DealOfTheDay clear() {
            this.featuredHeader = "";
            this.hasFeaturedHeader = false;
            this.colorThemeArgb = "";
            this.hasColorThemeArgb = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasFeaturedHeader || !this.featuredHeader.equals("")) {
                output.writeString(1, this.featuredHeader);
            }
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                output.writeString(2, this.colorThemeArgb);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasFeaturedHeader || !this.featuredHeader.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.featuredHeader);
            }
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.colorThemeArgb);
            }
            return size;
        }

        public DealOfTheDay mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.featuredHeader = input.readString();
                        this.hasFeaturedHeader = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.colorThemeArgb = input.readString();
                        this.hasColorThemeArgb = true;
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

    public static final class Dismissal extends MessageNano {
        public String descriptionHtml;
        public boolean hasDescriptionHtml;
        public boolean hasUrl;
        public String url;

        public Dismissal() {
            clear();
        }

        public Dismissal clear() {
            this.url = "";
            this.hasUrl = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUrl || !this.url.equals("")) {
                output.writeString(1, this.url);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(2, this.descriptionHtml);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUrl || !this.url.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.descriptionHtml);
            }
            return size;
        }

        public Dismissal mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.url = input.readString();
                        this.hasUrl = true;
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

    public static final class DocV2 extends MessageNano {
        private static volatile DocV2[] _emptyArray;
        public AggregateRating aggregateRating;
        public Annotations annotations;
        public Availability availability;
        public String backendDocid;
        public int backendId;
        public String backendUrl;
        public DocV2[] child;
        public ContainerMetadata containerMetadata;
        public String creator;
        public String descriptionHtml;
        public DocumentDetails details;
        public boolean detailsReusable;
        public String detailsUrl;
        public int docType;
        public String docid;
        public boolean hasBackendDocid;
        public boolean hasBackendId;
        public boolean hasBackendUrl;
        public boolean hasCreator;
        public boolean hasDescriptionHtml;
        public boolean hasDetailsReusable;
        public boolean hasDetailsUrl;
        public boolean hasDocType;
        public boolean hasDocid;
        public boolean hasMature;
        public boolean hasPromotionalDescription;
        public boolean hasPurchaseDetailsUrl;
        public boolean hasReviewsUrl;
        public boolean hasServerLogsCookie;
        public boolean hasShareUrl;
        public boolean hasSubtitle;
        public boolean hasTitle;
        public boolean hasTranslatedDescriptionHtml;
        public Image[] image;
        public boolean mature;
        public Offer[] offer;
        public ProductDetails productDetails;
        public String promotionalDescription;
        public String purchaseDetailsUrl;
        public String reviewsUrl;
        public byte[] serverLogsCookie;
        public String shareUrl;
        public String subtitle;
        public String title;
        public String translatedDescriptionHtml;

        public static DocV2[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DocV2[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DocV2() {
            clear();
        }

        public DocV2 clear() {
            this.docid = "";
            this.hasDocid = false;
            this.backendDocid = "";
            this.hasBackendDocid = false;
            this.docType = 1;
            this.hasDocType = false;
            this.backendId = 0;
            this.hasBackendId = false;
            this.title = "";
            this.hasTitle = false;
            this.subtitle = "";
            this.hasSubtitle = false;
            this.creator = "";
            this.hasCreator = false;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.translatedDescriptionHtml = "";
            this.hasTranslatedDescriptionHtml = false;
            this.promotionalDescription = "";
            this.hasPromotionalDescription = false;
            this.offer = Offer.emptyArray();
            this.availability = null;
            this.image = Image.emptyArray();
            this.child = emptyArray();
            this.containerMetadata = null;
            this.details = null;
            this.productDetails = null;
            this.aggregateRating = null;
            this.annotations = null;
            this.detailsUrl = "";
            this.hasDetailsUrl = false;
            this.shareUrl = "";
            this.hasShareUrl = false;
            this.reviewsUrl = "";
            this.hasReviewsUrl = false;
            this.backendUrl = "";
            this.hasBackendUrl = false;
            this.purchaseDetailsUrl = "";
            this.hasPurchaseDetailsUrl = false;
            this.detailsReusable = false;
            this.hasDetailsReusable = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.mature = false;
            this.hasMature = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDocid || !this.docid.equals("")) {
                output.writeString(1, this.docid);
            }
            if (this.hasBackendDocid || !this.backendDocid.equals("")) {
                output.writeString(2, this.backendDocid);
            }
            if (this.docType != 1 || this.hasDocType) {
                output.writeInt32(3, this.docType);
            }
            if (this.backendId != 0 || this.hasBackendId) {
                output.writeInt32(4, this.backendId);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(5, this.title);
            }
            if (this.hasCreator || !this.creator.equals("")) {
                output.writeString(6, this.creator);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(7, this.descriptionHtml);
            }
            if (this.offer != null && this.offer.length > 0) {
                for (Offer element : this.offer) {
                    if (element != null) {
                        output.writeMessage(8, element);
                    }
                }
            }
            if (this.availability != null) {
                output.writeMessage(9, this.availability);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element2 : this.image) {
                    if (element2 != null) {
                        output.writeMessage(10, element2);
                    }
                }
            }
            if (this.child != null && this.child.length > 0) {
                for (DocV2 element3 : this.child) {
                    if (element3 != null) {
                        output.writeMessage(11, element3);
                    }
                }
            }
            if (this.containerMetadata != null) {
                output.writeMessage(12, this.containerMetadata);
            }
            if (this.details != null) {
                output.writeMessage(13, this.details);
            }
            if (this.aggregateRating != null) {
                output.writeMessage(14, this.aggregateRating);
            }
            if (this.annotations != null) {
                output.writeMessage(15, this.annotations);
            }
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                output.writeString(16, this.detailsUrl);
            }
            if (this.hasShareUrl || !this.shareUrl.equals("")) {
                output.writeString(17, this.shareUrl);
            }
            if (this.hasReviewsUrl || !this.reviewsUrl.equals("")) {
                output.writeString(18, this.reviewsUrl);
            }
            if (this.hasBackendUrl || !this.backendUrl.equals("")) {
                output.writeString(19, this.backendUrl);
            }
            if (this.hasPurchaseDetailsUrl || !this.purchaseDetailsUrl.equals("")) {
                output.writeString(20, this.purchaseDetailsUrl);
            }
            if (this.hasDetailsReusable || this.detailsReusable) {
                output.writeBool(21, this.detailsReusable);
            }
            if (this.hasSubtitle || !this.subtitle.equals("")) {
                output.writeString(22, this.subtitle);
            }
            if (this.hasTranslatedDescriptionHtml || !this.translatedDescriptionHtml.equals("")) {
                output.writeString(23, this.translatedDescriptionHtml);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(24, this.serverLogsCookie);
            }
            if (this.productDetails != null) {
                output.writeMessage(25, this.productDetails);
            }
            if (this.hasMature || this.mature) {
                output.writeBool(26, this.mature);
            }
            if (this.hasPromotionalDescription || !this.promotionalDescription.equals("")) {
                output.writeString(27, this.promotionalDescription);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDocid || !this.docid.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.docid);
            }
            if (this.hasBackendDocid || !this.backendDocid.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.backendDocid);
            }
            if (this.docType != 1 || this.hasDocType) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.docType);
            }
            if (this.backendId != 0 || this.hasBackendId) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.backendId);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.title);
            }
            if (this.hasCreator || !this.creator.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.creator);
            }
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.descriptionHtml);
            }
            if (this.offer != null && this.offer.length > 0) {
                for (Offer element : this.offer) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(8, element);
                    }
                }
            }
            if (this.availability != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.availability);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element2 : this.image) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(10, element2);
                    }
                }
            }
            if (this.child != null && this.child.length > 0) {
                for (DocV2 element3 : this.child) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(11, element3);
                    }
                }
            }
            if (this.containerMetadata != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.containerMetadata);
            }
            if (this.details != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(13, this.details);
            }
            if (this.aggregateRating != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.aggregateRating);
            }
            if (this.annotations != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(15, this.annotations);
            }
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(16, this.detailsUrl);
            }
            if (this.hasShareUrl || !this.shareUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(17, this.shareUrl);
            }
            if (this.hasReviewsUrl || !this.reviewsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(18, this.reviewsUrl);
            }
            if (this.hasBackendUrl || !this.backendUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.backendUrl);
            }
            if (this.hasPurchaseDetailsUrl || !this.purchaseDetailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(20, this.purchaseDetailsUrl);
            }
            if (this.hasDetailsReusable || this.detailsReusable) {
                size += CodedOutputByteBufferNano.computeBoolSize(21, this.detailsReusable);
            }
            if (this.hasSubtitle || !this.subtitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(22, this.subtitle);
            }
            if (this.hasTranslatedDescriptionHtml || !this.translatedDescriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(23, this.translatedDescriptionHtml);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(24, this.serverLogsCookie);
            }
            if (this.productDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(25, this.productDetails);
            }
            if (this.hasMature || this.mature) {
                size += CodedOutputByteBufferNano.computeBoolSize(26, this.mature);
            }
            if (this.hasPromotionalDescription || !this.promotionalDescription.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(27, this.promotionalDescription);
            }
            return size;
        }

        public DocV2 mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.docid = input.readString();
                        this.hasDocid = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.backendDocid = input.readString();
                        this.hasBackendDocid = true;
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
                                this.docType = value;
                                this.hasDocType = true;
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
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.creator = input.readString();
                        this.hasCreator = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.offer == null) {
                            i = 0;
                        } else {
                            i = this.offer.length;
                        }
                        Offer[] newArray = new Offer[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.offer, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Offer();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Offer();
                        input.readMessage(newArray[i]);
                        this.offer = newArray;
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
                        Image[] newArray2 = new Image[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.image, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Image();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Image();
                        input.readMessage(newArray2[i]);
                        this.image = newArray2;
                        continue;
                    case 90:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                        if (this.child == null) {
                            i = 0;
                        } else {
                            i = this.child.length;
                        }
                        DocV2[] newArray3 = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.child, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new DocV2();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new DocV2();
                        input.readMessage(newArray3[i]);
                        this.child = newArray3;
                        continue;
                    case 98:
                        if (this.containerMetadata == null) {
                            this.containerMetadata = new ContainerMetadata();
                        }
                        input.readMessage(this.containerMetadata);
                        continue;
                    case 106:
                        if (this.details == null) {
                            this.details = new DocumentDetails();
                        }
                        input.readMessage(this.details);
                        continue;
                    case 114:
                        if (this.aggregateRating == null) {
                            this.aggregateRating = new AggregateRating();
                        }
                        input.readMessage(this.aggregateRating);
                        continue;
                    case 122:
                        if (this.annotations == null) {
                            this.annotations = new Annotations();
                        }
                        input.readMessage(this.annotations);
                        continue;
                    case 130:
                        this.detailsUrl = input.readString();
                        this.hasDetailsUrl = true;
                        continue;
                    case 138:
                        this.shareUrl = input.readString();
                        this.hasShareUrl = true;
                        continue;
                    case 146:
                        this.reviewsUrl = input.readString();
                        this.hasReviewsUrl = true;
                        continue;
                    case 154:
                        this.backendUrl = input.readString();
                        this.hasBackendUrl = true;
                        continue;
                    case 162:
                        this.purchaseDetailsUrl = input.readString();
                        this.hasPurchaseDetailsUrl = true;
                        continue;
                    case 168:
                        this.detailsReusable = input.readBool();
                        this.hasDetailsReusable = true;
                        continue;
                    case 178:
                        this.subtitle = input.readString();
                        this.hasSubtitle = true;
                        continue;
                    case 186:
                        this.translatedDescriptionHtml = input.readString();
                        this.hasTranslatedDescriptionHtml = true;
                        continue;
                    case 194:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case 202:
                        if (this.productDetails == null) {
                            this.productDetails = new ProductDetails();
                        }
                        input.readMessage(this.productDetails);
                        continue;
                    case 208:
                        this.mature = input.readBool();
                        this.hasMature = true;
                        continue;
                    case 218:
                        this.promotionalDescription = input.readString();
                        this.hasPromotionalDescription = true;
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

    public static final class EditorialSeriesContainer extends MessageNano {
        public String colorThemeArgb;
        public String episodeSubtitle;
        public String episodeTitle;
        public boolean hasColorThemeArgb;
        public boolean hasEpisodeSubtitle;
        public boolean hasEpisodeTitle;
        public boolean hasSeriesSubtitle;
        public boolean hasSeriesTitle;
        public String seriesSubtitle;
        public String seriesTitle;
        public VideoSnippet[] videoSnippet;

        public EditorialSeriesContainer() {
            clear();
        }

        public EditorialSeriesContainer clear() {
            this.seriesTitle = "";
            this.hasSeriesTitle = false;
            this.seriesSubtitle = "";
            this.hasSeriesSubtitle = false;
            this.episodeTitle = "";
            this.hasEpisodeTitle = false;
            this.episodeSubtitle = "";
            this.hasEpisodeSubtitle = false;
            this.colorThemeArgb = "";
            this.hasColorThemeArgb = false;
            this.videoSnippet = VideoSnippet.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSeriesTitle || !this.seriesTitle.equals("")) {
                output.writeString(1, this.seriesTitle);
            }
            if (this.hasSeriesSubtitle || !this.seriesSubtitle.equals("")) {
                output.writeString(2, this.seriesSubtitle);
            }
            if (this.hasEpisodeTitle || !this.episodeTitle.equals("")) {
                output.writeString(3, this.episodeTitle);
            }
            if (this.hasEpisodeSubtitle || !this.episodeSubtitle.equals("")) {
                output.writeString(4, this.episodeSubtitle);
            }
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                output.writeString(5, this.colorThemeArgb);
            }
            if (this.videoSnippet != null && this.videoSnippet.length > 0) {
                for (VideoSnippet element : this.videoSnippet) {
                    if (element != null) {
                        output.writeMessage(6, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSeriesTitle || !this.seriesTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.seriesTitle);
            }
            if (this.hasSeriesSubtitle || !this.seriesSubtitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.seriesSubtitle);
            }
            if (this.hasEpisodeTitle || !this.episodeTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.episodeTitle);
            }
            if (this.hasEpisodeSubtitle || !this.episodeSubtitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.episodeSubtitle);
            }
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.colorThemeArgb);
            }
            if (this.videoSnippet != null && this.videoSnippet.length > 0) {
                for (VideoSnippet element : this.videoSnippet) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element);
                    }
                }
            }
            return size;
        }

        public EditorialSeriesContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.seriesTitle = input.readString();
                        this.hasSeriesTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.seriesSubtitle = input.readString();
                        this.hasSeriesSubtitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.episodeTitle = input.readString();
                        this.hasEpisodeTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.episodeSubtitle = input.readString();
                        this.hasEpisodeSubtitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.colorThemeArgb = input.readString();
                        this.hasColorThemeArgb = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.videoSnippet == null) {
                            i = 0;
                        } else {
                            i = this.videoSnippet.length;
                        }
                        VideoSnippet[] newArray = new VideoSnippet[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.videoSnippet, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new VideoSnippet();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new VideoSnippet();
                        input.readMessage(newArray[i]);
                        this.videoSnippet = newArray;
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

    public static final class EmptyContainer extends MessageNano {
        public String emptyMessage;
        public boolean hasEmptyMessage;

        public EmptyContainer() {
            clear();
        }

        public EmptyContainer clear() {
            this.emptyMessage = "";
            this.hasEmptyMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasEmptyMessage || !this.emptyMessage.equals("")) {
                output.writeString(1, this.emptyMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasEmptyMessage || !this.emptyMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(1, this.emptyMessage);
            }
            return size;
        }

        public EmptyContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.emptyMessage = input.readString();
                        this.hasEmptyMessage = true;
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

    public static final class MoreByCreatorContainer extends MessageNano {
        public DocV2 creatorInformation;

        public MoreByCreatorContainer() {
            clear();
        }

        public MoreByCreatorContainer clear() {
            this.creatorInformation = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.creatorInformation != null) {
                output.writeMessage(1, this.creatorInformation);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.creatorInformation != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.creatorInformation);
            }
            return size;
        }

        public MoreByCreatorContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.creatorInformation == null) {
                            this.creatorInformation = new DocV2();
                        }
                        input.readMessage(this.creatorInformation);
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

    public static final class MyCirclesContainer extends MessageNano {
        public MyCirclesContainer() {
            clear();
        }

        public MyCirclesContainer clear() {
            this.cachedSize = -1;
            return this;
        }

        public MyCirclesContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class NextBanner extends MessageNano {
        public String colorTextArgb;
        public boolean hasColorTextArgb;
        public boolean hasSubtitle;
        public boolean hasTitle;
        public String subtitle;
        public String title;

        public NextBanner() {
            clear();
        }

        public NextBanner clear() {
            this.title = "";
            this.hasTitle = false;
            this.subtitle = "";
            this.hasSubtitle = false;
            this.colorTextArgb = "";
            this.hasColorTextArgb = false;
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
            if (this.hasColorTextArgb || !this.colorTextArgb.equals("")) {
                output.writeString(3, this.colorTextArgb);
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
            if (this.hasColorTextArgb || !this.colorTextArgb.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.colorTextArgb);
            }
            return size;
        }

        public NextBanner mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.colorTextArgb = input.readString();
                        this.hasColorTextArgb = true;
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

    public static final class OBSOLETE_Reason extends MessageNano {
        public String briefReason;
        public boolean hasBriefReason;
        public boolean hasOBSOLETEDetailedReason;
        public boolean hasUniqueId;
        public String oBSOLETEDetailedReason;
        public String uniqueId;

        public OBSOLETE_Reason() {
            clear();
        }

        public OBSOLETE_Reason clear() {
            this.briefReason = "";
            this.hasBriefReason = false;
            this.oBSOLETEDetailedReason = "";
            this.hasOBSOLETEDetailedReason = false;
            this.uniqueId = "";
            this.hasUniqueId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasBriefReason || !this.briefReason.equals("")) {
                output.writeString(1, this.briefReason);
            }
            if (this.hasOBSOLETEDetailedReason || !this.oBSOLETEDetailedReason.equals("")) {
                output.writeString(2, this.oBSOLETEDetailedReason);
            }
            if (this.hasUniqueId || !this.uniqueId.equals("")) {
                output.writeString(3, this.uniqueId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasBriefReason || !this.briefReason.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.briefReason);
            }
            if (this.hasOBSOLETEDetailedReason || !this.oBSOLETEDetailedReason.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.oBSOLETEDetailedReason);
            }
            if (this.hasUniqueId || !this.uniqueId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.uniqueId);
            }
            return size;
        }

        public OBSOLETE_Reason mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.briefReason = input.readString();
                        this.hasBriefReason = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.oBSOLETEDetailedReason = input.readString();
                        this.hasOBSOLETEDetailedReason = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.uniqueId = input.readString();
                        this.hasUniqueId = true;
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

    public static final class OverflowLink extends MessageNano {
        private static volatile OverflowLink[] _emptyArray;
        public boolean hasTitle;
        public Link link;
        public String title;

        public static OverflowLink[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new OverflowLink[0];
                    }
                }
            }
            return _emptyArray;
        }

        public OverflowLink() {
            clear();
        }

        public OverflowLink clear() {
            this.title = "";
            this.hasTitle = false;
            this.link = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.link != null) {
                output.writeMessage(2, this.link);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.link != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.link);
            }
            return size;
        }

        public OverflowLink mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        if (this.link == null) {
                            this.link = new Link();
                        }
                        input.readMessage(this.link);
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

    public static final class PlusOneData extends MessageNano {
        public DocV2[] circlePerson;
        public long circlesTotal;
        public boolean hasCirclesTotal;
        public boolean hasSetByUser;
        public boolean hasTotal;
        public OBSOLETE_PlusProfile[] oBSOLETECirclesProfiles;
        public boolean setByUser;
        public long total;

        public PlusOneData() {
            clear();
        }

        public PlusOneData clear() {
            this.setByUser = false;
            this.hasSetByUser = false;
            this.total = 0;
            this.hasTotal = false;
            this.circlesTotal = 0;
            this.hasCirclesTotal = false;
            this.circlePerson = DocV2.emptyArray();
            this.oBSOLETECirclesProfiles = OBSOLETE_PlusProfile.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSetByUser || this.setByUser) {
                output.writeBool(1, this.setByUser);
            }
            if (this.hasTotal || this.total != 0) {
                output.writeInt64(2, this.total);
            }
            if (this.hasCirclesTotal || this.circlesTotal != 0) {
                output.writeInt64(3, this.circlesTotal);
            }
            if (this.oBSOLETECirclesProfiles != null && this.oBSOLETECirclesProfiles.length > 0) {
                for (OBSOLETE_PlusProfile element : this.oBSOLETECirclesProfiles) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.circlePerson != null && this.circlePerson.length > 0) {
                for (DocV2 element2 : this.circlePerson) {
                    if (element2 != null) {
                        output.writeMessage(5, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSetByUser || this.setByUser) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.setByUser);
            }
            if (this.hasTotal || this.total != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.total);
            }
            if (this.hasCirclesTotal || this.circlesTotal != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.circlesTotal);
            }
            if (this.oBSOLETECirclesProfiles != null && this.oBSOLETECirclesProfiles.length > 0) {
                for (OBSOLETE_PlusProfile element : this.oBSOLETECirclesProfiles) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.circlePerson != null && this.circlePerson.length > 0) {
                for (DocV2 element2 : this.circlePerson) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element2);
                    }
                }
            }
            return size;
        }

        public PlusOneData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.setByUser = input.readBool();
                        this.hasSetByUser = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.total = input.readInt64();
                        this.hasTotal = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.circlesTotal = input.readInt64();
                        this.hasCirclesTotal = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.oBSOLETECirclesProfiles == null) {
                            i = 0;
                        } else {
                            i = this.oBSOLETECirclesProfiles.length;
                        }
                        OBSOLETE_PlusProfile[] newArray = new OBSOLETE_PlusProfile[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.oBSOLETECirclesProfiles, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new OBSOLETE_PlusProfile();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new OBSOLETE_PlusProfile();
                        input.readMessage(newArray[i]);
                        this.oBSOLETECirclesProfiles = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.circlePerson == null) {
                            i = 0;
                        } else {
                            i = this.circlePerson.length;
                        }
                        DocV2[] newArray2 = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.circlePerson, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new DocV2();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new DocV2();
                        input.readMessage(newArray2[i]);
                        this.circlePerson = newArray2;
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

    public static final class PurchaseHistoryContainer extends MessageNano {
        public PurchaseHistoryContainer() {
            clear();
        }

        public PurchaseHistoryContainer clear() {
            this.cachedSize = -1;
            return this;
        }

        public PurchaseHistoryContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class RateAndSuggestContainer extends MessageNano {
        public RateAndSuggestContainer() {
            clear();
        }

        public RateAndSuggestContainer clear() {
            this.cachedSize = -1;
            return this;
        }

        public RateAndSuggestContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class RateContainer extends MessageNano {
        public RateContainer() {
            clear();
        }

        public RateContainer clear() {
            this.cachedSize = -1;
            return this;
        }

        public RateContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class Reason extends MessageNano {
        private static volatile Reason[] _emptyArray;
        public String descriptionHtml;
        public Dismissal dismissal;
        public boolean hasDescriptionHtml;
        public ReasonPlusOne reasonPlusOne;
        public ReasonReview reasonReview;
        public ReasonUserAction reasonUserAction;

        public static Reason[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Reason[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Reason() {
            clear();
        }

        public Reason clear() {
            this.dismissal = null;
            this.descriptionHtml = "";
            this.hasDescriptionHtml = false;
            this.reasonPlusOne = null;
            this.reasonReview = null;
            this.reasonUserAction = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                output.writeString(3, this.descriptionHtml);
            }
            if (this.reasonPlusOne != null) {
                output.writeMessage(4, this.reasonPlusOne);
            }
            if (this.reasonReview != null) {
                output.writeMessage(5, this.reasonReview);
            }
            if (this.dismissal != null) {
                output.writeMessage(7, this.dismissal);
            }
            if (this.reasonUserAction != null) {
                output.writeMessage(9, this.reasonUserAction);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDescriptionHtml || !this.descriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.descriptionHtml);
            }
            if (this.reasonPlusOne != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.reasonPlusOne);
            }
            if (this.reasonReview != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.reasonReview);
            }
            if (this.dismissal != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.dismissal);
            }
            if (this.reasonUserAction != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(9, this.reasonUserAction);
            }
            return size;
        }

        public Reason mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.descriptionHtml = input.readString();
                        this.hasDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.reasonPlusOne == null) {
                            this.reasonPlusOne = new ReasonPlusOne();
                        }
                        input.readMessage(this.reasonPlusOne);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.reasonReview == null) {
                            this.reasonReview = new ReasonReview();
                        }
                        input.readMessage(this.reasonReview);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.dismissal == null) {
                            this.dismissal = new Dismissal();
                        }
                        input.readMessage(this.dismissal);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.reasonUserAction == null) {
                            this.reasonUserAction = new ReasonUserAction();
                        }
                        input.readMessage(this.reasonUserAction);
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

    public static final class ReasonPlusOne extends MessageNano {
        public boolean hasLocalizedDescriptionHtml;
        public String localizedDescriptionHtml;
        public OBSOLETE_PlusProfile[] oBSOLETEPlusProfile;
        public DocV2[] person;

        public ReasonPlusOne() {
            clear();
        }

        public ReasonPlusOne clear() {
            this.localizedDescriptionHtml = "";
            this.hasLocalizedDescriptionHtml = false;
            this.person = DocV2.emptyArray();
            this.oBSOLETEPlusProfile = OBSOLETE_PlusProfile.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLocalizedDescriptionHtml || !this.localizedDescriptionHtml.equals("")) {
                output.writeString(1, this.localizedDescriptionHtml);
            }
            if (this.oBSOLETEPlusProfile != null && this.oBSOLETEPlusProfile.length > 0) {
                for (OBSOLETE_PlusProfile element : this.oBSOLETEPlusProfile) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.person != null && this.person.length > 0) {
                for (DocV2 element2 : this.person) {
                    if (element2 != null) {
                        output.writeMessage(3, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLocalizedDescriptionHtml || !this.localizedDescriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.localizedDescriptionHtml);
            }
            if (this.oBSOLETEPlusProfile != null && this.oBSOLETEPlusProfile.length > 0) {
                for (OBSOLETE_PlusProfile element : this.oBSOLETEPlusProfile) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.person != null && this.person.length > 0) {
                for (DocV2 element2 : this.person) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                    }
                }
            }
            return size;
        }

        public ReasonPlusOne mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.localizedDescriptionHtml = input.readString();
                        this.hasLocalizedDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.oBSOLETEPlusProfile == null) {
                            i = 0;
                        } else {
                            i = this.oBSOLETEPlusProfile.length;
                        }
                        OBSOLETE_PlusProfile[] newArray = new OBSOLETE_PlusProfile[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.oBSOLETEPlusProfile, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new OBSOLETE_PlusProfile();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new OBSOLETE_PlusProfile();
                        input.readMessage(newArray[i]);
                        this.oBSOLETEPlusProfile = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.person == null) {
                            i = 0;
                        } else {
                            i = this.person.length;
                        }
                        DocV2[] newArray2 = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.person, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new DocV2();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new DocV2();
                        input.readMessage(newArray2[i]);
                        this.person = newArray2;
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

    public static final class ReasonReview extends MessageNano {
        public Review review;

        public ReasonReview() {
            clear();
        }

        public ReasonReview clear() {
            this.review = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.review != null) {
                output.writeMessage(1, this.review);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.review != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.review);
            }
            return size;
        }

        public ReasonReview mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.review == null) {
                            this.review = new Review();
                        }
                        input.readMessage(this.review);
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

    public static final class ReasonUserAction extends MessageNano {
        public boolean hasLocalizedDescriptionHtml;
        public String localizedDescriptionHtml;
        public DocV2 person;

        public ReasonUserAction() {
            clear();
        }

        public ReasonUserAction clear() {
            this.person = null;
            this.localizedDescriptionHtml = "";
            this.hasLocalizedDescriptionHtml = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.person != null) {
                output.writeMessage(1, this.person);
            }
            if (this.hasLocalizedDescriptionHtml || !this.localizedDescriptionHtml.equals("")) {
                output.writeString(2, this.localizedDescriptionHtml);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.person != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.person);
            }
            if (this.hasLocalizedDescriptionHtml || !this.localizedDescriptionHtml.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.localizedDescriptionHtml);
            }
            return size;
        }

        public ReasonUserAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.person == null) {
                            this.person = new DocV2();
                        }
                        input.readMessage(this.person);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.localizedDescriptionHtml = input.readString();
                        this.hasLocalizedDescriptionHtml = true;
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

    public static final class RecommendationsContainer extends MessageNano {
        public RecommendationsContainer() {
            clear();
        }

        public RecommendationsContainer clear() {
            this.cachedSize = -1;
            return this;
        }

        public RecommendationsContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class RecommendationsContainerWithHeader extends MessageNano {
        public DocV2 primaryFace;
        public DocV2[] secondaryFace;

        public RecommendationsContainerWithHeader() {
            clear();
        }

        public RecommendationsContainerWithHeader clear() {
            this.primaryFace = null;
            this.secondaryFace = DocV2.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.primaryFace != null) {
                output.writeMessage(1, this.primaryFace);
            }
            if (this.secondaryFace != null && this.secondaryFace.length > 0) {
                for (DocV2 element : this.secondaryFace) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.primaryFace != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.primaryFace);
            }
            if (this.secondaryFace != null && this.secondaryFace.length > 0) {
                for (DocV2 element : this.secondaryFace) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public RecommendationsContainerWithHeader mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.primaryFace == null) {
                            this.primaryFace = new DocV2();
                        }
                        input.readMessage(this.primaryFace);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.secondaryFace == null) {
                            i = 0;
                        } else {
                            i = this.secondaryFace.length;
                        }
                        DocV2[] newArray = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.secondaryFace, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new DocV2();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new DocV2();
                        input.readMessage(newArray[i]);
                        this.secondaryFace = newArray;
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

    public static final class Review extends MessageNano {
        private static volatile Review[] _emptyArray;
        public DocV2 author;
        public String authorName;
        public String comment;
        public String commentId;
        public String deviceName;
        public String documentVersion;
        public boolean hasAuthorName;
        public boolean hasComment;
        public boolean hasCommentId;
        public boolean hasDeviceName;
        public boolean hasDocumentVersion;
        public boolean hasReplyText;
        public boolean hasReplyTimestampMsec;
        public boolean hasSource;
        public boolean hasStarRating;
        public boolean hasTimestampMsec;
        public boolean hasTitle;
        public boolean hasUrl;
        public OBSOLETE_PlusProfile oBSOLETEPlusProfile;
        public String replyText;
        public long replyTimestampMsec;
        public Image sentiment;
        public String source;
        public int starRating;
        public long timestampMsec;
        public String title;
        public String url;

        public static Review[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Review[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Review() {
            clear();
        }

        public Review clear() {
            this.commentId = "";
            this.hasCommentId = false;
            this.author = null;
            this.starRating = 0;
            this.hasStarRating = false;
            this.sentiment = null;
            this.title = "";
            this.hasTitle = false;
            this.comment = "";
            this.hasComment = false;
            this.url = "";
            this.hasUrl = false;
            this.source = "";
            this.hasSource = false;
            this.documentVersion = "";
            this.hasDocumentVersion = false;
            this.timestampMsec = 0;
            this.hasTimestampMsec = false;
            this.deviceName = "";
            this.hasDeviceName = false;
            this.replyText = "";
            this.hasReplyText = false;
            this.replyTimestampMsec = 0;
            this.hasReplyTimestampMsec = false;
            this.oBSOLETEPlusProfile = null;
            this.authorName = "";
            this.hasAuthorName = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasAuthorName || !this.authorName.equals("")) {
                output.writeString(1, this.authorName);
            }
            if (this.hasUrl || !this.url.equals("")) {
                output.writeString(2, this.url);
            }
            if (this.hasSource || !this.source.equals("")) {
                output.writeString(3, this.source);
            }
            if (this.hasDocumentVersion || !this.documentVersion.equals("")) {
                output.writeString(4, this.documentVersion);
            }
            if (this.hasTimestampMsec || this.timestampMsec != 0) {
                output.writeInt64(5, this.timestampMsec);
            }
            if (this.hasStarRating || this.starRating != 0) {
                output.writeInt32(6, this.starRating);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(7, this.title);
            }
            if (this.hasComment || !this.comment.equals("")) {
                output.writeString(8, this.comment);
            }
            if (this.hasCommentId || !this.commentId.equals("")) {
                output.writeString(9, this.commentId);
            }
            if (this.hasDeviceName || !this.deviceName.equals("")) {
                output.writeString(19, this.deviceName);
            }
            if (this.hasReplyText || !this.replyText.equals("")) {
                output.writeString(29, this.replyText);
            }
            if (this.hasReplyTimestampMsec || this.replyTimestampMsec != 0) {
                output.writeInt64(30, this.replyTimestampMsec);
            }
            if (this.oBSOLETEPlusProfile != null) {
                output.writeMessage(31, this.oBSOLETEPlusProfile);
            }
            if (this.author != null) {
                output.writeMessage(33, this.author);
            }
            if (this.sentiment != null) {
                output.writeMessage(34, this.sentiment);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasAuthorName || !this.authorName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.authorName);
            }
            if (this.hasUrl || !this.url.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.url);
            }
            if (this.hasSource || !this.source.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.source);
            }
            if (this.hasDocumentVersion || !this.documentVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.documentVersion);
            }
            if (this.hasTimestampMsec || this.timestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.timestampMsec);
            }
            if (this.hasStarRating || this.starRating != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.starRating);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.title);
            }
            if (this.hasComment || !this.comment.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.comment);
            }
            if (this.hasCommentId || !this.commentId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.commentId);
            }
            if (this.hasDeviceName || !this.deviceName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(19, this.deviceName);
            }
            if (this.hasReplyText || !this.replyText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(29, this.replyText);
            }
            if (this.hasReplyTimestampMsec || this.replyTimestampMsec != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(30, this.replyTimestampMsec);
            }
            if (this.oBSOLETEPlusProfile != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(31, this.oBSOLETEPlusProfile);
            }
            if (this.author != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(33, this.author);
            }
            if (this.sentiment != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(34, this.sentiment);
            }
            return size;
        }

        public Review mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.authorName = input.readString();
                        this.hasAuthorName = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.url = input.readString();
                        this.hasUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.source = input.readString();
                        this.hasSource = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.documentVersion = input.readString();
                        this.hasDocumentVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.timestampMsec = input.readInt64();
                        this.hasTimestampMsec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.starRating = input.readInt32();
                        this.hasStarRating = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.comment = input.readString();
                        this.hasComment = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.commentId = input.readString();
                        this.hasCommentId = true;
                        continue;
                    case 154:
                        this.deviceName = input.readString();
                        this.hasDeviceName = true;
                        continue;
                    case 234:
                        this.replyText = input.readString();
                        this.hasReplyText = true;
                        continue;
                    case 240:
                        this.replyTimestampMsec = input.readInt64();
                        this.hasReplyTimestampMsec = true;
                        continue;
                    case 250:
                        if (this.oBSOLETEPlusProfile == null) {
                            this.oBSOLETEPlusProfile = new OBSOLETE_PlusProfile();
                        }
                        input.readMessage(this.oBSOLETEPlusProfile);
                        continue;
                    case 266:
                        if (this.author == null) {
                            this.author = new DocV2();
                        }
                        input.readMessage(this.author);
                        continue;
                    case 274:
                        if (this.sentiment == null) {
                            this.sentiment = new Image();
                        }
                        input.readMessage(this.sentiment);
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

    public static final class SeriesAntenna extends MessageNano {
        public String colorThemeArgb;
        public String episodeSubtitle;
        public String episodeTitle;
        public boolean hasColorThemeArgb;
        public boolean hasEpisodeSubtitle;
        public boolean hasEpisodeTitle;
        public boolean hasSeriesSubtitle;
        public boolean hasSeriesTitle;
        public SectionMetadata sectionAlbums;
        public SectionMetadata sectionTracks;
        public String seriesSubtitle;
        public String seriesTitle;

        public SeriesAntenna() {
            clear();
        }

        public SeriesAntenna clear() {
            this.seriesTitle = "";
            this.hasSeriesTitle = false;
            this.seriesSubtitle = "";
            this.hasSeriesSubtitle = false;
            this.episodeTitle = "";
            this.hasEpisodeTitle = false;
            this.episodeSubtitle = "";
            this.hasEpisodeSubtitle = false;
            this.colorThemeArgb = "";
            this.hasColorThemeArgb = false;
            this.sectionTracks = null;
            this.sectionAlbums = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSeriesTitle || !this.seriesTitle.equals("")) {
                output.writeString(1, this.seriesTitle);
            }
            if (this.hasSeriesSubtitle || !this.seriesSubtitle.equals("")) {
                output.writeString(2, this.seriesSubtitle);
            }
            if (this.hasEpisodeTitle || !this.episodeTitle.equals("")) {
                output.writeString(3, this.episodeTitle);
            }
            if (this.hasEpisodeSubtitle || !this.episodeSubtitle.equals("")) {
                output.writeString(4, this.episodeSubtitle);
            }
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                output.writeString(5, this.colorThemeArgb);
            }
            if (this.sectionTracks != null) {
                output.writeMessage(6, this.sectionTracks);
            }
            if (this.sectionAlbums != null) {
                output.writeMessage(7, this.sectionAlbums);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSeriesTitle || !this.seriesTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.seriesTitle);
            }
            if (this.hasSeriesSubtitle || !this.seriesSubtitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.seriesSubtitle);
            }
            if (this.hasEpisodeTitle || !this.episodeTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.episodeTitle);
            }
            if (this.hasEpisodeSubtitle || !this.episodeSubtitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.episodeSubtitle);
            }
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.colorThemeArgb);
            }
            if (this.sectionTracks != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.sectionTracks);
            }
            if (this.sectionAlbums != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.sectionAlbums);
            }
            return size;
        }

        public SeriesAntenna mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.seriesTitle = input.readString();
                        this.hasSeriesTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.seriesSubtitle = input.readString();
                        this.hasSeriesSubtitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.episodeTitle = input.readString();
                        this.hasEpisodeTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.episodeSubtitle = input.readString();
                        this.hasEpisodeSubtitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.colorThemeArgb = input.readString();
                        this.hasColorThemeArgb = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.sectionTracks == null) {
                            this.sectionTracks = new SectionMetadata();
                        }
                        input.readMessage(this.sectionTracks);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.sectionAlbums == null) {
                            this.sectionAlbums = new SectionMetadata();
                        }
                        input.readMessage(this.sectionAlbums);
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

    public static final class SingleCardContainer extends MessageNano {
        public SingleCardContainer() {
            clear();
        }

        public SingleCardContainer clear() {
            this.cachedSize = -1;
            return this;
        }

        public SingleCardContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class Snow extends MessageNano {
        public String clickUrl;
        public boolean hasClickUrl;
        public boolean hasSnowBadgeText;
        public boolean hasSnowText;
        public String snowBadgeText;
        public String snowText;

        public Snow() {
            clear();
        }

        public Snow clear() {
            this.clickUrl = "";
            this.hasClickUrl = false;
            this.snowText = "";
            this.hasSnowText = false;
            this.snowBadgeText = "";
            this.hasSnowBadgeText = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasClickUrl || !this.clickUrl.equals("")) {
                output.writeString(1, this.clickUrl);
            }
            if (this.hasSnowText || !this.snowText.equals("")) {
                output.writeString(2, this.snowText);
            }
            if (this.hasSnowBadgeText || !this.snowBadgeText.equals("")) {
                output.writeString(3, this.snowBadgeText);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasClickUrl || !this.clickUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.clickUrl);
            }
            if (this.hasSnowText || !this.snowText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.snowText);
            }
            if (this.hasSnowBadgeText || !this.snowBadgeText.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.snowBadgeText);
            }
            return size;
        }

        public Snow mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.clickUrl = input.readString();
                        this.hasClickUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.snowText = input.readString();
                        this.hasSnowText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.snowBadgeText = input.readString();
                        this.hasSnowBadgeText = true;
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

    public static final class SuggestionReasons extends MessageNano {
        public Dismissal neutralDismissal;
        public Dismissal positiveDismissal;
        public Reason[] reason;

        public SuggestionReasons() {
            clear();
        }

        public SuggestionReasons clear() {
            this.neutralDismissal = null;
            this.positiveDismissal = null;
            this.reason = Reason.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.reason != null && this.reason.length > 0) {
                for (Reason element : this.reason) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.neutralDismissal != null) {
                output.writeMessage(4, this.neutralDismissal);
            }
            if (this.positiveDismissal != null) {
                output.writeMessage(5, this.positiveDismissal);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.reason != null && this.reason.length > 0) {
                for (Reason element : this.reason) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.neutralDismissal != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.neutralDismissal);
            }
            if (this.positiveDismissal != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.positiveDismissal);
            }
            return size;
        }

        public SuggestionReasons mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.reason == null) {
                            i = 0;
                        } else {
                            i = this.reason.length;
                        }
                        Reason[] newArray = new Reason[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.reason, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Reason();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Reason();
                        input.readMessage(newArray[i]);
                        this.reason = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.neutralDismissal == null) {
                            this.neutralDismissal = new Dismissal();
                        }
                        input.readMessage(this.neutralDismissal);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.positiveDismissal == null) {
                            this.positiveDismissal = new Dismissal();
                        }
                        input.readMessage(this.positiveDismissal);
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

    public static final class Template extends MessageNano {
        public ActionBanner actionBanner;
        public AddToCirclesContainer addToCirclesContainer;
        public ContainerWithBanner containerWithBanner;
        public DealOfTheDay dealOfTheDay;
        public EditorialSeriesContainer editorialSeriesContainer;
        public EmptyContainer emptyContainer;
        public MoreByCreatorContainer moreByCreatorContainer;
        public MyCirclesContainer myCirclesContainer;
        public NextBanner nextBanner;
        public PurchaseHistoryContainer purchaseHistoryContainer;
        public RateAndSuggestContainer rateAndSuggestContainer;
        public RateContainer rateContainer;
        public RecommendationsContainer recommendationsContainer;
        public RecommendationsContainerWithHeader recommendationsContainerWithHeader;
        public SeriesAntenna seriesAntenna;
        public SingleCardContainer singleCardContainer;
        public Snow snow;
        public TileTemplate tileDetailsReflectedGraphic2X2;
        public TileTemplate tileFourBlock4X2;
        public TileTemplate tileGraphic2X1;
        public TileTemplate tileGraphic4X2;
        public TileTemplate tileGraphicColoredTitle2X1;
        public TileTemplate tileGraphicColoredTitle4X2;
        public TileTemplate tileGraphicUpperLeftTitle2X1;
        public TrustedSourceContainer trustedSourceContainer;
        public WarmWelcome warmWelcome;

        public Template() {
            clear();
        }

        public Template clear() {
            this.seriesAntenna = null;
            this.tileGraphic2X1 = null;
            this.tileGraphic4X2 = null;
            this.tileGraphicColoredTitle2X1 = null;
            this.tileGraphicUpperLeftTitle2X1 = null;
            this.tileDetailsReflectedGraphic2X2 = null;
            this.tileFourBlock4X2 = null;
            this.tileGraphicColoredTitle4X2 = null;
            this.containerWithBanner = null;
            this.dealOfTheDay = null;
            this.editorialSeriesContainer = null;
            this.recommendationsContainer = null;
            this.recommendationsContainerWithHeader = null;
            this.nextBanner = null;
            this.rateContainer = null;
            this.rateAndSuggestContainer = null;
            this.addToCirclesContainer = null;
            this.myCirclesContainer = null;
            this.trustedSourceContainer = null;
            this.actionBanner = null;
            this.warmWelcome = null;
            this.moreByCreatorContainer = null;
            this.emptyContainer = null;
            this.singleCardContainer = null;
            this.purchaseHistoryContainer = null;
            this.snow = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.seriesAntenna != null) {
                output.writeMessage(1, this.seriesAntenna);
            }
            if (this.tileGraphic2X1 != null) {
                output.writeMessage(2, this.tileGraphic2X1);
            }
            if (this.tileGraphic4X2 != null) {
                output.writeMessage(3, this.tileGraphic4X2);
            }
            if (this.tileGraphicColoredTitle2X1 != null) {
                output.writeMessage(4, this.tileGraphicColoredTitle2X1);
            }
            if (this.tileGraphicUpperLeftTitle2X1 != null) {
                output.writeMessage(5, this.tileGraphicUpperLeftTitle2X1);
            }
            if (this.tileDetailsReflectedGraphic2X2 != null) {
                output.writeMessage(6, this.tileDetailsReflectedGraphic2X2);
            }
            if (this.tileFourBlock4X2 != null) {
                output.writeMessage(7, this.tileFourBlock4X2);
            }
            if (this.containerWithBanner != null) {
                output.writeMessage(8, this.containerWithBanner);
            }
            if (this.dealOfTheDay != null) {
                output.writeMessage(9, this.dealOfTheDay);
            }
            if (this.tileGraphicColoredTitle4X2 != null) {
                output.writeMessage(10, this.tileGraphicColoredTitle4X2);
            }
            if (this.editorialSeriesContainer != null) {
                output.writeMessage(11, this.editorialSeriesContainer);
            }
            if (this.recommendationsContainer != null) {
                output.writeMessage(12, this.recommendationsContainer);
            }
            if (this.nextBanner != null) {
                output.writeMessage(13, this.nextBanner);
            }
            if (this.rateContainer != null) {
                output.writeMessage(14, this.rateContainer);
            }
            if (this.addToCirclesContainer != null) {
                output.writeMessage(15, this.addToCirclesContainer);
            }
            if (this.trustedSourceContainer != null) {
                output.writeMessage(16, this.trustedSourceContainer);
            }
            if (this.rateAndSuggestContainer != null) {
                output.writeMessage(17, this.rateAndSuggestContainer);
            }
            if (this.actionBanner != null) {
                output.writeMessage(18, this.actionBanner);
            }
            if (this.warmWelcome != null) {
                output.writeMessage(19, this.warmWelcome);
            }
            if (this.recommendationsContainerWithHeader != null) {
                output.writeMessage(20, this.recommendationsContainerWithHeader);
            }
            if (this.emptyContainer != null) {
                output.writeMessage(21, this.emptyContainer);
            }
            if (this.myCirclesContainer != null) {
                output.writeMessage(22, this.myCirclesContainer);
            }
            if (this.singleCardContainer != null) {
                output.writeMessage(23, this.singleCardContainer);
            }
            if (this.moreByCreatorContainer != null) {
                output.writeMessage(24, this.moreByCreatorContainer);
            }
            if (this.purchaseHistoryContainer != null) {
                output.writeMessage(25, this.purchaseHistoryContainer);
            }
            if (this.snow != null) {
                output.writeMessage(26, this.snow);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.seriesAntenna != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.seriesAntenna);
            }
            if (this.tileGraphic2X1 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.tileGraphic2X1);
            }
            if (this.tileGraphic4X2 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.tileGraphic4X2);
            }
            if (this.tileGraphicColoredTitle2X1 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.tileGraphicColoredTitle2X1);
            }
            if (this.tileGraphicUpperLeftTitle2X1 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.tileGraphicUpperLeftTitle2X1);
            }
            if (this.tileDetailsReflectedGraphic2X2 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.tileDetailsReflectedGraphic2X2);
            }
            if (this.tileFourBlock4X2 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.tileFourBlock4X2);
            }
            if (this.containerWithBanner != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.containerWithBanner);
            }
            if (this.dealOfTheDay != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.dealOfTheDay);
            }
            if (this.tileGraphicColoredTitle4X2 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.tileGraphicColoredTitle4X2);
            }
            if (this.editorialSeriesContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.editorialSeriesContainer);
            }
            if (this.recommendationsContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.recommendationsContainer);
            }
            if (this.nextBanner != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(13, this.nextBanner);
            }
            if (this.rateContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.rateContainer);
            }
            if (this.addToCirclesContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(15, this.addToCirclesContainer);
            }
            if (this.trustedSourceContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(16, this.trustedSourceContainer);
            }
            if (this.rateAndSuggestContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(17, this.rateAndSuggestContainer);
            }
            if (this.actionBanner != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(18, this.actionBanner);
            }
            if (this.warmWelcome != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(19, this.warmWelcome);
            }
            if (this.recommendationsContainerWithHeader != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(20, this.recommendationsContainerWithHeader);
            }
            if (this.emptyContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(21, this.emptyContainer);
            }
            if (this.myCirclesContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(22, this.myCirclesContainer);
            }
            if (this.singleCardContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(23, this.singleCardContainer);
            }
            if (this.moreByCreatorContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(24, this.moreByCreatorContainer);
            }
            if (this.purchaseHistoryContainer != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(25, this.purchaseHistoryContainer);
            }
            if (this.snow != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(26, this.snow);
            }
            return size;
        }

        public Template mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.seriesAntenna == null) {
                            this.seriesAntenna = new SeriesAntenna();
                        }
                        input.readMessage(this.seriesAntenna);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.tileGraphic2X1 == null) {
                            this.tileGraphic2X1 = new TileTemplate();
                        }
                        input.readMessage(this.tileGraphic2X1);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.tileGraphic4X2 == null) {
                            this.tileGraphic4X2 = new TileTemplate();
                        }
                        input.readMessage(this.tileGraphic4X2);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.tileGraphicColoredTitle2X1 == null) {
                            this.tileGraphicColoredTitle2X1 = new TileTemplate();
                        }
                        input.readMessage(this.tileGraphicColoredTitle2X1);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.tileGraphicUpperLeftTitle2X1 == null) {
                            this.tileGraphicUpperLeftTitle2X1 = new TileTemplate();
                        }
                        input.readMessage(this.tileGraphicUpperLeftTitle2X1);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.tileDetailsReflectedGraphic2X2 == null) {
                            this.tileDetailsReflectedGraphic2X2 = new TileTemplate();
                        }
                        input.readMessage(this.tileDetailsReflectedGraphic2X2);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.tileFourBlock4X2 == null) {
                            this.tileFourBlock4X2 = new TileTemplate();
                        }
                        input.readMessage(this.tileFourBlock4X2);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.containerWithBanner == null) {
                            this.containerWithBanner = new ContainerWithBanner();
                        }
                        input.readMessage(this.containerWithBanner);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.dealOfTheDay == null) {
                            this.dealOfTheDay = new DealOfTheDay();
                        }
                        input.readMessage(this.dealOfTheDay);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.tileGraphicColoredTitle4X2 == null) {
                            this.tileGraphicColoredTitle4X2 = new TileTemplate();
                        }
                        input.readMessage(this.tileGraphicColoredTitle4X2);
                        continue;
                    case 90:
                        if (this.editorialSeriesContainer == null) {
                            this.editorialSeriesContainer = new EditorialSeriesContainer();
                        }
                        input.readMessage(this.editorialSeriesContainer);
                        continue;
                    case 98:
                        if (this.recommendationsContainer == null) {
                            this.recommendationsContainer = new RecommendationsContainer();
                        }
                        input.readMessage(this.recommendationsContainer);
                        continue;
                    case 106:
                        if (this.nextBanner == null) {
                            this.nextBanner = new NextBanner();
                        }
                        input.readMessage(this.nextBanner);
                        continue;
                    case 114:
                        if (this.rateContainer == null) {
                            this.rateContainer = new RateContainer();
                        }
                        input.readMessage(this.rateContainer);
                        continue;
                    case 122:
                        if (this.addToCirclesContainer == null) {
                            this.addToCirclesContainer = new AddToCirclesContainer();
                        }
                        input.readMessage(this.addToCirclesContainer);
                        continue;
                    case 130:
                        if (this.trustedSourceContainer == null) {
                            this.trustedSourceContainer = new TrustedSourceContainer();
                        }
                        input.readMessage(this.trustedSourceContainer);
                        continue;
                    case 138:
                        if (this.rateAndSuggestContainer == null) {
                            this.rateAndSuggestContainer = new RateAndSuggestContainer();
                        }
                        input.readMessage(this.rateAndSuggestContainer);
                        continue;
                    case 146:
                        if (this.actionBanner == null) {
                            this.actionBanner = new ActionBanner();
                        }
                        input.readMessage(this.actionBanner);
                        continue;
                    case 154:
                        if (this.warmWelcome == null) {
                            this.warmWelcome = new WarmWelcome();
                        }
                        input.readMessage(this.warmWelcome);
                        continue;
                    case 162:
                        if (this.recommendationsContainerWithHeader == null) {
                            this.recommendationsContainerWithHeader = new RecommendationsContainerWithHeader();
                        }
                        input.readMessage(this.recommendationsContainerWithHeader);
                        continue;
                    case 170:
                        if (this.emptyContainer == null) {
                            this.emptyContainer = new EmptyContainer();
                        }
                        input.readMessage(this.emptyContainer);
                        continue;
                    case 178:
                        if (this.myCirclesContainer == null) {
                            this.myCirclesContainer = new MyCirclesContainer();
                        }
                        input.readMessage(this.myCirclesContainer);
                        continue;
                    case 186:
                        if (this.singleCardContainer == null) {
                            this.singleCardContainer = new SingleCardContainer();
                        }
                        input.readMessage(this.singleCardContainer);
                        continue;
                    case 194:
                        if (this.moreByCreatorContainer == null) {
                            this.moreByCreatorContainer = new MoreByCreatorContainer();
                        }
                        input.readMessage(this.moreByCreatorContainer);
                        continue;
                    case 202:
                        if (this.purchaseHistoryContainer == null) {
                            this.purchaseHistoryContainer = new PurchaseHistoryContainer();
                        }
                        input.readMessage(this.purchaseHistoryContainer);
                        continue;
                    case 210:
                        if (this.snow == null) {
                            this.snow = new Snow();
                        }
                        input.readMessage(this.snow);
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

    public static final class TileTemplate extends MessageNano {
        public String colorTextArgb;
        public String colorThemeArgb;
        public boolean hasColorTextArgb;
        public boolean hasColorThemeArgb;

        public TileTemplate() {
            clear();
        }

        public TileTemplate clear() {
            this.colorThemeArgb = "";
            this.hasColorThemeArgb = false;
            this.colorTextArgb = "";
            this.hasColorTextArgb = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                output.writeString(1, this.colorThemeArgb);
            }
            if (this.hasColorTextArgb || !this.colorTextArgb.equals("")) {
                output.writeString(2, this.colorTextArgb);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasColorThemeArgb || !this.colorThemeArgb.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.colorThemeArgb);
            }
            if (this.hasColorTextArgb || !this.colorTextArgb.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.colorTextArgb);
            }
            return size;
        }

        public TileTemplate mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.colorThemeArgb = input.readString();
                        this.hasColorThemeArgb = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.colorTextArgb = input.readString();
                        this.hasColorTextArgb = true;
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

    public static final class TrustedSourceContainer extends MessageNano {
        public DocV2 source;

        public TrustedSourceContainer() {
            clear();
        }

        public TrustedSourceContainer clear() {
            this.source = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.source != null) {
                output.writeMessage(1, this.source);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.source != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.source);
            }
            return size;
        }

        public TrustedSourceContainer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.source == null) {
                            this.source = new DocV2();
                        }
                        input.readMessage(this.source);
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

    public static final class VideoSnippet extends MessageNano {
        private static volatile VideoSnippet[] _emptyArray;
        public String description;
        public boolean hasDescription;
        public boolean hasTitle;
        public Image[] image;
        public String title;

        public static VideoSnippet[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new VideoSnippet[0];
                    }
                }
            }
            return _emptyArray;
        }

        public VideoSnippet() {
            clear();
        }

        public VideoSnippet clear() {
            this.image = Image.emptyArray();
            this.title = "";
            this.hasTitle = false;
            this.description = "";
            this.hasDescription = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(2, this.title);
            }
            if (this.hasDescription || !this.description.equals("")) {
                output.writeString(3, this.description);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.title);
            }
            if (this.hasDescription || !this.description.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.description);
            }
            return size;
        }

        public VideoSnippet mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
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
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
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

    public static final class VoucherInfo extends MessageNano {
        private static volatile VoucherInfo[] _emptyArray;
        public DocV2 doc;
        public Offer[] offer;

        public static VoucherInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new VoucherInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public VoucherInfo() {
            clear();
        }

        public VoucherInfo clear() {
            this.doc = null;
            this.offer = Offer.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.doc != null) {
                output.writeMessage(1, this.doc);
            }
            if (this.offer != null && this.offer.length > 0) {
                for (Offer element : this.offer) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.doc != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.doc);
            }
            if (this.offer != null && this.offer.length > 0) {
                for (Offer element : this.offer) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public VoucherInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.offer == null) {
                            i = 0;
                        } else {
                            i = this.offer.length;
                        }
                        Offer[] newArray = new Offer[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.offer, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Offer();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Offer();
                        input.readMessage(newArray[i]);
                        this.offer = newArray;
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

    public static final class WarmWelcome extends MessageNano {
        public CallToAction[] action;

        public WarmWelcome() {
            clear();
        }

        public WarmWelcome clear() {
            this.action = CallToAction.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.action != null && this.action.length > 0) {
                for (CallToAction element : this.action) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.action != null && this.action.length > 0) {
                for (CallToAction element : this.action) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public WarmWelcome mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.action == null) {
                            i = 0;
                        } else {
                            i = this.action.length;
                        }
                        CallToAction[] newArray = new CallToAction[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.action, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new CallToAction();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new CallToAction();
                        input.readMessage(newArray[i]);
                        this.action = newArray;
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
