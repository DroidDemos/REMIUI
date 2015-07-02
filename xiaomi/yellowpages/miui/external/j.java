package miui.external;

import android.app.Dialog;
import android.os.AsyncTask;

class j extends AsyncTask {
    final /* synthetic */ a JT;
    final /* synthetic */ Dialog e;

    j(a aVar, Dialog dialog) {
        this.JT = aVar;
        this.e = dialog;
    }

    protected /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        return c((Void[]) objArr);
    }

    protected /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        a((Boolean) obj);
    }

    protected Boolean c(Void... voidArr) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Boolean.valueOf(this.JT.bt.an());
    }

    protected void a(Boolean bool) {
        this.e.dismiss();
        new g(this.JT.bt, bool.booleanValue() ? this.JT.bt.hq() : this.JT.bt.hr()).show(this.JT.bt.getFragmentManager(), "SdkUpdateFinishDialog");
    }
}
