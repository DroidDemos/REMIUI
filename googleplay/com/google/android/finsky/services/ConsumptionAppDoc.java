package com.google.android.finsky.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.SpannableString;
import android.text.TextUtils;
import com.google.android.finsky.utils.Objects;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsumptionAppDoc implements Parcelable {
    public static final Creator<ConsumptionAppDoc> CREATOR;
    private final String mDocId;
    private final Uri mImageUri;
    private final long mLastUpdateTimeMs;
    private final String mReason;
    private final Intent mViewIntent;

    public ConsumptionAppDoc(Bundle bundle) {
        this((Uri) bundle.getParcelable("Play.ImageUri"), (CharSequence) bundle.get("Play.Reason"), bundle.getLong("Play.LastUpdateTimeMillis"), bundle.getString("Play.FinskyDocId"), (Intent) bundle.getParcelable("Play.ViewIntent"));
    }

    ConsumptionAppDoc(Uri imageUri, CharSequence reason, long lastUpdateTimeMs, String docId, Intent viewIntent) {
        this.mImageUri = imageUri;
        this.mLastUpdateTimeMs = lastUpdateTimeMs;
        this.mDocId = docId;
        this.mViewIntent = viewIntent;
        String str = (String) reason;
        if (TextUtils.isEmpty(str)) {
            SpannableString s = (SpannableString) reason;
            if (s != null) {
                str = s.toString();
            }
        }
        this.mReason = str;
    }

    public Uri getImageUri() {
        return this.mImageUri;
    }

    public String getReason() {
        return this.mReason;
    }

    public long getLastUpdateTimeMs() {
        return this.mLastUpdateTimeMs;
    }

    public String getDocId() {
        return this.mDocId;
    }

    public Intent getViewIntent() {
        return this.mViewIntent;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ConsumptionAppDoc)) {
            return false;
        }
        ConsumptionAppDoc other = (ConsumptionAppDoc) o;
        if (Objects.equal(getImageUri(), other.getImageUri()) && TextUtils.equals(getReason(), other.getReason()) && getLastUpdateTimeMs() == other.getLastUpdateTimeMs() && TextUtils.equals(getDocId(), other.getDocId())) {
            return true;
        }
        return false;
    }

    public String toString() {
        DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance();
        return String.format("Doc %s, Image %s, Reason %s, Last update %s", new Object[]{getDocId(), getImageUri(), getReason(), dateFormat.format(new Date(getLastUpdateTimeMs()))});
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(1);
        dest.writeParcelable(this.mImageUri, 0);
        dest.writeString(this.mReason);
        dest.writeLong(this.mLastUpdateTimeMs);
        dest.writeString(this.mDocId);
        dest.writeParcelable(this.mViewIntent, 0);
    }

    static {
        CREATOR = new Creator<ConsumptionAppDoc>() {
            public ConsumptionAppDoc createFromParcel(Parcel source) {
                ClassLoader classLoader = ConsumptionAppDoc.class.getClassLoader();
                long parcelVersion = source.readLong();
                if (1 == parcelVersion) {
                    return new ConsumptionAppDoc((Uri) source.readParcelable(classLoader), source.readString(), source.readLong(), source.readString(), (Intent) source.readParcelable(classLoader));
                }
                throw new IllegalArgumentException("Unable to unparcel v" + parcelVersion);
            }

            public ConsumptionAppDoc[] newArray(int size) {
                return new ConsumptionAppDoc[size];
            }
        };
    }
}
