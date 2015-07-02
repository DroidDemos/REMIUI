package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface DebugSettings {

    public static final class DebugSettingsResponse extends MessageNano {
        public boolean hasPlayCountryDebugInfo;
        public boolean hasPlayCountryOverride;
        public String playCountryDebugInfo;
        public String playCountryOverride;

        public DebugSettingsResponse() {
            clear();
        }

        public DebugSettingsResponse clear() {
            this.playCountryOverride = "";
            this.hasPlayCountryOverride = false;
            this.playCountryDebugInfo = "";
            this.hasPlayCountryDebugInfo = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPlayCountryOverride || !this.playCountryOverride.equals("")) {
                output.writeString(1, this.playCountryOverride);
            }
            if (this.hasPlayCountryDebugInfo || !this.playCountryDebugInfo.equals("")) {
                output.writeString(2, this.playCountryDebugInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPlayCountryOverride || !this.playCountryOverride.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.playCountryOverride);
            }
            if (this.hasPlayCountryDebugInfo || !this.playCountryDebugInfo.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.playCountryDebugInfo);
            }
            return size;
        }

        public DebugSettingsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.playCountryOverride = input.readString();
                        this.hasPlayCountryOverride = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.playCountryDebugInfo = input.readString();
                        this.hasPlayCountryDebugInfo = true;
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
