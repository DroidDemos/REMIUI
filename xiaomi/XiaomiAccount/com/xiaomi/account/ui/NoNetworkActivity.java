package com.xiaomi.account.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.xiaomi.account.R;
import com.xiaomi.account.utils.SysHelper;
import miui.app.ActionBar;
import miui.app.Activity;

public class NoNetworkActivity extends Activity {
    private static final String ACTION_FROM_CALLING_ACTIVITY = "calling_activity_action";
    public static final String TITLE = "title";

    public static void start(Context context, String title, String action) {
        Intent intent = new Intent(context, NoNetworkActivity.class);
        intent.putExtra(TITLE, title);
        intent.putExtra(ACTION_FROM_CALLING_ACTIVITY, action);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_network);
        ActionBar actionbar = getActionBar();
        if (actionbar != null) {
            actionbar.setTitle(getIntent().getStringExtra(TITLE));
        }
        ((Button) findViewById(R.id.action_retry)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (SysHelper.isNetworkConnected(NoNetworkActivity.this)) {
                    Intent intent = new Intent();
                    intent.setAction(NoNetworkActivity.this.getIntent().getStringExtra(NoNetworkActivity.ACTION_FROM_CALLING_ACTIVITY));
                    NoNetworkActivity.this.startActivity(intent);
                    NoNetworkActivity.this.finish();
                }
            }
        });
    }
}
