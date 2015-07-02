package com.miui.yellowpage.activity;

import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.miui.yellowpage.R;
import com.miui.yellowpage.utils.D;
import com.miui.yellowpage.utils.x;

public class UserNoticeActivity extends BaseActivity {
    private OnClickListener fW;
    private OnClickListener fX;

    public UserNoticeActivity() {
        this.fW = new ah(this);
        this.fX = new ai(this);
    }

    protected boolean supportsBanner() {
        return false;
    }

    protected boolean requireNetworking() {
        return false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        D.a(this, R.string.user_notice_title, x.X(this) ? R.string.user_notice_yp_detail_summary_format_cta : R.string.user_notice_yp_detail_summary_format, this.fW, this.fX, x.X(this) ? R.string.do_not_remind_me : 0);
    }
}
