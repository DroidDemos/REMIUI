package com.google.android.finsky.protos;

import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.finsky.protos.PlusData.OBSOLETE_PlusProfile;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface PlusProfile {

    public static final class PlusProfileResponse extends MessageNano {
        public boolean hasIsGplusUser;
        public boolean isGplusUser;
        public DocV2 partialUserProfile;
        public OBSOLETE_PlusProfile plusProfile;

        public PlusProfileResponse() {
            clear();
        }

        public PlusProfileResponse clear() {
            this.partialUserProfile = null;
            this.isGplusUser = true;
            this.hasIsGplusUser = false;
            this.plusProfile = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.plusProfile != null) {
                output.writeMessage(1, this.plusProfile);
            }
            if (this.partialUserProfile != null) {
                output.writeMessage(2, this.partialUserProfile);
            }
            if (this.hasIsGplusUser || !this.isGplusUser) {
                output.writeBool(3, this.isGplusUser);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.plusProfile != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.plusProfile);
            }
            if (this.partialUserProfile != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.partialUserProfile);
            }
            if (this.hasIsGplusUser || !this.isGplusUser) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.isGplusUser);
            }
            return size;
        }

        public PlusProfileResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.plusProfile == null) {
                            this.plusProfile = new OBSOLETE_PlusProfile();
                        }
                        input.readMessage(this.plusProfile);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.partialUserProfile == null) {
                            this.partialUserProfile = new DocV2();
                        }
                        input.readMessage(this.partialUserProfile);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
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
