package com.google.android.finsky.protos;

import com.google.android.finsky.protos.ChallengeProto.Challenge;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface ChallengeAction {

    public static final class ChallengeResponse extends MessageNano {
        public Challenge challenge;
        public boolean challengePassed;
        public boolean hasChallengePassed;

        public ChallengeResponse() {
            clear();
        }

        public ChallengeResponse clear() {
            this.challenge = null;
            this.challengePassed = false;
            this.hasChallengePassed = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.challenge != null) {
                output.writeMessage(1, this.challenge);
            }
            if (this.hasChallengePassed || this.challengePassed) {
                output.writeBool(2, this.challengePassed);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.challenge != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.challenge);
            }
            if (this.hasChallengePassed || this.challengePassed) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.challengePassed);
            }
            return size;
        }

        public ChallengeResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.challenge == null) {
                            this.challenge = new Challenge();
                        }
                        input.readMessage(this.challenge);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.challengePassed = input.readBool();
                        this.hasChallengePassed = true;
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
