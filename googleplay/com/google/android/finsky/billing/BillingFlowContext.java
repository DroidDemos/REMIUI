package com.google.android.finsky.billing;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

public interface BillingFlowContext {
    void hideFragment(Fragment fragment, boolean z);

    void hideProgress();

    void persistFragment(Bundle bundle, String str, Fragment fragment);

    Fragment restoreFragment(Bundle bundle, String str);

    void showDialogFragment(DialogFragment dialogFragment, String str);

    void showFragment(Fragment fragment, String str, boolean z);

    void showProgress(int i);
}
