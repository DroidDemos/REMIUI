package org.apache.thrift;

public class ShortStack {
    private int top;
    private short[] vector;

    public ShortStack(int initialCapacity) {
        this.top = -1;
        this.vector = new short[initialCapacity];
    }

    public short pop() {
        short[] sArr = this.vector;
        int i = this.top;
        this.top = i - 1;
        return sArr[i];
    }

    public void push(short pushed) {
        if (this.vector.length == this.top + 1) {
            grow();
        }
        short[] sArr = this.vector;
        int i = this.top + 1;
        this.top = i;
        sArr[i] = pushed;
    }

    private void grow() {
        short[] newVector = new short[(this.vector.length * 2)];
        System.arraycopy(this.vector, 0, newVector, 0, this.vector.length);
        this.vector = newVector;
    }

    public short peek() {
        return this.vector[this.top];
    }

    public void clear() {
        this.top = -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ShortStack vector:[");
        for (int i = 0; i < this.vector.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            if (i == this.top) {
                sb.append(">>");
            }
            sb.append(this.vector[i]);
            if (i == this.top) {
                sb.append("<<");
            }
        }
        sb.append("]>");
        return sb.toString();
    }
}
