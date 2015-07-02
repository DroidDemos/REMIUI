package miui.external;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;

class c implements OnClickListener {
    final /* synthetic */ SdkErrorActivity b;

    c(SdkErrorActivity sdkErrorActivity) {
        this.b = sdkErrorActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        final Dialog a = this.b.d();
        new a(this.b, a).show(this.b.getFragmentManager(), "SdkUpdatePromptDialog");
        new AsyncTask<Void, Void, Boolean>(this) {
            final /* synthetic */ c f;

            protected /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
                a((Boolean) obj);
            }

            protected Boolean a(Void... voidArr) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Boolean.valueOf(this.f.b.h());
            }

            protected void a(Boolean bool) {
                a.dismiss();
                new a(this.f.b, bool.booleanValue() ? this.f.b.e() : this.f.b.f()).show(this.f.b.getFragmentManager(), "SdkUpdateFinishDialog");
            }
        }.execute(new Void[0]);
    }
}
