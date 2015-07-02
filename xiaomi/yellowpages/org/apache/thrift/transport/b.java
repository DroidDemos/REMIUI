package org.apache.thrift.transport;

import org.apache.thrift.a;

public class b extends c {
    private a Ej;
    private int b;

    public b(a aVar) {
        this.Ej = aVar;
    }

    public int a(byte[] bArr, int i, int i2) {
        Object ag = this.Ej.ag();
        if (i2 > this.Ej.ah() - this.b) {
            i2 = this.Ej.ah() - this.b;
        }
        if (i2 > 0) {
            System.arraycopy(ag, this.b, bArr, i, i2);
            this.b += i2;
        }
        return i2;
    }

    public void e(byte[] bArr, int i, int i2) {
        this.Ej.write(bArr, i, i2);
    }
}
