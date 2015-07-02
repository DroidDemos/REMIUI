package android.support.v4.view;

/* compiled from: ViewPager */
class v implements Runnable {
    final /* synthetic */ ViewPager rT;

    v(ViewPager viewPager) {
        this.rT = viewPager;
    }

    public void run() {
        this.rT.i(0);
        this.rT.populate();
    }
}
