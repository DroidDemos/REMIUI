package com.google.android.finsky.analytics;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface PlayStore {

    public static final class AppData extends MessageNano {
        public boolean downloadExternal;
        public boolean downloadGzip;
        public boolean downloadPatch;
        public boolean hasDownloadExternal;
        public boolean hasDownloadGzip;
        public boolean hasDownloadPatch;
        public boolean hasOldVersion;
        public boolean hasSystemApp;
        public boolean hasVersion;
        public int oldVersion;
        public boolean systemApp;
        public int version;

        public AppData() {
            clear();
        }

        public AppData clear() {
            this.version = 0;
            this.hasVersion = false;
            this.oldVersion = 0;
            this.hasOldVersion = false;
            this.systemApp = false;
            this.hasSystemApp = false;
            this.downloadExternal = false;
            this.hasDownloadExternal = false;
            this.downloadGzip = false;
            this.hasDownloadGzip = false;
            this.downloadPatch = false;
            this.hasDownloadPatch = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasVersion || this.version != 0) {
                output.writeInt32(1, this.version);
            }
            if (this.hasOldVersion || this.oldVersion != 0) {
                output.writeInt32(2, this.oldVersion);
            }
            if (this.hasSystemApp || this.systemApp) {
                output.writeBool(3, this.systemApp);
            }
            if (this.hasDownloadExternal || this.downloadExternal) {
                output.writeBool(4, this.downloadExternal);
            }
            if (this.hasDownloadGzip || this.downloadGzip) {
                output.writeBool(5, this.downloadGzip);
            }
            if (this.hasDownloadPatch || this.downloadPatch) {
                output.writeBool(6, this.downloadPatch);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasVersion || this.version != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.version);
            }
            if (this.hasOldVersion || this.oldVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.oldVersion);
            }
            if (this.hasSystemApp || this.systemApp) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.systemApp);
            }
            if (this.hasDownloadExternal || this.downloadExternal) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.downloadExternal);
            }
            if (this.hasDownloadGzip || this.downloadGzip) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.downloadGzip);
            }
            if (this.hasDownloadPatch || this.downloadPatch) {
                return size + CodedOutputByteBufferNano.computeBoolSize(6, this.downloadPatch);
            }
            return size;
        }

        public AppData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.version = input.readInt32();
                        this.hasVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.oldVersion = input.readInt32();
                        this.hasOldVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.systemApp = input.readBool();
                        this.hasSystemApp = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.downloadExternal = input.readBool();
                        this.hasDownloadExternal = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.downloadGzip = input.readBool();
                        this.hasDownloadGzip = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.downloadPatch = input.readBool();
                        this.hasDownloadPatch = true;
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

    public static final class AuthContext extends MessageNano {
        public boolean hasMode;
        public int mode;

        public AuthContext() {
            clear();
        }

        public AuthContext clear() {
            this.mode = 0;
            this.hasMode = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.mode != 0 || this.hasMode) {
                output.writeInt32(1, this.mode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.mode != 0 || this.hasMode) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.mode);
            }
            return size;
        }

        public AuthContext mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.mode = value;
                                this.hasMode = true;
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
    }

    public static final class CreditCardEntryAction extends MessageNano {
        public int expDateEntryType;
        public boolean expDateOcrEnabled;
        public boolean expDateRecognizedByOcr;
        public boolean expDateValidationErrorOccurred;
        public boolean hasExpDateEntryType;
        public boolean hasExpDateOcrEnabled;
        public boolean hasExpDateRecognizedByOcr;
        public boolean hasExpDateValidationErrorOccurred;
        public boolean hasNumOcrAttempts;
        public boolean hasOcrExitReason;
        public boolean hasPanEntryType;
        public boolean hasPanOcrEnabled;
        public boolean hasPanRecognizedByOcr;
        public boolean hasPanValidationErrorOccurred;
        public int numOcrAttempts;
        public int ocrExitReason;
        public int panEntryType;
        public boolean panOcrEnabled;
        public boolean panRecognizedByOcr;
        public boolean panValidationErrorOccurred;

        public CreditCardEntryAction() {
            clear();
        }

        public CreditCardEntryAction clear() {
            this.panOcrEnabled = false;
            this.hasPanOcrEnabled = false;
            this.panEntryType = 0;
            this.hasPanEntryType = false;
            this.panRecognizedByOcr = false;
            this.hasPanRecognizedByOcr = false;
            this.panValidationErrorOccurred = false;
            this.hasPanValidationErrorOccurred = false;
            this.expDateOcrEnabled = false;
            this.hasExpDateOcrEnabled = false;
            this.expDateEntryType = 0;
            this.hasExpDateEntryType = false;
            this.expDateRecognizedByOcr = false;
            this.hasExpDateRecognizedByOcr = false;
            this.expDateValidationErrorOccurred = false;
            this.hasExpDateValidationErrorOccurred = false;
            this.numOcrAttempts = 0;
            this.hasNumOcrAttempts = false;
            this.ocrExitReason = 0;
            this.hasOcrExitReason = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPanOcrEnabled || this.panOcrEnabled) {
                output.writeBool(1, this.panOcrEnabled);
            }
            if (this.panEntryType != 0 || this.hasPanEntryType) {
                output.writeInt32(2, this.panEntryType);
            }
            if (this.hasPanRecognizedByOcr || this.panRecognizedByOcr) {
                output.writeBool(3, this.panRecognizedByOcr);
            }
            if (this.hasPanValidationErrorOccurred || this.panValidationErrorOccurred) {
                output.writeBool(4, this.panValidationErrorOccurred);
            }
            if (this.hasExpDateOcrEnabled || this.expDateOcrEnabled) {
                output.writeBool(5, this.expDateOcrEnabled);
            }
            if (this.expDateEntryType != 0 || this.hasExpDateEntryType) {
                output.writeInt32(6, this.expDateEntryType);
            }
            if (this.hasExpDateRecognizedByOcr || this.expDateRecognizedByOcr) {
                output.writeBool(7, this.expDateRecognizedByOcr);
            }
            if (this.hasExpDateValidationErrorOccurred || this.expDateValidationErrorOccurred) {
                output.writeBool(8, this.expDateValidationErrorOccurred);
            }
            if (this.hasNumOcrAttempts || this.numOcrAttempts != 0) {
                output.writeInt32(9, this.numOcrAttempts);
            }
            if (this.ocrExitReason != 0 || this.hasOcrExitReason) {
                output.writeInt32(10, this.ocrExitReason);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPanOcrEnabled || this.panOcrEnabled) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.panOcrEnabled);
            }
            if (this.panEntryType != 0 || this.hasPanEntryType) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.panEntryType);
            }
            if (this.hasPanRecognizedByOcr || this.panRecognizedByOcr) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.panRecognizedByOcr);
            }
            if (this.hasPanValidationErrorOccurred || this.panValidationErrorOccurred) {
                size += CodedOutputByteBufferNano.computeBoolSize(4, this.panValidationErrorOccurred);
            }
            if (this.hasExpDateOcrEnabled || this.expDateOcrEnabled) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.expDateOcrEnabled);
            }
            if (this.expDateEntryType != 0 || this.hasExpDateEntryType) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.expDateEntryType);
            }
            if (this.hasExpDateRecognizedByOcr || this.expDateRecognizedByOcr) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.expDateRecognizedByOcr);
            }
            if (this.hasExpDateValidationErrorOccurred || this.expDateValidationErrorOccurred) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.expDateValidationErrorOccurred);
            }
            if (this.hasNumOcrAttempts || this.numOcrAttempts != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.numOcrAttempts);
            }
            if (this.ocrExitReason != 0 || this.hasOcrExitReason) {
                return size + CodedOutputByteBufferNano.computeInt32Size(10, this.ocrExitReason);
            }
            return size;
        }

        public CreditCardEntryAction mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.panOcrEnabled = input.readBool();
                        this.hasPanOcrEnabled = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.panEntryType = value;
                                this.hasPanEntryType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.panRecognizedByOcr = input.readBool();
                        this.hasPanRecognizedByOcr = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.panValidationErrorOccurred = input.readBool();
                        this.hasPanValidationErrorOccurred = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.expDateOcrEnabled = input.readBool();
                        this.hasExpDateOcrEnabled = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.expDateEntryType = value;
                                this.hasExpDateEntryType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.expDateRecognizedByOcr = input.readBool();
                        this.hasExpDateRecognizedByOcr = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.expDateValidationErrorOccurred = input.readBool();
                        this.hasExpDateValidationErrorOccurred = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.numOcrAttempts = input.readInt32();
                        this.hasNumOcrAttempts = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                                this.ocrExitReason = value;
                                this.hasOcrExitReason = true;
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
    }

    public static final class DeviceFeature extends MessageNano {
        public DeviceFeatureInfo[] deviceFeatureInfo;

        public static final class DeviceFeatureInfo extends MessageNano {
            private static volatile DeviceFeatureInfo[] _emptyArray;
            public String featureName;
            public boolean hasFeatureName;
            public boolean hasLastConnectionTimeMs;
            public long lastConnectionTimeMs;

            public static DeviceFeatureInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new DeviceFeatureInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public DeviceFeatureInfo() {
                clear();
            }

            public DeviceFeatureInfo clear() {
                this.featureName = "";
                this.hasFeatureName = false;
                this.lastConnectionTimeMs = 0;
                this.hasLastConnectionTimeMs = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasFeatureName || !this.featureName.equals("")) {
                    output.writeString(1, this.featureName);
                }
                if (this.hasLastConnectionTimeMs || this.lastConnectionTimeMs != 0) {
                    output.writeInt64(2, this.lastConnectionTimeMs);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasFeatureName || !this.featureName.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.featureName);
                }
                if (this.hasLastConnectionTimeMs || this.lastConnectionTimeMs != 0) {
                    return size + CodedOutputByteBufferNano.computeInt64Size(2, this.lastConnectionTimeMs);
                }
                return size;
            }

            public DeviceFeatureInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            this.featureName = input.readString();
                            this.hasFeatureName = true;
                            continue;
                        case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            this.lastConnectionTimeMs = input.readInt64();
                            this.hasLastConnectionTimeMs = true;
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

        public DeviceFeature() {
            clear();
        }

        public DeviceFeature clear() {
            this.deviceFeatureInfo = DeviceFeatureInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.deviceFeatureInfo != null && this.deviceFeatureInfo.length > 0) {
                for (DeviceFeatureInfo element : this.deviceFeatureInfo) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.deviceFeatureInfo != null && this.deviceFeatureInfo.length > 0) {
                for (DeviceFeatureInfo element : this.deviceFeatureInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public DeviceFeature mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.deviceFeatureInfo == null) {
                            i = 0;
                        } else {
                            i = this.deviceFeatureInfo.length;
                        }
                        DeviceFeatureInfo[] newArray = new DeviceFeatureInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.deviceFeatureInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new DeviceFeatureInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new DeviceFeatureInfo();
                        input.readMessage(newArray[i]);
                        this.deviceFeatureInfo = newArray;
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

    public static final class NlpRepairStatus extends MessageNano {
        public int enabled;
        public int flags;
        public boolean foundTestKeys;
        public boolean hasEnabled;
        public boolean hasFlags;
        public boolean hasFoundTestKeys;
        public boolean hasHoldoffAfterInstall;
        public boolean hasHoldoffUntilBoot;
        public boolean hasHoldoffUntilWipe;
        public boolean hasRepairStatus;
        public boolean hasSignatureHash;
        public boolean hasVersionCode;
        public boolean holdoffAfterInstall;
        public boolean holdoffUntilBoot;
        public boolean holdoffUntilWipe;
        public int repairStatus;
        public String signatureHash;
        public int versionCode;

        public NlpRepairStatus() {
            clear();
        }

        public NlpRepairStatus clear() {
            this.repairStatus = 0;
            this.hasRepairStatus = false;
            this.flags = 0;
            this.hasFlags = false;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.signatureHash = "";
            this.hasSignatureHash = false;
            this.foundTestKeys = false;
            this.hasFoundTestKeys = false;
            this.holdoffUntilBoot = false;
            this.hasHoldoffUntilBoot = false;
            this.holdoffUntilWipe = false;
            this.hasHoldoffUntilWipe = false;
            this.holdoffAfterInstall = false;
            this.hasHoldoffAfterInstall = false;
            this.enabled = 0;
            this.hasEnabled = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.repairStatus != 0 || this.hasRepairStatus) {
                output.writeInt32(1, this.repairStatus);
            }
            if (this.hasFlags || this.flags != 0) {
                output.writeInt32(2, this.flags);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                output.writeInt32(3, this.versionCode);
            }
            if (this.hasSignatureHash || !this.signatureHash.equals("")) {
                output.writeString(4, this.signatureHash);
            }
            if (this.hasFoundTestKeys || this.foundTestKeys) {
                output.writeBool(5, this.foundTestKeys);
            }
            if (this.hasHoldoffUntilBoot || this.holdoffUntilBoot) {
                output.writeBool(6, this.holdoffUntilBoot);
            }
            if (this.hasHoldoffUntilWipe || this.holdoffUntilWipe) {
                output.writeBool(7, this.holdoffUntilWipe);
            }
            if (this.hasHoldoffAfterInstall || this.holdoffAfterInstall) {
                output.writeBool(8, this.holdoffAfterInstall);
            }
            if (this.hasEnabled || this.enabled != 0) {
                output.writeInt32(9, this.enabled);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.repairStatus != 0 || this.hasRepairStatus) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.repairStatus);
            }
            if (this.hasFlags || this.flags != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.flags);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.versionCode);
            }
            if (this.hasSignatureHash || !this.signatureHash.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.signatureHash);
            }
            if (this.hasFoundTestKeys || this.foundTestKeys) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.foundTestKeys);
            }
            if (this.hasHoldoffUntilBoot || this.holdoffUntilBoot) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.holdoffUntilBoot);
            }
            if (this.hasHoldoffUntilWipe || this.holdoffUntilWipe) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.holdoffUntilWipe);
            }
            if (this.hasHoldoffAfterInstall || this.holdoffAfterInstall) {
                size += CodedOutputByteBufferNano.computeBoolSize(8, this.holdoffAfterInstall);
            }
            if (this.hasEnabled || this.enabled != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(9, this.enabled);
            }
            return size;
        }

        public NlpRepairStatus mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                                this.repairStatus = value;
                                this.hasRepairStatus = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.flags = input.readInt32();
                        this.hasFlags = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.versionCode = input.readInt32();
                        this.hasVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.signatureHash = input.readString();
                        this.hasSignatureHash = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.foundTestKeys = input.readBool();
                        this.hasFoundTestKeys = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.holdoffUntilBoot = input.readBool();
                        this.hasHoldoffUntilBoot = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.holdoffUntilWipe = input.readBool();
                        this.hasHoldoffUntilWipe = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.holdoffAfterInstall = input.readBool();
                        this.hasHoldoffAfterInstall = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.enabled = input.readInt32();
                        this.hasEnabled = true;
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

    public static final class PlayStoreBackgroundActionEvent extends MessageNano {
        public AppData appData;
        public int attempts;
        public AuthContext authContext;
        public String callingPackage;
        public long clientLatencyMs;
        public CreditCardEntryAction creditCardEntryAction;
        public DeviceFeature deviceFeature;
        public String document;
        public int errorCode;
        public String exceptionType;
        public int fromSetting;
        public boolean hasAttempts;
        public boolean hasCallingPackage;
        public boolean hasClientLatencyMs;
        public boolean hasDocument;
        public boolean hasErrorCode;
        public boolean hasExceptionType;
        public boolean hasFromSetting;
        public boolean hasHost;
        public boolean hasLastUrl;
        public boolean hasOfferCheckoutFlowRequired;
        public boolean hasOfferType;
        public boolean hasOperationSuccess;
        public boolean hasReason;
        public boolean hasServerLatencyMs;
        public boolean hasServerLogsCookie;
        public boolean hasToSetting;
        public boolean hasType;
        public String host;
        public String lastUrl;
        public NlpRepairStatus nlpRepairStatus;
        public boolean offerCheckoutFlowRequired;
        public int offerType;
        public boolean operationSuccess;
        public String reason;
        public ReviewData reviewData;
        public PlayStoreRpcReport rpcReport;
        public SearchSuggestionReport searchSuggestionReport;
        public long serverLatencyMs;
        public byte[] serverLogsCookie;
        public PlayStoreSessionData sessionInfo;
        public int toSetting;
        public int type;
        public WidgetEventData widgetEventData;
        public WifiAutoUpdateAttempt wifiAutoUpdateAttempt;

        public PlayStoreBackgroundActionEvent() {
            clear();
        }

        public PlayStoreBackgroundActionEvent clear() {
            this.type = 0;
            this.hasType = false;
            this.document = "";
            this.hasDocument = false;
            this.reason = "";
            this.hasReason = false;
            this.errorCode = 0;
            this.hasErrorCode = false;
            this.exceptionType = "";
            this.hasExceptionType = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.offerType = 0;
            this.hasOfferType = false;
            this.fromSetting = 0;
            this.hasFromSetting = false;
            this.toSetting = 0;
            this.hasToSetting = false;
            this.sessionInfo = null;
            this.appData = null;
            this.serverLatencyMs = 0;
            this.hasServerLatencyMs = false;
            this.clientLatencyMs = 0;
            this.hasClientLatencyMs = false;
            this.nlpRepairStatus = null;
            this.operationSuccess = false;
            this.hasOperationSuccess = false;
            this.host = "";
            this.hasHost = false;
            this.widgetEventData = null;
            this.wifiAutoUpdateAttempt = null;
            this.attempts = 0;
            this.hasAttempts = false;
            this.offerCheckoutFlowRequired = false;
            this.hasOfferCheckoutFlowRequired = false;
            this.searchSuggestionReport = null;
            this.callingPackage = "";
            this.hasCallingPackage = false;
            this.reviewData = null;
            this.lastUrl = "";
            this.hasLastUrl = false;
            this.authContext = null;
            this.deviceFeature = null;
            this.rpcReport = null;
            this.creditCardEntryAction = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            if (this.hasDocument || !this.document.equals("")) {
                output.writeString(2, this.document);
            }
            if (this.hasReason || !this.reason.equals("")) {
                output.writeString(3, this.reason);
            }
            if (this.hasErrorCode || this.errorCode != 0) {
                output.writeInt32(4, this.errorCode);
            }
            if (this.hasExceptionType || !this.exceptionType.equals("")) {
                output.writeString(5, this.exceptionType);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.serverLogsCookie);
            }
            if (this.hasOfferType || this.offerType != 0) {
                output.writeInt32(7, this.offerType);
            }
            if (this.hasFromSetting || this.fromSetting != 0) {
                output.writeInt32(8, this.fromSetting);
            }
            if (this.hasToSetting || this.toSetting != 0) {
                output.writeInt32(9, this.toSetting);
            }
            if (this.sessionInfo != null) {
                output.writeMessage(10, this.sessionInfo);
            }
            if (this.appData != null) {
                output.writeMessage(11, this.appData);
            }
            if (this.hasServerLatencyMs || this.serverLatencyMs != 0) {
                output.writeInt64(12, this.serverLatencyMs);
            }
            if (this.hasClientLatencyMs || this.clientLatencyMs != 0) {
                output.writeInt64(13, this.clientLatencyMs);
            }
            if (this.nlpRepairStatus != null) {
                output.writeMessage(14, this.nlpRepairStatus);
            }
            if (this.hasOperationSuccess || this.operationSuccess) {
                output.writeBool(15, this.operationSuccess);
            }
            if (this.hasHost || !this.host.equals("")) {
                output.writeString(16, this.host);
            }
            if (this.widgetEventData != null) {
                output.writeMessage(17, this.widgetEventData);
            }
            if (this.wifiAutoUpdateAttempt != null) {
                output.writeMessage(18, this.wifiAutoUpdateAttempt);
            }
            if (this.hasAttempts || this.attempts != 0) {
                output.writeInt32(19, this.attempts);
            }
            if (this.hasOfferCheckoutFlowRequired || this.offerCheckoutFlowRequired) {
                output.writeBool(20, this.offerCheckoutFlowRequired);
            }
            if (this.searchSuggestionReport != null) {
                output.writeMessage(21, this.searchSuggestionReport);
            }
            if (this.hasCallingPackage || !this.callingPackage.equals("")) {
                output.writeString(23, this.callingPackage);
            }
            if (this.reviewData != null) {
                output.writeMessage(24, this.reviewData);
            }
            if (this.hasLastUrl || !this.lastUrl.equals("")) {
                output.writeString(25, this.lastUrl);
            }
            if (this.authContext != null) {
                output.writeMessage(26, this.authContext);
            }
            if (this.deviceFeature != null) {
                output.writeMessage(27, this.deviceFeature);
            }
            if (this.rpcReport != null) {
                output.writeMessage(28, this.rpcReport);
            }
            if (this.creditCardEntryAction != null) {
                output.writeMessage(29, this.creditCardEntryAction);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.hasDocument || !this.document.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.document);
            }
            if (this.hasReason || !this.reason.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.reason);
            }
            if (this.hasErrorCode || this.errorCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.errorCode);
            }
            if (this.hasExceptionType || !this.exceptionType.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.exceptionType);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.serverLogsCookie);
            }
            if (this.hasOfferType || this.offerType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.offerType);
            }
            if (this.hasFromSetting || this.fromSetting != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.fromSetting);
            }
            if (this.hasToSetting || this.toSetting != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.toSetting);
            }
            if (this.sessionInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.sessionInfo);
            }
            if (this.appData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.appData);
            }
            if (this.hasServerLatencyMs || this.serverLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(12, this.serverLatencyMs);
            }
            if (this.hasClientLatencyMs || this.clientLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(13, this.clientLatencyMs);
            }
            if (this.nlpRepairStatus != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.nlpRepairStatus);
            }
            if (this.hasOperationSuccess || this.operationSuccess) {
                size += CodedOutputByteBufferNano.computeBoolSize(15, this.operationSuccess);
            }
            if (this.hasHost || !this.host.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(16, this.host);
            }
            if (this.widgetEventData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(17, this.widgetEventData);
            }
            if (this.wifiAutoUpdateAttempt != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(18, this.wifiAutoUpdateAttempt);
            }
            if (this.hasAttempts || this.attempts != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(19, this.attempts);
            }
            if (this.hasOfferCheckoutFlowRequired || this.offerCheckoutFlowRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(20, this.offerCheckoutFlowRequired);
            }
            if (this.searchSuggestionReport != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(21, this.searchSuggestionReport);
            }
            if (this.hasCallingPackage || !this.callingPackage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(23, this.callingPackage);
            }
            if (this.reviewData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(24, this.reviewData);
            }
            if (this.hasLastUrl || !this.lastUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(25, this.lastUrl);
            }
            if (this.authContext != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(26, this.authContext);
            }
            if (this.deviceFeature != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(27, this.deviceFeature);
            }
            if (this.rpcReport != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(28, this.rpcReport);
            }
            if (this.creditCardEntryAction != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(29, this.creditCardEntryAction);
            }
            return size;
        }

        public PlayStoreBackgroundActionEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case 100:
                            case 101:
                            case 102:
                            case 103:
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                            case 109:
                            case 110:
                            case 111:
                            case 112:
                            case 113:
                            case 114:
                            case 115:
                            case 116:
                            case 117:
                            case 118:
                            case 119:
                            case 120:
                            case 121:
                            case 122:
                            case 123:
                            case 124:
                            case 125:
                            case 126:
                            case 127:
                            case 200:
                            case 201:
                            case 202:
                            case 300:
                            case 301:
                            case 302:
                            case 303:
                            case 304:
                            case 305:
                            case 306:
                            case 320:
                            case 321:
                            case 322:
                            case 323:
                            case 324:
                            case 330:
                            case 331:
                            case 332:
                            case 335:
                            case 336:
                            case 337:
                            case 340:
                            case 341:
                            case 342:
                            case 350:
                            case 351:
                            case 352:
                            case 353:
                            case 354:
                            case 355:
                            case 356:
                            case 357:
                            case 360:
                            case 361:
                            case 362:
                            case 363:
                            case 364:
                            case 365:
                            case 366:
                            case 380:
                            case 381:
                            case 382:
                            case 383:
                            case 384:
                            case 400:
                            case 401:
                            case 402:
                            case 403:
                            case 404:
                            case 406:
                            case 500:
                            case 501:
                            case 502:
                            case 503:
                            case 504:
                            case 505:
                            case 506:
                            case 507:
                            case 508:
                            case 509:
                            case 510:
                            case 511:
                            case 512:
                            case 513:
                            case 514:
                            case 515:
                            case 516:
                            case 517:
                            case 518:
                            case 600:
                            case 601:
                            case 700:
                            case 701:
                            case 702:
                            case 710:
                            case 711:
                            case 712:
                            case 713:
                            case 720:
                            case 721:
                            case 722:
                            case 723:
                            case 770:
                            case 800:
                            case 801:
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.document = input.readString();
                        this.hasDocument = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.reason = input.readString();
                        this.hasReason = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.errorCode = input.readInt32();
                        this.hasErrorCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.exceptionType = input.readString();
                        this.hasExceptionType = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.offerType = input.readInt32();
                        this.hasOfferType = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.fromSetting = input.readInt32();
                        this.hasFromSetting = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.toSetting = input.readInt32();
                        this.hasToSetting = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.sessionInfo == null) {
                            this.sessionInfo = new PlayStoreSessionData();
                        }
                        input.readMessage(this.sessionInfo);
                        continue;
                    case 90:
                        if (this.appData == null) {
                            this.appData = new AppData();
                        }
                        input.readMessage(this.appData);
                        continue;
                    case 96:
                        this.serverLatencyMs = input.readInt64();
                        this.hasServerLatencyMs = true;
                        continue;
                    case 104:
                        this.clientLatencyMs = input.readInt64();
                        this.hasClientLatencyMs = true;
                        continue;
                    case 114:
                        if (this.nlpRepairStatus == null) {
                            this.nlpRepairStatus = new NlpRepairStatus();
                        }
                        input.readMessage(this.nlpRepairStatus);
                        continue;
                    case 120:
                        this.operationSuccess = input.readBool();
                        this.hasOperationSuccess = true;
                        continue;
                    case 130:
                        this.host = input.readString();
                        this.hasHost = true;
                        continue;
                    case 138:
                        if (this.widgetEventData == null) {
                            this.widgetEventData = new WidgetEventData();
                        }
                        input.readMessage(this.widgetEventData);
                        continue;
                    case 146:
                        if (this.wifiAutoUpdateAttempt == null) {
                            this.wifiAutoUpdateAttempt = new WifiAutoUpdateAttempt();
                        }
                        input.readMessage(this.wifiAutoUpdateAttempt);
                        continue;
                    case 152:
                        this.attempts = input.readInt32();
                        this.hasAttempts = true;
                        continue;
                    case 160:
                        this.offerCheckoutFlowRequired = input.readBool();
                        this.hasOfferCheckoutFlowRequired = true;
                        continue;
                    case 170:
                        if (this.searchSuggestionReport == null) {
                            this.searchSuggestionReport = new SearchSuggestionReport();
                        }
                        input.readMessage(this.searchSuggestionReport);
                        continue;
                    case 186:
                        this.callingPackage = input.readString();
                        this.hasCallingPackage = true;
                        continue;
                    case 194:
                        if (this.reviewData == null) {
                            this.reviewData = new ReviewData();
                        }
                        input.readMessage(this.reviewData);
                        continue;
                    case 202:
                        this.lastUrl = input.readString();
                        this.hasLastUrl = true;
                        continue;
                    case 210:
                        if (this.authContext == null) {
                            this.authContext = new AuthContext();
                        }
                        input.readMessage(this.authContext);
                        continue;
                    case 218:
                        if (this.deviceFeature == null) {
                            this.deviceFeature = new DeviceFeature();
                        }
                        input.readMessage(this.deviceFeature);
                        continue;
                    case 226:
                        if (this.rpcReport == null) {
                            this.rpcReport = new PlayStoreRpcReport();
                        }
                        input.readMessage(this.rpcReport);
                        continue;
                    case 234:
                        if (this.creditCardEntryAction == null) {
                            this.creditCardEntryAction = new CreditCardEntryAction();
                        }
                        input.readMessage(this.creditCardEntryAction);
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

    public static final class PlayStoreClickEvent extends MessageNano {
        public PlayStoreUiElement[] elementPath;

        public PlayStoreClickEvent() {
            clear();
        }

        public PlayStoreClickEvent clear() {
            this.elementPath = PlayStoreUiElement.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.elementPath != null && this.elementPath.length > 0) {
                for (PlayStoreUiElement element : this.elementPath) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.elementPath != null && this.elementPath.length > 0) {
                for (PlayStoreUiElement element : this.elementPath) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public PlayStoreClickEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.elementPath == null) {
                            i = 0;
                        } else {
                            i = this.elementPath.length;
                        }
                        PlayStoreUiElement[] newArray = new PlayStoreUiElement[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.elementPath, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new PlayStoreUiElement();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new PlayStoreUiElement();
                        input.readMessage(newArray[i]);
                        this.elementPath = newArray;
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

    public static final class PlayStoreDeepLinkEvent extends MessageNano {
        public boolean canResolve;
        public String externalReferrer;
        public String externalUrl;
        public boolean hasCanResolve;
        public boolean hasExternalReferrer;
        public boolean hasExternalUrl;
        public boolean hasMinVersion;
        public boolean hasNewEnough;
        public boolean hasPackageName;
        public boolean hasResolvedType;
        public boolean hasServerLogsCookie;
        public int minVersion;
        public boolean newEnough;
        public String packageName;
        public int resolvedType;
        public byte[] serverLogsCookie;

        public PlayStoreDeepLinkEvent() {
            clear();
        }

        public PlayStoreDeepLinkEvent clear() {
            this.externalUrl = "";
            this.hasExternalUrl = false;
            this.resolvedType = 0;
            this.hasResolvedType = false;
            this.packageName = "";
            this.hasPackageName = false;
            this.minVersion = 0;
            this.hasMinVersion = false;
            this.newEnough = false;
            this.hasNewEnough = false;
            this.canResolve = false;
            this.hasCanResolve = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.externalReferrer = "";
            this.hasExternalReferrer = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasExternalUrl || !this.externalUrl.equals("")) {
                output.writeString(1, this.externalUrl);
            }
            if (this.resolvedType != 0 || this.hasResolvedType) {
                output.writeInt32(2, this.resolvedType);
            }
            if (this.hasPackageName || !this.packageName.equals("")) {
                output.writeString(3, this.packageName);
            }
            if (this.hasMinVersion || this.minVersion != 0) {
                output.writeInt32(4, this.minVersion);
            }
            if (this.hasNewEnough || this.newEnough) {
                output.writeBool(5, this.newEnough);
            }
            if (this.hasCanResolve || this.canResolve) {
                output.writeBool(6, this.canResolve);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.serverLogsCookie);
            }
            if (this.hasExternalReferrer || !this.externalReferrer.equals("")) {
                output.writeString(8, this.externalReferrer);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasExternalUrl || !this.externalUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.externalUrl);
            }
            if (this.resolvedType != 0 || this.hasResolvedType) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.resolvedType);
            }
            if (this.hasPackageName || !this.packageName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.packageName);
            }
            if (this.hasMinVersion || this.minVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.minVersion);
            }
            if (this.hasNewEnough || this.newEnough) {
                size += CodedOutputByteBufferNano.computeBoolSize(5, this.newEnough);
            }
            if (this.hasCanResolve || this.canResolve) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.canResolve);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(7, this.serverLogsCookie);
            }
            if (this.hasExternalReferrer || !this.externalReferrer.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(8, this.externalReferrer);
            }
            return size;
        }

        public PlayStoreDeepLinkEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.externalUrl = input.readString();
                        this.hasExternalUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                                this.resolvedType = value;
                                this.hasResolvedType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.packageName = input.readString();
                        this.hasPackageName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.minVersion = input.readInt32();
                        this.hasMinVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.newEnough = input.readBool();
                        this.hasNewEnough = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.canResolve = input.readBool();
                        this.hasCanResolve = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.externalReferrer = input.readString();
                        this.hasExternalReferrer = true;
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

    public static final class PlayStoreImpressionEvent extends MessageNano {
        public boolean hasId;
        public long id;
        public PlayStoreUiElement[] referrerPath;
        public PlayStoreUiElement tree;

        public PlayStoreImpressionEvent() {
            clear();
        }

        public PlayStoreImpressionEvent clear() {
            this.tree = null;
            this.referrerPath = PlayStoreUiElement.emptyArray();
            this.id = 0;
            this.hasId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.tree != null) {
                output.writeMessage(1, this.tree);
            }
            if (this.referrerPath != null && this.referrerPath.length > 0) {
                for (PlayStoreUiElement element : this.referrerPath) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.hasId || this.id != 0) {
                output.writeInt64(3, this.id);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.tree != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.tree);
            }
            if (this.referrerPath != null && this.referrerPath.length > 0) {
                for (PlayStoreUiElement element : this.referrerPath) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.hasId || this.id != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(3, this.id);
            }
            return size;
        }

        public PlayStoreImpressionEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.tree == null) {
                            this.tree = new PlayStoreUiElement();
                        }
                        input.readMessage(this.tree);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.referrerPath == null) {
                            i = 0;
                        } else {
                            i = this.referrerPath.length;
                        }
                        PlayStoreUiElement[] newArray = new PlayStoreUiElement[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.referrerPath, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new PlayStoreUiElement();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new PlayStoreUiElement();
                        input.readMessage(newArray[i]);
                        this.referrerPath = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.id = input.readInt64();
                        this.hasId = true;
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

    public static final class PlayStoreLogEvent extends MessageNano {
        public PlayStoreBackgroundActionEvent backgroundAction;
        public PlayStoreClickEvent click;
        public PlayStoreDeepLinkEvent deepLink;
        public PlayStoreImpressionEvent impression;
        public PlayStoreSearchEvent search;

        public PlayStoreLogEvent() {
            clear();
        }

        public PlayStoreLogEvent clear() {
            this.impression = null;
            this.click = null;
            this.backgroundAction = null;
            this.search = null;
            this.deepLink = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.impression != null) {
                output.writeMessage(1, this.impression);
            }
            if (this.click != null) {
                output.writeMessage(3, this.click);
            }
            if (this.backgroundAction != null) {
                output.writeMessage(4, this.backgroundAction);
            }
            if (this.search != null) {
                output.writeMessage(5, this.search);
            }
            if (this.deepLink != null) {
                output.writeMessage(6, this.deepLink);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.impression != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.impression);
            }
            if (this.click != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.click);
            }
            if (this.backgroundAction != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.backgroundAction);
            }
            if (this.search != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.search);
            }
            if (this.deepLink != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(6, this.deepLink);
            }
            return size;
        }

        public PlayStoreLogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.impression == null) {
                            this.impression = new PlayStoreImpressionEvent();
                        }
                        input.readMessage(this.impression);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.click == null) {
                            this.click = new PlayStoreClickEvent();
                        }
                        input.readMessage(this.click);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.backgroundAction == null) {
                            this.backgroundAction = new PlayStoreBackgroundActionEvent();
                        }
                        input.readMessage(this.backgroundAction);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.search == null) {
                            this.search = new PlayStoreSearchEvent();
                        }
                        input.readMessage(this.search);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.deepLink == null) {
                            this.deepLink = new PlayStoreDeepLinkEvent();
                        }
                        input.readMessage(this.deepLink);
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

    public static final class PlayStoreRpcReport extends MessageNano {
        public float backoffMultiplier;
        public long clientLatencyMs;
        public int endConnectionType;
        public boolean hasBackoffMultiplier;
        public boolean hasClientLatencyMs;
        public boolean hasEndConnectionType;
        public boolean hasNumAttempts;
        public boolean hasResponseBodySizeBytes;
        public boolean hasServerLatencyMs;
        public boolean hasStartConnectionType;
        public boolean hasTimeoutMs;
        public boolean hasUrl;
        public boolean hasVolleyErrorType;
        public boolean hasWasSuccessful;
        public int numAttempts;
        public int responseBodySizeBytes;
        public long serverLatencyMs;
        public int startConnectionType;
        public int timeoutMs;
        public String url;
        public int volleyErrorType;
        public boolean wasSuccessful;

        public PlayStoreRpcReport() {
            clear();
        }

        public PlayStoreRpcReport clear() {
            this.url = "";
            this.hasUrl = false;
            this.clientLatencyMs = 0;
            this.hasClientLatencyMs = false;
            this.serverLatencyMs = 0;
            this.hasServerLatencyMs = false;
            this.numAttempts = 0;
            this.hasNumAttempts = false;
            this.timeoutMs = 0;
            this.hasTimeoutMs = false;
            this.backoffMultiplier = 0.0f;
            this.hasBackoffMultiplier = false;
            this.wasSuccessful = false;
            this.hasWasSuccessful = false;
            this.startConnectionType = 0;
            this.hasStartConnectionType = false;
            this.endConnectionType = 0;
            this.hasEndConnectionType = false;
            this.responseBodySizeBytes = 0;
            this.hasResponseBodySizeBytes = false;
            this.volleyErrorType = 0;
            this.hasVolleyErrorType = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUrl || !this.url.equals("")) {
                output.writeString(1, this.url);
            }
            if (this.hasClientLatencyMs || this.clientLatencyMs != 0) {
                output.writeUInt64(2, this.clientLatencyMs);
            }
            if (this.hasServerLatencyMs || this.serverLatencyMs != 0) {
                output.writeUInt64(3, this.serverLatencyMs);
            }
            if (this.hasNumAttempts || this.numAttempts != 0) {
                output.writeUInt32(4, this.numAttempts);
            }
            if (this.hasTimeoutMs || this.timeoutMs != 0) {
                output.writeUInt32(5, this.timeoutMs);
            }
            if (this.hasBackoffMultiplier || Float.floatToIntBits(this.backoffMultiplier) != Float.floatToIntBits(0.0f)) {
                output.writeFloat(6, this.backoffMultiplier);
            }
            if (this.hasWasSuccessful || this.wasSuccessful) {
                output.writeBool(7, this.wasSuccessful);
            }
            if (this.startConnectionType != 0 || this.hasStartConnectionType) {
                output.writeInt32(8, this.startConnectionType);
            }
            if (this.endConnectionType != 0 || this.hasEndConnectionType) {
                output.writeInt32(9, this.endConnectionType);
            }
            if (this.hasResponseBodySizeBytes || this.responseBodySizeBytes != 0) {
                output.writeUInt32(10, this.responseBodySizeBytes);
            }
            if (this.volleyErrorType != 0 || this.hasVolleyErrorType) {
                output.writeInt32(11, this.volleyErrorType);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUrl || !this.url.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
            }
            if (this.hasClientLatencyMs || this.clientLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(2, this.clientLatencyMs);
            }
            if (this.hasServerLatencyMs || this.serverLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeUInt64Size(3, this.serverLatencyMs);
            }
            if (this.hasNumAttempts || this.numAttempts != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(4, this.numAttempts);
            }
            if (this.hasTimeoutMs || this.timeoutMs != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(5, this.timeoutMs);
            }
            if (this.hasBackoffMultiplier || Float.floatToIntBits(this.backoffMultiplier) != Float.floatToIntBits(0.0f)) {
                size += CodedOutputByteBufferNano.computeFloatSize(6, this.backoffMultiplier);
            }
            if (this.hasWasSuccessful || this.wasSuccessful) {
                size += CodedOutputByteBufferNano.computeBoolSize(7, this.wasSuccessful);
            }
            if (this.startConnectionType != 0 || this.hasStartConnectionType) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.startConnectionType);
            }
            if (this.endConnectionType != 0 || this.hasEndConnectionType) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.endConnectionType);
            }
            if (this.hasResponseBodySizeBytes || this.responseBodySizeBytes != 0) {
                size += CodedOutputByteBufferNano.computeUInt32Size(10, this.responseBodySizeBytes);
            }
            if (this.volleyErrorType != 0 || this.hasVolleyErrorType) {
                return size + CodedOutputByteBufferNano.computeInt32Size(11, this.volleyErrorType);
            }
            return size;
        }

        public PlayStoreRpcReport mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.url = input.readString();
                        this.hasUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.clientLatencyMs = input.readUInt64();
                        this.hasClientLatencyMs = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.serverLatencyMs = input.readUInt64();
                        this.hasServerLatencyMs = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.numAttempts = input.readUInt32();
                        this.hasNumAttempts = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.timeoutMs = input.readUInt32();
                        this.hasTimeoutMs = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_toolbarNavigationButtonStyle /*53*/:
                        this.backoffMultiplier = input.readFloat();
                        this.hasBackoffMultiplier = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.wasSuccessful = input.readBool();
                        this.hasWasSuccessful = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                this.startConnectionType = value;
                                this.hasStartConnectionType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                this.endConnectionType = value;
                                this.hasEndConnectionType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.responseBodySizeBytes = input.readUInt32();
                        this.hasResponseBodySizeBytes = true;
                        continue;
                    case 88:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                                this.volleyErrorType = value;
                                this.hasVolleyErrorType = true;
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
    }

    public static final class PlayStoreSearchEvent extends MessageNano {
        public boolean hasQuery;
        public boolean hasQueryUrl;
        public boolean hasReferrerUrl;
        public String query;
        public String queryUrl;
        public String referrerUrl;

        public PlayStoreSearchEvent() {
            clear();
        }

        public PlayStoreSearchEvent clear() {
            this.query = "";
            this.hasQuery = false;
            this.queryUrl = "";
            this.hasQueryUrl = false;
            this.referrerUrl = "";
            this.hasReferrerUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasQuery || !this.query.equals("")) {
                output.writeString(1, this.query);
            }
            if (this.hasQueryUrl || !this.queryUrl.equals("")) {
                output.writeString(2, this.queryUrl);
            }
            if (this.hasReferrerUrl || !this.referrerUrl.equals("")) {
                output.writeString(3, this.referrerUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasQuery || !this.query.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.query);
            }
            if (this.hasQueryUrl || !this.queryUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.queryUrl);
            }
            if (this.hasReferrerUrl || !this.referrerUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.referrerUrl);
            }
            return size;
        }

        public PlayStoreSearchEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.query = input.readString();
                        this.hasQuery = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.queryUrl = input.readString();
                        this.hasQueryUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.referrerUrl = input.readString();
                        this.hasReferrerUrl = true;
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

    public static final class PlayStoreSessionData extends MessageNano {
        public boolean allowUnknownSources;
        public int autoUpdateCleanupDialogNumTimesShown;
        public int contentFilterLevel;
        public int downloadDataDirSizeMb;
        public boolean gaiaPasswordAuthOptedOut;
        public boolean globalAutoUpdateEnabled;
        public boolean globalAutoUpdateOverWifiOnly;
        public boolean hasAllowUnknownSources;
        public boolean hasAutoUpdateCleanupDialogNumTimesShown;
        public boolean hasContentFilterLevel;
        public boolean hasDownloadDataDirSizeMb;
        public boolean hasGaiaPasswordAuthOptedOut;
        public boolean hasGlobalAutoUpdateEnabled;
        public boolean hasGlobalAutoUpdateOverWifiOnly;
        public boolean hasNetworkSubType;
        public boolean hasNetworkType;
        public boolean hasNumAccountsOnDevice;
        public boolean hasNumAutoUpdatingInstalledApps;
        public boolean hasNumInstalledApps;
        public boolean hasNumInstalledAppsNotAutoUpdating;
        public boolean hasPurchaseAuthType;
        public boolean hasRecommendedMaxDownloadOverMobileBytes;
        public int networkSubType;
        public int networkType;
        public int numAccountsOnDevice;
        public int numAutoUpdatingInstalledApps;
        public int numInstalledApps;
        public int numInstalledAppsNotAutoUpdating;
        public PromptForFopData promptForFopData;
        public int purchaseAuthType;
        public long recommendedMaxDownloadOverMobileBytes;

        public PlayStoreSessionData() {
            clear();
        }

        public PlayStoreSessionData clear() {
            this.globalAutoUpdateEnabled = false;
            this.hasGlobalAutoUpdateEnabled = false;
            this.globalAutoUpdateOverWifiOnly = false;
            this.hasGlobalAutoUpdateOverWifiOnly = false;
            this.autoUpdateCleanupDialogNumTimesShown = 0;
            this.hasAutoUpdateCleanupDialogNumTimesShown = false;
            this.networkType = 0;
            this.hasNetworkType = false;
            this.networkSubType = 0;
            this.hasNetworkSubType = false;
            this.numAccountsOnDevice = 0;
            this.hasNumAccountsOnDevice = false;
            this.numInstalledApps = 0;
            this.hasNumInstalledApps = false;
            this.numAutoUpdatingInstalledApps = 0;
            this.hasNumAutoUpdatingInstalledApps = false;
            this.numInstalledAppsNotAutoUpdating = 0;
            this.hasNumInstalledAppsNotAutoUpdating = false;
            this.gaiaPasswordAuthOptedOut = false;
            this.hasGaiaPasswordAuthOptedOut = false;
            this.contentFilterLevel = 0;
            this.hasContentFilterLevel = false;
            this.allowUnknownSources = false;
            this.hasAllowUnknownSources = false;
            this.promptForFopData = null;
            this.purchaseAuthType = 0;
            this.hasPurchaseAuthType = false;
            this.downloadDataDirSizeMb = 0;
            this.hasDownloadDataDirSizeMb = false;
            this.recommendedMaxDownloadOverMobileBytes = 0;
            this.hasRecommendedMaxDownloadOverMobileBytes = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasGlobalAutoUpdateEnabled || this.globalAutoUpdateEnabled) {
                output.writeBool(1, this.globalAutoUpdateEnabled);
            }
            if (this.hasGlobalAutoUpdateOverWifiOnly || this.globalAutoUpdateOverWifiOnly) {
                output.writeBool(2, this.globalAutoUpdateOverWifiOnly);
            }
            if (this.hasAutoUpdateCleanupDialogNumTimesShown || this.autoUpdateCleanupDialogNumTimesShown != 0) {
                output.writeInt32(3, this.autoUpdateCleanupDialogNumTimesShown);
            }
            if (this.hasNetworkType || this.networkType != 0) {
                output.writeInt32(4, this.networkType);
            }
            if (this.hasNetworkSubType || this.networkSubType != 0) {
                output.writeInt32(5, this.networkSubType);
            }
            if (this.hasNumAccountsOnDevice || this.numAccountsOnDevice != 0) {
                output.writeInt32(6, this.numAccountsOnDevice);
            }
            if (this.hasNumInstalledApps || this.numInstalledApps != 0) {
                output.writeInt32(7, this.numInstalledApps);
            }
            if (this.hasNumAutoUpdatingInstalledApps || this.numAutoUpdatingInstalledApps != 0) {
                output.writeInt32(8, this.numAutoUpdatingInstalledApps);
            }
            if (this.hasNumInstalledAppsNotAutoUpdating || this.numInstalledAppsNotAutoUpdating != 0) {
                output.writeInt32(9, this.numInstalledAppsNotAutoUpdating);
            }
            if (this.hasGaiaPasswordAuthOptedOut || this.gaiaPasswordAuthOptedOut) {
                output.writeBool(10, this.gaiaPasswordAuthOptedOut);
            }
            if (this.hasContentFilterLevel || this.contentFilterLevel != 0) {
                output.writeInt32(11, this.contentFilterLevel);
            }
            if (this.hasAllowUnknownSources || this.allowUnknownSources) {
                output.writeBool(12, this.allowUnknownSources);
            }
            if (this.promptForFopData != null) {
                output.writeMessage(13, this.promptForFopData);
            }
            if (this.hasPurchaseAuthType || this.purchaseAuthType != 0) {
                output.writeInt32(14, this.purchaseAuthType);
            }
            if (this.hasDownloadDataDirSizeMb || this.downloadDataDirSizeMb != 0) {
                output.writeInt32(15, this.downloadDataDirSizeMb);
            }
            if (this.hasRecommendedMaxDownloadOverMobileBytes || this.recommendedMaxDownloadOverMobileBytes != 0) {
                output.writeInt64(16, this.recommendedMaxDownloadOverMobileBytes);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasGlobalAutoUpdateEnabled || this.globalAutoUpdateEnabled) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.globalAutoUpdateEnabled);
            }
            if (this.hasGlobalAutoUpdateOverWifiOnly || this.globalAutoUpdateOverWifiOnly) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.globalAutoUpdateOverWifiOnly);
            }
            if (this.hasAutoUpdateCleanupDialogNumTimesShown || this.autoUpdateCleanupDialogNumTimesShown != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.autoUpdateCleanupDialogNumTimesShown);
            }
            if (this.hasNetworkType || this.networkType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.networkType);
            }
            if (this.hasNetworkSubType || this.networkSubType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.networkSubType);
            }
            if (this.hasNumAccountsOnDevice || this.numAccountsOnDevice != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.numAccountsOnDevice);
            }
            if (this.hasNumInstalledApps || this.numInstalledApps != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.numInstalledApps);
            }
            if (this.hasNumAutoUpdatingInstalledApps || this.numAutoUpdatingInstalledApps != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.numAutoUpdatingInstalledApps);
            }
            if (this.hasNumInstalledAppsNotAutoUpdating || this.numInstalledAppsNotAutoUpdating != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(9, this.numInstalledAppsNotAutoUpdating);
            }
            if (this.hasGaiaPasswordAuthOptedOut || this.gaiaPasswordAuthOptedOut) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.gaiaPasswordAuthOptedOut);
            }
            if (this.hasContentFilterLevel || this.contentFilterLevel != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(11, this.contentFilterLevel);
            }
            if (this.hasAllowUnknownSources || this.allowUnknownSources) {
                size += CodedOutputByteBufferNano.computeBoolSize(12, this.allowUnknownSources);
            }
            if (this.promptForFopData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(13, this.promptForFopData);
            }
            if (this.hasPurchaseAuthType || this.purchaseAuthType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(14, this.purchaseAuthType);
            }
            if (this.hasDownloadDataDirSizeMb || this.downloadDataDirSizeMb != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(15, this.downloadDataDirSizeMb);
            }
            if (this.hasRecommendedMaxDownloadOverMobileBytes || this.recommendedMaxDownloadOverMobileBytes != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(16, this.recommendedMaxDownloadOverMobileBytes);
            }
            return size;
        }

        public PlayStoreSessionData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.globalAutoUpdateEnabled = input.readBool();
                        this.hasGlobalAutoUpdateEnabled = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.globalAutoUpdateOverWifiOnly = input.readBool();
                        this.hasGlobalAutoUpdateOverWifiOnly = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.autoUpdateCleanupDialogNumTimesShown = input.readInt32();
                        this.hasAutoUpdateCleanupDialogNumTimesShown = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.networkType = input.readInt32();
                        this.hasNetworkType = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.networkSubType = input.readInt32();
                        this.hasNetworkSubType = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.numAccountsOnDevice = input.readInt32();
                        this.hasNumAccountsOnDevice = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.numInstalledApps = input.readInt32();
                        this.hasNumInstalledApps = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.numAutoUpdatingInstalledApps = input.readInt32();
                        this.hasNumAutoUpdatingInstalledApps = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.numInstalledAppsNotAutoUpdating = input.readInt32();
                        this.hasNumInstalledAppsNotAutoUpdating = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.gaiaPasswordAuthOptedOut = input.readBool();
                        this.hasGaiaPasswordAuthOptedOut = true;
                        continue;
                    case 88:
                        this.contentFilterLevel = input.readInt32();
                        this.hasContentFilterLevel = true;
                        continue;
                    case 96:
                        this.allowUnknownSources = input.readBool();
                        this.hasAllowUnknownSources = true;
                        continue;
                    case 106:
                        if (this.promptForFopData == null) {
                            this.promptForFopData = new PromptForFopData();
                        }
                        input.readMessage(this.promptForFopData);
                        continue;
                    case 112:
                        this.purchaseAuthType = input.readInt32();
                        this.hasPurchaseAuthType = true;
                        continue;
                    case 120:
                        this.downloadDataDirSizeMb = input.readInt32();
                        this.hasDownloadDataDirSizeMb = true;
                        continue;
                    case 128:
                        this.recommendedMaxDownloadOverMobileBytes = input.readInt64();
                        this.hasRecommendedMaxDownloadOverMobileBytes = true;
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

    public static final class PlayStoreUiElement extends MessageNano {
        private static volatile PlayStoreUiElement[] _emptyArray;
        public PlayStoreUiElement[] child;
        public PlayStoreUiElementInfo clientLogsCookie;
        public boolean hasServerLogsCookie;
        public boolean hasType;
        public byte[] serverLogsCookie;
        public int type;

        public static PlayStoreUiElement[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new PlayStoreUiElement[0];
                    }
                }
            }
            return _emptyArray;
        }

        public PlayStoreUiElement() {
            clear();
        }

        public PlayStoreUiElement clear() {
            this.type = 0;
            this.hasType = false;
            this.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasServerLogsCookie = false;
            this.clientLogsCookie = null;
            this.child = emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.type != 0 || this.hasType) {
                output.writeInt32(1, this.type);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.serverLogsCookie);
            }
            if (this.clientLogsCookie != null) {
                output.writeMessage(3, this.clientLogsCookie);
            }
            if (this.child != null && this.child.length > 0) {
                for (PlayStoreUiElement element : this.child) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.type != 0 || this.hasType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.type);
            }
            if (this.hasServerLogsCookie || !Arrays.equals(this.serverLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.serverLogsCookie);
            }
            if (this.clientLogsCookie != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.clientLogsCookie);
            }
            if (this.child != null && this.child.length > 0) {
                for (PlayStoreUiElement element : this.child) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            return size;
        }

        public PlayStoreUiElement mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case 100:
                            case 101:
                            case 102:
                            case 103:
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                            case 109:
                            case 110:
                            case 111:
                            case 112:
                            case 113:
                            case 114:
                            case 115:
                            case 116:
                            case 117:
                            case 118:
                            case 119:
                            case 120:
                            case 121:
                            case 122:
                            case 123:
                            case 124:
                            case 125:
                            case 126:
                            case 127:
                            case 128:
                            case 200:
                            case 201:
                            case 202:
                            case 203:
                            case 204:
                            case 205:
                            case 206:
                            case 207:
                            case 208:
                            case 209:
                            case 210:
                            case 211:
                            case 212:
                            case 213:
                            case 214:
                            case 215:
                            case 216:
                            case 217:
                            case 218:
                            case 219:
                            case 220:
                            case 221:
                            case 222:
                            case 223:
                            case 224:
                            case 225:
                            case 226:
                            case 227:
                            case 228:
                            case 229:
                            case 230:
                            case 231:
                            case 232:
                            case 233:
                            case 234:
                            case 235:
                            case 236:
                            case 237:
                            case 238:
                            case 239:
                            case 240:
                            case 241:
                            case 242:
                            case 243:
                            case 244:
                            case 245:
                            case 246:
                            case 247:
                            case 248:
                            case 249:
                            case 250:
                            case 251:
                            case 252:
                            case 253:
                            case 254:
                            case 255:
                            case 256:
                            case 257:
                            case 258:
                            case 259:
                            case 260:
                            case 261:
                            case 262:
                            case 263:
                            case 264:
                            case 265:
                            case 266:
                            case 267:
                            case 268:
                            case 269:
                            case 270:
                            case 271:
                            case 272:
                            case 273:
                            case 274:
                            case 275:
                            case 276:
                            case 277:
                            case 278:
                            case 279:
                            case 280:
                            case 281:
                            case 282:
                            case 283:
                            case 284:
                            case 285:
                            case 286:
                            case 287:
                            case 288:
                            case 289:
                            case 290:
                            case 291:
                            case 292:
                            case 293:
                            case 294:
                            case 300:
                            case 301:
                            case 302:
                            case 303:
                            case 304:
                            case 305:
                            case 306:
                            case 307:
                            case 308:
                            case 309:
                            case 310:
                            case 311:
                            case 312:
                            case 313:
                            case 314:
                            case 315:
                            case 316:
                            case 317:
                            case 318:
                            case 319:
                            case 320:
                            case 400:
                            case 401:
                            case 402:
                            case 403:
                            case 404:
                            case 405:
                            case 406:
                            case 407:
                            case 408:
                            case 409:
                            case 410:
                            case 411:
                            case 412:
                            case 413:
                            case 414:
                            case 415:
                            case 416:
                            case 417:
                            case 418:
                            case 500:
                            case 501:
                            case 502:
                            case 503:
                            case 504:
                            case 505:
                            case 506:
                            case 507:
                            case 508:
                            case 509:
                            case 510:
                            case 511:
                            case 512:
                            case 513:
                            case 514:
                            case 515:
                            case 516:
                            case 517:
                            case 518:
                            case 600:
                            case 601:
                            case 602:
                            case 603:
                            case 700:
                            case 710:
                            case 711:
                            case 712:
                            case 713:
                            case 714:
                            case 715:
                            case 716:
                            case 717:
                            case 718:
                            case 719:
                            case 740:
                            case 750:
                            case 751:
                            case 752:
                            case 753:
                            case 754:
                            case 770:
                            case 771:
                            case 775:
                            case 776:
                            case 777:
                            case 778:
                            case 780:
                            case 781:
                            case 782:
                            case 790:
                            case 791:
                            case 792:
                            case 793:
                            case 800:
                            case 801:
                            case 802:
                            case 810:
                            case 811:
                            case 812:
                            case 813:
                            case 814:
                            case 820:
                            case 821:
                            case 822:
                            case 823:
                            case 824:
                            case 825:
                            case 840:
                            case 841:
                            case 842:
                            case 843:
                            case 844:
                            case 845:
                            case 846:
                            case 847:
                            case 848:
                            case 849:
                            case 860:
                            case 861:
                            case 862:
                            case 863:
                            case 864:
                            case 865:
                            case 866:
                            case 880:
                            case 881:
                            case 882:
                            case 883:
                            case 884:
                            case 885:
                            case 886:
                            case 887:
                            case 888:
                            case 889:
                            case 890:
                            case 891:
                            case 892:
                            case 893:
                            case 894:
                            case 895:
                            case 896:
                            case 897:
                            case 898:
                            case 900:
                            case 901:
                            case 902:
                            case 903:
                            case 904:
                            case 1000:
                            case 1001:
                            case 1002:
                            case 1003:
                            case 1004:
                            case 1005:
                            case 1006:
                            case 1007:
                            case 1008:
                            case 1009:
                            case 1010:
                            case 1101:
                            case 1102:
                            case 1103:
                            case 1104:
                            case 1105:
                            case 1106:
                            case 1107:
                            case 1200:
                            case 1201:
                            case 1202:
                            case 1203:
                            case 1204:
                            case 1205:
                            case 1206:
                            case 1207:
                            case 1208:
                            case 1220:
                            case 1221:
                            case 1230:
                            case 1231:
                            case 1232:
                            case 1233:
                            case 1234:
                            case 1250:
                            case 1251:
                            case 1252:
                            case 1270:
                            case 1271:
                            case 1280:
                            case 1281:
                            case 1400:
                            case 1401:
                            case 1402:
                            case 1403:
                            case 1404:
                            case 1405:
                            case 1406:
                            case 1407:
                            case 1408:
                            case 1409:
                            case 1500:
                            case 1501:
                            case 1502:
                            case 1530:
                            case 1531:
                            case 1532:
                            case 1600:
                            case 1620:
                            case 1621:
                            case 1622:
                            case 1623:
                            case 1624:
                            case 1625:
                            case 1626:
                            case 1627:
                            case 1628:
                            case 1629:
                            case 1650:
                            case 1651:
                            case 1652:
                            case 1653:
                            case 1665:
                            case 1666:
                            case 1667:
                            case 1668:
                            case 1680:
                            case 1681:
                            case 1700:
                            case 1800:
                            case 1801:
                            case 1802:
                            case 1803:
                            case 1804:
                            case 1805:
                            case 1806:
                            case 1820:
                            case 2500:
                            case 2501:
                            case 2502:
                            case 2503:
                            case 2504:
                            case 2505:
                            case 2506:
                            case 2507:
                            case 2508:
                            case 2509:
                            case 2510:
                            case 2600:
                            case 2601:
                            case 2602:
                            case 2603:
                            case 2604:
                            case 2605:
                            case 2620:
                            case 2621:
                            case 2622:
                            case 2623:
                            case 2624:
                            case 2625:
                                this.type = value;
                                this.hasType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.serverLogsCookie = input.readBytes();
                        this.hasServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.clientLogsCookie == null) {
                            this.clientLogsCookie = new PlayStoreUiElementInfo();
                        }
                        input.readMessage(this.clientLogsCookie);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.child == null) {
                            i = 0;
                        } else {
                            i = this.child.length;
                        }
                        PlayStoreUiElement[] newArray = new PlayStoreUiElement[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.child, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new PlayStoreUiElement();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new PlayStoreUiElement();
                        input.readMessage(newArray[i]);
                        this.child = newArray;
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

    public static final class PlayStoreUiElementInfo extends MessageNano {
        public AuthContext authContext;
        public String document;
        public boolean hasDocument;
        public boolean hasHost;
        public boolean hasIsImeAction;
        public boolean hasOfferType;
        public boolean hasSerialDocid;
        public String host;
        public InstrumentInfo instrumentInfo;
        public boolean isImeAction;
        public int offerType;
        public String serialDocid;

        public static final class InstrumentInfo extends MessageNano {
            public boolean hasInstrumentFamily;
            public boolean hasIsDefault;
            public int instrumentFamily;
            public boolean isDefault;

            public InstrumentInfo() {
                clear();
            }

            public InstrumentInfo clear() {
                this.instrumentFamily = 0;
                this.hasInstrumentFamily = false;
                this.isDefault = false;
                this.hasIsDefault = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasInstrumentFamily || this.instrumentFamily != 0) {
                    output.writeInt32(1, this.instrumentFamily);
                }
                if (this.hasIsDefault || this.isDefault) {
                    output.writeBool(2, this.isDefault);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasInstrumentFamily || this.instrumentFamily != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(1, this.instrumentFamily);
                }
                if (this.hasIsDefault || this.isDefault) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(2, this.isDefault);
                }
                return size;
            }

            public InstrumentInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            this.instrumentFamily = input.readInt32();
                            this.hasInstrumentFamily = true;
                            continue;
                        case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            this.isDefault = input.readBool();
                            this.hasIsDefault = true;
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

        public PlayStoreUiElementInfo() {
            clear();
        }

        public PlayStoreUiElementInfo clear() {
            this.instrumentInfo = null;
            this.serialDocid = "";
            this.hasSerialDocid = false;
            this.host = "";
            this.hasHost = false;
            this.document = "";
            this.hasDocument = false;
            this.offerType = 0;
            this.hasOfferType = false;
            this.isImeAction = false;
            this.hasIsImeAction = false;
            this.authContext = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.instrumentInfo != null) {
                output.writeMessage(1, this.instrumentInfo);
            }
            if (this.hasSerialDocid || !this.serialDocid.equals("")) {
                output.writeString(2, this.serialDocid);
            }
            if (this.hasHost || !this.host.equals("")) {
                output.writeString(3, this.host);
            }
            if (this.hasDocument || !this.document.equals("")) {
                output.writeString(4, this.document);
            }
            if (this.hasOfferType || this.offerType != 0) {
                output.writeInt32(5, this.offerType);
            }
            if (this.hasIsImeAction || this.isImeAction) {
                output.writeBool(6, this.isImeAction);
            }
            if (this.authContext != null) {
                output.writeMessage(7, this.authContext);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.instrumentInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.instrumentInfo);
            }
            if (this.hasSerialDocid || !this.serialDocid.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.serialDocid);
            }
            if (this.hasHost || !this.host.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.host);
            }
            if (this.hasDocument || !this.document.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.document);
            }
            if (this.hasOfferType || this.offerType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.offerType);
            }
            if (this.hasIsImeAction || this.isImeAction) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.isImeAction);
            }
            if (this.authContext != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.authContext);
            }
            return size;
        }

        public PlayStoreUiElementInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.instrumentInfo == null) {
                            this.instrumentInfo = new InstrumentInfo();
                        }
                        input.readMessage(this.instrumentInfo);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.serialDocid = input.readString();
                        this.hasSerialDocid = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.host = input.readString();
                        this.hasHost = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.document = input.readString();
                        this.hasDocument = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.offerType = input.readInt32();
                        this.hasOfferType = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.isImeAction = input.readBool();
                        this.hasIsImeAction = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.authContext == null) {
                            this.authContext = new AuthContext();
                        }
                        input.readMessage(this.authContext);
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

    public static final class PromptForFopData extends MessageNano {
        public boolean fopAdded;
        public boolean hasFop;
        public boolean hasFopAdded;
        public boolean hasHasFop;
        public boolean hasNumDialogShown;
        public boolean hasNumFopSelectorShown;
        public boolean hasNumSnooze;
        public int numDialogShown;
        public int numFopSelectorShown;
        public int numSnooze;

        public PromptForFopData() {
            clear();
        }

        public PromptForFopData clear() {
            this.hasFop = false;
            this.hasHasFop = false;
            this.fopAdded = false;
            this.hasFopAdded = false;
            this.numDialogShown = 0;
            this.hasNumDialogShown = false;
            this.numFopSelectorShown = 0;
            this.hasNumFopSelectorShown = false;
            this.numSnooze = 0;
            this.hasNumSnooze = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasHasFop || this.hasFop) {
                output.writeBool(1, this.hasFop);
            }
            if (this.hasFopAdded || this.fopAdded) {
                output.writeBool(2, this.fopAdded);
            }
            if (this.hasNumDialogShown || this.numDialogShown != 0) {
                output.writeInt32(3, this.numDialogShown);
            }
            if (this.hasNumFopSelectorShown || this.numFopSelectorShown != 0) {
                output.writeInt32(4, this.numFopSelectorShown);
            }
            if (this.hasNumSnooze || this.numSnooze != 0) {
                output.writeInt32(5, this.numSnooze);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasHasFop || this.hasFop) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.hasFop);
            }
            if (this.hasFopAdded || this.fopAdded) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.fopAdded);
            }
            if (this.hasNumDialogShown || this.numDialogShown != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.numDialogShown);
            }
            if (this.hasNumFopSelectorShown || this.numFopSelectorShown != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.numFopSelectorShown);
            }
            if (this.hasNumSnooze || this.numSnooze != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.numSnooze);
            }
            return size;
        }

        public PromptForFopData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.hasFop = input.readBool();
                        this.hasHasFop = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.fopAdded = input.readBool();
                        this.hasFopAdded = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.numDialogShown = input.readInt32();
                        this.hasNumDialogShown = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.numFopSelectorShown = input.readInt32();
                        this.hasNumFopSelectorShown = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.numSnooze = input.readInt32();
                        this.hasNumSnooze = true;
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

    public static final class ReviewData extends MessageNano {
        public boolean containsText;
        public boolean hasContainsText;
        public boolean hasRating;
        public boolean hasReviewSource;
        public int rating;
        public int reviewSource;

        public ReviewData() {
            clear();
        }

        public ReviewData clear() {
            this.reviewSource = 0;
            this.hasReviewSource = false;
            this.rating = 0;
            this.hasRating = false;
            this.containsText = false;
            this.hasContainsText = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.reviewSource != 0 || this.hasReviewSource) {
                output.writeInt32(1, this.reviewSource);
            }
            if (this.hasRating || this.rating != 0) {
                output.writeInt32(2, this.rating);
            }
            if (this.hasContainsText || this.containsText) {
                output.writeBool(3, this.containsText);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.reviewSource != 0 || this.hasReviewSource) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.reviewSource);
            }
            if (this.hasRating || this.rating != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.rating);
            }
            if (this.hasContainsText || this.containsText) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.containsText);
            }
            return size;
        }

        public ReviewData mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                            case 100:
                            case 101:
                            case 102:
                            case 103:
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                            case 109:
                            case 110:
                            case 111:
                            case 112:
                            case 113:
                            case 114:
                            case 115:
                            case 116:
                            case 117:
                            case 118:
                            case 119:
                            case 120:
                            case 121:
                            case 122:
                            case 123:
                            case 124:
                            case 125:
                            case 126:
                            case 127:
                            case 128:
                            case 200:
                            case 201:
                            case 202:
                            case 203:
                            case 204:
                            case 205:
                            case 206:
                            case 207:
                            case 208:
                            case 209:
                            case 210:
                            case 211:
                            case 212:
                            case 213:
                            case 214:
                            case 215:
                            case 216:
                            case 217:
                            case 218:
                            case 219:
                            case 220:
                            case 221:
                            case 222:
                            case 223:
                            case 224:
                            case 225:
                            case 226:
                            case 227:
                            case 228:
                            case 229:
                            case 230:
                            case 231:
                            case 232:
                            case 233:
                            case 234:
                            case 235:
                            case 236:
                            case 237:
                            case 238:
                            case 239:
                            case 240:
                            case 241:
                            case 242:
                            case 243:
                            case 244:
                            case 245:
                            case 246:
                            case 247:
                            case 248:
                            case 249:
                            case 250:
                            case 251:
                            case 252:
                            case 253:
                            case 254:
                            case 255:
                            case 256:
                            case 257:
                            case 258:
                            case 259:
                            case 260:
                            case 261:
                            case 262:
                            case 263:
                            case 264:
                            case 265:
                            case 266:
                            case 267:
                            case 268:
                            case 269:
                            case 270:
                            case 271:
                            case 272:
                            case 273:
                            case 274:
                            case 275:
                            case 276:
                            case 277:
                            case 278:
                            case 279:
                            case 280:
                            case 281:
                            case 282:
                            case 283:
                            case 284:
                            case 285:
                            case 286:
                            case 287:
                            case 288:
                            case 289:
                            case 290:
                            case 291:
                            case 292:
                            case 293:
                            case 294:
                            case 300:
                            case 301:
                            case 302:
                            case 303:
                            case 304:
                            case 305:
                            case 306:
                            case 307:
                            case 308:
                            case 309:
                            case 310:
                            case 311:
                            case 312:
                            case 313:
                            case 314:
                            case 315:
                            case 316:
                            case 317:
                            case 318:
                            case 319:
                            case 320:
                            case 400:
                            case 401:
                            case 402:
                            case 403:
                            case 404:
                            case 405:
                            case 406:
                            case 407:
                            case 408:
                            case 409:
                            case 410:
                            case 411:
                            case 412:
                            case 413:
                            case 414:
                            case 415:
                            case 416:
                            case 417:
                            case 418:
                            case 500:
                            case 501:
                            case 502:
                            case 503:
                            case 504:
                            case 505:
                            case 506:
                            case 507:
                            case 508:
                            case 509:
                            case 510:
                            case 511:
                            case 512:
                            case 513:
                            case 514:
                            case 515:
                            case 516:
                            case 517:
                            case 518:
                            case 600:
                            case 601:
                            case 602:
                            case 603:
                            case 700:
                            case 710:
                            case 711:
                            case 712:
                            case 713:
                            case 714:
                            case 715:
                            case 716:
                            case 717:
                            case 718:
                            case 719:
                            case 740:
                            case 750:
                            case 751:
                            case 752:
                            case 753:
                            case 754:
                            case 770:
                            case 771:
                            case 775:
                            case 776:
                            case 777:
                            case 778:
                            case 780:
                            case 781:
                            case 782:
                            case 790:
                            case 791:
                            case 792:
                            case 793:
                            case 800:
                            case 801:
                            case 802:
                            case 810:
                            case 811:
                            case 812:
                            case 813:
                            case 814:
                            case 820:
                            case 821:
                            case 822:
                            case 823:
                            case 824:
                            case 825:
                            case 840:
                            case 841:
                            case 842:
                            case 843:
                            case 844:
                            case 845:
                            case 846:
                            case 847:
                            case 848:
                            case 849:
                            case 860:
                            case 861:
                            case 862:
                            case 863:
                            case 864:
                            case 865:
                            case 866:
                            case 880:
                            case 881:
                            case 882:
                            case 883:
                            case 884:
                            case 885:
                            case 886:
                            case 887:
                            case 888:
                            case 889:
                            case 890:
                            case 891:
                            case 892:
                            case 893:
                            case 894:
                            case 895:
                            case 896:
                            case 897:
                            case 898:
                            case 900:
                            case 901:
                            case 902:
                            case 903:
                            case 904:
                            case 1000:
                            case 1001:
                            case 1002:
                            case 1003:
                            case 1004:
                            case 1005:
                            case 1006:
                            case 1007:
                            case 1008:
                            case 1009:
                            case 1010:
                            case 1101:
                            case 1102:
                            case 1103:
                            case 1104:
                            case 1105:
                            case 1106:
                            case 1107:
                            case 1200:
                            case 1201:
                            case 1202:
                            case 1203:
                            case 1204:
                            case 1205:
                            case 1206:
                            case 1207:
                            case 1208:
                            case 1220:
                            case 1221:
                            case 1230:
                            case 1231:
                            case 1232:
                            case 1233:
                            case 1234:
                            case 1250:
                            case 1251:
                            case 1252:
                            case 1270:
                            case 1271:
                            case 1280:
                            case 1281:
                            case 1400:
                            case 1401:
                            case 1402:
                            case 1403:
                            case 1404:
                            case 1405:
                            case 1406:
                            case 1407:
                            case 1408:
                            case 1409:
                            case 1500:
                            case 1501:
                            case 1502:
                            case 1530:
                            case 1531:
                            case 1532:
                            case 1600:
                            case 1620:
                            case 1621:
                            case 1622:
                            case 1623:
                            case 1624:
                            case 1625:
                            case 1626:
                            case 1627:
                            case 1628:
                            case 1629:
                            case 1650:
                            case 1651:
                            case 1652:
                            case 1653:
                            case 1665:
                            case 1666:
                            case 1667:
                            case 1668:
                            case 1680:
                            case 1681:
                            case 1700:
                            case 1800:
                            case 1801:
                            case 1802:
                            case 1803:
                            case 1804:
                            case 1805:
                            case 1806:
                            case 1820:
                            case 2500:
                            case 2501:
                            case 2502:
                            case 2503:
                            case 2504:
                            case 2505:
                            case 2506:
                            case 2507:
                            case 2508:
                            case 2509:
                            case 2510:
                            case 2600:
                            case 2601:
                            case 2602:
                            case 2603:
                            case 2604:
                            case 2605:
                            case 2620:
                            case 2621:
                            case 2622:
                            case 2623:
                            case 2624:
                            case 2625:
                                this.reviewSource = value;
                                this.hasReviewSource = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.rating = input.readInt32();
                        this.hasRating = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.containsText = input.readBool();
                        this.hasContainsText = true;
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

    public static final class SearchSuggestionReport extends MessageNano {
        public long clientLatencyMs;
        public boolean hasClientLatencyMs;
        public boolean hasQuery;
        public boolean hasResponseServerLogsCookie;
        public boolean hasResultCount;
        public boolean hasSource;
        public boolean hasSuggestedQuery;
        public boolean hasSuggestionServerLogsCookie;
        public String query;
        public byte[] responseServerLogsCookie;
        public int resultCount;
        public int source;
        public String suggestedQuery;
        public byte[] suggestionServerLogsCookie;

        public SearchSuggestionReport() {
            clear();
        }

        public SearchSuggestionReport clear() {
            this.query = "";
            this.hasQuery = false;
            this.suggestedQuery = "";
            this.hasSuggestedQuery = false;
            this.clientLatencyMs = 0;
            this.hasClientLatencyMs = false;
            this.source = 0;
            this.hasSource = false;
            this.resultCount = 0;
            this.hasResultCount = false;
            this.responseServerLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasResponseServerLogsCookie = false;
            this.suggestionServerLogsCookie = WireFormatNano.EMPTY_BYTES;
            this.hasSuggestionServerLogsCookie = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasQuery || !this.query.equals("")) {
                output.writeString(1, this.query);
            }
            if (this.hasSuggestedQuery || !this.suggestedQuery.equals("")) {
                output.writeString(2, this.suggestedQuery);
            }
            if (this.hasClientLatencyMs || this.clientLatencyMs != 0) {
                output.writeInt64(3, this.clientLatencyMs);
            }
            if (this.source != 0 || this.hasSource) {
                output.writeInt32(4, this.source);
            }
            if (this.hasResultCount || this.resultCount != 0) {
                output.writeInt32(5, this.resultCount);
            }
            if (this.hasResponseServerLogsCookie || !Arrays.equals(this.responseServerLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.responseServerLogsCookie);
            }
            if (this.hasSuggestionServerLogsCookie || !Arrays.equals(this.suggestionServerLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(7, this.suggestionServerLogsCookie);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasQuery || !this.query.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.query);
            }
            if (this.hasSuggestedQuery || !this.suggestedQuery.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.suggestedQuery);
            }
            if (this.hasClientLatencyMs || this.clientLatencyMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.clientLatencyMs);
            }
            if (this.source != 0 || this.hasSource) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.source);
            }
            if (this.hasResultCount || this.resultCount != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.resultCount);
            }
            if (this.hasResponseServerLogsCookie || !Arrays.equals(this.responseServerLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.responseServerLogsCookie);
            }
            if (this.hasSuggestionServerLogsCookie || !Arrays.equals(this.suggestionServerLogsCookie, WireFormatNano.EMPTY_BYTES)) {
                return size + CodedOutputByteBufferNano.computeBytesSize(7, this.suggestionServerLogsCookie);
            }
            return size;
        }

        public SearchSuggestionReport mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.query = input.readString();
                        this.hasQuery = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.suggestedQuery = input.readString();
                        this.hasSuggestedQuery = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.clientLatencyMs = input.readInt64();
                        this.hasClientLatencyMs = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.source = value;
                                this.hasSource = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.resultCount = input.readInt32();
                        this.hasResultCount = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.responseServerLogsCookie = input.readBytes();
                        this.hasResponseServerLogsCookie = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.suggestionServerLogsCookie = input.readBytes();
                        this.hasSuggestionServerLogsCookie = true;
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

        public static SearchSuggestionReport parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (SearchSuggestionReport) MessageNano.mergeFrom(new SearchSuggestionReport(), data);
        }
    }

    public static final class WidgetEventData extends MessageNano {
        public int backendId;
        public int classId;
        public boolean hasBackendId;
        public boolean hasClassId;
        public boolean hasIntentActionId;
        public boolean hasMaxHeight;
        public boolean hasMaxWidth;
        public boolean hasMinHeight;
        public boolean hasMinWidth;
        public boolean hasNumWidgets;
        public int intentActionId;
        public int maxHeight;
        public int maxWidth;
        public int minHeight;
        public int minWidth;
        public int numWidgets;

        public WidgetEventData() {
            clear();
        }

        public WidgetEventData clear() {
            this.classId = 0;
            this.hasClassId = false;
            this.intentActionId = 0;
            this.hasIntentActionId = false;
            this.numWidgets = 0;
            this.hasNumWidgets = false;
            this.backendId = 0;
            this.hasBackendId = false;
            this.minWidth = 0;
            this.hasMinWidth = false;
            this.minHeight = 0;
            this.hasMinHeight = false;
            this.maxWidth = 0;
            this.hasMaxWidth = false;
            this.maxHeight = 0;
            this.hasMaxHeight = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.classId != 0 || this.hasClassId) {
                output.writeInt32(1, this.classId);
            }
            if (this.intentActionId != 0 || this.hasIntentActionId) {
                output.writeInt32(2, this.intentActionId);
            }
            if (this.hasNumWidgets || this.numWidgets != 0) {
                output.writeInt32(3, this.numWidgets);
            }
            if (this.hasBackendId || this.backendId != 0) {
                output.writeInt32(4, this.backendId);
            }
            if (this.hasMinWidth || this.minWidth != 0) {
                output.writeInt32(5, this.minWidth);
            }
            if (this.hasMinHeight || this.minHeight != 0) {
                output.writeInt32(6, this.minHeight);
            }
            if (this.hasMaxWidth || this.maxWidth != 0) {
                output.writeInt32(7, this.maxWidth);
            }
            if (this.hasMaxHeight || this.maxHeight != 0) {
                output.writeInt32(8, this.maxHeight);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.classId != 0 || this.hasClassId) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.classId);
            }
            if (this.intentActionId != 0 || this.hasIntentActionId) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.intentActionId);
            }
            if (this.hasNumWidgets || this.numWidgets != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.numWidgets);
            }
            if (this.hasBackendId || this.backendId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.backendId);
            }
            if (this.hasMinWidth || this.minWidth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.minWidth);
            }
            if (this.hasMinHeight || this.minHeight != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(6, this.minHeight);
            }
            if (this.hasMaxWidth || this.maxWidth != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.maxWidth);
            }
            if (this.hasMaxHeight || this.maxHeight != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(8, this.maxHeight);
            }
            return size;
        }

        public WidgetEventData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.classId = value;
                                this.hasClassId = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                                this.intentActionId = value;
                                this.hasIntentActionId = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.numWidgets = input.readInt32();
                        this.hasNumWidgets = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.backendId = input.readInt32();
                        this.hasBackendId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.minWidth = input.readInt32();
                        this.hasMinWidth = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.minHeight = input.readInt32();
                        this.hasMinHeight = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.maxWidth = input.readInt32();
                        this.hasMaxWidth = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.maxHeight = input.readInt32();
                        this.hasMaxHeight = true;
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

    public static final class WifiAutoUpdateAttempt extends MessageNano {
        public boolean autoUpdateSuccess;
        public boolean hasAutoUpdateSuccess;
        public boolean hasNumFailedAttempts;
        public boolean hasSkippedDueToProjection;
        public boolean hasTimeSinceFirstFailMs;
        public boolean hasWifiCheckIntervalMs;
        public int numFailedAttempts;
        public boolean skippedDueToProjection;
        public long timeSinceFirstFailMs;
        public long wifiCheckIntervalMs;

        public WifiAutoUpdateAttempt() {
            clear();
        }

        public WifiAutoUpdateAttempt clear() {
            this.autoUpdateSuccess = false;
            this.hasAutoUpdateSuccess = false;
            this.numFailedAttempts = 0;
            this.hasNumFailedAttempts = false;
            this.timeSinceFirstFailMs = 0;
            this.hasTimeSinceFirstFailMs = false;
            this.wifiCheckIntervalMs = 0;
            this.hasWifiCheckIntervalMs = false;
            this.skippedDueToProjection = false;
            this.hasSkippedDueToProjection = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasAutoUpdateSuccess || this.autoUpdateSuccess) {
                output.writeBool(1, this.autoUpdateSuccess);
            }
            if (this.hasNumFailedAttempts || this.numFailedAttempts != 0) {
                output.writeInt32(2, this.numFailedAttempts);
            }
            if (this.hasTimeSinceFirstFailMs || this.timeSinceFirstFailMs != 0) {
                output.writeInt64(3, this.timeSinceFirstFailMs);
            }
            if (this.hasWifiCheckIntervalMs || this.wifiCheckIntervalMs != 0) {
                output.writeInt64(4, this.wifiCheckIntervalMs);
            }
            if (this.hasSkippedDueToProjection || this.skippedDueToProjection) {
                output.writeBool(5, this.skippedDueToProjection);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasAutoUpdateSuccess || this.autoUpdateSuccess) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.autoUpdateSuccess);
            }
            if (this.hasNumFailedAttempts || this.numFailedAttempts != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.numFailedAttempts);
            }
            if (this.hasTimeSinceFirstFailMs || this.timeSinceFirstFailMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.timeSinceFirstFailMs);
            }
            if (this.hasWifiCheckIntervalMs || this.wifiCheckIntervalMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.wifiCheckIntervalMs);
            }
            if (this.hasSkippedDueToProjection || this.skippedDueToProjection) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.skippedDueToProjection);
            }
            return size;
        }

        public WifiAutoUpdateAttempt mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.autoUpdateSuccess = input.readBool();
                        this.hasAutoUpdateSuccess = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.numFailedAttempts = input.readInt32();
                        this.hasNumFailedAttempts = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.timeSinceFirstFailMs = input.readInt64();
                        this.hasTimeSinceFirstFailMs = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.wifiCheckIntervalMs = input.readInt64();
                        this.hasWifiCheckIntervalMs = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.skippedDueToProjection = input.readBool();
                        this.hasSkippedDueToProjection = true;
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
