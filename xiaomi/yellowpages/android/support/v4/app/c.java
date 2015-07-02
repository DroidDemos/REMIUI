package android.support.v4.app;

/* compiled from: FragmentManager */
class c implements Runnable {
    final /* synthetic */ p hu;

    c(p pVar) {
        this.hu = pVar;
    }

    public void run() {
        this.hu.execPendingActions();
    }
}
