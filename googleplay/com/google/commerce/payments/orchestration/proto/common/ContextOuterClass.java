package com.google.commerce.payments.orchestration.proto.common;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import paymentfraud.mobile.DeviceFingerprinting.Parsed;

public interface ContextOuterClass {

    public static final class NativeClientContext extends MessageNano {
        public String device;
        public String imsiHash;
        public String mccMnc;
        public String osVersion;
        public String packageName;
        public String packageVersionCode;
        public String packageVersionName;
        public Parsed riskData;
        public int screenHeightPx;
        public int screenWidthPx;
        public float screenXDpi;
        public float screenYDpi;

        public NativeClientContext() {
            clear();
        }

        public NativeClientContext clear() {
            this.imsiHash = "";
            this.mccMnc = "";
            this.osVersion = "";
            this.device = "";
            this.screenWidthPx = 0;
            this.screenHeightPx = 0;
            this.screenXDpi = 0.0f;
            this.screenYDpi = 0.0f;
            this.packageName = "";
            this.packageVersionCode = "";
            this.packageVersionName = "";
            this.riskData = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.imsiHash.equals("")) {
                output.writeString(1, this.imsiHash);
            }
            if (!this.mccMnc.equals("")) {
                output.writeString(2, this.mccMnc);
            }
            if (!this.osVersion.equals("")) {
                output.writeString(3, this.osVersion);
            }
            if (!this.device.equals("")) {
                output.writeString(4, this.device);
            }
            if (this.screenWidthPx != 0) {
                output.writeInt32(5, this.screenWidthPx);
            }
            if (this.screenHeightPx != 0) {
                output.writeInt32(6, this.screenHeightPx);
            }
            if (Float.floatToIntBits(this.screenXDpi) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(7, this.screenXDpi);
            }
            if (Float.floatToIntBits(this.screenYDpi) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(8, this.screenYDpi);
            }
            if (!this.packageName.equals("")) {
                output.writeString(9, this.packageName);
            }
            if (!this.packageVersionCode.equals("")) {
                output.writeString(10, this.packageVersionCode);
            }
            if (!this.packageVersionName.equals("")) {
                output.writeString(11, this.packageVersionName);
            }
            if (this.riskData != null) {
                output.writeMessage(12, this.riskData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.imsiHash.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.imsiHash);
            }
            if (!this.mccMnc.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.mccMnc);
            }
            if (!this.osVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.osVersion);
            }
            if (!this.device.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.device);
            }
            if (this.screenWidthPx != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.screenWidthPx);
            }
            if (this.screenHeightPx != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.screenHeightPx);
            }
            if (Float.floatToIntBits(this.screenXDpi) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(7, this.screenXDpi);
            }
            if (Float.floatToIntBits(this.screenYDpi) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(8, this.screenYDpi);
            }
            if (!this.packageName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.packageName);
            }
            if (!this.packageVersionCode.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.packageVersionCode);
            }
            if (!this.packageVersionName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.packageVersionName);
            }
            if (this.riskData != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(12, this.riskData);
            }
            return size;
        }

        public NativeClientContext mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.imsiHash = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.mccMnc = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.osVersion = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.device = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.screenWidthPx = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.screenHeightPx = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_textColorSearchUrl /*61*/:
                        this.screenXDpi = input.readFloat();
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPopupWindowStyle /*69*/:
                        this.screenYDpi = input.readFloat();
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.packageName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.packageVersionCode = input.readString();
                        continue;
                    case 90:
                        this.packageVersionName = input.readString();
                        continue;
                    case 98:
                        if (this.riskData == null) {
                            this.riskData = new Parsed();
                        }
                        input.readMessage(this.riskData);
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
