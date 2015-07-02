package com.miui.yellowpage.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Coupon.Detail;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import com.miui.yellowpage.utils.x;

public class CouponListItem extends RelativeLayout {
    private static boolean Cq;
    private static int Cr;
    private static int Cs;
    private static ec Ct;
    private static Typeface Cu;
    private static int kt;
    private ImageView Ck;
    private ImageView Cl;
    private TextView Cm;
    private TextView Cn;
    private TextView Co;
    private String Cp;
    private TextView bR;
    private TextView mTitle;

    public CouponListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!Cq) {
            Cr = (int) context.getResources().getDimension(R.dimen.coupon_image_width);
            Cs = (int) context.getResources().getDimension(R.dimen.coupon_image_height);
            kt = (int) context.getResources().getDimension(R.dimen.coupon_image_round_corner_radius);
            Ct = new ec();
            Cu = Typeface.createFromAsset(context.getAssets(), "fonts/coupon.ttf");
            Cq = true;
        }
        this.Cp = context.getString(R.string.currency_character);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.Ck = (ImageView) findViewById(R.id.image);
        this.Cl = (ImageView) findViewById(R.id.item_res);
        this.mTitle = (TextView) findViewById(R.id.title);
        this.bR = (TextView) findViewById(R.id.description);
        this.Co = (TextView) findViewById(R.id.purchasecount);
        this.Cn = (TextView) findViewById(R.id.currentprice);
        this.Cn.setTypeface(Cu);
        this.Cm = (TextView) findViewById(R.id.listprice);
        this.Cm.setPaintFlags(this.Cm.getPaintFlags() | 16);
    }

    public void a(Context context, Detail detail) {
        this.mTitle.setText(detail.getTitle());
        this.bR.setText(detail.getDescription());
        this.Cl.setVisibility(detail.isReservationRequired() ? 8 : 0);
        this.Cm.setText(x.b((double) detail.getListPrice()) + this.Cp);
        this.Cn.setText(x.b((double) detail.getCurrentPrice()));
        if (detail.getPurchaseCount() > 0) {
            this.Co.setText(context.getString(R.string.coupon_bought_count, new Object[]{Integer.valueOf(detail.getPurchaseCount())}));
            this.Co.setVisibility(0);
        } else {
            this.Co.setVisibility(8);
        }
        YellowPageImgLoader.loadImage(context, this.Ck, Ct, ImageFormat.PNG, detail.getImageUrl(), Cr, Cs, 0);
    }
}
