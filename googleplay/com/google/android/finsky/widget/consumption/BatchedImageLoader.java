package com.google.android.finsky.widget.consumption;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.android.vending.R;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.BackgroundThreadFactory;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.widget.consumption.ImageBatch.ImageSpec;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BatchedImageLoader {
    private static final ExecutorService sFetchThread;
    private final BitmapLoader mBitmapLoader;
    private final Context mContext;
    private volatile Map<ImageSpec, Bitmap> mPreviousBitmaps;
    private final Thread mProcessingThread;
    private LinkedBlockingQueue<ImageBatch> mQueue;
    private int mTotalBitmapMemory;
    private final Semaphore mWaitLock;
    private final Cache mWidgetCache;

    public interface BatchedImageCallback {
        void onLoaded(Map<ImageSpec, Bitmap> map);
    }

    static {
        sFetchThread = Executors.newSingleThreadExecutor(new BackgroundThreadFactory());
    }

    public BatchedImageLoader(Context context, Cache cache) {
        this.mQueue = new LinkedBlockingQueue();
        this.mWaitLock = new Semaphore(0);
        this.mTotalBitmapMemory = 0;
        this.mWidgetCache = cache;
        this.mContext = context;
        this.mBitmapLoader = FinskyApp.get().getBitmapLoader();
        this.mProcessingThread = BackgroundThreadFactory.createThread("BatchedImageLoader.mProcessingThread", new Runnable() {
            public void run() {
                while (true) {
                    try {
                        BatchedImageLoader.this.handleBatchedRequest((ImageBatch) BatchedImageLoader.this.mQueue.take());
                    } catch (InterruptedException e) {
                        FinskyLog.e("Interrupted while trying to load images!", new Object[0]);
                    }
                }
            }
        });
        this.mProcessingThread.start();
    }

    private void handleBatchedRequest(ImageBatch item) throws InterruptedException {
        ImageSpec uriWrapper;
        int reusedBitmapMemory = 0;
        int reusedBitmapCount = 0;
        this.mTotalBitmapMemory = 0;
        Map<ImageSpec, Bitmap> loadedBitmaps = Maps.newHashMap();
        if (this.mPreviousBitmaps != null) {
            Iterator<ImageSpec> iterator = item.urisToLoad.iterator();
            while (iterator.hasNext()) {
                uriWrapper = (ImageSpec) iterator.next();
                for (ImageSpec previous : this.mPreviousBitmaps.keySet()) {
                    if (previous.satisfies(uriWrapper.uri, uriWrapper.width, uriWrapper.height)) {
                        Bitmap bm = (Bitmap) this.mPreviousBitmaps.get(previous);
                        if (bm != null) {
                            loadedBitmaps.put(previous, bm);
                            iterator.remove();
                            reusedBitmapMemory += bm.getByteCount();
                            reusedBitmapCount++;
                            break;
                        }
                    }
                }
            }
            this.mPreviousBitmaps = null;
        }
        for (ImageSpec uriWrapper2 : item.urisToLoad) {
            Bitmap bitmap;
            Uri uri = uriWrapper2.uri;
            if ("http".equals(uri.getScheme()) || "https".equals(uri.getScheme())) {
                bitmap = loadFromBitmapLoader(loadedBitmaps, uriWrapper2);
            } else {
                bitmap = loadFromProvider(uriWrapper2);
            }
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(this.mContext.getResources(), R.drawable.bg_widget_gradient);
            }
            loadedBitmaps.put(uriWrapper2, bitmap);
        }
        if (FinskyLog.DEBUG) {
            Object[] objArr = new Object[5];
            objArr[0] = Integer.valueOf(item.urisToLoad.size() + reusedBitmapCount);
            objArr[1] = Integer.valueOf((this.mTotalBitmapMemory + reusedBitmapMemory) / 1024);
            objArr[2] = Integer.valueOf(item.backendId);
            objArr[3] = Integer.valueOf(reusedBitmapCount);
            objArr[4] = Integer.valueOf(reusedBitmapMemory / 1024);
            FinskyLog.v("Loaded %s images [%s k] for backend=[%s] (%s were reused, %s k)", objArr);
        }
        final BatchedImageCallback callback = item.callback;
        final Map<ImageSpec, Bitmap> output = loadedBitmaps;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (callback != null) {
                    callback.onLoaded(output);
                }
                BatchedImageLoader.this.mWaitLock.release();
            }
        });
        this.mWaitLock.acquire();
        this.mPreviousBitmaps = loadedBitmaps;
    }

    private Bitmap getScaledBitmap(byte[] file, int targetWidth, int targetHeight) {
        if (targetWidth == 0 || targetHeight == 0) {
            return BitmapFactory.decodeByteArray(file, 0, file.length);
        }
        Options decodeOptions = new Options();
        decodeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(file, 0, file.length, decodeOptions);
        int actualWidth = decodeOptions.outWidth;
        int actualHeight = decodeOptions.outHeight;
        if (actualWidth < targetWidth || actualHeight < targetHeight) {
            return BitmapFactory.decodeByteArray(file, 0, file.length);
        }
        int finalWidth;
        int finalHeight;
        float aspectRatio = ((float) actualWidth) / ((float) actualHeight);
        int option1Height = (int) (((float) targetWidth) / aspectRatio);
        int option2Width = (int) (((float) targetHeight) * aspectRatio);
        decodeOptions.inJustDecodeBounds = false;
        if (option1Height > targetHeight) {
            finalWidth = targetWidth;
            finalHeight = option1Height;
        } else {
            finalWidth = option2Width;
            finalHeight = targetHeight;
        }
        decodeOptions.inSampleSize = findBestSampleSize(actualWidth, actualHeight, finalWidth, finalHeight);
        Bitmap tempBitmap = BitmapFactory.decodeByteArray(file, 0, file.length, decodeOptions);
        if (tempBitmap == null || (tempBitmap.getWidth() == finalWidth && tempBitmap.getHeight() == finalHeight)) {
            return tempBitmap;
        }
        Bitmap output = Bitmap.createScaledBitmap(tempBitmap, finalWidth, finalHeight, true);
        tempBitmap.recycle();
        return output;
    }

    private static int findBestSampleSize(int actualWidth, int actualHeight, int desiredWidth, int desiredHeight) {
        float n = 1.0f;
        while (((double) (2.0f * n)) <= Math.min(((double) actualWidth) / ((double) desiredWidth), ((double) actualHeight) / ((double) desiredHeight))) {
            n *= 2.0f;
        }
        return (int) n;
    }

    private Bitmap loadFromBitmapLoader(Map<ImageSpec, Bitmap> map, ImageSpec wrapper) {
        final Bitmap[] bitmap = new Bitmap[1];
        final Semaphore lock = new Semaphore(0);
        BitmapContainer container = this.mBitmapLoader.get(wrapper.uri.toString(), 0, 0, new BitmapLoadedHandler() {
            public void onResponse(BitmapContainer result) {
                bitmap[0] = result.getBitmap();
                lock.release();
            }
        });
        if (container.getBitmap() != null) {
            bitmap[0] = container.getBitmap();
        } else {
            lock.acquireUninterruptibly();
        }
        return bitmap[0];
    }

    private Bitmap loadFromProvider(final ImageSpec wrapper) {
        Entry entry = this.mWidgetCache.get(wrapper.toString());
        byte[] file = null;
        if (entry != null) {
            file = entry.data;
        } else {
            FutureTask<byte[]> future = new FutureTask(new Callable<byte[]>() {
                public byte[] call() throws Exception {
                    return BatchedImageLoader.this.loadFileFromUri(wrapper);
                }
            });
            try {
                sFetchThread.execute(future);
                file = (byte[]) future.get(((Long) G.consumptionAppImageTimeoutMs.get()).longValue(), TimeUnit.MILLISECONDS);
                if (file != null) {
                    Entry cachedEntry = new Entry();
                    cachedEntry.data = file;
                    cachedEntry.ttl = Long.MAX_VALUE;
                    this.mWidgetCache.put(wrapper.toString(), cachedEntry);
                }
            } catch (TimeoutException e) {
                FinskyLog.e("Timed out while waiting for %s", wrapper.uri);
            } catch (InterruptedException e2) {
                FinskyLog.e("Interrupted while loading %s", wrapper.uri);
            } catch (ExecutionException e3) {
                FinskyLog.e("ExecutionException while loading %s", wrapper.uri);
            }
        }
        Bitmap bitmap = null;
        if (file != null) {
            bitmap = getScaledBitmap(file, wrapper.width, wrapper.height);
            if (bitmap != null) {
                this.mTotalBitmapMemory += bitmap.getByteCount();
            } else {
                FinskyLog.e("Failed to decode bitmap %s", wrapper.uri);
            }
        } else {
            FinskyLog.e("File was not loaded for uri=[%s]", wrapper.uri);
        }
        return bitmap;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] loadFileFromUri(com.google.android.finsky.widget.consumption.ImageBatch.ImageSpec r9) {
        /*
        r8 = this;
        r0 = 1;
        r3 = 0;
    L_0x0002:
        r5 = 3;
        if (r0 >= r5) goto L_0x005d;
    L_0x0005:
        if (r3 != 0) goto L_0x005d;
    L_0x0007:
        r5 = r9.uri;	 Catch:{ IOException -> 0x004a }
        r5 = r5.buildUpon();	 Catch:{ IOException -> 0x004a }
        r6 = "w";
        r7 = r9.width;	 Catch:{ IOException -> 0x004a }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ IOException -> 0x004a }
        r5 = r5.appendQueryParameter(r6, r7);	 Catch:{ IOException -> 0x004a }
        r6 = "h";
        r7 = r9.height;	 Catch:{ IOException -> 0x004a }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ IOException -> 0x004a }
        r5 = r5.appendQueryParameter(r6, r7);	 Catch:{ IOException -> 0x004a }
        r4 = r5.build();	 Catch:{ IOException -> 0x004a }
        r5 = r8.mContext;	 Catch:{ IOException -> 0x004a }
        r5 = r5.getContentResolver();	 Catch:{ IOException -> 0x004a }
        r6 = "r";
        r1 = r5.openFileDescriptor(r4, r6);	 Catch:{ IOException -> 0x004a }
        if (r1 == 0) goto L_0x0047;
    L_0x0037:
        r5 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x004a }
        r6 = r1.getFileDescriptor();	 Catch:{ IOException -> 0x004a }
        r5.<init>(r6);	 Catch:{ IOException -> 0x004a }
        r3 = com.google.android.finsky.utils.Utils.readBytes(r5);	 Catch:{ IOException -> 0x004a }
        r1.close();	 Catch:{ IOException -> 0x004a }
    L_0x0047:
        r0 = r0 + 1;
        goto L_0x0002;
    L_0x004a:
        r2 = move-exception;
        r5 = "IOException parsing [%s]";
        r6 = 1;
        r6 = new java.lang.Object[r6];	 Catch:{ all -> 0x0059 }
        r7 = 0;
        r6[r7] = r9;	 Catch:{ all -> 0x0059 }
        com.google.android.finsky.utils.FinskyLog.d(r5, r6);	 Catch:{ all -> 0x0059 }
        r0 = r0 + 1;
        goto L_0x0002;
    L_0x0059:
        r5 = move-exception;
        r0 = r0 + 1;
        throw r5;
    L_0x005d:
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.finsky.widget.consumption.BatchedImageLoader.loadFileFromUri(com.google.android.finsky.widget.consumption.ImageBatch$ImageSpec):byte[]");
    }

    public Map<ImageSpec, Bitmap> getCachedBitmaps() {
        return this.mPreviousBitmaps;
    }

    public void enqueue(ImageBatch item) {
        this.mQueue.add(item);
    }
}
