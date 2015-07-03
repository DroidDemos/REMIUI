package com.xiaomi.passport.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.account.R;
import com.xiaomi.passport.Constants;

public class WelcomeFragment extends Fragment implements OnClickListener {
    private static final String TAG = "WelcomeFragment";
    private String addAccountPrompt;
    private Button mButtonLogin;
    private Button mButtonReg;
    private TextView mPromptText;
    private WelcomeActionListener mWelcomeActionListener;

    public interface WelcomeActionListener {
        void onGoBack();

        void onSkipLogin();

        void onStartSignIn(String str);

        void onStartSignUp();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.addAccountPrompt = args.getString(Constants.EXTRA_ADD_ACCOUNT_PROMPT);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.passport_welcome, container, false);
        this.mButtonLogin = (Button) view.findViewById(R.id.btn_start_login);
        this.mButtonLogin.setOnClickListener(this);
        this.mButtonReg = (Button) view.findViewById(R.id.btn_reg);
        this.mButtonReg.setOnClickListener(this);
        if (!TextUtils.isEmpty(this.addAccountPrompt)) {
            this.mPromptText = (TextView) view.findViewById(R.id.add_account_prompt);
            this.mPromptText.setText(this.addAccountPrompt);
            this.mPromptText.setVisibility(0);
        }
        return view;
    }

    public void onClick(View view) {
        if (view == this.mButtonLogin) {
            Bundle args = getArguments();
            this.mWelcomeActionListener.onStartSignIn(args != null ? args.getString(Constants.EXTRA_SERVICE_URL) : null);
        } else if (view == this.mButtonReg) {
            this.mWelcomeActionListener.onStartSignUp();
        }
    }

    public void setWelcomeActionListener(WelcomeActionListener welcomeActionListener) {
        this.mWelcomeActionListener = welcomeActionListener;
    }
}
