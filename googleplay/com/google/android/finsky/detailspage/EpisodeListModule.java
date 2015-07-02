package com.google.android.finsky.detailspage;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.analytics.FinskyEventLog;
import com.google.android.finsky.analytics.PlayStore.PlayStoreUiElement;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.detailspage.EpisodeListModuleLayout.EpisodeSelectionListener;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.finsky.utils.Sets;
import com.google.android.play.image.BitmapLoader;
import com.google.protobuf.nano.WireFormatNano;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EpisodeListModule extends FinskyModule<Data> implements EpisodeSelectionListener, PlayStoreUiElementNode, Listener {
    private OnDataChangedListener mEpisodesDataListener;
    private ErrorListener mEpisodesErrorListener;
    private DfeList mEpisodesRequest;
    private boolean mNeedsRefresh;
    private List<Document> mOldEpisodes;
    private Document mOldSeason;
    private PlayStoreUiElement mPlayStoreUiElement;
    private OnDataChangedListener mSeasonsDataListener;
    private ErrorListener mSeasonsErrorListener;
    private DfeList mSeasonsRequest;

    protected static class Data extends ModuleData {
        Map<String, Set<String>> initiallyOwnedEpisodes;
        String seasonListUrl;
        List<Document> seasons;
        Document selectedEpisode;
        Document selectedSeason;
        List<Document> selectedSeasonEpisodes;

        protected Data() {
        }
    }

    public EpisodeListModule() {
        this.mPlayStoreUiElement = FinskyEventLog.obtainPlayStoreUiElement(211);
        this.mSeasonsDataListener = new OnDataChangedListener() {
            public void onDataChanged() {
                int seasonCount = EpisodeListModule.this.mSeasonsRequest.getCount();
                if (seasonCount != 0) {
                    List<Document> seasons = Lists.newArrayList();
                    for (int i = 0; i < seasonCount; i++) {
                        seasons.add(EpisodeListModule.this.mSeasonsRequest.getItem(i));
                    }
                    ((Data) EpisodeListModule.this.mModuleData).seasons = seasons;
                    if (((Data) EpisodeListModule.this.mModuleData).selectedSeason == null) {
                        ((Data) EpisodeListModule.this.mModuleData).selectedSeason = (Document) seasons.get(0);
                    }
                    if (((Data) EpisodeListModule.this.mModuleData).selectedSeasonEpisodes == null) {
                        EpisodeListModule.this.loadEpisodesForSelectedSeason();
                    }
                }
            }
        };
        this.mSeasonsErrorListener = new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EpisodeListModule.this.mContext, ErrorStrings.get(EpisodeListModule.this.mContext, error), 0).show();
            }
        };
        this.mEpisodesDataListener = new OnDataChangedListener() {
            public void onDataChanged() {
                List<Document> episodes = Lists.newArrayList(EpisodeListModule.this.mEpisodesRequest.getCount());
                for (int i = 0; i < EpisodeListModule.this.mEpisodesRequest.getCount(); i++) {
                    episodes.add(EpisodeListModule.this.mEpisodesRequest.getItem(i));
                }
                ((Data) EpisodeListModule.this.mModuleData).selectedSeasonEpisodes = episodes;
                EpisodeListModule.this.refreshInitiallyOwnedEpisodes();
                EpisodeListModule.this.mOldEpisodes = null;
                EpisodeListModule.this.mOldSeason = null;
                EpisodeListModule.this.mNeedsRefresh = true;
                EpisodeListModule.this.mFinskyModuleController.refreshModule(EpisodeListModule.this, true);
            }
        };
        this.mEpisodesErrorListener = new ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                EpisodeListModule.this.mNeedsRefresh = true;
                ((Data) EpisodeListModule.this.mModuleData).selectedSeason = EpisodeListModule.this.mOldSeason;
                ((Data) EpisodeListModule.this.mModuleData).selectedSeasonEpisodes = EpisodeListModule.this.mOldEpisodes;
                EpisodeListModule.this.mFinskyModuleController.refreshModule(EpisodeListModule.this, true);
                Toast.makeText(EpisodeListModule.this.mContext, ErrorStrings.get(EpisodeListModule.this.mContext, error), 0).show();
            }
        };
    }

    private static boolean hasSeasonList(Document doc) {
        return doc.getDocumentType() == 18 && !TextUtils.isEmpty(doc.getCoreContentListUrl());
    }

    public int getLayoutResId() {
        return R.layout.episode_list_module;
    }

    public boolean readyForDisplay() {
        return (this.mModuleData == null || (((Data) this.mModuleData).selectedSeasonEpisodes == null && this.mOldEpisodes == null)) ? false : true;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null) {
            this.mLibraries.addListener(this);
            if (((Data) this.mModuleData).seasons == null) {
                this.mSeasonsRequest = new DfeList(this.mDfeApi, ((Data) this.mModuleData).seasonListUrl, false);
                this.mSeasonsRequest.addDataChangedListener(this.mSeasonsDataListener);
                this.mSeasonsRequest.addErrorListener(this.mSeasonsErrorListener);
                this.mSeasonsRequest.startLoadItems();
            } else if (((Data) this.mModuleData).selectedSeasonEpisodes == null) {
                loadEpisodesForSelectedSeason();
            }
        }
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (hasSeasonList(detailsDoc) && this.mModuleData == null) {
            this.mModuleData = new Data();
            ((Data) this.mModuleData).initiallyOwnedEpisodes = Maps.newHashMap();
            ((Data) this.mModuleData).seasonListUrl = detailsDoc.getCoreContentListUrl();
            this.mSeasonsRequest = new DfeList(this.mDfeApi, ((Data) this.mModuleData).seasonListUrl, false);
            this.mSeasonsRequest.addDataChangedListener(this.mSeasonsDataListener);
            this.mSeasonsRequest.addErrorListener(this.mSeasonsErrorListener);
            this.mSeasonsRequest.startLoadItems();
            this.mLibraries.addListener(this);
        }
    }

    public void bindView(View view) {
        EpisodeListModuleLayout layout = (EpisodeListModuleLayout) view;
        if (!layout.hasBinded() || this.mNeedsRefresh) {
            boolean z;
            Set<String> initiallyOwnedEpisodes = (Set) ((Data) this.mModuleData).initiallyOwnedEpisodes.get(((Data) this.mModuleData).selectedSeason.getDocId());
            BitmapLoader bitmapLoader = this.mBitmapLoader;
            NavigationManager navigationManager = this.mNavigationManager;
            List list = ((Data) this.mModuleData).seasons;
            Document document = ((Data) this.mModuleData).selectedSeason;
            List list2 = ((Data) this.mModuleData).selectedSeasonEpisodes;
            Document document2 = ((Data) this.mModuleData).selectedEpisode;
            Library library = this.mLibraries;
            if (this.mOldEpisodes != null) {
                z = true;
            } else {
                z = false;
            }
            layout.bind(this, bitmapLoader, this, navigationManager, list, document, list2, document2, library, initiallyOwnedEpisodes, z);
            this.mNeedsRefresh = false;
        }
    }

    public void onDestroyModule() {
        this.mLibraries.removeListener(this);
        if (this.mSeasonsRequest != null) {
            this.mSeasonsRequest.removeDataChangedListener(this.mSeasonsDataListener);
            this.mSeasonsRequest.removeErrorListener(this.mSeasonsErrorListener);
        }
        if (this.mEpisodesRequest != null) {
            this.mEpisodesRequest.removeDataChangedListener(this.mEpisodesDataListener);
            this.mEpisodesRequest.removeErrorListener(this.mEpisodesErrorListener);
        }
    }

    private void updateSeasonNode() {
        if (!(this.mPlayStoreUiElement.serverLogsCookie.length == 0 || Arrays.equals(this.mPlayStoreUiElement.serverLogsCookie, ((Data) this.mModuleData).selectedSeason.getServerLogsCookie()))) {
            this.mPlayStoreUiElement.child = PlayStoreUiElement.emptyArray();
            this.mPlayStoreUiElement.hasServerLogsCookie = false;
            this.mPlayStoreUiElement.serverLogsCookie = WireFormatNano.EMPTY_BYTES;
        }
        FinskyEventLog.setServerLogCookie(this.mPlayStoreUiElement, ((Data) this.mModuleData).selectedSeason.getServerLogsCookie());
    }

    public void onEpisodeSelected(Document episode) {
        ((Data) this.mModuleData).selectedEpisode = episode;
    }

    public void onSeasonSelected(Document season) {
        if (((Data) this.mModuleData).selectedSeason != season) {
            this.mOldSeason = ((Data) this.mModuleData).selectedSeason;
            this.mOldEpisodes = ((Data) this.mModuleData).selectedSeasonEpisodes;
            ((Data) this.mModuleData).selectedSeason = season;
            ((Data) this.mModuleData).selectedSeasonEpisodes = null;
            loadEpisodesForSelectedSeason();
            updateSeasonNode();
        }
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        refreshInitiallyOwnedEpisodes();
    }

    private void loadEpisodesForSelectedSeason() {
        if (this.mEpisodesRequest != null) {
            this.mEpisodesRequest.removeDataChangedListener(this.mEpisodesDataListener);
            this.mEpisodesRequest.removeErrorListener(this.mEpisodesErrorListener);
        }
        this.mEpisodesRequest = new DfeList(this.mDfeApi, ((Data) this.mModuleData).selectedSeason.getCoreContentListUrl(), false);
        this.mEpisodesRequest.addDataChangedListener(this.mEpisodesDataListener);
        this.mEpisodesRequest.addErrorListener(this.mEpisodesErrorListener);
        this.mEpisodesRequest.startLoadItems();
        if (this.mOldEpisodes != null) {
            this.mNeedsRefresh = true;
            this.mFinskyModuleController.refreshModule(this, false);
        }
    }

    public PlayStoreUiElement getPlayStoreUiElement() {
        return this.mPlayStoreUiElement;
    }

    public PlayStoreUiElementNode getParentNode() {
        return this.mParentNode;
    }

    public void childImpression(PlayStoreUiElementNode childNode) {
        FinskyEventLog.childImpression(this, childNode);
    }

    private void refreshInitiallyOwnedEpisodes() {
        String seasonId = ((Data) this.mModuleData).selectedSeason.getDocId();
        if (!((Data) this.mModuleData).initiallyOwnedEpisodes.containsKey(seasonId)) {
            Set<String> ownedDocumentsFromLibrary = Sets.newHashSet();
            for (Document episode : ((Data) this.mModuleData).selectedSeasonEpisodes) {
                if (LibraryUtils.isOwned(episode, this.mLibraries)) {
                    ownedDocumentsFromLibrary.add(episode.getDocId());
                }
            }
            ((Data) this.mModuleData).initiallyOwnedEpisodes.put(seasonId, ownedDocumentsFromLibrary);
        }
    }
}
