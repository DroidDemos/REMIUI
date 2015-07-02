package com.google.android.gms.car.support;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import com.google.android.gms.car.CarActivity;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Fragment implements ComponentCallbacks, OnCreateContextMenuListener {
    private static final l<String, Class<?>> Ne;
    Fragment Nf;
    g Ng;
    FragmentActivity Nh;
    g Ni;
    Fragment Nj;
    boolean mAdded;
    View mAnimatingAway;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    boolean mCheckedForLoaderManager;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mInLayout;
    int mIndex;
    View mInnerView;
    boolean mLoadersStarted;
    boolean mMenuVisible;
    int mNextAnim;
    boolean mRemoving;
    boolean mRestored;
    boolean mResumed;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    SparseArray<Parcelable> mSavedViewState;
    int mState;
    int mStateAfterAnimating;
    String mTag;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    String mWho;

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String msg, Exception cause) {
            super(msg, cause);
        }
    }

    public static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        final Bundle mState;

        static {
            CREATOR = new Creator<SavedState>() {
                public SavedState bD(Parcel parcel) {
                    return new SavedState(parcel, null);
                }

                public /* synthetic */ Object createFromParcel(Parcel x0) {
                    return bD(x0);
                }

                public SavedState[] cv(int i) {
                    return new SavedState[i];
                }

                public /* synthetic */ Object[] newArray(int x0) {
                    return cv(x0);
                }
            };
        }

        SavedState(Parcel in, ClassLoader loader) {
            this.mState = in.readBundle();
            if (loader != null && this.mState != null) {
                this.mState.setClassLoader(loader);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeBundle(this.mState);
        }
    }

    static {
        Ne = new l();
    }

    public Fragment() {
        this.mState = 0;
        this.mIndex = -1;
        this.mTargetIndex = -1;
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
    }

    public static Fragment instantiate(Context context, String fname) {
        return instantiate(context, fname, null);
    }

    public static Fragment instantiate(Context context, String fname, Bundle args) {
        try {
            Class cls = (Class) Ne.get(fname);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(fname);
                Ne.put(fname, cls);
            }
            Fragment fragment = (Fragment) cls.newInstance();
            if (args != null) {
                args.setClassLoader(fragment.getClass().getClassLoader());
                fragment.mArguments = args;
            }
            return fragment;
        } catch (Exception e) {
            throw new InstantiationException("Unable to instantiate fragment " + fname + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e);
        } catch (Exception e2) {
            throw new InstantiationException("Unable to instantiate fragment " + fname + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e2);
        } catch (Exception e22) {
            throw new InstantiationException("Unable to instantiate fragment " + fname + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e22);
        }
    }

    static boolean isSupportFragmentClass(Context context, String fname) {
        try {
            Class cls = (Class) Ne.get(fname);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(fname);
                Ne.put(fname, cls);
            }
            return Fragment.class.isAssignableFrom(cls);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    final void a(int i, Fragment fragment) {
        this.mIndex = i;
        if (fragment != null) {
            this.mWho = fragment.mWho + ":" + this.mIndex;
        } else {
            this.mWho = "android:fragment:" + this.mIndex;
        }
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.print(prefix);
        writer.print("mFragmentId=#");
        writer.print(Integer.toHexString(this.mFragmentId));
        writer.print(" mContainerId=#");
        writer.print(Integer.toHexString(this.mContainerId));
        writer.print(" mTag=");
        writer.println(this.mTag);
        writer.print(prefix);
        writer.print("mState=");
        writer.print(this.mState);
        writer.print(" mIndex=");
        writer.print(this.mIndex);
        writer.print(" mWho=");
        writer.print(this.mWho);
        writer.print(" mBackStackNesting=");
        writer.println(this.mBackStackNesting);
        writer.print(prefix);
        writer.print("mAdded=");
        writer.print(this.mAdded);
        writer.print(" mRemoving=");
        writer.print(this.mRemoving);
        writer.print(" mResumed=");
        writer.print(this.mResumed);
        writer.print(" mFromLayout=");
        writer.print(this.mFromLayout);
        writer.print(" mInLayout=");
        writer.println(this.mInLayout);
        writer.print(prefix);
        writer.print("mHidden=");
        writer.print(this.mHidden);
        writer.print(" mDetached=");
        writer.print(this.mDetached);
        writer.print(" mMenuVisible=");
        writer.print(this.mMenuVisible);
        writer.print(" mHasMenu=");
        writer.println(this.mHasMenu);
        writer.print(prefix);
        writer.print("mRetainInstance=");
        writer.print(this.mRetainInstance);
        writer.print(" mRetaining=");
        writer.print(this.mRetaining);
        writer.print(" mUserVisibleHint=");
        writer.println(this.mUserVisibleHint);
        if (this.Ng != null) {
            writer.print(prefix);
            writer.print("mFragmentManager=");
            writer.println(this.Ng);
        }
        if (this.Nh != null) {
            writer.print(prefix);
            writer.print("mActivity=");
            writer.println(this.Nh);
        }
        if (this.Nj != null) {
            writer.print(prefix);
            writer.print("mParentFragment=");
            writer.println(this.Nj);
        }
        if (this.mArguments != null) {
            writer.print(prefix);
            writer.print("mArguments=");
            writer.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            writer.print(prefix);
            writer.print("mSavedFragmentState=");
            writer.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            writer.print(prefix);
            writer.print("mSavedViewState=");
            writer.println(this.mSavedViewState);
        }
        if (this.Nf != null) {
            writer.print(prefix);
            writer.print("mTarget=");
            writer.print(this.Nf);
            writer.print(" mTargetRequestCode=");
            writer.println(this.mTargetRequestCode);
        }
        if (this.mNextAnim != 0) {
            writer.print(prefix);
            writer.print("mNextAnim=");
            writer.println(this.mNextAnim);
        }
        if (this.mContainer != null) {
            writer.print(prefix);
            writer.print("mContainer=");
            writer.println(this.mContainer);
        }
        if (this.mView != null) {
            writer.print(prefix);
            writer.print("mView=");
            writer.println(this.mView);
        }
        if (this.mInnerView != null) {
            writer.print(prefix);
            writer.print("mInnerView=");
            writer.println(this.mView);
        }
        if (this.mAnimatingAway != null) {
            writer.print(prefix);
            writer.print("mAnimatingAway=");
            writer.println(this.mAnimatingAway);
            writer.print(prefix);
            writer.print("mStateAfterAnimating=");
            writer.println(this.mStateAfterAnimating);
        }
        if (this.Ni != null) {
            writer.print(prefix);
            writer.println("Child " + this.Ni + ":");
            this.Ni.dump(prefix + "  ", fd, writer, args);
        }
    }

    public final boolean equals(Object o) {
        return super.equals(o);
    }

    public final FragmentActivity getActivity() {
        return this.Nh;
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return this.Nh.getLayoutInflater();
    }

    public final Resources getResources() {
        if (this.Nh != null) {
            return this.Nh.getResources();
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public final int hashCode() {
        return super.hashCode();
    }

    void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mResumed = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.Ng = null;
        this.Nh = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
        this.mLoadersStarted = false;
        this.mCheckedForLoaderManager = false;
    }

    void instantiateChildFragmentManager() {
        this.Ni = new g();
        this.Ni.a(this.Nh, new f(this) {
            final /* synthetic */ Fragment Nk;

            {
                this.Nk = r1;
            }

            public View findViewById(int id) {
                if (this.Nk.mView != null) {
                    return this.Nk.mView.findViewById(id);
                }
                throw new IllegalStateException("Fragment does not have a view");
            }
        }, this);
    }

    final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        this.mCalled = true;
    }

    public void onAttach(CarActivity activity) {
        this.mCalled = true;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        this.mCalled = true;
    }

    public void onCreate(Bundle savedInstanceState) {
        this.mCalled = true;
    }

    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return null;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getActivity().onCreateContextMenu(menu, v, menuInfo);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public void onHiddenChanged(boolean hidden) {
    }

    public void onInflate(CarActivity activity, AttributeSet attrs, Bundle savedInstanceState) {
        this.mCalled = true;
    }

    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
        this.mCalled = true;
    }

    void performActivityCreated(Bundle savedInstanceState) {
        if (this.Ni != null) {
            this.Ni.noteStateNotSaved();
        }
        this.mCalled = false;
        onActivityCreated(savedInstanceState);
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
        } else if (this.Ni != null) {
            this.Ni.dispatchActivityCreated();
        }
    }

    void performCreate(Bundle savedInstanceState) {
        if (this.Ni != null) {
            this.Ni.noteStateNotSaved();
        }
        this.mCalled = false;
        onCreate(savedInstanceState);
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
        } else if (savedInstanceState != null) {
            Parcelable parcelable = savedInstanceState.getParcelable("android:support:fragments");
            if (parcelable != null) {
                if (this.Ni == null) {
                    instantiateChildFragmentManager();
                }
                this.Ni.restoreAllState(parcelable, null);
                this.Ni.dispatchCreate();
            }
        }
    }

    View performCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.Ni != null) {
            this.Ni.noteStateNotSaved();
        }
        Log.d("Fragment", "Fragment createView inflater dpi: " + inflater.getContext().getResources().getConfiguration().densityDpi);
        return onCreateView(inflater, container, savedInstanceState);
    }

    void performDestroy() {
        if (this.Ni != null) {
            this.Ni.dispatchDestroy();
        }
        this.mCalled = false;
        onDestroy();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
        }
    }

    void performDestroyView() {
        if (this.Ni != null) {
            this.Ni.dispatchDestroyView();
        }
        this.mCalled = false;
        onDestroyView();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    void performPause() {
        if (this.Ni != null) {
            this.Ni.dispatchPause();
        }
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    void performReallyStop() {
        if (this.Ni != null) {
            this.Ni.dispatchReallyStop();
        }
    }

    void performResume() {
        if (this.Ni != null) {
            this.Ni.noteStateNotSaved();
            this.Ni.execPendingActions();
        }
        this.mCalled = false;
        onResume();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        } else if (this.Ni != null) {
            this.Ni.dispatchResume();
            this.Ni.execPendingActions();
        }
    }

    void performStart() {
        if (this.Ni != null) {
            this.Ni.noteStateNotSaved();
            this.Ni.execPendingActions();
        }
        this.mCalled = false;
        onStart();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
        } else if (this.Ni != null) {
            this.Ni.dispatchStart();
        }
    }

    void performStop() {
        if (this.Ni != null) {
            this.Ni.dispatchStop();
        }
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
        }
    }

    final void restoreViewState(Bundle savedInstanceState) {
        if (this.mSavedViewState != null) {
            this.mInnerView.restoreHierarchyState(this.mSavedViewState);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        onViewStateRestored(savedInstanceState);
        if (!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        e.buildShortClassTag(this, stringBuilder);
        if (this.mIndex >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.mIndex);
        }
        if (this.mFragmentId != 0) {
            stringBuilder.append(" id=0x");
            stringBuilder.append(Integer.toHexString(this.mFragmentId));
        }
        if (this.mTag != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.mTag);
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
