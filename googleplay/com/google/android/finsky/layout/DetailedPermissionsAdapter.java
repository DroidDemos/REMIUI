package com.google.android.finsky.layout;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PermissionsBucketer;
import com.google.android.finsky.utils.PermissionsBucketer.PermissionBucket;
import com.google.android.finsky.utils.PermissionsBucketer.PermissionData;
import com.google.android.finsky.utils.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DetailedPermissionsAdapter extends PermissionAdapter {
    private final Context mContext;
    private final List<DetailedPermissionBucket> mData;
    private final boolean mIsAppInstalled;
    private final LayoutInflater mLayoutInflater;

    private static class DetailedPermissionBucket {
        public final int mBucketIcon;
        public final int mBucketTitle;
        public final List<String> mExistingPermissionDescriptions;
        public final List<String> mNewPermissionDescriptions;

        public DetailedPermissionBucket(PermissionBucket newPermissionBucket, PermissionBucket existingBucket) {
            if (newPermissionBucket != null) {
                this.mBucketIcon = newPermissionBucket.mBucketIcon;
                this.mBucketTitle = newPermissionBucket.mBucketTitle;
            } else {
                this.mBucketIcon = existingBucket.mBucketIcon;
                this.mBucketTitle = existingBucket.mBucketTitle;
            }
            this.mNewPermissionDescriptions = newPermissionBucket != null ? newPermissionBucket.mPermissionDescriptions : Collections.emptyList();
            this.mExistingPermissionDescriptions = existingBucket != null ? existingBucket.mPermissionDescriptions : Collections.emptyList();
        }
    }

    public DetailedPermissionsAdapter(Context context, String packageName, String[] serverPermissions) {
        this.mData = Lists.newArrayList();
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        PackageInfo packageInfo = AppPermissionAdapter.getPackageInfo(context.getPackageManager(), packageName);
        this.mIsAppInstalled = packageInfo != null;
        Set<String> installedPermissions = AppPermissionAdapter.loadLocalAssetPermissions(packageInfo);
        Set<String> existingPermissions = null;
        if (installedPermissions != null) {
            Set<String> newPermissions = Sets.newHashSet((Object[]) serverPermissions);
            newPermissions.removeAll(installedPermissions);
            existingPermissions = Sets.newHashSet((Object[]) serverPermissions);
            existingPermissions.removeAll(newPermissions);
        }
        PermissionData permissionData = PermissionsBucketer.getPermissionBuckets(serverPermissions, existingPermissions, true, true);
        PermissionBucket[] newBuckets = new PermissionBucket[17];
        PermissionBucket[] existingBuckets = new PermissionBucket[17];
        for (PermissionBucket bucket : permissionData.mNewPermissions) {
            newBuckets[bucket.mBucketId] = bucket;
        }
        for (PermissionBucket bucket2 : permissionData.mExistingPermissionsBucket) {
            existingBuckets[bucket2.mBucketId] = bucket2;
        }
        int i = 0;
        while (i < 17) {
            if (newBuckets[i] != null || existingBuckets[i] != null) {
                this.mData.add(new DetailedPermissionBucket(newBuckets[i], existingBuckets[i]));
            }
            i++;
        }
    }

    public int getCount() {
        return this.mData.size();
    }

    public Object getItem(int i) {
        return this.mData.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean isAppInstalled() {
        return this.mIsAppInstalled;
    }

    public boolean showTheNoPermissionMessage() {
        return this.mData.size() == 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        int i;
        DetailedPermissionBucket bucket = (DetailedPermissionBucket) this.mData.get(position);
        View view = this.mLayoutInflater.inflate(R.layout.permission_row, parent, false);
        TextView headerView = (TextView) view.findViewById(R.id.header);
        TextView contentView = (TextView) view.findViewById(R.id.content);
        ImageView bucketIcon = (ImageView) view.findViewById(R.id.bucket_icon);
        ((ImageView) view.findViewById(R.id.expander_icon)).setVisibility(8);
        headerView.setText(bucket.mBucketTitle);
        StringBuilder builder = new StringBuilder();
        boolean hasExistingPermissions = bucket.mExistingPermissionDescriptions.size() > 0;
        for (i = 0; i < bucket.mNewPermissionDescriptions.size(); i++) {
            String newPermission;
            if (this.mIsAppInstalled) {
                newPermission = this.mContext.getString(R.string.new_permission, new Object[]{bucket.mNewPermissionDescriptions.get(i)});
            } else {
                newPermission = (String) bucket.mNewPermissionDescriptions.get(i);
            }
            builder.append(this.mContext.getString(R.string.bulleted_line_item, new Object[]{newPermission}));
            if (i < bucket.mNewPermissionDescriptions.size() - 1 || hasExistingPermissions) {
                builder.append("<br>");
            }
        }
        for (i = 0; i < bucket.mExistingPermissionDescriptions.size(); i++) {
            builder.append(this.mContext.getString(R.string.bulleted_line_item, new Object[]{bucket.mExistingPermissionDescriptions.get(i)}));
            if (i < bucket.mExistingPermissionDescriptions.size() - 1) {
                builder.append("<br>");
            }
        }
        contentView.setText(Html.fromHtml(builder.toString()));
        bucketIcon.setImageResource(bucket.mBucketIcon);
        contentView.setVisibility(0);
        return view;
    }
}
