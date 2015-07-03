package com.android.providers.telephony;

import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.UriMatcher;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.os.Environment;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings.System;
import android.provider.Telephony;
import android.provider.Telephony.Carriers.GeminiCarriers;
import android.provider.Telephony.Carriers.SIM1Carriers;
import android.provider.Telephony.Carriers.SIM2Carriers;
import android.provider.Telephony.Carriers.SIM3Carriers;
import android.provider.Telephony.Carriers.SIM4Carriers;
import android.provider.Telephony.GprsInfo;
import android.provider.Telephony.SimInfo;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.android.common.speech.LoggingEvents;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.util.XmlUtils;
import com.mediatek.common.MediatekClassFactory;
import com.mediatek.common.telephony.ITelephonyEx;
import com.mediatek.common.telephony.ITelephonyEx.Stub;
import com.mediatek.common.telephony.ITelephonyProviderExt;
import com.mediatek.encapsulation.android.telephony.EncapsulatedSimInfoManager;
import com.mediatek.encapsulation.android.telephony.EncapsulatedTelephony.Carriers;
import com.mediatek.telephony.SimInfoManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import miui.provider.yellowpage.YellowPageUtils.AntispamNumber;
import miui.provider.yellowpage.YellowPageUtils.Provider;
import miui.provider.yellowpage.YellowPageUtils.TagPhone;
import miui.telephony.MultiSimManager;
import miui.telephony.SpnOverride;
import miui.telephony.TelephonyManager;
import miui.telephony.TelephonyManagerFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TelephonyProvider extends ContentProvider {
    private static final String CARRIERS_DM_TABLE = "carriers_dm";
    private static final String CARRIERS_DM_TABLE_GEMINI = "carriers_dm_gemini";
    private static final String CARRIERS_TABLE = "carriers";
    private static final String[] CARRIERS_TABLE_GEMINI;
    private static final String COLUMN_APN_ID = "apn_id";
    private static final String DATABASE_NAME = "telephony.db";
    private static final int DATABASE_VERSION = 1048576;
    private static final boolean DBG = true;
    private static final String GPRSINFO_TABLE = "gprsinfo";
    private static final String PARTNER_APNS_PATH = "etc/apns-conf.xml";
    private static final String PREF_FILE = "preferred-apn";
    private static final String[] PREF_FILE_GEMINI;
    private static final String PREF_LOAD_APN = "load-apn";
    private static final String[] PREF_LOAD_APN_SLOT_KEY;
    private static final String PREF_TETHERING_FILE = "preferred-tethering-apn";
    private static final String[] PREF_TETHERING_FILE_GEMINI;
    private static final String SIMINFO_TABLE = "siminfo";
    private static final String TAG = "TelephonyProvider";
    private static final Uri[] URI_GEMINI;
    private static final int[] URL_CARD_GEMINI;
    private static final int URL_CURRENT = 2;
    private static final int URL_GPRSINFO = 1001;
    private static final int URL_GPRSINFO_ID = 1002;
    private static final int URL_ID = 3;
    private static final int URL_ID_DM = 22;
    private static final int URL_ID_DM_GEMINI = 26;
    private static final int URL_MAIN_CARD = 1;
    private static final int URL_PREFERAPN = 5;
    private static final int URL_PREFERAPN_NO_UPDATE = 6;
    private static final int URL_PREFERAPN_W_SUB_ID = 7;
    private static final int URL_PREFERTETHERINGAPN = 11;
    private static final int URL_RESTOREAPN = 4;
    private static final int URL_SIMINFO = 101;
    private static final int URL_SIMINFO_ID = 102;
    private static final int URL_TELEPHONY = 1;
    private static final int URL_TELEPHONY_DM = 21;
    private static final int URL_TELEPHONY_DM_GEMINI = 25;
    private static final String[] carriersUri;
    private static final boolean[] mInitAPNGemini;
    private static final ContentValues s_currentNullMap;
    private static final ContentValues s_currentSetMap;
    private static final UriMatcher s_urlMatcher;
    private static final UriMatcher[] s_urlMatcherGemini;
    private ITelephonyEx iTelephony;
    private BroadcastReceiver mBroadcastReceiver;
    private Object[] mLock;
    private DatabaseHelper mOpenHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private SQLiteStatement[] mCarrierIntertGeminiStatement;
        private Context mContext;
        private ITelephonyProviderExt mTelephonyProviderExt;

        public DatabaseHelper(Context context) {
            super(context, TelephonyProvider.DATABASE_NAME, null, getVersion(context));
            this.mCarrierIntertGeminiStatement = new SQLiteStatement[PhoneConstants.GEMINI_SIM_NUM];
            this.mContext = context;
            try {
                Object[] objArr = new Object[TelephonyProvider.URL_TELEPHONY];
                objArr[0] = this.mContext;
                this.mTelephonyProviderExt = (ITelephonyProviderExt) MediatekClassFactory.createInstance(ITelephonyProviderExt.class, objArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                this.mCarrierIntertGeminiStatement[i] = null;
            }
        }

        private static int getVersion(Context context) {
            int i = TelephonyProvider.DATABASE_VERSION;
            XmlResourceParser parser = context.getResources().getXml(17760256);
            try {
                XmlUtils.beginDocument(parser, "apns");
                i = TelephonyProvider.DATABASE_VERSION | Integer.parseInt(parser.getAttributeValue(null, TagPhone.VERSION));
            } catch (Exception e) {
                Log.e(TelephonyProvider.TAG, "Can't get version of APN database", e);
            } finally {
                parser.close();
            }
            return i;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE siminfo ( _id INTEGER PRIMARY KEY AUTOINCREMENT,display_name TEXT,name_source INTEGER DEFAULT 0,number TEXT,display_number_format INTEGER NOT NULL DEFAULT 1,icc_id TEXT NOT NULL,color INTEGER DEFAULT 0,slot INTEGER DEFAULT -1,operator TEXT,data_roaming INTEGER DEFAULT 0);");
            db.execSQL("CREATE TABLE gprsinfo ( _id INTEGER PRIMARY KEY AUTOINCREMENT,sim_id INTEGER REFERENCES siminfo(_id) NOT NULL,gprs_in INTEGER DEFAULT 0,gprs_out INTEGER DEFAULT 0);");
            String columns = "(_id INTEGER PRIMARY KEY,name TEXT,numeric TEXT,mcc TEXT,mnc TEXT,apn TEXT,user TEXT,server TEXT,password TEXT,proxy TEXT,port TEXT,mmsproxy TEXT,mmsport TEXT,mmsc TEXT,authtype INTEGER,type TEXT,current INTEGER,sourcetype INTEGER,csdnum TEXT,protocol TEXT,roaming_protocol TEXT,omacpid TEXT,napid TEXT,proxyid TEXT,carrier_enabled BOOLEAN,bearer INTEGER,spn TEXT,imsi TEXT,pnn TEXT,ppp TEXT,mvno_type TEXT,mvno_match_data TEXT);";
            for (int i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                db.execSQL("CREATE TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + columns);
                db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN slot_id INTEGER DEFAULT " + i + ";");
            }
            db.execSQL("CREATE TABLE carriers_dm" + columns);
            db.execSQL("CREATE TABLE carriers_dm_gemini" + columns);
        }

        public void initDatabase(SQLiteDatabase db, String table) {
            Exception e;
            Throwable th;
            XmlResourceParser parser = this.mContext.getResources().getXml(17760256);
            int publicversion = -1;
            try {
                XmlUtils.beginDocument(parser, "apns");
                publicversion = Integer.parseInt(parser.getAttributeValue(null, TagPhone.VERSION));
                loadApns(db, parser, table);
            } catch (Exception e2) {
                Log.e(TelephonyProvider.TAG, "Got exception while loading APN database.", e2);
            } finally {
                parser.close();
            }
            File confFile = new File(Environment.getRootDirectory(), TelephonyProvider.PARTNER_APNS_PATH);
            FileReader confreader = null;
            try {
                FileReader confreader2 = new FileReader(confFile);
                try {
                    XmlPullParser confparser = Xml.newPullParser();
                    confparser.setInput(confreader2);
                    XmlUtils.beginDocument(confparser, "apns");
                    if (publicversion != Integer.parseInt(confparser.getAttributeValue(null, TagPhone.VERSION))) {
                        throw new IllegalStateException("Internal APNS file version doesn't match " + confFile.getAbsolutePath());
                    }
                    db.beginTransaction();
                    loadApns(db, confparser, table);
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    if (confreader2 != null) {
                        try {
                            confreader2.close();
                        } catch (IOException e3) {
                            confreader = confreader2;
                            return;
                        }
                    }
                    confreader = confreader2;
                } catch (FileNotFoundException e4) {
                    confreader = confreader2;
                    if (confreader != null) {
                        try {
                            confreader.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (Exception e6) {
                    e2 = e6;
                    confreader = confreader2;
                    try {
                        Log.e(TelephonyProvider.TAG, "Exception while parsing '" + confFile.getAbsolutePath() + "'", e2);
                        if (confreader != null) {
                            try {
                                confreader.close();
                            } catch (IOException e7) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (confreader != null) {
                            try {
                                confreader.close();
                            } catch (IOException e8) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    confreader = confreader2;
                    if (confreader != null) {
                        confreader.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e9) {
                if (confreader != null) {
                    confreader.close();
                }
            } catch (Exception e10) {
                e2 = e10;
                Log.e(TelephonyProvider.TAG, "Exception while parsing '" + confFile.getAbsolutePath() + "'", e2);
                if (confreader != null) {
                    confreader.close();
                }
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            int i;
            if (oldVersion < 327686) {
                for (i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                    db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN authtype INTEGER DEFAULT -1;");
                }
                oldVersion = 327686;
            }
            if (oldVersion < 393222) {
                for (i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                    db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN protocol TEXT DEFAULT IP;");
                    db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN roaming_protocol TEXT DEFAULT IP;");
                }
                oldVersion = 393222;
            }
            if (oldVersion < 458758) {
                oldVersion = 458758;
            }
            if (oldVersion < 524294) {
                for (i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                    db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN carrier_enabled BOOLEAN DEFAULT 1;");
                    db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN bearer INTEGER DEFAULT 0;");
                }
                int size = SimInfoManager.SimBackgroundRes.length;
                Log.e(TelephonyProvider.TAG, "Update GB to ICS, color size " + size);
                db.execSQL("UPDATE siminfo SET color=color%" + size + ";");
                oldVersion = 524294;
            }
            if (oldVersion < 589830) {
                for (i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                    try {
                        db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN spn TEXT;");
                        db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN imsi TEXT;");
                        Log.d(TelephonyProvider.TAG, "Update ICS to JB, add MVNO columns");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        Log.e(TelephonyProvider.TAG, "Add MVNO columns fail with table " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + ".");
                    }
                }
                oldVersion = 589830;
            }
            if (oldVersion < 655366) {
                try {
                    db.execSQL("ALTER TABLE siminfo ADD COLUMN name_source INTEGER DEFAULT 0;");
                    Log.d(TelephonyProvider.TAG, "Update JB, add SIMInfo name_source columns");
                } catch (SQLException e2) {
                    e2.printStackTrace();
                    Log.e(TelephonyProvider.TAG, "Add SIMInfo name_source columns fail.");
                }
                oldVersion = 655366;
            }
            if (oldVersion < 720902) {
                for (i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                    try {
                        db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN pnn TEXT;");
                        Log.d(TelephonyProvider.TAG, "Update ICS to JB, add MVNO columns");
                    } catch (SQLException e22) {
                        e22.printStackTrace();
                        Log.e(TelephonyProvider.TAG, "Add MVNO columns fail with table " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + ".");
                    }
                }
                oldVersion = 720902;
            }
            if (oldVersion < 786438) {
                try {
                    db.execSQL("ALTER TABLE siminfo ADD COLUMN operator TEXT;");
                    Log.d(TelephonyProvider.TAG, "Update JB2, add SIMInfo operator columns");
                } catch (SQLException e222) {
                    e222.printStackTrace();
                    Log.e(TelephonyProvider.TAG, "Add SIMInfo operator columns fail.");
                }
                oldVersion = 786438;
            }
            if (oldVersion < 851974) {
                for (i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                    try {
                        db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN ppp TEXT;");
                        Log.d(TelephonyProvider.TAG, "Update ppp column");
                    } catch (SQLException e2222) {
                        e2222.printStackTrace();
                        Log.e(TelephonyProvider.TAG, "Add ppp column fail with table " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + ".");
                    }
                }
                oldVersion = 851974;
            }
            if (oldVersion < 917510) {
                for (i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                    try {
                        db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN mvno_type TEXT DEFAULT '';");
                        db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN mvno_match_data TEXT DEFAULT '';");
                        Log.d(TelephonyProvider.TAG, "Update mvno column");
                    } catch (SQLException e22222) {
                        e22222.printStackTrace();
                        Log.e(TelephonyProvider.TAG, "Add mvno column fail with table " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + ".");
                    }
                }
                oldVersion = 917510;
            }
            if (oldVersion < 983046) {
                for (i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                    try {
                        db.execSQL("ALTER TABLE " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + " ADD COLUMN slot_id INTEGER DEFAULT " + i + ";");
                    } catch (SQLException e222222) {
                        e222222.printStackTrace();
                        Log.e(TelephonyProvider.TAG, "Add slot_id column fail with table " + TelephonyProvider.CARRIERS_TABLE_GEMINI[i] + ".");
                    }
                }
                oldVersion = 983046;
            }
            if (oldVersion < 1048582) {
                TelephonyProvider.updateFromV5(db);
            }
        }

        private ContentValues getRow(XmlPullParser parser) {
            if (!"apn".equals(parser.getName())) {
                return null;
            }
            ContentValues map = new ContentValues();
            String mcc = parser.getAttributeValue(null, "mcc");
            String mnc = parser.getAttributeValue(null, "mnc");
            String str = "numeric";
            map.put(r23, mcc + mnc);
            map.put("mcc", mcc);
            map.put("mnc", mnc);
            map.put(Provider.NAME, parser.getAttributeValue(null, "carrier"));
            str = "apn";
            map.put(r23, parser.getAttributeValue(null, "apn"));
            str = "user";
            map.put(r23, parser.getAttributeValue(null, "user"));
            str = "server";
            map.put(r23, parser.getAttributeValue(null, "server"));
            str = "password";
            map.put(r23, parser.getAttributeValue(null, "password"));
            map.put(Carriers.SOURCE_TYPE, Integer.valueOf(0));
            String proxy = parser.getAttributeValue(null, "proxy");
            if (proxy != null) {
                map.put("proxy", proxy);
            }
            String port = parser.getAttributeValue(null, "port");
            if (port != null) {
                map.put("port", port);
            }
            String mmsproxy = parser.getAttributeValue(null, "mmsproxy");
            if (mmsproxy != null) {
                map.put("mmsproxy", mmsproxy);
            }
            String mmsport = parser.getAttributeValue(null, "mmsport");
            if (mmsport != null) {
                map.put("mmsport", mmsport);
            }
            str = "mmsc";
            map.put(r23, parser.getAttributeValue(null, "mmsc"));
            String type = parser.getAttributeValue(null, AntispamNumber.TYPE);
            if (type != null) {
                map.put(AntispamNumber.TYPE, type);
            }
            String auth = parser.getAttributeValue(null, "authtype");
            if (auth != null) {
                map.put("authtype", Integer.valueOf(Integer.parseInt(auth)));
            }
            String protocol = parser.getAttributeValue(null, "protocol");
            if (protocol != null) {
                map.put("protocol", protocol);
            }
            String roamingProtocol = parser.getAttributeValue(null, "roaming_protocol");
            if (roamingProtocol != null) {
                map.put("roaming_protocol", roamingProtocol);
            }
            String carrierEnabled = parser.getAttributeValue(null, "carrier_enabled");
            if (carrierEnabled != null) {
                map.put("carrier_enabled", Boolean.valueOf(Boolean.parseBoolean(carrierEnabled)));
            }
            String bearer = parser.getAttributeValue(null, "bearer");
            if (bearer != null) {
                map.put("bearer", Integer.valueOf(Integer.parseInt(bearer)));
            }
            String ppp = parser.getAttributeValue(null, "ppp");
            if (ppp != null) {
                map.put("ppp", ppp);
            }
            String spn = parser.getAttributeValue(null, Carriers.SPN);
            if (spn != null) {
                map.put(Carriers.SPN, spn);
            }
            String imsi = parser.getAttributeValue(null, Carriers.IMSI);
            if (imsi != null) {
                map.put(Carriers.IMSI, imsi);
            }
            String pnn = parser.getAttributeValue(null, "pnn");
            if (pnn != null) {
                map.put("pnn", pnn);
            }
            String mvno_type = parser.getAttributeValue(null, "mvno_type");
            if (mvno_type == null) {
                return map;
            }
            String mvno_match_data = parser.getAttributeValue(null, "mvno_match_data");
            if (mvno_match_data == null) {
                return map;
            }
            map.put("mvno_type", mvno_type);
            map.put("mvno_match_data", mvno_match_data);
            return map;
        }

        private void loadApns(SQLiteDatabase db, XmlPullParser parser, String table) {
            if (parser != null) {
                try {
                    db.beginTransaction();
                    XmlUtils.nextElement(parser);
                    while (parser.getEventType() != TelephonyProvider.URL_TELEPHONY) {
                        ContentValues row = getRow(parser);
                        if (row != null) {
                            this.mTelephonyProviderExt.onLoadApns(row);
                            insertAddingDefaults(db, table, row);
                            XmlUtils.nextElement(parser);
                        }
                    }
                    db.setTransactionSuccessful();
                } catch (XmlPullParserException e) {
                    Log.e(TelephonyProvider.TAG, "Got XmlPullParserException while loading apns.", e);
                } catch (IOException e2) {
                    Log.e(TelephonyProvider.TAG, "Got IOException while loading apns.", e2);
                } catch (SQLException e3) {
                    Log.e(TelephonyProvider.TAG, "Got SQLException while loading apns.", e3);
                } finally {
                    db.endTransaction();
                }
            }
        }

        private void insertAddingDefaults(SQLiteDatabase db, String table, ContentValues row) {
            if (!row.containsKey("authtype")) {
                row.put("authtype", Integer.valueOf(-1));
            }
            if (!row.containsKey("protocol")) {
                row.put("protocol", "IP");
            }
            if (!row.containsKey("roaming_protocol")) {
                row.put("roaming_protocol", "IP");
            }
            if (!row.containsKey("carrier_enabled")) {
                row.put("carrier_enabled", Boolean.valueOf(TelephonyProvider.DBG));
            }
            if (!row.containsKey("bearer")) {
                row.put("bearer", Integer.valueOf(0));
            }
            if (!row.containsKey("mvno_type")) {
                row.put("mvno_type", LoggingEvents.EXTRA_CALLING_APP_NAME);
            }
            if (!row.containsKey("mvno_match_data")) {
                row.put("mvno_match_data", LoggingEvents.EXTRA_CALLING_APP_NAME);
            }
            Boolean bTableEqual = Boolean.valueOf(false);
            for (int i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += TelephonyProvider.URL_TELEPHONY) {
                if (TelephonyProvider.CARRIERS_TABLE_GEMINI[i].equals(table)) {
                    bTableEqual = Boolean.valueOf(TelephonyProvider.DBG);
                    String sqlCmd = (ReplaceSqlStatement.CARRIER_INSERT + TelephonyProvider.CARRIERS_TABLE_GEMINI[i]) + ReplaceSqlStatement.CARRIER_MVNO_OMACP_GEMINI_TABLE_COLUMN;
                    if (this.mCarrierIntertGeminiStatement[i] == null) {
                        this.mCarrierIntertGeminiStatement[i] = db.compileStatement(sqlCmd);
                    }
                    bindStatement(this.mCarrierIntertGeminiStatement[i], row);
                    if (!bTableEqual.booleanValue()) {
                        db.insert(table, null, row);
                    }
                }
            }
            if (!bTableEqual.booleanValue()) {
                db.insert(table, null, row);
            }
        }

        private void bindStatement(SQLiteStatement ss, ContentValues row) {
            if (ss == null) {
                Log.e(TelephonyProvider.TAG, "SQLiteStatement should not be null!");
                return;
            }
            String name = row.getAsString(Provider.NAME);
            if (name != null) {
                ss.bindString(TelephonyProvider.URL_TELEPHONY, name);
            } else {
                ss.bindNull(TelephonyProvider.URL_TELEPHONY);
            }
            String numeric = row.getAsString("numeric");
            if (numeric != null) {
                ss.bindString(TelephonyProvider.URL_CURRENT, numeric);
            } else {
                ss.bindNull(TelephonyProvider.URL_CURRENT);
            }
            String mcc = row.getAsString("mcc");
            if (mcc != null) {
                ss.bindString(TelephonyProvider.URL_ID, mcc);
            } else {
                ss.bindNull(TelephonyProvider.URL_ID);
            }
            String mnc = row.getAsString("mnc");
            if (mnc != null) {
                ss.bindString(TelephonyProvider.URL_RESTOREAPN, mnc);
            } else {
                ss.bindNull(TelephonyProvider.URL_RESTOREAPN);
            }
            String apn = row.getAsString("apn");
            if (apn != null) {
                ss.bindString(TelephonyProvider.URL_PREFERAPN, apn);
            } else {
                ss.bindNull(TelephonyProvider.URL_PREFERAPN);
            }
            String user = row.getAsString("user");
            if (user != null) {
                ss.bindString(TelephonyProvider.URL_PREFERAPN_NO_UPDATE, user);
            } else {
                ss.bindNull(TelephonyProvider.URL_PREFERAPN_NO_UPDATE);
            }
            String server = row.getAsString("server");
            if (server != null) {
                ss.bindString(TelephonyProvider.URL_PREFERAPN_W_SUB_ID, server);
            } else {
                ss.bindNull(TelephonyProvider.URL_PREFERAPN_W_SUB_ID);
            }
            String password = row.getAsString("password");
            if (password != null) {
                ss.bindString(8, password);
            } else {
                ss.bindNull(8);
            }
            String proxy = row.getAsString("proxy");
            if (proxy != null) {
                ss.bindString(9, proxy);
            } else {
                ss.bindNull(9);
            }
            String port = row.getAsString("port");
            if (port != null) {
                ss.bindString(10, port);
            } else {
                ss.bindNull(10);
            }
            String mmsproxy = row.getAsString("mmsproxy");
            if (mmsproxy != null) {
                ss.bindString(TelephonyProvider.URL_PREFERTETHERINGAPN, mmsproxy);
            } else {
                ss.bindNull(TelephonyProvider.URL_PREFERTETHERINGAPN);
            }
            String mmsport = row.getAsString("mmsport");
            if (mmsport != null) {
                ss.bindString(12, mmsport);
            } else {
                ss.bindNull(12);
            }
            String mmsc = row.getAsString("mmsc");
            if (mmsc != null) {
                ss.bindString(13, mmsc);
            } else {
                ss.bindNull(13);
            }
            Integer authtype = row.getAsInteger("authtype");
            if (authtype != null) {
                ss.bindLong(14, (long) authtype.intValue());
            } else {
                ss.bindLong(14, -1);
            }
            String type = row.getAsString(AntispamNumber.TYPE);
            if (type != null) {
                ss.bindString(15, type);
            } else {
                ss.bindNull(15);
            }
            Integer current = row.getAsInteger("current");
            if (current != null) {
                ss.bindLong(16, (long) current.intValue());
            } else {
                ss.bindNull(16);
            }
            ss.bindLong(17, 0);
            String csdnum = row.getAsString(Carriers.CSD_NUM);
            if (csdnum != null) {
                ss.bindString(18, csdnum);
            } else {
                ss.bindNull(18);
            }
            String protocol = row.getAsString("protocol");
            if (protocol != null) {
                ss.bindString(19, protocol);
            } else {
                ss.bindString(19, "IP");
            }
            String roaming_protocol = row.getAsString("roaming_protocol");
            if (roaming_protocol != null) {
                ss.bindString(20, roaming_protocol);
            } else {
                ss.bindString(20, "IP");
            }
            String mvnotype = row.getAsString("mvno_type");
            if (mvnotype != null) {
                ss.bindString(TelephonyProvider.URL_ID_DM, mvnotype);
            } else {
                ss.bindNull(TelephonyProvider.URL_ID_DM);
            }
            String mvnomatchdata = row.getAsString("mvno_match_data");
            if (mvnomatchdata != null) {
                ss.bindString(23, mvnomatchdata);
            } else {
                ss.bindNull(23);
            }
            Boolean carrier_enabled = row.getAsBoolean("carrier_enabled");
            Integer bearer = row.getAsInteger("bearer");
            String omacpid = row.getAsString(Carriers.OMACPID);
            if (omacpid != null) {
                ss.bindString(24, omacpid);
            } else {
                ss.bindNull(24);
            }
            String napid = row.getAsString(Carriers.NAPID);
            if (napid != null) {
                ss.bindString(TelephonyProvider.URL_TELEPHONY_DM_GEMINI, napid);
            } else {
                ss.bindNull(TelephonyProvider.URL_TELEPHONY_DM_GEMINI);
            }
            String proxyid = row.getAsString(Carriers.PROXYID);
            if (proxyid != null) {
                ss.bindString(TelephonyProvider.URL_ID_DM_GEMINI, proxyid);
            } else {
                ss.bindNull(TelephonyProvider.URL_ID_DM_GEMINI);
            }
            if (carrier_enabled != null) {
                ss.bindLong(27, carrier_enabled.booleanValue() ? 1 : 0);
            } else {
                ss.bindLong(27, 1);
            }
            if (bearer != null) {
                ss.bindLong(28, (long) bearer.intValue());
            } else {
                ss.bindLong(28, 0);
            }
            String ppp = row.getAsString("ppp");
            if (ppp != null) {
                ss.bindString(TelephonyProvider.URL_TELEPHONY_DM, ppp);
            } else {
                ss.bindNull(TelephonyProvider.URL_TELEPHONY_DM);
            }
            String spn = row.getAsString(Carriers.SPN);
            if (spn != null) {
                ss.bindString(29, spn);
            } else {
                ss.bindNull(29);
            }
            String imsi = row.getAsString(Carriers.IMSI);
            if (imsi != null) {
                ss.bindString(30, imsi);
            } else {
                ss.bindNull(30);
            }
            String pnn = row.getAsString("pnn");
            if (pnn != null) {
                ss.bindString(31, pnn);
            } else {
                ss.bindNull(31);
            }
            ss.executeInsert();
        }
    }

    private interface ReplaceSqlStatement {
        public static final int APN = 5;
        public static final int AUTH_TYPE = 14;
        public static final int BEARER = 25;
        public static final int CARRIER_ENABLED = 24;
        public static final String CARRIER_INSERT = "INSERT INTO ";
        public static final String CARRIER_MVNO_GEMINI_TABLE_COLUMN = " (name, numeric, mcc, mnc, apn, user, server, password, proxy, port, mmsproxy, mmsport, mmsc, authtype, type, current, sourcetype, csdnum, protocol, roaming_protocol, ppp, mvno_type, mvno_match_data, carrier_enabled, bearer, spn, imsi, pnn)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        public static final String CARRIER_MVNO_OMACP_GEMINI_TABLE_COLUMN = " (name, numeric, mcc, mnc, apn, user, server, password, proxy, port, mmsproxy, mmsport, mmsc, authtype, type, current, sourcetype, csdnum, protocol, roaming_protocol, ppp, mvno_type, mvno_match_data, omacpid, napid, proxyid, carrier_enabled, bearer, spn, imsi, pnn)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        public static final int CSD_NUM = 18;
        public static final int CURRENT = 16;
        public static final int IMSI = 27;
        public static final int MCC = 3;
        public static final int MMSC = 13;
        public static final int MMSPORT = 12;
        public static final int MMSPROXY = 11;
        public static final int MNC = 4;
        public static final int MVNO_MATCH_DATA = 23;
        public static final int MVNO_TYPE = 22;
        public static final int NAME = 1;
        public static final int NUMERIC = 2;
        public static final int OMACP_BEARER = 28;
        public static final int OMACP_CARRIER_ENABLED = 27;
        public static final int OMACP_IMSI = 30;
        public static final int OMACP_NAPID = 25;
        public static final int OMACP_OMACPID = 24;
        public static final int OMACP_PNN = 31;
        public static final int OMACP_PROXYID = 26;
        public static final int OMACP_SPN = 29;
        public static final int PASSWORD = 8;
        public static final int PNN = 28;
        public static final int PORT = 10;
        public static final int PPP = 21;
        public static final int PROTOCOL = 19;
        public static final int PROXY = 9;
        public static final int ROAMING_PROTOCOL = 20;
        public static final int SERVER = 7;
        public static final int SOURCE_TYPE = 17;
        public static final int SPN = 26;
        public static final int TYPE = 15;
        public static final int USER = 6;
    }

    private android.database.Cursor setDefaultDisplayName(int r7, android.database.Cursor r8) {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x004c in list []
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r6 = this;
        r5 = -1;
        r2 = r8;
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r7 == r3) goto L_0x000a;
    L_0x0006:
        r3 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        if (r7 != r3) goto L_0x0040;
    L_0x000a:
        if (r8 == 0) goto L_0x0040;
    L_0x000c:
        r3 = r8.getCount();	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        if (r3 <= 0) goto L_0x0042;	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
    L_0x0012:
        r1 = new android.database.MatrixCursor;	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        r3 = r8.getColumnNames();	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        r4 = r8.getCount();	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        r1.<init>(r3, r4);	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        r8.moveToFirst();	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
    L_0x0022:
        r3 = r8.isAfterLast();	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        if (r3 != 0) goto L_0x0041;	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
    L_0x0028:
        r3 = r6.getSIMInfoRow(r8);	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        r1.addRow(r3);	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        r8.moveToNext();	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        goto L_0x0022;
    L_0x0033:
        r0 = move-exception;
        r3 = "TelephonyProvider";	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        r4 = "setDefaultDisplayName has error.";	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        android.util.Log.e(r3, r4, r0);	 Catch:{ Exception -> 0x0033, all -> 0x0050 }
        if (r2 == r8) goto L_0x004c;
    L_0x003d:
        r8.close();
    L_0x0040:
        return r2;
    L_0x0041:
        r2 = r1;
    L_0x0042:
        if (r2 == r8) goto L_0x0048;
    L_0x0044:
        r8.close();
        goto L_0x0040;
    L_0x0048:
        r8.moveToPosition(r5);
        goto L_0x0040;
    L_0x004c:
        r8.moveToPosition(r5);
        goto L_0x0040;
    L_0x0050:
        r3 = move-exception;
        if (r2 == r8) goto L_0x0057;
    L_0x0053:
        r8.close();
    L_0x0056:
        throw r3;
    L_0x0057:
        r8.moveToPosition(r5);
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.setDefaultDisplayName(int, android.database.Cursor):android.database.Cursor");
    }

    public TelephonyProvider() {
        Object[] objArr = new Object[URL_RESTOREAPN];
        objArr[0] = new Object();
        objArr[URL_TELEPHONY] = new Object();
        objArr[URL_CURRENT] = new Object();
        objArr[URL_ID] = new Object();
        this.mLock = objArr;
        this.mBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (("android.intent.action.SIM_STATE_CHANGED".equals(action) && "LOADED".equals(intent.getStringExtra("ss"))) || "android.provider.Telephony.SPN_STRINGS_UPDATED".equals(action) || "android.intent.action.LOCALE_CHANGED".equals(action)) {
                    TelephonyProvider.this.getContext().getContentResolver().notifyChange(SimInfo.CONTENT_URI, null);
                }
            }
        };
    }

    static {
        s_urlMatcher = new UriMatcher(-1);
        URL_CARD_GEMINI = new int[]{URL_TELEPHONY, URL_CURRENT, URL_ID, URL_RESTOREAPN};
        String[] strArr = new String[URL_RESTOREAPN];
        strArr[0] = "load_slot1_apn";
        strArr[URL_TELEPHONY] = "load_slot2_apn";
        strArr[URL_CURRENT] = "load_slot3_apn";
        strArr[URL_ID] = "load_slot4_apn";
        PREF_LOAD_APN_SLOT_KEY = strArr;
        mInitAPNGemini = new boolean[]{false, false, false, false};
        strArr = new String[URL_RESTOREAPN];
        strArr[0] = "carriers_sim1";
        strArr[URL_TELEPHONY] = "carriers_sim2";
        strArr[URL_CURRENT] = "carriers_sim3";
        strArr[URL_ID] = "carriers_sim4";
        carriersUri = strArr;
        strArr = new String[URL_RESTOREAPN];
        strArr[0] = CARRIERS_TABLE;
        strArr[URL_TELEPHONY] = "carriers_2";
        strArr[URL_CURRENT] = "carriers_3";
        strArr[URL_ID] = "carriers_4";
        CARRIERS_TABLE_GEMINI = strArr;
        strArr = new String[URL_RESTOREAPN];
        strArr[0] = PREF_FILE;
        strArr[URL_TELEPHONY] = "preferred-apn-2";
        strArr[URL_CURRENT] = "preferred-apn-3";
        strArr[URL_ID] = "preferred-apn-4";
        PREF_FILE_GEMINI = strArr;
        strArr = new String[URL_RESTOREAPN];
        strArr[0] = PREF_TETHERING_FILE;
        strArr[URL_TELEPHONY] = "preferred-tethering-apn-2";
        strArr[URL_CURRENT] = "preferred-tethering-apn-3";
        strArr[URL_ID] = "preferred-tethering-apn-4";
        PREF_TETHERING_FILE_GEMINI = strArr;
        Uri[] uriArr = new Uri[URL_RESTOREAPN];
        uriArr[0] = SIM1Carriers.CONTENT_URI;
        uriArr[URL_TELEPHONY] = SIM2Carriers.CONTENT_URI;
        uriArr[URL_CURRENT] = SIM3Carriers.CONTENT_URI;
        uriArr[URL_ID] = SIM4Carriers.CONTENT_URI;
        URI_GEMINI = uriArr;
        s_urlMatcherGemini = new UriMatcher[PhoneConstants.GEMINI_SIM_NUM];
        s_urlMatcher.addURI("telephony", CARRIERS_TABLE, URL_TELEPHONY);
        s_urlMatcher.addURI("telephony", "carriers/current", URL_CURRENT);
        s_urlMatcher.addURI("telephony", "carriers/#", URL_ID);
        s_urlMatcher.addURI("telephony", "carriers/restore", URL_RESTOREAPN);
        s_urlMatcher.addURI("telephony", "carriers/preferapn", URL_PREFERAPN);
        s_urlMatcher.addURI("telephony", "carriers/preferapn_no_update", URL_PREFERAPN_NO_UPDATE);
        s_urlMatcher.addURI("telephony", "carriers/preferapn/#", URL_PREFERAPN_W_SUB_ID);
        s_urlMatcher.addURI("telephony", "carriers/prefertetheringapn", URL_PREFERTETHERINGAPN);
        for (int i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += URL_TELEPHONY) {
            s_urlMatcherGemini[i] = new UriMatcher(-1);
            s_urlMatcherGemini[i].addURI("telephony", carriersUri[i] + "/", URL_TELEPHONY);
            s_urlMatcherGemini[i].addURI("telephony", carriersUri[i] + "/current", URL_CURRENT);
            s_urlMatcherGemini[i].addURI("telephony", carriersUri[i] + "/#", URL_ID);
            s_urlMatcherGemini[i].addURI("telephony", carriersUri[i] + "/restore", URL_RESTOREAPN);
            s_urlMatcherGemini[i].addURI("telephony", carriersUri[i] + "/preferapn", URL_PREFERAPN);
            s_urlMatcherGemini[i].addURI("telephony", carriersUri[i] + "/preferapn_no_update", URL_PREFERAPN_NO_UPDATE);
            s_urlMatcherGemini[i].addURI("telephony", carriersUri[i] + "/prefertetheringapn", URL_PREFERTETHERINGAPN);
        }
        s_urlMatcher.addURI("telephony", SIMINFO_TABLE, URL_SIMINFO);
        s_urlMatcher.addURI("telephony", "siminfo/#", URL_SIMINFO_ID);
        s_urlMatcher.addURI("telephony", GPRSINFO_TABLE, URL_GPRSINFO);
        s_urlMatcher.addURI("telephony", "gprsinfo/#", URL_GPRSINFO_ID);
        s_urlMatcher.addURI("telephony", CARRIERS_DM_TABLE, URL_TELEPHONY_DM);
        s_urlMatcher.addURI("telephony", "carriers_dm/#", URL_ID_DM);
        s_urlMatcher.addURI("telephony", CARRIERS_DM_TABLE_GEMINI, URL_TELEPHONY_DM_GEMINI);
        s_urlMatcher.addURI("telephony", "carriers_dm_gemini/#", URL_ID_DM_GEMINI);
        s_currentNullMap = new ContentValues(URL_TELEPHONY);
        s_currentNullMap.put("current", (Long) null);
        s_currentSetMap = new ContentValues(URL_TELEPHONY);
        s_currentSetMap.put("current", "1");
    }

    public boolean onCreate() {
        this.mOpenHelper = new DatabaseHelper(getContext());
        SharedPreferences sp = getContext().getSharedPreferences(PREF_LOAD_APN, 0);
        for (int i = 0; i < PhoneConstants.GEMINI_SIM_NUM; i += URL_TELEPHONY) {
            mInitAPNGemini[i] = sp.getBoolean(PREF_LOAD_APN_SLOT_KEY[i], false);
            Log.i(TAG, "mInitAPNGemini[" + i + "]: " + mInitAPNGemini[i]);
        }
        this.iTelephony = Stub.asInterface(ServiceManager.checkService("phoneEx"));
        IntentFilter filter = new IntentFilter("android.intent.action.SIM_STATE_CHANGED");
        filter.addAction("android.intent.action.LOCALE_CHANGED");
        filter.addAction("android.provider.Telephony.SPN_STRINGS_UPDATED");
        getContext().registerReceiver(this.mBroadcastReceiver, filter);
        return DBG;
    }

    private void setPreferredApnId(String file, Long id) {
        Editor editor = getContext().getSharedPreferences(file, 0).edit();
        editor.putLong(COLUMN_APN_ID, id != null ? id.longValue() : -1);
        editor.apply();
    }

    private long getPreferredApnId(String file) {
        return getContext().getSharedPreferences(file, 0).getLong(COLUMN_APN_ID, -1);
    }

    public Cursor query(Uri url, String[] projectionIn, String selection, String[] selectionArgs, String sort) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setStrict(DBG);
        initDatabaseIfNeeded(url);
        int match = s_urlMatcher.match(url);
        switch (match) {
            case URL_TELEPHONY /*1*/:
                qb.setTables(CARRIERS_TABLE_GEMINI[0]);
                break;
            case URL_CURRENT /*2*/:
                qb.setTables(CARRIERS_TABLE_GEMINI[0]);
                qb.appendWhere("current IS NOT NULL");
                break;
            case URL_ID /*3*/:
                qb.setTables(CARRIERS_TABLE_GEMINI[0]);
                qb.appendWhere("_id = " + ((String) url.getPathSegments().get(URL_TELEPHONY)));
                break;
            case URL_PREFERAPN /*5*/:
            case URL_PREFERAPN_NO_UPDATE /*6*/:
                qb.setTables(CARRIERS_TABLE_GEMINI[0]);
                qb.appendWhere("_id = " + getPreferredApnId(PREF_FILE_GEMINI[0]));
                break;
            case URL_PREFERAPN_W_SUB_ID /*7*/:
                qb.setTables(CARRIERS_TABLE_GEMINI[parseSubId(url)]);
                qb.appendWhere("_id = " + getPreferredApnId(PREF_FILE_GEMINI[parseSubId(url)]));
                break;
            case URL_PREFERTETHERINGAPN /*11*/:
                qb.setTables(CARRIERS_TABLE_GEMINI[0]);
                qb.appendWhere("_id = " + getPreferredApnId(PREF_TETHERING_FILE_GEMINI[0]));
                break;
            case URL_TELEPHONY_DM /*21*/:
                qb.setTables(CARRIERS_DM_TABLE);
                break;
            case URL_ID_DM /*22*/:
                qb.setTables(CARRIERS_DM_TABLE);
                qb.appendWhere("_id = " + ((String) url.getPathSegments().get(URL_TELEPHONY)));
                break;
            case URL_TELEPHONY_DM_GEMINI /*25*/:
                qb.setTables(CARRIERS_DM_TABLE_GEMINI);
                break;
            case URL_ID_DM_GEMINI /*26*/:
                qb.setTables(CARRIERS_DM_TABLE_GEMINI);
                qb.appendWhere("_id = " + ((String) url.getPathSegments().get(URL_TELEPHONY)));
                break;
            case URL_SIMINFO /*101*/:
                qb.setTables(SIMINFO_TABLE);
                break;
            case URL_SIMINFO_ID /*102*/:
                qb.setTables(SIMINFO_TABLE);
                qb.appendWhere("_id=" + ContentUris.parseId(url));
                break;
            case URL_GPRSINFO /*1001*/:
                qb.setTables(GPRSINFO_TABLE);
                break;
            case URL_GPRSINFO_ID /*1002*/:
                qb.setTables(GPRSINFO_TABLE);
                qb.appendWhere("_id=" + ContentUris.parseId(url));
                break;
            default:
                return null;
        }
        Cursor ret = null;
        try {
            ret = qb.query(this.mOpenHelper.getReadableDatabase(), projectionIn, selection, selectionArgs, null, null, sort);
        } catch (SQLException e) {
            Log.e(TAG, "got exception when querying: " + e);
        }
        if (ret != null && match == URL_SIMINFO) {
            ret = setDefaultDisplayName(match, ret);
        }
        if (ret == null) {
            return ret;
        }
        ret.setNotificationUri(getContext().getContentResolver(), url);
        return ret;
    }

    public void initDatabaseIfNeeded(Uri uri) {
        Log.i(TAG, "initDatabaseIfNeeded begin " + uri);
        String table = null;
        if (s_urlMatcher.match(uri) != -1) {
            table = CARRIERS_TABLE_GEMINI[0];
        }
        if (table != null) {
            SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
            SharedPreferences sp = getContext().getSharedPreferences(PREF_LOAD_APN, 0);
            String initTable = table;
            Log.i(TAG, " mInitAPNGemini[" + 0 + "]: " + mInitAPNGemini[0] + ",uri" + uri);
            synchronized (this.mLock[0]) {
                Log.i(TAG, " mInitAPNGemini[" + 0 + "] unlock: " + mInitAPNGemini[0] + ",uri" + uri);
                if (!mInitAPNGemini[0]) {
                    Editor editor = sp.edit();
                    this.mOpenHelper.initDatabase(db, initTable);
                    editor.putBoolean(PREF_LOAD_APN_SLOT_KEY[0], DBG);
                    editor.apply();
                    mInitAPNGemini[0] = DBG;
                }
            }
        }
        Log.i(TAG, "initDatabaseIfNeeded end " + uri);
    }

    public String getType(Uri url) {
        switch (s_urlMatcher.match(url)) {
            case URL_TELEPHONY /*1*/:
                return "vnd.android.cursor.dir/telephony-carrier";
            case URL_ID /*3*/:
                return "vnd.android.cursor.item/telephony-carrier";
            case URL_PREFERAPN /*5*/:
            case URL_PREFERAPN_NO_UPDATE /*6*/:
            case URL_PREFERAPN_W_SUB_ID /*7*/:
                return "vnd.android.cursor.item/telephony-carrier";
            default:
                throw new IllegalArgumentException("Unknown URL " + url);
        }
    }

    public Uri insert(Uri url, ContentValues initialValues) {
        Uri result = null;
        checkPermission();
        initDatabaseIfNeeded(url);
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        boolean notify = false;
        ContentValues values;
        long rowID;
        switch (s_urlMatcher.match(url)) {
            case URL_TELEPHONY /*1*/:
                if (initialValues != null) {
                    values = new ContentValues(initialValues);
                } else {
                    values = new ContentValues();
                }
                if (!values.containsKey(Provider.NAME)) {
                    values.put(Provider.NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("apn")) {
                    values.put("apn", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("port")) {
                    values.put("port", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("proxy")) {
                    values.put("proxy", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("user")) {
                    values.put("user", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("server")) {
                    values.put("server", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("password")) {
                    values.put("password", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("mmsport")) {
                    values.put("mmsport", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("mmsproxy")) {
                    values.put("mmsproxy", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("authtype")) {
                    values.put("authtype", Integer.valueOf(-1));
                }
                if (!values.containsKey("protocol")) {
                    values.put("protocol", "IP");
                }
                if (!values.containsKey("roaming_protocol")) {
                    values.put("roaming_protocol", "IP");
                }
                if (!values.containsKey(Carriers.SOURCE_TYPE)) {
                    values.put(Carriers.SOURCE_TYPE, Integer.valueOf(URL_CURRENT));
                }
                if (!values.containsKey("carrier_enabled")) {
                    values.put("carrier_enabled", Boolean.valueOf(DBG));
                }
                if (!values.containsKey("bearer")) {
                    values.put("bearer", Integer.valueOf(0));
                }
                if (!values.containsKey("ppp")) {
                    values.put("ppp", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("mvno_type")) {
                    values.put("mvno_type", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("mvno_match_data")) {
                    values.put("mvno_match_data", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey(Carriers.SPN)) {
                    values.put(Carriers.SPN, LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey(Carriers.IMSI)) {
                    values.put(Carriers.IMSI, LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                if (!values.containsKey("pnn")) {
                    values.put("pnn", LoggingEvents.EXTRA_CALLING_APP_NAME);
                }
                rowID = db.insert(CARRIERS_TABLE_GEMINI[0], null, values);
                if (rowID > 0) {
                    result = ContentUris.withAppendedId(url, rowID);
                    notify = DBG;
                }
                Log.d(TAG, "inserted " + values.toString() + " rowID = " + rowID);
                break;
            case URL_CURRENT /*2*/:
                db.update(CARRIERS_TABLE_GEMINI[0], s_currentNullMap, "current IS NOT NULL", null);
                String numeric = initialValues.getAsString("numeric");
                if (db.update(CARRIERS_TABLE_GEMINI[0], s_currentSetMap, "numeric = '" + numeric + "'", null) <= 0) {
                    Log.e(TAG, "Failed setting numeric '" + numeric + "' to the current operator");
                    break;
                }
                Log.d(TAG, "Setting numeric '" + numeric + "' to be the current operator1");
                break;
            case URL_PREFERAPN /*5*/:
            case URL_PREFERAPN_NO_UPDATE /*6*/:
                if (initialValues != null) {
                    if (initialValues.containsKey(COLUMN_APN_ID)) {
                        setPreferredApnId(PREF_FILE_GEMINI[0], initialValues.getAsLong(COLUMN_APN_ID));
                        break;
                    }
                }
                break;
            case URL_PREFERAPN_W_SUB_ID /*7*/:
                if (initialValues != null) {
                    if (initialValues.containsKey(COLUMN_APN_ID)) {
                        setPreferredApnId(PREF_FILE_GEMINI[parseSubId(url)], initialValues.getAsLong(COLUMN_APN_ID));
                        break;
                    }
                }
                break;
            case URL_PREFERTETHERINGAPN /*11*/:
                if (initialValues != null) {
                    if (initialValues.containsKey(COLUMN_APN_ID)) {
                        setPreferredApnId(PREF_TETHERING_FILE_GEMINI[0], initialValues.getAsLong(COLUMN_APN_ID));
                        break;
                    }
                }
                break;
            case URL_TELEPHONY_DM /*21*/:
                values = genContentValue(initialValues);
                rowID = db.insert(CARRIERS_DM_TABLE, null, values);
                if (rowID > 0) {
                    result = ContentUris.withAppendedId(Telephony.Carriers.CONTENT_URI_DM, rowID);
                    notify = DBG;
                }
                Log.d(TAG, "inserted " + values.toString() + " rowID = " + rowID);
                break;
            case URL_TELEPHONY_DM_GEMINI /*25*/:
                values = genContentValue(initialValues);
                rowID = db.insert(CARRIERS_DM_TABLE_GEMINI, null, values);
                if (rowID > 0) {
                    result = ContentUris.withAppendedId(GeminiCarriers.CONTENT_URI_DM, rowID);
                    notify = DBG;
                }
                Log.d(TAG, "inserted " + values.toString() + " rowID = " + rowID);
                break;
            case URL_SIMINFO /*101*/:
                result = ContentUris.withAppendedId(SimInfoManager.CONTENT_URI, db.insert(SIMINFO_TABLE, null, initialValues));
                notify = DBG;
                break;
            case URL_GPRSINFO /*1001*/:
                result = ContentUris.withAppendedId(GprsInfo.CONTENT_URI, db.insert(GPRSINFO_TABLE, null, initialValues));
                notify = DBG;
                break;
        }
        if (notify) {
            getContext().getContentResolver().notifyChange(url, null);
        }
        return result;
    }

    public int delete(Uri url, String where, String[] whereArgs) {
        int count = 0;
        checkPermission();
        initDatabaseIfNeeded(url);
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        int match = s_urlMatcher.match(url);
        int urlType = -1;
        String[] strArr;
        switch (match) {
            case URL_TELEPHONY /*1*/:
                count = db.delete(CARRIERS_TABLE_GEMINI[0], where, whereArgs);
                urlType = URL_CARD_GEMINI[0];
                break;
            case URL_CURRENT /*2*/:
                count = db.delete(CARRIERS_TABLE_GEMINI[0], where, whereArgs);
                urlType = URL_CARD_GEMINI[0];
                break;
            case URL_ID /*3*/:
                strArr = new String[URL_TELEPHONY];
                strArr[0] = url.getLastPathSegment();
                count = db.delete(CARRIERS_TABLE_GEMINI[0], "_id=?", strArr);
                break;
            case URL_RESTOREAPN /*4*/:
                count = URL_TELEPHONY;
                restoreDefaultAPN(PREF_FILE_GEMINI[0], CARRIERS_TABLE_GEMINI[0]);
                urlType = URL_CARD_GEMINI[0];
                break;
            case URL_PREFERAPN /*5*/:
            case URL_PREFERAPN_NO_UPDATE /*6*/:
                setPreferredApnId(PREF_FILE_GEMINI[0], Long.valueOf(-1));
                if (match == URL_PREFERAPN) {
                    count = URL_TELEPHONY;
                }
                urlType = URL_CARD_GEMINI[0];
                break;
            case URL_PREFERAPN_W_SUB_ID /*7*/:
                setPreferredApnId(PREF_FILE_GEMINI[parseSubId(url)], Long.valueOf(-1));
                count = URL_TELEPHONY;
                urlType = URL_CARD_GEMINI[parseSubId(url)];
                break;
            case URL_PREFERTETHERINGAPN /*11*/:
                setPreferredApnId(PREF_TETHERING_FILE_GEMINI[0], Long.valueOf(-1));
                count = URL_TELEPHONY;
                urlType = URL_CARD_GEMINI[0];
                break;
            case URL_TELEPHONY_DM /*21*/:
                count = db.delete(CARRIERS_DM_TABLE, where, whereArgs);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI_DM, null);
                }
                return count;
            case URL_ID_DM /*22*/:
                count = db.delete(CARRIERS_DM_TABLE, where, whereArgs);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI_DM, null);
                }
                return count;
            case URL_TELEPHONY_DM_GEMINI /*25*/:
                count = db.delete(CARRIERS_DM_TABLE_GEMINI, where, whereArgs);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(GeminiCarriers.CONTENT_URI_DM, null);
                }
                return count;
            case URL_ID_DM_GEMINI /*26*/:
                strArr = new String[URL_TELEPHONY];
                strArr[0] = url.getLastPathSegment();
                count = db.delete(CARRIERS_DM_TABLE_GEMINI, "_id=?", strArr);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(GeminiCarriers.CONTENT_URI_DM, null);
                }
                return count;
            case URL_SIMINFO /*101*/:
                count = db.delete(SIMINFO_TABLE, where, whereArgs);
                urlType = URL_SIMINFO;
                break;
            case URL_SIMINFO_ID /*102*/:
                count = db.delete(SIMINFO_TABLE, "_id=" + ContentUris.parseId(url) + " " + (where == null ? LoggingEvents.EXTRA_CALLING_APP_NAME : " AND (" + where + ")"), whereArgs);
                urlType = URL_SIMINFO_ID;
                break;
            case URL_GPRSINFO /*1001*/:
                count = db.delete(GPRSINFO_TABLE, where, whereArgs);
                urlType = URL_GPRSINFO;
                break;
            case URL_GPRSINFO_ID /*1002*/:
                count = db.delete(GPRSINFO_TABLE, "_id=" + ContentUris.parseId(url) + " " + (where == null ? LoggingEvents.EXTRA_CALLING_APP_NAME : " AND (" + where + ")"), whereArgs);
                urlType = URL_GPRSINFO_ID;
                break;
            default:
                throw new UnsupportedOperationException("Cannot delete that URL: " + url);
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI, null);
            switch (urlType) {
                case URL_SIMINFO /*101*/:
                case URL_SIMINFO_ID /*102*/:
                    getContext().getContentResolver().notifyChange(SimInfoManager.CONTENT_URI, null);
                    break;
                case URL_GPRSINFO /*1001*/:
                case URL_GPRSINFO_ID /*1002*/:
                    getContext().getContentResolver().notifyChange(GprsInfo.CONTENT_URI, null);
                    break;
            }
        }
        return count;
    }

    public int update(Uri url, ContentValues values, String where, String[] whereArgs) {
        int count = 0;
        checkPermission();
        int urlType = -1;
        initDatabaseIfNeeded(url);
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        int match = s_urlMatcher.match(url);
        String[] strArr;
        switch (match) {
            case URL_TELEPHONY /*1*/:
                count = db.update(CARRIERS_TABLE_GEMINI[0], values, where, whereArgs);
                urlType = URL_CARD_GEMINI[0];
                break;
            case URL_CURRENT /*2*/:
                count = db.update(CARRIERS_TABLE_GEMINI[0], values, where, whereArgs);
                urlType = URL_CARD_GEMINI[0];
                break;
            case URL_ID /*3*/:
                if (where == null && whereArgs == null) {
                    strArr = new String[URL_TELEPHONY];
                    strArr[0] = url.getLastPathSegment();
                    count = db.update(CARRIERS_TABLE_GEMINI[0], values, "_id=?", strArr);
                    urlType = URL_CARD_GEMINI[0];
                    break;
                }
                throw new UnsupportedOperationException("Cannot update URL " + url + " with a where clause");
                break;
            case URL_PREFERAPN /*5*/:
            case URL_PREFERAPN_NO_UPDATE /*6*/:
                if (values != null) {
                    if (values.containsKey(COLUMN_APN_ID)) {
                        setPreferredApnId(PREF_FILE_GEMINI[0], values.getAsLong(COLUMN_APN_ID));
                        if (match == URL_PREFERAPN) {
                            count = URL_TELEPHONY;
                        }
                        urlType = URL_CARD_GEMINI[0];
                        break;
                    }
                }
                break;
            case URL_PREFERAPN_W_SUB_ID /*7*/:
                if (values != null) {
                    if (values.containsKey(COLUMN_APN_ID)) {
                        setPreferredApnId(PREF_FILE_GEMINI[parseSubId(url)], values.getAsLong(COLUMN_APN_ID));
                        count = URL_TELEPHONY;
                        urlType = URL_CARD_GEMINI[0];
                        break;
                    }
                }
                break;
            case URL_PREFERTETHERINGAPN /*11*/:
                if (values != null) {
                    if (values.containsKey(COLUMN_APN_ID)) {
                        setPreferredApnId(PREF_TETHERING_FILE_GEMINI[0], values.getAsLong(COLUMN_APN_ID));
                        count = URL_TELEPHONY;
                        urlType = URL_CARD_GEMINI[0];
                        break;
                    }
                }
                break;
            case URL_TELEPHONY_DM /*21*/:
                count = db.update(CARRIERS_DM_TABLE, values, where, whereArgs);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI_DM, null);
                }
                return count;
            case URL_ID_DM /*22*/:
                if (where == null && whereArgs == null) {
                    strArr = new String[URL_TELEPHONY];
                    strArr[0] = url.getLastPathSegment();
                    count = db.update(CARRIERS_DM_TABLE, values, "_id=?", strArr);
                    if (count > 0) {
                        getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI_DM, null);
                    }
                    return count;
                }
                throw new UnsupportedOperationException("Cannot update URL " + url + " with a where clause");
            case URL_TELEPHONY_DM_GEMINI /*25*/:
                count = db.update(CARRIERS_DM_TABLE_GEMINI, values, where, whereArgs);
                if (count > 0) {
                    getContext().getContentResolver().notifyChange(GeminiCarriers.CONTENT_URI_DM, null);
                }
                return count;
            case URL_ID_DM_GEMINI /*26*/:
                if (where == null && whereArgs == null) {
                    strArr = new String[URL_TELEPHONY];
                    strArr[0] = url.getLastPathSegment();
                    count = db.update(CARRIERS_DM_TABLE_GEMINI, values, "_id=?", strArr);
                    if (count > 0) {
                        getContext().getContentResolver().notifyChange(GeminiCarriers.CONTENT_URI_DM, null);
                    }
                    return count;
                }
                throw new UnsupportedOperationException("Cannot update URL " + url + " with a where clause");
            case URL_SIMINFO /*101*/:
                count = db.update(SIMINFO_TABLE, values, where, whereArgs);
                urlType = URL_SIMINFO;
                break;
            case URL_SIMINFO_ID /*102*/:
                count = db.update(SIMINFO_TABLE, values, "_id=" + ContentUris.parseId(url) + " " + (where == null ? LoggingEvents.EXTRA_CALLING_APP_NAME : " AND (" + where + ")"), whereArgs);
                urlType = URL_SIMINFO_ID;
                break;
            case URL_GPRSINFO /*1001*/:
                count = db.update(GPRSINFO_TABLE, values, where, whereArgs);
                urlType = URL_GPRSINFO;
                break;
            case URL_GPRSINFO_ID /*1002*/:
                count = db.update(GPRSINFO_TABLE, values, "_id=" + ContentUris.parseId(url) + " " + (where == null ? LoggingEvents.EXTRA_CALLING_APP_NAME : " AND (" + where + ")"), whereArgs);
                urlType = URL_GPRSINFO_ID;
                break;
            default:
                throw new UnsupportedOperationException("Cannot update that URL: " + url);
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI, null);
            switch (urlType) {
                case URL_SIMINFO /*101*/:
                case URL_SIMINFO_ID /*102*/:
                    getContext().getContentResolver().notifyChange(SimInfoManager.CONTENT_URI, null);
                    break;
                case URL_GPRSINFO /*1001*/:
                case URL_GPRSINFO_ID /*1002*/:
                    getContext().getContentResolver().notifyChange(GprsInfo.CONTENT_URI, null);
                    break;
            }
        }
        return count;
    }

    private void checkPermission() {
        getContext().enforceCallingOrSelfPermission("android.permission.WRITE_APN_SETTINGS", "No permission to write APN settings");
    }

    private void restoreDefaultAPN(String file, String table) {
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        Log.i(TAG, "into restoreDefaultAPN");
        if (db != null) {
            db.beginTransaction();
            try {
                db.delete(table, null, null);
            } catch (SQLiteException e) {
            }
            Log.i(TAG, "delete");
            setPreferredApnId(file, Long.valueOf(-1));
            this.mOpenHelper.initDatabase(db, table);
            Log.i(TAG, "initDatabase");
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    private static void log(String key, String val) {
        Log.d(TAG, key + val);
    }

    private ContentValues genContentValue(ContentValues initialValues) {
        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }
        if (values.containsKey("mcc") && values.containsKey("mnc")) {
            String mcc = values.getAsString("mcc");
            values.put("numeric", mcc + values.getAsString("mnc"));
        }
        if (!values.containsKey(Provider.NAME)) {
            values.put(Provider.NAME, LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("apn")) {
            values.put("apn", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("port")) {
            values.put("port", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("proxy")) {
            values.put("proxy", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("user")) {
            values.put("user", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("server")) {
            values.put("server", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("password")) {
            values.put("password", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("mmsport")) {
            values.put("mmsport", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("mmsproxy")) {
            values.put("mmsproxy", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("authtype")) {
            values.put("authtype", Integer.valueOf(-1));
        }
        if (!values.containsKey("protocol")) {
            values.put("protocol", "IP");
        }
        if (!values.containsKey("roaming_protocol")) {
            values.put("roaming_protocol", "IP");
        }
        if (!values.containsKey("carrier_enabled")) {
            values.put("carrier_enabled", Boolean.valueOf(DBG));
        }
        if (!values.containsKey("bearer")) {
            values.put("bearer", Integer.valueOf(0));
        }
        if (!values.containsKey("mvno_type")) {
            values.put("mvno_type", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        if (!values.containsKey("mvno_match_data")) {
            values.put("mvno_match_data", LoggingEvents.EXTRA_CALLING_APP_NAME);
        }
        values.put(AntispamNumber.TYPE, "dm");
        return values;
    }

    private int getPreferSimdId() {
        int simId = System.getInt(getContext().getContentResolver(), "gprs_connection_setting", -4) - 1;
        if (simId < 0 || simId >= PhoneConstants.GEMINI_SIM_NUM) {
            if (this.iTelephony != null) {
                try {
                    simId = this.iTelephony.get3GCapabilitySIM();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Log.e(TAG, "iTelephony exception");
                }
            } else {
                Log.e(TAG, "iTelephony is null!!!");
            }
        }
        if (simId < 0 || simId >= PhoneConstants.GEMINI_SIM_NUM) {
        }
        simId = MultiSimManager.getInstance().getPreferredDataSlotId();
        Log.d(TAG, "Default Data Setting value=" + simId);
        return simId;
    }

    private Object[] getSIMInfoRow(Cursor cursor) {
        Object[] columns = new Object[cursor.getColumnCount()];
        for (int i = 0; i < cursor.getColumnCount(); i += URL_TELEPHONY) {
            String column = cursor.getString(i);
            if (EncapsulatedSimInfoManager.DISPLAY_NAME.equals(cursor.getColumnName(i)) && TextUtils.isEmpty(column)) {
                int slot = cursor.getInt(cursor.getColumnIndexOrThrow(EncapsulatedSimInfoManager.SLOT));
                if (slot >= 0) {
                    TelephonyManager tm = TelephonyManagerFactory.getDefault(slot);
                    String simOperator = tm.getSimOperator();
                    if (!TextUtils.isEmpty(simOperator)) {
                        column = SpnOverride.getInstance().getSpn(simOperator, tm.getNetworkOperatorName());
                    }
                    if (TextUtils.isEmpty(column)) {
                        column = tm.getSimOperatorName();
                    }
                }
            }
            columns[i] = column;
        }
        return columns;
    }

    private int parseSubId(Uri url) {
        int subId = -1;
        try {
            subId = Integer.parseInt(url.getLastPathSegment());
        } catch (NumberFormatException e) {
            Log.e(TAG, "NumberFormatException: ", e);
        }
        Log.d(TAG, "SUB ID in the uri is" + subId);
        return subId;
    }

    private static void updateFromV5(SQLiteDatabase db) {
        try {
            String columns = "(_id INTEGER PRIMARY KEY,name TEXT,numeric TEXT,mcc TEXT,mnc TEXT,apn TEXT,user TEXT,server TEXT,password TEXT,proxy TEXT,port TEXT,mmsproxy TEXT,mmsport TEXT,mmsc TEXT,authtype INTEGER,type TEXT,current INTEGER,sourcetype INTEGER,csdnum TEXT,protocol TEXT,roaming_protocol TEXT,omacpid TEXT,napid TEXT,proxyid TEXT,carrier_enabled BOOLEAN,bearer INTEGER,spn TEXT,imsi TEXT,pnn TEXT,ppp TEXT,mvno_type TEXT,mvno_match_data TEXT,";
            for (int i = URL_TELEPHONY; i < PhoneConstants.GEMINI_SIM_NUM; i += URL_TELEPHONY) {
                db.execSQL("CREATE TABLE " + CARRIERS_TABLE_GEMINI[i] + columns + "slot_id INTEGER DEFAULT " + i + ");");
                mInitAPNGemini[i] = false;
            }
            mInitAPNGemini[0] = false;
            Log.d(TAG, "Update JB2, add table CARRIERS_TABLE_GEMINI");
        } catch (SQLException e) {
            Log.e(TAG, "add table CARRIERS_TABLE_GEMINI fail.", e);
        }
    }
}
