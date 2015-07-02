package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.config.G;
import com.google.android.finsky.utils.ExpandableUtils;
import com.google.android.finsky.utils.Lists;
import java.util.List;

public class AppSecurityPermissions extends LinearLayout {
    private Context mContext;
    private int mExpansionState;
    private String mPackageTitle;
    private PermissionAdapter mPermissionAdapter;
    private final List<View> mPermissionViews;

    public AppSecurityPermissions(Context context) {
        this(context, null);
    }

    public AppSecurityPermissions(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPermissionViews = Lists.newArrayList();
        this.mExpansionState = 0;
    }

    public void bindInfo(PermissionAdapter adapter, String packageTitle, Bundle savedState) {
        this.mContext = getContext();
        this.mPackageTitle = packageTitle;
        this.mPermissionAdapter = adapter;
        if (this.mExpansionState == 0) {
            this.mExpansionState = ExpandableUtils.getSavedExpansionState(savedState, this.mPackageTitle + ":" + getId(), 1);
        }
        showPermissions();
    }

    public void saveInstanceState(Bundle bundle) {
        ExpandableUtils.saveExpansionState(bundle, this.mPackageTitle + ":" + getId(), this.mExpansionState);
    }

    private void showPermissions() {
        removeAllViews();
        this.mPermissionViews.clear();
        int count = this.mPermissionAdapter.getCount();
        if (this.mPermissionAdapter.showTheNoPermissionMessage()) {
            TextView noPermissions = (TextView) LayoutInflater.from(this.mContext).inflate(R.layout.no_permissions_required, this, false);
            if (TextUtils.isEmpty((String) G.permissionBucketsLearnMoreLink.get())) {
                noPermissions.setText(Html.fromHtml(this.mPermissionAdapter.isAppInstalled() ? this.mContext.getString(R.string.no_new_dangerous_permissions, new Object[]{this.mPackageTitle}) : this.mContext.getString(R.string.no_dangerous_permissions, new Object[]{this.mPackageTitle})));
            } else {
                noPermissions.setText(Html.fromHtml(getResources().getString(this.mPermissionAdapter.isAppInstalled() ? R.string.no_new_dangerous_permissions_with_learn_more_link : R.string.no_dangerous_permissions_with_learn_more_link, new Object[]{this.mPackageTitle, (String) G.permissionBucketsLearnMoreLink.get()})));
                noPermissions.setMovementMethod(LinkMovementMethod.getInstance());
            }
            addView(noPermissions);
            return;
        }
        for (int i = 0; i < count; i++) {
            View view = this.mPermissionAdapter.getView(i, null, this);
            this.mPermissionViews.add(view);
            addView(view);
        }
    }
}
