package com.google.android.finsky.appstate;

import java.util.Arrays;
import java.util.Collection;

public interface PackageStateRepository {

    public static class PackageState {
        public final String[] certificateHashes;
        public final int installedVersion;
        public final boolean isActiveDeviceAdmin;
        public final boolean isDisabled;
        public final boolean isDisabledByUser;
        public final boolean isSystemApp;
        public final boolean isUpdatedSystemApp;
        public final String packageName;

        public PackageState(String packageName, String[] certificateHashes, boolean isSystemApp, boolean isUpdatedSystemApp, boolean isDisabled, boolean isDisabledByUser, int installedVersion, boolean isActiveDeviceAdmin) {
            this.packageName = packageName;
            this.certificateHashes = certificateHashes;
            this.isSystemApp = isSystemApp;
            this.isUpdatedSystemApp = isUpdatedSystemApp;
            this.isDisabled = isDisabled;
            this.isDisabledByUser = isDisabledByUser;
            this.installedVersion = installedVersion;
            this.isActiveDeviceAdmin = isActiveDeviceAdmin;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof PackageState)) {
                return false;
            }
            PackageState that = (PackageState) o;
            if (this.installedVersion != that.installedVersion) {
                return false;
            }
            if (this.isActiveDeviceAdmin != that.isActiveDeviceAdmin) {
                return false;
            }
            if (this.isDisabled != that.isDisabled) {
                return false;
            }
            if (this.isDisabledByUser != that.isDisabledByUser) {
                return false;
            }
            if (this.isSystemApp != that.isSystemApp) {
                return false;
            }
            if (this.isUpdatedSystemApp != that.isUpdatedSystemApp) {
                return false;
            }
            if (!Arrays.equals(this.certificateHashes, that.certificateHashes)) {
                return false;
            }
            if (this.packageName != null) {
                if (this.packageName.equals(that.packageName)) {
                    return true;
                }
            } else if (that.packageName == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int result;
            int hashCode;
            int i = 1;
            if (this.packageName != null) {
                result = this.packageName.hashCode();
            } else {
                result = 0;
            }
            int i2 = result * 31;
            if (this.certificateHashes != null) {
                hashCode = Arrays.hashCode(this.certificateHashes);
            } else {
                hashCode = 0;
            }
            i2 = (((i2 + hashCode) * 31) + this.installedVersion) * 31;
            if (this.isSystemApp) {
                hashCode = 1;
            } else {
                hashCode = 0;
            }
            i2 = (i2 + hashCode) * 31;
            if (this.isUpdatedSystemApp) {
                hashCode = 1;
            } else {
                hashCode = 0;
            }
            i2 = (i2 + hashCode) * 31;
            if (this.isDisabled) {
                hashCode = 1;
            } else {
                hashCode = 0;
            }
            i2 = (i2 + hashCode) * 31;
            if (this.isDisabledByUser) {
                hashCode = 1;
            } else {
                hashCode = 0;
            }
            hashCode = (i2 + hashCode) * 31;
            if (!this.isActiveDeviceAdmin) {
                i = 0;
            }
            return hashCode + i;
        }

        public String toString() {
            return String.format("(packageName=%s,installedVersion=%d,isSystemApp=%s,certificateHashes=%s)", new Object[]{this.packageName, Integer.valueOf(this.installedVersion), Boolean.valueOf(this.isSystemApp), Arrays.deepToString(this.certificateHashes)});
        }
    }

    boolean canLaunch(String str);

    PackageState get(String str);

    Collection<PackageState> getAllBlocking();

    String getVersionName(String str);

    void invalidate(String str);
}
