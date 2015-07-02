package org.keyczar.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.keyczar.exceptions.Base64DecodingException;
import org.keyczar.exceptions.KeyczarException;
import org.keyczar.interfaces.KeyType;
import org.keyczar.interfaces.KeyType.KeyTypeDeserializer;
import org.keyczar.interfaces.KeyType.KeyTypeSerializer;

public class Util {
    private static final ConcurrentLinkedQueue<MessageDigest> DIGEST_QUEUE;
    private static final ThreadLocal<Gson> GSON_THREAD_LOCAL;
    private static final ConcurrentLinkedQueue<SecureRandom> RAND_QUEUE;

    static {
        DIGEST_QUEUE = new ConcurrentLinkedQueue();
        RAND_QUEUE = new ConcurrentLinkedQueue();
        GSON_THREAD_LOCAL = new ThreadLocal<Gson>() {
            protected Gson initialValue() {
                return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(KeyType.class, new KeyTypeSerializer()).registerTypeAdapter(KeyType.class, new KeyTypeDeserializer()).create();
            }
        };
    }

    public static Gson gson() {
        return (Gson) GSON_THREAD_LOCAL.get();
    }

    public static byte[] stripLeadingZeros(byte[] input) {
        int zeros = 0;
        while (zeros < input.length && input[zeros] == (byte) 0) {
            zeros++;
        }
        if (zeros == 0) {
            return input;
        }
        byte[] output = new byte[(input.length - zeros)];
        System.arraycopy(input, zeros, output, 0, output.length);
        return output;
    }

    public static byte[] fromInt(int input) {
        byte[] output = new byte[4];
        writeInt(input, output, 0);
        return output;
    }

    public static byte[] prefixHash(byte[]... inputs) throws KeyczarException {
        MessageDigest md = (MessageDigest) DIGEST_QUEUE.poll();
        if (md == null) {
            try {
                md = MessageDigest.getInstance("SHA-1");
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }
        for (byte[] array : inputs) {
            md.update(fromInt(array.length));
            md.update(array);
        }
        byte[] digest = md.digest();
        DIGEST_QUEUE.add(md);
        return digest;
    }

    public static byte[] lenPrefix(byte[] data) {
        if (data == null || data.length == 0) {
            return fromInt(0);
        }
        return ByteBuffer.allocate(data.length + 4).putInt(data.length).put(data).array();
    }

    public static byte[] hash(byte[]... inputs) throws KeyczarException {
        MessageDigest md = (MessageDigest) DIGEST_QUEUE.poll();
        if (md == null) {
            try {
                md = MessageDigest.getInstance("SHA-1");
            } catch (Throwable e) {
                throw new KeyczarException(e);
            }
        }
        for (byte[] array : inputs) {
            md.update(array);
        }
        byte[] digest = md.digest();
        DIGEST_QUEUE.add(md);
        return digest;
    }

    public static void rand(byte[] dest) {
        SecureRandom random = (SecureRandom) RAND_QUEUE.poll();
        if (random == null) {
            random = new SecureRandom();
        }
        random.nextBytes(dest);
        RAND_QUEUE.add(random);
    }

    public static byte[] rand(int len) {
        byte[] output = new byte[len];
        rand(output);
        return output;
    }

    static int readInt(byte[] src, int offset) {
        int offset2 = offset + 1;
        offset = offset2 + 1;
        offset2 = offset + 1;
        offset = offset2 + 1;
        return (((0 | ((src[offset] & 255) << 24)) | ((src[offset2] & 255) << 16)) | ((src[offset] & 255) << 8)) | (src[offset2] & 255);
    }

    public static int toInt(byte[] src) {
        return readInt(src, 0);
    }

    static void writeInt(int input, byte[] dest, int offset) {
        int i = offset + 1;
        dest[offset] = (byte) (input >> 24);
        offset = i + 1;
        dest[i] = (byte) (input >> 16);
        i = offset + 1;
        dest[offset] = (byte) (input >> 8);
        offset = i + 1;
        dest[i] = (byte) input;
    }

    public static boolean safeArrayEquals(byte[] a1, byte[] a2) {
        if (a1 == null || a2 == null) {
            if (a1 == a2) {
                return true;
            }
            return false;
        } else if (a1.length != a2.length) {
            return false;
        } else {
            byte result = (byte) 0;
            for (int i = 0; i < a1.length; i++) {
                result = (byte) ((a1[i] ^ a2[i]) | result);
            }
            if (result != (byte) 0) {
                return false;
            }
            return true;
        }
    }

    public static byte[] cat(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (byte[] array2 : arrays) {
            System.arraycopy(array2, 0, result, pos, array2.length);
            pos += array2.length;
        }
        return result;
    }

    public static BigInteger decodeBigInteger(String value) throws Base64DecodingException {
        return new BigInteger(Base64Coder.decodeWebSafe(value));
    }
}
