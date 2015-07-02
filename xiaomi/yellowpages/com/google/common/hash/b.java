package com.google.common.hash;

/* compiled from: AbstractCompositeHashFunction */
abstract class b extends i {
    final g[] gB;

    abstract HashCode a(m[] mVarArr);

    public m av() {
        m[] mVarArr = new m[this.gB.length];
        for (int i = 0; i < mVarArr.length; i++) {
            mVarArr[i] = this.gB[i].av();
        }
        return new j(this, mVarArr);
    }
}
