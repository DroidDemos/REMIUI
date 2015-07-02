package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.lightpurchase.AgeVerificationActivity;
import com.google.android.finsky.layout.actionbar.FinskySearchToolbar;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.library.Libraries;
import com.google.android.finsky.library.Library;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocDetails.MusicDetails;
import com.google.android.finsky.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.FinskyLog;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.finsky.utils.Lists;
import com.google.android.finsky.utils.Maps;
import com.google.android.play.image.BitmapLoader;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SongList extends LinearLayout {
    private String mHighlightedSongDocId;
    private ScrollView mParent;
    private final Runnable mScrollRunnable;
    private final Map<String, SongSnippet> mSongSnippets;
    private LinearLayout mSongsContainer;

    public SongList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSongSnippets = Maps.newHashMap();
        this.mScrollRunnable = new Runnable() {
            public void run() {
                SongSnippet song = (SongSnippet) SongList.this.mSongSnippets.get(SongList.this.mHighlightedSongDocId);
                if (song != null) {
                    song.setState(2);
                    if (SongList.this.mParent == null) {
                        FinskyLog.d("Unable to scroll the highlighted song into view.", new Object[0]);
                        return;
                    }
                    Rect position = new Rect();
                    if (!song.getLocalVisibleRect(position)) {
                        SongList.this.mParent.scrollTo(position.left, position.top - FinskySearchToolbar.getToolbarHeight(SongList.this.getContext()));
                    }
                }
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mSongsContainer = (LinearLayout) findViewById(R.id.songs);
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.mScrollRunnable);
        super.onDetachedFromWindow();
    }

    public void setListDetails(NavigationManager navigationManager, BitmapLoader bitmapLoader, Document album, List<Document> songs, boolean useActualTrackNumbers, Set<String> initiallyOwnedDocuments, PlayStoreUiElementNode parentNode) {
        if (!this.mSongSnippets.isEmpty()) {
            this.mSongSnippets.clear();
            this.mSongsContainer.removeAllViews();
        }
        if (songs == null || songs.isEmpty()) {
            hideUi();
            return;
        }
        boolean showArtistNames = shouldShowArtistNames(album, songs);
        final PlaylistControlButtons songListControl = (PlaylistControlButtons) findViewById(R.id.song_list_control);
        List<Document> songList = Lists.newArrayList();
        for (Document song : songs) {
            Document song2;
            MusicDetails musicDetails = song2.getSongDetails().details;
            if (musicDetails != null && musicDetails.durationSec > 0) {
                songList.add(song2);
            }
        }
        Libraries libraries = FinskyApp.get().getLibraries();
        final Account account = FinskyApp.get().getCurrentAccount();
        final Context context = getContext();
        View header = findViewById(R.id.song_list_header);
        int songsWithPreviews = 0;
        for (Document songDoc : songList) {
            SongDetails details = songDoc.getSongDetails();
            if (!(details == null || !details.hasPreviewUrl || TextUtils.isEmpty(details.previewUrl))) {
                songsWithPreviews++;
            }
        }
        if (songsWithPreviews >= 2) {
            songListControl.setVisibility(0);
            songListControl.configure(songList);
            final boolean isMature = isSongListMature(songList);
            header.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (isMature && FinskyApp.get().getClientMutationCache(account.name).isAgeVerificationRequired()) {
                        context.startActivity(AgeVerificationActivity.createIntent(account.name, 2, null));
                        return;
                    }
                    songListControl.onClick(v);
                }
            });
            header.setClickable(true);
        } else {
            songListControl.setVisibility(8);
            header.setOnClickListener(null);
            header.setClickable(false);
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean hasInitializedSnippet = false;
        int i = 0;
        while (i < songs.size()) {
            song2 = (Document) songs.get(i);
            SongSnippet snippet = (SongSnippet) inflater.inflate(R.layout.music_song_snippet, this, false);
            int trackNumber = useActualTrackNumbers ? song2.getSongDetails().trackNumber : i + 1;
            boolean isNewPurchase = LibraryUtils.isOwned(song2, (Library) libraries) && !initiallyOwnedDocuments.contains(song2.getDocId());
            snippet.setSongDetails(bitmapLoader, album, song2, trackNumber, showArtistNames, navigationManager, isNewPurchase, parentNode);
            this.mSongsContainer.addView(snippet);
            this.mSongSnippets.put(song2.getDocId(), snippet);
            if (!hasInitializedSnippet && snippet.isPlayable()) {
                snippet.initialize();
                hasInitializedSnippet = true;
            }
            i++;
        }
        highlightSong();
    }

    private boolean isSongListMature(List<Document> songList) {
        for (Document songDoc : songList) {
            if (songDoc.isMature()) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldShowArtistNames(Document album, List<Document> songs) {
        String representativeTitle;
        if (album == null || album.getDisplayArtist() == null) {
            representativeTitle = ((Document) songs.get(0)).getCreator();
        } else {
            representativeTitle = album.getDisplayArtist().name;
        }
        for (Document song : songs) {
            if (!TextUtils.equals(representativeTitle, song.getCreator())) {
                return true;
            }
        }
        return false;
    }

    public void setHighlightedSong(String songDocumentId, ScrollView parent) {
        this.mHighlightedSongDocId = songDocumentId;
        this.mParent = parent;
    }

    private void highlightSong() {
        if (this.mSongSnippets.containsKey(this.mHighlightedSongDocId)) {
            for (String songDocId : this.mSongSnippets.keySet()) {
                ((SongSnippet) this.mSongSnippets.get(songDocId)).setState(0);
            }
            post(this.mScrollRunnable);
        }
    }

    private void hideUi() {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
        } else {
            setVisibility(8);
        }
    }
}
