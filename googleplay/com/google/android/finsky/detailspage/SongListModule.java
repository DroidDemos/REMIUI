package com.google.android.finsky.detailspage;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.model.DfeDetails;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.detailspage.FinskyModule.ModuleData;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.protos.DocDetails.MusicDetails;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import java.util.List;
import java.util.Set;

public class SongListModule extends FinskyModule<Data> implements ErrorListener, OnDataChangedListener, Listener {
    private boolean mNeedsRefresh;
    private DfeList mSongsRequest;

    protected static class Data extends ModuleData {
        Document album;
        String highlightedSongDocId;
        Set<String> initiallyOwnedSongs;
        Set<String> newPurchases;
        List<Document> songs;
        String songsListUrl;
        String subtitle;
        String title;
        boolean useActualTrackNumbers;

        protected Data() {
        }
    }

    public int getLayoutResId() {
        return R.layout.song_list_module;
    }

    public boolean readyForDisplay() {
        return (this.mModuleData == null || ((Data) this.mModuleData).songs == null) ? false : true;
    }

    public void onRestoreModuleData(ModuleData savedModuleData) {
        super.onRestoreModuleData(savedModuleData);
        if (this.mModuleData != null && ((Data) this.mModuleData).songs == null) {
            loadSongs();
        }
        this.mLibraries.addListener(this);
    }

    public void bindModule(boolean hasDetailsLoaded, Document detailsDoc, DfeDetails dfeDetails, Document socialDetailsDoc, DfeDetails socialDfeDetails) {
        if (hasSongList(detailsDoc) && this.mModuleData == null) {
            this.mModuleData = getData(detailsDoc);
            ((Data) this.mModuleData).newPurchases = Sets.newHashSet();
            loadSongs();
        }
    }

    protected Data getData(Document detailsDoc) {
        Data data = new Data();
        data.album = detailsDoc;
        data.useActualTrackNumbers = detailsDoc.getDocumentType() == 2;
        data.highlightedSongDocId = Uri.parse(this.mDetailsUrl).getQueryParameter("tid");
        if (hasSongListUrl(detailsDoc)) {
            data.title = detailsDoc.getCoreContentHeader();
            data.songsListUrl = detailsDoc.getCoreContentListUrl();
        } else {
            data.title = detailsDoc.getRelatedDocTypeHeader();
            data.songsListUrl = detailsDoc.getRelatedDocTypeListUrl();
        }
        return data;
    }

    public void bindView(View view) {
        SongListModuleLayout layout = (SongListModuleLayout) view;
        if (!layout.hasBinded() || this.mNeedsRefresh) {
            layout.bind(this.mNavigationManager, this.mBitmapLoader, ((Data) this.mModuleData).album, ((Data) this.mModuleData).songs, ((Data) this.mModuleData).title, ((Data) this.mModuleData).subtitle, ((Data) this.mModuleData).useActualTrackNumbers, ((Data) this.mModuleData).newPurchases, this.mParentNode, this.mDfeApi.getAccountName(), ((Data) this.mModuleData).highlightedSongDocId);
            this.mNeedsRefresh = false;
        }
    }

    public void onDestroyModule() {
        super.onDestroyModule();
        if (this.mSongsRequest != null) {
            this.mSongsRequest.removeDataChangedListener(this);
            this.mSongsRequest.removeErrorListener(this);
        }
        this.mLibraries.removeListener(this);
    }

    public void onRecycleView(View view) {
        ((SongListModuleLayout) view).unbind();
    }

    private void loadSongs() {
        this.mSongsRequest = new DfeList(this.mDfeApi, ((Data) this.mModuleData).songsListUrl, false);
        this.mSongsRequest.addDataChangedListener(this);
        this.mSongsRequest.addErrorListener(this);
        this.mSongsRequest.startLoadItems();
    }

    private static boolean isMusicDetails(Document doc) {
        int docType = doc.getDocumentType();
        return docType == 2 || docType == 3;
    }

    private static boolean hasSongListUrl(Document doc) {
        return !TextUtils.isEmpty(doc.getCoreContentListUrl());
    }

    private static boolean hasRelatedDocTypeListUrl(Document doc) {
        return !TextUtils.isEmpty(doc.getRelatedDocTypeListUrl());
    }

    protected boolean hasSongList(Document doc) {
        return isMusicDetails(doc) && (hasSongListUrl(doc) || hasRelatedDocTypeListUrl(doc));
    }

    public void onDataChanged() {
        int count = this.mSongsRequest.getCount();
        List<Document> songs = Lists.newArrayList(count);
        for (int i = 0; i < count; i++) {
            Document song = (Document) this.mSongsRequest.getItem(i);
            MusicDetails musicDetails = song.getSongDetails().details;
            if (musicDetails != null && musicDetails.durationSec > 0) {
                songs.add(song);
            }
        }
        ((Data) this.mModuleData).songs = songs;
        updateOwnedSongs();
    }

    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this.mContext, ErrorStrings.get(this.mContext, error), 0).show();
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        if (this.mModuleData != null && ((Data) this.mModuleData).songs != null) {
            updateOwnedSongs();
        }
    }

    private void updateOwnedSongs() {
        boolean firstTimeThrough = ((Data) this.mModuleData).initiallyOwnedSongs == null;
        if (firstTimeThrough) {
            ((Data) this.mModuleData).initiallyOwnedSongs = Sets.newHashSet();
        }
        for (int i = 0; i < ((Data) this.mModuleData).songs.size(); i++) {
            Document song = (Document) ((Data) this.mModuleData).songs.get(i);
            if (LibraryUtils.isOwned(song, this.mLibraries)) {
                if (firstTimeThrough) {
                    ((Data) this.mModuleData).initiallyOwnedSongs.add(song.getDocId());
                } else if (!((Data) this.mModuleData).initiallyOwnedSongs.contains(song.getDocId())) {
                    ((Data) this.mModuleData).newPurchases.add(song.getDocId());
                }
            }
        }
        this.mNeedsRefresh = true;
        this.mFinskyModuleController.refreshModule(this, true);
    }
}
