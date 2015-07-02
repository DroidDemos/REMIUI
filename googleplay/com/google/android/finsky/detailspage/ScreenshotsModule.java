package com.google.android.finsky.detailspage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.detailspage.ScreenshotsModuleLayout.ScreenshotsClickListener;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.Common.Image.Dimension;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;

public class ScreenshotsModule extends FinskyModule<Data> implements ScreenshotsClickListener {
    private Dimension[] mImageDimensions;
    private Drawable[] mImageDrawables;
    private AsyncTask<Void, Integer, Void> mImageLoadTask;
    private Image[] mImagesToLoad;
    private BitmapContainer[] mInFlightRequests;
    private int mNumImagesFailed;
    int mState;

    protected static class Data extends ModuleData {
        Document detailsDoc;

        protected Data() {
        }
    }

    static /* synthetic */ int access$204(ScreenshotsModule x0) {
        int i = x0.mNumImagesFailed + 1;
        x0.mNumImagesFailed = i;
        return i;
    }

    public int getLayoutResId() {
        return R.layout.screenshots_module;
    }

    public boolean readyForDisplay() {
        return this.mModuleData != null;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (savedModuleData != null) {
            loadImages();
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (this.mModuleData == null && detailsDoc.hasScreenshots()) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).detailsDoc = detailsDoc;
            loadImages();
        }
    }

    public void bindView(View view) {
        boolean isInLoadingMode;
        boolean isInErrorMode;
        if (this.mState == 0) {
            isInLoadingMode = true;
        } else {
            isInLoadingMode = false;
        }
        if (this.mState == 3) {
            isInErrorMode = true;
        } else {
            isInErrorMode = false;
        }
        ((ScreenshotsModuleLayout) view).bind(this.mImageDimensions, this.mImageDrawables, this, isInLoadingMode, isInErrorMode);
    }

    public void onDestroyModule() {
        if (this.mImageLoadTask != null) {
            this.mImageLoadTask.cancel(true);
        }
        if (this.mInFlightRequests != null) {
            for (BitmapContainer inFlightRequest : this.mInFlightRequests) {
                if (inFlightRequest != null) {
                    inFlightRequest.cancelRequest();
                }
            }
        }
        this.mState = 2;
    }

    private void loadImages() {
        this.mState = 0;
        this.mImagesToLoad = (Image[]) ((Data) this.mModuleData).detailsDoc.getImages(1).toArray(new Image[0]);
        final int imageCount = this.mImagesToLoad.length;
        this.mInFlightRequests = new BitmapContainer[imageCount];
        this.mImageDimensions = new Dimension[imageCount];
        for (int i = 0; i < imageCount; i++) {
            this.mImageDimensions[i] = this.mImagesToLoad[i].dimension;
        }
        this.mImageDrawables = new Drawable[imageCount];
        this.mNumImagesFailed = 0;
        if (this.mImageLoadTask != null) {
            this.mImageLoadTask.cancel(true);
        }
        this.mImageLoadTask = new AsyncTask<Void, Integer, Void>() {
            protected Void doInBackground(Void... params) {
                int imageIndex = 0;
                while (!isCancelled()) {
                    publishProgress(new Integer[]{Integer.valueOf(imageIndex)});
                    imageIndex++;
                    if (imageIndex >= imageCount) {
                        break;
                    }
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                    }
                }
                return null;
            }

            protected void onProgressUpdate(Integer... values) {
                ScreenshotsModule.this.loadNextImage(values[0].intValue());
            }
        };
        Utils.executeMultiThreaded(this.mImageLoadTask, new Void[0]);
    }

    private void loadNextImage(final int imageIndex) {
        final int numImagesToLoad = this.mImagesToLoad.length;
        Image img = this.mImagesToLoad[imageIndex];
        if (img != null) {
            int requestHeight = 0;
            if (img.supportsFifeUrlOptions) {
                requestHeight = (int) this.mContext.getResources().getDimension(R.dimen.screenshots_height);
            }
            BitmapContainer bitmapContainer = this.mBitmapLoader.get(img.imageUrl, 0, requestHeight, false, new BitmapLoadedHandler() {
                public void onResponse(BitmapContainer bitmapContainer) {
                    if (ScreenshotsModule.this.mState != 2) {
                        ScreenshotsModule.this.mInFlightRequests[imageIndex] = null;
                        Bitmap bitmap = bitmapContainer.getBitmap();
                        if (bitmap != null) {
                            ScreenshotsModule.this.mState = 1;
                            ScreenshotsModule.this.mImageDrawables[imageIndex] = ScreenshotsModule.this.createFadeInDrawable(bitmap);
                            ScreenshotsModule.this.mFinskyModuleController.refreshModule(ScreenshotsModule.this, false);
                        } else if (ScreenshotsModule.access$204(ScreenshotsModule.this) == numImagesToLoad) {
                            ScreenshotsModule.this.mState = 3;
                            ScreenshotsModule.this.mFinskyModuleController.refreshModule(ScreenshotsModule.this, false);
                        }
                    }
                }
            });
            Bitmap cachedBitmap = bitmapContainer.getBitmap();
            if (cachedBitmap != null) {
                this.mState = 1;
                this.mImageDrawables[imageIndex] = createFadeInDrawable(cachedBitmap);
                this.mFinskyModuleController.refreshModule(this, false);
                return;
            }
            this.mInFlightRequests[imageIndex] = bitmapContainer;
        }
    }

    private TransitionDrawable createFadeInDrawable(Bitmap bitmap) {
        TransitionDrawable fadeInDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(0), new BitmapDrawable(this.mContext.getResources(), bitmap)});
        fadeInDrawable.setCrossFadeEnabled(true);
        fadeInDrawable.startTransition(250);
        return fadeInDrawable;
    }

    public void onImageClick(int index) {
        this.mNavigationManager.goToScreenshots(((Data) this.mModuleData).detailsDoc, index);
    }

    public void onRetryClick() {
        loadImages();
        this.mFinskyModuleController.refreshModule(this, false);
    }
}
