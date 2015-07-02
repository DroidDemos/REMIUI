package com.google.android.play.analytics;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface ClientAnalytics {

    public static final class ActiveExperiments extends MessageNano {
        public String[] clientAlteringExperiment;
        public int[] gwsExperiment;
        public String[] otherExperiment;

        public ActiveExperiments() {
            clear();
        }

        public ActiveExperiments clear() {
            this.clientAlteringExperiment = WireFormatNano.EMPTY_STRING_ARRAY;
            this.otherExperiment = WireFormatNano.EMPTY_STRING_ARRAY;
            this.gwsExperiment = WireFormatNano.EMPTY_INT_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clientAlteringExperiment != null && this.clientAlteringExperiment.length > 0) {
                for (String element : this.clientAlteringExperiment) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.otherExperiment != null && this.otherExperiment.length > 0) {
                for (String element2 : this.otherExperiment) {
                    if (element2 != null) {
                        output.writeString(2, element2);
                    }
                }
            }
            if (this.gwsExperiment != null && this.gwsExperiment.length > 0) {
                for (int writeInt32 : this.gwsExperiment) {
                    output.writeInt32(3, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.clientAlteringExperiment != null && this.clientAlteringExperiment.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.clientAlteringExperiment) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.otherExperiment != null && this.otherExperiment.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.otherExperiment) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.gwsExperiment == null || this.gwsExperiment.length <= 0) {
                return size;
            }
            dataSize = 0;
            for (int element3 : this.gwsExperiment) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
            }
            return (size + dataSize) + (this.gwsExperiment.length * 1);
        }

        public ActiveExperiments mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                int[] newArray2;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        i = this.clientAlteringExperiment == null ? 0 : this.clientAlteringExperiment.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientAlteringExperiment, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.clientAlteringExperiment = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        i = this.otherExperiment == null ? 0 : this.otherExperiment.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.otherExperiment, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.otherExperiment = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 24);
                        i = this.gwsExperiment == null ? 0 : this.gwsExperiment.length;
                        newArray2 = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gwsExperiment, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readInt32();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readInt32();
                        this.gwsExperiment = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt32();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.gwsExperiment == null ? 0 : this.gwsExperiment.length;
                        newArray2 = new int[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.gwsExperiment, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length) {
                            newArray2[i] = input.readInt32();
                            i++;
                        }
                        this.gwsExperiment = newArray2;
                        input.popLimit(limit);
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

    public static final class AndroidClientInfo extends MessageNano {
        public long androidId;
        public String applicationBuild;
        public String board;
        public String brand;
        public String country;
        public String device;
        public long deviceId;
        public String fingerprint;
        public int gmsCoreVersionCode;
        public String hardware;
        public String locale;
        public String loggingId;
        public String manufacturer;
        public String mccMnc;
        public String model;
        public String osBuild;
        public String product;
        public String radioVersion;
        public int sdkVersion;

        public AndroidClientInfo() {
            clear();
        }

        public AndroidClientInfo clear() {
            this.androidId = 0;
            this.loggingId = "";
            this.deviceId = 0;
            this.sdkVersion = 0;
            this.model = "";
            this.product = "";
            this.hardware = "";
            this.device = "";
            this.osBuild = "";
            this.applicationBuild = "";
            this.mccMnc = "";
            this.locale = "";
            this.country = "";
            this.manufacturer = "";
            this.brand = "";
            this.board = "";
            this.radioVersion = "";
            this.fingerprint = "";
            this.gmsCoreVersionCode = 0;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.androidId != 0) {
                output.writeInt64(1, this.androidId);
            }
            if (!this.loggingId.equals("")) {
                output.writeString(2, this.loggingId);
            }
            if (this.sdkVersion != 0) {
                output.writeInt32(3, this.sdkVersion);
            }
            if (!this.model.equals("")) {
                output.writeString(4, this.model);
            }
            if (!this.product.equals("")) {
                output.writeString(5, this.product);
            }
            if (!this.osBuild.equals("")) {
                output.writeString(6, this.osBuild);
            }
            if (!this.applicationBuild.equals("")) {
                output.writeString(7, this.applicationBuild);
            }
            if (!this.hardware.equals("")) {
                output.writeString(8, this.hardware);
            }
            if (!this.device.equals("")) {
                output.writeString(9, this.device);
            }
            if (!this.mccMnc.equals("")) {
                output.writeString(10, this.mccMnc);
            }
            if (!this.locale.equals("")) {
                output.writeString(11, this.locale);
            }
            if (!this.country.equals("")) {
                output.writeString(12, this.country);
            }
            if (!this.manufacturer.equals("")) {
                output.writeString(13, this.manufacturer);
            }
            if (!this.brand.equals("")) {
                output.writeString(14, this.brand);
            }
            if (!this.board.equals("")) {
                output.writeString(15, this.board);
            }
            if (!this.radioVersion.equals("")) {
                output.writeString(16, this.radioVersion);
            }
            if (!this.fingerprint.equals("")) {
                output.writeString(17, this.fingerprint);
            }
            if (this.deviceId != 0) {
                output.writeInt64(18, this.deviceId);
            }
            if (this.gmsCoreVersionCode != 0) {
                output.writeInt32(19, this.gmsCoreVersionCode);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.androidId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.androidId);
            }
            if (!this.loggingId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.loggingId);
            }
            if (this.sdkVersion != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.sdkVersion);
            }
            if (!this.model.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.model);
            }
            if (!this.product.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.product);
            }
            if (!this.osBuild.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.osBuild);
            }
            if (!this.applicationBuild.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.applicationBuild);
            }
            if (!this.hardware.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(8, this.hardware);
            }
            if (!this.device.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.device);
            }
            if (!this.mccMnc.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(10, this.mccMnc);
            }
            if (!this.locale.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.locale);
            }
            if (!this.country.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.country);
            }
            if (!this.manufacturer.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.manufacturer);
            }
            if (!this.brand.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.brand);
            }
            if (!this.board.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.board);
            }
            if (!this.radioVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(16, this.radioVersion);
            }
            if (!this.fingerprint.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(17, this.fingerprint);
            }
            if (this.deviceId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(18, this.deviceId);
            }
            if (this.gmsCoreVersionCode != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(19, this.gmsCoreVersionCode);
            }
            return size;
        }

        public AndroidClientInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.androidId = input.readInt64();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.loggingId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.sdkVersion = input.readInt32();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.model = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.product = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.osBuild = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.applicationBuild = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.hardware = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.device = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        this.mccMnc = input.readString();
                        continue;
                    case 90:
                        this.locale = input.readString();
                        continue;
                    case 98:
                        this.country = input.readString();
                        continue;
                    case 106:
                        this.manufacturer = input.readString();
                        continue;
                    case 114:
                        this.brand = input.readString();
                        continue;
                    case 122:
                        this.board = input.readString();
                        continue;
                    case 130:
                        this.radioVersion = input.readString();
                        continue;
                    case 138:
                        this.fingerprint = input.readString();
                        continue;
                    case 144:
                        this.deviceId = input.readInt64();
                        continue;
                    case 152:
                        this.gmsCoreVersionCode = input.readInt32();
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

    public static final class AppUsage1pLogEvent extends MessageNano {
        public String androidPackageName;
        public int appType;
        public String version;

        public AppUsage1pLogEvent() {
            clear();
        }

        public AppUsage1pLogEvent clear() {
            this.appType = 0;
            this.androidPackageName = "";
            this.version = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.appType != 0) {
                output.writeInt32(1, this.appType);
            }
            if (!this.androidPackageName.equals("")) {
                output.writeString(2, this.androidPackageName);
            }
            if (!this.version.equals("")) {
                output.writeString(3, this.version);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.appType);
            }
            if (!this.androidPackageName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.androidPackageName);
            }
            if (this.version.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(3, this.version);
        }

        public AppUsage1pLogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                                this.appType = value;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.androidPackageName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.version = input.readString();
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

    public static final class ClientInfo extends MessageNano {
        public AndroidClientInfo androidClientInfo;
        public int clientType;
        public DesktopClientInfo desktopClientInfo;
        public IosClientInfo iosClientInfo;
        public PlayCeClientInfo playCeClientInfo;

        public ClientInfo() {
            clear();
        }

        public ClientInfo clear() {
            this.clientType = 0;
            this.androidClientInfo = null;
            this.desktopClientInfo = null;
            this.iosClientInfo = null;
            this.playCeClientInfo = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clientType != 0) {
                output.writeInt32(1, this.clientType);
            }
            if (this.androidClientInfo != null) {
                output.writeMessage(2, this.androidClientInfo);
            }
            if (this.desktopClientInfo != null) {
                output.writeMessage(3, this.desktopClientInfo);
            }
            if (this.iosClientInfo != null) {
                output.writeMessage(4, this.iosClientInfo);
            }
            if (this.playCeClientInfo != null) {
                output.writeMessage(5, this.playCeClientInfo);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.clientType != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.clientType);
            }
            if (this.androidClientInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.androidClientInfo);
            }
            if (this.desktopClientInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.desktopClientInfo);
            }
            if (this.iosClientInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.iosClientInfo);
            }
            if (this.playCeClientInfo != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(5, this.playCeClientInfo);
            }
            return size;
        }

        public ClientInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.clientType = value;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.androidClientInfo == null) {
                            this.androidClientInfo = new AndroidClientInfo();
                        }
                        input.readMessage(this.androidClientInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.desktopClientInfo == null) {
                            this.desktopClientInfo = new DesktopClientInfo();
                        }
                        input.readMessage(this.desktopClientInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.iosClientInfo == null) {
                            this.iosClientInfo = new IosClientInfo();
                        }
                        input.readMessage(this.iosClientInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.playCeClientInfo == null) {
                            this.playCeClientInfo = new PlayCeClientInfo();
                        }
                        input.readMessage(this.playCeClientInfo);
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

    public static final class DesktopClientInfo extends MessageNano {
        public String applicationBuild;
        public String clientId;
        public String loggingId;
        public String os;
        public String osFullVersion;
        public String osMajorVersion;

        public DesktopClientInfo() {
            clear();
        }

        public DesktopClientInfo clear() {
            this.clientId = "";
            this.loggingId = "";
            this.os = "";
            this.osMajorVersion = "";
            this.osFullVersion = "";
            this.applicationBuild = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.clientId.equals("")) {
                output.writeString(1, this.clientId);
            }
            if (!this.loggingId.equals("")) {
                output.writeString(2, this.loggingId);
            }
            if (!this.os.equals("")) {
                output.writeString(3, this.os);
            }
            if (!this.osMajorVersion.equals("")) {
                output.writeString(4, this.osMajorVersion);
            }
            if (!this.osFullVersion.equals("")) {
                output.writeString(5, this.osFullVersion);
            }
            if (!this.applicationBuild.equals("")) {
                output.writeString(6, this.applicationBuild);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.clientId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.clientId);
            }
            if (!this.loggingId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.loggingId);
            }
            if (!this.os.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.os);
            }
            if (!this.osMajorVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.osMajorVersion);
            }
            if (!this.osFullVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.osFullVersion);
            }
            if (this.applicationBuild.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(6, this.applicationBuild);
        }

        public DesktopClientInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.clientId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.loggingId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.os = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.osMajorVersion = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.osFullVersion = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.applicationBuild = input.readString();
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

    public static final class ExperimentIdList extends MessageNano {
        public String[] id;

        public ExperimentIdList() {
            clear();
        }

        public ExperimentIdList clear() {
            this.id = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.id != null && this.id.length > 0) {
                for (String element : this.id) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.id == null || this.id.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element : this.id) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public ExperimentIdList mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        int i = this.id == null ? 0 : this.id.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.id, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.id = newArray;
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

    public static final class IosClientInfo extends MessageNano {
        public String applicationBuild;
        public String clientId;
        public String loggingId;
        public String osFullVersion;
        public String osMajorVersion;

        public IosClientInfo() {
            clear();
        }

        public IosClientInfo clear() {
            this.clientId = "";
            this.loggingId = "";
            this.osMajorVersion = "";
            this.osFullVersion = "";
            this.applicationBuild = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.clientId.equals("")) {
                output.writeString(1, this.clientId);
            }
            if (!this.loggingId.equals("")) {
                output.writeString(2, this.loggingId);
            }
            if (!this.osMajorVersion.equals("")) {
                output.writeString(3, this.osMajorVersion);
            }
            if (!this.osFullVersion.equals("")) {
                output.writeString(4, this.osFullVersion);
            }
            if (!this.applicationBuild.equals("")) {
                output.writeString(5, this.applicationBuild);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.clientId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.clientId);
            }
            if (!this.loggingId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.loggingId);
            }
            if (!this.osMajorVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.osMajorVersion);
            }
            if (!this.osFullVersion.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.osFullVersion);
            }
            if (this.applicationBuild.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(5, this.applicationBuild);
        }

        public IosClientInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.clientId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.loggingId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.osMajorVersion = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.osFullVersion = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.applicationBuild = input.readString();
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

    public static final class LogEvent extends MessageNano {
        private static volatile LogEvent[] _emptyArray;
        public AppUsage1pLogEvent appUsage1P;
        public int eventCode;
        public int eventFlowId;
        public long eventTimeMs;
        public ActiveExperiments exp;
        public boolean isUserInitiated;
        public byte[] sourceExtension;
        public byte[] sourceExtensionJs;
        public byte[] sourceExtensionJson;
        public String tag;
        public String testId;
        public long timezoneOffsetSeconds;
        public LogEventKeyValues[] value;

        public static LogEvent[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogEvent[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LogEvent() {
            clear();
        }

        public LogEvent clear() {
            this.eventTimeMs = 0;
            this.tag = "";
            this.eventCode = 0;
            this.eventFlowId = 0;
            this.isUserInitiated = false;
            this.value = LogEventKeyValues.emptyArray();
            this.appUsage1P = null;
            this.sourceExtension = WireFormatNano.EMPTY_BYTES;
            this.sourceExtensionJs = WireFormatNano.EMPTY_BYTES;
            this.sourceExtensionJson = WireFormatNano.EMPTY_BYTES;
            this.exp = null;
            this.testId = "";
            this.timezoneOffsetSeconds = 180000;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.eventTimeMs != 0) {
                output.writeInt64(1, this.eventTimeMs);
            }
            if (!this.tag.equals("")) {
                output.writeString(2, this.tag);
            }
            if (this.value != null && this.value.length > 0) {
                for (LogEventKeyValues element : this.value) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (!Arrays.equals(this.sourceExtension, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(6, this.sourceExtension);
            }
            if (this.exp != null) {
                output.writeMessage(7, this.exp);
            }
            if (!Arrays.equals(this.sourceExtensionJs, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(8, this.sourceExtensionJs);
            }
            if (this.appUsage1P != null) {
                output.writeMessage(9, this.appUsage1P);
            }
            if (this.isUserInitiated) {
                output.writeBool(10, this.isUserInitiated);
            }
            if (this.eventCode != 0) {
                output.writeInt32(11, this.eventCode);
            }
            if (this.eventFlowId != 0) {
                output.writeInt32(12, this.eventFlowId);
            }
            if (!Arrays.equals(this.sourceExtensionJson, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(13, this.sourceExtensionJson);
            }
            if (!this.testId.equals("")) {
                output.writeString(14, this.testId);
            }
            if (this.timezoneOffsetSeconds != 180000) {
                output.writeSInt64(15, this.timezoneOffsetSeconds);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.eventTimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.eventTimeMs);
            }
            if (!this.tag.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.tag);
            }
            if (this.value != null && this.value.length > 0) {
                for (LogEventKeyValues element : this.value) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (!Arrays.equals(this.sourceExtension, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(6, this.sourceExtension);
            }
            if (this.exp != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.exp);
            }
            if (!Arrays.equals(this.sourceExtensionJs, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(8, this.sourceExtensionJs);
            }
            if (this.appUsage1P != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.appUsage1P);
            }
            if (this.isUserInitiated) {
                size += CodedOutputByteBufferNano.computeBoolSize(10, this.isUserInitiated);
            }
            if (this.eventCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(11, this.eventCode);
            }
            if (this.eventFlowId != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(12, this.eventFlowId);
            }
            if (!Arrays.equals(this.sourceExtensionJson, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(13, this.sourceExtensionJson);
            }
            if (!this.testId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.testId);
            }
            if (this.timezoneOffsetSeconds != 180000) {
                return size + CodedOutputByteBufferNano.computeSInt64Size(15, this.timezoneOffsetSeconds);
            }
            return size;
        }

        public LogEvent mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.eventTimeMs = input.readInt64();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.tag = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.value == null) {
                            i = 0;
                        } else {
                            i = this.value.length;
                        }
                        LogEventKeyValues[] newArray = new LogEventKeyValues[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.value, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LogEventKeyValues();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new LogEventKeyValues();
                        input.readMessage(newArray[i]);
                        this.value = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.sourceExtension = input.readBytes();
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.exp == null) {
                            this.exp = new ActiveExperiments();
                        }
                        input.readMessage(this.exp);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        this.sourceExtensionJs = input.readBytes();
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.appUsage1P == null) {
                            this.appUsage1P = new AppUsage1pLogEvent();
                        }
                        input.readMessage(this.appUsage1P);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        this.isUserInitiated = input.readBool();
                        continue;
                    case 88:
                        this.eventCode = input.readInt32();
                        continue;
                    case 96:
                        this.eventFlowId = input.readInt32();
                        continue;
                    case 106:
                        this.sourceExtensionJson = input.readBytes();
                        continue;
                    case 114:
                        this.testId = input.readString();
                        continue;
                    case 120:
                        this.timezoneOffsetSeconds = input.readSInt64();
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

    public static final class LogEventKeyValues extends MessageNano {
        private static volatile LogEventKeyValues[] _emptyArray;
        public String key;
        public String value;

        public static LogEventKeyValues[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new LogEventKeyValues[0];
                    }
                }
            }
            return _emptyArray;
        }

        public LogEventKeyValues() {
            clear();
        }

        public LogEventKeyValues clear() {
            this.key = "";
            this.value = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.key.equals("")) {
                output.writeString(1, this.key);
            }
            if (!this.value.equals("")) {
                output.writeString(2, this.value);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.key.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.key);
            }
            if (this.value.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(2, this.value);
        }

        public LogEventKeyValues mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.key = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.value = input.readString();
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

    public static final class LogRequest extends MessageNano {
        public ClientInfo clientInfo;
        public LogEvent[] logEvent;
        public int logSource;
        public String logSourceName;
        public long requestTimeMs;
        public byte[][] serializedLogEvents;
        public String zwiebackCookie;

        public LogRequest() {
            clear();
        }

        public LogRequest clear() {
            this.requestTimeMs = 0;
            this.clientInfo = null;
            this.logSource = -1;
            this.logSourceName = "";
            this.zwiebackCookie = "";
            this.logEvent = LogEvent.emptyArray();
            this.serializedLogEvents = WireFormatNano.EMPTY_BYTES_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.clientInfo != null) {
                output.writeMessage(1, this.clientInfo);
            }
            if (this.logSource != -1) {
                output.writeInt32(2, this.logSource);
            }
            if (this.logEvent != null && this.logEvent.length > 0) {
                for (LogEvent element : this.logEvent) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            if (this.requestTimeMs != 0) {
                output.writeInt64(4, this.requestTimeMs);
            }
            if (this.serializedLogEvents != null && this.serializedLogEvents.length > 0) {
                for (byte[] element2 : this.serializedLogEvents) {
                    if (element2 != null) {
                        output.writeBytes(5, element2);
                    }
                }
            }
            if (!this.logSourceName.equals("")) {
                output.writeString(6, this.logSourceName);
            }
            if (!this.zwiebackCookie.equals("")) {
                output.writeString(7, this.zwiebackCookie);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.clientInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.clientInfo);
            }
            if (this.logSource != -1) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.logSource);
            }
            if (this.logEvent != null && this.logEvent.length > 0) {
                for (LogEvent element : this.logEvent) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            if (this.requestTimeMs != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(4, this.requestTimeMs);
            }
            if (this.serializedLogEvents != null && this.serializedLogEvents.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (byte[] element2 : this.serializedLogEvents) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeBytesSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (!this.logSourceName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.logSourceName);
            }
            if (this.zwiebackCookie.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(7, this.zwiebackCookie);
        }

        public LogRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.clientInfo == null) {
                            this.clientInfo = new ClientInfo();
                        }
                        input.readMessage(this.clientInfo);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        int value = input.readInt32();
                        switch (value) {
                            case -1:
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
                            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            case com.google.android.play.R.styleable.Toolbar_theme /*17*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationIcon /*20*/:
                            case com.google.android.play.R.styleable.Toolbar_navigationContentDescription /*21*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextAppearance /*22*/:
                            case com.google.android.play.R.styleable.Theme_actionMenuTextColor /*23*/:
                            case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCloseButtonStyle /*25*/:
                            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            case com.google.android.play.R.styleable.Theme_actionModeSplitBackground /*27*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCloseDrawable /*28*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCutDrawable /*29*/:
                            case com.google.android.play.R.styleable.Theme_actionModeCopyDrawable /*30*/:
                            case com.google.android.play.R.styleable.Theme_actionModePasteDrawable /*31*/:
                            case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                            case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                            case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                            case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                            case com.google.android.play.R.styleable.Theme_actionModePopupWindowStyle /*36*/:
                            case com.google.android.play.R.styleable.Theme_textAppearanceLargePopupMenu /*37*/:
                            case com.google.android.play.R.styleable.Theme_textAppearanceSmallPopupMenu /*38*/:
                            case com.google.android.play.R.styleable.Theme_actionDropDownStyle /*39*/:
                            case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                            case com.google.android.play.R.styleable.Theme_spinnerStyle /*41*/:
                            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                            case com.google.android.play.R.styleable.Theme_homeAsUpIndicator /*43*/:
                            case com.google.android.play.R.styleable.Theme_actionButtonStyle /*44*/:
                            case com.google.android.play.R.styleable.Theme_buttonBarStyle /*45*/:
                            case com.google.android.play.R.styleable.Theme_buttonBarButtonStyle /*46*/:
                            case com.google.android.play.R.styleable.Theme_selectableItemBackground /*47*/:
                            case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                            case com.google.android.play.R.styleable.Theme_dividerVertical /*49*/:
                            case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                            case com.google.android.play.R.styleable.Theme_activityChooserViewStyle /*51*/:
                            case com.google.android.play.R.styleable.Theme_toolbarStyle /*52*/:
                                this.logSource = value;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.logEvent == null) {
                            i = 0;
                        } else {
                            i = this.logEvent.length;
                        }
                        LogEvent[] newArray = new LogEvent[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.logEvent, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LogEvent();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new LogEvent();
                        input.readMessage(newArray[i]);
                        this.logEvent = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.requestTimeMs = input.readInt64();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        i = this.serializedLogEvents == null ? 0 : this.serializedLogEvents.length;
                        byte[][] newArray2 = new byte[(i + arrayLength)][];
                        if (i != 0) {
                            System.arraycopy(this.serializedLogEvents, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readBytes();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readBytes();
                        this.serializedLogEvents = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.logSourceName = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.zwiebackCookie = input.readString();
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

    public static final class LogResponse extends MessageNano {
        public ExperimentIdList experiments;
        public long nextRequestWaitMillis;

        public LogResponse() {
            clear();
        }

        public LogResponse clear() {
            this.nextRequestWaitMillis = -1;
            this.experiments = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.nextRequestWaitMillis != -1) {
                output.writeInt64(1, this.nextRequestWaitMillis);
            }
            if (this.experiments != null) {
                output.writeMessage(2, this.experiments);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.nextRequestWaitMillis != -1) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.nextRequestWaitMillis);
            }
            if (this.experiments != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(2, this.experiments);
            }
            return size;
        }

        public LogResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.nextRequestWaitMillis = input.readInt64();
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.experiments == null) {
                            this.experiments = new ExperimentIdList();
                        }
                        input.readMessage(this.experiments);
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

        public static LogResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (LogResponse) MessageNano.mergeFrom(new LogResponse(), data);
        }
    }

    public static final class PlayCeClientInfo extends MessageNano {
        public String applicationBuild;
        public String clientId;
        public String make;
        public String model;
        public String platformVersion;

        public PlayCeClientInfo() {
            clear();
        }

        public PlayCeClientInfo clear() {
            this.clientId = "";
            this.make = "";
            this.model = "";
            this.applicationBuild = "";
            this.platformVersion = "";
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (!this.clientId.equals("")) {
                output.writeString(1, this.clientId);
            }
            if (!this.make.equals("")) {
                output.writeString(3, this.make);
            }
            if (!this.model.equals("")) {
                output.writeString(4, this.model);
            }
            if (!this.applicationBuild.equals("")) {
                output.writeString(5, this.applicationBuild);
            }
            if (!this.platformVersion.equals("")) {
                output.writeString(6, this.platformVersion);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (!this.clientId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.clientId);
            }
            if (!this.make.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.make);
            }
            if (!this.model.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.model);
            }
            if (!this.applicationBuild.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.applicationBuild);
            }
            if (this.platformVersion.equals("")) {
                return size;
            }
            return size + CodedOutputByteBufferNano.computeStringSize(6, this.platformVersion);
        }

        public PlayCeClientInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.clientId = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.make = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.model = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.applicationBuild = input.readString();
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.platformVersion = input.readString();
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
