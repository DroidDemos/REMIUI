package com.miui.yellowpage.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.GlobalDefine;
import com.miui.yellowpage.R;
import com.miui.yellowpage.activity.aj;
import com.miui.yellowpage.activity.ap;
import com.miui.yellowpage.base.utils.IntentUtil;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.utils.x;
import java.io.Serializable;
import java.util.regex.Pattern;
import miui.app.AlertDialog.Builder;

public class ExpressInquiryFragment extends dF {
    private static Pattern cY;
    private cc cZ;
    private Button da;
    private Button db;
    private Button dc;
    private EditText dd;
    private aj de;
    private View df;
    private String dg;
    private String dh;
    private String di;
    private boolean dj;
    private boolean dk;
    private Dialog dl;
    private QueryMode dm;
    private Bundle mArgs;
    private String mNumber;

    enum QueryMode {
        UNKNOWN,
        LOGISTICS_UNSPECIFIED,
        LOGISTICS_SPECIFIED
    }

    public ExpressInquiryFragment() {
        this.dm = QueryMode.UNKNOWN;
    }

    static {
        cY = Pattern.compile("[^0-9a-zA-Z\\s]+");
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof cc) {
            this.cZ = (cc) activity;
        } else {
            Log.e("ExpressInquiryFragment", "parent activity is not an instance of ExpressInquiryListener");
        }
        this.mArgs = getArguments();
        if (this.mArgs == null) {
            this.mArgs = new Bundle();
        }
        this.dg = this.mArgs.getString("bizCode");
        if (TextUtils.isEmpty(this.dg)) {
            this.dm = QueryMode.LOGISTICS_UNSPECIFIED;
        } else {
            this.dm = QueryMode.LOGISTICS_SPECIFIED;
        }
    }

    public void onResume() {
        super.onResume();
        this.JP.k(true);
        this.JP.setTitle((int) R.string.express_inquiry);
    }

    public void onStart() {
        super.onStart();
        if (this.dk) {
            R();
            N();
            this.dk = false;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.express_inquiry_fragment, viewGroup, false);
        if (TextUtils.isEmpty(this.dg) && bundle != null) {
            this.dg = bundle.getString("bizCode");
        }
        this.dh = this.mArgs.getString("logistics_name");
        if (bundle != null) {
            this.dh = bundle.getString("logistics_name");
            Serializable serializable = bundle.getSerializable("query_mode");
            if (serializable != null) {
                this.dm = (QueryMode) serializable;
            }
        }
        if (this.dm == QueryMode.UNKNOWN) {
            if (TextUtils.isEmpty(this.dg)) {
                this.dm = QueryMode.LOGISTICS_UNSPECIFIED;
            } else {
                this.dm = QueryMode.LOGISTICS_SPECIFIED;
            }
        }
        if (!TextUtils.isEmpty(this.dh)) {
            this.JP.setTitle(this.dh);
        }
        this.da = (Button) inflate.findViewById(R.id.button);
        this.da.setOnClickListener(new aV());
        this.db = (Button) inflate.findViewById(R.id.scan);
        this.db.setOnClickListener(new E());
        this.dc = (Button) inflate.findViewById(R.id.history);
        this.dc.setOnClickListener(new ac(this));
        this.dd = (EditText) inflate.findViewById(R.id.serial_number);
        this.dd.addTextChangedListener(new ba());
        this.df = inflate.findViewById(R.id.source_info_container);
        this.de = new bs(this, this.JP);
        ap bSVar = new bS(this.JP);
        bSVar.l(this.df);
        this.de.a(bSVar);
        getLoaderManager().initLoader(0, null, this.de);
        if (O()) {
            P();
        }
        if (TextUtils.isEmpty(this.mNumber)) {
            R();
        }
        N();
        CharSequence S = S();
        if (a(S)) {
            b(S);
            this.dl = c(S);
        }
        return inflate;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!TextUtils.isEmpty(this.dg)) {
            bundle.putString("bizCode", this.dg);
        }
        if (!TextUtils.isEmpty(this.dh)) {
            bundle.putString("logistics_name", this.dh);
        }
        bundle.putSerializable("query_mode", this.dm);
    }

    public void onPause() {
        super.onPause();
        if (this.dl != null && this.dl.isShowing()) {
            this.dl.dismiss();
            this.dl = null;
        }
    }

    private void N() {
        this.dd.setText(this.mNumber);
        this.dd.setSelection(this.mNumber.length());
    }

    private boolean O() {
        return !IntentUtil.canResolveIntent(this.JP, new Intent("android.intent.action.scanbarcode"));
    }

    private void P() {
        int paddingLeft = this.dd.getPaddingLeft();
        this.dd.setPadding(paddingLeft, this.dd.getPaddingTop(), paddingLeft, this.dd.getPaddingBottom());
        this.db.setVisibility(8);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent != null && i == 0) {
            b(intent);
        }
    }

    private void b(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object string = extras.getString(GlobalDefine.g);
            if (!TextUtils.isEmpty(string)) {
                this.mNumber = string;
                this.dk = true;
                U();
            }
        }
    }

    private void Q() {
        if (!TextUtils.isEmpty(this.mNumber)) {
            if (TextUtils.isEmpty(this.dg)) {
                Preference.setString(this.JP, "pref_last_express_inquiry", this.mNumber);
            } else {
                Preference.setString(this.JP, "pref_last_express_inquiry", this.mNumber + ',' + this.dg);
            }
            this.di = this.mNumber;
        }
    }

    private void R() {
        this.di = Preference.getString(this.JP, "pref_last_express_inquiry", ConfigConstant.WIRELESS_FILENAME);
        int indexOf = this.di.indexOf(44);
        if (indexOf >= 0) {
            if (this.dm != QueryMode.LOGISTICS_SPECIFIED) {
                this.dg = this.di.substring(indexOf + 1, this.di.length());
                this.di = this.di.substring(0, indexOf);
            } else if (TextUtils.equals(this.dg, this.di.substring(indexOf + 1, this.di.length()))) {
                this.di = this.di.substring(0, indexOf);
            } else {
                this.di = ConfigConstant.WIRELESS_FILENAME;
            }
        }
        this.mNumber = this.di;
    }

    private CharSequence S() {
        ClipboardManager clipboardManager = (ClipboardManager) this.JP.getSystemService("clipboard");
        if (clipboardManager == null || !clipboardManager.hasPrimaryClip()) {
            return null;
        }
        ClipData primaryClip = clipboardManager.getPrimaryClip();
        if (primaryClip != null && primaryClip.getItemCount() > 0) {
            Item itemAt = primaryClip.getItemAt(0);
            if (itemAt != null) {
                CharSequence text = itemAt.getText();
                if (!(TextUtils.isEmpty(text) || cY.matcher(text).find())) {
                    return text;
                }
            }
        }
        return null;
    }

    private boolean a(CharSequence charSequence) {
        CharSequence string = Preference.getString(this.JP, "pref_last_express_inquiry_clipboard", null);
        if (TextUtils.isEmpty(charSequence) || TextUtils.equals(string, charSequence) || TextUtils.equals(this.mNumber, charSequence)) {
            return false;
        }
        return true;
    }

    private void b(CharSequence charSequence) {
        Preference.setString(this.JP, "pref_last_express_inquiry_clipboard", charSequence.toString());
    }

    private Dialog c(CharSequence charSequence) {
        return new Builder(this.JP).setMessage(charSequence).setTitle(R.string.express_inquiry_clipboard_number_message).setPositiveButton(R.string.express_inquiry_button_text, new ab(this, charSequence)).setNegativeButton(17039360, null).show();
    }

    private boolean T() {
        return TextUtils.equals(this.di, this.mNumber);
    }

    private void U() {
        if (!cY.matcher(this.mNumber).find()) {
            x.a(getView(), false);
            if (this.dm != QueryMode.LOGISTICS_SPECIFIED) {
                if (!T()) {
                    this.dg = null;
                    this.mArgs.remove("inquiry_type");
                    this.mArgs.remove("bizCode");
                } else if (!TextUtils.isEmpty(this.dg)) {
                    this.mArgs.putString("inquiry_type", "inquiry_last");
                    this.mArgs.putString("bizCode", this.dg);
                }
                this.dk = true;
            }
            Q();
            this.mArgs.putString("order", k(this.mNumber));
            if (this.cZ != null) {
                this.cZ.g(this.mArgs);
            }
        }
    }

    private String k(String str) {
        return str.replaceAll(" ", ConfigConstant.WIRELESS_FILENAME);
    }

    private void V() {
        Intent intent = new Intent("android.intent.action.VIEW");
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("yellowpage");
        builder.authority("inquiry_history");
        intent.setData(builder.build());
        startActivity(intent);
    }
}
