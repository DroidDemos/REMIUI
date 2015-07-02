package com.google.android.finsky.widget.recommendation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ParcelUtils;
import com.google.android.finsky.utils.ParcelUtils.CacheVersionException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class RecommendationList implements Parcelable, Collection<Recommendation> {
    public static final Creator<RecommendationList> CREATOR;
    private final int mBackendId;
    private final List<Recommendation> mRecommendations;
    private String mTitle;

    public RecommendationList(String title, int backendId) {
        this.mRecommendations = Lists.newArrayList();
        this.mTitle = title;
        this.mBackendId = backendId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getBackendId() {
        return this.mBackendId;
    }

    public void writeToDisk(Context context) {
        try {
            ParcelUtils.writeToDisk(RecommendationsStore.getCacheFile(context, this.mBackendId), this);
        } catch (IOException e) {
            FinskyLog.e(e, "Unable to cache recs for %d", Integer.valueOf(this.mBackendId));
        }
    }

    public Recommendation get(int position) {
        return (Recommendation) this.mRecommendations.get(position);
    }

    public boolean add(Recommendation object) {
        return this.mRecommendations.add(object);
    }

    public boolean addAll(Collection<? extends Recommendation> items) {
        for (Recommendation rec : items) {
            if (!contains(rec) && !add(rec)) {
                return false;
            }
        }
        return true;
    }

    public int merge(RecommendationList items) {
        int startSize = size();
        this.mTitle = items.getTitle();
        addAll(items);
        return size() - startSize;
    }

    public void clear() {
        this.mRecommendations.clear();
    }

    public boolean contains(Object object) {
        return this.mRecommendations.contains(object);
    }

    public boolean containsAll(Collection<?> items) {
        return this.mRecommendations.containsAll(items);
    }

    public boolean isEmpty() {
        return this.mRecommendations.isEmpty();
    }

    public Iterator<Recommendation> iterator() {
        return this.mRecommendations.iterator();
    }

    public boolean remove(String documentId) {
        for (Object recommendation : this.mRecommendations) {
            if (recommendation.getDocument().getDocId().equals(documentId)) {
                return remove(recommendation);
            }
        }
        return false;
    }

    public int removeExpiredRecommendations() {
        List<Recommendation> expired = Lists.newArrayList();
        for (Recommendation recommendation : this.mRecommendations) {
            if (recommendation.isExpired()) {
                expired.add(recommendation);
            }
        }
        this.mRecommendations.removeAll(expired);
        return expired.size();
    }

    public boolean needsBackfill() {
        return size() < ((Integer) G.minimumNumberOfRecs.get()).intValue();
    }

    public boolean remove(Object recommendation) {
        return this.mRecommendations.remove(recommendation);
    }

    public boolean removeAll(Collection<?> items) {
        return this.mRecommendations.removeAll(items);
    }

    public boolean retainAll(Collection<?> items) {
        return this.mRecommendations.retainAll(items);
    }

    public int size() {
        return this.mRecommendations.size();
    }

    public Object[] toArray() {
        return toArray(new Recommendation[size()]);
    }

    public <T> T[] toArray(T[] array) {
        return this.mRecommendations.toArray(array);
    }

    public String toString() {
        return "[" + this.mBackendId + ", " + this.mRecommendations + "]";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(1);
        dest.writeString(this.mTitle);
        dest.writeInt(this.mBackendId);
        dest.writeTypedList(this.mRecommendations);
    }

    static {
        CREATOR = new Creator<RecommendationList>() {
            public RecommendationList createFromParcel(Parcel source) {
                long cachedVersion = source.readLong();
                if (1 != cachedVersion) {
                    throw new CacheVersionException(RecommendationList.class, 1, cachedVersion);
                }
                String title = source.readString();
                int backendId = source.readInt();
                List<Recommendation> recs = Lists.newArrayList();
                source.readTypedList(recs, Recommendation.CREATOR);
                RecommendationList recList = new RecommendationList(title, backendId);
                for (Recommendation rec : recs) {
                    recList.add(rec);
                }
                return recList;
            }

            public RecommendationList[] newArray(int size) {
                return new RecommendationList[size];
            }
        };
    }
}
