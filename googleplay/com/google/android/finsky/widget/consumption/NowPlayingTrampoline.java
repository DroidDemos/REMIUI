package com.google.android.finsky.widget.consumption;

import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.widget.BaseWidgetProvider;
import com.google.android.finsky.widget.WidgetTrampolineActivity;

public class NowPlayingTrampoline extends WidgetTrampolineActivity {
    protected Class<? extends BaseWidgetProvider> getWidgetClass() {
        return NowPlayingWidgetProvider.class;
    }

    protected boolean enableMultiCorpus() {
        return true;
    }

    protected boolean isBackendEnabled(int backend) {
        return backend != 3;
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
        return R.string.widget_now_playing_family;
    }
}
