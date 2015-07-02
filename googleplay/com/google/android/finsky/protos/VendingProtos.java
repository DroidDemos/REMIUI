package com.google.android.finsky.protos;

import com.google.android.finsky.protos.CommonDevice.BillingAddressSpec;
import com.google.android.finsky.protos.Notifications.Notification;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface VendingProtos {

    public static final class AckNotificationsRequestProto extends MessageNano {
        public String[] nackNotificationId;
        public String[] notificationId;
        public SignatureHashProto signatureHash;

        public AckNotificationsRequestProto() {
            clear();
        }

        public AckNotificationsRequestProto clear() {
            this.notificationId = WireFormatNano.EMPTY_STRING_ARRAY;
            this.signatureHash = null;
            this.nackNotificationId = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.notificationId != null && this.notificationId.length > 0) {
                for (String element : this.notificationId) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.signatureHash != null) {
                output.writeMessage(2, this.signatureHash);
            }
            if (this.nackNotificationId != null && this.nackNotificationId.length > 0) {
                for (String element2 : this.nackNotificationId) {
                    if (element2 != null) {
                        output.writeString(3, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.notificationId != null && this.notificationId.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.notificationId) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.signatureHash != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.signatureHash);
            }
            if (this.nackNotificationId == null || this.nackNotificationId.length <= 0) {
                return size;
            }
            dataCount = 0;
            dataSize = 0;
            for (String element2 : this.nackNotificationId) {
                if (element2 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public AckNotificationsRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        i = this.notificationId == null ? 0 : this.notificationId.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.notificationId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.notificationId = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.signatureHash == null) {
                            this.signatureHash = new SignatureHashProto();
                        }
                        input.readMessage(this.signatureHash);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.nackNotificationId == null ? 0 : this.nackNotificationId.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.nackNotificationId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.nackNotificationId = newArray;
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

    public static final class AckNotificationsResponseProto extends MessageNano {
        public AckNotificationsResponseProto() {
            clear();
        }

        public AckNotificationsResponseProto clear() {
            this.cachedSize = -1;
            return this;
        }

        public AckNotificationsResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static final class AppDataProto extends MessageNano {
        private static volatile AppDataProto[] _emptyArray;
        public boolean hasKey;
        public boolean hasValue;
        public String key;
        public String value;

        public static AppDataProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new AppDataProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public AppDataProto() {
            clear();
        }

        public AppDataProto clear() {
            this.key = "";
            this.hasKey = false;
            this.value = "";
            this.hasValue = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasKey || !this.key.equals("")) {
                output.writeString(1, this.key);
            }
            if (this.hasValue || !this.value.equals("")) {
                output.writeString(2, this.value);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasKey || !this.key.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.key);
            }
            if (this.hasValue || !this.value.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.value);
            }
            return size;
        }

        public AppDataProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.key = input.readString();
                        this.hasKey = true;
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

    public static final class BillingEventRequestProto extends MessageNano {
        public String billingParametersId;
        public String clientMessage;
        public int eventType;
        public boolean hasBillingParametersId;
        public boolean hasClientMessage;
        public boolean hasEventType;
        public boolean hasResultSuccess;
        public boolean resultSuccess;

        public BillingEventRequestProto() {
            clear();
        }

        public BillingEventRequestProto clear() {
            this.eventType = 0;
            this.hasEventType = false;
            this.billingParametersId = "";
            this.hasBillingParametersId = false;
            this.resultSuccess = false;
            this.hasResultSuccess = false;
            this.clientMessage = "";
            this.hasClientMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.eventType != 0 || this.hasEventType) {
                output.writeInt32(1, this.eventType);
            }
            if (this.hasBillingParametersId || !this.billingParametersId.equals("")) {
                output.writeString(2, this.billingParametersId);
            }
            if (this.hasResultSuccess || this.resultSuccess) {
                output.writeBool(3, this.resultSuccess);
            }
            if (this.hasClientMessage || !this.clientMessage.equals("")) {
                output.writeString(4, this.clientMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.eventType != 0 || this.hasEventType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.eventType);
            }
            if (this.hasBillingParametersId || !this.billingParametersId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.billingParametersId);
            }
            if (this.hasResultSuccess || this.resultSuccess) {
                size += CodedOutputByteBufferNano.computeBoolSize(3, this.resultSuccess);
            }
            if (this.hasClientMessage || !this.clientMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.clientMessage);
            }
            return size;
        }

        public BillingEventRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.eventType = value;
                                this.hasEventType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.billingParametersId = input.readString();
                        this.hasBillingParametersId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.resultSuccess = input.readBool();
                        this.hasResultSuccess = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.clientMessage = input.readString();
                        this.hasClientMessage = true;
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

    public static final class BillingEventResponseProto extends MessageNano {
        public BillingEventResponseProto() {
            clear();
        }

        public BillingEventResponseProto clear() {
            this.cachedSize = -1;
            return this;
        }

        public BillingEventResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static final class CheckForNotificationsRequestProto extends MessageNano {
        public long alarmDuration;
        public boolean hasAlarmDuration;

        public CheckForNotificationsRequestProto() {
            clear();
        }

        public CheckForNotificationsRequestProto clear() {
            this.alarmDuration = 0;
            this.hasAlarmDuration = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasAlarmDuration || this.alarmDuration != 0) {
                output.writeInt64(1, this.alarmDuration);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasAlarmDuration || this.alarmDuration != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(1, this.alarmDuration);
            }
            return size;
        }

        public CheckForNotificationsRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.alarmDuration = input.readInt64();
                        this.hasAlarmDuration = true;
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

    public static final class CheckLicenseRequestProto extends MessageNano {
        public boolean hasNonce;
        public boolean hasPackageName;
        public boolean hasVersionCode;
        public long nonce;
        public String packageName;
        public int versionCode;

        public CheckLicenseRequestProto() {
            clear();
        }

        public CheckLicenseRequestProto clear() {
            this.packageName = "";
            this.hasPackageName = false;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.nonce = 0;
            this.hasNonce = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPackageName || !this.packageName.equals("")) {
                output.writeString(1, this.packageName);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                output.writeInt32(2, this.versionCode);
            }
            if (this.hasNonce || this.nonce != 0) {
                output.writeInt64(3, this.nonce);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPackageName || !this.packageName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.versionCode);
            }
            if (this.hasNonce || this.nonce != 0) {
                return size + CodedOutputByteBufferNano.computeInt64Size(3, this.nonce);
            }
            return size;
        }

        public CheckLicenseRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.packageName = input.readString();
                        this.hasPackageName = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.versionCode = input.readInt32();
                        this.hasVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.nonce = input.readInt64();
                        this.hasNonce = true;
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

    public static final class CheckLicenseResponseProto extends MessageNano {
        public boolean hasResponseCode;
        public boolean hasSignature;
        public boolean hasSignedData;
        public int responseCode;
        public String signature;
        public String signedData;

        public CheckLicenseResponseProto() {
            clear();
        }

        public CheckLicenseResponseProto clear() {
            this.responseCode = 0;
            this.hasResponseCode = false;
            this.signedData = "";
            this.hasSignedData = false;
            this.signature = "";
            this.hasSignature = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasResponseCode || this.responseCode != 0) {
                output.writeInt32(1, this.responseCode);
            }
            if (this.hasSignedData || !this.signedData.equals("")) {
                output.writeString(2, this.signedData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                output.writeString(3, this.signature);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasResponseCode || this.responseCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.responseCode);
            }
            if (this.hasSignedData || !this.signedData.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.signedData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.signature);
            }
            return size;
        }

        public CheckLicenseResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.responseCode = input.readInt32();
                        this.hasResponseCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.signedData = input.readString();
                        this.hasSignedData = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.signature = input.readString();
                        this.hasSignature = true;
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

    public static final class ContentSyncRequestProto extends MessageNano {
        public AssetInstallState[] assetInstallState;
        public boolean hasIncremental;
        public boolean hasSideloadedAppCount;
        public boolean incremental;
        public int sideloadedAppCount;
        public SystemApp[] systemApp;

        public static final class AssetInstallState extends MessageNano {
            private static volatile AssetInstallState[] _emptyArray;
            public String assetId;
            public String assetReferrer;
            public int assetState;
            public boolean hasAssetId;
            public boolean hasAssetReferrer;
            public boolean hasAssetState;
            public boolean hasInstallTime;
            public boolean hasPackageName;
            public boolean hasUninstallTime;
            public boolean hasVersionCode;
            public long installTime;
            public String packageName;
            public long uninstallTime;
            public int versionCode;

            public static AssetInstallState[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new AssetInstallState[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public AssetInstallState() {
                clear();
            }

            public AssetInstallState clear() {
                this.assetId = "";
                this.hasAssetId = false;
                this.assetState = 1;
                this.hasAssetState = false;
                this.installTime = 0;
                this.hasInstallTime = false;
                this.uninstallTime = 0;
                this.hasUninstallTime = false;
                this.packageName = "";
                this.hasPackageName = false;
                this.versionCode = 0;
                this.hasVersionCode = false;
                this.assetReferrer = "";
                this.hasAssetReferrer = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasAssetId || !this.assetId.equals("")) {
                    output.writeString(3, this.assetId);
                }
                if (this.assetState != 1 || this.hasAssetState) {
                    output.writeInt32(4, this.assetState);
                }
                if (this.hasInstallTime || this.installTime != 0) {
                    output.writeInt64(5, this.installTime);
                }
                if (this.hasUninstallTime || this.uninstallTime != 0) {
                    output.writeInt64(6, this.uninstallTime);
                }
                if (this.hasPackageName || !this.packageName.equals("")) {
                    output.writeString(7, this.packageName);
                }
                if (this.hasVersionCode || this.versionCode != 0) {
                    output.writeInt32(8, this.versionCode);
                }
                if (this.hasAssetReferrer || !this.assetReferrer.equals("")) {
                    output.writeString(9, this.assetReferrer);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasAssetId || !this.assetId.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.assetId);
                }
                if (this.assetState != 1 || this.hasAssetState) {
                    size += CodedOutputByteBufferNano.computeInt32Size(4, this.assetState);
                }
                if (this.hasInstallTime || this.installTime != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(5, this.installTime);
                }
                if (this.hasUninstallTime || this.uninstallTime != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(6, this.uninstallTime);
                }
                if (this.hasPackageName || !this.packageName.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(7, this.packageName);
                }
                if (this.hasVersionCode || this.versionCode != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(8, this.versionCode);
                }
                if (this.hasAssetReferrer || !this.assetReferrer.equals("")) {
                    return size + CodedOutputByteBufferNano.computeStringSize(9, this.assetReferrer);
                }
                return size;
            }

            public AssetInstallState mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            this.assetId = input.readString();
                            this.hasAssetId = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                            int value = input.readInt32();
                            switch (value) {
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
                                    this.assetState = value;
                                    this.hasAssetState = true;
                                    break;
                                default:
                                    continue;
                            }
                        case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                            this.installTime = input.readInt64();
                            this.hasInstallTime = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                            this.uninstallTime = input.readInt64();
                            this.hasUninstallTime = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                            this.packageName = input.readString();
                            this.hasPackageName = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                            this.versionCode = input.readInt32();
                            this.hasVersionCode = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                            this.assetReferrer = input.readString();
                            this.hasAssetReferrer = true;
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

        public static final class SystemApp extends MessageNano {
            private static volatile SystemApp[] _emptyArray;
            public String[] certificateHash;
            public boolean hasPackageName;
            public boolean hasVersionCode;
            public String packageName;
            public int versionCode;

            public static SystemApp[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new SystemApp[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public SystemApp() {
                clear();
            }

            public SystemApp clear() {
                this.packageName = "";
                this.hasPackageName = false;
                this.versionCode = 0;
                this.hasVersionCode = false;
                this.certificateHash = WireFormatNano.EMPTY_STRING_ARRAY;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasPackageName || !this.packageName.equals("")) {
                    output.writeString(11, this.packageName);
                }
                if (this.hasVersionCode || this.versionCode != 0) {
                    output.writeInt32(12, this.versionCode);
                }
                if (this.certificateHash != null && this.certificateHash.length > 0) {
                    for (String element : this.certificateHash) {
                        if (element != null) {
                            output.writeString(13, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasPackageName || !this.packageName.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(11, this.packageName);
                }
                if (this.hasVersionCode || this.versionCode != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(12, this.versionCode);
                }
                if (this.certificateHash == null || this.certificateHash.length <= 0) {
                    return size;
                }
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.certificateHash) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                return (size + dataSize) + (dataCount * 1);
            }

            public SystemApp mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case 90:
                            this.packageName = input.readString();
                            this.hasPackageName = true;
                            continue;
                        case 96:
                            this.versionCode = input.readInt32();
                            this.hasVersionCode = true;
                            continue;
                        case 106:
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 106);
                            int i = this.certificateHash == null ? 0 : this.certificateHash.length;
                            String[] newArray = new String[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.certificateHash, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = input.readString();
                                input.readTag();
                                i++;
                            }
                            newArray[i] = input.readString();
                            this.certificateHash = newArray;
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

        public ContentSyncRequestProto() {
            clear();
        }

        public ContentSyncRequestProto clear() {
            this.incremental = false;
            this.hasIncremental = false;
            this.sideloadedAppCount = 0;
            this.hasSideloadedAppCount = false;
            this.assetInstallState = AssetInstallState.emptyArray();
            this.systemApp = SystemApp.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasIncremental || this.incremental) {
                output.writeBool(1, this.incremental);
            }
            if (this.assetInstallState != null && this.assetInstallState.length > 0) {
                for (AssetInstallState element : this.assetInstallState) {
                    if (element != null) {
                        output.writeGroup(2, element);
                    }
                }
            }
            if (this.systemApp != null && this.systemApp.length > 0) {
                for (SystemApp element2 : this.systemApp) {
                    if (element2 != null) {
                        output.writeGroup(10, element2);
                    }
                }
            }
            if (this.hasSideloadedAppCount || this.sideloadedAppCount != 0) {
                output.writeInt32(14, this.sideloadedAppCount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasIncremental || this.incremental) {
                size += CodedOutputByteBufferNano.computeBoolSize(1, this.incremental);
            }
            if (this.assetInstallState != null && this.assetInstallState.length > 0) {
                for (AssetInstallState element : this.assetInstallState) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeGroupSize(2, element);
                    }
                }
            }
            if (this.systemApp != null && this.systemApp.length > 0) {
                for (SystemApp element2 : this.systemApp) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeGroupSize(10, element2);
                    }
                }
            }
            if (this.hasSideloadedAppCount || this.sideloadedAppCount != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(14, this.sideloadedAppCount);
            }
            return size;
        }

        public ContentSyncRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.incremental = input.readBool();
                        this.hasIncremental = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 19);
                        if (this.assetInstallState == null) {
                            i = 0;
                        } else {
                            i = this.assetInstallState.length;
                        }
                        AssetInstallState[] newArray = new AssetInstallState[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.assetInstallState, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AssetInstallState();
                            input.readGroup(newArray[i], 2);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AssetInstallState();
                        input.readGroup(newArray[i], 2);
                        this.assetInstallState = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorSwitchThumbNormal /*83*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 83);
                        if (this.systemApp == null) {
                            i = 0;
                        } else {
                            i = this.systemApp.length;
                        }
                        SystemApp[] newArray2 = new SystemApp[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.systemApp, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new SystemApp();
                            input.readGroup(newArray2[i], 10);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new SystemApp();
                        input.readGroup(newArray2[i], 10);
                        this.systemApp = newArray2;
                        continue;
                    case 112:
                        this.sideloadedAppCount = input.readInt32();
                        this.hasSideloadedAppCount = true;
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

    public static final class ContentSyncResponseProto extends MessageNano {
        public boolean hasNumUpdatesAvailable;
        public int numUpdatesAvailable;

        public ContentSyncResponseProto() {
            clear();
        }

        public ContentSyncResponseProto clear() {
            this.numUpdatesAvailable = 0;
            this.hasNumUpdatesAvailable = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasNumUpdatesAvailable || this.numUpdatesAvailable != 0) {
                output.writeInt32(1, this.numUpdatesAvailable);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasNumUpdatesAvailable || this.numUpdatesAvailable != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.numUpdatesAvailable);
            }
            return size;
        }

        public ContentSyncResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.numUpdatesAvailable = input.readInt32();
                        this.hasNumUpdatesAvailable = true;
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

    public static final class DataMessageProto extends MessageNano {
        private static volatile DataMessageProto[] _emptyArray;
        public AppDataProto[] appData;
        public String category;
        public boolean hasCategory;

        public static DataMessageProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new DataMessageProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public DataMessageProto() {
            clear();
        }

        public DataMessageProto clear() {
            this.category = "";
            this.hasCategory = false;
            this.appData = AppDataProto.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasCategory || !this.category.equals("")) {
                output.writeString(1, this.category);
            }
            if (this.appData != null && this.appData.length > 0) {
                for (AppDataProto element : this.appData) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasCategory || !this.category.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.category);
            }
            if (this.appData != null && this.appData.length > 0) {
                for (AppDataProto element : this.appData) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public DataMessageProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.category = input.readString();
                        this.hasCategory = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.appData == null) {
                            i = 0;
                        } else {
                            i = this.appData.length;
                        }
                        AppDataProto[] newArray = new AppDataProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appData, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new AppDataProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new AppDataProto();
                        input.readMessage(newArray[i]);
                        this.appData = newArray;
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

    public static final class FileMetadataProto extends MessageNano {
        private static volatile FileMetadataProto[] _emptyArray;
        public String downloadUrl;
        public int fileType;
        public boolean hasDownloadUrl;
        public boolean hasFileType;
        public boolean hasSize;
        public boolean hasVersionCode;
        public long size;
        public int versionCode;

        public static FileMetadataProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FileMetadataProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FileMetadataProto() {
            clear();
        }

        public FileMetadataProto clear() {
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

        public FileMetadataProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static final class GetAssetResponseProto extends MessageNano {
        private static volatile GetAssetResponseProto[] _emptyArray;
        public FileMetadataProto[] additionalFile;
        public InstallAsset installAsset;

        public static final class InstallAsset extends MessageNano {
            public String assetId;
            public String assetName;
            public String assetPackage;
            public String assetSignature;
            public long assetSize;
            public String assetType;
            public String blobUrl;
            public String downloadAuthCookieName;
            public String downloadAuthCookieValue;
            public boolean forwardLocked;
            public boolean hasAssetId;
            public boolean hasAssetName;
            public boolean hasAssetPackage;
            public boolean hasAssetSignature;
            public boolean hasAssetSize;
            public boolean hasAssetType;
            public boolean hasBlobUrl;
            public boolean hasDownloadAuthCookieName;
            public boolean hasDownloadAuthCookieValue;
            public boolean hasForwardLocked;
            public boolean hasPostInstallRefundWindowMillis;
            public boolean hasRefundTimeoutMillis;
            public boolean hasSecured;
            public boolean hasVersionCode;
            public long postInstallRefundWindowMillis;
            public long refundTimeoutMillis;
            public boolean secured;
            public int versionCode;

            public InstallAsset() {
                clear();
            }

            public InstallAsset clear() {
                this.assetId = "";
                this.hasAssetId = false;
                this.assetName = "";
                this.hasAssetName = false;
                this.assetType = "";
                this.hasAssetType = false;
                this.assetPackage = "";
                this.hasAssetPackage = false;
                this.blobUrl = "";
                this.hasBlobUrl = false;
                this.assetSignature = "";
                this.hasAssetSignature = false;
                this.assetSize = 0;
                this.hasAssetSize = false;
                this.refundTimeoutMillis = 0;
                this.hasRefundTimeoutMillis = false;
                this.forwardLocked = false;
                this.hasForwardLocked = false;
                this.secured = false;
                this.hasSecured = false;
                this.versionCode = 0;
                this.hasVersionCode = false;
                this.downloadAuthCookieName = "";
                this.hasDownloadAuthCookieName = false;
                this.downloadAuthCookieValue = "";
                this.hasDownloadAuthCookieValue = false;
                this.postInstallRefundWindowMillis = 0;
                this.hasPostInstallRefundWindowMillis = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasAssetId || !this.assetId.equals("")) {
                    output.writeString(2, this.assetId);
                }
                if (this.hasAssetName || !this.assetName.equals("")) {
                    output.writeString(3, this.assetName);
                }
                if (this.hasAssetType || !this.assetType.equals("")) {
                    output.writeString(4, this.assetType);
                }
                if (this.hasAssetPackage || !this.assetPackage.equals("")) {
                    output.writeString(5, this.assetPackage);
                }
                if (this.hasBlobUrl || !this.blobUrl.equals("")) {
                    output.writeString(6, this.blobUrl);
                }
                if (this.hasAssetSignature || !this.assetSignature.equals("")) {
                    output.writeString(7, this.assetSignature);
                }
                if (this.hasAssetSize || this.assetSize != 0) {
                    output.writeInt64(8, this.assetSize);
                }
                if (this.hasRefundTimeoutMillis || this.refundTimeoutMillis != 0) {
                    output.writeInt64(9, this.refundTimeoutMillis);
                }
                if (this.hasForwardLocked || this.forwardLocked) {
                    output.writeBool(10, this.forwardLocked);
                }
                if (this.hasSecured || this.secured) {
                    output.writeBool(11, this.secured);
                }
                if (this.hasVersionCode || this.versionCode != 0) {
                    output.writeInt32(12, this.versionCode);
                }
                if (this.hasDownloadAuthCookieName || !this.downloadAuthCookieName.equals("")) {
                    output.writeString(13, this.downloadAuthCookieName);
                }
                if (this.hasDownloadAuthCookieValue || !this.downloadAuthCookieValue.equals("")) {
                    output.writeString(14, this.downloadAuthCookieValue);
                }
                if (this.hasPostInstallRefundWindowMillis || this.postInstallRefundWindowMillis != 0) {
                    output.writeInt64(16, this.postInstallRefundWindowMillis);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasAssetId || !this.assetId.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(2, this.assetId);
                }
                if (this.hasAssetName || !this.assetName.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.assetName);
                }
                if (this.hasAssetType || !this.assetType.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(4, this.assetType);
                }
                if (this.hasAssetPackage || !this.assetPackage.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.assetPackage);
                }
                if (this.hasBlobUrl || !this.blobUrl.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(6, this.blobUrl);
                }
                if (this.hasAssetSignature || !this.assetSignature.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(7, this.assetSignature);
                }
                if (this.hasAssetSize || this.assetSize != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(8, this.assetSize);
                }
                if (this.hasRefundTimeoutMillis || this.refundTimeoutMillis != 0) {
                    size += CodedOutputByteBufferNano.computeInt64Size(9, this.refundTimeoutMillis);
                }
                if (this.hasForwardLocked || this.forwardLocked) {
                    size += CodedOutputByteBufferNano.computeBoolSize(10, this.forwardLocked);
                }
                if (this.hasSecured || this.secured) {
                    size += CodedOutputByteBufferNano.computeBoolSize(11, this.secured);
                }
                if (this.hasVersionCode || this.versionCode != 0) {
                    size += CodedOutputByteBufferNano.computeInt32Size(12, this.versionCode);
                }
                if (this.hasDownloadAuthCookieName || !this.downloadAuthCookieName.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(13, this.downloadAuthCookieName);
                }
                if (this.hasDownloadAuthCookieValue || !this.downloadAuthCookieValue.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(14, this.downloadAuthCookieValue);
                }
                if (this.hasPostInstallRefundWindowMillis || this.postInstallRefundWindowMillis != 0) {
                    return size + CodedOutputByteBufferNano.computeInt64Size(16, this.postInstallRefundWindowMillis);
                }
                return size;
            }

            public InstallAsset mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            this.assetId = input.readString();
                            this.hasAssetId = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            this.assetName = input.readString();
                            this.hasAssetName = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                            this.assetType = input.readString();
                            this.hasAssetType = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                            this.assetPackage = input.readString();
                            this.hasAssetPackage = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                            this.blobUrl = input.readString();
                            this.hasBlobUrl = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                            this.assetSignature = input.readString();
                            this.hasAssetSignature = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                            this.assetSize = input.readInt64();
                            this.hasAssetSize = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                            this.refundTimeoutMillis = input.readInt64();
                            this.hasRefundTimeoutMillis = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                            this.forwardLocked = input.readBool();
                            this.hasForwardLocked = true;
                            continue;
                        case 88:
                            this.secured = input.readBool();
                            this.hasSecured = true;
                            continue;
                        case 96:
                            this.versionCode = input.readInt32();
                            this.hasVersionCode = true;
                            continue;
                        case 106:
                            this.downloadAuthCookieName = input.readString();
                            this.hasDownloadAuthCookieName = true;
                            continue;
                        case 114:
                            this.downloadAuthCookieValue = input.readString();
                            this.hasDownloadAuthCookieValue = true;
                            continue;
                        case 128:
                            this.postInstallRefundWindowMillis = input.readInt64();
                            this.hasPostInstallRefundWindowMillis = true;
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

        public static GetAssetResponseProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new GetAssetResponseProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public GetAssetResponseProto() {
            clear();
        }

        public GetAssetResponseProto clear() {
            this.installAsset = null;
            this.additionalFile = FileMetadataProto.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.installAsset != null) {
                output.writeGroup(1, this.installAsset);
            }
            if (this.additionalFile != null && this.additionalFile.length > 0) {
                for (FileMetadataProto element : this.additionalFile) {
                    if (element != null) {
                        output.writeMessage(15, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.installAsset != null) {
                size += CodedOutputByteBufferNano.computeGroupSize(1, this.installAsset);
            }
            if (this.additionalFile != null && this.additionalFile.length > 0) {
                for (FileMetadataProto element : this.additionalFile) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(15, element);
                    }
                }
            }
            return size;
        }

        public GetAssetResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.MapAttrs_uiZoomControls /*11*/:
                        if (this.installAsset == null) {
                            this.installAsset = new InstallAsset();
                        }
                        input.readGroup(this.installAsset, 1);
                        continue;
                    case 122:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 122);
                        if (this.additionalFile == null) {
                            i = 0;
                        } else {
                            i = this.additionalFile.length;
                        }
                        FileMetadataProto[] newArray = new FileMetadataProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.additionalFile, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new FileMetadataProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new FileMetadataProto();
                        input.readMessage(newArray[i]);
                        this.additionalFile = newArray;
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

    public static final class GetMarketMetadataResponseProto extends MessageNano {
        public boolean hasLatestClientVersionCode;
        public int latestClientVersionCode;

        public GetMarketMetadataResponseProto() {
            clear();
        }

        public GetMarketMetadataResponseProto clear() {
            this.latestClientVersionCode = 0;
            this.hasLatestClientVersionCode = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLatestClientVersionCode || this.latestClientVersionCode != 0) {
                output.writeInt32(1, this.latestClientVersionCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasLatestClientVersionCode || this.latestClientVersionCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.latestClientVersionCode);
            }
            return size;
        }

        public GetMarketMetadataResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.latestClientVersionCode = input.readInt32();
                        this.hasLatestClientVersionCode = true;
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

    public static final class InAppPurchaseInformationRequestProto extends MessageNano {
        public int billingApiVersion;
        public boolean hasBillingApiVersion;
        public boolean hasNonce;
        public boolean hasSignatureAlgorithm;
        public long nonce;
        public String[] notificationId;
        public String signatureAlgorithm;
        public SignatureHashProto signatureHash;

        public InAppPurchaseInformationRequestProto() {
            clear();
        }

        public InAppPurchaseInformationRequestProto clear() {
            this.signatureHash = null;
            this.nonce = 0;
            this.hasNonce = false;
            this.notificationId = WireFormatNano.EMPTY_STRING_ARRAY;
            this.signatureAlgorithm = "";
            this.hasSignatureAlgorithm = false;
            this.billingApiVersion = 0;
            this.hasBillingApiVersion = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.signatureHash != null) {
                output.writeMessage(1, this.signatureHash);
            }
            if (this.hasNonce || this.nonce != 0) {
                output.writeInt64(2, this.nonce);
            }
            if (this.notificationId != null && this.notificationId.length > 0) {
                for (String element : this.notificationId) {
                    if (element != null) {
                        output.writeString(3, element);
                    }
                }
            }
            if (this.hasSignatureAlgorithm || !this.signatureAlgorithm.equals("")) {
                output.writeString(4, this.signatureAlgorithm);
            }
            if (this.hasBillingApiVersion || this.billingApiVersion != 0) {
                output.writeInt32(5, this.billingApiVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.signatureHash != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.signatureHash);
            }
            if (this.hasNonce || this.nonce != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.nonce);
            }
            if (this.notificationId != null && this.notificationId.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.notificationId) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasSignatureAlgorithm || !this.signatureAlgorithm.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.signatureAlgorithm);
            }
            if (this.hasBillingApiVersion || this.billingApiVersion != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.billingApiVersion);
            }
            return size;
        }

        public InAppPurchaseInformationRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.signatureHash == null) {
                            this.signatureHash = new SignatureHashProto();
                        }
                        input.readMessage(this.signatureHash);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.nonce = input.readInt64();
                        this.hasNonce = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        int i = this.notificationId == null ? 0 : this.notificationId.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.notificationId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.notificationId = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.signatureAlgorithm = input.readString();
                        this.hasSignatureAlgorithm = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.billingApiVersion = input.readInt32();
                        this.hasBillingApiVersion = true;
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

    public static final class InAppPurchaseInformationResponseProto extends MessageNano {
        public PurchaseResultProto purchaseResult;
        public SignedDataProto signedResponse;
        public StatusBarNotificationProto[] statusBarNotification;

        public InAppPurchaseInformationResponseProto() {
            clear();
        }

        public InAppPurchaseInformationResponseProto clear() {
            this.signedResponse = null;
            this.statusBarNotification = StatusBarNotificationProto.emptyArray();
            this.purchaseResult = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.signedResponse != null) {
                output.writeMessage(1, this.signedResponse);
            }
            if (this.statusBarNotification != null && this.statusBarNotification.length > 0) {
                for (StatusBarNotificationProto element : this.statusBarNotification) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            if (this.purchaseResult != null) {
                output.writeMessage(3, this.purchaseResult);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.signedResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.signedResponse);
            }
            if (this.statusBarNotification != null && this.statusBarNotification.length > 0) {
                for (StatusBarNotificationProto element : this.statusBarNotification) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            if (this.purchaseResult != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.purchaseResult);
            }
            return size;
        }

        public InAppPurchaseInformationResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.signedResponse == null) {
                            this.signedResponse = new SignedDataProto();
                        }
                        input.readMessage(this.signedResponse);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.statusBarNotification == null) {
                            i = 0;
                        } else {
                            i = this.statusBarNotification.length;
                        }
                        StatusBarNotificationProto[] newArray = new StatusBarNotificationProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.statusBarNotification, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new StatusBarNotificationProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new StatusBarNotificationProto();
                        input.readMessage(newArray[i]);
                        this.statusBarNotification = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.purchaseResult == null) {
                            this.purchaseResult = new PurchaseResultProto();
                        }
                        input.readMessage(this.purchaseResult);
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

    public static final class InAppRestoreTransactionsRequestProto extends MessageNano {
        public int billingApiVersion;
        public boolean hasBillingApiVersion;
        public boolean hasNonce;
        public boolean hasSignatureAlgorithm;
        public long nonce;
        public String signatureAlgorithm;
        public SignatureHashProto signatureHash;

        public InAppRestoreTransactionsRequestProto() {
            clear();
        }

        public InAppRestoreTransactionsRequestProto clear() {
            this.signatureHash = null;
            this.nonce = 0;
            this.hasNonce = false;
            this.signatureAlgorithm = "";
            this.hasSignatureAlgorithm = false;
            this.billingApiVersion = 0;
            this.hasBillingApiVersion = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.signatureHash != null) {
                output.writeMessage(1, this.signatureHash);
            }
            if (this.hasNonce || this.nonce != 0) {
                output.writeInt64(2, this.nonce);
            }
            if (this.hasSignatureAlgorithm || !this.signatureAlgorithm.equals("")) {
                output.writeString(3, this.signatureAlgorithm);
            }
            if (this.hasBillingApiVersion || this.billingApiVersion != 0) {
                output.writeInt32(4, this.billingApiVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.signatureHash != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.signatureHash);
            }
            if (this.hasNonce || this.nonce != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.nonce);
            }
            if (this.hasSignatureAlgorithm || !this.signatureAlgorithm.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.signatureAlgorithm);
            }
            if (this.hasBillingApiVersion || this.billingApiVersion != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.billingApiVersion);
            }
            return size;
        }

        public InAppRestoreTransactionsRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.signatureHash == null) {
                            this.signatureHash = new SignatureHashProto();
                        }
                        input.readMessage(this.signatureHash);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.nonce = input.readInt64();
                        this.hasNonce = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.signatureAlgorithm = input.readString();
                        this.hasSignatureAlgorithm = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.billingApiVersion = input.readInt32();
                        this.hasBillingApiVersion = true;
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

    public static final class InAppRestoreTransactionsResponseProto extends MessageNano {
        public PurchaseResultProto purchaseResult;
        public SignedDataProto signedResponse;

        public InAppRestoreTransactionsResponseProto() {
            clear();
        }

        public InAppRestoreTransactionsResponseProto clear() {
            this.signedResponse = null;
            this.purchaseResult = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.signedResponse != null) {
                output.writeMessage(1, this.signedResponse);
            }
            if (this.purchaseResult != null) {
                output.writeMessage(2, this.purchaseResult);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.signedResponse != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.signedResponse);
            }
            if (this.purchaseResult != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.purchaseResult);
            }
            return size;
        }

        public InAppRestoreTransactionsResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.signedResponse == null) {
                            this.signedResponse = new SignedDataProto();
                        }
                        input.readMessage(this.signedResponse);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.purchaseResult == null) {
                            this.purchaseResult = new PurchaseResultProto();
                        }
                        input.readMessage(this.purchaseResult);
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

    public static final class ModifyCommentRequestProto extends MessageNano {
        public String assetId;
        public String flagMessage;
        public int flagType;
        public boolean hasAssetId;
        public boolean hasFlagMessage;
        public boolean hasFlagType;

        public ModifyCommentRequestProto() {
            clear();
        }

        public ModifyCommentRequestProto clear() {
            this.assetId = "";
            this.hasAssetId = false;
            this.flagType = 1;
            this.hasFlagType = false;
            this.flagMessage = "";
            this.hasFlagMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasAssetId || !this.assetId.equals("")) {
                output.writeString(1, this.assetId);
            }
            if (this.flagType != 1 || this.hasFlagType) {
                output.writeInt32(5, this.flagType);
            }
            if (this.hasFlagMessage || !this.flagMessage.equals("")) {
                output.writeString(6, this.flagMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasAssetId || !this.assetId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.assetId);
            }
            if (this.flagType != 1 || this.hasFlagType) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.flagType);
            }
            if (this.hasFlagMessage || !this.flagMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(6, this.flagMessage);
            }
            return size;
        }

        public ModifyCommentRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.assetId = input.readString();
                        this.hasAssetId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                                this.flagType = value;
                                this.hasFlagType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.flagMessage = input.readString();
                        this.hasFlagMessage = true;
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

    public static final class ModifyCommentResponseProto extends MessageNano {
        public ModifyCommentResponseProto() {
            clear();
        }

        public ModifyCommentResponseProto clear() {
            this.cachedSize = -1;
            return this;
        }

        public ModifyCommentResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static final class PendingNotificationsProto extends MessageNano {
        public Notification[] finskyNotification;
        public boolean hasNextCheckMillis;
        public long nextCheckMillis;
        public DataMessageProto[] notification;

        public PendingNotificationsProto() {
            clear();
        }

        public PendingNotificationsProto clear() {
            this.notification = DataMessageProto.emptyArray();
            this.finskyNotification = Notification.emptyArray();
            this.nextCheckMillis = 0;
            this.hasNextCheckMillis = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.notification != null && this.notification.length > 0) {
                for (DataMessageProto element : this.notification) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasNextCheckMillis || this.nextCheckMillis != 0) {
                output.writeInt64(2, this.nextCheckMillis);
            }
            if (this.finskyNotification != null && this.finskyNotification.length > 0) {
                for (Notification element2 : this.finskyNotification) {
                    if (element2 != null) {
                        output.writeMessage(3, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.notification != null && this.notification.length > 0) {
                for (DataMessageProto element : this.notification) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasNextCheckMillis || this.nextCheckMillis != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(2, this.nextCheckMillis);
            }
            if (this.finskyNotification != null && this.finskyNotification.length > 0) {
                for (Notification element2 : this.finskyNotification) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element2);
                    }
                }
            }
            return size;
        }

        public PendingNotificationsProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.notification == null) {
                            i = 0;
                        } else {
                            i = this.notification.length;
                        }
                        DataMessageProto[] newArray = new DataMessageProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.notification, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new DataMessageProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new DataMessageProto();
                        input.readMessage(newArray[i]);
                        this.notification = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.nextCheckMillis = input.readInt64();
                        this.hasNextCheckMillis = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.finskyNotification == null) {
                            i = 0;
                        } else {
                            i = this.finskyNotification.length;
                        }
                        Notification[] newArray2 = new Notification[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.finskyNotification, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Notification();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Notification();
                        input.readMessage(newArray2[i]);
                        this.finskyNotification = newArray2;
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

    public static final class PurchaseMetadataRequestProto extends MessageNano {
        public PurchaseMetadataRequestProto() {
            clear();
        }

        public PurchaseMetadataRequestProto clear() {
            this.cachedSize = -1;
            return this;
        }

        public PurchaseMetadataRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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

    public static final class PurchaseMetadataResponseProto extends MessageNano {
        public Countries countries;

        public static final class Countries extends MessageNano {
            public Country[] country;

            public static final class Country extends MessageNano {
                private static volatile Country[] _emptyArray;
                public boolean allowsReducedBillingAddress;
                public String countryCode;
                public String countryName;
                public boolean hasAllowsReducedBillingAddress;
                public boolean hasCountryCode;
                public boolean hasCountryName;
                public InstrumentAddressSpec[] instrumentAddressSpec;

                public static final class InstrumentAddressSpec extends MessageNano {
                    private static volatile InstrumentAddressSpec[] _emptyArray;
                    public BillingAddressSpec billingAddressSpec;
                    public boolean hasInstrumentFamily;
                    public int instrumentFamily;

                    public static com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec[] emptyArray() {
                        /* JADX: method processing error */
/*
                        Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                        /*
                        r0 = _emptyArray;
                        if (r0 != 0) goto L_0x0011;
                    L_0x0004:
                        r1 = com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK;
                        monitor-enter(r1);
                        r0 = _emptyArray;
                        if (r0 != 0) goto L_0x0010;
                    L_0x000b:
                        r0 = 0;
                        r0 = new com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec[r0];
                        _emptyArray = r0;
                    L_0x0010:
                        monitor-exit(r1);
                    L_0x0011:
                        r0 = _emptyArray;
                        return r0;
                    L_0x0014:
                        r0 = move-exception;
                        monitor-exit(r1);
                        throw r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec.emptyArray():com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country$InstrumentAddressSpec[]");
                    }

                    public InstrumentAddressSpec() {
                        /* JADX: method processing error */
/*
                        Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                        /*
                        r0 = this;
                        r0.<init>();
                        r0.clear();
                        return;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec.<init>():void");
                    }

                    public com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec clear() {
                        /* JADX: method processing error */
/*
                        Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                        /*
                        r1 = this;
                        r0 = 0;
                        r1.instrumentFamily = r0;
                        r1.hasInstrumentFamily = r0;
                        r0 = 0;
                        r1.billingAddressSpec = r0;
                        r0 = -1;
                        r1.cachedSize = r0;
                        return r1;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec.clear():com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country$InstrumentAddressSpec");
                    }

                    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r3) throws java.io.IOException {
                        /* JADX: method processing error */
/*
                        Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                        /*
                        r2 = this;
                        r0 = r2.instrumentFamily;
                        if (r0 != 0) goto L_0x0008;
                    L_0x0004:
                        r0 = r2.hasInstrumentFamily;
                        if (r0 == 0) goto L_0x000f;
                    L_0x0008:
                        r0 = 8;
                        r1 = r2.instrumentFamily;
                        r3.writeInt32(r0, r1);
                    L_0x000f:
                        r0 = r2.billingAddressSpec;
                        if (r0 == 0) goto L_0x001a;
                    L_0x0013:
                        r0 = 9;
                        r1 = r2.billingAddressSpec;
                        r3.writeMessage(r0, r1);
                    L_0x001a:
                        super.writeTo(r3);
                        return;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
                    }

                    protected int computeSerializedSize() {
                        /* JADX: method processing error */
/*
                        Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                        /*
                        r3 = this;
                        r0 = super.computeSerializedSize();
                        r1 = r3.instrumentFamily;
                        if (r1 != 0) goto L_0x000c;
                    L_0x0008:
                        r1 = r3.hasInstrumentFamily;
                        if (r1 == 0) goto L_0x0015;
                    L_0x000c:
                        r1 = 8;
                        r2 = r3.instrumentFamily;
                        r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeInt32Size(r1, r2);
                        r0 = r0 + r1;
                    L_0x0015:
                        r1 = r3.billingAddressSpec;
                        if (r1 == 0) goto L_0x0022;
                    L_0x0019:
                        r1 = 9;
                        r2 = r3.billingAddressSpec;
                        r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeMessageSize(r1, r2);
                        r0 = r0 + r1;
                    L_0x0022:
                        return r0;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec.computeSerializedSize():int");
                    }

                    public com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r4) throws java.io.IOException {
                        /* JADX: method processing error */
/*
                        Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                        /*
                        r3 = this;
                    L_0x0000:
                        r0 = r4.readTag();
                        switch(r0) {
                            case 0: goto L_0x000d;
                            case 64: goto L_0x000e;
                            case 74: goto L_0x001c;
                            default: goto L_0x0007;
                        };
                    L_0x0007:
                        r2 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r4, r0);
                        if (r2 != 0) goto L_0x0000;
                    L_0x000d:
                        return r3;
                    L_0x000e:
                        r1 = r4.readInt32();
                        switch(r1) {
                            case 0: goto L_0x0016;
                            case 1: goto L_0x0016;
                            case 2: goto L_0x0016;
                            case 3: goto L_0x0016;
                            case 4: goto L_0x0016;
                            case 5: goto L_0x0016;
                            case 6: goto L_0x0016;
                            case 7: goto L_0x0016;
                            case 8: goto L_0x0016;
                            case 9: goto L_0x0016;
                            case 100: goto L_0x0016;
                            default: goto L_0x0015;
                        };
                    L_0x0015:
                        goto L_0x0000;
                    L_0x0016:
                        r3.instrumentFamily = r1;
                        r2 = 1;
                        r3.hasInstrumentFamily = r2;
                        goto L_0x0000;
                    L_0x001c:
                        r2 = r3.billingAddressSpec;
                        if (r2 != 0) goto L_0x0027;
                    L_0x0020:
                        r2 = new com.google.android.finsky.protos.CommonDevice$BillingAddressSpec;
                        r2.<init>();
                        r3.billingAddressSpec = r2;
                    L_0x0027:
                        r2 = r3.billingAddressSpec;
                        r4.readMessage(r2);
                        goto L_0x0000;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country$InstrumentAddressSpec");
                    }
                }

                public static com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country[] emptyArray() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = _emptyArray;
                    if (r0 != 0) goto L_0x0011;
                L_0x0004:
                    r1 = com.google.protobuf.nano.InternalNano.LAZY_INIT_LOCK;
                    monitor-enter(r1);
                    r0 = _emptyArray;
                    if (r0 != 0) goto L_0x0010;
                L_0x000b:
                    r0 = 0;
                    r0 = new com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country[r0];
                    _emptyArray = r0;
                L_0x0010:
                    monitor-exit(r1);
                L_0x0011:
                    r0 = _emptyArray;
                    return r0;
                L_0x0014:
                    r0 = move-exception;
                    monitor-exit(r1);
                    throw r0;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.emptyArray():com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country[]");
                }

                public Country() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r0 = this;
                    r0.<init>();
                    r0.clear();
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.<init>():void");
                }

                public com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country clear() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r2 = this;
                    r1 = 0;
                    r0 = "";
                    r2.countryCode = r0;
                    r2.hasCountryCode = r1;
                    r0 = "";
                    r2.countryName = r0;
                    r2.hasCountryName = r1;
                    r2.allowsReducedBillingAddress = r1;
                    r2.hasAllowsReducedBillingAddress = r1;
                    r0 = com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec.emptyArray();
                    r2.instrumentAddressSpec = r0;
                    r0 = -1;
                    r2.cachedSize = r0;
                    return r2;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.clear():com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country");
                }

                public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r5) throws java.io.IOException {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r4 = this;
                    r2 = r4.hasCountryCode;
                    if (r2 != 0) goto L_0x000e;
                L_0x0004:
                    r2 = r4.countryCode;
                    r3 = "";
                    r2 = r2.equals(r3);
                    if (r2 != 0) goto L_0x0014;
                L_0x000e:
                    r2 = 3;
                    r3 = r4.countryCode;
                    r5.writeString(r2, r3);
                L_0x0014:
                    r2 = r4.hasCountryName;
                    if (r2 != 0) goto L_0x0022;
                L_0x0018:
                    r2 = r4.countryName;
                    r3 = "";
                    r2 = r2.equals(r3);
                    if (r2 != 0) goto L_0x0028;
                L_0x0022:
                    r2 = 4;
                    r3 = r4.countryName;
                    r5.writeString(r2, r3);
                L_0x0028:
                    r2 = r4.hasAllowsReducedBillingAddress;
                    if (r2 != 0) goto L_0x0030;
                L_0x002c:
                    r2 = r4.allowsReducedBillingAddress;
                    if (r2 == 0) goto L_0x0036;
                L_0x0030:
                    r2 = 6;
                    r3 = r4.allowsReducedBillingAddress;
                    r5.writeBool(r2, r3);
                L_0x0036:
                    r2 = r4.instrumentAddressSpec;
                    if (r2 == 0) goto L_0x0052;
                L_0x003a:
                    r2 = r4.instrumentAddressSpec;
                    r2 = r2.length;
                    if (r2 <= 0) goto L_0x0052;
                L_0x003f:
                    r1 = 0;
                L_0x0040:
                    r2 = r4.instrumentAddressSpec;
                    r2 = r2.length;
                    if (r1 >= r2) goto L_0x0052;
                L_0x0045:
                    r2 = r4.instrumentAddressSpec;
                    r0 = r2[r1];
                    if (r0 == 0) goto L_0x004f;
                L_0x004b:
                    r2 = 7;
                    r5.writeGroup(r2, r0);
                L_0x004f:
                    r1 = r1 + 1;
                    goto L_0x0040;
                L_0x0052:
                    super.writeTo(r5);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
                }

                protected int computeSerializedSize() {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r5 = this;
                    r2 = super.computeSerializedSize();
                    r3 = r5.hasCountryCode;
                    if (r3 != 0) goto L_0x0012;
                L_0x0008:
                    r3 = r5.countryCode;
                    r4 = "";
                    r3 = r3.equals(r4);
                    if (r3 != 0) goto L_0x001a;
                L_0x0012:
                    r3 = 3;
                    r4 = r5.countryCode;
                    r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r3, r4);
                    r2 = r2 + r3;
                L_0x001a:
                    r3 = r5.hasCountryName;
                    if (r3 != 0) goto L_0x0028;
                L_0x001e:
                    r3 = r5.countryName;
                    r4 = "";
                    r3 = r3.equals(r4);
                    if (r3 != 0) goto L_0x0030;
                L_0x0028:
                    r3 = 4;
                    r4 = r5.countryName;
                    r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeStringSize(r3, r4);
                    r2 = r2 + r3;
                L_0x0030:
                    r3 = r5.hasAllowsReducedBillingAddress;
                    if (r3 != 0) goto L_0x0038;
                L_0x0034:
                    r3 = r5.allowsReducedBillingAddress;
                    if (r3 == 0) goto L_0x0040;
                L_0x0038:
                    r3 = 6;
                    r4 = r5.allowsReducedBillingAddress;
                    r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeBoolSize(r3, r4);
                    r2 = r2 + r3;
                L_0x0040:
                    r3 = r5.instrumentAddressSpec;
                    if (r3 == 0) goto L_0x005e;
                L_0x0044:
                    r3 = r5.instrumentAddressSpec;
                    r3 = r3.length;
                    if (r3 <= 0) goto L_0x005e;
                L_0x0049:
                    r1 = 0;
                L_0x004a:
                    r3 = r5.instrumentAddressSpec;
                    r3 = r3.length;
                    if (r1 >= r3) goto L_0x005e;
                L_0x004f:
                    r3 = r5.instrumentAddressSpec;
                    r0 = r3[r1];
                    if (r0 == 0) goto L_0x005b;
                L_0x0055:
                    r3 = 7;
                    r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeGroupSize(r3, r0);
                    r2 = r2 + r3;
                L_0x005b:
                    r1 = r1 + 1;
                    goto L_0x004a;
                L_0x005e:
                    return r2;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.computeSerializedSize():int");
                }

                public com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r9) throws java.io.IOException {
                    /* JADX: method processing error */
/*
                    Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                    /*
                    r8 = this;
                    r7 = 7;
                    r6 = 1;
                    r4 = 0;
                L_0x0003:
                    r3 = r9.readTag();
                    switch(r3) {
                        case 0: goto L_0x0010;
                        case 26: goto L_0x0011;
                        case 34: goto L_0x001a;
                        case 48: goto L_0x0023;
                        case 59: goto L_0x002c;
                        default: goto L_0x000a;
                    };
                L_0x000a:
                    r5 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r9, r3);
                    if (r5 != 0) goto L_0x0003;
                L_0x0010:
                    return r8;
                L_0x0011:
                    r5 = r9.readString();
                    r8.countryCode = r5;
                    r8.hasCountryCode = r6;
                    goto L_0x0003;
                L_0x001a:
                    r5 = r9.readString();
                    r8.countryName = r5;
                    r8.hasCountryName = r6;
                    goto L_0x0003;
                L_0x0023:
                    r5 = r9.readBool();
                    r8.allowsReducedBillingAddress = r5;
                    r8.hasAllowsReducedBillingAddress = r6;
                    goto L_0x0003;
                L_0x002c:
                    r5 = 59;
                    r0 = com.google.protobuf.nano.WireFormatNano.getRepeatedFieldArrayLength(r9, r5);
                    r5 = r8.instrumentAddressSpec;
                    if (r5 != 0) goto L_0x0059;
                L_0x0036:
                    r1 = r4;
                L_0x0037:
                    r5 = r1 + r0;
                    r2 = new com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.InstrumentAddressSpec[r5];
                    if (r1 == 0) goto L_0x0042;
                L_0x003d:
                    r5 = r8.instrumentAddressSpec;
                    java.lang.System.arraycopy(r5, r4, r2, r4, r1);
                L_0x0042:
                    r5 = r2.length;
                    r5 = r5 + -1;
                    if (r1 >= r5) goto L_0x005d;
                L_0x0047:
                    r5 = new com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country$InstrumentAddressSpec;
                    r5.<init>();
                    r2[r1] = r5;
                    r5 = r2[r1];
                    r9.readGroup(r5, r7);
                    r9.readTag();
                    r1 = r1 + 1;
                    goto L_0x0042;
                L_0x0059:
                    r5 = r8.instrumentAddressSpec;
                    r1 = r5.length;
                    goto L_0x0037;
                L_0x005d:
                    r5 = new com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country$InstrumentAddressSpec;
                    r5.<init>();
                    r2[r1] = r5;
                    r5 = r2[r1];
                    r9.readGroup(r5, r7);
                    r8.instrumentAddressSpec = r2;
                    goto L_0x0003;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country");
                }
            }

            public Countries() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r0 = this;
                r0.<init>();
                r0.clear();
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.<init>():void");
            }

            public com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries clear() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r1 = this;
                r0 = com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country.emptyArray();
                r1.country = r0;
                r0 = -1;
                r1.cachedSize = r0;
                return r1;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.clear():com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries");
            }

            public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r4) throws java.io.IOException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r3 = this;
                r2 = r3.country;
                if (r2 == 0) goto L_0x001c;
            L_0x0004:
                r2 = r3.country;
                r2 = r2.length;
                if (r2 <= 0) goto L_0x001c;
            L_0x0009:
                r1 = 0;
            L_0x000a:
                r2 = r3.country;
                r2 = r2.length;
                if (r1 >= r2) goto L_0x001c;
            L_0x000f:
                r2 = r3.country;
                r0 = r2[r1];
                if (r0 == 0) goto L_0x0019;
            L_0x0015:
                r2 = 2;
                r4.writeGroup(r2, r0);
            L_0x0019:
                r1 = r1 + 1;
                goto L_0x000a;
            L_0x001c:
                super.writeTo(r4);
                return;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
            }

            protected int computeSerializedSize() {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r4 = this;
                r2 = super.computeSerializedSize();
                r3 = r4.country;
                if (r3 == 0) goto L_0x0022;
            L_0x0008:
                r3 = r4.country;
                r3 = r3.length;
                if (r3 <= 0) goto L_0x0022;
            L_0x000d:
                r1 = 0;
            L_0x000e:
                r3 = r4.country;
                r3 = r3.length;
                if (r1 >= r3) goto L_0x0022;
            L_0x0013:
                r3 = r4.country;
                r0 = r3[r1];
                if (r0 == 0) goto L_0x001f;
            L_0x0019:
                r3 = 2;
                r3 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeGroupSize(r3, r0);
                r2 = r2 + r3;
            L_0x001f:
                r1 = r1 + 1;
                goto L_0x000e;
            L_0x0022:
                return r2;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.computeSerializedSize():int");
            }

            public com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r8) throws java.io.IOException {
                /* JADX: method processing error */
/*
                Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
                /*
                r7 = this;
                r6 = 2;
                r4 = 0;
            L_0x0002:
                r3 = r8.readTag();
                switch(r3) {
                    case 0: goto L_0x000f;
                    case 19: goto L_0x0010;
                    default: goto L_0x0009;
                };
            L_0x0009:
                r5 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r8, r3);
                if (r5 != 0) goto L_0x0002;
            L_0x000f:
                return r7;
            L_0x0010:
                r5 = 19;
                r0 = com.google.protobuf.nano.WireFormatNano.getRepeatedFieldArrayLength(r8, r5);
                r5 = r7.country;
                if (r5 != 0) goto L_0x003d;
            L_0x001a:
                r1 = r4;
            L_0x001b:
                r5 = r1 + r0;
                r2 = new com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.Country[r5];
                if (r1 == 0) goto L_0x0026;
            L_0x0021:
                r5 = r7.country;
                java.lang.System.arraycopy(r5, r4, r2, r4, r1);
            L_0x0026:
                r5 = r2.length;
                r5 = r5 + -1;
                if (r1 >= r5) goto L_0x0041;
            L_0x002b:
                r5 = new com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country;
                r5.<init>();
                r2[r1] = r5;
                r5 = r2[r1];
                r8.readGroup(r5, r6);
                r8.readTag();
                r1 = r1 + 1;
                goto L_0x0026;
            L_0x003d:
                r5 = r7.country;
                r1 = r5.length;
                goto L_0x001b;
            L_0x0041:
                r5 = new com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries$Country;
                r5.<init>();
                r2[r1] = r5;
                r5 = r2[r1];
                r8.readGroup(r5, r6);
                r7.country = r2;
                goto L_0x0002;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.Countries.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries");
            }
        }

        public PurchaseMetadataResponseProto() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r0 = this;
            r0.<init>();
            r0.clear();
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.<init>():void");
        }

        public com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto clear() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r1 = this;
            r0 = 0;
            r1.countries = r0;
            r0 = -1;
            r1.cachedSize = r0;
            return r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.clear():com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto");
        }

        public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano r3) throws java.io.IOException {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r2 = this;
            r0 = r2.countries;
            if (r0 == 0) goto L_0x000a;
        L_0x0004:
            r0 = 1;
            r1 = r2.countries;
            r3.writeGroup(r0, r1);
        L_0x000a:
            super.writeTo(r3);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano):void");
        }

        protected int computeSerializedSize() {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r3 = this;
            r0 = super.computeSerializedSize();
            r1 = r3.countries;
            if (r1 == 0) goto L_0x0010;
        L_0x0008:
            r1 = 1;
            r2 = r3.countries;
            r1 = com.google.protobuf.nano.CodedOutputByteBufferNano.computeGroupSize(r1, r2);
            r0 = r0 + r1;
        L_0x0010:
            return r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.computeSerializedSize():int");
        }

        public com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano r4) throws java.io.IOException {
            /* JADX: method processing error */
/*
            Error: jadx.core.utils.exceptions.JadxRuntimeException: SSA rename variables already executed
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:119)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
            /*
            r3 = this;
        L_0x0000:
            r0 = r4.readTag();
            switch(r0) {
                case 0: goto L_0x000d;
                case 11: goto L_0x000e;
                default: goto L_0x0007;
            };
        L_0x0007:
            r1 = com.google.protobuf.nano.WireFormatNano.parseUnknownField(r4, r0);
            if (r1 != 0) goto L_0x0000;
        L_0x000d:
            return r3;
        L_0x000e:
            r1 = r3.countries;
            if (r1 != 0) goto L_0x0019;
        L_0x0012:
            r1 = new com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto$Countries;
            r1.<init>();
            r3.countries = r1;
        L_0x0019:
            r1 = r3.countries;
            r2 = 1;
            r4.readGroup(r1, r2);
            goto L_0x0000;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.protos.VendingProtos.PurchaseMetadataResponseProto.mergeFrom(com.google.protobuf.nano.CodedInputByteBufferNano):com.google.android.finsky.protos.VendingProtos$PurchaseMetadataResponseProto");
        }
    }

    public static final class PurchaseResultProto extends MessageNano {
        public boolean hasResultCode;
        public boolean hasResultCodeMessage;
        public int resultCode;
        public String resultCodeMessage;

        public PurchaseResultProto() {
            clear();
        }

        public PurchaseResultProto clear() {
            this.resultCode = 0;
            this.hasResultCode = false;
            this.resultCodeMessage = "";
            this.hasResultCodeMessage = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.resultCode != 0 || this.hasResultCode) {
                output.writeInt32(1, this.resultCode);
            }
            if (this.hasResultCodeMessage || !this.resultCodeMessage.equals("")) {
                output.writeString(2, this.resultCodeMessage);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.resultCode != 0 || this.hasResultCode) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.resultCode);
            }
            if (this.hasResultCodeMessage || !this.resultCodeMessage.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.resultCodeMessage);
            }
            return size;
        }

        public PurchaseResultProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.resultCode = value;
                                this.hasResultCode = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.resultCodeMessage = input.readString();
                        this.hasResultCodeMessage = true;
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

    public static final class RequestPropertiesProto extends MessageNano {
        public String aid;
        public String clientId;
        public boolean hasAid;
        public boolean hasClientId;
        public boolean hasLoggingId;
        public boolean hasOperatorName;
        public boolean hasOperatorNumericName;
        public boolean hasProductNameAndVersion;
        public boolean hasSimOperatorName;
        public boolean hasSimOperatorNumericName;
        public boolean hasSoftwareVersion;
        public boolean hasUserAuthToken;
        public boolean hasUserAuthTokenSecure;
        public boolean hasUserCountry;
        public boolean hasUserLanguage;
        public String loggingId;
        public String operatorName;
        public String operatorNumericName;
        public String productNameAndVersion;
        public String simOperatorName;
        public String simOperatorNumericName;
        public int softwareVersion;
        public String userAuthToken;
        public boolean userAuthTokenSecure;
        public String userCountry;
        public String userLanguage;

        public RequestPropertiesProto() {
            clear();
        }

        public RequestPropertiesProto clear() {
            this.userAuthToken = "";
            this.hasUserAuthToken = false;
            this.userAuthTokenSecure = false;
            this.hasUserAuthTokenSecure = false;
            this.softwareVersion = 0;
            this.hasSoftwareVersion = false;
            this.aid = "";
            this.hasAid = false;
            this.productNameAndVersion = "";
            this.hasProductNameAndVersion = false;
            this.userLanguage = "";
            this.hasUserLanguage = false;
            this.userCountry = "";
            this.hasUserCountry = false;
            this.operatorName = "";
            this.hasOperatorName = false;
            this.simOperatorName = "";
            this.hasSimOperatorName = false;
            this.operatorNumericName = "";
            this.hasOperatorNumericName = false;
            this.simOperatorNumericName = "";
            this.hasSimOperatorNumericName = false;
            this.clientId = "";
            this.hasClientId = false;
            this.loggingId = "";
            this.hasLoggingId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUserAuthToken || !this.userAuthToken.equals("")) {
                output.writeString(1, this.userAuthToken);
            }
            if (this.hasUserAuthTokenSecure || this.userAuthTokenSecure) {
                output.writeBool(2, this.userAuthTokenSecure);
            }
            if (this.hasSoftwareVersion || this.softwareVersion != 0) {
                output.writeInt32(3, this.softwareVersion);
            }
            if (this.hasAid || !this.aid.equals("")) {
                output.writeString(4, this.aid);
            }
            if (this.hasProductNameAndVersion || !this.productNameAndVersion.equals("")) {
                output.writeString(5, this.productNameAndVersion);
            }
            if (this.hasUserLanguage || !this.userLanguage.equals("")) {
                output.writeString(6, this.userLanguage);
            }
            if (this.hasUserCountry || !this.userCountry.equals("")) {
                output.writeString(7, this.userCountry);
            }
            if (this.hasOperatorName || !this.operatorName.equals("")) {
                output.writeString(8, this.operatorName);
            }
            if (this.hasSimOperatorName || !this.simOperatorName.equals("")) {
                output.writeString(9, this.simOperatorName);
            }
            if (this.hasOperatorNumericName || !this.operatorNumericName.equals("")) {
                output.writeString(10, this.operatorNumericName);
            }
            if (this.hasSimOperatorNumericName || !this.simOperatorNumericName.equals("")) {
                output.writeString(11, this.simOperatorNumericName);
            }
            if (this.hasClientId || !this.clientId.equals("")) {
                output.writeString(12, this.clientId);
            }
            if (this.hasLoggingId || !this.loggingId.equals("")) {
                output.writeString(13, this.loggingId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasUserAuthToken || !this.userAuthToken.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.userAuthToken);
            }
            if (this.hasUserAuthTokenSecure || this.userAuthTokenSecure) {
                size += CodedOutputByteBufferNano.computeBoolSize(2, this.userAuthTokenSecure);
            }
            if (this.hasSoftwareVersion || this.softwareVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.softwareVersion);
            }
            if (this.hasAid || !this.aid.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.aid);
            }
            if (this.hasProductNameAndVersion || !this.productNameAndVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.productNameAndVersion);
            }
            if (this.hasUserLanguage || !this.userLanguage.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.userLanguage);
            }
            if (this.hasUserCountry || !this.userCountry.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.userCountry);
            }
            if (this.hasOperatorName || !this.operatorName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.operatorName);
            }
            if (this.hasSimOperatorName || !this.simOperatorName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.simOperatorName);
            }
            if (this.hasOperatorNumericName || !this.operatorNumericName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.operatorNumericName);
            }
            if (this.hasSimOperatorNumericName || !this.simOperatorNumericName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.simOperatorNumericName);
            }
            if (this.hasClientId || !this.clientId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.clientId);
            }
            if (this.hasLoggingId || !this.loggingId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(13, this.loggingId);
            }
            return size;
        }

        public RequestPropertiesProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.userAuthToken = input.readString();
                        this.hasUserAuthToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.userAuthTokenSecure = input.readBool();
                        this.hasUserAuthTokenSecure = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.softwareVersion = input.readInt32();
                        this.hasSoftwareVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.aid = input.readString();
                        this.hasAid = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.productNameAndVersion = input.readString();
                        this.hasProductNameAndVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.userLanguage = input.readString();
                        this.hasUserLanguage = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.userCountry = input.readString();
                        this.hasUserCountry = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.operatorName = input.readString();
                        this.hasOperatorName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.simOperatorName = input.readString();
                        this.hasSimOperatorName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.operatorNumericName = input.readString();
                        this.hasOperatorNumericName = true;
                        continue;
                    case 90:
                        this.simOperatorNumericName = input.readString();
                        this.hasSimOperatorNumericName = true;
                        continue;
                    case 98:
                        this.clientId = input.readString();
                        this.hasClientId = true;
                        continue;
                    case 106:
                        this.loggingId = input.readString();
                        this.hasLoggingId = true;
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

    public static final class RequestProto extends MessageNano {
        public Request[] request;
        public RequestPropertiesProto requestProperties;

        public static final class Request extends MessageNano {
            private static volatile Request[] _emptyArray;
            public AckNotificationsRequestProto ackNotificationsRequest;
            public BillingEventRequestProto billingEventRequest;
            public CheckForNotificationsRequestProto checkForNotificationsRequest;
            public CheckLicenseRequestProto checkLicenseRequest;
            public ContentSyncRequestProto contentSyncRequest;
            public InAppPurchaseInformationRequestProto inAppPurchaseInformationRequest;
            public InAppRestoreTransactionsRequestProto inAppRestoreTransactionsRequest;
            public ModifyCommentRequestProto modifyCommentRequest;
            public PurchaseMetadataRequestProto purchaseMetadataRequest;
            public RestoreApplicationsRequestProto restoreApplicationsRequest;

            public static Request[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Request[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Request() {
                clear();
            }

            public Request clear() {
                this.modifyCommentRequest = null;
                this.contentSyncRequest = null;
                this.purchaseMetadataRequest = null;
                this.checkLicenseRequest = null;
                this.restoreApplicationsRequest = null;
                this.billingEventRequest = null;
                this.inAppRestoreTransactionsRequest = null;
                this.inAppPurchaseInformationRequest = null;
                this.checkForNotificationsRequest = null;
                this.ackNotificationsRequest = null;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.modifyCommentRequest != null) {
                    output.writeMessage(6, this.modifyCommentRequest);
                }
                if (this.contentSyncRequest != null) {
                    output.writeMessage(9, this.contentSyncRequest);
                }
                if (this.purchaseMetadataRequest != null) {
                    output.writeMessage(13, this.purchaseMetadataRequest);
                }
                if (this.checkLicenseRequest != null) {
                    output.writeMessage(18, this.checkLicenseRequest);
                }
                if (this.restoreApplicationsRequest != null) {
                    output.writeMessage(24, this.restoreApplicationsRequest);
                }
                if (this.billingEventRequest != null) {
                    output.writeMessage(26, this.billingEventRequest);
                }
                if (this.inAppRestoreTransactionsRequest != null) {
                    output.writeMessage(31, this.inAppRestoreTransactionsRequest);
                }
                if (this.inAppPurchaseInformationRequest != null) {
                    output.writeMessage(32, this.inAppPurchaseInformationRequest);
                }
                if (this.checkForNotificationsRequest != null) {
                    output.writeMessage(33, this.checkForNotificationsRequest);
                }
                if (this.ackNotificationsRequest != null) {
                    output.writeMessage(34, this.ackNotificationsRequest);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.modifyCommentRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(6, this.modifyCommentRequest);
                }
                if (this.contentSyncRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(9, this.contentSyncRequest);
                }
                if (this.purchaseMetadataRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(13, this.purchaseMetadataRequest);
                }
                if (this.checkLicenseRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(18, this.checkLicenseRequest);
                }
                if (this.restoreApplicationsRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(24, this.restoreApplicationsRequest);
                }
                if (this.billingEventRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(26, this.billingEventRequest);
                }
                if (this.inAppRestoreTransactionsRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(31, this.inAppRestoreTransactionsRequest);
                }
                if (this.inAppPurchaseInformationRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(32, this.inAppPurchaseInformationRequest);
                }
                if (this.checkForNotificationsRequest != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(33, this.checkForNotificationsRequest);
                }
                if (this.ackNotificationsRequest != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(34, this.ackNotificationsRequest);
                }
                return size;
            }

            public Request mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                            if (this.modifyCommentRequest == null) {
                                this.modifyCommentRequest = new ModifyCommentRequestProto();
                            }
                            input.readMessage(this.modifyCommentRequest);
                            continue;
                        case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                            if (this.contentSyncRequest == null) {
                                this.contentSyncRequest = new ContentSyncRequestProto();
                            }
                            input.readMessage(this.contentSyncRequest);
                            continue;
                        case 106:
                            if (this.purchaseMetadataRequest == null) {
                                this.purchaseMetadataRequest = new PurchaseMetadataRequestProto();
                            }
                            input.readMessage(this.purchaseMetadataRequest);
                            continue;
                        case 146:
                            if (this.checkLicenseRequest == null) {
                                this.checkLicenseRequest = new CheckLicenseRequestProto();
                            }
                            input.readMessage(this.checkLicenseRequest);
                            continue;
                        case 194:
                            if (this.restoreApplicationsRequest == null) {
                                this.restoreApplicationsRequest = new RestoreApplicationsRequestProto();
                            }
                            input.readMessage(this.restoreApplicationsRequest);
                            continue;
                        case 210:
                            if (this.billingEventRequest == null) {
                                this.billingEventRequest = new BillingEventRequestProto();
                            }
                            input.readMessage(this.billingEventRequest);
                            continue;
                        case 250:
                            if (this.inAppRestoreTransactionsRequest == null) {
                                this.inAppRestoreTransactionsRequest = new InAppRestoreTransactionsRequestProto();
                            }
                            input.readMessage(this.inAppRestoreTransactionsRequest);
                            continue;
                        case 258:
                            if (this.inAppPurchaseInformationRequest == null) {
                                this.inAppPurchaseInformationRequest = new InAppPurchaseInformationRequestProto();
                            }
                            input.readMessage(this.inAppPurchaseInformationRequest);
                            continue;
                        case 266:
                            if (this.checkForNotificationsRequest == null) {
                                this.checkForNotificationsRequest = new CheckForNotificationsRequestProto();
                            }
                            input.readMessage(this.checkForNotificationsRequest);
                            continue;
                        case 274:
                            if (this.ackNotificationsRequest == null) {
                                this.ackNotificationsRequest = new AckNotificationsRequestProto();
                            }
                            input.readMessage(this.ackNotificationsRequest);
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

        public RequestProto() {
            clear();
        }

        public RequestProto clear() {
            this.requestProperties = null;
            this.request = Request.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.requestProperties != null) {
                output.writeMessage(1, this.requestProperties);
            }
            if (this.request != null && this.request.length > 0) {
                for (Request element : this.request) {
                    if (element != null) {
                        output.writeGroup(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.requestProperties != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.requestProperties);
            }
            if (this.request != null && this.request.length > 0) {
                for (Request element : this.request) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeGroupSize(2, element);
                    }
                }
            }
            return size;
        }

        public RequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.requestProperties == null) {
                            this.requestProperties = new RequestPropertiesProto();
                        }
                        input.readMessage(this.requestProperties);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 19);
                        if (this.request == null) {
                            i = 0;
                        } else {
                            i = this.request.length;
                        }
                        Request[] newArray = new Request[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.request, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Request();
                            input.readGroup(newArray[i], 2);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Request();
                        input.readGroup(newArray[i], 2);
                        this.request = newArray;
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

    public static final class ResponsePropertiesProto extends MessageNano {
        public boolean hasResult;
        public int result;

        public ResponsePropertiesProto() {
            clear();
        }

        public ResponsePropertiesProto clear() {
            this.result = 0;
            this.hasResult = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.result != 0 || this.hasResult) {
                output.writeInt32(1, this.result);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.result != 0 || this.hasResult) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.result);
            }
            return size;
        }

        public ResponsePropertiesProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.result = value;
                                this.hasResult = true;
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

    public static final class ResponseProto extends MessageNano {
        public PendingNotificationsProto pendingNotifications;
        public Response[] response;

        public static final class Response extends MessageNano {
            private static volatile Response[] _emptyArray;
            public AckNotificationsResponseProto ackNotificationsResponse;
            public BillingEventResponseProto billingEventResponse;
            public CheckLicenseResponseProto checkLicenseResponse;
            public ContentSyncResponseProto contentSyncResponse;
            public GetAssetResponseProto getAssetResponse;
            public GetMarketMetadataResponseProto getMarketMetadataResponse;
            public InAppPurchaseInformationResponseProto inAppPurchaseInformationResponse;
            public InAppRestoreTransactionsResponseProto inAppRestoreTransactionsResponse;
            public ModifyCommentResponseProto modifyCommentResponse;
            public PurchaseMetadataResponseProto purchaseMetadataResponse;
            public ResponsePropertiesProto responseProperties;
            public RestoreApplicationsResponseProto restoreApplicationResponse;

            public static Response[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Response[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Response() {
                clear();
            }

            public Response clear() {
                this.responseProperties = null;
                this.modifyCommentResponse = null;
                this.contentSyncResponse = null;
                this.getAssetResponse = null;
                this.purchaseMetadataResponse = null;
                this.checkLicenseResponse = null;
                this.getMarketMetadataResponse = null;
                this.restoreApplicationResponse = null;
                this.billingEventResponse = null;
                this.inAppRestoreTransactionsResponse = null;
                this.inAppPurchaseInformationResponse = null;
                this.ackNotificationsResponse = null;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.responseProperties != null) {
                    output.writeMessage(2, this.responseProperties);
                }
                if (this.modifyCommentResponse != null) {
                    output.writeMessage(5, this.modifyCommentResponse);
                }
                if (this.contentSyncResponse != null) {
                    output.writeMessage(8, this.contentSyncResponse);
                }
                if (this.getAssetResponse != null) {
                    output.writeMessage(9, this.getAssetResponse);
                }
                if (this.purchaseMetadataResponse != null) {
                    output.writeMessage(12, this.purchaseMetadataResponse);
                }
                if (this.checkLicenseResponse != null) {
                    output.writeMessage(17, this.checkLicenseResponse);
                }
                if (this.getMarketMetadataResponse != null) {
                    output.writeMessage(18, this.getMarketMetadataResponse);
                }
                if (this.restoreApplicationResponse != null) {
                    output.writeMessage(23, this.restoreApplicationResponse);
                }
                if (this.billingEventResponse != null) {
                    output.writeMessage(25, this.billingEventResponse);
                }
                if (this.inAppRestoreTransactionsResponse != null) {
                    output.writeMessage(30, this.inAppRestoreTransactionsResponse);
                }
                if (this.inAppPurchaseInformationResponse != null) {
                    output.writeMessage(31, this.inAppPurchaseInformationResponse);
                }
                if (this.ackNotificationsResponse != null) {
                    output.writeMessage(33, this.ackNotificationsResponse);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.responseProperties != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, this.responseProperties);
                }
                if (this.modifyCommentResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(5, this.modifyCommentResponse);
                }
                if (this.contentSyncResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(8, this.contentSyncResponse);
                }
                if (this.getAssetResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(9, this.getAssetResponse);
                }
                if (this.purchaseMetadataResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(12, this.purchaseMetadataResponse);
                }
                if (this.checkLicenseResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(17, this.checkLicenseResponse);
                }
                if (this.getMarketMetadataResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(18, this.getMarketMetadataResponse);
                }
                if (this.restoreApplicationResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(23, this.restoreApplicationResponse);
                }
                if (this.billingEventResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(25, this.billingEventResponse);
                }
                if (this.inAppRestoreTransactionsResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(30, this.inAppRestoreTransactionsResponse);
                }
                if (this.inAppPurchaseInformationResponse != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(31, this.inAppPurchaseInformationResponse);
                }
                if (this.ackNotificationsResponse != null) {
                    return size + CodedOutputByteBufferNano.computeMessageSize(33, this.ackNotificationsResponse);
                }
                return size;
            }

            public Response mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            if (this.responseProperties == null) {
                                this.responseProperties = new ResponsePropertiesProto();
                            }
                            input.readMessage(this.responseProperties);
                            continue;
                        case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                            if (this.modifyCommentResponse == null) {
                                this.modifyCommentResponse = new ModifyCommentResponseProto();
                            }
                            input.readMessage(this.modifyCommentResponse);
                            continue;
                        case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                            if (this.contentSyncResponse == null) {
                                this.contentSyncResponse = new ContentSyncResponseProto();
                            }
                            input.readMessage(this.contentSyncResponse);
                            continue;
                        case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                            if (this.getAssetResponse == null) {
                                this.getAssetResponse = new GetAssetResponseProto();
                            }
                            input.readMessage(this.getAssetResponse);
                            continue;
                        case 98:
                            if (this.purchaseMetadataResponse == null) {
                                this.purchaseMetadataResponse = new PurchaseMetadataResponseProto();
                            }
                            input.readMessage(this.purchaseMetadataResponse);
                            continue;
                        case 138:
                            if (this.checkLicenseResponse == null) {
                                this.checkLicenseResponse = new CheckLicenseResponseProto();
                            }
                            input.readMessage(this.checkLicenseResponse);
                            continue;
                        case 146:
                            if (this.getMarketMetadataResponse == null) {
                                this.getMarketMetadataResponse = new GetMarketMetadataResponseProto();
                            }
                            input.readMessage(this.getMarketMetadataResponse);
                            continue;
                        case 186:
                            if (this.restoreApplicationResponse == null) {
                                this.restoreApplicationResponse = new RestoreApplicationsResponseProto();
                            }
                            input.readMessage(this.restoreApplicationResponse);
                            continue;
                        case 202:
                            if (this.billingEventResponse == null) {
                                this.billingEventResponse = new BillingEventResponseProto();
                            }
                            input.readMessage(this.billingEventResponse);
                            continue;
                        case 242:
                            if (this.inAppRestoreTransactionsResponse == null) {
                                this.inAppRestoreTransactionsResponse = new InAppRestoreTransactionsResponseProto();
                            }
                            input.readMessage(this.inAppRestoreTransactionsResponse);
                            continue;
                        case 250:
                            if (this.inAppPurchaseInformationResponse == null) {
                                this.inAppPurchaseInformationResponse = new InAppPurchaseInformationResponseProto();
                            }
                            input.readMessage(this.inAppPurchaseInformationResponse);
                            continue;
                        case 266:
                            if (this.ackNotificationsResponse == null) {
                                this.ackNotificationsResponse = new AckNotificationsResponseProto();
                            }
                            input.readMessage(this.ackNotificationsResponse);
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

        public ResponseProto() {
            clear();
        }

        public ResponseProto clear() {
            this.response = Response.emptyArray();
            this.pendingNotifications = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.response != null && this.response.length > 0) {
                for (Response element : this.response) {
                    if (element != null) {
                        output.writeGroup(1, element);
                    }
                }
            }
            if (this.pendingNotifications != null) {
                output.writeMessage(38, this.pendingNotifications);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.response != null && this.response.length > 0) {
                for (Response element : this.response) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeGroupSize(1, element);
                    }
                }
            }
            if (this.pendingNotifications != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(38, this.pendingNotifications);
            }
            return size;
        }

        public ResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.MapAttrs_uiZoomControls /*11*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 11);
                        if (this.response == null) {
                            i = 0;
                        } else {
                            i = this.response.length;
                        }
                        Response[] newArray = new Response[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.response, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Response();
                            input.readGroup(newArray[i], 1);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Response();
                        input.readGroup(newArray[i], 1);
                        this.response = newArray;
                        continue;
                    case 306:
                        if (this.pendingNotifications == null) {
                            this.pendingNotifications = new PendingNotificationsProto();
                        }
                        input.readMessage(this.pendingNotifications);
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

        public static ResponseProto parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ResponseProto) MessageNano.mergeFrom(new ResponseProto(), data);
        }
    }

    public static final class RestoreApplicationsRequestProto extends MessageNano {
        public String backupAndroidId;
        public DeviceConfigurationProto deviceConfiguration;
        public boolean hasBackupAndroidId;
        public boolean hasTosVersion;
        public String tosVersion;

        public RestoreApplicationsRequestProto() {
            clear();
        }

        public RestoreApplicationsRequestProto clear() {
            this.backupAndroidId = "";
            this.hasBackupAndroidId = false;
            this.tosVersion = "";
            this.hasTosVersion = false;
            this.deviceConfiguration = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasBackupAndroidId || !this.backupAndroidId.equals("")) {
                output.writeString(1, this.backupAndroidId);
            }
            if (this.hasTosVersion || !this.tosVersion.equals("")) {
                output.writeString(2, this.tosVersion);
            }
            if (this.deviceConfiguration != null) {
                output.writeMessage(3, this.deviceConfiguration);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasBackupAndroidId || !this.backupAndroidId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.backupAndroidId);
            }
            if (this.hasTosVersion || !this.tosVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.tosVersion);
            }
            if (this.deviceConfiguration != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.deviceConfiguration);
            }
            return size;
        }

        public RestoreApplicationsRequestProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.backupAndroidId = input.readString();
                        this.hasBackupAndroidId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.tosVersion = input.readString();
                        this.hasTosVersion = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
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

    public static final class RestoreApplicationsResponseProto extends MessageNano {
        public GetAssetResponseProto[] asset;

        public RestoreApplicationsResponseProto() {
            clear();
        }

        public RestoreApplicationsResponseProto clear() {
            this.asset = GetAssetResponseProto.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.asset != null && this.asset.length > 0) {
                for (GetAssetResponseProto element : this.asset) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.asset != null && this.asset.length > 0) {
                for (GetAssetResponseProto element : this.asset) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public RestoreApplicationsResponseProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.asset == null) {
                            i = 0;
                        } else {
                            i = this.asset.length;
                        }
                        GetAssetResponseProto[] newArray = new GetAssetResponseProto[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.asset, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new GetAssetResponseProto();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new GetAssetResponseProto();
                        input.readMessage(newArray[i]);
                        this.asset = newArray;
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

    public static final class SignatureHashProto extends MessageNano {
        public boolean hasHash;
        public boolean hasPackageName;
        public boolean hasVersionCode;
        public byte[] hash;
        public String packageName;
        public int versionCode;

        public SignatureHashProto() {
            clear();
        }

        public SignatureHashProto clear() {
            this.packageName = "";
            this.hasPackageName = false;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.hash = WireFormatNano.EMPTY_BYTES;
            this.hasHash = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPackageName || !this.packageName.equals("")) {
                output.writeString(1, this.packageName);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                output.writeInt32(2, this.versionCode);
            }
            if (this.hasHash || !Arrays.equals(this.hash, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(3, this.hash);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPackageName || !this.packageName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.packageName);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.versionCode);
            }
            if (this.hasHash || !Arrays.equals(this.hash, WireFormatNano.EMPTY_BYTES)) {
                return size + CodedOutputByteBufferNano.computeBytesSize(3, this.hash);
            }
            return size;
        }

        public SignatureHashProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.packageName = input.readString();
                        this.hasPackageName = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.versionCode = input.readInt32();
                        this.hasVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.hash = input.readBytes();
                        this.hasHash = true;
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

    public static final class SignedDataProto extends MessageNano {
        public boolean hasSignature;
        public boolean hasSignedData;
        public String signature;
        public String signedData;

        public SignedDataProto() {
            clear();
        }

        public SignedDataProto clear() {
            this.signedData = "";
            this.hasSignedData = false;
            this.signature = "";
            this.hasSignature = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSignedData || !this.signedData.equals("")) {
                output.writeString(1, this.signedData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                output.writeString(2, this.signature);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSignedData || !this.signedData.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.signedData);
            }
            if (this.hasSignature || !this.signature.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.signature);
            }
            return size;
        }

        public SignedDataProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.signedData = input.readString();
                        this.hasSignedData = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.signature = input.readString();
                        this.hasSignature = true;
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

    public static final class StatusBarNotificationProto extends MessageNano {
        private static volatile StatusBarNotificationProto[] _emptyArray;
        public String contentText;
        public String contentTitle;
        public boolean hasContentText;
        public boolean hasContentTitle;
        public boolean hasSeverity;
        public boolean hasTickerText;
        public int severity;
        public String tickerText;

        public static StatusBarNotificationProto[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new StatusBarNotificationProto[0];
                    }
                }
            }
            return _emptyArray;
        }

        public StatusBarNotificationProto() {
            clear();
        }

        public StatusBarNotificationProto clear() {
            this.tickerText = "";
            this.hasTickerText = false;
            this.contentTitle = "";
            this.hasContentTitle = false;
            this.contentText = "";
            this.hasContentText = false;
            this.severity = 0;
            this.hasSeverity = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTickerText || !this.tickerText.equals("")) {
                output.writeString(1, this.tickerText);
            }
            if (this.hasContentTitle || !this.contentTitle.equals("")) {
                output.writeString(2, this.contentTitle);
            }
            if (this.hasContentText || !this.contentText.equals("")) {
                output.writeString(3, this.contentText);
            }
            if (this.severity != 0 || this.hasSeverity) {
                output.writeInt32(4, this.severity);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTickerText || !this.tickerText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.tickerText);
            }
            if (this.hasContentTitle || !this.contentTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.contentTitle);
            }
            if (this.hasContentText || !this.contentText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.contentText);
            }
            if (this.severity != 0 || this.hasSeverity) {
                return size + CodedOutputByteBufferNano.computeInt32Size(4, this.severity);
            }
            return size;
        }

        public StatusBarNotificationProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.tickerText = input.readString();
                        this.hasTickerText = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.contentTitle = input.readString();
                        this.hasContentTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.contentText = input.readString();
                        this.hasContentText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                this.severity = value;
                                this.hasSeverity = true;
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
}
