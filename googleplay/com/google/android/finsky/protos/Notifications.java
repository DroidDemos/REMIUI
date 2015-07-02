package com.google.android.finsky.protos;

import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Notifications {

    public static final class AndroidAppNotificationData extends MessageNano {
        public String assetId;
        public boolean hasAssetId;
        public boolean hasVersionCode;
        public int versionCode;

        public AndroidAppNotificationData() {
            clear();
        }

        public AndroidAppNotificationData clear() {
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.assetId = "";
            this.hasAssetId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasVersionCode || this.versionCode != 0) {
                output.writeInt32(1, this.versionCode);
            }
            if (this.hasAssetId || !this.assetId.equals("")) {
                output.writeString(2, this.assetId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasVersionCode || this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.versionCode);
            }
            if (this.hasAssetId || !this.assetId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.assetId);
            }
            return size;
        }

        public AndroidAppNotificationData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.versionCode = input.readInt32();
                        this.hasVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.assetId = input.readString();
                        this.hasAssetId = true;
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

    public static final class InAppNotificationData extends MessageNano {
        public String checkoutOrderId;
        public boolean hasCheckoutOrderId;
        public boolean hasInAppNotificationId;
        public String inAppNotificationId;

        public InAppNotificationData() {
            clear();
        }

        public InAppNotificationData clear() {
            this.checkoutOrderId = "";
            this.hasCheckoutOrderId = false;
            this.inAppNotificationId = "";
            this.hasInAppNotificationId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasCheckoutOrderId || !this.checkoutOrderId.equals("")) {
                output.writeString(1, this.checkoutOrderId);
            }
            if (this.hasInAppNotificationId || !this.inAppNotificationId.equals("")) {
                output.writeString(2, this.inAppNotificationId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasCheckoutOrderId || !this.checkoutOrderId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.checkoutOrderId);
            }
            if (this.hasInAppNotificationId || !this.inAppNotificationId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.inAppNotificationId);
            }
            return size;
        }

        public InAppNotificationData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.checkoutOrderId = input.readString();
                        this.hasCheckoutOrderId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.inAppNotificationId = input.readString();
                        this.hasInAppNotificationId = true;
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

    public static final class LibraryDirtyData extends MessageNano {
        public int backend;
        public boolean hasBackend;
        public boolean hasLibraryId;
        public String libraryId;

        public LibraryDirtyData() {
            clear();
        }

        public LibraryDirtyData clear() {
            this.backend = 0;
            this.hasBackend = false;
            this.libraryId = "";
            this.hasLibraryId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.backend != 0 || this.hasBackend) {
                output.writeInt32(1, this.backend);
            }
            if (this.hasLibraryId || !this.libraryId.equals("")) {
                output.writeString(2, this.libraryId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.backend != 0 || this.hasBackend) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.backend);
            }
            if (this.hasLibraryId || !this.libraryId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.libraryId);
            }
            return size;
        }

        public LibraryDirtyData mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.backend = value;
                                this.hasBackend = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.libraryId = input.readString();
                        this.hasLibraryId = true;
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

    public static final class Notification extends MessageNano {
        private static volatile Notification[] _emptyArray;
        public AndroidAppNotificationData appData;
        public AndroidAppDeliveryData appDeliveryData;
        public String docTitle;
        public Docid docid;
        public boolean hasDocTitle;
        public boolean hasNotificationId;
        public boolean hasNotificationType;
        public boolean hasTimestamp;
        public boolean hasUserEmail;
        public InAppNotificationData inAppNotificationData;
        public LibraryDirtyData libraryDirtyData;
        public LibraryUpdate libraryUpdate;
        public String notificationId;
        public int notificationType;
        public PurchaseDeclinedData purchaseDeclinedData;
        public PurchaseRemovalData purchaseRemovalData;
        public long timestamp;
        public String userEmail;
        public UserNotificationData userNotificationData;

        public static Notification[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Notification[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Notification() {
            clear();
        }

        public Notification clear() {
            this.notificationType = 1;
            this.hasNotificationType = false;
            this.notificationId = "";
            this.hasNotificationId = false;
            this.timestamp = 0;
            this.hasTimestamp = false;
            this.docid = null;
            this.docTitle = "";
            this.hasDocTitle = false;
            this.userEmail = "";
            this.hasUserEmail = false;
            this.libraryUpdate = null;
            this.appData = null;
            this.appDeliveryData = null;
            this.purchaseRemovalData = null;
            this.purchaseDeclinedData = null;
            this.userNotificationData = null;
            this.inAppNotificationData = null;
            this.libraryDirtyData = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.notificationType != 1 || this.hasNotificationType) {
                output.writeInt32(1, this.notificationType);
            }
            if (this.hasTimestamp || this.timestamp != 0) {
                output.writeInt64(3, this.timestamp);
            }
            if (this.docid != null) {
                output.writeMessage(4, this.docid);
            }
            if (this.hasDocTitle || !this.docTitle.equals("")) {
                output.writeString(5, this.docTitle);
            }
            if (this.hasUserEmail || !this.userEmail.equals("")) {
                output.writeString(6, this.userEmail);
            }
            if (this.appData != null) {
                output.writeMessage(7, this.appData);
            }
            if (this.appDeliveryData != null) {
                output.writeMessage(8, this.appDeliveryData);
            }
            if (this.purchaseRemovalData != null) {
                output.writeMessage(9, this.purchaseRemovalData);
            }
            if (this.userNotificationData != null) {
                output.writeMessage(10, this.userNotificationData);
            }
            if (this.inAppNotificationData != null) {
                output.writeMessage(11, this.inAppNotificationData);
            }
            if (this.purchaseDeclinedData != null) {
                output.writeMessage(12, this.purchaseDeclinedData);
            }
            if (this.hasNotificationId || !this.notificationId.equals("")) {
                output.writeString(13, this.notificationId);
            }
            if (this.libraryUpdate != null) {
                output.writeMessage(14, this.libraryUpdate);
            }
            if (this.libraryDirtyData != null) {
                output.writeMessage(15, this.libraryDirtyData);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.notificationType != 1 || this.hasNotificationType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.notificationType);
            }
            if (this.hasTimestamp || this.timestamp != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.timestamp);
            }
            if (this.docid != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.docid);
            }
            if (this.hasDocTitle || !this.docTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.docTitle);
            }
            if (this.hasUserEmail || !this.userEmail.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.userEmail);
            }
            if (this.appData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.appData);
            }
            if (this.appDeliveryData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.appDeliveryData);
            }
            if (this.purchaseRemovalData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.purchaseRemovalData);
            }
            if (this.userNotificationData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.userNotificationData);
            }
            if (this.inAppNotificationData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.inAppNotificationData);
            }
            if (this.purchaseDeclinedData != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.purchaseDeclinedData);
            }
            if (this.hasNotificationId || !this.notificationId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.notificationId);
            }
            if (this.libraryUpdate != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(14, this.libraryUpdate);
            }
            if (this.libraryDirtyData != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(15, this.libraryDirtyData);
            }
            return size;
        }

        public Notification mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                            case R.styleable.WalletImFormEditText_required /*4*/:
                            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                                this.notificationType = value;
                                this.hasNotificationType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.timestamp = input.readInt64();
                        this.hasTimestamp = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.docid == null) {
                            this.docid = new Docid();
                        }
                        input.readMessage(this.docid);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.docTitle = input.readString();
                        this.hasDocTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.userEmail = input.readString();
                        this.hasUserEmail = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.appData == null) {
                            this.appData = new AndroidAppNotificationData();
                        }
                        input.readMessage(this.appData);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.appDeliveryData == null) {
                            this.appDeliveryData = new AndroidAppDeliveryData();
                        }
                        input.readMessage(this.appDeliveryData);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.purchaseRemovalData == null) {
                            this.purchaseRemovalData = new PurchaseRemovalData();
                        }
                        input.readMessage(this.purchaseRemovalData);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.userNotificationData == null) {
                            this.userNotificationData = new UserNotificationData();
                        }
                        input.readMessage(this.userNotificationData);
                        continue;
                    case 90:
                        if (this.inAppNotificationData == null) {
                            this.inAppNotificationData = new InAppNotificationData();
                        }
                        input.readMessage(this.inAppNotificationData);
                        continue;
                    case 98:
                        if (this.purchaseDeclinedData == null) {
                            this.purchaseDeclinedData = new PurchaseDeclinedData();
                        }
                        input.readMessage(this.purchaseDeclinedData);
                        continue;
                    case 106:
                        this.notificationId = input.readString();
                        this.hasNotificationId = true;
                        continue;
                    case 114:
                        if (this.libraryUpdate == null) {
                            this.libraryUpdate = new LibraryUpdate();
                        }
                        input.readMessage(this.libraryUpdate);
                        continue;
                    case 122:
                        if (this.libraryDirtyData == null) {
                            this.libraryDirtyData = new LibraryDirtyData();
                        }
                        input.readMessage(this.libraryDirtyData);
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

        public static Notification parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (Notification) MessageNano.mergeFrom(new Notification(), data);
        }
    }

    public static final class PurchaseDeclinedData extends MessageNano {
        public boolean hasReason;
        public boolean hasShowNotification;
        public int reason;
        public boolean showNotification;

        public PurchaseDeclinedData() {
            clear();
        }

        public PurchaseDeclinedData clear() {
            this.reason = 0;
            this.hasReason = false;
            this.showNotification = true;
            this.hasShowNotification = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.reason != 0 || this.hasReason) {
                output.writeInt32(1, this.reason);
            }
            if (this.hasShowNotification || !this.showNotification) {
                output.writeBool(2, this.showNotification);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.reason != 0 || this.hasReason) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.reason);
            }
            if (this.hasShowNotification || !this.showNotification) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.showNotification);
            }
            return size;
        }

        public PurchaseDeclinedData mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.reason = value;
                                this.hasReason = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.showNotification = input.readBool();
                        this.hasShowNotification = true;
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

    public static final class PurchaseRemovalData extends MessageNano {
        public boolean hasMalicious;
        public boolean malicious;

        public PurchaseRemovalData() {
            clear();
        }

        public PurchaseRemovalData clear() {
            this.malicious = false;
            this.hasMalicious = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasMalicious || this.malicious) {
                output.writeBool(1, this.malicious);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasMalicious || this.malicious) {
                return size + CodedOutputByteBufferNano.computeBoolSize(1, this.malicious);
            }
            return size;
        }

        public PurchaseRemovalData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.malicious = input.readBool();
                        this.hasMalicious = true;
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

    public static final class UserNotificationData extends MessageNano {
        public String dialogText;
        public String dialogTitle;
        public boolean hasDialogText;
        public boolean hasDialogTitle;
        public boolean hasNotificationText;
        public boolean hasNotificationTitle;
        public boolean hasTickerText;
        public String notificationText;
        public String notificationTitle;
        public String tickerText;

        public UserNotificationData() {
            clear();
        }

        public UserNotificationData clear() {
            this.notificationTitle = "";
            this.hasNotificationTitle = false;
            this.notificationText = "";
            this.hasNotificationText = false;
            this.tickerText = "";
            this.hasTickerText = false;
            this.dialogTitle = "";
            this.hasDialogTitle = false;
            this.dialogText = "";
            this.hasDialogText = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasNotificationTitle || !this.notificationTitle.equals("")) {
                output.writeString(1, this.notificationTitle);
            }
            if (this.hasNotificationText || !this.notificationText.equals("")) {
                output.writeString(2, this.notificationText);
            }
            if (this.hasTickerText || !this.tickerText.equals("")) {
                output.writeString(3, this.tickerText);
            }
            if (this.hasDialogTitle || !this.dialogTitle.equals("")) {
                output.writeString(4, this.dialogTitle);
            }
            if (this.hasDialogText || !this.dialogText.equals("")) {
                output.writeString(5, this.dialogText);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasNotificationTitle || !this.notificationTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.notificationTitle);
            }
            if (this.hasNotificationText || !this.notificationText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.notificationText);
            }
            if (this.hasTickerText || !this.tickerText.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.tickerText);
            }
            if (this.hasDialogTitle || !this.dialogTitle.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.dialogTitle);
            }
            if (this.hasDialogText || !this.dialogText.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.dialogText);
            }
            return size;
        }

        public UserNotificationData mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.notificationTitle = input.readString();
                        this.hasNotificationTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.notificationText = input.readString();
                        this.hasNotificationText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.tickerText = input.readString();
                        this.hasTickerText = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.dialogTitle = input.readString();
                        this.hasDialogTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.dialogText = input.readString();
                        this.hasDialogText = true;
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
