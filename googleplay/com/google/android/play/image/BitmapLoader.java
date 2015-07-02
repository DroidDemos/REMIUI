package com.google.android.play.image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.android.play.image.BitmapCache.BitmapCacheEntry;
import com.google.android.play.utils.PlayCommonLog;
import com.google.android.play.utils.collections.Lists;
import com.google.android.play.utils.collections.Maps;
import com.google.android.play.utils.config.PlayG;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BitmapLoader {
    private static int MIN_CACHE_SIZE_BYTES;
    private static int MIN_NUM_IMAGES_IN_CACHE;
    private final HashMap<String, RequestListenerWrapper> mBatchedResponses;
    private final BitmapCache mCachedRemoteImages;
    private final Handler mHandler;
    private final HashMap<String, RequestListenerWrapper> mInFlightRequests;
    private final int mMaxImageSizeToCacheInBytes;
    private final RequestQueue mRequestQueue;
    private Runnable mRunnable;
    private TentativeGcRunner mTentativeGcRunner;

    public static class DebugImageRequest extends ImageRequest {
        private static final Matrix IDENTITY;
        private boolean mResponseDelivered;

        static {
            IDENTITY = new Matrix();
        }

        public DebugImageRequest(String url, int maxWidth, int maxHeight, Config decodeConfig, Listener<Bitmap> bitmapListener, ErrorListener errorListener) {
            super(url, bitmapListener, maxWidth, maxHeight, decodeConfig, errorListener);
        }

        protected Response<Bitmap> parseNetworkResponse(NetworkResponse response) {
            Response<Bitmap> original = super.parseNetworkResponse(response);
            return (original.isSuccess() && ((Boolean) PlayG.debugImageSizes.get()).booleanValue()) ? Response.success(overlayDebugInfo((Bitmap) original.result, response.data.length / 1024), original.cacheEntry) : original;
        }

        protected void deliverResponse(Bitmap response) {
            if (!this.mResponseDelivered) {
                this.mResponseDelivered = true;
                super.deliverResponse(response);
            }
        }

        private Bitmap overlayDebugInfo(Bitmap bitmap, int responseSizeInK) {
            Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            Canvas canvas = new Canvas(newBitmap);
            canvas.drawBitmap(bitmap, IDENTITY, null);
            Paint paint = new Paint(8);
            paint.setColor(getUrl().contains("ggpht.com") ? -16711681 : -65281);
            paint.setStrokeWidth(3.0f);
            paint.setTextAlign(Align.LEFT);
            String sizeStr = String.format("%dk", new Object[]{Integer.valueOf(responseSizeInK)});
            String heightString = String.format("%dh", new Object[]{Integer.valueOf(bitmap.getHeight())});
            String widthString = String.format("%dw", new Object[]{Integer.valueOf(bitmap.getWidth())});
            float textSize = 40.0f;
            while (true) {
                paint.setTextSize(textSize);
                if (((double) textSize) * 3.1d > ((double) canvas.getHeight()) || ((double) Math.max(Math.max(paint.measureText(heightString), paint.measureText(widthString)), paint.measureText(sizeStr))) * 1.1d >= ((double) canvas.getWidth())) {
                    textSize = (float) (0.8d * ((double) textSize));
                } else {
                    float y = ((float) (canvas.getHeight() / 2)) - textSize;
                    canvas.drawText(sizeStr, 4.0f, y, paint);
                    y += 5.0f + textSize;
                    canvas.drawText(heightString, 4.0f, y, paint);
                    canvas.drawText(widthString, 4.0f, y + (5.0f + textSize), paint);
                    bitmap.recycle();
                    return newBitmap;
                }
            }
        }
    }

    public interface BitmapLoadedHandler extends Listener<BitmapContainer> {
        void onResponse(BitmapContainer bitmapContainer);
    }

    private interface RemoteRequestCreator {
        Request<?> create();
    }

    public class BitmapContainer {
        private Bitmap mBitmap;
        private BitmapLoadedHandler mBitmapLoaded;
        private final String mModifiedUrl;
        private final int mRequestHeight;
        private final String mRequestUrl;
        private final int mRequestWidth;

        public BitmapContainer(Bitmap bitmap, String requestUrl, String modifiedUrl, int requestWidth, int requestHeight, BitmapLoadedHandler handler) {
            this.mBitmap = bitmap;
            this.mRequestUrl = requestUrl;
            this.mModifiedUrl = modifiedUrl;
            this.mRequestWidth = requestWidth;
            this.mRequestHeight = requestHeight;
            this.mBitmapLoaded = handler;
        }

        public void cancelRequest() {
            if (this.mBitmapLoaded != null) {
                RequestListenerWrapper wrapper = (RequestListenerWrapper) BitmapLoader.this.mInFlightRequests.get(this.mModifiedUrl);
                if (wrapper == null) {
                    wrapper = (RequestListenerWrapper) BitmapLoader.this.mBatchedResponses.get(this.mModifiedUrl);
                    if (wrapper != null) {
                        wrapper.removeHandlerAndCancelIfNecessary(this);
                        if (wrapper.handlers.size() == 0) {
                            BitmapLoader.this.mBatchedResponses.remove(this.mModifiedUrl);
                        }
                    }
                } else if (wrapper.removeHandlerAndCancelIfNecessary(this)) {
                    BitmapLoader.this.mInFlightRequests.remove(this.mModifiedUrl);
                }
            }
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public String getRequestUrl() {
            return this.mRequestUrl;
        }

        public int getRequestWidth() {
            return this.mRequestWidth;
        }

        public int getRequestHeight() {
            return this.mRequestHeight;
        }
    }

    private class RequestListenerWrapper {
        private List<BitmapContainer> handlers;
        private Request<?> request;
        private Bitmap responseBitmap;

        public RequestListenerWrapper(Request<?> request, BitmapContainer container) {
            this.handlers = new ArrayList();
            this.request = request;
            this.handlers.add(container);
        }

        public void addHandler(BitmapContainer container) {
            this.handlers.add(container);
        }

        public boolean removeHandlerAndCancelIfNecessary(BitmapContainer container) {
            this.handlers.remove(container);
            if (this.handlers.size() != 0) {
                return false;
            }
            this.request.cancel();
            return true;
        }
    }

    static {
        MIN_CACHE_SIZE_BYTES = 3145728;
        MIN_NUM_IMAGES_IN_CACHE = 6;
    }

    public BitmapLoader(RequestQueue queue, int screenWidthPx, int screenHeightPx, TentativeGcRunner tentativeGcRunner) {
        int cacheSizeInBytes;
        this.mInFlightRequests = Maps.newHashMap();
        this.mBatchedResponses = Maps.newHashMap();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRequestQueue = queue;
        int gServicesCacheSizeMb = ((Integer) PlayG.bitmapLoaderCacheSizeOverrideMb.get()).intValue();
        if (gServicesCacheSizeMb == -1) {
            cacheSizeInBytes = Math.max(MIN_CACHE_SIZE_BYTES, (int) (((Float) PlayG.bitmapLoaderCacheSizeRatioToScreen.get()).floatValue() * ((float) ((screenWidthPx * screenHeightPx) * 4))));
        } else {
            cacheSizeInBytes = (gServicesCacheSizeMb * 1024) * 1024;
        }
        this.mMaxImageSizeToCacheInBytes = Math.max(((Integer) PlayG.minImageSizeLimitInLRUCacheBytes.get()).intValue(), cacheSizeInBytes / MIN_NUM_IMAGES_IN_CACHE);
        this.mCachedRemoteImages = new BitmapCache(cacheSizeInBytes);
        this.mTentativeGcRunner = tentativeGcRunner;
    }

    public void removeInFlightRequests(int sequenceNumber) {
        List<String> wrappersToRemove = Lists.newArrayList();
        for (String key : this.mInFlightRequests.keySet()) {
            if (((RequestListenerWrapper) this.mInFlightRequests.get(key)).request == null || ((RequestListenerWrapper) this.mInFlightRequests.get(key)).request.getSequence() < sequenceNumber) {
                wrappersToRemove.add(key);
            }
        }
        for (String key2 : wrappersToRemove) {
            this.mInFlightRequests.remove(key2);
        }
    }

    private BitmapContainer get(String requestUrl, String modifiedUrl, String cacheKey, int requestWidth, int requestHeight, boolean useCachedPlaceholder, RemoteRequestCreator remoteRequestCreator, BitmapLoadedHandler bitmapLoadedHandler) {
        if (TextUtils.isEmpty(requestUrl)) {
            return new BitmapContainer(null, null, null, requestWidth, requestHeight, null);
        }
        BitmapCacheEntry bitmapCacheEntry = this.mCachedRemoteImages.get(cacheKey, requestWidth, requestHeight);
        if (bitmapCacheEntry != null && bitmapCacheEntry.requestedWidth == requestWidth && bitmapCacheEntry.requestedHeight == requestHeight) {
            return new BitmapContainer(bitmapCacheEntry.bitmap, requestUrl, modifiedUrl, requestWidth, requestHeight, null);
        }
        Bitmap placeholderBitmap = null;
        if (useCachedPlaceholder && bitmapCacheEntry != null) {
            placeholderBitmap = bitmapCacheEntry.bitmap;
        }
        BitmapContainer bitmapContainer = new BitmapContainer(placeholderBitmap, requestUrl, modifiedUrl, requestWidth, requestHeight, bitmapLoadedHandler);
        RequestListenerWrapper wrapper = (RequestListenerWrapper) this.mInFlightRequests.get(modifiedUrl);
        if (wrapper != null) {
            wrapper.addHandler(bitmapContainer);
            return bitmapContainer;
        }
        Request<?> newRequest = remoteRequestCreator.create();
        this.mRequestQueue.add(newRequest);
        this.mInFlightRequests.put(modifiedUrl, new RequestListenerWrapper(newRequest, bitmapContainer));
        return bitmapContainer;
    }

    public BitmapContainer get(String requestUrl, int requestWidth, int requestHeight, boolean useCachedPlaceholder, BitmapLoadedHandler bitmapLoadedHandler) {
        String modifiedUrl = requestUrl;
        if (requestWidth > 0 || requestHeight > 0) {
            modifiedUrl = FifeUrlUtils.buildFifeUrl(requestUrl, requestWidth, requestHeight);
        }
        final String cacheKey = requestUrl;
        final String finalModifiedUrl = modifiedUrl;
        final int i = requestWidth;
        final int i2 = requestHeight;
        return get(requestUrl, finalModifiedUrl, cacheKey, requestWidth, requestHeight, useCachedPlaceholder, new RemoteRequestCreator() {
            public Request<?> create() {
                BitmapLoader.this.mTentativeGcRunner.onAllocatingSoon((i * i2) * 2);
                return BitmapLoader.this.createImageRequest(finalModifiedUrl, i, i2, Config.RGB_565, new Listener<Bitmap>() {
                    public void onResponse(Bitmap response) {
                        BitmapLoader.this.onGetImageSuccess(finalModifiedUrl, cacheKey, i, i2, response);
                    }
                }, new ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        BitmapLoader.this.onGetImageError(finalModifiedUrl);
                    }
                });
            }
        }, bitmapLoadedHandler);
    }

    protected DebugImageRequest createImageRequest(String url, int requestWidth, int requestHeight, Config decodeConfig, Listener<Bitmap> bitmapListener, ErrorListener errorListener) {
        return new DebugImageRequest(url, requestWidth, requestHeight, decodeConfig, bitmapListener, errorListener);
    }

    public BitmapContainer get(String requestUrl, int requestWidth, int requestHeight, BitmapLoadedHandler bitmapLoadedHandler) {
        return get(requestUrl, requestWidth, requestHeight, true, bitmapLoadedHandler);
    }

    private void onGetImageSuccess(String modifiedUrl, String cacheKey, int requestWidth, int requestHeight, Bitmap response) {
        if (response.getHeight() * response.getRowBytes() <= this.mMaxImageSizeToCacheInBytes) {
            this.mCachedRemoteImages.put(cacheKey, requestWidth, requestHeight, response);
        } else {
            PlayCommonLog.d("%s is too large to cache", modifiedUrl);
        }
        RequestListenerWrapper wrapper = (RequestListenerWrapper) this.mInFlightRequests.remove(modifiedUrl);
        if (wrapper != null) {
            wrapper.responseBitmap = response;
            batchResponse(modifiedUrl, wrapper);
            PlayCommonLog.logTiming("Loaded bitmap %s", wrapper.request.getUrl());
        }
    }

    private void onGetImageError(String modifiedUrl) {
        RequestListenerWrapper wrapper = (RequestListenerWrapper) this.mInFlightRequests.remove(modifiedUrl);
        if (wrapper != null) {
            batchResponse(modifiedUrl, wrapper);
            String url = wrapper.request != null ? wrapper.request.getUrl() : "<null request>";
            PlayCommonLog.logTiming("Bitmap error %s", url);
        }
    }

    private void batchResponse(String modifiedUrl, RequestListenerWrapper wrapper) {
        this.mBatchedResponses.put(modifiedUrl, wrapper);
        if (this.mRunnable == null) {
            this.mRunnable = new Runnable() {
                public void run() {
                    for (RequestListenerWrapper wrapper : BitmapLoader.this.mBatchedResponses.values()) {
                        List<BitmapContainer> containers = wrapper.handlers;
                        int containerCount = containers.size();
                        for (int i = 0; i < containerCount; i++) {
                            BitmapContainer container = (BitmapContainer) containers.get(i);
                            container.mBitmap = wrapper.responseBitmap;
                            container.mBitmapLoaded.onResponse(container);
                        }
                    }
                    BitmapLoader.this.mBatchedResponses.clear();
                    BitmapLoader.this.mRunnable = null;
                }
            };
            this.mHandler.postDelayed(this.mRunnable, 100);
        }
    }
}
