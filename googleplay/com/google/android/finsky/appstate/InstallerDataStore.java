package com.google.android.finsky.appstate;

import com.google.android.finsky.protos.AndroidAppDelivery.AndroidAppDeliveryData;
import com.google.android.finsky.utils.FinskyLog;
import java.util.Collection;

public interface InstallerDataStore {

    public enum AutoUpdateState {
        DEFAULT,
        USE_GLOBAL,
        DISABLED;

        public static AutoUpdateState valueOf(int ordinal) {
            for (AutoUpdateState state : values()) {
                if (state.ordinal() == ordinal) {
                    return state;
                }
            }
            throw new IllegalArgumentException("Unknown ordinal: " + ordinal);
        }
    }

    public static class InstallerData {
        private static final String[] EMPTY_STRING_ARRAY;
        private String accountForUpdate;
        private String accountName;
        private String[] autoAcquireTags;
        private AutoUpdateState autoUpdate;
        private String continueUrl;
        private AndroidAppDeliveryData deliveryData;
        private long deliveryDataTimestampMs;
        private String deliveryToken;
        private int desiredVersion;
        private String downloadUri;
        private String externalReferrer;
        private long externalReferrerTimestampMs;
        private long firstDownloadMs;
        private int flags;
        private int installerState;
        private int lastNotifiedVersion;
        private long lastUpdateTimestampMs;
        private final String packageName;
        private int permissionsVersion;
        private int persistentFlags;
        private String title;

        public static class Builder {
            private final InstallerData mInstance;

            public Builder(String packageName) {
                this.mInstance = new InstallerData(packageName);
            }

            public static Builder buildUpon(InstallerData original, String packageName) {
                Builder builder = new Builder(packageName);
                if (original != null) {
                    if (!packageName.equals(original.packageName)) {
                        FinskyLog.wtf("Package name mismatch,  %s != %s", packageName, original.packageName);
                    }
                    builder.setAutoUpdate(original.getAutoUpdate());
                    builder.setDesiredVersion(original.getDesiredVersion());
                    builder.setLastNotifiedVersion(original.getLastNotifiedVersion());
                    builder.setDeliveryData(original.getDeliveryData(), original.getDeliveryDataTimestampMs());
                    builder.setInstallerState(original.getInstallerState());
                    builder.setDownloadUri(original.getDownloadUri());
                    builder.setFirstDownloadMs(original.getFirstDownloadMs());
                    builder.setExternalReferrer(original.getExternalReferrer());
                    builder.setContinueUrl(original.getContinueUrl());
                    builder.setAccountName(original.getAccountName());
                    builder.setTitle(original.getTitle());
                    builder.setFlags(original.getFlags());
                    builder.setLastUpdateTimestampMs(original.getLastUpdateTimestampMs());
                    builder.setAccountForUpdate(original.getAccountForUpdate());
                    builder.setAutoAcquireTags(original.getAutoAcquireTags());
                    builder.setExternalReferrerTimestampMs(original.getExternalReferrerTimestampMs());
                    builder.setPersistentFlags(original.getPersistentFlags());
                    builder.setPermissionsVersion(original.getPermissionsVersion());
                    builder.setDeliveryToken(original.getDeliveryToken());
                }
                return builder;
            }

            public Builder setAutoUpdate(AutoUpdateState autoUpdate) {
                this.mInstance.autoUpdate = autoUpdate;
                return this;
            }

            public Builder setDesiredVersion(int desiredVersion) {
                this.mInstance.desiredVersion = desiredVersion;
                return this;
            }

            public Builder setLastNotifiedVersion(int lastNotifiedVersion) {
                this.mInstance.lastNotifiedVersion = lastNotifiedVersion;
                return this;
            }

            public Builder setDeliveryData(AndroidAppDeliveryData deliveryData, long timestampMs) {
                this.mInstance.deliveryData = deliveryData;
                this.mInstance.deliveryDataTimestampMs = timestampMs;
                return this;
            }

            public Builder setInstallerState(int installerState) {
                this.mInstance.installerState = installerState;
                return this;
            }

            public Builder setDownloadUri(String downloadUri) {
                this.mInstance.downloadUri = downloadUri;
                return this;
            }

            public Builder setFirstDownloadMs(long firstDownloadMs) {
                this.mInstance.firstDownloadMs = firstDownloadMs;
                return this;
            }

            public Builder setExternalReferrer(String externalReferrer) {
                this.mInstance.externalReferrer = externalReferrer;
                return this;
            }

            public Builder setAccountName(String accountName) {
                this.mInstance.accountName = accountName;
                return this;
            }

            public Builder setTitle(String title) {
                this.mInstance.title = title;
                return this;
            }

            public Builder setFlags(int flags) {
                this.mInstance.flags = flags;
                return this;
            }

            public Builder setContinueUrl(String continueUrl) {
                this.mInstance.continueUrl = continueUrl;
                return this;
            }

            public Builder setLastUpdateTimestampMs(long lastUpdateTimestampMs) {
                this.mInstance.lastUpdateTimestampMs = lastUpdateTimestampMs;
                return this;
            }

            public Builder setAccountForUpdate(String accountForUpdate) {
                this.mInstance.accountForUpdate = accountForUpdate;
                return this;
            }

            public Builder setAutoAcquireTags(String[] autoAcquireTags) {
                this.mInstance.autoAcquireTags = autoAcquireTags;
                return this;
            }

            public Builder setExternalReferrerTimestampMs(long externalReferrerTimestampMs) {
                this.mInstance.externalReferrerTimestampMs = externalReferrerTimestampMs;
                return this;
            }

            public Builder setPersistentFlags(int persistentFlags) {
                this.mInstance.persistentFlags = persistentFlags;
                return this;
            }

            public Builder setPermissionsVersion(int permissionsVersion) {
                this.mInstance.permissionsVersion = permissionsVersion;
                return this;
            }

            public Builder setDeliveryToken(String deliveryToken) {
                this.mInstance.deliveryToken = deliveryToken;
                return this;
            }

            public InstallerData build() {
                return this.mInstance;
            }
        }

        static {
            EMPTY_STRING_ARRAY = new String[0];
        }

        private InstallerData(String packageName) {
            this.autoUpdate = AutoUpdateState.USE_GLOBAL;
            this.desiredVersion = -1;
            this.lastNotifiedVersion = -1;
            this.packageName = packageName;
        }

        public InstallerData(String packageName, AutoUpdateState autoUpdate, int desiredVersion, int lastNotifiedVersion, AndroidAppDeliveryData deliveryData, long deliveryDataTimestampMs, int installerState, String downloadUri, long firstDownloadMs, String externalReferrer, String continueUrl, String accountName, String title, int flags, long lastUpdateTimestampMs, String accountForUpdate, String[] autoAcquireTags, long externalReferrerTimestampMs, int persistentFlags, int permissionsVersion, String deliveryToken) {
            this.autoUpdate = AutoUpdateState.USE_GLOBAL;
            this.desiredVersion = -1;
            this.lastNotifiedVersion = -1;
            this.packageName = packageName;
            this.autoUpdate = autoUpdate;
            this.desiredVersion = desiredVersion;
            this.lastNotifiedVersion = lastNotifiedVersion;
            this.deliveryData = deliveryData;
            this.deliveryDataTimestampMs = deliveryDataTimestampMs;
            this.installerState = installerState;
            this.downloadUri = downloadUri;
            this.firstDownloadMs = firstDownloadMs;
            this.externalReferrer = externalReferrer;
            this.continueUrl = continueUrl;
            this.accountName = accountName;
            this.title = title;
            this.flags = flags;
            this.lastUpdateTimestampMs = lastUpdateTimestampMs;
            this.accountForUpdate = accountForUpdate;
            this.autoAcquireTags = autoAcquireTags;
            this.externalReferrerTimestampMs = externalReferrerTimestampMs;
            this.persistentFlags = persistentFlags;
            this.permissionsVersion = permissionsVersion;
            this.deliveryToken = deliveryToken;
        }

        public String toString() {
            boolean z = true;
            String str = "(packageName=%s,autoUpdate=%s,desiredVersion=%d,hasDeliveryData=%b,installerState=%d,downloadUri=%s,firstDownloadMs=%d,externalReferrer=%s,continueUrl=%s,account=%s)";
            Object[] objArr = new Object[10];
            objArr[0] = this.packageName;
            objArr[1] = this.autoUpdate;
            objArr[2] = Integer.valueOf(this.desiredVersion);
            if (this.deliveryData == null) {
                z = false;
            }
            objArr[3] = Boolean.valueOf(z);
            objArr[4] = Integer.valueOf(this.installerState);
            objArr[5] = this.downloadUri;
            objArr[6] = Long.valueOf(this.firstDownloadMs);
            objArr[7] = this.externalReferrer;
            objArr[8] = this.continueUrl;
            objArr[9] = FinskyLog.scrubPii(this.accountName);
            return String.format(str, objArr);
        }

        public String getPackageName() {
            return this.packageName;
        }

        public AutoUpdateState getAutoUpdate() {
            return this.autoUpdate;
        }

        public int getDesiredVersion() {
            return this.desiredVersion;
        }

        public int getLastNotifiedVersion() {
            return this.lastNotifiedVersion;
        }

        public AndroidAppDeliveryData getDeliveryData() {
            return this.deliveryData;
        }

        public long getDeliveryDataTimestampMs() {
            return this.deliveryDataTimestampMs;
        }

        public int getInstallerState() {
            return this.installerState;
        }

        public String getDownloadUri() {
            return this.downloadUri;
        }

        public String getAccountName() {
            return this.accountName;
        }

        public long getFirstDownloadMs() {
            return this.firstDownloadMs;
        }

        public String getExternalReferrer() {
            return this.externalReferrer;
        }

        public String getTitle() {
            return this.title;
        }

        public int getFlags() {
            return this.flags;
        }

        public String getContinueUrl() {
            return this.continueUrl;
        }

        public long getLastUpdateTimestampMs() {
            return this.lastUpdateTimestampMs;
        }

        public String getAccountForUpdate() {
            return this.accountForUpdate;
        }

        public String[] getAutoAcquireTags() {
            return this.autoAcquireTags == null ? EMPTY_STRING_ARRAY : this.autoAcquireTags;
        }

        public long getExternalReferrerTimestampMs() {
            return this.externalReferrerTimestampMs;
        }

        public int getPersistentFlags() {
            return this.persistentFlags;
        }

        public int getPermissionsVersion() {
            return this.permissionsVersion;
        }

        public String getDeliveryToken() {
            return this.deliveryToken;
        }
    }

    InstallerData get(String str);

    Collection<InstallerData> getAll();

    void put(InstallerData installerData);

    void removeLocalAppState(String str);

    void setAccountForUpdate(String str, String str2);

    void setAutoAcquireTags(String str, String[] strArr);

    void setAutoUpdate(String str, AutoUpdateState autoUpdateState);

    void setContinueUrl(String str, String str2);

    void setDeliveryData(String str, AndroidAppDeliveryData androidAppDeliveryData, long j);

    void setDeliveryToken(String str, String str2);

    void setDesiredVersion(String str, int i);

    void setExternalReferrer(String str, String str2);

    void setExternalReferrerTimestampMs(String str, long j);

    void setFirstDownloadMs(String str, long j);

    void setFlags(String str, int i);

    void setInstallerState(String str, int i, String str2);

    void setLastNotifiedVersion(String str, int i);

    void setLastUpdateTimestampMs(String str, long j);

    void setPermissionsVersion(String str, int i);

    void setPersistentFlags(String str, int i);
}
