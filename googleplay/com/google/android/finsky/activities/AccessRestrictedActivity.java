package com.google.android.finsky.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.android.vending.R;
import com.google.android.finsky.activities.SimpleAlertDialog.Builder;
import com.google.android.finsky.activities.SimpleAlertDialog.Listener;

public class AccessRestrictedActivity extends FragmentActivity implements Listener {
    protected void onResume() {
        super.onResume();
        int messageId = getIntent().getIntExtra("AccessRestrictedActivity.messageId", R.string.limited_user_text);
        Builder builder = new Builder();
        builder.setMessageId(messageId).setPositiveId(R.string.ok).setCanceledOnTouchOutside(true).setEventLog(308, null, -1, -1, null);
        builder.build().show(getSupportFragmentManager(), "access_restricted_dialog");
    }

    public static void showLimitedUserUI(Activity activity) {
        Intent intent = new Intent(activity, AccessRestrictedActivity.class);
        intent.putExtra("AccessRestrictedActivity.messageId", R.string.limited_user_text);
        activity.startActivity(intent);
    }

    public static void showLimitedPurchaseUI(Activity activity) {
        Intent intent = new Intent(activity, AccessRestrictedActivity.class);
        intent.putExtra("AccessRestrictedActivity.messageId", R.string.limited_user_purchase_text);
        activity.startActivity(intent);
    }

    public void onPositiveClick(int requestCode, Bundle extraArguments) {
        finish();
    }

    public void onNegativeClick(int requestCode, Bundle extraArguments) {
        finish();
    }
}
