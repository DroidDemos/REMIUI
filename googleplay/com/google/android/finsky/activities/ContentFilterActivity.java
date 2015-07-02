package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.config.ContentLevel;
import com.google.android.finsky.config.G;
import com.google.android.finsky.layout.ButtonBar;
import com.google.android.finsky.layout.ButtonBar.ClickListener;
import com.google.android.finsky.layout.play.GenericUiElementNode;

public class ContentFilterActivity extends Activity implements ClickListener {
    private ButtonBar mButtonBar;
    private FinskyEventLog mEventLogger;
    private ContentLevel mLevel;
    private TextView mMoreInfoView;
    private GenericUiElementNode mNode;
    private RadioGroup mRadioButtonsView;

    public ContentFilterActivity() {
        this.mNode = new GenericUiElementNode(315, null, null, null);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_filter_activity);
        if (savedInstanceState == null) {
            this.mLevel = ContentLevel.importFromSettings(this);
        } else {
            this.mLevel = ContentLevel.convertFromLegacyValue(savedInstanceState.getInt("level"));
        }
        this.mRadioButtonsView = (RadioGroup) findViewById(R.id.level_radiobuttons);
        this.mMoreInfoView = (TextView) findViewById(R.id.more_info);
        this.mButtonBar = (ButtonBar) findViewById(R.id.button_bar);
        setupViews();
        setResult(0);
        this.mEventLogger = FinskyApp.get().getEventLogger();
        if (savedInstanceState == null) {
            this.mEventLogger.logPathImpression(0, this.mNode);
        }
    }

    private void setupRadioButton(LayoutInflater layoutInflater, ContentLevel level, int checkboxId, int stringResourceId) {
        boolean z = true;
        RadioButton button = (RadioButton) layoutInflater.inflate(R.layout.radio_button_row, this.mRadioButtonsView, false);
        button.setText(stringResourceId);
        button.setTag(level);
        button.setFocusable(true);
        if (this.mLevel != level) {
            z = false;
        }
        button.setChecked(z);
        button.setId(checkboxId);
        this.mRadioButtonsView.addView(button);
    }

    private void setupViews() {
        this.mRadioButtonsView.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);
        setupRadioButton(inflater, ContentLevel.EVERYONE, R.id.content_filter_everyone, R.string.content_filter_everyone);
        setupRadioButton(inflater, ContentLevel.LOW_MATURITY, R.id.content_filter_low_maturity, R.string.content_filter_low_maturity);
        setupRadioButton(inflater, ContentLevel.MEDIUM_MATURITY, R.id.content_filter_medium_maturity, R.string.content_filter_medium_maturity);
        setupRadioButton(inflater, ContentLevel.HIGH_MATURITY, R.id.content_filter_high_maturity, R.string.content_filter_high_maturity);
        setupRadioButton(inflater, ContentLevel.SHOW_ALL, R.id.content_filter_show_all_apps, R.string.content_filter_show_all_apps);
        this.mRadioButtonsView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ContentFilterActivity.this.mLevel = (ContentLevel) group.findViewById(checkedId).getTag();
            }
        });
        this.mMoreInfoView.setText(Html.fromHtml(String.format("%s <a href='%s'>%s</a>", new Object[]{getString(R.string.info_about_content_filter), G.contentFilterInfoUrl.get(), getString(R.string.more_about_content_filter)})));
        this.mMoreInfoView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mButtonBar.setPositiveButtonTitle((int) R.string.content_filter_ok);
        this.mButtonBar.setNegativeButtonTitle((int) R.string.content_filter_cancel);
        this.mButtonBar.setClickListener(this);
    }

    public void onSaveInstanceState(Bundle icicle) {
        super.onSaveInstanceState(icicle);
        icicle.putInt("level", this.mLevel.getValue());
    }

    public static String getLabel(Context context, int level) {
        Resources res = context.getResources();
        switch (level) {
            case -1:
                return res.getString(R.string.content_filter_no_rating);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return res.getString(R.string.content_filter_everyone);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return res.getString(R.string.content_filter_low_maturity);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                return res.getString(R.string.content_filter_medium_maturity);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                return res.getString(R.string.content_filter_high_maturity);
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                return res.getString(R.string.content_filter_show_all_apps);
            default:
                return null;
        }
    }

    public void onPositiveButtonClick() {
        this.mEventLogger.logClickEvent(254, null, this.mNode);
        int currentFilterLevel = ContentLevel.importFromSettings(this).getValue();
        int selectedFilterLevel = this.mLevel.getValue();
        if (currentFilterLevel != selectedFilterLevel) {
            Intent intent = new Intent();
            intent.putExtra("ContentFilterActivity_selectedFilterLevel", selectedFilterLevel);
            setResult(-1, intent);
        }
        finish();
    }

    public void onNegativeButtonClick() {
        this.mEventLogger.logClickEvent(255, null, this.mNode);
        finish();
    }
}
