package com.google.android.gms.auth;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.kl;
import com.google.android.gms.internal.kn;
import com.google.android.wallet.instrumentmanager.R;

public class AccountChangeEvent implements SafeParcelable {
    public static final AccountChangeEventCreator CREATOR;
    final String Fl;
    final int Gf;
    final long Gg;
    final int Gh;
    final int Gi;
    final String Gj;

    static {
        CREATOR = new AccountChangeEventCreator();
    }

    AccountChangeEvent(int version, long id, String accountName, int changeType, int eventIndex, String changeData) {
        this.Gf = version;
        this.Gg = id;
        this.Fl = (String) kn.k(accountName);
        this.Gh = changeType;
        this.Gi = eventIndex;
        this.Gj = changeData;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof AccountChangeEvent)) {
            return false;
        }
        AccountChangeEvent accountChangeEvent = (AccountChangeEvent) that;
        return this.Gf == accountChangeEvent.Gf && this.Gg == accountChangeEvent.Gg && kl.equal(this.Fl, accountChangeEvent.Fl) && this.Gh == accountChangeEvent.Gh && this.Gi == accountChangeEvent.Gi && kl.equal(this.Gj, accountChangeEvent.Gj);
    }

    public int hashCode() {
        return kl.hashCode(Integer.valueOf(this.Gf), Long.valueOf(this.Gg), this.Fl, Integer.valueOf(this.Gh), Integer.valueOf(this.Gi), this.Gj);
    }

    public String toString() {
        String str = "UNKNOWN";
        switch (this.Gh) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                str = "ADDED";
                break;
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                str = "REMOVED";
                break;
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                str = "RENAMED_FROM";
                break;
            case R.styleable.WalletImFormEditText_required /*4*/:
                str = "RENAMED_TO";
                break;
        }
        return "AccountChangeEvent {accountName = " + this.Fl + ", changeType = " + str + ", changeData = " + this.Gj + ", eventIndex = " + this.Gi + "}";
    }

    public void writeToParcel(Parcel dest, int flags) {
        AccountChangeEventCreator.a(this, dest, flags);
    }
}
