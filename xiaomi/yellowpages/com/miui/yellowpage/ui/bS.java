package com.miui.yellowpage.ui;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.A;
import com.miui.yellowpage.activity.ap;
import com.miui.yellowpage.activity.y;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

/* compiled from: ExpressInquiryFragment */
public class bS implements ap {
    private LinearLayout AV;
    private int AW;
    private int AX;
    private OnClickListener AY;
    private Context mContext;
    private View mRoot;
    private ImageProcessor xY;

    public bS(Context context) {
        this.AY = new cv(this);
        this.xY = new cu(this);
        this.mContext = context;
        this.AW = (int) this.mContext.getResources().getDimension(R.dimen.express_inquiry_provider_icon_size);
        this.AX = (int) this.mContext.getResources().getDimension(R.dimen.express_inquiry_provider_icon_margin);
    }

    public void l(View view) {
        this.mRoot = view;
        this.AV = (LinearLayout) this.mRoot.findViewById(R.id.container);
    }

    public void a(y yVar) {
        if (this.mRoot == null) {
            Log.e("OnProviderLoadedListenerImpl", "mServiceProviderContainer is null");
        } else if (yVar.hasData()) {
            this.mRoot.setVisibility(0);
            this.AV.removeAllViews();
            TreeSet treeSet = new TreeSet(new ct(this));
            for (Entry value : yVar.map.entrySet()) {
                treeSet.add(value.getValue());
            }
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                A a = (A) it.next();
                View imageView = new ImageView(this.mContext);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(this.AW, this.AW);
                layoutParams.setMargins(this.AX, this.AX, this.AX, this.AX);
                imageView.setLayoutParams(layoutParams);
                imageView.setTag(a);
                if (a.mIntent != null) {
                    imageView.setOnClickListener(this.AY);
                }
                this.AV.addView(imageView);
                YellowPageImgLoader.loadImage(this.mContext, imageView, this.xY, ImageFormat.PNG, a.rv, this.AW, this.AW, R.drawable.express_company_default_logo);
            }
        } else {
            this.mRoot.setVisibility(8);
        }
    }
}
