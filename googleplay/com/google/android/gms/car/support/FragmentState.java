package com.google.android.gms.car.support;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR;
    Fragment Nu;
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

    static {
        CREATOR = new Creator<FragmentState>() {
            public FragmentState bF(Parcel parcel) {
                return new FragmentState(parcel);
            }

            public /* synthetic */ Object createFromParcel(Parcel x0) {
                return bF(x0);
            }

            public FragmentState[] cx(int i) {
                return new FragmentState[i];
            }

            public /* synthetic */ Object[] newArray(int x0) {
                return cx(x0);
            }
        };
    }

    public FragmentState(Parcel in) {
        boolean z = true;
        this.mClassName = in.readString();
        this.mIndex = in.readInt();
        this.mFromLayout = in.readInt() != 0;
        this.mFragmentId = in.readInt();
        this.mContainerId = in.readInt();
        this.mTag = in.readString();
        this.mRetainInstance = in.readInt() != 0;
        if (in.readInt() == 0) {
            z = false;
        }
        this.mDetached = z;
        this.mArguments = in.readBundle();
        this.mSavedFragmentState = in.readBundle();
    }

    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(FragmentActivity activity, Fragment parent) {
        if (this.Nu != null) {
            return this.Nu;
        }
        if (this.mArguments != null) {
            this.mArguments.setClassLoader(activity.getClassLoader());
        }
        this.Nu = Fragment.instantiate(activity.getContext(), this.mClassName, this.mArguments);
        if (this.mSavedFragmentState != null) {
            this.mSavedFragmentState.setClassLoader(activity.getClassLoader());
            this.Nu.mSavedFragmentState = this.mSavedFragmentState;
        }
        this.Nu.a(this.mIndex, parent);
        this.Nu.mFromLayout = this.mFromLayout;
        this.Nu.mRestored = true;
        this.Nu.mFragmentId = this.mFragmentId;
        this.Nu.mContainerId = this.mContainerId;
        this.Nu.mTag = this.mTag;
        this.Nu.mRetainInstance = this.mRetainInstance;
        this.Nu.mDetached = this.mDetached;
        this.Nu.Ng = activity.Nl;
        if (g.DEBUG) {
            Log.v("FragmentManager", "Instantiated fragment " + this.Nu);
        }
        return this.Nu;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeString(this.mClassName);
        dest.writeInt(this.mIndex);
        dest.writeInt(this.mFromLayout ? 1 : 0);
        dest.writeInt(this.mFragmentId);
        dest.writeInt(this.mContainerId);
        dest.writeString(this.mTag);
        dest.writeInt(this.mRetainInstance ? 1 : 0);
        if (!this.mDetached) {
            i = 0;
        }
        dest.writeInt(i);
        dest.writeBundle(this.mArguments);
        dest.writeBundle(this.mSavedFragmentState);
    }
}
