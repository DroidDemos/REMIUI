package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocAnnotations.Badge;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface DocDetails {

    public static final class AlbumDetails extends MessageNano {
        public MusicDetails details;
        public ArtistDetails displayArtist;
        public boolean hasName;
        public String name;

        public AlbumDetails() {
            clear();
        }

        public AlbumDetails clear() {
            this.name = "";
            this.hasName = false;
            this.details = null;
            this.displayArtist = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasName || !this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (this.details != null) {
                output.writeMessage(2, this.details);
            }
            if (this.displayArtist != null) {
                output.writeMessage(3, this.displayArtist);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.details != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.details);
            }
            if (this.displayArtist != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.displayArtist);
            }
            return size;
        }

        public AlbumDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        if (this.details == null) {
                            this.details = new MusicDetails();
                        }
                        input.readMessage(this.details);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.displayArtist == null) {
                            this.displayArtist = new ArtistDetails();
                        }
                        input.readMessage(this.displayArtist);
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

    public static final class AppDetails extends MessageNano {
        public String[] appCategory;
        public String appType;
        public String[] autoAcquireFreeAppIfHigherVersionAvailableTag;
        public String[] certificateHash;
        public CertificateSet[] certificateSet;
        public int contentRating;
        public boolean declaresIab;
        public String developerEmail;
        public String developerName;
        public String developerWebsite;
        public boolean externallyHosted;
        public FileMetadata[] file;
        public boolean gamepadRequired;
        public boolean hasAppType;
        public boolean hasContentRating;
        public boolean hasDeclaresIab;
        public boolean hasDeveloperEmail;
        public boolean hasDeveloperName;
        public boolean hasDeveloperWebsite;
        public boolean hasExternallyHosted;
        public boolean hasGamepadRequired;
        public boolean hasInstallationSize;
        public boolean hasMajorVersionNumber;
        public boolean hasNumDownloads;
        public boolean hasPackageName;
        public boolean hasRecentChangesHtml;
        public boolean hasTitle;
        public boolean hasUploadDate;
        public boolean hasVariesByAccount;
        public boolean hasVersionCode;
        public boolean hasVersionString;
        public long installationSize;
        public int majorVersionNumber;
        public String numDownloads;
        public String packageName;
        public String[] permission;
        public String recentChangesHtml;
        public String[] splitId;
        public String title;
        public String uploadDate;
        public boolean variesByAccount;
        public int versionCode;
        public String versionString;

        public AppDetails() {
            clear();
        }

        public AppDetails clear() {
            this.developerName = "";
            this.hasDeveloperName = false;
            this.majorVersionNumber = 0;
            this.hasMajorVersionNumber = false;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.versionString = "";
            this.hasVersionString = false;
            this.title = "";
            this.hasTitle = false;
            this.appCategory = WireFormatNano.EMPTY_STRING_ARRAY;
            this.contentRating = 0;
            this.hasContentRating = false;
            this.installationSize = 0;
            this.hasInstallationSize = false;
            this.permission = WireFormatNano.EMPTY_STRING_ARRAY;
            this.developerEmail = "";
            this.hasDeveloperEmail = false;
            this.developerWebsite = "";
            this.hasDeveloperWebsite = false;
            this.numDownloads = "";
            this.hasNumDownloads = false;
            this.packageName = "";
            this.hasPackageName = false;
            this.recentChangesHtml = "";
            this.hasRecentChangesHtml = false;
            this.uploadDate = "";
            this.hasUploadDate = false;
            this.file = FileMetadata.emptyArray();
            this.appType = "";
            this.hasAppType = false;
            this.certificateSet = CertificateSet.emptyArray();
            this.certificateHash = WireFormatNano.EMPTY_STRING_ARRAY;
            this.variesByAccount = true;
            this.hasVariesByAccount = false;
            this.autoAcquireFreeAppIfHigherVersionAvailableTag = WireFormatNano.EMPTY_STRING_ARRAY;
            this.declaresIab = false;
            this.hasDeclaresIab = false;
            this.splitId = WireFormatNano.EMPTY_STRING_ARRAY;
            this.gamepadRequired = false;
            this.hasGamepadRequired = false;
            this.externallyHosted = false;
            this.hasExternallyHosted = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDeveloperName || !this.developerName.equals("")) {
                output.writeString(1, this.developerName);
            }
            if (this.hasMajorVersionNumber || this.majorVersionNumber != 0) {
                output.writeInt32(2, this.majorVersionNumber);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                output.writeInt32(3, this.versionCode);
            }
            if (this.hasVersionString || !this.versionString.equals("")) {
                output.writeString(4, this.versionString);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(5, this.title);
            }
            if (this.appCategory != null && this.appCategory.length > 0) {
                for (String element : this.appCategory) {
                    if (element != null) {
                        output.writeString(7, element);
                    }
                }
            }
            if (this.hasContentRating || this.contentRating != 0) {
                output.writeInt32(8, this.contentRating);
            }
            if (this.hasInstallationSize || this.installationSize != 0) {
                output.writeInt64(9, this.installationSize);
            }
            if (this.permission != null && this.permission.length > 0) {
                for (String element2 : this.permission) {
                    if (element2 != null) {
                        output.writeString(10, element2);
                    }
                }
            }
            if (this.hasDeveloperEmail || !this.developerEmail.equals("")) {
                output.writeString(11, this.developerEmail);
            }
            if (this.hasDeveloperWebsite || !this.developerWebsite.equals("")) {
                output.writeString(12, this.developerWebsite);
            }
            if (this.hasNumDownloads || !this.numDownloads.equals("")) {
                output.writeString(13, this.numDownloads);
            }
            if (this.hasPackageName || !this.packageName.equals("")) {
                output.writeString(14, this.packageName);
            }
            if (this.hasRecentChangesHtml || !this.recentChangesHtml.equals("")) {
                output.writeString(15, this.recentChangesHtml);
            }
            if (this.hasUploadDate || !this.uploadDate.equals("")) {
                output.writeString(16, this.uploadDate);
            }
            if (this.file != null && this.file.length > 0) {
                for (FileMetadata element3 : this.file) {
                    if (element3 != null) {
                        output.writeMessage(17, element3);
                    }
                }
            }
            if (this.hasAppType || !this.appType.equals("")) {
                output.writeString(18, this.appType);
            }
            if (this.certificateHash != null && this.certificateHash.length > 0) {
                for (String element22 : this.certificateHash) {
                    if (element22 != null) {
                        output.writeString(19, element22);
                    }
                }
            }
            if (this.hasVariesByAccount || !this.variesByAccount) {
                output.writeBool(21, this.variesByAccount);
            }
            if (this.certificateSet != null && this.certificateSet.length > 0) {
                for (CertificateSet element4 : this.certificateSet) {
                    if (element4 != null) {
                        output.writeMessage(22, element4);
                    }
                }
            }
            if (this.autoAcquireFreeAppIfHigherVersionAvailableTag != null && this.autoAcquireFreeAppIfHigherVersionAvailableTag.length > 0) {
                for (String element222 : this.autoAcquireFreeAppIfHigherVersionAvailableTag) {
                    if (element222 != null) {
                        output.writeString(23, element222);
                    }
                }
            }
            if (this.hasDeclaresIab || this.declaresIab) {
                output.writeBool(24, this.declaresIab);
            }
            if (this.splitId != null && this.splitId.length > 0) {
                for (String element2222 : this.splitId) {
                    if (element2222 != null) {
                        output.writeString(25, element2222);
                    }
                }
            }
            if (this.hasGamepadRequired || this.gamepadRequired) {
                output.writeBool(26, this.gamepadRequired);
            }
            if (this.hasExternallyHosted || this.externallyHosted) {
                output.writeBool(27, this.externallyHosted);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.hasDeveloperName || !this.developerName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.developerName);
            }
            if (this.hasMajorVersionNumber || this.majorVersionNumber != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.majorVersionNumber);
            }
            if (this.hasVersionCode || this.versionCode != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.versionCode);
            }
            if (this.hasVersionString || !this.versionString.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.versionString);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.title);
            }
            if (this.appCategory != null && this.appCategory.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.appCategory) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasContentRating || this.contentRating != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(8, this.contentRating);
            }
            if (this.hasInstallationSize || this.installationSize != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(9, this.installationSize);
            }
            if (this.permission != null && this.permission.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.permission) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasDeveloperEmail || !this.developerEmail.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.developerEmail);
            }
            if (this.hasDeveloperWebsite || !this.developerWebsite.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(12, this.developerWebsite);
            }
            if (this.hasNumDownloads || !this.numDownloads.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(13, this.numDownloads);
            }
            if (this.hasPackageName || !this.packageName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(14, this.packageName);
            }
            if (this.hasRecentChangesHtml || !this.recentChangesHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(15, this.recentChangesHtml);
            }
            if (this.hasUploadDate || !this.uploadDate.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(16, this.uploadDate);
            }
            if (this.file != null && this.file.length > 0) {
                for (FileMetadata element3 : this.file) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(17, element3);
                    }
                }
            }
            if (this.hasAppType || !this.appType.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(18, this.appType);
            }
            if (this.certificateHash != null && this.certificateHash.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element22 : this.certificateHash) {
                    if (element22 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element22);
                    }
                }
                size = (size + dataSize) + (dataCount * 2);
            }
            if (this.hasVariesByAccount || !this.variesByAccount) {
                size += CodedOutputByteBufferNano.computeBoolSize(21, this.variesByAccount);
            }
            if (this.certificateSet != null && this.certificateSet.length > 0) {
                for (CertificateSet element4 : this.certificateSet) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(22, element4);
                    }
                }
            }
            if (this.autoAcquireFreeAppIfHigherVersionAvailableTag != null && this.autoAcquireFreeAppIfHigherVersionAvailableTag.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element222 : this.autoAcquireFreeAppIfHigherVersionAvailableTag) {
                    if (element222 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element222);
                    }
                }
                size = (size + dataSize) + (dataCount * 2);
            }
            if (this.hasDeclaresIab || this.declaresIab) {
                size += CodedOutputByteBufferNano.computeBoolSize(24, this.declaresIab);
            }
            if (this.splitId != null && this.splitId.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2222 : this.splitId) {
                    if (element2222 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2222);
                    }
                }
                size = (size + dataSize) + (dataCount * 2);
            }
            if (this.hasGamepadRequired || this.gamepadRequired) {
                size += CodedOutputByteBufferNano.computeBoolSize(26, this.gamepadRequired);
            }
            if (this.hasExternallyHosted || this.externallyHosted) {
                return size + CodedOutputByteBufferNano.computeBoolSize(27, this.externallyHosted);
            }
            return size;
        }

        public AppDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.developerName = input.readString();
                        this.hasDeveloperName = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.majorVersionNumber = input.readInt32();
                        this.hasMajorVersionNumber = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.versionCode = input.readInt32();
                        this.hasVersionCode = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.versionString = input.readString();
                        this.hasVersionString = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        i = this.appCategory == null ? 0 : this.appCategory.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.appCategory, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.appCategory = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        this.contentRating = input.readInt32();
                        this.hasContentRating = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelBackground /*72*/:
                        this.installationSize = input.readInt64();
                        this.hasInstallationSize = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 82);
                        i = this.permission == null ? 0 : this.permission.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.permission, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.permission = newArray;
                        continue;
                    case 90:
                        this.developerEmail = input.readString();
                        this.hasDeveloperEmail = true;
                        continue;
                    case 98:
                        this.developerWebsite = input.readString();
                        this.hasDeveloperWebsite = true;
                        continue;
                    case 106:
                        this.numDownloads = input.readString();
                        this.hasNumDownloads = true;
                        continue;
                    case 114:
                        this.packageName = input.readString();
                        this.hasPackageName = true;
                        continue;
                    case 122:
                        this.recentChangesHtml = input.readString();
                        this.hasRecentChangesHtml = true;
                        continue;
                    case 130:
                        this.uploadDate = input.readString();
                        this.hasUploadDate = true;
                        continue;
                    case 138:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 138);
                        if (this.file == null) {
                            i = 0;
                        } else {
                            i = this.file.length;
                        }
                        FileMetadata[] newArray2 = new FileMetadata[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.file, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new FileMetadata();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new FileMetadata();
                        input.readMessage(newArray2[i]);
                        this.file = newArray2;
                        continue;
                    case 146:
                        this.appType = input.readString();
                        this.hasAppType = true;
                        continue;
                    case 154:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 154);
                        i = this.certificateHash == null ? 0 : this.certificateHash.length;
                        newArray = new String[(i + arrayLength)];
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
                    case 168:
                        this.variesByAccount = input.readBool();
                        this.hasVariesByAccount = true;
                        continue;
                    case 178:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 178);
                        if (this.certificateSet == null) {
                            i = 0;
                        } else {
                            i = this.certificateSet.length;
                        }
                        CertificateSet[] newArray3 = new CertificateSet[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.certificateSet, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new CertificateSet();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new CertificateSet();
                        input.readMessage(newArray3[i]);
                        this.certificateSet = newArray3;
                        continue;
                    case 186:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 186);
                        i = this.autoAcquireFreeAppIfHigherVersionAvailableTag == null ? 0 : this.autoAcquireFreeAppIfHigherVersionAvailableTag.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.autoAcquireFreeAppIfHigherVersionAvailableTag, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.autoAcquireFreeAppIfHigherVersionAvailableTag = newArray;
                        continue;
                    case 192:
                        this.declaresIab = input.readBool();
                        this.hasDeclaresIab = true;
                        continue;
                    case 202:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 202);
                        i = this.splitId == null ? 0 : this.splitId.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.splitId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.splitId = newArray;
                        continue;
                    case 208:
                        this.gamepadRequired = input.readBool();
                        this.hasGamepadRequired = true;
                        continue;
                    case 216:
                        this.externallyHosted = input.readBool();
                        this.hasExternallyHosted = true;
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

    public static final class ArtistDetails extends MessageNano {
        private static volatile ArtistDetails[] _emptyArray;
        public String detailsUrl;
        public ArtistExternalLinks externalLinks;
        public boolean hasDetailsUrl;
        public boolean hasName;
        public String name;

        public static ArtistDetails[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ArtistDetails[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ArtistDetails() {
            clear();
        }

        public ArtistDetails clear() {
            this.detailsUrl = "";
            this.hasDetailsUrl = false;
            this.name = "";
            this.hasName = false;
            this.externalLinks = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                output.writeString(1, this.detailsUrl);
            }
            if (this.hasName || !this.name.equals("")) {
                output.writeString(2, this.name);
            }
            if (this.externalLinks != null) {
                output.writeMessage(3, this.externalLinks);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasDetailsUrl || !this.detailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.detailsUrl);
            }
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.name);
            }
            if (this.externalLinks != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.externalLinks);
            }
            return size;
        }

        public ArtistDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.detailsUrl = input.readString();
                        this.hasDetailsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.externalLinks == null) {
                            this.externalLinks = new ArtistExternalLinks();
                        }
                        input.readMessage(this.externalLinks);
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

    public static final class ArtistExternalLinks extends MessageNano {
        public String googlePlusProfileUrl;
        public boolean hasGooglePlusProfileUrl;
        public boolean hasYoutubeChannelUrl;
        public String[] websiteUrl;
        public String youtubeChannelUrl;

        public ArtistExternalLinks() {
            clear();
        }

        public ArtistExternalLinks clear() {
            this.websiteUrl = WireFormatNano.EMPTY_STRING_ARRAY;
            this.googlePlusProfileUrl = "";
            this.hasGooglePlusProfileUrl = false;
            this.youtubeChannelUrl = "";
            this.hasYoutubeChannelUrl = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.websiteUrl != null && this.websiteUrl.length > 0) {
                for (String element : this.websiteUrl) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.hasGooglePlusProfileUrl || !this.googlePlusProfileUrl.equals("")) {
                output.writeString(2, this.googlePlusProfileUrl);
            }
            if (this.hasYoutubeChannelUrl || !this.youtubeChannelUrl.equals("")) {
                output.writeString(3, this.youtubeChannelUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.websiteUrl != null && this.websiteUrl.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.websiteUrl) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasGooglePlusProfileUrl || !this.googlePlusProfileUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.googlePlusProfileUrl);
            }
            if (this.hasYoutubeChannelUrl || !this.youtubeChannelUrl.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.youtubeChannelUrl);
            }
            return size;
        }

        public ArtistExternalLinks mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        int i = this.websiteUrl == null ? 0 : this.websiteUrl.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.websiteUrl, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.websiteUrl = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.googlePlusProfileUrl = input.readString();
                        this.hasGooglePlusProfileUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.youtubeChannelUrl = input.readString();
                        this.hasYoutubeChannelUrl = true;
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

    public static final class BookDetails extends MessageNano {
        public String aboutTheAuthor;
        public boolean hasAboutTheAuthor;
        public boolean hasIsbn;
        public boolean hasNumberOfPages;
        public boolean hasPublicationDate;
        public boolean hasPublisher;
        public String isbn;
        public int numberOfPages;
        public String publicationDate;
        public String publisher;

        public BookDetails() {
            clear();
        }

        public BookDetails clear() {
            this.publisher = "";
            this.hasPublisher = false;
            this.publicationDate = "";
            this.hasPublicationDate = false;
            this.isbn = "";
            this.hasIsbn = false;
            this.numberOfPages = 0;
            this.hasNumberOfPages = false;
            this.aboutTheAuthor = "";
            this.hasAboutTheAuthor = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPublisher || !this.publisher.equals("")) {
                output.writeString(4, this.publisher);
            }
            if (this.hasPublicationDate || !this.publicationDate.equals("")) {
                output.writeString(5, this.publicationDate);
            }
            if (this.hasIsbn || !this.isbn.equals("")) {
                output.writeString(6, this.isbn);
            }
            if (this.hasNumberOfPages || this.numberOfPages != 0) {
                output.writeInt32(7, this.numberOfPages);
            }
            if (this.hasAboutTheAuthor || !this.aboutTheAuthor.equals("")) {
                output.writeString(17, this.aboutTheAuthor);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPublisher || !this.publisher.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.publisher);
            }
            if (this.hasPublicationDate || !this.publicationDate.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.publicationDate);
            }
            if (this.hasIsbn || !this.isbn.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(6, this.isbn);
            }
            if (this.hasNumberOfPages || this.numberOfPages != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(7, this.numberOfPages);
            }
            if (this.hasAboutTheAuthor || !this.aboutTheAuthor.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(17, this.aboutTheAuthor);
            }
            return size;
        }

        public BookDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.publisher = input.readString();
                        this.hasPublisher = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.publicationDate = input.readString();
                        this.hasPublicationDate = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        this.isbn = input.readString();
                        this.hasIsbn = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                        this.numberOfPages = input.readInt32();
                        this.hasNumberOfPages = true;
                        continue;
                    case 138:
                        this.aboutTheAuthor = input.readString();
                        this.hasAboutTheAuthor = true;
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

    public static final class CertificateSet extends MessageNano {
        private static volatile CertificateSet[] _emptyArray;
        public String[] certificateHash;

        public static CertificateSet[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new CertificateSet[0];
                    }
                }
            }
            return _emptyArray;
        }

        public CertificateSet() {
            clear();
        }

        public CertificateSet clear() {
            this.certificateHash = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.certificateHash != null && this.certificateHash.length > 0) {
                for (String element : this.certificateHash) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
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

        public CertificateSet mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
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

    public static final class DocumentDetails extends MessageNano {
        public AlbumDetails albumDetails;
        public AppDetails appDetails;
        public ArtistDetails artistDetails;
        public BookDetails bookDetails;
        public MagazineDetails magazineDetails;
        public PersonDetails personDetails;
        public SongDetails songDetails;
        public SubscriptionDetails subscriptionDetails;
        public TalentDetails talentDetails;
        public TvEpisodeDetails tvEpisodeDetails;
        public TvSeasonDetails tvSeasonDetails;
        public TvShowDetails tvShowDetails;
        public VideoDetails videoDetails;

        public DocumentDetails() {
            clear();
        }

        public DocumentDetails clear() {
            this.appDetails = null;
            this.albumDetails = null;
            this.artistDetails = null;
            this.songDetails = null;
            this.bookDetails = null;
            this.videoDetails = null;
            this.subscriptionDetails = null;
            this.magazineDetails = null;
            this.tvShowDetails = null;
            this.tvSeasonDetails = null;
            this.tvEpisodeDetails = null;
            this.personDetails = null;
            this.talentDetails = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.appDetails != null) {
                output.writeMessage(1, this.appDetails);
            }
            if (this.albumDetails != null) {
                output.writeMessage(2, this.albumDetails);
            }
            if (this.artistDetails != null) {
                output.writeMessage(3, this.artistDetails);
            }
            if (this.songDetails != null) {
                output.writeMessage(4, this.songDetails);
            }
            if (this.bookDetails != null) {
                output.writeMessage(5, this.bookDetails);
            }
            if (this.videoDetails != null) {
                output.writeMessage(6, this.videoDetails);
            }
            if (this.subscriptionDetails != null) {
                output.writeMessage(7, this.subscriptionDetails);
            }
            if (this.magazineDetails != null) {
                output.writeMessage(8, this.magazineDetails);
            }
            if (this.tvShowDetails != null) {
                output.writeMessage(9, this.tvShowDetails);
            }
            if (this.tvSeasonDetails != null) {
                output.writeMessage(10, this.tvSeasonDetails);
            }
            if (this.tvEpisodeDetails != null) {
                output.writeMessage(11, this.tvEpisodeDetails);
            }
            if (this.personDetails != null) {
                output.writeMessage(12, this.personDetails);
            }
            if (this.talentDetails != null) {
                output.writeMessage(13, this.talentDetails);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.appDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.appDetails);
            }
            if (this.albumDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.albumDetails);
            }
            if (this.artistDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(3, this.artistDetails);
            }
            if (this.songDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.songDetails);
            }
            if (this.bookDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.bookDetails);
            }
            if (this.videoDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.videoDetails);
            }
            if (this.subscriptionDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(7, this.subscriptionDetails);
            }
            if (this.magazineDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(8, this.magazineDetails);
            }
            if (this.tvShowDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(9, this.tvShowDetails);
            }
            if (this.tvSeasonDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(10, this.tvSeasonDetails);
            }
            if (this.tvEpisodeDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(11, this.tvEpisodeDetails);
            }
            if (this.personDetails != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.personDetails);
            }
            if (this.talentDetails != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(13, this.talentDetails);
            }
            return size;
        }

        public DocumentDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.appDetails == null) {
                            this.appDetails = new AppDetails();
                        }
                        input.readMessage(this.appDetails);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.albumDetails == null) {
                            this.albumDetails = new AlbumDetails();
                        }
                        input.readMessage(this.albumDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.artistDetails == null) {
                            this.artistDetails = new ArtistDetails();
                        }
                        input.readMessage(this.artistDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.songDetails == null) {
                            this.songDetails = new SongDetails();
                        }
                        input.readMessage(this.songDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.bookDetails == null) {
                            this.bookDetails = new BookDetails();
                        }
                        input.readMessage(this.bookDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.videoDetails == null) {
                            this.videoDetails = new VideoDetails();
                        }
                        input.readMessage(this.videoDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.subscriptionDetails == null) {
                            this.subscriptionDetails = new SubscriptionDetails();
                        }
                        input.readMessage(this.subscriptionDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        if (this.magazineDetails == null) {
                            this.magazineDetails = new MagazineDetails();
                        }
                        input.readMessage(this.magazineDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        if (this.tvShowDetails == null) {
                            this.tvShowDetails = new TvShowDetails();
                        }
                        input.readMessage(this.tvShowDetails);
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        if (this.tvSeasonDetails == null) {
                            this.tvSeasonDetails = new TvSeasonDetails();
                        }
                        input.readMessage(this.tvSeasonDetails);
                        continue;
                    case 90:
                        if (this.tvEpisodeDetails == null) {
                            this.tvEpisodeDetails = new TvEpisodeDetails();
                        }
                        input.readMessage(this.tvEpisodeDetails);
                        continue;
                    case 98:
                        if (this.personDetails == null) {
                            this.personDetails = new PersonDetails();
                        }
                        input.readMessage(this.personDetails);
                        continue;
                    case 106:
                        if (this.talentDetails == null) {
                            this.talentDetails = new TalentDetails();
                        }
                        input.readMessage(this.talentDetails);
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

    public static final class FileMetadata extends MessageNano {
        private static volatile FileMetadata[] _emptyArray;
        public int fileType;
        public boolean hasFileType;
        public boolean hasSize;
        public boolean hasSplitId;
        public boolean hasVersionCode;
        public long size;
        public String splitId;
        public int versionCode;

        public static FileMetadata[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new FileMetadata[0];
                    }
                }
            }
            return _emptyArray;
        }

        public FileMetadata() {
            clear();
        }

        public FileMetadata clear() {
            this.fileType = 0;
            this.hasFileType = false;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.size = 0;
            this.hasSize = false;
            this.splitId = "";
            this.hasSplitId = false;
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
            if (this.hasSplitId || !this.splitId.equals("")) {
                output.writeString(4, this.splitId);
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
            if (this.hasSplitId || !this.splitId.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.splitId);
            }
            return size;
        }

        public FileMetadata mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        this.splitId = input.readString();
                        this.hasSplitId = true;
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

    public static final class MagazineDetails extends MessageNano {
        public String deliveryFrequencyDescription;
        public String deviceAvailabilityDescriptionHtml;
        public boolean hasDeliveryFrequencyDescription;
        public boolean hasDeviceAvailabilityDescriptionHtml;
        public boolean hasParentDetailsUrl;
        public boolean hasPsvDescription;
        public String parentDetailsUrl;
        public String psvDescription;

        public MagazineDetails() {
            clear();
        }

        public MagazineDetails clear() {
            this.parentDetailsUrl = "";
            this.hasParentDetailsUrl = false;
            this.deviceAvailabilityDescriptionHtml = "";
            this.hasDeviceAvailabilityDescriptionHtml = false;
            this.psvDescription = "";
            this.hasPsvDescription = false;
            this.deliveryFrequencyDescription = "";
            this.hasDeliveryFrequencyDescription = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasParentDetailsUrl || !this.parentDetailsUrl.equals("")) {
                output.writeString(1, this.parentDetailsUrl);
            }
            if (this.hasDeviceAvailabilityDescriptionHtml || !this.deviceAvailabilityDescriptionHtml.equals("")) {
                output.writeString(2, this.deviceAvailabilityDescriptionHtml);
            }
            if (this.hasPsvDescription || !this.psvDescription.equals("")) {
                output.writeString(3, this.psvDescription);
            }
            if (this.hasDeliveryFrequencyDescription || !this.deliveryFrequencyDescription.equals("")) {
                output.writeString(4, this.deliveryFrequencyDescription);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasParentDetailsUrl || !this.parentDetailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.parentDetailsUrl);
            }
            if (this.hasDeviceAvailabilityDescriptionHtml || !this.deviceAvailabilityDescriptionHtml.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.deviceAvailabilityDescriptionHtml);
            }
            if (this.hasPsvDescription || !this.psvDescription.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.psvDescription);
            }
            if (this.hasDeliveryFrequencyDescription || !this.deliveryFrequencyDescription.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.deliveryFrequencyDescription);
            }
            return size;
        }

        public MagazineDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.parentDetailsUrl = input.readString();
                        this.hasParentDetailsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.deviceAvailabilityDescriptionHtml = input.readString();
                        this.hasDeviceAvailabilityDescriptionHtml = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.psvDescription = input.readString();
                        this.hasPsvDescription = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.deliveryFrequencyDescription = input.readString();
                        this.hasDeliveryFrequencyDescription = true;
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

    public static final class MusicDetails extends MessageNano {
        public ArtistDetails[] artist;
        public int censoring;
        public int durationSec;
        public String[] genre;
        public boolean hasCensoring;
        public boolean hasDurationSec;
        public boolean hasLabel;
        public boolean hasOriginalReleaseDate;
        public boolean hasReleaseDate;
        public String label;
        public String originalReleaseDate;
        public String releaseDate;
        public int[] releaseType;

        public MusicDetails() {
            clear();
        }

        public MusicDetails clear() {
            this.censoring = 0;
            this.hasCensoring = false;
            this.releaseType = WireFormatNano.EMPTY_INT_ARRAY;
            this.durationSec = 0;
            this.hasDurationSec = false;
            this.originalReleaseDate = "";
            this.hasOriginalReleaseDate = false;
            this.releaseDate = "";
            this.hasReleaseDate = false;
            this.label = "";
            this.hasLabel = false;
            this.artist = ArtistDetails.emptyArray();
            this.genre = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.censoring != 0 || this.hasCensoring) {
                output.writeInt32(1, this.censoring);
            }
            if (this.hasDurationSec || this.durationSec != 0) {
                output.writeInt32(2, this.durationSec);
            }
            if (this.hasOriginalReleaseDate || !this.originalReleaseDate.equals("")) {
                output.writeString(3, this.originalReleaseDate);
            }
            if (this.hasLabel || !this.label.equals("")) {
                output.writeString(4, this.label);
            }
            if (this.artist != null && this.artist.length > 0) {
                for (ArtistDetails element : this.artist) {
                    if (element != null) {
                        output.writeMessage(5, element);
                    }
                }
            }
            if (this.genre != null && this.genre.length > 0) {
                for (String element2 : this.genre) {
                    if (element2 != null) {
                        output.writeString(6, element2);
                    }
                }
            }
            if (this.hasReleaseDate || !this.releaseDate.equals("")) {
                output.writeString(7, this.releaseDate);
            }
            if (this.releaseType != null && this.releaseType.length > 0) {
                for (int writeInt32 : this.releaseType) {
                    output.writeInt32(8, writeInt32);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.censoring != 0 || this.hasCensoring) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.censoring);
            }
            if (this.hasDurationSec || this.durationSec != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.durationSec);
            }
            if (this.hasOriginalReleaseDate || !this.originalReleaseDate.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.originalReleaseDate);
            }
            if (this.hasLabel || !this.label.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.label);
            }
            if (this.artist != null && this.artist.length > 0) {
                for (ArtistDetails element : this.artist) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(5, element);
                    }
                }
            }
            if (this.genre != null && this.genre.length > 0) {
                int dataCount = 0;
                dataSize = 0;
                for (String element2 : this.genre) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasReleaseDate || !this.releaseDate.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(7, this.releaseDate);
            }
            if (this.releaseType == null || this.releaseType.length <= 0) {
                return size;
            }
            dataSize = 0;
            for (int element3 : this.releaseType) {
                dataSize += CodedOutputByteBufferNano.computeInt32SizeNoTag(element3);
            }
            return (size + dataSize) + (this.releaseType.length * 1);
        }

        public MusicDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int value;
                int arrayLength;
                int i;
                int[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.censoring = value;
                                this.hasCensoring = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.durationSec = input.readInt32();
                        this.hasDurationSec = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.originalReleaseDate = input.readString();
                        this.hasOriginalReleaseDate = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.label = input.readString();
                        this.hasLabel = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 42);
                        if (this.artist == null) {
                            i = 0;
                        } else {
                            i = this.artist.length;
                        }
                        ArtistDetails[] newArray2 = new ArtistDetails[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.artist, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new ArtistDetails();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new ArtistDetails();
                        input.readMessage(newArray2[i]);
                        this.artist = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 50);
                        i = this.genre == null ? 0 : this.genre.length;
                        String[] newArray3 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.genre, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = input.readString();
                        this.genre = newArray3;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        this.releaseDate = input.readString();
                        this.hasReleaseDate = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemHeightSmall /*64*/:
                        int length = WireFormatNano.getRepeatedFieldArrayLength(input, 64);
                        int[] validValues = new int[length];
                        i = 0;
                        int validCount = 0;
                        while (i < length) {
                            int validCount2;
                            if (i != 0) {
                                input.readTag();
                            }
                            value = input.readInt32();
                            switch (value) {
                                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                    validCount2 = validCount + 1;
                                    validValues[validCount] = value;
                                    break;
                                default:
                                    validCount2 = validCount;
                                    break;
                            }
                            i++;
                            validCount = validCount2;
                        }
                        if (validCount != 0) {
                            i = this.releaseType == null ? 0 : this.releaseType.length;
                            if (i != 0 || validCount != validValues.length) {
                                newArray = new int[(i + validCount)];
                                if (i != 0) {
                                    System.arraycopy(this.releaseType, 0, newArray, 0, i);
                                }
                                System.arraycopy(validValues, 0, newArray, i, validCount);
                                this.releaseType = newArray;
                                break;
                            }
                            this.releaseType = validValues;
                            break;
                        }
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            switch (input.readInt32()) {
                                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                    arrayLength++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (arrayLength != 0) {
                            input.rewindToPosition(startPos);
                            if (this.releaseType == null) {
                                i = 0;
                            } else {
                                i = this.releaseType.length;
                            }
                            newArray = new int[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.releaseType, 0, newArray, 0, i);
                            }
                            while (input.getBytesUntilLimit() > 0) {
                                value = input.readInt32();
                                switch (value) {
                                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                        int i2 = i + 1;
                                        newArray[i] = value;
                                        i = i2;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.releaseType = newArray;
                        }
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

    public static final class PersonDetails extends MessageNano {
        public boolean hasPersonIsRequester;
        public boolean personIsRequester;

        public PersonDetails() {
            clear();
        }

        public PersonDetails clear() {
            this.personIsRequester = false;
            this.hasPersonIsRequester = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasPersonIsRequester || this.personIsRequester) {
                output.writeBool(1, this.personIsRequester);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasPersonIsRequester || this.personIsRequester) {
                return size + CodedOutputByteBufferNano.computeBoolSize(1, this.personIsRequester);
            }
            return size;
        }

        public PersonDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.personIsRequester = input.readBool();
                        this.hasPersonIsRequester = true;
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

    public static final class ProductDetails extends MessageNano {
        public boolean hasTitle;
        public ProductDetailsSection[] section;
        public String title;

        public ProductDetails() {
            clear();
        }

        public ProductDetails clear() {
            this.title = "";
            this.hasTitle = false;
            this.section = ProductDetailsSection.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.section != null && this.section.length > 0) {
                for (ProductDetailsSection element : this.section) {
                    if (element != null) {
                        output.writeMessage(2, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.section != null && this.section.length > 0) {
                for (ProductDetailsSection element : this.section) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(2, element);
                    }
                }
            }
            return size;
        }

        public ProductDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        if (this.section == null) {
                            i = 0;
                        } else {
                            i = this.section.length;
                        }
                        ProductDetailsSection[] newArray = new ProductDetailsSection[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.section, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ProductDetailsSection();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ProductDetailsSection();
                        input.readMessage(newArray[i]);
                        this.section = newArray;
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

    public static final class ProductDetailsDescription extends MessageNano {
        private static volatile ProductDetailsDescription[] _emptyArray;
        public String description;
        public boolean hasDescription;
        public Image image;

        public static ProductDetailsDescription[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ProductDetailsDescription[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ProductDetailsDescription() {
            clear();
        }

        public ProductDetailsDescription clear() {
            this.image = null;
            this.description = "";
            this.hasDescription = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.image != null) {
                output.writeMessage(1, this.image);
            }
            if (this.hasDescription || !this.description.equals("")) {
                output.writeString(2, this.description);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.image != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.image);
            }
            if (this.hasDescription || !this.description.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(2, this.description);
            }
            return size;
        }

        public ProductDetailsDescription mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.image == null) {
                            this.image = new Image();
                        }
                        input.readMessage(this.image);
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.description = input.readString();
                        this.hasDescription = true;
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

    public static final class ProductDetailsSection extends MessageNano {
        private static volatile ProductDetailsSection[] _emptyArray;
        public ProductDetailsDescription[] description;
        public boolean hasTitle;
        public String title;

        public static ProductDetailsSection[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ProductDetailsSection[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ProductDetailsSection() {
            clear();
        }

        public ProductDetailsSection clear() {
            this.title = "";
            this.hasTitle = false;
            this.description = ProductDetailsDescription.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(1, this.title);
            }
            if (this.description != null && this.description.length > 0) {
                for (ProductDetailsDescription element : this.description) {
                    if (element != null) {
                        output.writeMessage(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.title);
            }
            if (this.description != null && this.description.length > 0) {
                for (ProductDetailsDescription element : this.description) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                    }
                }
            }
            return size;
        }

        public ProductDetailsSection mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        if (this.description == null) {
                            i = 0;
                        } else {
                            i = this.description.length;
                        }
                        ProductDetailsDescription[] newArray = new ProductDetailsDescription[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.description, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ProductDetailsDescription();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ProductDetailsDescription();
                        input.readMessage(newArray[i]);
                        this.description = newArray;
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

    public static final class SongDetails extends MessageNano {
        public String albumName;
        public Badge badge;
        public MusicDetails details;
        public ArtistDetails displayArtist;
        public boolean hasAlbumName;
        public boolean hasName;
        public boolean hasPreviewUrl;
        public boolean hasTrackNumber;
        public String name;
        public String previewUrl;
        public int trackNumber;

        public SongDetails() {
            clear();
        }

        public SongDetails clear() {
            this.name = "";
            this.hasName = false;
            this.details = null;
            this.albumName = "";
            this.hasAlbumName = false;
            this.trackNumber = 0;
            this.hasTrackNumber = false;
            this.previewUrl = "";
            this.hasPreviewUrl = false;
            this.displayArtist = null;
            this.badge = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasName || !this.name.equals("")) {
                output.writeString(1, this.name);
            }
            if (this.details != null) {
                output.writeMessage(2, this.details);
            }
            if (this.hasAlbumName || !this.albumName.equals("")) {
                output.writeString(3, this.albumName);
            }
            if (this.hasTrackNumber || this.trackNumber != 0) {
                output.writeInt32(4, this.trackNumber);
            }
            if (this.hasPreviewUrl || !this.previewUrl.equals("")) {
                output.writeString(5, this.previewUrl);
            }
            if (this.displayArtist != null) {
                output.writeMessage(6, this.displayArtist);
            }
            if (this.badge != null) {
                output.writeMessage(7, this.badge);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
            }
            if (this.details != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.details);
            }
            if (this.hasAlbumName || !this.albumName.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.albumName);
            }
            if (this.hasTrackNumber || this.trackNumber != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.trackNumber);
            }
            if (this.hasPreviewUrl || !this.previewUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(5, this.previewUrl);
            }
            if (this.displayArtist != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(6, this.displayArtist);
            }
            if (this.badge != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(7, this.badge);
            }
            return size;
        }

        public SongDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        if (this.details == null) {
                            this.details = new MusicDetails();
                        }
                        input.readMessage(this.details);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.albumName = input.readString();
                        this.hasAlbumName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.trackNumber = input.readInt32();
                        this.hasTrackNumber = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.previewUrl = input.readString();
                        this.hasPreviewUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                        if (this.displayArtist == null) {
                            this.displayArtist = new ArtistDetails();
                        }
                        input.readMessage(this.displayArtist);
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        if (this.badge == null) {
                            this.badge = new Badge();
                        }
                        input.readMessage(this.badge);
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

    public static final class SubscriptionDetails extends MessageNano {
        public boolean hasSubscriptionPeriod;
        public int subscriptionPeriod;

        public SubscriptionDetails() {
            clear();
        }

        public SubscriptionDetails clear() {
            this.subscriptionPeriod = 1;
            this.hasSubscriptionPeriod = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.subscriptionPeriod != 1 || this.hasSubscriptionPeriod) {
                output.writeInt32(1, this.subscriptionPeriod);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.subscriptionPeriod != 1 || this.hasSubscriptionPeriod) {
                return size + CodedOutputByteBufferNano.computeInt32Size(1, this.subscriptionPeriod);
            }
            return size;
        }

        public SubscriptionDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.subscriptionPeriod = value;
                                this.hasSubscriptionPeriod = true;
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

    public static final class TalentDetails extends MessageNano {
        public TalentExternalLinks externalLinks;
        public boolean hasPrimaryRoleId;
        public int primaryRoleId;

        public TalentDetails() {
            clear();
        }

        public TalentDetails clear() {
            this.externalLinks = null;
            this.primaryRoleId = 0;
            this.hasPrimaryRoleId = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.externalLinks != null) {
                output.writeMessage(1, this.externalLinks);
            }
            if (this.primaryRoleId != 0 || this.hasPrimaryRoleId) {
                output.writeInt32(2, this.primaryRoleId);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.externalLinks != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(1, this.externalLinks);
            }
            if (this.primaryRoleId != 0 || this.hasPrimaryRoleId) {
                return size + CodedOutputByteBufferNano.computeInt32Size(2, this.primaryRoleId);
            }
            return size;
        }

        public TalentDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.externalLinks == null) {
                            this.externalLinks = new TalentExternalLinks();
                        }
                        input.readMessage(this.externalLinks);
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
                                this.primaryRoleId = value;
                                this.hasPrimaryRoleId = true;
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

    public static final class TalentExternalLinks extends MessageNano {
        public Link googlePlusProfileUrl;
        public Link[] websiteUrl;
        public Link youtubeChannelUrl;

        public TalentExternalLinks() {
            clear();
        }

        public TalentExternalLinks clear() {
            this.websiteUrl = Link.emptyArray();
            this.googlePlusProfileUrl = null;
            this.youtubeChannelUrl = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.websiteUrl != null && this.websiteUrl.length > 0) {
                for (Link element : this.websiteUrl) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.googlePlusProfileUrl != null) {
                output.writeMessage(2, this.googlePlusProfileUrl);
            }
            if (this.youtubeChannelUrl != null) {
                output.writeMessage(3, this.youtubeChannelUrl);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.websiteUrl != null && this.websiteUrl.length > 0) {
                for (Link element : this.websiteUrl) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.googlePlusProfileUrl != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.googlePlusProfileUrl);
            }
            if (this.youtubeChannelUrl != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(3, this.youtubeChannelUrl);
            }
            return size;
        }

        public TalentExternalLinks mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.websiteUrl == null) {
                            i = 0;
                        } else {
                            i = this.websiteUrl.length;
                        }
                        Link[] newArray = new Link[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.websiteUrl, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Link();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Link();
                        input.readMessage(newArray[i]);
                        this.websiteUrl = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.googlePlusProfileUrl == null) {
                            this.googlePlusProfileUrl = new Link();
                        }
                        input.readMessage(this.googlePlusProfileUrl);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        if (this.youtubeChannelUrl == null) {
                            this.youtubeChannelUrl = new Link();
                        }
                        input.readMessage(this.youtubeChannelUrl);
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

    public static final class Trailer extends MessageNano {
        private static volatile Trailer[] _emptyArray;
        public String duration;
        public boolean hasDuration;
        public boolean hasThumbnailUrl;
        public boolean hasTitle;
        public boolean hasTrailerId;
        public boolean hasWatchUrl;
        public String thumbnailUrl;
        public String title;
        public String trailerId;
        public String watchUrl;

        public static Trailer[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new Trailer[0];
                    }
                }
            }
            return _emptyArray;
        }

        public Trailer() {
            clear();
        }

        public Trailer clear() {
            this.trailerId = "";
            this.hasTrailerId = false;
            this.title = "";
            this.hasTitle = false;
            this.thumbnailUrl = "";
            this.hasThumbnailUrl = false;
            this.watchUrl = "";
            this.hasWatchUrl = false;
            this.duration = "";
            this.hasDuration = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasTrailerId || !this.trailerId.equals("")) {
                output.writeString(1, this.trailerId);
            }
            if (this.hasTitle || !this.title.equals("")) {
                output.writeString(2, this.title);
            }
            if (this.hasThumbnailUrl || !this.thumbnailUrl.equals("")) {
                output.writeString(3, this.thumbnailUrl);
            }
            if (this.hasWatchUrl || !this.watchUrl.equals("")) {
                output.writeString(4, this.watchUrl);
            }
            if (this.hasDuration || !this.duration.equals("")) {
                output.writeString(5, this.duration);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasTrailerId || !this.trailerId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.trailerId);
            }
            if (this.hasTitle || !this.title.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.title);
            }
            if (this.hasThumbnailUrl || !this.thumbnailUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.thumbnailUrl);
            }
            if (this.hasWatchUrl || !this.watchUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.watchUrl);
            }
            if (this.hasDuration || !this.duration.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(5, this.duration);
            }
            return size;
        }

        public Trailer mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.trailerId = input.readString();
                        this.hasTrailerId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.title = input.readString();
                        this.hasTitle = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.thumbnailUrl = input.readString();
                        this.hasThumbnailUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.watchUrl = input.readString();
                        this.hasWatchUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        this.duration = input.readString();
                        this.hasDuration = true;
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

    public static final class TvEpisodeDetails extends MessageNano {
        public int episodeIndex;
        public boolean hasEpisodeIndex;
        public boolean hasParentDetailsUrl;
        public boolean hasReleaseDate;
        public String parentDetailsUrl;
        public String releaseDate;

        public TvEpisodeDetails() {
            clear();
        }

        public TvEpisodeDetails clear() {
            this.parentDetailsUrl = "";
            this.hasParentDetailsUrl = false;
            this.episodeIndex = 0;
            this.hasEpisodeIndex = false;
            this.releaseDate = "";
            this.hasReleaseDate = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasParentDetailsUrl || !this.parentDetailsUrl.equals("")) {
                output.writeString(1, this.parentDetailsUrl);
            }
            if (this.hasEpisodeIndex || this.episodeIndex != 0) {
                output.writeInt32(2, this.episodeIndex);
            }
            if (this.hasReleaseDate || !this.releaseDate.equals("")) {
                output.writeString(3, this.releaseDate);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasParentDetailsUrl || !this.parentDetailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.parentDetailsUrl);
            }
            if (this.hasEpisodeIndex || this.episodeIndex != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.episodeIndex);
            }
            if (this.hasReleaseDate || !this.releaseDate.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(3, this.releaseDate);
            }
            return size;
        }

        public TvEpisodeDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.parentDetailsUrl = input.readString();
                        this.hasParentDetailsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.episodeIndex = input.readInt32();
                        this.hasEpisodeIndex = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.releaseDate = input.readString();
                        this.hasReleaseDate = true;
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

    public static final class TvSeasonDetails extends MessageNano {
        public String broadcaster;
        public int episodeCount;
        public int expectedEpisodeCount;
        public boolean hasBroadcaster;
        public boolean hasEpisodeCount;
        public boolean hasExpectedEpisodeCount;
        public boolean hasParentDetailsUrl;
        public boolean hasReleaseDate;
        public boolean hasSeasonIndex;
        public String parentDetailsUrl;
        public String releaseDate;
        public int seasonIndex;

        public TvSeasonDetails() {
            clear();
        }

        public TvSeasonDetails clear() {
            this.parentDetailsUrl = "";
            this.hasParentDetailsUrl = false;
            this.seasonIndex = 0;
            this.hasSeasonIndex = false;
            this.releaseDate = "";
            this.hasReleaseDate = false;
            this.broadcaster = "";
            this.hasBroadcaster = false;
            this.episodeCount = 0;
            this.hasEpisodeCount = false;
            this.expectedEpisodeCount = 0;
            this.hasExpectedEpisodeCount = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasParentDetailsUrl || !this.parentDetailsUrl.equals("")) {
                output.writeString(1, this.parentDetailsUrl);
            }
            if (this.hasSeasonIndex || this.seasonIndex != 0) {
                output.writeInt32(2, this.seasonIndex);
            }
            if (this.hasReleaseDate || !this.releaseDate.equals("")) {
                output.writeString(3, this.releaseDate);
            }
            if (this.hasBroadcaster || !this.broadcaster.equals("")) {
                output.writeString(4, this.broadcaster);
            }
            if (this.hasEpisodeCount || this.episodeCount != 0) {
                output.writeInt32(5, this.episodeCount);
            }
            if (this.hasExpectedEpisodeCount || this.expectedEpisodeCount != 0) {
                output.writeInt32(6, this.expectedEpisodeCount);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasParentDetailsUrl || !this.parentDetailsUrl.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.parentDetailsUrl);
            }
            if (this.hasSeasonIndex || this.seasonIndex != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.seasonIndex);
            }
            if (this.hasReleaseDate || !this.releaseDate.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.releaseDate);
            }
            if (this.hasBroadcaster || !this.broadcaster.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.broadcaster);
            }
            if (this.hasEpisodeCount || this.episodeCount != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(5, this.episodeCount);
            }
            if (this.hasExpectedEpisodeCount || this.expectedEpisodeCount != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(6, this.expectedEpisodeCount);
            }
            return size;
        }

        public TvSeasonDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.parentDetailsUrl = input.readString();
                        this.hasParentDetailsUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.seasonIndex = input.readInt32();
                        this.hasSeasonIndex = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.releaseDate = input.readString();
                        this.hasReleaseDate = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.broadcaster = input.readString();
                        this.hasBroadcaster = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.episodeCount = input.readInt32();
                        this.hasEpisodeCount = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.expectedEpisodeCount = input.readInt32();
                        this.hasExpectedEpisodeCount = true;
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

    public static final class TvShowDetails extends MessageNano {
        public String broadcaster;
        public int endYear;
        public boolean hasBroadcaster;
        public boolean hasEndYear;
        public boolean hasSeasonCount;
        public boolean hasStartYear;
        public int seasonCount;
        public int startYear;

        public TvShowDetails() {
            clear();
        }

        public TvShowDetails clear() {
            this.seasonCount = 0;
            this.hasSeasonCount = false;
            this.startYear = 0;
            this.hasStartYear = false;
            this.endYear = 0;
            this.hasEndYear = false;
            this.broadcaster = "";
            this.hasBroadcaster = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasSeasonCount || this.seasonCount != 0) {
                output.writeInt32(1, this.seasonCount);
            }
            if (this.hasStartYear || this.startYear != 0) {
                output.writeInt32(2, this.startYear);
            }
            if (this.hasEndYear || this.endYear != 0) {
                output.writeInt32(3, this.endYear);
            }
            if (this.hasBroadcaster || !this.broadcaster.equals("")) {
                output.writeString(4, this.broadcaster);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasSeasonCount || this.seasonCount != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.seasonCount);
            }
            if (this.hasStartYear || this.startYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(2, this.startYear);
            }
            if (this.hasEndYear || this.endYear != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(3, this.endYear);
            }
            if (this.hasBroadcaster || !this.broadcaster.equals("")) {
                return size + CodedOutputByteBufferNano.computeStringSize(4, this.broadcaster);
            }
            return size;
        }

        public TvShowDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.seasonCount = input.readInt32();
                        this.hasSeasonCount = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.startYear = input.readInt32();
                        this.hasStartYear = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.endYear = input.readInt32();
                        this.hasEndYear = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.broadcaster = input.readString();
                        this.hasBroadcaster = true;
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

    public static final class VideoCredit extends MessageNano {
        private static volatile VideoCredit[] _emptyArray;
        public String credit;
        public int creditType;
        public boolean hasCredit;
        public boolean hasCreditType;
        public String[] name;

        public static VideoCredit[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new VideoCredit[0];
                    }
                }
            }
            return _emptyArray;
        }

        public VideoCredit() {
            clear();
        }

        public VideoCredit clear() {
            this.creditType = 0;
            this.hasCreditType = false;
            this.credit = "";
            this.hasCredit = false;
            this.name = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.creditType != 0 || this.hasCreditType) {
                output.writeInt32(1, this.creditType);
            }
            if (this.hasCredit || !this.credit.equals("")) {
                output.writeString(2, this.credit);
            }
            if (this.name != null && this.name.length > 0) {
                for (String element : this.name) {
                    if (element != null) {
                        output.writeString(3, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.creditType != 0 || this.hasCreditType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.creditType);
            }
            if (this.hasCredit || !this.credit.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.credit);
            }
            if (this.name == null || this.name.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element : this.name) {
                if (element != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public VideoCredit mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.creditType = value;
                                this.hasCreditType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.credit = input.readString();
                        this.hasCredit = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        int i = this.name == null ? 0 : this.name.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.name, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.name = newArray;
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

    public static final class VideoDetails extends MessageNano {
        public String[] audioLanguage;
        public String[] captionLanguage;
        public String contentRating;
        public VideoCredit[] credit;
        public long dislikes;
        public String duration;
        public String[] genre;
        public boolean hasContentRating;
        public boolean hasDislikes;
        public boolean hasDuration;
        public boolean hasLikes;
        public boolean hasReleaseDate;
        public long likes;
        public String releaseDate;
        public VideoRentalTerm[] rentalTerm;
        public Trailer[] trailer;

        public VideoDetails() {
            clear();
        }

        public VideoDetails clear() {
            this.credit = VideoCredit.emptyArray();
            this.duration = "";
            this.hasDuration = false;
            this.releaseDate = "";
            this.hasReleaseDate = false;
            this.contentRating = "";
            this.hasContentRating = false;
            this.likes = 0;
            this.hasLikes = false;
            this.dislikes = 0;
            this.hasDislikes = false;
            this.genre = WireFormatNano.EMPTY_STRING_ARRAY;
            this.trailer = Trailer.emptyArray();
            this.rentalTerm = VideoRentalTerm.emptyArray();
            this.audioLanguage = WireFormatNano.EMPTY_STRING_ARRAY;
            this.captionLanguage = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.credit != null && this.credit.length > 0) {
                for (VideoCredit element : this.credit) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasDuration || !this.duration.equals("")) {
                output.writeString(2, this.duration);
            }
            if (this.hasReleaseDate || !this.releaseDate.equals("")) {
                output.writeString(3, this.releaseDate);
            }
            if (this.hasContentRating || !this.contentRating.equals("")) {
                output.writeString(4, this.contentRating);
            }
            if (this.hasLikes || this.likes != 0) {
                output.writeInt64(5, this.likes);
            }
            if (this.hasDislikes || this.dislikes != 0) {
                output.writeInt64(6, this.dislikes);
            }
            if (this.genre != null && this.genre.length > 0) {
                for (String element2 : this.genre) {
                    if (element2 != null) {
                        output.writeString(7, element2);
                    }
                }
            }
            if (this.trailer != null && this.trailer.length > 0) {
                for (Trailer element3 : this.trailer) {
                    if (element3 != null) {
                        output.writeMessage(8, element3);
                    }
                }
            }
            if (this.rentalTerm != null && this.rentalTerm.length > 0) {
                for (VideoRentalTerm element4 : this.rentalTerm) {
                    if (element4 != null) {
                        output.writeMessage(9, element4);
                    }
                }
            }
            if (this.audioLanguage != null && this.audioLanguage.length > 0) {
                for (String element22 : this.audioLanguage) {
                    if (element22 != null) {
                        output.writeString(10, element22);
                    }
                }
            }
            if (this.captionLanguage != null && this.captionLanguage.length > 0) {
                for (String element222 : this.captionLanguage) {
                    if (element222 != null) {
                        output.writeString(11, element222);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.credit != null && this.credit.length > 0) {
                for (VideoCredit element : this.credit) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasDuration || !this.duration.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.duration);
            }
            if (this.hasReleaseDate || !this.releaseDate.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.releaseDate);
            }
            if (this.hasContentRating || !this.contentRating.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(4, this.contentRating);
            }
            if (this.hasLikes || this.likes != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(5, this.likes);
            }
            if (this.hasDislikes || this.dislikes != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(6, this.dislikes);
            }
            if (this.genre != null && this.genre.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.genre) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.trailer != null && this.trailer.length > 0) {
                for (Trailer element3 : this.trailer) {
                    if (element3 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(8, element3);
                    }
                }
            }
            if (this.rentalTerm != null && this.rentalTerm.length > 0) {
                for (VideoRentalTerm element4 : this.rentalTerm) {
                    if (element4 != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(9, element4);
                    }
                }
            }
            if (this.audioLanguage != null && this.audioLanguage.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element22 : this.audioLanguage) {
                    if (element22 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element22);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.captionLanguage == null || this.captionLanguage.length <= 0) {
                return size;
            }
            dataCount = 0;
            dataSize = 0;
            for (String element222 : this.captionLanguage) {
                if (element222 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element222);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public VideoDetails mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                        if (this.credit == null) {
                            i = 0;
                        } else {
                            i = this.credit.length;
                        }
                        VideoCredit[] newArray2 = new VideoCredit[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.credit, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new VideoCredit();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new VideoCredit();
                        input.readMessage(newArray2[i]);
                        this.credit = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.duration = input.readString();
                        this.hasDuration = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.releaseDate = input.readString();
                        this.hasReleaseDate = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.contentRating = input.readString();
                        this.hasContentRating = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.likes = input.readInt64();
                        this.hasLikes = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.dislikes = input.readInt64();
                        this.hasDislikes = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_switchStyle /*58*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 58);
                        i = this.genre == null ? 0 : this.genre.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.genre, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.genre = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        if (this.trailer == null) {
                            i = 0;
                        } else {
                            i = this.trailer.length;
                        }
                        Trailer[] newArray3 = new Trailer[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.trailer, 0, newArray3, 0, i);
                        }
                        while (i < newArray3.length - 1) {
                            newArray3[i] = new Trailer();
                            input.readMessage(newArray3[i]);
                            input.readTag();
                            i++;
                        }
                        newArray3[i] = new Trailer();
                        input.readMessage(newArray3[i]);
                        this.trailer = newArray3;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 74);
                        if (this.rentalTerm == null) {
                            i = 0;
                        } else {
                            i = this.rentalTerm.length;
                        }
                        VideoRentalTerm[] newArray4 = new VideoRentalTerm[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.rentalTerm, 0, newArray4, 0, i);
                        }
                        while (i < newArray4.length - 1) {
                            newArray4[i] = new VideoRentalTerm();
                            input.readMessage(newArray4[i]);
                            input.readTag();
                            i++;
                        }
                        newArray4[i] = new VideoRentalTerm();
                        input.readMessage(newArray4[i]);
                        this.rentalTerm = newArray4;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorButtonNormal /*82*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 82);
                        i = this.audioLanguage == null ? 0 : this.audioLanguage.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.audioLanguage, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.audioLanguage = newArray;
                        continue;
                    case 90:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 90);
                        i = this.captionLanguage == null ? 0 : this.captionLanguage.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.captionLanguage, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.captionLanguage = newArray;
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

    public static final class VideoRentalTerm extends MessageNano {
        private static volatile VideoRentalTerm[] _emptyArray;
        public boolean hasOfferAbbreviation;
        public boolean hasOfferType;
        public boolean hasRentalHeader;
        public String offerAbbreviation;
        public int offerType;
        public String rentalHeader;
        public Term[] term;

        public static final class Term extends MessageNano {
            private static volatile Term[] _emptyArray;
            public String body;
            public boolean hasBody;
            public boolean hasHeader;
            public String header;

            public static Term[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Term[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Term() {
                clear();
            }

            public Term clear() {
                this.header = "";
                this.hasHeader = false;
                this.body = "";
                this.hasBody = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasHeader || !this.header.equals("")) {
                    output.writeString(5, this.header);
                }
                if (this.hasBody || !this.body.equals("")) {
                    output.writeString(6, this.body);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasHeader || !this.header.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(5, this.header);
                }
                if (this.hasBody || !this.body.equals("")) {
                    return size + CodedOutputByteBufferNano.computeStringSize(6, this.body);
                }
                return size;
            }

            public Term mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                            this.header = input.readString();
                            this.hasHeader = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_dividerHorizontal /*50*/:
                            this.body = input.readString();
                            this.hasBody = true;
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

        public static VideoRentalTerm[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new VideoRentalTerm[0];
                    }
                }
            }
            return _emptyArray;
        }

        public VideoRentalTerm() {
            clear();
        }

        public VideoRentalTerm clear() {
            this.offerType = 1;
            this.hasOfferType = false;
            this.offerAbbreviation = "";
            this.hasOfferAbbreviation = false;
            this.rentalHeader = "";
            this.hasRentalHeader = false;
            this.term = Term.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.offerType != 1 || this.hasOfferType) {
                output.writeInt32(1, this.offerType);
            }
            if (this.hasOfferAbbreviation || !this.offerAbbreviation.equals("")) {
                output.writeString(2, this.offerAbbreviation);
            }
            if (this.hasRentalHeader || !this.rentalHeader.equals("")) {
                output.writeString(3, this.rentalHeader);
            }
            if (this.term != null && this.term.length > 0) {
                for (Term element : this.term) {
                    if (element != null) {
                        output.writeGroup(4, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.offerType != 1 || this.hasOfferType) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.offerType);
            }
            if (this.hasOfferAbbreviation || !this.offerAbbreviation.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.offerAbbreviation);
            }
            if (this.hasRentalHeader || !this.rentalHeader.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.rentalHeader);
            }
            if (this.term != null && this.term.length > 0) {
                for (Term element : this.term) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeGroupSize(4, element);
                    }
                }
            }
            return size;
        }

        public VideoRentalTerm mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                                this.offerType = value;
                                this.hasOfferType = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.offerAbbreviation = input.readString();
                        this.hasOfferAbbreviation = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.rentalHeader = input.readString();
                        this.hasRentalHeader = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeWebSearchDrawable /*35*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 35);
                        if (this.term == null) {
                            i = 0;
                        } else {
                            i = this.term.length;
                        }
                        Term[] newArray = new Term[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.term, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Term();
                            input.readGroup(newArray[i], 4);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new Term();
                        input.readGroup(newArray[i], 4);
                        this.term = newArray;
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
