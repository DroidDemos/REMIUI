package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.utils.Utils;

public class SuggestionBarLayout extends IdentifiableRelativeLayout {
    private boolean mFitsInOneLine;
    private int mHeaderHeight;
    private int mLayoutHeight;
    private int mSuggestionBarUnderlinePadding;
    private int mSuggestionBarVerticalPadding;
    private TextView mSuggestionLine1;
    private TextView mSuggestionLine2;
    private LinearLayout mSuggestionLineFull;
    private TextView mSuggestionLineQuery;
    private TextView mSuggestionLineText;
    private View mSuggestionUnderline;

    public SuggestionBarLayout(Context context) {
        this(context, null);
    }

    public SuggestionBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSuggestionBarUnderlinePadding = getResources().getDimensionPixelSize(R.dimen.suggestion_bar_underline_padding);
        this.mSuggestionBarVerticalPadding = getResources().getDimensionPixelSize(R.dimen.suggestion_bar_vertical_padding);
        this.mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.play_cluster_header_height);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSuggestionLineFull = (LinearLayout) findViewById(R.id.suggestion_line_full);
        this.mSuggestionLineText = (TextView) findViewById(R.id.suggestion_line_text);
        this.mSuggestionLineQuery = (TextView) findViewById(R.id.suggestion_line_query);
        this.mSuggestionLine1 = (TextView) findViewById(R.id.suggestion_line1);
        this.mSuggestionLine2 = (TextView) findViewById(R.id.suggestion_line2);
        this.mSuggestionUnderline = findViewById(R.id.suggestion_underline);
    }

    public void setDisplayLine(String text, String query) {
        String formattedQuery = Utils.getItalicSafeString(query);
        this.mSuggestionLine1.setText(text);
        this.mSuggestionLine2.setText(formattedQuery);
        this.mSuggestionLine2.setSelected(true);
        this.mSuggestionLineText.setText(text);
        this.mSuggestionLineQuery.setText(formattedQuery);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        boolean z = false;
        int availableTextWidth = (MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()) - getPaddingRight();
        int textViewWidthSpec = MeasureSpec.makeMeasureSpec(availableTextWidth, Integer.MIN_VALUE);
        this.mSuggestionLineFull.measure(0, 0);
        this.mSuggestionLine1.measure(textViewWidthSpec, 0);
        this.mSuggestionLine2.measure(textViewWidthSpec, 0);
        this.mSuggestionUnderline.measure(0, MeasureSpec.makeMeasureSpec(this.mSuggestionUnderline.getLayoutParams().height, 1073741824));
        if (this.mSuggestionLineFull.getMeasuredWidth() <= availableTextWidth) {
            z = true;
        }
        this.mFitsInOneLine = z;
        this.mLayoutHeight = this.mFitsInOneLine ? this.mSuggestionLineFull.getMeasuredHeight() : this.mSuggestionLine1.getMeasuredHeight() + this.mSuggestionLine2.getMeasuredHeight();
        this.mLayoutHeight += this.mSuggestionBarVerticalPadding * 2;
        this.mLayoutHeight += this.mSuggestionUnderline.getMeasuredHeight();
        if (this.mLayoutHeight < this.mHeaderHeight) {
            this.mLayoutHeight = this.mHeaderHeight;
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), this.mLayoutHeight);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int xText = getPaddingLeft();
        int suggestionLineFullWidth = this.mSuggestionLineFull.getMeasuredWidth();
        int suggestionLine1Width = this.mSuggestionLine1.getMeasuredWidth();
        int suggestionLine2Width = this.mSuggestionLine2.getMeasuredWidth();
        int middleY = (height - this.mSuggestionUnderline.getMeasuredHeight()) / 2;
        if (this.mFitsInOneLine) {
            this.mSuggestionLineFull.setVisibility(0);
            this.mSuggestionLine1.setVisibility(8);
            this.mSuggestionLine2.setVisibility(8);
            int textHeight = this.mSuggestionLineFull.getMeasuredHeight();
            this.mSuggestionLineFull.layout(xText, middleY - (textHeight / 2), xText + suggestionLineFullWidth, (textHeight / 2) + middleY);
        } else {
            this.mSuggestionLineFull.setVisibility(8);
            this.mSuggestionLine1.setVisibility(0);
            this.mSuggestionLine2.setVisibility(0);
            int textHeight1 = this.mSuggestionLine1.getMeasuredHeight();
            int textHeight2 = this.mSuggestionLine2.getMeasuredHeight();
            int textTop1 = middleY - ((textHeight1 + textHeight2) / 2);
            int textTop2 = textTop1 + textHeight1;
            this.mSuggestionLine1.layout(xText, textTop1, xText + suggestionLine1Width, textTop1 + textHeight1);
            this.mSuggestionLine2.layout(xText, textTop2, xText + suggestionLine2Width, textTop2 + textHeight2);
        }
        View view = this.mSuggestionUnderline;
        int i = this.mSuggestionBarUnderlinePadding;
        this.mSuggestionUnderline.layout(this.mSuggestionBarUnderlinePadding, this.mLayoutHeight - r0.getMeasuredHeight(), width - r0, this.mLayoutHeight);
    }
}
