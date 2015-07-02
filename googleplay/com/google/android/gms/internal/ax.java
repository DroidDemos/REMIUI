package com.google.android.gms.internal;

import java.util.PriorityQueue;

public class ax {

    public static class a {
        final String oc;
        final long value;

        a(long j, String str) {
            this.value = j;
            this.oc = str;
        }

        public boolean equals(Object other) {
            return other != null && (other instanceof a) && ((a) other).value == this.value;
        }

        public int hashCode() {
            return (int) this.value;
        }
    }

    static long a(int i, int i2, long j, long j2, long j3) {
        return ((((((j + 1073807359) - ((((((long) i) + 2147483647L) % 1073807359) * j2) % 1073807359)) % 1073807359) * j3) % 1073807359) + ((((long) i2) + 2147483647L) % 1073807359)) % 1073807359;
    }

    static long a(long j, int i) {
        return i == 0 ? 1 : i != 1 ? i % 2 == 0 ? a((j * j) % 1073807359, i / 2) % 1073807359 : ((a((j * j) % 1073807359, i / 2) % 1073807359) * j) % 1073807359 : j;
    }

    static String a(String[] strArr, int i, int i2) {
        if (strArr.length < i + i2) {
            gw.e("Unable to construct shingle");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 < (i + i2) - 1; i3++) {
            stringBuffer.append(strArr[i3]);
            stringBuffer.append(' ');
        }
        stringBuffer.append(strArr[(i + i2) - 1]);
        return stringBuffer.toString();
    }

    static void a(int i, long j, String str, PriorityQueue<a> priorityQueue) {
        a aVar = new a(j, str);
        if ((priorityQueue.size() != i || ((a) priorityQueue.peek()).value <= aVar.value) && !priorityQueue.contains(aVar)) {
            priorityQueue.add(aVar);
            if (priorityQueue.size() > i) {
                priorityQueue.poll();
            }
        }
    }

    public static void a(String[] strArr, int i, int i2, PriorityQueue<a> priorityQueue) {
        long b = b(strArr, 0, i2);
        a(i, b, a(strArr, 0, i2), (PriorityQueue) priorityQueue);
        long a = a(16785407, i2 - 1);
        for (int i3 = 1; i3 < (strArr.length - i2) + 1; i3++) {
            b = a(av.r(strArr[i3 - 1]), av.r(strArr[(i3 + i2) - 1]), b, a, 16785407);
            a(i, b, a(strArr, i3, i2), (PriorityQueue) priorityQueue);
        }
    }

    private static long b(String[] strArr, int i, int i2) {
        long r = (((long) av.r(strArr[i])) + 2147483647L) % 1073807359;
        for (int i3 = i + 1; i3 < i + i2; i3++) {
            r = (((r * 16785407) % 1073807359) + ((((long) av.r(strArr[i3])) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return r;
    }
}
