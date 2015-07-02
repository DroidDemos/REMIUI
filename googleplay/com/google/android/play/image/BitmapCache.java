package com.google.android.play.image;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import java.util.ArrayList;
import java.util.List;

public class BitmapCache {
    private BitmapListLruCache mLruCache;

    public static class BitmapCacheEntry {
        public Bitmap bitmap;
        public int requestedHeight;
        public int requestedWidth;

        public BitmapCacheEntry(Bitmap bitmap, int requestedWidth, int requestedHeight) {
            this.bitmap = bitmap;
            this.requestedWidth = requestedWidth;
            this.requestedHeight = requestedHeight;
        }
    }

    private class BitmapListLruCache extends LruCache<String, ArrayList<BitmapCacheEntry>> {
        public BitmapListLruCache(int maxSizeInBytes) {
            super(maxSizeInBytes);
        }

        protected int sizeOf(String key, ArrayList<BitmapCacheEntry> bitmapArrayList) {
            int totalSize = 0;
            int arraySize = bitmapArrayList.size();
            for (int i = 0; i < arraySize; i++) {
                Bitmap bitmap = ((BitmapCacheEntry) bitmapArrayList.get(i)).bitmap;
                totalSize += bitmap.getRowBytes() * bitmap.getHeight();
            }
            return totalSize;
        }
    }

    public BitmapCache(int maxSizeInBytes) {
        this.mLruCache = new BitmapListLruCache(maxSizeInBytes);
    }

    public void put(String cacheKey, int requestedWidth, int requestedHeight, Bitmap bitmap) {
        ArrayList<BitmapCacheEntry> bitmapList = (ArrayList) this.mLruCache.get(cacheKey);
        if (bitmapList == null) {
            bitmapList = new ArrayList();
        }
        int insertIndex = 0;
        for (int i = 0; i < bitmapList.size(); i++) {
            int currentWidth = ((BitmapCacheEntry) bitmapList.get(i)).bitmap.getWidth();
            int newWidth = bitmap.getWidth();
            if (currentWidth >= newWidth) {
                if (currentWidth == newWidth) {
                    bitmapList.remove(i);
                    insertIndex = i;
                } else {
                    insertIndex = i + 1;
                }
                bitmapList.add(insertIndex, new BitmapCacheEntry(bitmap, requestedWidth, requestedHeight));
                this.mLruCache.put(cacheKey, bitmapList);
            }
        }
        bitmapList.add(insertIndex, new BitmapCacheEntry(bitmap, requestedWidth, requestedHeight));
        this.mLruCache.put(cacheKey, bitmapList);
    }

    public BitmapCacheEntry get(String cacheKey, int requestedWidth, int requestedHeight) {
        boolean checkHeight = true;
        List<BitmapCacheEntry> bitmapList = (List) this.mLruCache.get(cacheKey);
        if (bitmapList == null) {
            return null;
        }
        boolean checkWidth;
        if (requestedWidth != 0) {
            checkWidth = true;
        } else {
            checkWidth = false;
        }
        if (requestedWidth == 0) {
            checkHeight = false;
        }
        BitmapCacheEntry smallestLargerBitmap = null;
        for (int i = 0; i < bitmapList.size(); i++) {
            BitmapCacheEntry entry = (BitmapCacheEntry) bitmapList.get(i);
            if (entry.requestedWidth == requestedWidth && entry.requestedHeight == requestedHeight) {
                return entry;
            }
            if (smallestLargerBitmap == null && ((!checkWidth || entry.bitmap.getWidth() >= requestedWidth) && (!checkHeight || entry.bitmap.getHeight() >= requestedHeight))) {
                smallestLargerBitmap = entry;
            }
        }
        if (smallestLargerBitmap == null) {
            smallestLargerBitmap = (BitmapCacheEntry) bitmapList.get(bitmapList.size() - 1);
        }
        return smallestLargerBitmap;
    }
}
