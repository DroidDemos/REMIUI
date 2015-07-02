package com.miui.yellowpage.a;

import android.content.Context;
import android.widget.Toast;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.request.BaseResult;

/* compiled from: PagedBaseLoader */
public class a extends p {
    private long fj;
    private long fk;
    private HttpRequest fl;
    private final int fm;
    private final String fn;
    private boolean fo;

    public a(Context context, k kVar, String str, int i, int i2) {
        super(context, kVar);
        this.fm = i;
        this.fn = str;
        this.fj = (long) i2;
        this.fo = true;
        this.fk = this.fj;
    }

    protected BaseResult a(int i, BaseResult baseResult, BaseResult baseResult2, boolean z) {
        if (baseResult2 != null) {
            this.fo = baseResult2.getCount() != 0;
        }
        if (!al()) {
            baseResult2 = super.a(i, baseResult, baseResult2, z);
        }
        if (this.fo && !z) {
            this.fj++;
        }
        return baseResult2;
    }

    public void a(BaseRequest baseRequest) {
        if (baseRequest instanceof HttpRequest) {
            this.fl = (HttpRequest) baseRequest;
            super.a(baseRequest);
            return;
        }
        throw new UnsupportedOperationException("Must be an instance of HttpRequest");
    }

    protected void onForceLoad() {
        if (this.fo) {
            this.fl.addParam(this.fn, String.valueOf(this.fj));
            if (!al()) {
                this.fl.setRequestCache(false);
            }
            super.onForceLoad();
            return;
        }
        Toast.makeText(getContext(), R.string.no_more_result, 0).show();
    }

    public void reload() {
        if (!iI()) {
            super.reload();
        }
    }

    public boolean al() {
        return this.fj == this.fk;
    }
}
