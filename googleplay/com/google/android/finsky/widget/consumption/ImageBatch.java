package com.google.android.finsky.widget.consumption;

import android.net.Uri;
import com.google.android.finsky.widget.consumption.BatchedImageLoader.BatchedImageCallback;
import java.util.List;

public class ImageBatch {
    int backendId;
    BatchedImageCallback callback;
    List<ImageSpec> urisToLoad;

    public static class ImageSpec {
        public final int height;
        public final Uri uri;
        public final int width;

        public ImageSpec(Uri uri, int widthDips, int heightDips) {
            this.uri = uri;
            this.width = widthDips;
            this.height = heightDips;
        }

        public boolean satisfies(Uri uri, int width, int height) {
            return this.uri.equals(uri) && this.width >= width && this.height >= height;
        }

        public String toString() {
            return String.format("uri=[%s], [%s x %s]", new Object[]{this.uri, Integer.valueOf(this.width), Integer.valueOf(this.height)});
        }

        public static ImageSpec merge(ImageSpec wrapper1, ImageSpec wrapper2) {
            if (wrapper1.uri.equals(wrapper2.uri)) {
                return new ImageSpec(wrapper1.uri, Math.max(wrapper1.width, wrapper2.width), Math.max(wrapper1.height, wrapper2.height));
            }
            throw new IllegalStateException("tried to merge wrappers with different uris!");
        }
    }

    public ImageBatch(int backendId, List<ImageSpec> urisToLoad, BatchedImageCallback callback) {
        this.backendId = backendId;
        this.urisToLoad = urisToLoad;
        this.callback = callback;
    }

    public boolean equals(Object o) {
        return (o instanceof ImageBatch) && ((ImageBatch) o).backendId == this.backendId;
    }
}
