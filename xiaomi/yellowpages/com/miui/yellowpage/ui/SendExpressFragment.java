package com.miui.yellowpage.ui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.alipay.mobilesecuritysdk.constant.ConfigConstant;
import com.alipay.sdk.cons.MiniDefine;
import com.miui.internal.widget.DropDownPopupWindow;
import com.miui.internal.widget.DropDownPopupWindow.DefaultContainerController;
import com.miui.internal.widget.DropDownPopupWindow.ListController;
import com.miui.yellowpage.R;
import com.miui.yellowpage.a.g;
import com.miui.yellowpage.activity.ExpressCompanyListActivity;
import com.miui.yellowpage.activity.InternalWebActivity;
import com.miui.yellowpage.b.a;
import com.miui.yellowpage.base.model.Image.ImageFormat;
import com.miui.yellowpage.base.model.Image.ImageProcessor;
import com.miui.yellowpage.base.model.YellowPageImage;
import com.miui.yellowpage.base.utils.GeoLocationManager;
import com.miui.yellowpage.base.utils.Preference;
import com.miui.yellowpage.base.utils.Sim;
import com.miui.yellowpage.base.utils.YellowPageImgLoader;
import com.miui.yellowpage.model.ExpressAddress;
import com.miui.yellowpage.model.ExpressCompany;
import com.miui.yellowpage.model.ExpressOrder;
import com.miui.yellowpage.model.ExpressOrder.Cargo;
import com.miui.yellowpage.utils.d;
import com.miui.yellowpage.utils.s;
import com.miui.yellowpage.widget.LoadingProgressView;
import com.ta.utdid2.core.persistent.TransactionXMLFile;
import java.io.Serializable;
import java.util.List;
import miui.graphics.BitmapFactory;
import miui.graphics.BitmapFactory.CropOption;
import org.json.JSONException;
import org.json.JSONObject;

public class SendExpressFragment extends i {
    private static int xs;
    private ExpressOrder cP;
    private EditText hc;
    private TextView jl;
    private Handler mHandler;
    private LoadingProgressView mLoadingProgressView;
    private View mRootView;
    private Button xA;
    private View xB;
    private View xC;
    private TextView xD;
    private ImageView xE;
    private EditText xF;
    private DropDownPopupWindow xG;
    private CompanyListAdapter xH;
    private ExpressCompany xI;
    private X xJ;
    private GetCurrentLocationTask xK;
    private OnCreateOrderListener xL;
    private int xM;
    private volatile boolean xN;
    private volatile boolean xO;
    private boolean xP;
    private String xQ;
    private int xR;
    private int xS;
    private String xT;
    private s xU;
    private TextWatcher xV;
    private Runnable xW;
    private OnClickListener xX;
    private ImageProcessor xY;
    private String xt;
    private View xu;
    private ImageView xv;
    private ImageView xw;
    private TextView xx;
    private TextView xy;
    private VerticallyClippedButton xz;

    public interface OnCreateOrderListener {
        void onCreateOrder(String str, ExpressOrder expressOrder);
    }

    class Animator {
        private Animator() {
        }

        public void setValue(float f) {
            if (((double) f) > 0.2d && !SendExpressFragment.this.xP) {
                SendExpressFragment.this.xz.setBackgroundResource(R.drawable.express_select_city_bg);
                SendExpressFragment.this.xz.setPadding(SendExpressFragment.this.hc.getPaddingLeft(), SendExpressFragment.this.hc.getPaddingTop(), SendExpressFragment.this.hc.getPaddingRight(), SendExpressFragment.this.hc.getPaddingBottom());
                SendExpressFragment.this.xP = true;
            }
            SendExpressFragment.this.xB.setTranslationY(((float) SendExpressFragment.this.hc.getMeasuredHeight()) * f);
            SendExpressFragment.this.xz.d(f);
            SendExpressFragment.this.xz.invalidate();
        }
    }

    class CompanyListAdapter extends a {
        private CompanyListAdapter(Context context) {
            super(context);
        }

        public View newView(Context context, ExpressCompany expressCompany, ViewGroup viewGroup) {
            return LayoutInflater.from(context).inflate(R.layout.express_company_simple_list_item, viewGroup, false);
        }

        public void bindView(View view, int i, ExpressCompany expressCompany) {
            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            TextView textView = (TextView) view.findViewById(R.id.name);
            RadioButton radioButton = (RadioButton) view.findViewById(R.id.radio);
            SendExpressFragment.this.a((TextView) view.findViewById(R.id.description), expressCompany.ef());
            SendExpressFragment.this.b(expressCompany.ei(), imageView);
            textView.setText(expressCompany.ee());
            if (TextUtils.equals(SendExpressFragment.this.xI.ed(), expressCompany.ed())) {
                textView.setTextColor(SendExpressFragment.this.xS);
                radioButton.setChecked(true);
                return;
            }
            textView.setTextColor(SendExpressFragment.this.xR);
            radioButton.setChecked(false);
        }
    }

    class CompanyListAnimator {
        private CompanyListAnimator() {
        }

        public void setWidthPercent(float f) {
            if (SendExpressFragment.xs == 0) {
                SendExpressFragment.xs = SendExpressFragment.this.xC.getMeasuredWidth();
            }
            SendExpressFragment.this.xC.getLayoutParams().width = (int) (((float) SendExpressFragment.xs) * f);
            SendExpressFragment.this.xC.requestLayout();
        }
    }

    class GetCurrentLocationTask extends AsyncTask {
        private boolean mIsLocationing;

        private GetCurrentLocationTask() {
        }

        protected void onPreExecute() {
            this.mIsLocationing = true;
            SendExpressFragment.this.xF.setHint(R.string.express_contact_address_finding);
            SendExpressFragment.this.xF.clearFocus();
            SendExpressFragment.this.xE.setVisibility(8);
        }

        protected Address doInBackground(Void... voidArr) {
            GeoLocationManager instance = GeoLocationManager.getInstance(SendExpressFragment.this.JP);
            Location location = instance.getLocation();
            if (location != null) {
                return instance.getAddress(SendExpressFragment.this.JP, location.getLongitude(), location.getLatitude());
            }
            return null;
        }

        protected void onPostExecute(Address address) {
            if (this.mIsLocationing) {
                if (address != null) {
                    SendExpressFragment.this.b(address);
                    ExpressAddress au = SendExpressFragment.this.cP.au();
                    au.setAdminArea(address.getAdminArea());
                    au.setSubAdminArea(address.getSubAdminArea());
                    au.setLocality(address.getLocality());
                    au.setSubLocality(address.getSubLocality());
                    SendExpressFragment.this.xz.setText(au.eV());
                }
                SendExpressFragment.this.fT();
            }
        }

        public void finish() {
            this.mIsLocationing = false;
        }

        public boolean isLocationing() {
            return this.mIsLocationing;
        }
    }

    class GetPhoneNumberTask extends AsyncTask {
        private GetPhoneNumberTask() {
        }

        protected void onPreExecute() {
            SendExpressFragment.this.hc.setHint(R.string.express_contact_phone_finding);
        }

        protected String doInBackground(Void... voidArr) {
            if (Sim.isSimCardReady(SendExpressFragment.this.JP, Sim.DEFAULT_SIM_INDEX)) {
                return Sim.getPhoneNumber(SendExpressFragment.this.JP, Sim.DEFAULT_SIM_INDEX);
            }
            return null;
        }

        protected void onPostExecute(String str) {
            if (!TextUtils.isEmpty(str)) {
                SendExpressFragment.this.xQ = str;
            }
            SendExpressFragment.this.hc.setHint(R.string.express_contact_phone);
            if (!SendExpressFragment.this.xO) {
                SendExpressFragment.this.hc.setText(SendExpressFragment.this.xQ);
            }
        }
    }

    public SendExpressFragment() {
        this.xU = new s() {
            public void onClick() {
                Intent intent = new Intent(SendExpressFragment.this.JP, InternalWebActivity.class);
                intent.putExtra("web_title", SendExpressFragment.this.getString(R.string.express_send_notice));
                intent.putExtra("web_url", "http://web.huangye.miui.com/portal/html/yellowpage/express-notice.html");
                SendExpressFragment.this.startActivity(intent);
            }
        };
        this.xV = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                SendExpressFragment.this.mHandler.removeCallbacks(SendExpressFragment.this.xW);
                SendExpressFragment.this.mHandler.postDelayed(SendExpressFragment.this.xW, 200);
            }
        };
        this.xW = new Runnable() {
            public void run() {
                SendExpressFragment.this.xA.setEnabled(SendExpressFragment.this.fQ());
            }
        };
        this.xX = new OnClickListener() {
            public void onClick(View view) {
                SendExpressFragment.this.y(true);
                SendExpressFragment.this.fS();
            }
        };
        this.xY = new ImageProcessor() {
            public Bitmap processImage(Bitmap bitmap) {
                return BitmapFactory.cropBitmap(bitmap, new CropOption(SendExpressFragment.this.xM, SendExpressFragment.this.xM, 0, 0));
            }
        };
    }

    public void a(OnCreateOrderListener onCreateOrderListener) {
        this.xL = onCreateOrderListener;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.xT = arguments.getString("company");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        f(bundle);
        View inflate = layoutInflater.inflate(R.layout.send_express_fragment, viewGroup, false);
        this.mLoadingProgressView = (LoadingProgressView) inflate.findViewById(R.id.loading_view);
        this.mRootView = inflate.findViewById(R.id.root);
        this.xB = inflate.findViewById(R.id.address_container);
        this.hc = (EditText) inflate.findViewById(R.id.phone);
        this.xF = (EditText) inflate.findViewById(R.id.address);
        this.jl = (TextView) inflate.findViewById(R.id.dest_city);
        this.xz = (VerticallyClippedButton) inflate.findViewById(R.id.src_city);
        this.xE = (ImageView) inflate.findViewById(R.id.location);
        this.xD = (TextView) inflate.findViewById(R.id.express_notice);
        this.xA = (Button) inflate.findViewById(R.id.create_order);
        this.xu = inflate.findViewById(R.id.express_company_container);
        this.xx = (TextView) inflate.findViewById(R.id.express_company_name);
        this.xy = (TextView) inflate.findViewById(R.id.express_company_description);
        this.xv = (ImageView) inflate.findViewById(R.id.express_company_icon);
        this.xw = (ImageView) inflate.findViewById(R.id.arrow);
        inflate.findViewById(R.id.express_company).setOnClickListener(this.xX);
        this.xC = inflate.findViewById(R.id.express_company_list);
        this.xC.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SendExpressFragment.this.startActivity(new Intent(SendExpressFragment.this.JP, ExpressCompanyListActivity.class));
            }
        });
        this.xG = new DropDownPopupWindow(this.JP, null, 0);
        ListView listView = new ListController(this.xG).getListView();
        if (this.xH == null) {
            this.xH = new CompanyListAdapter(this.JP);
        }
        listView.setChoiceMode(1);
        listView.setAdapter(this.xH);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                SendExpressFragment.this.xG.dismiss();
                SendExpressFragment.this.b((ExpressCompany) SendExpressFragment.this.xH.getItem(i));
            }
        });
        this.xF.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z && !SendExpressFragment.this.xN) {
                    SendExpressFragment.this.fV();
                }
                if (z && SendExpressFragment.this.xK != null && SendExpressFragment.this.xK.isLocationing()) {
                    SendExpressFragment.this.fT();
                }
            }
        });
        this.hc.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z && !SendExpressFragment.this.xO) {
                    SendExpressFragment.this.fU();
                }
            }
        });
        this.jl.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SendExpressFragment.this.startActivityForResult(new Intent("com.miui.yellowpage.action.PICK_REGION"), 1);
            }
        });
        this.xz.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SendExpressFragment.this.xK != null && SendExpressFragment.this.xK.isLocationing()) {
                    SendExpressFragment.this.fT();
                }
                SendExpressFragment.this.startActivityForResult(new Intent("com.miui.yellowpage.action.PICK_REGION"), 2);
            }
        });
        this.xE.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SendExpressFragment.this.requestLocation();
            }
        });
        this.xF.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(editable)) {
                    SendExpressFragment.this.xE.setVisibility(0);
                } else {
                    SendExpressFragment.this.xE.setVisibility(8);
                }
            }
        });
        this.xF.addTextChangedListener(this.xV);
        this.hc.addTextChangedListener(this.xV);
        this.jl.addTextChangedListener(this.xV);
        this.xz.addTextChangedListener(this.xV);
        this.xA.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SendExpressFragment.this.xL != null) {
                    ExpressAddress au = SendExpressFragment.this.cP.au();
                    au.setPhone(SendExpressFragment.this.hc.getText().toString());
                    au.be(SendExpressFragment.this.xF.getText().toString());
                    SendExpressFragment.this.fL();
                    SendExpressFragment.this.xL.onCreateOrder(SendExpressFragment.this.xI.ei(), SendExpressFragment.this.cP);
                }
            }
        });
        this.xM = (int) this.JP.getResources().getDimension(R.dimen.express_company_list_icon_size);
        this.mHandler = new Handler();
        this.xt = this.JP.getString(R.string.express_select_city);
        this.xR = this.JP.getResources().getColor(R.color.list_text_color_normal_light);
        this.xS = this.JP.getResources().getColor(R.color.checked_text_light);
        fP();
        if (TextUtils.isEmpty(this.cP.au().eV()) || TextUtils.isEmpty(this.hc.getText())) {
            fM();
        }
        return inflate;
    }

    public void onStart() {
        super.onStart();
        fO();
    }

    protected g I() {
        return this.mLoadingProgressView;
    }

    protected int J() {
        return 0;
    }

    protected int K() {
        return 0;
    }

    protected X L() {
        return this.xJ;
    }

    protected void a(dU dUVar) {
        if (dUVar.hasData()) {
            this.mRootView.setVisibility(0);
            this.xH.updateData(dUVar.list);
            if (this.xI == null) {
                if (TextUtils.isEmpty(this.xT)) {
                    this.xT = Preference.getString(this.JP, "pref_express_last_selected_company", null);
                }
                this.xI = f(dUVar.list);
                if (this.xI == null) {
                    this.xI = (ExpressCompany) dUVar.list.get(0);
                }
            }
            b(this.xI);
            return;
        }
        this.mRootView.setVisibility(8);
    }

    private void fL() {
        Preference.setString(this.JP, "pref_last_send_express_sender_info_key", this.cP.au().eW().toString());
    }

    private void fM() {
        ExpressAddress j;
        Object string = Preference.getString(this.JP, "pref_last_send_express_sender_info_key", ConfigConstant.WIRELESS_FILENAME);
        try {
            if (!TextUtils.isEmpty(string)) {
                j = ExpressAddress.j(new JSONObject(string));
                if (j == null) {
                    j = new ExpressAddress();
                }
                this.xQ = j.getPhone();
                if (TextUtils.isEmpty(this.hc.getText().toString())) {
                    new GetPhoneNumberTask().execute(new Void[0]);
                }
                if (TextUtils.isEmpty(this.cP.au().eV())) {
                    g(j);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        j = null;
        if (j == null) {
            j = new ExpressAddress();
        }
        this.xQ = j.getPhone();
        if (TextUtils.isEmpty(this.hc.getText().toString())) {
            new GetPhoneNumberTask().execute(new Void[0]);
        }
        if (TextUtils.isEmpty(this.cP.au().eV())) {
            g(j);
        }
    }

    private void g(ExpressAddress expressAddress) {
        if (TextUtils.isEmpty(expressAddress.eV())) {
            requestLocation();
            return;
        }
        expressAddress.setPhone(this.hc.getText().toString());
        this.cP.d(expressAddress);
        this.xz.setText(expressAddress.eV());
        this.xF.setText(expressAddress.eQ());
        if (!this.xN) {
            fN();
        }
    }

    private void fN() {
        ((LayoutParams) this.xB.getLayoutParams()).addRule(3, R.id.phone);
        this.xN = true;
        this.xz.setBackgroundResource(R.drawable.express_select_city_bg);
        this.xP = true;
        this.xz.d(1.0f);
    }

    private void requestLocation() {
        this.xK = new GetCurrentLocationTask();
        this.xK.execute(new Void[0]);
    }

    private ExpressCompany f(List list) {
        if (!TextUtils.isEmpty(this.xT)) {
            for (ExpressCompany expressCompany : list) {
                if (TextUtils.equals(expressCompany.ed(), this.xT)) {
                    return expressCompany;
                }
            }
        }
        return null;
    }

    private void fO() {
        CharSequence spannableStringBuilder = new SpannableStringBuilder(this.JP.getResources().getString(R.string.express_send_notice));
        spannableStringBuilder.setSpan(new d(this.xU), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(this.JP.getResources().getColor(R.color.hyper_link_text_color)), 0, spannableStringBuilder.length(), 33);
        this.xD.setText(spannableStringBuilder);
        this.xD.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void fP() {
        fR();
        if (this.xO) {
            fU();
        }
        if (this.xN) {
            fN();
        }
    }

    private void f(Bundle bundle) {
        if (bundle != null) {
            Serializable serializable = bundle.getSerializable("order");
            if (serializable != null) {
                this.cP = (ExpressOrder) serializable;
            }
            this.xN = bundle.getBoolean("address_mode");
            this.xO = bundle.getBoolean("phone_mode");
        }
        if (this.cP == null) {
            this.cP = new ExpressOrder();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("order", this.cP);
        bundle.putBoolean("address_mode", this.xN);
        bundle.putBoolean("phone_mode", this.xO);
    }

    private boolean fQ() {
        if (TextUtils.isEmpty(this.hc.getText().toString())) {
            return false;
        }
        CharSequence obj = this.xz.getText().toString();
        if (TextUtils.isEmpty(this.cP.au().eV()) || TextUtils.isEmpty(obj) || TextUtils.equals(obj, this.xt)) {
            return false;
        }
        obj = this.jl.getText().toString();
        if (TextUtils.isEmpty(this.cP.at().eV()) || TextUtils.isEmpty(obj) || TextUtils.equals(obj, this.xt) || TextUtils.isEmpty(this.xF.getText().toString())) {
            return false;
        }
        return true;
    }

    private void fR() {
        if (this.xI != null) {
            this.xx.setText(this.xI.ee());
            a(this.xy, this.xI.ef());
            b(this.xI.ei(), this.xv);
        }
        CharSequence eV = this.cP.at().eV();
        if (TextUtils.isEmpty(eV)) {
            this.jl.setText(this.xt);
        } else {
            this.jl.setText(eV);
        }
        Address au = this.cP.au();
        if (!TextUtils.isEmpty(au.getPhone())) {
            this.hc.setText(au.getPhone());
        }
        b(au);
        if (!TextUtils.isEmpty(au.eV())) {
            this.xz.setText(au.eV());
        }
    }

    private void b(ExpressCompany expressCompany) {
        this.xI = expressCompany;
        Preference.setString(this.JP, "pref_express_last_selected_company", this.xI.ed());
        Cargo as = this.cP.as();
        as.cv(this.xI.ed());
        as.cu(this.xI.ee());
        fR();
        b(this.xI.ei(), this.xv);
    }

    private void a(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(str);
        textView.setVisibility(0);
    }

    private void b(String str, ImageView imageView) {
        YellowPageImage yellowPageImage = new YellowPageImage(this.JP, str, this.xM, this.xM, ImageFormat.PNG);
        yellowPageImage.setImageProcessor(this.xY);
        YellowPageImgLoader.loadImage(this.JP, imageView, yellowPageImage, R.drawable.express_company_default_logo);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.xJ = new X(this);
        getLoaderManager().initLoader(0, null, this.xJ);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != 0 && intent != null) {
            switch (i) {
                case TransactionXMLFile.MODE_PRIVATE /*0*/:
                    Serializable serializable = intent.getExtras().getSerializable("extra_express_company");
                    if (serializable != null) {
                        this.xI = (ExpressCompany) serializable;
                        break;
                    }
                    break;
                case TransactionXMLFile.MODE_WORLD_READABLE /*1*/:
                    h(intent);
                    break;
                case TransactionXMLFile.MODE_WORLD_WRITEABLE /*2*/:
                    g(intent);
                    break;
            }
            fR();
        }
    }

    private void g(Intent intent) {
        ExpressAddress a = ExpressAddress.a((Address) intent.getExtras().getParcelable("extra_address"));
        this.xz.setText(a.eV());
        ExpressAddress au = this.cP.au();
        au.setAdminArea(a.getAdminArea());
        au.setSubAdminArea(a.getSubAdminArea());
        au.setLocality(a.getLocality());
        au.setSubLocality(a.getSubLocality());
    }

    private void h(Intent intent) {
        ExpressAddress a = ExpressAddress.a((Address) intent.getExtras().getParcelable("extra_address"));
        this.jl.setText(a.eV());
        this.cP.c(a);
    }

    public void onResume() {
        super.onResume();
        this.JP.setTitle((int) R.string.express_send);
        this.JP.k(true);
    }

    private void fS() {
        this.xw.setImageResource(R.drawable.expander_close);
        this.xG.setContainerController(new DefaultContainerController() {
            public void onDismiss() {
                SendExpressFragment.this.xw.setImageResource(R.drawable.expander_open);
                SendExpressFragment.this.y(false);
            }
        });
        this.xG.setAnchor(this.xu);
        this.xG.show();
    }

    private void fT() {
        this.xK.finish();
        this.xK = null;
        this.xF.setHint(R.string.express_contact_address);
        if (TextUtils.isEmpty(this.xF.getText())) {
            this.xE.setVisibility(0);
        }
    }

    private void b(Address address) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(address.getThoroughfare())) {
            stringBuilder.append(address.getThoroughfare());
        }
        if (!TextUtils.isEmpty(address.getSubThoroughfare())) {
            stringBuilder.append(address.getSubThoroughfare());
        }
        CharSequence stringBuilder2 = stringBuilder.toString();
        if (!TextUtils.isEmpty(stringBuilder2)) {
            this.xF.setText(stringBuilder2);
        }
    }

    private void fU() {
        this.xO = true;
        this.hc.setHint(R.string.express_contact_phone);
    }

    private void fV() {
        this.xN = true;
        this.xF.setHint(R.string.express_contact_address);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(new Animator(), MiniDefine.a, new float[]{0.0f, 1.0f});
        ofFloat.setDuration(300);
        ofFloat.start();
    }

    private void y(boolean z) {
        ObjectAnimator ofFloat = z ? ObjectAnimator.ofFloat(new CompanyListAnimator(), "WidthPercent", new float[]{1.0f, 0.0f}) : ObjectAnimator.ofFloat(new CompanyListAnimator(), "WidthPercent", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(300);
        ofFloat.start();
    }
}
