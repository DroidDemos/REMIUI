package com.xiaomi.passport.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.SysHelper;

public class AccountRegSuccessFragment extends Fragment {
    public static final String ARGS_ACCOUNT = "account";
    public static final String ARGS_PASSWORD = "password";
    public static final String ARGS_REG_TYPE = "regtype";
    public static final int REG_EMAIL = 1;
    public static final int REG_PHONE = 2;
    private static final String TAG = "AccountRegSuccessFragment";
    private String mAccountArg;
    private String mPackageName;
    private int mRegType;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.passport_account_register_success, container, false);
        Bundle args = getArguments();
        TextView tvNotice = (TextView) v.findViewById(R.id.tv_notice);
        this.mRegType = args.getInt(ARGS_REG_TYPE);
        this.mAccountArg = args.getString(ARGS_ACCOUNT);
        this.mPackageName = args.getString(AccountManager.KEY_ANDROID_PACKAGE_NAME);
        Object[] objArr;
        if (this.mRegType == REG_PHONE) {
            objArr = new Object[REG_EMAIL];
            objArr[0] = this.mAccountArg;
            tvNotice.setText(getString(R.string.passport_reg_success_summary, objArr));
        } else if (this.mRegType == REG_EMAIL) {
            objArr = new Object[REG_EMAIL];
            objArr[0] = this.mAccountArg;
            tvNotice.setText(getString(R.string.passport_email_reg_success_summary, objArr));
        }
        ((Button) v.findViewById(R.id.btn_finish)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Bundle args = AccountRegSuccessFragment.this.getArguments();
                AccountHelper.navigateToAutoLogin(AccountRegSuccessFragment.this.getActivity(), args.getString(AccountRegSuccessFragment.ARGS_ACCOUNT), args.getString(AccountRegSuccessFragment.ARGS_PASSWORD), AccountRegSuccessFragment.this.mPackageName);
                AccountRegSuccessFragment.this.getActivity().finish();
            }
        });
        return v;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        SysHelper.setSoftInputVisibility(getActivity(), REG_PHONE);
    }
}
