package com.google.android.gms.reminders;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.b;
import com.google.android.wallet.instrumentmanager.R;
import java.util.List;

public class a implements Creator<LoadRemindersOptions> {
    static void a(LoadRemindersOptions loadRemindersOptions, Parcel parcel, int i) {
        int bU = b.bU(parcel);
        b.c(parcel, 1, loadRemindersOptions.mVersionCode);
        b.b(parcel, 2, loadRemindersOptions.getServerAssignedIds(), false);
        b.c(parcel, 3, loadRemindersOptions.getClientAssignedIds(), false);
        b.a(parcel, 4, loadRemindersOptions.getTaskListIds(), false);
        b.J(parcel, bU);
    }

    public /* synthetic */ Object createFromParcel(Parcel x0) {
        return hj(x0);
    }

    public LoadRemindersOptions hj(Parcel parcel) {
        List list = null;
        int bT = com.google.android.gms.common.internal.safeparcel.a.bT(parcel);
        int i = 0;
        List list2 = null;
        List list3 = null;
        while (parcel.dataPosition() < bT) {
            int bS = com.google.android.gms.common.internal.safeparcel.a.bS(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.a.dk(bS)) {
                case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    i = com.google.android.gms.common.internal.safeparcel.a.g(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    list3 = com.google.android.gms.common.internal.safeparcel.a.D(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    list2 = com.google.android.gms.common.internal.safeparcel.a.E(parcel, bS);
                    break;
                case R.styleable.WalletImFormEditText_required /*4*/:
                    list = com.google.android.gms.common.internal.safeparcel.a.C(parcel, bS);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.a.b(parcel, bS);
                    break;
            }
        }
        if (parcel.dataPosition() == bT) {
            return new LoadRemindersOptions(i, list3, list2, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.a.a("Overread allowed size end=" + bT, parcel);
    }

    public LoadRemindersOptions[] jU(int i) {
        return new LoadRemindersOptions[i];
    }

    public /* synthetic */ Object[] newArray(int x0) {
        return jU(x0);
    }
}
