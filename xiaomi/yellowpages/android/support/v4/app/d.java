package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

/* compiled from: Fragment */
final class d implements Parcelable {
    public static final Creator CREATOR;
    Fragment hK;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final int mIndex;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;

    public d(Fragment fragment) {
        this.mClassName = fragment.getClass().getName();
        this.mIndex = fragment.mIndex;
        this.mFromLayout = fragment.mFromLayout;
        this.mFragmentId = fragment.mFragmentId;
        this.mContainerId = fragment.mContainerId;
        this.mTag = fragment.mTag;
        this.mRetainInstance = fragment.mRetainInstance;
        this.mDetached = fragment.mDetached;
        this.mArguments = fragment.mArguments;
    }

    public d(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.mClassName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mFromLayout = parcel.readInt() != 0;
        this.mFragmentId = parcel.readInt();
        this.mContainerId = parcel.readInt();
        this.mTag = parcel.readString();
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mRetainInstance = z;
        if (parcel.readInt() == 0) {
            z2 = false;
        }
        this.mDetached = z2;
        this.mArguments = parcel.readBundle();
        this.mSavedFragmentState = parcel.readBundle();
    }

    public Fragment a(FragmentActivity fragmentActivity, Fragment fragment) {
        if (this.hK != null) {
            return this.hK;
        }
        if (this.mArguments != null) {
            this.mArguments.setClassLoader(fragmentActivity.getClassLoader());
        }
        this.hK = Fragment.a((Context) fragmentActivity, this.mClassName, this.mArguments);
        if (this.mSavedFragmentState != null) {
            this.mSavedFragmentState.setClassLoader(fragmentActivity.getClassLoader());
            this.hK.mSavedFragmentState = this.mSavedFragmentState;
        }
        this.hK.a(this.mIndex, fragment);
        this.hK.mFromLayout = this.mFromLayout;
        this.hK.mRestored = true;
        this.hK.mFragmentId = this.mFragmentId;
        this.hK.mContainerId = this.mContainerId;
        this.hK.mTag = this.mTag;
        this.hK.mRetainInstance = this.mRetainInstance;
        this.hK.mDetached = this.mDetached;
        this.hK.ty = fragmentActivity.oa;
        if (p.DEBUG) {
            Log.v("FragmentManager", "Instantiated fragment " + this.hK);
        }
        return this.hK;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.mClassName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mFromLayout ? 1 : 0);
        parcel.writeInt(this.mFragmentId);
        parcel.writeInt(this.mContainerId);
        parcel.writeString(this.mTag);
        if (this.mRetainInstance) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.mDetached) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeBundle(this.mArguments);
        parcel.writeBundle(this.mSavedFragmentState);
    }

    static {
        CREATOR = new g();
    }
}
