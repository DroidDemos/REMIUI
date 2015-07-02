package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface Targeting {

    public static final class Targets extends MessageNano {
        public long[] targetId;

        public Targets() {
            clear();
        }

        public Targets clear() {
            this.targetId = WireFormatNano.EMPTY_LONG_ARRAY;
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.targetId != null && this.targetId.length > 0) {
                for (long writeInt64 : this.targetId) {
                    output.writeInt64(1, writeInt64);
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.targetId == null || this.targetId.length <= 0) {
                return size;
            }
            int dataSize = 0;
            for (long element : this.targetId) {
                dataSize += CodedOutputByteBufferNano.computeInt64SizeNoTag(element);
            }
            return (size + dataSize) + (this.targetId.length * 1);
        }

        public Targets mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                long[] newArray;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 8);
                        i = this.targetId == null ? 0 : this.targetId.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.targetId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readInt64();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readInt64();
                        this.targetId = newArray;
                        continue;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        int limit = input.pushLimit(input.readRawVarint32());
                        arrayLength = 0;
                        int startPos = input.getPosition();
                        while (input.getBytesUntilLimit() > 0) {
                            input.readInt64();
                            arrayLength++;
                        }
                        input.rewindToPosition(startPos);
                        i = this.targetId == null ? 0 : this.targetId.length;
                        newArray = new long[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.targetId, 0, newArray, 0, i);
                        }
                        while (i < newArray.length) {
                            newArray[i] = input.readInt64();
                            i++;
                        }
                        this.targetId = newArray;
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
}
