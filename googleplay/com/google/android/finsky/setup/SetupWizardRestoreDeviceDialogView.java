package com.google.android.finsky.setup;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.activities.SimpleAlertDialog.ConfigurableView;
import com.google.android.finsky.protos.Restore.BackupDeviceInfo;
import com.google.android.finsky.utils.ParcelableProto;
import java.util.concurrent.TimeUnit;

public class SetupWizardRestoreDeviceDialogView extends LinearLayout implements ConfigurableView {
    private int mCurrentDeviceSelection;

    private static class BackupDeviceInfoArrayAdapter extends ArrayAdapter<BackupDeviceInfo> {
        private final BackupDeviceInfo[] mDevices;

        public static class ViewHolder {
            public CheckedTextView deviceName;
            public TextView usageTime;
        }

        public BackupDeviceInfoArrayAdapter(Context context, BackupDeviceInfo[] devices) {
            super(context, R.layout.restore_device_row_entry, devices);
            this.mDevices = devices;
        }

        public int getCount() {
            return this.mDevices.length + 1;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            int viewType = getItemViewType(position);
            Context context = getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            if (viewType == 0) {
                if (rowView == null) {
                    rowView = inflater.inflate(R.layout.restore_as_new_device_row_entry, parent, false);
                    ((CheckedTextView) rowView).setText(context.getResources().getString(R.string.setup_wizard_setup_as_new_option));
                }
                return rowView;
            }
            if (rowView == null) {
                rowView = inflater.inflate(R.layout.restore_device_row_entry, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.deviceName = (CheckedTextView) rowView.findViewById(R.id.setup_wizard_device_name);
                viewHolder.usageTime = (TextView) rowView.findViewById(R.id.device_usage_time);
                rowView.setTag(viewHolder);
            }
            ViewHolder holder = (ViewHolder) rowView.getTag();
            BackupDeviceInfo backupDeviceInfo = this.mDevices[position - 1];
            holder.deviceName.setText(backupDeviceInfo.name);
            holder.usageTime.setText(getLastUsageTimeText(context, backupDeviceInfo));
            return rowView;
        }

        private String getLastUsageTimeText(Context context, BackupDeviceInfo backupDeviceInfo) {
            long days = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - backupDeviceInfo.lastCheckinTimeMs);
            Resources resources = context.getResources();
            if (days == 0) {
                return resources.getString(R.string.setup_wizard_device_last_used_today_message);
            }
            return resources.getQuantityString(R.plurals.setup_wizard_device_last_used_message, (int) days, new Object[]{Long.valueOf(days)});
        }
    }

    public SetupWizardRestoreDeviceDialogView(Context context) {
        super(context);
    }

    public SetupWizardRestoreDeviceDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void configureView(Bundle arguments) {
        this.mCurrentDeviceSelection = arguments.getInt("SetupWizardRestoreDeviceDialogView.selectedDevicePosition", 0) + 1;
        Parcelable[] parcelableBackupDeviceInfos = arguments.getParcelableArray("SetupWizardRestoreDeviceDialogView.selectedDevices");
        BackupDeviceInfo[] backupDeviceInfos = new BackupDeviceInfo[parcelableBackupDeviceInfos.length];
        for (int i = 0; i < parcelableBackupDeviceInfos.length; i++) {
            backupDeviceInfos[i] = (BackupDeviceInfo) ((ParcelableProto) parcelableBackupDeviceInfos[i]).getPayload();
        }
        ListView listView = (ListView) findViewById(R.id.setup_wizard_device_list);
        listView.setChoiceMode(1);
        listView.setAdapter(new BackupDeviceInfoArrayAdapter(getContext(), backupDeviceInfos));
        listView.setItemChecked(this.mCurrentDeviceSelection, true);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                SetupWizardRestoreDeviceDialogView.this.mCurrentDeviceSelection = position;
            }
        });
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("SetupWizardRestoreDeviceDialogView.parent_instance_state", super.onSaveInstanceState());
        bundle.putInt("SetupWizardRestoreDeviceDialogView.selectedDevicePosition", this.mCurrentDeviceSelection);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mCurrentDeviceSelection = bundle.getInt("SetupWizardRestoreDeviceDialogView.selectedDevicePosition");
            super.onRestoreInstanceState(bundle.getParcelable("SetupWizardRestoreDeviceDialogView.parent_instance_state"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public Bundle getResult() {
        Bundle result = new Bundle();
        result.putInt("SetupWizardRestoreDeviceDialogView.selectedDevicePosition", this.mCurrentDeviceSelection - 1);
        return result;
    }
}
