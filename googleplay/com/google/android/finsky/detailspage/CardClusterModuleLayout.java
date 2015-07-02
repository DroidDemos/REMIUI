package com.google.android.finsky.detailspage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.PaintDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.android.vending.R;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoBottomDivider;
import com.google.android.finsky.detailspage.ModuleDividerItemDecoration.NoTopDivider;
import com.google.android.finsky.detailspage.ModuleMarginItemDecoration.MarginOffset;
import com.google.android.finsky.layout.BucketRow;
import com.google.android.finsky.layout.play.PlayCardClusterViewHeader;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.layout.PlayCardViewBase;

public class CardClusterModuleLayout extends LinearLayout implements NoBottomDivider, NoTopDivider, MarginOffset {
    protected BucketRow mBucketRowFirst;
    protected BucketRow mBucketRowSecond;
    protected PlayCardClusterViewHeader mHeaderView;
    private final int mMarginOffset;
    private final int mMaxItemsPerRow;

    public interface CardBinder {
        void bindCard(PlayCardViewBase playCardViewBase, int i);
    }

    public CardClusterModuleLayout(Context context) {
        this(context, null);
    }

    public CardClusterModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources res = context.getResources();
        boolean useWideLayout = res.getBoolean(R.bool.use_wide_details_layout);
        int mainBgColor = res.getColor(R.color.play_main_background);
        if (useWideLayout) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = getPaddingRight();
            int paddingBottom = getPaddingBottom();
            int extraInset = res.getDimensionPixelSize(R.dimen.play_card_inset);
            setBackgroundDrawable(new InsetDrawable(new PaintDrawable(mainBgColor), extraInset, 0, extraInset, 0));
            this.mMarginOffset = -extraInset;
            setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        } else {
            this.mMarginOffset = 0;
            setBackgroundColor(mainBgColor);
        }
        this.mMaxItemsPerRow = getMaxItemsPerRow(getResources());
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeaderView = (PlayCardClusterViewHeader) findViewById(R.id.cluster_header);
        this.mBucketRowFirst = (BucketRow) findViewById(R.id.bucket_row);
        inflateCards(this.mBucketRowFirst);
    }

    public void bind(CardBinder cardBinder, int backend, String header, String subHeader, String more, boolean showSecondRow, OnClickListener moreOnClickListener) {
        int colNum;
        this.mHeaderView.setContent(backend, header, subHeader, more, moreOnClickListener);
        for (colNum = 0; colNum < this.mMaxItemsPerRow; colNum++) {
            cardBinder.bindCard((PlayCardViewBase) this.mBucketRowFirst.getChildAt(colNum), colNum);
        }
        if (showSecondRow) {
            if (this.mBucketRowSecond == null) {
                this.mBucketRowSecond = (BucketRow) LayoutInflater.from(getContext()).inflate(R.layout.card_cluster_module_row, this, false);
                addView(this.mBucketRowSecond);
                inflateCards(this.mBucketRowSecond);
            }
            boolean hasVisibleCard = false;
            for (colNum = 0; colNum < this.mMaxItemsPerRow; colNum++) {
                PlayCardViewBase card = (PlayCardViewBase) this.mBucketRowSecond.getChildAt(colNum);
                cardBinder.bindCard(card, this.mMaxItemsPerRow + colNum);
                if (card.getVisibility() == 0) {
                    hasVisibleCard = true;
                }
            }
            this.mBucketRowSecond.setVisibility(hasVisibleCard ? 0 : 8);
        } else if (this.mBucketRowSecond != null) {
            this.mBucketRowSecond.setVisibility(8);
        }
    }

    private void inflateCards(BucketRow bucketRow) {
        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        Resources resources = context.getResources();
        for (int colNum = 0; colNum < this.mMaxItemsPerRow; colNum++) {
            bucketRow.addView(inflater.inflate(getCardLayoutId(resources), bucketRow, false));
        }
    }

    protected int getCardLayoutId(Resources res) {
        if (res.getBoolean(R.bool.play_can_use_mini_cards)) {
            return R.layout.play_card_mini;
        }
        return R.layout.play_card_small;
    }

    protected int getMaxItemsPerRow(Resources res) {
        int gridColumnCount = UiUtils.getFeaturedGridColumnCount(res, 1.0d);
        if (res.getBoolean(R.bool.play_can_use_mini_cards)) {
            return res.getInteger(R.integer.related_items_per_row);
        }
        return gridColumnCount;
    }

    public int getMarginOffset() {
        return this.mMarginOffset;
    }
}
