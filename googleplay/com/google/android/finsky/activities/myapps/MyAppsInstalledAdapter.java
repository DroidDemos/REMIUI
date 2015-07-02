package com.google.android.finsky.activities.myapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.MultiInstallActivity;
import com.google.android.finsky.adapters.AggregatedAdapter;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.appstate.AppStates;
import com.google.android.finsky.appstate.InstallerDataStore.InstallerData;
import com.google.android.finsky.appstate.PackageStateRepository.PackageState;
import com.google.android.finsky.config.G;
import com.google.android.finsky.installer.InstallPolicies;
import com.google.android.finsky.layout.IdentifiableLinearLayout;
import com.google.android.finsky.layout.play.FinskyHeaderListLayout;
import com.google.android.finsky.layout.play.PlayCardViewMyApps;
import com.google.android.finsky.layout.play.PlayCardViewMyAppsDownloading;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.receivers.Installer;
import com.google.android.finsky.receivers.Installer.InstallerState;
import com.google.android.finsky.utils.CorpusResourceUtils;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.PlayCardUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayActionButton;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyAppsInstalledAdapter extends BaseAdapter implements MyAppsListAdapter {
    private static final Collator sDocumentAbcCollator;
    private static final Comparator<Document> sDocumentAbcSorter;
    private final AggregatedAdapter<SectionAdapter> mAggregatedAdapter;
    private final AppStates mAppStates;
    private final BitmapLoader mBitmapLoader;
    private final BucketsChangedListener mBucketsChangedListener;
    protected Context mContext;
    private final SectionAdapter mDownloadingSectionAdapter;
    private final InstallPolicies mInstallPolicies;
    private final SectionAdapter mInstalledSectionAdapter;
    private final Installer mInstaller;
    protected final LayoutInflater mLayoutInflater;
    private final int mLeadingSpacerHeight;
    private final OnClickListener mOnClickListener;
    private PlayStoreUiElementNode mParentNode;
    private final SectionAdapter mRecentlyUpdatedSectionAdapter;
    private final List<Document> mUnsortedDocuments;
    private final SectionAdapter mUpdatesSectionAdapter;

    interface BucketsChangedListener {
        void bucketsChanged();
    }

    public enum DocumentBulkAction {
        UPDATE_ALL {
            public void run(Context ctx, MyAppsInstalledAdapter downloadsAdapter, InstallPolicies installPolicies) {
                ctx.startActivity(MultiInstallActivity.getUpdateIntent(ctx, Lists.newArrayList(downloadsAdapter.mUpdatesSectionAdapter.mDocs)));
            }

            public int getLabelId() {
                return R.string.update_all;
            }

            public boolean isVisible(MyAppsInstalledAdapter downloadsAdapter) {
                return downloadsAdapter.mDownloadingSectionAdapter.getCount() == 0;
            }

            public boolean isWaiting(MyAppsInstalledAdapter downloadsAdapter) {
                return downloadsAdapter.mInstaller.isBusy();
            }
        },
        STOP_ALL_DOWNLOADS {
            public void run(Context ctx, MyAppsInstalledAdapter downloadsAdapter, InstallPolicies installPolicies) {
                downloadsAdapter.mInstaller.cancelAll();
            }

            public int getLabelId() {
                return R.string.stop_all_downloads;
            }

            public boolean isVisible(MyAppsInstalledAdapter downloadsAdapter) {
                Installer installer = FinskyApp.get().getInstaller();
                for (Document doc : downloadsAdapter.mUnsortedDocuments) {
                    if (installer.getState(doc.getAppDetails().packageName).isDownloadingOrInstalling()) {
                        return true;
                    }
                }
                return false;
            }

            public boolean isWaiting(MyAppsInstalledAdapter downloadsAdapter) {
                return false;
            }
        };

        public abstract int getLabelId();

        public abstract boolean isVisible(MyAppsInstalledAdapter myAppsInstalledAdapter);

        public abstract boolean isWaiting(MyAppsInstalledAdapter myAppsInstalledAdapter);

        public abstract void run(Context context, MyAppsInstalledAdapter myAppsInstalledAdapter, InstallPolicies installPolicies);
    }

    private enum DocumentState {
        DOWNLOADING,
        INSTALLED,
        UPDATE_AVAILABLE,
        RECENTLY_UPDATED
    }

    private class SectionAdapter extends BaseAdapter {
        private final List<Document> mDocs;
        private final DocumentState mDocumentState;
        private final DocumentBulkAction mHeaderAction;
        private final String mHeaderIdentifier;
        private final int mHeaderTextId;

        public SectionAdapter(DocumentState documentState, int headerTextId, DocumentBulkAction headerAction) {
            this.mDocumentState = documentState;
            this.mHeaderTextId = headerTextId;
            this.mHeaderAction = headerAction;
            this.mHeaderIdentifier = "**HEADER" + documentState.ordinal();
            this.mDocs = new ArrayList();
        }

        void clearDocs() {
            this.mDocs.clear();
        }

        void addDoc(Document doc) {
            this.mDocs.add(doc);
        }

        void sortDocs() {
            Collections.sort(this.mDocs, MyAppsInstalledAdapter.sDocumentAbcSorter);
        }

        public boolean isVisible() {
            return this.mDocs.size() > 0;
        }

        public int getCount() {
            return isVisible() ? this.mDocs.size() + 1 : 0;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public Object getItem(int position) {
            if (position == 0) {
                return null;
            }
            return this.mDocs.get(position - 1);
        }

        public int getViewTypeCount() {
            return 4;
        }

        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;
            }
            return this.mDocumentState == DocumentState.DOWNLOADING ? 2 : 1;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);
            boolean isLastInSection = position == getCount() + -1;
            switch (type) {
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImButtonBar_capitalizeButtonText /*0*/:
                    return MyAppsInstalledAdapter.this.getHeaderView(position, convertView, parent, this);
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorErrorString /*1*/:
                    return MyAppsInstalledAdapter.this.getDocView((Document) getItem(position), convertView, parent, this.mDocumentState, isLastInSection);
                case com.google.android.wallet.instrumentmanager.R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                    return MyAppsInstalledAdapter.this.getDownloadingDocView((Document) getItem(position), convertView, parent);
                default:
                    throw new IllegalStateException("Null row view for position " + position + " and type " + type);
            }
        }
    }

    private static final class SectionHeaderHolder {
        public final PlayActionButton bulkActionButton;
        public final TextView countView;
        public final View loadingProgress;
        public final TextView titleView;

        public SectionHeaderHolder(View convertView) {
            this.titleView = (TextView) convertView.findViewById(R.id.header_text);
            this.bulkActionButton = (PlayActionButton) convertView.findViewById(R.id.bulk_action_button);
            this.loadingProgress = convertView.findViewById(R.id.loading_progress);
            this.countView = (TextView) convertView.findViewById(R.id.count);
            convertView.setTag(this);
        }
    }

    static {
        sDocumentAbcCollator = Collator.getInstance();
        sDocumentAbcSorter = new Comparator<Document>() {
            public int compare(Document doc1, Document doc2) {
                int titleComp = MyAppsInstalledAdapter.sDocumentAbcCollator.compare(doc1.getTitle(), doc2.getTitle());
                return titleComp != 0 ? titleComp : doc1.getAppDetails().packageName.compareTo(doc2.getAppDetails().packageName);
            }
        };
    }

    public MyAppsInstalledAdapter(Context context, Installer installer, InstallPolicies installThresholds, AppStates appStates, BitmapLoader bitmapLoader, OnClickListener onClickListener, BucketsChangedListener bucketsChangedListener, PlayStoreUiElementNode parentNode) {
        this.mUnsortedDocuments = Lists.newArrayList();
        this.mParentNode = null;
        this.mContext = context;
        this.mInstaller = installer;
        this.mInstallPolicies = installThresholds;
        this.mAppStates = appStates;
        this.mOnClickListener = onClickListener;
        this.mBitmapLoader = bitmapLoader;
        this.mParentNode = parentNode;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mBucketsChangedListener = bucketsChangedListener;
        this.mDownloadingSectionAdapter = new SectionAdapter(DocumentState.DOWNLOADING, R.string.downloading_section, DocumentBulkAction.STOP_ALL_DOWNLOADS);
        this.mUpdatesSectionAdapter = new SectionAdapter(DocumentState.UPDATE_AVAILABLE, R.string.updates_available_section, DocumentBulkAction.UPDATE_ALL);
        this.mRecentlyUpdatedSectionAdapter = new SectionAdapter(DocumentState.RECENTLY_UPDATED, R.string.recently_updated_section, null);
        this.mInstalledSectionAdapter = new SectionAdapter(DocumentState.INSTALLED, R.string.installed_section, null);
        this.mAggregatedAdapter = new AggregatedAdapter(new SectionAdapter[]{this.mDownloadingSectionAdapter, this.mUpdatesSectionAdapter, this.mRecentlyUpdatedSectionAdapter, this.mInstalledSectionAdapter});
        this.mLeadingSpacerHeight = FinskyHeaderListLayout.getMinimumHeaderHeight(context, 0, 0);
    }

    public long getItemId(int index) {
        return (long) index;
    }

    public int getItemViewType(int position) {
        if (position == 0) {
            return 3;
        }
        return this.mAggregatedAdapter.getItemViewType(position - 1);
    }

    public int getViewTypeCount() {
        return 4;
    }

    public boolean areAllItemsEnabled() {
        return this.mAggregatedAdapter.areAllItemsEnabled();
    }

    public boolean isEnabled(int position) {
        if (position == 0) {
            return false;
        }
        return this.mAggregatedAdapter.isEnabled(position - 1);
    }

    public int getCount() {
        int count = this.mAggregatedAdapter.getCount();
        if (count == 0) {
            return 0;
        }
        return count + 1;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (position != 0) {
            return this.mAggregatedAdapter.getView(position - 1, convertView, parent);
        }
        if (convertView == null) {
            convertView = this.mLayoutInflater.inflate(R.layout.play_list_vspacer, parent, false);
        }
        convertView.getLayoutParams().height = this.mLeadingSpacerHeight;
        convertView.setId(R.id.play_header_spacer);
        return convertView;
    }

    public Object getItem(int position) {
        if (position == 0) {
            return null;
        }
        return this.mAggregatedAdapter.getItem(position - 1);
    }

    private View getDocView(Document doc, View convertView, ViewGroup parent, DocumentState documentState, boolean isLastInSection) {
        if (convertView == null) {
            convertView = this.mLayoutInflater.inflate(R.layout.play_card_myapps, parent, false);
        }
        PlayCardViewMyApps entry = (PlayCardViewMyApps) convertView;
        PlayCardUtils.bindCard(entry, doc, "my_apps:installed", this.mBitmapLoader, null, this.mParentNode);
        entry.setOnClickListener(this.mOnClickListener);
        entry.setArchivable(false, null);
        entry.setTag(doc);
        entry.setIdentifier(doc.getBackendDocId());
        return convertView;
    }

    private View getDownloadingDocView(Document doc, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.mLayoutInflater.inflate(R.layout.play_card_myapps_downloading, parent, false);
        }
        if (!(convertView instanceof PlayCardViewMyAppsDownloading)) {
            dumpState();
        }
        PlayCardViewMyAppsDownloading card = (PlayCardViewMyAppsDownloading) convertView;
        PlayCardUtils.bindCard(card, doc, "my_apps:installed", this.mBitmapLoader, null, this.mParentNode);
        card.bindProgress(this.mInstaller.getProgress(doc.getAppDetails().packageName));
        card.setOnClickListener(this.mOnClickListener);
        int titleDescriptionResourceId = CorpusResourceUtils.getTitleContentDescriptionResourceId(this.mContext.getResources(), 1);
        TextView cardTitle = card.getTitle();
        if (cardTitle != null) {
            cardTitle.setContentDescription(this.mContext.getString(titleDescriptionResourceId, new Object[]{doc.getTitle()}));
        }
        card.setTag(doc);
        card.setIdentifier(doc.getBackendDocId());
        return convertView;
    }

    private View getHeaderView(int position, View convertView, ViewGroup parent, SectionAdapter sectionAdapter) {
        IdentifiableLinearLayout header = (IdentifiableLinearLayout) convertView;
        if (header == null) {
            header = (IdentifiableLinearLayout) this.mLayoutInflater.inflate(R.layout.my_apps_section_header, parent, false);
        }
        SectionHeaderHolder headerTag = header.getTag();
        if (headerTag instanceof Document) {
            dumpState();
        }
        SectionHeaderHolder holder = headerTag;
        if (holder == null) {
            holder = new SectionHeaderHolder(header);
        }
        holder.titleView.setText(this.mContext.getString(sectionAdapter.mHeaderTextId));
        holder.bulkActionButton.setVisibility(8);
        holder.loadingProgress.setVisibility(8);
        final DocumentBulkAction sectionAction = sectionAdapter.mHeaderAction;
        boolean isActionVisible = sectionAction != null && sectionAction.isVisible(this);
        boolean isActionWaiting = sectionAction != null && sectionAction.isWaiting(this);
        if (isActionWaiting) {
            holder.loadingProgress.setVisibility(0);
        } else if (isActionVisible) {
            holder.bulkActionButton.setVisibility(0);
            holder.bulkActionButton.configure(3, this.mContext.getString(sectionAction.getLabelId()), new OnClickListener() {
                public void onClick(View v) {
                    sectionAction.run(MyAppsInstalledAdapter.this.mContext, MyAppsInstalledAdapter.this, MyAppsInstalledAdapter.this.mInstallPolicies);
                }
            });
        }
        boolean isCountVisible = isActionWaiting || !isActionVisible;
        holder.countView.setVisibility(isCountVisible ? 0 : 8);
        if (isCountVisible) {
            holder.countView.setText(Integer.toString(sectionAdapter.getCount() - 1));
        }
        header.setIdentifier(sectionAdapter.mHeaderIdentifier);
        return header;
    }

    public void addDocs(Collection<Document> docs) {
        this.mUnsortedDocuments.clear();
        this.mUnsortedDocuments.addAll(docs);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        putDocsInBuckets();
        this.mAggregatedAdapter.notifyDataSetChanged();
        super.notifyDataSetChanged();
        if (this.mBucketsChangedListener != null) {
            this.mBucketsChangedListener.bucketsChanged();
        }
    }

    public void notifyDataSetInvalidated() {
        putDocsInBuckets();
        this.mAggregatedAdapter.notifyDataSetInvalidated();
        super.notifyDataSetInvalidated();
        if (this.mBucketsChangedListener != null) {
            this.mBucketsChangedListener.bucketsChanged();
        }
    }

    public void invalidateInstallingItem(String packageName, ListView list) {
        if (list != null) {
            Document target = null;
            int totalCount = this.mDownloadingSectionAdapter.getCount();
            for (int position = 1; position < totalCount; position++) {
                Document doc = (Document) this.mDownloadingSectionAdapter.getItem(position);
                if (packageName.equals(doc.getAppDetails().packageName)) {
                    target = doc;
                    break;
                }
            }
            if (target != null) {
                int firstPosition = list.getFirstVisiblePosition();
                int lastPosition = list.getLastVisiblePosition();
                for (int i = firstPosition; i <= lastPosition; i++) {
                    if (target == list.getItemAtPosition(i)) {
                        list.getAdapter().getView(i, list.getChildAt(i - firstPosition), list);
                        return;
                    }
                }
            }
        }
    }

    private void putDocsInBuckets() {
        SectionAdapter[] adapters = (SectionAdapter[]) this.mAggregatedAdapter.getAdapters();
        for (SectionAdapter adapter : adapters) {
            adapter.clearDocs();
        }
        Collection<Document> skipped = Lists.newArrayList();
        long recentlyUpdatedTimeThresholdMs = System.currentTimeMillis() - ((Long) G.recentlyUpdatedWindowSizeMs.get()).longValue();
        for (Document doc : this.mUnsortedDocuments) {
            String packageName = doc.getAppDetails().packageName;
            PackageState packageState = this.mAppStates.getPackageStateRepository().get(packageName);
            InstallerState installerState = this.mInstaller.getState(packageName);
            InstallerData installerData = this.mAppStates.getInstallerDataStore().get(packageName);
            if (installerState.isDownloadingOrInstalling()) {
                this.mDownloadingSectionAdapter.addDoc(doc);
            } else if (packageState == null || packageState.isDisabled) {
                skipped.add(doc);
            } else {
                if (this.mInstallPolicies.canUpdateApp(packageState, doc)) {
                    this.mUpdatesSectionAdapter.addDoc(doc);
                } else if (installerData == null || installerData.getLastUpdateTimestampMs() <= recentlyUpdatedTimeThresholdMs) {
                    this.mInstalledSectionAdapter.addDoc(doc);
                } else {
                    this.mRecentlyUpdatedSectionAdapter.addDoc(doc);
                }
            }
        }
        this.mUnsortedDocuments.removeAll(skipped);
        for (SectionAdapter adapter2 : adapters) {
            adapter2.sortDocs();
        }
    }

    public Document getDocument(int position) {
        return (Document) getItem(position);
    }

    public static Document getViewDoc(View v) {
        Object tag = v.getTag();
        if (tag instanceof Document) {
            return (Document) tag;
        }
        return null;
    }

    private void dumpState() {
        FinskyLog.d("****** INSTALLED ADAPTER START ******", new Object[0]);
        FinskyLog.d("Total docs: %d", Integer.valueOf(this.mUnsortedDocuments.size()));
        StringBuilder countBuilder = new StringBuilder("Total items: ");
        countBuilder.append(getCount());
        countBuilder.append(" [ ");
        for (SectionAdapter adapter : (SectionAdapter[]) this.mAggregatedAdapter.getAdapters()) {
            countBuilder.append(adapter.getCount());
            countBuilder.append(" ");
        }
        countBuilder.append("]");
        FinskyLog.d(countBuilder.toString(), new Object[0]);
        StringBuilder viewTypeBuilder = new StringBuilder("Index translation: ");
        for (int i = 0; i < getCount(); i++) {
            viewTypeBuilder.append(i);
            viewTypeBuilder.append(":");
            viewTypeBuilder.append(getItemViewType(i));
            viewTypeBuilder.append(" ");
        }
        FinskyLog.d(viewTypeBuilder.toString(), new Object[0]);
        FinskyLog.d("****** INSTALLED ADAPTER  END  ******", new Object[0]);
        this.mAggregatedAdapter.dumpState();
    }
}
