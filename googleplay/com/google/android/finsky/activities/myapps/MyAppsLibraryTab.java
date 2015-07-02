package com.google.android.finsky.activities.myapps;

import android.os.Build.VERSION;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.Checkable;
import android.widget.ListView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.activities.AuthenticatedActivity;
import com.google.android.finsky.api.DfeApi;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.DfeToc;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.installer.InstallerListener.InstallerPackageEvent;
import com.google.android.finsky.layout.play.GenericUiElementNode;
import com.google.android.finsky.layout.play.PlayCardViewMyApps.OnArchiveActionListener;
import com.google.android.finsky.layout.play.PlayListView;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.ObjectMap;
import com.google.android.finsky.utils.UiUtils;
import com.google.android.play.image.BitmapLoader;
import com.google.android.play.layout.PlayActionButton;
import java.util.List;

public class MyAppsLibraryTab extends MyAppsTab<DfeList> implements OnLongClickListener {
    public static final boolean SUPPORTS_MULTI_CHOICE;
    private final AccountLibrary mAccountLibrary;
    private final MyAppsLibraryAdapter mAdapter;
    private ActionMode mCurrentActionMode;
    private final MyAppsTabbedFragment mFragment;
    private ViewGroup mLibraryView;
    private boolean mListInitialized;
    private PlayListView mListView;
    private ObjectMap mSavedInstanceState;

    private static class MultiChoiceListener implements MultiChoiceModeListener {
        private final MyAppsLibraryTab mTab;

        private MultiChoiceListener(MyAppsLibraryTab tab) {
            this.mTab = tab;
        }

        public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
            int selectedCount = getCheckedDocuments().size();
            this.mTab.mCurrentActionMode.setTitle(this.mTab.mAuthenticatedActivity.getResources().getQuantityString(R.plurals.my_apps_selected, selectedCount, new Object[]{Integer.valueOf(selectedCount)}));
        }

        public boolean onCreateActionMode(final ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.my_apps_actions, menu);
            this.mTab.mCurrentActionMode = actionMode;
            final MenuItem item = menu.findItem(R.id.install_button);
            View actionView = this.mTab.mAuthenticatedActivity.getLayoutInflater().inflate(R.layout.my_apps_bulk_install_button, null);
            PlayActionButton button = (PlayActionButton) actionView.findViewById(R.id.bulk_install_button);
            button.setActionStyle(1);
            button.configure(3, (int) R.string.install, new OnClickListener() {
                public void onClick(View v) {
                    MultiChoiceListener.this.onActionItemClicked(actionMode, item);
                }
            });
            item.setActionView(actionView);
            return true;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (this.mTab.mFragment.handleMenuItem(getCheckedDocuments(), menuItem)) {
                return true;
            }
            return false;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.mTab.mCurrentActionMode = null;
            this.mTab.getListView().post(new Runnable() {
                public void run() {
                    ListView listView = MultiChoiceListener.this.mTab.getListView();
                    MultiChoiceListener.this.mTab.mAdapter.setMultiChoiceMode(false);
                    listView.clearChoices();
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        View child = listView.getChildAt(i);
                        if (child instanceof Checkable) {
                            ((Checkable) child).setChecked(false);
                        }
                    }
                    listView.setChoiceMode(0);
                }
            });
        }

        private List<Document> getCheckedDocuments() {
            SparseBooleanArray checkedItems = this.mTab.getListView().getCheckedItemPositions();
            List<Document> docs = Lists.newArrayList(checkedItems.size());
            for (int i = 0; i < checkedItems.size(); i++) {
                int position = checkedItems.keyAt(i);
                if (checkedItems.get(position)) {
                    docs.add(this.mTab.getAdapter().getDocument(position));
                }
            }
            return docs;
        }
    }

    static {
        SUPPORTS_MULTI_CHOICE = VERSION.SDK_INT >= 11;
    }

    public MyAppsLibraryTab(AuthenticatedActivity authenticatedActivity, DfeApi dfeApi, DfeToc dfeToc, NavigationManager navigationManager, BitmapLoader bitmapLoader, MyAppsTabbedFragment fragment, AccountLibrary accountLibrary, PlayStoreUiElementNode parentNode) {
        super(authenticatedActivity, dfeApi, dfeToc, navigationManager);
        this.mListInitialized = false;
        this.mSavedInstanceState = new ObjectMap();
        this.mFragment = fragment;
        this.mAccountLibrary = accountLibrary;
        AuthenticatedActivity authenticatedActivity2 = authenticatedActivity;
        NavigationManager navigationManager2 = navigationManager;
        this.mAdapter = new MyAppsLibraryAdapter(authenticatedActivity2, navigationManager2, FinskyApp.get().getToc(), this.mLibraries, FinskyApp.get().getPackageInfoRepository(), this.mInstaller, bitmapLoader, this, new OnArchiveActionListener() {
            public void onArchiveAction(Document doc) {
                MyAppsLibraryTab.this.mFragment.confirmArchiveDocs(Lists.newArrayList(doc));
            }
        }, this, new GenericUiElementNode(405, null, null, parentNode));
        this.mAdapter.showAccountSwitcher();
    }

    public View getView(int backendId) {
        if (this.mLibraryView == null) {
            this.mLibraryView = (ViewGroup) this.mLayoutInflater.inflate(R.layout.my_apps_library, null);
        }
        return this.mLibraryView;
    }

    public void onDataChanged() {
        super.onDataChanged();
        if (!this.mListInitialized) {
            this.mListInitialized = true;
            this.mListView = (PlayListView) this.mLibraryView.findViewById(R.id.my_apps_content_list);
            int horizontalPadding = UiUtils.getGridHorizontalPadding(this.mListView.getResources());
            this.mListView.setPadding(horizontalPadding, this.mListView.getPaddingTop(), horizontalPadding, this.mListView.getPaddingBottom());
            this.mListView.setAnimateChanges(true);
            this.mListView.setAdapter(this.mAdapter);
            this.mListView.setItemsCanFocus(true);
            if (SUPPORTS_MULTI_CHOICE) {
                this.mListView.setMultiChoiceModeListener(new MultiChoiceListener());
            }
            if (this.mSavedInstanceState.containsKey("MyAppsTab.KeyListParcel")) {
                this.mListView.onRestoreInstanceState((Parcelable) this.mSavedInstanceState.get("MyAppsTab.KeyListParcel"));
            }
            configureEmptyUI(true, R.string.empty_myapps_description_all);
        }
        syncViewToState();
        this.mAdapter.onDataChanged();
    }

    protected View getFullView() {
        return this.mLibraryView;
    }

    protected void retryFromError() {
        ((DfeList) this.mDfeModel).resetItems();
        ((DfeList) this.mDfeModel).clearTransientState();
        ((DfeList) this.mDfeModel).startLoadItems();
    }

    protected void requestData() {
        clearState();
        this.mDfeModel = getLibraryList();
        ((DfeList) this.mDfeModel).addDataChangedListener(this);
        ((DfeList) this.mDfeModel).addErrorListener(this);
        ((DfeList) this.mDfeModel).startLoadItems();
        this.mAdapter.setDfeList((DfeList) this.mDfeModel);
    }

    private DfeList getLibraryList() {
        DfeList dfeList;
        String libraryUrl = this.mDfeApi.getLibraryUrl(3, AccountLibrary.LIBRARY_ID_APPS, 1, this.mAccountLibrary.getServerToken(AccountLibrary.LIBRARY_ID_APPS));
        if (this.mSavedInstanceState != null && this.mSavedInstanceState.containsKey("MyAppsLibraryTab.ListData")) {
            dfeList = (DfeList) this.mSavedInstanceState.get("MyAppsLibraryTab.ListData");
            if (libraryUrl.equals(dfeList.getInitialUrl())) {
                dfeList.setDfeApi(this.mDfeApi);
                return dfeList;
            }
        }
        dfeList = new DfeList(this.mDfeApi, libraryUrl, true);
        dfeList.filterDocId("com.google.android.gms");
        return dfeList;
    }

    protected Document getDocumentForView(View view) {
        return MyAppsLibraryAdapter.getViewDoc(view);
    }

    protected ListView getListView() {
        return this.mListView;
    }

    protected MyAppsListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void onInstallPackageEvent(String packageName, InstallerPackageEvent event, int statusCode) {
        if (event == InstallerPackageEvent.INSTALLED || event == InstallerPackageEvent.UNINSTALLED) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
    }

    public void onRestoreInstanceState(ObjectMap savedInstanceState) {
        if (savedInstanceState != null) {
            this.mSavedInstanceState = savedInstanceState;
        }
    }

    public ObjectMap onSaveInstanceState() {
        if (this.mDfeModel != null && ((DfeList) this.mDfeModel).isReady()) {
            this.mSavedInstanceState.put("MyAppsLibraryTab.ListData", this.mDfeModel);
        }
        if (this.mListView != null) {
            this.mSavedInstanceState.put("MyAppsTab.KeyListParcel", this.mListView.onSaveInstanceState());
        }
        return this.mSavedInstanceState;
    }

    public void onClick(View view) {
        if (this.mListView.getChoiceMode() != 3) {
            super.onClick(view);
            return;
        }
        int position = getPositionForView(view);
        this.mListView.setItemChecked(position, !this.mListView.isItemChecked(position));
    }

    public boolean onLongClick(View view) {
        boolean z = false;
        if (!SUPPORTS_MULTI_CHOICE) {
            return false;
        }
        if (this.mListView.getChoiceMode() != 3) {
            this.mAdapter.setMultiChoiceMode(true);
            this.mListView.setChoiceMode(3);
        }
        int position = getPositionForView(view);
        if (position == -1) {
            return true;
        }
        PlayListView playListView = this.mListView;
        if (!this.mListView.isItemChecked(position)) {
            z = true;
        }
        playListView.setItemChecked(position, z);
        return true;
    }

    public void setTabSelected(boolean isSelected) {
        super.setTabSelected(isSelected);
        if (!isSelected) {
            finishActiveMode();
        }
    }

    protected boolean finishActiveMode() {
        if (this.mCurrentActionMode == null) {
            return false;
        }
        this.mCurrentActionMode.finish();
        return true;
    }

    public void removeItems(List<String> docids) {
        for (int i = 0; i < docids.size(); i++) {
            for (int j = 0; j < ((DfeList) this.mDfeModel).getCount(); j++) {
                if (((String) docids.get(i)).equals(((Document) ((DfeList) this.mDfeModel).getItem(j)).getDocId())) {
                    ((DfeList) this.mDfeModel).removeItem(j);
                    break;
                }
            }
        }
        this.mAdapter.notifyDataSetChanged();
    }
}
