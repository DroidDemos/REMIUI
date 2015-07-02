package com.google.android.wallet.instrumentmanager.api;

import android.content.Context;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.google.android.volley.GoogleHttpClientStack;
import com.google.android.wallet.instrumentmanager.api.http.GoogleHurlStack;
import com.google.android.wallet.instrumentmanager.config.G;
import com.google.android.wallet.instrumentmanager.config.G.images;
import java.io.File;

public final class InstrumentManagerRequestQueue {
    private static final int MAX_IMAGE_CACHE_SIZE_BYTES;
    private static RequestQueue sImageInstance;
    private static RequestQueue sInstance;

    static {
        MAX_IMAGE_CACHE_SIZE_BYTES = ((Integer) images.diskCacheSizeBytes.get()).intValue();
    }

    public static synchronized RequestQueue getApiRequestQueue(Context appContext) {
        RequestQueue requestQueue;
        synchronized (InstrumentManagerRequestQueue.class) {
            if (sInstance == null) {
                sInstance = new RequestQueue(new DiskBasedCache(new File(appContext.getCacheDir(), "wallet_im_volley_api_cache"), 1048576), createNetwork(appContext), 2);
                sInstance.start();
            }
            requestQueue = sInstance;
        }
        return requestQueue;
    }

    public static synchronized RequestQueue getImageRequestQueue(Context appContext) {
        RequestQueue requestQueue;
        synchronized (InstrumentManagerRequestQueue.class) {
            if (sImageInstance == null) {
                sImageInstance = new RequestQueue(new DiskBasedCache(new File(appContext.getCacheDir(), "wallet_im_volley_image_cache"), MAX_IMAGE_CACHE_SIZE_BYTES), createNetwork(appContext), 6);
                sImageInstance.start();
            }
            requestQueue = sImageInstance;
        }
        return requestQueue;
    }

    private static Network createNetwork(Context appContext) {
        HttpStack httpStack;
        if (((Boolean) G.allowGzippedResponses.get()).booleanValue()) {
            httpStack = new GoogleHurlStack(appContext);
        } else {
            httpStack = new GoogleHttpClientStack(appContext, ((Boolean) G.allowPiiLogging.get()).booleanValue());
        }
        return new BasicNetwork(httpStack);
    }
}
