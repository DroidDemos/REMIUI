package android.support.v4.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.v4.b.c;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.alipay.sdk.data.Response;
import com.alipay.sdk.protocol.WindowData;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewPager extends ViewGroup {
    private static final int[] jr;
    private static final Comparator jt;
    private static final Interpolator ju;
    private static final w kp;
    private ClassLoader jA;
    private E jB;
    private int jC;
    private Drawable jD;
    private int jE;
    private int jF;
    private float jG;
    private float jH;
    private int jI;
    private int jJ;
    private boolean jK;
    private int jL;
    private boolean jM;
    private boolean jN;
    private int jO;
    private int jP;
    private int jQ;
    private float jR;
    private float jS;
    private int jT;
    private VelocityTracker jU;
    private int jV;
    private int jW;
    private int jX;
    private int jY;
    private Rect jZ;
    private int js;
    private final U jv;
    private D jw;
    private int jx;
    private int jy;
    private Parcelable jz;
    private int ka;
    private int kb;
    private boolean kc;
    private c kd;
    private c ke;
    private boolean kf;
    private boolean kg;
    private boolean kh;
    private int ki;
    private af kj;
    private af kk;
    private G kl;
    private o km;
    private int kn;
    private ArrayList ko;
    private final Runnable kq;
    private int kr;
    private boolean mInLayout;
    private final ArrayList mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private final Rect mTempRect;

    static {
        jr = new int[]{16842931};
        jt = new y();
        ju = new x();
        kp = new w();
    }

    public ViewPager(Context context) {
        super(context);
        this.mItems = new ArrayList();
        this.jv = new U();
        this.mTempRect = new Rect();
        this.jy = -1;
        this.jz = null;
        this.jA = null;
        this.jG = -3.4028235E38f;
        this.jH = Float.MAX_VALUE;
        this.jL = 1;
        this.jT = -1;
        this.kb = -1;
        this.kf = true;
        this.kg = false;
        this.kq = new v(this);
        this.kr = 0;
        initViewPager();
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItems = new ArrayList();
        this.jv = new U();
        this.mTempRect = new Rect();
        this.jy = -1;
        this.jz = null;
        this.jA = null;
        this.jG = -3.4028235E38f;
        this.jH = Float.MAX_VALUE;
        this.jL = 1;
        this.jT = -1;
        this.kb = -1;
        this.kf = true;
        this.kg = false;
        this.kq = new v(this);
        this.kr = 0;
        initViewPager();
    }

    void initViewPager() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.mScroller = new Scroller(context, ju);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.jQ = O.a(viewConfiguration);
        this.jV = (int) (400.0f * f);
        this.jW = viewConfiguration.getScaledMaximumFlingVelocity();
        this.kd = new c(context);
        this.ke = new c(context);
        this.jX = (int) (25.0f * f);
        this.jY = (int) (2.0f * f);
        this.jO = (int) (16.0f * f);
        j.a((View) this, new J(this));
        if (j.c(this) == 0) {
            j.b(this, 1);
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.kq);
        super.onDetachedFromWindow();
    }

    private void i(int i) {
        if (this.kr != i) {
            this.kr = i;
            if (this.km != null) {
                h(i != 0);
            }
            if (this.kj != null) {
                this.kj.onPageScrollStateChanged(i);
            }
        }
    }

    public void a(D d) {
        if (this.jw != null) {
            this.jw.unregisterDataSetObserver(this.jB);
            this.jw.startUpdate((ViewGroup) this);
            for (int i = 0; i < this.mItems.size(); i++) {
                U u = (U) this.mItems.get(i);
                this.jw.destroyItem((ViewGroup) this, u.position, u.object);
            }
            this.jw.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            aV();
            this.jx = 0;
            scrollTo(0, 0);
        }
        D d2 = this.jw;
        this.jw = d;
        this.js = 0;
        if (this.jw != null) {
            if (this.jB == null) {
                this.jB = new E();
            }
            this.jw.registerDataSetObserver(this.jB);
            this.jK = false;
            boolean z = this.kf;
            this.kf = true;
            this.js = this.jw.getCount();
            if (this.jy >= 0) {
                this.jw.restoreState(this.jz, this.jA);
                setCurrentItemInternal(this.jy, false, true);
                this.jy = -1;
                this.jz = null;
                this.jA = null;
            } else if (z) {
                requestLayout();
            } else {
                populate();
            }
        }
        if (this.kl != null && d2 != d) {
            this.kl.a(d2, d);
        }
    }

    private void aV() {
        int i = 0;
        while (i < getChildCount()) {
            if (!((S) getChildAt(i).getLayoutParams()).isDecor) {
                removeViewAt(i);
                i--;
            }
            i++;
        }
    }

    public D aW() {
        return this.jw;
    }

    private int aX() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void setCurrentItem(int i) {
        boolean z;
        this.jK = false;
        if (this.kf) {
            z = false;
        } else {
            z = true;
        }
        setCurrentItemInternal(i, z, false);
    }

    public void setCurrentItem(int i, boolean z) {
        this.jK = false;
        setCurrentItemInternal(i, z, false);
    }

    public int getCurrentItem() {
        return this.jx;
    }

    void setCurrentItemInternal(int i, boolean z, boolean z2) {
        setCurrentItemInternal(i, z, z2, 0);
    }

    void setCurrentItemInternal(int i, boolean z, boolean z2, int i2) {
        boolean z3 = false;
        if (this.jw == null || this.jw.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z2 || this.jx != i || this.mItems.size() == 0) {
            if (i < 0) {
                i = 0;
            } else if (i >= this.jw.getCount()) {
                i = this.jw.getCount() - 1;
            }
            int i3 = this.jL;
            if (i > this.jx + i3 || i < this.jx - i3) {
                for (int i4 = 0; i4 < this.mItems.size(); i4++) {
                    ((U) this.mItems.get(i4)).scrolling = true;
                }
            }
            if (this.jx != i) {
                z3 = true;
            }
            if (this.kf) {
                this.jx = i;
                if (z3 && this.kj != null) {
                    this.kj.onPageSelected(i);
                }
                if (z3 && this.kk != null) {
                    this.kk.onPageSelected(i);
                }
                requestLayout();
                return;
            }
            populate(i);
            a(i, z, i2, z3);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    private void a(int i, boolean z, int i2, boolean z2) {
        int max;
        U j = j(i);
        if (j != null) {
            max = (int) (Math.max(this.jG, Math.min(j.offset, this.jH)) * ((float) aX()));
        } else {
            max = 0;
        }
        if (z) {
            if (z2 && this.kj != null) {
                this.kj.onPageSelected(i);
            }
            if (z2 && this.kk != null) {
                this.kk.onPageSelected(i);
            }
            smoothScrollTo(max, 0, i2);
            return;
        }
        if (z2 && this.kj != null) {
            this.kj.onPageSelected(i);
        }
        if (z2 && this.kk != null) {
            this.kk.onPageSelected(i);
        }
        g(false);
        scrollTo(max, 0);
        k(max);
    }

    public void a(af afVar) {
        this.kj = afVar;
    }

    protected int getChildDrawingOrder(int i, int i2) {
        if (this.kn == 2) {
            i2 = (i - 1) - i2;
        }
        return ((S) ((View) this.ko.get(i2)).getLayoutParams()).Dp;
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.jD;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.jD;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    void smoothScrollTo(int i, int i2, int i3) {
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i4 = i - scrollX;
        int i5 = i2 - scrollY;
        if (i4 == 0 && i5 == 0) {
            g(false);
            populate();
            i(0);
            return;
        }
        setScrollingCacheEnabled(true);
        i(2);
        int aX = aX();
        int i6 = aX / 2;
        float distanceInfluenceForSnapDuration = (((float) i6) * distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i4)) * 1.0f) / ((float) aX)))) + ((float) i6);
        int abs = Math.abs(i3);
        if (abs > 0) {
            aX = Math.round(1000.0f * Math.abs(distanceInfluenceForSnapDuration / ((float) abs))) * 4;
        } else {
            aX = (int) (((((float) Math.abs(i4)) / ((((float) aX) * this.jw.getPageWidth(this.jx)) + ((float) this.jC))) + 1.0f) * 100.0f);
        }
        this.mScroller.startScroll(scrollX, scrollY, i4, i5, Math.min(aX, 600));
        j.b(this);
    }

    U b(int i, int i2) {
        U u = new U();
        u.position = i;
        u.object = this.jw.instantiateItem((ViewGroup) this, i);
        u.widthFactor = this.jw.getPageWidth(i);
        if (i2 < 0 || i2 >= this.mItems.size()) {
            this.mItems.add(u);
        } else {
            this.mItems.add(i2, u);
        }
        return u;
    }

    void dataSetChanged() {
        int i;
        int count = this.jw.getCount();
        this.js = count;
        boolean z = this.mItems.size() < (this.jL * 2) + 1 && this.mItems.size() < count;
        boolean z2 = false;
        int i2 = this.jx;
        boolean z3 = z;
        int i3 = 0;
        while (i3 < this.mItems.size()) {
            int i4;
            boolean z4;
            boolean z5;
            U u = (U) this.mItems.get(i3);
            int itemPosition = this.jw.getItemPosition(u.object);
            if (itemPosition == -1) {
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = z3;
            } else if (itemPosition == -2) {
                this.mItems.remove(i3);
                i3--;
                if (!z2) {
                    this.jw.startUpdate((ViewGroup) this);
                    z2 = true;
                }
                this.jw.destroyItem((ViewGroup) this, u.position, u.object);
                if (this.jx == u.position) {
                    i4 = i3;
                    z4 = z2;
                    i = Math.max(0, Math.min(this.jx, count - 1));
                    z5 = true;
                } else {
                    i4 = i3;
                    z4 = z2;
                    i = i2;
                    z5 = true;
                }
            } else if (u.position != itemPosition) {
                if (u.position == this.jx) {
                    i2 = itemPosition;
                }
                u.position = itemPosition;
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = true;
            } else {
                i4 = i3;
                z4 = z2;
                i = i2;
                z5 = z3;
            }
            z3 = z5;
            i2 = i;
            z2 = z4;
            i3 = i4 + 1;
        }
        if (z2) {
            this.jw.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, jt);
        if (z3) {
            i = getChildCount();
            for (i3 = 0; i3 < i; i3++) {
                S s = (S) getChildAt(i3).getLayoutParams();
                if (!s.isDecor) {
                    s.widthFactor = 0.0f;
                }
            }
            setCurrentItemInternal(i2, false, true);
            requestLayout();
        }
    }

    void populate() {
        populate(this.jx);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void populate(int r19) {
        /*
        r18 = this;
        r3 = 0;
        r2 = 2;
        r0 = r18;
        r4 = r0.jx;
        r0 = r19;
        if (r4 == r0) goto L_0x033f;
    L_0x000a:
        r0 = r18;
        r2 = r0.jx;
        r0 = r19;
        if (r2 >= r0) goto L_0x0030;
    L_0x0012:
        r2 = 66;
    L_0x0014:
        r0 = r18;
        r3 = r0.jx;
        r0 = r18;
        r3 = r0.j(r3);
        r0 = r19;
        r1 = r18;
        r1.jx = r0;
        r4 = r3;
        r3 = r2;
    L_0x0026:
        r0 = r18;
        r2 = r0.jw;
        if (r2 != 0) goto L_0x0033;
    L_0x002c:
        r18.aY();
    L_0x002f:
        return;
    L_0x0030:
        r2 = 17;
        goto L_0x0014;
    L_0x0033:
        r0 = r18;
        r2 = r0.jK;
        if (r2 == 0) goto L_0x003d;
    L_0x0039:
        r18.aY();
        goto L_0x002f;
    L_0x003d:
        r2 = r18.getWindowToken();
        if (r2 == 0) goto L_0x002f;
    L_0x0043:
        r0 = r18;
        r2 = r0.jw;
        r0 = r18;
        r2.startUpdate(r0);
        r0 = r18;
        r2 = r0.jL;
        r5 = 0;
        r0 = r18;
        r6 = r0.jx;
        r6 = r6 - r2;
        r11 = java.lang.Math.max(r5, r6);
        r0 = r18;
        r5 = r0.jw;
        r12 = r5.getCount();
        r5 = r12 + -1;
        r0 = r18;
        r6 = r0.jx;
        r2 = r2 + r6;
        r13 = java.lang.Math.min(r5, r2);
        r0 = r18;
        r2 = r0.js;
        if (r12 == r2) goto L_0x00da;
    L_0x0073:
        r2 = r18.getResources();	 Catch:{ NotFoundException -> 0x00d0 }
        r3 = r18.getId();	 Catch:{ NotFoundException -> 0x00d0 }
        r2 = r2.getResourceName(r3);	 Catch:{ NotFoundException -> 0x00d0 }
    L_0x007f:
        r3 = new java.lang.IllegalStateException;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: ";
        r4 = r4.append(r5);
        r0 = r18;
        r5 = r0.js;
        r4 = r4.append(r5);
        r5 = ", found: ";
        r4 = r4.append(r5);
        r4 = r4.append(r12);
        r5 = " Pager id: ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r4 = " Pager class: ";
        r2 = r2.append(r4);
        r4 = r18.getClass();
        r2 = r2.append(r4);
        r4 = " Problematic adapter: ";
        r2 = r2.append(r4);
        r0 = r18;
        r4 = r0.jw;
        r4 = r4.getClass();
        r2 = r2.append(r4);
        r2 = r2.toString();
        r3.<init>(r2);
        throw r3;
    L_0x00d0:
        r2 = move-exception;
        r2 = r18.getId();
        r2 = java.lang.Integer.toHexString(r2);
        goto L_0x007f;
    L_0x00da:
        r6 = 0;
        r2 = 0;
        r5 = r2;
    L_0x00dd:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.size();
        if (r5 >= r2) goto L_0x033c;
    L_0x00e7:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.U) r2;
        r7 = r2.position;
        r0 = r18;
        r8 = r0.jx;
        if (r7 < r8) goto L_0x01cf;
    L_0x00f9:
        r7 = r2.position;
        r0 = r18;
        r8 = r0.jx;
        if (r7 != r8) goto L_0x033c;
    L_0x0101:
        if (r2 != 0) goto L_0x0339;
    L_0x0103:
        if (r12 <= 0) goto L_0x0339;
    L_0x0105:
        r0 = r18;
        r2 = r0.jx;
        r0 = r18;
        r2 = r0.b(r2, r5);
        r10 = r2;
    L_0x0110:
        if (r10 == 0) goto L_0x0180;
    L_0x0112:
        r9 = 0;
        r8 = r5 + -1;
        if (r8 < 0) goto L_0x01d4;
    L_0x0117:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r8);
        r2 = (android.support.v4.view.U) r2;
    L_0x0121:
        r14 = r18.aX();
        if (r14 > 0) goto L_0x01d7;
    L_0x0127:
        r6 = 0;
    L_0x0128:
        r0 = r18;
        r7 = r0.jx;
        r7 = r7 + -1;
        r16 = r7;
        r7 = r9;
        r9 = r16;
        r17 = r8;
        r8 = r5;
        r5 = r17;
    L_0x0138:
        if (r9 < 0) goto L_0x0142;
    L_0x013a:
        r15 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1));
        if (r15 < 0) goto L_0x0216;
    L_0x013e:
        if (r9 >= r11) goto L_0x0216;
    L_0x0140:
        if (r2 != 0) goto L_0x01e6;
    L_0x0142:
        r6 = r10.widthFactor;
        r9 = r8 + 1;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x017b;
    L_0x014c:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.size();
        if (r9 >= r2) goto L_0x024c;
    L_0x0156:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r9);
        r2 = (android.support.v4.view.U) r2;
        r7 = r2;
    L_0x0161:
        if (r14 > 0) goto L_0x024f;
    L_0x0163:
        r2 = 0;
        r5 = r2;
    L_0x0165:
        r0 = r18;
        r2 = r0.jx;
        r2 = r2 + 1;
        r16 = r2;
        r2 = r7;
        r7 = r9;
        r9 = r16;
    L_0x0171:
        if (r9 >= r12) goto L_0x017b;
    L_0x0173:
        r11 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1));
        if (r11 < 0) goto L_0x029a;
    L_0x0177:
        if (r9 <= r13) goto L_0x029a;
    L_0x0179:
        if (r2 != 0) goto L_0x025c;
    L_0x017b:
        r0 = r18;
        r0.a(r10, r8, r4);
    L_0x0180:
        r0 = r18;
        r4 = r0.jw;
        r0 = r18;
        r5 = r0.jx;
        if (r10 == 0) goto L_0x02e8;
    L_0x018a:
        r2 = r10.object;
    L_0x018c:
        r0 = r18;
        r4.setPrimaryItem(r0, r5, r2);
        r0 = r18;
        r2 = r0.jw;
        r0 = r18;
        r2.finishUpdate(r0);
        r5 = r18.getChildCount();
        r2 = 0;
        r4 = r2;
    L_0x01a0:
        if (r4 >= r5) goto L_0x02eb;
    L_0x01a2:
        r0 = r18;
        r6 = r0.getChildAt(r4);
        r2 = r6.getLayoutParams();
        r2 = (android.support.v4.view.S) r2;
        r2.Dp = r4;
        r7 = r2.isDecor;
        if (r7 != 0) goto L_0x01cb;
    L_0x01b4:
        r7 = r2.widthFactor;
        r8 = 0;
        r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1));
        if (r7 != 0) goto L_0x01cb;
    L_0x01bb:
        r0 = r18;
        r6 = r0.d(r6);
        if (r6 == 0) goto L_0x01cb;
    L_0x01c3:
        r7 = r6.widthFactor;
        r2.widthFactor = r7;
        r6 = r6.position;
        r2.position = r6;
    L_0x01cb:
        r2 = r4 + 1;
        r4 = r2;
        goto L_0x01a0;
    L_0x01cf:
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x00dd;
    L_0x01d4:
        r2 = 0;
        goto L_0x0121;
    L_0x01d7:
        r6 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = r10.widthFactor;
        r6 = r6 - r7;
        r7 = r18.getPaddingLeft();
        r7 = (float) r7;
        r15 = (float) r14;
        r7 = r7 / r15;
        r6 = r6 + r7;
        goto L_0x0128;
    L_0x01e6:
        r15 = r2.position;
        if (r9 != r15) goto L_0x0210;
    L_0x01ea:
        r15 = r2.scrolling;
        if (r15 != 0) goto L_0x0210;
    L_0x01ee:
        r0 = r18;
        r15 = r0.mItems;
        r15.remove(r5);
        r0 = r18;
        r15 = r0.jw;
        r2 = r2.object;
        r0 = r18;
        r15.destroyItem(r0, r9, r2);
        r5 = r5 + -1;
        r8 = r8 + -1;
        if (r5 < 0) goto L_0x0214;
    L_0x0206:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.U) r2;
    L_0x0210:
        r9 = r9 + -1;
        goto L_0x0138;
    L_0x0214:
        r2 = 0;
        goto L_0x0210;
    L_0x0216:
        if (r2 == 0) goto L_0x0230;
    L_0x0218:
        r15 = r2.position;
        if (r9 != r15) goto L_0x0230;
    L_0x021c:
        r2 = r2.widthFactor;
        r7 = r7 + r2;
        r5 = r5 + -1;
        if (r5 < 0) goto L_0x022e;
    L_0x0223:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.U) r2;
        goto L_0x0210;
    L_0x022e:
        r2 = 0;
        goto L_0x0210;
    L_0x0230:
        r2 = r5 + 1;
        r0 = r18;
        r2 = r0.b(r9, r2);
        r2 = r2.widthFactor;
        r7 = r7 + r2;
        r8 = r8 + 1;
        if (r5 < 0) goto L_0x024a;
    L_0x023f:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r5);
        r2 = (android.support.v4.view.U) r2;
        goto L_0x0210;
    L_0x024a:
        r2 = 0;
        goto L_0x0210;
    L_0x024c:
        r7 = 0;
        goto L_0x0161;
    L_0x024f:
        r2 = r18.getPaddingRight();
        r2 = (float) r2;
        r5 = (float) r14;
        r2 = r2 / r5;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 + r5;
        r5 = r2;
        goto L_0x0165;
    L_0x025c:
        r11 = r2.position;
        if (r9 != r11) goto L_0x0332;
    L_0x0260:
        r11 = r2.scrolling;
        if (r11 != 0) goto L_0x0332;
    L_0x0264:
        r0 = r18;
        r11 = r0.mItems;
        r11.remove(r7);
        r0 = r18;
        r11 = r0.jw;
        r2 = r2.object;
        r0 = r18;
        r11.destroyItem(r0, r9, r2);
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x0298;
    L_0x0280:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.U) r2;
    L_0x028a:
        r16 = r6;
        r6 = r2;
        r2 = r16;
    L_0x028f:
        r9 = r9 + 1;
        r16 = r2;
        r2 = r6;
        r6 = r16;
        goto L_0x0171;
    L_0x0298:
        r2 = 0;
        goto L_0x028a;
    L_0x029a:
        if (r2 == 0) goto L_0x02c1;
    L_0x029c:
        r11 = r2.position;
        if (r9 != r11) goto L_0x02c1;
    L_0x02a0:
        r2 = r2.widthFactor;
        r6 = r6 + r2;
        r7 = r7 + 1;
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02bf;
    L_0x02af:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.U) r2;
    L_0x02b9:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02bf:
        r2 = 0;
        goto L_0x02b9;
    L_0x02c1:
        r0 = r18;
        r2 = r0.b(r9, r7);
        r7 = r7 + 1;
        r2 = r2.widthFactor;
        r6 = r6 + r2;
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.size();
        if (r7 >= r2) goto L_0x02e6;
    L_0x02d6:
        r0 = r18;
        r2 = r0.mItems;
        r2 = r2.get(r7);
        r2 = (android.support.v4.view.U) r2;
    L_0x02e0:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x02e6:
        r2 = 0;
        goto L_0x02e0;
    L_0x02e8:
        r2 = 0;
        goto L_0x018c;
    L_0x02eb:
        r18.aY();
        r2 = r18.hasFocus();
        if (r2 == 0) goto L_0x002f;
    L_0x02f4:
        r2 = r18.findFocus();
        if (r2 == 0) goto L_0x0330;
    L_0x02fa:
        r0 = r18;
        r2 = r0.e(r2);
    L_0x0300:
        if (r2 == 0) goto L_0x030a;
    L_0x0302:
        r2 = r2.position;
        r0 = r18;
        r4 = r0.jx;
        if (r2 == r4) goto L_0x002f;
    L_0x030a:
        r2 = 0;
    L_0x030b:
        r4 = r18.getChildCount();
        if (r2 >= r4) goto L_0x002f;
    L_0x0311:
        r0 = r18;
        r4 = r0.getChildAt(r2);
        r0 = r18;
        r5 = r0.d(r4);
        if (r5 == 0) goto L_0x032d;
    L_0x031f:
        r5 = r5.position;
        r0 = r18;
        r6 = r0.jx;
        if (r5 != r6) goto L_0x032d;
    L_0x0327:
        r4 = r4.requestFocus(r3);
        if (r4 != 0) goto L_0x002f;
    L_0x032d:
        r2 = r2 + 1;
        goto L_0x030b;
    L_0x0330:
        r2 = 0;
        goto L_0x0300;
    L_0x0332:
        r16 = r6;
        r6 = r2;
        r2 = r16;
        goto L_0x028f;
    L_0x0339:
        r10 = r2;
        goto L_0x0110;
    L_0x033c:
        r2 = r6;
        goto L_0x0101;
    L_0x033f:
        r4 = r3;
        r3 = r2;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.view.ViewPager.populate(int):void");
    }

    private void aY() {
        if (this.kn != 0) {
            if (this.ko == null) {
                this.ko = new ArrayList();
            } else {
                this.ko.clear();
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                this.ko.add(getChildAt(i));
            }
            Collections.sort(this.ko, kp);
        }
    }

    private void a(U u, int i, U u2) {
        float f;
        float f2;
        int i2;
        U u3;
        int i3;
        int count = this.jw.getCount();
        int aX = aX();
        if (aX > 0) {
            f = ((float) this.jC) / ((float) aX);
        } else {
            f = 0.0f;
        }
        if (u2 != null) {
            aX = u2.position;
            int i4;
            if (aX < u.position) {
                f2 = (u2.offset + u2.widthFactor) + f;
                i4 = aX + 1;
                i2 = 0;
                while (i4 <= u.position && i2 < this.mItems.size()) {
                    u3 = (U) this.mItems.get(i2);
                    while (i4 > u3.position && i2 < this.mItems.size() - 1) {
                        i2++;
                        u3 = (U) this.mItems.get(i2);
                    }
                    while (i4 < u3.position) {
                        f2 += this.jw.getPageWidth(i4) + f;
                        i4++;
                    }
                    u3.offset = f2;
                    f2 += u3.widthFactor + f;
                    i4++;
                }
            } else if (aX > u.position) {
                i2 = this.mItems.size() - 1;
                f2 = u2.offset;
                i4 = aX - 1;
                while (i4 >= u.position && i2 >= 0) {
                    u3 = (U) this.mItems.get(i2);
                    while (i4 < u3.position && i2 > 0) {
                        i2--;
                        u3 = (U) this.mItems.get(i2);
                    }
                    while (i4 > u3.position) {
                        f2 -= this.jw.getPageWidth(i4) + f;
                        i4--;
                    }
                    f2 -= u3.widthFactor + f;
                    u3.offset = f2;
                    i4--;
                }
            }
        }
        int size = this.mItems.size();
        float f3 = u.offset;
        i2 = u.position - 1;
        this.jG = u.position == 0 ? u.offset : -3.4028235E38f;
        this.jH = u.position == count + -1 ? (u.offset + u.widthFactor) - 1.0f : Float.MAX_VALUE;
        for (i3 = i - 1; i3 >= 0; i3--) {
            u3 = (U) this.mItems.get(i3);
            f2 = f3;
            while (i2 > u3.position) {
                f2 -= this.jw.getPageWidth(i2) + f;
                i2--;
            }
            f3 = f2 - (u3.widthFactor + f);
            u3.offset = f3;
            if (u3.position == 0) {
                this.jG = f3;
            }
            i2--;
        }
        f3 = (u.offset + u.widthFactor) + f;
        i2 = u.position + 1;
        for (i3 = i + 1; i3 < size; i3++) {
            u3 = (U) this.mItems.get(i3);
            f2 = f3;
            while (i2 < u3.position) {
                f2 = (this.jw.getPageWidth(i2) + f) + f2;
                i2++;
            }
            if (u3.position == count - 1) {
                this.jH = (u3.widthFactor + f2) - 1.0f;
            }
            u3.offset = f2;
            f3 = f2 + (u3.widthFactor + f);
            i2++;
        }
        this.kg = false;
    }

    public Parcelable onSaveInstanceState() {
        Parcelable c = new C(super.onSaveInstanceState());
        c.position = this.jx;
        if (this.jw != null) {
            c.adapterState = this.jw.saveState();
        }
        return c;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof C) {
            C c = (C) parcelable;
            super.onRestoreInstanceState(c.getSuperState());
            if (this.jw != null) {
                this.jw.restoreState(c.adapterState, c.loader);
                setCurrentItemInternal(c.position, false, true);
                return;
            }
            this.jy = c.position;
            this.jz = c.adapterState;
            this.jA = c.loader;
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        if (checkLayoutParams(layoutParams)) {
            layoutParams2 = layoutParams;
        } else {
            layoutParams2 = generateLayoutParams(layoutParams);
        }
        S s = (S) layoutParams2;
        s.isDecor |= view instanceof A;
        if (!this.mInLayout) {
            super.addView(view, i, layoutParams2);
        } else if (s == null || !s.isDecor) {
            s.needsMeasure = true;
            addViewInLayout(view, i, layoutParams2);
        } else {
            throw new IllegalStateException("Cannot add pager decor view during layout");
        }
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    U d(View view) {
        for (int i = 0; i < this.mItems.size(); i++) {
            U u = (U) this.mItems.get(i);
            if (this.jw.isViewFromObject(view, u.object)) {
                return u;
            }
        }
        return null;
    }

    U e(View view) {
        while (true) {
            ViewPager parent = view.getParent();
            if (parent == this) {
                return d(view);
            }
            if (parent != null && (parent instanceof View)) {
                view = parent;
            }
        }
        return null;
    }

    U j(int i) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            U u = (U) this.mItems.get(i2);
            if (u.position == i) {
                return u;
            }
        }
        return null;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.kf = true;
    }

    protected void onMeasure(int i, int i2) {
        S s;
        int i3;
        setMeasuredDimension(getDefaultSize(0, i), getDefaultSize(0, i2));
        int measuredWidth = getMeasuredWidth();
        this.jP = Math.min(measuredWidth / 10, this.jO);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            int i5;
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                s = (S) childAt.getLayoutParams();
                if (s != null && s.isDecor) {
                    int i6 = s.gravity & 7;
                    int i7 = s.gravity & 112;
                    i3 = Integer.MIN_VALUE;
                    i5 = Integer.MIN_VALUE;
                    Object obj = (i7 == 48 || i7 == 80) ? 1 : null;
                    Object obj2 = (i6 == 3 || i6 == 5) ? 1 : null;
                    if (obj != null) {
                        i3 = 1073741824;
                    } else if (obj2 != null) {
                        i5 = 1073741824;
                    }
                    if (s.width != -2) {
                        i7 = 1073741824;
                        i3 = s.width != -1 ? s.width : paddingLeft;
                    } else {
                        i7 = i3;
                        i3 = paddingLeft;
                    }
                    if (s.height != -2) {
                        i5 = 1073741824;
                        if (s.height != -1) {
                            measuredWidth = s.height;
                            childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i5));
                            if (obj != null) {
                                measuredHeight -= childAt.getMeasuredHeight();
                            } else if (obj2 != null) {
                                paddingLeft -= childAt.getMeasuredWidth();
                            }
                        }
                    }
                    measuredWidth = measuredHeight;
                    childAt.measure(MeasureSpec.makeMeasureSpec(i3, i7), MeasureSpec.makeMeasureSpec(measuredWidth, i5));
                    if (obj != null) {
                        measuredHeight -= childAt.getMeasuredHeight();
                    } else if (obj2 != null) {
                        paddingLeft -= childAt.getMeasuredWidth();
                    }
                }
            }
        }
        this.jI = MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        this.jJ = MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        i3 = getChildCount();
        for (i5 = 0; i5 < i3; i5++) {
            View childAt2 = getChildAt(i5);
            if (childAt2.getVisibility() != 8) {
                s = (S) childAt2.getLayoutParams();
                if (s == null || !s.isDecor) {
                    childAt2.measure(MeasureSpec.makeMeasureSpec((int) (s.widthFactor * ((float) paddingLeft)), 1073741824), this.jJ);
                }
            }
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3) {
            a(i, i3, this.jC, this.jC);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        if (i2 <= 0 || this.mItems.isEmpty()) {
            U j = j(this.jx);
            int min = (int) ((j != null ? Math.min(j.offset, this.jH) : 0.0f) * ((float) ((i - getPaddingLeft()) - getPaddingRight())));
            if (min != getScrollX()) {
                g(false);
                scrollTo(min, getScrollY());
                return;
            }
            return;
        }
        int paddingLeft = (int) (((float) (((i - getPaddingLeft()) - getPaddingRight()) + i3)) * (((float) getScrollX()) / ((float) (((i2 - getPaddingLeft()) - getPaddingRight()) + i4))));
        scrollTo(paddingLeft, getScrollY());
        if (!this.mScroller.isFinished()) {
            this.mScroller.startScroll(paddingLeft, 0, (int) (j(this.jx).offset * ((float) i)), 0, this.mScroller.getDuration() - this.mScroller.timePassed());
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int max;
        int childCount = getChildCount();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int scrollX = getScrollX();
        int i7 = 0;
        int i8 = 0;
        while (i8 < childCount) {
            S s;
            int measuredWidth;
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                s = (S) childAt.getLayoutParams();
                if (s.isDecor) {
                    int i9 = s.gravity & 112;
                    switch (s.gravity & 7) {
                        case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                            max = Math.max((i5 - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            break;
                        case WindowData.d /*3*/:
                            max = paddingLeft;
                            paddingLeft = childAt.getMeasuredWidth() + paddingLeft;
                            break;
                        case WindowData.f /*5*/:
                            measuredWidth = (i5 - paddingRight) - childAt.getMeasuredWidth();
                            paddingRight += childAt.getMeasuredWidth();
                            max = measuredWidth;
                            break;
                        default:
                            max = paddingLeft;
                            break;
                    }
                    int i10;
                    switch (i9) {
                        case Base64.NO_CLOSE /*16*/:
                            measuredWidth = Math.max((i6 - childAt.getMeasuredHeight()) / 2, paddingTop);
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        case 48:
                            measuredWidth = childAt.getMeasuredHeight() + paddingTop;
                            i10 = paddingTop;
                            paddingTop = paddingBottom;
                            paddingBottom = measuredWidth;
                            measuredWidth = i10;
                            break;
                        case 80:
                            measuredWidth = (i6 - paddingBottom) - childAt.getMeasuredHeight();
                            i10 = paddingBottom + childAt.getMeasuredHeight();
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                        default:
                            measuredWidth = paddingTop;
                            i10 = paddingBottom;
                            paddingBottom = paddingTop;
                            paddingTop = i10;
                            break;
                    }
                    max += scrollX;
                    childAt.layout(max, measuredWidth, childAt.getMeasuredWidth() + max, childAt.getMeasuredHeight() + measuredWidth);
                    measuredWidth = i7 + 1;
                    i7 = paddingBottom;
                    paddingBottom = paddingTop;
                    paddingTop = paddingRight;
                    paddingRight = paddingLeft;
                    i8++;
                    paddingLeft = paddingRight;
                    paddingRight = paddingTop;
                    paddingTop = i7;
                    i7 = measuredWidth;
                }
            }
            measuredWidth = i7;
            i7 = paddingTop;
            paddingTop = paddingRight;
            paddingRight = paddingLeft;
            i8++;
            paddingLeft = paddingRight;
            paddingRight = paddingTop;
            paddingTop = i7;
            i7 = measuredWidth;
        }
        max = (i5 - paddingLeft) - paddingRight;
        for (paddingRight = 0; paddingRight < childCount; paddingRight++) {
            View childAt2 = getChildAt(paddingRight);
            if (childAt2.getVisibility() != 8) {
                s = (S) childAt2.getLayoutParams();
                if (!s.isDecor) {
                    U d = d(childAt2);
                    if (d != null) {
                        i5 = ((int) (d.offset * ((float) max))) + paddingLeft;
                        if (s.needsMeasure) {
                            s.needsMeasure = false;
                            childAt2.measure(MeasureSpec.makeMeasureSpec((int) (s.widthFactor * ((float) max)), 1073741824), MeasureSpec.makeMeasureSpec((i6 - paddingTop) - paddingBottom, 1073741824));
                        }
                        childAt2.layout(i5, paddingTop, childAt2.getMeasuredWidth() + i5, childAt2.getMeasuredHeight() + paddingTop);
                    }
                }
            }
        }
        this.jE = paddingTop;
        this.jF = i6 - paddingBottom;
        this.ki = i7;
        if (this.kf) {
            a(this.jx, false, 0, false);
        }
        this.kf = false;
    }

    public void computeScroll() {
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            g(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!k(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        j.b(this);
    }

    private boolean k(int i) {
        if (this.mItems.size() == 0) {
            this.kh = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.kh) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        U aZ = aZ();
        int aX = aX();
        int i2 = this.jC + aX;
        float f = ((float) this.jC) / ((float) aX);
        int i3 = aZ.position;
        float f2 = ((((float) i) / ((float) aX)) - aZ.offset) / (aZ.widthFactor + f);
        aX = (int) (((float) i2) * f2);
        this.kh = false;
        onPageScrolled(i3, f2, aX);
        if (this.kh) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    protected void onPageScrolled(int i, float f, int i2) {
        int paddingLeft;
        int paddingRight;
        int i3;
        if (this.ki > 0) {
            int scrollX = getScrollX();
            paddingLeft = getPaddingLeft();
            paddingRight = getPaddingRight();
            int width = getWidth();
            int childCount = getChildCount();
            i3 = 0;
            while (i3 < childCount) {
                int i4;
                View childAt = getChildAt(i3);
                S s = (S) childAt.getLayoutParams();
                if (s.isDecor) {
                    int max;
                    switch (s.gravity & 7) {
                        case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                            max = Math.max((width - childAt.getMeasuredWidth()) / 2, paddingLeft);
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        case WindowData.d /*3*/:
                            max = childAt.getWidth() + paddingLeft;
                            i4 = paddingLeft;
                            paddingLeft = paddingRight;
                            paddingRight = max;
                            max = i4;
                            break;
                        case WindowData.f /*5*/:
                            max = (width - paddingRight) - childAt.getMeasuredWidth();
                            i4 = paddingRight + childAt.getMeasuredWidth();
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                        default:
                            max = paddingLeft;
                            i4 = paddingRight;
                            paddingRight = paddingLeft;
                            paddingLeft = i4;
                            break;
                    }
                    max = (max + scrollX) - childAt.getLeft();
                    if (max != 0) {
                        childAt.offsetLeftAndRight(max);
                    }
                } else {
                    i4 = paddingRight;
                    paddingRight = paddingLeft;
                    paddingLeft = i4;
                }
                i3++;
                i4 = paddingLeft;
                paddingLeft = paddingRight;
                paddingRight = i4;
            }
        }
        if (this.kj != null) {
            this.kj.onPageScrolled(i, f, i2);
        }
        if (this.kk != null) {
            this.kk.onPageScrolled(i, f, i2);
        }
        if (this.km != null) {
            paddingRight = getScrollX();
            i3 = getChildCount();
            for (paddingLeft = 0; paddingLeft < i3; paddingLeft++) {
                View childAt2 = getChildAt(paddingLeft);
                if (!((S) childAt2.getLayoutParams()).isDecor) {
                    this.km.a(childAt2, ((float) (childAt2.getLeft() - paddingRight)) / ((float) aX()));
                }
            }
        }
        this.kh = true;
    }

    private void g(boolean z) {
        int scrollX;
        boolean z2 = this.kr == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.mScroller.abortAnimation();
            scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                scrollTo(currX, currY);
            }
        }
        this.jK = false;
        boolean z3 = z2;
        for (scrollX = 0; scrollX < this.mItems.size(); scrollX++) {
            U u = (U) this.mItems.get(scrollX);
            if (u.scrolling) {
                u.scrolling = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            j.a((View) this, this.kq);
        } else {
            this.kq.run();
        }
    }

    private boolean a(float f, float f2) {
        return (f < ((float) this.jP) && f2 > 0.0f) || (f > ((float) (getWidth() - this.jP)) && f2 < 0.0f);
    }

    private void h(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int i2;
            if (z) {
                i2 = 2;
            } else {
                i2 = 0;
            }
            j.a(getChildAt(i), i2, null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.jM = false;
            this.jN = false;
            this.jT = -1;
            if (this.jU == null) {
                return false;
            }
            this.jU.recycle();
            this.jU = null;
            return false;
        }
        if (action != 0) {
            if (this.jM) {
                return true;
            }
            if (this.jN) {
                return false;
            }
        }
        switch (action) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                float x = motionEvent.getX();
                this.jR = x;
                this.mLastMotionX = x;
                x = motionEvent.getY();
                this.jS = x;
                this.mLastMotionY = x;
                this.jT = ah.b(motionEvent, 0);
                this.jN = false;
                this.mScroller.computeScrollOffset();
                if (this.kr == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.jY) {
                    this.mScroller.abortAnimation();
                    this.jK = false;
                    populate();
                    this.jM = true;
                    i(true);
                    i(1);
                    break;
                }
                g(false);
                this.jM = false;
                break;
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                action = this.jT;
                if (action != -1) {
                    int a = ah.a(motionEvent, action);
                    if (a == -1) {
                        Log.e("ViewPager", "Invalid pointerId=" + action + " in onInterceptTouchEvent ACTION_MOVE");
                        break;
                    }
                    float c = ah.c(motionEvent, a);
                    float f = c - this.mLastMotionX;
                    float abs = Math.abs(f);
                    float d = ah.d(motionEvent, a);
                    float abs2 = Math.abs(d - this.jS);
                    if (f != 0.0f && !a(this.mLastMotionX, f) && canScroll(this, false, (int) f, (int) c, (int) d)) {
                        this.mLastMotionX = c;
                        this.mLastMotionY = d;
                        this.jN = true;
                        return false;
                    } else if (abs >= ((float) this.kb) || abs2 >= ((float) this.kb) || !a(motionEvent, this.jZ)) {
                        if (abs > ((float) this.jQ) && 0.5f * abs > abs2) {
                            this.jM = true;
                            i(true);
                            i(1);
                            this.mLastMotionX = f > 0.0f ? this.jR + ((float) this.jQ) : this.jR - ((float) this.jQ);
                            this.mLastMotionY = d;
                            setScrollingCacheEnabled(true);
                        } else if (abs2 > ((float) this.jQ)) {
                            this.jN = true;
                        }
                        if (this.jM && b(c)) {
                            j.b(this);
                            break;
                        }
                    } else {
                        Log.d("ViewPager", "xDiff = " + abs + ", yDiff = " + abs2 + ", mTouchSlopAdj = " + this.kb);
                        return false;
                    }
                }
                break;
            case WindowData.g /*6*/:
                a(motionEvent);
                break;
        }
        if (this.jU == null) {
            this.jU = VelocityTracker.obtain();
        }
        this.jU.addMovement(motionEvent);
        return this.jM;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (this.kc) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        if (this.jw == null || this.jw.getCount() == 0) {
            return false;
        }
        if (this.jU == null) {
            this.jU = VelocityTracker.obtain();
        }
        this.jU.addMovement(motionEvent);
        float x;
        int a;
        switch (motionEvent.getAction() & 255) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                this.mScroller.abortAnimation();
                this.jK = false;
                populate();
                x = motionEvent.getX();
                this.jR = x;
                this.mLastMotionX = x;
                x = motionEvent.getY();
                this.jS = x;
                this.mLastMotionY = x;
                this.jT = ah.b(motionEvent, 0);
                break;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                if (this.jM) {
                    VelocityTracker velocityTracker = this.jU;
                    velocityTracker.computeCurrentVelocity(Response.a, (float) this.jW);
                    a = (int) aj.a(velocityTracker, this.jT);
                    this.jK = true;
                    int aX = aX();
                    int scrollX = getScrollX();
                    U aZ = aZ();
                    setCurrentItemInternal(a(aZ.position, ((((float) scrollX) / ((float) aX)) - aZ.offset) / aZ.widthFactor, a, (int) (ah.c(motionEvent, ah.a(motionEvent, this.jT)) - this.jR)), true, true, a);
                    this.jT = -1;
                    ba();
                    z = this.ke.ha() | this.kd.ha();
                    break;
                }
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                if (!this.jM) {
                    a = ah.a(motionEvent, this.jT);
                    float c = ah.c(motionEvent, a);
                    float abs = Math.abs(c - this.mLastMotionX);
                    float d = ah.d(motionEvent, a);
                    x = Math.abs(d - this.mLastMotionY);
                    if (abs > ((float) this.jQ) && abs > x) {
                        this.jM = true;
                        i(true);
                        if (c - this.jR > 0.0f) {
                            x = this.jR + ((float) this.jQ);
                        } else {
                            x = this.jR - ((float) this.jQ);
                        }
                        this.mLastMotionX = x;
                        this.mLastMotionY = d;
                        i(1);
                        setScrollingCacheEnabled(true);
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
                if (this.jM) {
                    z = false | b(ah.c(motionEvent, ah.a(motionEvent, this.jT)));
                    break;
                }
                break;
            case WindowData.d /*3*/:
                if (this.jM) {
                    a(this.jx, true, 0, false);
                    this.jT = -1;
                    ba();
                    z = this.ke.ha() | this.kd.ha();
                    break;
                }
                break;
            case WindowData.f /*5*/:
                a = ah.b(motionEvent);
                this.mLastMotionX = ah.c(motionEvent, a);
                this.jT = ah.b(motionEvent, a);
                break;
            case WindowData.g /*6*/:
                a(motionEvent);
                this.mLastMotionX = ah.c(motionEvent, ah.a(motionEvent, this.jT));
                break;
        }
        if (z) {
            j.b(this);
        }
        return true;
    }

    private void i(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean b(float f) {
        boolean z;
        float f2;
        boolean z2 = true;
        boolean z3 = false;
        float f3 = this.mLastMotionX - f;
        this.mLastMotionX = f;
        float scrollX = ((float) getScrollX()) + f3;
        int aX = aX();
        float f4 = ((float) aX) * this.jG;
        float f5 = ((float) aX) * this.jH;
        U u = (U) this.mItems.get(0);
        U u2 = (U) this.mItems.get(this.mItems.size() - 1);
        if (u.position != 0) {
            f4 = u.offset * ((float) aX);
            z = false;
        } else {
            z = true;
        }
        if (u2.position != this.jw.getCount() - 1) {
            f2 = u2.offset * ((float) aX);
            z2 = false;
        } else {
            f2 = f5;
        }
        if (scrollX < f4) {
            if (z) {
                z3 = this.kd.e(Math.abs(f4 - scrollX) / ((float) aX));
            }
        } else if (scrollX > f2) {
            if (z2) {
                z3 = this.ke.e(Math.abs(scrollX - f2) / ((float) aX));
            }
            f4 = f2;
        } else {
            f4 = scrollX;
        }
        this.mLastMotionX += f4 - ((float) ((int) f4));
        scrollTo((int) f4, getScrollY());
        k((int) f4);
        return z3;
    }

    private U aZ() {
        float f;
        int aX = aX();
        float scrollX = aX > 0 ? ((float) getScrollX()) / ((float) aX) : 0.0f;
        if (aX > 0) {
            f = ((float) this.jC) / ((float) aX);
        } else {
            f = 0.0f;
        }
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i = -1;
        int i2 = 0;
        Object obj = 1;
        U u = null;
        while (i2 < this.mItems.size()) {
            int i3;
            U u2;
            U u3 = (U) this.mItems.get(i2);
            U u4;
            if (obj != null || u3.position == i + 1) {
                u4 = u3;
                i3 = i2;
                u2 = u4;
            } else {
                u3 = this.jv;
                u3.offset = (f2 + f3) + f;
                u3.position = i + 1;
                u3.widthFactor = this.jw.getPageWidth(u3.position);
                u4 = u3;
                i3 = i2 - 1;
                u2 = u4;
            }
            f2 = u2.offset;
            f3 = (u2.widthFactor + f2) + f;
            if (obj == null && scrollX < f2) {
                return u;
            }
            if (scrollX < f3 || i3 == this.mItems.size() - 1) {
                return u2;
            }
            f3 = f2;
            i = u2.position;
            obj = null;
            f2 = u2.widthFactor;
            u = u2;
            i2 = i3 + 1;
        }
        return u;
    }

    private int a(int i, float f, int i2, int i3) {
        if (Math.abs(i3) <= this.jX || Math.abs(i2) <= this.jV) {
            i = (int) ((i >= this.jx ? 0.4f : 0.6f) + (((float) i) + f));
        } else if (i2 <= 0) {
            i++;
        }
        if (this.mItems.size() <= 0) {
            return i;
        }
        return Math.max(((U) this.mItems.get(0)).position, Math.min(i, ((U) this.mItems.get(this.mItems.size() - 1)).position));
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int i = 0;
        int a = j.a(this);
        if (a == 0 || (a == 1 && this.jw != null && this.jw.getCount() > 1)) {
            int height;
            int width;
            if (!this.kd.isFinished()) {
                a = canvas.save();
                height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                width = getWidth();
                canvas.rotate(270.0f);
                canvas.translate((float) ((-height) + getPaddingTop()), this.jG * ((float) width));
                this.kd.setSize(height, width);
                i = 0 | this.kd.draw(canvas);
                canvas.restoreToCount(a);
            }
            if (!this.ke.isFinished()) {
                a = canvas.save();
                height = getWidth();
                width = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                canvas.translate((float) (-getPaddingTop()), (-(this.jH + 1.0f)) * ((float) height));
                this.ke.setSize(width, height);
                i |= this.ke.draw(canvas);
                canvas.restoreToCount(a);
            }
        } else {
            this.kd.finish();
            this.ke.finish();
        }
        if (i != 0) {
            j.b(this);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.jC > 0 && this.jD != null && this.mItems.size() > 0 && this.jw != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f = ((float) this.jC) / ((float) width);
            U u = (U) this.mItems.get(0);
            float f2 = u.offset;
            int size = this.mItems.size();
            int i = u.position;
            int i2 = ((U) this.mItems.get(size - 1)).position;
            int i3 = 0;
            int i4 = i;
            while (i4 < i2) {
                float f3;
                while (i4 > u.position && i3 < size) {
                    i3++;
                    u = (U) this.mItems.get(i3);
                }
                if (i4 == u.position) {
                    f3 = (u.offset + u.widthFactor) * ((float) width);
                    f2 = (u.offset + u.widthFactor) + f;
                } else {
                    float pageWidth = this.jw.getPageWidth(i4);
                    f3 = (f2 + pageWidth) * ((float) width);
                    f2 += pageWidth + f;
                }
                if (((float) this.jC) + f3 > ((float) scrollX)) {
                    this.jD.setBounds((int) f3, this.jE, (int) ((((float) this.jC) + f3) + 0.5f), this.jF);
                    this.jD.draw(canvas);
                }
                if (f3 <= ((float) (scrollX + width))) {
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    private void a(MotionEvent motionEvent) {
        int b = ah.b(motionEvent);
        if (ah.b(motionEvent, b) == this.jT) {
            b = b == 0 ? 1 : 0;
            this.mLastMotionX = ah.c(motionEvent, b);
            this.jT = ah.b(motionEvent, b);
            if (this.jU != null) {
                this.jU.clear();
            }
        }
    }

    private void ba() {
        this.jM = false;
        this.jN = false;
        if (this.jU != null) {
            this.jU.recycle();
            this.jU = null;
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    public boolean canScrollHorizontally(int i) {
        boolean z = true;
        if (this.jw == null) {
            return false;
        }
        int aX = aX();
        int scrollX = getScrollX();
        if (i < 0) {
            if (scrollX <= ((int) (((float) aX) * this.jG))) {
                z = false;
            }
            return z;
        } else if (i <= 0) {
            return false;
        } else {
            if (scrollX >= ((int) (((float) aX) * this.jH))) {
                z = false;
            }
            return z;
        }
    }

    protected boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i2 + scrollX >= childAt.getLeft() && i2 + scrollX < childAt.getRight() && i3 + scrollY >= childAt.getTop() && i3 + scrollY < childAt.getBottom()) {
                    if (canScroll(childAt, true, i, (i2 + scrollX) - childAt.getLeft(), (i3 + scrollY) - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (z && j.a(view, -i)) {
            return true;
        }
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        switch (keyEvent.getKeyCode()) {
            case 21:
                return arrowScroll(17);
            case 22:
                return arrowScroll(66);
            case 61:
                if (VERSION.SDK_INT < 11) {
                    return false;
                }
                if (n.a(keyEvent)) {
                    return arrowScroll(2);
                }
                if (n.a(keyEvent, 1)) {
                    return arrowScroll(1);
                }
                return false;
            default:
                return false;
        }
    }

    public boolean arrowScroll(int i) {
        View view;
        boolean pageLeft;
        View findFocus = findFocus();
        if (findFocus == this) {
            view = null;
        } else {
            if (findFocus != null) {
                Object obj;
                for (ViewPager parent = findFocus.getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
                    if (parent == this) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(findFocus.getClass().getSimpleName());
                    for (ViewParent parent2 = findFocus.getParent(); parent2 instanceof ViewGroup; parent2 = parent2.getParent()) {
                        stringBuilder.append(" => ").append(parent2.getClass().getSimpleName());
                    }
                    Log.e("ViewPager", "arrowScroll tried to find focus based on non-child current focused view " + stringBuilder.toString());
                    view = null;
                }
            }
            view = findFocus;
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(this, view, i);
        if (findNextFocus == null || findNextFocus == view) {
            if (i == 17 || i == 1) {
                pageLeft = pageLeft();
            } else {
                if (i == 66 || i == 2) {
                    pageLeft = pageRight();
                }
                pageLeft = false;
            }
        } else if (i == 17) {
            pageLeft = (view == null || a(this.mTempRect, findNextFocus).left < a(this.mTempRect, view).left) ? findNextFocus.requestFocus() : pageLeft();
        } else {
            if (i == 66) {
                pageLeft = (view == null || a(this.mTempRect, findNextFocus).left > a(this.mTempRect, view).left) ? findNextFocus.requestFocus() : pageRight();
            }
            pageLeft = false;
        }
        if (pageLeft) {
            playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
        }
        return pageLeft;
    }

    private Rect a(Rect rect, View view) {
        Rect rect2;
        if (rect == null) {
            rect2 = new Rect();
        } else {
            rect2 = rect;
        }
        if (view == null) {
            rect2.set(0, 0, 0, 0);
            return rect2;
        }
        rect2.left = view.getLeft();
        rect2.right = view.getRight();
        rect2.top = view.getTop();
        rect2.bottom = view.getBottom();
        ViewPager parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = parent;
            rect2.left += viewGroup.getLeft();
            rect2.right += viewGroup.getRight();
            rect2.top += viewGroup.getTop();
            rect2.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect2;
    }

    boolean pageLeft() {
        if (this.jx <= 0) {
            return false;
        }
        setCurrentItem(this.jx - 1, true);
        return true;
    }

    boolean pageRight() {
        if (this.jw == null || this.jx >= this.jw.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.jx + 1, true);
        return true;
    }

    public void addFocusables(ArrayList arrayList, int i, int i2) {
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() == 0) {
                    U d = d(childAt);
                    if (d != null && d.position == this.jx) {
                        childAt.addFocusables(arrayList, i, i2);
                    }
                }
            }
        }
        if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
            return;
        }
        if (((i2 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) && arrayList != null) {
            arrayList.add(this);
        }
    }

    public void addTouchables(ArrayList arrayList) {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                U d = d(childAt);
                if (d != null && d.position == this.jx) {
                    childAt.addTouchables(arrayList);
                }
            }
        }
    }

    protected boolean onRequestFocusInDescendants(int i, Rect rect) {
        int i2;
        int i3 = -1;
        int childCount = getChildCount();
        if ((i & 2) != 0) {
            i3 = 1;
            i2 = 0;
        } else {
            i2 = childCount - 1;
            childCount = -1;
        }
        while (i2 != childCount) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                U d = d(childAt);
                if (d != null && d.position == this.jx && childAt.requestFocus(i, rect)) {
                    return true;
                }
            }
            i2 += i3;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                U d = d(childAt);
                if (d != null && d.position == this.jx && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new S();
    }

    protected LayoutParams generateLayoutParams(LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    protected boolean checkLayoutParams(LayoutParams layoutParams) {
        return (layoutParams instanceof S) && super.checkLayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new S(getContext(), attributeSet);
    }

    private boolean a(MotionEvent motionEvent, Rect rect) {
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        Log.d("ViewPager", "pointInRect x = " + rawX + ", y = " + rawY + ", rect = " + rect);
        if (rect == null || this.jx != this.ka || rawX < ((float) rect.left) || rawX > ((float) rect.right) || rawY < ((float) rect.top) || rawY > ((float) rect.bottom)) {
            return false;
        }
        return true;
    }
}
