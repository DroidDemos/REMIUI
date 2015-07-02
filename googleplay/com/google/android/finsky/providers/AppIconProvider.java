package com.google.android.finsky.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.support.v4.util.LruCache;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.BackgroundThreadFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppIconProvider extends ContentProvider {
    public static final Uri CONTENT_URI;
    private static int mIconSize;
    private Semaphore mIoSemaphore;

    public static class AppIconLoader {
        private static IconCache sIconCache;
        private String mAppPackage;
        private File mIconFile;

        public static synchronized void initialize(File[] files) {
            synchronized (AppIconLoader.class) {
                if (sIconCache == null) {
                    sIconCache = new IconCache(20);
                    for (File file : files) {
                        String filename = file.getName();
                        if (isTempFileName(filename)) {
                            sIconCache.put(packageNameFromFile(filename), file);
                        }
                    }
                }
            }
        }

        public AppIconLoader(Context context, String appPackage) {
            if (sIconCache == null) {
                throw new IllegalStateException("AppIconLoader must be initialized before use.");
            }
            this.mAppPackage = appPackage;
            this.mIconFile = (File) sIconCache.get(appPackage);
            if (this.mIconFile != null && this.mIconFile.lastModified() + 10800000 < System.currentTimeMillis()) {
                sIconCache.remove(appPackage);
                this.mIconFile = null;
            }
            if (this.mIconFile == null) {
                this.mIconFile = new File(context.getCacheDir(), fileNameFromPackage(this.mAppPackage));
            }
        }

        public static boolean isTempFileName(String filename) {
            return filename.startsWith("thmb_");
        }

        public static String fileNameFromPackage(String appPackage) {
            return "thmb_" + appPackage;
        }

        public static String packageNameFromFile(String filename) {
            return filename.substring(5);
        }

        public File getIconFile() {
            return this.mIconFile;
        }

        public void loadToFileFromUrl(Image image, final TimedOnCompleteListener listener) {
            if (sIconCache.get(this.mAppPackage) != null) {
                listener.callOnComplete();
                return;
            }
            int requestIconSize;
            BitmapLoader bitmapLoader = FinskyApp.get().getBitmapLoader();
            if (image.supportsFifeUrlOptions) {
                requestIconSize = AppIconProvider.mIconSize;
            } else {
                requestIconSize = 0;
            }
            BitmapContainer container = bitmapLoader.get(image.imageUrl, requestIconSize, requestIconSize, false, new BitmapLoadedHandler() {
                public void onResponse(BitmapContainer result) {
                    if (result.getBitmap() == null) {
                        FinskyLog.w("Failed to fetch icon.", new Object[0]);
                        listener.callOnComplete();
                        return;
                    }
                    AppIconLoader.this.loadToFileFromBitmap(result.getBitmap(), listener);
                }
            });
            if (container.getBitmap() != null) {
                loadToFileFromBitmap(container.getBitmap(), listener);
            }
        }

        private void loadToFileFromBitmap(Bitmap bitmap, TimedOnCompleteListener listener) {
            try {
                FileOutputStream out = new FileOutputStream(this.mIconFile);
                bitmap.compress(CompressFormat.PNG, 0, out);
                out.close();
            } catch (IOException ioe) {
                FinskyLog.w("Failed to write icon blob to file: " + ioe, new Object[0]);
            } finally {
                sIconCache.put(packageNameFromFile(this.mIconFile.getName()), this.mIconFile);
                listener.callOnComplete();
            }
        }

        public void loadToFileFromBlob(byte[] blob, TimedOnCompleteListener listener) {
            if (sIconCache.get(this.mAppPackage) != null) {
                listener.callOnComplete();
                return;
            }
            try {
                FileOutputStream out = new FileOutputStream(this.mIconFile);
                out.write(blob);
                out.close();
            } catch (IOException ioe) {
                FinskyLog.w("Failed to write icon blob to file: " + ioe, new Object[0]);
            } finally {
                sIconCache.put(packageNameFromFile(this.mIconFile.getName()), this.mIconFile);
                listener.callOnComplete();
            }
        }
    }

    private static class IconCache extends LruCache<String, File> {
        public IconCache(int maxSize) {
            super(maxSize);
        }

        protected void entryRemoved(boolean evicted, String key, File oldValue, File newValue) {
            oldValue.delete();
        }
    }

    public static abstract class TimedOnCompleteListener {
        private AtomicBoolean completed;

        protected abstract void onComplete();

        public TimedOnCompleteListener() {
            this.completed = new AtomicBoolean(false);
            new Timer().schedule(new TimerTask() {
                public void run() {
                    TimedOnCompleteListener.this.callOnComplete();
                }
            }, 200);
        }

        public void callOnComplete() {
            if (this.completed.compareAndSet(false, true)) {
                onComplete();
            }
        }
    }

    public android.os.ParcelFileDescriptor openFile(android.net.Uri r9, java.lang.String r10) throws java.io.FileNotFoundException {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:16:? in {7, 12, 14, 15, 17, 18} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
*/
        /*
        r8 = this;
        r2 = android.os.Binder.clearCallingIdentity();
        r5 = r8.mIoSemaphore;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r5.acquire();	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r5 = r9.getPathSegments();	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r5 = r5.size();	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = 1;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        if (r5 != r6) goto L_0x003b;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
    L_0x0014:
        r5 = r9.getPathSegments();	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = 0;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r0 = r5.get(r6);	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r0 = (java.lang.String) r0;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r1 = new com.google.android.finsky.providers.AppIconProvider$AppIconLoader;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r5 = r8.getContext();	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r1.<init>(r5, r0);	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r5 = r1.getIconFile();	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r5 = android.os.ParcelFileDescriptor.open(r5, r6);	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = r8.mIoSemaphore;
        r6.release();
        android.os.Binder.restoreCallingIdentity(r2);
    L_0x003a:
        return r5;
    L_0x003b:
        r5 = new java.lang.IllegalArgumentException;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6.<init>();	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r7 = "Invalid URI: ";	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = r6.append(r7);	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = r6.append(r9);	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = r6.toString();	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r5.<init>(r6);	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        throw r5;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
    L_0x0054:
        r4 = move-exception;
        r5 = "Opening file interrupted while waiting for IO.";	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = 0;	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r6 = new java.lang.Object[r6];	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        com.google.android.finsky.utils.FinskyLog.w(r5, r6);	 Catch:{ InterruptedException -> 0x0054, all -> 0x0067 }
        r5 = 0;
        r6 = r8.mIoSemaphore;
        r6.release();
        android.os.Binder.restoreCallingIdentity(r2);
        goto L_0x003a;
    L_0x0067:
        r5 = move-exception;
        r6 = r8.mIoSemaphore;
        r6.release();
        android.os.Binder.restoreCallingIdentity(r2);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.providers.AppIconProvider.openFile(android.net.Uri, java.lang.String):android.os.ParcelFileDescriptor");
    }

    static {
        CONTENT_URI = Uri.parse("content://com.google.android.finsky.AppIconProvider");
        mIconSize = 0;
    }

    public AppIconProvider() {
        this.mIoSemaphore = new Semaphore(0);
    }

    public boolean onCreate() {
        BackgroundThreadFactory.createThread(new Runnable() {
            public void run() {
                AppIconLoader.initialize(AppIconProvider.this.getContext().getCacheDir().listFiles());
                AppIconProvider.this.mIoSemaphore.release();
            }
        }).start();
        mIconSize = getContext().getResources().getDimensionPixelSize(R.dimen.suggestion_icon_size);
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    public String getType(Uri uri) {
        return "image/png";
    }

    public int delete(Uri uri, String arg1, String[] arg2) {
        return 0;
    }

    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
