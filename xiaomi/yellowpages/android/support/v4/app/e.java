package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* compiled from: BackStackRecord */
final class e implements Parcelable {
    public static final Creator CREATOR;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int mIndex;
    final String mName;
    final int[] mOps;
    final int mTransition;
    final int mTransitionStyle;

    public e(p pVar, x xVar) {
        int i = 0;
        for (v vVar = xVar.Hi; vVar != null; vVar = vVar.Go) {
            if (vVar.removed != null) {
                i += vVar.removed.size();
            }
        }
        this.mOps = new int[(i + (xVar.mNumOp * 7))];
        if (xVar.mAddToBackStack) {
            i = 0;
            for (v vVar2 = xVar.Hi; vVar2 != null; vVar2 = vVar2.Go) {
                int i2 = i + 1;
                this.mOps[i] = vVar2.cmd;
                int i3 = i2 + 1;
                this.mOps[i2] = vVar2.Gq != null ? vVar2.Gq.mIndex : -1;
                int i4 = i3 + 1;
                this.mOps[i3] = vVar2.enterAnim;
                i2 = i4 + 1;
                this.mOps[i4] = vVar2.exitAnim;
                i4 = i2 + 1;
                this.mOps[i2] = vVar2.popEnterAnim;
                i2 = i4 + 1;
                this.mOps[i4] = vVar2.popExitAnim;
                if (vVar2.removed != null) {
                    int size = vVar2.removed.size();
                    i4 = i2 + 1;
                    this.mOps[i2] = size;
                    i2 = 0;
                    while (i2 < size) {
                        i3 = i4 + 1;
                        this.mOps[i4] = ((Fragment) vVar2.removed.get(i2)).mIndex;
                        i2++;
                        i4 = i3;
                    }
                    i = i4;
                } else {
                    i = i2 + 1;
                    this.mOps[i2] = 0;
                }
            }
            this.mTransition = xVar.mTransition;
            this.mTransitionStyle = xVar.mTransitionStyle;
            this.mName = xVar.mName;
            this.mIndex = xVar.mIndex;
            this.mBreadCrumbTitleRes = xVar.mBreadCrumbTitleRes;
            this.mBreadCrumbTitleText = xVar.mBreadCrumbTitleText;
            this.mBreadCrumbShortTitleRes = xVar.mBreadCrumbShortTitleRes;
            this.mBreadCrumbShortTitleText = xVar.mBreadCrumbShortTitleText;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public e(Parcel parcel) {
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

    public x a(p pVar) {
        x xVar = new x(pVar);
        int i = 0;
        int i2 = 0;
        while (i2 < this.mOps.length) {
            v vVar = new v();
            int i3 = i2 + 1;
            vVar.cmd = this.mOps[i2];
            if (p.DEBUG) {
                Log.v("FragmentManager", "Instantiate " + xVar + " op #" + i + " base fragment #" + this.mOps[i3]);
            }
            int i4 = i3 + 1;
            i2 = this.mOps[i3];
            if (i2 >= 0) {
                vVar.Gq = (Fragment) pVar.mActive.get(i2);
            } else {
                vVar.Gq = null;
            }
            i3 = i4 + 1;
            vVar.enterAnim = this.mOps[i4];
            i4 = i3 + 1;
            vVar.exitAnim = this.mOps[i3];
            i3 = i4 + 1;
            vVar.popEnterAnim = this.mOps[i4];
            int i5 = i3 + 1;
            vVar.popExitAnim = this.mOps[i3];
            i4 = i5 + 1;
            int i6 = this.mOps[i5];
            if (i6 > 0) {
                vVar.removed = new ArrayList(i6);
                i3 = 0;
                while (i3 < i6) {
                    if (p.DEBUG) {
                        Log.v("FragmentManager", "Instantiate " + xVar + " set remove fragment #" + this.mOps[i4]);
                    }
                    i5 = i4 + 1;
                    vVar.removed.add((Fragment) pVar.mActive.get(this.mOps[i4]));
                    i3++;
                    i4 = i5;
                }
            }
            xVar.a(vVar);
            i++;
            i2 = i4;
        }
        xVar.mTransition = this.mTransition;
        xVar.mTransitionStyle = this.mTransitionStyle;
        xVar.mName = this.mName;
        xVar.mIndex = this.mIndex;
        xVar.mAddToBackStack = true;
        xVar.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        xVar.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        xVar.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        xVar.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        xVar.bumpBackStackNesting(1);
        return xVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.mOps);
        parcel.writeInt(this.mTransition);
        parcel.writeInt(this.mTransitionStyle);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel, 0);
    }

    static {
        CREATOR = new u();
    }
}
