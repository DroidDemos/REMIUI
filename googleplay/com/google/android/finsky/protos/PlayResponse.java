package com.google.android.finsky.protos;

import com.google.android.finsky.protos.PlayPlusProfile.PlayPlusProfileResponse;
import com.google.android.finsky.protos.PlusProfile.PlusProfileResponse;
import com.google.android.finsky.protos.ResponseMessages.PreFetch;
import com.google.android.finsky.protos.ResponseMessages.ServerCommands;
import com.google.android.finsky.protos.ResponseMessages.ServerMetadata;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface PlayResponse {

    public static final class PlayPayload extends MessageNano {
        public PlayPlusProfileResponse oBSOLETEPlusProfileResponse;
        public PlusProfileResponse plusProfileResponse;

        public PlayPayload() {
            clear();
        }

        public PlayPayload clear() {
            this.oBSOLETEPlusProfileResponse = null;
            this.plusProfileResponse = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.oBSOLETEPlusProfileResponse != null) {
                output.writeMessage(1, this.oBSOLETEPlusProfileResponse);
            }
            if (this.plusProfileResponse != null) {
                output.writeMessage(2, this.plusProfileResponse);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.oBSOLETEPlusProfileResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.oBSOLETEPlusProfileResponse);
            }
            if (this.plusProfileResponse != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.plusProfileResponse);
            }
            return size;
        }

        public PlayPayload mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.oBSOLETEPlusProfileResponse == null) {
                            this.oBSOLETEPlusProfileResponse = new PlayPlusProfileResponse();
                        }
                        input.readMessage(this.oBSOLETEPlusProfileResponse);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.plusProfileResponse == null) {
                            this.plusProfileResponse = new PlusProfileResponse();
                        }
                        input.readMessage(this.plusProfileResponse);
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

    public static final class PlayResponseWrapper extends MessageNano {
        public ServerCommands commands;
        public PlayPayload payload;
        public PreFetch[] preFetch;
        public ServerMetadata serverMetadata;

        public PlayResponseWrapper() {
            clear();
        }

        public PlayResponseWrapper clear() {
            this.payload = null;
            this.commands = null;
            this.preFetch = PreFetch.emptyArray();
            this.serverMetadata = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.payload != null) {
                output.writeMessage(1, this.payload);
            }
            if (this.commands != null) {
                output.writeMessage(2, this.commands);
            }
            if (this.preFetch != null && this.preFetch.length > 0) {
                for (PreFetch element : this.preFetch) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.serverMetadata != null) {
                output.writeMessage(4, this.serverMetadata);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.payload != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.payload);
            }
            if (this.commands != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.commands);
            }
            if (this.preFetch != null && this.preFetch.length > 0) {
                for (PreFetch element : this.preFetch) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.serverMetadata != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(4, this.serverMetadata);
            }
            return size;
        }

        public PlayResponseWrapper mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.payload == null) {
                            this.payload = new PlayPayload();
                        }
                        input.readMessage(this.payload);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.commands == null) {
                            this.commands = new ServerCommands();
                        }
                        input.readMessage(this.commands);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.preFetch == null) {
                            i = 0;
                        } else {
                            i = this.preFetch.length;
                        }
                        PreFetch[] newArray = new PreFetch[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.preFetch, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new PreFetch();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new PreFetch();
                        input.readMessage(newArray[i]);
                        this.preFetch = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.serverMetadata == null) {
                            this.serverMetadata = new ServerMetadata();
                        }
                        input.readMessage(this.serverMetadata);
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

        public static PlayResponseWrapper parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (PlayResponseWrapper) MessageNano.mergeFrom(new PlayResponseWrapper(), data);
        }
    }
}
