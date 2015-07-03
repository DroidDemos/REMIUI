package miui.provider.yellowpage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.BaseColumns;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import miui.telephony.PhoneNumberUtils;

public class YellowPageUtils {
    private static final int ANTISPAM_COLUMN_CID = 1;
    private static final int ANTISPAM_COLUMN_MARKED_COUNT = 3;
    private static final int ANTISPAM_COLUMN_NORMALIZED_NUMBER = 4;
    private static final int ANTISPAM_COLUMN_PID = 0;
    private static final int ANTISPAM_COLUMN_TYPE = 2;
    private static final String[] ANTISPAM_PROJECTION;
    public static final String AUTHORITY = "miui.yellowpage";
    private static final String BASE_URI_STRING = "content://miui.yellowpage";
    private static final int PHONE_COLUMN_MIN_MATCH = 9;
    private static final int PHONE_COLUMN_NORMALIZED_NUMBER = 10;
    private static final int PHONE_COLUMN_PHOTO_URL = 3;
    private static final int PHONE_COLUMN_PROVIDER_ID = 1;
    private static final int PHONE_COLUMN_SUSPECT = 11;
    private static final int PHONE_COLUMN_TAG = 2;
    private static final int PHONE_COLUMN_TAG_PINYIN = 7;
    private static final int PHONE_COLUMN_THUMBNAIL_URL = 4;
    private static final int PHONE_COLUMN_VISIBLE = 8;
    private static final int PHONE_COLUMN_YID = 0;
    private static final int PHONE_COLUMN_YP_NAME = 5;
    private static final int PHONE_COLUMN_YP_NAME_PINYIN = 6;
    private static final String[] PHONE_PROJECTION;
    private static final String TAG = "YellowPageUtils";
    private static final HashMap<Integer, Category> sCidCategories;
    private static final HashMap<Integer, Provider> sProviders;

    public interface AntispamCategory {
        public static final String CID = "cid";
        public static final int CID_CUSTOM_START = 10000;
        public static final Uri CONTENT_URI;
        public static final String DIRECTORY = "antispam_category";
        public static final String NAMES = "names";
        public static final String TYPE = "type";
        public static final int TYPE_DO_NOT_SHOW_IN_MARK = 2;
        public static final int TYPE_SYSTEM = 0;
        public static final int TYPE_USER = 1;
        public static final String VERSION_KEY = "antispam_category";

        static {
            CONTENT_URI = Uri.parse("content://miui.yellowpage/antispam_category");
        }
    }

    public interface AntispamNumber extends BaseColumns {
        public static final String CID = "cid";
        public static final Uri CONTENT_PHONE_LOOKUP_URI;
        public static final Uri CONTENT_URI;
        public static final String DIRECTORY = "antispam_number";
        public static final String DIRECTORY_PHONE_LOOKUP = "antispam_number_phone_lookup";
        public static final String MARKED_COUNT = "marked_count";
        public static final String MIN_MATCH = "min_match";
        public static final String NORMALIZED_NUMBER = "normalized_number";
        public static final String NUMBER = "number";
        public static final String PID = "pid";
        public static final String TYPE = "type";
        public static final int TYPE_CLOUD = 2;
        public static final int TYPE_MARKED = 3;
        public static final int TYPE_ONE_RING_CALL = 4;
        public static final int TYPE_PRESET = 1;
        public static final String UPLOAD = "upload";
        public static final int UPLOADED_NO = 0;
        public static final int UPLOADED_YES = 1;
        public static final String VERSION_KEY = "antispam_number_v2";

        static {
            CONTENT_URI = Uri.parse("content://miui.yellowpage/antispam_number");
            CONTENT_PHONE_LOOKUP_URI = Uri.parse("content://miui.yellowpage/antispam_number_phone_lookup");
        }
    }

    public static class Category {
        private String mCustomName;
        private int mId;
        private HashMap<String, String> mNameMap;
        private String mNames;
        private int mType;

        public Category(int id, String names, int type) {
            this.mId = id;
            this.mNames = names;
            this.mType = type;
            if (isUserCustom()) {
                this.mCustomName = this.mNames;
                return;
            }
            String[] arr$ = this.mNames.split(";");
            int len$ = arr$.length;
            for (int i$ = YellowPageUtils.PHONE_COLUMN_YID; i$ < len$; i$ += YellowPageUtils.PHONE_COLUMN_PROVIDER_ID) {
                String[] nameArray = arr$[i$].split(":");
                String language = nameArray[YellowPageUtils.PHONE_COLUMN_YID];
                String name = nameArray[YellowPageUtils.PHONE_COLUMN_PROVIDER_ID];
                if (this.mNameMap == null) {
                    this.mNameMap = new HashMap();
                }
                this.mNameMap.put(language, name);
            }
        }

        public Category(int id, String names) {
            this(id, names, YellowPageUtils.PHONE_COLUMN_YID);
        }

        public String getCategoryName() {
            return isUserCustom() ? this.mCustomName : (String) this.mNameMap.get(Locale.getDefault().toString());
        }

        public String getCategoryAllNames() {
            return this.mNames;
        }

        public int getCategoryId() {
            return this.mId;
        }

        public int getCategoryType() {
            return this.mType;
        }

        public boolean isUserCustom() {
            return this.mId >= AntispamCategory.CID_CUSTOM_START;
        }
    }

    public interface PhoneLookup {
        public static final Uri CONTENT_URI;
        public static final String DIRECTORY = "phone_lookup";
        public static final String HIDE = "hide";
        public static final String MIN_MATCH = "min_match";
        public static final String NORMALIZED_NUMBER = "normalized_number";
        public static final String NUMBER = "number";
        public static final String PHOTO_URL = "photo_url";
        public static final String PROVIDER_ID = "provider_id";
        public static final String SUSPECT = "suspect";
        public static final String TAG = "tag";
        public static final String TAG_PINYIN = "tag_pinyin";
        public static final String THUMBNAIL_URL = "thumbnail_url";
        public static final String YELLOW_PAGE_NAME = "yellow_page_name";
        public static final String YELLOW_PAGE_NAME_PINYIN = "yellow_page_name_pinyin";
        public static final String YID = "yid";

        static {
            CONTENT_URI = Uri.parse("content://miui.yellowpage/phone_lookup");
        }
    }

    public static class Provider {
        public static final Uri CONTENT_URI;
        public static final Provider DEFAULT_PROVIDER;
        public static final String DIRECTORY = "phone_usage";
        public static final String ICON = "icon";
        public static final String ICON_BIG = "icon_big";
        public static final String NAME = "name";
        public static final String PID = "pid";
        public static final int PID_DEFAULT = 0;
        public static final String PNAME_DEFAULT = "MIUI";
        private Bitmap mIcon;
        private Bitmap mIconBig;
        private int mId;
        private String mName;

        static {
            CONTENT_URI = Uri.parse("content://miui.yellowpage/phone_usage");
            DEFAULT_PROVIDER = new Provider(PID_DEFAULT, PNAME_DEFAULT, null, null);
        }

        public Provider(int id, String name, Bitmap icon, Bitmap iconBig) {
            this.mId = id;
            this.mName = name;
            this.mIcon = icon;
            this.mIconBig = iconBig;
        }

        public int getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public Bitmap getIcon() {
            return this.mIcon;
        }

        public Bitmap getBigIcon() {
            return this.mIconBig;
        }

        public boolean isMiui() {
            return this.mId == 0;
        }
    }

    public static final class TagPhone {
        public static final String BUILTIN = "buildIn";
        public static final int BUILTIN_FALSE = 0;
        public static final String BUILTIN_SYNC_ATD_DATA = "atd_data";
        public static final String BUILTIN_SYNC_HAS_MORE = "has_more";
        public static final int BUILTIN_TRUE = 1;
        public static final String CATEGORY_ID = "catId";
        public static final String CATEGORY_NAME = "catTitle";
        public static final String DELETED = "status";
        public static final int DELETED_FALSE = 1;
        public static final int DELETED_TRUE = 0;
        public static final String DURATION = "duration";
        public static final String IMEI = "device";
        public static final String KEY = "atd";
        public static final String MARKED_COUNT = "count";
        public static final String NORMALIZED_NUMBER = "norPhone";
        public static final String NUMBER = "phone";
        public static final String NUMBER_ALTERNATIVE = "markedNo";
        public static final String NUMBER_NORMALIZED_ALTERNATIVE = "norMarkedNo";
        public static final String PINYIN = "contactName_py";
        public static final String PROVIDER = "provider";
        public static final String Tag = "contactName";
        public static final String VERSION = "version";
        public static final String VISIBLE = "hide";
        public static final int VISIBLE_FALSE = 1;
        public static final int VISIBLE_TRUE = 0;
    }

    public static final class TagProvider {
        public static final int PID_DEFAULT = 0;
        public static final String PNAME_DEFAULT = "MIUI";
    }

    public static final class TagYellowPage {
        public static final String ADDRESS = "address";
        public static final String ALIAS = "aliasName";
        public static final String BRIEF_INFO = "shInfo";
        public static final String BUILTIN = "buildIn";
        public static final int BUILTIN_FALSE = 0;
        public static final String BUILTIN_SYNC_HAS_MORE = "has_more";
        public static final String BUILTIN_SYNC_IMG_DOMAIN = "image_domain";
        public static final String BUILTIN_SYNC_YP_DATA = "ypdata";
        public static final int BUILTIN_TRUE = 1;
        public static final String CAT_ID = "catId";
        public static final String DELETED = "status";
        public static final int DELETED_FALSE = 1;
        public static final int DELETED_TRUE = 0;
        public static final String HOT = "hot";
        public static final String HOT_CAT_ID = "hotCatId";
        public static final int HOT_FALSE = 0;
        public static final String HOT_SORT = "hotSort";
        public static final int HOT_TRUE = 1;
        public static final String KEY = "yp";
        public static final String LATITUDE = "latitude";
        public static final String LOC_ID = "locId";
        public static final String LONGITUDE = "longitude";
        public static final String MIID = "miid";
        public static final String NAME = "sName";
        public static final String PHONE = "phone";
        public static final String PHOTO = "photo";
        public static final String PINYIN = "sName_py";
        public static final String PRESET = "buildIn";
        public static final int PRESET_TRUE = 1;
        public static final String PROVIDER = "provider";
        public static final String SOCIAL = "snsInfo";
        public static final String SOURCE_ID = "sourceId";
        public static final String SOURCE_URL = "sourceUrl";
        public static final String SUSPECT = "needTips";
        public static final int SUSPECT_FLASE = 0;
        public static final int SUSPECT_TRUE = 1;
        public static final String THUMBNAIL = "thumbnail";
        public static final String TYPE = "sType";
        public static final int TYPE_BRANCH = 1;
        public static final int TYPE_MASTER = 2;
        public static final int TYPE_NORMAL = 0;
        public static final String URL = "site";
        public static final String VERSION = "max_version";
        public static final String YID = "sid";
    }

    public static class YellowPagePhone {
        public static final int INVALIDE_YID = -1;
        public static final int TYPE_ANTISPAM = 2;
        public static final int TYPE_MARKED = 3;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_YELLOW_PAGE = 1;
        private boolean mIsSuspectPhone;
        private int mMarkCount;
        private String mNormalizedNumber;
        private String mNumber;
        private int mProviderId;
        private String mTag;
        private String mTagPinyin;
        private int mType;
        private boolean mVisible;
        private long mYpId;
        private String mYpName;
        private String mYpNamePinyin;

        public YellowPagePhone(long ypId, String ypName, String tag, String number, String normalizedNumber, int type, int providerId, int markedCount, boolean visible, String ypNamePinyin, String tagPinyin, boolean suspect) {
            this.mYpName = ypName;
            this.mTag = tag;
            this.mNumber = number;
            this.mType = type;
            this.mVisible = visible;
            this.mMarkCount = markedCount;
            this.mYpId = ypId;
            this.mProviderId = providerId;
            this.mYpNamePinyin = ypNamePinyin;
            this.mTagPinyin = tagPinyin;
            this.mNormalizedNumber = normalizedNumber;
            this.mIsSuspectPhone = suspect;
        }

        public YellowPagePhone(long ypId, String ypName, String tag, String number, String normalizedNumber, int type, int providerId, int markedCount, boolean visible, String ypNamePinyin, String tagPinyin) {
            this(ypId, ypName, tag, number, normalizedNumber, type, providerId, markedCount, visible, ypNamePinyin, tagPinyin, false);
        }

        public boolean isAntispam() {
            return this.mType == TYPE_ANTISPAM;
        }

        public boolean isUserMarked() {
            return this.mType == TYPE_MARKED;
        }

        public boolean isYellowPage() {
            return this.mType == TYPE_YELLOW_PAGE;
        }

        public int getPhoneType() {
            return this.mType;
        }

        public String getYellowPageName() {
            return this.mYpName;
        }

        public long getYellowPageId() {
            return this.mYpId;
        }

        public String getTagPinyin() {
            return this.mTagPinyin;
        }

        public String getYellowPagePinyin() {
            return this.mYpNamePinyin;
        }

        public boolean isUnknown() {
            return this.mType == 0;
        }

        public String getTag() {
            return this.mTag;
        }

        public String getNumber() {
            return this.mNumber;
        }

        public String getNormalizedNumber() {
            return this.mNormalizedNumber;
        }

        public boolean isVisible() {
            return this.mVisible;
        }

        public String getProviderName(Context context) {
            return YellowPageUtils.getProvider(context, this.mProviderId).getName();
        }

        public int getProviderId() {
            return this.mProviderId;
        }

        public boolean isProviderMiui() {
            return this.mProviderId == 0;
        }

        public int getMarkedCount() {
            return this.mMarkCount;
        }

        public boolean isSuspect() {
            return this.mIsSuspectPhone;
        }
    }

    static {
        PHONE_PROJECTION = new String[]{PhoneLookup.YID, PhoneLookup.PROVIDER_ID, PhoneLookup.TAG, PhoneLookup.PHOTO_URL, PhoneLookup.THUMBNAIL_URL, PhoneLookup.YELLOW_PAGE_NAME, PhoneLookup.YELLOW_PAGE_NAME_PINYIN, PhoneLookup.TAG_PINYIN, TagPhone.VISIBLE, PhoneLookup.MIN_MATCH, PhoneLookup.NORMALIZED_NUMBER, PhoneLookup.SUSPECT};
        String[] strArr = new String[PHONE_COLUMN_YP_NAME];
        strArr[PHONE_COLUMN_YID] = Provider.PID;
        strArr[PHONE_COLUMN_PROVIDER_ID] = AntispamNumber.CID;
        strArr[PHONE_COLUMN_TAG] = AntispamNumber.TYPE;
        strArr[PHONE_COLUMN_PHOTO_URL] = AntispamNumber.MARKED_COUNT;
        strArr[PHONE_COLUMN_THUMBNAIL_URL] = PhoneLookup.NORMALIZED_NUMBER;
        ANTISPAM_PROJECTION = strArr;
        sProviders = new HashMap();
        sCidCategories = new HashMap();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int createAntispamCategory(android.content.Context r11, java.lang.String r12) {
        /*
        r5 = 0;
        r3 = 0;
        r0 = android.text.TextUtils.isEmpty(r12);
        if (r0 == 0) goto L_0x000f;
    L_0x0008:
        r0 = "YellowPageUtils";
        r1 = "The category name must not be null";
        android.util.Log.d(r0, r1);
    L_0x000f:
        r0 = r11.getContentResolver();
        r1 = miui.provider.yellowpage.YellowPageUtils.AntispamCategory.CONTENT_URI;
        r2 = 1;
        r2 = new java.lang.String[r2];
        r4 = "MAX(cid)";
        r2[r5] = r4;
        r4 = r3;
        r5 = r3;
        r6 = r0.query(r1, r2, r3, r4, r5);
        r7 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        if (r6 == 0) goto L_0x0038;
    L_0x0026:
        r0 = r6.moveToFirst();	 Catch:{ Exception -> 0x0055 }
        if (r0 == 0) goto L_0x0035;
    L_0x002c:
        r0 = 0;
        r9 = r6.getInt(r0);	 Catch:{ Exception -> 0x0055 }
        if (r9 < r7) goto L_0x0035;
    L_0x0033:
        r7 = r9 + 1;
    L_0x0035:
        r6.close();
    L_0x0038:
        r10 = new android.content.ContentValues;
        r10.<init>();
        r0 = "cid";
        r1 = java.lang.Integer.valueOf(r7);
        r10.put(r0, r1);
        r0 = "names";
        r10.put(r0, r12);
        r0 = r11.getContentResolver();
        r1 = miui.provider.yellowpage.YellowPageUtils.AntispamCategory.CONTENT_URI;
        r0.insert(r1, r10);
        return r7;
    L_0x0055:
        r8 = move-exception;
        r8.printStackTrace();	 Catch:{ all -> 0x005d }
        r6.close();
        goto L_0x0038;
    L_0x005d:
        r0 = move-exception;
        r6.close();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.provider.yellowpage.YellowPageUtils.createAntispamCategory(android.content.Context, java.lang.String):int");
    }

    public static void markAntiSpam(final Context context, final String number, int categoryId, boolean delete) {
        context.getContentResolver().delete(Uri.withAppendedPath(AntispamNumber.CONTENT_PHONE_LOOKUP_URI, number), "type = 3", null);
        if (!delete) {
            ContentValues values = new ContentValues();
            values.put(PhoneLookup.NUMBER, number);
            values.put(PhoneLookup.NORMALIZED_NUMBER, PhoneNumberUtils.normalizeNumber(number));
            values.put(AntispamNumber.CID, Integer.valueOf(categoryId));
            values.put(AntispamNumber.TYPE, Integer.valueOf(PHONE_COLUMN_PHOTO_URL));
            values.put(Provider.PID, Integer.valueOf(PHONE_COLUMN_YID));
            context.getContentResolver().insert(AntispamNumber.CONTENT_URI, values);
        }
        new Thread() {
            public void run() {
                YellowPageUtils.updateCallLogAntispamTag(context, number);
                super.run();
            }
        }.start();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void updateCallLogAntispamTag(android.content.Context r14, java.lang.String r15) {
        /*
        r4 = 0;
        r13 = 1;
        r5 = 0;
        r0 = android.text.TextUtils.isEmpty(r15);
        if (r0 == 0) goto L_0x000a;
    L_0x0009:
        return;
    L_0x000a:
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r0 = "PHONE_NUMBERS_EQUAL(number,";
        r11.append(r0);
        r0 = android.database.DatabaseUtils.sqlEscapeString(r15);
        r11.append(r0);
        r0 = ",0)";
        r11.append(r0);
        r0 = r14.getContentResolver();
        r1 = android.provider.CallLog.Calls.CONTENT_URI;
        r2 = 2;
        r2 = new java.lang.String[r2];
        r3 = "_id";
        r2[r5] = r3;
        r3 = "normalized_number";
        r2[r13] = r3;
        r3 = r11.toString();
        r5 = r4;
        r8 = r0.query(r1, r2, r3, r4, r5);
        if (r8 == 0) goto L_0x0009;
    L_0x003c:
        r6 = new miui.provider.BatchOperation;
        r0 = r14.getContentResolver();
        r1 = "call_log";
        r6.<init>(r0, r1);
        r10 = getLocalPhoneInfo(r14, r15);
    L_0x004b:
        r0 = r8.moveToNext();	 Catch:{ Exception -> 0x00c7 }
        if (r0 == 0) goto L_0x00f0;
    L_0x0051:
        r12 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00c7 }
        r12.<init>();	 Catch:{ Exception -> 0x00c7 }
        r0 = "normalized_number";
        r1 = 1;
        r1 = r8.getString(r1);	 Catch:{ Exception -> 0x00c7 }
        r12.put(r0, r1);	 Catch:{ Exception -> 0x00c7 }
        if (r10 == 0) goto L_0x00e0;
    L_0x0062:
        r0 = "cloud_antispam_type";
        r1 = r10.getPhoneType();	 Catch:{ Exception -> 0x00c7 }
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x00c7 }
        r12.put(r0, r1);	 Catch:{ Exception -> 0x00c7 }
        r0 = "cloud_antispam_type_tag";
        r1 = r10.getTag();	 Catch:{ Exception -> 0x00c7 }
        r12.put(r0, r1);	 Catch:{ Exception -> 0x00c7 }
        r0 = r10.isYellowPage();	 Catch:{ Exception -> 0x00c7 }
        if (r0 == 0) goto L_0x00d0;
    L_0x007e:
        r0 = r10.getYellowPageName();	 Catch:{ Exception -> 0x00c7 }
        r1 = r10.getTag();	 Catch:{ Exception -> 0x00c7 }
        r0 = android.text.TextUtils.equals(r0, r1);	 Catch:{ Exception -> 0x00c7 }
        if (r0 == 0) goto L_0x0091;
    L_0x008c:
        r0 = "cloud_antispam_type_tag";
        r12.putNull(r0);	 Catch:{ Exception -> 0x00c7 }
    L_0x0091:
        r0 = "number_type";
        r1 = 1;
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x00c7 }
        r12.put(r0, r1);	 Catch:{ Exception -> 0x00c7 }
    L_0x009b:
        r0 = android.provider.CallLog.Calls.CONTENT_URI;	 Catch:{ Exception -> 0x00c7 }
        r7 = android.content.ContentProviderOperation.newUpdate(r0);	 Catch:{ Exception -> 0x00c7 }
        r7.withValues(r12);	 Catch:{ Exception -> 0x00c7 }
        r0 = "_id=?";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x00c7 }
        r2 = 0;
        r3 = 0;
        r3 = r8.getString(r3);	 Catch:{ Exception -> 0x00c7 }
        r1[r2] = r3;	 Catch:{ Exception -> 0x00c7 }
        r7.withSelection(r0, r1);	 Catch:{ Exception -> 0x00c7 }
        r0 = r7.build();	 Catch:{ Exception -> 0x00c7 }
        r6.add(r0);	 Catch:{ Exception -> 0x00c7 }
        r0 = r6.size();	 Catch:{ Exception -> 0x00c7 }
        r1 = 100;
        if (r0 <= r1) goto L_0x004b;
    L_0x00c3:
        r6.execute();	 Catch:{ Exception -> 0x00c7 }
        goto L_0x004b;
    L_0x00c7:
        r9 = move-exception;
        r9.printStackTrace();	 Catch:{ all -> 0x00db }
        r8.close();
        goto L_0x0009;
    L_0x00d0:
        r0 = "number_type";
        r1 = 0;
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x00c7 }
        r12.put(r0, r1);	 Catch:{ Exception -> 0x00c7 }
        goto L_0x009b;
    L_0x00db:
        r0 = move-exception;
        r8.close();
        throw r0;
    L_0x00e0:
        r0 = "cloud_antispam_type";
        r1 = 0;
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ Exception -> 0x00c7 }
        r12.put(r0, r1);	 Catch:{ Exception -> 0x00c7 }
        r0 = "cloud_antispam_type_tag";
        r12.putNull(r0);	 Catch:{ Exception -> 0x00c7 }
        goto L_0x009b;
    L_0x00f0:
        r0 = r6.size();	 Catch:{ Exception -> 0x00c7 }
        if (r0 <= 0) goto L_0x00f9;
    L_0x00f6:
        r6.execute();	 Catch:{ Exception -> 0x00c7 }
    L_0x00f9:
        r8.close();
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.provider.yellowpage.YellowPageUtils.updateCallLogAntispamTag(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static miui.provider.yellowpage.YellowPageUtils.Provider getProvider(android.content.Context r15, int r16) {
        /*
        r0 = sProviders;
        r1 = java.lang.Integer.valueOf(r16);
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x0019;
    L_0x000c:
        r0 = sProviders;
        r1 = java.lang.Integer.valueOf(r16);
        r0 = r0.get(r1);
        r0 = (miui.provider.yellowpage.YellowPageUtils.Provider) r0;
    L_0x0018:
        return r0;
    L_0x0019:
        r12 = 0;
        r0 = r15.getContentResolver();
        r1 = miui.provider.yellowpage.YellowPageUtils.Provider.CONTENT_URI;
        r2 = 4;
        r2 = new java.lang.String[r2];
        r3 = 0;
        r4 = "name";
        r2[r3] = r4;
        r3 = 1;
        r4 = "icon";
        r2[r3] = r4;
        r3 = 2;
        r4 = "pid";
        r2[r3] = r4;
        r3 = 3;
        r4 = "icon_big";
        r2[r3] = r4;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = r0.query(r1, r2, r3, r4, r5);
        if (r6 == 0) goto L_0x0083;
    L_0x0040:
        r13 = r12;
    L_0x0041:
        r0 = r6.moveToNext();	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        if (r0 == 0) goto L_0x007f;
    L_0x0047:
        r0 = 0;
        r11 = r6.getString(r0);	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        r0 = 1;
        r10 = r6.getBlob(r0);	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        if (r10 != 0) goto L_0x0071;
    L_0x0053:
        r8 = 0;
    L_0x0054:
        r0 = 3;
        r10 = r6.getBlob(r0);	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        if (r10 != 0) goto L_0x0078;
    L_0x005b:
        r9 = 0;
    L_0x005c:
        r0 = 2;
        r14 = r6.getInt(r0);	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        r12 = new miui.provider.yellowpage.YellowPageUtils$Provider;	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        r12.<init>(r14, r11, r8, r9);	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        r0 = sProviders;	 Catch:{ Exception -> 0x00a6 }
        r1 = java.lang.Integer.valueOf(r14);	 Catch:{ Exception -> 0x00a6 }
        r0.put(r1, r12);	 Catch:{ Exception -> 0x00a6 }
        r13 = r12;
        goto L_0x0041;
    L_0x0071:
        r0 = 0;
        r1 = r10.length;	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        r8 = android.graphics.BitmapFactory.decodeByteArray(r10, r0, r1);	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        goto L_0x0054;
    L_0x0078:
        r0 = 0;
        r1 = r10.length;	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        r9 = android.graphics.BitmapFactory.decodeByteArray(r10, r0, r1);	 Catch:{ Exception -> 0x0095, all -> 0x009e }
        goto L_0x005c;
    L_0x007f:
        r6.close();
        r12 = r13;
    L_0x0083:
        r0 = sProviders;
        r1 = java.lang.Integer.valueOf(r16);
        r12 = r0.get(r1);
        r12 = (miui.provider.yellowpage.YellowPageUtils.Provider) r12;
        if (r12 != 0) goto L_0x0093;
    L_0x0091:
        r12 = miui.provider.yellowpage.YellowPageUtils.Provider.DEFAULT_PROVIDER;
    L_0x0093:
        r0 = r12;
        goto L_0x0018;
    L_0x0095:
        r7 = move-exception;
        r12 = r13;
    L_0x0097:
        r7.printStackTrace();	 Catch:{ all -> 0x00a4 }
        r6.close();
        goto L_0x0083;
    L_0x009e:
        r0 = move-exception;
        r12 = r13;
    L_0x00a0:
        r6.close();
        throw r0;
    L_0x00a4:
        r0 = move-exception;
        goto L_0x00a0;
    L_0x00a6:
        r7 = move-exception;
        goto L_0x0097;
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.provider.yellowpage.YellowPageUtils.getProvider(android.content.Context, int):miui.provider.yellowpage.YellowPageUtils$Provider");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static miui.provider.yellowpage.YellowPageUtils.YellowPagePhone getLocalPhoneInfo(android.content.Context r31, java.lang.String r32) {
        /*
        r1 = r31.getContentResolver();
        r6 = miui.provider.yellowpage.YellowPageUtils.PhoneLookup.CONTENT_URI;
        r0 = r32;
        r2 = android.net.Uri.withAppendedPath(r6, r0);
        r3 = PHONE_PROJECTION;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r28 = r1.query(r2, r3, r4, r5, r6);
        if (r28 == 0) goto L_0x009a;
    L_0x0017:
        r1 = r28.moveToFirst();	 Catch:{ Exception -> 0x015a }
        if (r1 == 0) goto L_0x0097;
    L_0x001d:
        r1 = "YellowPageUtils";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x015a }
        r6.<init>();	 Catch:{ Exception -> 0x015a }
        r8 = "getLocalPhoneInfo:The number ";
        r6 = r6.append(r8);	 Catch:{ Exception -> 0x015a }
        r0 = r32;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x015a }
        r8 = " is a local yellowpage";
        r6 = r6.append(r8);	 Catch:{ Exception -> 0x015a }
        r6 = r6.toString();	 Catch:{ Exception -> 0x015a }
        android.util.Log.d(r1, r6);	 Catch:{ Exception -> 0x015a }
        r1 = 1;
        r0 = r28;
        r9 = r0.getInt(r1);	 Catch:{ Exception -> 0x015a }
        r1 = 2;
        r0 = r28;
        r5 = r0.getString(r1);	 Catch:{ Exception -> 0x015a }
        r1 = 5;
        r0 = r28;
        r4 = r0.getString(r1);	 Catch:{ Exception -> 0x015a }
        r1 = 0;
        r0 = r28;
        r2 = r0.getLong(r1);	 Catch:{ Exception -> 0x015a }
        r1 = 6;
        r0 = r28;
        r12 = r0.getString(r1);	 Catch:{ Exception -> 0x015a }
        r1 = 7;
        r0 = r28;
        r13 = r0.getString(r1);	 Catch:{ Exception -> 0x015a }
        r1 = 10;
        r0 = r28;
        r7 = r0.getString(r1);	 Catch:{ Exception -> 0x015a }
        r1 = 8;
        r0 = r28;
        r1 = r0.getInt(r1);	 Catch:{ Exception -> 0x015a }
        if (r1 != 0) goto L_0x0093;
    L_0x0079:
        r11 = 1;
    L_0x007a:
        r1 = 11;
        r0 = r28;
        r1 = r0.getInt(r1);	 Catch:{ Exception -> 0x015a }
        r6 = 1;
        if (r1 != r6) goto L_0x0095;
    L_0x0085:
        r14 = 1;
    L_0x0086:
        r1 = new miui.provider.yellowpage.YellowPageUtils$YellowPagePhone;	 Catch:{ Exception -> 0x015a }
        r8 = 1;
        r10 = 0;
        r6 = r32;
        r1.<init>(r2, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);	 Catch:{ Exception -> 0x015a }
        r28.close();
    L_0x0092:
        return r1;
    L_0x0093:
        r11 = 0;
        goto L_0x007a;
    L_0x0095:
        r14 = 0;
        goto L_0x0086;
    L_0x0097:
        r28.close();
    L_0x009a:
        r15 = r31.getContentResolver();
        r1 = miui.provider.yellowpage.YellowPageUtils.AntispamNumber.CONTENT_PHONE_LOOKUP_URI;
        r0 = r32;
        r16 = android.net.Uri.withAppendedPath(r1, r0);
        r17 = ANTISPAM_PROJECTION;
        r18 = "type<>4";
        r19 = 0;
        r20 = 0;
        r28 = r15.query(r16, r17, r18, r19, r20);
        if (r28 == 0) goto L_0x0172;
    L_0x00b4:
        r30 = 0;
    L_0x00b6:
        r1 = r28.moveToNext();	 Catch:{ Exception -> 0x016b }
        if (r1 == 0) goto L_0x0153;
    L_0x00bc:
        r1 = "YellowPageUtils";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016b }
        r6.<init>();	 Catch:{ Exception -> 0x016b }
        r8 = "getLocalPhoneInfo:The number ";
        r6 = r6.append(r8);	 Catch:{ Exception -> 0x016b }
        r0 = r32;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x016b }
        r8 = " is a local antispam";
        r6 = r6.append(r8);	 Catch:{ Exception -> 0x016b }
        r6 = r6.toString();	 Catch:{ Exception -> 0x016b }
        android.util.Log.d(r1, r6);	 Catch:{ Exception -> 0x016b }
        r1 = 1;
        r0 = r28;
        r1 = r0.getInt(r1);	 Catch:{ Exception -> 0x016b }
        r0 = r31;
        r19 = getCidName(r0, r1);	 Catch:{ Exception -> 0x016b }
        r1 = android.text.TextUtils.isEmpty(r19);	 Catch:{ Exception -> 0x016b }
        if (r1 != 0) goto L_0x00b6;
    L_0x00ef:
        r1 = 0;
        r0 = r28;
        r9 = r0.getInt(r1);	 Catch:{ Exception -> 0x016b }
        r1 = 2;
        r0 = r28;
        r1 = r0.getInt(r1);	 Catch:{ Exception -> 0x016b }
        r6 = 3;
        if (r1 != r6) goto L_0x0168;
    L_0x0100:
        r22 = 3;
    L_0x0102:
        r1 = 3;
        r0 = r28;
        r24 = r0.getInt(r1);	 Catch:{ Exception -> 0x016b }
        r1 = 4;
        r0 = r28;
        r7 = r0.getString(r1);	 Catch:{ Exception -> 0x016b }
        r15 = new miui.provider.yellowpage.YellowPageUtils$YellowPagePhone;	 Catch:{ Exception -> 0x016b }
        r16 = -1;
        r18 = 0;
        r25 = 1;
        r26 = 0;
        r27 = 0;
        r20 = r32;
        r21 = r7;
        r23 = r9;
        r15.<init>(r16, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27);	 Catch:{ Exception -> 0x016b }
        if (r30 == 0) goto L_0x012c;
    L_0x0127:
        r1 = 3;
        r0 = r22;
        if (r0 != r1) goto L_0x00b6;
    L_0x012c:
        r30 = r15;
        r1 = 3;
        r0 = r22;
        if (r0 != r1) goto L_0x00b6;
    L_0x0133:
        r1 = "YellowPageUtils";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016b }
        r6.<init>();	 Catch:{ Exception -> 0x016b }
        r8 = "The number ";
        r6 = r6.append(r8);	 Catch:{ Exception -> 0x016b }
        r0 = r32;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x016b }
        r8 = " is a local antispam user marked";
        r6 = r6.append(r8);	 Catch:{ Exception -> 0x016b }
        r6 = r6.toString();	 Catch:{ Exception -> 0x016b }
        android.util.Log.d(r1, r6);	 Catch:{ Exception -> 0x016b }
    L_0x0153:
        r28.close();
        r1 = r30;
        goto L_0x0092;
    L_0x015a:
        r29 = move-exception;
        r29.printStackTrace();	 Catch:{ all -> 0x0163 }
        r28.close();
        goto L_0x009a;
    L_0x0163:
        r1 = move-exception;
        r28.close();
        throw r1;
    L_0x0168:
        r22 = 2;
        goto L_0x0102;
    L_0x016b:
        r29 = move-exception;
        r29.printStackTrace();	 Catch:{ all -> 0x0175 }
        r28.close();
    L_0x0172:
        r1 = 0;
        goto L_0x0092;
    L_0x0175:
        r1 = move-exception;
        r28.close();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: miui.provider.yellowpage.YellowPageUtils.getLocalPhoneInfo(android.content.Context, java.lang.String):miui.provider.yellowpage.YellowPageUtils$YellowPagePhone");
    }

    public static String getCidName(Context context, int cid) {
        Category category;
        if (sCidCategories.containsKey(Integer.valueOf(cid))) {
            category = (Category) sCidCategories.get(Integer.valueOf(cid));
            if (category == null) {
                return null;
            }
            return category.getCategoryName();
        }
        getCategories(context);
        category = (Category) sCidCategories.get(Integer.valueOf(cid));
        if (category != null) {
            return category.getCategoryName();
        }
        return null;
    }

    public static List<Category> getCategories(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = AntispamCategory.CONTENT_URI;
        String[] strArr = new String[PHONE_COLUMN_PHOTO_URL];
        strArr[PHONE_COLUMN_YID] = AntispamNumber.CID;
        strArr[PHONE_COLUMN_PROVIDER_ID] = AntispamCategory.NAMES;
        strArr[PHONE_COLUMN_TAG] = AntispamNumber.TYPE;
        Cursor c = contentResolver.query(uri, strArr, null, null, null);
        if (c != null) {
            while (c.moveToNext()) {
                try {
                    int categoryId = c.getInt(PHONE_COLUMN_YID);
                    sCidCategories.put(Integer.valueOf(categoryId), new Category(categoryId, c.getString(PHONE_COLUMN_PROVIDER_ID), c.getInt(PHONE_COLUMN_TAG)));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    c.close();
                }
            }
        }
        if (sCidCategories.values() == null) {
            return null;
        }
        return new ArrayList(sCidCategories.values());
    }
}
