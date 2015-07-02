package com.google.android.finsky.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import com.android.vending.R;
import com.android.volley.NetworkError;
import com.android.volley.VolleyError;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AccessRestrictedActivity;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.protos.Toc.TocResponse;
import com.google.android.finsky.utils.GetTocHelper;
import com.google.android.finsky.utils.RestrictedDeviceHelper;
import com.google.android.finsky.utils.RestrictedDeviceHelper.OnCompleteListener;

public abstract class TrampolineActivity extends FragmentActivity implements Listener {
    protected abstract boolean enableMultiCorpus();

    public abstract void finish(int i, String str);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RestrictedDeviceHelper.isLimitedOrSchoolEduUser(new OnCompleteListener() {
            public void onComplete(boolean isLimitedOrSchoolEduUser) {
                if (isLimitedOrSchoolEduUser) {
                    TrampolineActivity.this.setResult(0);
                    AccessRestrictedActivity.showLimitedUserUI(TrampolineActivity.this);
                    TrampolineActivity.this.finish();
                    return;
                }
                TrampolineActivity.this.continueOnCreate();
            }
        });
    }

    private void continueOnCreate() {
        int appWidgetId = -1;
        DfeApi dfeApi = FinskyApp.get().getDfeApi();
        if (dfeApi == null) {
            finish(-1, null);
            return;
        }
        Intent intent = getIntent();
        if (intent != null) {
            appWidgetId = intent.getIntExtra("appWidgetId", -1);
        }
        DfeToc dfeToc = FinskyApp.get().getToc();
        if (dfeToc != null) {
            initialize(dfeToc, appWidgetId);
        } else {
            GetTocHelper.getToc(dfeApi, false, new GetTocHelper.Listener() {
                public void onResponse(TocResponse response) {
                    DfeToc newToc = new DfeToc(response);
                    FinskyApp.get().setToc(newToc);
                    TrampolineActivity.this.initialize(newToc, appWidgetId);
                }

                public void onErrorResponse(VolleyError error) {
                    if (error instanceof NetworkError) {
                        TrampolineActivity.this.showNetworkNecessaryDialog();
                        return;
                    }
                    TrampolineActivity.this.setResult(0);
                    TrampolineActivity.this.finish();
                }
            });
        }
    }

    private void showNetworkNecessaryDialog() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm != null && fm.findFragmentByTag("Dialog.NoNetworkConnection") == null) {
            Builder builder = new Builder();
            builder.setMessageId(R.string.no_connection_widget_dialog).setPositiveId(R.string.ok);
            builder.build().show(fm, "Dialog.NoNetworkConnection");
        }
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        setResult(0);
        finish();
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        setResult(0);
        finish();
    }

    private void initialize(DfeToc dfeToc, int appWidgetId) {
        int i;
        int size = dfeToc.getCorpusList().size();
        if (enableMultiCorpus()) {
            i = 1;
        } else {
            i = 0;
        }
        int numChoices = size + i;
        if (!shouldAllowConfiguration() || numChoices <= 1) {
            finish(-1, "apps");
            return;
        }
        Intent destination = new Intent(this, WidgetConfigurationActivity.class);
        destination.putExtra("enableMultiCorpus", enableMultiCorpus());
        destination.putExtra("dfeToc", dfeToc);
        destination.putExtra("appWidgetId", appWidgetId);
        for (CorpusMetadata corpus : dfeToc.getCorpusList()) {
            int backend = corpus.backend;
            destination.putExtra("backend_" + backend, isBackendEnabled(backend));
            String nameKey = "name_" + backend;
            String name = getCorpusName(corpus.backend);
            if (TextUtils.isEmpty(name)) {
                name = corpus.name;
            }
            destination.putExtra(nameKey, name);
        }
        destination.putExtra("name_0", getCorpusName(0));
        destination.putExtra("dialog_title", getDialogTitle());
        startActivityForResult(destination, 1);
    }

    protected int getDialogTitle() {
        return R.string.app_name;
    }

    protected String getCorpusName(int backend) {
        return null;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String type = null;
        if (data != null) {
            type = WidgetUtils.translate(data.getIntExtra("backend", 3));
        }
        finish(resultCode, type);
    }

    protected boolean isBackendEnabled(int backend) {
        return true;
    }

    protected boolean shouldAllowConfiguration() {
        return true;
    }

    public static Intent getLaunchIntent(Context context, Class<? extends TrampolineActivity> activity, int appWidgetId) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(268435456);
        intent.putExtra("appWidgetId", appWidgetId);
        return intent;
    }

    public static PendingIntent getPendingLaunchIntent(Context context, Class<? extends TrampolineActivity> activity, int appWidgetId) {
        Intent launchIntent = getLaunchIntent(context, activity, -1);
        launchIntent.putExtra("appWidgetId", appWidgetId);
        return PendingIntent.getActivity(context, appWidgetId, launchIntent, 0);
    }
}
