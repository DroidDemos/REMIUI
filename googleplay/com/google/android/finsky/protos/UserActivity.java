package com.google.android.finsky.protos;

import com.google.android.finsky.protos.DocumentV2.DocV2;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface UserActivity {

    public static final class RecordUserActivityResponse extends MessageNano {
        public RecordUserActivityResponse() {
            clear();
        }

        public RecordUserActivityResponse clear() {
            this.cachedSize = -1;
            return this;
        }

        public RecordUserActivityResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            int tag;
            do {
                tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    default:
                        break;
                }
                return this;
            } while (WireFormatNano.parseUnknownField(input, tag));
            return this;
        }
    }

    public static final class UserActivitySettingsResponse extends MessageNano {
        public DocV2 currentUser;
        public boolean hasOptIn;
        public boolean hasSettingsDescription;
        public boolean hasSettingsTitle;
        public boolean hasSettingsTosHtml;
        public boolean optIn;
        public String settingsDescription;
        public String settingsTitle;
        public String settingsTosHtml;

        public UserActivitySettingsResponse() {
            clear();
        }

        public UserActivitySettingsResponse clear() {
            this.currentUser = null;
            this.settingsTitle = "";
            this.hasSettingsTitle = false;
            this.settingsDescription = "";
            this.hasSettingsDescription = false;
            this.settingsTosHtml = "";
            this.hasSettingsTosHtml = false;
            this.optIn = false;
            this.hasOptIn = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.currentUser != null) {
                output.writeMessage(1, this.currentUser);
            }
            if (this.hasSettingsTitle || !this.settingsTitle.equals("")) {
                output.writeString(2, this.settingsTitle);
            }
            if (this.hasSettingsDescription || !this.settingsDescription.equals("")) {
                output.writeString(3, this.settingsDescription);
            }
            if (this.hasSettingsTosHtml || !this.settingsTosHtml.equals("")) {
                output.writeString(4, this.settingsTosHtml);
            }
            if (this.hasOptIn || this.optIn) {
                output.writeBool(5, this.optIn);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.currentUser != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.currentUser);
            }
            if (this.hasSettingsTitle || !this.settingsTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.settingsTitle);
            }
            if (this.hasSettingsDescription || !this.settingsDescription.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.settingsDescription);
            }
            if (this.hasSettingsTosHtml || !this.settingsTosHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.settingsTosHtml);
            }
            if (this.hasOptIn || this.optIn) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.optIn);
            }
            return size;
        }

        public UserActivitySettingsResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.currentUser == null) {
                            this.currentUser = new DocV2();
                        }
                        input.readMessage(this.currentUser);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.settingsTitle = input.readString();
                        this.hasSettingsTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.settingsDescription = input.readString();
                        this.hasSettingsDescription = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.settingsTosHtml = input.readString();
                        this.hasSettingsTosHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.optIn = input.readBool();
                        this.hasOptIn = true;
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
