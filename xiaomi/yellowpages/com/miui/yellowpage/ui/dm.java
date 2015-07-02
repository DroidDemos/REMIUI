package com.miui.yellowpage.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.A;
import com.miui.yellowpage.activity.ap;
import com.miui.yellowpage.activity.y;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

/* compiled from: ExpressInquiryProgressFragment */
class dm implements ap {
    private int AW;
    private Context mContext;
    private ImageView mIcon;
    private View mRoot;
    final /* synthetic */ ExpressInquiryProgressFragment mY;
    private ImageProcessor xY;

    private dm(ExpressInquiryProgressFragment expressInquiryProgressFragment, Context context, View view) {
        this.mY = expressInquiryProgressFragment;
        this.xY = new y(this);
        this.mContext = context;
        this.mRoot = view;
        this.AW = (int) this.mContext.getResources().getDimension(R.dimen.express_inquiry_provider_icon_size);
        this.mIcon = (ImageView) this.mRoot.findViewById(R.id.icon);
    }

    public void a(y yVar) {
        if (yVar.hasData() && this.mY.dg != null && (yVar.map.containsKey(this.mY.dg) || yVar.map.containsKey("kuaidi100"))) {
            this.mRoot.setVisibility(0);
            Object k = this.mY.dg;
            if (!yVar.map.containsKey(k)) {
                k = "kuaidi100";
            }
            A a = (A) yVar.map.get(k);
            YellowPageImgLoader.loadImage(this.mContext, this.mIcon, this.xY, ImageFormat.PNG, a.rv, this.AW, this.AW, R.drawable.express_company_default_logo);
            Intent intent = a.mIntent;
            if (intent != null) {
                this.mIcon.setOnClickListener(new z(this, intent));
                return;
            }
            return;
        }
        this.mRoot.setVisibility(8);
    }
}
