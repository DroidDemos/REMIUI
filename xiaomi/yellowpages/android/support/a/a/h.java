package android.support.a.a;

import android.app.Fragment;
import android.app.Fragment.SavedState;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.D;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* compiled from: FragmentStatePagerAdapter */
public abstract class h extends D {
    private FragmentTransaction Mf;
    private ArrayList Mg;
    private ArrayList Mh;
    private Fragment Mi;
    private final FragmentManager mFragmentManager;

    public abstract Fragment r(int i);

    public h(FragmentManager fragmentManager) {
        this.Mf = null;
        this.Mg = new ArrayList();
        this.Mh = new ArrayList();
        this.Mi = null;
        this.mFragmentManager = fragmentManager;
    }

    public void startUpdate(ViewGroup viewGroup) {
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.Mh.size() > i) {
            Fragment fragment = (Fragment) this.Mh.get(i);
            if (fragment != null) {
                return fragment;
            }
        }
        if (this.Mf == null) {
            this.Mf = this.mFragmentManager.beginTransaction();
        }
        Fragment r = r(i);
        if (this.Mg.size() > i) {
            SavedState savedState = (SavedState) this.Mg.get(i);
            if (savedState != null) {
                r.setInitialSavedState(savedState);
            }
        }
        while (this.Mh.size() <= i) {
            this.Mh.add(null);
        }
        d.b(r, false);
        d.a(r, false);
        this.Mh.set(i, r);
        this.Mf.add(viewGroup.getId(), r);
        return r;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.Mf == null) {
            this.Mf = this.mFragmentManager.beginTransaction();
        }
        while (this.Mg.size() <= i) {
            this.Mg.add(null);
        }
        this.Mg.set(i, this.mFragmentManager.saveFragmentInstanceState(fragment));
        this.Mh.set(i, null);
        this.Mf.remove(fragment);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.Mi) {
            if (this.Mi != null) {
                d.b(this.Mi, false);
                d.a(this.Mi, false);
            }
            if (fragment != null) {
                d.b(fragment, true);
                d.a(fragment, true);
            }
            this.Mi = fragment;
        }
    }

    public void finishUpdate(ViewGroup viewGroup) {
        if (this.Mf != null) {
            this.Mf.commitAllowingStateLoss();
            this.Mf = null;
            this.mFragmentManager.executePendingTransactions();
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    public Parcelable saveState() {
        Bundle bundle = null;
        if (this.Mg.size() > 0) {
            bundle = new Bundle();
            Parcelable[] parcelableArr = new SavedState[this.Mg.size()];
            this.Mg.toArray(parcelableArr);
            bundle.putParcelableArray("states", parcelableArr);
        }
        Parcelable parcelable = bundle;
        for (int i = 0; i < this.Mh.size(); i++) {
            Fragment fragment = (Fragment) this.Mh.get(i);
            if (fragment != null) {
                if (parcelable == null) {
                    parcelable = new Bundle();
                }
                this.mFragmentManager.putFragment(parcelable, "f" + i, fragment);
            }
        }
        return parcelable;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.Mg.clear();
            this.Mh.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.Mg.add((SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    Fragment fragment = this.mFragmentManager.getFragment(bundle, str);
                    if (fragment != null) {
                        while (this.Mh.size() <= parseInt) {
                            this.Mh.add(null);
                        }
                        d.b(fragment, false);
                        this.Mh.set(parseInt, fragment);
                    } else {
                        Log.w("FragmentStatePagerAdapter", "Bad fragment at key " + str);
                    }
                }
            }
        }
    }
}
