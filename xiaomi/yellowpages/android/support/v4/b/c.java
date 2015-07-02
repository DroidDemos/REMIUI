package android.support.v4.b;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;

/* compiled from: EdgeEffectCompat */
public class c {
    private static final b Dx;
    private Object Dw;

    static {
        if (VERSION.SDK_INT >= 14) {
            Dx = new a();
        } else {
            Dx = new e();
        }
    }

    public c(Context context) {
        this.Dw = Dx.o(context);
    }

    public void setSize(int i, int i2) {
        Dx.a(this.Dw, i, i2);
    }

    public boolean isFinished() {
        return Dx.d(this.Dw);
    }

    public void finish() {
        Dx.e(this.Dw);
    }

    public boolean e(float f) {
        return Dx.a(this.Dw, f);
    }

    public boolean ha() {
        return Dx.f(this.Dw);
    }

    public boolean draw(Canvas canvas) {
        return Dx.a(this.Dw, canvas);
    }
}
