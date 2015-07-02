package com.google.commerce.payments.orchestration.proto.ui.common;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.common.ContextOuterClass.NativeClientContext;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface RequestContextOuterClass {

    public static final class RequestContext extends MessageNano {
        public int clientType;
        public long clientVersion;
        public String languageCode;
        public NativeClientContext nativeContext;
        public byte[] sessionData;

        public RequestContext() {
            clear();
        }

        public RequestContext clear() {
            this.sessionData = WireFormatNano.EMPTY_BYTES;
            this.nativeContext = null;
            this.languageCode = "";
            this.clientType = 0;
            this.clientVersion = 0;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!Arrays.equals(this.sessionData, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.sessionData);
            }
            if (!this.languageCode.equals("")) {
                output.writeString(7, this.languageCode);
            }
            if (this.clientType != 0) {
                output.writeInt32(8, this.clientType);
            }
            if (this.clientVersion != 0) {
                output.writeInt64(9, this.clientVersion);
            }
            if (this.nativeContext != null) {
                output.writeMessage(10, this.nativeContext);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!Arrays.equals(this.sessionData, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.sessionData);
            }
            if (!this.languageCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.languageCode);
            }
            if (this.clientType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.clientType);
            }
            if (this.clientVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(9, this.clientVersion);
            }
            if (this.nativeContext != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(10, this.nativeContext);
            }
            return size;
        }

        public RequestContext mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.sessionData = input.readBytes();
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.languageCode = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.clientType = value;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.clientVersion = input.readInt64();
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.nativeContext == null) {
                            this.nativeContext = new NativeClientContext();
                        }
                        input.readMessage(this.nativeContext);
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
