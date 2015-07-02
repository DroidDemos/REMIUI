package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Document.PlayDocument;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface PlayPlusProfile {

    public static final class PlayPlusProfileResponse extends MessageNano {
        public boolean hasIsGplusUser;
        public boolean isGplusUser;
        public PlayDocument userProfile;

        public PlayPlusProfileResponse() {
            clear();
        }

        public PlayPlusProfileResponse clear() {
            this.userProfile = null;
            this.isGplusUser = false;
            this.hasIsGplusUser = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.userProfile != null) {
                output.writeMessage(1, this.userProfile);
            }
            if (this.hasIsGplusUser || this.isGplusUser) {
                output.writeBool(2, this.isGplusUser);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.userProfile != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.userProfile);
            }
            if (this.hasIsGplusUser || this.isGplusUser) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.isGplusUser);
            }
            return size;
        }

        public PlayPlusProfileResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.userProfile == null) {
                            this.userProfile = new PlayDocument();
                        }
                        input.readMessage(this.userProfile);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.isGplusUser = input.readBool();
                        this.hasIsGplusUser = true;
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
