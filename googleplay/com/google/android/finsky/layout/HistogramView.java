package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.IntMath;
import com.google.android.play.layout.StarRatingBar;
import java.text.NumberFormat;

public class HistogramView extends RelativeLayout {
    private TextView mAverageValue;
    private NumberFormat mFloatFormatter;
    private final int mHalfSeparatorThickness;
    private HistogramTable mHistogramTable;
    private NumberFormat mIntegerFormatter;
    private final boolean mNeedsHeightCorrection;
    private StarRatingBar mRatingBar;
    private TextView mRatingCount;
    private final int mSeparatorInset;
    private final Paint mSeparatorPaint;
    private final boolean mShowsBottomSeparator;
    private LinearLayout mSummaryBox;

    public HistogramView(Context context) {
        this(context, null);
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFloatFormatter = NumberFormat.getNumberInstance();
        this.mFloatFormatter.setMinimumFractionDigits(1);
        this.mFloatFormatter.setMaximumFractionDigits(1);
        this.mIntegerFormatter = NumberFormat.getIntegerInstance();
        TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.HistogramView);
        this.mNeedsHeightCorrection = attrArray.getBoolean(0, false);
        this.mShowsBottomSeparator = attrArray.getBoolean(1, false);
        attrArray.recycle();
        setWillNotDraw(false);
        Resources res = context.getResources();
        int separatorThickness = res.getDimensionPixelSize(R.dimen.play_hairline_separator_thickness);
        this.mHalfSeparatorThickness = IntMath.ceil(separatorThickness, 2);
        this.mSeparatorPaint = new Paint();
        this.mSeparatorPaint.setColor(res.getColor(R.color.play_translucent_separator_line));
        this.mSeparatorPaint.setStrokeWidth((float) separatorThickness);
        this.mSeparatorInset = res.getDimensionPixelSize(R.dimen.details_new_content_margin);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSummaryBox = (LinearLayout) findViewById(R.id.ratings_summary_box);
        this.mHistogramTable = (HistogramTable) findViewById(R.id.histogram);
        this.mAverageValue = (TextView) findViewById(R.id.average_value);
        this.mRatingBar = (StarRatingBar) this.mSummaryBox.findViewById(R.id.summary_rating_bar);
        this.mRatingCount = (TextView) this.mSummaryBox.findViewById(R.id.num_reviews);
    }

    public View getSummaryBox() {
        return this.mSummaryBox;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mNeedsHeightCorrection) {
            int baseHeight;
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int leftHeight = this.mAverageValue.getMeasuredHeight() + this.mSummaryBox.getMeasuredHeight();
            int rightHeight = this.mHistogramTable.getMeasuredHeight();
            if (leftHeight > rightHeight) {
                baseHeight = leftHeight + (this.mHistogramTable.getMeasuredHeight() - this.mHistogramTable.getBaseline());
            } else {
                baseHeight = rightHeight + (this.mRatingCount.getMeasuredHeight() - this.mRatingCount.getBaseline());
            }
            setMeasuredDimension(width, (getPaddingBottom() + baseHeight) + getPaddingTop());
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mShowsBottomSeparator) {
            int bottomY = getHeight() - this.mHalfSeparatorThickness;
            canvas.drawLine((float) this.mSeparatorInset, (float) bottomY, (float) (getWidth() - this.mSeparatorInset), (float) bottomY, this.mSeparatorPaint);
        }
    }

    public void bind(Document doc) {
        if (doc.hasReviewHistogramData()) {
            setVisibility(0);
            Resources res = getResources();
            long ratingCount = doc.getRatingCount();
            this.mRatingCount.setText(this.mIntegerFormatter.format(ratingCount));
            this.mRatingCount.setContentDescription(res.getQuantityString(R.plurals.content_description_review_histogram_review_count, (int) ratingCount, new Object[]{Long.valueOf(ratingCount)}));
            this.mAverageValue.setText(this.mFloatFormatter.format((double) doc.getStarRating()));
            this.mAverageValue.setContentDescription(res.getString(R.string.content_description_review_histogram_rating, new Object[]{formattedRating}));
            this.mRatingBar.setRating(doc.getStarRating());
            this.mHistogramTable.setHistogram(doc.getRatingHistogram());
            return;
        }
        FinskyLog.w("No histogram data received from server", new Object[0]);
        setVisibility(8);
    }
}
