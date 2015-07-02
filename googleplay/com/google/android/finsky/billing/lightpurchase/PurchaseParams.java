package com.google.android.finsky.billing.lightpurchase;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.IabParameters;
import com.google.android.finsky.protos.Common.Docid;
import com.google.android.finsky.utils.ParcelableProto;

public class PurchaseParams implements Parcelable {
    public static final Creator<PurchaseParams> CREATOR;
    public final String appContinueUrl;
    public final String appTitle;
    public final int appVersionCode;
    public final Docid docid;
    public final String docidStr;
    public final IabParameters iabParameters;
    public final int offerType;
    public final String voucherId;

    public static class Builder {
        private String appContinueUrl;
        private String appTitle;
        private int appVersionCode;
        private Docid docid;
        private String docidStr;
        private IabParameters iabParameters;
        private int offerType;
        private String voucherId;

        public Builder setDocument(Document doc) {
            setDocid(doc.getFullDocid());
            setDocidStr(doc.getDocId());
            return this;
        }

        public Builder setDocid(Docid docid) {
            this.docid = docid;
            return this;
        }

        public Builder setDocidStr(String docidStr) {
            this.docidStr = docidStr;
            return this;
        }

        public Builder setOfferType(int offerType) {
            this.offerType = offerType;
            return this;
        }

        public Builder setAppData(int appVersionCode, String appTitle, String appContinueUrl) {
            this.appVersionCode = appVersionCode;
            this.appTitle = appTitle;
            this.appContinueUrl = appContinueUrl;
            return this;
        }

        public Builder setVoucherId(String voucherId) {
            this.voucherId = voucherId;
            return this;
        }

        public Builder setIabParameters(IabParameters iabParameters) {
            this.iabParameters = iabParameters;
            return this;
        }

        public PurchaseParams build() {
            return new PurchaseParams();
        }
    }

    private PurchaseParams(Builder builder) {
        this.docid = builder.docid;
        if (this.docid == null) {
            throw new IllegalArgumentException("docid cannot be null");
        }
        this.docidStr = builder.docidStr;
        if (this.docidStr == null) {
            throw new IllegalArgumentException("docidStr cannot be null");
        }
        this.offerType = builder.offerType;
        this.appVersionCode = builder.appVersionCode;
        this.appTitle = builder.appTitle;
        this.appContinueUrl = builder.appContinueUrl;
        this.voucherId = builder.voucherId;
        this.iabParameters = builder.iabParameters;
    }

    private PurchaseParams(Parcel in) {
        this.docid = (Docid) ParcelableProto.getProtoFromParcel(in);
        this.docidStr = in.readString();
        this.offerType = in.readInt();
        this.appVersionCode = in.readInt();
        if (in.readByte() != (byte) 0) {
            this.appTitle = in.readString();
        } else {
            this.appTitle = null;
        }
        if (in.readByte() != (byte) 0) {
            this.appContinueUrl = in.readString();
        } else {
            this.appContinueUrl = null;
        }
        if (in.readByte() != (byte) 0) {
            this.voucherId = in.readString();
        } else {
            this.voucherId = null;
        }
        if (in.readByte() != (byte) 0) {
            this.iabParameters = (IabParameters) in.readParcelable(IabParameters.class.getClassLoader());
        } else {
            this.iabParameters = null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        int i;
        int i2 = 0;
        out.writeParcelable(ParcelableProto.forProto(this.docid), flags);
        out.writeString(this.docidStr);
        out.writeInt(this.offerType);
        out.writeInt(this.appVersionCode);
        out.writeByte((byte) (this.appTitle == null ? 0 : 1));
        if (this.appTitle != null) {
            out.writeString(this.appTitle);
        }
        if (this.appContinueUrl == null) {
            i = 0;
        } else {
            i = 1;
        }
        out.writeByte((byte) i);
        if (this.appContinueUrl != null) {
            out.writeString(this.appContinueUrl);
        }
        if (this.voucherId == null) {
            i = 0;
        } else {
            i = 1;
        }
        out.writeByte((byte) i);
        if (this.voucherId != null) {
            out.writeString(this.voucherId);
        }
        if (this.iabParameters != null) {
            i2 = 1;
        }
        out.writeByte((byte) i2);
        if (this.iabParameters != null) {
            out.writeParcelable(this.iabParameters, flags);
        }
    }

    static {
        CREATOR = new Creator<PurchaseParams>() {
            public PurchaseParams createFromParcel(Parcel parcel) {
                return new PurchaseParams(parcel);
            }

            public PurchaseParams[] newArray(int size) {
                return new PurchaseParams[size];
            }
        };
    }

    public static Builder builder() {
        return new Builder();
    }
}
