package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.a.f;
import android.support.v4.a.i;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: FragmentManager */
final class p extends s {
    static final boolean BF;
    static final Interpolator BH;
    static final Interpolator BI;
    static final Interpolator BJ;
    static final Interpolator BK;
    static boolean DEBUG;
    Fragment BG;
    ArrayList mActive;
    ArrayList mAdded;
    ArrayList mAvailBackStackIndices;
    ArrayList mAvailIndices;
    ArrayList mBackStack;
    ArrayList mBackStackChangeListeners;
    ArrayList mBackStackIndices;
    ArrayList mCreatedMenus;
    int mCurState;
    boolean mDestroyed;
    Runnable mExecCommit;
    boolean mExecutingActions;
    boolean mHavePendingDeferredStart;
    boolean mNeedMenuInvalidate;
    String mNoTransactionsBecause;
    ArrayList mPendingActions;
    SparseArray mStateArray;
    Bundle mStateBundle;
    boolean mStateSaved;
    Runnable[] mTmpActions;
    w ob;
    FragmentActivity tz;

    p() {
        this.mCurState = 0;
        this.mStateBundle = null;
        this.mStateArray = null;
        this.mExecCommit = new c(this);
    }

    static {
        boolean z = false;
        DEBUG = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        BF = z;
        BH = new DecelerateInterpolator(2.5f);
        BI = new DecelerateInterpolator(1.5f);
        BJ = new AccelerateInterpolator(2.5f);
        BK = new AccelerateInterpolator(1.5f);
    }

    private void a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new f("FragmentManager"));
        if (this.tz != null) {
            try {
                this.tz.dump("  ", null, printWriter, new String[0]);
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

    public boolean executePendingTransactions() {
        return execPendingActions();
    }

    public boolean popBackStackImmediate() {
        gM();
        executePendingTransactions();
        return popBackStackState(this.tz.mHandler, null, -1, 0);
    }

    public void a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mIndex < 0) {
            a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, fragment.mIndex);
    }

    public Fragment a(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        if (i >= this.mActive.size()) {
            a(new IllegalStateException("Fragement no longer exists for key " + str + ": index " + i));
        }
        Fragment fragment = (Fragment) this.mActive.get(i);
        if (fragment != null) {
            return fragment;
        }
        a(new IllegalStateException("Fragement no longer exists for key " + str + ": index " + i));
        return fragment;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("FragmentManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        if (this.BG != null) {
            i.buildShortClassTag(this.BG, stringBuilder);
        } else {
            i.buildShortClassTag(this.tz, stringBuilder);
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int i;
        int i2 = 0;
        String str2 = str + "    ";
        if (this.mActive != null) {
            size = this.mActive.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(":");
                for (i = 0; i < size; i++) {
                    Fragment fragment;
                    fragment = (Fragment) this.mActive.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment);
                    if (fragment != null) {
                        fragment.dump(str2, fileDescriptor, printWriter, strArr);
                    }
                }
            }
        }
        if (this.mAdded != null) {
            size = this.mAdded.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Added Fragments:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.mAdded.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.mCreatedMenus != null) {
            size = this.mCreatedMenus.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Fragments Created Menus:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.mCreatedMenus.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.mBackStack != null) {
            size = this.mBackStack.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack:");
                for (i = 0; i < size; i++) {
                    x xVar = (x) this.mBackStack.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(xVar.toString());
                    xVar.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        synchronized (this) {
            if (this.mBackStackIndices != null) {
                int size2 = this.mBackStackIndices.size();
                if (size2 > 0) {
                    printWriter.print(str);
                    printWriter.println("Back Stack Indices:");
                    for (i = 0; i < size2; i++) {
                        xVar = (x) this.mBackStackIndices.get(i);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i);
                        printWriter.print(": ");
                        printWriter.println(xVar);
                    }
                }
            }
            if (this.mAvailBackStackIndices != null && this.mAvailBackStackIndices.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.mAvailBackStackIndices.toArray()));
            }
        }
        if (this.mPendingActions != null) {
            i = this.mPendingActions.size();
            if (i > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                while (i2 < i) {
                    Runnable runnable = (Runnable) this.mPendingActions.get(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i2);
                    printWriter.print(": ");
                    printWriter.println(runnable);
                    i2++;
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mActivity=");
        printWriter.println(this.tz);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.ob);
        if (this.BG != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.BG);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.mCurState);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.mStateSaved);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.mDestroyed);
        if (this.mNeedMenuInvalidate) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.mNeedMenuInvalidate);
        }
        if (this.mNoTransactionsBecause != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.mNoTransactionsBecause);
        }
        if (this.mAvailIndices != null && this.mAvailIndices.size() > 0) {
            printWriter.print(str);
            printWriter.print("  mAvailIndices: ");
            printWriter.println(Arrays.toString(this.mAvailIndices.toArray()));
        }
    }

    static Animation a(Context context, float f, float f2, float f3, float f4) {
        Animation animationSet = new AnimationSet(false);
        Animation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(BH);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(f3, f4);
        scaleAnimation.setInterpolator(BI);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    static Animation a(Context context, float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(BI);
        alphaAnimation.setDuration(220);
        return alphaAnimation;
    }

    Animation a(Fragment fragment, int i, boolean z, int i2) {
        Animation a = fragment.a(i, z, fragment.mNextAnim);
        if (a != null) {
            return a;
        }
        if (fragment.mNextAnim != 0) {
            a = AnimationUtils.loadAnimation(this.tz, fragment.mNextAnim);
            if (a != null) {
                return a;
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
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return a(this.tz, 1.125f, 1.0f, 0.0f, 1.0f);
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return a(this.tz, 1.0f, 0.975f, 1.0f, 0.0f);
            case WindowData.d /*3*/:
                return a(this.tz, 0.975f, 1.0f, 0.0f, 1.0f);
            case Base64.CRLF /*4*/:
                return a(this.tz, 1.0f, 1.075f, 1.0f, 0.0f);
            case WindowData.f /*5*/:
                return a(this.tz, 0.0f, 1.0f);
            case WindowData.g /*6*/:
                return a(this.tz, 1.0f, 0.0f);
            default:
                if (i2 == 0 && this.tz.getWindow() != null) {
                    i2 = this.tz.getWindow().getAttributes().windowAnimations;
                }
                if (i2 == 0) {
                    return null;
                }
                return null;
        }
    }

    public void b(Fragment fragment) {
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void a(android.support.v4.app.Fragment r10, int r11, int r12, int r13, boolean r14) {
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
        r0 = r10.tw;
        if (r0 == 0) goto L_0x0040;
    L_0x0036:
        r10.tw = r7;
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
        r0 = r9.a(r0, r1);
        r10.tx = r0;
        r0 = r10.tx;
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
        r0 = r9.tz;
        r10.tz = r0;
        r0 = r9.BG;
        r10.tB = r0;
        r0 = r9.BG;
        if (r0 == 0) goto L_0x00d9;
    L_0x00a9:
        r0 = r9.BG;
        r0 = r0.tA;
    L_0x00ad:
        r10.ty = r0;
        r10.mCalled = r3;
        r0 = r9.tz;
        r10.onAttach(r0);
        r0 = r10.mCalled;
        if (r0 != 0) goto L_0x00de;
    L_0x00ba:
        r0 = new android.support.v4.app.SuperNotCalledException;
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
        r0 = r9.tz;
        r0 = r0.oa;
        goto L_0x00ad;
    L_0x00de:
        r0 = r10.tB;
        if (r0 != 0) goto L_0x00e7;
    L_0x00e2:
        r0 = r9.tz;
        r0.a(r10);
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
        r10.tC = r0;
        r0 = r10.mView;
        r0 = android.support.v4.app.A.p(r0);
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
        r0 = r9.ob;
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
        r10.tC = r1;
        r1 = r10.mView;
        r1 = android.support.v4.app.A.p(r1);
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
        r10.tC = r7;
        goto L_0x0126;
    L_0x023d:
        r10.tC = r7;
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
        r0 = r10.tw;
        if (r0 == 0) goto L_0x025c;
    L_0x0255:
        r0 = r10.tw;
        r10.tw = r7;
        r0.clearAnimation();
    L_0x025c:
        r0 = r10.tw;
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
        r10.el();
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
        r0 = r9.tz;
        r0 = r0.isFinishing();
        if (r0 != 0) goto L_0x02fd;
    L_0x02f6:
        r0 = r10.mSavedViewState;
        if (r0 != 0) goto L_0x02fd;
    L_0x02fa:
        r9.f(r10);
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
        r10.tw = r1;
        r10.mStateAfterAnimating = r11;
        r1 = new android.support.v4.app.b;
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
        r10.tC = r7;
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
        r0 = new android.support.v4.app.SuperNotCalledException;
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
        r9.e(r10);
        goto L_0x0045;
    L_0x038e:
        r10.tz = r7;
        r10.ty = r7;
        goto L_0x0045;
    L_0x0394:
        r0 = r7;
        goto L_0x0314;
    L_0x0397:
        r0 = r7;
        goto L_0x019b;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.p.a(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    void c(Fragment fragment) {
        a(fragment, this.mCurState, 0, 0, false);
    }

    void moveToState(int i, boolean z) {
        moveToState(i, 0, 0, z);
    }

    void moveToState(int i, int i2, int i3, boolean z) {
        if (this.tz == null && i != 0) {
            throw new IllegalStateException("No activity");
        } else if (z || this.mCurState != i) {
            this.mCurState = i;
            if (this.mActive != null) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < this.mActive.size()) {
                    int hasRunningLoaders;
                    Fragment fragment = (Fragment) this.mActive.get(i4);
                    if (fragment != null) {
                        a(fragment, i, i2, i3, false);
                        if (fragment.of != null) {
                            hasRunningLoaders = i5 | fragment.of.hasRunningLoaders();
                            i4++;
                            i5 = hasRunningLoaders;
                        }
                    }
                    hasRunningLoaders = i5;
                    i4++;
                    i5 = hasRunningLoaders;
                }
                if (i5 == 0) {
                    startPendingDeferredFragments();
                }
                if (this.mNeedMenuInvalidate && this.tz != null && this.mCurState == 5) {
                    this.tz.cm();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }

    void startPendingDeferredFragments() {
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment fragment = (Fragment) this.mActive.get(i);
                if (fragment != null) {
                    b(fragment);
                }
            }
        }
    }

    void d(Fragment fragment) {
        if (fragment.mIndex < 0) {
            if (this.mAvailIndices == null || this.mAvailIndices.size() <= 0) {
                if (this.mActive == null) {
                    this.mActive = new ArrayList();
                }
                fragment.a(this.mActive.size(), this.BG);
                this.mActive.add(fragment);
            } else {
                fragment.a(((Integer) this.mAvailIndices.remove(this.mAvailIndices.size() - 1)).intValue(), this.BG);
                this.mActive.set(fragment.mIndex, fragment);
            }
            if (DEBUG) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }

    void e(Fragment fragment) {
        if (fragment.mIndex >= 0) {
            if (DEBUG) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.mActive.set(fragment.mIndex, null);
            if (this.mAvailIndices == null) {
                this.mAvailIndices = new ArrayList();
            }
            this.mAvailIndices.add(Integer.valueOf(fragment.mIndex));
            this.tz.Y(fragment.mWho);
            fragment.initState();
        }
    }

    public void a(Fragment fragment, boolean z) {
        if (this.mAdded == null) {
            this.mAdded = new ArrayList();
        }
        if (DEBUG) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        d(fragment);
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
                c(fragment);
            }
        }
    }

    public void a(Fragment fragment, int i, int i2) {
        if (DEBUG) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            int i3;
            if (this.mAdded != null) {
                this.mAdded.remove(fragment);
            }
            if (fragment.mHasMenu && fragment.mMenuVisible) {
                this.mNeedMenuInvalidate = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
            if (z) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            a(fragment, i3, i, i2, false);
        }
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

    public Fragment T(int i) {
        int size;
        Fragment fragment;
        if (this.mAdded != null) {
            for (size = this.mAdded.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.mAdded.get(size);
                if (fragment != null && fragment.mFragmentId == i) {
                    return fragment;
                }
            }
        }
        if (this.mActive != null) {
            for (size = this.mActive.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.mActive.get(size);
                if (fragment != null && fragment.mFragmentId == i) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment cc(String str) {
        int size;
        Fragment fragment;
        if (!(this.mAdded == null || str == null)) {
            for (size = this.mAdded.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.mAdded.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (!(this.mActive == null || str == null)) {
            for (size = this.mActive.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.mActive.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    private void gM() {
        if (this.mStateSaved) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.mNoTransactionsBecause != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.mNoTransactionsBecause);
        }
    }

    public void a(int i, x xVar) {
        synchronized (this) {
            if (this.mBackStackIndices == null) {
                this.mBackStackIndices = new ArrayList();
            }
            int size = this.mBackStackIndices.size();
            if (i < size) {
                if (DEBUG) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + xVar);
                }
                this.mBackStackIndices.set(i, xVar);
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
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + xVar);
                }
                this.mBackStackIndices.add(xVar);
            }
        }
    }

    public void freeBackStackIndex(int i) {
        synchronized (this) {
            this.mBackStackIndices.set(i, null);
            if (this.mAvailBackStackIndices == null) {
                this.mAvailBackStackIndices = new ArrayList();
            }
            if (DEBUG) {
                Log.v("FragmentManager", "Freeing back stack index " + i);
            }
            this.mAvailBackStackIndices.add(Integer.valueOf(i));
        }
    }

    public boolean execPendingActions() {
        if (this.mExecutingActions) {
            throw new IllegalStateException("Recursive entry to executePendingTransactions");
        } else if (Looper.myLooper() != this.tz.mHandler.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of process");
        } else {
            int size;
            boolean z = false;
            while (true) {
                synchronized (this) {
                    if (this.mPendingActions == null || this.mPendingActions.size() == 0) {
                    } else {
                        size = this.mPendingActions.size();
                        if (this.mTmpActions == null || this.mTmpActions.length < size) {
                            this.mTmpActions = new Runnable[size];
                        }
                        this.mPendingActions.toArray(this.mTmpActions);
                        this.mPendingActions.clear();
                        this.tz.mHandler.removeCallbacks(this.mExecCommit);
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
                int i2 = 0;
                for (size = 0; size < this.mActive.size(); size++) {
                    Fragment fragment = (Fragment) this.mActive.get(size);
                    if (!(fragment == null || fragment.of == null)) {
                        i2 |= fragment.of.hasRunningLoaders();
                    }
                }
                if (i2 == 0) {
                    this.mHavePendingDeferredStart = false;
                    startPendingDeferredFragments();
                }
            }
            return z;
        }
    }

    void reportBackStackChanged() {
        if (this.mBackStackChangeListeners != null) {
            for (int i = 0; i < this.mBackStackChangeListeners.size(); i++) {
                ((a) this.mBackStackChangeListeners.get(i)).onBackStackChanged();
            }
        }
    }

    void a(x xVar) {
        if (this.mBackStack == null) {
            this.mBackStack = new ArrayList();
        }
        this.mBackStack.add(xVar);
        reportBackStackChanged();
    }

    boolean popBackStackState(Handler handler, String str, int i, int i2) {
        if (this.mBackStack == null) {
            return false;
        }
        int size;
        if (str == null && i < 0 && (i2 & 1) == 0) {
            size = this.mBackStack.size() - 1;
            if (size < 0) {
                return false;
            }
            ((x) this.mBackStack.remove(size)).popFromBackStack(true);
            reportBackStackChanged();
        } else {
            int size2;
            x xVar;
            size = -1;
            if (str != null || i >= 0) {
                size2 = this.mBackStack.size() - 1;
                while (size2 >= 0) {
                    xVar = (x) this.mBackStack.get(size2);
                    if ((str != null && str.equals(xVar.getName())) || (i >= 0 && i == xVar.mIndex)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    size2--;
                    while (size2 >= 0) {
                        xVar = (x) this.mBackStack.get(size2);
                        if ((str == null || !str.equals(xVar.getName())) && (i < 0 || i != xVar.mIndex)) {
                            break;
                        }
                        size2--;
                    }
                }
                size = size2;
            }
            if (size == this.mBackStack.size() - 1) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (size2 = this.mBackStack.size() - 1; size2 > size; size2--) {
                arrayList.add(this.mBackStack.remove(size2));
            }
            int size3 = arrayList.size() - 1;
            for (int i3 = 0; i3 <= size3; i3++) {
                boolean z;
                if (DEBUG) {
                    Log.v("FragmentManager", "Popping back stack state: " + arrayList.get(i3));
                }
                xVar = (x) arrayList.get(i3);
                if (i3 == size3) {
                    z = true;
                } else {
                    z = false;
                }
                xVar.popFromBackStack(z);
            }
            reportBackStackChanged();
        }
        return true;
    }

    ArrayList retainNonConfig() {
        ArrayList arrayList = null;
        if (this.mActive != null) {
            for (int i = 0; i < this.mActive.size(); i++) {
                Fragment fragment = (Fragment) this.mActive.get(i);
                if (fragment != null && fragment.mRetainInstance) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                    fragment.mRetaining = true;
                    fragment.mTargetIndex = fragment.tx != null ? fragment.tx.mIndex : -1;
                    if (DEBUG) {
                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                    }
                }
            }
        }
        return arrayList;
    }

    void f(Fragment fragment) {
        if (fragment.tC != null) {
            if (this.mStateArray == null) {
                this.mStateArray = new SparseArray();
            } else {
                this.mStateArray.clear();
            }
            fragment.tC.saveHierarchyState(this.mStateArray);
            if (this.mStateArray.size() > 0) {
                fragment.mSavedViewState = this.mStateArray;
                this.mStateArray = null;
            }
        }
    }

    Bundle g(Fragment fragment) {
        Bundle bundle;
        if (this.mStateBundle == null) {
            this.mStateBundle = new Bundle();
        }
        fragment.performSaveInstanceState(this.mStateBundle);
        if (this.mStateBundle.isEmpty()) {
            bundle = null;
        } else {
            bundle = this.mStateBundle;
            this.mStateBundle = null;
        }
        if (fragment.mView != null) {
            f(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    Parcelable saveAllState() {
        e[] eVarArr = null;
        execPendingActions();
        if (BF) {
            this.mStateSaved = true;
        }
        if (this.mActive == null || this.mActive.size() <= 0) {
            return null;
        }
        int size = this.mActive.size();
        d[] dVarArr = new d[size];
        int i = 0;
        boolean z = false;
        while (i < size) {
            boolean z2;
            Fragment fragment = (Fragment) this.mActive.get(i);
            if (fragment != null) {
                if (fragment.mIndex < 0) {
                    a(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.mIndex));
                }
                d dVar = new d(fragment);
                dVarArr[i] = dVar;
                if (fragment.mState <= 0 || dVar.mSavedFragmentState != null) {
                    dVar.mSavedFragmentState = fragment.mSavedFragmentState;
                } else {
                    dVar.mSavedFragmentState = g(fragment);
                    if (fragment.tx != null) {
                        if (fragment.tx.mIndex < 0) {
                            a(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.tx));
                        }
                        if (dVar.mSavedFragmentState == null) {
                            dVar.mSavedFragmentState = new Bundle();
                        }
                        a(dVar.mSavedFragmentState, "android:target_state", fragment.tx);
                        if (fragment.mTargetRequestCode != 0) {
                            dVar.mSavedFragmentState.putInt("android:target_req_state", fragment.mTargetRequestCode);
                        }
                    }
                }
                if (DEBUG) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + dVar.mSavedFragmentState);
                }
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        if (z) {
            int[] iArr;
            int i2;
            i iVar;
            if (this.mAdded != null) {
                i = this.mAdded.size();
                if (i > 0) {
                    iArr = new int[i];
                    for (i2 = 0; i2 < i; i2++) {
                        iArr[i2] = ((Fragment) this.mAdded.get(i2)).mIndex;
                        if (iArr[i2] < 0) {
                            a(new IllegalStateException("Failure saving state: active " + this.mAdded.get(i2) + " has cleared index: " + iArr[i2]));
                        }
                        if (DEBUG) {
                            Log.v("FragmentManager", "saveAllState: adding fragment #" + i2 + ": " + this.mAdded.get(i2));
                        }
                    }
                    if (this.mBackStack != null) {
                        i = this.mBackStack.size();
                        if (i > 0) {
                            eVarArr = new e[i];
                            for (i2 = 0; i2 < i; i2++) {
                                eVarArr[i2] = new e(this, (x) this.mBackStack.get(i2));
                                if (DEBUG) {
                                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.mBackStack.get(i2));
                                }
                            }
                        }
                    }
                    iVar = new i();
                    iVar.ua = dVarArr;
                    iVar.mAdded = iArr;
                    iVar.ub = eVarArr;
                    return iVar;
                }
            }
            iArr = null;
            if (this.mBackStack != null) {
                i = this.mBackStack.size();
                if (i > 0) {
                    eVarArr = new e[i];
                    for (i2 = 0; i2 < i; i2++) {
                        eVarArr[i2] = new e(this, (x) this.mBackStack.get(i2));
                        if (DEBUG) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.mBackStack.get(i2));
                        }
                    }
                }
            }
            iVar = new i();
            iVar.ua = dVarArr;
            iVar.mAdded = iArr;
            iVar.ub = eVarArr;
            return iVar;
        } else if (!DEBUG) {
            return null;
        } else {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
    }

    void restoreAllState(Parcelable parcelable, ArrayList arrayList) {
        if (parcelable != null) {
            i iVar = (i) parcelable;
            if (iVar.ua != null) {
                int i;
                Fragment fragment;
                int i2;
                if (arrayList != null) {
                    for (i = 0; i < arrayList.size(); i++) {
                        fragment = (Fragment) arrayList.get(i);
                        if (DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                        }
                        d dVar = iVar.ua[fragment.mIndex];
                        dVar.hK = fragment;
                        fragment.mSavedViewState = null;
                        fragment.mBackStackNesting = 0;
                        fragment.mInLayout = false;
                        fragment.mAdded = false;
                        fragment.tx = null;
                        if (dVar.mSavedFragmentState != null) {
                            dVar.mSavedFragmentState.setClassLoader(this.tz.getClassLoader());
                            fragment.mSavedViewState = dVar.mSavedFragmentState.getSparseParcelableArray("android:view_state");
                        }
                    }
                }
                this.mActive = new ArrayList(iVar.ua.length);
                if (this.mAvailIndices != null) {
                    this.mAvailIndices.clear();
                }
                for (i2 = 0; i2 < iVar.ua.length; i2++) {
                    d dVar2 = iVar.ua[i2];
                    if (dVar2 != null) {
                        Fragment a = dVar2.a(this.tz, this.BG);
                        if (DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i2 + ": " + a);
                        }
                        this.mActive.add(a);
                        dVar2.hK = null;
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
                if (arrayList != null) {
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        fragment = (Fragment) arrayList.get(i3);
                        if (fragment.mTargetIndex >= 0) {
                            if (fragment.mTargetIndex < this.mActive.size()) {
                                fragment.tx = (Fragment) this.mActive.get(fragment.mTargetIndex);
                            } else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.mTargetIndex);
                                fragment.tx = null;
                            }
                        }
                    }
                }
                if (iVar.mAdded != null) {
                    this.mAdded = new ArrayList(iVar.mAdded.length);
                    for (i = 0; i < iVar.mAdded.length; i++) {
                        fragment = (Fragment) this.mActive.get(iVar.mAdded[i]);
                        if (fragment == null) {
                            a(new IllegalStateException("No instantiated fragment for index #" + iVar.mAdded[i]));
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
                if (iVar.ub != null) {
                    this.mBackStack = new ArrayList(iVar.ub.length);
                    for (i2 = 0; i2 < iVar.ub.length; i2++) {
                        x a2 = iVar.ub[i2].a(this);
                        if (DEBUG) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a2.mIndex + "): " + a2);
                            a2.dump("  ", new PrintWriter(new f("FragmentManager")), false);
                        }
                        this.mBackStack.add(a2);
                        if (a2.mIndex >= 0) {
                            a(a2.mIndex, a2);
                        }
                    }
                    return;
                }
                this.mBackStack = null;
            }
        }
    }

    public void a(FragmentActivity fragmentActivity, w wVar, Fragment fragment) {
        if (this.tz != null) {
            throw new IllegalStateException("Already attached");
        }
        this.tz = fragmentActivity;
        this.ob = wVar;
        this.BG = fragment;
    }

    public void noteStateNotSaved() {
        this.mStateSaved = false;
    }

    public void dispatchCreate() {
        this.mStateSaved = false;
        moveToState(1, false);
    }

    public void dispatchActivityCreated() {
        this.mStateSaved = false;
        moveToState(2, false);
    }

    public void dispatchStart() {
        this.mStateSaved = false;
        moveToState(4, false);
    }

    public void dispatchResume() {
        this.mStateSaved = false;
        moveToState(5, false);
    }

    public void dispatchPause() {
        moveToState(4, false);
    }

    public void dispatchStop() {
        this.mStateSaved = true;
        moveToState(3, false);
    }

    public void gN() {
        moveToState(2, false);
    }

    public void dispatchDestroyView() {
        moveToState(1, false);
    }

    public void dispatchDestroy() {
        this.mDestroyed = true;
        execPendingActions();
        moveToState(0, false);
        this.tz = null;
        this.ob = null;
        this.BG = null;
    }

    public void dispatchConfigurationChanged(Configuration configuration) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment fragment = (Fragment) this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performConfigurationChanged(configuration);
                }
            }
        }
    }

    public void dispatchLowMemory() {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment fragment = (Fragment) this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performLowMemory();
                }
            }
        }
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z;
        Fragment fragment;
        int i = 0;
        ArrayList arrayList = null;
        if (this.mAdded != null) {
            int i2 = 0;
            z = false;
            while (i2 < this.mAdded.size()) {
                fragment = (Fragment) this.mAdded.get(i2);
                if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                    z = true;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                }
                i2++;
                z = z;
            }
        } else {
            z = false;
        }
        if (this.mCreatedMenus != null) {
            while (i < this.mCreatedMenus.size()) {
                fragment = (Fragment) this.mCreatedMenus.get(i);
                if (arrayList == null || !arrayList.contains(fragment)) {
                    fragment.onDestroyOptionsMenu();
                }
                i++;
            }
        }
        this.mCreatedMenus = arrayList;
        return z;
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu) {
        if (this.mAdded == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = (Fragment) this.mAdded.get(i);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuItem) {
        if (this.mAdded == null) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = (Fragment) this.mAdded.get(i);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean dispatchContextItemSelected(MenuItem menuItem) {
        if (this.mAdded == null) {
            return false;
        }
        for (int i = 0; i < this.mAdded.size(); i++) {
            Fragment fragment = (Fragment) this.mAdded.get(i);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void dispatchOptionsMenuClosed(Menu menu) {
        if (this.mAdded != null) {
            for (int i = 0; i < this.mAdded.size(); i++) {
                Fragment fragment = (Fragment) this.mAdded.get(i);
                if (fragment != null) {
                    fragment.performOptionsMenuClosed(menu);
                }
            }
        }
    }

    public static int reverseTransit(int i) {
        switch (i) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    public static int transitToStyleIndex(int i, boolean z) {
        switch (i) {
            case 4097:
                return z ? 1 : 2;
            case 4099:
                return z ? 5 : 6;
            case 8194:
                return z ? 3 : 4;
            default:
                return -1;
        }
    }
}
