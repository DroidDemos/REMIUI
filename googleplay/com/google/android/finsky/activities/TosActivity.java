package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.CheckBox;
import android.widget.TextView;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.protos.Tos.AcceptTosResponse;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.FinskyPreferences;
import com.google.android.finsky.utils.Maps;
import java.util.Map;

public class TosActivity extends Activity implements ClickListener {
    private static Map<String, String> sLastTosAcceptedInProcessLife;
    private String mAccount;
    private ButtonBar mButtonBar;
    private CheckBox mEmailOptIn;
    private DfeToc mToc;

    public TosActivity() {
        this.mAccount = null;
        this.mToc = null;
    }

    static {
        sLastTosAcceptedInProcessLife = Maps.newHashMap();
    }

    public static Intent getIntent(Context context, String account, DfeToc toc) {
        Intent result = new Intent(context, TosActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("finsky.TosActivity.account", account);
        bundle.putParcelable("finsky.TosActivity.toc", toc);
        result.putExtras(bundle);
        return result;
    }

    public static boolean requiresAcceptance(String account, DfeToc toc) {
        if (sLastTosAcceptedInProcessLife.containsKey(account) && ((String) sLastTosAcceptedInProcessLife.get(account)).equals(toc.getTosToken())) {
            return false;
        }
        String storedToken = (String) FinskyPreferences.acceptedTosToken.get(account).get();
        boolean z = TextUtils.isEmpty(storedToken) || !storedToken.equals(toc.getTosToken());
        return z;
    }

    protected void onCreate(Bundle savedState) {
        Bundle initBundle;
        super.onCreate(savedState);
        if (savedState != null) {
            initBundle = savedState;
        } else {
            initBundle = getIntent().getExtras();
        }
        if (initBundle != null) {
            this.mAccount = initBundle.getString("finsky.TosActivity.account");
            this.mToc = (DfeToc) initBundle.getParcelable("finsky.TosActivity.toc");
        }
        if (this.mAccount == null || this.mToc == null) {
            FinskyLog.w("Bad request to Terms of Service activity.", new Object[0]);
            finish();
            return;
        }
        setContentView(R.layout.terms_of_service);
        this.mButtonBar = (ButtonBar) findViewById(R.id.button_bar);
        this.mButtonBar.setPositiveButtonTitle((int) R.string.accept);
        this.mButtonBar.setNegativeButtonTitle((int) R.string.decline);
        this.mButtonBar.setClickListener(this);
        ((TextView) findViewById(R.id.account_name)).setText(this.mAccount);
        TextView mainContent = (TextView) findViewById(R.id.content);
        mainContent.setMovementMethod(LinkMovementMethod.getInstance());
        mainContent.setText(Html.fromHtml(this.mToc.getTosContent()));
        this.mEmailOptIn = (CheckBox) findViewById(R.id.email_opt_in);
        String optInText = this.mToc.getTosCheckboxTextMarketingEmails();
        if (TextUtils.isEmpty(optInText)) {
            this.mEmailOptIn.setVisibility(8);
            return;
        }
        this.mEmailOptIn.setText(optInText);
        this.mEmailOptIn.setChecked(this.mToc.hasCurrentUserPreviouslyOptedIn());
        this.mEmailOptIn.setVisibility(0);
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("finsky.TosActivity.account", this.mAccount);
        outState.putParcelable("finsky.TosActivity.toc", this.mToc);
    }

    public void onPositiveButtonClick() {
        Boolean optedIn = null;
        if (!TextUtils.isEmpty(this.mToc.getTosCheckboxTextMarketingEmails())) {
            optedIn = Boolean.valueOf(this.mEmailOptIn.isChecked());
        }
        FinskyApp.get().getDfeApi().acceptTos(this.mToc.getTosToken(), optedIn, new Listener<AcceptTosResponse>() {
            public void onResponse(AcceptTosResponse response) {
                FinskyPreferences.acceptedTosToken.get(TosActivity.this.mAccount).put(TosActivity.this.mToc.getTosToken());
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                FinskyLog.e("Error sending TOS acceptance: %s", error);
            }
        });
        sLastTosAcceptedInProcessLife.put(this.mAccount, this.mToc.getTosToken());
        setResult(-1);
        finish();
    }

    public void onNegativeButtonClick() {
        setResult(0);
        finish();
    }
}
