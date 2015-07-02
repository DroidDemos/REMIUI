package com.google.android.finsky.widget.recommendation;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.config.G;
import com.google.android.finsky.protos.Common.Image;
import com.google.android.finsky.utils.ParcelUtils.CacheVersionException;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import java.util.List;

public class Recommendation implements Parcelable {
    private static final int[] APP_IMAGE_TYPES;
    public static final Creator<Recommendation> CREATOR;
    private static final int[] NONAPP_IMAGE_TYPES;
    private final Document mDocument;
    private final long mExpirationTimeMs;
    private final Image mImage;

    static {
        NONAPP_IMAGE_TYPES = new int[]{4, 2, 0};
        APP_IMAGE_TYPES = PlayCardImageTypeSequence.PROMO_IMAGE_TYPES;
        CREATOR = new Creator<Recommendation>() {
            public Recommendation createFromParcel(Parcel source) {
                ClassLoader classLoader = Recommendation.class.getClassLoader();
                long cachedVersion = source.readLong();
                if (2 == cachedVersion) {
                    return new Recommendation((Document) source.readParcelable(classLoader), source.readLong());
                }
                throw new CacheVersionException(Recommendation.class, 2, cachedVersion);
            }

            public Recommendation[] newArray(int size) {
                return new Recommendation[size];
            }
        };
    }

    public Recommendation(Document document) {
        this(document, ((Long) G.recommendationTtlMs.get()).longValue() + System.currentTimeMillis());
    }

    Recommendation(Document document, long expirationTimeMs) {
        this.mDocument = document;
        this.mImage = fetchImage();
        this.mExpirationTimeMs = expirationTimeMs;
    }

    long getExpirationTimeMs() {
        return this.mExpirationTimeMs;
    }

    public boolean isExpired() {
        return this.mExpirationTimeMs < System.currentTimeMillis();
    }

    public int getBackend() {
        return this.mDocument.getBackend();
    }

    public Document getDocument() {
        return this.mDocument;
    }

    public Image getImage() {
        return this.mImage;
    }

    public int getImageType() {
        return this.mImage != null ? this.mImage.imageType : 0;
    }

    private Image fetchImage() {
        for (int imageType : this.mDocument.getBackend() == 3 ? APP_IMAGE_TYPES : NONAPP_IMAGE_TYPES) {
            List<Image> images = this.mDocument.getImages(imageType);
            if (images != null && !images.isEmpty()) {
                return (Image) images.get(0);
            }
        }
        return null;
    }

    public int hashCode() {
        return (this.mDocument.hashCode() * 31) + this.mImage.imageType;
    }

    public boolean equals(Object o) {
        if (o instanceof Recommendation) {
            return ((Recommendation) o).mDocument.getDocId().equals(this.mDocument.getDocId());
        }
        return false;
    }

    public String toString() {
        return this.mDocument.hasNeutralDismissal() ? this.mDocument.getNeutralDismissal().url : null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(2);
        dest.writeParcelable(this.mDocument, 0);
        dest.writeLong(this.mExpirationTimeMs);
    }
}
