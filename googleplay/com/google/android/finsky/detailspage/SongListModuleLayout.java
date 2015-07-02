package com.google.android.finsky.detailspage;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.vending.R;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.billing.lightpurchase.AgeVerificationActivity;
import com.google.android.finsky.layout.PlaylistControlButtons;
import com.google.android.finsky.layout.SongSnippet;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.protos.DocDetails.SongDetails;
import com.google.android.play.image.BitmapLoader;
import java.util.List;
import java.util.Set;

public class SongListModuleLayout extends LinearLayout {
    private boolean mHasBinded;
    private TextView mHeader;
    private LinearLayout mSongsContainer;
    private TextView mSubHeader;

    public SongListModuleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean hasBinded() {
        return this.mHasBinded;
    }

    public void unbind() {
        this.mHasBinded = false;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHeader = (TextView) findViewById(R.id.header);
        this.mSubHeader = (TextView) findViewById(R.id.subheader);
        this.mSongsContainer = (LinearLayout) findViewById(R.id.songs);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void bind(NavigationManager navigationManager, BitmapLoader bitmapLoader, Document album, List<Document> songs, String title, String subtitle, boolean useActualTrackNumbers, Set<String> newPurchases, PlayStoreUiElementNode parentNode, String accountName, String highlightedSongDocId) {
        int i;
        boolean showArtistNames = shouldShowArtistNames(album, songs);
        View header = findViewById(R.id.song_list_header);
        int songsWithPreviews = 0;
        for (i = 0; i < songs.size(); i++) {
            SongDetails details = ((Document) songs.get(i)).getSongDetails();
            if (!(details == null || !details.hasPreviewUrl || TextUtils.isEmpty(details.previewUrl))) {
                songsWithPreviews++;
            }
        }
        this.mHeader.setText(title);
        if (TextUtils.isEmpty(subtitle)) {
            this.mSubHeader.setVisibility(8);
        } else {
            this.mSubHeader.setText(subtitle);
            this.mSubHeader.setVisibility(0);
        }
        PlaylistControlButtons songListControl = (PlaylistControlButtons) findViewById(R.id.song_list_control);
        if (songsWithPreviews < 2) {
            songListControl.setVisibility(8);
            header.setOnClickListener(null);
            header.setClickable(false);
        } else {
            songListControl.setVisibility(0);
            songListControl.configure(songs);
            final boolean isSongListMature = isSongListMature(songs);
            final String str = accountName;
            final PlaylistControlButtons playlistControlButtons = songListControl;
            header.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (isSongListMature && FinskyApp.get().getClientMutationCache(str).isAgeVerificationRequired()) {
                        v.getContext().startActivity(AgeVerificationActivity.createIntent(str, 2, null));
                        return;
                    }
                    playlistControlButtons.onClick(v);
                }
            });
            header.setClickable(true);
        }
        int songsToDisplay = album.getDocumentType() == 3 ? Math.min(songs.size(), 5) : songs.size();
        LayoutInflater inflater = null;
        boolean needsInit = TextUtils.isEmpty(highlightedSongDocId);
        int existingSnippets = this.mSongsContainer.getChildCount();
        i = 0;
        while (i < songsToDisplay) {
            SongSnippet snippet;
            boolean isNewSnippet;
            Document song = (Document) songs.get(i);
            if (i < existingSnippets) {
                snippet = (SongSnippet) this.mSongsContainer.getChildAt(i);
                snippet.setVisibility(0);
                isNewSnippet = false;
            } else {
                if (inflater == null) {
                    inflater = LayoutInflater.from(getContext());
                }
                snippet = (SongSnippet) inflater.inflate(R.layout.music_song_snippet, null);
                isNewSnippet = true;
            }
            snippet.setSongDetails(bitmapLoader, album, song, useActualTrackNumbers ? song.getSongDetails().trackNumber : i + 1, showArtistNames, navigationManager, newPurchases.contains(song.getDocId()), parentNode);
            if (!this.mHasBinded && song.getDocId().equals(highlightedSongDocId)) {
                snippet.setState(2);
            } else if (isNewSnippet) {
                snippet.setState(0);
            }
            if (needsInit) {
                snippet.initialize();
                needsInit = false;
            }
            if (isNewSnippet) {
                this.mSongsContainer.addView(snippet);
            } else {
                snippet.updateContentView();
            }
            i++;
        }
        for (i = songs.size(); i < existingSnippets; i++) {
            this.mSongsContainer.getChildAt(i).setVisibility(8);
        }
        this.mHasBinded = true;
    }

    private boolean isSongListMature(List<Document> songs) {
        for (int i = 0; i < songs.size(); i++) {
            if (((Document) songs.get(i)).isMature()) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldShowArtistNames(Document album, List<Document> songs) {
        String representativeTitle;
        if (album.getDisplayArtist() != null) {
            representativeTitle = album.getDisplayArtist().name;
        } else {
            representativeTitle = ((Document) songs.get(0)).getCreator();
        }
        for (int i = 0; i < songs.size(); i++) {
            if (!TextUtils.equals(representativeTitle, ((Document) songs.get(i)).getCreator())) {
                return true;
            }
        }
        return false;
    }
}
