package com.miui.yellowpage.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.YellowPage;
import com.miui.yellowpage.base.reference.RefMethods.IntentScope;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Permission;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.base.utils.XiaomiAccount;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import com.miui.yellowpage.ui.Y;
import com.miui.yellowpage.ui.cQ;
import com.miui.yellowpage.ui.cs;
import com.miui.yellowpage.utils.e;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.util.List;
import miui.yellowpage.YellowPagePhone;

public class YellowPageActivity extends BaseActivity implements cQ {
    private Y Lo;
    private boolean Lp;
    private View Lq;
    private int Lr;
    private long ld;
    private String le;
    private String lf;
    private ViewType lj;
    private boolean ll;
    private TextView mTitle;
    private ImageView uX;

    public enum ViewType {
        UNKNOWN,
        YID,
        NUMBER,
        MIID
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initTitleBar();
        this.lj = io();
        if (this.lj == ViewType.UNKNOWN) {
            Log.e("YellowPageActivity", "unknown view type");
            finish();
            return;
        }
        ip();
        if (!Permission.networkingAllowed(this) && !this.Lp) {
            startActivityForResult(Permission.createUserNoticeIntent(), 0);
            this.Lp = true;
        }
    }

    private void initTitleBar() {
        View inflate = getLayoutInflater().inflate(R.layout.yellow_page_activity_ab_custom_view, null);
        this.Lq = inflate.findViewById(16908332);
        this.Lq.setOnClickListener(new X(this));
        this.mTitle = (TextView) inflate.findViewById(R.id.title);
        this.uX = (ImageView) inflate.findViewById(R.id.auth_icon);
        this.mActionBar.setDisplayShowCustomEnabled(true);
        this.mActionBar.setDisplayShowHomeEnabled(false);
        this.mActionBar.setDisplayShowTitleEnabled(false);
        this.mActionBar.setCustomView(inflate);
        this.Lr = getResources().getDimensionPixelSize(R.dimen.nearby_list_item_service_icon_size);
    }

    private ViewType io() {
        Intent intent = getIntent();
        this.le = intent.getStringExtra("com.miui.yellowpage.extra.number");
        String action = intent.getAction();
        if ("com.miui.yellowpage.action.VIEW".equals(action)) {
            this.ll = true;
            this.ld = intent.getLongExtra("com.miui.yellowpage.extra.yid", 0);
            if (this.ld > 0) {
                return ViewType.YID;
            }
        } else if ("android.intent.action.VIEW".equals(action)) {
            Uri data = intent.getData();
            if (data != null && TextUtils.equals(data.getScheme(), "yellowpage")) {
                Object queryParameter = data.getQueryParameter("id");
                if (TextUtils.isEmpty(queryParameter)) {
                    queryParameter = data.getQueryParameter("sid");
                }
                if (!TextUtils.isEmpty(queryParameter)) {
                    this.ld = Long.valueOf(queryParameter).longValue();
                    if (this.ld > 0) {
                        return ViewType.YID;
                    }
                }
                queryParameter = data.getQueryParameter("number");
                if (TextUtils.isEmpty(queryParameter)) {
                    queryParameter = data.getQueryParameter("vipid");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        this.lf = queryParameter;
                        return ViewType.MIID;
                    }
                }
                this.le = queryParameter;
                return ViewType.NUMBER;
            }
        }
        return ViewType.UNKNOWN;
    }

    private void ip() {
        Log.d("YellowPageActivity", "setUpYellowPageFragment");
        Bundle bundle = new Bundle();
        bundle.putLong("com.miui.yellowpage.extra.yid", this.ld);
        bundle.putString("com.miui.yellowpage.extra.number", this.le);
        bundle.putString("mi_id", this.lf);
        bundle.putSerializable("yp_view_type", this.lj);
        bundle.putBoolean("yp_internal_jump", this.ll);
        getStatisticsContext().attach(bundle);
        showFragment("YellowPageFragment", null, bundle, false);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                this.Lp = false;
                if (i2 != -1) {
                    finish();
                    return;
                } else if (this.Lo != null) {
                    this.Lo.reload();
                    return;
                } else {
                    return;
                }
            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                if (i2 == -1) {
                    is();
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void onStop() {
        super.onStop();
        Permission.setNetworkingAllowedTemporarily(this, false);
    }

    protected void onStart() {
        super.onStart();
        iq();
    }

    private void iq() {
        ThreadPool.execute(new W(this));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.yellowpage_activity, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.favorite);
        if (findItem != null) {
            b(findItem);
        }
        findItem = menu.findItem(R.id.save_contact);
        if (findItem != null) {
            a(findItem);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.enroll:
                is();
                break;
            case R.id.favorite:
                it();
                break;
            case R.id.correction:
                iu();
                break;
            case R.id.save_contact:
                ir();
                break;
        }
        return true;
    }

    private void ir() {
        int i = 0;
        YellowPage bl = this.Lo.bl();
        if (bl != null) {
            List phones = bl.getPhones();
            if (phones != null) {
                int i2;
                Intent intent = new Intent("android.intent.action.INSERT", Contacts.CONTENT_URI);
                intent.putExtra(MiniDefine.l, bl.getName());
                CharSequence bm = this.Lo.bm();
                if (TextUtils.isEmpty(bm)) {
                    i2 = 0;
                } else {
                    intent.putExtra("phone", bm);
                    i2 = 1;
                }
                int i3 = i2;
                while (phones.size() > i && i3 < 4) {
                    String number = ((YellowPagePhone) phones.get(i)).getNumber();
                    if (!TextUtils.equals(bm, number)) {
                        switch (i3) {
                            case TransactionXMLFile.MODE_PRIVATE /*0*/:
                                intent.putExtra("phone", number);
                                i2 = i3 + 1;
                                continue;
                            case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                                intent.putExtra("secondary_phone", number);
                                i2 = i3 + 1;
                                continue;
                            case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                                intent.putExtra("tertiary_phone", number);
                                i2 = i3 + 1;
                                continue;
                        }
                    }
                    i2 = i3;
                    i++;
                    i3 = i2;
                }
                startActivity(IntentScope.processIntentScope(this, intent, "com.android.contacts"));
            }
        }
    }

    private void is() {
        if (XiaomiAccount.hasLogin(this)) {
            Intent intent = new Intent(this, InternalWebActivity.class);
            intent.putExtra("web_url", "http://app.huangye.miui.com/merchants/enroll#/index");
            intent.putExtra("web_title", getString(R.string.menu_enroll_in_yellow_page));
            startActivity(intent);
            return;
        }
        intent = new Intent(this, LoginActivity.class);
        intent.putExtra("service_token_id", "spbook");
        intent.putExtra("android.intent.extra.TITLE", getString(R.string.menu_enroll_in_yellow_page));
        intent.putExtra("com.miui.yellowpage.extra.LOGIN_TYPE", "account");
        startActivityForResult(intent, 1);
    }

    private void it() {
        boolean a = e.a(this, this.ld);
        e.a(this, this.ld, !a);
        if (a) {
            Toast.makeText(this, R.string.general_cancel_favorite_ok, 0).show();
        } else {
            Toast.makeText(this, R.string.general_favorite_ok, 1).show();
        }
    }

    private void a(MenuItem menuItem) {
        if (this.Lo != null) {
            YellowPage bl = this.Lo.bl();
            if (!(bl == null || bl.getPhones() == null)) {
                menuItem.setVisible(true);
                return;
            }
        }
        menuItem.setVisible(false);
    }

    private void b(MenuItem menuItem) {
        if (e.a(this, this.ld)) {
            menuItem.setTitle(R.string.menu_remove_from_favorite);
        } else {
            menuItem.setTitle(R.string.menu_add_to_favorite);
        }
    }

    private void iu() {
        Intent intent = new Intent(this, InternalWebActivity.class);
        intent.putExtra("web_title", getString(R.string.menu_correct_yellow_page));
        intent.putExtra("web_url", String.format("%s?%s=%s", new Object[]{"http://web.huangye.miui.com/portal/html/yellowpage/feedback.jsp", "sid", Long.valueOf(this.ld)}));
        startActivity(intent);
    }

    public void b(YellowPage yellowPage) {
        Log.d("YellowPageActivity", "onYellowPageLoaded");
        if (yellowPage != null) {
            this.ld = yellowPage.getId();
            this.mTitle.setText(yellowPage.getName());
            if (TextUtils.isEmpty(yellowPage.getAuthIconName())) {
                this.uX.setVisibility(8);
                return;
            }
            this.uX.setVisibility(0);
            YellowPageImgLoader.loadImage(this, this.uX, null, ImageFormat.PNG, yellowPage.getAuthIconName(), this.Lr, this.Lr, 0);
            return;
        }
        Log.e("YellowPageActivity", "The yellow page is null");
    }

    protected boolean supportsBanner() {
        return false;
    }

    protected cs newFragmentByTag(String str) {
        if (!"YellowPageFragment".equals(str)) {
            return null;
        }
        this.Lo = new Y();
        this.Lo.a((cQ) this);
        return this.Lo;
    }

    protected boolean requireNetworking() {
        return false;
    }
}
