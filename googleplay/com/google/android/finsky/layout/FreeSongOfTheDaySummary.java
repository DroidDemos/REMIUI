package com.google.android.finsky.layout;

import android.accounts.Account;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.finsky.FinskyApp;
import com.google.android.finsky.api.model.Document;
import com.google.android.finsky.layout.play.PlayStoreUiElementNode;
import com.google.android.finsky.navigationmanager.NavigationManager;
import com.google.android.finsky.previews.PreviewController;
import com.google.android.finsky.previews.SongSnippetStatusListener;
import com.google.android.finsky.previews.StatusListener;
import com.google.android.finsky.protos.DocDetails.SongDetails;
import com.google.android.finsky.utils.LibraryUtils;
import com.google.android.play.layout.PlayActionButton;
import com.google.android.wallet.instrumentmanager.R;

public class FreeSongOfTheDaySummary extends RelativeLayout {
    private PlayActionButton mBuyButton;
    private final PreviewController mConnection;
    private TextView mCreator;
    private TextView mPlaybackLegend;
    private SongIndex mSongIndex;
    private final StatusListener mStatusListener;
    private TextView mTitle;

    public FreeSongOfTheDaySummary(Context context) {
        this(context, null);
    }

    public FreeSongOfTheDaySummary(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mStatusListener = new SongSnippetStatusListener() {
            protected void update(int state, boolean highlighted) {
                FreeSongOfTheDaySummary.this.mSongIndex.setState(state);
                FreeSongOfTheDaySummary.this.setHighlighted(highlighted);
                switch (state) {
                    case R.styleable.WalletImFormEditText_validatorRegexp /*2*/:
                        FreeSongOfTheDaySummary.this.mPlaybackLegend.setText(com.android.vending.R.string.track_pause);
                        return;
                    case R.styleable.WalletImFormEditText_requiredErrorString /*3*/:
                        FreeSongOfTheDaySummary.this.mPlaybackLegend.setText(com.android.vending.R.string.track_play);
                        return;
                    default:
                        FreeSongOfTheDaySummary.this.mPlaybackLegend.setText(null);
                        return;
                }
            }
        };
        this.mConnection = new PreviewController(this.mStatusListener);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mTitle = (TextView) findViewById(com.android.vending.R.id.title);
        this.mCreator = (TextView) findViewById(com.android.vending.R.id.creator);
        this.mBuyButton = (PlayActionButton) findViewById(com.android.vending.R.id.buy_button);
        this.mSongIndex = (SongIndex) findViewById(com.android.vending.R.id.song_index);
        this.mPlaybackLegend = (TextView) findViewById(com.android.vending.R.id.playback_legend);
    }

    public void showSummary(Document doc, NavigationManager navManager, PlayStoreUiElementNode parentNode) {
        this.mTitle.setText(doc.getTitle());
        this.mCreator.setText(doc.getCreator());
        Account currentAccount = FinskyApp.get().getCurrentAccount();
        final Account owner = LibraryUtils.getOwnerWithCurrentAccount(doc, FinskyApp.get().getLibraries(), currentAccount);
        if (owner != null) {
            final PlayStoreUiElementNode playStoreUiElementNode = parentNode;
            final NavigationManager navigationManager = navManager;
            final Document document = doc;
            this.mBuyButton.configure(doc.getBackend(), (int) com.android.vending.R.string.listen, new OnClickListener() {
                public void onClick(View v) {
                    FinskyApp.get().getEventLogger().logClickEvent(218, null, playStoreUiElementNode);
                    navigationManager.openItem(owner, document);
                }
            });
        } else {
            String price = doc.getFormattedPrice(1);
            if (!TextUtils.isEmpty(price)) {
                this.mBuyButton.configure(doc.getBackend(), price, navManager.getBuyImmediateClickListener(currentAccount, doc, 1, null, null, 223, null));
            }
        }
        this.mSongIndex.setState(5);
        this.mPlaybackLegend.setText(com.android.vending.R.string.track_play);
        final SongDetails song = doc.getSongDetails();
        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FreeSongOfTheDaySummary.this.mConnection.togglePlayback(song);
            }
        });
        PreviewController.setupOnBackStackChangedListener(navManager);
    }

    protected void onDetachedFromWindow() {
        this.mConnection.unbind();
        super.onDetachedFromWindow();
    }

    private void setHighlighted(boolean isHighlighted) {
        int[] paddings = new int[]{getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom()};
        Resources r = getContext().getResources();
        if (isHighlighted) {
            setBackgroundColor(r.getColor(com.android.vending.R.color.song_highlighted_background));
        } else {
            setBackgroundResource(com.android.vending.R.drawable.play_highlight_overlay_light);
        }
        setPadding(paddings[0], paddings[1], paddings[2], paddings[3]);
    }
}
