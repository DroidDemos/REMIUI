package com.google.android.finsky.protos;

import com.google.android.wallet.instrumentmanager.R;
import com.google.protobuf.nano.CodedInputByteBufferNano;
import com.google.protobuf.nano.CodedOutputByteBufferNano;
import com.google.protobuf.nano.InternalNano;
import com.google.protobuf.nano.MessageNano;
import com.google.protobuf.nano.WireFormatNano;
import java.io.IOException;

public interface DebugInfoProto {

    public static final class DebugInfo extends MessageNano {
        public String[] message;
        public Timing[] timing;

        public static final class Timing extends MessageNano {
            private static volatile Timing[] _emptyArray;
            public boolean hasName;
            public boolean hasTimeInMs;
            public String name;
            public double timeInMs;

            public static Timing[] emptyArray() {
                if (_emptyArray == null) {
                    synchronized (InternalNano.LAZY_INIT_LOCK) {
                        if (_emptyArray == null) {
                            _emptyArray = new Timing[0];
                        }
                    }
                }
                return _emptyArray;
            }

            public Timing() {
                clear();
            }

            public Timing clear() {
                this.name = "";
                this.hasName = false;
                this.timeInMs = 0.0d;
                this.hasTimeInMs = false;
                this.cachedSize = -1;
                return this;
            }

            public void writeTo(CodedOutputByteBufferNano output) throws IOException {
                if (this.hasName || !this.name.equals("")) {
                    output.writeString(3, this.name);
                }
                if (this.hasTimeInMs || Double.doubleToLongBits(this.timeInMs) != Double.doubleToLongBits(0.0d)) {
                    output.writeDouble(4, this.timeInMs);
                }
                super.writeTo(output);
            }

            protected int computeSerializedSize() {
                int size = super.computeSerializedSize();
                if (this.hasName || !this.name.equals("")) {
                    size += CodedOutputByteBufferNano.computeStringSize(3, this.name);
                }
                if (this.hasTimeInMs || Double.doubleToLongBits(this.timeInMs) != Double.doubleToLongBits(0.0d)) {
                    return size + CodedOutputByteBufferNano.computeDoubleSize(4, this.timeInMs);
                }
                return size;
            }

            public Timing mergeFrom(CodedInputByteBufferNano input) throws IOException {
                while (true) {
                    int tag = input.readTag();
                    switch (tag) {
                        case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                            break;
                        case com.google.android.play.R.styleable.Theme_actionModeBackground /*26*/:
                            this.name = input.readString();
                            this.hasName = true;
                            continue;
                        case com.google.android.play.R.styleable.Theme_actionModeShareDrawable /*33*/:
                            this.timeInMs = input.readDouble();
                            this.hasTimeInMs = true;
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

        public DebugInfo() {
            clear();
        }

        public DebugInfo clear() {
            this.message = WireFormatNano.EMPTY_STRING_ARRAY;
            this.timing = Timing.emptyArray();
            this.cachedSize = -1;
            return this;
        }

        public void writeTo(CodedOutputByteBufferNano output) throws IOException {
            if (this.message != null && this.message.length > 0) {
                for (String element : this.message) {
                    if (element != null) {
                        output.writeString(1, element);
                    }
                }
            }
            if (this.timing != null && this.timing.length > 0) {
                for (Timing element2 : this.timing) {
                    if (element2 != null) {
                        output.writeGroup(2, element2);
                    }
                }
            }
            super.writeTo(output);
        }

        protected int computeSerializedSize() {
            int size = super.computeSerializedSize();
            if (this.message != null && this.message.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.message) {
                    if (element != null) {
                        dataCount++;
                        dataSize += CodedOutputByteBufferNano.computeStringSizeNoTag(element);
                    }
                }
                size = (size + dataSize) + (dataCount * 1);
            }
            if (this.timing != null && this.timing.length > 0) {
                for (Timing element2 : this.timing) {
                    if (element2 != null) {
                        size += CodedOutputByteBufferNano.computeGroupSize(2, element2);
                    }
                }
            }
            return size;
        }

        public DebugInfo mergeFrom(CodedInputByteBufferNano input) throws IOException {
            while (true) {
                int tag = input.readTag();
                int arrayLength;
                int i;
                switch (tag) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 10);
                        i = this.message == null ? 0 : this.message.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.message, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.readString();
                            input.readTag();
                            i++;
                        }
                        newArray[i] = input.readString();
                        this.message = newArray;
                        continue;
                    case com.google.android.play.R.styleable.Toolbar_collapseContentDescription /*19*/:
                        arrayLength = WireFormatNano.getRepeatedFieldArrayLength(input, 19);
                        if (this.timing == null) {
                            i = 0;
                        } else {
                            i = this.timing.length;
                        }
                        Timing[] newArray2 = new Timing[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.timing, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new Timing();
                            input.readGroup(newArray2[i], 2);
                            input.readTag();
                            i++;
                        }
                        newArray2[i] = new Timing();
                        input.readGroup(newArray2[i], 2);
                        this.timing = newArray2;
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
