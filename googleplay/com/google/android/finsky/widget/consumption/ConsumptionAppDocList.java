package com.google.android.finsky.widget.consumption;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.services.ConsumptionAppDoc;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ConsumptionAppDocList extends ArrayList<ConsumptionAppDoc> implements Parcelable {
    public static final Creator<ConsumptionAppDocList> CREATOR;
    public static final Comparator<ConsumptionAppDocList> NEWEST_FIRST;
    private final int mBackend;

    public ConsumptionAppDocList(int backend) {
        this.mBackend = backend;
    }

    public ConsumptionAppDocList(int backend, List<ConsumptionAppDoc> docs) {
        this(backend);
        addAll(docs);
    }

    public static ConsumptionAppDocList createFromBundles(int backend, List<Bundle> docBundles) {
        List<ConsumptionAppDoc> newData = Lists.newArrayList(docBundles.size());
        for (Bundle bundle : docBundles) {
            newData.add(new ConsumptionAppDoc(bundle));
        }
        return new ConsumptionAppDocList(backend, newData);
    }

    public boolean add(ConsumptionAppDoc doc) {
        if (doc != null) {
            return super.add(doc);
        }
        FinskyLog.wtf("Not adding a null document for backend=[%d]", Integer.valueOf(this.mBackend));
        return false;
    }

    public boolean addAll(Collection<? extends ConsumptionAppDoc> collection) {
        boolean success = true;
        for (ConsumptionAppDoc doc : collection) {
            success &= add(doc);
        }
        return success;
    }

    public int getBackend() {
        return this.mBackend;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mBackend);
        dest.writeTypedList(this);
    }

    static {
        CREATOR = new Creator<ConsumptionAppDocList>() {
            public ConsumptionAppDocList createFromParcel(Parcel source) {
                int backend = source.readInt();
                List<ConsumptionAppDoc> docs = Lists.newArrayList();
                source.readTypedList(docs, ConsumptionAppDoc.CREATOR);
                return new ConsumptionAppDocList(backend, docs);
            }

            public ConsumptionAppDocList[] newArray(int size) {
                return new ConsumptionAppDocList[size];
            }
        };
        NEWEST_FIRST = new Comparator<ConsumptionAppDocList>() {
            public int compare(ConsumptionAppDocList lhs, ConsumptionAppDocList rhs) {
                if (lhs.isEmpty() || rhs.isEmpty()) {
                    return rhs.size() - lhs.size();
                }
                return (int) (((ConsumptionAppDoc) rhs.get(0)).getLastUpdateTimeMs() - ((ConsumptionAppDoc) lhs.get(0)).getLastUpdateTimeMs());
            }
        };
    }
}
