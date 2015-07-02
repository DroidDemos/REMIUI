package com.google.android.finsky.widget.consumption;

import android.content.Intent;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.IntentUtils;
import com.google.android.finsky.widget.TrampolineActivity;
import com.google.android.finsky.widget.WidgetUtils;

public class MyLibraryTrampoline extends TrampolineActivity {
    protected boolean enableMultiCorpus() {
        return false;
    }

    protected boolean isBackendEnabled(int backend) {
        return (backend == 3 || !IntentUtils.isConsumptionAppInstalled(getPackageManager(), backend) || IntentUtils.isConsumptionAppDisabled(FinskyApp.get().getPackageInfoRepository(), backend)) ? false : true;
    }

    protected String getCorpusName(int backend) {
        DfeToc toc = FinskyApp.get().getToc();
        if (toc != null) {
            CorpusMetadata corpus = toc.getCorpus(backend);
            if (corpus != null) {
                return corpus.libraryName;
            }
        }
        return getString(R.string.widget_now_playing);
    }

    protected int getDialogTitle() {
        return R.string.widget_now_playing;
    }

    public void finish(int resultCode, String type) {
        if (resultCode == -1) {
            int backend = WidgetUtils.translate(type);
            Intent launchIntent = getIntent();
            if (launchIntent == null || !launchIntent.hasExtra("appWidgetId")) {
                startActivity(IntentUtils.buildConsumptionAppLaunchIntent(this, backend, FinskyApp.get().getCurrentAccountName()));
            } else {
                int appWidgetId = launchIntent.getIntExtra("appWidgetId", -1);
                Intent intent = new Intent(this, NowPlayingWidgetProvider.class);
                intent.setAction("NowPlayingWidgetProvider.WarmWelcome");
                intent.putExtra("appWidgetId", appWidgetId);
                intent.putExtra("backendId", backend);
                sendBroadcast(intent);
            }
        }
        finish();
    }
}
