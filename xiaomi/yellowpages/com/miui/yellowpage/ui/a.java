package com.miui.yellowpage.ui;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.miui.yellowpage.R;

/* compiled from: FlowOfPackageConfirmFragment */
public class a extends cs {
    private TextView bX;
    private TextView bY;
    private Button bZ;
    private String ca;
    private String cb;
    private String cc;
    private Bundle cd;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.flow_of_package_confirm_fragment, viewGroup, false);
        u();
        this.bX = (TextView) inflate.findViewById(R.id.packages_info);
        this.bY = (TextView) inflate.findViewById(R.id.packages_description);
        this.bZ = (Button) inflate.findViewById(R.id.pay);
        v();
        return inflate;
    }

    private void u() {
        this.cd = getArguments();
        if (this.cd != null) {
            this.cb = this.cd.getString("com.miui.yellowpage.flow_of_packages_group_title");
            this.ca = this.cd.getString("com.miui.yellowpage.flow_of_packages_package_title");
            this.cc = this.cd.getString("com.miui.yellowpage.flow_of_packages_order_tips");
        }
    }

    private void v() {
        this.bZ.setOnClickListener(new bU(this));
        CharSequence spannableStringBuilder = new SpannableStringBuilder();
        int length = getString(R.string.packages_select_to_buy_title).length();
        spannableStringBuilder.append(getString(R.string.packages_select_to_buy_title)).append("\n");
        spannableStringBuilder.append(this.cb).append("\n");
        spannableStringBuilder.append(this.ca);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(this.mActivity, miui.R.style.TextAppearance_Medium), 0, length, 33);
        int length2 = (length + 1) + this.cb.length();
        spannableStringBuilder.setSpan(new TextAppearanceSpan(this.mActivity, miui.R.style.TextAppearance_List_Primary), length + 1, length2, 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.highlight_text_color)), length2 + 1, spannableStringBuilder.length(), 33);
        this.bX.setText(spannableStringBuilder);
        this.bY.setText(this.cc);
    }
}
