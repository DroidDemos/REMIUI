package com.google.android.finsky.layout;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import com.android.vending.R;
import java.text.NumberFormat;

public class HistogramTable extends TableLayout {
    private final int mBarHeight;
    private final int mBarMargin;
    private final boolean mLabelsOn;
    private final int mMaxBarWidth;

    public HistogramTable(Context context) {
        this(context, null);
    }

    public HistogramTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attrArray = context.obtainStyledAttributes(attrs, R.styleable.HistogramTable);
        this.mLabelsOn = attrArray.getBoolean(0, false);
        this.mBarHeight = attrArray.getDimensionPixelSize(1, 0);
        this.mMaxBarWidth = attrArray.getDimensionPixelSize(2, -1);
        this.mBarMargin = attrArray.getDimensionPixelSize(3, 0);
        attrArray.recycle();
    }

    public void setHistogram(int[] histogram) {
        int i;
        removeAllViews();
        double max = 0.0d;
        for (i = 0; i < 5; i++) {
            if (((double) histogram[i]) > max) {
                max = (double) histogram[i];
            }
        }
        LayoutInflater inflater = LayoutInflater.from(getContext());
        NumberFormat formatter = NumberFormat.getIntegerInstance();
        Resources res = getResources();
        for (i = 0; i < 5; i++) {
            int colorId;
            TableRow tableRow = (TableRow) inflater.inflate(R.layout.histogram_row, this, false);
            StarLabel starLabel = (StarLabel) tableRow.findViewById(R.id.star_label);
            TextView countLabel = (TextView) tableRow.findViewById(R.id.count_label);
            starLabel.setMaxStars(5);
            starLabel.setStarHeight(this.mBarHeight);
            if (this.mLabelsOn) {
                starLabel.setNumStars(5 - i);
                countLabel.setText(formatter.format((long) histogram[i]));
                tableRow.setBaselineAlignedChildIndex(2);
            } else {
                starLabel.setVisibility(8);
                countLabel.setVisibility(8);
                tableRow.setBaselineAlignedChildIndex(1);
            }
            HistogramBar bar = (HistogramBar) tableRow.findViewById(R.id.histogram_bar);
            bar.setMaxBarWidth((double) this.mMaxBarWidth);
            bar.setBarHeight(this.mBarHeight);
            bar.setWidthPercentage(((double) histogram[i]) / max);
            LayoutParams params = new LayoutParams(-2, -2);
            if (i != 0) {
                params.setMargins(0, this.mBarMargin, 0, 0);
            }
            switch (i) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    colorId = R.color.review_histogram_5_bar;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    colorId = R.color.review_histogram_4_bar;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    colorId = R.color.review_histogram_3_bar;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                    colorId = R.color.review_histogram_2_bar;
                    break;
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                    colorId = R.color.review_histogram_1_bar;
                    break;
                default:
                    colorId = R.color.review_histogram_1_bar;
                    break;
            }
            bar.setColor(colorId);
            tableRow.setContentDescription(res.getQuantityString(R.plurals.content_description_review_histogram_row, histogram[i], new Object[]{Integer.valueOf(histogram[i]), Integer.valueOf(5 - i)}));
            addView(tableRow, params);
        }
    }

    public int getBaseline() {
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        if (childCount == 0) {
            return measuredHeight;
        }
        View bottomChild = getChildAt(childCount - 1);
        return (measuredHeight - bottomChild.getMeasuredHeight()) + bottomChild.getBaseline();
    }
}
