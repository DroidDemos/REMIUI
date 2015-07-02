package com.google.android.finsky.protos;

import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface ModifyLibrary {

    public static final class ModifyLibraryRequest extends MessageNano {
        public String[] forAddDocid;
        public String[] forArchiveDocid;
        public String[] forRemovalDocid;
        public boolean hasLibraryId;
        public String libraryId;

        public ModifyLibraryRequest() {
            clear();
        }

        public ModifyLibraryRequest clear() {
            this.libraryId = "";
            this.hasLibraryId = false;
            this.forAddDocid = WireFormatNano.EMPTY_STRING_ARRAY;
            this.forRemovalDocid = WireFormatNano.EMPTY_STRING_ARRAY;
            this.forArchiveDocid = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.hasLibraryId || !this.libraryId.equals("")) {
                output.writeString(1, this.libraryId);
            }
            if (this.forAddDocid != null && this.forAddDocid.length > 0) {
                for (String element : this.forAddDocid) {
                    if (element != null) {
                        output.writeString(2, element);
                    }
                }
            }
            if (this.forRemovalDocid != null && this.forRemovalDocid.length > 0) {
                for (String element2 : this.forRemovalDocid) {
                    if (element2 != null) {
                        output.writeString(3, element2);
                    }
                }
            }
            if (this.forArchiveDocid != null && this.forArchiveDocid.length > 0) {
                for (String element22 : this.forArchiveDocid) {
                    if (element22 != null) {
                        output.writeString(4, element22);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int dataCount;
            int dataSize;
            int size = super.computeSerializedSize();
            if (this.hasLibraryId || !this.libraryId.equals("")) {
                size += CodedOutputByteBufferNano.computeStringSize(1, this.libraryId);
            }
            if (this.forAddDocid != null && this.forAddDocid.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element : this.forAddDocid) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.forRemovalDocid != null && this.forRemovalDocid.length > 0) {
                dataCount = 0;
                dataSize = 0;
                for (String element2 : this.forRemovalDocid) {
                    if (element2 != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.forArchiveDocid == null || this.forArchiveDocid.length <= 0) {
                return size;
            }
            dataCount = 0;
            dataSize = 0;
            for (String element22 : this.forArchiveDocid) {
                if (element22 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element22);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public ModifyLibraryRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                String[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        this.libraryId = input.readString();
                        this.hasLibraryId = true;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        i = this.forAddDocid == null ? 0 : this.forAddDocid.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.forAddDocid, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.forAddDocid = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 26);
                        i = this.forRemovalDocid == null ? 0 : this.forRemovalDocid.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.forRemovalDocid, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.forRemovalDocid = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Theme_actionModeFindDrawable /*34*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 34);
                        i = this.forArchiveDocid == null ? 0 : this.forArchiveDocid.length;
                        newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.forArchiveDocid, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.forArchiveDocid = newArray;
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

    public static final class ModifyLibraryResponse extends MessageNano {
        public LibraryUpdate libraryUpdate;

        public ModifyLibraryResponse() {
            clear();
        }

        public ModifyLibraryResponse clear() {
            this.libraryUpdate = null;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.libraryUpdate != null) {
                output.writeMessage(1, this.libraryUpdate);
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.libraryUpdate != null) {
                return size + CodedOutputByteBufferNano.computeMessageSize(1, this.libraryUpdate);
            }
            return size;
        }

        public ModifyLibraryResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        if (this.libraryUpdate == null) {
                            this.libraryUpdate = new LibraryUpdate();
                        }
                        input.readMessage(this.libraryUpdate);
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
