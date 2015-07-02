package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Docid;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface EarlyUpdate {

    public static final class EarlyDocumentInfo extends MessageNano {
        private static volatile EarlyDocumentInfo[] _emptyArray;
        public boolean background;
        public boolean critical;
        public Docid docid;
        public boolean hasBackground;
        public boolean hasCritical;
        public boolean hasTitle;
        public boolean hasVersionCode;
        public String title;
        public int versionCode;

        public static EarlyDocumentInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new EarlyDocumentInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public EarlyDocumentInfo() {
            clear();
        }

        public EarlyDocumentInfo clear() {
            this.docid = null;
            this.title = "";
            this.hasTitle = false;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.background = false;
            this.hasBackground = false;
            this.critical = false;
            this.hasCritical = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.docid != null) {
                output.writeMessage(1, this.docid);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(2, this.title);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                output.writeInt32(3, this.versionCode);
            }
            if (this.hasBackground || this.background) {
                output.writeBool(4, this.background);
            }
            if (this.hasCritical || this.critical) {
                output.writeBool(5, this.critical);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.docid);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.title);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.versionCode);
            }
            if (this.hasBackground || this.background) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.background);
            }
            if (this.hasCritical || this.critical) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.critical);
            }
            return size;
        }

        public EarlyDocumentInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.docid == null) {
                            this.docid = new Docid();
                        }
                        input.readMessage(this.docid);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.versionCode = input.readInt32();
                        this.hasVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.background = input.readBool();
                        this.hasBackground = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.critical = input.readBool();
                        this.hasCritical = true;
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

    public static final class EarlyUpdateRequest extends MessageNano {
        public DeviceConfigurationProto deviceConfiguration;

        public EarlyUpdateRequest() {
            clear();
        }

        public EarlyUpdateRequest clear() {
            this.deviceConfiguration = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deviceConfiguration != null) {
                output.writeMessage(1, this.deviceConfiguration);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deviceConfiguration != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.deviceConfiguration);
            }
            return size;
        }

        public EarlyUpdateRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.deviceConfiguration == null) {
                            this.deviceConfiguration = new DeviceConfigurationProto();
                        }
                        input.readMessage(this.deviceConfiguration);
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

    public static final class EarlyUpdateResponse extends MessageNano {
        public EarlyDocumentInfo[] earlyDocumentInfo;

        public EarlyUpdateResponse() {
            clear();
        }

        public EarlyUpdateResponse clear() {
            this.earlyDocumentInfo = EarlyDocumentInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.earlyDocumentInfo != null && this.earlyDocumentInfo.length > 0) {
                for (EarlyDocumentInfo element : this.earlyDocumentInfo) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.earlyDocumentInfo != null && this.earlyDocumentInfo.length > 0) {
                for (EarlyDocumentInfo element : this.earlyDocumentInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public EarlyUpdateResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.earlyDocumentInfo == null) {
                            i = 0;
                        } else {
                            i = this.earlyDocumentInfo.length;
                        }
                        EarlyDocumentInfo[] newArray = new EarlyDocumentInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.earlyDocumentInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new EarlyDocumentInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new EarlyDocumentInfo();
                        input.readMessage(newArray[i]);
                        this.earlyDocumentInfo = newArray;
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
