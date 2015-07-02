package com.miui.yellowpage.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.model.YellowPageDataDetailEntry;
import com.miui.yellowpage.model.YellowPageDataDetailEntry.Type;
import java.util.EnumSet;

public class YellowPageListItem extends FrameLayout {
    private static int zM;
    private static int zN;
    private static int zO;
    private static EnumSet zP;
    protected TextView zJ;
    protected TextView zK;
    protected ImageView zL;

    static {
        zP = EnumSet.of(Type.WEBSITE, new Type[]{Type.ADDRESS, Type.SOCIAL, Type.ABOUT, Type.CORRECTION, Type.PROVIDER});
    }

    public YellowPageListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (zM == 0) {
            zM = (int) context.getResources().getDimension(R.dimen.yellow_page_list_item_secondary_action_padding_right);
            zN = (int) context.getResources().getDimension(R.dimen.yellow_page_module_action_icon_size);
            zO = (int) context.getResources().getDimension(R.dimen.list_preferred_item_height);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.zJ = (TextView) findViewById(R.id.type);
        this.zK = (TextView) findViewById(R.id.data);
        this.zL = (ImageView) findViewById(R.id.secondary_action_button);
    }

    public void a(YellowPageDataDetailEntry yellowPageDataDetailEntry) {
        if (yellowPageDataDetailEntry.dv()) {
            this.zK.setTextAppearance(getContext(), R.style.HightlightedTextAppearance);
        } else {
            this.zK.setTextAppearance(getContext(), R.style.YellowPageListItemTextAppearancePrimary);
        }
        if (yellowPageDataDetailEntry.du() == Type.BRIEF) {
            this.zK.setSingleLine(false);
        } else if (yellowPageDataDetailEntry.du() == Type.ADDRESS) {
            this.zK.setSingleLine(false);
        } else {
            this.zK.setSingleLine(true);
        }
        if (yellowPageDataDetailEntry.du() != Type.PHONE) {
            this.zK.setTypeface(Typeface.create(this.zK.getTypeface(), 0));
        } else {
            this.zK.setTypeface(Typeface.create(this.zK.getTypeface(), 1));
        }
        this.zK.setText(yellowPageDataDetailEntry.getData());
        LayoutParams layoutParams;
        int i;
        if (yellowPageDataDetailEntry.dt() != null) {
            layoutParams = (LayoutParams) this.zL.getLayoutParams();
            i = zN + (zM * 2);
            layoutParams.width = i;
            layoutParams.height = Math.min(zO, i);
            layoutParams.setMargins(0, 0, 0, 0);
            this.zL.setLayoutParams(layoutParams);
            this.zL.setVisibility(0);
            this.zL.setImageDrawable(yellowPageDataDetailEntry.dr());
            this.zL.setOnClickListener(new cl(this, yellowPageDataDetailEntry));
        } else if (zP.contains(yellowPageDataDetailEntry.du())) {
            layoutParams = (LayoutParams) this.zL.getLayoutParams();
            i = zN;
            layoutParams.width = i;
            layoutParams.height = i;
            layoutParams.setMargins(0, 0, zM, 0);
            this.zL.setLayoutParams(layoutParams);
            this.zL.setImageDrawable(yellowPageDataDetailEntry.dr());
            this.zL.setVisibility(0);
            this.zL.setClickable(false);
        } else {
            this.zL.setVisibility(8);
        }
        if ((yellowPageDataDetailEntry.du() == Type.PHONE || yellowPageDataDetailEntry.du() == Type.PROVIDER) && !TextUtils.isEmpty(yellowPageDataDetailEntry.getTypeString())) {
            this.zJ.setText(yellowPageDataDetailEntry.getTypeString());
            this.zJ.setVisibility(0);
            return;
        }
        this.zJ.setVisibility(8);
    }
}
