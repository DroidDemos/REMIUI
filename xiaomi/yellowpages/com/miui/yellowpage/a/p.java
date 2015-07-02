package com.miui.yellowpage.a;

import android.content.Context;
import android.content.Loader;
import android.os.Handler;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.request.BaseResult;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BaseLoader */
public class p extends Loader implements e {
    private volatile boolean Mj;
    private boolean Mk;
    private boolean Ml;
    private List Mm;
    private Handler Mn;
    private h Mo;
    protected k Mp;
    protected b Mq;
    protected o Mr;
    private g is;
    protected BaseResult rA;

    public p(Context context, k kVar) {
        super(context);
        this.Mo = new h();
        this.Mm = new ArrayList();
        h hVar = this.Mo;
        hVar.getClass();
        this.Mn = new Handler(new n(hVar));
        this.Mp = kVar;
        this.Mk = true;
    }

    public void a(b bVar) {
        this.Mq = bVar;
    }

    public void iH() {
        this.is = null;
    }

    public void g(List list) {
        this.Mm.clear();
        this.Mm.addAll(list);
    }

    public void a(BaseRequest baseRequest) {
        this.Mm.clear();
        this.Mm.add(baseRequest);
    }

    public void reload() {
        if (!this.Mj) {
            forceLoad();
        }
    }

    protected BaseResult a(int i, BaseResult baseResult, BaseResult baseResult2, boolean z) {
        if (this.Mq == null || baseResult == null || baseResult2 == null) {
            return baseResult2;
        }
        return this.Mq.a(i, baseResult, baseResult2, z);
    }

    public boolean iI() {
        return this.Mj;
    }

    protected void onStartLoading() {
        this.is = this.Mp.s();
        if (this.is != null) {
            this.is.a(false, false, (e) this);
        }
        if (this.rA != null && this.rA.hasData()) {
            deliverResult(this.rA.shallowClone());
            if (this.is != null) {
                this.is.onStopLoading(iJ());
            }
        }
        if (!this.Mj) {
            if (this.rA == null || !this.rA.hasData() || takeContentChanged()) {
                forceLoad();
            }
        }
    }

    protected void onForceLoad() {
        this.Mo.execute();
    }

    private boolean iJ() {
        return this.rA != null && this.rA.hasData() && this.Ml;
    }
}
