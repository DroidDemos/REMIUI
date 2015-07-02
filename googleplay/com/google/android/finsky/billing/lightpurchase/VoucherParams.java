package com.google.android.finsky.billing.lightpurchase;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class VoucherParams implements Parcelable {
    public static final Creator<VoucherParams> CREATOR;
    public final boolean autoApply;
    public final boolean hasVouchers;
    public final String selectedVoucherId;

    public VoucherParams(String selectedVoucherId, boolean autoApply, boolean hasVouchers) {
        this.selectedVoucherId = selectedVoucherId;
        this.autoApply = autoApply;
        this.hasVouchers = hasVouchers;
    }

    private VoucherParams(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.selectedVoucherId = in.readString();
        if (in.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.autoApply = z;
        if (in.readByte() != (byte) 1) {
            z2 = false;
        }
        this.hasVouchers = z2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        int i;
        int i2 = 1;
        out.writeString(this.selectedVoucherId);
        if (this.autoApply) {
            i = 1;
        } else {
            i = 0;
        }
        out.writeByte((byte) i);
        if (!this.hasVouchers) {
            i2 = 0;
        }
        out.writeByte((byte) i2);
    }

    static {
        CREATOR = new Creator<VoucherParams>() {
            public VoucherParams createFromParcel(Parcel parcel) {
                return new VoucherParams(parcel);
            }

            public VoucherParams[] newArray(int size) {
                return new VoucherParams[size];
            }
        };
    }
}
