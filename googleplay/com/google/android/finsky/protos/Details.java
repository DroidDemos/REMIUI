package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.DocumentV1.DocV1;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.DocumentV2.Review;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface Details {

    public static final class BulkDetailsEntry extends MessageNano {
        private static volatile BulkDetailsEntry[] _emptyArray;
        public DocV2 doc;

        public static BulkDetailsEntry[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BulkDetailsEntry[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BulkDetailsEntry() {
            clear();
        }

        public BulkDetailsEntry clear() {
            this.doc = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.doc != null) {
                output.writeMessage(1, this.doc);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.doc != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.doc);
            }
            return size;
        }

        public BulkDetailsEntry mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static final class BulkDetailsRequest extends MessageNano {
        public String[] docid;
        public boolean hasIncludeChildDocs;
        public boolean hasIncludeDetails;
        public boolean hasIncludeSplitDetailsForAllApps;
        public boolean hasIncludeSplitDetailsForNewerVersions;
        public boolean hasSourcePackageName;
        public boolean includeChildDocs;
        public boolean includeDetails;
        public boolean includeSplitDetailsForAllApps;
        public boolean includeSplitDetailsForNewerVersions;
        public int[] installedVersionCode;
        public String sourcePackageName;

        public BulkDetailsRequest() {
            clear();
        }

        public BulkDetailsRequest clear() {
            this.docid = WireFormatNano.EMPTY_STRING_ARRAY;
            this.installedVersionCode = WireFormatNano.EMPTY_INT_ARRAY;
            this.includeChildDocs = true;
            this.hasIncludeChildDocs = false;
            this.includeDetails = false;
            this.hasIncludeDetails = false;
            this.sourcePackageName = "";
            this.hasSourcePackageName = false;
            this.includeSplitDetailsForAllApps = false;
            this.hasIncludeSplitDetailsForAllApps = false;
            this.includeSplitDetailsForNewerVersions = false;
            this.hasIncludeSplitDetailsForNewerVersions = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docid != null && this.docid.length > 0) {
                for (String element : this.docid) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.hasIncludeChildDocs || !this.includeChildDocs) {
                output.writeBool(2, this.includeChildDocs);
            }
            if (this.hasIncludeDetails || this.includeDetails) {
                output.writeBool(3, this.includeDetails);
            }
            if (this.hasSourcePackageName || !this.sourcePackageName.equals("")) {
                output.writeString(4, this.sourcePackageName);
            }
            if (this.hasIncludeSplitDetailsForAllApps || this.includeSplitDetailsForAllApps) {
                output.writeBool(5, this.includeSplitDetailsForAllApps);
            }
            if (this.hasIncludeSplitDetailsForNewerVersions || this.includeSplitDetailsForNewerVersions) {
                output.writeBool(6, this.includeSplitDetailsForNewerVersions);
            }
            if (this.installedVersionCode != null && this.installedVersionCode.length > 0) {
                for (int writeInt32 : this.installedVersionCode) {
                    output.writeInt32(7, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.docid != null && this.docid.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (String element : this.docid) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasIncludeChildDocs || !this.includeChildDocs) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.includeChildDocs);
            }
            if (this.hasIncludeDetails || this.includeDetails) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.includeDetails);
            }
            if (this.hasSourcePackageName || !this.sourcePackageName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.sourcePackageName);
            }
            if (this.hasIncludeSplitDetailsForAllApps || this.includeSplitDetailsForAllApps) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.includeSplitDetailsForAllApps);
            }
            if (this.hasIncludeSplitDetailsForNewerVersions || this.includeSplitDetailsForNewerVersions) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.includeSplitDetailsForNewerVersions);
            }
            if (this.installedVersionCode == null || this.installedVersionCode.length <= 0) {
                return size;
            }
            dataSize = 0;
            for (int element2 : this.installedVersionCode) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element2);
            }
            return (size + dataSize) + (this.installedVersionCode.length * 1);
        }

        public BulkDetailsRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        i = this.docid == null ? 0 : this.docid.length;
                        String[] newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.docid, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.docid = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.includeChildDocs = input.readBool();
                        this.hasIncludeChildDocs = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.includeDetails = input.readBool();
                        this.hasIncludeDetails = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.sourcePackageName = input.readString();
                        this.hasSourcePackageName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.includeSplitDetailsForAllApps = input.readBool();
                        this.hasIncludeSplitDetailsForAllApps = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.includeSplitDetailsForNewerVersions = input.readBool();
                        this.hasIncludeSplitDetailsForNewerVersions = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 56);
                        i = this.installedVersionCode == null ? 0 : this.installedVersionCode.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.installedVersionCode, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt32();
                        this.installedVersionCode = newArray;
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
                        i = this.installedVersionCode == null ? 0 : this.installedVersionCode.length;
                        newArray = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.installedVersionCode, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt32();
                            i++;
                        }
                        this.installedVersionCode = newArray;
                        input.popLimit(limit);
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

    public static final class BulkDetailsResponse extends MessageNano {
        public BulkDetailsEntry[] entry;

        public BulkDetailsResponse() {
            clear();
        }

        public BulkDetailsResponse clear() {
            this.entry = BulkDetailsEntry.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.entry != null && this.entry.length > 0) {
                for (BulkDetailsEntry element : this.entry) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.entry != null && this.entry.length > 0) {
                for (BulkDetailsEntry element : this.entry) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public BulkDetailsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.entry == null) {
                            i = 0;
                        } else {
                            i = this.entry.length;
                        }
                        BulkDetailsEntry[] newArray = new BulkDetailsEntry[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.entry, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new BulkDetailsEntry();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new BulkDetailsEntry();
                        input.readMessage(newArray[i]);
                        this.entry = newArray;
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

    public static final class DetailsResponse extends MessageNano {
        public String analyticsCookie;
        public DiscoveryBadge[] discoveryBadge;
        public DocV1 docV1;
        public DocV2 docV2;
        public String footerHtml;
        public boolean hasAnalyticsCookie;
        public boolean hasFooterHtml;
        public boolean hasServerLogsCookie;
        public byte[] serverLogsCookie;
        public Review userReview;

        public DetailsResponse() {
            clear();
        }

        public DetailsResponse clear() {
            this.docV1 = null;
            this.docV2 = null;
            this.analyticsCookie = "";
            this.hasAnalyticsCookie = false;
            this.userReview = null;
            this.footerHtml = "";
            this.hasFooterHtml = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.discoveryBadge = DiscoveryBadge.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docV1 != null) {
                output.writeMessage(1, this.docV1);
            }
            if (this.hasAnalyticsCookie || !this.analyticsCookie.equals("")) {
                output.writeString(2, this.analyticsCookie);
            }
            if (this.userReview != null) {
                output.writeMessage(3, this.userReview);
            }
            if (this.docV2 != null) {
                output.writeMessage(4, this.docV2);
            }
            if (this.hasFooterHtml || !this.footerHtml.equals("")) {
                output.writeString(5, this.footerHtml);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.serverLogsCookie);
            }
            if (this.discoveryBadge != null && this.discoveryBadge.length > 0) {
                for (DiscoveryBadge element : this.discoveryBadge) {
                    if (element != null) {
                        output.writeMessage(7, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.docV1 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.docV1);
            }
            if (this.hasAnalyticsCookie || !this.analyticsCookie.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.analyticsCookie);
            }
            if (this.userReview != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.userReview);
            }
            if (this.docV2 != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.docV2);
            }
            if (this.hasFooterHtml || !this.footerHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.footerHtml);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.serverLogsCookie);
            }
            if (this.discoveryBadge != null && this.discoveryBadge.length > 0) {
                for (DiscoveryBadge element : this.discoveryBadge) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element);
                    }
                }
            }
            return size;
        }

        public DetailsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.docV1 == null) {
                            this.docV1 = new DocV1();
                        }
                        input.readMessage(this.docV1);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.analyticsCookie = input.readString();
                        this.hasAnalyticsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.userReview == null) {
                            this.userReview = new Review();
                        }
                        input.readMessage(this.userReview);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.docV2 == null) {
                            this.docV2 = new DocV2();
                        }
                        input.readMessage(this.docV2);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.footerHtml = input.readString();
                        this.hasFooterHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.discoveryBadge == null) {
                            i = 0;
                        } else {
                            i = this.discoveryBadge.length;
                        }
                        DiscoveryBadge[] newArray = new DiscoveryBadge[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.discoveryBadge, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new DiscoveryBadge();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new DiscoveryBadge();
                        input.readMessage(newArray[i]);
                        this.discoveryBadge = newArray;
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

    public static final class DiscoveryBadge extends MessageNano {
        private static volatile DiscoveryBadge[] _emptyArray;
        public float aggregateRating;
        public int backgroundColor;
        public String contentDescription;
        public DiscoveryBadgeLink discoveryBadgeLink;
        public String downloadCount;
        public String downloadUnits;
        public boolean hasAggregateRating;
        public boolean hasBackgroundColor;
        public boolean hasContentDescription;
        public boolean hasDownloadCount;
        public boolean hasDownloadUnits;
        public boolean hasIsPlusOne;
        public boolean hasServerLogsCookie;
        public boolean hasTitle;
        public boolean hasUserStarRating;
        public Image image;
        public boolean isPlusOne;
        public PlayerBadge playerBadge;
        public byte[] serverLogsCookie;
        public String title;
        public int userStarRating;

        public static DiscoveryBadge[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DiscoveryBadge[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DiscoveryBadge() {
            clear();
        }

        public DiscoveryBadge clear() {
            this.title = "";
            this.hasTitle = false;
            this.contentDescription = "";
            this.hasContentDescription = false;
            this.image = null;
            this.backgroundColor = 0;
            this.hasBackgroundColor = false;
            this.discoveryBadgeLink = null;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.isPlusOne = false;
            this.hasIsPlusOne = false;
            this.aggregateRating = 0.0f;
            this.hasAggregateRating = false;
            this.userStarRating = 0;
            this.hasUserStarRating = false;
            this.downloadCount = "";
            this.hasDownloadCount = false;
            this.downloadUnits = "";
            this.hasDownloadUnits = false;
            this.playerBadge = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.image != null) {
                output.writeMessage(2, this.image);
            }
            if (this.hasBackgroundColor || this.backgroundColor != 0) {
                output.writeInt32(3, this.backgroundColor);
            }
            if (this.discoveryBadgeLink != null) {
                output.writeMessage(4, this.discoveryBadgeLink);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(5, this.serverLogsCookie);
            }
            if (this.hasIsPlusOne || this.isPlusOne) {
                output.writeBool(6, this.isPlusOne);
            }
            if (this.hasAggregateRating || Float.floatToIntBits(this.aggregateRating) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(7, this.aggregateRating);
            }
            if (this.hasUserStarRating || this.userStarRating != 0) {
                output.writeInt32(8, this.userStarRating);
            }
            if (this.hasDownloadCount || !this.downloadCount.equals("")) {
                output.writeString(9, this.downloadCount);
            }
            if (this.hasDownloadUnits || !this.downloadUnits.equals("")) {
                output.writeString(10, this.downloadUnits);
            }
            if (this.hasContentDescription || !this.contentDescription.equals("")) {
                output.writeString(11, this.contentDescription);
            }
            if (this.playerBadge != null) {
                output.writeMessage(12, this.playerBadge);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.image != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.image);
            }
            if (this.hasBackgroundColor || this.backgroundColor != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.backgroundColor);
            }
            if (this.discoveryBadgeLink != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.discoveryBadgeLink);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(5, this.serverLogsCookie);
            }
            if (this.hasIsPlusOne || this.isPlusOne) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.isPlusOne);
            }
            if (this.hasAggregateRating || Float.floatToIntBits(this.aggregateRating) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(7, this.aggregateRating);
            }
            if (this.hasUserStarRating || this.userStarRating != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.userStarRating);
            }
            if (this.hasDownloadCount || !this.downloadCount.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.downloadCount);
            }
            if (this.hasDownloadUnits || !this.downloadUnits.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.downloadUnits);
            }
            if (this.hasContentDescription || !this.contentDescription.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.contentDescription);
            }
            if (this.playerBadge != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(12, this.playerBadge);
            }
            return size;
        }

        public DiscoveryBadge mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        if (this.image == null) {
                            this.image = new Image();
                        }
                        input.readMessage(this.image);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.backgroundColor = input.readInt32();
                        this.hasBackgroundColor = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.discoveryBadgeLink == null) {
                            this.discoveryBadgeLink = new DiscoveryBadgeLink();
                        }
                        input.readMessage(this.discoveryBadgeLink);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.isPlusOne = input.readBool();
                        this.hasIsPlusOne = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_textColorSearchUrl /*61*/:
                        this.aggregateRating = input.readFloat();
                        this.hasAggregateRating = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.userStarRating = input.readInt32();
                        this.hasUserStarRating = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.downloadCount = input.readString();
                        this.hasDownloadCount = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.downloadUnits = input.readString();
                        this.hasDownloadUnits = true;
                        continue;
                    case 90:
                        this.contentDescription = input.readString();
                        this.hasContentDescription = true;
                        continue;
                    case 98:
                        if (this.playerBadge == null) {
                            this.playerBadge = new PlayerBadge();
                        }
                        input.readMessage(this.playerBadge);
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

    public static final class DiscoveryBadgeLink extends MessageNano {
        public String criticReviewsUrl;
        public boolean hasCriticReviewsUrl;
        public boolean hasUserReviewsUrl;
        public Link link;
        public String userReviewsUrl;

        public DiscoveryBadgeLink() {
            clear();
        }

        public DiscoveryBadgeLink clear() {
            this.link = null;
            this.userReviewsUrl = "";
            this.hasUserReviewsUrl = false;
            this.criticReviewsUrl = "";
            this.hasCriticReviewsUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.link != null) {
                output.writeMessage(1, this.link);
            }
            if (this.hasUserReviewsUrl || !this.userReviewsUrl.equals("")) {
                output.writeString(2, this.userReviewsUrl);
            }
            if (this.hasCriticReviewsUrl || !this.criticReviewsUrl.equals("")) {
                output.writeString(3, this.criticReviewsUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.link != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.link);
            }
            if (this.hasUserReviewsUrl || !this.userReviewsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.userReviewsUrl);
            }
            if (this.hasCriticReviewsUrl || !this.criticReviewsUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.criticReviewsUrl);
            }
            return size;
        }

        public DiscoveryBadgeLink mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.link == null) {
                            this.link = new Link();
                        }
                        input.readMessage(this.link);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.userReviewsUrl = input.readString();
                        this.hasUserReviewsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.criticReviewsUrl = input.readString();
                        this.hasCriticReviewsUrl = true;
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

    public static final class PlayerBadge extends MessageNano {
        public Image overlayIcon;

        public PlayerBadge() {
            clear();
        }

        public PlayerBadge clear() {
            this.overlayIcon = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.overlayIcon != null) {
                output.writeMessage(1, this.overlayIcon);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.overlayIcon != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.overlayIcon);
            }
            return size;
        }

        public PlayerBadge mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.overlayIcon == null) {
                            this.overlayIcon = new Image();
                        }
                        input.readMessage(this.overlayIcon);
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
