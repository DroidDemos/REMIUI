package com.google.android.finsky.activities;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.vending.R;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.android.finsky.api.model.DfeList;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.api.model.OnDataChangedListener;
import com.google.android.finsky.fragments.DetailsViewBinder;
import com.google.android.finsky.layout.SongList;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.AccountLibrary;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Libraries.Listener;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.utils.ErrorStrings;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Sets;
import com.google.android.play.image.BitmapLoader;
import java.util.List;
import java.util.Set;

public class SongListViewBinder extends DetailsViewBinder implements ErrorListener, OnDataChangedListener, Listener {
    private Document mAlbumDoc;
    private BitmapLoader mBitmapLoader;
    private Set<String> mInitialDocumentsOwned;
    private DfeList mItemListRequest;
    private Libraries mLibraries;
    private int mNumSongsToShow;
    private PlayStoreUiElementNode mParentNode;
    private boolean mUseActualTrackNumbers;

    public SongListViewBinder() {
        this.mInitialDocumentsOwned = null;
    }

    public void bind(SongList view, Document albumDoc, String title, String subtitle, String songListUrl, boolean useActualTrackNumbers, int numSongsToShow, boolean hasDetailsLoaded, Libraries libraries, BitmapLoader bitmapLoader, PlayStoreUiElementNode parentNode) {
        super.bind((View) view, title, 2);
        this.mLibraries = libraries;
        this.mBitmapLoader = bitmapLoader;
        this.mParentNode = parentNode;
        if (hasDetailsLoaded) {
            this.mLayout.setVisibility(0);
            TextView subheaderView = (TextView) this.mLayout.findViewById(R.id.subheader);
            if (TextUtils.isEmpty(subtitle)) {
                subheaderView.setVisibility(8);
            } else {
                subheaderView.setVisibility(0);
                subheaderView.setText(Html.fromHtml(subtitle));
            }
            this.mAlbumDoc = albumDoc;
            this.mNumSongsToShow = numSongsToShow;
            detachRequestListeners();
            this.mItemListRequest = new DfeList(this.mDfeApi, songListUrl, false);
            this.mItemListRequest.addDataChangedListener(this);
            this.mItemListRequest.addErrorListener(this);
            this.mItemListRequest.startLoadItems();
            this.mUseActualTrackNumbers = useActualTrackNumbers;
            this.mLibraries.removeListener(this);
            this.mLibraries.addListener(this);
            return;
        }
        this.mLayout.setVisibility(8);
    }

    public void onDataChanged() {
        populateFromLibrary(this.mLibraries);
    }

    public void onAllLibrariesLoaded() {
    }

    public void onLibraryContentsChanged(AccountLibrary library) {
        populateFromLibrary(library);
    }

    private void populateFromLibrary(Library library) {
        SongList songList = this.mLayout;
        List<Document> docs = Lists.newArrayList();
        int numTracks = Math.min(this.mNumSongsToShow, this.mItemListRequest.getCount());
        boolean firstTimeThrough = this.mInitialDocumentsOwned == null;
        if (firstTimeThrough) {
            this.mInitialDocumentsOwned = Sets.newHashSet();
        }
        for (int i = 0; i < numTracks; i++) {
            Document song = (Document) this.mItemListRequest.getItem(i);
            docs.add(song);
            if (firstTimeThrough && LibraryUtils.isOwned(song, library)) {
                this.mInitialDocumentsOwned.add(song.getDocId());
            }
        }
        songList.setListDetails(this.mNavigationManager, this.mBitmapLoader, this.mAlbumDoc, docs, this.mUseActualTrackNumbers, this.mInitialDocumentsOwned, this.mParentNode);
    }

    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this.mContext, ErrorStrings.get(this.mContext, error), 0).show();
    }

    public void onDestroyView() {
        if (this.mLibraries != null) {
            this.mLibraries.removeListener(this);
        }
        detachRequestListeners();
    }

    public void restoreInstanceState(Bundle savedInstanceState) {
        String[] ownedDocumentIds = savedInstanceState.getStringArray("SongListViewBinder.InitialDocumentsOwned");
        if (ownedDocumentIds != null) {
            this.mInitialDocumentsOwned = Sets.newHashSet();
            for (String ownedDocumentId : ownedDocumentIds) {
                this.mInitialDocumentsOwned.add(ownedDocumentId);
            }
        }
    }

    public void saveInstanceState(Bundle savedInstanceState) {
        String[] ownedDocumentIds = null;
        if (this.mInitialDocumentsOwned != null) {
            ownedDocumentIds = (String[]) this.mInitialDocumentsOwned.toArray(new String[this.mInitialDocumentsOwned.size()]);
        }
        savedInstanceState.putStringArray("SongListViewBinder.InitialDocumentsOwned", ownedDocumentIds);
    }

    private void detachRequestListeners() {
        if (this.mItemListRequest != null) {
            this.mItemListRequest.removeDataChangedListener(this);
            this.mItemListRequest.removeErrorListener(this);
        }
    }
}
