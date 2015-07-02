package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Review.Detail;

public class ReviewDetailListItem extends LinearLayout {
    private TextView bQ;
    private RatingBar bS;
    private TextView ky;
    private TextView lA;
    private TextView lz;

    public ReviewDetailListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.lz = (TextView) findViewById(R.id.detail);
        this.bQ = (TextView) findViewById(R.id.name);
        this.bS = (RatingBar) findViewById(R.id.rating_bar);
        this.lA = (TextView) findViewById(R.id.time);
        this.ky = (TextView) findViewById(R.id.price);
    }

    public void a(Detail detail) {
        this.lz.setText(detail.getExcerpt());
        this.bQ.setText(detail.getUserName());
        if (((int) detail.getReviewPrice()) > 0) {
            this.ky.setVisibility(0);
            this.ky.setText(getContext().getString(R.string.minimum_price, new Object[]{Integer.valueOf(r0)}));
        } else {
            this.ky.setVisibility(8);
        }
        float rating = detail.getRating();
        if (rating <= 0.0f || rating > 5.0f) {
            this.bS.setVisibility(8);
        } else {
            this.bS.setVisibility(0);
            this.bS.setRating(rating);
        }
        if (TextUtils.isEmpty(detail.getCreatedTime())) {
            this.lA.setVisibility(8);
            return;
        }
        this.lA.setVisibility(0);
        this.lA.setText(detail.getCreatedTime());
    }
}
