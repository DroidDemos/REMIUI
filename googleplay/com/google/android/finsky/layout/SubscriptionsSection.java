package com.google.android.finsky.layout;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.SubscriptionView.CancelListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.LibrarySubscriptionEntry;

public class SubscriptionsSection extends DetailsSectionStack {
    private final LayoutInflater mLayoutInflater;

    public SubscriptionsSection(Context context) {
        this(context, null);
    }

    public SubscriptionsSection(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void addSubscription(Document doc, LibrarySubscriptionEntry subscriptionDetails, int subscriptionItemLayoutId, CancelListener cancelListener, Bundle savedState, PlayStoreUiElementNode parentNode) {
        SubscriptionView subscriptionView = (SubscriptionView) this.mLayoutInflater.inflate(subscriptionItemLayoutId, this, false);
        subscriptionView.configure(doc, subscriptionDetails, cancelListener, savedState, parentNode);
        addView(subscriptionView);
    }

    public void clearSubscriptions() {
        removeAllViews();
    }

    public void saveInstanceState(Bundle bundle) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof SubscriptionView) {
                ((SubscriptionView) child).saveInstanceState(bundle);
            }
        }
    }
}
