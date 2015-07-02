package com.google.android.finsky.protos;

import com.google.android.finsky.protos.DocList.Bucket;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface Search {

    public static final class RelatedSearch extends MessageNano {
        private static volatile RelatedSearch[] _emptyArray;
        public int backendId;
        public boolean current;
        public int docType;
        public boolean hasBackendId;
        public boolean hasCurrent;
        public boolean hasDocType;
        public boolean hasHeader;
        public boolean hasSearchUrl;
        public String header;
        public String searchUrl;

        public static RelatedSearch[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new RelatedSearch[0];
                    }
                }
            }
            return _emptyArray;
        }

        public RelatedSearch() {
            clear();
        }

        public RelatedSearch clear() {
            this.searchUrl = "";
            this.hasSearchUrl = false;
            this.header = "";
            this.hasHeader = false;
            this.backendId = 0;
            this.hasBackendId = false;
            this.docType = 1;
            this.hasDocType = false;
            this.current = false;
            this.hasCurrent = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSearchUrl || !this.searchUrl.equals("")) {
                output.writeString(1, this.searchUrl);
            }
            if (this.hasHeader || !this.header.equals("")) {
                output.writeString(2, this.header);
            }
            if (this.backendId != 0 || this.hasBackendId) {
                output.writeInt32(3, this.backendId);
            }
            if (this.docType != 1 || this.hasDocType) {
                output.writeInt32(4, this.docType);
            }
            if (this.hasCurrent || this.current) {
                output.writeBool(5, this.current);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSearchUrl || !this.searchUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.searchUrl);
            }
            if (this.hasHeader || !this.header.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.header);
            }
            if (this.backendId != 0 || this.hasBackendId) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.backendId);
            }
            if (this.docType != 1 || this.hasDocType) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.docType);
            }
            if (this.hasCurrent || this.current) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.current);
            }
            return size;
        }

        public RelatedSearch mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.searchUrl = input.readString();
                        this.hasSearchUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.header = input.readString();
                        this.hasHeader = true;
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
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
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
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.current = input.readBool();
                        this.hasCurrent = true;
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

    public static final class SearchResponse extends MessageNano {
        public boolean aggregateQuery;
        public Bucket[] bucket;
        public boolean containsSnow;
        public DocV2[] doc;
        public boolean fullPageReplaced;
        public boolean hasAggregateQuery;
        public boolean hasContainsSnow;
        public boolean hasFullPageReplaced;
        public boolean hasOriginalQuery;
        public boolean hasServerLogsCookie;
        public boolean hasSuggestedQuery;
        public String originalQuery;
        public RelatedSearch[] relatedSearch;
        public byte[] serverLogsCookie;
        public String suggestedQuery;

        public SearchResponse() {
            clear();
        }

        public SearchResponse clear() {
            this.originalQuery = "";
            this.hasOriginalQuery = false;
            this.suggestedQuery = "";
            this.hasSuggestedQuery = false;
            this.fullPageReplaced = false;
            this.hasFullPageReplaced = false;
            this.aggregateQuery = false;
            this.hasAggregateQuery = false;
            this.bucket = Bucket.emptyArray();
            this.doc = DocV2.emptyArray();
            this.relatedSearch = RelatedSearch.emptyArray();
            this.containsSnow = false;
            this.hasContainsSnow = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasOriginalQuery || !this.originalQuery.equals("")) {
                output.writeString(1, this.originalQuery);
            }
            if (this.hasSuggestedQuery || !this.suggestedQuery.equals("")) {
                output.writeString(2, this.suggestedQuery);
            }
            if (this.hasAggregateQuery || this.aggregateQuery) {
                output.writeBool(3, this.aggregateQuery);
            }
            if (this.bucket != null && this.bucket.length > 0) {
                for (Bucket element : this.bucket) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.doc != null && this.doc.length > 0) {
                for (DocV2 element2 : this.doc) {
                    if (element2 != null) {
                        output.writeMessage(5, element2);
                    }
                }
            }
            if (this.relatedSearch != null && this.relatedSearch.length > 0) {
                for (RelatedSearch element3 : this.relatedSearch) {
                    if (element3 != null) {
                        output.writeMessage(6, element3);
                    }
                }
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.serverLogsCookie);
            }
            if (this.hasFullPageReplaced || this.fullPageReplaced) {
                output.writeBool(8, this.fullPageReplaced);
            }
            if (this.hasContainsSnow || this.containsSnow) {
                output.writeBool(9, this.containsSnow);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasOriginalQuery || !this.originalQuery.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.originalQuery);
            }
            if (this.hasSuggestedQuery || !this.suggestedQuery.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.suggestedQuery);
            }
            if (this.hasAggregateQuery || this.aggregateQuery) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.aggregateQuery);
            }
            if (this.bucket != null && this.bucket.length > 0) {
                for (Bucket element : this.bucket) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.doc != null && this.doc.length > 0) {
                for (DocV2 element2 : this.doc) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element2);
                    }
                }
            }
            if (this.relatedSearch != null && this.relatedSearch.length > 0) {
                for (RelatedSearch element3 : this.relatedSearch) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(6, element3);
                    }
                }
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(7, this.serverLogsCookie);
            }
            if (this.hasFullPageReplaced || this.fullPageReplaced) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.fullPageReplaced);
            }
            if (this.hasContainsSnow || this.containsSnow) {
                return size + CodedOutputByteBufferNano.computeBoolSize(9, this.containsSnow);
            }
            return size;
        }

        public SearchResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.originalQuery = input.readString();
                        this.hasOriginalQuery = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.suggestedQuery = input.readString();
                        this.hasSuggestedQuery = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.aggregateQuery = input.readBool();
                        this.hasAggregateQuery = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.bucket == null) {
                            i = 0;
                        } else {
                            i = this.bucket.length;
                        }
                        Bucket[] newArray = new Bucket[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.bucket, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Bucket();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Bucket();
                        input.readMessage(newArray[i]);
                        this.bucket = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.doc == null) {
                            i = 0;
                        } else {
                            i = this.doc.length;
                        }
                        DocV2[] newArray2 = new DocV2[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.doc, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new DocV2();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new DocV2();
                        input.readMessage(newArray2[i]);
                        this.doc = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        if (this.relatedSearch == null) {
                            i = 0;
                        } else {
                            i = this.relatedSearch.length;
                        }
                        RelatedSearch[] newArray3 = new RelatedSearch[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.relatedSearch, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new RelatedSearch();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new RelatedSearch();
                        input.readMessage(newArray3[i]);
                        this.relatedSearch = newArray3;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.fullPageReplaced = input.readBool();
                        this.hasFullPageReplaced = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.containsSnow = input.readBool();
                        this.hasContainsSnow = true;
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
