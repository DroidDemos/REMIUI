package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Document {

    public static final class PlayDocument extends MessageNano {
        public String detailsUrl;
        public Docid docid;
        public String docidStr;
        public boolean hasDetailsUrl;
        public boolean hasDocidStr;
        public boolean hasSubtitle;
        public boolean hasTitle;
        public Image[] image;
        public String subtitle;
        public String title;

        public PlayDocument() {
            clear();
        }

        public PlayDocument clear() {
            this.docid = null;
            this.docidStr = "";
            this.hasDocidStr = false;
            this.title = "";
            this.hasTitle = false;
            this.subtitle = "";
            this.hasSubtitle = false;
            this.image = Image.emptyArray();
            this.detailsUrl = "";
            this.hasDetailsUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docid != null) {
                output.writeMessage(1, this.docid);
            }
            if (this.hasDocidStr || !this.docidStr.equals("")) {
                output.writeString(2, this.docidStr);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(3, this.title);
            }
            if (this.hasSubtitle || !this.subtitle.equals("")) {
                output.writeString(4, this.subtitle);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        output.writeMessage(5, element);
                    }
                }
            }
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                output.writeString(6, this.detailsUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.docid);
            }
            if (this.hasDocidStr || !this.docidStr.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.docidStr);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.title);
            }
            if (this.hasSubtitle || !this.subtitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.subtitle);
            }
            if (this.image != null && this.image.length > 0) {
                for (Image element : this.image) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element);
                    }
                }
            }
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(6, this.detailsUrl);
            }
            return size;
        }

        public PlayDocument mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
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
                        this.docidStr = input.readString();
                        this.hasDocidStr = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.subtitle = input.readString();
                        this.hasSubtitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
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
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.detailsUrl = input.readString();
                        this.hasDetailsUrl = true;
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
