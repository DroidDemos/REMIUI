package org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import org.apache.thrift.transport.c;

public abstract class b {
    protected c lv;

    private b() {
    }

    protected b(c cVar) {
        this.lv = cVar;
    }

    public abstract void a();

    public abstract void a(int i);

    public abstract void a(long j);

    public abstract void a(String str);

    public abstract void a(a aVar);

    public abstract void a(d dVar);

    public abstract void a(e eVar);

    public abstract void a(f fVar);

    public abstract void a(g gVar);

    public abstract void a(boolean z);

    public abstract void am();

    public abstract void ao();

    public abstract void b();

    public abstract int bA();

    public abstract long bB();

    public abstract double bC();

    public abstract String bD();

    public abstract ByteBuffer bE();

    public void bF() {
    }

    public abstract f bq();

    public abstract e br();

    public abstract d bs();

    public abstract g bt();

    public abstract void bu();

    public abstract a bv();

    public abstract void bw();

    public abstract boolean bx();

    public abstract byte by();

    public abstract short bz();

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract void f(ByteBuffer byteBuffer);

    public abstract void j();
}
