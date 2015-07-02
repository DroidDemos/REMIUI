package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface SearchSuggest {

    public static final class NavSuggestion extends MessageNano {
        public String description;
        public String docId;
        public boolean hasDescription;
        public boolean hasDocId;
        public boolean hasImageBlob;
        public Image image;
        public byte[] imageBlob;

        public NavSuggestion() {
            clear();
        }

        public NavSuggestion clear() {
            this.docId = "";
            this.hasDocId = false;
            this.description = "";
            this.hasDescription = false;
            this.imageBlob = WireFormatNano.EMPTY_BYTES;
            this.hasImageBlob = false;
            this.image = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDocId || !this.docId.equals("")) {
                output.writeString(1, this.docId);
            }
            if (this.hasImageBlob || !Arrays.equals(this.imageBlob, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.imageBlob);
            }
            if (this.image != null) {
                output.writeMessage(3, this.image);
            }
            if (this.hasDescription || !this.description.equals("")) {
                output.writeString(4, this.description);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDocId || !this.docId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.docId);
            }
            if (this.hasImageBlob || !Arrays.equals(this.imageBlob, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.imageBlob);
            }
            if (this.image != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.image);
            }
            if (this.hasDescription || !this.description.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.description);
            }
            return size;
        }

        public NavSuggestion mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.docId = input.readString();
                        this.hasDocId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.imageBlob = input.readBytes();
                        this.hasImageBlob = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.image == null) {
                            this.image = new Image();
                        }
                        input.readMessage(this.image);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
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

    public static final class SearchSuggestResponse extends MessageNano {
        public boolean hasServerLogsCookie;
        public byte[] serverLogsCookie;
        public Suggestion[] suggestion;

        public SearchSuggestResponse() {
            clear();
        }

        public SearchSuggestResponse clear() {
            this.suggestion = Suggestion.emptyArray();
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.suggestion != null && this.suggestion.length > 0) {
                for (Suggestion element : this.suggestion) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.serverLogsCookie);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.suggestion != null && this.suggestion.length > 0) {
                for (Suggestion element : this.suggestion) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                return size + CodedOutputByteBufferNano.computeBytesSize(2, this.serverLogsCookie);
            }
            return size;
        }

        public SearchSuggestResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.suggestion == null) {
                            i = 0;
                        } else {
                            i = this.suggestion.length;
                        }
                        Suggestion[] newArray = new Suggestion[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.suggestion, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Suggestion();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Suggestion();
                        input.readMessage(newArray[i]);
                        this.suggestion = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
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

    public static final class Suggestion extends MessageNano {
        private static volatile Suggestion[] _emptyArray;
        public String displayText;
        public DocV2 document;
        public boolean hasDisplayText;
        public boolean hasServerLogsCookie;
        public boolean hasSuggestedQuery;
        public boolean hasType;
        public Image image;
        public Link link;
        public NavSuggestion navSuggestion;
        public byte[] serverLogsCookie;
        public String suggestedQuery;
        public int type;

        public static Suggestion[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Suggestion[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Suggestion() {
            clear();
        }

        public Suggestion clear() {
            this.type = 1;
            this.hasType = false;
            this.displayText = "";
            this.hasDisplayText = false;
            this.image = null;
            this.link = null;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.document = null;
            this.suggestedQuery = "";
            this.hasSuggestedQuery = false;
            this.navSuggestion = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 1 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            if (this.hasSuggestedQuery || !this.suggestedQuery.equals("")) {
                output.writeString(2, this.suggestedQuery);
            }
            if (this.navSuggestion != null) {
                output.writeMessage(3, this.navSuggestion);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.serverLogsCookie);
            }
            if (this.image != null) {
                output.writeMessage(5, this.image);
            }
            if (this.hasDisplayText || !this.displayText.equals("")) {
                output.writeString(6, this.displayText);
            }
            if (this.link != null) {
                output.writeMessage(7, this.link);
            }
            if (this.document != null) {
                output.writeMessage(8, this.document);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 1 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.hasSuggestedQuery || !this.suggestedQuery.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.suggestedQuery);
            }
            if (this.navSuggestion != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.navSuggestion);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(4, this.serverLogsCookie);
            }
            if (this.image != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.image);
            }
            if (this.hasDisplayText || !this.displayText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.displayText);
            }
            if (this.link != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.link);
            }
            if (this.document != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(8, this.document);
            }
            return size;
        }

        public Suggestion mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.suggestedQuery = input.readString();
                        this.hasSuggestedQuery = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.navSuggestion == null) {
                            this.navSuggestion = new NavSuggestion();
                        }
                        input.readMessage(this.navSuggestion);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.image == null) {
                            this.image = new Image();
                        }
                        input.readMessage(this.image);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.displayText = input.readString();
                        this.hasDisplayText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.link == null) {
                            this.link = new Link();
                        }
                        input.readMessage(this.link);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.document == null) {
                            this.document = new DocV2();
                        }
                        input.readMessage(this.document);
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
