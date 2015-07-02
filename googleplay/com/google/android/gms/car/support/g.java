package com.google.android.gms.car.support;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import com.google.android.gms.car.support.FragmentManager.OnBackStackChangedListener;
import com.google.android.wallet.instrumentmanager.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

final class g extends FragmentManager {
    static final Interpolator ACCELERATE_CUBIC;
    static final Interpolator ACCELERATE_QUINT;
    static boolean DEBUG;
    static final Interpolator DECELERATE_CUBIC;
    static final Interpolator DECELERATE_QUINT;
    static final boolean HONEYCOMB;
    FragmentActivity Nh;
    f Nm;
    Fragment Np;
    ArrayList<Fragment> mActive;
    ArrayList<Fragment> mAdded;
    ArrayList<Integer> mAvailBackStackIndices;
    ArrayList<Integer> mAvailIndices;
    ArrayList<b> mBackStack;
    ArrayList<OnBackStackChangedListener> mBackStackChangeListeners;
    ArrayList<b> mBackStackIndices;
    ArrayList<Fragment> mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    ArrayList<Runnable> mPendingActions;
    SparseArray<Parcelable> mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    Runnable[] mTmpActions;

    static {
        boolean z = false;
        DEBUG = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        HONEYCOMB = z;
        DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
        DECELERATE_CUBIC = new DecelerateInterpolator(1.5f);
        ACCELERATE_QUINT = new AccelerateInterpolator(2.5f);
        ACCELERATE_CUBIC = new AccelerateInterpolator(1.5f);
    }

    g() {
        this.mCurState = 0;
        this.mStateBundle = null;
        this.mStateArray = null;
        this.mExecCommit = new Runnable(this) {
            final /* synthetic */ g Nq;

            {
                this.Nq = r1;
            }

            public void run() {
                this.Nq.execPendingActions();
            }
        };
    }

    static Animation a(float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(DECELERATE_CUBIC);
        alphaAnimation.setDuration(220);
        return alphaAnimation;
    }

    static Animation a(float f, float f2, float f3, float f4) {
        Animation animationSet = new AnimationSet(false);
        Animation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(DECELERATE_QUINT);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(f3, f4);
        scaleAnimation.setInterpolator(DECELERATE_CUBIC);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    private void a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new i("FragmentManager"));
        if (this.Nh != null) {
            try {
                this.Nh.dump("  ", null, printWriter, new String[0]);
            } catch (Throwable e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                dump("  ", null, printWriter, new String[0]);
            } catch (Throwable e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        }
        throw runtimeException;
    }

    public static int transitToStyleIndex(int transit, boolean enter) {
        switch (transit) {
            case 4097:
                return enter ? 1 : 2;
            case 4099:
                return enter ? 5 : 6;
            case 8194:
                return enter ? 3 : 4;
            default:
                return -1;
        }
    }

    Animation a(Fragment fragment, int i, boolean z, int i2) {
        Animation onCreateAnimation = fragment.onCreateAnimation(i, z, fragment.mNextAnim);
        if (onCreateAnimation != null) {
            return onCreateAnimation;
        }
        if (fragment.mNextAnim != 0) {
            onCreateAnimation = AnimationUtils.loadAnimation(this.Nh.getContext(), fragment.mNextAnim);
            if (onCreateAnimation != null) {
                return onCreateAnimation;
            }
        }
        if (i == 0) {
            return null;
        }
        int transitToStyleIndex = transitToStyleIndex(i, z);
        if (transitToStyleIndex < 0) {
            return null;
        }
        switch (transitToStyleIndex) {
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return a(1.125f, 1.0f, 0.0f, 1.0f);
            case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return a(1.0f, 0.975f, 1.0f, 0.0f);
            case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return a(0.975f, 1.0f, 0.0f, 1.0f);
            case R.styleable.WalletImFormEditText_required /*4*/:
                return a(1.0f, 1.075f, 1.0f, 0.0f);
            case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                return a(0.0f, 1.0f);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return a(1.0f, 0.0f);
            default:
                if (i2 == 0 && this.Nh.getWindow() != null) {
                    i2 = this.Nh.getWindow().getAttributes().windowAnimations;
                }
                return i2 == 0 ? null : null;
        }
    }

    public void a(int i, b bVar) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }
            int size = this.mBackStackIndices.size();
            if (i < size) {
                if (DEBUG) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + bVar);
                }
                this.mBackStackIndices.set(i, bVar);
            } else {
                while (size < i) {
                    this.mBackStackIndices.add(null);
                    if (this.mAvailBackStackIndices == null) {
                        this.mAvailBackStackIndices = new ArrayList();
                    }
                    if (DEBUG) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.mAvailBackStackIndices.add(Integer.valueOf(size));
                    size++;
                }
                if (DEBUG) {
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + bVar);
                }
                this.mBackStackIndices.add(bVar);
            }
        }
    }

    public void a(Fragment fragment) {
        if (!fragment.mDeferStart) {
            return;
        }
        if (this.mExecutingActions) {
            this.mHavePendingDeferredStart = true;
            return;
        }
        fragment.mDeferStart = false;
        a(fragment, this.mCurState, 0, 0, false);
    }

    public void a(Fragment fragment, int i, int i2) {
        if (DEBUG) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            if (this.mAdded != null) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
            a(fragment, z ? 0 : 1, i, i2, false);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a(final com.google.android.gms.car.support.Fragment r10, int r11, int r12, int r13, boolean r14) {
        /*
        r9 = this;
        r8 = 4;
        r6 = 3;
        r3 = 0;
        r5 = 1;
        r7 = 0;
        r0 = r10.mAdded;
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = r10.mDetached;
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        if (r11 <= r5) goto L_0x0010;
    L_0x000f:
        r11 = r5;
    L_0x0010:
        r0 = r10.mRemoving;
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r10.mState;
        if (r11 <= r0) goto L_0x001a;
    L_0x0018:
        r11 = r10.mState;
    L_0x001a:
        r0 = r10.mDeferStart;
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r10.mState;
        if (r0 >= r8) goto L_0x0025;
    L_0x0022:
        if (r11 <= r6) goto L_0x0025;
    L_0x0024:
        r11 = r6;
    L_0x0025:
        r0 = r10.mState;
        if (r0 >= r11) goto L_0x0240;
    L_0x0029:
        r0 = r10.mFromLayout;
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r10.mInLayout;
        if (r0 != 0) goto L_0x0032;
    L_0x0031:
        return;
    L_0x0032:
        r0 = r10.mAnimatingAway;
        if (r0 == 0) goto L_0x0040;
    L_0x0036:
        r10.mAnimatingAway = r7;
        r2 = r10.mStateAfterAnimating;
        r0 = r9;
        r1 = r10;
        r4 = r3;
        r0.a(r1, r2, r3, r4, r5);
    L_0x0040:
        r0 = r10.mState;
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0126;
            case 2: goto L_0x01ef;
            case 3: goto L_0x01ef;
            case 4: goto L_0x0210;
            default: goto L_0x0045;
        };
    L_0x0045:
        r10.mState = r11;
        goto L_0x0031;
    L_0x0048:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x0064;
    L_0x004c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0064:
        r0 = r10.mSavedFragmentState;
        if (r0 == 0) goto L_0x009d;
    L_0x0068:
        r0 = r10.mSavedFragmentState;
        r1 = "android:view_state";
        r0 = r0.getSparseParcelableArray(r1);
        r10.mSavedViewState = r0;
        r0 = r10.mSavedFragmentState;
        r1 = "android:target_state";
        r0 = r9.getFragment(r0, r1);
        r10.Nf = r0;
        r0 = r10.Nf;
        if (r0 == 0) goto L_0x008a;
    L_0x0080:
        r0 = r10.mSavedFragmentState;
        r1 = "android:target_req_state";
        r0 = r0.getInt(r1, r3);
        r10.mTargetRequestCode = r0;
    L_0x008a:
        r0 = r10.mSavedFragmentState;
        r1 = "android:user_visible_hint";
        r0 = r0.getBoolean(r1, r5);
        r10.mUserVisibleHint = r0;
        r0 = r10.mUserVisibleHint;
        if (r0 != 0) goto L_0x009d;
    L_0x0098:
        r10.mDeferStart = r5;
        if (r11 <= r6) goto L_0x009d;
    L_0x009c:
        r11 = r6;
    L_0x009d:
        r0 = r9.Nh;
        r10.Nh = r0;
        r0 = r9.Np;
        r10.Nj = r0;
        r0 = r9.Np;
        if (r0 == 0) goto L_0x00d9;
    L_0x00a9:
        r0 = r9.Np;
        r0 = r0.Ni;
    L_0x00ad:
        r10.Ng = r0;
        r10.mCalled = r3;
        r0 = r9.Nh;
        r10.onAttach(r0);
        r0 = r10.mCalled;
        if (r0 != 0) goto L_0x00de;
    L_0x00ba:
        r0 = new com.google.android.gms.car.support.SuperNotCalledException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " did not call through to super.onAttach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00d9:
        r0 = r9.Nh;
        r0 = r0.Nl;
        goto L_0x00ad;
    L_0x00de:
        r0 = r10.Nj;
        if (r0 != 0) goto L_0x00e7;
    L_0x00e2:
        r0 = r9.Nh;
        r0.onAttachFragment(r10);
    L_0x00e7:
        r0 = r10.mRetaining;
        if (r0 != 0) goto L_0x00f0;
    L_0x00eb:
        r0 = r10.mSavedFragmentState;
        r10.performCreate(r0);
    L_0x00f0:
        r10.mRetaining = r3;
        r0 = r10.mFromLayout;
        if (r0 == 0) goto L_0x0126;
    L_0x00f6:
        r0 = r10.mSavedFragmentState;
        r0 = r10.getLayoutInflater(r0);
        r1 = r10.mSavedFragmentState;
        r0 = r10.performCreateView(r0, r7, r1);
        r10.mView = r0;
        r0 = r10.mView;
        if (r0 == 0) goto L_0x0239;
    L_0x0108:
        r0 = r10.mView;
        r10.mInnerView = r0;
        r0 = r10.mView;
        r0 = com.google.android.gms.car.support.k.wrap(r0);
        r10.mView = r0;
        r0 = r10.mHidden;
        if (r0 == 0) goto L_0x011f;
    L_0x0118:
        r0 = r10.mView;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x011f:
        r0 = r10.mView;
        r1 = r10.mSavedFragmentState;
        r10.onViewCreated(r0, r1);
    L_0x0126:
        if (r11 <= r5) goto L_0x01ef;
    L_0x0128:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x0144;
    L_0x012c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0144:
        r0 = r10.mFromLayout;
        if (r0 != 0) goto L_0x01df;
    L_0x0148:
        r0 = r10.mContainerId;
        if (r0 == 0) goto L_0x0397;
    L_0x014c:
        r0 = r9.Nm;
        r1 = r10.mContainerId;
        r0 = r0.findViewById(r1);
        r0 = (android.view.ViewGroup) r0;
        if (r0 != 0) goto L_0x019b;
    L_0x0158:
        r1 = r10.mRestored;
        if (r1 != 0) goto L_0x019b;
    L_0x015c:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "No view found for id 0x";
        r2 = r2.append(r3);
        r3 = r10.mContainerId;
        r3 = java.lang.Integer.toHexString(r3);
        r2 = r2.append(r3);
        r3 = " (";
        r2 = r2.append(r3);
        r3 = r10.getResources();
        r4 = r10.mContainerId;
        r3 = r3.getResourceName(r4);
        r2 = r2.append(r3);
        r3 = ") for fragment ";
        r2 = r2.append(r3);
        r2 = r2.append(r10);
        r2 = r2.toString();
        r1.<init>(r2);
        r9.a(r1);
    L_0x019b:
        r10.mContainer = r0;
        r1 = r10.mSavedFragmentState;
        r1 = r10.getLayoutInflater(r1);
        r2 = r10.mSavedFragmentState;
        r1 = r10.performCreateView(r1, r0, r2);
        r10.mView = r1;
        r1 = r10.mView;
        if (r1 == 0) goto L_0x023d;
    L_0x01af:
        r1 = r10.mView;
        r10.mInnerView = r1;
        r1 = r10.mView;
        r1 = com.google.android.gms.car.support.k.wrap(r1);
        r10.mView = r1;
        if (r0 == 0) goto L_0x01cd;
    L_0x01bd:
        r1 = r9.a(r10, r12, r5, r13);
        if (r1 == 0) goto L_0x01c8;
    L_0x01c3:
        r2 = r10.mView;
        r2.startAnimation(r1);
    L_0x01c8:
        r1 = r10.mView;
        r0.addView(r1);
    L_0x01cd:
        r0 = r10.mHidden;
        if (r0 == 0) goto L_0x01d8;
    L_0x01d1:
        r0 = r10.mView;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x01d8:
        r0 = r10.mView;
        r1 = r10.mSavedFragmentState;
        r10.onViewCreated(r0, r1);
    L_0x01df:
        r0 = r10.mSavedFragmentState;
        r10.performActivityCreated(r0);
        r0 = r10.mView;
        if (r0 == 0) goto L_0x01ed;
    L_0x01e8:
        r0 = r10.mSavedFragmentState;
        r10.restoreViewState(r0);
    L_0x01ed:
        r10.mSavedFragmentState = r7;
    L_0x01ef:
        if (r11 <= r6) goto L_0x0210;
    L_0x01f1:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x020d;
    L_0x01f5:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x020d:
        r10.performStart();
    L_0x0210:
        if (r11 <= r8) goto L_0x0045;
    L_0x0212:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x022e;
    L_0x0216:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x022e:
        r10.mResumed = r5;
        r10.performResume();
        r10.mSavedFragmentState = r7;
        r10.mSavedViewState = r7;
        goto L_0x0045;
    L_0x0239:
        r10.mInnerView = r7;
        goto L_0x0126;
    L_0x023d:
        r10.mInnerView = r7;
        goto L_0x01df;
    L_0x0240:
        r0 = r10.mState;
        if (r0 <= r11) goto L_0x0045;
    L_0x0244:
        r0 = r10.mState;
        switch(r0) {
            case 1: goto L_0x024b;
            case 2: goto L_0x02cb;
            case 3: goto L_0x02aa;
            case 4: goto L_0x0289;
            case 5: goto L_0x0265;
            default: goto L_0x0249;
        };
    L_0x0249:
        goto L_0x0045;
    L_0x024b:
        if (r11 >= r5) goto L_0x0045;
    L_0x024d:
        r0 = r9.mDestroyed;
        if (r0 == 0) goto L_0x025c;
    L_0x0251:
        r0 = r10.mAnimatingAway;
        if (r0 == 0) goto L_0x025c;
    L_0x0255:
        r0 = r10.mAnimatingAway;
        r10.mAnimatingAway = r7;
        r0.clearAnimation();
    L_0x025c:
        r0 = r10.mAnimatingAway;
        if (r0 == 0) goto L_0x0338;
    L_0x0260:
        r10.mStateAfterAnimating = r11;
        r11 = r5;
        goto L_0x0045;
    L_0x0265:
        r0 = 5;
        if (r11 >= r0) goto L_0x0289;
    L_0x0268:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x0284;
    L_0x026c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0284:
        r10.performPause();
        r10.mResumed = r3;
    L_0x0289:
        if (r11 >= r8) goto L_0x02aa;
    L_0x028b:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x02a7;
    L_0x028f:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02a7:
        r10.performStop();
    L_0x02aa:
        if (r11 >= r6) goto L_0x02cb;
    L_0x02ac:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x02c8;
    L_0x02b0:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STOPPED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02c8:
        r10.performReallyStop();
    L_0x02cb:
        r0 = 2;
        if (r11 >= r0) goto L_0x024b;
    L_0x02ce:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x02ea;
    L_0x02d2:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02ea:
        r0 = r10.mView;
        if (r0 == 0) goto L_0x02fd;
    L_0x02ee:
        r0 = r9.Nh;
        r0 = r0.isFinishing();
        if (r0 != 0) goto L_0x02fd;
    L_0x02f6:
        r0 = r10.mSavedViewState;
        if (r0 != 0) goto L_0x02fd;
    L_0x02fa:
        r9.e(r10);
    L_0x02fd:
        r10.performDestroyView();
        r0 = r10.mView;
        if (r0 == 0) goto L_0x0330;
    L_0x0304:
        r0 = r10.mContainer;
        if (r0 == 0) goto L_0x0330;
    L_0x0308:
        r0 = r9.mCurState;
        if (r0 <= 0) goto L_0x0394;
    L_0x030c:
        r0 = r9.mDestroyed;
        if (r0 != 0) goto L_0x0394;
    L_0x0310:
        r0 = r9.a(r10, r12, r3, r13);
    L_0x0314:
        if (r0 == 0) goto L_0x0329;
    L_0x0316:
        r1 = r10.mView;
        r10.mAnimatingAway = r1;
        r10.mStateAfterAnimating = r11;
        r1 = new com.google.android.gms.car.support.g$5;
        r1.<init>(r9, r10);
        r0.setAnimationListener(r1);
        r1 = r10.mView;
        r1.startAnimation(r0);
    L_0x0329:
        r0 = r10.mContainer;
        r1 = r10.mView;
        r0.removeView(r1);
    L_0x0330:
        r10.mContainer = r7;
        r10.mView = r7;
        r10.mInnerView = r7;
        goto L_0x024b;
    L_0x0338:
        r0 = DEBUG;
        if (r0 == 0) goto L_0x0354;
    L_0x033c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0354:
        r0 = r10.mRetaining;
        if (r0 != 0) goto L_0x035b;
    L_0x0358:
        r10.performDestroy();
    L_0x035b:
        r10.mCalled = r3;
        r10.onDetach();
        r0 = r10.mCalled;
        if (r0 != 0) goto L_0x0383;
    L_0x0364:
        r0 = new com.google.android.gms.car.support.SuperNotCalledException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " did not call through to super.onDetach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0383:
        if (r14 != 0) goto L_0x0045;
    L_0x0385:
        r0 = r10.mRetaining;
        if (r0 != 0) goto L_0x038e;
    L_0x0389:
        r9.d(r10);
        goto L_0x0045;
    L_0x038e:
        r10.Nh = r7;
        r10.Ng = r7;
        goto L_0x0045;
    L_0x0394:
        r0 = r7;
        goto L_0x0314;
    L_0x0397:
        r0 = r7;
        goto L_0x019b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.car.support.g.a(com.google.android.gms.car.support.Fragment, int, int, int, boolean):void");
    }

    public void a(Fragment fragment, boolean z) {
        if (this.mAdded == null) {
            this.mAdded = new ArrayList();
        }
        if (DEBUG) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        c(fragment);
        if (!fragment.mDetached) {
            if (this.mAdded.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            this.mAdded.add(fragment);
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            if (z) {
                b(fragment);
            }
        }
    }

    public void a(FragmentActivity fragmentActivity, f fVar, Fragment fragment) {
        if (this.Nh != null) {
            throw new IllegalStateException("Already attached");
        }
        this.Nh = fragmentActivity;
        this.Nm = fVar;
        this.Np = fragment;
    }

    void b(Fragment fragment) {
        a(fragment, this.mCurState, 0, 0, false);
    }

    public void b(Fragment fragment, int i, int i2) {
        if (DEBUG) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.mHidden) {
            fragment.mHidden = true;
            if (fragment.mView != null) {
                Animation a = a(fragment, i, false, i2);
                if (a != null) {
                    fragment.mView.startAnimation(a);
                }
                fragment.mView.setVisibility(8);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(true);
        }
    }

    void b(b bVar) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList();
        }
        this.mBackStack.add(bVar);
        reportBackStackChanged();
    }

    void c(Fragment fragment) {
        if (fragment.mIndex < 0) {
            if (this.mAvailIndices == null || this.mAvailIndices.size() <= 0) {
                if (this.mActive == null) {
                    this.mActive = new ArrayList();
                }
                fragment.a(this.mActive.size(), this.Np);
                this.mActive.add(fragment);
            } else {
                fragment.a(((Integer) this.mAvailIndices.remove(this.mAvailIndices.size() - 1)).intValue(), this.Np);
                this.mActive.set(fragment.mIndex, fragment);
            }
            if (DEBUG) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }

    public void c(Fragment fragment, int i, int i2) {
        if (DEBUG) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            if (fragment.mView != null) {
                Animation a = a(fragment, i, true, i2);
                if (a != null) {
                    fragment.mView.startAnimation(a);
                }
                fragment.mView.setVisibility(0);
            }
            if (fragment.mAdded && fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.onHiddenChanged(false);
        }
    }

    void d(Fragment fragment) {
        if (fragment.mIndex >= 0) {
            if (DEBUG) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.mActive.set(fragment.mIndex, null);
            if (this.mAvailIndices == null) {
                this.mAvailIndices = new ArrayList();
            }
            this.mAvailIndices.add(Integer.valueOf(fragment.mIndex));
            this.Nh.invalidateSupportFragment(fragment.mWho);
            fragment.initState();
        }
    }

    public void d(Fragment fragment, int i, int i2) {
        if (DEBUG) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.mDetached) {
            fragment.mDetached = true;
            if (fragment.mAdded) {
                if (this.mAdded != null) {
                    if (DEBUG) {
                        Log.v("FragmentManager", "remove from detach: " + fragment);
                    }
                    this.mAdded.remove(fragment);
                }
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                fragment.mAdded = false;
                a(fragment, 1, i, i2, false);
            }
        }
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        moveToState(2, false);
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        moveToState(1, false);
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        moveToState(0, false);
        this.Nh = null;
        this.Nm = null;
        this.Np = null;
    }

    public void dispatchDestroyView() {
        moveToState(1, false);
    }

    public void dispatchPause() {
        moveToState(4, false);
    }

    public void dispatchReallyStop() {
        moveToState(2, false);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        moveToState(5, false);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        moveToState(4, false);
    }

    public void dispatchStop() {
        this.mStateSaved = true;
        moveToState(3, false);
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        int size;
        int i;
        Fragment fragment;
        int i2 = 0;
        String str = prefix + "    ";
        if (this.mActive != null) {
            size = this.mActive.size();
            if (size > 0) {
                writer.print(prefix);
                writer.print("Active Fragments in ");
                writer.print(Integer.toHexString(System.identityHashCode(this)));
                writer.println(":");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.mActive.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(fragment);
                    if (fragment != null) {
                        fragment.dump(str, fd, writer, args);
                    }
                }
            }
        }
        if (this.mAdded != null) {
            size = this.mAdded.size();
            if (size > 0) {
                writer.print(prefix);
                writer.println("Added Fragments:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.mAdded.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(fragment.toString());
                }
            }
        }
        if (this.mCreatedMenus != null) {
            size = this.mCreatedMenus.size();
            if (size > 0) {
                writer.print(prefix);
                writer.println("Fragments Created Menus:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.mCreatedMenus.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(fragment.toString());
                }
            }
        }
        if (this.mBackStack != null) {
            size = this.mBackStack.size();
            if (size > 0) {
                writer.print(prefix);
                writer.println("Back Stack:");
                for (i = 0; i < size; i++) {
                    b bVar = (b) this.mBackStack.get(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i);
                    writer.print(": ");
                    writer.println(bVar.toString());
                    bVar.dump(str, fd, writer, args);
                }
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null) {
                int size2 = this.mBackStackIndices.size();
                if (size2 > 0) {
                    writer.print(prefix);
                    writer.println("Back Stack Indices:");
                    for (i = 0; i < size2; i++) {
                        bVar = (b) this.mBackStackIndices.get(i);
                        writer.print(prefix);
                        writer.print("  #");
                        writer.print(i);
                        writer.print(": ");
                        writer.println(bVar);
                    }
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                writer.print(prefix);
                writer.print("mAvailBackStackIndices: ");
                writer.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        if (this.mPendingActions != null) {
            i = this.mPendingActions.size();
            if (i > 0) {
                writer.print(prefix);
                writer.println("Pending Actions:");
                while (i2 < i) {
                    Runnable runnable = (Runnable) this.mPendingActions.get(i2);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(i2);
                    writer.print(": ");
                    writer.println(runnable);
                    i2++;
                }
            }
        }
        writer.print(prefix);
        writer.println("FragmentManager misc state:");
        writer.print(prefix);
        writer.print("  mActivity=");
        writer.println(this.Nh);
        writer.print(prefix);
        writer.print("  mContainer=");
        writer.println(this.Nm);
        if (this.Np != null) {
            writer.print(prefix);
            writer.print("  mParent=");
            writer.println(this.Np);
        }
        writer.print(prefix);
        writer.print("  mCurState=");
        writer.print(this.mCurState);
        writer.print(" mStateSaved=");
        writer.print(this.mStateSaved);
        writer.print(" mDestroyed=");
        writer.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            writer.print(prefix);
            writer.print("  mNeedMenuInvalidate=");
            writer.println(this.mNeedMenuInvalidate);
        }
        if (this.mNoTransactionsBecause != null) {
            writer.print(prefix);
            writer.print("  mNoTransactionsBecause=");
            writer.println(this.mNoTransactionsBecause);
        }
        if (this.mAvailIndices != null && this.mAvailIndices.size() > 0) {
            writer.print(prefix);
            writer.print("  mAvailIndices: ");
            writer.println(Arrays.toString(this.mAvailIndices.toArray()));
        }
    }

    void e(Fragment fragment) {
        if (fragment.mInnerView != null) {
            if (this.mStateArray == null) {
                this.mStateArray = new SparseArray();
            } else {
                this.mStateArray.clear();
            }
            fragment.mInnerView.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                fragment.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    public void e(Fragment fragment, int i, int i2) {
        if (DEBUG) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (!fragment.mAdded) {
                if (this.mAdded == null) {
                    this.mAdded = new ArrayList();
                }
                if (this.mAdded.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (DEBUG) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                this.mAdded.add(fragment);
                fragment.mAdded = true;
                if (fragment.mHasMenu && fragment.mMenuVisible) {
                    this.mNeedMenuInvalidate = true;
                }
                a(fragment, this.mCurState, i, i2, false);
            }
        }
    }

    public boolean execPendingActions() {
        if (this.mExecutingActions) {
            throw new IllegalStateException("Recursive entry to executePendingTransactions");
        } else if (Looper.myLooper() != this.Nh.mHandler.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of process");
        } else {
            boolean z = false;
            while (true) {
                synchronized (this) {
                    if (this.mPendingActions == null || this.mPendingActions.size() == 0) {
                    } else {
                        int size = this.mPendingActions.size();
                        if (this.mTmpActions == null || this.mTmpActions.length < size) {
                            this.mTmpActions = new Runnable[size];
                        }
                        this.mPendingActions.toArray(this.mTmpActions);
                        this.mPendingActions.clear();
                        this.Nh.mHandler.removeCallbacks(this.mExecCommit);
                        this.mExecutingActions = true;
                        for (int i = 0; i < size; i++) {
                            this.mTmpActions[i].run();
                            this.mTmpActions[i] = null;
                        }
                        this.mExecutingActions = false;
                        z = true;
                    }
                }
            }
            if (this.mHavePendingDeferredStart) {
                this.mHavePendingDeferredStart = false;
                startPendingDeferredFragments();
            }
            return z;
        }
    }

    public Fragment findFragmentById(int id) {
        int size;
        Fragment fragment;
        if (this.mAdded != null) {
            for (size = this.mAdded.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.mAdded.get(size);
                if (fragment != null && fragment.mFragmentId == id) {
                    return fragment;
                }
            }
        }
        if (this.mActive != null) {
            for (size = this.mActive.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.mActive.get(size);
                if (fragment != null && fragment.mFragmentId == id) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment findFragmentByTag(String tag) {
        int size;
        Fragment fragment;
        if (!(this.mAdded == null || tag == null)) {
            for (size = this.mAdded.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.mAdded.get(size);
                if (fragment != null && tag.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (!(this.mActive == null || tag == null)) {
            for (size = this.mActive.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.mActive.get(size);
                if (fragment != null && tag.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment getFragment(Bundle bundle, String key) {
        int i = bundle.getInt(key, -1);
        if (i == -1) {
            return null;
        }
        if (i >= this.mActive.size()) {
            a(new IllegalStateException("Fragment no longer exists for key " + key + ": index " + i));
        }
        Fragment fragment = (Fragment) this.mActive.get(i);
        if (fragment != null) {
            return fragment;
        }
        a(new IllegalStateException("Fragment no longer exists for key " + key + ": index " + i));
        return fragment;
    }

    void moveToState(int newState, int transit, int transitStyle, boolean always) {
        if (this.Nh == null && newState != 0) {
            throw new IllegalStateException("No activity");
        } else if (always || this.mCurState != newState) {
            this.mCurState = newState;
            if (this.mActive != null) {
                for (int i = 0; i < this.mActive.size(); i++) {
                    Fragment fragment = (Fragment) this.mActive.get(i);
                    if (fragment != null) {
                        a(fragment, newState, transit, transitStyle, false);
                    }
                }
                startPendingDeferredFragments();
                if (this.mNeedMenuInvalidate && this.Nh != null && this.mCurState == 5) {
                    this.Nh.supportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }

    void moveToState(int newState, boolean always) {
        moveToState(newState, 0, 0, always);
    }

    public void noteStateNotSaved() {
        this.mStateSaved = false;
    }

    void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); i++) {
                ((OnBackStackChangedListener) this.mBackStackChangeListeners.get(i)).onBackStackChanged();
            }
        }
    }

    void restoreAllState(Parcelable state, ArrayList<Fragment> nonConfig) {
        if (state != null) {
            h hVar = (h) state;
            if (hVar.Ns != null) {
                int i;
                Fragment fragment;
                int i2;
                if (nonConfig != null) {
                    for (i = 0; i < nonConfig.size(); i++) {
                        fragment = (Fragment) nonConfig.get(i);
                        if (DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                        }
                        FragmentState fragmentState = hVar.Ns[fragment.mIndex];
                        fragmentState.Nu = fragment;
                        fragment.mSavedViewState = null;
                        fragment.mBackStackNesting = 0;
                        fragment.mInLayout = false;
                        fragment.mAdded = false;
                        fragment.Nf = null;
                        if (fragmentState.mSavedFragmentState != null) {
                            fragmentState.mSavedFragmentState.setClassLoader(this.Nh.getClassLoader());
                            fragment.mSavedViewState = fragmentState.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                        }
                    }
                }
                this.mActive = new ArrayList(hVar.Ns.length);
                if (this.mAvailIndices != null) {
                    this.mAvailIndices.clear();
                }
                for (i2 = 0; i2 < hVar.Ns.length; i2++) {
                    FragmentState fragmentState2 = hVar.Ns[i2];
                    if (fragmentState2 != null) {
                        Fragment instantiate = fragmentState2.instantiate(this.Nh, this.Np);
                        if (DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i2 + ": " + instantiate);
                        }
                        this.mActive.add(instantiate);
                        fragmentState2.Nu = null;
                    } else {
                        this.mActive.add(null);
                        if (this.mAvailIndices == null) {
                            this.mAvailIndices = new ArrayList();
                        }
                        if (DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + i2);
                        }
                        this.mAvailIndices.add(Integer.valueOf(i2));
                    }
                }
                if (nonConfig != null) {
                    for (int i3 = 0; i3 < nonConfig.size(); i3++) {
                        fragment = (Fragment) nonConfig.get(i3);
                        if (fragment.mTargetIndex >= 0) {
                            if (fragment.mTargetIndex < this.mActive.size()) {
                                fragment.Nf = (Fragment) this.mActive.get(fragment.mTargetIndex);
                            } else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.mTargetIndex);
                                fragment.Nf = null;
                            }
                        }
                    }
                }
                if (hVar.mAdded != null) {
                    this.mAdded = new ArrayList(hVar.mAdded.length);
                    for (i = 0; i < hVar.mAdded.length; i++) {
                        fragment = (Fragment) this.mActive.get(hVar.mAdded[i]);
                        if (fragment == null) {
                            a(new IllegalStateException("No instantiated fragment for index #" + hVar.mAdded[i]));
                        }
                        fragment.mAdded = true;
                        if (DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + fragment);
                        }
                        if (this.mAdded.contains(fragment)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.mAdded.add(fragment);
                    }
                } else {
                    this.mAdded = null;
                }
                if (hVar.Nt != null) {
                    this.mBackStack = new ArrayList(hVar.Nt.length);
                    for (i2 = 0; i2 < hVar.Nt.length; i2++) {
                        b a = hVar.Nt[i2].a(this);
                        if (DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a.mIndex + "): " + a);
                            a.dump("  ", new PrintWriter(new i("FragmentManager")), false);
                        }
                        this.mBackStack.add(a);
                        if (a.mIndex >= 0) {
                            a(a.mIndex, a);
                        }
                    }
                    return;
                }
                this.mBackStack = null;
            }
        }
    }

    void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment fragment = (Fragment) this.mActive.get(i);
                if (fragment != null) {
                    a(fragment);
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("FragmentManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }
}
