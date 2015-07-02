package com.miui.yellowpage.openapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.c;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.request.BaseResult;
import java.util.HashMap;
import java.util.Map;
import miui.yellowpage.YellowPageContract.Permission;

public class PermissionManager {
    private static final String[] PROJECTION;
    private static final int REQUEST_CHECK_PERMISSION = 0;
    private static final int STATE_INDEX = 0;
    private static PermissionManager sInstance;
    private d mLoader;
    private Map mPermissions;

    public interface OnCheckFinishedListener {
        public static final int NOT_FOUND = -1;

        void onCheckFinished(int i);
    }

    class PermissionCheckCallBack implements c {
        private OnCheckFinishedListener mFinishedListener;

        private PermissionCheckCallBack() {
        }

        public void onPreRequest(int i) {
        }

        public void onRequestFinished(int i, BaseResult baseResult) {
            if (i == 0) {
                PermissionCheckResult permissionCheckResult = (PermissionCheckResult) baseResult;
                if (permissionCheckResult.hasData()) {
                    this.mFinishedListener.onCheckFinished(permissionCheckResult.mState.intValue());
                }
            }
        }

        public BaseResult onParseRequest(int i, Object obj, BaseResult baseResult) {
            PermissionCheckResult permissionCheckResult = (PermissionCheckResult) baseResult;
            if (i == 0) {
                Cursor cursor = (Cursor) obj;
                if (cursor == null) {
                    try {
                        permissionCheckResult.mState = null;
                    } catch (Throwable th) {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                } else if (cursor.moveToFirst()) {
                    permissionCheckResult.mState = Integer.valueOf(cursor.getInt(0));
                } else {
                    permissionCheckResult.mState = Integer.valueOf(-1);
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
            return permissionCheckResult;
        }
    }

    class PermissionCheckResult extends BaseResult {
        private Integer mState;

        private PermissionCheckResult() {
        }

        public boolean hasData() {
            return this.mState != null;
        }

        public BaseResult shallowClone() {
            BaseResult permissionCheckResult = new PermissionCheckResult();
            permissionCheckResult.mState = this.mState;
            return permissionCheckResult;
        }
    }

    static {
        PROJECTION = new String[]{"state"};
    }

    public static synchronized PermissionManager getInstance(Context context) {
        PermissionManager permissionManager;
        synchronized (PermissionManager.class) {
            if (sInstance == null) {
                sInstance = new PermissionManager();
                sInstance.initialize(context);
            }
            permissionManager = sInstance;
        }
        return permissionManager;
    }

    private PermissionManager() {
        this.mPermissions = new HashMap();
    }

    public void check(Context context, String str, PermissionInfo permissionInfo, OnCheckFinishedListener onCheckFinishedListener) {
        if (permissionInfo != null && str != null && onCheckFinishedListener != null && context != null) {
            if (this.mLoader == null) {
                this.mLoader = new d();
            }
            c permissionCheckCallBack = new PermissionCheckCallBack();
            permissionCheckCallBack.mFinishedListener = onCheckFinishedListener;
            this.mLoader.a(permissionCheckCallBack);
            this.mLoader.a(getPermissionCheckRequest(context, str, permissionInfo.getKey()), new PermissionCheckResult());
        }
    }

    public PermissionInfo getPermissionInfoByKey(String str) {
        return (PermissionInfo) this.mPermissions.get(str);
    }

    private void initialize(Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array.permission_keys);
        String[] stringArray2 = context.getResources().getStringArray(R.array.permission_labels);
        for (int i = 0; i < stringArray.length; i++) {
            this.mPermissions.put(stringArray[i], new PermissionInfo(stringArray[i], stringArray2[i]));
        }
    }

    public void insert(Context context, String str, String str2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", Integer.valueOf(i));
        contentValues.put(MiniDefine.aL, str);
        contentValues.put("permission", str2);
        context.getContentResolver().insert(Permission.CONTENT_URI, contentValues);
    }

    public void update(Context context, String str, String str2, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", Integer.valueOf(i));
        context.getContentResolver().update(Permission.CONTENT_URI, contentValues, "host =? AND permission=?", new String[]{str, str2});
    }

    public void delete(Context context, String str, String str2) {
        context.getContentResolver().delete(Permission.CONTENT_URI, "host =? AND permission=?", new String[]{str, str2});
    }

    private BaseRequest getPermissionCheckRequest(Context context, String str, String str2) {
        LocalRequest localRequest = new LocalRequest(context, 0);
        localRequest.setUri(Permission.CONTENT_URI);
        localRequest.setProjection(PROJECTION);
        localRequest.setSelection("host=? AND permission=?");
        localRequest.setArgs(new String[]{str, str2});
        return localRequest;
    }
}
