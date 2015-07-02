package com.xiaomi.push.log;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.channel.commonutils.file.SDCardUtils;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MIPushLog2File implements LoggerInterface {
    private static final long FILE_MAX_SIZE = 1048576;
    private static final String LOCK_FILE = "log.lock";
    private static String MIPUSH_LOG_PATH = null;
    private static final String NEW_FILE_NAME = "log1.txt";
    private static final String OLD_FILE_NAME = "log0.txt";
    private static final SimpleDateFormat dateFormatter;
    private static List<Pair<String, Throwable>> logs;
    private static SerializedAsyncTaskProcessor mAsyncProcessor;
    private String mTag;
    private Context sAppContext;

    static {
        dateFormatter = new SimpleDateFormat("MM-dd HH:mm:ss aaa");
        mAsyncProcessor = new SerializedAsyncTaskProcessor(true);
        MIPUSH_LOG_PATH = "/MiPushLog";
        logs = Collections.synchronizedList(new ArrayList());
    }

    public MIPushLog2File(Context context) {
        this.sAppContext = context;
        if (context.getApplicationContext() != null) {
            this.sAppContext = context.getApplicationContext();
        }
        this.mTag = this.sAppContext.getPackageName();
    }

    public final void setTag(String tag) {
        this.mTag = tag;
    }

    public final void log(String msg) {
        log(msg, null);
    }

    public final void log(String text, Throwable t) {
        logs.add(new Pair(String.format("%1$s %2$s %3$s ", new Object[]{dateFormatter.format(new Date()), this.mTag, text}), t));
        mAsyncProcessor.addNewTask(new SerializedAsyncTask() {
            public void process() {
                if (!MIPushLog2File.logs.isEmpty()) {
                    try {
                        if (SDCardUtils.isSDCardUseful()) {
                            MIPushLog2File.this.writeLog2File();
                        } else {
                            Log.w(MIPushLog2File.this.mTag, "SDCard is unavailable.");
                        }
                    } catch (Exception e) {
                        Log.e(MIPushLog2File.this.mTag, "", e);
                    }
                }
            }
        });
    }

    private void writeLog2File() {
        Exception e;
        Throwable th;
        IOException e2;
        String str;
        String str2;
        RandomAccessFile lockFile = null;
        FileLock lock = null;
        BufferedWriter bw = null;
        try {
            File dir = new File(this.sAppContext.getExternalFilesDir(null) + MIPUSH_LOG_PATH);
            if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
                File file = new File(dir, LOCK_FILE);
                if (!file.exists() || file.isDirectory()) {
                    file.createNewFile();
                }
                RandomAccessFile lockFile2 = new RandomAccessFile(file, "rw");
                try {
                    lock = lockFile2.getChannel().lock();
                    BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(dir, NEW_FILE_NAME), true)));
                    while (!logs.isEmpty()) {
                        try {
                            Pair<String, Throwable> pair = (Pair) logs.remove(0);
                            String log = pair.first;
                            if (pair.second != null) {
                                log = log + "\n";
                                log = log + Log.getStackTraceString((Throwable) pair.second);
                            }
                            bw2.write(log + "\n");
                        } catch (Exception e3) {
                            e = e3;
                            bw = bw2;
                            lockFile = lockFile2;
                        } catch (Throwable th2) {
                            th = th2;
                            bw = bw2;
                            lockFile = lockFile2;
                        }
                    }
                    bw2.flush();
                    if (bw2 != null) {
                        bw2.close();
                        bw = null;
                    } else {
                        bw = bw2;
                    }
                    File newLogFile = new File(dir, NEW_FILE_NAME);
                    if (newLogFile.length() >= FILE_MAX_SIZE) {
                        File oldLogFile = new File(dir, OLD_FILE_NAME);
                        if (oldLogFile.exists() && oldLogFile.isFile()) {
                            oldLogFile.delete();
                        }
                        newLogFile.renameTo(oldLogFile);
                    }
                    if (bw != null) {
                        try {
                            bw.close();
                        } catch (IOException e22) {
                            Log.e(this.mTag, "", e22);
                        }
                    }
                    if (lock != null && lock.isValid()) {
                        try {
                            lock.release();
                        } catch (IOException e222) {
                            Log.e(this.mTag, "", e222);
                        }
                    }
                    if (lockFile2 != null) {
                        try {
                            lockFile2.close();
                        } catch (IOException e2222) {
                            Log.e(this.mTag, "", e2222);
                        }
                    }
                    lockFile = lockFile2;
                    return;
                } catch (Exception e4) {
                    e = e4;
                    lockFile = lockFile2;
                    try {
                        Log.e(this.mTag, "", e);
                        if (bw != null) {
                            try {
                                bw.close();
                            } catch (IOException e22222) {
                                Log.e(this.mTag, "", e22222);
                            }
                        }
                        if (lock != null && lock.isValid()) {
                            try {
                                lock.release();
                            } catch (IOException e222222) {
                                Log.e(this.mTag, "", e222222);
                            }
                        }
                        if (lockFile != null) {
                            try {
                                lockFile.close();
                            } catch (IOException e5) {
                                e222222 = e5;
                                str = this.mTag;
                                str2 = "";
                                Log.e(str, str2, e222222);
                                return;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (bw != null) {
                            try {
                                bw.close();
                            } catch (IOException e2222222) {
                                Log.e(this.mTag, "", e2222222);
                            }
                        }
                        if (lock != null && lock.isValid()) {
                            try {
                                lock.release();
                            } catch (IOException e22222222) {
                                Log.e(this.mTag, "", e22222222);
                            }
                        }
                        if (lockFile != null) {
                            try {
                                lockFile.close();
                            } catch (IOException e222222222) {
                                Log.e(this.mTag, "", e222222222);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    lockFile = lockFile2;
                    if (bw != null) {
                        bw.close();
                    }
                    lock.release();
                    if (lockFile != null) {
                        lockFile.close();
                    }
                    throw th;
                }
            }
            Log.w(this.mTag, "Create mipushlog directory fail.");
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e2222222222) {
                    Log.e(this.mTag, "", e2222222222);
                }
            }
            if (lock != null && lock.isValid()) {
                try {
                    lock.release();
                } catch (IOException e22222222222) {
                    Log.e(this.mTag, "", e22222222222);
                }
            }
            if (lockFile != null) {
                try {
                    lockFile.close();
                } catch (IOException e6) {
                    e22222222222 = e6;
                    str = this.mTag;
                    str2 = "";
                }
            }
        } catch (Exception e7) {
            e = e7;
            Log.e(this.mTag, "", e);
            if (bw != null) {
                bw.close();
            }
            lock.release();
            if (lockFile != null) {
                lockFile.close();
            }
        }
    }
}
