package com.google.android.finsky.billing.giftcard;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.protos.DocAnnotations.Link;
import com.google.android.finsky.utils.ParcelableProto;

public class RedeemCodeResult implements Parcelable {
    public static final Creator<RedeemCodeResult> CREATOR;
    private final Bundle mExtraPurchaseData;
    private final boolean mIsInstallAppDeferred;
    private final Link mLink;
    private final String mRedeemedOfferHtml;
    private final String mStoredValueInstrumentId;

    public RedeemCodeResult(String redeemedOfferHtml, String storedValueInstrumentId, boolean isInstallAppDeferred, Bundle extraPurchaseData, Link link) {
        this.mRedeemedOfferHtml = redeemedOfferHtml;
        this.mStoredValueInstrumentId = storedValueInstrumentId;
        this.mIsInstallAppDeferred = isInstallAppDeferred;
        this.mExtraPurchaseData = extraPurchaseData;
        this.mLink = link;
    }

    public RedeemCodeResult(Parcel in) {
        boolean z = true;
        this.mRedeemedOfferHtml = in.readString();
        this.mStoredValueInstrumentId = in.readString();
        if (in.readByte() != (byte) 1) {
            z = false;
        }
        this.mIsInstallAppDeferred = z;
        this.mExtraPurchaseData = in.readBundle();
        this.mLink = (Link) ParcelableProto.getProtoFromParcel(in);
    }

    public void writeToParcel(Parcel out, int flags) {
        int i;
        out.writeString(this.mRedeemedOfferHtml);
        out.writeString(this.mStoredValueInstrumentId);
        if (this.mIsInstallAppDeferred) {
            i = 1;
        } else {
            i = 0;
        }
        out.writeByte((byte) i);
        out.writeBundle(this.mExtraPurchaseData);
        out.writeParcelable(ParcelableProto.forProto(this.mLink), 0);
    }

    public int describeContents() {
        return 0;
    }

    public String getRedeemedOfferHtml() {
        return this.mRedeemedOfferHtml;
    }

    public String getStoredValueInstrumentId() {
        return this.mStoredValueInstrumentId;
    }

    public boolean isInstallAppDeferred() {
        return this.mIsInstallAppDeferred;
    }

    public Bundle getExtraPurchaseData() {
        return this.mExtraPurchaseData;
    }

    public Link getLink() {
        return this.mLink;
    }

    static {
        CREATOR = new Creator<RedeemCodeResult>() {
            public RedeemCodeResult createFromParcel(Parcel parcel) {
                return new RedeemCodeResult(parcel);
            }

            public RedeemCodeResult[] newArray(int size) {
                return new RedeemCodeResult[size];
            }
        };
    }
}
