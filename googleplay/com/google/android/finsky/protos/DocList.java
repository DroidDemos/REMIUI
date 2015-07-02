package com.google.android.finsky.protos;

import com.google.android.finsky.protos.DocumentV1.DocV1;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface DocList {

    public static final class Bucket extends MessageNano {
        private static volatile Bucket[] _emptyArray;
        public String analyticsCookie;
        public DocV1[] document;
        public long estimatedResults;
        public String fullContentsListUrl;
        public String fullContentsUrl;
        public boolean hasAnalyticsCookie;
        public boolean hasEstimatedResults;
        public boolean hasFullContentsListUrl;
        public boolean hasFullContentsUrl;
        public boolean hasIconUrl;
        public boolean hasMultiCorpus;
        public boolean hasNextPageUrl;
        public boolean hasOrdered;
        public boolean hasRelevance;
        public boolean hasTitle;
        public String iconUrl;
        public boolean multiCorpus;
        public String nextPageUrl;
        public boolean ordered;
        public double relevance;
        public String title;

        public static Bucket[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Bucket[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Bucket() {
            clear();
        }

        public Bucket clear() {
            this.document = DocV1.emptyArray();
            this.multiCorpus = false;
            this.hasMultiCorpus = false;
            this.title = "";
            this.hasTitle = false;
            this.iconUrl = "";
            this.hasIconUrl = false;
            this.fullContentsUrl = "";
            this.hasFullContentsUrl = false;
            this.fullContentsListUrl = "";
            this.hasFullContentsListUrl = false;
            this.nextPageUrl = "";
            this.hasNextPageUrl = false;
            this.relevance = 0.0d;
            this.hasRelevance = false;
            this.estimatedResults = 0;
            this.hasEstimatedResults = false;
            this.analyticsCookie = "";
            this.hasAnalyticsCookie = false;
            this.ordered = false;
            this.hasOrdered = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.document != null && this.document.length > 0) {
                for (DocV1 element : this.document) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasMultiCorpus || this.multiCorpus) {
                output.writeBool(2, this.multiCorpus);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(3, this.title);
            }
            if (this.hasIconUrl || !this.iconUrl.equals("")) {
                output.writeString(4, this.iconUrl);
            }
            if (this.hasFullContentsUrl || !this.fullContentsUrl.equals("")) {
                output.writeString(5, this.fullContentsUrl);
            }
            if (this.hasRelevance || Double.doubleToLongBits(this.relevance) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(6, this.relevance);
            }
            if (this.hasEstimatedResults || this.estimatedResults != 0) {
                output.writeInt64(7, this.estimatedResults);
            }
            if (this.hasAnalyticsCookie || !this.analyticsCookie.equals("")) {
                output.writeString(8, this.analyticsCookie);
            }
            if (this.hasFullContentsListUrl || !this.fullContentsListUrl.equals("")) {
                output.writeString(9, this.fullContentsListUrl);
            }
            if (this.hasNextPageUrl || !this.nextPageUrl.equals("")) {
                output.writeString(10, this.nextPageUrl);
            }
            if (this.hasOrdered || this.ordered) {
                output.writeBool(11, this.ordered);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.document != null && this.document.length > 0) {
                for (DocV1 element : this.document) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasMultiCorpus || this.multiCorpus) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.multiCorpus);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.title);
            }
            if (this.hasIconUrl || !this.iconUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.iconUrl);
            }
            if (this.hasFullContentsUrl || !this.fullContentsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.fullContentsUrl);
            }
            if (this.hasRelevance || Double.doubleToLongBits(this.relevance) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(6, this.relevance);
            }
            if (this.hasEstimatedResults || this.estimatedResults != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(7, this.estimatedResults);
            }
            if (this.hasAnalyticsCookie || !this.analyticsCookie.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.analyticsCookie);
            }
            if (this.hasFullContentsListUrl || !this.fullContentsListUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.fullContentsListUrl);
            }
            if (this.hasNextPageUrl || !this.nextPageUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.nextPageUrl);
            }
            if (this.hasOrdered || this.ordered) {
                return size + CodedOutputByteBufferNano.computeBoolSize(11, this.ordered);
            }
            return size;
        }

        public Bucket mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.document == null) {
                            i = 0;
                        } else {
                            i = this.document.length;
                        }
                        DocV1[] newArray = new DocV1[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.document, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new DocV1();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new DocV1();
                        input.readMessage(newArray[i]);
                        this.document = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.multiCorpus = input.readBool();
                        this.hasMultiCorpus = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.iconUrl = input.readString();
                        this.hasIconUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.fullContentsUrl = input.readString();
                        this.hasFullContentsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerVertical /*49*/:
                        this.relevance = input.readDouble();
                        this.hasRelevance = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.estimatedResults = input.readInt64();
                        this.hasEstimatedResults = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.analyticsCookie = input.readString();
                        this.hasAnalyticsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.fullContentsListUrl = input.readString();
                        this.hasFullContentsListUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.nextPageUrl = input.readString();
                        this.hasNextPageUrl = true;
                        continue;
                    case 88:
                        this.ordered = input.readBool();
                        this.hasOrdered = true;
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

    public static final class ListResponse extends MessageNano {
        public Bucket[] bucket;
        public DocV2[] doc;

        public ListResponse() {
            clear();
        }

        public ListResponse clear() {
            this.bucket = Bucket.emptyArray();
            this.doc = DocV2.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.bucket != null && this.bucket.length > 0) {
                for (Bucket element : this.bucket) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.doc != null && this.doc.length > 0) {
                for (DocV2 element2 : this.doc) {
                    if (element2 != null) {
                        output.writeMessage(2, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.bucket != null && this.bucket.length > 0) {
                for (Bucket element : this.bucket) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.doc != null && this.doc.length > 0) {
                for (DocV2 element2 : this.doc) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element2);
                    }
                }
            }
            return size;
        }

        public ListResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
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
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
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
