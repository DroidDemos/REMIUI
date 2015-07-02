package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.utils.ContactThumbnailProcessor;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

public class FavoriteYellowPageListItem extends LinearLayout {
    private static ImageProcessor kD;
    private static int uY;
    private TextView bQ;
    private TextView oA;
    private ImageView uW;
    private ImageView uX;

    static {
        uY = 0;
    }

    public FavoriteYellowPageListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (kD == null) {
            kD = new ContactThumbnailProcessor(this.mContext, R.drawable.yellow_page_list_thumbnail_fg, R.drawable.yellow_page_list_thumbnail_bg, R.drawable.yellow_page_list_thumbnail_mask);
        }
        if (uY == 0) {
            uY = context.getResources().getDimensionPixelSize(R.dimen.nearby_list_item_service_icon_size);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.uW = (ImageView) findViewById(R.id.thumbnail);
        this.bQ = (TextView) findViewById(R.id.name);
        this.oA = (TextView) findViewById(R.id.address);
        this.uX = (ImageView) findViewById(R.id.auth_icon);
    }

    public void a(YellowPage yellowPage) {
        this.bQ.setText(yellowPage.getName());
        if (TextUtils.isEmpty(yellowPage.getAddress())) {
            this.oA.setVisibility(8);
        } else {
            this.oA.setVisibility(0);
            this.oA.setText(yellowPage.getAddress());
        }
        YellowPageImgLoader.loadThumbnailByName(this.mContext, this.uW, kD, yellowPage.getThumbnailName(), R.drawable.yellowpage_default_icon);
        if (TextUtils.isEmpty(yellowPage.getAuthIconName())) {
            this.uX.setVisibility(8);
            return;
        }
        this.uX.setVisibility(0);
        YellowPageImgLoader.loadImage(this.mContext, this.uX, null, ImageFormat.PNG, yellowPage.getAuthIconName(), uY, uY, 0);
    }
}
