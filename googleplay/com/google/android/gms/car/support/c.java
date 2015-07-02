package com.google.android.gms.car.support;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

final class c implements Parcelable {
    public static final Creator<c> CREATOR;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int[] mOps;
    final int mTransition;
    final int mTransitionStyle;

    static {
        CREATOR = new Creator<c>() {
            public c bC(Parcel parcel) {
                return new c(parcel);
            }

            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return bC(x0);
            }

            public c[] cu(int i) {
                return new c[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return cu(x0);
            }
        };
    }

    public c(Parcel parcel) {
        this.mOps = parcel.createIntArray();
        this.mTransition = parcel.readInt();
        this.mTransitionStyle = parcel.readInt();
        this.mName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mBreadCrumbTitleRes = parcel.readInt();
        this.mBreadCrumbTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mBreadCrumbShortTitleRes = parcel.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    public b a(g gVar) {
        b bVar = new b(gVar);
        int i = 0;
        int i2 = 0;
        while (i2 < this.mOps.length) {
            a aVar = new a();
            int i3 = i2 + 1;
            aVar.cmd = this.mOps[i2];
            if (g.DEBUG) {
                Log.v("FragmentManager", "Instantiate " + bVar + " op #" + i + " base fragment #" + this.mOps[i3]);
            }
            int i4 = i3 + 1;
            i2 = this.mOps[i3];
            if (i2 >= 0) {
                aVar.Nd = (Fragment) gVar.mActive.get(i2);
            } else {
                aVar.Nd = null;
            }
            i3 = i4 + 1;
            aVar.enterAnim = this.mOps[i4];
            i4 = i3 + 1;
            aVar.exitAnim = this.mOps[i3];
            i3 = i4 + 1;
            aVar.popEnterAnim = this.mOps[i4];
            int i5 = i3 + 1;
            aVar.popExitAnim = this.mOps[i3];
            i4 = i5 + 1;
            int i6 = this.mOps[i5];
            if (i6 > 0) {
                aVar.removed = new ArrayList(i6);
                i3 = 0;
                while (i3 < i6) {
                    if (g.DEBUG) {
                        Log.v("FragmentManager", "Instantiate " + bVar + " set remove fragment #" + this.mOps[i4]);
                    }
                    i5 = i4 + 1;
                    aVar.removed.add((Fragment) gVar.mActive.get(this.mOps[i4]));
                    i3++;
                    i4 = i5;
                }
            }
            bVar.a(aVar);
            i++;
            i2 = i4;
        }
        bVar.mTransition = this.mTransition;
        bVar.mTransitionStyle = this.mTransitionStyle;
        bVar.mName = this.mName;
        bVar.mIndex = this.mIndex;
        bVar.mAddToBackStack = true;
        bVar.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        bVar.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        bVar.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        bVar.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        bVar.bumpBackStackNesting(1);
        return bVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(this.mOps);
        dest.writeInt(this.mTransition);
        dest.writeInt(this.mTransitionStyle);
        dest.writeString(this.mName);
        dest.writeInt(this.mIndex);
        dest.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, dest, 0);
        dest.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, dest, 0);
    }
}
