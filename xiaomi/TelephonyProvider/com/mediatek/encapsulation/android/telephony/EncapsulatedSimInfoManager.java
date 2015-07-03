package com.mediatek.encapsulation.android.telephony;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import com.android.common.speech.LoggingEvents;
import com.mediatek.telephony.SimInfoManager;
import com.mediatek.telephony.SimInfoManager.SimInfoRecord;
import java.util.ArrayList;
import java.util.List;

public class EncapsulatedSimInfoManager implements BaseColumns {
    public static final String COLOR = "color";
    public static final int COLOR_1 = 0;
    public static final int COLOR_2 = 1;
    public static final int COLOR_3 = 2;
    public static final int COLOR_4 = 3;
    public static final int COLOR_DEFAULT = 0;
    public static final Uri CONTENT_URI;
    public static final String DATA_ROAMING = "data_roaming";
    public static final int DATA_ROAMING_DEFAULT = 0;
    public static final int DATA_ROAMING_DISABLE = 0;
    public static final int DATA_ROAMING_ENABLE = 1;
    public static final int DEFAULT_NAME_MAX_INDEX = 99;
    public static final int DEFAULT_NAME_MIN_INDEX = 1;
    public static final int DEFAULT_NAME_RES = 33882260;
    public static final String DEFAULT_SORT_ORDER = "name ASC";
    public static final int DISLPAY_NUMBER_DEFAULT = 1;
    public static final int DISPALY_NUMBER_NONE = 0;
    public static final String DISPLAY_NAME = "display_name";
    public static final int DISPLAY_NUMBER_FIRST = 1;
    public static final String DISPLAY_NUMBER_FORMAT = "display_number_format";
    public static final int DISPLAY_NUMBER_LAST = 2;
    public static final int ERROR_GENERAL = -1;
    public static final int ERROR_NAME_EXIST = -2;
    public static final String ICC_ID = "icc_id";
    public static final String NUMBER = "number";
    public static final int[] SIMBackgroundRes;
    public static final String SLOT = "slot";
    public static final int SLOT_NONE = -1;
    private int mColor;
    private int mDataRoaming;
    private int mDispalyNumberFormat;
    private String mDisplayName;
    public String mIccId;
    private String mNumber;
    private SimInfoRecord mSIMInfoRecord;
    private int mSimBackgroundRes;
    public long mSimInfoId;
    public int mSimSlotId;
    private int mSlot;

    public static class ErrorCode {
        public static final int ERROR_GENERAL = -1;
        public static final int ERROR_NAME_EXIST = -2;
    }

    static {
        CONTENT_URI = SimInfoManager.CONTENT_URI;
        SIMBackgroundRes = new int[]{33685722, 33685725, 33685723, 33685726};
    }

    private EncapsulatedSimInfoManager() {
        this.mDisplayName = LoggingEvents.EXTRA_CALLING_APP_NAME;
        this.mNumber = LoggingEvents.EXTRA_CALLING_APP_NAME;
        this.mDispalyNumberFormat = DISPLAY_NUMBER_FIRST;
        this.mSimSlotId = SLOT_NONE;
        this.mDataRoaming = DISPALY_NUMBER_NONE;
        this.mSlot = SLOT_NONE;
        this.mSimBackgroundRes = SIMBackgroundRes[DISPALY_NUMBER_NONE];
    }

    public EncapsulatedSimInfoManager(SimInfoRecord simInfoRecord) {
        this.mDisplayName = LoggingEvents.EXTRA_CALLING_APP_NAME;
        this.mNumber = LoggingEvents.EXTRA_CALLING_APP_NAME;
        this.mDispalyNumberFormat = DISPLAY_NUMBER_FIRST;
        this.mSimSlotId = SLOT_NONE;
        this.mDataRoaming = DISPALY_NUMBER_NONE;
        this.mSlot = SLOT_NONE;
        this.mSimBackgroundRes = SIMBackgroundRes[DISPALY_NUMBER_NONE];
        if (simInfoRecord != null) {
            this.mSIMInfoRecord = simInfoRecord;
        }
    }

    public long getSimId() {
        return this.mSIMInfoRecord.mSimInfoId;
    }

    public String getICCId() {
        return this.mSIMInfoRecord.mIccId;
    }

    public String getDisplayName() {
        return this.mSIMInfoRecord.mDisplayName;
    }

    public String getNumber() {
        return this.mSIMInfoRecord.mNumber;
    }

    public int getDispalyNumberFormat() {
        return this.mSIMInfoRecord.mDispalyNumberFormat;
    }

    public int getColor() {
        return this.mSIMInfoRecord.mColor;
    }

    public int getDataRoaming() {
        return this.mSIMInfoRecord.mDataRoaming;
    }

    public int getSlot() {
        return this.mSIMInfoRecord.mSimSlotId;
    }

    public int getSimBackgroundRes() {
        return this.mSIMInfoRecord.mSimBackgroundRes;
    }

    private static EncapsulatedSimInfoManager fromCursor(Cursor cursor) {
        EncapsulatedSimInfoManager info = new EncapsulatedSimInfoManager();
        info.mSimInfoId = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
        info.mIccId = cursor.getString(cursor.getColumnIndexOrThrow(ICC_ID));
        info.mDisplayName = cursor.getString(cursor.getColumnIndexOrThrow(DISPLAY_NAME));
        info.mNumber = cursor.getString(cursor.getColumnIndexOrThrow(NUMBER));
        info.mDispalyNumberFormat = cursor.getInt(cursor.getColumnIndexOrThrow(DISPLAY_NUMBER_FORMAT));
        info.mColor = cursor.getInt(cursor.getColumnIndexOrThrow(COLOR));
        info.mDataRoaming = cursor.getInt(cursor.getColumnIndexOrThrow(DATA_ROAMING));
        info.mSimSlotId = cursor.getInt(cursor.getColumnIndexOrThrow(SLOT));
        int size = SIMBackgroundRes.length;
        if (info.mColor >= 0 && info.mColor < size) {
            info.mSimBackgroundRes = SIMBackgroundRes[info.mColor];
        }
        return info;
    }

    public static List<EncapsulatedSimInfoManager> getInsertedSimInfoList(Context ctx) {
        List<SimInfoRecord> oldSimList = SimInfoManager.getInsertedSimInfoList(ctx);
        ArrayList<EncapsulatedSimInfoManager> newSimList = new ArrayList();
        for (int i = DISPALY_NUMBER_NONE; i < oldSimList.size(); i += DISPLAY_NUMBER_FIRST) {
            newSimList.add(new EncapsulatedSimInfoManager((SimInfoRecord) oldSimList.get(i)));
        }
        return newSimList;
    }

    public static List<EncapsulatedSimInfoManager> getAllSIMList(Context ctx) {
        List<SimInfoRecord> oldSimList = SimInfoManager.getAllSimInfoList(ctx);
        ArrayList<EncapsulatedSimInfoManager> newSimList = new ArrayList();
        for (int i = DISPALY_NUMBER_NONE; i < oldSimList.size(); i += DISPLAY_NUMBER_FIRST) {
            newSimList.add(new EncapsulatedSimInfoManager((SimInfoRecord) oldSimList.get(i)));
        }
        return newSimList;
    }

    public static EncapsulatedSimInfoManager getSIMInfoById(Context ctx, long SIMId) {
        SimInfoRecord siminfo = SimInfoManager.getSimInfoById(ctx, SIMId);
        if (siminfo == null) {
            return null;
        }
        return new EncapsulatedSimInfoManager(siminfo);
    }

    public static EncapsulatedSimInfoManager getSIMInfoByName(Context ctx, String SIMName) {
        SimInfoRecord siminfo = SimInfoManager.getSimInfoByName(ctx, SIMName);
        if (siminfo == null) {
            return null;
        }
        return new EncapsulatedSimInfoManager(siminfo);
    }

    public static EncapsulatedSimInfoManager getSimInfoBySlot(Context ctx, int cardSlot) {
        SimInfoRecord siminfo = SimInfoManager.getSimInfoBySlot(ctx, cardSlot);
        if (siminfo == null) {
            return null;
        }
        return new EncapsulatedSimInfoManager(siminfo);
    }

    public static EncapsulatedSimInfoManager getSIMInfoByICCId(Context ctx, String iccid) {
        SimInfoRecord siminfo = SimInfoManager.getSimInfoByIccId(ctx, iccid);
        if (siminfo == null) {
            return null;
        }
        return new EncapsulatedSimInfoManager(siminfo);
    }

    public static int getSlotById(Context ctx, long SIMId) {
        SimInfoRecord simInfo = SimInfoManager.getSimInfoById(ctx, SIMId);
        if (simInfo != null) {
            return simInfo.mSimSlotId;
        }
        return SLOT_NONE;
    }

    public static long getIdBySlot(Context ctx, int slot) {
        SimInfoRecord simInfo = SimInfoManager.getSimInfoBySlot(ctx, slot);
        if (simInfo != null) {
            return simInfo.mSimInfoId;
        }
        return -1;
    }

    public static int getSlotByName(Context ctx, String SIMName) {
        SimInfoRecord simInfo = SimInfoManager.getSimInfoByName(ctx, SIMName);
        if (simInfo != null) {
            return simInfo.mSimSlotId;
        }
        return SLOT_NONE;
    }

    public static int getInsertedSIMCount(Context ctx) {
        return SimInfoManager.getInsertedSimCount(ctx);
    }

    public static int getAllSIMCount(Context ctx) {
        return SimInfoManager.getAllSimCount(ctx);
    }

    public static int setDisplayName(Context ctx, String displayName, long SIMId) {
        return SimInfoManager.setDisplayName(ctx, displayName, SIMId);
    }

    public static int setNumber(Context ctx, String number, long SIMId) {
        return SimInfoManager.setNumber(ctx, number, SIMId);
    }

    public static int setColor(Context ctx, int color, long SIMId) {
        return SimInfoManager.setColor(ctx, color, SIMId);
    }

    public static int setDispalyNumberFormat(Context ctx, int format, long SIMId) {
        return SimInfoManager.setDispalyNumberFormat(ctx, format, SIMId);
    }

    public static int setDataRoaming(Context ctx, int roaming, long SIMId) {
        return SimInfoManager.setDataRoaming(ctx, roaming, SIMId);
    }

    public static int setDefaultName(Context ctx, long simId, String name) {
        return SimInfoManager.setDefaultName(ctx, simId, name);
    }

    private static String getSuffixFromIndex(int index) {
        if (index < 10) {
            return "0" + index;
        }
        return String.valueOf(index);
    }

    private static int getAppropriateIndex(Context ctx, long simId, String name) {
        String default_name = ctx.getString(DEFAULT_NAME_RES);
        StringBuilder sb = new StringBuilder("display_name LIKE ");
        if (name == null) {
            DatabaseUtils.appendEscapedSQLString(sb, default_name + '%');
        } else {
            DatabaseUtils.appendEscapedSQLString(sb, name + '%');
        }
        sb.append(" AND (");
        sb.append("_id!=" + simId);
        sb.append(")");
        ContentResolver contentResolver = ctx.getContentResolver();
        Uri uri = SimInfoManager.CONTENT_URI;
        String[] strArr = new String[DISPLAY_NUMBER_LAST];
        strArr[DISPALY_NUMBER_NONE] = "_id";
        strArr[DISPLAY_NUMBER_FIRST] = DISPLAY_NAME;
        Cursor cursor = contentResolver.query(uri, strArr, sb.toString(), null, DISPLAY_NAME);
        ArrayList<Long> array = new ArrayList();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String display_name = cursor.getString(DISPLAY_NUMBER_FIRST);
                if (display_name != null) {
                    int length = display_name.length();
                    if (length >= DISPLAY_NUMBER_LAST) {
                        String sub = display_name.substring(length + ERROR_NAME_EXIST);
                        if (TextUtils.isDigitsOnly(sub)) {
                            array.add(Long.valueOf(Long.valueOf(sub).longValue()));
                        }
                    }
                }
            }
            cursor.close();
        }
        for (int i = DISPLAY_NUMBER_FIRST; i <= DEFAULT_NAME_MAX_INDEX; i += DISPLAY_NUMBER_FIRST) {
            if (!array.contains(Long.valueOf((long) i))) {
                return i;
            }
        }
        return DISPLAY_NUMBER_FIRST;
    }
}
