package android.support.v4.a;

/* compiled from: SparseArrayCompat */
public class k implements Cloneable {
    private static final Object GF;
    private boolean GG;
    private int[] GH;
    private Object[] GI;
    private int mSize;

    public /* bridge */ /* synthetic */ Object clone() {
        return hF();
    }

    static {
        GF = new Object();
    }

    public k() {
        this(10);
    }

    public k(int i) {
        this.GG = false;
        if (i == 0) {
            this.GH = h.EMPTY_INTS;
            this.GI = h.EMPTY_OBJECTS;
        } else {
            int idealIntArraySize = h.idealIntArraySize(i);
            this.GH = new int[idealIntArraySize];
            this.GI = new Object[idealIntArraySize];
        }
        this.mSize = 0;
    }

    public k hF() {
        try {
            k kVar = (k) super.clone();
            try {
                kVar.GH = (int[]) this.GH.clone();
                kVar.GI = (Object[]) this.GI.clone();
                return kVar;
            } catch (CloneNotSupportedException e) {
                return kVar;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    private void gc() {
        int i = this.mSize;
        int[] iArr = this.GH;
        Object[] objArr = this.GI;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != GF) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.GG = false;
        this.mSize = i2;
    }

    public int size() {
        if (this.GG) {
            gc();
        }
        return this.mSize;
    }

    public int keyAt(int i) {
        if (this.GG) {
            gc();
        }
        return this.GH[i];
    }

    public Object valueAt(int i) {
        if (this.GG) {
            gc();
        }
        return this.GI[i];
    }

    public void clear() {
        int i = this.mSize;
        Object[] objArr = this.GI;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.mSize = 0;
        this.GG = false;
    }

    public String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.mSize * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.mSize; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(keyAt(i));
            stringBuilder.append('=');
            k valueAt = valueAt(i);
            if (valueAt != this) {
                stringBuilder.append(valueAt);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
