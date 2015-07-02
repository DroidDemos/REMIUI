package com.miui.yellowpage.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.LoginActivity;
import com.miui.yellowpage.base.model.Enrolling;
import com.miui.yellowpage.base.utils.XiaomiAccount;

public class EnrollingListItem extends LinearLayout {
    private View gC;
    private View gD;
    private TextView gE;
    private TextView gF;

    public EnrollingListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.gC = findViewById(R.id.enroll);
        this.gD = findViewById(R.id.manage);
        this.gE = (TextView) this.gC.findViewById(R.id.enroll_hint);
        this.gF = (TextView) this.gD.findViewById(R.id.manage_hint);
        this.gE.setPaintFlags(this.gE.getPaintFlags() | 8);
    }

    public void a(Enrolling enrolling) {
        if (enrolling.isEnrolled()) {
            this.gC.setVisibility(8);
            this.gD.setVisibility(0);
            this.gF.setText(enrolling.getTitle());
            this.gD.setOnClickListener(new aM(this, enrolling));
            return;
        }
        this.gC.setVisibility(0);
        this.gD.setVisibility(8);
        this.gE.setText(enrolling.getTitle());
        this.gE.setOnClickListener(new aN(this, enrolling));
    }

    private void b(Enrolling enrolling) {
        if (XiaomiAccount.hasLogin(getContext())) {
            Intent intent = new Intent("com.miui.yellowppage.action.LOAD_WEBVIEW");
            intent.putExtra("web_url", enrolling.getUrl());
            getContext().startActivity(intent);
            return;
        }
        c(enrolling);
    }

    private void c(Enrolling enrolling) {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.putExtra("service_token_id", "spbook");
        intent.putExtra("android.intent.extra.TITLE", enrolling.getTitle());
        intent.putExtra("com.miui.yellowpage.extra.LOGIN_TYPE", "account");
        getContext().startActivity(intent);
    }
}
