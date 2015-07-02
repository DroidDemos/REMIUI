package com.imbryk.viewPager;

import android.os.Parcelable;
import android.support.v4.app.f;
import android.support.v4.app.j;
import android.support.v4.view.D;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: LoopPagerAdapterWrapper */
public class a extends D {
    private D jw;
    private boolean tF;
    private SparseArray uf;

    void v(boolean z) {
        this.tF = z;
    }

    a(D d) {
        this.uf = new SparseArray();
        this.jw = d;
    }

    public void notifyDataSetChanged() {
        this.uf = new SparseArray();
        super.notifyDataSetChanged();
    }

    int F(int i) {
        int ey = ey();
        if (ey < 1) {
            return 0;
        }
        int i2 = (i - 1) % ey;
        if (i2 < 0) {
            return i2 + ey;
        }
        return i2;
    }

    public int G(int i) {
        return i + 1;
    }

    private int ew() {
        return 1;
    }

    private int ex() {
        return (ew() + ey()) - 1;
    }

    public int getCount() {
        int count = this.jw.getCount();
        return count <= 1 ? count : this.jw.getCount() + 2;
    }

    public int ey() {
        return this.jw.getCount();
    }

    public D ez() {
        return this.jw;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int F = ((this.jw instanceof f) || (this.jw instanceof j)) ? i : F(i);
        if (this.tF) {
            d dVar = (d) this.uf.get(i);
            if (dVar != null) {
                this.uf.remove(i);
                return dVar.object;
            }
        }
        return this.jw.instantiateItem(viewGroup, F);
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        int ew = ew();
        int ex = ex();
        int F = ((this.jw instanceof f) || (this.jw instanceof j)) ? i : F(i);
        if (this.tF && (i == ew || i == ex)) {
            this.uf.put(i, new d(viewGroup, F, obj));
        } else {
            this.jw.destroyItem(viewGroup, F, obj);
        }
    }

    public void finishUpdate(ViewGroup viewGroup) {
        this.jw.finishUpdate(viewGroup);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return this.jw.isViewFromObject(view, obj);
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        this.jw.restoreState(parcelable, classLoader);
    }

    public Parcelable saveState() {
        return this.jw.saveState();
    }

    public void startUpdate(ViewGroup viewGroup) {
        this.jw.startUpdate(viewGroup);
    }

    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        this.jw.setPrimaryItem(viewGroup, i, obj);
    }
}
