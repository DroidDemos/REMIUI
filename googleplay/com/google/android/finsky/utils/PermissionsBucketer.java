package com.google.android.finsky.utils;

import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.text.TextUtils;
import com.google.android.finsky.FinskyApp;
import com.google.android.wallet.instrumentmanager.R;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PermissionsBucketer {
    protected static final String[] BILLING_PERMISSIONS_BUCKET;
    protected static final String[] BLUETOOTH_CONNECTION_INFORMATION;
    protected static final String[] CALENDAR_PERMISSIONS;
    protected static final String[] CAMERA_PERMISSIONS;
    protected static final String[] CONTACTS_PERMISSIONS;
    protected static final String[] DEVICE_AND_APP_HISTORY_PERMISSIONS;
    protected static final String[] DEVICE_ID_AND_CALL_INFORMATION;
    protected static final String[] IDENTITY_PERMISSIONS;
    protected static final String[] IGNORED_PERMISSIONS;
    protected static final String[] LOCATION_PERMISSIONS;
    protected static final String[] MIC_PERMISSIONS;
    protected static final String[] PHONE_PERMISSIONS;
    protected static final String[] PHOTOS_MEDIA_FILES_PERMISSIONS;
    protected static final String[] REDIRECT_INTERNET_TRAFFIC;
    protected static final String[] SMS_PERMISSIONS;
    protected static final String[] WEARABLE_ACTIVITY_PERMISSIONS;
    protected static final String[] WIFI_CONNECTION_INFORMATION;
    private static Set<String> sCachedIgnoredPermissionSet;
    private static Map<String, Integer> sCachedPermissionsMap;

    public static class PermissionBucket {
        public final int mBucketDescription;
        public final int mBucketIcon;
        public final int mBucketId;
        public final int mBucketTitle;
        public final List<String> mPermissionDescriptions;

        public PermissionBucket(int bucketId, int bucketIcon, int bucketTitle, int bucketDescription) {
            this.mPermissionDescriptions = Lists.newArrayList();
            this.mBucketId = bucketId;
            this.mBucketIcon = bucketIcon;
            this.mBucketTitle = bucketTitle;
            this.mBucketDescription = bucketDescription;
        }

        public void addPermission(String description) {
            this.mPermissionDescriptions.add(description);
        }
    }

    public static class PermissionData {
        public final List<PermissionBucket> mExistingPermissionsBucket;
        public final boolean mForcePermissionPrompt;
        public final List<PermissionBucket> mNewPermissions;

        public PermissionData() {
            this.mNewPermissions = Collections.emptyList();
            this.mExistingPermissionsBucket = Collections.emptyList();
            this.mForcePermissionPrompt = false;
        }

        public PermissionData(List<PermissionBucket> newPermissions, List<PermissionBucket> existingPermissions, boolean forcePermissionPrompt) {
            this.mNewPermissions = newPermissions;
            this.mExistingPermissionsBucket = existingPermissions;
            this.mForcePermissionPrompt = forcePermissionPrompt;
        }
    }

    static {
        BILLING_PERMISSIONS_BUCKET = new String[]{"com.android.vending.BILLING"};
        IDENTITY_PERMISSIONS = new String[]{"android.permission.GET_ACCOUNTS", "android.permission.MANAGE_ACCOUNTS", "android.permission.READ_PROFILE", "android.permission.WRITE_PROFILE"};
        CALENDAR_PERMISSIONS = new String[]{"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR"};
        CONTACTS_PERMISSIONS = new String[]{"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS"};
        LOCATION_PERMISSIONS = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS", "android.permission.ACCESS_GPS"};
        SMS_PERMISSIONS = new String[]{"android.permission.RECEIVE_SMS", "android.permission.READ_SMS", "android.permission.WRITE_SMS", "android.permission.SEND_SMS", "android.permission.RECEIVE_MMS", "android.permission.RECEIVE_WAP_PUSH"};
        PHONE_PERMISSIONS = new String[]{"android.permission.CALL_PHONE", "android.permission.WRITE_CALL_LOG", "android.permission.READ_CALL_LOG", "android.permission.CALL_PRIVILEGED", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.MODIFY_PHONE_STATE"};
        PHOTOS_MEDIA_FILES_PERMISSIONS = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.MOUNT_FORMAT_FILESYSTEMS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS"};
        CAMERA_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.RECORD_VIDEO"};
        MIC_PERMISSIONS = new String[]{"android.permission.RECORD_AUDIO"};
        DEVICE_AND_APP_HISTORY_PERMISSIONS = new String[]{"android.permission.READ_LOGS", "android.permission.GET_TASKS", "android.permission.DUMP", "com.android.browser.permission.READ_HISTORY_BOOKMARKS"};
        REDIRECT_INTERNET_TRAFFIC = new String[]{"android.permission.WRITE_APN_SETTINGS"};
        WIFI_CONNECTION_INFORMATION = new String[]{"android.permission.ACCESS_WIFI_STATE"};
        BLUETOOTH_CONNECTION_INFORMATION = new String[]{"android.permission.BLUETOOTH_ADMIN"};
        DEVICE_ID_AND_CALL_INFORMATION = new String[]{"android.permission.READ_PHONE_STATE"};
        WEARABLE_ACTIVITY_PERMISSIONS = new String[]{"android.permission.BODY_SENSORS"};
        IGNORED_PERMISSIONS = new String[]{"android.permission.ACCESS_MOCK_LOCATION", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCOUNT_MANAGER", "android.permission.AUTHENTICATE_ACCOUNTS", "android.permission.BATTERY_STATS", "android.permission.BLUETOOTH", "android.permission.BROADCAST_STICKY", "android.permission.CHANGE_CONFIGURATION", "android.permission.CHANGE_NETWORK_STATE", "android.permission.CHANGE_WIFI_MULTICAST_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.CHANGE_WIMAX_STATE", "android.permission.CLEAR_APP_CACHE", "android.permission.DISABLE_KEYGUARD", "android.permission.EXPAND_STATUS_BAR", "android.permission.FLASHLIGHT", "android.permission.GET_PACKAGE_SIZE", "android.permission.INTERNET", "android.permission.KILL_BACKGROUND_PROCESSES", "android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.NFC", "android.permission.PERSISTENT_ACTIVITY", "android.permission.READ_SYNC_SETTINGS", "android.permission.READ_USER_DICTIONARY", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.REORDER_TASKS", "android.permission.SERIAL_PORT", "android.permission.SET_ALWAYS_FINISH", "android.permission.SET_ANIMATION_SCALE", "android.permission.SET_DEBUG_APP", "android.permission.SET_PREFERRED_APPLICATIONS", "android.permission.SET_PROCESS_LIMIT", "android.permission.SET_TIME_ZONE", "android.permission.SET_WALLPAPER", "android.permission.SIGNAL_PERSISTENT_PROCESSES", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.USE_CREDENTIALS", "android.permission.USE_SIP", "android.permission.VIBRATE", "android.permission.WAKE_LOCK", "android.permission.WRITE_SETTINGS", "android.permission.WRITE_SYNC_SETTINGS", "android.permission.WRITE_USER_DICTIONARY", "com.android.alarm.permission.SET_ALARM", "com.android.browser.permission.WRITE_HISTORY_BOOKMARKS", "com.android.launcher.permission.INSTALL_SHORTCUT", "com.android.launcher.permission.UNINSTALL_SHORTCUT", "com.android.vending.CHECK_LICENSE", "com.google.android.providers.gsf.permission.READ_GSERVICES"};
        sCachedPermissionsMap = null;
        sCachedIgnoredPermissionSet = null;
    }

    public static PermissionData getPermissionBuckets(String[] permissionsFromLatestVersion, Set<String> permissionsFromInstalledVersion, boolean hadAcceptedBucketedPermissions) {
        return getPermissionBuckets(permissionsFromLatestVersion, permissionsFromInstalledVersion, hadAcceptedBucketedPermissions, false);
    }

    public static PermissionData getPermissionBuckets(String[] permissionsFromLatestVersion, Set<String> permissionsFromInstalledVersion, boolean hadAcceptedBucketedPermissions, boolean showIgnoredPermissionsInOtherBucket) {
        if (permissionsFromLatestVersion == null) {
            return new PermissionData();
        }
        Set<String> newlyAddedPermissions;
        boolean forcePermissionPrompt = false;
        Map<String, Integer> permissionToBucketMap = generatePermissionsMap();
        if (hadAcceptedBucketedPermissions) {
            newlyAddedPermissions = Sets.newHashSet((Object[]) permissionsFromLatestVersion);
            if (permissionsFromInstalledVersion != null) {
                newlyAddedPermissions.removeAll(permissionsFromInstalledVersion);
            }
        } else {
            if (permissionsFromInstalledVersion != null) {
                newlyAddedPermissions = Sets.newHashSet((Object[]) permissionsFromLatestVersion);
                newlyAddedPermissions.removeAll(permissionsFromInstalledVersion);
                if (!hasDangerousNewPermissions(permissionToBucketMap.keySet(), newlyAddedPermissions)) {
                    return new PermissionData();
                }
                forcePermissionPrompt = true;
            }
            permissionsFromInstalledVersion = null;
            newlyAddedPermissions = Sets.newHashSet((Object[]) permissionsFromLatestVersion);
        }
        PermissionBucket[] latestBucketedPermissions = bucketPermissions(permissionToBucketMap, newlyAddedPermissions, showIgnoredPermissionsInOtherBucket);
        PermissionBucket[] oldVersionBucketedPermissions = null;
        if (permissionsFromInstalledVersion != null) {
            oldVersionBucketedPermissions = bucketPermissions(permissionToBucketMap, permissionsFromInstalledVersion, showIgnoredPermissionsInOtherBucket);
        }
        List<PermissionBucket> newPermissions = Lists.newArrayList();
        List<PermissionBucket> existingPermissions = Lists.newArrayList();
        int i = 0;
        while (i < 16) {
            PermissionBucket latestPermission = latestBucketedPermissions != null ? latestBucketedPermissions[i] : null;
            if ((oldVersionBucketedPermissions != null ? oldVersionBucketedPermissions[i] : null) != null) {
                existingPermissions.add(oldVersionBucketedPermissions[i]);
            } else if (latestPermission != null) {
                newPermissions.add(latestPermission);
            }
            i++;
        }
        if (!(oldVersionBucketedPermissions == null || oldVersionBucketedPermissions[16] == null)) {
            existingPermissions.add(oldVersionBucketedPermissions[16]);
        }
        if (!(latestBucketedPermissions == null || latestBucketedPermissions[16] == null)) {
            newPermissions.add(latestBucketedPermissions[16]);
        }
        return new PermissionData(newPermissions, existingPermissions, forcePermissionPrompt);
    }

    public static void setAcceptedNewBuckets(String docId) {
        FinskyApp.get().getAppStates().getInstallerDataStore().setPermissionsVersion(docId, 1);
    }

    protected static boolean hasDangerousNewPermissions(Set<String> dangerousPermissions, Set<String> passedInNewPermissions) {
        for (String permission : passedInNewPermissions) {
            if (dangerousPermissions.contains(permission)) {
                return true;
            }
        }
        Set<String> newPermissions = Sets.newHashSet((Collection) passedInNewPermissions);
        newPermissions.removeAll(dangerousPermissions);
        for (String permission2 : newPermissions) {
            if (isDangerousPlatformDefinedPermission(permission2)) {
                return true;
            }
        }
        return false;
    }

    private static PermissionBucket[] bucketPermissions(Map<String, Integer> permissionToBucketMap, Set<String> permissions, boolean showIgnoredPermissionsInOtherBucket) {
        PermissionBucket[] mPermissionBuckets = null;
        for (String permission : permissions) {
            Integer bucketId = (Integer) permissionToBucketMap.get(permission);
            if (bucketId == null) {
                boolean isIgnoredPermission = getIgnoredPermissionSet().contains(permission);
                if ((isIgnoredPermission && showIgnoredPermissionsInOtherBucket) || (!isIgnoredPermission && isDangerousPlatformDefinedPermission(permission))) {
                    bucketId = Integer.valueOf(16);
                }
            }
            if (mPermissionBuckets == null) {
                mPermissionBuckets = new PermissionBucket[17];
            }
            PermissionBucket bucket = mPermissionBuckets[bucketId.intValue()];
            if (bucket == null) {
                bucket = getBucketByBucketId(bucketId.intValue());
                mPermissionBuckets[bucketId.intValue()] = bucket;
            }
            String permissionName = getHumanReadablePermissionString(permission);
            if (!TextUtils.isEmpty(permissionName)) {
                bucket.addPermission(permissionName);
            }
        }
        return mPermissionBuckets;
    }

    private static boolean isDangerousPlatformDefinedPermission(String permission) {
        try {
            PermissionInfo permissionInfo = FinskyApp.get().getPackageManager().getPermissionInfo(permission, 0);
            if (permissionInfo != null && permissionInfo.protectionLevel == 1) {
                return true;
            }
        } catch (NameNotFoundException e) {
        }
        return false;
    }

    private static String getHumanReadablePermissionString(String permission) {
        String str = null;
        try {
            PermissionInfo permissionInfo = FinskyApp.get().getPackageManager().getPermissionInfo(permission, 0);
            if (permissionInfo != null) {
                CharSequence sequence = permissionInfo.loadLabel(FinskyApp.get().getPackageManager());
                if (sequence != null) {
                    str = sequence.toString();
                }
            }
        } catch (NameNotFoundException e) {
        }
        return str;
    }

    protected static Set<String> getIgnoredPermissionSet() {
        if (sCachedIgnoredPermissionSet == null) {
            sCachedIgnoredPermissionSet = Sets.newHashSet(IGNORED_PERMISSIONS);
        }
        return sCachedIgnoredPermissionSet;
    }

    protected static Map<String, Integer> generatePermissionsMap() {
        if (sCachedPermissionsMap != null) {
            return sCachedPermissionsMap;
        }
        Map<String, Integer> permissionsMap = Maps.newHashMap();
        for (String permission : BILLING_PERMISSIONS_BUCKET) {
            permissionsMap.put(permission, Integer.valueOf(0));
        }
        for (String permission2 : IDENTITY_PERMISSIONS) {
            permissionsMap.put(permission2, Integer.valueOf(3));
        }
        for (String permission22 : CONTACTS_PERMISSIONS) {
            permissionsMap.put(permission22, Integer.valueOf(5));
        }
        for (String permission222 : CALENDAR_PERMISSIONS) {
            permissionsMap.put(permission222, Integer.valueOf(4));
        }
        for (String permission2222 : LOCATION_PERMISSIONS) {
            permissionsMap.put(permission2222, Integer.valueOf(6));
        }
        for (String permission22222 : SMS_PERMISSIONS) {
            permissionsMap.put(permission22222, Integer.valueOf(7));
        }
        for (String permission222222 : PHONE_PERMISSIONS) {
            permissionsMap.put(permission222222, Integer.valueOf(8));
        }
        for (String permission2222222 : PHOTOS_MEDIA_FILES_PERMISSIONS) {
            permissionsMap.put(permission2222222, Integer.valueOf(9));
        }
        for (String permission22222222 : CAMERA_PERMISSIONS) {
            permissionsMap.put(permission22222222, Integer.valueOf(10));
        }
        for (String permission222222222 : MIC_PERMISSIONS) {
            permissionsMap.put(permission222222222, Integer.valueOf(11));
        }
        for (String permission2222222222 : DEVICE_AND_APP_HISTORY_PERMISSIONS) {
            permissionsMap.put(permission2222222222, Integer.valueOf(1));
        }
        for (String permission22222222222 : REDIRECT_INTERNET_TRAFFIC) {
            permissionsMap.put(permission22222222222, Integer.valueOf(2));
        }
        for (String permission222222222222 : WIFI_CONNECTION_INFORMATION) {
            permissionsMap.put(permission222222222222, Integer.valueOf(12));
        }
        for (String permission2222222222222 : BLUETOOTH_CONNECTION_INFORMATION) {
            permissionsMap.put(permission2222222222222, Integer.valueOf(13));
        }
        for (String permission22222222222222 : DEVICE_ID_AND_CALL_INFORMATION) {
            permissionsMap.put(permission22222222222222, Integer.valueOf(14));
        }
        for (String permission222222222222222 : WEARABLE_ACTIVITY_PERMISSIONS) {
            permissionsMap.put(permission222222222222222, Integer.valueOf(15));
        }
        sCachedPermissionsMap = Collections.unmodifiableMap(permissionsMap);
        return sCachedPermissionsMap;
    }

    protected static PermissionBucket getBucketByBucketId(int bucketId) {
        switch (bucketId) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return new PermissionBucket(0, com.android.vending.R.drawable.ic_perm_in_app_purchases, com.android.vending.R.string.permission_bucket_in_app_purchases_title, com.android.vending.R.string.permission_bucket_in_app_purchases_description);
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return new PermissionBucket(1, com.android.vending.R.drawable.ic_perm_history, com.android.vending.R.string.permission_bucket_device_and_app_history_title, com.android.vending.R.string.permission_bucket_device_and_app_history_description);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return new PermissionBucket(2, com.android.vending.R.drawable.ic_perm_data_setting, com.android.vending.R.string.permission_bucket_redirect_internet_traffic_title, com.android.vending.R.string.permission_bucket_redirect_internet_traffic_description);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return new PermissionBucket(3, com.android.vending.R.drawable.ic_perm_identity, com.android.vending.R.string.permission_bucket_identity_title, com.android.vending.R.string.permission_bucket_identity_description);
            case R.styleable.WalletImFormEditText_required /*4*/:
                return new PermissionBucket(4, com.android.vending.R.drawable.ic_perm_cal, com.android.vending.R.string.permission_bucket_calendar_title, com.android.vending.R.string.permission_bucket_calendar_description);
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return new PermissionBucket(5, com.android.vending.R.drawable.ic_perm_contacts, com.android.vending.R.string.permission_bucket_contacts_title, com.android.vending.R.string.permission_bucket_contacts_description);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return new PermissionBucket(6, com.android.vending.R.drawable.ic_perm_location, com.android.vending.R.string.permission_bucket_location_title, com.android.vending.R.string.permission_bucket_location_description);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return new PermissionBucket(7, com.android.vending.R.drawable.ic_perm_messaging, com.android.vending.R.string.permission_bucket_sms_title, com.android.vending.R.string.permission_bucket_sms_description);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return new PermissionBucket(8, com.android.vending.R.drawable.ic_perm_phone, com.android.vending.R.string.permission_bucket_phone_title, com.android.vending.R.string.permission_bucket_phone_description);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                return new PermissionBucket(9, com.android.vending.R.drawable.ic_perm_media, com.android.vending.R.string.permission_bucket_photos_media_files_title, com.android.vending.R.string.permission_bucket_photos_media_files_description);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                return new PermissionBucket(10, com.android.vending.R.drawable.ic_perm_camera, com.android.vending.R.string.permission_bucket_camera_title, com.android.vending.R.string.permission_bucket_camera_description);
            case R.styleable.MapAttrs_uiZoomControls /*11*/:
                return new PermissionBucket(11, com.android.vending.R.drawable.ic_perm_microphone, com.android.vending.R.string.permission_bucket_mic_title, com.android.vending.R.string.permission_bucket_mic_description);
            case R.styleable.MapAttrs_uiZoomGestures /*12*/:
                return new PermissionBucket(12, com.android.vending.R.drawable.ic_perm_scan_wifi, com.android.vending.R.string.permission_bucket_wifi_connection_info_title, com.android.vending.R.string.permission_bucket_wifi_connection_info_desc);
            case R.styleable.MapAttrs_useViewLifecycle /*13*/:
                return new PermissionBucket(13, com.android.vending.R.drawable.ic_perm_bluetooth_discovery, com.android.vending.R.string.permission_bucket_bluetooth_connection_info_title, com.android.vending.R.string.permission_bucket_bluetooth_connection_info_desc);
            case R.styleable.MapAttrs_zOrderOnTop /*14*/:
                return new PermissionBucket(14, com.android.vending.R.drawable.ic_perm_deviceid, com.android.vending.R.string.permission_bucket_device_id_and_call_information_title, com.android.vending.R.string.permission_bucket_device_id_and_call_information_description);
            case R.styleable.MapAttrs_uiMapToolbar /*15*/:
                return new PermissionBucket(15, com.android.vending.R.drawable.ic_perm_body_motion, com.android.vending.R.string.permission_bucket_wearables_title, com.android.vending.R.string.permissions_bucket_wearables_description);
            case com.google.android.play.R.styleable.Toolbar_maxButtonHeight /*16*/:
                return new PermissionBucket(16, com.android.vending.R.drawable.ic_perm_unknown, com.android.vending.R.string.permission_bucket_other_title, com.android.vending.R.string.permission_bucket_other_description);
            default:
                throw new IllegalStateException("invalid permission bucket.");
        }
    }
}
