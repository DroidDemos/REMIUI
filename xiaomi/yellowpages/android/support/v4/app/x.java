package android.support.v4.app;

import android.support.v4.a.f;
import android.util.Log;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: BackStackRecord */
final class x extends B implements Runnable {
    final p Hh;
    v Hi;
    v Hj;
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

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        dump(str, printWriter, true);
    }

    public void dump(String str, PrintWriter printWriter, boolean z) {
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (!(this.mEnterAnim == 0 && this.mExitAnim == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.mExitAnim));
            }
            if (!(this.mPopEnterAnim == 0 && this.mPopExitAnim == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (!(this.mBreadCrumbTitleRes == 0 && this.mBreadCrumbTitleText == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.mBreadCrumbTitleText);
            }
            if (!(this.mBreadCrumbShortTitleRes == 0 && this.mBreadCrumbShortTitleText == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (this.Hi != null) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str2 = str + "    ";
            int i = 0;
            v vVar = this.Hi;
            while (vVar != null) {
                String str3;
                switch (vVar.cmd) {
                    case TransactionXMLFile.MODE_PRIVATE /*0*/:
                        str3 = "NULL";
                        break;
                    case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                        str3 = "ADD";
                        break;
                    case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                        str3 = "REPLACE";
                        break;
                    case WindowData.d /*3*/:
                        str3 = "REMOVE";
                        break;
                    case Base64.CRLF /*4*/:
                        str3 = "HIDE";
                        break;
                    case WindowData.f /*5*/:
                        str3 = "SHOW";
                        break;
                    case WindowData.g /*6*/:
                        str3 = "DETACH";
                        break;
                    case WindowData.h /*7*/:
                        str3 = "ATTACH";
                        break;
                    default:
                        str3 = "cmd=" + vVar.cmd;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str3);
                printWriter.print(" ");
                printWriter.println(vVar.Gq);
                if (z) {
                    if (!(vVar.enterAnim == 0 && vVar.exitAnim == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(vVar.enterAnim));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(vVar.exitAnim));
                    }
                    if (!(vVar.popEnterAnim == 0 && vVar.popExitAnim == 0)) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(vVar.popEnterAnim));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(vVar.popExitAnim));
                    }
                }
                if (vVar.removed != null && vVar.removed.size() > 0) {
                    for (int i2 = 0; i2 < vVar.removed.size(); i2++) {
                        printWriter.print(str2);
                        if (vVar.removed.size() == 1) {
                            printWriter.print("Removed: ");
                        } else {
                            if (i2 == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(str2);
                            printWriter.print("  #");
                            printWriter.print(i2);
                            printWriter.print(": ");
                        }
                        printWriter.println(vVar.removed.get(i2));
                    }
                }
                vVar = vVar.Go;
                i++;
            }
        }
    }

    public x(p pVar) {
        this.mAllowAddToBackStack = true;
        this.mIndex = -1;
        this.Hh = pVar;
    }

    void a(v vVar) {
        if (this.Hi == null) {
            this.Hj = vVar;
            this.Hi = vVar;
        } else {
            vVar.Gp = this.Hj;
            this.Hj.Go = vVar;
            this.Hj = vVar;
        }
        vVar.enterAnim = this.mEnterAnim;
        vVar.exitAnim = this.mExitAnim;
        vVar.popEnterAnim = this.mPopEnterAnim;
        vVar.popExitAnim = this.mPopExitAnim;
        this.mNumOp++;
    }

    void bumpBackStackNesting(int i) {
        if (this.mAddToBackStack) {
            if (p.DEBUG) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            for (v vVar = this.Hi; vVar != null; vVar = vVar.Go) {
                Fragment fragment;
                if (vVar.Gq != null) {
                    fragment = vVar.Gq;
                    fragment.mBackStackNesting += i;
                    if (p.DEBUG) {
                        Log.v("FragmentManager", "Bump nesting of " + vVar.Gq + " to " + vVar.Gq.mBackStackNesting);
                    }
                }
                if (vVar.removed != null) {
                    for (int size = vVar.removed.size() - 1; size >= 0; size--) {
                        fragment = (Fragment) vVar.removed.get(size);
                        fragment.mBackStackNesting += i;
                        if (p.DEBUG) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.mBackStackNesting);
                        }
                    }
                }
            }
        }
    }

    public void run() {
        if (p.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (!this.mAddToBackStack || this.mIndex >= 0) {
            bumpBackStackNesting(1);
            for (v vVar = this.Hi; vVar != null; vVar = vVar.Go) {
                Fragment fragment;
                switch (vVar.cmd) {
                    case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                        fragment = vVar.Gq;
                        fragment.mNextAnim = vVar.enterAnim;
                        this.Hh.a(fragment, false);
                        break;
                    case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                        Fragment fragment2;
                        fragment = vVar.Gq;
                        if (this.Hh.mAdded != null) {
                            fragment2 = fragment;
                            for (int i = 0; i < this.Hh.mAdded.size(); i++) {
                                fragment = (Fragment) this.Hh.mAdded.get(i);
                                if (p.DEBUG) {
                                    Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment);
                                }
                                if (fragment2 == null || fragment.mContainerId == fragment2.mContainerId) {
                                    if (fragment == fragment2) {
                                        fragment2 = null;
                                        vVar.Gq = null;
                                    } else {
                                        if (vVar.removed == null) {
                                            vVar.removed = new ArrayList();
                                        }
                                        vVar.removed.add(fragment);
                                        fragment.mNextAnim = vVar.exitAnim;
                                        if (this.mAddToBackStack) {
                                            fragment.mBackStackNesting++;
                                            if (p.DEBUG) {
                                                Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.mBackStackNesting);
                                            }
                                        }
                                        this.Hh.a(fragment, this.mTransition, this.mTransitionStyle);
                                    }
                                }
                            }
                        } else {
                            fragment2 = fragment;
                        }
                        if (fragment2 == null) {
                            break;
                        }
                        fragment2.mNextAnim = vVar.enterAnim;
                        this.Hh.a(fragment2, false);
                        break;
                        break;
                    case WindowData.d /*3*/:
                        fragment = vVar.Gq;
                        fragment.mNextAnim = vVar.exitAnim;
                        this.Hh.a(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    case Base64.CRLF /*4*/:
                        fragment = vVar.Gq;
                        fragment.mNextAnim = vVar.exitAnim;
                        this.Hh.b(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    case WindowData.f /*5*/:
                        fragment = vVar.Gq;
                        fragment.mNextAnim = vVar.enterAnim;
                        this.Hh.c(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    case WindowData.g /*6*/:
                        fragment = vVar.Gq;
                        fragment.mNextAnim = vVar.exitAnim;
                        this.Hh.d(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    case WindowData.h /*7*/:
                        fragment = vVar.Gq;
                        fragment.mNextAnim = vVar.enterAnim;
                        this.Hh.e(fragment, this.mTransition, this.mTransitionStyle);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + vVar.cmd);
                }
            }
            this.Hh.moveToState(this.Hh.mCurState, this.mTransition, this.mTransitionStyle, true);
            if (this.mAddToBackStack) {
                this.Hh.a(this);
                return;
            }
            return;
        }
        throw new IllegalStateException("addToBackStack() called after commit()");
    }

    public void popFromBackStack(boolean z) {
        if (p.DEBUG) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            dump("  ", null, new PrintWriter(new f("FragmentManager")), null);
        }
        bumpBackStackNesting(-1);
        for (v vVar = this.Hj; vVar != null; vVar = vVar.Gp) {
            Fragment fragment;
            switch (vVar.cmd) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    fragment = vVar.Gq;
                    fragment.mNextAnim = vVar.popExitAnim;
                    this.Hh.a(fragment, p.reverseTransit(this.mTransition), this.mTransitionStyle);
                    break;
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    fragment = vVar.Gq;
                    if (fragment != null) {
                        fragment.mNextAnim = vVar.popExitAnim;
                        this.Hh.a(fragment, p.reverseTransit(this.mTransition), this.mTransitionStyle);
                    }
                    if (vVar.removed == null) {
                        break;
                    }
                    for (int i = 0; i < vVar.removed.size(); i++) {
                        fragment = (Fragment) vVar.removed.get(i);
                        fragment.mNextAnim = vVar.popEnterAnim;
                        this.Hh.a(fragment, false);
                    }
                    break;
                case WindowData.d /*3*/:
                    fragment = vVar.Gq;
                    fragment.mNextAnim = vVar.popEnterAnim;
                    this.Hh.a(fragment, false);
                    break;
                case Base64.CRLF /*4*/:
                    fragment = vVar.Gq;
                    fragment.mNextAnim = vVar.popEnterAnim;
                    this.Hh.c(fragment, p.reverseTransit(this.mTransition), this.mTransitionStyle);
                    break;
                case WindowData.f /*5*/:
                    fragment = vVar.Gq;
                    fragment.mNextAnim = vVar.popExitAnim;
                    this.Hh.b(fragment, p.reverseTransit(this.mTransition), this.mTransitionStyle);
                    break;
                case WindowData.g /*6*/:
                    fragment = vVar.Gq;
                    fragment.mNextAnim = vVar.popEnterAnim;
                    this.Hh.e(fragment, p.reverseTransit(this.mTransition), this.mTransitionStyle);
                    break;
                case WindowData.h /*7*/:
                    fragment = vVar.Gq;
                    fragment.mNextAnim = vVar.popEnterAnim;
                    this.Hh.d(fragment, p.reverseTransit(this.mTransition), this.mTransitionStyle);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + vVar.cmd);
            }
        }
        if (z) {
            this.Hh.moveToState(this.Hh.mCurState, p.reverseTransit(this.mTransition), this.mTransitionStyle, true);
        }
        if (this.mIndex >= 0) {
            this.Hh.freeBackStackIndex(this.mIndex);
            this.mIndex = -1;
        }
    }

    public String getName() {
        return this.mName;
    }
}
