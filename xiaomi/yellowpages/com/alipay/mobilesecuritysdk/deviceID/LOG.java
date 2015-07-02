package com.alipay.mobilesecuritysdk.deviceID;

import HttpUtils.HttpFetcher;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.cons.MiniDefine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class LOG {
    public static boolean DBG;
    private static String TAG;
    private static File logFileDir;
    private static File logFileName;
    private static Context mcontext;
    private static String model;
    private static String pkgName;

    static {
        logFileDir = null;
        logFileName = null;
        model = null;
        pkgName = null;
        DBG = true;
        TAG = "logger";
        mcontext = null;
    }

    public static synchronized void init(Context context) {
        synchronized (LOG.class) {
            mcontext = context;
            if (logFileDir == null) {
                logFileDir = new File(new StringBuilder(String.valueOf(context.getFilesDir().getAbsolutePath())).append(ConfigConstant.LOG_DIR).toString());
                getInfo(context);
            }
            if (!logFileDir.exists()) {
                logFileDir.mkdirs();
            } else if (!logFileDir.isDirectory()) {
                throw new IllegalStateException(String.format("<%s> exists but not a Directory!", new Object[]{logFileDir.getAbsoluteFile()}));
            }
        }
    }

    public static synchronized void uploadLogFile() {
        int i = 0;
        synchronized (LOG.class) {
            if (logFileDir == null) {
                throw new IllegalStateException("logFileDir can not be null! call 'LOG.init' first!");
            }
            if (logFileDir.exists() && logFileDir.isDirectory() && logFileDir.list().length != 0) {
                int i2;
                String str;
                List arrayList = new ArrayList();
                for (Object add : logFileDir.list()) {
                    arrayList.add(add);
                }
                Collections.sort(arrayList);
                String str2 = (String) arrayList.get(arrayList.size() - 1);
                int size = arrayList.size();
                int i3;
                if (!str2.equals(getCurLogFileName())) {
                    i3 = size;
                    str = str2;
                    i2 = i3;
                } else if (arrayList.size() >= 2) {
                    i3 = size - 1;
                    str = (String) arrayList.get(arrayList.size() - 2);
                    i2 = i3;
                } else if (DBG) {
                    Log.d(TAG, "only log of today");
                }
                if (doUpload(toJsonString(str))) {
                    if (DBG) {
                        Log.d(TAG, "upload success");
                    }
                    size = i2;
                } else {
                    size = i2 - 1;
                }
                while (i < size) {
                    new File(logFileDir, (String) arrayList.get(i)).delete();
                    i++;
                }
            } else if (DBG) {
                Log.d(TAG, "log Dir not exist or no log");
            }
        }
    }

    private static boolean doUpload(String str) {
        if (str == null) {
            Log.e(TAG, "logFile to JosonString is null");
            return false;
        }
        if (DBG) {
            Log.d(TAG, str);
        }
        if (mcontext == null) {
            return false;
        }
        if (!CommonUtils.isNetWorkActive(mcontext)) {
            return false;
        }
        HttpResponse uploadCollectedData = new HttpFetcher().uploadCollectedData(mcontext, ConfigConstant.DATA_POST_ADDRESS, "bugTrack", str, GlobalConstants.d, true);
        if (uploadCollectedData == null) {
            return false;
        }
        if (uploadCollectedData.getStatusLine().getStatusCode() != ConfigConstant.RESPONSE_CODE) {
            return false;
        }
        return true;
    }

    public static synchronized void logMessage(List list) {
        synchronized (LOG.class) {
            if (logFileDir == null) {
                throw new IllegalStateException("logFileDir can not be null! call 'LOG.init' first!");
            }
            StringBuffer stringBuffer = new StringBuffer(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime()));
            stringBuffer.append("," + model);
            stringBuffer.append("," + pkgName);
            for (String str : list) {
                stringBuffer.append("," + str);
            }
            FileWriter fileWriter = null;
            try {
                long checkLogFile = checkLogFile();
                if (DBG) {
                    Log.d(TAG, "logFileSize=" + checkLogFile);
                }
                if (checkLogFile + ((long) stringBuffer.length()) <= ConfigConstant.MAX_SIZE_OF_FILE) {
                    fileWriter = new FileWriter(logFileName, true);
                } else {
                    fileWriter = new FileWriter(logFileName);
                }
                stringBuffer.append("\n");
                if (DBG) {
                    Log.d(TAG, "sb=" + stringBuffer.toString());
                }
                fileWriter.write(stringBuffer.toString());
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        Log.e(TAG, "close logfile failed");
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e3) {
                        Log.e(TAG, "close logfile failed");
                        e3.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException e4) {
                        Log.e(TAG, "close logfile failed");
                        e4.printStackTrace();
                    }
                }
            }
        }
    }

    private static synchronized long checkLogFile() {
        long length;
        synchronized (LOG.class) {
            logFileName = new File(logFileDir, getCurLogFileName());
            if (DBG) {
                Log.d(TAG, "current logfile is:" + logFileName.getAbsolutePath());
            }
            if (logFileName.exists()) {
                length = logFileName.length();
            } else {
                try {
                    logFileName.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                length = 0;
            }
        }
        return length;
    }

    private static String getCurLogFileName() {
        return new StringBuilder(String.valueOf(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()))).append(".log").toString();
    }

    private static void getInfo(Context context) {
        model = Build.MODEL;
        pkgName = context.getApplicationContext().getApplicationInfo().packageName;
        if (DBG) {
            Log.d(TAG, pkgName + "," + model);
        }
    }

    private static String toJsonString(String str) {
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        JSONObject jSONObject = new JSONObject();
        File file = new File(logFileDir, str);
        if (file == null || !file.exists() || file.length() == 0) {
            return null;
        }
        char[] cArr = new char[((int) file.length())];
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            try {
                fileReader.read(cArr);
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                try {
                    jSONObject.put(MiniDefine.m, "id");
                    jSONObject.put(ConfigConstant.LOG_JSON_STR_ERROR, String.valueOf(cArr));
                    return jSONObject.toString();
                } catch (JSONException e4) {
                    Log.e(TAG, e4.getMessage());
                    return null;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                try {
                    e.printStackTrace();
                    if (fileReader != null) {
                        return null;
                    }
                    try {
                        fileReader.close();
                        return null;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                e222 = e6;
                e222.printStackTrace();
                if (fileReader != null) {
                    return null;
                }
                try {
                    fileReader.close();
                    return null;
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                    return null;
                }
            }
        } catch (FileNotFoundException e7) {
            e = e7;
            fileReader = null;
            e.printStackTrace();
            if (fileReader != null) {
                return null;
            }
            fileReader.close();
            return null;
        } catch (IOException e8) {
            e2222 = e8;
            fileReader = null;
            e2222.printStackTrace();
            if (fileReader != null) {
                return null;
            }
            fileReader.close();
            return null;
        } catch (Throwable th3) {
            fileReader = null;
            th = th3;
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public static String getStackString(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
