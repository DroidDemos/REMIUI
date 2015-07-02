package com.google.android.vending.verifier;

public class PackageVerificationData {
    public final long mApkLength;
    public final long mCacheFingerprint;
    public byte[][] mCertFingerprints;
    public final boolean mForwardLocked;
    public boolean mInStoppedState;
    public boolean mInstalledByPlay;
    public final String mPackageName;
    public final byte[] mSha256Digest;
    public final boolean mSuppressUserWarning;

    public PackageVerificationData(String packageName, long cacheFingerprint, byte[] sha256Digest, long length, boolean forwardLocked, boolean suppressUserWarning) {
        this.mPackageName = packageName;
        this.mCacheFingerprint = cacheFingerprint;
        this.mSha256Digest = (byte[]) sha256Digest.clone();
        this.mApkLength = length;
        this.mForwardLocked = forwardLocked;
        this.mSuppressUserWarning = suppressUserWarning;
    }
}
