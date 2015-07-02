package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface Containers {

    public static final class ContainerMetadata extends MessageNano {
        public String analyticsCookie;
        public String browseUrl;
        public ContainerView[] containerView;
        public long estimatedResults;
        public boolean hasAnalyticsCookie;
        public boolean hasBrowseUrl;
        public boolean hasEstimatedResults;
        public boolean hasNextPageUrl;
        public boolean hasOrdered;
        public boolean hasRelevance;
        public String nextPageUrl;
        public boolean ordered;
        public double relevance;

        public ContainerMetadata() {
            clear();
        }

        public ContainerMetadata clear() {
            this.browseUrl = "";
            this.hasBrowseUrl = false;
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
            this.containerView = ContainerView.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasBrowseUrl || !this.browseUrl.equals("")) {
                output.writeString(1, this.browseUrl);
            }
            if (this.hasNextPageUrl || !this.nextPageUrl.equals("")) {
                output.writeString(2, this.nextPageUrl);
            }
            if (this.hasRelevance || Double.doubleToLongBits(this.relevance) != Double.doubleToLongBits(0.0d)) {
                output.writeDouble(3, this.relevance);
            }
            if (this.hasEstimatedResults || this.estimatedResults != 0) {
                output.writeInt64(4, this.estimatedResults);
            }
            if (this.hasAnalyticsCookie || !this.analyticsCookie.equals("")) {
                output.writeString(5, this.analyticsCookie);
            }
            if (this.hasOrdered || this.ordered) {
                output.writeBool(6, this.ordered);
            }
            if (this.containerView != null && this.containerView.length > 0) {
                for (ContainerView element : this.containerView) {
                    if (element != null) {
                        output.writeMessage(7, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasBrowseUrl || !this.browseUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.browseUrl);
            }
            if (this.hasNextPageUrl || !this.nextPageUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.nextPageUrl);
            }
            if (this.hasRelevance || Double.doubleToLongBits(this.relevance) != Double.doubleToLongBits(0.0d)) {
                size += CodedOutputByteBufferNano.computeDoubleSize(3, this.relevance);
            }
            if (this.hasEstimatedResults || this.estimatedResults != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.estimatedResults);
            }
            if (this.hasAnalyticsCookie || !this.analyticsCookie.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.analyticsCookie);
            }
            if (this.hasOrdered || this.ordered) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.ordered);
            }
            if (this.containerView != null && this.containerView.length > 0) {
                for (ContainerView element : this.containerView) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(7, element);
                    }
                }
            }
            return size;
        }

        public ContainerMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.browseUrl = input.readString();
                        this.hasBrowseUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.nextPageUrl = input.readString();
                        this.hasNextPageUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                        this.relevance = input.readDouble();
                        this.hasRelevance = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.estimatedResults = input.readInt64();
                        this.hasEstimatedResults = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.analyticsCookie = input.readString();
                        this.hasAnalyticsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.ordered = input.readBool();
                        this.hasOrdered = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        if (this.containerView == null) {
                            i = 0;
                        } else {
                            i = this.containerView.length;
                        }
                        ContainerView[] newArray = new ContainerView[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.containerView, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ContainerView();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ContainerView();
                        input.readMessage(newArray[i]);
                        this.containerView = newArray;
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

    public static final class ContainerView extends MessageNano {
        private static volatile ContainerView[] _emptyArray;
        public boolean hasListUrl;
        public boolean hasSelected;
        public boolean hasServerLogsCookie;
        public boolean hasTitle;
        public String listUrl;
        public boolean selected;
        public byte[] serverLogsCookie;
        public String title;

        public static ContainerView[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ContainerView[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ContainerView() {
            clear();
        }

        public ContainerView clear() {
            this.selected = false;
            this.hasSelected = false;
            this.title = "";
            this.hasTitle = false;
            this.listUrl = "";
            this.hasListUrl = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSelected || this.selected) {
                output.writeBool(1, this.selected);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(2, this.title);
            }
            if (this.hasListUrl || !this.listUrl.equals("")) {
                output.writeString(3, this.listUrl);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.serverLogsCookie);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSelected || this.selected) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.selected);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.title);
            }
            if (this.hasListUrl || !this.listUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.listUrl);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                return size + CodedOutputByteBufferNano.computeBytesSize(4, this.serverLogsCookie);
            }
            return size;
        }

        public ContainerView mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.selected = input.readBool();
                        this.hasSelected = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.listUrl = input.readString();
                        this.hasListUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
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
