package com.google.android.finsky.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.adapters.ReviewsAdapter.ChooseListingOptionsHandler;
import com.google.android.finsky.api.model.DfeReviews;
import com.google.android.finsky.utils.ReviewsSortingUtils;

public class ReviewsControlContainer extends LinearLayout {
    private TextView mFilterBox;
    private TextView mSortBox;

    public ReviewsControlContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSortBox = (TextView) findViewById(R.id.reviews_sort_box);
        this.mFilterBox = (TextView) findViewById(R.id.reviews_filter_box);
    }

    public void configure(DfeReviews reviewsData, final ChooseListingOptionsHandler listingOptionsHandler) {
        this.mSortBox.setText(ReviewsSortingUtils.getDisplayString(getContext(), reviewsData.getSortType()));
        this.mSortBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                listingOptionsHandler.onChooseSortingOptions();
            }
        });
        this.mFilterBox.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                listingOptionsHandler.onChooseFilterOptions();
            }
        });
    }
}
