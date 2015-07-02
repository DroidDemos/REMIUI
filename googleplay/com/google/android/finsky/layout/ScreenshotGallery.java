package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.FrameLayout;
import com.android.vending.R;
import com.google.android.finsky.adapters.ImageStripAdapter;
import com.google.android.finsky.adapters.ImageStripAdapter.OnImageChildViewTapListener;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DetailsSectionStack.NoBottomSeparator;
import com.google.android.finsky.layout.DetailsSectionStack.NoTopSeparator;
import com.google.android.finsky.layout.LayoutSwitcher.RetryButtonListener;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Utils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ScreenshotGallery extends FrameLayout implements OnImageChildViewTapListener, NoBottomSeparator, NoTopSeparator, RetryButtonListener {
    private BitmapLoader mBitmapLoader;
    private List<Image> mCombinedImagesToLoad;
    private Document mDocument;
    private final Handler mHandler;
    private boolean mHasDetailsLoaded;
    private AsyncTask<Void, Integer, Void> mImageLoadTask;
    private HorizontalStrip mImageStrip;
    private ImageStripAdapter mImageStripAdapter;
    private SparseArray<List<Image>> mImageUrls;
    private final List<BitmapContainer> mInFlightRequests;
    private final Runnable mInvalidateRunnable;
    private int mLastRequestedHeight;
    private LayoutSwitcher mLayoutSwitcher;
    private NavigationManager mNavigationManager;
    private int mNumImagesFailed;
    private Resources mResources;

    static /* synthetic */ int access$404(ScreenshotGallery x0) {
        int i = x0.mNumImagesFailed + 1;
        x0.mNumImagesFailed = i;
        return i;
    }

    public ScreenshotGallery(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScreenshotGallery(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        this.mInFlightRequests = Lists.newArrayList();
        this.mImageUrls = new SparseArray();
        this.mHandler = new Handler(Looper.myLooper());
        this.mInvalidateRunnable = new Runnable() {
            public void run() {
                ScreenshotGallery.this.mImageStripAdapter.notifyDataSetInvalidated();
            }
        };
        this.mImageUrls.put(1, Collections.emptyList());
        this.mImageUrls.put(3, Collections.emptyList());
        this.mImageUrls.put(13, Collections.emptyList());
        this.mResources = context.getResources();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mImageStrip = (HorizontalStrip) findViewById(R.id.strip);
        this.mLayoutSwitcher = new LayoutSwitcher(this, R.id.strip, this);
        this.mLayoutSwitcher.switchToLoadingDelayed(500);
    }

    private boolean isSame(List<Image> list1, List<Image> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list1.size(); i++) {
            if (!((Image) list1.get(i)).imageUrl.equals(((Image) list2.get(i)).imageUrl)) {
                return false;
            }
        }
        return true;
    }

    public void bind(Document doc, BitmapLoader bitmapLoader, NavigationManager navigationManager, boolean hasDetailsLoaded) {
        if (hasDetailsLoaded) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        this.mBitmapLoader = bitmapLoader;
        this.mNavigationManager = navigationManager;
        this.mDocument = doc;
        this.mHasDetailsLoaded = hasDetailsLoaded;
        startImageLoadingTaskIfNecessary();
    }

    public void setMargins(int leadingMargin, int gapMargin) {
        this.mImageStrip.setMargins(leadingMargin, gapMargin);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        startImageLoadingTaskIfNecessary();
    }

    private void startImageLoadingTaskIfNecessary() {
        int height = getHeight();
        if (height != 0 && height != this.mLastRequestedHeight && this.mBitmapLoader != null && getVisibility() != 8) {
            Collection previewImages = this.mDocument.hasScreenshots() ? this.mDocument.getImages(1) : Collections.emptyList();
            if (previewImages.isEmpty()) {
                if (this.mHasDetailsLoaded) {
                    setVisibility(8);
                }
            } else if (this.mDocument == null || !this.mDocument.getDocId().equals(this.mDocument.getDocId()) || getVisibility() != 0 || !isSame(previewImages, (List) this.mImageUrls.get(1))) {
                this.mImageUrls.clear();
                this.mImageUrls.put(1, previewImages);
                this.mCombinedImagesToLoad = Lists.newArrayList(previewImages);
                if (this.mImageStripAdapter != null) {
                    this.mImageStripAdapter.unregisterAll();
                }
                this.mImageStripAdapter = new ImageStripAdapter(previewImages.size(), this);
                this.mImageStrip.setAdapter(this.mImageStripAdapter);
                this.mLastRequestedHeight = getHeight();
                clearPendingRequests();
                if (this.mImageLoadTask != null) {
                    this.mImageLoadTask.cancel(true);
                }
                this.mImageLoadTask = new AsyncTask<Void, Integer, Void>() {
                    protected Void doInBackground(Void... params) {
                        int imageIndex = 0;
                        while (!isCancelled() && ScreenshotGallery.this.mImageStripAdapter != null) {
                            publishProgress(new Integer[]{Integer.valueOf(imageIndex)});
                            imageIndex++;
                            if (imageIndex >= ScreenshotGallery.this.mCombinedImagesToLoad.size()) {
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
                        ScreenshotGallery.this.loadNextImage(values[0].intValue());
                    }
                };
                Utils.executeMultiThreaded(this.mImageLoadTask, new Void[0]);
            }
        }
    }

    public void onImageChildViewTap(int index) {
        this.mNavigationManager.goToScreenshots(this.mDocument, index);
    }

    public void onRetry() {
        this.mLastRequestedHeight = 0;
        startImageLoadingTaskIfNecessary();
    }

    private void loadNextImage(final int imageIndex) {
        if (this.mImageStripAdapter != null) {
            final int numImagesToLoad = this.mCombinedImagesToLoad.size();
            this.mNumImagesFailed = 0;
            boolean switchToDataMode = false;
            Image img = (Image) this.mCombinedImagesToLoad.get(imageIndex);
            if (img == null) {
                this.mInFlightRequests.add(null);
                return;
            }
            this.mImageStripAdapter.setImageDimensionAt(imageIndex, img.dimension);
            if (img.supportsFifeUrlOptions) {
                int requestHeight = getHeight();
            }
            BitmapContainer bitmapContainer = this.mBitmapLoader.get(img.imageUrl, 0, getHeight(), new BitmapLoadedHandler() {
                public void onResponse(BitmapContainer bitmapContainer) {
                    if (ScreenshotGallery.this.mImageStripAdapter != null) {
                        ScreenshotGallery.this.mInFlightRequests.set(imageIndex, null);
                        if (bitmapContainer.getBitmap() != null) {
                            if (!ScreenshotGallery.this.mLayoutSwitcher.isDataMode()) {
                                ScreenshotGallery.this.mLayoutSwitcher.switchToDataMode();
                            }
                            TransitionDrawable fadeInDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(0), new BitmapDrawable(ScreenshotGallery.this.mResources, bitmap)});
                            fadeInDrawable.setCrossFadeEnabled(true);
                            fadeInDrawable.startTransition(250);
                            ScreenshotGallery.this.mImageStripAdapter.setImageAt(imageIndex, fadeInDrawable);
                        } else if (ScreenshotGallery.access$404(ScreenshotGallery.this) == numImagesToLoad) {
                            ScreenshotGallery.this.mLayoutSwitcher.switchToErrorMode(ScreenshotGallery.this.getContext().getString(R.string.screenshots_not_loaded));
                        }
                    }
                }
            });
            this.mInFlightRequests.add(bitmapContainer);
            if (bitmapContainer.getBitmap() != null) {
                switchToDataMode = true;
                this.mImageStripAdapter.setImageAt(imageIndex, new BitmapDrawable(this.mResources, bitmapContainer.getBitmap()));
                this.mInFlightRequests.set(imageIndex, null);
            }
            if (switchToDataMode) {
                this.mLayoutSwitcher.switchToDataMode();
                this.mHandler.post(this.mInvalidateRunnable);
            }
        }
    }

    private void clearPendingRequests() {
        for (BitmapContainer container : this.mInFlightRequests) {
            if (container != null) {
                container.cancelRequest();
            }
        }
        this.mInFlightRequests.clear();
    }

    protected void onDetachedFromWindow() {
        if (this.mImageLoadTask != null) {
            this.mImageLoadTask.cancel(true);
        }
        if (this.mImageStripAdapter != null) {
            this.mImageStripAdapter.unregisterAll();
        }
        this.mHandler.removeCallbacks(this.mInvalidateRunnable);
        this.mImageStrip.onDestroyView();
        this.mImageStripAdapter = null;
        super.onDetachedFromWindow();
    }
}
