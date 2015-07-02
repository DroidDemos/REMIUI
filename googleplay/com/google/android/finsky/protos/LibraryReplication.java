package com.google.android.finsky.protos;

import com.google.android.finsky.protos.LibraryUpdateProto.ClientLibraryState;
import com.google.android.finsky.protos.LibraryUpdateProto.LibraryUpdate;
import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface LibraryReplication {

    public static final class LibraryReplicationRequest extends MessageNano {
        public ClientLibraryState[] libraryState;

        public LibraryReplicationRequest() {
            clear();
        }

        public LibraryReplicationRequest clear() {
            this.libraryState = ClientLibraryState.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.libraryState != null && this.libraryState.length > 0) {
                for (ClientLibraryState element : this.libraryState) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.libraryState != null && this.libraryState.length > 0) {
                for (ClientLibraryState element : this.libraryState) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            return size;
        }

        public LibraryReplicationRequest mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int i;
                        int arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.libraryState == null) {
                            i = 0;
                        } else {
                            i = this.libraryState.length;
                        }
                        ClientLibraryState[] newArray = new ClientLibraryState[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.libraryState, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new ClientLibraryState();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new ClientLibraryState();
                        input.readMessage(newArray[i]);
                        this.libraryState = newArray;
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

    public static final class LibraryReplicationResponse extends MessageNano {
        public String[] autoAcquireFreeAppIfHigherVersionAvailableTag;
        public LibraryUpdate[] update;

        public LibraryReplicationResponse() {
            clear();
        }

        public LibraryReplicationResponse clear() {
            this.update = LibraryUpdate.emptyArray();
            this.autoAcquireFreeAppIfHigherVersionAvailableTag = WireFormatNano.EMPTY_STRING_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.update != null && this.update.length > 0) {
                for (LibraryUpdate element : this.update) {
                    if (element != null) {
                        output.writeMessage(1, element);
                    }
                }
            }
            if (this.autoAcquireFreeAppIfHigherVersionAvailableTag != null && this.autoAcquireFreeAppIfHigherVersionAvailableTag.length > 0) {
                for (String element2 : this.autoAcquireFreeAppIfHigherVersionAvailableTag) {
                    if (element2 != null) {
                        output.writeString(2, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.update != null && this.update.length > 0) {
                for (LibraryUpdate element : this.update) {
                    if (element != null) {
                        size += CodedOutputByteBufferNano.computeMessageSize(1, element);
                    }
                }
            }
            if (this.autoAcquireFreeAppIfHigherVersionAvailableTag == null || this.autoAcquireFreeAppIfHigherVersionAvailableTag.length <= 0) {
                return size;
            }
            int dataCount = 0;
            int dataSize = 0;
            for (String element2 : this.autoAcquireFreeAppIfHigherVersionAvailableTag) {
                if (element2 != null) {
                    dataCount++;
                    dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element2);
                }
            }
            return (size + dataSize) + (dataCount * 1);
        }

        public LibraryReplicationResponse mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        if (this.update == null) {
                            i = 0;
                        } else {
                            i = this.update.length;
                        }
                        LibraryUpdate[] newArray = new LibraryUpdate[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.update, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new LibraryUpdate();
                            input.readMessage(newArray[i]);
                            input.readTag();
                            i++;
                        }
                        newArray[i] = new LibraryUpdate();
                        input.readMessage(newArray[i]);
                        this.update = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseIcon /*18*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 18);
                        i = this.autoAcquireFreeAppIfHigherVersionAvailableTag == null ? 0 : this.autoAcquireFreeAppIfHigherVersionAvailableTag.length;
                        String[] newArray2 = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.autoAcquireFreeAppIfHigherVersionAvailableTag, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = input.readString();
                        this.autoAcquireFreeAppIfHigherVersionAvailableTag = newArray2;
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
