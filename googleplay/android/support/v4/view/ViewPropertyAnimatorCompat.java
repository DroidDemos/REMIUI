package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ViewPropertyAnimatorCompat {
    static final ViewPropertyAnimatorCompatImpl IMPL;
    private Runnable mEndAction;
    private int mOldLayerType;
    private Runnable mStartAction;
    private WeakReference<View> mView;

    interface ViewPropertyAnimatorCompatImpl {
        void alpha(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f);

        void cancel(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view);

        void scaleY(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f);

        void setDuration(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, long j);

        void setInterpolator(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, Interpolator interpolator);

        void setListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener);

        void setUpdateListener(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener);

        void start(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view);

        void translationX(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f);

        void translationY(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, View view, float f);
    }

    static class BaseViewPropertyAnimatorCompatImpl implements ViewPropertyAnimatorCompatImpl {
        WeakHashMap<View, Runnable> mStarterMap;

        class Starter implements Runnable {
            WeakReference<View> mViewRef;
            ViewPropertyAnimatorCompat mVpa;

            private Starter(ViewPropertyAnimatorCompat vpa, View view) {
                this.mViewRef = new WeakReference(view);
                this.mVpa = vpa;
            }

            public void run() {
                BaseViewPropertyAnimatorCompatImpl.this.startAnimation(this.mVpa, (View) this.mViewRef.get());
            }
        }

        BaseViewPropertyAnimatorCompatImpl() {
            this.mStarterMap = null;
        }

        public void setDuration(ViewPropertyAnimatorCompat vpa, View view, long value) {
        }

        public void alpha(ViewPropertyAnimatorCompat vpa, View view, float value) {
            postStartMessage(vpa, view);
        }

        public void translationX(ViewPropertyAnimatorCompat vpa, View view, float value) {
            postStartMessage(vpa, view);
        }

        public void translationY(ViewPropertyAnimatorCompat vpa, View view, float value) {
            postStartMessage(vpa, view);
        }

        public void setInterpolator(ViewPropertyAnimatorCompat vpa, View view, Interpolator value) {
        }

        public void scaleY(ViewPropertyAnimatorCompat vpa, View view, float value) {
            postStartMessage(vpa, view);
        }

        public void cancel(ViewPropertyAnimatorCompat vpa, View view) {
            postStartMessage(vpa, view);
        }

        public void start(ViewPropertyAnimatorCompat vpa, View view) {
            removeStartMessage(view);
            startAnimation(vpa, view);
        }

        public void setListener(ViewPropertyAnimatorCompat vpa, View view, ViewPropertyAnimatorListener listener) {
            view.setTag(2113929216, listener);
        }

        public void setUpdateListener(ViewPropertyAnimatorCompat vpa, View view, ViewPropertyAnimatorUpdateListener listener) {
        }

        private void startAnimation(ViewPropertyAnimatorCompat vpa, View view) {
            ViewPropertyAnimatorListener listenerTag = view.getTag(2113929216);
            ViewPropertyAnimatorListener listener = null;
            if (listenerTag instanceof ViewPropertyAnimatorListener) {
                listener = listenerTag;
            }
            Runnable startAction = vpa.mStartAction;
            Runnable endAction = vpa.mEndAction;
            if (startAction != null) {
                startAction.run();
            }
            if (listener != null) {
                listener.onAnimationStart(view);
                listener.onAnimationEnd(view);
            }
            if (endAction != null) {
                endAction.run();
            }
            if (this.mStarterMap != null) {
                this.mStarterMap.remove(view);
            }
        }

        private void removeStartMessage(View view) {
            if (this.mStarterMap != null) {
                Runnable starter = (Runnable) this.mStarterMap.get(view);
                if (starter != null) {
                    view.removeCallbacks(starter);
                }
            }
        }

        private void postStartMessage(ViewPropertyAnimatorCompat vpa, View view) {
            Runnable runnable = null;
            if (this.mStarterMap != null) {
                runnable = (Runnable) this.mStarterMap.get(view);
            }
            if (runnable == null) {
                runnable = new Starter(vpa, view);
                if (this.mStarterMap == null) {
                    this.mStarterMap = new WeakHashMap();
                }
                this.mStarterMap.put(view, runnable);
            }
            view.removeCallbacks(runnable);
            view.post(runnable);
        }
    }

    static class ICSViewPropertyAnimatorCompatImpl extends BaseViewPropertyAnimatorCompatImpl {
        WeakHashMap<View, Integer> mLayerMap;

        static class MyVpaListener implements ViewPropertyAnimatorListener {
            ViewPropertyAnimatorCompat mVpa;

            MyVpaListener(ViewPropertyAnimatorCompat vpa) {
                this.mVpa = vpa;
            }

            public void onAnimationStart(View view) {
                if (this.mVpa.mOldLayerType >= 0) {
                    ViewCompat.setLayerType(view, 2, null);
                }
                if (this.mVpa.mStartAction != null) {
                    this.mVpa.mStartAction.run();
                }
                ViewPropertyAnimatorListener listenerTag = view.getTag(2113929216);
                ViewPropertyAnimatorListener listener = null;
                if (listenerTag instanceof ViewPropertyAnimatorListener) {
                    listener = listenerTag;
                }
                if (listener != null) {
                    listener.onAnimationStart(view);
                }
            }

            public void onAnimationEnd(View view) {
                if (this.mVpa.mOldLayerType >= 0) {
                    ViewCompat.setLayerType(view, this.mVpa.mOldLayerType, null);
                    this.mVpa.mOldLayerType = -1;
                }
                if (this.mVpa.mEndAction != null) {
                    this.mVpa.mEndAction.run();
                }
                ViewPropertyAnimatorListener listenerTag = view.getTag(2113929216);
                ViewPropertyAnimatorListener listener = null;
                if (listenerTag instanceof ViewPropertyAnimatorListener) {
                    listener = listenerTag;
                }
                if (listener != null) {
                    listener.onAnimationEnd(view);
                }
            }

            public void onAnimationCancel(View view) {
                ViewPropertyAnimatorListener listenerTag = view.getTag(2113929216);
                ViewPropertyAnimatorListener listener = null;
                if (listenerTag instanceof ViewPropertyAnimatorListener) {
                    listener = listenerTag;
                }
                if (listener != null) {
                    listener.onAnimationCancel(view);
                }
            }
        }

        ICSViewPropertyAnimatorCompatImpl() {
            this.mLayerMap = null;
        }

        public void setDuration(ViewPropertyAnimatorCompat vpa, View view, long value) {
            ViewPropertyAnimatorCompatICS.setDuration(view, value);
        }

        public void alpha(ViewPropertyAnimatorCompat vpa, View view, float value) {
            ViewPropertyAnimatorCompatICS.alpha(view, value);
        }

        public void translationX(ViewPropertyAnimatorCompat vpa, View view, float value) {
            ViewPropertyAnimatorCompatICS.translationX(view, value);
        }

        public void translationY(ViewPropertyAnimatorCompat vpa, View view, float value) {
            ViewPropertyAnimatorCompatICS.translationY(view, value);
        }

        public void setInterpolator(ViewPropertyAnimatorCompat vpa, View view, Interpolator value) {
            ViewPropertyAnimatorCompatICS.setInterpolator(view, value);
        }

        public void scaleY(ViewPropertyAnimatorCompat vpa, View view, float value) {
            ViewPropertyAnimatorCompatICS.scaleY(view, value);
        }

        public void cancel(ViewPropertyAnimatorCompat vpa, View view) {
            ViewPropertyAnimatorCompatICS.cancel(view);
        }

        public void start(ViewPropertyAnimatorCompat vpa, View view) {
            ViewPropertyAnimatorCompatICS.start(view);
        }

        public void setListener(ViewPropertyAnimatorCompat vpa, View view, ViewPropertyAnimatorListener listener) {
            view.setTag(2113929216, listener);
            ViewPropertyAnimatorCompatICS.setListener(view, new MyVpaListener(vpa));
        }
    }

    static class JBViewPropertyAnimatorCompatImpl extends ICSViewPropertyAnimatorCompatImpl {
        JBViewPropertyAnimatorCompatImpl() {
        }

        public void setListener(ViewPropertyAnimatorCompat vpa, View view, ViewPropertyAnimatorListener listener) {
            ViewPropertyAnimatorCompatJB.setListener(view, listener);
        }
    }

    static class JBMr2ViewPropertyAnimatorCompatImpl extends JBViewPropertyAnimatorCompatImpl {
        JBMr2ViewPropertyAnimatorCompatImpl() {
        }
    }

    static class KitKatViewPropertyAnimatorCompatImpl extends JBMr2ViewPropertyAnimatorCompatImpl {
        KitKatViewPropertyAnimatorCompatImpl() {
        }

        public void setUpdateListener(ViewPropertyAnimatorCompat vpa, View view, ViewPropertyAnimatorUpdateListener listener) {
            ViewPropertyAnimatorCompatKK.setUpdateListener(view, listener);
        }
    }

    ViewPropertyAnimatorCompat(View view) {
        this.mStartAction = null;
        this.mEndAction = null;
        this.mOldLayerType = -1;
        this.mView = new WeakReference(view);
    }

    static {
        int version = VERSION.SDK_INT;
        if (version >= 19) {
            IMPL = new KitKatViewPropertyAnimatorCompatImpl();
        } else if (version >= 18) {
            IMPL = new JBMr2ViewPropertyAnimatorCompatImpl();
        } else if (version >= 16) {
            IMPL = new JBViewPropertyAnimatorCompatImpl();
        } else if (version >= 14) {
            IMPL = new ICSViewPropertyAnimatorCompatImpl();
        } else {
            IMPL = new BaseViewPropertyAnimatorCompatImpl();
        }
    }

    public ViewPropertyAnimatorCompat setDuration(long value) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.setDuration(this, view, value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alpha(float value) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.alpha(this, view, value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationX(float value) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.translationX(this, view, value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float value) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.translationY(this, view, value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator value) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.setInterpolator(this, view, value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float value) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.scaleY(this, view, value);
        }
        return this;
    }

    public void cancel() {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.cancel(this, view);
        }
    }

    public void start() {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.start(this, view);
        }
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener listener) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.setListener(this, view, listener);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener listener) {
        View view = (View) this.mView.get();
        if (view != null) {
            IMPL.setUpdateListener(this, view, listener);
        }
        return this;
    }
}
