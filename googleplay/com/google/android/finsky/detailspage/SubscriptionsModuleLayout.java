package com.google.android.finsky.detailspage;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.android.vending.R;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DetailsSectionStack;
import com.google.android.finsky.layout.SubscriptionView;
import com.google.android.finsky.layout.SubscriptionView.CancelListener;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.LibrarySubscriptionEntry;
import java.util.List;

public class SubscriptionsModuleLayout extends DetailsSectionStack {
    private boolean mHasBinded;
    private final LayoutInflater mLayoutInflater;

    public SubscriptionsModuleLayout(Context context) {
        this(context, null);
    }

    public SubscriptionsModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void bind(List<Document> subscriptionDocuments, List<LibrarySubscriptionEntry> subscriptionEntries, CancelListener cancelListener, int parentBackend, Bundle savedState, PlayStoreUiElementNode parentNode) {
        this.mHasBinded = true;
        removeAllViews();
        for (int i = 0; i < subscriptionDocuments.size(); i++) {
            addSubscription((Document) subscriptionDocuments.get(i), (LibrarySubscriptionEntry) subscriptionEntries.get(i), cancelListener, parentBackend, savedState, parentNode);
        }
    }

    public boolean hasBinded() {
        return this.mHasBinded;
    }

    private void addSubscription(Document doc, LibrarySubscriptionEntry subscriptionDetails, CancelListener cancelListener, int parentBackend, Bundle savedState, PlayStoreUiElementNode parentNode) {
        SubscriptionView subscriptionView = (SubscriptionView) this.mLayoutInflater.inflate(R.layout.subscription_item, this, false);
        subscriptionView.configure(doc, subscriptionDetails, cancelListener, parentBackend, savedState, parentNode);
        addView(subscriptionView);
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
