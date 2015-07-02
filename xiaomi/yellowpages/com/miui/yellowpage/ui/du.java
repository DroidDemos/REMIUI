package com.miui.yellowpage.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.model.SearchResultDataEntry;

/* compiled from: SearchResultItem */
public abstract class du extends RelativeLayout {
    protected static int Ip;
    protected static int bP;
    protected static ImageProcessor kD;
    protected TextView bQ;
    protected Context mContext;
    protected ImageView uW;

    public abstract void a(SearchResultDataEntry searchResultDataEntry);

    public du(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        if (kD == null) {
            bP = (int) context.getResources().getDimension(R.dimen.thumbnail_size);
            Ip = (int) context.getResources().getDimension(R.dimen.search_result_thumbnail_corner_radius);
            kD = new N(this);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.uW = (ImageView) findViewById(R.id.thumbnail);
        this.bQ = (TextView) findViewById(R.id.name);
    }
}
