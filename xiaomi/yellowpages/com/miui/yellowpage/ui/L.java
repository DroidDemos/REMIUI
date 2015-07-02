package com.miui.yellowpage.ui;

import android.os.AsyncTask;
import com.miui.yellowpage.base.utils.ExtendedData;
import com.miui.yellowpage.model.YellowPageDataDetailEntry;
import java.util.ArrayList;

/* compiled from: YellowPageFragment */
class L extends AsyncTask {
    final /* synthetic */ Y iu;

    private L(Y y) {
        this.iu = y;
    }

    protected /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        return b((Void[]) objArr);
    }

    protected /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        a((ArrayList) obj);
    }

    protected void a(ArrayList arrayList) {
        this.iu.lk.updateData(arrayList);
    }

    protected ArrayList b(Void... voidArr) {
        if (this.iu.lh == null) {
            return null;
        }
        return YellowPageDataDetailEntry.a(this.iu.mActivity, this.iu.lh, this.iu.le, ExtendedData.parseExtendedData(this.iu.mActivity, this.iu.ld, this.iu.lf, this.iu.lh.getName(), this.iu.li, false, this.iu.getStatisticsContext()), this.iu.getStatisticsContext());
    }
}
