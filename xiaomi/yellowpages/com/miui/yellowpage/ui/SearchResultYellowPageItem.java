package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.SearchResultDataEntry;
import com.miui.yellowpage.base.model.SearchResultYellowPageDataEntry;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import java.util.List;

public class SearchResultYellowPageItem extends du {
    private static int wk;
    private RatingBar kA;
    private TextView oA;
    private TextView oB;
    private ImageView wl;
    private ImageView wm;
    private TextView wn;
    private SearchResultYellowPageDataEntry wo;
    private ImageView wp;

    public SearchResultYellowPageItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (wk == 0) {
            wk = context.getResources().getDimensionPixelSize(R.dimen.nearby_list_item_service_icon_size);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.bQ = (TextView) findViewById(R.id.name);
        this.oA = (TextView) findViewById(R.id.address);
        this.oB = (TextView) findViewById(R.id.distance);
        this.kA = (RatingBar) findViewById(R.id.rating);
        this.wn = (TextView) findViewById(R.id.average_price);
        this.wl = (ImageView) findViewById(R.id.first_service_icon);
        this.wm = (ImageView) findViewById(R.id.second_service_icon);
        this.wp = (ImageView) findViewById(R.id.auth_icon);
    }

    public void a(SearchResultDataEntry searchResultDataEntry) {
        if (searchResultDataEntry != null) {
            this.wo = (SearchResultYellowPageDataEntry) searchResultDataEntry;
            this.bQ.setText(searchResultDataEntry.getTitle());
            if (TextUtils.isEmpty(searchResultDataEntry.getSubTitle()) || TextUtils.isEmpty(searchResultDataEntry.getSubTitle().trim())) {
                this.oA.setVisibility(8);
                this.oB.setVisibility(8);
            } else {
                this.oA.setText(searchResultDataEntry.getSubTitle());
                this.oA.setVisibility(0);
                if (TextUtils.isEmpty(this.wo.getDistance())) {
                    this.oB.setVisibility(8);
                } else {
                    this.oB.setText(this.wo.getDistance());
                    this.oB.setVisibility(0);
                }
            }
            float averagePrice = this.wo.getAveragePrice();
            float rating = this.wo.getRating();
            if (averagePrice > 0.0f) {
                this.wn.setText(getResources().getString(R.string.nearby_average_price, new Object[]{Integer.valueOf((int) averagePrice)}));
                this.wn.setVisibility(0);
            } else {
                this.wn.setVisibility(8);
            }
            if (rating > 0.0f) {
                this.kA.setRating(rating);
                this.kA.setVisibility(0);
            } else {
                this.kA.setVisibility(8);
            }
            a(this.wo);
            YellowPageImgLoader.loadThumbnailByName(this.mContext, this.uW, kD, searchResultDataEntry.getIcon(), R.drawable.yellowpage_default_icon);
            if (TextUtils.isEmpty(this.wo.getAuthIconName())) {
                this.wp.setVisibility(8);
                return;
            }
            this.wp.setVisibility(0);
            YellowPageImgLoader.loadImage(this.mContext, this.wp, null, ImageFormat.PNG, this.wo.getAuthIconName(), wk, wk, 0);
        }
    }

    private void a(SearchResultYellowPageDataEntry searchResultYellowPageDataEntry) {
        List serviceIcons = searchResultYellowPageDataEntry.getServiceIcons();
        if (serviceIcons == null || serviceIcons.size() <= 0) {
            this.wl.setVisibility(8);
            this.wm.setVisibility(8);
            return;
        }
        this.wl.setVisibility(0);
        YellowPageImgLoader.loadImage(this.mContext, this.wl, kD, ImageFormat.PNG, (String) serviceIcons.get(0), wk, wk, 0);
        if (serviceIcons.size() > 1) {
            this.wm.setVisibility(0);
            YellowPageImgLoader.loadImage(this.mContext, this.wm, kD, ImageFormat.PNG, (String) serviceIcons.get(1), wk, wk, 0);
            return;
        }
        this.wm.setVisibility(8);
    }
}
