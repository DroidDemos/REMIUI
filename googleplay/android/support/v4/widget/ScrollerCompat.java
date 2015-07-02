package android.support.v4.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ScrollerCompat {
    ScrollerCompatImpl mImpl;
    Object mScroller;

    interface ScrollerCompatImpl {
        void abortAnimation(Object obj);

        boolean computeScrollOffset(Object obj);

        Object createScroller(Context context, Interpolator interpolator);

        void fling(Object obj, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

        float getCurrVelocity(Object obj);

        int getCurrX(Object obj);

        int getCurrY(Object obj);

        int getFinalX(Object obj);

        int getFinalY(Object obj);

        boolean isFinished(Object obj);

        void startScroll(Object obj, int i, int i2, int i3, int i4, int i5);
    }

    static class ScrollerCompatImplBase implements ScrollerCompatImpl {
        ScrollerCompatImplBase() {
        }

        public Object createScroller(Context context, Interpolator interpolator) {
            return interpolator != null ? new Scroller(context, interpolator) : new Scroller(context);
        }

        public boolean isFinished(Object scroller) {
            return ((Scroller) scroller).isFinished();
        }

        public int getCurrX(Object scroller) {
            return ((Scroller) scroller).getCurrX();
        }

        public int getCurrY(Object scroller) {
            return ((Scroller) scroller).getCurrY();
        }

        public float getCurrVelocity(Object scroller) {
            return 0.0f;
        }

        public boolean computeScrollOffset(Object scroller) {
            return ((Scroller) scroller).computeScrollOffset();
        }

        public void startScroll(Object scroller, int startX, int startY, int dx, int dy, int duration) {
            ((Scroller) scroller).startScroll(startX, startY, dx, dy, duration);
        }

        public void fling(Object scroller, int startX, int startY, int velX, int velY, int minX, int maxX, int minY, int maxY) {
            ((Scroller) scroller).fling(startX, startY, velX, velY, minX, maxX, minY, maxY);
        }

        public void abortAnimation(Object scroller) {
            ((Scroller) scroller).abortAnimation();
        }

        public int getFinalX(Object scroller) {
            return ((Scroller) scroller).getFinalX();
        }

        public int getFinalY(Object scroller) {
            return ((Scroller) scroller).getFinalY();
        }
    }

    static class ScrollerCompatImplGingerbread implements ScrollerCompatImpl {
        ScrollerCompatImplGingerbread() {
        }

        public Object createScroller(Context context, Interpolator interpolator) {
            return ScrollerCompatGingerbread.createScroller(context, interpolator);
        }

        public boolean isFinished(Object scroller) {
            return ScrollerCompatGingerbread.isFinished(scroller);
        }

        public int getCurrX(Object scroller) {
            return ScrollerCompatGingerbread.getCurrX(scroller);
        }

        public int getCurrY(Object scroller) {
            return ScrollerCompatGingerbread.getCurrY(scroller);
        }

        public float getCurrVelocity(Object scroller) {
            return 0.0f;
        }

        public boolean computeScrollOffset(Object scroller) {
            return ScrollerCompatGingerbread.computeScrollOffset(scroller);
        }

        public void startScroll(Object scroller, int startX, int startY, int dx, int dy, int duration) {
            ScrollerCompatGingerbread.startScroll(scroller, startX, startY, dx, dy, duration);
        }

        public void fling(Object scroller, int startX, int startY, int velX, int velY, int minX, int maxX, int minY, int maxY) {
            ScrollerCompatGingerbread.fling(scroller, startX, startY, velX, velY, minX, maxX, minY, maxY);
        }

        public void abortAnimation(Object scroller) {
            ScrollerCompatGingerbread.abortAnimation(scroller);
        }

        public int getFinalX(Object scroller) {
            return ScrollerCompatGingerbread.getFinalX(scroller);
        }

        public int getFinalY(Object scroller) {
            return ScrollerCompatGingerbread.getFinalY(scroller);
        }
    }

    static class ScrollerCompatImplIcs extends ScrollerCompatImplGingerbread {
        ScrollerCompatImplIcs() {
        }

        public float getCurrVelocity(Object scroller) {
            return ScrollerCompatIcs.getCurrVelocity(scroller);
        }
    }

    public static ScrollerCompat create(Context context) {
        return create(context, null);
    }

    public static ScrollerCompat create(Context context, Interpolator interpolator) {
        return new ScrollerCompat(context, interpolator);
    }

    ScrollerCompat(Context context, Interpolator interpolator) {
        this(VERSION.SDK_INT, context, interpolator);
    }

    private ScrollerCompat(int apiVersion, Context context, Interpolator interpolator) {
        if (apiVersion >= 14) {
            this.mImpl = new ScrollerCompatImplIcs();
        } else if (apiVersion >= 9) {
            this.mImpl = new ScrollerCompatImplGingerbread();
        } else {
            this.mImpl = new ScrollerCompatImplBase();
        }
        this.mScroller = this.mImpl.createScroller(context, interpolator);
    }

    public boolean isFinished() {
        return this.mImpl.isFinished(this.mScroller);
    }

    public int getCurrX() {
        return this.mImpl.getCurrX(this.mScroller);
    }

    public int getCurrY() {
        return this.mImpl.getCurrY(this.mScroller);
    }

    public int getFinalX() {
        return this.mImpl.getFinalX(this.mScroller);
    }

    public int getFinalY() {
        return this.mImpl.getFinalY(this.mScroller);
    }

    public float getCurrVelocity() {
        return this.mImpl.getCurrVelocity(this.mScroller);
    }

    public boolean computeScrollOffset() {
        return this.mImpl.computeScrollOffset(this.mScroller);
    }

    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        this.mImpl.startScroll(this.mScroller, startX, startY, dx, dy, duration);
    }

    public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY) {
        this.mImpl.fling(this.mScroller, startX, startY, velocityX, velocityY, minX, maxX, minY, maxY);
    }

    public void abortAnimation() {
        this.mImpl.abortAnimation(this.mScroller);
    }
}
