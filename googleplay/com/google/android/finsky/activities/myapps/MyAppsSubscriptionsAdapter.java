package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.DocImageView;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.PlayCardViewMyApps;
import com.google.android.finsky.library.LibraryInAppSubscriptionEntry;
import com.google.android.finsky.protos.Common.Offer;
import com.google.android.finsky.utils.DateUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PlayCardImageTypeSequence;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayCardLabelView;
import com.google.android.play.layout.PlayCardThumbnail;
import com.google.android.play.layout.PlayTextView;
import com.google.android.wallet.instrumentmanager.R;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyAppsSubscriptionsAdapter extends BaseAdapter implements MyAppsListAdapter {
    private static final Collator sSubscriptionAbcCollator;
    private static final Comparator<MyAppsSubscriptionEntry> sSubscriptionAbcSorter;
    private final BitmapLoader mBitmapLoader;
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final int mLeadingSpacerHeight;
    private final OnClickListener mListener;
    private final List<MyAppsSubscriptionEntry> mSubscriptions;

    private class MyAppsSubscriptionEntry {
        public final Document parentDoc;
        public final Document subscriptionDoc;
        public final LibraryInAppSubscriptionEntry subscriptionOwnership;

        public MyAppsSubscriptionEntry(Document subscriptionDoc, Document parentDoc, LibraryInAppSubscriptionEntry libEntry) {
            this.subscriptionDoc = subscriptionDoc;
            this.parentDoc = parentDoc;
            this.subscriptionOwnership = libEntry;
        }
    }

    static {
        sSubscriptionAbcCollator = Collator.getInstance();
        sSubscriptionAbcSorter = new Comparator<MyAppsSubscriptionEntry>() {
            public int compare(MyAppsSubscriptionEntry entry1, MyAppsSubscriptionEntry entry2) {
                int titleComp = MyAppsSubscriptionsAdapter.sSubscriptionAbcCollator.compare(entry1.parentDoc.getTitle(), entry2.parentDoc.getTitle());
                if (titleComp != 0) {
                    return titleComp;
                }
                titleComp = MyAppsSubscriptionsAdapter.sSubscriptionAbcCollator.compare(entry1.subscriptionDoc.getTitle(), entry2.subscriptionDoc.getTitle());
                if (titleComp != 0) {
                    return titleComp;
                }
                return entry1.parentDoc.getAppDetails().packageName.compareTo(entry2.parentDoc.getAppDetails().packageName);
            }
        };
    }

    public MyAppsSubscriptionsAdapter(Context context, LayoutInflater inflater, BitmapLoader bitmapLoader, OnClickListener listener) {
        this.mSubscriptions = Lists.newArrayList();
        this.mContext = context;
        this.mInflater = inflater;
        this.mBitmapLoader = bitmapLoader;
        this.mListener = listener;
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(this.mContext, 0, 0);
    }

    public void addEntry(LibraryInAppSubscriptionEntry libEntry, Document doc, Document parent) {
        this.mSubscriptions.add(new MyAppsSubscriptionEntry(doc, parent, libEntry));
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mSubscriptions.size() + 1;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        }
        return 0;
    }

    public Object getItem(int position) {
        if (position == 0) {
            return null;
        }
        return ((MyAppsSubscriptionEntry) this.mSubscriptions.get(position - 1)).subscriptionDoc;
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                return getSubscriptionView(position, convertView, parent);
            case R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                return getLeadingSpacerView(convertView, parent);
            default:
                throw new IllegalStateException("Unknown type for getView " + position);
        }
    }

    private View getLeadingSpacerView(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.mInflater.inflate(com.android.vending.R.layout.play_list_vspacer, parent, false);
        }
        convertView.getLayoutParams().height = this.mLeadingSpacerHeight;
        convertView.setId(com.android.vending.R.id.play_header_spacer);
        return convertView;
    }

    public View getSubscriptionView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.mInflater.inflate(com.android.vending.R.layout.play_card_myapps, parent, false);
        }
        MyAppsSubscriptionEntry subsEntry = (MyAppsSubscriptionEntry) this.mSubscriptions.get(position - 1);
        LibraryInAppSubscriptionEntry libEntry = subsEntry.subscriptionOwnership;
        Document subsDoc = subsEntry.subscriptionDoc;
        Document parentDoc = subsEntry.parentDoc;
        Resources res = this.mContext.getResources();
        PlayCardViewMyApps entry = (PlayCardViewMyApps) convertView;
        entry.setArchivable(false, null);
        PlayCardThumbnail thumbnail = entry.getThumbnail();
        thumbnail.updateCoverPadding(parentDoc.getBackend());
        ((DocImageView) thumbnail.getImageView()).bind(parentDoc, this.mBitmapLoader, PlayCardImageTypeSequence.CORE_IMAGE_TYPES);
        entry.getTitle().setText(subsDoc.getTitle());
        entry.getSubtitle().setText(parentDoc.getTitle());
        PlayCardLabelView status = entry.getLabel();
        PlayTextView extraInfo = entry.getItemBadge();
        int subsDocBackendId = subsDoc.getBackend();
        if (libEntry.isAutoRenewing) {
            if (System.currentTimeMillis() < libEntry.trialUntilTimestampMs) {
                status.setText(com.android.vending.R.string.subscription_trial, subsDocBackendId);
            } else {
                status.setText(com.android.vending.R.string.subscription_renewing, subsDocBackendId);
            }
            if (extraInfo != null) {
                Offer offer = subsDoc.getOffer(1);
                if (offer == null || offer.subscriptionTerms == null) {
                    extraInfo.setVisibility(8);
                    FinskyLog.wtf("Document for %s does not contain a subscription offer or terms.", subsDoc.getDocId());
                } else {
                    String priceWithPeriod = offer.subscriptionTerms.formattedPriceWithRecurrencePeriod;
                    if (TextUtils.isEmpty(priceWithPeriod)) {
                        extraInfo.setVisibility(8);
                        FinskyLog.wtf("Document for %s does not contain a formatted price.", subsDoc.getDocId());
                    } else {
                        extraInfo.setVisibility(0);
                        extraInfo.setText(priceWithPeriod);
                    }
                }
            }
        } else {
            status.setText(com.android.vending.R.string.subscription_canceled, subsDocBackendId);
            if (extraInfo != null) {
                extraInfo.setText(Html.fromHtml(res.getString(com.android.vending.R.string.subscription_expires_on, new Object[]{DateUtils.formatShortDisplayDate(libEntry.getValidUntilTimestampMs())})));
                extraInfo.setVisibility(0);
            }
        }
        convertView.setTag(parentDoc);
        convertView.setOnClickListener(this.mListener);
        convertView.findViewById(com.android.vending.R.id.loading_progress_bar).setVisibility(8);
        return convertView;
    }

    public Document getDocument(int position) {
        if (position == 0) {
            return null;
        }
        return ((MyAppsSubscriptionEntry) this.mSubscriptions.get(position - 1)).parentDoc;
    }

    void sortDocs() {
        Collections.sort(this.mSubscriptions, sSubscriptionAbcSorter);
    }

    public void clear() {
        this.mSubscriptions.clear();
        notifyDataSetChanged();
    }
}
