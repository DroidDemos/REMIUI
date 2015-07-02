package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface ResponseMessages {

    public static final class PreFetch extends MessageNano {
        private static volatile PreFetch[] _emptyArray;
        public String etag;
        public boolean hasEtag;
        public boolean hasResponse;
        public boolean hasSoftTtl;
        public boolean hasTtl;
        public boolean hasUrl;
        public byte[] response;
        public long softTtl;
        public long ttl;
        public String url;

        public static PreFetch[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PreFetch[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PreFetch() {
            clear();
        }

        public PreFetch clear() {
            this.url = "";
            this.hasUrl = false;
            this.response = WireFormatNano.EMPTY_BYTES;
            this.hasResponse = false;
            this.etag = "";
            this.hasEtag = false;
            this.ttl = 0;
            this.hasTtl = false;
            this.softTtl = 0;
            this.hasSoftTtl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUrl || !this.url.equals("")) {
                output.writeString(1, this.url);
            }
            if (this.hasResponse || !Arrays.equals(this.response, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.response);
            }
            if (this.hasEtag || !this.etag.equals("")) {
                output.writeString(3, this.etag);
            }
            if (this.hasTtl || this.ttl != 0) {
                output.writeInt64(4, this.ttl);
            }
            if (this.hasSoftTtl || this.softTtl != 0) {
                output.writeInt64(5, this.softTtl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUrl || !this.url.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
            }
            if (this.hasResponse || !Arrays.equals(this.response, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.response);
            }
            if (this.hasEtag || !this.etag.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.etag);
            }
            if (this.hasTtl || this.ttl != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.ttl);
            }
            if (this.hasSoftTtl || this.softTtl != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(5, this.softTtl);
            }
            return size;
        }

        public PreFetch mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.response = input.readBytes();
                        this.hasResponse = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.etag = input.readString();
                        this.hasEtag = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.ttl = input.readInt64();
                        this.hasTtl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.softTtl = input.readInt64();
                        this.hasSoftTtl = true;
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

    public static final class ServerCommands extends MessageNano {
        public boolean clearCache;
        public String displayErrorMessage;
        public boolean hasClearCache;
        public boolean hasDisplayErrorMessage;
        public boolean hasLogErrorStacktrace;
        public String logErrorStacktrace;

        public ServerCommands() {
            clear();
        }

        public ServerCommands clear() {
            this.clearCache = false;
            this.hasClearCache = false;
            this.displayErrorMessage = "";
            this.hasDisplayErrorMessage = false;
            this.logErrorStacktrace = "";
            this.hasLogErrorStacktrace = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasClearCache || this.clearCache) {
                output.writeBool(1, this.clearCache);
            }
            if (this.hasDisplayErrorMessage || !this.displayErrorMessage.equals("")) {
                output.writeString(2, this.displayErrorMessage);
            }
            if (this.hasLogErrorStacktrace || !this.logErrorStacktrace.equals("")) {
                output.writeString(3, this.logErrorStacktrace);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasClearCache || this.clearCache) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.clearCache);
            }
            if (this.hasDisplayErrorMessage || !this.displayErrorMessage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.displayErrorMessage);
            }
            if (this.hasLogErrorStacktrace || !this.logErrorStacktrace.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.logErrorStacktrace);
            }
            return size;
        }

        public ServerCommands mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.clearCache = input.readBool();
                        this.hasClearCache = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.displayErrorMessage = input.readString();
                        this.hasDisplayErrorMessage = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.logErrorStacktrace = input.readString();
                        this.hasLogErrorStacktrace = true;
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

    public static final class ServerMetadata extends MessageNano {
        public boolean hasLatencyMillis;
        public long latencyMillis;

        public ServerMetadata() {
            clear();
        }

        public ServerMetadata clear() {
            this.latencyMillis = 0;
            this.hasLatencyMillis = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLatencyMillis || this.latencyMillis != 0) {
                output.writeInt64(1, this.latencyMillis);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLatencyMillis || this.latencyMillis != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(1, this.latencyMillis);
            }
            return size;
        }

        public ServerMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.latencyMillis = input.readInt64();
                        this.hasLatencyMillis = true;
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
