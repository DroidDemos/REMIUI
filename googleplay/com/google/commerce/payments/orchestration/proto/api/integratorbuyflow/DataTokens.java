package com.google.commerce.payments.orchestration.proto.api.integratorbuyflow;

import com.google.android.wallet.instrumentmanager.R;
import com.google.commerce.payments.orchestration.proto.ui.common.RequestContextOuterClass.RequestContext;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.InitializeResponse;
import com.google.commerce.payments.orchestration.proto.ui.instrumentmanager.Api.InstrumentManagerParameters;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface DataTokens {

    public static final class ActionToken extends MessageNano {
        public InitializeResponse initializeResponse;
        public InstrumentManagerParameters parameters;

        public ActionToken() {
            clear();
        }

        public ActionToken clear() {
            this.parameters = null;
            this.initializeResponse = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.parameters != null) {
                output.writeMessage(1, this.parameters);
            }
            if (this.initializeResponse != null) {
                output.writeMessage(2, this.initializeResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.parameters != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.parameters);
            }
            if (this.initializeResponse != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.initializeResponse);
            }
            return size;
        }

        public ActionToken mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.parameters == null) {
                            this.parameters = new InstrumentManagerParameters();
                        }
                        input.readMessage(this.parameters);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.initializeResponse == null) {
                            this.initializeResponse = new InitializeResponse();
                        }
                        input.readMessage(this.initializeResponse);
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

    public static final class AndroidEnvironmentConfig extends MessageNano {
        public String accountName;
        public String authTokenType;
        public String serverBasePath;
        public String serverEesBasePath;

        public AndroidEnvironmentConfig() {
            clear();
        }

        public AndroidEnvironmentConfig clear() {
            this.serverBasePath = "";
            this.serverEesBasePath = "";
            this.authTokenType = "";
            this.accountName = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.serverBasePath.equals("")) {
                output.writeString(1, this.serverBasePath);
            }
            if (!this.authTokenType.equals("")) {
                output.writeString(2, this.authTokenType);
            }
            if (!this.accountName.equals("")) {
                output.writeString(3, this.accountName);
            }
            if (!this.serverEesBasePath.equals("")) {
                output.writeString(4, this.serverEesBasePath);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.serverBasePath.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.serverBasePath);
            }
            if (!this.authTokenType.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.authTokenType);
            }
            if (!this.accountName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.accountName);
            }
            if (this.serverEesBasePath.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(4, this.serverEesBasePath);
        }

        public AndroidEnvironmentConfig mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.serverBasePath = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.authTokenType = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.accountName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.serverEesBasePath = input.readString();
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

    public static final class ClientToken extends MessageNano {
        public RequestContext requestContext;

        public ClientToken() {
            clear();
        }

        public ClientToken clear() {
            this.requestContext = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.requestContext != null) {
                output.writeMessage(1, this.requestContext);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.requestContext != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.requestContext);
            }
            return size;
        }

        public ClientToken mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.requestContext == null) {
                            this.requestContext = new RequestContext();
                        }
                        input.readMessage(this.requestContext);
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

    public static final class CommonToken extends MessageNano {
        public AndroidEnvironmentConfig androidEnvironmentConfig;
        public InstrumentManagerParameters parameters;

        public CommonToken() {
            clear();
        }

        public CommonToken clear() {
            this.parameters = null;
            this.androidEnvironmentConfig = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.parameters != null) {
                output.writeMessage(1, this.parameters);
            }
            if (this.androidEnvironmentConfig != null) {
                output.writeMessage(2, this.androidEnvironmentConfig);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.parameters != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.parameters);
            }
            if (this.androidEnvironmentConfig != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.androidEnvironmentConfig);
            }
            return size;
        }

        public CommonToken mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.parameters == null) {
                            this.parameters = new InstrumentManagerParameters();
                        }
                        input.readMessage(this.parameters);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.androidEnvironmentConfig == null) {
                            this.androidEnvironmentConfig = new AndroidEnvironmentConfig();
                        }
                        input.readMessage(this.androidEnvironmentConfig);
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
