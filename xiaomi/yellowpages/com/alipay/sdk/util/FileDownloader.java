package com.alipay.sdk.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

public final class FileDownloader {
    public boolean a;
    private String b;
    private String c;
    private String d;
    private IDownloadProgress e;
    private FileFetch f;

    public interface IDownloadProgress {
        void a();

        void a(float f);

        void b();
    }

    class ProgressOutput extends Handler {
        WeakReference a;
        private boolean b;

        private ProgressOutput(Looper looper, FileDownloader fileDownloader) {
            super(looper);
            this.b = false;
            this.a = new WeakReference(fileDownloader);
        }

        public void handleMessage(Message message) {
            if (((FileDownloader) this.a.get()).e != null) {
                float f = 50.0f;
                try {
                    if (((FileDownloader) this.a.get()).a) {
                        f = (float) ((100 * ((FileDownloader) this.a.get()).f.a()) / ((FileDownloader) this.a.get()).f.b());
                    } else if (((FileDownloader) this.a.get()).f.c()) {
                        f = 100.0f;
                    }
                    if (!((FileDownloader) this.a.get()).f.c()) {
                        ((FileDownloader) this.a.get()).e.a(f);
                    } else if (f == 100.0f && !this.b) {
                        ((FileDownloader) this.a.get()).e.a();
                        this.b = true;
                    } else if (f > 100.0f) {
                        ((FileDownloader) this.a.get()).f();
                        ((FileDownloader) this.a.get()).e.b();
                    } else if (!this.b) {
                        ((FileDownloader) this.a.get()).e.b();
                    }
                } catch (Exception e) {
                    ((FileDownloader) this.a.get()).e.b();
                }
            }
        }
    }

    public FileDownloader() {
        this.a = false;
    }

    public FileDownloader(boolean z) {
        this.a = z;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final void a(boolean z) {
        this.a = z;
    }

    protected final boolean a() {
        return this.a;
    }

    public final void b(String str) {
        this.c = str;
        this.d = str + ".tmp";
    }

    public final void a(IDownloadProgress iDownloadProgress) {
        if (iDownloadProgress != null) {
            this.e = iDownloadProgress;
        }
    }

    public void b() {
        final ProgressOutput progressOutput = new ProgressOutput(Looper.getMainLooper(), this);
        new Thread(new Runnable(this) {
            final /* synthetic */ FileDownloader b;

            public void run() {
                this.b.f = new FileFetch(this.b.b, this.b.c, this.b);
                long j = -1;
                if (this.b.a) {
                    j = this.b.e();
                    if (j <= 0) {
                        progressOutput.sendEmptyMessage(0);
                        return;
                    }
                }
                this.b.f();
                if (this.b.a) {
                    this.b.g();
                    if (this.b.f.b() != j) {
                        this.b.f();
                        this.b.f.a(0);
                        this.b.f.b(j);
                    }
                }
                new Thread(this.b.f).start();
                progressOutput.b = false;
                while (!this.b.f.c()) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressOutput.sendEmptyMessage(0);
                }
                progressOutput.sendEmptyMessage(0);
            }
        }).start();
    }

    public void c() {
        this.f.d();
    }

    private long e() {
        long j = -1;
        try {
            j = a(this.b, false).getContentLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    private void f() {
        File file = new File(this.c);
        if (file.exists()) {
            file.delete();
        }
        file = new File(this.d);
        if (file.exists()) {
            file.delete();
        }
    }

    protected void d() {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(this.d);
            try {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                try {
                    objectOutputStream.writeLong(this.f.a());
                    objectOutputStream.writeLong(this.f.b());
                    objectOutputStream.flush();
                    try {
                        fileOutputStream.close();
                    } catch (Exception e2) {
                    }
                    try {
                        objectOutputStream.close();
                    } catch (Exception e3) {
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileOutputStream2 = fileOutputStream;
                    try {
                        e.printStackTrace();
                        try {
                            fileOutputStream2.close();
                        } catch (Exception e5) {
                        }
                        try {
                            objectOutputStream.close();
                        } catch (Exception e6) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        try {
                            fileOutputStream.close();
                        } catch (Exception e7) {
                        }
                        try {
                            objectOutputStream.close();
                        } catch (Exception e8) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream.close();
                    objectOutputStream.close();
                    throw th;
                }
            } catch (Exception e9) {
                e = e9;
                objectOutputStream = null;
                fileOutputStream2 = fileOutputStream;
                e.printStackTrace();
                fileOutputStream2.close();
                objectOutputStream.close();
            } catch (Throwable th4) {
                th = th4;
                objectOutputStream = null;
                fileOutputStream.close();
                objectOutputStream.close();
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            objectOutputStream = null;
            e.printStackTrace();
            fileOutputStream2.close();
            objectOutputStream.close();
        } catch (Throwable th5) {
            th = th5;
            objectOutputStream = null;
            fileOutputStream = null;
            fileOutputStream.close();
            objectOutputStream.close();
            throw th;
        }
    }

    private void g() {
        ObjectInputStream objectInputStream;
        Exception e;
        Throwable th;
        FileInputStream fileInputStream = null;
        FileInputStream fileInputStream2;
        try {
            fileInputStream2 = new FileInputStream(this.d);
            try {
                objectInputStream = new ObjectInputStream(fileInputStream2);
                try {
                    this.f.a(objectInputStream.readLong());
                    this.f.b(objectInputStream.readLong());
                    try {
                        fileInputStream2.close();
                    } catch (Exception e2) {
                    }
                    try {
                        objectInputStream.close();
                    } catch (Exception e3) {
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileInputStream = fileInputStream2;
                    try {
                        e.printStackTrace();
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                        }
                        try {
                            objectInputStream.close();
                        } catch (Exception e6) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        try {
                            fileInputStream2.close();
                        } catch (Exception e7) {
                        }
                        try {
                            objectInputStream.close();
                        } catch (Exception e8) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream2.close();
                    objectInputStream.close();
                    throw th;
                }
            } catch (Exception e9) {
                e = e9;
                objectInputStream = null;
                fileInputStream = fileInputStream2;
                e.printStackTrace();
                fileInputStream.close();
                objectInputStream.close();
            } catch (Throwable th4) {
                th = th4;
                objectInputStream = null;
                fileInputStream2.close();
                objectInputStream.close();
                throw th;
            }
        } catch (Exception e10) {
            e = e10;
            objectInputStream = null;
            e.printStackTrace();
            fileInputStream.close();
            objectInputStream.close();
        } catch (Throwable th5) {
            th = th5;
            objectInputStream = null;
            fileInputStream2 = null;
            fileInputStream2.close();
            objectInputStream.close();
            throw th;
        }
    }

    public static HttpEntity a(String str, boolean z) {
        try {
            HttpUriRequest httpGet = new HttpGet(str);
            HttpClient defaultHttpClient = new DefaultHttpClient();
            if (z) {
                httpGet.addHeader("Accept-Encoding", "gzip");
            }
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            int statusCode = execute.getStatusLine().getStatusCode();
            if (statusCode == ConfigConstant.RESPONSE_CODE) {
                return execute.getEntity();
            }
            throw new Exception("net work exception,ErrorCode :" + statusCode);
        } catch (SSLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
