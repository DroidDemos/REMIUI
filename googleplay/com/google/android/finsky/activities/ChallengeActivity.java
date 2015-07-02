package com.google.android.finsky.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.Toast;
import com.android.vending.R;
import com.google.android.finsky.billing.BillingFlow;
import com.google.android.finsky.billing.BillingFlowContext;
import com.google.android.finsky.billing.BillingFlowListener;

public abstract class ChallengeActivity extends ActionBarActivity implements BillingFlowContext, BillingFlowListener {
    public void showFragment(Fragment fragment, String title, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add((int) R.id.content_frame, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    public void showDialogFragment(DialogFragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        fragment.show(getSupportFragmentManager(), tag);
    }

    public void hideFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public void persistFragment(Bundle bundle, String key, Fragment fragment) {
        getSupportFragmentManager().putFragment(bundle, key, fragment);
    }

    public Fragment restoreFragment(Bundle bundle, String key) {
        return getSupportFragmentManager().getFragment(bundle, key);
    }

    public void showProgress(int messageId) {
    }

    public void hideProgress() {
    }

    public void onFinished(BillingFlow flow, boolean canceled, Bundle result) {
        if (canceled) {
            setResult(0);
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("challenge_response", result);
        setResult(-1, intent);
        finish();
    }

    public void onError(BillingFlow flow, String error) {
        if (error != null) {
            Toast.makeText(this, error, 1).show();
        }
        setResult(0);
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
