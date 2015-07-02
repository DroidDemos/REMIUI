package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.js;
import com.google.android.gms.internal.ju;
import com.google.android.gms.internal.lc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public final class ImageManager {
    private static final Object Ux;
    private static HashSet<Uri> Uy;
    private final ExecutorService UB;
    private final b UC;
    private final js UD;
    private final Map<a, ImageReceiver> UE;
    private final Map<Uri, ImageReceiver> UF;
    private final Map<Uri, Long> UG;
    private final Context mContext;
    private final Handler mHandler;

    private final class ImageReceiver extends ResultReceiver {
        private final ArrayList<a> UH;
        final /* synthetic */ ImageManager UI;
        private final Uri mUri;

        public void onReceiveResult(int resultCode, Bundle resultData) {
            this.UI.UB.execute(new c(this.UI, this.mUri, (ParcelFileDescriptor) resultData.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    private static final class b extends lc<a, Bitmap> {
        protected int a(a aVar, Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        protected void a(boolean z, a aVar, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, aVar, bitmap, bitmap2);
        }

        protected /* synthetic */ void entryRemoved(boolean x0, Object x1, Object x2, Object x3) {
            a(x0, (a) x1, (Bitmap) x2, (Bitmap) x3);
        }

        protected /* synthetic */ int sizeOf(Object x0, Object x1) {
            return a((a) x0, (Bitmap) x1);
        }
    }

    private final class c implements Runnable {
        final /* synthetic */ ImageManager UI;
        private final ParcelFileDescriptor UJ;
        private final Uri mUri;

        public c(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.UI = imageManager;
            this.mUri = uri;
            this.UJ = parcelFileDescriptor;
        }

        public void run() {
            ju.be("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.UJ != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.UJ.getFileDescriptor());
                } catch (Throwable e) {
                    Log.e("ImageManager", "OOM while loading bitmap for uri: " + this.mUri, e);
                    z = true;
                }
                try {
                    this.UJ.close();
                } catch (Throwable e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.UI.mHandler.post(new f(this.UI, this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                Log.w("ImageManager", "Latch interrupted while posting " + this.mUri);
            }
        }
    }

    private final class f implements Runnable {
        final /* synthetic */ ImageManager UI;
        private boolean UL;
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch mu;

        public f(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.UI = imageManager;
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.UL = z;
            this.mu = countDownLatch;
        }

        private void a(ImageReceiver imageReceiver, boolean z) {
            ArrayList a = imageReceiver.UH;
            int size = a.size();
            for (int i = 0; i < size; i++) {
                a aVar = (a) a.get(i);
                if (z) {
                    aVar.a(this.UI.mContext, this.mBitmap, false);
                } else {
                    this.UI.UG.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                    aVar.a(this.UI.mContext, this.UI.UD, false);
                }
                if (!(aVar instanceof com.google.android.gms.common.images.a.c)) {
                    this.UI.UE.remove(aVar);
                }
            }
        }

        public void run() {
            ju.bd("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (this.UI.UC != null) {
                if (this.UL) {
                    this.UI.UC.evictAll();
                    System.gc();
                    this.UL = false;
                    this.UI.mHandler.post(this);
                    return;
                } else if (z) {
                    this.UI.UC.put(new a(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) this.UI.UF.remove(this.mUri);
            if (imageReceiver != null) {
                a(imageReceiver, z);
            }
            this.mu.countDown();
            synchronized (ImageManager.Ux) {
                ImageManager.Uy.remove(this.mUri);
            }
        }
    }

    static {
        Ux = new Object();
        Uy = new HashSet();
    }
}
