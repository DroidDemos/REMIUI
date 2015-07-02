package com.google.android.play.utils;

import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.protos.DocumentV2.DocV2;

public class DocV2Utils {
    public static Image getFirstImageOfType(DocV2 doc, int imageType) {
        if (doc == null || doc.image == null) {
            return null;
        }
        Image[] images = doc.image;
        for (int i = 0; i < images.length; i++) {
            if (images[i].imageType == imageType) {
                return images[i];
            }
        }
        return null;
    }
}
