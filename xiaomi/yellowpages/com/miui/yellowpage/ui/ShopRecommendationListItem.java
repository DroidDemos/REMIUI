package com.miui.yellowpage.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.model.ShopRecommendation;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.model.YellowPageExtraData;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

public class ShopRecommendationListItem extends RelativeLayout {
    private static int bP;
    private static ImageProcessor kD;
    private static int kt;
    private TextView bQ;
    private RatingBar bS;
    private ImageView mIcon;
    private TextView oA;
    private TextView oB;

    public ShopRecommendationListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (bP == 0) {
            bP = (int) context.getResources().getDimension(R.dimen.yellow_page_recommendation_icon_size);
            kt = (int) context.getResources().getDimension(R.dimen.yellow_page_recommendation_icon_round_corner_radius);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.bQ = (TextView) findViewById(R.id.name);
        this.oA = (TextView) findViewById(R.id.address);
        this.oB = (TextView) findViewById(R.id.distance);
        this.bS = (RatingBar) findViewById(R.id.rating);
    }

    public void a(ShopRecommendation shopRecommendation) {
        float rating;
        YellowPage recommendedYellowPage = shopRecommendation.getRecommendedYellowPage();
        this.bQ.setText(recommendedYellowPage.getName());
        CharSequence address = recommendedYellowPage.getAddress();
        if (TextUtils.isEmpty(address)) {
            this.oA.setVisibility(8);
            this.oB.setVisibility(8);
        } else {
            this.oA.setText(address);
            this.oA.setVisibility(0);
            address = shopRecommendation.getDistance();
            if (TextUtils.isEmpty(address)) {
                this.oB.setVisibility(8);
            } else {
                this.oB.setText(address);
                this.oB.setVisibility(0);
            }
        }
        YellowPageExtraData fromJson = YellowPageExtraData.fromJson(recommendedYellowPage.getExtraData());
        if (fromJson != null) {
            rating = fromJson.getRating();
        } else {
            rating = 0.0f;
        }
        if (rating <= 0.0f || rating > 5.0f) {
            this.bS.setVisibility(8);
        } else {
            this.bS.setVisibility(0);
            this.bS.setRating(rating);
        }
        YellowPageImgLoader.loadThumbnailByName(getContext(), this.mIcon, kD, recommendedYellowPage.getThumbnailName(), R.drawable.yellowpage_default_icon);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("yellowpage://details?id=" + recommendedYellowPage.getId()));
        setOnClickListener(new h(this, intent, shopRecommendation, recommendedYellowPage));
    }

    static {
        kD = new g();
    }
}
