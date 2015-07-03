package com.xiaomi.passport.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.WrapperListAdapter;
import com.xiaomi.account.R;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class AlphabetFastIndexer extends ImageView {
    private static final int FADE_DELAYED = 1500;
    private static final int MSG_FADE = 1;
    private static final String STARRED_LABEL = "\u2605";
    public static final String STARRED_TITLE = "!";
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_NONE = 0;
    private Handler mHandler;
    private int mLastAlphabetIndex;
    private int mListScrollState;
    private AdapterView<?> mListView;
    private TextView mOverlay;
    private Drawable mOverlayBackground;
    private int mOverlayLeftMargin;
    private int mOverlayTextColor;
    private int mOverlayTextSize;
    private int mOverlayTopMargin;
    private Runnable mRefreshMaskRunnable;
    private int mState;
    private AnimatorUpdateListener mTextHilightAnimListener;
    private TextHilighter mTextHilighter;
    private int mVerticalPosition;

    private static class OnScrollerDecorator implements OnScrollListener {
        private final WeakReference<AlphabetFastIndexer> mIndexerRef;
        private final OnScrollListener mListener;

        public OnScrollerDecorator(AlphabetFastIndexer indexer, OnScrollListener l) {
            this.mIndexerRef = new WeakReference(indexer);
            this.mListener = l;
        }

        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            AlphabetFastIndexer indexer = (AlphabetFastIndexer) this.mIndexerRef.get();
            if (indexer != null) {
                indexer.refreshMask();
            }
            if (this.mListener != null) {
                this.mListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        }

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            AlphabetFastIndexer indexer = (AlphabetFastIndexer) this.mIndexerRef.get();
            if (indexer != null) {
                indexer.mListScrollState = scrollState;
            }
            if (this.mListener != null) {
                this.mListener.onScrollStateChanged(view, scrollState);
            }
        }
    }

    private static class TextHilighter {
        int mActivatedColor;
        ValueAnimator mAnimator;
        BitmapDrawable mBackground;
        Bitmap mBmpBuffer;
        Canvas mCanvas;
        Xfermode mClearMode;
        Xfermode mDstInMode;
        int mHilightColor;
        String[] mIndexes;
        int mNormalColor;
        Paint mPaint;
        Rect mSrcBounds;
        Rect mTextBound;
        Rect mTextBoundIntersect;

        TextHilighter(Context context, TypedArray a) {
            this.mPaint = new Paint();
            this.mTextBound = new Rect();
            this.mSrcBounds = new Rect();
            Resources res = context.getResources();
            CharSequence[] table = a.getTextArray(0);
            if (table != null) {
                this.mIndexes = new String[table.length];
                CharSequence[] arr$ = table;
                int len$ = arr$.length;
                int i$ = 0;
                int i = 0;
                while (i$ < len$) {
                    int i2 = i + AlphabetFastIndexer.STATE_DRAGGING;
                    this.mIndexes[i] = arr$[i$].toString();
                    i$ += AlphabetFastIndexer.STATE_DRAGGING;
                    i = i2;
                }
            } else {
                this.mIndexes = res.getStringArray(R.array.passport_alphabet_table);
            }
            this.mNormalColor = a.getColor(2, res.getColor(R.color.passport_alphabet_indexer_text_color));
            this.mActivatedColor = a.getColor(3, res.getColor(R.color.passport_alphabet_indexer_activated_text_color));
            this.mHilightColor = a.getColor(4, res.getColor(R.color.passport_alphabet_indexer_highlight_text_color));
            this.mPaint.setTextSize(a.getDimension(AlphabetFastIndexer.STATE_DRAGGING, res.getDimension(R.dimen.passport_alphabet_indexer_text_size)));
            this.mPaint.setAntiAlias(true);
            this.mPaint.setTextAlign(Align.CENTER);
            this.mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            Drawable textHilightBackground = a.getDrawable(5);
            if (textHilightBackground == null) {
                textHilightBackground = res.getDrawable(R.drawable.passport_alphabet_indexer_text_highlight_bg);
            }
            if (textHilightBackground != null && (textHilightBackground instanceof BitmapDrawable)) {
                this.mBackground = (BitmapDrawable) textHilightBackground;
                this.mBmpBuffer = this.mBackground.getBitmap().copy(Config.ARGB_8888, true);
                this.mCanvas = new Canvas(this.mBmpBuffer);
                this.mTextBoundIntersect = new Rect();
                this.mClearMode = new PorterDuffXfermode(Mode.CLEAR);
                this.mDstInMode = new PorterDuffXfermode(Mode.DST_IN);
            }
            update(0.0f, 0.0f);
        }

        void update(float xcenter, float ycenter) {
            float width = ((float) this.mBackground.getIntrinsicWidth()) / 2.0f;
            float height = ((float) this.mBackground.getIntrinsicHeight()) / 2.0f;
            this.mTextBound.set((int) ((xcenter - width) + 1.0f), (int) (ycenter - height), (int) ((xcenter + width) + 1.0f), (int) (ycenter + height));
        }

        void draw(Canvas canvas, boolean isPressed, int pos, float x, float y) {
            Paint paint = this.mPaint;
            String alpha = TextUtils.equals(this.mIndexes[pos], AlphabetFastIndexer.STARRED_TITLE) ? AlphabetFastIndexer.STARRED_LABEL : this.mIndexes[pos];
            paint.getTextBounds(alpha, 0, alpha.length(), this.mSrcBounds);
            float textWidth = (float) this.mSrcBounds.width();
            float textHeight = (float) this.mSrcBounds.height();
            paint.setColor(isPressed ? this.mActivatedColor : this.mNormalColor);
            canvas.drawText(alpha, x, y - (((float) (this.mSrcBounds.top + this.mSrcBounds.bottom)) / 2.0f), paint);
            if (this.mTextBoundIntersect.intersect((int) (x - (textWidth / 2.0f)), (int) (y - (textHeight / 2.0f)), (int) ((textWidth / 2.0f) + x), (int) ((textHeight / 2.0f) + y))) {
                x -= (float) this.mTextBound.left;
                y -= (float) this.mTextBound.top;
                paint.setColor(this.mHilightColor);
                this.mCanvas.drawText(alpha, x, y - (((float) (this.mSrcBounds.top + this.mSrcBounds.bottom)) / 2.0f), paint);
                this.mTextBoundIntersect.set(this.mTextBound);
            }
        }

        void beginDraw() {
            Paint paint = this.mPaint;
            Xfermode xfermode = paint.getXfermode();
            paint.setXfermode(this.mClearMode);
            this.mCanvas.drawPaint(paint);
            paint.setXfermode(xfermode);
            this.mBackground.setBounds(0, 0, this.mTextBound.width(), this.mTextBound.height());
            this.mBackground.draw(this.mCanvas);
            this.mTextBoundIntersect.set(this.mTextBound);
        }

        void endDraw(Canvas canvas) {
            Paint bgPaint = this.mBackground.getPaint();
            Xfermode xfermode = bgPaint.getXfermode();
            bgPaint.setXfermode(this.mDstInMode);
            this.mBackground.draw(this.mCanvas);
            bgPaint.setXfermode(xfermode);
            canvas.drawBitmap(this.mBmpBuffer, null, this.mTextBound, null);
        }

        void startSlidding(float ycenter, AnimatorUpdateListener listener) {
            float ystart;
            if (this.mAnimator != null) {
                this.mAnimator.cancel();
            }
            if (this.mTextBound == null) {
                ystart = ycenter;
            } else {
                ystart = ((float) (this.mTextBound.top + this.mTextBound.bottom)) / 2.0f;
            }
            this.mAnimator = ValueAnimator.ofFloat(new float[]{ystart, ycenter});
            this.mAnimator.addUpdateListener(listener);
            this.mAnimator.setDuration(200);
            this.mAnimator.start();
        }
    }

    public AlphabetFastIndexer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphabetFastIndexer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mTextHilightAnimListener = new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animator) {
                AlphabetFastIndexer.this.mTextHilighter.update(((float) AlphabetFastIndexer.this.getWidth()) / 2.0f, ((Float) animator.getAnimatedValue()).floatValue());
                AlphabetFastIndexer.this.postInvalidate();
            }
        };
        this.mListScrollState = 0;
        this.mState = 0;
        this.mRefreshMaskRunnable = new Runnable() {
            public void run() {
                AlphabetFastIndexer.this.refreshMask();
            }
        };
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case AlphabetFastIndexer.STATE_DRAGGING /*1*/:
                        if (AlphabetFastIndexer.this.mOverlay != null) {
                            AlphabetFastIndexer.this.mOverlay.setVisibility(8);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        Resources res = context.getResources();
        TypedArray a = context.obtainStyledAttributes(attrs, com.xiaomi.passport.R.styleable.Passport_AlphabetFastIndexer, 0, attrs.getStyleAttribute() == 0 ? defStyle : attrs.getStyleAttribute());
        this.mTextHilighter = new TextHilighter(context, a);
        this.mOverlayLeftMargin = a.getDimensionPixelOffset(8, res.getDimensionPixelOffset(R.dimen.passport_alphabet_indexer_overlay_offset));
        this.mOverlayTopMargin = a.getDimensionPixelOffset(9, res.getDimensionPixelOffset(R.dimen.passport_alphabet_indexer_overlay_padding_top));
        this.mOverlayTextSize = a.getDimensionPixelSize(10, res.getDimensionPixelSize(R.dimen.passport_alphabet_indexer_overlay_text_size));
        this.mOverlayTextColor = a.getColor(11, res.getColor(R.color.passport_alphabet_indexer_overlay_text_color));
        this.mOverlayBackground = a.getDrawable(7);
        if (this.mOverlayBackground == null) {
            this.mOverlayBackground = res.getDrawable(R.drawable.passport_alphabet_indexer_overlay);
        }
        Drawable background = a.getDrawable(6);
        if (background == null) {
            background = res.getDrawable(R.drawable.passport_alphabet_indexer_bg);
        }
        setBackground(background);
        a.recycle();
        this.mVerticalPosition = 5;
    }

    public void setVerticalPosition(boolean isRight) {
        this.mVerticalPosition = isRight ? 5 : 3;
    }

    public void setOverlayOffset(int leftMargin, int topMargin) {
        this.mOverlayLeftMargin = leftMargin;
        this.mOverlayTopMargin = topMargin;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mLastAlphabetIndex = -1;
        post(this.mRefreshMaskRunnable);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int top = getPaddingTop();
        int height = (getHeight() - top) - getPaddingBottom();
        if (height > 0) {
            String[] table = this.mTextHilighter.mIndexes;
            float alphaHeight = ((float) height) / ((float) table.length);
            float x = ((float) getWidth()) / 2.0f;
            this.mTextHilighter.beginDraw();
            float y = ((float) top) + (alphaHeight / 2.0f);
            for (int i = 0; i < table.length; i += STATE_DRAGGING) {
                this.mTextHilighter.draw(canvas, isPressed(), i, x, y);
                y += alphaHeight;
            }
            this.mTextHilighter.endDraw(canvas);
        }
    }

    public void attatch(AdapterView<?> lv) {
        if (this.mListView != lv) {
            detach();
            if (lv != null) {
                this.mLastAlphabetIndex = -1;
                this.mListView = lv;
                FrameLayout parent = (FrameLayout) getParent();
                this.mOverlay = new TextView(getContext());
                LayoutParams params = new LayoutParams(-2, -2, 17);
                params.leftMargin = this.mOverlayLeftMargin;
                params.topMargin = this.mOverlayTopMargin;
                this.mOverlay.setLayoutParams(params);
                this.mOverlay.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
                this.mOverlay.setBackground(this.mOverlayBackground);
                this.mOverlay.setGravity(17);
                this.mOverlay.setTextSize((float) this.mOverlayTextSize);
                this.mOverlay.setTextColor(this.mOverlayTextColor);
                this.mOverlay.setVisibility(8);
                parent.addView(this.mOverlay);
                params = (LayoutParams) getLayoutParams();
                params.gravity = this.mVerticalPosition | 48;
                setLayoutParams(params);
                refreshMask();
            }
        }
    }

    public void detach() {
        if (this.mListView != null) {
            stop(0);
            ((FrameLayout) getParent()).removeView(this.mOverlay);
            setVisibility(8);
            this.mListView = null;
        }
    }

    public OnScrollListener decorateScrollListener(OnScrollListener l) {
        return new OnScrollerDecorator(this, l);
    }

    public void drawThumb(CharSequence title) {
        if (this.mState == 0 && this.mListScrollState == 2) {
            drawThumbInternal(title);
        }
    }

    private void refreshMask() {
        if (this.mListView != null) {
            int newIndex = 0;
            SectionIndexer indexer = getSectionIndexer();
            if (indexer != null) {
                int section = indexer.getSectionForPosition(this.mListView.getFirstVisiblePosition() - getListOffset());
                if (section != -1) {
                    String name = indexer.getSections()[section];
                    if (!TextUtils.isEmpty(name)) {
                        newIndex = Arrays.binarySearch(this.mTextHilighter.mIndexes, name);
                    }
                }
            }
            if (this.mLastAlphabetIndex != newIndex) {
                this.mLastAlphabetIndex = newIndex;
                if (STATE_DRAGGING != this.mState) {
                    slidTextHilightBackground(this.mLastAlphabetIndex);
                }
                invalidate();
            }
        }
    }

    public int getIndexerIntrinsicWidth() {
        Drawable background = getBackground();
        return background != null ? background.getIntrinsicWidth() : 0;
    }

    private SectionIndexer getSectionIndexer() {
        if (this.mListView == null) {
            return null;
        }
        Adapter adapter = this.mListView.getAdapter();
        while (!(adapter instanceof SectionIndexer) && (adapter instanceof WrapperListAdapter)) {
            adapter = ((WrapperListAdapter) adapter).getWrappedAdapter();
        }
        if (adapter instanceof SectionIndexer) {
            return (SectionIndexer) adapter;
        }
        return null;
    }

    private int getListOffset() {
        if (this.mListView instanceof ListView) {
            return ((ListView) this.mListView).getHeaderViewsCount();
        }
        return 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
        r7 = this;
        r4 = 1;
        r3 = 0;
        r5 = r7.mListView;
        if (r5 != 0) goto L_0x000a;
    L_0x0006:
        r7.stop(r3);
    L_0x0009:
        return r3;
    L_0x000a:
        r1 = r7.getSectionIndexer();
        if (r1 != 0) goto L_0x0014;
    L_0x0010:
        r7.stop(r3);
        goto L_0x0009;
    L_0x0014:
        r5 = r8.getAction();
        r0 = r5 & 255;
        switch(r0) {
            case 0: goto L_0x0024;
            case 1: goto L_0x0078;
            case 2: goto L_0x0029;
            case 3: goto L_0x0078;
            default: goto L_0x001d;
        };
    L_0x001d:
        r3 = 1500; // 0x5dc float:2.102E-42 double:7.41E-321;
        r7.stop(r3);
    L_0x0022:
        r3 = r4;
        goto L_0x0009;
    L_0x0024:
        r7.mState = r4;
        r7.setPressed(r4);
    L_0x0029:
        r5 = r8.getY();
        r2 = r7.getPostion(r5, r1);
        if (r2 >= 0) goto L_0x0039;
    L_0x0033:
        r5 = r7.mListView;
        r5.setSelection(r3);
        goto L_0x0022;
    L_0x0039:
        r7.scrollTo(r1, r2);
        r3 = r7.mTextHilighter;
        if (r3 == 0) goto L_0x0022;
    L_0x0040:
        r3 = r8.getY();
        r5 = r7.getTop();
        r6 = r7.getPaddingTop();
        r5 = r5 + r6;
        r5 = (float) r5;
        r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r3 <= 0) goto L_0x0022;
    L_0x0052:
        r3 = r8.getY();
        r5 = r7.getBottom();
        r6 = r7.getPaddingBottom();
        r5 = r5 - r6;
        r5 = (float) r5;
        r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1));
        if (r3 >= 0) goto L_0x0022;
    L_0x0064:
        r3 = r7.mTextHilighter;
        r5 = r7.getWidth();
        r5 = r5 / 2;
        r5 = (float) r5;
        r6 = r8.getY();
        r3.update(r5, r6);
        r7.postInvalidate();
        goto L_0x0022;
    L_0x0078:
        r3 = r7.mLastAlphabetIndex;
        r7.slidTextHilightBackground(r3);
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.widget.AlphabetFastIndexer.onTouchEvent(android.view.MotionEvent):boolean");
    }

    void stop(int delay) {
        setPressed(false);
        this.mState = 0;
        postInvalidate();
        this.mHandler.removeMessages(STATE_DRAGGING);
        if (delay > 0) {
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(STATE_DRAGGING), (long) delay);
        } else if (this.mOverlay != null) {
            this.mOverlay.setVisibility(8);
        }
    }

    private int getPostion(float y, SectionIndexer indexer) {
        Object[] sections = indexer.getSections();
        if (sections == null) {
            return -1;
        }
        int paddingTop = getPaddingTop();
        int contentHeight = (getHeight() - paddingTop) - getPaddingBottom();
        if (contentHeight <= 0) {
            return -1;
        }
        int needlePos = (int) (((float) this.mTextHilighter.mIndexes.length) * ((y - ((float) paddingTop)) / ((float) contentHeight)));
        if (needlePos < 0) {
            return -1;
        }
        if (needlePos >= this.mTextHilighter.mIndexes.length) {
            return sections.length;
        }
        int section = Arrays.binarySearch(sections, this.mTextHilighter.mIndexes[needlePos]);
        if (section < 0) {
            section = (-section) - 2;
        }
        if (section < 0) {
            return 0;
        }
        return section;
    }

    private void scrollTo(SectionIndexer indexer, int position) {
        int sectionIndex;
        int index;
        int count = this.mListView.getCount();
        int listOffset = getListOffset();
        float fThreshold = (1.0f / ((float) count)) / 8.0f;
        Object[] sections = indexer.getSections();
        if (sections != null) {
            int length = sections.length;
            if (r0 > STATE_DRAGGING) {
                int nSections = sections.length;
                int section = position;
                if (section >= nSections) {
                    section = nSections - 1;
                }
                int exactSection = section;
                sectionIndex = section;
                index = indexer.getPositionForSection(section);
                int nextIndex = count;
                int prevIndex = index;
                int prevSection = section;
                int nextSection = section + STATE_DRAGGING;
                if (section < nSections - 1) {
                    nextIndex = indexer.getPositionForSection(section + STATE_DRAGGING);
                }
                if (nextIndex == index) {
                    while (section > 0) {
                        section--;
                        prevIndex = indexer.getPositionForSection(section);
                        if (prevIndex == index) {
                            if (section == 0) {
                                sectionIndex = 0;
                                break;
                            }
                        }
                        prevSection = section;
                        sectionIndex = section;
                        break;
                    }
                }
                int nextNextSection = nextSection + STATE_DRAGGING;
                while (nextNextSection < nSections && indexer.getPositionForSection(nextNextSection) == nextIndex) {
                    nextNextSection += STATE_DRAGGING;
                    nextSection += STATE_DRAGGING;
                }
                float fPrev = ((float) prevSection) / ((float) nSections);
                float fNext = ((float) nextSection) / ((float) nSections);
                float current = ((float) position) / ((float) nSections);
                if (prevSection != exactSection || current - fPrev >= fThreshold) {
                    index = prevIndex + Math.round((((float) (nextIndex - prevIndex)) * (current - fPrev)) / (fNext - fPrev));
                } else {
                    index = prevIndex;
                }
                if (index > count - 1) {
                    index = count - 1;
                }
                if (this.mListView instanceof ExpandableListView) {
                    ExpandableListView expList = this.mListView;
                    expList.setSelectionFromTop(expList.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(index + listOffset)), 0);
                } else {
                    if (this.mListView instanceof ListView) {
                        ((ListView) this.mListView).setSelectionFromTop(index + listOffset, 0);
                    } else {
                        this.mListView.setSelection(index + listOffset);
                    }
                }
                if (sectionIndex >= 0 && sections != null) {
                    String text = sections[sectionIndex].toString();
                    if (!TextUtils.isEmpty(text)) {
                        drawThumbInternal(text.subSequence(0, STATE_DRAGGING));
                        return;
                    }
                    return;
                }
            }
        }
        index = Math.round((float) (position * count));
        if (this.mListView instanceof ExpandableListView) {
            expList = (ExpandableListView) this.mListView;
            expList.setSelectionFromTop(expList.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(index + listOffset)), 0);
        } else {
            if (this.mListView instanceof ListView) {
                ((ListView) this.mListView).setSelectionFromTop(index + listOffset, 0);
            } else {
                this.mListView.setSelection(index + listOffset);
            }
        }
        sectionIndex = -1;
        if (sectionIndex >= 0) {
        }
    }

    private void drawThumbInternal(CharSequence title) {
        if (this.mListView != null) {
            if (TextUtils.equals(title, STARRED_TITLE)) {
                title = STARRED_LABEL;
            }
            this.mOverlay.setText(title);
            if (getVisibility() == 0) {
                this.mOverlay.setVisibility(0);
                this.mHandler.removeMessages(STATE_DRAGGING);
                this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(STATE_DRAGGING), 1500);
            }
        }
    }

    private void slidTextHilightBackground(int pos) {
        if (this.mTextHilighter != null) {
            if (pos < 0) {
                pos = 0;
            }
            int top = getPaddingTop();
            float alphaHeight = ((float) ((getHeight() - top) - getPaddingBottom())) / ((float) this.mTextHilighter.mIndexes.length);
            this.mTextHilighter.startSlidding((((((float) pos) * alphaHeight) + ((float) top)) + (alphaHeight / 2.0f)) + 1.0f, this.mTextHilightAnimListener);
        }
    }
}
