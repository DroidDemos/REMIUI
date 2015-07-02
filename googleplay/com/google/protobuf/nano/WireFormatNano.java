package com.google.protobuf.nano;

import java.io.IOException;

public final class WireFormatNano {
    public static final boolean[] EMPTY_BOOLEAN_ARRAY;
    public static final byte[] EMPTY_BYTES;
    public static final byte[][] EMPTY_BYTES_ARRAY;
    public static final double[] EMPTY_DOUBLE_ARRAY;
    public static final float[] EMPTY_FLOAT_ARRAY;
    public static final int[] EMPTY_INT_ARRAY;
    public static final long[] EMPTY_LONG_ARRAY;
    public static final String[] EMPTY_STRING_ARRAY;

    static int getTagWireType(int tag) {
        return tag & 7;
    }

    public static int getTagFieldNumber(int tag) {
        return tag >>> 3;
    }

    static int makeTag(int fieldNumber, int wireType) {
        return (fieldNumber << 3) | wireType;
    }

    static {
        EMPTY_INT_ARRAY = new int[0];
        EMPTY_LONG_ARRAY = new long[0];
        EMPTY_FLOAT_ARRAY = new float[0];
        EMPTY_DOUBLE_ARRAY = new double[0];
        EMPTY_BOOLEAN_ARRAY = new boolean[0];
        EMPTY_STRING_ARRAY = new String[0];
        EMPTY_BYTES_ARRAY = new byte[0][];
        EMPTY_BYTES = new byte[0];
    }

    public static boolean parseUnknownField(CodedInputByteBufferNano input, int tag) throws IOException {
        return input.skipField(tag);
    }

    public static final int getRepeatedFieldArrayLength(CodedInputByteBufferNano input, int tag) throws IOException {
        int arrayLength = 1;
        int startPos = input.getPosition();
        input.skipField(tag);
        while (input.getBytesUntilLimit() > 0 && input.readTag() == tag) {
            input.skipField(tag);
            arrayLength++;
        }
        input.rewindToPosition(startPos);
        return arrayLength;
    }
}
