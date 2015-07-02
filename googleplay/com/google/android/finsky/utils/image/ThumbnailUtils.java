package com.google.android.finsky.utils.image;

import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.protos.Common.Image;
import java.util.List;

public class ThumbnailUtils {
    public static Image getImageFromDocument(Document document, int width, int height, int[] imageTypes) {
        for (int imageType : imageTypes) {
            Image result = getBestImage(document.getImages(imageType), width, height);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public static Image getPromoBitmapFromDocument(Document document, int width, int height) {
        return getBestImage(document.getImages(2), width, height);
    }

    public static Image getBestImage(List<Image> images, int minWidth, int minHeight) {
        if (images == null) {
            return null;
        }
        int selectedWidth = Integer.MAX_VALUE;
        int selectedHeight = Integer.MAX_VALUE;
        Image selectedImage = null;
        int imageCount = images.size();
        for (int i = 0; i < imageCount; i++) {
            Image image = (Image) images.get(i);
            if (image.supportsFifeUrlOptions) {
                return image;
            }
            if (image.dimension != null) {
                int imgWidth = image.dimension.width;
                int imgHeight = image.dimension.height;
                if (imgWidth >= minWidth && imgHeight >= minHeight && selectedWidth >= imgWidth && selectedHeight >= imgHeight) {
                    selectedWidth = imgWidth;
                    selectedHeight = imgHeight;
                    selectedImage = image;
                }
            }
        }
        if (selectedImage != null) {
            return selectedImage;
        }
        return images.size() > 0 ? (Image) images.get(images.size() - 1) : null;
    }
}
