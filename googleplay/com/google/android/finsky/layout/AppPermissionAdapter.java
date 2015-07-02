package com.google.android.finsky.layout;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.PermissionsBucketer;
import com.google.android.finsky.utils.PermissionsBucketer.PermissionBucket;
import com.google.android.finsky.utils.PermissionsBucketer.PermissionData;
import com.google.android.finsky.utils.Sets;
import java.util.List;
import java.util.Set;

public class AppPermissionAdapter extends PermissionAdapter {
    private Context mContext;
    private final PermissionData mData;
    private final boolean mIsAppInstalled;
    private LayoutInflater mLayoutInflater;

    public AppPermissionAdapter(Context context, String packageName, String[] permissions, boolean hasAcceptedBuckets) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        PackageInfo packageInfo = getPackageInfo(context.getPackageManager(), packageName);
        this.mIsAppInstalled = packageInfo != null;
        this.mData = PermissionsBucketer.getPermissionBuckets(permissions, loadLocalAssetPermissions(packageInfo), hasAcceptedBuckets);
    }

    public boolean isAppInstalled() {
        return this.mIsAppInstalled;
    }

    public int getCount() {
        return (this.mData.mExistingPermissionsBucket.size() > 0 ? 1 : 0) + this.mData.mNewPermissions.size();
    }

    public Object getItem(int i) {
        if (i < this.mData.mNewPermissions.size()) {
            return this.mData.mNewPermissions.get(i);
        }
        return this.mData.mExistingPermissionsBucket;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean hasNewPermissions() {
        return this.mData.mNewPermissions.size() > 0;
    }

    public boolean showTheNoPermissionMessage() {
        return !hasNewPermissions();
    }

    public static Set<String> loadLocalAssetPermissions(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        Set<String> oldPermissions = Sets.newHashSet();
        if (packageInfo.requestedPermissions == null) {
            return oldPermissions;
        }
        for (String permission : packageInfo.requestedPermissions) {
            oldPermissions.add(permission);
        }
        return oldPermissions;
    }

    public static PackageInfo getPackageInfo(PackageManager packageManager, String packageName) {
        try {
            return packageManager.getPackageInfo(packageName, 4096);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (position >= this.mData.mNewPermissions.size()) {
            return getExistingPermissionsView(parent, this.mData.mExistingPermissionsBucket);
        }
        String contentViewText;
        PermissionBucket bucket = (PermissionBucket) this.mData.mNewPermissions.get(position);
        if (bucket.mBucketId == 16) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bucket.mPermissionDescriptions.size(); i++) {
                builder.append(this.mContext.getString(R.string.bulleted_line_item, new Object[]{bucket.mPermissionDescriptions.get(i)}));
                builder.append("\n");
            }
            contentViewText = builder.toString();
        } else {
            contentViewText = this.mContext.getString(bucket.mBucketDescription);
        }
        return getPermissionView(parent, bucket, contentViewText);
    }

    private View getExistingPermissionsView(ViewGroup parent, List<PermissionBucket> existingBuckets) {
        String first;
        String second;
        String third;
        String fourth;
        String fifth;
        String sixth;
        int count = existingBuckets.size();
        if (count == 0 || count == 17) {
            String str = "]";
            Object[] objArr = new Object[0];
            FinskyLog.wtf("numBuckets=[" + count + r23, objArr);
        }
        String permissionsStr = null;
        if (count > 0) {
            Context context = this.mContext;
            first = r23.getString(((PermissionBucket) existingBuckets.get(0)).mBucketTitle);
        } else {
            first = null;
        }
        if (count > 1) {
            context = this.mContext;
            second = r23.getString(((PermissionBucket) existingBuckets.get(1)).mBucketTitle);
        } else {
            second = null;
        }
        if (count > 2) {
            context = this.mContext;
            third = r23.getString(((PermissionBucket) existingBuckets.get(2)).mBucketTitle);
        } else {
            third = null;
        }
        if (count > 3) {
            context = this.mContext;
            fourth = r23.getString(((PermissionBucket) existingBuckets.get(3)).mBucketTitle);
        } else {
            fourth = null;
        }
        if (count > 4) {
            context = this.mContext;
            fifth = r23.getString(((PermissionBucket) existingBuckets.get(4)).mBucketTitle);
        } else {
            fifth = null;
        }
        if (count > 5) {
            context = this.mContext;
            sixth = r23.getString(((PermissionBucket) existingBuckets.get(5)).mBucketTitle);
        } else {
            sixth = null;
        }
        switch (count) {
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                context = this.mContext;
                permissionsStr = r23.getString(((PermissionBucket) existingBuckets.get(0)).mBucketTitle);
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                permissionsStr = this.mContext.getString(R.string.multiple_items_2, new Object[]{first, second});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                permissionsStr = this.mContext.getString(R.string.multiple_items_3, new Object[]{first, second, third});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_required /*4*/:
                permissionsStr = this.mContext.getString(R.string.multiple_items_4, new Object[]{first, second, third, fourth});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validateWhenNotVisible /*5*/:
                permissionsStr = this.mContext.getString(R.string.multiple_items_5, new Object[]{first, second, third, fourth, fifth});
                break;
            case com.google.android.wallet.instrumentmanager.R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                permissionsStr = this.mContext.getString(R.string.multiple_items_6, new Object[]{first, second, third, fourth, fifth, sixth});
                break;
        }
        View view = this.mLayoutInflater.inflate(R.layout.existing_permissions_row, parent, false);
        ((ImageView) view.findViewById(R.id.bucket_icon)).setImageResource(R.drawable.ic_perm_check);
        final TextView shortDescription = (TextView) view.findViewById(R.id.short_description);
        final ImageView expanderIcon = (ImageView) view.findViewById(R.id.expander_icon);
        final ViewGroup detailedBuckets = (ViewGroup) view.findViewById(R.id.detailed_buckets);
        ((TextView) view.findViewById(R.id.header)).setText(R.string.already_has_access_to);
        shortDescription.setText(permissionsStr);
        final View headerRow = view.findViewById(R.id.permission_row);
        headerRow.setTag(Boolean.valueOf(false));
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                boolean z;
                boolean isExpanded = ((Boolean) headerRow.getTag()).booleanValue();
                if (isExpanded) {
                    expanderIcon.setImageResource(R.drawable.ic_more_arrow_down);
                    expanderIcon.setContentDescription(AppPermissionAdapter.this.mContext.getString(R.string.content_description_toggle_expand));
                    detailedBuckets.setVisibility(8);
                    shortDescription.setVisibility(0);
                } else {
                    expanderIcon.setImageResource(R.drawable.ic_more_arrow_up);
                    expanderIcon.setContentDescription(AppPermissionAdapter.this.mContext.getString(R.string.content_description_toggle_collapse));
                    detailedBuckets.setVisibility(0);
                    shortDescription.setVisibility(8);
                }
                View view2 = headerRow;
                if (isExpanded) {
                    z = false;
                } else {
                    z = true;
                }
                view2.setTag(Boolean.valueOf(z));
            }
        });
        for (PermissionBucket bucket : existingBuckets) {
            View bucketView = getPermissionView(parent, bucket, this.mContext.getString(bucket.mBucketDescription));
            bucketView.findViewById(R.id.expander_icon).setVisibility(8);
            bucketView.setOnClickListener(null);
            bucketView.setClickable(false);
            bucketView.setBackgroundResource(R.drawable.focus_overlay);
            bucketView.findViewById(R.id.content).setVisibility(0);
            detailedBuckets.addView(bucketView);
        }
        return view;
    }

    private View getPermissionView(ViewGroup parent, PermissionBucket bucket, String contentViewText) {
        View view = this.mLayoutInflater.inflate(R.layout.permission_row, parent, false);
        final TextView contentView = (TextView) view.findViewById(R.id.content);
        final ImageView expanderIcon = (ImageView) view.findViewById(R.id.expander_icon);
        ImageView bucketIcon = (ImageView) view.findViewById(R.id.bucket_icon);
        ((TextView) view.findViewById(R.id.header)).setText(bucket.mBucketTitle);
        contentView.setText(contentViewText);
        bucketIcon.setImageResource(bucket.mBucketIcon);
        contentView.setVisibility(8);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                boolean isCollapsed;
                if (contentView.getVisibility() == 8) {
                    isCollapsed = true;
                } else {
                    isCollapsed = false;
                }
                if (isCollapsed) {
                    expanderIcon.setImageResource(R.drawable.ic_more_arrow_up);
                    expanderIcon.setContentDescription(AppPermissionAdapter.this.mContext.getString(R.string.content_description_toggle_collapse));
                    contentView.setVisibility(0);
                    return;
                }
                expanderIcon.setImageResource(R.drawable.ic_more_arrow_down);
                expanderIcon.setContentDescription(AppPermissionAdapter.this.mContext.getString(R.string.content_description_toggle_expand));
                contentView.setVisibility(8);
            }
        });
        view.setBackgroundResource(R.drawable.play_highlight_overlay_light);
        return view;
    }
}
