package com.miui.yellowpage.ui;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.d;
import com.miui.yellowpage.b.n;
import com.miui.yellowpage.b.o;
import com.miui.yellowpage.base.reference.RefMethods.Telephony;
import com.miui.yellowpage.base.request.BaseRequest;
import com.miui.yellowpage.base.request.HttpRequest;
import com.miui.yellowpage.base.request.LocalRequest;
import com.miui.yellowpage.base.utils.HostManager;
import com.miui.yellowpage.base.utils.Log;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.base.utils.ThreadPool;
import com.miui.yellowpage.model.b;
import com.miui.yellowpage.model.v;
import com.miui.yellowpage.utils.s;
import com.miui.yellowpage.widget.LoadingProgressView;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import miui.telephony.PhoneNumberUtils;
import miui.telephony.PhoneNumberUtils.PhoneNumber;
import miui.util.ViewUtils;

/* compiled from: RechargeFragment */
public class aL extends cs {
    private LoadingProgressView hh;
    private String iq;
    private ImageView mClearButton;
    private View mRootView;
    private TextView mh;
    private s mk;
    private ArrayList qA;
    private int qB;
    private String qC;
    private int qD;
    private n qE;
    private o qF;
    private b qG;
    private boolean qH;
    private OnClickListener qI;
    private OnCheckedChangeListener qJ;
    private OnTouchListener qK;
    private OnClickListener qL;
    private aU qj;
    private ce qk;
    private dD ql;
    private d qm;
    private ImageView qn;
    private AutoCompleteTextView qo;
    private RadioGroup qp;
    private int qq;
    private TextView qr;
    private Button qs;
    private TextView qt;
    private cr qu;
    private boolean qv;
    private cY qw;
    private Z qx;
    private String qy;
    private String qz;

    public aL() {
        this.qq = -1;
        this.mk = new aF(this);
        this.qI = new aG(this);
        this.qJ = new I(this);
        this.qK = new J(this);
        this.qL = new G(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.recharge_fragment, viewGroup, false);
        this.qt = (TextView) inflate.findViewById(R.id.contact_name);
        this.qn = (ImageView) inflate.findViewById(R.id.pick_phone);
        this.qn.setOnClickListener(this.qL);
        this.qp = (RadioGroup) inflate.findViewById(R.id.charge_choices);
        this.qp.setOnCheckedChangeListener(this.qJ);
        this.qr = (TextView) inflate.findViewById(R.id.charge_range);
        this.mh = (TextView) inflate.findViewById(R.id.pay_hint);
        this.qs = (Button) inflate.findViewById(R.id.pay);
        this.qs.setOnClickListener(this.qI);
        this.hh = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.mRootView = inflate.findViewById(R.id.root_view);
        this.qq = Preference.getInt(this.mActivity, "pref_last_charge_value_id", Integer.valueOf(0));
        this.mClearButton = (ImageView) inflate.findViewById(R.id.clear_button_place_holder);
        this.mClearButton.setOnClickListener(new ax(this));
        this.ql = new dD();
        this.qm = new d();
        this.qm.a(this.ql);
        this.qE = new n(this.mActivity);
        this.mRootView.setOnTouchListener(this.qK);
        this.qo = (AutoCompleteTextView) inflate.findViewById(R.id.phone);
        this.qo.setThreshold(3);
        if (this.qu == null) {
            this.qu = new cr();
        }
        this.qo.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        this.qo.addTextChangedListener(new R());
        this.qo.addTextChangedListener(new cJ());
        this.qo.setOnItemClickListener(new ay(this));
        this.qo.setOnClickListener(new az(this));
        this.qo.setAdapter(this.qE);
        this.qy = getString(R.string.recharge_my_phone_number);
        bH();
        cU();
        return inflate;
    }

    public void onStart() {
        super.onStart();
        bI();
        this.qo.addTextChangedListener(this.qu);
    }

    public void onPause() {
        super.onPause();
        this.qH = false;
    }

    public void onStop() {
        super.onStop();
        dc();
        this.qo.removeTextChangedListener(this.qu);
    }

    public void onDetach() {
        super.onDetach();
        this.qm.a(null);
    }

    private void bH() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            Object string = arguments.getString("phone_number");
            if (!TextUtils.isEmpty(string)) {
                this.qC = string;
            }
            this.iq = arguments.getString("source");
            this.qD = arguments.getInt("sim_index", -1);
        }
    }

    private void cQ() {
        if (this.qo != null && this.qF != null) {
            if (this.qo.getAdapter() instanceof n) {
                if (this.qo.isPopupShowing()) {
                    this.qo.dismissDropDown();
                }
                this.qo.setAdapter(this.qF);
                this.qo.showDropDown();
            } else if (!this.qo.isPopupShowing()) {
                this.qo.showDropDown();
            }
            this.qv = true;
        }
    }

    private void cR() {
        if (this.qo != null && this.qE != null) {
            this.qo.dismissDropDown();
            this.qo.setAdapter(this.qE);
            this.qv = false;
        }
    }

    private void cS() {
        ThreadPool.execute(new aA(this));
    }

    private void cT() {
        if (!TextUtils.isEmpty(this.qC)) {
            this.qo.setText(this.qC);
        } else if (this.qD >= 0) {
            B(this.qD);
        } else if (!this.qA.isEmpty()) {
            this.qo.setText(((v) this.qA.get(0)).getPhone());
        }
    }

    private void B(int i) {
        if (i >= Sim.getSimCount(this.mActivity)) {
            Log.d("RechargeFragment", "Index out of bounds: " + i);
        } else {
            ThreadPool.execute(new aB(this, i));
        }
    }

    private void cU() {
        this.qF = new o(this.mActivity);
        this.qF.a(new aC(this));
        this.qA = new ArrayList();
        this.qF.updateData(this.qA);
        this.qB = Sim.getSimCount(this.mActivity);
        cS();
    }

    private void bI() {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(this.mActivity.getResources().getString(R.string.recharge_pay_hint_license));
        spannableStringBuilder.setSpan(new com.miui.yellowpage.utils.d(this.mk), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.mActivity.getResources().getColor(R.color.info_text_color)), 0, spannableStringBuilder.length(), 33);
        this.mh.setText(spannableStringBuilder);
        this.mh.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void cV() {
        ThreadPool.execute(new aE(this));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.qj = new aU();
        getLoaderManager().initLoader(1, null, this.qj);
        this.mRequestLoader = new d();
        this.qx = new Z();
        this.qk = new ce();
        this.mRequestLoader.a(this.hh);
        this.mRequestLoader.a(this.qk);
    }

    private boolean l(String str, String str2) {
        return PhoneNumberUtils.compareLoosely(str, str2);
    }

    private BaseRequest b(String str, int i) {
        LocalRequest localRequest = new LocalRequest(this.mActivity, i);
        localRequest.setUri(Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, URLEncoder.encode(str)));
        localRequest.setProjection(new String[]{"display_name"});
        return localRequest;
    }

    private BaseRequest cW() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargeProductUrl(), 1);
        httpRequest.setRequireLogin(true);
        httpRequest.setRequestCache(false);
        return httpRequest;
    }

    private BaseRequest cX() {
        b da = da();
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargePriceUrl(), da.hashCode());
        httpRequest.addParam("recharge_name", normalizeNumber(this.qo.getText().toString()));
        httpRequest.addParam("pervalue", da.Y());
        httpRequest.setRequireLogin(true);
        httpRequest.setRequestCache(false);
        return httpRequest;
    }

    private BaseRequest cY() {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargeOrderUrl(), 2);
        httpRequest.addParam("recharge_name", normalizeNumber(this.qo.getText().toString()));
        httpRequest.addParam("pervalue", this.qG.Y());
        httpRequest.setRequireLogin(true);
        httpRequest.setRequestCache(false);
        httpRequest.setRequestMethod("POST");
        return httpRequest;
    }

    private BaseRequest at(String str) {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargeOrderInfoUrl(), 4);
        httpRequest.addParam("order_id", str);
        httpRequest.setRequireLogin(true);
        httpRequest.setRequestCache(false);
        return httpRequest;
    }

    private BaseRequest S(String str) {
        HttpRequest httpRequest = new HttpRequest(this.mActivity, HostManager.getRechargeSecurityPayUrl(), 3);
        httpRequest.addParam("order_id", str);
        httpRequest.setRequestCache(false);
        httpRequest.setRequireLogin(true);
        return httpRequest;
    }

    private void cZ() {
        if (this.qw != null) {
            this.qp.removeAllViews();
            this.qp.clearCheck();
            this.qp.setDividerDrawable(null);
            ViewUtils.setTagChildrenSequenceState(this.qp, false);
            int size = this.qw.DO.size();
            int i = 0;
            while (i < size) {
                b bVar = (b) this.qw.DO.get(i);
                RadioButton radioButton = (RadioButton) LayoutInflater.from(this.mActivity).inflate(R.layout.recharge_choice_item, this.qp, false);
                radioButton.setOnTouchListener(this.qK);
                radioButton.setText(bVar.getName());
                radioButton.setId(i);
                this.qp.addView(radioButton, i);
                if (i == this.qq || (i == size - 1 && (this.qq < 0 || this.qq >= size))) {
                    radioButton.setChecked(true);
                }
                i++;
            }
        }
    }

    private void C(int i) {
        this.qq = i;
        Preference.setInt(this.mActivity, "pref_last_charge_value_id", Integer.valueOf(this.qq));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 1) {
            Cursor query = this.mActivity.getContentResolver().query(intent.getData(), new String[]{"data1"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        CharSequence normalizeNumber = normalizeNumber(query.getString(0));
                        this.qo.setText(ConfigConstant.WIRELESS_FILENAME);
                        this.qo.append(normalizeNumber);
                        this.qo.dismissDropDown();
                    }
                    query.close();
                } catch (Throwable th) {
                    query.close();
                }
            }
        }
    }

    private String normalizeNumber(String str) {
        PhoneNumber parse = PhoneNumber.parse(str);
        String effectiveNumber = parse.getEffectiveNumber();
        parse.recycle();
        Log.d("RechargeFragment", "The recharge phone number is " + effectiveNumber);
        return effectiveNumber;
    }

    private b da() {
        return (b) this.qw.DO.get(this.qp.getCheckedRadioButtonId());
    }

    private void db() {
        String normalizeNumber = normalizeNumber(this.qo.getText().toString());
        String string = getString(R.string.recharge_user_confirm_summary);
        aa aaVar = new aa(this, new SpannableStringBuilder());
        aaVar.a('1', normalizeNumber);
        aaVar.a('2', this.qG.Y());
        aaVar.a('3', this.qx.lB);
        new Builder(this.mActivity).setTitle(R.string.recharge_user_confirm_title).setMessage(aaVar.P(string)).setIconAttribute(16843605).setNegativeButton(17039360, null).setPositiveButton(17039370, new H(this, normalizeNumber)).show();
        r(true);
    }

    private void r(boolean z) {
        this.qs.setEnabled(z);
        for (int i = 0; i < this.qp.getChildCount(); i++) {
            this.qp.getChildAt(i).setEnabled(z);
        }
        this.qn.setEnabled(z);
        this.qo.setEnabled(z);
        this.mh.setEnabled(z);
    }

    private boolean au(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        PhoneNumber parse = PhoneNumber.parse(str);
        String effectiveNumber = parse.getEffectiveNumber();
        parse.recycle();
        if (effectiveNumber.length() == 11) {
            return true;
        }
        return false;
    }

    private void m(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            v vVar = new v(str2, str, av(str));
            if (!vVar.im()) {
                if (this.qA.contains(vVar)) {
                    this.qA.remove(vVar);
                }
                if (this.qA.size() <= this.qB) {
                    this.qA.add(vVar);
                } else {
                    this.qA.add(this.qB, vVar);
                }
                this.qF.updateData(this.qA);
            }
        }
    }

    private boolean av(String str) {
        for (int i = 0; i < this.qB; i++) {
            if (PhoneNumberUtils.compareLoosely(((v) this.qA.get(i)).getPhone(), str)) {
                return true;
            }
        }
        return false;
    }

    private void dc() {
        Preference.setString(this.mActivity, "pref_recharge_history_key", dd());
    }

    private String dd() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = this.qA.iterator();
        while (it.hasNext()) {
            v vVar = (v) it.next();
            if (!vVar.im()) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(';');
                }
                stringBuilder.append(vVar.getPhone());
            }
        }
        return stringBuilder.toString();
    }

    private void de() {
        Object string = Preference.getString(this.mActivity, "pref_recharge_history_key", ConfigConstant.WIRELESS_FILENAME);
        if (!TextUtils.isEmpty(string)) {
            aw(string);
        }
    }

    private void aw(String str) {
        for (String str2 : str.split(";")) {
            this.qA.add(new v(null, str2));
            this.qm.a(b(str2, 6), new cL(this, str2));
        }
        this.qF.updateData(this.qA);
    }

    private void b(ArrayList arrayList) {
        String str = null;
        if (arrayList != null) {
            String phoneNumber;
            String phoneNumber2;
            if (this.qB > 1) {
                phoneNumber = Sim.getPhoneNumber(this.mActivity, 0);
                phoneNumber2 = Sim.getPhoneNumber(this.mActivity, 1);
                if (TextUtils.isEmpty(phoneNumber)) {
                    this.qB--;
                    str = phoneNumber2;
                }
                if (TextUtils.isEmpty(phoneNumber2)) {
                    this.qB--;
                    str = phoneNumber;
                }
            } else if (this.qB == 1) {
                CharSequence phoneNumber3 = Sim.getPhoneNumber(this.mActivity, Sim.DEFAULT_SIM_INDEX);
                if (TextUtils.isEmpty(phoneNumber3)) {
                    this.qB--;
                }
                phoneNumber = null;
                CharSequence charSequence = phoneNumber3;
                phoneNumber2 = null;
            } else {
                phoneNumber2 = null;
                phoneNumber = null;
            }
            if (this.qB > 1) {
                String D = D(0);
                str = D(1);
                if (TextUtils.equals(D, str)) {
                    D = getString(R.string.sim1);
                    str = getString(R.string.sim2);
                }
                D = ax(D);
                str = ax(str);
                arrayList.add(new v(D, phoneNumber, true));
                arrayList.add(new v(str, phoneNumber2, true));
            } else if (this.qB == 1) {
                arrayList.add(new v(this.qy, str, true));
            }
        }
    }

    private String ax(String str) {
        if (TextUtils.isEmpty(str)) {
            return this.qy;
        }
        return this.qy + '-' + str;
    }

    private String D(int i) {
        return Telephony.getSimDisplayName(this.mActivity, i);
    }
}
