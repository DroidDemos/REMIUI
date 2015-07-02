package com.google.android.finsky.widget;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.protos.Toc.CorpusMetadata;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class WidgetConfigurationActivity extends Activity {

    private static class CorpusAdapter extends BaseAdapter {
        private final Activity mActivity;
        private final ActivityManager mActivityManager;
        private final int mAppWidgetId;
        private final List<CorpusMetadata> mCorpora;

        public CorpusAdapter(Activity activity, List<CorpusMetadata> corpora, int appWidgetId) {
            this.mActivity = activity;
            this.mCorpora = corpora;
            this.mAppWidgetId = appWidgetId;
            this.mActivityManager = (ActivityManager) this.mActivity.getSystemService("activity");
        }

        public int getCount() {
            return this.mCorpora.size();
        }

        public CorpusMetadata getItem(int position) {
            return (CorpusMetadata) this.mCorpora.get(position);
        }

        public long getItemId(int position) {
            return (long) getBackend(position);
        }

        private int getBackend(int position) {
            return getItem(position).backend;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(this.mActivity).inflate(R.layout.widget_configuration_choice, parent, false);
            }
            Holder holder = (Holder) convertView.getTag();
            if (holder == null) {
                holder = new Holder(convertView);
            }
            final int backend = getBackend(position);
            holder.name.setText(getItem(position).name);
            int density = this.mActivityManager.getLauncherLargeIconDensity();
            holder.icon.setImageDrawable(this.mActivity.getResources().getDrawableForDensity(WidgetUtils.getBackendIcon(backend), density));
            holder.container.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent result = new Intent();
                    result.putExtra("backend", backend);
                    result.putExtra("appWidgetId", CorpusAdapter.this.mAppWidgetId);
                    CorpusAdapter.this.mActivity.setResult(-1, result);
                    CorpusAdapter.this.mActivity.finish();
                }
            });
            return convertView;
        }
    }

    private static class Holder {
        final ViewGroup container;
        final ImageView icon;
        final TextView name;

        public Holder(View v) {
            this.container = (ViewGroup) v.findViewById(R.id.container);
            this.name = (TextView) v.findViewById(R.id.backend_name);
            this.icon = (ImageView) v.findViewById(R.id.backend_icon);
            v.setTag(this);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_configuration);
        setResult(0);
        Intent intent = getIntent();
        setTitle(intent.getIntExtra("dialog_title", R.string.app_name));
        GridView container = (GridView) findViewById(R.id.container);
        List<CorpusMetadata> corpusList = ((DfeToc) intent.getParcelableExtra("dfeToc")).getCorpusList();
        List<CorpusMetadata> enabledCorpora = Lists.newArrayList(corpusList.size());
        if (intent.getBooleanExtra("enableMultiCorpus", true)) {
            CorpusMetadata all = new CorpusMetadata();
            all.backend = 0;
            all.hasBackend = true;
            all.name = getCorpusName(0);
            all.hasName = true;
            enabledCorpora.add(all);
        }
        for (CorpusMetadata corpus : corpusList) {
            if (intent.getBooleanExtra("backend_" + corpus.backend, true)) {
                String name = getCorpusName(corpus.backend);
                if (!TextUtils.isEmpty(name)) {
                    corpus.name = name;
                    corpus.hasName = true;
                }
                enabledCorpora.add(corpus);
            }
        }
        container.setNumColumns(Math.min(enabledCorpora.size(), 3));
        container.setAdapter(new CorpusAdapter(this, enabledCorpora, intent.getIntExtra("appWidgetId", -1)));
    }

    private String getCorpusName(int backend) {
        return getIntent().getStringExtra("name_" + backend);
    }
}
