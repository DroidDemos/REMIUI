package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface AndroidAppDelivery {

    public static final class AndroidAppDeliveryData extends MessageNano {
        public AppFileMetadata[] additionalFile;
        public HttpCookie[] downloadAuthCookie;
        public long downloadSize;
        public String downloadUrl;
        public EncryptionParams encryptionParams;
        public boolean forwardLocked;
        public long gzippedDownloadSize;
        public String gzippedDownloadUrl;
        public boolean hasDownloadSize;
        public boolean hasDownloadUrl;
        public boolean hasForwardLocked;
        public boolean hasGzippedDownloadSize;
        public boolean hasGzippedDownloadUrl;
        public boolean hasImmediateStartNeeded;
        public boolean hasInstallLocation;
        public boolean hasPostInstallRefundWindowMillis;
        public boolean hasRefundTimeout;
        public boolean hasServerInitiated;
        public boolean hasSignature;
        public boolean immediateStartNeeded;
        public int installLocation;
        public AndroidAppPatchData patchData;
        public long postInstallRefundWindowMillis;
        public long refundTimeout;
        public boolean serverInitiated;
        public String signature;
        public SplitDeliveryData[] splitDeliveryData;

        public AndroidAppDeliveryData() {
            clear();
        }

        public AndroidAppDeliveryData clear() {
            this.downloadSize = 0;
            this.hasDownloadSize = false;
            this.gzippedDownloadSize = 0;
            this.hasGzippedDownloadSize = false;
            this.signature = "";
            this.hasSignature = false;
            this.downloadUrl = "";
            this.hasDownloadUrl = false;
            this.gzippedDownloadUrl = "";
            this.hasGzippedDownloadUrl = false;
            this.encryptionParams = null;
            this.additionalFile = AppFileMetadata.emptyArray();
            this.downloadAuthCookie = HttpCookie.emptyArray();
            this.forwardLocked = false;
            this.hasForwardLocked = false;
            this.refundTimeout = 0;
            this.hasRefundTimeout = false;
            this.postInstallRefundWindowMillis = 0;
            this.hasPostInstallRefundWindowMillis = false;
            this.serverInitiated = true;
            this.hasServerInitiated = false;
            this.immediateStartNeeded = false;
            this.hasImmediateStartNeeded = false;
            this.patchData = null;
            this.splitDeliveryData = SplitDeliveryData.emptyArray();
            this.installLocation = 0;
            this.hasInstallLocation = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDownloadSize || this.downloadSize != 0) {
                output.writeInt64(1, this.downloadSize);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                output.writeString(2, this.signature);
            }
            if (this.hasDownloadUrl || !this.downloadUrl.equals("")) {
                output.writeString(3, this.downloadUrl);
            }
            if (this.additionalFile != null && this.additionalFile.length > 0) {
                for (AppFileMetadata element : this.additionalFile) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.downloadAuthCookie != null && this.downloadAuthCookie.length > 0) {
                for (HttpCookie element2 : this.downloadAuthCookie) {
                    if (element2 != null) {
                        output.writeMessage(5, element2);
                    }
                }
            }
            if (this.hasForwardLocked || this.forwardLocked) {
                output.writeBool(6, this.forwardLocked);
            }
            if (this.hasRefundTimeout || this.refundTimeout != 0) {
                output.writeInt64(7, this.refundTimeout);
            }
            if (this.hasServerInitiated || !this.serverInitiated) {
                output.writeBool(8, this.serverInitiated);
            }
            if (this.hasPostInstallRefundWindowMillis || this.postInstallRefundWindowMillis != 0) {
                output.writeInt64(9, this.postInstallRefundWindowMillis);
            }
            if (this.hasImmediateStartNeeded || this.immediateStartNeeded) {
                output.writeBool(10, this.immediateStartNeeded);
            }
            if (this.patchData != null) {
                output.writeMessage(11, this.patchData);
            }
            if (this.encryptionParams != null) {
                output.writeMessage(12, this.encryptionParams);
            }
            if (this.hasGzippedDownloadUrl || !this.gzippedDownloadUrl.equals("")) {
                output.writeString(13, this.gzippedDownloadUrl);
            }
            if (this.hasGzippedDownloadSize || this.gzippedDownloadSize != 0) {
                output.writeInt64(14, this.gzippedDownloadSize);
            }
            if (this.splitDeliveryData != null && this.splitDeliveryData.length > 0) {
                for (SplitDeliveryData element3 : this.splitDeliveryData) {
                    if (element3 != null) {
                        output.writeMessage(15, element3);
                    }
                }
            }
            if (this.installLocation != 0 || this.hasInstallLocation) {
                output.writeInt32(16, this.installLocation);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDownloadSize || this.downloadSize != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.downloadSize);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.signature);
            }
            if (this.hasDownloadUrl || !this.downloadUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.downloadUrl);
            }
            if (this.additionalFile != null && this.additionalFile.length > 0) {
                for (AppFileMetadata element : this.additionalFile) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.downloadAuthCookie != null && this.downloadAuthCookie.length > 0) {
                for (HttpCookie element2 : this.downloadAuthCookie) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element2);
                    }
                }
            }
            if (this.hasForwardLocked || this.forwardLocked) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.forwardLocked);
            }
            if (this.hasRefundTimeout || this.refundTimeout != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(7, this.refundTimeout);
            }
            if (this.hasServerInitiated || !this.serverInitiated) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.serverInitiated);
            }
            if (this.hasPostInstallRefundWindowMillis || this.postInstallRefundWindowMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(9, this.postInstallRefundWindowMillis);
            }
            if (this.hasImmediateStartNeeded || this.immediateStartNeeded) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.immediateStartNeeded);
            }
            if (this.patchData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.patchData);
            }
            if (this.encryptionParams != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.encryptionParams);
            }
            if (this.hasGzippedDownloadUrl || !this.gzippedDownloadUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.gzippedDownloadUrl);
            }
            if (this.hasGzippedDownloadSize || this.gzippedDownloadSize != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(14, this.gzippedDownloadSize);
            }
            if (this.splitDeliveryData != null && this.splitDeliveryData.length > 0) {
                for (SplitDeliveryData element3 : this.splitDeliveryData) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(15, element3);
                    }
                }
            }
            if (this.installLocation != 0 || this.hasInstallLocation) {
                return size + CodedOutputByteBufferNano.computeInt32Size(16, this.installLocation);
            }
            return size;
        }

        public AndroidAppDeliveryData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.downloadSize = input.readInt64();
                        this.hasDownloadSize = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.signature = input.readString();
                        this.hasSignature = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.downloadUrl = input.readString();
                        this.hasDownloadUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.additionalFile == null) {
                            i = 0;
                        } else {
                            i = this.additionalFile.length;
                        }
                        AppFileMetadata[] newArray = new AppFileMetadata[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.additionalFile, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppFileMetadata();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppFileMetadata();
                        input.readMessage(newArray[i]);
                        this.additionalFile = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.downloadAuthCookie == null) {
                            i = 0;
                        } else {
                            i = this.downloadAuthCookie.length;
                        }
                        HttpCookie[] newArray2 = new HttpCookie[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.downloadAuthCookie, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new HttpCookie();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new HttpCookie();
                        input.readMessage(newArray2[i]);
                        this.downloadAuthCookie = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.forwardLocked = input.readBool();
                        this.hasForwardLocked = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.refundTimeout = input.readInt64();
                        this.hasRefundTimeout = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.serverInitiated = input.readBool();
                        this.hasServerInitiated = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.postInstallRefundWindowMillis = input.readInt64();
                        this.hasPostInstallRefundWindowMillis = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.immediateStartNeeded = input.readBool();
                        this.hasImmediateStartNeeded = true;
                        continue;
                    case 90:
                        if (this.patchData == null) {
                            this.patchData = new AndroidAppPatchData();
                        }
                        input.readMessage(this.patchData);
                        continue;
                    case 98:
                        if (this.encryptionParams == null) {
                            this.encryptionParams = new EncryptionParams();
                        }
                        input.readMessage(this.encryptionParams);
                        continue;
                    case 106:
                        this.gzippedDownloadUrl = input.readString();
                        this.hasGzippedDownloadUrl = true;
                        continue;
                    case 112:
                        this.gzippedDownloadSize = input.readInt64();
                        this.hasGzippedDownloadSize = true;
                        continue;
                    case 122:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 122);
                        if (this.splitDeliveryData == null) {
                            i = 0;
                        } else {
                            i = this.splitDeliveryData.length;
                        }
                        SplitDeliveryData[] newArray3 = new SplitDeliveryData[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.splitDeliveryData, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new SplitDeliveryData();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new SplitDeliveryData();
                        input.readMessage(newArray3[i]);
                        this.splitDeliveryData = newArray3;
                        continue;
                    case 128:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.installLocation = value;
                                this.hasInstallLocation = true;
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!WireFormatNano.parseUnknownField(input, tag)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public static AndroidAppDeliveryData parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (AndroidAppDeliveryData) MessageNano.mergeFrom(new AndroidAppDeliveryData(), data);
        }
    }

    public static final class AndroidAppPatchData extends MessageNano {
        public String baseSignature;
        public int baseVersionCode;
        public String downloadUrl;
        public boolean hasBaseSignature;
        public boolean hasBaseVersionCode;
        public boolean hasDownloadUrl;
        public boolean hasMaxPatchSize;
        public boolean hasPatchFormat;
        public long maxPatchSize;
        public int patchFormat;

        public AndroidAppPatchData() {
            clear();
        }

        public AndroidAppPatchData clear() {
            this.baseVersionCode = 0;
            this.hasBaseVersionCode = false;
            this.baseSignature = "";
            this.hasBaseSignature = false;
            this.downloadUrl = "";
            this.hasDownloadUrl = false;
            this.patchFormat = 1;
            this.hasPatchFormat = false;
            this.maxPatchSize = 0;
            this.hasMaxPatchSize = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasBaseVersionCode || this.baseVersionCode != 0) {
                output.writeInt32(1, this.baseVersionCode);
            }
            if (this.hasBaseSignature || !this.baseSignature.equals("")) {
                output.writeString(2, this.baseSignature);
            }
            if (this.hasDownloadUrl || !this.downloadUrl.equals("")) {
                output.writeString(3, this.downloadUrl);
            }
            if (this.patchFormat != 1 || this.hasPatchFormat) {
                output.writeInt32(4, this.patchFormat);
            }
            if (this.hasMaxPatchSize || this.maxPatchSize != 0) {
                output.writeInt64(5, this.maxPatchSize);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasBaseVersionCode || this.baseVersionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.baseVersionCode);
            }
            if (this.hasBaseSignature || !this.baseSignature.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.baseSignature);
            }
            if (this.hasDownloadUrl || !this.downloadUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.downloadUrl);
            }
            if (this.patchFormat != 1 || this.hasPatchFormat) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.patchFormat);
            }
            if (this.hasMaxPatchSize || this.maxPatchSize != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(5, this.maxPatchSize);
            }
            return size;
        }

        public AndroidAppPatchData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.baseVersionCode = input.readInt32();
                        this.hasBaseVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.baseSignature = input.readString();
                        this.hasBaseSignature = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.downloadUrl = input.readString();
                        this.hasDownloadUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.patchFormat = value;
                                this.hasPatchFormat = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.maxPatchSize = input.readInt64();
                        this.hasMaxPatchSize = true;
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

    public static final class AppFileMetadata extends MessageNano {
        private static volatile AppFileMetadata[] _emptyArray;
        public String downloadUrl;
        public int fileType;
        public boolean hasDownloadUrl;
        public boolean hasFileType;
        public boolean hasSize;
        public boolean hasVersionCode;
        public long size;
        public int versionCode;

        public static AppFileMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppFileMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppFileMetadata() {
            clear();
        }

        public AppFileMetadata clear() {
            this.fileType = 0;
            this.hasFileType = false;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.size = 0;
            this.hasSize = false;
            this.downloadUrl = "";
            this.hasDownloadUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.fileType != 0 || this.hasFileType) {
                output.writeInt32(1, this.fileType);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                output.writeInt32(2, this.versionCode);
            }
            if (this.hasSize || this.size != 0) {
                output.writeInt64(3, this.size);
            }
            if (this.hasDownloadUrl || !this.downloadUrl.equals("")) {
                output.writeString(4, this.downloadUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.fileType != 0 || this.hasFileType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.fileType);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.versionCode);
            }
            if (this.hasSize || this.size != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.size);
            }
            if (this.hasDownloadUrl || !this.downloadUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.downloadUrl);
            }
            return size;
        }

        public AppFileMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                this.fileType = value;
                                this.hasFileType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.versionCode = input.readInt32();
                        this.hasVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.size = input.readInt64();
                        this.hasSize = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.downloadUrl = input.readString();
                        this.hasDownloadUrl = true;
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

    public static final class EncryptionParams extends MessageNano {
        public String encryptionKey;
        public boolean hasEncryptionKey;
        public boolean hasHmacKey;
        public boolean hasVersion;
        public String hmacKey;
        public int version;

        public EncryptionParams() {
            clear();
        }

        public EncryptionParams clear() {
            this.version = 1;
            this.hasVersion = false;
            this.encryptionKey = "";
            this.hasEncryptionKey = false;
            this.hmacKey = "";
            this.hasHmacKey = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasVersion || this.version != 1) {
                output.writeInt32(1, this.version);
            }
            if (this.hasEncryptionKey || !this.encryptionKey.equals("")) {
                output.writeString(2, this.encryptionKey);
            }
            if (this.hasHmacKey || !this.hmacKey.equals("")) {
                output.writeString(3, this.hmacKey);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasVersion || this.version != 1) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.version);
            }
            if (this.hasEncryptionKey || !this.encryptionKey.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.encryptionKey);
            }
            if (this.hasHmacKey || !this.hmacKey.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.hmacKey);
            }
            return size;
        }

        public EncryptionParams mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.version = input.readInt32();
                        this.hasVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.encryptionKey = input.readString();
                        this.hasEncryptionKey = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.hmacKey = input.readString();
                        this.hasHmacKey = true;
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

    public static final class HttpCookie extends MessageNano {
        private static volatile HttpCookie[] _emptyArray;
        public boolean hasName;
        public boolean hasValue;
        public String name;
        public String value;

        public static HttpCookie[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new HttpCookie[0];
                    }
                }
            }
            return _emptyArray;
        }

        public HttpCookie() {
            clear();
        }

        public HttpCookie clear() {
            this.name = "";
            this.hasName = false;
            this.value = "";
            this.hasValue = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasName || !this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (this.hasValue || !this.value.equals("")) {
                output.writeString(2, this.value);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.hasValue || !this.value.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.value);
            }
            return size;
        }

        public HttpCookie mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.value = input.readString();
                        this.hasValue = true;
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

    public static final class SplitDeliveryData extends MessageNano {
        private static volatile SplitDeliveryData[] _emptyArray;
        public long downloadSize;
        public String downloadUrl;
        public long gzippedDownloadSize;
        public String gzippedDownloadUrl;
        public boolean hasDownloadSize;
        public boolean hasDownloadUrl;
        public boolean hasGzippedDownloadSize;
        public boolean hasGzippedDownloadUrl;
        public boolean hasId;
        public boolean hasSignature;
        public String id;
        public AndroidAppPatchData patchData;
        public String signature;

        public static SplitDeliveryData[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new SplitDeliveryData[0];
                    }
                }
            }
            return _emptyArray;
        }

        public SplitDeliveryData() {
            clear();
        }

        public SplitDeliveryData clear() {
            this.id = "";
            this.hasId = false;
            this.downloadSize = 0;
            this.hasDownloadSize = false;
            this.gzippedDownloadSize = 0;
            this.hasGzippedDownloadSize = false;
            this.signature = "";
            this.hasSignature = false;
            this.downloadUrl = "";
            this.hasDownloadUrl = false;
            this.gzippedDownloadUrl = "";
            this.hasGzippedDownloadUrl = false;
            this.patchData = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasId || !this.id.equals("")) {
                output.writeString(1, this.id);
            }
            if (this.hasDownloadSize || this.downloadSize != 0) {
                output.writeInt64(2, this.downloadSize);
            }
            if (this.hasGzippedDownloadSize || this.gzippedDownloadSize != 0) {
                output.writeInt64(3, this.gzippedDownloadSize);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                output.writeString(4, this.signature);
            }
            if (this.hasDownloadUrl || !this.downloadUrl.equals("")) {
                output.writeString(5, this.downloadUrl);
            }
            if (this.hasGzippedDownloadUrl || !this.gzippedDownloadUrl.equals("")) {
                output.writeString(6, this.gzippedDownloadUrl);
            }
            if (this.patchData != null) {
                output.writeMessage(7, this.patchData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasId || !this.id.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.id);
            }
            if (this.hasDownloadSize || this.downloadSize != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.downloadSize);
            }
            if (this.hasGzippedDownloadSize || this.gzippedDownloadSize != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.gzippedDownloadSize);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.signature);
            }
            if (this.hasDownloadUrl || !this.downloadUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.downloadUrl);
            }
            if (this.hasGzippedDownloadUrl || !this.gzippedDownloadUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.gzippedDownloadUrl);
            }
            if (this.patchData != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.patchData);
            }
            return size;
        }

        public SplitDeliveryData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.id = input.readString();
                        this.hasId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.downloadSize = input.readInt64();
                        this.hasDownloadSize = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.gzippedDownloadSize = input.readInt64();
                        this.hasGzippedDownloadSize = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.signature = input.readString();
                        this.hasSignature = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.downloadUrl = input.readString();
                        this.hasDownloadUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.gzippedDownloadUrl = input.readString();
                        this.hasGzippedDownloadUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.patchData == null) {
                            this.patchData = new AndroidAppPatchData();
                        }
                        input.readMessage(this.patchData);
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
