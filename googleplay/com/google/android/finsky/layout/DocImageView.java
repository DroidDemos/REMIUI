package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.image.ThumbnailUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.image.FifeImageView;
import java.util.Arrays;

public class DocImageView extends FifeImageView {
    private Document mDoc;
    private int[] mImageTypes;

    public DocImageView(Context context) {
        this(context, null);
    }

    public DocImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(Document doc, BitmapLoader bitmapLoader, int... imageTypes) {
        boolean isSameData;
        if (this.mDoc != null && this.mDoc == doc && Arrays.equals(this.mImageTypes, imageTypes)) {
            isSameData = true;
        } else {
            isSameData = false;
        }
        if (!isSameData) {
            this.mDoc = doc;
            this.mImageTypes = imageTypes;
            int width = getWidth();
            int height = getHeight();
            Image image = height > 0 ? ThumbnailUtils.getImageFromDocument(this.mDoc, 0, height, this.mImageTypes) : ThumbnailUtils.getImageFromDocument(this.mDoc, width, 0, this.mImageTypes);
            if (image != null) {
                setImage(image.imageUrl, image.supportsFifeUrlOptions, bitmapLoader);
            } else {
                clearImage();
            }
        }
    }

    public void clearImage() {
        super.clearImage();
        this.mDoc = null;
        this.mImageTypes = null;
    }
}
