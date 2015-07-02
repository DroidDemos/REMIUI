package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.AppRecommendation;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

public class AppRecommendationListItem extends RelativeLayout {
    private static int bP;
    private TextView bQ;
    private TextView bR;
    private RatingBar bS;
    private Button bT;
    private ImageView mIcon;

    public AppRecommendationListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (bP == 0) {
            bP = (int) context.getResources().getDimension(R.dimen.yellow_page_recommendation_icon_size);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.bQ = (TextView) findViewById(R.id.name);
        this.bR = (TextView) findViewById(R.id.description);
        this.bS = (RatingBar) findViewById(R.id.rating);
        this.bT = (Button) findViewById(R.id.install);
    }

    public void a(AppRecommendation appRecommendation) {
        this.bQ.setText(appRecommendation.getName());
        if (TextUtils.isEmpty(appRecommendation.getDescription())) {
            this.bR.setVisibility(8);
        } else {
            this.bR.setText(appRecommendation.getDescription());
            this.bR.setVisibility(0);
        }
        this.bS.setRating(appRecommendation.getRating());
        YellowPageImgLoader.loadImage(getContext(), this.mIcon, null, ImageFormat.PNG, appRecommendation.getIconUrl(), bP, bP, R.drawable.yellowpage_default_icon);
        this.bT.setOnClickListener(new bR(this, appRecommendation));
    }
}
