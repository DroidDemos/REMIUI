package com.miui.yellowpage.business.recharge;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.utils.d;
import com.miui.yellowpage.utils.s;

/* compiled from: PaymentResultFragment */
public class C extends cs implements OnClickListener {
    private r Dz;
    private ImageView ii;
    private TextView ij;
    private TextView ik;
    private Button il;
    private TextView im;
    private s nl;

    public C() {
        this.nl = new a(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.payment_result_fragment, viewGroup, false);
        this.ii = (ImageView) inflate.findViewById(R.id.payment_result_icon);
        this.ij = (TextView) inflate.findViewById(R.id.payment_result_title);
        this.ik = (TextView) inflate.findViewById(R.id.payment_result_summary);
        this.il = (Button) inflate.findViewById(R.id.payment_back);
        this.il.setOnClickListener(this);
        this.im = (TextView) inflate.findViewById(R.id.payment_failed_hint);
        bS();
        return inflate;
    }

    private void bS() {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(getString(R.string.payment_failed_common_problems));
        spannableStringBuilder.setSpan(new d(this.nl), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.mActivity.getResources().getColor(R.color.info_text_color)), 0, spannableStringBuilder.length(), 33);
        this.im.getPaint().setFlags(8);
        this.im.setText(spannableStringBuilder);
        this.im.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mRequestLoader = new com.miui.yellowpage.a.d();
        this.Dz = new r();
        this.mRequestLoader.a(this.Dz);
        a(getArguments());
    }

    private void a(Bundle bundle) {
        this.mRequestLoader.a(U(bundle.getString("order_id")), new v());
    }

    private BaseRequest U(String str) {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getMiPayRechargeOrderInfoUrl(), 1);
        httpRequest.addParam("order_id", str);
        httpRequest.setRequestCache(false);
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }

    private void b(int i, String str, String str2) {
        if (i == 0) {
            this.ii.setImageResource(R.drawable.order_success);
        } else if (i == 1) {
            this.ii.setImageResource(R.drawable.order_error);
        } else if (i == 2) {
            this.ii.setImageResource(R.drawable.order_waiting);
        }
        this.ij.setText(str);
        this.ik.setText(str2);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.payment_back:
                this.mActivity.onBackPressed();
                return;
            default:
                return;
        }
    }
}
