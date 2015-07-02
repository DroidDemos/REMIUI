package com.miui.yellowpage.ui;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.utils.Contact;

/* compiled from: FlowOfPackageResultFragment */
public class K extends cs implements OnClickListener {
    private ImageView ii;
    private TextView ij;
    private TextView ik;
    private Button il;
    private TextView im;
    private String in;
    private String io;
    private int ip;
    private String iq;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.payment_result_fragment, viewGroup, false);
        this.ii = (ImageView) inflate.findViewById(R.id.payment_result_icon);
        this.ij = (TextView) inflate.findViewById(R.id.payment_result_title);
        this.ik = (TextView) inflate.findViewById(R.id.payment_result_summary);
        this.ik.setVisibility(8);
        this.il = (Button) inflate.findViewById(R.id.payment_back);
        this.il.setOnClickListener(this);
        this.im = (TextView) inflate.findViewById(R.id.payment_failed_hint);
        this.im.setVisibility(8);
        aI();
        if (Contact.directlySendSms(this.mActivity, this.in, this.io, g(this.io, this.in), this.ip)) {
            this.ii.setImageResource(R.drawable.order_waiting);
            this.ij.setText(168231159);
        } else {
            this.ii.setImageResource(R.drawable.order_error);
            this.ij.setText(168231158);
        }
        return inflate;
    }

    private PendingIntent g(String str, String str2) {
        this.mActivity.getApplicationContext().registerReceiver(new bV(), new IntentFilter("com.miui.yellowppage.send_sms"));
        return PendingIntent.getBroadcast(this.mActivity, 0, new Intent("com.miui.yellowppage.send_sms"), 0);
    }

    private void aI() {
        Bundle arguments = getArguments();
        this.in = arguments.getString("phone");
        this.io = arguments.getString("sms_body");
        this.ip = arguments.getInt("sim_index");
        this.iq = arguments.getString("source");
    }

    public void onClick(View view) {
        if (view.getId() == R.id.payment_back) {
            this.mActivity.finish();
        }
    }
}
