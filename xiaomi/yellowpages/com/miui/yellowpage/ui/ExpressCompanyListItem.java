package com.miui.yellowpage.ui;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.model.YellowPageImage;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import com.miui.yellowpage.model.ExpressCompany;

public class ExpressCompanyListItem extends LinearLayout {
    private static int gO;
    private static ImageProcessor gQ;
    private TextView bQ;
    private ImageView gH;
    private TextView gI;
    private TextView gJ;
    private TextView gK;
    private ImageView gL;
    private View gM;
    private View gN;
    private OnClickListener gP;
    private Context mContext;

    public ExpressCompanyListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.gP = new eb(this);
        this.mContext = context;
        if (gO == 0) {
            gO = (int) context.getResources().getDimension(R.dimen.express_company_detail_list_icon_size);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.bQ = (TextView) findViewById(R.id.name);
        this.gI = (TextView) findViewById(R.id.detail);
        this.gJ = (TextView) findViewById(R.id.highlight);
        this.gK = (TextView) findViewById(R.id.number);
        this.gL = (ImageView) findViewById(R.id.call);
        this.gH = (ImageView) findViewById(R.id.logo);
        this.gM = findViewById(R.id.bottom);
        this.gN = findViewById(R.id.divider);
    }

    public void a(ExpressCompany expressCompany) {
        this.bQ.setText(expressCompany.ee());
        this.gK.setText(expressCompany.getPhoneNumber());
        this.gL.setTag(expressCompany.getPhoneNumber());
        this.gL.setOnClickListener(this.gP);
        a(this.gJ, expressCompany.eh());
        a(this.gI, expressCompany.eg());
        YellowPageImage yellowPageImage = new YellowPageImage(this.mContext, expressCompany.ei(), gO, gO, ImageFormat.PNG);
        yellowPageImage.setImageProcessor(gQ);
        YellowPageImgLoader.loadImage(this.mContext, this.gH, yellowPageImage, R.drawable.express_company_default_logo);
        LayoutParams layoutParams = this.gN.getLayoutParams();
        layoutParams.height = this.gM.getMeasuredHeight();
        this.gN.setLayoutParams(layoutParams);
    }

    private static void a(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(str);
        textView.setVisibility(0);
    }

    private static void f(Context context, String str) {
        new Builder(context).setTitle(str).setNegativeButton(17039360, null).setPositiveButton(R.string.call, new ed(context, str)).show();
    }

    static {
        gQ = new ea();
    }
}
