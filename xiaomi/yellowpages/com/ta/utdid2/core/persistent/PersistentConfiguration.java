package com.ta.utdid2.core.persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor;
import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

public class PersistentConfiguration {
    private static final String KEY_TIMESTAMP = "t";
    private static final String KEY_TIMESTAMP2 = "t2";
    private boolean mCanRead;
    private boolean mCanWrite;
    private String mConfigName;
    private Context mContext;
    private Editor mEditor;
    private String mFolderName;
    private boolean mIsLessMode;
    private boolean mIsSafety;
    private MyEditor mMyEditor;
    private MySharedPreferences mMySP;
    private SharedPreferences mSp;
    private TransactionXMLFile mTxf;

    public PersistentConfiguration(Context context, String str, String str2, boolean z, boolean z2) {
        long j;
        Editor edit;
        MyEditor edit2;
        this.mConfigName = ConfigConstant.WIRELESS_FILENAME;
        this.mFolderName = ConfigConstant.WIRELESS_FILENAME;
        this.mIsSafety = false;
        this.mCanRead = false;
        this.mCanWrite = false;
        this.mSp = null;
        this.mMySP = null;
        this.mEditor = null;
        this.mMyEditor = null;
        this.mContext = null;
        this.mTxf = null;
        this.mIsLessMode = false;
        this.mIsSafety = z;
        this.mIsLessMode = z2;
        this.mConfigName = str2;
        this.mFolderName = str;
        this.mContext = context;
        long j2 = 0;
        long j3 = 0;
        if (context != null) {
            this.mSp = context.getSharedPreferences(str2, 0);
            j2 = this.mSp.getLong(KEY_TIMESTAMP, 0);
        }
        String externalStorageState = Environment.getExternalStorageState();
        if (StringUtils.isEmpty(externalStorageState)) {
            this.mCanWrite = false;
            this.mCanRead = false;
        } else if (externalStorageState.equals("mounted")) {
            this.mCanWrite = true;
            this.mCanRead = true;
        } else if (externalStorageState.equals("mounted_ro")) {
            this.mCanRead = true;
            this.mCanWrite = false;
        } else {
            this.mCanWrite = false;
            this.mCanRead = false;
        }
        if (!((!this.mCanRead && !this.mCanWrite) || context == null || StringUtils.isEmpty(str))) {
            this.mTxf = getTransactionXMLFile(str);
            if (this.mTxf != null) {
                try {
                    long j4;
                    this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                    j3 = this.mMySP.getLong(KEY_TIMESTAMP, 0);
                    if (z2) {
                        j = this.mSp.getLong(KEY_TIMESTAMP2, 0);
                        try {
                            j2 = this.mMySP.getLong(KEY_TIMESTAMP2, 0);
                            if (j < j2 && j > 0) {
                                try {
                                    copySPToMySP(this.mSp, this.mMySP);
                                    this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                                    j3 = j;
                                } catch (Exception e) {
                                    j3 = j2;
                                    j2 = j;
                                    if (j2 == j3) {
                                    }
                                    j = System.currentTimeMillis();
                                    if (this.mIsLessMode) {
                                    }
                                    if (this.mSp != null) {
                                        edit = this.mSp.edit();
                                        edit.putLong(KEY_TIMESTAMP2, j);
                                        edit.commit();
                                    }
                                    try {
                                        if (this.mMySP == null) {
                                            edit2 = this.mMySP.edit();
                                            edit2.putLong(KEY_TIMESTAMP2, j);
                                            edit2.commit();
                                        }
                                    } catch (Exception e2) {
                                        return;
                                    }
                                }
                            } else if (j > j2 && j2 > 0) {
                                copyMySPToSP(this.mMySP, this.mSp);
                                this.mSp = context.getSharedPreferences(str2, 0);
                                j3 = j;
                            } else if (j == 0 && j2 > 0) {
                                copyMySPToSP(this.mMySP, this.mSp);
                                this.mSp = context.getSharedPreferences(str2, 0);
                                j3 = j;
                            } else if (j2 != 0 || j <= 0) {
                                if (j == j2) {
                                    copySPToMySP(this.mSp, this.mMySP);
                                    this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                                }
                                j3 = j;
                            } else {
                                copySPToMySP(this.mSp, this.mMySP);
                                this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                                j3 = j;
                            }
                        } catch (Exception e3) {
                            j2 = j3;
                            j3 = j2;
                            j2 = j;
                            if (j2 == j3) {
                            }
                            j = System.currentTimeMillis();
                            if (this.mIsLessMode) {
                            }
                            if (this.mSp != null) {
                                edit = this.mSp.edit();
                                edit.putLong(KEY_TIMESTAMP2, j);
                                edit.commit();
                            }
                            if (this.mMySP == null) {
                                edit2 = this.mMySP.edit();
                                edit2.putLong(KEY_TIMESTAMP2, j);
                                edit2.commit();
                            }
                        }
                    } else if (j2 > j3) {
                        copySPToMySP(this.mSp, this.mMySP);
                        this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                        j4 = j3;
                        j3 = j2;
                        j2 = j4;
                    } else if (j2 < j3) {
                        copyMySPToSP(this.mMySP, this.mSp);
                        this.mSp = context.getSharedPreferences(str2, 0);
                        j4 = j3;
                        j3 = j2;
                        j2 = j4;
                    } else if (j2 == j3) {
                        copySPToMySP(this.mSp, this.mMySP);
                        this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                        j4 = j3;
                        j3 = j2;
                        j2 = j4;
                    } else {
                        j4 = j3;
                        j3 = j2;
                        j2 = j4;
                    }
                    j4 = j2;
                    j2 = j3;
                    j3 = j4;
                } catch (Exception e4) {
                    j = j2;
                    j2 = j3;
                    j3 = j2;
                    j2 = j;
                    if (j2 == j3) {
                    }
                    j = System.currentTimeMillis();
                    if (this.mIsLessMode) {
                    }
                    if (this.mSp != null) {
                        edit = this.mSp.edit();
                        edit.putLong(KEY_TIMESTAMP2, j);
                        edit.commit();
                    }
                    if (this.mMySP == null) {
                        edit2 = this.mMySP.edit();
                        edit2.putLong(KEY_TIMESTAMP2, j);
                        edit2.commit();
                    }
                }
            }
        }
        if (j2 == j3 || (j2 == 0 && j3 == 0)) {
            j = System.currentTimeMillis();
            if (this.mIsLessMode || (this.mIsLessMode && j2 == 0 && j3 == 0)) {
                if (this.mSp != null) {
                    edit = this.mSp.edit();
                    edit.putLong(KEY_TIMESTAMP2, j);
                    edit.commit();
                }
                if (this.mMySP == null) {
                    edit2 = this.mMySP.edit();
                    edit2.putLong(KEY_TIMESTAMP2, j);
                    edit2.commit();
                }
            }
        }
    }

    private TransactionXMLFile getTransactionXMLFile(String str) {
        File rootFolder = getRootFolder(str);
        if (rootFolder == null) {
            return null;
        }
        this.mTxf = new TransactionXMLFile(rootFolder.getAbsolutePath());
        return this.mTxf;
    }

    private File getRootFolder(String str) {
        if (Environment.getExternalStorageDirectory() == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), File.separator, str}));
        if (file == null || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    private void copySPToMySP(SharedPreferences sharedPreferences, MySharedPreferences mySharedPreferences) {
        if (sharedPreferences != null && mySharedPreferences != null) {
            MyEditor edit = mySharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : sharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    private void copyMySPToSP(MySharedPreferences mySharedPreferences, SharedPreferences sharedPreferences) {
        if (mySharedPreferences != null && sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : mySharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    private boolean checkSDCardXMLFile() {
        if (this.mMySP == null) {
            return false;
        }
        boolean checkFile = this.mMySP.checkFile();
        if (checkFile) {
            return checkFile;
        }
        commit();
        return checkFile;
    }

    private void initEditor() {
        if (this.mEditor == null && this.mSp != null) {
            this.mEditor = this.mSp.edit();
        }
        if (this.mCanWrite && this.mMyEditor == null && this.mMySP != null) {
            this.mMyEditor = this.mMySP.edit();
        }
        checkSDCardXMLFile();
    }

    public void putInt(String str, int i) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putInt(str, i);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putInt(str, i);
            }
        }
    }

    public void putLong(String str, long j) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putLong(str, j);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putLong(str, j);
            }
        }
    }

    public void putBoolean(String str, boolean z) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putBoolean(str, z);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putBoolean(str, z);
            }
        }
    }

    public void putFloat(String str, float f) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putFloat(str, f);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putFloat(str, f);
            }
        }
    }

    public void putString(String str, String str2) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putString(str, str2);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putString(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.remove(str);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.remove(str);
            }
        }
    }

    public void reload() {
        if (!(this.mSp == null || this.mContext == null)) {
            this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
        }
        String externalStorageState = Environment.getExternalStorageState();
        if (!StringUtils.isEmpty(externalStorageState)) {
            if (externalStorageState.equals("mounted") || (externalStorageState.equals("mounted_ro") && this.mMySP != null)) {
                try {
                    if (this.mTxf != null) {
                        this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void clear() {
        initEditor();
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mEditor != null) {
            this.mEditor.clear();
            this.mEditor.putLong(KEY_TIMESTAMP, currentTimeMillis);
        }
        if (this.mMyEditor != null) {
            this.mMyEditor.clear();
            this.mMyEditor.putLong(KEY_TIMESTAMP, currentTimeMillis);
        }
    }

    public boolean commit() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mEditor != null) {
            if (!(this.mIsLessMode || this.mSp == null)) {
                this.mEditor.putLong(KEY_TIMESTAMP, currentTimeMillis);
            }
            if (!this.mEditor.commit()) {
                z = false;
            }
        }
        if (!(this.mSp == null || this.mContext == null)) {
            this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
        }
        String externalStorageState = Environment.getExternalStorageState();
        if (!StringUtils.isEmpty(externalStorageState)) {
            if (externalStorageState.equals("mounted")) {
                if (this.mMySP == null) {
                    TransactionXMLFile transactionXMLFile = getTransactionXMLFile(this.mFolderName);
                    if (transactionXMLFile != null) {
                        this.mMySP = transactionXMLFile.getMySharedPreferences(this.mConfigName, 0);
                        if (this.mIsLessMode) {
                            copyMySPToSP(this.mMySP, this.mSp);
                        } else {
                            copySPToMySP(this.mSp, this.mMySP);
                        }
                        this.mMyEditor = this.mMySP.edit();
                    }
                } else if (!(this.mMyEditor == null || this.mMyEditor.commit())) {
                    z = false;
                }
            }
            if (externalStorageState.equals("mounted") || (externalStorageState.equals("mounted_ro") && this.mMySP != null)) {
                try {
                    if (this.mTxf != null) {
                        this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
                    }
                } catch (Exception e) {
                }
            }
        }
        return z;
    }

    public String getString(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            String string = this.mSp.getString(str, ConfigConstant.WIRELESS_FILENAME);
            if (!StringUtils.isEmpty(string)) {
                return string;
            }
        }
        if (this.mMySP != null) {
            return this.mMySP.getString(str, ConfigConstant.WIRELESS_FILENAME);
        }
        return ConfigConstant.WIRELESS_FILENAME;
    }

    public int getInt(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getInt(str, 0);
        }
        if (this.mMySP != null) {
            return this.mMySP.getInt(str, 0);
        }
        return 0;
    }

    public long getLong(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getLong(str, 0);
        }
        if (this.mMySP != null) {
            return this.mMySP.getLong(str, 0);
        }
        return 0;
    }

    public float getFloat(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getFloat(str, 0.0f);
        }
        if (this.mMySP != null) {
            return this.mMySP.getFloat(str, 0.0f);
        }
        return 0.0f;
    }

    public boolean getBoolean(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getBoolean(str, false);
        }
        if (this.mMySP != null) {
            return this.mMySP.getBoolean(str, false);
        }
        return false;
    }

    public Map getAll() {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getAll();
        }
        if (this.mMySP != null) {
            return this.mMySP.getAll();
        }
        return null;
    }
}
