package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

@fd
public class dq {
    private final Context mContext;
    private final WindowManager mU;
    private final gz mr;
    private float rA;
    int rB;
    int rC;
    private int rD;
    private int rE;
    private int rF;
    private int[] rG;
    private final bq ry;
    DisplayMetrics rz;

    public dq(gz gzVar, Context context, bq bqVar) {
        this.rB = -1;
        this.rC = -1;
        this.rE = -1;
        this.rF = -1;
        this.rG = new int[2];
        this.mr = gzVar;
        this.mContext = context;
        this.ry = bqVar;
        this.mU = (WindowManager) context.getSystemService("window");
        bV();
        bW();
        bX();
    }

    private void bV() {
        this.rz = new DisplayMetrics();
        Display defaultDisplay = this.mU.getDefaultDisplay();
        defaultDisplay.getMetrics(this.rz);
        this.rA = this.rz.density;
        this.rD = defaultDisplay.getRotation();
    }

    private void bX() {
        this.mr.getLocationOnScreen(this.rG);
        this.mr.measure(0, 0);
        float f = 160.0f / ((float) this.rz.densityDpi);
        this.rE = Math.round(((float) this.mr.getMeasuredWidth()) * f);
        this.rF = Math.round(f * ((float) this.mr.getMeasuredHeight()));
    }

    void bW() {
        int s = gn.s(this.mContext);
        float f = 160.0f / ((float) this.rz.densityDpi);
        this.rB = Math.round(((float) this.rz.widthPixels) * f);
        this.rC = Math.round(((float) (this.rz.heightPixels - s)) * f);
    }
}
