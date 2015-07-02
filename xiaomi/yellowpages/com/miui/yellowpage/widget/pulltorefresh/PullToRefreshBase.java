package com.miui.yellowpage.widget.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.a;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public abstract class PullToRefreshBase extends LinearLayout {
    private boolean jM;
    private int jQ;
    private float jR;
    private float jS;
    private float mLastMotionX;
    private float mLastMotionY;
    private int wL;
    private State wM;
    private Mode wN;
    private Mode wO;
    View wP;
    private FrameLayout wQ;
    private boolean wR;
    private boolean wS;
    private boolean wT;
    private boolean wU;
    private boolean wV;
    private Interpolator wW;
    private AnimationStyle wX;
    private k wY;
    private k wZ;
    private a xa;
    private c xb;
    private m xc;
    private j xd;

    public enum AnimationStyle {
        ROTATE,
        FLIP;

        static AnimationStyle gB() {
            return ROTATE;
        }

        static AnimationStyle P(int i) {
            switch (i) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    return FLIP;
                default:
                    return ROTATE;
            }
        }

        k a(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
            int i = e.kF[ordinal()];
            return new i(context, mode, orientation, typedArray);
        }
    }

    public enum Mode {
        DISABLED(0),
        PULL_FROM_START(1),
        PULL_FROM_END(2),
        BOTH(3),
        MANUAL_REFRESH_ONLY(4);
        
        public static Mode uS;
        public static Mode uT;
        private int mIntValue;

        static {
            uS = PULL_FROM_START;
            uT = PULL_FROM_END;
        }

        static Mode H(int i) {
            for (Mode mode : values()) {
                if (i == mode.getIntValue()) {
                    return mode;
                }
            }
            return eM();
        }

        static Mode eM() {
            return PULL_FROM_START;
        }

        private Mode(int i) {
            this.mIntValue = i;
        }

        boolean eN() {
            return (this == DISABLED || this == MANUAL_REFRESH_ONLY) ? false : true;
        }

        public boolean eO() {
            return this == PULL_FROM_START || this == BOTH;
        }

        public boolean eP() {
            return this == PULL_FROM_END || this == BOTH || this == MANUAL_REFRESH_ONLY;
        }

        int getIntValue() {
            return this.mIntValue;
        }
    }

    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    public enum State {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9),
        OVERSCROLLING(16);
        
        private int mIntValue;

        static State V(int i) {
            for (State state : values()) {
                if (i == state.getIntValue()) {
                    return state;
                }
            }
            return RESET;
        }

        private State(int i) {
            this.mIntValue = i;
        }

        int getIntValue() {
            return this.mIntValue;
        }
    }

    protected abstract View a(Context context, AttributeSet attributeSet);

    protected abstract boolean fA();

    protected abstract boolean fB();

    public abstract Orientation fw();

    public PullToRefreshBase(Context context) {
        super(context);
        this.jM = false;
        this.wM = State.RESET;
        this.wN = Mode.eM();
        this.wR = true;
        this.wS = false;
        this.wT = true;
        this.wU = true;
        this.wV = true;
        this.wX = AnimationStyle.gB();
        b(context, null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.jM = false;
        this.wM = State.RESET;
        this.wN = Mode.eM();
        this.wR = true;
        this.wS = false;
        this.wT = true;
        this.wU = true;
        this.wV = true;
        this.wX = AnimationStyle.gB();
        b(context, attributeSet);
    }

    public PullToRefreshBase(Context context, Mode mode) {
        super(context);
        this.jM = false;
        this.wM = State.RESET;
        this.wN = Mode.eM();
        this.wR = true;
        this.wS = false;
        this.wT = true;
        this.wU = true;
        this.wV = true;
        this.wX = AnimationStyle.gB();
        this.wN = mode;
        b(context, null);
    }

    public PullToRefreshBase(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context);
        this.jM = false;
        this.wM = State.RESET;
        this.wN = Mode.eM();
        this.wR = true;
        this.wS = false;
        this.wT = true;
        this.wU = true;
        this.wV = true;
        this.wX = AnimationStyle.gB();
        this.wN = mode;
        this.wX = animationStyle;
        b(context, null);
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        View fs = fs();
        if (fs instanceof ViewGroup) {
            ((ViewGroup) fs).addView(view, i, layoutParams);
            return;
        }
        throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
    }

    public final View fs() {
        return this.wP;
    }

    public final boolean ft() {
        return this.wN.eN();
    }

    public final boolean fu() {
        return this.wM == State.REFRESHING || this.wM == State.MANUAL_REFRESHING;
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!ft()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.jM = false;
            return false;
        } else if (action != 0 && this.jM) {
            return true;
        } else {
            float y;
            switch (action) {
                case TransactionXMLFile.MODE_PRIVATE /*0*/:
                    if (fH()) {
                        y = motionEvent.getY();
                        this.jS = y;
                        this.mLastMotionY = y;
                        y = motionEvent.getX();
                        this.jR = y;
                        this.mLastMotionX = y;
                        this.jM = false;
                        break;
                    }
                    break;
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    if (fH()) {
                        float y2 = motionEvent.getY();
                        float x = motionEvent.getX();
                        float f;
                        switch (e.gc[fw().ordinal()]) {
                            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                                y = x - this.mLastMotionX;
                                f = y2 - this.mLastMotionY;
                                break;
                            default:
                                y = y2 - this.mLastMotionY;
                                f = x - this.mLastMotionX;
                                break;
                        }
                        float abs = Math.abs(y);
                        if (abs > ((float) this.jQ) && (!this.wT || abs > Math.abs(r0))) {
                            if (!this.wN.eO() || y < 1.0f || !fB()) {
                                if (this.wN.eP() && y <= -1.0f && fA()) {
                                    this.mLastMotionY = y2;
                                    this.mLastMotionX = x;
                                    this.jM = true;
                                    if (this.wN == Mode.BOTH) {
                                        this.wO = Mode.PULL_FROM_END;
                                        break;
                                    }
                                }
                            }
                            this.mLastMotionY = y2;
                            this.mLastMotionX = x;
                            this.jM = true;
                            if (this.wN == Mode.BOTH) {
                                this.wO = Mode.PULL_FROM_START;
                                break;
                            }
                        }
                    }
                    break;
            }
            return this.jM;
        }
    }

    public final void fv() {
        if (fu()) {
            a(State.RESET, new boolean[0]);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!ft()) {
            return false;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                if (!fH()) {
                    return false;
                }
                float y = motionEvent.getY();
                this.jS = y;
                this.mLastMotionY = y;
                y = motionEvent.getX();
                this.jR = y;
                this.mLastMotionX = y;
                return true;
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
            case WindowData.d /*3*/:
                if (!this.jM) {
                    return false;
                }
                this.jM = false;
                if (this.wM == State.RELEASE_TO_REFRESH && (this.xa != null || this.xb != null)) {
                    a(State.REFRESHING, true);
                    return true;
                } else if (fu()) {
                    L(0);
                    return true;
                } else {
                    a(State.RESET, new boolean[0]);
                    return true;
                }
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                if (!this.jM) {
                    return false;
                }
                this.mLastMotionY = motionEvent.getY();
                this.mLastMotionX = motionEvent.getX();
                fI();
                return true;
            default:
                return false;
        }
    }

    public void setLongClickable(boolean z) {
        fs().setLongClickable(z);
    }

    public final void a(Mode mode) {
        if (mode != this.wN) {
            this.wN = mode;
            fF();
        }
    }

    public final void a(a aVar) {
        this.xa = aVar;
        this.xb = null;
    }

    final void a(State state, boolean... zArr) {
        this.wM = state;
        switch (e.kE[this.wM.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                onReset();
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                fC();
                break;
            case WindowData.d /*3*/:
                fD();
                break;
            case Base64.CRLF /*4*/:
            case WindowData.f /*5*/:
                x(zArr[0]);
                break;
        }
        if (this.xc != null) {
            this.xc.a(this, this.wM, this.wO);
        }
    }

    protected final void a(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    protected final void a(View view, LayoutParams layoutParams) {
        super.addView(view, -1, layoutParams);
    }

    protected k a(Context context, Mode mode, TypedArray typedArray) {
        k a = this.wX.a(context, mode, fw(), typedArray);
        a.setVisibility(4);
        return a;
    }

    protected final int fx() {
        return this.wZ.gG();
    }

    protected final int fy() {
        return this.wY.gG();
    }

    protected int fz() {
        return ConfigConstant.RESPONSE_CODE;
    }

    protected void a(TypedArray typedArray) {
    }

    protected void d(Bundle bundle) {
    }

    protected void e(Bundle bundle) {
    }

    protected void fC() {
        switch (e.gd[this.wO.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.wZ.gH();
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                this.wY.gH();
                return;
            default:
                return;
        }
    }

    protected void x(boolean z) {
        if (this.wN.eO()) {
            this.wY.gI();
        }
        if (this.wN.eP()) {
            this.wZ.gI();
        }
        if (!z) {
            fG();
        } else if (this.wR) {
            h dVar = new d(this);
            switch (e.gd[this.wO.ordinal()]) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                case WindowData.d /*3*/:
                    a(fx(), dVar);
                    return;
                default:
                    a(-fy(), dVar);
                    return;
            }
        } else {
            L(0);
        }
    }

    protected void fD() {
        switch (e.gd[this.wO.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                this.wZ.gJ();
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                this.wY.gJ();
                return;
            default:
                return;
        }
    }

    protected void onReset() {
        this.jM = false;
        this.wV = true;
        this.wY.reset();
        this.wZ.reset();
        L(0);
    }

    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            a(Mode.H(bundle.getInt("ptr_mode", 0)));
            this.wO = Mode.H(bundle.getInt("ptr_current_mode", 0));
            this.wS = bundle.getBoolean("ptr_disable_scrolling", false);
            this.wR = bundle.getBoolean("ptr_show_refreshing_view", true);
            super.onRestoreInstanceState(bundle.getParcelable("ptr_super"));
            State V = State.V(bundle.getInt("ptr_state", 0));
            if (V == State.REFRESHING || V == State.MANUAL_REFRESHING) {
                a(V, true);
            }
            d(bundle);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected final Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        e(bundle);
        bundle.putInt("ptr_state", this.wM.getIntValue());
        bundle.putInt("ptr_mode", this.wN.getIntValue());
        bundle.putInt("ptr_current_mode", this.wO.getIntValue());
        bundle.putBoolean("ptr_disable_scrolling", this.wS);
        bundle.putBoolean("ptr_show_refreshing_view", this.wR);
        bundle.putParcelable("ptr_super", super.onSaveInstanceState());
        return bundle;
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        fE();
    }

    protected final void fE() {
        int i;
        int i2 = 0;
        int fK = (int) (((float) fK()) * 1.2f);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        switch (e.gc[fw().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                if (this.wN.eO()) {
                    this.wY.setWidth(fK);
                    i = -fK;
                } else {
                    i = 0;
                }
                if (!this.wN.eP()) {
                    paddingRight = i;
                    i = 0;
                    i2 = paddingBottom;
                    paddingBottom = paddingTop;
                    break;
                }
                this.wZ.setWidth(fK);
                paddingRight = i;
                i = -fK;
                i2 = paddingBottom;
                paddingBottom = paddingTop;
                break;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                if (this.wN.eO()) {
                    this.wY.setHeight(fK);
                    i = -fK;
                } else {
                    i = 0;
                }
                if (!this.wN.eP()) {
                    paddingBottom = i;
                    i = paddingRight;
                    paddingRight = paddingLeft;
                    break;
                }
                this.wZ.setHeight(fK);
                i2 = -fK;
                paddingBottom = i;
                i = paddingRight;
                paddingRight = paddingLeft;
                break;
            default:
                i2 = paddingBottom;
                i = paddingRight;
                paddingBottom = paddingTop;
                paddingRight = paddingLeft;
                break;
        }
        setPadding(paddingRight, paddingBottom, i, i2);
    }

    protected final void K(int i) {
        int fK = fK();
        fK = Math.min(fK, Math.max(-fK, i));
        if (this.wV) {
            if (fK < 0) {
                this.wY.setVisibility(0);
            } else if (fK > 0) {
                this.wZ.setVisibility(0);
            } else {
                this.wY.setVisibility(4);
                this.wZ.setVisibility(4);
            }
        }
        switch (e.gc[fw().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                scrollTo(fK, 0);
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                scrollTo(0, fK);
                return;
            default:
                return;
        }
    }

    protected final void L(int i) {
        a(i, (long) fz());
    }

    protected final void a(int i, h hVar) {
        a(i, (long) fz(), 0, hVar);
    }

    protected void fF() {
        LayoutParams fJ = fJ();
        if (this == this.wY.getParent()) {
            removeView(this.wY);
        }
        if (this.wN.eO()) {
            a(this.wY, 0, fJ);
        }
        if (this == this.wZ.getParent()) {
            removeView(this.wZ);
        }
        if (this.wN.eP()) {
            a(this.wZ, fJ);
        }
        fE();
        this.wO = this.wN != Mode.BOTH ? this.wN : Mode.PULL_FROM_START;
    }

    private void a(Context context, View view) {
        this.wQ = new FrameLayout(context);
        this.wQ.addView(view, -1, -1);
        a(this.wQ, new LinearLayout.LayoutParams(-1, -1));
    }

    private void fG() {
        if (this.xa != null) {
            this.xa.onRefresh(this);
        } else if (this.xb == null) {
        } else {
            if (this.wO == Mode.PULL_FROM_START) {
                this.xb.a(this);
            } else if (this.wO == Mode.PULL_FROM_END) {
                this.xb.b(this);
            }
        }
    }

    private void b(Context context, AttributeSet attributeSet) {
        switch (e.gc[fw().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                setOrientation(0);
                break;
            default:
                setOrientation(1);
                break;
        }
        setGravity(17);
        this.jQ = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.dI);
        if (obtainStyledAttributes.hasValue(4)) {
            this.wN = Mode.H(obtainStyledAttributes.getInteger(4, 0));
        }
        if (obtainStyledAttributes.hasValue(12)) {
            this.wX = AnimationStyle.P(obtainStyledAttributes.getInteger(12, 0));
        }
        this.wP = a(context, attributeSet);
        a(context, this.wP);
        this.wY = a(context, Mode.PULL_FROM_START, obtainStyledAttributes);
        this.wZ = a(context, Mode.PULL_FROM_END, obtainStyledAttributes);
        Drawable drawable;
        if (obtainStyledAttributes.hasValue(0)) {
            drawable = obtainStyledAttributes.getDrawable(0);
            if (drawable != null) {
                this.wP.setBackgroundDrawable(drawable);
            }
        } else if (obtainStyledAttributes.hasValue(17)) {
            drawable = obtainStyledAttributes.getDrawable(17);
            if (drawable != null) {
                this.wP.setBackgroundDrawable(drawable);
            }
        }
        if (obtainStyledAttributes.hasValue(9)) {
            this.wU = obtainStyledAttributes.getBoolean(9, true);
        }
        if (obtainStyledAttributes.hasValue(13)) {
            this.wS = obtainStyledAttributes.getBoolean(13, false);
        }
        if (obtainStyledAttributes.hasValue(16)) {
            this.wL = obtainStyledAttributes.getDimensionPixelOffset(16, 0);
        }
        a(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        fF();
    }

    private boolean fH() {
        switch (e.gd[this.wN.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return fA();
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                return fB();
            case Base64.CRLF /*4*/:
                return fA() || fB();
            default:
                return false;
        }
    }

    private void fI() {
        float f;
        float f2;
        int round;
        int fx;
        switch (e.gc[fw().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                f = this.jR;
                f2 = this.mLastMotionX;
                break;
            default:
                f = this.jS;
                f2 = this.mLastMotionY;
                break;
        }
        switch (e.gd[this.wO.ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                round = Math.round(Math.max(f - f2, 0.0f) / 2.0f);
                fx = fx();
                break;
            default:
                round = Math.round(Math.min(f - f2, 0.0f) / 2.0f);
                fx = fy();
                break;
        }
        K(round);
        if (round != 0 && !fu()) {
            float abs = ((float) Math.abs(round)) / ((float) fx);
            switch (e.gd[this.wO.ordinal()]) {
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    this.wZ.onPull(abs);
                    break;
                default:
                    this.wY.onPull(abs);
                    break;
            }
            if (this.wM != State.PULL_TO_REFRESH && fx >= Math.abs(round)) {
                a(State.PULL_TO_REFRESH, new boolean[0]);
            } else if (this.wM == State.PULL_TO_REFRESH && fx < Math.abs(round)) {
                a(State.RELEASE_TO_REFRESH, new boolean[0]);
            }
        }
    }

    private LinearLayout.LayoutParams fJ() {
        switch (e.gc[fw().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return new LinearLayout.LayoutParams(-2, -1);
            default:
                return new LinearLayout.LayoutParams(-1, -2);
        }
    }

    private int fK() {
        switch (e.gc[fw().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                return Math.round(((float) getWidth()) / 2.0f);
            default:
                if (this.wL != 0) {
                    return this.wL;
                }
                return Math.round(((float) getHeight()) / 2.0f);
        }
    }

    private final void a(int i, long j) {
        a(i, j, 0, null);
    }

    private final void a(int i, long j, long j2, h hVar) {
        int scrollX;
        if (this.xd != null) {
            this.xd.stop();
        }
        switch (e.gc[fw().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                scrollX = getScrollX();
                break;
            default:
                scrollX = getScrollY();
                break;
        }
        if (scrollX != i) {
            if (this.wW == null) {
                this.wW = new DecelerateInterpolator();
            }
            this.xd = new j(this, scrollX, i, j, hVar);
            if (j2 > 0) {
                postDelayed(this.xd, j2);
            } else {
                post(this.xd);
            }
        }
    }
}
