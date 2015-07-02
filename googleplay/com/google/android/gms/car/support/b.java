package com.google.android.gms.car.support;

import android.util.Log;
import com.google.android.wallet.instrumentmanager.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

final class b extends FragmentTransaction implements Runnable {
    final g MY;
    a MZ;
    a Na;
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex;
    String mName;
    int mNumOp;
    int mPopEnterAnim;
    int mPopExitAnim;
    int mTransition;
    int mTransitionStyle;

    static final class a {
        a Nb;
        a Nc;
        Fragment Nd;
        int cmd;
        int enterAnim;
        int exitAnim;
        int popEnterAnim;
        int popExitAnim;
        ArrayList<Fragment> removed;

        a() {
        }
    }

    public b(g gVar) {
        this.mAllowAddToBackStack = true;
        this.mIndex = -1;
        this.MY = gVar;
    }

    void a(a aVar) {
        if (this.MZ == null) {
            this.Na = aVar;
            this.MZ = aVar;
        } else {
            aVar.Nc = this.Na;
            this.Na.Nb = aVar;
            this.Na = aVar;
        }
        aVar.enterAnim = this.mEnterAnim;
        aVar.exitAnim = this.mExitAnim;
        aVar.popEnterAnim = this.mPopEnterAnim;
        aVar.popExitAnim = this.mPopExitAnim;
        this.mNumOp++;
    }

    void bumpBackStackNesting(int amt) {
        if (this.mAddToBackStack) {
            if (g.DEBUG) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + amt);
            }
            for (a aVar = this.MZ; aVar != null; aVar = aVar.Nb) {
                Fragment fragment;
                if (aVar.Nd != null) {
                    fragment = aVar.Nd;
                    fragment.mBackStackNesting += amt;
                    if (g.DEBUG) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.Nd + " to " + aVar.Nd.mBackStackNesting);
                    }
                }
                if (aVar.removed != null) {
                    for (int size = aVar.removed.size() - 1; size >= 0; size--) {
                        fragment = (Fragment) aVar.removed.get(size);
                        fragment.mBackStackNesting += amt;
                        if (g.DEBUG) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.mBackStackNesting);
                        }
                    }
                }
            }
        }
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        dump(prefix, writer, true);
    }

    public void dump(String prefix, PrintWriter writer, boolean full) {
        if (full) {
            writer.print(prefix);
            writer.print("mName=");
            writer.print(this.mName);
            writer.print(" mIndex=");
            writer.print(this.mIndex);
            writer.print(" mCommitted=");
            writer.println(this.mCommitted);
            if (this.mTransition != 0) {
                writer.print(prefix);
                writer.print("mTransition=#");
                writer.print(Integer.toHexString(this.mTransition));
                writer.print(" mTransitionStyle=#");
                writer.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (!(this.mEnterAnim == 0 && this.mExitAnim == 0)) {
                writer.print(prefix);
                writer.print("mEnterAnim=#");
                writer.print(Integer.toHexString(this.mEnterAnim));
                writer.print(" mExitAnim=#");
                writer.println(Integer.toHexString(this.mExitAnim));
            }
            if (!(this.mPopEnterAnim == 0 && this.mPopExitAnim == 0)) {
                writer.print(prefix);
                writer.print("mPopEnterAnim=#");
                writer.print(Integer.toHexString(this.mPopEnterAnim));
                writer.print(" mPopExitAnim=#");
                writer.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (!(this.mBreadCrumbTitleRes == 0 && this.mBreadCrumbTitleText == null)) {
                writer.print(prefix);
                writer.print("mBreadCrumbTitleRes=#");
                writer.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                writer.print(" mBreadCrumbTitleText=");
                writer.println(this.mBreadCrumbTitleText);
            }
            if (!(this.mBreadCrumbShortTitleRes == 0 && this.mBreadCrumbShortTitleText == null)) {
                writer.print(prefix);
                writer.print("mBreadCrumbShortTitleRes=#");
                writer.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                writer.print(" mBreadCrumbShortTitleText=");
                writer.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (this.MZ != null) {
            writer.print(prefix);
            writer.println("Operations:");
            String str = prefix + "    ";
            int i = 0;
            a aVar = this.MZ;
            while (aVar != null) {
                String str2;
                switch (aVar.cmd) {
                    case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                        str2 = "NULL";
                        break;
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        str2 = "ADD";
                        break;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        str2 = "REPLACE";
                        break;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        str2 = "REMOVE";
                        break;
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        str2 = "HIDE";
                        break;
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                        str2 = "SHOW";
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        str2 = "DETACH";
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                        str2 = "ATTACH";
                        break;
                    default:
                        str2 = "cmd=" + aVar.cmd;
                        break;
                }
                writer.print(prefix);
                writer.print("  Op #");
                writer.print(i);
                writer.print(": ");
                writer.print(str2);
                writer.print(" ");
                writer.println(aVar.Nd);
                if (full) {
                    if (!(aVar.enterAnim == 0 && aVar.exitAnim == 0)) {
                        writer.print(prefix);
                        writer.print("enterAnim=#");
                        writer.print(Integer.toHexString(aVar.enterAnim));
                        writer.print(" exitAnim=#");
                        writer.println(Integer.toHexString(aVar.exitAnim));
                    }
                    if (!(aVar.popEnterAnim == 0 && aVar.popExitAnim == 0)) {
                        writer.print(prefix);
                        writer.print("popEnterAnim=#");
                        writer.print(Integer.toHexString(aVar.popEnterAnim));
                        writer.print(" popExitAnim=#");
                        writer.println(Integer.toHexString(aVar.popExitAnim));
                    }
                }
                if (aVar.removed != null && aVar.removed.size() > 0) {
                    for (int i2 = 0; i2 < aVar.removed.size(); i2++) {
                        writer.print(str);
                        if (aVar.removed.size() == 1) {
                            writer.print("Removed: ");
                        } else {
                            if (i2 == 0) {
                                writer.println("Removed:");
                            }
                            writer.print(str);
                            writer.print("  #");
                            writer.print(i2);
                            writer.print(": ");
                        }
                        writer.println(aVar.removed.get(i2));
                    }
                }
                aVar = aVar.Nb;
                i++;
            }
        }
    }

    public void run() {
        if (g.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (!this.mAddToBackStack || this.mIndex >= 0) {
            bumpBackStackNesting(1);
            for (a aVar = this.MZ; aVar != null; aVar = aVar.Nb) {
                Fragment fragment;
                switch (aVar.cmd) {
                    case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                        fragment = aVar.Nd;
                        fragment.mNextAnim = aVar.enterAnim;
                        this.MY.a(fragment, false);
                        break;
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        Fragment fragment2;
                        fragment = aVar.Nd;
                        if (this.MY.mAdded != null) {
                            fragment2 = fragment;
                            for (int i = 0; i < this.MY.mAdded.size(); i++) {
                                fragment = (Fragment) this.MY.mAdded.get(i);
                                if (g.DEBUG) {
                                    Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment);
                                }
                                if (fragment2 == null || fragment.mContainerId == fragment2.mContainerId) {
                                    if (fragment == fragment2) {
                                        fragment2 = null;
                                        aVar.Nd = null;
                                    } else {
                                        if (aVar.removed == null) {
                                            aVar.removed = new ArrayList();
                                        }
                                        aVar.removed.add(fragment);
                                        fragment.mNextAnim = aVar.exitAnim;
                                        if (this.mAddToBackStack) {
                                            fragment.mBackStackNesting++;
                                            if (g.DEBUG) {
                                                Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.mBackStackNesting);
                                            }
                                        }
                                        this.MY.a(fragment, this.mTransition, this.mTransitionStyle);
                                    }
                                }
                            }
                        } else {
                            fragment2 = fragment;
                        }
                        if (fragment2 == null) {
                            break;
                        }
                        fragment2.mNextAnim = aVar.enterAnim;
                        this.MY.a(fragment2, false);
                        break;
                        break;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        fragment = aVar.Nd;
                        fragment.mNextAnim = aVar.exitAnim;
                        this.MY.a(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    case R.styleable.WalletImFormEditText_required /*4*/:
                        fragment = aVar.Nd;
                        fragment.mNextAnim = aVar.exitAnim;
                        this.MY.b(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    case R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                        fragment = aVar.Nd;
                        fragment.mNextAnim = aVar.enterAnim;
                        this.MY.c(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                        fragment = aVar.Nd;
                        fragment.mNextAnim = aVar.exitAnim;
                        this.MY.d(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                        fragment = aVar.Nd;
                        fragment.mNextAnim = aVar.enterAnim;
                        this.MY.e(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + aVar.cmd);
                }
            }
            this.MY.moveToState(this.MY.mCurState, this.mTransition, this.mTransitionStyle, true);
            if (this.mAddToBackStack) {
                this.MY.b(this);
                return;
            }
            return;
        }
        throw new IllegalStateException("addToBackStack() called after commit()");
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("BackStackEntry{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.mIndex);
        }
        if (this.mName != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.mName);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
