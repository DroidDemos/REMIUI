package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.model.YellowPageImage;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

public class YellowPageGridModuleItem extends LinearLayout {
    private static int bP;
    private static ImageProcessor ks;
    private TextView bQ;
    private ImageView mIcon;

    public YellowPageGridModuleItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (bP == 0) {
            bP = (int) context.getResources().getDimension(R.dimen.yellow_page_service_icon_size);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mIcon = (ImageView) findViewById(R.id.icon);
        this.bQ = (TextView) findViewById(R.id.name);
    }

    public void b(YellowPageModuleEntry yellowPageModuleEntry) {
        this.bQ.setText(yellowPageModuleEntry.getTitle());
        YellowPageImage yellowPageImage = new YellowPageImage(getContext(), yellowPageModuleEntry.getActionIconUrl(), bP, bP, ImageFormat.PNG);
        yellowPageImage.setImageProcessor(ks);
        YellowPageImgLoader.loadImage(getContext(), this.mIcon, yellowPageImage, R.drawable.yellowpage_default_service);
    }

    public void bc() {
        this.bQ.setText(R.string.all_services);
        this.mIcon.setImageResource(R.drawable.more_services);
    }

    static {
        ks = new bO();
    }
}
