package com.google.android.vending.verifier.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;
import java.util.Arrays;

public interface CsdClient {

    public static final class ClientDownloadRequest extends MessageNano {
        private static volatile ClientDownloadRequest[] _emptyArray;
        public long androidId;
        public ApkInfo apkInfo;
        public String[] clientAsn;
        public Digests digests;
        public int downloadType;
        public String fileBasename;
        public boolean hasAndroidId;
        public boolean hasDownloadType;
        public boolean hasFileBasename;
        public boolean hasLength;
        public boolean hasLocale;
        public boolean hasRequestId;
        public boolean hasUrl;
        public boolean hasUserInitiated;
        public long length;
        public String locale;
        public String[] originatingPackages;
        public SignatureInfo originatingSignature;
        public byte[] requestId;
        public Resource[] resources;
        public SignatureInfo signature;
        public String url;
        public boolean userInitiated;

        public static final class ApkInfo extends MessageNano {
            public boolean dontWarnAgain;
            public FileInfo[] files;
            public boolean forwardLocked;
            public boolean hasDontWarnAgain;
            public boolean hasForwardLocked;
            public boolean hasInStoppedState;
            public boolean hasInstalledByPlay;
            public boolean hasPackageName;
            public boolean hasVersionCode;
            public boolean inStoppedState;
            public boolean installedByPlay;
            public String packageName;
            public int versionCode;

            public ApkInfo() {
                clear();
            }

            public ApkInfo clear() {
                this.packageName = "";
                this.hasPackageName = false;
                this.versionCode = 0;
                this.hasVersionCode = false;
                this.files = FileInfo.emptyArray();
                this.installedByPlay = false;
                this.hasInstalledByPlay = false;
                this.forwardLocked = false;
                this.hasForwardLocked = false;
                this.inStoppedState = false;
                this.hasInStoppedState = false;
                this.dontWarnAgain = false;
                this.hasDontWarnAgain = false;
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
                if (this.files != null && this.files.length > 0) {
                    for (FileInfo element : this.files) {
                        if (element != null) {
                            output.writeMessage(3, element);
                        }
                    }
                }
                if (this.hasInstalledByPlay || this.installedByPlay) {
                    output.writeBool(4, this.installedByPlay);
                }
                if (this.hasForwardLocked || this.forwardLocked) {
                    output.writeBool(5, this.forwardLocked);
                }
                if (this.hasInStoppedState || this.inStoppedState) {
                    output.writeBool(6, this.inStoppedState);
                }
                if (this.hasDontWarnAgain || this.dontWarnAgain) {
                    output.writeBool(7, this.dontWarnAgain);
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
                if (this.files != null && this.files.length > 0) {
                    for (FileInfo element : this.files) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(3, element);
                        }
                    }
                }
                if (this.hasInstalledByPlay || this.installedByPlay) {
                    size += CodedOutputByteBufferNano.computeBoolSize(4, this.installedByPlay);
                }
                if (this.hasForwardLocked || this.forwardLocked) {
                    size += CodedOutputByteBufferNano.computeBoolSize(5, this.forwardLocked);
                }
                if (this.hasInStoppedState || this.inStoppedState) {
                    size += CodedOutputByteBufferNano.computeBoolSize(6, this.inStoppedState);
                }
                if (this.hasDontWarnAgain || this.dontWarnAgain) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(7, this.dontWarnAgain);
                }
                return size;
            }

            public ApkInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                            if (this.files == null) {
                                i = 0;
                            } else {
                                i = this.files.length;
                            }
                            FileInfo[] newArray = new FileInfo[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.files, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new FileInfo();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new FileInfo();
                            input.readMessage(newArray[i]);
                            this.files = newArray;
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                            this.installedByPlay = input.readBool();
                            this.hasInstalledByPlay = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                            this.forwardLocked = input.readBool();
                            this.hasForwardLocked = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                            this.inStoppedState = input.readBool();
                            this.hasInStoppedState = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                            this.dontWarnAgain = input.readBool();
                            this.hasDontWarnAgain = true;
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

        public static final class CertificateChain extends MessageNano {
            private static volatile CertificateChain[] _emptyArray;
            public Element[] element;

            public static final class Element extends MessageNano {
                private static volatile Element[] _emptyArray;
                public byte[] certificate;
                public long expiryTime;
                public byte[] fingerprint;
                public boolean hasCertificate;
                public boolean hasExpiryTime;
                public boolean hasFingerprint;
                public boolean hasIssuer;
                public boolean hasParsedSuccessfully;
                public boolean hasStartTime;
                public boolean hasSubject;
                public byte[] issuer;
                public boolean parsedSuccessfully;
                public long startTime;
                public byte[] subject;

                public static Element[] emptyArray() {
                    if (_emptyArray == null) {
                        synchronized (InternalNano.LAZY_INIT_LOCK) {
                            if (_emptyArray == null) {
                                _emptyArray = new Element[0];
                            }
                        }
                    }
                    return _emptyArray;
                }

                public Element() {
                    clear();
                }

                public Element clear() {
                    this.certificate = WireFormatNano.EMPTY_BYTES;
                    this.hasCertificate = false;
                    this.parsedSuccessfully = false;
                    this.hasParsedSuccessfully = false;
                    this.subject = WireFormatNano.EMPTY_BYTES;
                    this.hasSubject = false;
                    this.issuer = WireFormatNano.EMPTY_BYTES;
                    this.hasIssuer = false;
                    this.fingerprint = WireFormatNano.EMPTY_BYTES;
                    this.hasFingerprint = false;
                    this.expiryTime = 0;
                    this.hasExpiryTime = false;
                    this.startTime = 0;
                    this.hasStartTime = false;
                    this.cachedSize = -1;
                    return this;
                }

                public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                    if (this.hasCertificate || !Arrays.equals(this.certificate, WireFormatNano.EMPTY_BYTES)) {
                        output.writeBytes(1, this.certificate);
                    }
                    if (this.hasParsedSuccessfully || this.parsedSuccessfully) {
                        output.writeBool(2, this.parsedSuccessfully);
                    }
                    if (this.hasSubject || !Arrays.equals(this.subject, WireFormatNano.EMPTY_BYTES)) {
                        output.writeBytes(3, this.subject);
                    }
                    if (this.hasIssuer || !Arrays.equals(this.issuer, WireFormatNano.EMPTY_BYTES)) {
                        output.writeBytes(4, this.issuer);
                    }
                    if (this.hasFingerprint || !Arrays.equals(this.fingerprint, WireFormatNano.EMPTY_BYTES)) {
                        output.writeBytes(5, this.fingerprint);
                    }
                    if (this.hasExpiryTime || this.expiryTime != 0) {
                        output.writeInt64(6, this.expiryTime);
                    }
                    if (this.hasStartTime || this.startTime != 0) {
                        output.writeInt64(7, this.startTime);
                    }
                    super.writeTo(output);
                }

                protected int computeSerializedSize() {
                    int size = super.computeSerializedSize();
                    if (this.hasCertificate || !Arrays.equals(this.certificate, WireFormatNano.EMPTY_BYTES)) {
                        size += CodedOutputByteBufferNano.computeBytesSize(1, this.certificate);
                    }
                    if (this.hasParsedSuccessfully || this.parsedSuccessfully) {
                        size += CodedOutputByteBufferNano.computeBoolSize(2, this.parsedSuccessfully);
                    }
                    if (this.hasSubject || !Arrays.equals(this.subject, WireFormatNano.EMPTY_BYTES)) {
                        size += CodedOutputByteBufferNano.computeBytesSize(3, this.subject);
                    }
                    if (this.hasIssuer || !Arrays.equals(this.issuer, WireFormatNano.EMPTY_BYTES)) {
                        size += CodedOutputByteBufferNano.computeBytesSize(4, this.issuer);
                    }
                    if (this.hasFingerprint || !Arrays.equals(this.fingerprint, WireFormatNano.EMPTY_BYTES)) {
                        size += CodedOutputByteBufferNano.computeBytesSize(5, this.fingerprint);
                    }
                    if (this.hasExpiryTime || this.expiryTime != 0) {
                        size += CodedOutputByteBufferNano.computeInt64Size(6, this.expiryTime);
                    }
                    if (this.hasStartTime || this.startTime != 0) {
                        return size + CodedOutputByteBufferNano.computeInt64Size(7, this.startTime);
                    }
                    return size;
                }

                public Element mergeFrom(CodedInputByteBufferNano input) throws IOException {
                    while (true) {
                        int tag = input.readTag();
                        switch (tag) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                break;
                            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                                this.certificate = input.readBytes();
                                this.hasCertificate = true;
                                continue;
                            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                                this.parsedSuccessfully = input.readBool();
                                this.hasParsedSuccessfully = true;
                                continue;
                            case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                                this.subject = input.readBytes();
                                this.hasSubject = true;
                                continue;
                            case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                                this.issuer = input.readBytes();
                                this.hasIssuer = true;
                                continue;
                            case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                                this.fingerprint = input.readBytes();
                                this.hasFingerprint = true;
                                continue;
                            case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                                this.expiryTime = input.readInt64();
                                this.hasExpiryTime = true;
                                continue;
                            case com.google.android.play.R.styleable.Theme_editTextColor /*56*/:
                                this.startTime = input.readInt64();
                                this.hasStartTime = true;
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

            public static CertificateChain[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new CertificateChain[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public CertificateChain() {
                clear();
            }

            public CertificateChain clear() {
                this.element = Element.emptyArray();
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.element != null && this.element.length > 0) {
                    for (Element element : this.element) {
                        if (element != null) {
                            output.writeMessage(1, element);
                        }
                    }
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.element != null && this.element.length > 0) {
                    for (Element element : this.element) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                        }
                    }
                }
                return size;
            }

            public CertificateChain mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                            if (this.element == null) {
                                i = 0;
                            } else {
                                i = this.element.length;
                            }
                            Element[] newArray = new Element[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.element, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new Element();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new Element();
                            input.readMessage(newArray[i]);
                            this.element = newArray;
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

        public static final class Digests extends MessageNano {
            public boolean hasMd5;
            public boolean hasSha1;
            public boolean hasSha256;
            public byte[] md5;
            public byte[] sha1;
            public byte[] sha256;

            public Digests() {
                clear();
            }

            public Digests clear() {
                this.sha256 = WireFormatNano.EMPTY_BYTES;
                this.hasSha256 = false;
                this.sha1 = WireFormatNano.EMPTY_BYTES;
                this.hasSha1 = false;
                this.md5 = WireFormatNano.EMPTY_BYTES;
                this.hasMd5 = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasSha256 || !Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(1, this.sha256);
                }
                if (this.hasSha1 || !Arrays.equals(this.sha1, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(2, this.sha1);
                }
                if (this.hasMd5 || !Arrays.equals(this.md5, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(3, this.md5);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasSha256 || !Arrays.equals(this.sha256, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(1, this.sha256);
                }
                if (this.hasSha1 || !Arrays.equals(this.sha1, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(2, this.sha1);
                }
                if (this.hasMd5 || !Arrays.equals(this.md5, WireFormatNano.EMPTY_BYTES)) {
                    return size + CodedOutputByteBufferNano.computeBytesSize(3, this.md5);
                }
                return size;
            }

            public Digests mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            this.sha256 = input.readBytes();
                            this.hasSha256 = true;
                            continue;
                        case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            this.sha1 = input.readBytes();
                            this.hasSha1 = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            this.md5 = input.readBytes();
                            this.hasMd5 = true;
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

        public static final class FileInfo extends MessageNano {
            private static volatile FileInfo[] _emptyArray;
            public Digests digests;
            public boolean hasName;
            public boolean hasVerificationErrors;
            public String name;
            public int verificationErrors;

            public static FileInfo[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new FileInfo[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public FileInfo() {
                clear();
            }

            public FileInfo clear() {
                this.name = "";
                this.hasName = false;
                this.digests = null;
                this.verificationErrors = 0;
                this.hasVerificationErrors = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasName || !this.name.equals("")) {
                    output.writeString(1, this.name);
                }
                if (this.digests != null) {
                    output.writeMessage(2, this.digests);
                }
                if (this.hasVerificationErrors || this.verificationErrors != 0) {
                    output.writeUInt32(3, this.verificationErrors);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasName || !this.name.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.name);
                }
                if (this.digests != null) {
                    size += CodedOutputByteBufferNano.computeMessageSize(2, this.digests);
                }
                if (this.hasVerificationErrors || this.verificationErrors != 0) {
                    return size + CodedOutputByteBufferNano.computeUInt32Size(3, this.verificationErrors);
                }
                return size;
            }

            public FileInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            if (this.digests == null) {
                                this.digests = new Digests();
                            }
                            input.readMessage(this.digests);
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                            this.verificationErrors = input.readUInt32();
                            this.hasVerificationErrors = true;
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

        public static final class Resource extends MessageNano {
            private static volatile Resource[] _emptyArray;
            public boolean hasReferrer;
            public boolean hasRemoteIp;
            public boolean hasType;
            public boolean hasUrl;
            public String referrer;
            public byte[] remoteIp;
            public int type;
            public String url;

            public static Resource[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Resource[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Resource() {
                clear();
            }

            public Resource clear() {
                this.url = "";
                this.hasUrl = false;
                this.type = 0;
                this.hasType = false;
                this.remoteIp = WireFormatNano.EMPTY_BYTES;
                this.hasRemoteIp = false;
                this.referrer = "";
                this.hasReferrer = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasUrl || !this.url.equals("")) {
                    output.writeString(1, this.url);
                }
                if (this.type != 0 || this.hasType) {
                    output.writeInt32(2, this.type);
                }
                if (this.hasRemoteIp || !Arrays.equals(this.remoteIp, WireFormatNano.EMPTY_BYTES)) {
                    output.writeBytes(3, this.remoteIp);
                }
                if (this.hasReferrer || !this.referrer.equals("")) {
                    output.writeString(4, this.referrer);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasUrl || !this.url.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
                }
                if (this.type != 0 || this.hasType) {
                    size += CodedOutputByteBufferNano.computeInt32Size(2, this.type);
                }
                if (this.hasRemoteIp || !Arrays.equals(this.remoteIp, WireFormatNano.EMPTY_BYTES)) {
                    size += CodedOutputByteBufferNano.computeBytesSize(3, this.remoteIp);
                }
                if (this.hasReferrer || !this.referrer.equals("")) {
                    return size + CodedOutputByteBufferNano.computeStringSize(4, this.referrer);
                }
                return size;
            }

            public Resource mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            this.url = input.readString();
                            this.hasUrl = true;
                            continue;
                        case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            int value = input.readInt32();
                            switch (value) {
                                case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                    this.type = value;
                                    this.hasType = true;
                                    break;
                                default:
                                    continue;
                            }
                        case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            this.remoteIp = input.readBytes();
                            this.hasRemoteIp = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                            this.referrer = input.readString();
                            this.hasReferrer = true;
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

        public static final class SignatureInfo extends MessageNano {
            public CertificateChain[] certificateChain;
            public boolean hasTrusted;
            public boolean trusted;

            public SignatureInfo() {
                clear();
            }

            public SignatureInfo clear() {
                this.certificateChain = CertificateChain.emptyArray();
                this.trusted = false;
                this.hasTrusted = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.certificateChain != null && this.certificateChain.length > 0) {
                    for (CertificateChain element : this.certificateChain) {
                        if (element != null) {
                            output.writeMessage(1, element);
                        }
                    }
                }
                if (this.hasTrusted || this.trusted) {
                    output.writeBool(2, this.trusted);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.certificateChain != null && this.certificateChain.length > 0) {
                    for (CertificateChain element : this.certificateChain) {
                        if (element != null) {
                            size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                        }
                    }
                }
                if (this.hasTrusted || this.trusted) {
                    return size + CodedOutputByteBufferNano.computeBoolSize(2, this.trusted);
                }
                return size;
            }

            public SignatureInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            int i;
                            int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                            if (this.certificateChain == null) {
                                i = 0;
                            } else {
                                i = this.certificateChain.length;
                            }
                            CertificateChain[] newArray = new CertificateChain[(i + arrayLength)];
                            if (i != 0) {
                                System.arraycopy(this.certificateChain, 0, newArray, 0, i);
                            }
                            while (i < newArray.length - 1) {
                                newArray[i] = new CertificateChain();
                                input.readMessage(newArray[i]);
                                input.readTag();
                                i++;
                            }
                            newArray[i] = new CertificateChain();
                            input.readMessage(newArray[i]);
                            this.certificateChain = newArray;
                            continue;
                        case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                            this.trusted = input.readBool();
                            this.hasTrusted = true;
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

        public static ClientDownloadRequest[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClientDownloadRequest[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClientDownloadRequest() {
            clear();
        }

        public ClientDownloadRequest clear() {
            this.url = "";
            this.hasUrl = false;
            this.digests = null;
            this.length = 0;
            this.hasLength = false;
            this.resources = Resource.emptyArray();
            this.signature = null;
            this.userInitiated = false;
            this.hasUserInitiated = false;
            this.clientAsn = WireFormatNano.EMPTY_STRING_ARRAY;
            this.fileBasename = "";
            this.hasFileBasename = false;
            this.downloadType = 0;
            this.hasDownloadType = false;
            this.locale = "";
            this.hasLocale = false;
            this.apkInfo = null;
            this.androidId = 0;
            this.hasAndroidId = false;
            this.originatingPackages = WireFormatNano.EMPTY_STRING_ARRAY;
            this.requestId = WireFormatNano.EMPTY_BYTES;
            this.hasRequestId = false;
            this.originatingSignature = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasUrl || !this.url.equals("")) {
                output.writeString(1, this.url);
            }
            if (this.digests != null) {
                output.writeMessage(2, this.digests);
            }
            if (this.hasLength || this.length != 0) {
                output.writeInt64(3, this.length);
            }
            if (this.resources != null && this.resources.length > 0) {
                for (Resource element : this.resources) {
                    if (element != null) {
                        output.writeMessage(4, element);
                    }
                }
            }
            if (this.signature != null) {
                output.writeMessage(5, this.signature);
            }
            if (this.hasUserInitiated || this.userInitiated) {
                output.writeBool(6, this.userInitiated);
            }
            if (this.clientAsn != null && this.clientAsn.length > 0) {
                for (String element2 : this.clientAsn) {
                    if (element2 != null) {
                        output.writeString(8, element2);
                    }
                }
            }
            if (this.hasFileBasename || !this.fileBasename.equals("")) {
                output.writeString(9, this.fileBasename);
            }
            if (this.downloadType != 0 || this.hasDownloadType) {
                output.writeInt32(10, this.downloadType);
            }
            if (this.hasLocale || !this.locale.equals("")) {
                output.writeString(11, this.locale);
            }
            if (this.apkInfo != null) {
                output.writeMessage(12, this.apkInfo);
            }
            if (this.hasAndroidId || this.androidId != 0) {
                output.writeFixed64(13, this.androidId);
            }
            if (this.originatingPackages != null && this.originatingPackages.length > 0) {
                for (String element22 : this.originatingPackages) {
                    if (element22 != null) {
                        output.writeString(15, element22);
                    }
                }
            }
            if (this.hasRequestId || !Arrays.equals(this.requestId, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(16, this.requestId);
            }
            if (this.originatingSignature != null) {
                output.writeMessage(17, this.originatingSignature);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.hasUrl || !this.url.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.url);
            }
            if (this.digests != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.digests);
            }
            if (this.hasLength || this.length != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(3, this.length);
            }
            if (this.resources != null && this.resources.length > 0) {
                for (Resource element : this.resources) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(4, element);
                    }
                }
            }
            if (this.signature != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(5, this.signature);
            }
            if (this.hasUserInitiated || this.userInitiated) {
                size += CodedOutputByteBufferNano.computeBoolSize(6, this.userInitiated);
            }
            if (this.clientAsn != null && this.clientAsn.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.clientAsn) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasFileBasename || !this.fileBasename.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(9, this.fileBasename);
            }
            if (this.downloadType != 0 || this.hasDownloadType) {
                size += CodedOutputByteBufferNano.computeInt32Size(10, this.downloadType);
            }
            if (this.hasLocale || !this.locale.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(11, this.locale);
            }
            if (this.apkInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(12, this.apkInfo);
            }
            if (this.hasAndroidId || this.androidId != 0) {
                size += CodedOutputByteBufferNano.computeFixed64Size(13, this.androidId);
            }
            if (this.originatingPackages != null && this.originatingPackages.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element22 : this.originatingPackages) {
                    if (element22 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element22);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.hasRequestId || !Arrays.equals(this.requestId, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(16, this.requestId);
            }
            if (this.originatingSignature != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(17, this.originatingSignature);
            }
            return size;
        }

        public ClientDownloadRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.url = input.readString();
                        this.hasUrl = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.digests == null) {
                            this.digests = new Digests();
                        }
                        input.readMessage(this.digests);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.length = input.readInt64();
                        this.hasLength = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        if (this.resources == null) {
                            i = 0;
                        } else {
                            i = this.resources.length;
                        }
                        Resource[] newArray2 = new Resource[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.resources, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Resource();
                            input.readMessage(newArray2[i]);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Resource();
                        input.readMessage(newArray2[i]);
                        this.resources = newArray2;
                        continue;
                    case com.google.android.play.R.styleable.Theme_spinnerDropDownItemStyle /*42*/:
                        if (this.signature == null) {
                            this.signature = new SignatureInfo();
                        }
                        input.readMessage(this.signature);
                        continue;
                    case com.google.android.play.R.styleable.Theme_selectableItemBackgroundBorderless /*48*/:
                        this.userInitiated = input.readBool();
                        this.hasUserInitiated = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_listPreferredItemPaddingLeft /*66*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 66);
                        i = this.clientAsn == null ? 0 : this.clientAsn.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.clientAsn, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.clientAsn = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_panelMenuListTheme /*74*/:
                        this.fileBasename = input.readString();
                        this.hasFileBasename = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_colorControlActivated /*80*/:
                        int value = input.readInt32();
                        switch (value) {
                            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                                this.downloadType = value;
                                this.hasDownloadType = true;
                                break;
                            default:
                                continue;
                        }
                    case 90:
                        this.locale = input.readString();
                        this.hasLocale = true;
                        continue;
                    case 98:
                        if (this.apkInfo == null) {
                            this.apkInfo = new ApkInfo();
                        }
                        input.readMessage(this.apkInfo);
                        continue;
                    case 105:
                        this.androidId = input.readFixed64();
                        this.hasAndroidId = true;
                        continue;
                    case 122:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 122);
                        i = this.originatingPackages == null ? 0 : this.originatingPackages.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.originatingPackages, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.originatingPackages = newArray;
                        continue;
                    case 130:
                        this.requestId = input.readBytes();
                        this.hasRequestId = true;
                        continue;
                    case 138:
                        if (this.originatingSignature == null) {
                            this.originatingSignature = new SignatureInfo();
                        }
                        input.readMessage(this.originatingSignature);
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

    public static final class ClientDownloadResponse extends MessageNano {
        private static volatile ClientDownloadResponse[] _emptyArray;
        public boolean hasRequestId;
        public boolean hasToken;
        public boolean hasUploadApk;
        public boolean hasVerdict;
        public MoreInfo moreInfo;
        public byte[] requestId;
        public byte[] token;
        public boolean uploadApk;
        public int verdict;

        public static final class MoreInfo extends MessageNano {
            public String description;
            public boolean hasDescription;
            public boolean hasUrl;
            public String url;

            public MoreInfo() {
                clear();
            }

            public MoreInfo clear() {
                this.description = "";
                this.hasDescription = false;
                this.url = "";
                this.hasUrl = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasDescription || !this.description.equals("")) {
                    output.writeString(1, this.description);
                }
                if (this.hasUrl || !this.url.equals("")) {
                    output.writeString(2, this.url);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasDescription || !this.description.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(1, this.description);
                }
                if (this.hasUrl || !this.url.equals("")) {
                    return size + CodedOutputByteBufferNano.computeStringSize(2, this.url);
                }
                return size;
            }

            public MoreInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            this.description = input.readString();
                            this.hasDescription = true;
                            continue;
                        case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                            this.url = input.readString();
                            this.hasUrl = true;
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

        public static ClientDownloadResponse[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new ClientDownloadResponse[0];
                    }
                }
            }
            return _emptyArray;
        }

        public ClientDownloadResponse() {
            clear();
        }

        public ClientDownloadResponse clear() {
            this.verdict = 0;
            this.hasVerdict = false;
            this.moreInfo = null;
            this.token = WireFormatNano.EMPTY_BYTES;
            this.hasToken = false;
            this.requestId = WireFormatNano.EMPTY_BYTES;
            this.hasRequestId = false;
            this.uploadApk = false;
            this.hasUploadApk = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.verdict != 0 || this.hasVerdict) {
                output.writeInt32(1, this.verdict);
            }
            if (this.moreInfo != null) {
                output.writeMessage(2, this.moreInfo);
            }
            if (this.hasToken || !Arrays.equals(this.token, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(3, this.token);
            }
            if (this.hasRequestId || !Arrays.equals(this.requestId, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(4, this.requestId);
            }
            if (this.hasUploadApk || this.uploadApk) {
                output.writeBool(5, this.uploadApk);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.verdict != 0 || this.hasVerdict) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.verdict);
            }
            if (this.moreInfo != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(2, this.moreInfo);
            }
            if (this.hasToken || !Arrays.equals(this.token, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(3, this.token);
            }
            if (this.hasRequestId || !Arrays.equals(this.requestId, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(4, this.requestId);
            }
            if (this.hasUploadApk || this.uploadApk) {
                return size + CodedOutputByteBufferNano.computeBoolSize(5, this.uploadApk);
            }
            return size;
        }

        public ClientDownloadResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                                this.verdict = value;
                                this.hasVerdict = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        if (this.moreInfo == null) {
                            this.moreInfo = new MoreInfo();
                        }
                        input.readMessage(this.moreInfo);
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.token = input.readBytes();
                        this.hasToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        this.requestId = input.readBytes();
                        this.hasRequestId = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.uploadApk = input.readBool();
                        this.hasUploadApk = true;
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

        public static ClientDownloadResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ClientDownloadResponse) MessageNano.mergeFrom(new ClientDownloadResponse(), data);
        }
    }

    public static final class ClientDownloadStatsRequest extends MessageNano {
        public boolean dontWarnAgain;
        public boolean hasDontWarnAgain;
        public boolean hasToken;
        public boolean hasUserDecision;
        public byte[] token;
        public int userDecision;

        public ClientDownloadStatsRequest() {
            clear();
        }

        public ClientDownloadStatsRequest clear() {
            this.userDecision = 0;
            this.hasUserDecision = false;
            this.token = WireFormatNano.EMPTY_BYTES;
            this.hasToken = false;
            this.dontWarnAgain = false;
            this.hasDontWarnAgain = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.userDecision != 0 || this.hasUserDecision) {
                output.writeInt32(1, this.userDecision);
            }
            if (this.hasToken || !Arrays.equals(this.token, WireFormatNano.EMPTY_BYTES)) {
                output.writeBytes(2, this.token);
            }
            if (this.hasDontWarnAgain || this.dontWarnAgain) {
                output.writeBool(3, this.dontWarnAgain);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.userDecision != 0 || this.hasUserDecision) {
                size += CodedOutputByteBufferNano.computeInt32Size(1, this.userDecision);
            }
            if (this.hasToken || !Arrays.equals(this.token, WireFormatNano.EMPTY_BYTES)) {
                size += CodedOutputByteBufferNano.computeBytesSize(2, this.token);
            }
            if (this.hasDontWarnAgain || this.dontWarnAgain) {
                return size + CodedOutputByteBufferNano.computeBoolSize(3, this.dontWarnAgain);
            }
            return size;
        }

        public ClientDownloadStatsRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                                this.userDecision = value;
                                this.hasUserDecision = true;
                                break;
                            default:
                                continue;
                        }
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.token = input.readBytes();
                        this.hasToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeStyle /*24*/:
                        this.dontWarnAgain = input.readBool();
                        this.hasDontWarnAgain = true;
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

    public static final class ClientMultiDownloadRequest extends MessageNano {
        public boolean hasUserConsented;
        public ClientDownloadRequest[] requests;
        public boolean userConsented;

        public ClientMultiDownloadRequest() {
            clear();
        }

        public ClientMultiDownloadRequest clear() {
            this.requests = ClientDownloadRequest.emptyArray();
            this.userConsented = false;
            this.hasUserConsented = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.requests != null && this.requests.length > 0) {
                for (ClientDownloadRequest element : this.requests) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.hasUserConsented || this.userConsented) {
                output.writeBool(2, this.userConsented);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.requests != null && this.requests.length > 0) {
                for (ClientDownloadRequest element : this.requests) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.hasUserConsented || this.userConsented) {
                return size + CodedOutputByteBufferNano.computeBoolSize(2, this.userConsented);
            }
            return size;
        }

        public ClientMultiDownloadRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.requests == null) {
                            i = 0;
                        } else {
                            i = this.requests.length;
                        }
                        ClientDownloadRequest[] newArray = new ClientDownloadRequest[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.requests, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ClientDownloadRequest();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ClientDownloadRequest();
                        input.readMessage(newArray[i]);
                        this.requests = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                        this.userConsented = input.readBool();
                        this.hasUserConsented = true;
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

    public static final class ClientMultiDownloadResponse extends MessageNano {
        public ClientDownloadResponse[] responses;

        public ClientMultiDownloadResponse() {
            clear();
        }

        public ClientMultiDownloadResponse clear() {
            this.responses = ClientDownloadResponse.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.responses != null && this.responses.length > 0) {
                for (ClientDownloadResponse element : this.responses) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.responses != null && this.responses.length > 0) {
                for (ClientDownloadResponse element : this.responses) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public ClientMultiDownloadResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.responses == null) {
                            i = 0;
                        } else {
                            i = this.responses.length;
                        }
                        ClientDownloadResponse[] newArray = new ClientDownloadResponse[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.responses, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ClientDownloadResponse();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ClientDownloadResponse();
                        input.readMessage(newArray[i]);
                        this.responses = newArray;
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

        public static ClientMultiDownloadResponse parseFrom(byte[] data) throws InvalidProtocolBufferNanoException {
            return (ClientMultiDownloadResponse) MessageNano.mergeFrom(new ClientMultiDownloadResponse(), data);
        }
    }
}
