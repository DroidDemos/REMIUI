package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public final class DeviceConfigurationProto extends MessageNano {
    public int glEsVersion;
    public String[] glExtension;
    public boolean hasFiveWayNavigation;
    public boolean hasGlEsVersion;
    public boolean hasHardKeyboard;
    public boolean hasHasFiveWayNavigation;
    public boolean hasHasHardKeyboard;
    public boolean hasKeyboard;
    public boolean hasMaxApkDownloadSizeMb;
    public boolean hasNavigation;
    public boolean hasScreenDensity;
    public boolean hasScreenHeight;
    public boolean hasScreenLayout;
    public boolean hasScreenWidth;
    public boolean hasSmallestScreenWidthDp;
    public boolean hasTouchScreen;
    public int keyboard;
    public int maxApkDownloadSizeMb;
    public String[] nativePlatform;
    public int navigation;
    public int screenDensity;
    public int screenHeight;
    public int screenLayout;
    public int screenWidth;
    public int smallestScreenWidthDp;
    public String[] systemAvailableFeature;
    public String[] systemSharedLibrary;
    public String[] systemSupportedLocale;
    public int touchScreen;

    public DeviceConfigurationProto() {
        clear();
    }

    public DeviceConfigurationProto clear() {
        this.touchScreen = 0;
        this.hasTouchScreen = false;
        this.keyboard = 0;
        this.hasKeyboard = false;
        this.navigation = 0;
        this.hasNavigation = false;
        this.screenLayout = 0;
        this.hasScreenLayout = false;
        this.hasHardKeyboard = false;
        this.hasHasHardKeyboard = false;
        this.hasFiveWayNavigation = false;
        this.hasHasFiveWayNavigation = false;
        this.screenDensity = 0;
        this.hasScreenDensity = false;
        this.screenWidth = 0;
        this.hasScreenWidth = false;
        this.screenHeight = 0;
        this.hasScreenHeight = false;
        this.glEsVersion = 0;
        this.hasGlEsVersion = false;
        this.systemSharedLibrary = WireFormatNano.EMPTY_STRING_ARRAY;
        this.systemAvailableFeature = WireFormatNano.EMPTY_STRING_ARRAY;
        this.nativePlatform = WireFormatNano.EMPTY_STRING_ARRAY;
        this.systemSupportedLocale = WireFormatNano.EMPTY_STRING_ARRAY;
        this.glExtension = WireFormatNano.EMPTY_STRING_ARRAY;
        this.maxApkDownloadSizeMb = 50;
        this.hasMaxApkDownloadSizeMb = false;
        this.smallestScreenWidthDp = 0;
        this.hasSmallestScreenWidthDp = false;
        this.cachedSize = -1;
        return this;
    }

    public void writeTo(CodedOutputByteBufferNano output) throws IOException {
        if (this.touchScreen != 0 || this.hasTouchScreen) {
            output.writeInt32(1, this.touchScreen);
        }
        if (this.keyboard != 0 || this.hasKeyboard) {
            output.writeInt32(2, this.keyboard);
        }
        if (this.navigation != 0 || this.hasNavigation) {
            output.writeInt32(3, this.navigation);
        }
        if (this.screenLayout != 0 || this.hasScreenLayout) {
            output.writeInt32(4, this.screenLayout);
        }
        if (this.hasHasHardKeyboard || this.hasHardKeyboard) {
            output.writeBool(5, this.hasHardKeyboard);
        }
        if (this.hasHasFiveWayNavigation || this.hasFiveWayNavigation) {
            output.writeBool(6, this.hasFiveWayNavigation);
        }
        if (this.hasScreenDensity || this.screenDensity != 0) {
            output.writeInt32(7, this.screenDensity);
        }
        if (this.hasGlEsVersion || this.glEsVersion != 0) {
            output.writeInt32(8, this.glEsVersion);
        }
        if (this.systemSharedLibrary != null && this.systemSharedLibrary.length > 0) {
            for (String element : this.systemSharedLibrary) {
                if (element != null) {
                    output.writeString(9, element);
                }
            }
        }
        if (this.systemAvailableFeature != null && this.systemAvailableFeature.length > 0) {
            for (String element2 : this.systemAvailableFeature) {
                if (element2 != null) {
                    output.writeString(10, element2);
                }
            }
        }
        if (this.nativePlatform != null && this.nativePlatform.length > 0) {
            for (String element22 : this.nativePlatform) {
                if (element22 != null) {
                    output.writeString(11, element22);
                }
            }
        }
        if (this.hasScreenWidth || this.screenWidth != 0) {
            output.writeInt32(12, this.screenWidth);
        }
        if (this.hasScreenHeight || this.screenHeight != 0) {
            output.writeInt32(13, this.screenHeight);
        }
        if (this.systemSupportedLocale != null && this.systemSupportedLocale.length > 0) {
            for (String element222 : this.systemSupportedLocale) {
                if (element222 != null) {
                    output.writeString(14, element222);
                }
            }
        }
        if (this.glExtension != null && this.glExtension.length > 0) {
            for (String element2222 : this.glExtension) {
                if (element2222 != null) {
                    output.writeString(15, element2222);
                }
            }
        }
        if (this.hasMaxApkDownloadSizeMb || this.maxApkDownloadSizeMb != 50) {
            output.writeInt32(17, this.maxApkDownloadSizeMb);
        }
        if (this.hasSmallestScreenWidthDp || this.smallestScreenWidthDp != 0) {
            output.writeInt32(18, this.smallestScreenWidthDp);
        }
        super.writeTo(output);
    }

    protected int computeSerializedSize() {
        int dataCount;
        int dataSize;
        int size = super.computeSerializedSize();
        if (this.touchScreen != 0 || this.hasTouchScreen) {
            size += CodedOutputByteBufferNano.computeInt32Size(1, this.touchScreen);
        }
        if (this.keyboard != 0 || this.hasKeyboard) {
            size += CodedOutputByteBufferNano.computeInt32Size(2, this.keyboard);
        }
        if (this.navigation != 0 || this.hasNavigation) {
            size += CodedOutputByteBufferNano.computeInt32Size(3, this.navigation);
        }
        if (this.screenLayout != 0 || this.hasScreenLayout) {
            size += CodedOutputByteBufferNano.computeInt32Size(4, this.screenLayout);
        }
        if (this.hasHasHardKeyboard || this.hasHardKeyboard) {
            size += CodedOutputByteBufferNano.computeBoolSize(5, this.hasHardKeyboard);
        }
        if (this.hasHasFiveWayNavigation || this.hasFiveWayNavigation) {
            size += CodedOutputByteBufferNano.computeBoolSize(6, this.hasFiveWayNavigation);
        }
        if (this.hasScreenDensity || this.screenDensity != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(7, this.screenDensity);
        }
        if (this.hasGlEsVersion || this.glEsVersion != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(8, this.glEsVersion);
        }
        if (this.systemSharedLibrary != null && this.systemSharedLibrary.length > 0) {
            dataCount = 0;
            dataSize = 0;
            for (String element : this.systemSharedLibrary) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            size = (size + dataSize) + (dataCount * 1);
        }
        if (this.systemAvailableFeature != null && this.systemAvailableFeature.length > 0) {
            dataCount = 0;
            dataSize = 0;
            for (String element2 : this.systemAvailableFeature) {
                if (element2 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                }
            }
            size = (size + dataSize) + (dataCount * 1);
        }
        if (this.nativePlatform != null && this.nativePlatform.length > 0) {
            dataCount = 0;
            dataSize = 0;
            for (String element22 : this.nativePlatform) {
                if (element22 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element22);
                }
            }
            size = (size + dataSize) + (dataCount * 1);
        }
        if (this.hasScreenWidth || this.screenWidth != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(12, this.screenWidth);
        }
        if (this.hasScreenHeight || this.screenHeight != 0) {
            size += CodedOutputByteBufferNano.computeInt32Size(13, this.screenHeight);
        }
        if (this.systemSupportedLocale != null && this.systemSupportedLocale.length > 0) {
            dataCount = 0;
            dataSize = 0;
            for (String element222 : this.systemSupportedLocale) {
                if (element222 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element222);
                }
            }
            size = (size + dataSize) + (dataCount * 1);
        }
        if (this.glExtension != null && this.glExtension.length > 0) {
            dataCount = 0;
            dataSize = 0;
            for (String element2222 : this.glExtension) {
                if (element2222 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2222);
                }
            }
            size = (size + dataSize) + (dataCount * 1);
        }
        if (this.hasMaxApkDownloadSizeMb || this.maxApkDownloadSizeMb != 50) {
            size += CodedOutputByteBufferNano.computeInt32Size(17, this.maxApkDownloadSizeMb);
        }
        if (this.hasSmallestScreenWidthDp || this.smallestScreenWidthDp != 0) {
            return size + CodedOutputByteBufferNano.computeInt32Size(18, this.smallestScreenWidthDp);
        }
        return size;
    }

    public DeviceConfigurationProto mergeFrom(CodedInputByteBufferNano input) throws IOException {
        while (true) {
            int tag = input.readTag();
            int value;
            int arrayLength;
            int i;
            String[] newArray;
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
                            this.touchScreen = value;
                            this.hasTouchScreen = true;
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
                            this.keyboard = value;
                            this.hasKeyboard = true;
                            break;
                        default:
                            continue;
                    }
                case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                    value = input.readInt32();
                    switch (value) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        case R.styleable.WalletImFormEditText_required /*4*/:
                            this.navigation = value;
                            this.hasNavigation = true;
                            break;
                        default:
                            continue;
                    }
                case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                    value = input.readInt32();
                    switch (value) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        case R.styleable.WalletImFormEditText_required /*4*/:
                            this.screenLayout = value;
                            this.hasScreenLayout = true;
                            break;
                        default:
                            continue;
                    }
                case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                    this.hasHardKeyboard = input.readBool();
                    this.hasHasHardKeyboard = true;
                    continue;
                case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                    this.hasFiveWayNavigation = input.readBool();
                    this.hasHasFiveWayNavigation = true;
                    continue;
                case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                    this.screenDensity = input.readInt32();
                    this.hasScreenDensity = true;
                    continue;
                case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                    this.glEsVersion = input.readInt32();
                    this.hasGlEsVersion = true;
                    continue;
                case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 74);
                    i = this.systemSharedLibrary == null ? 0 : this.systemSharedLibrary.length;
                    newArray = new String[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.systemSharedLibrary, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = input.readString();
                        input.readTag();
                        i++;
                    }
                    newArray[i] = input.readString();
                    this.systemSharedLibrary = newArray;
                    continue;
                case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 82);
                    i = this.systemAvailableFeature == null ? 0 : this.systemAvailableFeature.length;
                    newArray = new String[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.systemAvailableFeature, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = input.readString();
                        input.readTag();
                        i++;
                    }
                    newArray[i] = input.readString();
                    this.systemAvailableFeature = newArray;
                    continue;
                case 90:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                    i = this.nativePlatform == null ? 0 : this.nativePlatform.length;
                    newArray = new String[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.nativePlatform, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = input.readString();
                        input.readTag();
                        i++;
                    }
                    newArray[i] = input.readString();
                    this.nativePlatform = newArray;
                    continue;
                case 96:
                    this.screenWidth = input.readInt32();
                    this.hasScreenWidth = true;
                    continue;
                case 104:
                    this.screenHeight = input.readInt32();
                    this.hasScreenHeight = true;
                    continue;
                case 114:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 114);
                    i = this.systemSupportedLocale == null ? 0 : this.systemSupportedLocale.length;
                    newArray = new String[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.systemSupportedLocale, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = input.readString();
                        input.readTag();
                        i++;
                    }
                    newArray[i] = input.readString();
                    this.systemSupportedLocale = newArray;
                    continue;
                case 122:
                    arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 122);
                    i = this.glExtension == null ? 0 : this.glExtension.length;
                    newArray = new String[(i + arrayLength)];
                    if (i != 0) {
                        System.arraycopy(this.glExtension, 0, newArray, 0, i);
                    }
                    while (i < newArray.length - 1) {
                        newArray[i] = input.readString();
                        input.readTag();
                        i++;
                    }
                    newArray[i] = input.readString();
                    this.glExtension = newArray;
                    continue;
                case 136:
                    this.maxApkDownloadSizeMb = input.readInt32();
                    this.hasMaxApkDownloadSizeMb = true;
                    continue;
                case 144:
                    this.smallestScreenWidthDp = input.readInt32();
                    this.hasSmallestScreenWidthDp = true;
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
