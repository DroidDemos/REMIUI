package b;

import b.a.a;
import org.json.JSONException;

class c implements Runnable {
    final /* synthetic */ a en;
    final /* synthetic */ b eo;

    c(b bVar, a aVar) {
        this.eo = bVar;
        this.en = aVar;
    }

    public void run() {
        a a = this.eo.b(this.en);
        if (a != a.NONE_ERROR) {
            try {
                this.eo.a(this.en.b(), a, true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
