package com.miui.yellowpage.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.utils.ContactThumbnailProcessor;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;

public class InstallYellowPageShortCutIconActivity extends BaseActivity {
    private String EU;
    private String EV;
    private String EW;
    private int EX;

    protected boolean supportsBanner() {
        return false;
    }

    @SuppressLint({"InflateParams"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri data = getIntent().getData();
        if (data != null && TextUtils.equals(data.getScheme(), "yellowpage")) {
            this.EU = data.getQueryParameter(MiniDefine.l);
            this.EV = data.getQueryParameter("icon");
            this.EW = data.getQueryParameter("id");
        }
        if (TextUtils.isEmpty(this.EU) || TextUtils.isEmpty(this.EV) || TextUtils.isEmpty(this.EW)) {
            finish();
            return;
        }
        ContactThumbnailProcessor contactThumbnailProcessor = new ContactThumbnailProcessor(this, R.drawable.yellow_page_thumbnail_fg, R.drawable.yellow_page_thumbnail_bg, R.drawable.yellow_page_thumbnail_mask);
        Builder builder = new Builder(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.shortcut_icon_confirm_dlg, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.thumbnail);
        this.EX = getResources().getDimensionPixelSize(R.dimen.shortcut_icon_size);
        YellowPageImgLoader.loadImage(this, imageView, contactThumbnailProcessor, ImageFormat.JPG, this.EV, this.EX, this.EX, R.drawable.yellowpage_default_icon);
        ((TextView) inflate.findViewById(R.id.summary)).setText(this.EU);
        builder.setTitle(getString(R.string.confirm_to_send_to_desktop, new Object[]{this.EU}));
        builder.setView(inflate);
        builder.setPositiveButton(17039370, new ac(this));
        builder.setNegativeButton(17039360, new ad(this));
        builder.setOnCancelListener(new ae(this));
        builder.show();
    }

    protected boolean requireNetworking() {
        return false;
    }
}
