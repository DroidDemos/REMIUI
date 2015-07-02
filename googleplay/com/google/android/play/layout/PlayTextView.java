package com.google.android.play.layout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build.VERSION;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import com.google.android.play.R;
import java.util.Locale;

public class PlayTextView extends TextView {
    private static final boolean RESPECT_ORIGINAL_LINE_SPACING;
    private Paint mBorderPaint;
    private float mBorderThickness;
    private final float mCompactFactor;
    private int mDecorationPosition;
    private boolean mDrawBorder;
    private GradientDrawable mLastLineFadeOutDrawable;
    private int mLastLineFadeOutHintMargin;
    private int mLastLineFadeOutSize;
    private final String mLastLineOverdrawHint;
    private Paint mLastLineOverdrawHintPaint;
    private Paint mLastLineOverdrawPaint;
    private boolean mToDrawOverLastLineIfNecessary;

    static {
        RESPECT_ORIGINAL_LINE_SPACING = VERSION.SDK_INT >= 16;
    }

    public PlayTextView(Context context) {
        this(context, null);
    }

    public PlayTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        TypedArray viewAttrs = context.obtainStyledAttributes(attrs, R.styleable.PlayTextView);
        this.mToDrawOverLastLineIfNecessary = viewAttrs.hasValue(R.styleable.PlayTextView_lastLineOverdrawColor);
        if (this.mToDrawOverLastLineIfNecessary) {
            int overdrawColor = viewAttrs.getColor(R.styleable.PlayTextView_lastLineOverdrawColor, res.getColor(R.color.play_white));
            this.mLastLineOverdrawPaint = new Paint();
            this.mLastLineOverdrawPaint.setColor(overdrawColor);
            this.mLastLineOverdrawPaint.setStyle(Style.FILL_AND_STROKE);
            this.mLastLineFadeOutSize = res.getDimensionPixelSize(R.dimen.play_text_view_fadeout);
            this.mLastLineFadeOutDrawable = new GradientDrawable(Orientation.LEFT_RIGHT, new int[]{16777215 & overdrawColor, overdrawColor});
            this.mLastLineFadeOutHintMargin = res.getDimensionPixelSize(R.dimen.play_text_view_fadeout_hint_margin);
        }
        String overdrawHint = viewAttrs.getString(R.styleable.PlayTextView_lastLineOverdrawHint);
        if (viewAttrs.getBoolean(R.styleable.PlayTextView_lastLineOverdrawAllCaps, false)) {
            overdrawHint = overdrawHint.toUpperCase(Locale.getDefault());
        }
        this.mLastLineOverdrawHint = overdrawHint;
        if (!TextUtils.isEmpty(this.mLastLineOverdrawHint)) {
            this.mLastLineOverdrawHintPaint = new Paint();
            this.mLastLineOverdrawHintPaint.setColor(viewAttrs.getColor(R.styleable.PlayTextView_lastLineOverdrawHintColor, getCurrentTextColor()));
            this.mLastLineOverdrawHintPaint.setTextSize(getTextSize());
            this.mLastLineOverdrawHintPaint.setTypeface(getTypeface());
            this.mLastLineOverdrawHintPaint.setAntiAlias(true);
        }
        this.mDecorationPosition = viewAttrs.getInt(R.styleable.PlayTextView_decoration_position, 1);
        float textSize = getTextSize();
        boolean isGlobalCompactModeEnabled = res.getBoolean(R.bool.play_text_compact_mode_enable);
        boolean isLocalCompactModeEnabled = viewAttrs.getBoolean(R.styleable.PlayTextView_allowsCompactLineSpacing, false);
        if (isGlobalCompactModeEnabled && isLocalCompactModeEnabled) {
            FontMetrics metrics = getPaint().getFontMetrics();
            this.mCompactFactor = Math.max(0.0f, 1.0f - ((1.172f * textSize) / (Math.abs(metrics.ascent) + Math.abs(metrics.descent))));
        } else {
            this.mCompactFactor = 0.0f;
        }
        if (this.mCompactFactor > 0.0f) {
            float compactedLineSpacing = (-this.mCompactFactor) * textSize;
            if (RESPECT_ORIGINAL_LINE_SPACING) {
                compactedLineSpacing += ((float) getLineHeight()) * (getLineSpacingMultiplier() - 1.0f);
            }
            setLineSpacing(compactedLineSpacing, 1.0f);
        }
        viewAttrs.recycle();
        this.mBorderThickness = context.getResources().getDimension(R.dimen.play_text_view_outline);
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setStrokeWidth(this.mBorderThickness);
        this.mBorderPaint.setStyle(Style.STROKE);
        setWillNotDraw(false);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mCompactFactor != 0.0f && MeasureSpec.getMode(heightMeasureSpec) != 1073741824) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + ((int) (this.mCompactFactor * ((float) getLineHeight()))));
        }
    }

    public void setDecorationBitmap(Bitmap decorationBitmap) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), decorationBitmap);
        switch (this.mDecorationPosition) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                setCompoundDrawablesWithIntrinsicBounds(bitmapDrawable, null, null, null);
                return;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                setCompoundDrawablesWithIntrinsicBounds(null, null, bitmapDrawable, null);
                return;
            default:
                return;
        }
    }

    public void setContentColorId(int contentColorId, boolean drawBorder) {
        int color = getResources().getColor(contentColorId);
        setTextColor(color);
        this.mDrawBorder = drawBorder;
        if (this.mDrawBorder) {
            this.mBorderPaint.setColor(color);
        }
        invalidate();
    }

    public void setContentColorStateListId(int contentColorStateListId, boolean drawBorder) {
        ColorStateList contentColorStateList = getResources().getColorStateList(contentColorStateListId);
        setTextColor(contentColorStateList);
        this.mDrawBorder = drawBorder;
        if (this.mDrawBorder) {
            this.mBorderPaint.setColor(contentColorStateList.getDefaultColor());
        }
        invalidate();
    }

    public void setLastLineOverdrawColor(int lastLineOverdrawColor) {
        if (!this.mToDrawOverLastLineIfNecessary) {
            this.mLastLineOverdrawPaint = new Paint();
            this.mLastLineOverdrawPaint.setStyle(Style.FILL_AND_STROKE);
            Resources res = getResources();
            this.mLastLineFadeOutSize = res.getDimensionPixelSize(R.dimen.play_text_view_fadeout);
            this.mLastLineFadeOutHintMargin = res.getDimensionPixelSize(R.dimen.play_text_view_fadeout_hint_margin);
        }
        this.mLastLineOverdrawPaint.setColor(lastLineOverdrawColor);
        this.mLastLineFadeOutDrawable = new GradientDrawable(Orientation.LEFT_RIGHT, new int[]{16777215 & lastLineOverdrawColor, lastLineOverdrawColor});
        this.mToDrawOverLastLineIfNecessary = true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawBorder) {
            int inset = (int) Math.ceil((double) (this.mBorderThickness / 2.0f));
            canvas.drawRect((float) inset, (float) inset, (float) (getWidth() - inset), (float) (getHeight() - inset), this.mBorderPaint);
        }
        if (this.mToDrawOverLastLineIfNecessary) {
            int bottomCutoff = getHeight() - getPaddingBottom();
            int width = getWidth();
            Layout layout = getLayout();
            if (layout != null) {
                int paddingTop = getPaddingTop();
                int layoutLineCount = layout.getLineCount();
                int line = 0;
                while (line < layoutLineCount) {
                    int currLineTop = layout.getLineTop(line);
                    int currLineBottom = layout.getLineBottom(line);
                    if (currLineTop > bottomCutoff || currLineBottom <= bottomCutoff) {
                        line++;
                    } else {
                        canvas.drawRect(0.0f, (float) (paddingTop + currLineTop), (float) width, (float) bottomCutoff, this.mLastLineOverdrawPaint);
                        if (line > 0) {
                            int nonWhiteSpaceCharOffset;
                            int previousNonWhiteSpaceLine = line;
                            do {
                                previousNonWhiteSpaceLine--;
                                nonWhiteSpaceCharOffset = getLineNonWhiteSpaceCharacterEnd(previousNonWhiteSpaceLine);
                                if (nonWhiteSpaceCharOffset == -1) {
                                    break;
                                }
                                break;
                            } while (previousNonWhiteSpaceLine > 0);
                            if (nonWhiteSpaceCharOffset == -1) {
                                nonWhiteSpaceCharOffset = 0;
                            }
                            int prevLineTop = layout.getLineTop(previousNonWhiteSpaceLine);
                            int prevLineBottom = layout.getLineBottom(previousNonWhiteSpaceLine);
                            int prevLineEnd = (int) layout.getPrimaryHorizontal(nonWhiteSpaceCharOffset);
                            int paddingLeft = getPaddingLeft();
                            int paddingRight = getPaddingRight();
                            int fadeOutRight = paddingLeft + prevLineEnd;
                            if (!TextUtils.isEmpty(this.mLastLineOverdrawHint)) {
                                int hintLeft = (width - paddingRight) - ((int) this.mLastLineOverdrawHintPaint.measureText(this.mLastLineOverdrawHint));
                                if (hintLeft - this.mLastLineFadeOutHintMargin < prevLineEnd) {
                                    fadeOutRight = hintLeft - this.mLastLineFadeOutHintMargin;
                                    canvas.drawRect((float) fadeOutRight, (float) (paddingTop + prevLineTop), (float) (width - paddingRight), (float) (paddingTop + prevLineBottom), this.mLastLineOverdrawPaint);
                                }
                                canvas.drawText(this.mLastLineOverdrawHint, (float) hintLeft, (float) layout.getLineBaseline(line - 1), this.mLastLineOverdrawHintPaint);
                            }
                            this.mLastLineFadeOutDrawable.setBounds(fadeOutRight - this.mLastLineFadeOutSize, paddingTop + prevLineTop, fadeOutRight, paddingTop + prevLineBottom);
                            this.mLastLineFadeOutDrawable.draw(canvas);
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    private int getLineNonWhiteSpaceCharacterEnd(int line) {
        Layout layout = getLayout();
        CharSequence text = layout.getText();
        int start = layout.getLineStart(line);
        for (int end = layout.getLineEnd(line); end > start; end--) {
            if (!Character.isWhitespace(text.charAt(end - 1))) {
                return end;
            }
        }
        return -1;
    }
}
