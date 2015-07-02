package com.google.android.finsky.protos;

import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Restore {

    public static final class BackupDeviceInfo extends MessageNano {
        private static volatile BackupDeviceInfo[] _emptyArray;
        public long androidId;
        public boolean hasAndroidId;
        public boolean hasLastCheckinTimeMs;
        public boolean hasName;
        public boolean hasNumDocuments;
        public boolean hasRestoreToken;
        public long lastCheckinTimeMs;
        public String name;
        public int numDocuments;
        public String restoreToken;

        public static BackupDeviceInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BackupDeviceInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BackupDeviceInfo() {
            clear();
        }

        public BackupDeviceInfo clear() {
            this.androidId = 0;
            this.hasAndroidId = false;
            this.name = "";
            this.hasName = false;
            this.restoreToken = "";
            this.hasRestoreToken = false;
            this.lastCheckinTimeMs = 0;
            this.hasLastCheckinTimeMs = false;
            this.numDocuments = 0;
            this.hasNumDocuments = false;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasAndroidId || this.androidId != 0) {
                output.writeInt64(1, this.androidId);
            }
            if (this.hasName || !this.name.equals("")) {
                output.writeString(2, this.name);
            }
            if (this.hasRestoreToken || !this.restoreToken.equals("")) {
                output.writeString(3, this.restoreToken);
            }
            if (this.hasNumDocuments || this.numDocuments != 0) {
                output.writeInt32(4, this.numDocuments);
            }
            if (this.hasLastCheckinTimeMs || this.lastCheckinTimeMs != 0) {
                output.writeUInt64(5, this.lastCheckinTimeMs);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.hasAndroidId || this.androidId != 0) {
                size += CodedOutputByteBufferNano.computeInt64Size(1, this.androidId);
            }
            if (this.hasName || !this.name.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(2, this.name);
            }
            if (this.hasRestoreToken || !this.restoreToken.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(3, this.restoreToken);
            }
            if (this.hasNumDocuments || this.numDocuments != 0) {
                size += CodedOutputByteBufferNano.computeInt32Size(4, this.numDocuments);
            }
            if (this.hasLastCheckinTimeMs || this.lastCheckinTimeMs != 0) {
                return size + CodedOutputByteBufferNano.computeUInt64Size(5, this.lastCheckinTimeMs);
            }
            return size;
        }

        public BackupDeviceInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        this.androidId = input.readInt64();
                        this.hasAndroidId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        this.name = input.readString();
                        this.hasName = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        this.restoreToken = input.readString();
                        this.hasRestoreToken = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeSelectAllDrawable /*32*/:
                        this.numDocuments = input.readInt32();
                        this.hasNumDocuments = true;
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.lastCheckinTimeMs = input.readUInt64();
                        this.hasLastCheckinTimeMs = true;
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

    public static final class BackupDocumentInfo extends MessageNano {
        private static volatile BackupDocumentInfo[] _emptyArray;
        public Docid docid;
        public boolean hasRestorePriority;
        public boolean hasTitle;
        public boolean hasVersionCode;
        public int restorePriority;
        public Image thumbnailImage;
        public String title;
        public int versionCode;

        public static BackupDocumentInfo[] emptyArray() {
            if (_emptyArray == null) {
                synchronized (InternalNano.LAZY_INIT_LOCK) {
                    if (_emptyArray == null) {
                        _emptyArray = new BackupDocumentInfo[0];
                    }
                }
            }
            return _emptyArray;
        }

        public BackupDocumentInfo() {
            clear();
        }

        public BackupDocumentInfo clear() {
            this.docid = null;
            this.title = "";
            this.hasTitle = false;
            this.versionCode = 0;
            this.hasVersionCode = false;
            this.thumbnailImage = null;
            this.restorePriority = 0;
            this.hasRestorePriority = false;
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
            if (this.thumbnailImage != null) {
                output.writeMessage(4, this.thumbnailImage);
            }
            if (this.hasRestorePriority || this.restorePriority != 0) {
                output.writeInt32(5, this.restorePriority);
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
            if (this.thumbnailImage != null) {
                size += CodedOutputByteBufferNano.computeMessageSize(4, this.thumbnailImage);
            }
            if (this.hasRestorePriority || this.restorePriority != 0) {
                return size + CodedOutputByteBufferNano.computeInt32Size(5, this.restorePriority);
            }
            return size;
        }

        public BackupDocumentInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
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
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        if (this.thumbnailImage == null) {
                            this.thumbnailImage = new Image();
                        }
                        input.readMessage(this.thumbnailImage);
                        continue;
                    case com.google.android.play.R.styleable.Theme_dropdownListPreferredItemHeight /*40*/:
                        this.restorePriority = input.readInt32();
                        this.hasRestorePriority = true;
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

    public static final class GetBackupDeviceChoicesResponse extends MessageNano {
        public BackupDeviceInfo[] backupDeviceInfo;

        public GetBackupDeviceChoicesResponse() {
            clear();
        }

        public GetBackupDeviceChoicesResponse clear() {
            this.backupDeviceInfo = BackupDeviceInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.backupDeviceInfo != null && this.backupDeviceInfo.length > 0) {
                for (BackupDeviceInfo element : this.backupDeviceInfo) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.backupDeviceInfo != null && this.backupDeviceInfo.length > 0) {
                for (BackupDeviceInfo element : this.backupDeviceInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public GetBackupDeviceChoicesResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.backupDeviceInfo == null) {
                            i = 0;
                        } else {
                            i = this.backupDeviceInfo.length;
                        }
                        BackupDeviceInfo[] newArray = new BackupDeviceInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.backupDeviceInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new BackupDeviceInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new BackupDeviceInfo();
                        input.readMessage(newArray[i]);
                        this.backupDeviceInfo = newArray;
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

    public static final class GetBackupDocumentChoicesResponse extends MessageNano {
        public BackupDocumentInfo[] backupDocumentInfo;

        public GetBackupDocumentChoicesResponse() {
            clear();
        }

        public GetBackupDocumentChoicesResponse clear() {
            this.backupDocumentInfo = BackupDocumentInfo.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.backupDocumentInfo != null && this.backupDocumentInfo.length > 0) {
                for (BackupDocumentInfo element : this.backupDocumentInfo) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.backupDocumentInfo != null && this.backupDocumentInfo.length > 0) {
                for (BackupDocumentInfo element : this.backupDocumentInfo) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public GetBackupDocumentChoicesResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.backupDocumentInfo == null) {
                            i = 0;
                        } else {
                            i = this.backupDocumentInfo.length;
                        }
                        BackupDocumentInfo[] newArray = new BackupDocumentInfo[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.backupDocumentInfo, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new BackupDocumentInfo();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new BackupDocumentInfo();
                        input.readMessage(newArray[i]);
                        this.backupDocumentInfo = newArray;
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
