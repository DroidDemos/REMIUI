package com.miui.yellowpage.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.protocol.WindowData;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.model.YellowPageExtraData;
import com.miui.yellowpage.base.model.YellowPageTitleDataEntry;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import com.ta.utdid2.core.persistent.TransactionXMLFile;

public class YellowPageTitleListItem extends RelativeLayout {
    private static int bP;
    private static ImageProcessor kD;
    private static int kt;
    private RatingBar kA;
    private TextView kB;
    private TextView kC;
    private View ku;
    private View kv;
    private View kw;
    private ImageView kx;
    private TextView ky;
    private TextView kz;
    private TextView mTitle;

    public YellowPageTitleListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (bP == 0) {
            bP = (int) context.getResources().getDimension(R.dimen.yellow_page_album_size);
            kt = (int) context.getResources().getDimension(R.dimen.yellow_page_album_round_corner_radius);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.kx = (ImageView) findViewById(R.id.album);
        this.ky = (TextView) findViewById(R.id.price);
        this.kz = (TextView) findViewById(R.id.evaluation);
        this.kA = (RatingBar) findViewById(R.id.rating);
        this.kB = (TextView) findViewById(R.id.brief);
        this.mTitle = (TextView) findViewById(R.id.title);
        this.kC = (TextView) findViewById(R.id.photo_count);
        this.ku = findViewById(R.id.standard_container);
        this.kv = findViewById(R.id.public_container);
        this.kw = findViewById(R.id.enterprise_container);
    }

    public void a(YellowPageTitleDataEntry yellowPageTitleDataEntry) {
        YellowPageImgLoader.loadImage(getContext(), this.kx, kD, ImageFormat.PNG, yellowPageTitleDataEntry.getAlbumPhotoUrl(), bP, bP, R.drawable.yellowpage_default_icon);
        if (yellowPageTitleDataEntry.getAlbumIntent() != null) {
            this.kx.setOnClickListener(new cj(this, yellowPageTitleDataEntry));
        } else {
            this.kx.setOnClickListener(null);
        }
        switch (ch.Dq[yellowPageTitleDataEntry.getStyle().ordinal()]) {
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                b(yellowPageTitleDataEntry);
                return;
            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                d(yellowPageTitleDataEntry);
                return;
            case WindowData.d /*3*/:
                c(yellowPageTitleDataEntry);
                return;
            default:
                return;
        }
    }

    private void b(YellowPageTitleDataEntry yellowPageTitleDataEntry) {
        this.ku.setVisibility(8);
        this.kv.setVisibility(8);
        this.kw.setVisibility(0);
        this.mTitle.setText(yellowPageTitleDataEntry.getTitle());
    }

    private void c(YellowPageTitleDataEntry yellowPageTitleDataEntry) {
        this.ku.setVisibility(8);
        this.kv.setVisibility(0);
        this.kw.setVisibility(8);
        this.kC.setVisibility(8);
        CharSequence string = getContext().getString(R.string.no_brief);
        if (!TextUtils.isEmpty(yellowPageTitleDataEntry.getBrief())) {
            string = yellowPageTitleDataEntry.getBrief();
        }
        this.kB.setText(string);
    }

    private void d(YellowPageTitleDataEntry yellowPageTitleDataEntry) {
        float f = 0.0f;
        this.ku.setVisibility(0);
        this.kv.setVisibility(8);
        this.kw.setVisibility(8);
        YellowPageExtraData extraData = yellowPageTitleDataEntry.getExtraData();
        CharSequence string = getContext().getString(R.string.no_evaluation);
        CharSequence string2 = getContext().getString(R.string.no_price);
        if (extraData != null) {
            if (((int) extraData.getAveragePrice()) > 0) {
                string2 = getContext().getString(R.string.average_price, new Object[]{Integer.valueOf(r4)});
            }
            if (extraData.getRating() >= 0.0f && extraData.getRating() <= 5.0f) {
                f = extraData.getRating();
            }
            if (!TextUtils.isEmpty(extraData.getEvaluation())) {
                string = extraData.getEvaluation();
            }
        }
        this.kz.setText(string);
        this.kA.setRating(f);
        this.ky.setText(string2);
        if (yellowPageTitleDataEntry.getPhotoCount() > 0) {
            this.kC.setVisibility(0);
            this.kC.setText(getContext().getString(R.string.photo_count, new Object[]{Integer.valueOf(yellowPageTitleDataEntry.getPhotoCount())}));
            return;
        }
        this.kC.setVisibility(8);
    }

    static {
        kD = new ci();
    }
}
