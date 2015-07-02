package com.xiaomi.activate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateServiceResponse;
import com.xiaomi.miui.analyticstracker.AnalyticsTracker;
import com.xiaomi.xmsf.R;
import miui.telephony.CloudTelephonyManager;

public class ActivatePopupActivity extends Activity implements OnClickListener {
    private static final boolean DEBUG = true;
    private static final String TAG = "ActivatePopupActivity";
    private static boolean mQueryDiagnosis;
    private static ActivatePopupActivity sInstance;
    private int mActivateMethod;
    private AnalyticsTracker mAnalytics;
    private Button mButtonCancel;
    private Button mButtonConfirm;
    private TextView mCountryCodeView;
    private String mDefaultPhoneNumber;
    private TextView mDescView;
    private AlertDialog mDialog;
    private int mFirstSimIndex;
    private int mLastSimIndex;
    private View mPhoneGroup;
    private EditText mPhoneView;
    private boolean mPromptOnly;
    private int mReason;
    private ActivateServiceResponse mResponse;
    private int mSimCount;
    private String mSource;
    private TextWatcher mTextWatcher;
    private TextView mTitleView;

    private class DiagnosisIntentSpan extends ClickableSpan {
        private DiagnosisIntentSpan() {
        }

        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(ActivatePopupActivity.DEBUG);
        }

        public void onClick(View widget) {
            ActivatePopupActivity.this.startActivity(new Intent(ActivatePopupActivity.this, ActivateDiagnosisDetailActivity.class));
        }
    }

    public ActivatePopupActivity() {
        this.mTextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                ActivatePopupActivity.this.mButtonConfirm.setEnabled(s.length() > 0 ? ActivatePopupActivity.DEBUG : false);
            }
        };
    }

    static {
        sInstance = null;
        mQueryDiagnosis = false;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sInstance = this;
        this.mAnalytics = AnalyticsTracker.getInstance();
        this.mAnalytics.startSession(this);
        final View root = LayoutInflater.from(this).inflate(R.layout.micloud_activate, null);
        this.mButtonCancel = (Button) root.findViewById(R.id.cancel);
        this.mButtonConfirm = (Button) root.findViewById(R.id.confirm);
        this.mTitleView = (TextView) root.findViewById(16908310);
        this.mDescView = (TextView) root.findViewById(R.id.desc);
        this.mPhoneGroup = root.findViewById(R.id.phone_group);
        this.mCountryCodeView = (TextView) root.findViewById(R.id.country_code);
        this.mPhoneView = (EditText) root.findViewById(R.id.phone);
        this.mButtonCancel.setOnClickListener(this);
        this.mButtonConfirm.setOnClickListener(this);
        Intent intent = getIntent();
        final int simIndex = intent.getIntExtra("extra_sim_index", -1);
        this.mReason = intent.getIntExtra("extra_activate_reason", 0);
        this.mSource = intent.getStringExtra("extra_activate_source");
        this.mPromptOnly = intent.getBooleanExtra("extra_activate_prompt_only", false);
        this.mDefaultPhoneNumber = intent.getStringExtra("extra_activate_default_phone_number");
        this.mResponse = (ActivateServiceResponse) intent.getParcelableExtra("extra_activate_prompt_extra");
        int featureIndex = intent.getIntExtra("extra_activate_feature_index", 0);
        final String intro = intent.getStringExtra("extra_activate_intro");
        final String feature = getString(new int[]{R.string.activate_feature_mx, R.string.activate_feature_call_log_sync, R.string.activate_feature_mms_sync, R.string.activate_feature_find_device}[featureIndex]);
        this.mSimCount = CloudTelephonyManager.getMultiSimCount();
        if (simIndex != -1) {
            this.mLastSimIndex = simIndex;
            this.mFirstSimIndex = simIndex;
        } else {
            this.mFirstSimIndex = 0;
            this.mLastSimIndex = this.mSimCount - 1;
        }
        final Context context = this;
        new AsyncTask<Void, Void, Void>() {
            int unactivatedCount;
            int unactivatedSimIndex;

            protected Void doInBackground(Void... params) {
                ActivateManager am = ActivateManager.get(context);
                for (int i = ActivatePopupActivity.this.mFirstSimIndex; i <= ActivatePopupActivity.this.mLastSimIndex; i++) {
                    Bundle info = am.blockingGetActivateInfo(i);
                    if (info == null) {
                        ActivatePopupActivity.this.finish();
                        break;
                    }
                    boolean inserted = info.getBoolean("sim_inserted");
                    int status = info.getInt("activate_status");
                    if (inserted && status == 1) {
                        this.unactivatedCount++;
                        this.unactivatedSimIndex = i;
                    }
                }
                return null;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            protected void onPostExecute(java.lang.Void r26) {
                /*
                r25 = this;
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.isFinishing();
                if (r3 == 0) goto L_0x000b;
            L_0x000a:
                return;
            L_0x000b:
                r0 = r25;
                r3 = r0.unactivatedCount;
                if (r3 != 0) goto L_0x009e;
            L_0x0011:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 2;
                if (r3 != r4) goto L_0x0086;
            L_0x001c:
                r3 = "ActivatePopupActivity";
                r4 = "Register account on an activated SIM. Use auto-activate";
                android.util.Log.w(r3, r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = 1;
                r3.mActivateMethod = r4;
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPromptOnly;
                if (r3 != 0) goto L_0x004f;
            L_0x0035:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r2 = com.xiaomi.accountsdk.activate.ActivateManager.get(r3);
                r0 = r25;
                r3 = r3;
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = r4.mActivateMethod;
                r5 = 0;
                r6 = 0;
                r7 = 0;
                r2.startActivateSim(r3, r4, r5, r6, r7);
            L_0x004f:
                r10 = new android.content.Intent;
                r10.<init>();
                r3 = "extra_activate_method";
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = r4.mActivateMethod;
                r10.putExtra(r3, r4);
                r3 = "extra_sim_index";
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = r4.mFirstSimIndex;
                r10.putExtra(r3, r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = -1;
                r3.setResult(r4, r10);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = 0;
                r3.response(r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3.finish();
                goto L_0x000a;
            L_0x0086:
                r3 = "ActivatePopupActivity";
                r4 = "All inserted sims are activated, finish.";
                android.util.Log.w(r3, r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = 2;
                r3.response(r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3.finish();
                goto L_0x000a;
            L_0x009e:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = 2;
                r3.mActivateMethod = r4;
                r0 = r25;
                r3 = r2;
                r0 = r25;
                r4 = r0.unactivatedSimIndex;
                r20 = miui.telephony.CloudTelephonyManager.getSimOperator(r3, r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mActivateMethod;
                r4 = 3;
                if (r3 != r4) goto L_0x00cc;
            L_0x00bd:
                r3 = r20.length();
                r4 = 3;
                if (r3 > r4) goto L_0x00cc;
            L_0x00c4:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = 2;
                r3.mActivateMethod = r4;
            L_0x00cc:
                r3 = 9;
                r0 = new int[r3];
                r22 = r0;
                r22 = {2131165227, 2131165234, 2131165241, 2131165245, 2131165248, 2131165254, 2131165257, 2131165251, 2131165260};
                r3 = 9;
                r9 = new int[r3];
                r9 = {2131165226, 2131165226, 17039370, 2131165247, 2131165250, 2131165256, 2131165259, 2131165253, 2131165262};
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                if (r3 == 0) goto L_0x00f1;
            L_0x00e6:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 1;
                if (r3 != r4) goto L_0x029d;
            L_0x00f1:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mTitleView;
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r5 = r5.mReason;
                r5 = r22[r5];
                r6 = 1;
                r6 = new java.lang.Object[r6];
                r7 = 0;
                r0 = r25;
                r0 = r4;
                r23 = r0;
                r6[r7] = r23;
                r4 = r4.getString(r5, r6);
                r3.setText(r4);
            L_0x011a:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mButtonConfirm;
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = r4.mReason;
                r4 = r9[r4];
                r3.setText(r4);
                r0 = r25;
                r3 = r5;
                if (r3 != 0) goto L_0x02b4;
            L_0x0135:
                r12 = "";
            L_0x0137:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 3;
                if (r3 < r4) goto L_0x02dc;
            L_0x0142:
                r3 = 9;
                r13 = new int[r3];
                r13 = {0, 0, 0, 2131165246, 2131165249, 2131165255, 2131165258, 2131165252, 2131165261};
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = r4.mReason;
                r4 = r13[r4];
                r14 = r3.getString(r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 8;
                if (r3 != r4) goto L_0x0198;
            L_0x0167:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = 2131165261; // 0x7f07004d float:1.7944734E38 double:1.052935541E-314;
                r5 = 1;
                r5 = new java.lang.Object[r5];
                r6 = 0;
                r0 = r25;
                r7 = com.xiaomi.activate.ActivatePopupActivity.this;
                r0 = r25;
                r0 = com.xiaomi.activate.ActivatePopupActivity.this;
                r23 = r0;
                r23 = r23.mFirstSimIndex;
                r0 = r25;
                r0 = com.xiaomi.activate.ActivatePopupActivity.this;
                r24 = r0;
                r24 = r24.mLastSimIndex;
                r0 = r23;
                r1 = r24;
                r7 = com.xiaomi.activate.ActivateUploadDiagnosisService.getTraceIdOfDiagnosis(r7, r0, r1);
                r5[r6] = r7;
                r14 = r3.getString(r4, r5);
            L_0x0198:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mDescView;
                r4 = new java.lang.StringBuilder;
                r4.<init>();
                r4 = r4.append(r12);
                r4 = r4.append(r14);
                r4 = r4.toString();
                r3.setText(r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 5;
                if (r3 == r4) goto L_0x01ca;
            L_0x01bf:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 6;
                if (r3 != r4) goto L_0x02cd;
            L_0x01ca:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneGroup;
                r4 = 0;
                r3.setVisibility(r4);
            L_0x01d6:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneGroup;
                r3 = r3.getVisibility();
                if (r3 != 0) goto L_0x0250;
            L_0x01e4:
                r3 = 0;
                r4 = 3;
                r0 = r20;
                r17 = r0.substring(r3, r4);
                r3 = new java.lang.StringBuilder;
                r3.<init>();
                r4 = "+";
                r3 = r3.append(r4);
                r4 = miui.telephony.phonenumber.CountryCodeConverter.getCountryCode(r17);
                r3 = r3.append(r4);
                r8 = r3.toString();
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mCountryCodeView;
                r3.setText(r8);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mDefaultPhoneNumber;
                r3 = android.text.TextUtils.isEmpty(r3);
                if (r3 != 0) goto L_0x04f2;
            L_0x021c:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r18 = r3.mDefaultPhoneNumber;
            L_0x0224:
                r3 = android.text.TextUtils.isEmpty(r18);
                if (r3 != 0) goto L_0x0250;
            L_0x022a:
                r19 = miui.telephony.PhoneNumberUtils.PhoneNumber.parse(r18);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneView;
                r4 = r19.getEffectiveNumber();
                r3.setText(r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneView;
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = r4.mTextWatcher;
                r3.addTextChangedListener(r4);
            L_0x0250:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 8;
                if (r3 != r4) goto L_0x0274;
            L_0x025c:
                r0 = r25;
                r3 = r6;
                r4 = 2131230725; // 0x7f080005 float:1.807751E38 double:1.0529678846E-314;
                r15 = r3.findViewById(r4);
                r15 = (android.widget.TextView) r15;
                r3 = 0;
                r15.setVisibility(r3);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3.setDiagnosisInfoLink(r15);
            L_0x0274:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = new android.app.AlertDialog$Builder;
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4.<init>(r5);
                r0 = r25;
                r5 = r6;
                r4 = r4.setView(r5);
                r5 = new com.xiaomi.activate.ActivatePopupActivity$2$1;
                r0 = r25;
                r5.<init>();
                r4 = r4.setOnCancelListener(r5);
                r4 = r4.show();
                r3.mDialog = r4;
                goto L_0x000a;
            L_0x029d:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mTitleView;
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = r4.mReason;
                r4 = r22[r4];
                r3.setText(r4);
                goto L_0x011a;
            L_0x02b4:
                r3 = new java.lang.StringBuilder;
                r3.<init>();
                r0 = r25;
                r4 = r5;
                r3 = r3.append(r4);
                r4 = " ";
                r3 = r3.append(r4);
                r12 = r3.toString();
                goto L_0x0137;
            L_0x02cd:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneGroup;
                r4 = 8;
                r3.setVisibility(r4);
                goto L_0x01d6;
            L_0x02dc:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 2;
                if (r3 != r4) goto L_0x03b8;
            L_0x02e7:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mActivateMethod;
                r4 = 2;
                if (r3 != r4) goto L_0x036e;
            L_0x02f2:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mAnalytics;
                r4 = "show_activate_popup_uplink";
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r5 = r5.mSource;
                com.xiaomi.activate.AnalyticsHelper.trackEvent(r3, r4, r5);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneGroup;
                r4 = 8;
                r3.setVisibility(r4);
                r0 = r25;
                r3 = r0.unactivatedCount;
                r4 = 1;
                if (r3 != r4) goto L_0x0356;
            L_0x031b:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r0 = r25;
                r4 = r0.unactivatedSimIndex;
                r3 = com.xiaomi.activate.ActivateHelper.hasLocalGateway(r3, r4);
                if (r3 == 0) goto L_0x0352;
            L_0x0329:
                r11 = 2131165242; // 0x7f07003a float:1.7944696E38 double:1.0529355317E-314;
            L_0x032c:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mDescView;
                r4 = new java.lang.StringBuilder;
                r4.<init>();
                r4 = r4.append(r12);
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r5 = r5.getString(r11);
                r4 = r4.append(r5);
                r4 = r4.toString();
                r3.setText(r4);
                goto L_0x01d6;
            L_0x0352:
                r11 = 2131165243; // 0x7f07003b float:1.7944698E38 double:1.052935532E-314;
                goto L_0x032c;
            L_0x0356:
                r3 = "ActivatePopupActivity";
                r4 = "Unexpected multi unactivated sim cards on registering account";
                android.util.Log.e(r3, r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = 3;
                r3.response(r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3.finish();
                goto L_0x000a;
            L_0x036e:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mAnalytics;
                r4 = "show_activate_popup_downlink";
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r5 = r5.mSource;
                com.xiaomi.activate.AnalyticsHelper.trackEvent(r3, r4, r5);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneGroup;
                r4 = 0;
                r3.setVisibility(r4);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mDescView;
                r4 = new java.lang.StringBuilder;
                r4.<init>();
                r4 = r4.append(r12);
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r6 = 2131165244; // 0x7f07003c float:1.79447E38 double:1.0529355327E-314;
                r5 = r5.getString(r6);
                r4 = r4.append(r5);
                r4 = r4.toString();
                r3.setText(r4);
                goto L_0x01d6;
            L_0x03b8:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mReason;
                r4 = 1;
                if (r3 != r4) goto L_0x043e;
            L_0x03c3:
                r16 = 1;
            L_0x03c5:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mActivateMethod;
                r4 = 2;
                if (r3 != r4) goto L_0x0496;
            L_0x03d0:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mAnalytics;
                r4 = "show_activate_popup_uplink";
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r5 = r5.mSource;
                com.xiaomi.activate.AnalyticsHelper.trackEvent(r3, r4, r5);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneGroup;
                r4 = 8;
                r3.setVisibility(r4);
                r0 = r25;
                r3 = r0.unactivatedCount;
                r4 = 1;
                if (r3 != r4) goto L_0x044f;
            L_0x03f9:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r0 = r25;
                r4 = r0.unactivatedSimIndex;
                r3 = com.xiaomi.activate.ActivateHelper.hasLocalGateway(r3, r4);
                if (r3 == 0) goto L_0x0445;
            L_0x0407:
                if (r16 == 0) goto L_0x0441;
            L_0x0409:
                r11 = 2131165235; // 0x7f070033 float:1.7944681E38 double:1.052935528E-314;
            L_0x040c:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mDescView;
                r4 = new java.lang.StringBuilder;
                r4.<init>();
                r4 = r4.append(r12);
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r6 = 1;
                r6 = new java.lang.Object[r6];
                r7 = 0;
                r0 = r25;
                r0 = r4;
                r23 = r0;
                r6[r7] = r23;
                r5 = r5.getString(r11, r6);
                r4 = r4.append(r5);
                r4 = r4.toString();
                r3.setText(r4);
                goto L_0x01d6;
            L_0x043e:
                r16 = 0;
                goto L_0x03c5;
            L_0x0441:
                r11 = 2131165228; // 0x7f07002c float:1.7944667E38 double:1.052935525E-314;
                goto L_0x040c;
            L_0x0445:
                if (r16 == 0) goto L_0x044b;
            L_0x0447:
                r11 = 2131165236; // 0x7f070034 float:1.7944683E38 double:1.0529355287E-314;
            L_0x044a:
                goto L_0x040c;
            L_0x044b:
                r11 = 2131165229; // 0x7f07002d float:1.794467E38 double:1.0529355253E-314;
                goto L_0x044a;
            L_0x044f:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r4 = 0;
                r3 = com.xiaomi.activate.ActivateHelper.hasLocalGateway(r3, r4);
                if (r3 == 0) goto L_0x0474;
            L_0x045a:
                r3 = 1;
            L_0x045b:
                r0 = r25;
                r4 = com.xiaomi.activate.ActivatePopupActivity.this;
                r5 = 1;
                r4 = com.xiaomi.activate.ActivateHelper.hasLocalGateway(r4, r5);
                if (r4 == 0) goto L_0x0476;
            L_0x0466:
                r4 = 1;
            L_0x0467:
                r21 = r3 + r4;
                r3 = 2;
                r0 = r21;
                if (r0 != r3) goto L_0x047c;
            L_0x046e:
                if (r16 == 0) goto L_0x0478;
            L_0x0470:
                r11 = 2131165238; // 0x7f070036 float:1.7944687E38 double:1.0529355297E-314;
            L_0x0473:
                goto L_0x040c;
            L_0x0474:
                r3 = 0;
                goto L_0x045b;
            L_0x0476:
                r4 = 0;
                goto L_0x0467;
            L_0x0478:
                r11 = 2131165231; // 0x7f07002f float:1.7944673E38 double:1.0529355262E-314;
                goto L_0x0473;
            L_0x047c:
                r3 = 1;
                r0 = r21;
                if (r0 != r3) goto L_0x048b;
            L_0x0481:
                if (r16 == 0) goto L_0x0487;
            L_0x0483:
                r11 = 2131165237; // 0x7f070035 float:1.7944685E38 double:1.052935529E-314;
            L_0x0486:
                goto L_0x040c;
            L_0x0487:
                r11 = 2131165230; // 0x7f07002e float:1.7944671E38 double:1.052935526E-314;
                goto L_0x0486;
            L_0x048b:
                if (r16 == 0) goto L_0x0492;
            L_0x048d:
                r11 = 2131165239; // 0x7f070037 float:1.794469E38 double:1.05293553E-314;
            L_0x0490:
                goto L_0x040c;
            L_0x0492:
                r11 = 2131165232; // 0x7f070030 float:1.7944675E38 double:1.0529355267E-314;
                goto L_0x0490;
            L_0x0496:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mAnalytics;
                r4 = "show_activate_popup_downlink";
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r5 = r5.mSource;
                com.xiaomi.activate.AnalyticsHelper.trackEvent(r3, r4, r5);
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mPhoneGroup;
                r4 = 0;
                r3.setVisibility(r4);
                if (r16 == 0) goto L_0x04ee;
            L_0x04b9:
                r11 = 2131165240; // 0x7f070038 float:1.7944692E38 double:1.0529355307E-314;
            L_0x04bc:
                r0 = r25;
                r3 = com.xiaomi.activate.ActivatePopupActivity.this;
                r3 = r3.mDescView;
                r4 = new java.lang.StringBuilder;
                r4.<init>();
                r4 = r4.append(r12);
                r0 = r25;
                r5 = com.xiaomi.activate.ActivatePopupActivity.this;
                r6 = 1;
                r6 = new java.lang.Object[r6];
                r7 = 0;
                r0 = r25;
                r0 = r4;
                r23 = r0;
                r6[r7] = r23;
                r5 = r5.getString(r11, r6);
                r4 = r4.append(r5);
                r4 = r4.toString();
                r3.setText(r4);
                goto L_0x01d6;
            L_0x04ee:
                r11 = 2131165233; // 0x7f070031 float:1.7944677E38 double:1.052935527E-314;
                goto L_0x04bc;
            L_0x04f2:
                r0 = r25;
                r3 = r2;
                r0 = r25;
                r4 = r0.unactivatedSimIndex;
                r18 = miui.telephony.CloudTelephonyManager.getLine1Number(r3, r4);
                goto L_0x0224;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.activate.ActivatePopupActivity.2.onPostExecute(java.lang.Void):void");
            }
        }.execute(new Void[0]);
    }

    public void onStop() {
        if (this.mReason != 8) {
            finish();
        }
        super.onStop();
    }

    private void setDiagnosisInfoLink(TextView view) {
        SpannableStringBuilder ss = new SpannableStringBuilder();
        String str = getString(R.string.activate_popup_link_diagnosis_detail);
        ss.append(str);
        ss.setSpan(new DiagnosisIntentSpan(), 0, str.length(), 33);
        view.setText(ss);
        view.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void onDestroy() {
        this.mAnalytics.endSession();
        if (sInstance == this) {
            sInstance = null;
        }
        super.onDestroy();
    }

    public void finish() {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
        super.finish();
    }

    private boolean shouldQueryDiagnosis() {
        return (this.mReason == 8 || !mQueryDiagnosis) ? false : DEBUG;
    }

    public void onClick(View view) {
        if (this.mButtonCancel == view) {
            if (shouldQueryDiagnosis()) {
                Intent intent = new Intent();
                intent.setClass(this, getClass());
                intent.putExtra("extra_activate_reason", 8);
                finish();
                startActivity(intent);
                return;
            }
            if (this.mReason == 8) {
                AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.CANCEL_ACTIVATE_UPLOAD_DIAGNOSIS_INFO);
            }
            setResult(0);
            response(1);
            finish();
        } else if (this.mButtonConfirm != view) {
        } else {
            if (this.mReason == 8) {
                AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.CONFIRM_ACTIVATE_UPLOAD_DIAGNOSIS_INFO);
                ActivateUploadDiagnosisService.startUploadDiagnosis(this, this.mFirstSimIndex, this.mLastSimIndex);
                setResult(0);
                response(1);
                finish();
                return;
            }
            String phone;
            if (this.mActivateMethod == 3) {
                AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.CONFIRM_ACTIVATE_POPUP_DOWNLINK, this.mSource);
            } else {
                AnalyticsHelper.trackEvent(this.mAnalytics, AnalyticsHelper.CONFIRM_ACTIVATE_POPUP_UPLINK, this.mSource);
            }
            if (this.mPhoneGroup.getVisibility() == 0) {
                phone = this.mCountryCodeView.getText().toString() + this.mPhoneView.getText().toString();
            } else {
                phone = null;
            }
            Intent data = new Intent();
            data.putExtra("extra_activate_method", this.mActivateMethod);
            data.putExtra("extra_activate_phone", phone);
            data.putExtra("extra_sim_index", this.mFirstSimIndex);
            setResult(-1, data);
            if (!this.mPromptOnly) {
                ActivateManager cm = ActivateManager.get(this);
                for (int i = this.mFirstSimIndex; i <= this.mLastSimIndex; i++) {
                    cm.startActivateSim(i, this.mActivateMethod, phone, false, null);
                }
            }
            response(0);
            finish();
        }
    }

    private void response(int code) {
        if (this.mResponse != null) {
            Bundle b = new Bundle();
            b.putInt("prompt_activate_result", code);
            this.mResponse.onResult(b);
        }
    }

    public static void dismiss() {
        if (sInstance != null) {
            hideSoftInputMethod(sInstance);
            sInstance.response(1);
            sInstance.finish();
            sInstance = null;
        }
    }

    private static void hideSoftInputMethod(Activity activity) {
        ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }
}
