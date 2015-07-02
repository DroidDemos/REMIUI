package com.google.android.finsky.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.Lists;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.BitmapLoader.BitmapContainer;
import com.google.android.play.image.BitmapLoader.BitmapLoadedHandler;
import java.util.Collection;
import java.util.List;

public class WidgetModel implements ErrorListener, OnDataChangedListener {
    private final BitmapLoader mBitmapLoader;
    private final int[] mDocumentTypes;
    private final int mImageHeightResource;
    private final ImageSelector mImageSelector;
    private final List<PromotionalItem> mItems;
    private DfeList mList;
    private RefreshListener mListener;
    private int mLoadedImagesSoFar;
    private int mMaxHeight;
    private final int mMaxItems;
    private int mSize;
    private boolean mUpdatePending;

    public interface ImageSelector {
        Image getImage(Document document, int i);

        int getImageType(Document document);
    }

    public interface RefreshListener {
        void onData();

        void onError(String str);
    }

    public static class PromotionalItem {
        public final String developer;
        public final Document doc;
        public final Bitmap image;
        public final int imageType;
        public final String title;

        public PromotionalItem(Bitmap image, int imageType, String title, String developer, Document doc) {
            this.image = image;
            this.imageType = imageType;
            this.title = title;
            this.developer = developer;
            this.doc = doc;
        }
    }

    public WidgetModel(BitmapLoader bitmapLoader, int[] validDocumentTypes, ImageSelector imageSelector, int imageHeightResource, int maxItems) {
        this.mLoadedImagesSoFar = 0;
        this.mMaxItems = maxItems;
        this.mItems = Lists.newArrayList(maxItems);
        this.mBitmapLoader = bitmapLoader;
        this.mDocumentTypes = validDocumentTypes;
        this.mImageSelector = imageSelector;
        this.mImageHeightResource = imageHeightResource;
    }

    public void refresh(Context context, DfeApi dfeApi, String dfeUrl, RefreshListener listener) {
        if (!this.mUpdatePending) {
            this.mUpdatePending = true;
            this.mListener = listener;
            if (this.mList != null) {
                this.mList.removeDataChangedListener(this);
                this.mList.removeErrorListener(this);
            }
            this.mMaxHeight = context.getResources().getDimensionPixelSize(this.mImageHeightResource);
            this.mList = new DfeList(dfeApi, dfeUrl, false);
            this.mList.addErrorListener(this);
            this.mList.addDataChangedListener(this);
            this.mList.startLoadItems();
        }
    }

    public void reset() {
        this.mUpdatePending = false;
        this.mItems.clear();
    }

    public Collection<PromotionalItem> getItems() {
        return this.mItems;
    }

    public void onDataChanged() {
        this.mItems.clear();
        this.mUpdatePending = false;
        this.mLoadedImagesSoFar = 0;
        int numItems = Math.min(this.mList.getCount(), this.mMaxItems);
        this.mSize = numItems;
        for (int item = 0; item < numItems; item++) {
            final Document document = (Document) this.mList.getItem(item);
            Image image = this.mImageSelector.getImage(document, this.mMaxHeight);
            final int imageType = this.mImageSelector.getImageType(document);
            if (!isValidDocument(document) || image == null || TextUtils.isEmpty(image.imageUrl)) {
                this.mSize--;
                loadViewsIfDone();
            } else {
                int requestHeight;
                if (image.supportsFifeUrlOptions) {
                    requestHeight = this.mMaxHeight;
                } else {
                    requestHeight = 0;
                }
                BitmapContainer newContainer = this.mBitmapLoader.get(image.imageUrl, 0, requestHeight, new BitmapLoadedHandler() {
                    public void onResponse(BitmapContainer result) {
                        WidgetModel.this.bitmapLoaded(document, result, imageType);
                    }
                });
                if (newContainer.getBitmap() != null) {
                    bitmapLoaded(document, newContainer, imageType);
                }
            }
        }
        loadViewsIfDone();
    }

    public void onErrorResponse(VolleyError error) {
        this.mUpdatePending = false;
        this.mListener.onError(ErrorStrings.get(FinskyApp.get(), error));
    }

    private boolean isValidDocument(Document document) {
        if (this.mDocumentTypes == null) {
            return true;
        }
        if (document.hasDocumentType()) {
            int documentType = document.getDocumentType();
            for (int type : this.mDocumentTypes) {
                if (type == documentType) {
                    return true;
                }
            }
        }
        return false;
    }

    private void bitmapLoaded(Document document, BitmapContainer result, int imageType) {
        this.mLoadedImagesSoFar++;
        if (result.getBitmap() != null) {
            this.mItems.add(new PromotionalItem(result.getBitmap(), imageType, document.getTitle(), document.getCreator(), document));
        }
        loadViewsIfDone();
    }

    private void loadViewsIfDone() {
        if (this.mLoadedImagesSoFar == this.mSize) {
            this.mListener.onData();
        }
    }
}
