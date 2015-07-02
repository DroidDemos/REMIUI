package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.YellowPageDownloadAppModuleEntry;
import com.miui.yellowpage.base.model.YellowPageModuleEntry;
import com.miui.yellowpage.base.model.action.DropdownMenuAction;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

public class YellowPageListModuleItem extends FrameLayout {
    private static int fN;
    private static int fO;
    private static M fP;
    private static int fQ;
    private TextView fJ;
    private ImageView fK;
    private ImageView fL;
    private ImageView fM;
    private TextView mTitle;

    public YellowPageListModuleItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (fN == 0 && fO == 0) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.yellow_page_module_action_icon_size);
            fN = dimensionPixelSize;
            fO = dimensionPixelSize;
            Log.d("YellowPageListModuleItem", "The action icon height is " + fN + " width is " + fO);
        }
        if (fQ == 0) {
            fQ = getResources().getDimensionPixelSize(R.dimen.yellow_page_module_action_icon_corner_radius);
        }
        if (fP == null) {
            fP = new M();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.fJ = (TextView) findViewById(R.id.sub_title);
        this.mTitle = (TextView) findViewById(R.id.title);
        this.fK = (ImageView) findViewById(R.id.action_button);
        this.fL = (ImageView) findViewById(R.id.action_button_menu);
        this.fM = (ImageView) findViewById(R.id.badge);
    }

    public void a(YellowPageModuleEntry yellowPageModuleEntry) {
        if (yellowPageModuleEntry != null) {
            if (TextUtils.isEmpty(yellowPageModuleEntry.getTitle())) {
                this.mTitle.setVisibility(8);
            } else {
                this.mTitle.setVisibility(0);
                this.mTitle.setText(yellowPageModuleEntry.getTitle());
            }
            if (TextUtils.isEmpty(yellowPageModuleEntry.getSubTitle())) {
                this.fJ.setVisibility(8);
            } else {
                this.fJ.setVisibility(0);
                this.fJ.setText(yellowPageModuleEntry.getSubTitle());
            }
            if (yellowPageModuleEntry.getAction() instanceof DropdownMenuAction) {
                this.fK.setVisibility(8);
                this.fL.setVisibility(0);
            } else {
                if (TextUtils.isEmpty(yellowPageModuleEntry.getTypeIconUrl()) || !(yellowPageModuleEntry instanceof YellowPageDownloadAppModuleEntry)) {
                    this.fK.setImageResource(R.drawable.ic_arrow_right);
                } else {
                    YellowPageImgLoader.loadImage(getContext(), this.fK, fP, ImageFormat.PNG, yellowPageModuleEntry.getTypeIconUrl(), fO, fN, 0);
                }
                this.fK.setVisibility(0);
                this.fL.setVisibility(8);
            }
            if (yellowPageModuleEntry.isNew()) {
                this.fM.setImageResource(R.drawable.module_new);
                this.fM.setVisibility(0);
            } else if (yellowPageModuleEntry.isHot()) {
                this.fM.setImageResource(R.drawable.module_hot);
                this.fM.setVisibility(0);
            } else {
                this.fM.setVisibility(8);
            }
        }
    }
}
