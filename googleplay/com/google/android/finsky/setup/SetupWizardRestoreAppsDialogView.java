package com.google.android.finsky.setup;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.SimpleAlertDialog.ConfigurableView;
import com.google.android.finsky.protos.Restore.BackupDocumentInfo;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class SetupWizardRestoreAppsDialogView extends LinearLayout implements OnItemClickListener, ConfigurableView {
    private ArrayList<BackupDocumentInfo> mBackupDocumentInfos;
    private ListView mListView;
    private boolean[] mSelectedBackupDocs;
    private int mSelectedBackupDocsCount;

    private class BackupAppListAdapter extends BaseAdapter {
        private ArrayList<BackupDocumentInfo> mAppsList;
        private LayoutInflater mInflater;

        public BackupAppListAdapter(ArrayList<BackupDocumentInfo> appsList) {
            this.mAppsList = appsList;
            this.mInflater = (LayoutInflater) SetupWizardRestoreAppsDialogView.this.getContext().getSystemService("layout_inflater");
        }

        public int getCount() {
            return this.mAppsList.size() + 1;
        }

        public String getItem(int position) {
            if (position == 0) {
                return SetupWizardRestoreAppsDialogView.this.getResources().getString(R.string.setup_wizard_all_apps);
            }
            return ((BackupDocumentInfo) this.mAppsList.get(position - 1)).title;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = this.mInflater.inflate(R.layout.restore_apps_row_entry, parent, false);
            }
            ((TextView) convertView).setText(getItem(position));
            return convertView;
        }
    }

    public SetupWizardRestoreAppsDialogView(Context context) {
        super(context);
    }

    public SetupWizardRestoreAppsDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void configureView(Bundle arguments) {
        Context context = getContext();
        ArrayList<ParcelableProto<BackupDocumentInfo>> parcelableBackupDocumentInfos = arguments.getParcelableArrayList("SetupWizardAppListDialog.backupDocs");
        boolean[] selectedBackupDocs = arguments.getBooleanArray("SetupWizardAppListDialog.selectedBackupDocs");
        this.mSelectedBackupDocs = Arrays.copyOf(selectedBackupDocs, selectedBackupDocs.length);
        this.mSelectedBackupDocsCount = getSelectedBackupDocsCount();
        this.mBackupDocumentInfos = new ArrayList(parcelableBackupDocumentInfos.size());
        Iterator i$ = parcelableBackupDocumentInfos.iterator();
        while (i$.hasNext()) {
            this.mBackupDocumentInfos.add(((ParcelableProto) i$.next()).getPayload());
        }
        configureListView((ListView) findViewById(R.id.restore_apps_list));
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("SetupWizardRestoreAppsDialogView.parent_instance_state", super.onSaveInstanceState());
        bundle.putBooleanArray("SetupWizardAppListDialog.selectedBackupDocs", this.mSelectedBackupDocs);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mSelectedBackupDocs = bundle.getBooleanArray("SetupWizardAppListDialog.selectedBackupDocs");
            super.onRestoreInstanceState(bundle.getParcelable("SetupWizardRestoreAppsDialogView.parent_instance_state"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public Bundle getResult() {
        Bundle result = new Bundle();
        result.putBooleanArray("SetupWizardAppListDialog.selectedBackupDocs", this.mSelectedBackupDocs);
        return result;
    }

    private void configureListView(View view) {
        boolean z;
        this.mListView = (ListView) view.findViewById(R.id.restore_apps_list);
        this.mListView.setAdapter(new BackupAppListAdapter(this.mBackupDocumentInfos));
        for (int pos = 0; pos < this.mSelectedBackupDocs.length; pos++) {
            if (this.mSelectedBackupDocs[pos]) {
                this.mListView.setItemChecked(pos + 1, true);
            }
        }
        ListView listView = this.mListView;
        if (this.mSelectedBackupDocsCount == this.mSelectedBackupDocs.length) {
            z = true;
        } else {
            z = false;
        }
        listView.setItemChecked(0, z);
        this.mListView.setOnItemClickListener(this);
    }

    private int getSelectedBackupDocsCount() {
        int count = 0;
        for (boolean valueOf : this.mSelectedBackupDocs) {
            if (Boolean.valueOf(valueOf).booleanValue()) {
                count++;
            }
        }
        return count;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        boolean z = true;
        int i = 0;
        boolean isChecked = ((CheckedTextView) view).isChecked();
        if (position < 1) {
            for (int pos = 0; pos < this.mSelectedBackupDocs.length; pos++) {
                this.mListView.setItemChecked(pos + 1, isChecked);
                this.mSelectedBackupDocs[pos] = isChecked;
            }
            if (isChecked) {
                i = this.mSelectedBackupDocs.length;
            }
            this.mSelectedBackupDocsCount = i;
            return;
        }
        this.mSelectedBackupDocs[position - 1] = isChecked;
        this.mSelectedBackupDocsCount = (isChecked ? 1 : -1) + this.mSelectedBackupDocsCount;
        ListView listView = this.mListView;
        if (this.mSelectedBackupDocsCount != this.mSelectedBackupDocs.length) {
            z = false;
        }
        listView.setItemChecked(0, z);
    }
}
