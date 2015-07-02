package org.apache.thrift;

public class e {
    private short[] DC;
    private int b;

    public e(int i) {
        this.b = -1;
        this.DC = new short[i];
    }

    private void c() {
        Object obj = new short[(this.DC.length * 2)];
        System.arraycopy(this.DC, 0, obj, 0, this.DC.length);
        this.DC = obj;
    }

    public void a(short s) {
        if (this.DC.length == this.b + 1) {
            c();
        }
        short[] sArr = this.DC;
        int i = this.b + 1;
        this.b = i;
        sArr[i] = s;
    }

    public void b() {
        this.b = -1;
    }

    public short he() {
        short[] sArr = this.DC;
        int i = this.b;
        this.b = i - 1;
        return sArr[i];
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ShortStack vector:[");
        for (int i = 0; i < this.DC.length; i++) {
            if (i != 0) {
                stringBuilder.append(" ");
            }
            if (i == this.b) {
                stringBuilder.append(">>");
            }
            stringBuilder.append(this.DC[i]);
            if (i == this.b) {
                stringBuilder.append("<<");
            }
        }
        stringBuilder.append("]>");
        return stringBuilder.toString();
    }
}
