package com.miui.yellowpage.openapi;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.BaseWebActivity;
import com.miui.yellowpage.openapi.OpenWebFragment.OnOptionsMenuUpdatedListener;

public class OpenWebActivity extends BaseWebActivity implements OnOptionsMenuUpdatedListener {
    private Button mMenu;
    private TextView mTitle;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void customizeActionBar() {
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mActionBar.setDisplayShowHomeEnabled(false);
        this.mActionBar.setDisplayShowTitleEnabled(false);
        this.mActionBar.setCustomView(R.layout.open_web_action_bar_custom_view);
        this.mCustomView = this.mActionBar.getCustomView();
        this.mTitle = (TextView) this.mCustomView.findViewById(R.id.title);
        this.mMenu = (Button) this.mCustomView.findViewById(R.id.menu);
        this.mCustomView.findViewById(R.id.close).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!((OpenWebFragment) OpenWebActivity.this.mWebFragment).handleHomePressed()) {
                    OpenWebActivity.this.finish();
                }
            }
        });
        ((OpenWebFragment) this.mWebFragment).setOnOptionsMenuUpdatedListener(this);
    }

    public void setTitle(int i) {
        super.setTitle(i);
        this.mTitle.setText(i);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mTitle.setText(charSequence);
    }

    protected int getWebFragmentResId() {
        return R.id.open_web_fragment;
    }

    protected int getContentViewResId() {
        return R.layout.open_web_activity;
    }

    protected void onMenuHome() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        ((OpenWebFragment) this.mWebFragment).onCreateWebMenu(menu);
        return true;
    }

    protected boolean requireLogin() {
        return true;
    }

    public void onOptionsMenuUpdated() {
        if (this.mMenu.getVisibility() != 0) {
            setImmersionMenuEnabled(true);
            this.mMenu.setVisibility(0);
            this.mMenu.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    OpenWebActivity.this.showImmersionMenu(view, (RelativeLayout) OpenWebActivity.this.mCustomView);
                }
            });
        }
    }
}
