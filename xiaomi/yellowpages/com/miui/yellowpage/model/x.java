package com.miui.yellowpage.model;

/* compiled from: CallLogDataItem */
public class x {
    private String LW;
    private int LX;
    private t LY;
    private String LZ;
    private long iD;
    private long mDuration;
    private long mId;
    private String mNumber;
    private int mSimId;
    private int mType;

    public x() {
        this.mSimId = -1;
    }

    public x ah(int i) {
        this.mSimId = i;
        return this;
    }

    public int getSimId() {
        return this.mSimId;
    }

    public x cr(String str) {
        this.LZ = str;
        return this;
    }

    public String iA() {
        return this.LZ;
    }

    public boolean iB() {
        return this.LZ != null;
    }

    public boolean iC() {
        return this.LY != null;
    }

    public x a(t tVar) {
        this.LY = tVar;
        return this;
    }

    public t iD() {
        return this.LY;
    }

    public x s(long j) {
        this.iD = j;
        return this;
    }

    public long getDate() {
        return this.iD;
    }

    public x t(long j) {
        this.mDuration = j;
        return this;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public x cs(String str) {
        this.mNumber = str;
        return this;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public x ai(int i) {
        this.mType = i;
        return this;
    }

    public int getType() {
        return this.mType;
    }

    public x u(long j) {
        this.mId = j;
        return this;
    }

    public long getId() {
        return this.mId;
    }

    public x ct(String str) {
        this.LW = str;
        return this;
    }

    public x aj(int i) {
        this.LX = i;
        return this;
    }

    public int iE() {
        return this.LX;
    }

    public boolean iF() {
        return this.mType == 1;
    }

    public boolean iG() {
        return this.mType == 2;
    }
}
